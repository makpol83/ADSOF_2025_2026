package estacion.unidadesLectura;

public enum MTemperatura implements UnidadLectura{
    Celsius(-273.15, 1000),
    Fahrenheit(-459.67,1832),
    Kelvin(0, 1273.15);

    private double minValor;
    private double maxValor;

    private MTemperatura(double minValor, double maxValor){
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

    @Override
    public String toString(){
        switch (this) {
            case Celsius:
                return "°C";
            case Fahrenheit:
                return "°F";
            case Kelvin:
                return "°K";
            default:
                return "unknown unit";
        }
    }
}
