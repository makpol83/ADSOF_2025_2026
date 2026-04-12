package estacion.exceptions;

import java.time.LocalDateTime;

import estacion.sensores.Medida;
import estacion.sensores.Sensor;

/**
 * Alerta de que se ha producido un cambio brusco en porcentaje respecto de la última medida
 */
public class CambioBruscoLecturaException extends Exception {
    /** Sensor asociado */
    Sensor sensor;
    /** Penultima medida */
    Medida penultimaMedida;
    /** Nueva medida con cambio brusco */
    Medida ultimaMedida;

    /**
     * Constructor excepcion
     * @param sensor sensor asociado
     * @param penultimaMedida penultima medida
     * @param ultimaMedida ultima medida con cambio brusco
     */
    public CambioBruscoLecturaException(Sensor sensor, Medida penultimaMedida, Medida ultimaMedida){
        super(LocalDateTime.now() + "Cambio brusco en " + sensor.getIdentificador() + ": " + ultimaMedida.getValorMedido() + sensor.getUnidadLectura() + " (anterior: " + penultimaMedida.getValorMedido() + sensor.getUnidadLectura() + ")");
        this.sensor = sensor;
    }

    /**
     * Getter del sensor asociado
     * @return Sensor
     */
    public Sensor getSensor(){ return this.sensor; }

    /**
     * Getter de la penultima medida
     * @return Medida
     */
    public Medida getPenultimaMedida(){ return this.penultimaMedida; }

    /**
     * Getter de la ultima medida
     * @return Medida
     */
    public Medida getUltimaMedida(){ return this.ultimaMedida; }
}
