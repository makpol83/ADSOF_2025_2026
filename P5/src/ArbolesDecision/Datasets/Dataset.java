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

/**
 * 
 * Esta clase poporciona la implementación básica de un dataset genérico. Permitiendo extraer las features del tipo paramétrico dado
 * su featurizer.
 * @param <T> tipo de objeto cuyas features guarda el dataset
 */
public class Dataset<T extends Comparable<? super T>>{
	/** Featurizer del tipo del dataset, usado para extraer las features */
    private Featurizer<T> featurizer;
    /** Lista en la que se guardan los objetos añadidos originales al dataset */
    private List<T> elementsFeaturized = new ArrayList<>();
    /** Mapa con las features de cada persona */
    private Map<String, Feature<?>> features = new HashMap<>();

    /**
     * Construye y retorna un dataset dado un featurizer del tipo paramétrico
     * @param featurizer que usara el dataset para extraer las features
     */
    public Dataset(Featurizer<T> featurizer){
        this.featurizer = featurizer;
    }

    /**
     * Añade un objeto al dataset y guarda sus features
     * @param elem objeto a añadir
     */
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

    /**
     * Funcion de uso interno para añadir los valores de una feature de valor desconocido a otra. Esta función se usa internamente con el
     * conocimiento de que no existe un conflicto de tipos.
     * @param <S> tipo de ambas features (aunque en una de ellas la referencia es de tipo ? )
     * @param featureToAdd feature a la que se añadirán los valores de featureToGet
     * @param featureToGet feature cuyos valores serán añadirdos a featureToAdd
     */
    private <S extends Comparable<? super S>> void addFeature(Feature<S> featureToAdd, Feature<?> featureToGet) {
        featureToAdd.add((S) featureToGet.get(0)); 
    }

    /**
     * Añade una colección de elementos a este dataset
     * @param elems colección de elementos a añadir
     */
    public void addAll(Collection<T> elems){

        for(T e : elems){
            this.add(e);
        }
    }

    /**
     * Añade un array de elementos a este dataset
     * @param elems array de elementos
     */
     public void addAll(T[] elems){

        for(T e : elems){
            this.add(e);
        }
    }
    

    /**
     * Elimina del dataset aquellos valores de features provenientes de objetos iguales.
     * No elimina las features iguales provenientes de distintos objetos
     */
    public void removeDuplicates(){
        Set<T> withoutDuplicates = new TreeSet<>(this.elementsFeaturized);

        this.elementsFeaturized.clear();
        this.features.clear();
        this.addAll(withoutDuplicates);
    }

    /**
     * Retorna una colección de los objetos cuyas features se han extraído en este dataset
     * @return Collecion de elementos
     */
    public Collection<T> getData(){ return this.elementsFeaturized; }

    /**
     * Retorna una feature del dataset dado su nombre
     * @param <S> tipo paramétrico de la feature
     * @param name nombre de la feature
     * @return Feature del nombre especificado
     */
    public <S extends Comparable<? super S>> Feature<S> feature(String name){
        return (Feature<S>)this.features.get(name);
    }

    /**
     * Retorna el featurizer de este dataset
     * @return Featurizer del dataset
     */
    public Featurizer<T> getFeaturizer(){
        return this.featurizer;
    }

    @Override
    public String toString(){
        return this.features.toString();
    }
}
