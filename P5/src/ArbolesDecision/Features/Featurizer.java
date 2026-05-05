package ArbolesDecision.Features;

import java.util.Collection;
import java.util.List;

public interface Featurizer<T> {
    List<String> getFeatureNames();
    Collection<Feature<?>> featurize(Collection<T> element);
}
