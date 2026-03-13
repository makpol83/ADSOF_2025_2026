package src.app;

import javax.management.ObjectInstance;

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
    public boolean aceptadoPor(Usuario u) {
        int requerimiento = 0;
        boolean aceptado = false;
        switch (u.getExposicion()){
            case Exposicion.OCULTA -> requerimiento = 0;
            case Exposicion.BAJA -> requerimiento = 5;
            case Exposicion.MEDIA -> requerimiento = 10;
            case Exposicion.ALTA -> requerimiento = 20;
            case Exposicion.VIRAL -> requerimiento = 50;
        }

        aceptado = (this.rigidez >= requerimiento) ? true : false;

        return aceptado;
    }
}
