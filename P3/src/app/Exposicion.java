package src.app;

/**
 * Enumeración para especificar los niveles de exposición asociados a un usuario.
 * Contiene la rigidez asociada a cada exposicion.
 */
public enum Exposicion {
    OCULTA(0),
    BAJA(5),
    MEDIA(10),
    ALTA(20),
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
