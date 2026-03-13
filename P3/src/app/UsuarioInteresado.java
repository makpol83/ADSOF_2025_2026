package src.app;

import java.util.ArrayList;

/**
 * Clase UsuarioInteresado, modifica la funcionalidad al obtener enlaces
 * para difundir, priorizando los enlaces a usuarios de exposición ALTA o VIRAL,
 * tan sólo modifica getEnlace.
 */
public class UsuarioInteresado extends Usuario{

    /**
     * Constructor UsuarioInteresado, capacidad de Amplificación/exposición por defecto
     * @param nombre Nombre del usuario
     */
    public UsuarioInteresado(String nombre) {
        super(nombre);
    }

    /**
     * Constructor UsuarioInteresado con capacidad de Amplificacion especifica. Exposición por defecto
     * @param nombre Nombre del usuario
     * @param capacidadAmplificacion Capacidad de Amplificación del Usuario
     */
    public UsuarioInteresado(String nombre, int capacidadAmplificacion) {
        super(nombre, capacidadAmplificacion);
    }

    /**
     * Constructor UsuarioInteresado con exposición específica y capacidad de Amplifiación específica.
     * @param nombre Nombre del usuario
     * @param capacidadAmplificacion Capacidad de Amplificación del Usuario
     * @param exposicion Exposición del usuario
     */
    public UsuarioInteresado(String nombre, int capacidadAmplificacion, Exposicion exposicion) {
        super(nombre, capacidadAmplificacion, exposicion);
    }

    /**
     * Constructor UsuarioInteresado con exposición específica y capacidad de amplificacion por defecto
     * @param nombre Nombre del usuario
     * @param exposicion Exposición del usuario
     */
    public UsuarioInteresado(String nombre, Exposicion exposicion) {
        super(nombre, exposicion);
    }



    @Override
    /**
     * Método para conseguir el enlace hacia el destino recibido, siempre y cuando no
     * haya un usuario destino en los enlaces considerado de exposicion ALTA o VIRAL.
     * @param destino Usuario destino a buscar si no existe un usuario con exposicion ALTA o VIRAL.
     * @return primer enlace a un usuario de exposicion ALTA o VIRAL, si no hay, enlace al
     * usuario destino especificado en argumento destino, si no hay, devuelve null.
     */
    public Enlace getEnlace(Usuario destino){
        Enlace enlace = getEnlaceExposicion(Exposicion.ALTA);
        if(enlace == null)
            return super.getEnlace(destino);

        return enlace;
    }
}
