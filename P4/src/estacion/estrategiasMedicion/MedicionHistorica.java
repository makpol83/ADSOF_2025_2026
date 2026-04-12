package estacion.estrategiasMedicion;

import estacion.sensores.Sensor;

/**
 * Describe el funcionamiento de las mediciones históricas, que usan la media histórica
 * para generar la siguiente medida
 */
public class MedicionHistorica implements EstrategiaMedicion{
    /**
     * Offset en porcentaje, rango 0 a 1
     */
    private double offsetPorcentaje;
    
    /**
     * Constructor medicion historica
     * @param offsetPorcentaje Offset procentaje, rango 0 a 1
     */
    public MedicionHistorica(double offsetPorcentaje){
        this.offsetPorcentaje = offsetPorcentaje;
    }

    public double medir(Sensor s){
        double p = Math.random() * offsetPorcentaje;
        return s.getMediaHistorica() + s.getMediaHistorica() * p;
    }
}
