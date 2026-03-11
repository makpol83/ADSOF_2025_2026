package trayectos;

/**
 * Esta clase incorpora la interfaz para realizar tramos a pie en un trayecto
 * Autores: Maksym Polyak y Aarón Charameli Mair
 * Version: 1.0
 * Nombre del fichero: TramoAPie.java
 */
public class TramoAPie extends TramoTrayecto{
    private Ritmo ritmo;
    private double numKilometros;

    /**
     * Método constructor para un tramo a pie sin ritmo asignado, por defecto MODERADO
     * @param origen Nombre del origen
     * @param destino Nombre del destino
     * @param numKilometros Número de kilómetros del tramo
     */
    public TramoAPie(String origen, String destino, double numKilometros) {
        super(origen, destino);
        this.numKilometros = numKilometros;
        this.ritmo = Ritmo.MODERADO;
    }

    /**
     * Método constructor para un tramo a pie con ritmo asignado
     * @param origen Nombre del origen
     * @param destino Nombre del destino
     * @param numKilometros Número de kilómetros del tramo
     * @param ritmo Ritmo asociado al tramo
     */
    public TramoAPie(String origen, String destino, double numKilometros, Ritmo ritmo) {
        this(origen, destino, numKilometros);
        this.ritmo = ritmo;
    }

    /**
     * Método para obtener el tiempo asociado al tramo a pie
     * @return double tiempo en minutos
     */
    @Override
    public double tiempo() {
        return this.numKilometros * this.ritmo.getMinutosPorKilometro();
    }

    /**
     * Método para mostrar en un string el tramo a pie, su tiempo y su ritmo
     * @return String con el contenido de la clase formateado
     */
    @Override
    public String toString() {
        return "A pie " + super.toString() + " " + this.ritmo.toString();
    }
}
