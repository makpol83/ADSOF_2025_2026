package estacion.utils;

/**
 * Clase de utilidad para formateo
 */
public abstract class Formatter {

    /**
     * Constructor por defecto
     */
    public Formatter(){}

    /**
     * Formatea un double con dos decimales
     * @param d double
     * @return String
     */
    public static String formatDouble(double d){
        return String.format("%.2f", d);
    }
}
