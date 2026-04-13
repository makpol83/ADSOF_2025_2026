package estacion.exceptions;

import estacion.unidadesLectura.UnidadMedida;
import estacion.unidadesLectura.conversores.Conversor;

public class ConversorIncompatibleException extends Exception {
    /** Conversor que genera incompatibilidad */
    private final Conversor c;
    /** Unidad de Lectura con la que el Conversor tiene conflicto*/
    private final UnidadMedida conflicto;
    /** Mensaje por defecto de la excepción */
    private static final String message = "Se ha intentado usar un conversor incompatible con una unidad de lectura.";

    /**
     * Construye y retorna una excepción que informa del uso de un conversor incompatible con la unidad de lectura
     * especificada
     * @param message mensaje de la excepción
     * @param c conversor que se intentó usar.
     * @param ul unidad de lectura que genera conflicto con el conversor
     */
    public ConversorIncompatibleException(String message, Conversor c, UnidadMedida ul){
        super(message);
        this.c = c;
        this.conflicto = ul;
    }

    /**
     * Construye y retorna una excepción que informa del uso de un conversor incompatible con la unidad de lectura
     * especificada
     * @param c conversor que se intentó usar.
     * @param ul unidad de lectura que genera conflicto con el conversor
     */
    public ConversorIncompatibleException(Conversor c, UnidadMedida ul){
        this(message, c, ul);
    }

    /**
     * Construye y retorna una excepción que informa del intento de uso de un conversor incompatible con
     * cierta unidad de lectura
     * @param c conversor incompatible
     */
    public ConversorIncompatibleException(Conversor c){
        this(message, c, null);
    }

    /** Retorna el conversor incompatible */
    public Conversor getConversorIncompatible(){return this.c; }
    /**  Retorna la unidad de lectura que genera conflicto con el conversor*/
    public UnidadMedida getUnidadLecturaConflicto(){return this.conflicto; }

}
