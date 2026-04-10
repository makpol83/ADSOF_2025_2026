package estacion.medidas;

public enum MHumedad implements VariableMedida{
    Porcentaje(0,100);

    private double minValor;
    private double maxValor;

    MHumedad(double minValor, double maxValor){
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
