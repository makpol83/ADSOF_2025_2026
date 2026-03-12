package src.app;

public class MensajeControlado extends Mensaje{
    private int rigidez;

    MensajeControlado(String mensaje, int alcanceInicial, Usuario autor, int rigidez){
        super(mensaje, alcanceInicial, autor);
        this.rigidez = rigidez;
    }

    @Override
    public int getRigidez(){
        return this.rigidez;
    }

    @Override
    public boolean puedeDifundirPor(Enlace e){
        return e.costeEspecial() != 0;
    }

    @Override
    public boolean aceptadoPor(Usuario u) {
        return super.aceptadoPor(u);
    }
}
