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
    private Collection<T> elementsFeaturized = new ArrayList<>();
    private Map<String, Feature<?>> features = new HashMap<>();

    public Dataset(Featurizer<T> featurizer){
        this.featurizer = featurizer;
    }

    public void addAll(Collection<T> elems){
        for(T e : elems){
            for(Feature<?> f : featurizer.featurize(e)){
                if(!features.containsKey(featurizer.name(f)))
                    features.put(featurizer.name(f), f);
                else
                    features.get(featurizer.name(f)).addAll(f);

            }
        }
        
    }
    

    public void removeDuplicates(){
        for(int i = 0; i < this.size(); i++){
            T dataInstance = this.get(i);
            
            //Iterador que empieza en i + 1
            Iterator<T> it = this.listIterator(i + 1);

            while(it.hasNext()){
                T dataToCompare = it.next();
                if(dataInstance.compareTo(dataToCompare) == 0){
                    it.remove();
                }
            }
        }
    }

    public Collection<T> getData(){ return this.elementsFeaturized; }

    @Override
    public String toString(){
        return "";
    }
}
