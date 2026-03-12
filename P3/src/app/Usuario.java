package src.app;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private int capacidadAmplificacion;
    private List<Enlace> enlaces;
    private Exposicion exposicion;
    private List<Mensaje> historial;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.capacidadAmplificacion = 2;
        this.enlaces = new ArrayList<Enlace>();
        this.exposicion = Exposicion.ALTA;
        this.historial = new ArrayList<>();
    }

    public Usuario(String nombre, int capacidadAmplificacion) {
        this(nombre);
        this.capacidadAmplificacion = capacidadAmplificacion;
    }

    public Usuario(String nombre, int capacidadAmplificacion, Exposicion exposicion) {
        this(nombre, capacidadAmplificacion);
        this.exposicion = exposicion;
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

    void cambiarExposicion(Exposicion e){ this.exposicion = e; }

    public boolean añadirMensaje(Mensaje mensaje){

        if(this.historial.contains(mensaje))
            return false;

        int alcancePromedio=0;
        for(Mensaje m : this.historial){
            alcancePromedio += m.getAlcance();
        }
        alcancePromedio /= this.historial.size();

        if(mensaje.getAlcance() > alcancePromedio && this.exposicion.compareTo(Exposicion.VIRAL) < 0){
            switch (this.exposicion){
                case Exposicion.ALTA -> this.exposicion = Exposicion.VIRAL;
                case Exposicion.MEDIA -> this.exposicion = Exposicion.ALTA;
                case Exposicion.BAJA -> this.exposicion = Exposicion.MEDIA;
                case Exposicion.OCULTA -> this.exposicion = Exposicion.BAJA;
            }
        } else if(mensaje.getAlcance() < alcancePromedio && this.exposicion.compareTo(Exposicion.OCULTA) > 0){
            switch (this.exposicion){
                case Exposicion.VIRAL -> this.exposicion = Exposicion.ALTA;
                case Exposicion.ALTA -> this.exposicion = Exposicion.MEDIA;
                case Exposicion.MEDIA -> this.exposicion = Exposicion.BAJA;
                case Exposicion.BAJA -> this.exposicion = Exposicion.OCULTA;

            }
        }

        this.historial.add(mensaje);
        return true;
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

    public List<Enlace> getEnlaces(){
        return this.enlaces;
    }

    public int getNumEnlaces() {
        return this.enlaces.size();
    }

    public Enlace getEnlace(Usuario destino) {
        return this.containsDestino(destino);
    }

    public Exposicion getExposicion(){
        return this.exposicion;
    }

    @Override
    public String toString(){
        return "@"+ this.getNombre() + "(" + this.getCapacidadAmplificacion() + ")" + this.enlaces.toString();
    }
}