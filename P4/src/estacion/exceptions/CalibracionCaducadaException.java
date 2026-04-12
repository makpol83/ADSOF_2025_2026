package estacion.exceptions;

import java.time.LocalDateTime;

import estacion.sensores.Sensor;

/**
 * Informa de que un sensor está caducado y necesita calibración
 */
public class CalibracionCaducadaException extends Exception {
    /** Sensor caducado. */
    Sensor sensor;

    /**
     * Constructor excepcion
     * @param sensor sensor caducado
     */
    public CalibracionCaducadaException(Sensor sensor){
        super(LocalDateTime.now() + "Sensor " + sensor.getIdentificador() + " sin calibrar (calibración caducada desde " + (sensor.getFechaCaducidad() == null ? "Nunca calibrado" : sensor.getFechaCaducidad()) + ")");
        this.sensor = sensor;
    }

    /**
     * Consigue el sensor caducado
     * @return Sensor
     */
    public Sensor getSensor(){ return this.sensor; }
}
