package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.UnidadMedida;

/**
 * Clase abstracta de la que deriva cada conversor, establece una variable de origen
 * y una variable de destino, con un constructor protegido, los conversores están pensados
 * para ser Singleton ya que su funcionalidad no varía entre instancias de la misma clase.
 */
public abstract class Conversor {
    /** Unidad de medida de llegada */
    private final UnidadMedida variableOrigen;
    /** Unidad de medida de salida */
    private final UnidadMedida variableDestino;

    /**
     * Constructor conversor, puesto en protected para que sólo lo puedan usar los conversores.
     * @param variableOrigen llegada
     * @param variableDestino salida
     */
    protected Conversor(UnidadMedida variableOrigen, UnidadMedida variableDestino){
        this.variableOrigen = variableOrigen;
        this.variableDestino = variableDestino;

    }

    /**
     * Getter unidad de origen
     * @return UnidadMedida
     */
    public UnidadMedida getUnidadOrigen(){ return this.variableOrigen; }

    /**
     * Getter unidad destino
     * @return UnidadMedida
     */
    public UnidadMedida getUnidadDestino(){ return this.variableDestino; }

    /**
     * Convierte las unidades de la Unidad de medida de llegada a la de salida
     * @param valor valor en Unidad de medida de llegada
     * @return double en Unidad de medida de salida
     */
    public abstract double convertirUnidades(double valor);
}
