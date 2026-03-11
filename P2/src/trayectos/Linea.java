package trayectos;

/**
 * Enumeración que incorpora las distintas líneas de tren de la aplicación
 * Autores: Maksym Polyak y Aarón Charameli Mair
 * Version: 1.0
 * Nombre del fichero: Linea.java
 */
public enum Linea {
    /** Línea C1 */
    C1("azul claro", 5),
    /** Línea C4 */
    C4("azul oscuro", 10),
    /** Línea C5 */
    C5("amarilla", 30);

    private String color;
    private int minutosEntreParadas;

    /**
     * Método constructor de la linea
     * @param color Nombre del color asociado a la linea
     * @param minutosEntreParadas Número de minutos necesarios para ir de una parada a otra
     */
    private Linea(String color, int minutosEntreParadas) {
        this.color = color;
        this.minutosEntreParadas = minutosEntreParadas;
    }

    /**
     * Getter del color de la linea
     * @return String con el nombre del color de la linea
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Getter del número de minutos entre paradas
     * @return int con el número de minutos entre paradas
     */
    public int getMinutosEntreParadas() {
        return this.minutosEntreParadas;
    }

    /**
     * Método para mostrar el color asociado a la línea
     * @return String con el nombre del color asociado a la línea
     */
    public String toString() {
        return this.name() + " (" + this.color + ")";
    }
}
