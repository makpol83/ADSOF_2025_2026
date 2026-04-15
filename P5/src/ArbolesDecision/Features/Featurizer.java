package ArbolesDecision.Features;

import java.util.Collection;

public interface Featurizer<T extends Comparable<? super T>> {
    Feature<T> featurize(String name, Collection<T> dataToFeaturize);
}
