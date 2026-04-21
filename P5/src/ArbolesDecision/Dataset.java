package ArbolesDecision;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ArbolesDecision.Features.Feature;
import ArbolesDecision.Features.Featurizer;

public class Dataset<T extends Comparable<? super T>> extends ArrayList<T>{
    private Featurizer<T> featurizer;

    public Dataset(Featurizer<T> featurizer){
        this.featurizer = featurizer;
    }

    public Feature<?> feature(String name){
        List<Feature<?>> features = this.featurizer.featurize(this);

        for(Feature f : features){
            if(f.getName().equals(name) == true){
                return f;
            }
        }

        return null;
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
}
