package src.app;

public class EnlaceSeñuelo extends Enlace{
    private int factorCosteExtra;
    private double probRetornoObligado;

    public EnlaceSeñuelo(Usuario usuarioOrigen, Usuario usuarioDestino, int coste, int factorCosteExtra, double probRetornoObligado){
        super(usuarioOrigen, usuarioDestino, coste);
        this.factorCosteExtra = factorCosteExtra;
        this.probRetornoObligado = probRetornoObligado;
    }

    public EnlaceSeñuelo(Usuario usuarioOrigen, Usuario usuarioDestino, int factorCosteExtra, double probRetornoObligado){
        this(usuarioOrigen, usuarioDestino, 1, factorCosteExtra, probRetornoObligado);
    }

    @Override
    public double getProbRetornoObligado(){ return probRetornoObligado; }
    public int getFactorCosteExtra(){ return factorCosteExtra; }

    @Override
    public int costeEspecial(){
        return this.getCoste() * this.factorCosteExtra;
    }
}
