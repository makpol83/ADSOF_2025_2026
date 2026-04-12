package estacion.estrategiasMedicion;

import estacion.sensores.Sensor;

/**
 * Describe el funcionamiento de las mediciones cercanas, que modifican el dato
 * en un procentaje respecto de la ultima medicion.
 */
public class MedicionCercana implements EstrategiaMedicion{
    /** Offset en porcentaje, rango 0 a 1 */
    private double offsetPorcentaje;

    /**
     * Constructor medicion cercana
     * @param offsetPorcentaje offset en porcentaje de 0 a 1
     */
    public MedicionCercana(double offsetPorcentaje){
        this.offsetPorcentaje = offsetPorcentaje;
    }

    public double medir(Sensor s){
        double ultimoValorMedido = s.getUltimaMedida().getValorMedido();
        double p = Math.random() * offsetPorcentaje;
        return ultimoValorMedido + ultimoValorMedido * p;
    }
}