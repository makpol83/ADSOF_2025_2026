package estacion.exceptions;

import java.time.LocalDateTime;

import estacion.sensores.Medida;
import estacion.sensores.Sensor;

/**
 * Alerta de que se ha realizado una medida fuera de rango
 */
public class MedidaFueraRangoException extends Exception {
    /** Sensor asociado */
    Sensor sensor;

    /**
     * Constructor excepcion
     * @param sensor Sensor asociado
     * @param medidaFueraDeRango Medida fuera de rango
     */
    public MedidaFueraRangoException(Sensor sensor, Medida medidaFueraDeRango){
        super(LocalDateTime.now() + "Lectura fuera de rango en " + sensor.getIdentificador() + ": " + medidaFueraDeRango.getValorMedido() + sensor.getUnidadLectura());
        this.sensor = sensor;
    }

    /**
     * Getter del sensor
     * @return Sensor
     */
    public Sensor getSensor(){ return this.sensor; }
}
