package estacion.medidas;

public enum Humedad {
    Porcentaje(0,100);

    private double minValor;
    private double maxValor;

    Humedad(double minValor, double maxValor){
        this.minValor = minValor;
        this.maxValor = maxValor;
    }

    public boolean esRangoValido(double value){
        if(value < this.minValor ||  value > this.maxValor)
            return false;

        return true;
    }
}
