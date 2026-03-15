package src.app;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase proporciona implementación básica para un Usuario de una red social. El usuario tiene una lista de
 * enlaces por los que mandar mensajes y un valor de amplificación que se usará en la transmisión de mensajes.
 */
public class Usuario {
    /** Nombre del usuario */
    private String nombre;
    /** Capacidad de amplificación del usuario. El alcance de un mensaje se incrementará por la amplificación de un
     * usuario tras recibirlo */
    private int capacidadAmplificacion;
    /** Lista de enlaces que posee el usuario */
    private List<Enlace> enlaces;
    /** Exposición que tiene el usuario. Este valor facilitará la recepción de algunos mensajes */
    private Exposicion exposicion;
    /** Historial de mensajes que ha recibido el usuario */
    private List<Mensaje> historial;

    /**
     * Construye y retorna un usuario con valores por defecto. Estos son:
     * capacidad de amplifición 2
     * exposición ALTA
     * @param nombre nombre del usuario
     */
    public Usuario(String nombre) {
        this.nombre = nombre;
        this.capacidadAmplificacion = 2;
        this.enlaces = new ArrayList<Enlace>();
        this.exposicion = Exposicion.ALTA;
        this.historial = new ArrayList<>();
    }

    /**
     * Construye y retorna un usuario dada una capacidad de amplificación. Por defecto se asigna una exposicion ALTA.
     * @param nombre nombre del usuario
     * @param capacidadAmplificacion capacidad de amplificacion del usuario (debe ser mayor o igual a 0)
     */
    public Usuario(String nombre, int capacidadAmplificacion) {
        this(nombre);
        if(capacidadAmplificacion >= 0)
            this.capacidadAmplificacion = capacidadAmplificacion;
    }

    /**
     * Construye y retorna un usuario dada una capacidad de amplificación y una exposición.
     * @param nombre nombre del usuario
     * @param capacidadAmplificacion capacidad de amplificación del usuario (debe ser mayor o igual a 0)
     * @param exposicion exposición del usuario
     */
    public Usuario(String nombre, int capacidadAmplificacion, Exposicion exposicion) {
        this(nombre, capacidadAmplificacion);
        this.exposicion = exposicion;
    }

    /**
     * Construye y retorna un usuario dada un nombre y una exposición.
     * @param nombre nombre del usuario
     * @param exposicion exposición del usuario
     */
    public Usuario(String nombre, Exposicion exposicion) {
        this(nombre);
        this.exposicion = exposicion;
    }

    /**
     * Añade un enlace a este objeto. Este objeto debe ser el usuario de origen del enlace y no puede ser el usuario
     * destino. Además, no puede tener otro enlace con el mismo destino.
     * @param e enlace a añadir
     * @return true si se añadió o false
     */
    public boolean addEnlace(Enlace e) {
        if(this.equals(e.getUsuarioDestino()) == true)
            return false;

        if(this.equals(e.getUsuarioOrigen()) == false)
            return false;
            
        if(this.containsDestino(e.getUsuarioDestino()) != null)
            return false;
            
        return this.enlaces.add(e);
    }

    /**
     * Añade un enlace a este objeto dado un usuario destino el coste del enlace. Este objeto no puede tener otro
     * enlace con el mismo usuario destino.
     * @param usuarioDestino usuario de destino que tendrá el enlace a añadir
     * @param coste coste del enlace
     * @return true si se añadió o false.
     */
    public boolean addEnlace(Usuario usuarioDestino, int coste) {
        Enlace nuevoEnlace = new Enlace(this, usuarioDestino, coste);
        return this.addEnlace(nuevoEnlace);
    }

    /**
     * Retorna el enlace que enlaza este objeto con el usuario destino especificado
     * @param usuarioDestino usuario de destino del enlace a buscar
     * @return Enlace si lo contiene, o null en otro caso
     */
    public Enlace containsDestino(Usuario usuarioDestino) {
        for(Enlace x : this.enlaces){
            if(x.getUsuarioDestino() == usuarioDestino)
                return x;
        }

        return null;
    }

    /**
     * Modifica la exposición de este objeto
     * @param e nueva exposición del usuario
     */
    public void cambiarExposicion(Exposicion e){ this.exposicion = e; }

