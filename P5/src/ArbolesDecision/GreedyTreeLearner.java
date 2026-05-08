package ArbolesDecision;

import java.util.ArrayList;
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

public class GreedyTreeLearner<T extends Comparable<? super T>,L> {
    private Map<L, Integer> lastRepeatedLabel = new HashMap<>();
    private Featurizer<T> featurizer;

    private FeatureSelectStrategy<T,L> featureSelectStrategy = null;

    public GreedyTreeLearner(){}

    public void setFeatureSelectStrategy(FeatureSelectStrategy<T,L> featureSelectStrategy){
        this.featureSelectStrategy = featureSelectStrategy;
    }

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

    public <S extends Comparable<? super S>> void buildTree(DecisionTree<T> treeToCreate, String nodeName, LabeledDataSet<T,L> dataSet, List<String> availableFeatures){        
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
