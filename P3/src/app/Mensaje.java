package src.app;

/**
 * Esta clase proporciona la implementación básica de un mensaje que pertenecerá a una red social.
 * El mensaje posee un autor, un usuario actual en el que se encuentra y un alcance, que indicará si un mensaje
 * puede o no cruzar un enlace en función de su coste.
 */
public class Mensaje {
    /** contenido del mensaje */
    private String mensaje;
    /** alcance del mensaje (debe ser mayor al coste de un enlace para poder transmitirse, y ademas se vera
     * decrementado por esta cantiddad. Además, si el mensaje llega a un usuario con capacidad de amplificacion
     * positiva, se incrementará en esa cantidad) */
    private int alcance;
    /** autor del mensaje */
    private Usuario autor;
    /** usuario en el que se encuentra el mensaje actualmente */
    private Usuario usuarioActual;

    /**
     * Construye y retorna un mensaje con los valores especificados. Por defecto, el usuarioActual de un mensaje
     * recién construido será su autor.
     * @param mensaje contenido del mensaje
     * @param alcanceInicial alcance inicial del menasje (se espera que sea una cantidad positiva para un
     *                       funcionamiento correcto
     * @param autor autor del mensaje
     */
    public Mensaje(String mensaje, int alcanceInicial, Usuario autor){
        this.mensaje = mensaje;
        this.autor = autor;
        this.alcance = alcanceInicial;
        this.usuarioActual = autor;
    }

    /**
     * Retorna el autor de este objeto
     * @return Usuario autor del mensaje
     */
    public Usuario getAutor(){
        return this.autor;
    }

    /**
     * Retorna el usuario actual en el que se encuentra este objeto
     * @return Usuario actual del mensaje
     */
    public Usuario getUsuarioActual(){
        return this.usuarioActual;
    }

    /**
     * Retorna el contenido de este objeto
     * @return String contenido del mensaje
     */
    public String getMensaje(){
        return this.mensaje;
    }

    /**
     * Retorna el alcance actual de este objeto
     * @return int alcance
     */
    public int getAlcance(){
        return this.alcance;
    }

    /**
     * Indica si este objeto se puede transmitir a traves del enlace especificado,
     * si el enlace tiene probabilidad de retorno obligado se comprueba si pasa
     * o no aquí
     * @param e Enlace por el que transmitir el mensaje
     * @return boolean true si es posible o false
     */
    public boolean puedeDifundirPor(Enlace e){
        if(this.alcance >= e.costeReal())
            return true;
        else
            return false;
    }

    /**
     * Indica si este objeto es aceptado por el usuario especificado. Siempre retorna true, pues su finalidad
     * es darle uso en futuras extensiones de esta clase
     * @param u Usuario para el que comprobar si el mensaje puede transmitirse
     * @return boolean true
     */
    public boolean aceptadoPor(Usuario u){
        return true;
    }

    /**
     * Difunde este objeto a través del enlace especificado si es posible. Además, existe una probabilidad de fallo en
     * la difusión que vendrá determinada por la probabilidad de retorno obligado del enlace. En caso de que el usuario
     * destino reciba el mensaje por primera vez, será añadido a su historial de mensajes
     * @param e Enlace por el que difundir el mensaje
     * @return true si se ha difundido o false
     */
    public boolean difunde(Enlace e){
        if(this.puedeDifundirPor(e) == false || aceptadoPor(e.getUsuarioDestino()) == false)
            return false;
        
        if(e.equals(this.usuarioActual.getEnlace(e.getUsuarioDestino())) == false)
            return false;

        if(e.esRetornoObligado())
            return false;
        
        this.usuarioActual = e.getUsuarioDestino();
        this.alcance -= e.costeReal();
        this.alcance += e.getUsuarioDestino().getCapacidadAmplificacion();
        this.usuarioActual.añadirMensaje(this);
        return true;
    }

    /**
     * Retorna la rigidez de este objeto. Este metodo siempre devuelve 0. Se espera que sea sobreescrito en futuras
     * extensiones de esta clase.
     * @return int 0
     */
    public int getRigidez(){
        return 0;
    }

    /**
     * Difunde el mensaje a traves de una serie de usuarios secuencielmente usando para ello el enlace del
     * usuario actual con el proximo destino.
     * @param usuarios secuencia de usuarios por la que difundir el mensaje
     * @return true si el mensaje se ha difundido correctamente por todos los usuarios especificados, o false en
     * otro caso.
     */
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

    /**
     * Retorna un string descriptivo de este objeto
     * @return String
     */
    @Override
    public String toString() {
        return "Mensaje(" + this.mensaje + ":" + this.alcance + ") en @" + this.usuarioActual.getNombre();
    }

}