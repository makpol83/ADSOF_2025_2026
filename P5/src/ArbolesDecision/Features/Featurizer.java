package ArbolesDecision.Features;

import java.util.List;

/**
 * Esta interfaz proporciona los metodos que requiere un featurizer, que se encargará de extraer las features de un objeto
 * @param <T>
 */
public interface Featurizer<T> {
	
	/**
	 * 
	 * @return
	 */
    List<String> getFeatureNames();
    <S extends Comparable<? super S>> Feature<S> featurize(T element, String featureName);
}
