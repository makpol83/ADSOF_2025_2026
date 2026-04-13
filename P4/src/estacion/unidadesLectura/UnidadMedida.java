package estacion.unidadesLectura;

/**
 * Interfaz unidad de medida
 */
public interface UnidadMedida {
    /**
     * Getter valor mínimo a tomar
     * @return double
     */
    double getValorMinimo();

    /**
     * Getter valor máximo a tomar
     * @return double
     */
    double getValorMaximo();

    /**
     * Comprueba si value está en rango válido para su unidad
     * @param value a comprobar
     * @return true si está en rango válido o false si no
     */
    boolean esRangoValido(double value);

    /**
     * Obtiene el nombre del enum, irrelevante, pero necesario si se implementan de otra forma
     * @return String
     */
    String name();
}
