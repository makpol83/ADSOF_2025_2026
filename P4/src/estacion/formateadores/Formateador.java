package estacion.formateadores;

/**
 * Interfaz para los formateadores de IDocumento
 */
public interface Formateador {
    /**
     * Debe devolver un String con el IDocumento formateado al tipo de la clase
     * @param informacion Objeto que implementa IDocumento
     * @return String
     */
    String formatear(IDocumento informacion);
}
