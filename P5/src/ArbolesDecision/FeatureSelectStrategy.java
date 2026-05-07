package ArbolesDecision;

import java.util.List;

@FunctionalInterface
public interface FeatureSelectStrategy<T extends Comparable<? super T>,L> {
    String selectFeature(List<String> availableFeatures, LabeledDataSet<T,L> dataset);
}