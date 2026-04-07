package estacion.estrategiasMedicion;

public class MedicionHistorica implements EstrategiaMedicion{
    private double offsetPorcentaje;
    
    public MedicionHistorica(double offsetPorcentaje){
        this.offsetPorcentaje = offsetPorcentaje;
    }

    public double medir(double valorMinimo, double valorMaximo, double ultimoValorMedido, double mediaHistorica){
        double p = Math.random() * offsetPorcentaje/100;
        return mediaHistorica + mediaHistorica * p;
    }
}
