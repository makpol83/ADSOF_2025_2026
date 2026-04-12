package estacion.utils;

public abstract class Formatter {
    public static String formatDouble(double d){
        return String.format("%.2f", d);
    }
}
