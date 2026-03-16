
/**
 * Enumeración para especificar los niveles de exposición asociados a un usuario.
 * Contiene la rigidez asociada a cada exposicion.
 */
public enum Exposicion {
    /** Exposicion OCULTA */
    OCULTA(0),
    /** Exposicion BAJA */
    BAJA(5),
    /** Exposicion MEDIA */
    MEDIA(10),
    /** Exposicion ALTA */
    ALTA(20),
    /** Exposicion VIRAL */
    VIRAL(50);

    /** Atributo que guarda la rigidez asociada a una exposición*/
    int rigidez;

    /**
     * Constructor Exposicion
     * @param rigidez Rigidez asociada a la exposicion
     */
    Exposicion(int rigidez){
        this.rigidez = rigidez;
    }

    /**
     * Getter de la rigidez
     * @return int >= 0
     */
    public int getRigidez(){
        return this.rigidez;
    }
}
