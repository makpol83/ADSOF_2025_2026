package ArbolesDecision.Utils.Strategies;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ArbolesDecision.Datasets.LabeledDataSet;
import ArbolesDecision.Utils.FeatureSelectStrategy;

/**
 * Implementación del método de la entropía para elegir features en el dataset
 * @param <T> Tipo del dato guardado en el dataset
 * @param <L> Tipo de label que da el LabelProvider
 */
public class Entropia<T,L> implements FeatureSelectStrategy<T,L>{

    /** Constructor default */
    public Entropia(){}

    @Override
    public String selectFeature(List<String> availableFeatures, LabeledDataSet<T,L> dataset) {
        String bestFeature = null;
        double maxGain = Double.NEGATIVE_INFINITY;

        double baseEntropy = calculateEntropyNode(dataset);
        
        for(String feature : availableFeatures){
            //Calculamos la entropía con pesos de cada feature
            double featureEntropy = calculateWeightedEntropy(dataset, feature);
            
            double infoGain = baseEntropy - featureEntropy;

            if(infoGain > maxGain){
                maxGain = infoGain;
                bestFeature = feature;
            }
        }

        return bestFeature;
    }

    /**
     * Calcula la entropía de un nodo con su subset
     * @param subset subset asociado al nodo
     * @return double
     */
    private double calculateEntropyNode(LabeledDataSet<T, L> subset){
        Map<L, Integer> frequencies = getLabelFrequency(subset);
        double entropy = 0.0;
        int n = subset.getData().size();

        for(L label : frequencies.keySet()){
            double p = frequencies.get(label) / n;
            if(p > 0){
                entropy -= p * (Math.log(p) / Math.log(2));
            }        
        }
        return entropy;
    }

    /**
     * Calcula la entropia ponderada
     * @param dataSet dataset 
     * @param featureName nombre de la feature evaluada
     * @return double
     */
    private double calculateWeightedEntropy(LabeledDataSet<T,L> dataSet, String featureName){
        Collection<LabeledDataSet<T,L>> subsets = getSubSets(dataSet, featureName);
        double entropy = 0.0;
        int n = dataSet.getData().size();

        for(LabeledDataSet<T, L> subset : subsets){
            double subsetProbabilty = (n + 0.0) / n;
            double entropyNode = calculateEntropyNode(subset);
            entropy += subsetProbabilty * entropyNode;
        }
            
        return entropy;
    }

    /**
     * Calcula la frecuencia de cada label posible en un mapa para un labeled dataset
     * @param subset subset a evaluar
     * @return Map L,Integer donde Integer es el número de veces que aparece L
     */
    private Map<L,Integer> getLabelFrequency(LabeledDataSet<T,L> subset){
        Map<L, Integer> scores = new HashMap<>();

        for(T data : subset.getData()){
            // Sacamos el valor del label
            L value = subset.getLabelProvider().getLabel(data);

            // Si no existe el valor value, lo añade
            scores.putIfAbsent(value, 0);

            // Ahora sabes que existe uno nuevo o ya existía uno, por lo que añadimos e al mapa
            scores.put(value, scores.get(value) + 1);
        }

        return scores;
    }

    /**
     * Consigue los subsets de un set para la feature recibida
     * @param <S> Tipo que maneja la feature en el featurizer
     * @param originalSet set original a partir
     * @param featureName nombre de la feature por la que partir
     * @return Collection LabeledDataSet T,L con los subsets
     */
    private <S extends Comparable<? super S>> Collection<LabeledDataSet<T,L>> getSubSets(LabeledDataSet<T,L> originalSet, String featureName){
        // Inicializamos el mapa y el set con los posibles valores distintos a tomar de originalSet
        Map<S, LabeledDataSet<T,L>> subsets = new HashMap<>();

        for(T e : originalSet.getData()){
            // Sacamos el valor del featurizer
            S value = (S)originalSet.getFeaturizer().featurize(e, featureName).get(0);

            // Si no existe el valor value, lo añade
            subsets.putIfAbsent(value, new LabeledDataSet<>(originalSet.getFeaturizer(), originalSet.getLabelProvider()));

            // Ahora sabes que existe uno nuevo o ya existía uno, por lo que añadimos e al mapa
            subsets.get(value).add(e);
        }
        
        return subsets.values();
    }
         
}