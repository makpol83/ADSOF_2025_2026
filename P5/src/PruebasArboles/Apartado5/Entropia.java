package PruebasArboles.Apartado5;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ArbolesDecision.Datasets.LabeledDataSet;
import ArbolesDecision.Utils.FeatureSelectStrategy;

public class Entropia<T extends Comparable<? super T>,L> implements FeatureSelectStrategy<T,L>{

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

    private double calculateWeightedEntropy(LabeledDataSet<T,L> dataSet, String featureName){
        Collection<LabeledDataSet<T,L>> subsets = getSubSets(dataSet, featureName);
        double entropy = 0.0;
        int n = dataSet.getData().size();

        for(LabeledDataSet<T, L> subset : subsets){
            double subsetProbabilty = (subset.getData().size() + 0.0) / dataSet.getData().size();
            double entropyNode = calculateEntropyNode(subset);
            entropy += subsetProbabilty * entropyNode;
        }
            
        return entropy;
    }

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