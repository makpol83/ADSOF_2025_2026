package ArbolesDecision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GreedyTreeLearner<T extends Comparable<? super T>,L> {
    

    public DecisionTree<T> learn(LabeledDataSet<T,L> dataset){
        DecisionTree<T> nodeTree = new DecisionTree<>();

        List<String> availableFeatures = new ArrayList<>(dataset.getFeaturizer().getFeatureNames());
        return this.buildTree(dataset, availableFeatures);
    }

    public DecisionTree<T> buildTree(LabeledDataSet<T,L> dataSet, List<String> availableFeatures){
        Set<String> differentLabels = new HashSet<>();
        for(T elem : dataSet.getData()){
            differentLabels.add(dataSet.getLabelProvider().getLabel(elem).toString());
        }

        if(differentLabels.size() == 0)
            return null;

        if(differentLabels.size() == 1){
            
            DecisionTree<T> tree = new DecisionTree<>();
            for(String label : differentLabels){
                tree.node(label);
            }
            return tree;
        }

        //Ver esto??
        if(availableFeatures.size() == 0)
            return null;

        String featureSelected = availableFeatures.get(0);
        Map<String, LabeledDataSet<T,L>> subsets = new HashMap<>();

        DecisionTree<T> tree = new DecisionTree<>();

        tree.node(featureSelected);
        for(String keyString : subsets.keySet()){
            LabeledDataSet<T,L> tempDataSet = subsets.get(keyString);
            tree.node(keyString);
            //Hacer recursivo que añada arboles nodos a tree
        }

        return tree;
    }

    private Map<String, LabeledDataSet<T,L>> getSubSets(LabeledDataSet originalSet, String featureName){
        Map<String, LabeledDataSet<T,L>> subsets = new HashMap<>();
        Set<String> differentValuesOnFeature = new HashSet<>();
        
        for(){
            Feature<?> feature = originalSet.getFeaturizer().featurize(subsets, featureName)
        }

        for(){
            LabeledDataSet<T,L> dataSet = new LabeledDataSet<>(originalSet.getFeaturizer(), originalSet.getLabelProvider());
        }
        
        return dataSet;
    }

function buildTree(data, availableFeatures)     // objetos/datos y lista de features disponibles
    if all labels are the same:                 // todos los objetos en data se etiquetan igual
        return single node with that label

    feat := choose the best feature to split on // elegir la "mejor" feature -> de momento una aleatoria
    availableFeatures.remove(feat)              // se borra feat de la lista de features disponibles
    split data into subsets based on feat       // devolver tantos subconjuntos como valores distintos feat hay en data

    for-each subset do                          // añadir la condition "feat == value" y llamada recursiva con el subconjunto
        build subtree recursively               // de data { x in data | x.feat == value }

    return node with branches










}
