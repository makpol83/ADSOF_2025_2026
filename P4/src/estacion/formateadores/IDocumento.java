package estacion.formateadores;

import java.util.List;

/**
 * Interfaz de objeto formateable
 */
public interface IDocumento {
    /**
     * Devuelve el string con el titulo del documentable
     * @return String
     */
    String getTituloDocumento();

    /**
     * Devuelve el string con el titulo de la seccion principal del documentable
     * @return String
     */
    String getTituloSeccionPrincipal();

    /**
     * Devuelve los párrafos en una lista en orden de la sección principal
     * @return List String
     */
    List<String> getParrafosSeccionPrincipal();

    /**
     * Devuelve en forma de lista de SeccionSecundaria cada una de las secciones secundarias
     * @return List SeccionSecundaria
     */
    List<SeccionSecundaria> getSeccionesSecundarias();
}
