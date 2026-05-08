package ArbolesDecision.Utils;

import java.util.List;

import ArbolesDecision.Datasets.LabeledDataSet;

@FunctionalInterface
public interface FeatureSelectStrategy<T extends Comparable<? super T>,L> {
    String selectFeature(List<String> availableFeatures, LabeledDataSet<T,L> dataset);
}