package ArbolesDecision.Features;

import java.util.Collection;
import java.util.List;

public interface Featurizer<T> {
    List<String> getFeatureNames();
    boolean featurize(Feature<?> featureToAdd, String featureName, T element);
    Collection<Feature<?>> featurize(T element);
    String name(Feature<?> f);


}
