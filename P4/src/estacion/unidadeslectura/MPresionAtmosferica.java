package estacion.unidadesLectura;

/**
 * Unidades de medida de la presión atmosférica
 */
public enum MPresionAtmosferica implements UnidadMedida{
    /** HectoPascal */
    hPa(300, 1100),
    /** Pascal */
    Pa(3, 11),
    /** MiliBar */
    mbar(300, 1100);

    /** Valor mínimo a tomar */
    private double minValor;
    /** Valor máximo a tomar */
    private double maxValor;

    /**
     * Constructor
     * @param minValor a tomar
     * @param maxValor a tomar
     */
    private MPresionAtmosferica(double minValor, double maxValor){
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
        return this.name();
    }
}
