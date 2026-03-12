package src.app;
public class Enlace {
    private static int costeAcumulado;
    
    private Usuario usuarioOrigen;
    private Usuario usuarioDestino;
    private int coste;
    

    public Enlace(Usuario usuarioOrigen, Usuario usuarioDestino){
        this.usuarioOrigen = usuarioOrigen;
        this.usuarioDestino = usuarioDestino;
        this.coste = 1;
        Enlace.costeAcumulado += 1;
    }
    
    public Enlace(Usuario usuarioOrigen, Usuario usuarioDestino, int coste){
        this(usuarioOrigen, usuarioDestino);
        if(coste > 1){ 
            this.coste = coste;
            Enlace.costeAcumulado += coste - 1;
        }
    }

    public Usuario getUsuarioOrigen() {
        return this.usuarioOrigen;
    }

    public Usuario getUsuarioDestino() {
        return this.usuarioDestino;
    }
    
    public int getCoste() {
        return this.coste;
    }

    public int getCosteAcumulado() {
        return Enlace.costeAcumulado;
    }
    
    public int costeEspecial(){
        return 0;
    }

    public double getProbRetornoObligado(){ return 0; }

    public int costeReal(){
        return this.costeEspecial() + this.coste;
    }

    public void cambiarDestino(Usuario nuevoDestino, int nuevoCoste) {
        this.usuarioDestino = nuevoDestino;
        Enlace.costeAcumulado -= this.coste;
        if(nuevoCoste <= 0){
            Enlace.costeAcumulado += 1;
            this.coste = 1;
        } else {
            Enlace.costeAcumulado += nuevoCoste;
            this.coste = nuevoCoste;
        }
    }

    @Override
    public String toString(){
        return "(@" + usuarioOrigen.getNombre() + "--" + this.coste + "-->@" + usuarioDestino.getNombre() + ")";
    }
}   