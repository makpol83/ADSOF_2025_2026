package src.app;

import java.util.ArrayList;

public class UsuarioInteresado extends Usuario{

    public UsuarioInteresado(String nombre) {
        super(nombre);
    }

    public UsuarioInteresado(String nombre, int capacidadAmplificacion) {
        super(nombre, capacidadAmplificacion);
    }

    public UsuarioInteresado(String nombre, int capacidadAmplificacion, Exposicion exposicion) {
        super(nombre, capacidadAmplificacion, exposicion);
    }


    @Override
    public Enlace getEnlace(Usuario destino){
        Enlace eNormal = null;
        for(Enlace e : this.getEnlaces()){
            if(e.getUsuarioDestino().getExposicion().compareTo(Exposicion.ALTA) >= 0)
                return e;
            else if (e.getUsuarioDestino() == destino) {
                eNormal = e;
            }
        }
        return eNormal;
    }
}
