package ArbolesDecision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import ArbolesDecision.Datasets.LabeledDataSet;
import ArbolesDecision.DecisionTree.DecisionTree;
import ArbolesDecision.Features.Featurizer;
import ArbolesDecision.Utils.FeatureSelectStrategy;
import ArbolesDecision.Utils.LabelProvider;

/**
 * Clase para crear árboles de decisión con algoritmos codiciosos
 * @param <T> Tipo del dato guardado en el dataset
 * @param <L> Tipo de label que da el LabelProvider
 */
public class GreedyTreeLearner<T,L> {
    /** 
     * Mapa para uso en cada learn, guarda el número asignado a la hora de asignar esa
     * label, útil para cuando pueden haber varias salidas repetidas como false0, false1...
     */
    private Map<L, Integer> lastRepeatedLabel = new HashMap<>();
    /** Featurizer del dataset al ejecutar learn */
    private Featurizer<T> featurizer;

    /** Estrategia a emplear para seleccionar feature en cada nivel */
    private FeatureSelectStrategy<T,L> featureSelectStrategy = null;

    /**
     * Constructor por defecto
     */
    public GreedyTreeLearner(){}

    /**
     * Asigna la estrategia a la instancia de GreedyTreeLearner
     * @param featureSelectStrategy estrategia a seleccionar
     */
    public void setFeatureSelectStrategy(FeatureSelectStrategy<T,L> featureSelectStrategy){
        this.featureSelectStrategy = featureSelectStrategy;
    }

    /**
     * Crea un árbol de decisión en base al dataset recibido y la estrategia seleccionada
     * @param dataset dataset
     * @return DecisionTree T
     */
    public DecisionTree<T> learn(LabeledDataSet<T,L> dataset){
        //Limpiamos ejecuciones anteriores
        this.featurizer = dataset.getFeaturizer();
        this.lastRepeatedLabel.clear();

        //Si no se ha asignado strategy, se asigna por defecto a tomar el primer valor de la lista
        if(this.featureSelectStrategy == null){
            this.featureSelectStrategy = (a, b) -> a.get(0);
            System.out.println("default strategy");
        }

        //Miramos las labels diferentes
        Set<L> differentLabels = new HashSet<>();
        for(T elem : dataset.getData()){
            differentLabels.add(dataset.getLabelProvider().getLabel(elem));
        }

        if(differentLabels.size() == 1){
            DecisionTree<T> tree = new DecisionTree<>();

            L label = differentLabels.iterator().next();
            //Añadimos al nodo actual la hoja de salida
            tree.node(label.toString()); //primer y unica etiqueta
            //Incrementamos el contador
            return tree;
        }

        //Contador para repeticion de labels
        for(L label : differentLabels){
            this.lastRepeatedLabel.put(label, 0);
        }


        List<String> availableFeatures = new ArrayList<>(featurizer.getFeatureNames());
        DecisionTree<T> tree = new DecisionTree<>();
        this.buildTree(tree, "root", dataset, availableFeatures);

        return tree;
    }
    
    
    /**
     * Crea un árbol de decisión en base a una colección de datos, featurizer y label provider recibidos y la estrategia seleccionada
     * @param data colección de datos
     * @param featurizer featurizer
     * @param labelProvider etiquetador
     * @return DecisionTree T
     */
    public DecisionTree<T> learn(Collection<T> data, Featurizer<T> featurizer, LabelProvider<T, L> labelProvider){
    	LabeledDataSet<T, L> dataset = new LabeledDataSet<>(featurizer, labelProvider);
    	dataset.addAll(data);
    	return learn(dataset);
    }

    /**
     * Construye el árbol recursivamente en cada nivel
     * @param <S> Tipo del featurizer obtenido, comparable
     * @param treeToCreate árbol al que se le añaden los nodos
     * @param nodeName Nombre del nodo actual
     * @param dataSet Dataset con el que se deben crear los siguientes nodos
     * @param availableFeatures Features por seleccionar
     */
    private <S extends Comparable<? super S>> void buildTree(DecisionTree<T> treeToCreate, String nodeName, LabeledDataSet<T,L> dataSet, List<String> availableFeatures){        
        Set<L> differentLabels = new HashSet<>();
        for(T elem : dataSet.getData()){
            differentLabels.add(dataSet.getLabelProvider().getLabel(elem));
        }

        //Caso de error
        if(differentLabels.size() == 0)
            return;

        //Caso de label única
        if(differentLabels.size() == 1){
            //Sacamos el único label
            L label = differentLabels.iterator().next();
            //Añadimos al nodo actual la hoja de salida
            treeToCreate.node(nodeName).withCondition(label.toString() + this.lastRepeatedLabel.get(label), p -> true); //primer y unica etiqueta
            //Incrementamos el contador
            this.lastRepeatedLabel.put(label, this.lastRepeatedLabel.get(label) + 1);
            return;
        }

        //Caso de error
        if(availableFeatures.size() == 0)
            return;

        //Sacamos una feature aleatoria y la quitamos
        String featureSelected = this.featureSelectStrategy.selectFeature(availableFeatures, dataSet);
        availableFeatures.remove(featureSelected);

        //Sacamos para cada posible valor de la feature en el dataset, su subset que verifica ese valor S
        Map<S, LabeledDataSet<T,L>> subsets = getSubSets(dataSet, featureSelected);

        for(S key : subsets.keySet()){
            // Sacamos el dataset de las personas que cumplen que la feature tiene valor S
            LabeledDataSet<T,L> tempDataSet = subsets.get(key);

            // Generamos el nombre del hijo (featurename == S)
            String childrenName = featureSelected + " == " + key.toString();

            // Creamos el predicado del hijo, que tiene que cumplir que el featurize de T de tempDataSet,
            // devuelva el valor S para la feature seleccionada
            Predicate<T> childrenPredicate = (p -> {
                S value = (S)featurizer.featurize(p, featureSelected).get(0);
                return value.compareTo(key) == 0;
            });
            
            // Añadimos el hijo a nodeName, con nombre childrenName
            treeToCreate.node(nodeName).withCondition(childrenName, childrenPredicate);
            
            //Creamos recursivamente el nodo hijo
            this.buildTree(treeToCreate, childrenName, tempDataSet, availableFeatures);
        }
    }

    /**
     * Consigue los subsets de un set para la feature recibida
     * @param <S> Tipo que maneja la feature en el featurizer
     * @param originalSet set original a partir
     * @param featureName nombre de la feature por la que partir
     * @return Map S, LabeledDataSet T,L con los subsets para cada valor posible de la feature S
     */
    private <S extends Comparable<? super S>> Map<S, LabeledDataSet<T,L>> getSubSets(LabeledDataSet<T,L> originalSet, String featureName){
        // Inicializamos el mapa y el set con los posibles valores distintos a tomar de originalSet
        Map<S, LabeledDataSet<T,L>> subsets = new HashMap<>();

        for(T e : originalSet.getData()){
            // Sacamos el valor del featurizer
            S value = (S)featurizer.featurize(e, featureName).get(0);

            // Si no existe el valor value, lo añade
            subsets.putIfAbsent(value, new LabeledDataSet<>(featurizer, originalSet.getLabelProvider()));

            // Ahora sabes que existe uno nuevo o ya existía uno, por lo que añadimos e al mapa
            subsets.get(value).add(e);
        }
        
        return subsets;
    }

}
