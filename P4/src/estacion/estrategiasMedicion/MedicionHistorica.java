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
        double p = Math.random() * offsetPorcentaje /100;
        int signo = Math.random() > 0.5 ? 1 : -1;
        return s.getMediaHistorica() + signo * s.getMediaHistorica() * p;
    }
}
