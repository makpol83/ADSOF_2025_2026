package ArbolesDecision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ArbolesDecision.Features.Feature;
import ArbolesDecision.Features.Featurizer;

public class Dataset<T extends Comparable<? super T>>{
    private Featurizer<T> featurizer;
    private List<T> elementsFeaturized = new ArrayList<>();
    private Map<String, Feature<?>> features = new HashMap<>();

    public Dataset(Featurizer<T> featurizer){
        this.featurizer = featurizer;
    }

    //public void add2(T elem){
    //    this.elementsFeaturized.add(elem);
    //
    //    for(Feature<?> f : this.featurizer.featurize(List.of(elem))){
    //        if(features.containsKey(f.getName())) {
    //            Collection<Feature<?>> tmp = new ArrayList(f);
    //            Feature<?> feature = features.get(f.getName());
    //            addToFeature(feature, tmp);
    //        }
    //    }
    //}


    public void add(T elem){
        this.elementsFeaturized.add(elem);
        // Obtenemos las features del nuevo elemento
        for (Feature<?> newFeature : this.featurizer.featurize(List.of(elem))) {
            String name = newFeature.getName();

            if (features.containsKey(name)) {
                // Sacamos feature y guardamos en crudo
                Feature existingFeature = features.get(name);
                existingFeature.addAll(newFeature);
            } else {
                // Si no existe, la guardamos directamente
                features.put(name, newFeature);
            }
        }
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
        for(int i = 0; i < elementsFeaturized.size(); i++){
            T dataInstance = elementsFeaturized.get(i);
            
            //Iterador que empieza en i + 1
            Iterator<T> it = elementsFeaturized.listIterator(i + 1);

            while(it.hasNext()){
                T dataToCompare = it.next();
                if(dataInstance.compareTo(dataToCompare) == 0){
                    it.remove();
                }
            }
        }

        
        this.reFeaturize(this.elementsFeaturized);
    }

    private void reFeaturize(Collection<T> elements){
        this.features.clear();
        for(T elem : elements){
            for (Feature<?> newFeature : this.featurizer.featurize(List.of(elem))) {
            String name = newFeature.getName();

            if (features.containsKey(name)) {
                // Sacamos feature y guardamos en crudo
                Feature existingFeature = features.get(name);
                existingFeature.addAll(newFeature);
            } else {
                // Si no existe, la guardamos directamente
                features.put(name, newFeature);
            }
            }
        }
    }

    public Collection<T> getData(){ return this.elementsFeaturized; }

    public Feature feature(String name){
        return this.features.get(name);
    }

    @Override
    public String toString(){
        return this.features.toString();
    }
}
