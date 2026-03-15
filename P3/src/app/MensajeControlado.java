package src.app;

/**
 * Clase MensajeControlado, implementa la funcionalidad de mensajes que no pueden ser
 * mandados si no se cumple el requisito de rigidez, tan sólo se sobreescribe el método
 * aceptadoPor de Mensaje.
 */
public class MensajeControlado extends Mensaje{
    /** Rigidez asociada al mensaje >= 0*/
    private int rigidez;

    /**
     * Constructur MensajeControlado, añade un argumento de rigidez.
     * @param mensaje Mensaje que guarda
     * @param alcanceInicial Alcance inicial del mensasje
     * @param autor Autor del mensaje
     * @param rigidez Rigidez del mensaje
     */
    public MensajeControlado(String mensaje, int alcanceInicial, Usuario autor, int rigidez){
        super(mensaje, alcanceInicial, autor);
        this.rigidez = rigidez;
    }

    @Override
    /**
     * Sobreescribe el método para incluir que un MensajeControlado no puede ser
     * transmitido por un enlace señuelo(no seguro)
     * @param e Enlace a comprobar
     * @return true si puede difundirse, false si no
     */
    public boolean puedeDifundirPor(Enlace e){
        // Comprueba si el enlace es Señuelo o no.
        if(e.esSeguro() == false)
            return false;

        return super.puedeDifundirPor(e);
    }

    @Override
    /**
     * Indica si el mensaje es aceptado por el usuario especificado, en esta clase
     * se considera la rigidez del mensaje.
     * @param u Usuario en el que se comprueba si el usuario puede llegar.
     * @return
     */
    public boolean aceptadoPor(Usuario u) {
        return this.rigidez >= u.getExposicion().getRigidez();
    }
}
