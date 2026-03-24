package estacion.medidas;

public enum PresionAtmosferica {
    hPa(300, 1100);

    private double minValor;
    private double maxValor;

    PresionAtmosferica(double minValor, double maxValor){
        this.minValor = minValor;
        this.maxValor = maxValor;
    }

    public boolean esRangoValido(double value){
        if(value < this.minValor ||  value > this.maxValor)
            return false;

        return true;
    }
}
