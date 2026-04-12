package estacion.exceptions;

import estacion.sensores.Sensor;

/**
 * Excepcion general de sensor
 */
public class SensorException extends Exception{
    /** Sensor que generó la excepción */
    Sensor sensor;

    /**
     * Constructor de excepcion, sólo usar para otras excepciones
     * @param sensor Sensor que generó la excepción
     * @param message Mensaje asociado
     */
    public SensorException(Sensor sensor, String message){
        super(message);
        this.sensor = sensor;
    }

    /**
     * Constructor de excepción para errores desconocidos.
     * @param sensor Sensor que generó la excepción
     */
    public SensorException(Sensor sensor){
        super("Error desconocido en el sensor: " + sensor.getIdentificador());
        this.sensor = sensor;
    }

    /**
     * Getter del sensor
     * @return Sensor
     */
    public Sensor getSensor(){ return this.sensor; }
    
}
