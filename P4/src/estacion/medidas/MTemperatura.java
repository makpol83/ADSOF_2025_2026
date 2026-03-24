package estacion.medidas;

public enum MTemperatura{
    Celsius(-273.15, 1000),
    Fahrenheit(0,1),
    Kelvin(0, 1273.15);

    private double minValor;
    private double maxValor;

    MTemperatura(double minValor, double maxValor){
        this.minValor = minValor;
        this.maxValor = maxValor;
    }

    public double getValorMinimo() { return this.minValor; }

    public double getValorMaximo() { return this.maxValor; }

    public boolean esRangoValido(double value){
        if(value < this.minValor ||  value > this.maxValor)
            return false;

        return true;
    }
}
