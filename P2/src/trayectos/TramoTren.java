package trayectos;

/**
 * Esta clase incorpora la interfaz para realizar tramos en tren
 * Autores: Maksym Polyak y Aarón Charameli Mair
 * Version: 1.0
 * Nombre del fichero: TramoTren.java
 */
public class TramoTren extends TramoTrayecto {
    private Linea linea;
    private int numParadas;

    /**
     * Método constructor del tramo en tren
     * @param origen Nombre del origen
     * @param destino Nombre del destino
     * @param linea Línea asociada al tren
     * @param numParadas Número de paradas a realizar en la línea
     */
    public TramoTren(String origen, String destino, Linea linea, int numParadas) {
        super(origen, destino);
        this.linea = linea;
        this.numParadas = numParadas;
    }

    /**
     * Método para calcular el tiempo necesario para recorrer el tramo en tren
     * @return double con el tiempo en minutos
     */
    @Override
    public double tiempo() {
        return this.numParadas * this.linea.getMinutosEntreParadas();
    }

    /**
     * Método para mostrar que el tramo es a tren, mostrar origen, destino, línea y tiempo
     * @return String con información del tramo en tren
     */
    @Override
    public String toString() {
        return "En tren de la línea " + this.linea + " " + super.toString();
    }

}
