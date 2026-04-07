package estacion.estrategiasMedicion;

public class MedicionCercana implements EstrategiaMedicion{
    private double offsetPorcentaje;

    public MedicionCercana(double offsetPorcentaje){
        this.offsetPorcentaje = offsetPorcentaje;
    }

    public double medir(double valorMinimo, double valorMaximo, double ultimoValorMedido, double mediaHistorica){
        double p = Math.random() * offsetPorcentaje/100;
        return ultimoValorMedido + ultimoValorMedido * p;
    }
}