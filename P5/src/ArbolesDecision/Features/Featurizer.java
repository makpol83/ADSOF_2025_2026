package ArbolesDecision.Features;

import java.util.Collection;
import java.util.List;

public interface Featurizer<T> {
    List<String> getFeatureNames();
    <S extends Comparable<? super S>> Feature<S> featurize(T element, String featureName);
}
