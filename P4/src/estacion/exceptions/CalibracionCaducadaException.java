package estacion.exceptions;

import java.time.LocalDateTime;

import estacion.sensores.Sensor;

/**
 * Informa de que un sensor está caducado y necesita calibración
 */
public class CalibracionCaducadaException extends SensorException {
    /**
     * Constructor excepcion
     * @param sensor sensor caducado
     */
    public CalibracionCaducadaException(Sensor sensor){
        super(sensor, "[" + LocalDateTime.now().withNano(0) + "] " + "Sensor " + sensor.getIdentificador() + " sin calibrar (calibración caducada desde " + (sensor.getFechaCaducidad() == null ? "Nunca calibrado" : sensor.getFechaCaducidad()) + ")");
    }
}
