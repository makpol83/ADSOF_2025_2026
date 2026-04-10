package estacion.unidadeslectura;

public enum MTemperatura implements UnidadLectura{
    Celsius(-273.15, 1000),
    Fahrenheit(-459.67,1832),
    Kelvin(0, 1273.15);

    private double minValor;
    private double maxValor;

    MTemperatura(double minValor, double maxValor){
        this.minValor = minValor;
        this.maxValor = maxValor;
        //TODO throw exception si no son validos
    }

    public double getValorMinimo() { return this.minValor; }

    public double getValorMaximo() { return this.maxValor; }

    public boolean esRangoValido(double value){
        if(value < this.minValor ||  value > this.maxValor)
            return false;

        return true;
    }
}
