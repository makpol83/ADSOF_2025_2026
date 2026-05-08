package ArbolesDecision.Utils.Strategies;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ArbolesDecision.Datasets.LabeledDataSet;
import ArbolesDecision.Utils.FeatureSelectStrategy;

/**
 * Implementacion del método de métrica de clasificación errónea para elegir features en el dataset
 * @param <T> Tipo del dato guardado en el dataset
 * @param <L> Tipo de label que da el LabelProvider
 */
public class MetricaClasificacionErronea<T, L> implements FeatureSelectStrategy<T,L>{

    /** Constructor default */
    public MetricaClasificacionErronea(){}

    @Override
    public String selectFeature(List<String> availableFeatures, LabeledDataSet<T, L> dataset) {
        Map<String, Integer> scores = new HashMap<>();
    
        for(String feature : availableFeatures){
            Collection<LabeledDataSet<T,L>> subsets = getSubSets(dataset, feature);
            for(LabeledDataSet<T,L> group : subsets){
                L label = getMajorityLabel(group);
                int count = countNotMatchingLabel(label, group);

                scores.putIfAbsent(feature, count);
                scores.put(feature, scores.get(feature) + count);
            }
        }

        String lowest = null;
        int lowestScore = Integer.MAX_VALUE;
        for(String feature : scores.keySet()){
            if(scores.get(feature) < lowestScore){
                lowest = feature;
                lowestScore = scores.get(feature);
            }
        }

        return lowest;
    }

    /**
     * Consigue la label que más se repite en el dataset
     * @param dataSet dataset
     * @return L
     */
    private L getMajorityLabel(LabeledDataSet<T,L> dataSet){
        Map<L, Integer> scores = new HashMap<>();

        for(T data : dataSet.getData()){
            // Sacamos el valor del label
            L value = dataSet.getLabelProvider().getLabel(data);

            // Si no existe el valor value, lo añade
            scores.putIfAbsent(value, 0);

            // Ahora sabes que existe uno nuevo o ya existía uno, por lo que añadimos e al mapa
            scores.put(value, scores.get(value) + 1);
        }

        L highest = null;
        int highestScore = -1;
        for(L value : scores.keySet()){
            if(scores.get(value) > highestScore){
                highest = value;
                highestScore = scores.get(value);
            }
        }

        return highest;
    }

    /**
     * Cuenta el número de labels que no se producen en un dataset iguales a label
     * @param label label a comparar
     * @param dataSet dataset
     * @return número de labels que no coinciden
     */
    private int countNotMatchingLabel(L label, LabeledDataSet<T,L> dataSet){
        int count = 0;

        for(T elem : dataSet.getData()){
            if(dataSet.getLabelProvider().getLabel(elem).equals(label) == false)
                count++;
        }

        return count;
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
