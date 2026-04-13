package estacion.unidadesLectura;

public enum UnidadMedida {
    ;

    private double minValor;
    private double maxValor;

    public double getValorMinimo() { return this.minValor; }

    public double getValorMaximo() { return this.maxValor; }

    public boolean esRangoValido(double value){
        if(value < this.minValor ||  value > this.maxValor)
            return false;

        return true;
    }
}
