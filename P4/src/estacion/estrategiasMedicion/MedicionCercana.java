package estacion.estrategiasMedicion;

import estacion.Sensor;

public class MedicionCercana implements EstrategiaMedicion{
    private double offsetPorcentaje;

    public MedicionCercana(double offsetPorcentaje){
        this.offsetPorcentaje = offsetPorcentaje;
    }

    public double medir(Sensor s){
        double ultimoValorMedido = s.getUltimoValorMedido();
        double p = Math.random() * offsetPorcentaje/100;
        return ultimoValorMedido + ultimoValorMedido * p;
    }
}