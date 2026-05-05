package ArbolesDecision.Features;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//Feature guarda objetos de tipo T que son comparables en T o en una de sus superclases
//Extiende de AbstractList<T> para que podamos usar Feature como una lista, ya que sólo hay que implementar el get y el
//size

public class Feature<T extends Comparable<? super T>> extends ArrayList<T> {
    private String name;

    public Feature(String name){
        super();
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public T min(){
        if(size() == 0)
            return null;

        T minimumData = get(0);
        for(T d : this){
            if(minimumData.compareTo(d) > 0)
                minimumData = d;
        }

        return minimumData;
    }

    public T max(){
        if(size() == 0)
            return null;

        T maximumData = get(0);
        for(T d : this){
            if(maximumData.compareTo(d) < 0)
                maximumData = d;
        }

        return maximumData;
    }

    public Map<T, Integer> distribution(){
        Map<T, Integer> distribution = new HashMap<>();
        List<T> copyData = new ArrayList<>(this);

        /*
        Para contar creamos una copia del data, tomamos el primero,
        buscamos todos los que son iguales a ese primero y los contamos,
        añadimos a una lista de borrado, de forma que al final se borra,
        se repite hasta que no quedan elementos en copyData.
        */
        while(copyData.size() != 0){
            List<T> removeData = new ArrayList<>();
            T dataToSearch = copyData.get(0);
            copyData.remove(0);

            int dataToSearchCounter = 1;

            for(T d : copyData){
                if(dataToSearch.compareTo(d) == 0){
                    dataToSearchCounter++;
                    removeData.add(d);
                }
            }

            copyData.removeAll(removeData);
            distribution.put(dataToSearch, dataToSearchCounter);
        }

        return distribution;
    }

    public boolean equals(Object obj){
        if(obj == this) return true;
        if(!(obj instanceof Feature feature)) return false;
        return this.name.equals(feature.name);
    }

    public int hashCode(){
        return Objects.hash(this.name);
    }

}
