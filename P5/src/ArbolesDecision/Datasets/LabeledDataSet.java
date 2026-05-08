package ArbolesDecision.Datasets;

import ArbolesDecision.*;
import ArbolesDecision.Features.*;
import ArbolesDecision.Utils.LabelProvider;

/**
 * Esta clase extiende la funcionalidad de un Dataset añadiendo un LabelProvider con el que se podrán
 * extraer la etiqueta de un objeto 
 * @param <T> tipo paramétrico de los objetos cuyas features se almacenarán
 * @param <L> tipo de las etiquetas retornas por el LabelProvider
 */
public class LabeledDataSet<T extends Comparable<? super T>, L> extends Dataset<T> {
    private LabelProvider<T,L> labelProvider;

    /**
     * Construye y retorna un LabeledDataSet
     * @param featurizer del dataset
     * @param labelProvider del dataset
     */
    public LabeledDataSet(Featurizer<T> featurizer, LabelProvider<T,L> labelProvider){
        super(featurizer);
        this.labelProvider = labelProvider;
    }

    /**
     * Retorna el LabelProvider de este dataset
     * @return LabelProvider con el que se creó el dataset
     */
    public LabelProvider<T,L> getLabelProvider(){
        return this.labelProvider;
    }
}