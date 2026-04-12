package estacion.exceptions;

import estacion.sensores.Sensor;

/**
 * Alerta de que se ha intentado añadir un sensor que comparte identificador con uno ya existente
 */
public class MismoIdException extends Exception {
    /** Sensor ya existente */
    private Sensor existente;
    /** Sensor a añadir */
    private Sensor nuevo;

    /**
     * Constructor excepcion
     * @param existente Sensor existente
     * @param nuevo Sensor a añadir
     */
    public MismoIdException(Sensor existente, Sensor nuevo){
        super("Se ha intentado añadir un sensor " + nuevo.getIdentificador() + " con un ID ya existente" + existente.getIdentificador());
        this.existente = existente;
        this.nuevo = nuevo;
    }

    /**
     * Getter del sensor existente
     * @return Sensor
     */
    public Sensor getExistente(){ return this.existente; }

    /**
     * Getter del sensor a añadir
     * @return Sensor
     */
    public Sensor getNuevo(){ return this.nuevo; }
}