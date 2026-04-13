package estacion.exceptions;

import estacion.unidadesLectura.conversores.Procesador;

/**
 * Excepcion que maneja el uso de conversores incorrectos en un procesador
 */
public class UnidadesIncorrectasException extends Exception {
    /** Procesador afectado */
    Procesador procesador;
    
    /**
     * Constructor
     * @param procesador Procesador afectado
     */
    public UnidadesIncorrectasException(Procesador procesador){
        super("Se ha intentado añadir al menos un conversor en orden incorrecto en el procesador: " + procesador.toString());
    }

    /**
     * Getter procesador
     * @return Procesador, puede ser null si se ha producido el error en el constructor
     */
    public Procesador getProcesador(){ return this.procesador; }
}
