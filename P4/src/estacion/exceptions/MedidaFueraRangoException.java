package estacion.exceptions;

import java.time.LocalDateTime;

import estacion.sensores.Medida;
import estacion.sensores.Sensor;

/**
 * Alerta de que se ha realizado una medida fuera de rango
 */
public class MedidaFueraRangoException extends SensorException {
    /**
     * Constructor excepcion
     * @param sensor Sensor asociado
     * @param medidaFueraDeRango Medida fuera de rango
     */
    public MedidaFueraRangoException(Sensor sensor, Medida medidaFueraDeRango){
        super(sensor, "[" + LocalDateTime.now().withNano(0) + "] " + "Lectura fuera de rango en " + sensor.getIdentificador() + ": " + String.format("%.2f", medidaFueraDeRango.getValorMedido()) + sensor.getUnidadMedida());
    }
}
