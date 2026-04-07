package estacion.estrategiasMedicion;

public class MedicionAleatoria implements EstrategiaMedicion{
    private double probFueraRango;

    public MedicionAleatoria(double probFueraRango){
        this.probFueraRango = probFueraRango;
    }

    public double medir(double valorMinimo, double valorMaximo, double ultimoValorMedido, double mediaHistorica){
        if(Math.random() < probFueraRango){
            if(Math.random() >= 0.5){
                return (valorMaximo - valorMinimo)*Math.random() + valorMinimo + valorMaximo;
            } else {
                return (valorMaximo - valorMinimo)*Math.random() - valorMaximo;
            }
        } else {
            return (valorMaximo - valorMinimo)*Math.random() + valorMinimo;
        }
    }
}
