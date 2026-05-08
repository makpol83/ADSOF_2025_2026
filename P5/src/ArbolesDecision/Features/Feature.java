package ArbolesDecision.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Esta clase, compatible con una Lista, almacena valores de una característica del tipo paramétrico
 * @param <T> tipo del que almacenar las características
 */
public class Feature<T extends Comparable<? super T>> extends ArrayList<T> {
	/** Nombre de la feature*/
    private String name;

    /**
     * Construye y retorna una feature con el nombre especificadp
     * @param name nombre de la feature
     */
    public Feature(String name){
        super();
        this.name = name;
    }

    /**
     * Retorna el nombre de esta feature
     * @return nombre de esta feature
     */
    public String getName(){
        return this.name;
    }

    /**
     * Retorna el mínimo de los valores almacenados por esta feature
     * @return valor mínimo
     */
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

    /**
     * Retorna el máximo de los valores almacenados por esta feature
     * @return valor máximo
     */
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

    /**
     * Retorna un mapa con las frecuencias de los valores almacenados en esta feature
     * @return mapa de frecuencias
     */
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

   /**
    * Dos features se consideran la misma si su nombre es el mimso, independientemente de los valores que contenga
    */
    @Override
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(!(obj instanceof Feature feature)) return false;
        return this.name.equals(feature.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.name);
    }

}
