package ArbolesDecision.Utils;

import java.util.List;

import ArbolesDecision.Datasets.LabeledDataSet;


/**
 * Esta interfaz funcional proporciona el método necesario para extraer el nombre de una feature de un LabeledDataset en base a una estrategia.
 * @param <T> tipo paramétrico del objeto a extraer el nombre de la feature
 * @param <L> tipo paramétrico de las Labels que proporciona el LabeledDataset
 */
@FunctionalInterface
public interface FeatureSelectStrategy<T extends Comparable<? super T>,L> {
	
	/**
	 * Retorna el nombre de una feature en base a una estrategia
	 * @param availableFeatures lista de los nombres de las features disponibles
	 * @param dataset dataset que contiene las features y conoce sus labels
	 * @return nombre de la feature seleccionada
	 */
    String selectFeature(List<String> availableFeatures, LabeledDataSet<T,L> dataset);
}