package estacion.unidadesLectura;

/**
 * Medidas asociadas a la humedad del aire
 */
public enum MHumedad implements UnidadMedida{
    /** Porcentaje */
    Porcentaje(0,100);

    /** Valor mínimo a tomar */
    private double minValor;
    /** Valor máximo a tomar */
    private double maxValor;

    /**
     * Constructor
     * @param minValor
     * @param maxValor
     */
    private MHumedad(double minValor, double maxValor){
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
        return "%";
    }
}
