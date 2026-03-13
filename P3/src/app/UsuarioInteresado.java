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
        //enlace cuyo destino es el usuario especificado
        Enlace eNormal = null;
        //iterador de enlace para comprobar si existe un usuario con exposición ALTA al que transmitir el mensaje
        Enlace e = null;
        for(int i = 0; i < this.getNumEnlaces(); i++){
             e = this.getEnlace(i);
            if(e.getUsuarioDestino().getExposicion().compareTo(Exposicion.ALTA) >= 0)
                return e;
            else if (e.getUsuarioDestino() == destino) {
                eNormal = e;
            }
        }
        return eNormal;
    }
}
