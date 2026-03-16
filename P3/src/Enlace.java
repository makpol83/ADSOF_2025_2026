
/**
 * Esta clase proporciona una implementación básica para un enlace perteneciente a una red social. Un enlace está
 * formado por un usuario de origen, un usuario de destino y un coste. Para transmitir un mensaje por un enlace,
 * debe tener un alcance mayor o igual al coste del enlace.
 */

public class Enlace {
    /** variable estática que representa la suma los costes de todos los enlaces creados */
    private static int costeAcumulado=0;

    /** usuario de origen del enlace */
    private Usuario usuarioOrigen;
    /** usuario de destino del enlace */
    private Usuario usuarioDestino;
    /** coste del enlace */
    private int coste;

    /**
     * Construye y devuelve un enlace dados un usuario de origen y un usuario de destino. Por defecto, tiene un coste
     * de valor 1
     * @param usuarioOrigen usuario de origen del enlace
     * @param usuarioDestino usuario de destino del enlace
     */
    public Enlace(Usuario usuarioOrigen, Usuario usuarioDestino){
        this.usuarioOrigen = usuarioOrigen;
        this.usuarioDestino = usuarioDestino;
        this.coste = 1;
        Enlace.costeAcumulado += 1;
    }

    /**
     * Construye y retorna un enlace dados un usuario de origen, un usuario de destino y un coste (que debe ser
     * mayor o igual a 1)
     * @param usuarioOrigen usuario de origen del enlace
     * @param usuarioDestino usuario de destino del enlace
     * @param coste coste del enlace
     */
    public Enlace(Usuario usuarioOrigen, Usuario usuarioDestino, int coste){
        this(usuarioOrigen, usuarioDestino);
        if(coste > 1){ 
            this.coste = coste;
            Enlace.costeAcumulado += coste - 1;
        }
    }

    /**
     * Retorna el usuario de origen de este objeto
     * @return Usuario de origen
     */
    public Usuario getUsuarioOrigen() {
        return this.usuarioOrigen;
    }

    /**
     * Retorna el usuario de destino de este objeto
     * @return Usuario de destino
     */
    public Usuario getUsuarioDestino() {
        return this.usuarioDestino;
    }

    /**
     * Retorna el coste de este objeto
     * @return int >= 1
     */
    public int getCoste() {
        return this.coste;
    }

    /**
     * Retorna el coste acumulado de todos los enlaces creados hasta el momento
     * @return int >= 1
     */
    public int getCosteAcumulado() {
        return Enlace.costeAcumulado;
    }

    /**
     * Retorna el coste especial de este objeto. Este metodo siempre retorna 0. Tiene como finalidad que subclases
     * lo sobreescriban para poseer un comportamiento distinto.
     * @return int 0
     */
    public int costeEspecial(){
        return 0;
    }

    /**
     * Retorna true si el mensaje que intenta pasar por este enlace debe quedarse en el emisario.
     * False si logra pasar correctamente
     * @return true si no pasa, false si pasa
     */
    public boolean esRetornoObligado(){ return false; }

    /**
     * Retorna el coste real de este objeto, calculado como el coste mas el coste especial
     * @return int coste real
     */
    public int costeReal(){
        return this.costeEspecial() + this.coste;
    }

    /**
     * Cambia el destino de este enlace por otro usuario, además de modificar el coste del enlace
     * @param nuevoDestino nuevo usuario de destino
     * @param nuevoCoste nuevo coste del enlace
     */
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

    /**
     * Retorna un booleano que indica si el enlace es seguro o no. La finalidad de este metodo es aportar informacion
     * sobre como manejar el enlace. (Concretamente, un MensajeControlado, no se podra enviar por un EnlaceSeñuelo,
     * que no es seguro.
     * @return true
     */
    public boolean esSeguro(){
        return true;
    }

    /**
     * Retorna un String descriptivo de este objeto
     * @return String
     */
    @Override
    public String toString(){
        return "(@" + usuarioOrigen.getNombre() + "--" + this.coste + "-->@" + usuarioDestino.getNombre() + ")";
    }
}   