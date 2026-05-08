package ArbolesDecision.Utils;


/**
 * Interfaz que proporciona el método que deben tener lo LabelProvider. Un LabelProvider es un objeto que etiqueta a otro
 * @param <T> tipo del objeto al que se etiqueta
 * @param <L> tipo de la etiqueta
 */
public interface LabelProvider<T,L> {
	
	/**
	 * Retorna una label dado un objeto
	 * @param element objeto a etiquetar
	 * @return etiqueta del objeto
	 */
    L getLabel(T element);
}
