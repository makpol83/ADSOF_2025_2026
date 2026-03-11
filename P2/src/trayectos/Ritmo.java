package trayectos;

/**
 * Enumeración para definir los distintos ritmos que se pueden tomar en un tramo a pie
 * Autores: Maksym Polyak y Aarón Charameli Mair
 * Version: 1.0
 * Nombre del fichero: Ritmo.java
 */
public enum Ritmo {
    /** Ritmo suave */
    SUAVE(15),
    /** Ritmo moderado */
    MODERADO(10),
    /** Ritmo rápido */
    RAPIDO(8);

    private int minutosPorKilometro;

    /**
     * Método constructor del ritmo
     * @param minutosPorKilometro minutos necesarios para recorrer un kilómetro
     */
    private Ritmo(int minutosPorKilometro) {
        this.minutosPorKilometro = minutosPorKilometro;
    }

    /**
     * Getter de los minutos por kilometro del ritmo
     * @return int con los minutos por kilómetro
     */
    public int getMinutosPorKilometro() {
        return this.minutosPorKilometro;
    }

    /**
     * Método para mostrar el ritmo asociado
     * @return String con el ritmo seleccionado
     */
    @Override
    public String toString() {
        return "(ritmo " + this.name() + ")";
    }
}
