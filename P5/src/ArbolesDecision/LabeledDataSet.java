package ArbolesDecision;

import ArbolesDecision.*;
import ArbolesDecision.Features.*;

public class LabeledDataSet<T extends Comparable<? super T>, L> extends Dataset<T> {
    private LabelProvider<T,L> labelProvider;

    LabeledDataSet(Featurizer<T> featurizer, LabelProvider<T,L> labelProvider){
        super(featurizer);
        this.labelProvider = labelProvider;
    }

    public LabelProvider<T,L> getLabelProvider(){
        return this.labelProvider;
    }
}