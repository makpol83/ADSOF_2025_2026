package estacion.formateadores;

import java.util.List;

import estacion.EstacionMeteorologica;

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
    List<String> getParrafosSeccionPrincipal();
    List<SeccionSecundaria> getSeccionesSecundarias();
}
