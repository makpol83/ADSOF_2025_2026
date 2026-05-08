package ArbolesDecision.Features;

import java.util.List;

/**
 * Esta interfaz proporciona los metodos que requiere un featurizer, que se encargará de extraer las features de un objeto
 * @param <T> tipo paramétrico sobre el que extraer las features
 */
public interface Featurizer<T> {
	
	/**
	 * Retorna los nombres de las features que se encarga de extraer el featurizer
	 * @return una lista con los nombres
	 */
    List<String> getFeatureNames();
    
    /**
     * Retorna una feature de un objeto dado su nombre
     * @param <S> tipo paramétrico de la feature retornada
     * @param element elemento al que extraer la feature
     * @param featureName nombre de la feature a extraer
     * @return
     */
    <S extends Comparable<? super S>> Feature<S> featurize(T element, String featureName);
}
