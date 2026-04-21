package ArbolesDecision.Features;

import java.util.Collection;
import java.util.List;

public interface Featurizer<T> {
    List<Feature<?>> featurize(Collection<T> dataToFeaturize);


}
