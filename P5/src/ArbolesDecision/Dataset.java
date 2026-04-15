package ArbolesDecision;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import ArbolesDecision.Features.Feature;
import ArbolesDecision.Features.Featurizer;

public class Dataset<T extends Comparable<? super T>> extends AbstractSet<T>{
    private Featurizer<T> featurizer;
    private List<T> data = new ArrayList<>();
    

    public Dataset(Featurizer<T> featurizer){
        this.featurizer = featurizer;
    }

    public Feature<T> feature(String name){
        return this.featurizer.featurize(name, data);
    }

    public void removeDuplicates(){
        for(int i = 0; i < this.data.size(); i++){
            T dataInstance = this.data.get(i);
            
            //Iterador que empieza en i + 1
            Iterator<T> it = this.data.listIterator(i + 1);

            while(it.hasNext()){
                T dataToCompare = it.next();
                if(dataInstance.compareTo(dataToCompare) == 0){
                    it.remove();
                }
            }
        }
    }

    @Override
    public boolean add(T e) {
        this.data.add(e);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> dataToAdd){
        for(T d : dataToAdd){
            if(this.add(d) == false)
                return false;
        }
        return true;
    }


    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public Iterator<T> iterator() {
        return this.data.iterator();
    }
}
