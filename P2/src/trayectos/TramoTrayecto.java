package trayectos;

/**
 * Esta clase incorpora la interfaz para realizar tramos de forma general de un trayecto, clase abstracta
 * Autores: Maksym Polyak y Aarón Charameli Mair
 * Version: 1.0
 * Nombre del fichero: TramoTrayecto.java
 */
public abstract class TramoTrayecto {
    private String origen;
    private String destino;

    /**
     * Método constructor para un TramoTrayecto
     * @param origen Nombre del origen
     * @param destino Nombre del destino
     */
    public TramoTrayecto(String origen, String destino) {
        this.origen = origen;
        this.destino = destino;
    }

    /**
     * Método abstracto para calcular el tiempo necesario para recorrer el tramo
     * @return double tiempo en minutos
     */
    public abstract double tiempo ();

    /**
     * Método para representar de forma general el origen, destino y tiempo necesario para recorrer un tramo
     * @return String con información del tramo
     */
    @Override
    public String toString() {
        return "desde " + this.origen + " a " + this.destino + ": " + this.tiempo() + " minutos";
    }
}
