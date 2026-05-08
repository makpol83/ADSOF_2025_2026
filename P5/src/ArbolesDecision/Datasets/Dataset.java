package ArbolesDecision.Datasets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import ArbolesDecision.Features.Feature;
import ArbolesDecision.Features.Featurizer;

public class Dataset<T extends Comparable<? super T>>{
    private Featurizer<T> featurizer;
    private List<T> elementsFeaturized = new ArrayList<>();
    private Map<String, Feature<?>> features = new HashMap<>();

    public Dataset(Featurizer<T> featurizer){
        this.featurizer = featurizer;
    }

    public void add(T elem){
        this.elementsFeaturized.add(elem);
        // Obtenemos las features del nuevo elemento
        for (String featureName : this.featurizer.getFeatureNames()) {
            

            if (features.containsKey(featureName)){
                // Sacamos feature y guardamos en crudo
                addFeature(features.get(featureName), this.featurizer.featurize(elem, featureName));
            } else {
                // Si no existe, la guardamos directamente
                features.put(featureName, this.featurizer.featurize(elem, featureName));
            }
        }
    }

    private <S extends Comparable<? super S>> void addFeature(Feature<S> featureToAdd, Feature<?> featureToGet) {
        featureToAdd.add((S) featureToGet.get(0)); 
    }

    

    public void addAll(Collection<T> elems){

        for(T e : elems){
            this.add(e);
        }
    }

     public void addAll(T[] elems){

        for(T e : elems){
            this.add(e);
        }
    }
    

    public void removeDuplicates(){
        Set<T> withoutDuplicates = new TreeSet<>(this.elementsFeaturized);

        this.elementsFeaturized.clear();
        this.features.clear();
        this.addAll(withoutDuplicates);
    }

    public Collection<T> getData(){ return this.elementsFeaturized; }

    public <S extends Comparable<? super S>> Feature<S> feature(String name){
        return (Feature<S>)this.features.get(name);
    }

    public Featurizer<T> getFeaturizer(){
        return this.featurizer;
    }

    @Override
    public String toString(){
        return this.features.toString();
    }
}
