package estacion.estrategiasMedicion;

import estacion.sensores.Sensor;
/**
 * Interfaz que define los métodos asociados a una estrategia de medición
 */
public interface EstrategiaMedicion {
    /**
     * Método para realizar una medición recibiendo un sensor
     * @param s Sensor en el que realizar la medida
     * @return valor medido en la unidad de lectura del sensor
     */
    double medir(Sensor s);
}
