package src.app;

import java.util.Random;
import java.util.random.RandomGenerator;

public class Mensaje {
    private String mensaje;
    private int alcance;
    private Usuario autor;
    private Usuario usuarioActual;

    public Mensaje(String mensaje, int alcanceInicial, Usuario autor){
        this.mensaje = mensaje;
        this.autor = autor;
        this.alcance = alcanceInicial;
        this.usuarioActual = autor;
    }

    public Usuario getAutor(){
        return this.autor;
    }

    public Usuario getUsuarioActual(){
        return this.usuarioActual;
    }
    
    public String getMensaje(){
        return this.mensaje;
    }

    public int getAlcance(){
        return this.alcance;
    }

    public boolean puedeDifundirPor(Enlace e){
        if(e.esSeguro() == false)
            return false;

        if(this.alcance >= e.costeReal())
            return true;
        else
            return false;
    }

    public boolean aceptadoPor(Usuario u){
        return true;
    }

    public boolean difunde(Enlace e){
        if(this.puedeDifundirPor(e) == false || aceptadoPor(e.getUsuarioDestino()) == false)
            return false;
        
        if(e.equals(usuarioActual.getEnlace(e.getUsuarioDestino())) == false)
            return false;

        //apartado 6
        if(e.getProbRetornoObligado() > Math.random())
            return false;

        
        this.usuarioActual = e.getUsuarioDestino();
        this.alcance -= e.costeReal();
        this.alcance += e.getUsuarioDestino().getCapacidadAmplificacion();
        return true;
    }

    public int getRigidez(){
        return 0;
    }

    public boolean difunde(Usuario... usuarios){
        boolean difundido = true;
        for(Usuario usuario : usuarios){
            Enlace e = usuarioActual.getEnlace(usuario);
            if(e != null){
                if(this.difunde(e) == false)
                    difundido = false;
            }
            else
                difundido = false;
        }

        return difundido;
    }

    @Override
    public String toString() {
        return "Mensaje(" + this.mensaje + ":" + this.alcance + ") en @" + this.usuarioActual.getNombre();
    }

}