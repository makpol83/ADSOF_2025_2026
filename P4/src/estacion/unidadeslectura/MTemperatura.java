package estacion.unidadesLectura;

/**
 * Unidades de medida asociadas a la temperatura
 */
public enum MTemperatura implements UnidadMedida{
    /** Celsius */
    Celsius(-273.15, 1000),
    /** Fahrenheit */
    Fahrenheit(-459.67,1832),
    /** Kelvin */
    Kelvin(0, 1273.15);

    /** Valor mínimo a tomar */
    private double minValor;
    /** Valor máximo a tomar */
    private double maxValor;

    /**
     * Constructor
     * @param minValor a tomar
     * @param maxValor a tomar
     */
    private MTemperatura(double minValor, double maxValor){
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
