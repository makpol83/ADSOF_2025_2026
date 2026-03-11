package src.app;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private int capacidadAmplificacion;
    private List<Enlace> enlaces;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.capacidadAmplificacion = 2;
        this.enlaces = new ArrayList<Enlace>();
    }

    public Usuario(String nombre, int capacidadAmplificacion) {
        this(nombre);
        this.capacidadAmplificacion = capacidadAmplificacion;
    }

    public boolean addEnlace(Enlace e) {
        if(this.equals(e.getUsuarioDestino()) == true)
            return false;

        if(this.equals(e.getUsuarioOrigen()) == false)
            return false;
            
        if(this.containsDestino(e.getUsuarioDestino()) != null)
            return false;
            
        this.enlaces.add(e);
        return true;
    }

    public boolean addEnlace(Usuario usuarioDestino, int coste) {
        Enlace nuevoEnlace = new Enlace(this, usuarioDestino, coste);
        return this.addEnlace(nuevoEnlace);
    }

    public Enlace containsDestino(Usuario usuarioDestino) {
        for(Enlace x : this.enlaces){
            if(x.getUsuarioDestino() == usuarioDestino)
                return x;
        }

        return null;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getCapacidadAmplificacion(){
        return this.capacidadAmplificacion;
    }

    public Enlace getEnlace(int i){
        return this.enlaces.get(i);
    }

    public int getNumEnlaces() {
        return this.enlaces.size();
    }

    public Enlace getEnlace(Usuario destino) {
        return this.containsDestino(destino);
    }

    @Override
    public String toString(){
        return "@"+ this.getNombre() + "(" + this.getCapacidadAmplificacion() + ")" + this.enlaces.toString();
    }
}