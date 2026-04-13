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

    /**
     * Getter valor minimo
     * @return double
     */
    public double getValorMinimo() { return this.minValor; }

    /**
     * Getter valor máximo
     * @return double
     */
    public double getValorMaximo() { return this.maxValor; }

    /**
     * Comprueba si un valor está en rango válido
     * @param value a comprobar
     * @return true si está en rango, false si no
     */
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
