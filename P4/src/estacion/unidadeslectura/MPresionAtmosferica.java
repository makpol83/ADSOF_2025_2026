package estacion.unidadeslectura;

public enum MPresionAtmosferica implements UnidadLectura{
    hPa(300, 1100);

    private double minValor;
    private double maxValor;

    MPresionAtmosferica(double minValor, double maxValor){
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