    /**
     * Añade un mensaje al historial de este objeto. Además, si el alcance del mensaje es mayor al alcance promedio
     * de los mensjaes del historial, incrementará en uno la exposición si es posible. Si el alcance del mensaje
     * es menor al alcancepromedio de los mensajes del historial, decrementará en uno la exposición si es posible.
     * @param mensaje el mensaje a añadir ( no debe estar ya contenido )
     * @return true si se añadió correctamente o false
     */
    public boolean añadirMensaje(Mensaje mensaje){

        if(this.historial.contains(mensaje))
            return false;

        if(this.historial.size()>0){
            int alcancePromedio=0;
            for(Mensaje m : this.historial){
                alcancePromedio += m.getAlcance();
            }
            alcancePromedio /= this.historial.size();

            //incrementar en uno la exposicion si se cumplen las condiciones especificadas
            if(mensaje.getAlcance() > alcancePromedio && this.exposicion.compareTo(Exposicion.VIRAL) < 0){
                switch (this.exposicion){
                    case Exposicion.ALTA -> this.cambiarExposicion(Exposicion.VIRAL);
                    case Exposicion.MEDIA -> this.cambiarExposicion(Exposicion.ALTA);
                    case Exposicion.BAJA -> this.cambiarExposicion(Exposicion.MEDIA);
                    case Exposicion.OCULTA -> this.cambiarExposicion(Exposicion.BAJA);
                }
            //decrementar en uno la exposicion si se cumplen las condiciones especificadas
            } else if(mensaje.getAlcance() < alcancePromedio && this.exposicion.compareTo(Exposicion.OCULTA) > 0){
                switch (this.exposicion){
                    case Exposicion.VIRAL -> this.cambiarExposicion(Exposicion.ALTA);
                    case Exposicion.ALTA -> this.cambiarExposicion(Exposicion.MEDIA);
                    case Exposicion.MEDIA -> this.cambiarExposicion(Exposicion.BAJA);
                    case Exposicion.BAJA -> this.cambiarExposicion(Exposicion.OCULTA);
                }
            }
        }

        return this.historial.add(mensaje);
    }

    /**
     * Retorna el nombre de este objeto
     * @return String nombre del usuario
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Retorna la capacidad de amplificación de este objeto
     * @return int >= 0
     */
    public int getCapacidadAmplificacion(){
        return this.capacidadAmplificacion;
    }

/**
 * Retorna el historial de este objeto como una lista de mensajes. Si no ha recibido ningun mensaje, se retornara una lista vacia
 * @return List<Mensaje> historial de mensajes
 */
    public List<Mensaje> getHistorial() { return this.historial; }

    /**
     * Retorna el enlace de indexado en la posición i de la lista de enlaces de este objeto
     * @param i posición del enlace a retornar en la lista
     * @return Enlace si el indice es válido o null
     */
    public Enlace getEnlace(int i){
        if(i < 0 || i >= this.enlaces.size())
            return null;
        return this.enlaces.get(i);
    }

    /**
     * Retorna la lista de enlaces de este objeto. Si no tiene enlaces, se retornará una lista vacía.
     * @return List<Enlace>
     */
    public List<Enlace> getEnlaces(){
        return this.enlaces;
    }

    /**
     * Retorna el número de enlaces que tiene este objeto
     * @return int >= 0
     */
    public int getNumEnlaces() {
        return this.enlaces.size();
    }

    /**
     * Retorna el enlace de este objeto cuyo destino es el usuario especificado.
     * Este metodo es equivalente a containsDestino(Usuario)
     * @param destino usuario de destino del enlace a buscar.
     * @return Enlace si existe o null
     */
    public Enlace getEnlace(Usuario destino) {
        return this.containsDestino(destino);
    }

    /**
     * Retorna el primer enlace cuya exposición sea al menos la indicada en el
     * argumento exposicion. Si no hay devuelve null.
     * @param exposicion Nivel de exposición a buscar en los enlaces
     * @return Enlace si se ha encontrado uno válido, null si no se ha encontrado
     */
    public Enlace getEnlaceExposicion(Exposicion exposicion) {
        for(Enlace e : this.enlaces){
            if(e.getUsuarioDestino().exposicion.compareTo(exposicion) >= 0)
                return e;
        }
        return null;
    }

    /**
     * Retorna el valor de exposición de este objeto
     * @return Exposicion
     */
    public Exposicion getExposicion(){
        return this.exposicion;
    }

    /**
     * Retorna un string que describe a este objeto con el siguiente formato
     * "@nombre(amplificacion)[enlaces]"
     * @return String
     */
    @Override
    public String toString(){
        return "@"+ this.getNombre() + "(" + this.getCapacidadAmplificacion() + ")" + this.enlaces.toString();
    }
}