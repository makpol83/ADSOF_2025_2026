/**
 * Esta clase contiene la interfaz implementada de un Libro
 * Autores: Maksym Polyak y Aarón Charameli Mair
 * Version: 1.0
 * Nombre del fichero: Libro.java
 */
public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int ejemplaresDisponibles = 0;
    private int annoPublicacion = -1; // Debe ser mayor que -1 para considerarse un año válido
    private String genero = "Sin género";

    /**
     * Método constructor de un Libro. Este constructor asignará a los parámetros no especificados,
     * ejemplaresDisponibles, annoPublicacion y genero, valores por defecto.
     * @param isbn isbn del Libro
     * @param titulo título del Libro
     * @param autor autor del Libro
     *
     */
    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

    /**
     * Método constructor de un Libro. Este constructor asignará a los parámetros no especificados,
     * annoPublicacion y genero, valores por defecto.
     * @param isbn isbn del Libro.
     * @param titulo título del Libro.
     * @param autor autor del Libro.
     * @param ejemplaresDisponibles ejemplares disponibles del Libro.
     */
    public Libro(String isbn, String titulo, String autor, int ejemplaresDisponibles) {
        this(isbn, titulo, autor);
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    /**
     * Método constructor de un Libro. Este constructor asignará a los parámetros no especificados,
     * annoPublicacion y genero, valores por defecto. Se asume que el año de publicación es positivo.
     * @param isbn isbn del Libro.
     * @param titulo título del Libro.
     * @param autor autor del Libro.
     * @param ejemplaresDisponibles ejemplares disponibles del Libro.
     * @param genero genero del Libro.
     * @param annoPublicacion año de publicación del Libro.
     */
    public Libro(String isbn, String titulo, String autor, int ejemplaresDisponibles, String genero, int annoPublicacion) {
        this(isbn, titulo, autor, ejemplaresDisponibles);
        this.genero = genero;
        this.annoPublicacion = annoPublicacion;
    }

    /**
     * Método con el que obtener el género de un Libro si lo tiene.
     * @return el género de un Libro o "Sin género" si no lo tiene.
     */
    public String tomarGenero() {
        return this.genero;
    }

    /**
     * Método con el que obtener el año de publicación de un Libro.
     * @return El año de publicación del Libro o el entero -1 si no tiene.
     */
    public int tomarAnnoPublicacion() {
        return this.annoPublicacion;
    }


    /**
     * Método para verificar si el libro está disponible
     * @return True si quedan ejemplares disponibles o False en otro caso.
     */
    public boolean estaDisponible() {
        return this.ejemplaresDisponibles > 0;
    }

    /**
     * Método para verificar si un Libro tiene un género especificado.
     * @return el género del Libro o "Sin género" si no lo tiene.
     */
    public boolean tieneGenero() {
        return this.genero.compareTo("Sin género") != 0;
    }

    /**
     * Método para verificar si un Libro tiene un año de publicación especificado.
     * @return el año de publicación del Libro o -1 si no se especificó
     */
    public boolean tieneAnnoPublicacion() {
        return this.annoPublicacion != -1;
    }

    /**
     * Método con el que tomar un Libro prestado, reduciendo en 1 el número de ejemplares disponibles.
     * @return True si se pudo coger el Libro o False si no quedaban ejemplares disponibles.
     */
    public boolean prestar() {
        if (estaDisponible()) {
            this.ejemplaresDisponibles--;
            return true;
        }
        return false;
    }

    /**
     * Método con el que devolver un Libro prestado, aumentando en 1 el número de ejemplares disponibles.
     */
    public void devolver() {
        this.ejemplaresDisponibles++;
    }

    /**
     * Método privado con el que obtener una descripción de un Libro en formato String, incluyendo datos
     * como su título y autor y su género y año de publicación en caso de tenerlos.
     * @return el string con la descrpcion.
     */
    private String descripcion() {
        String annoPublicacion = this.tieneAnnoPublicacion() ? ", Año publicación: " + this.annoPublicacion : "";
        String genero = this.tieneGenero() ? ", Género: " + this.genero : "";
        String estado = this.estaDisponible() ? "Disponible" : "No disponible";
        return "'"+this.titulo+"' de " + this.autor + genero + annoPublicacion + " [" + estado + "]";
    }

    /**
     * Método sobreescrito para imprimir la descripción de un libro, incluyendo datos como su ISBN, título,
     * autor, ejemplares disponibles y genero y año de publicación si fueron especificados.
     * @return un string con el contenido
     */
    @Override
    public String toString() {
        return "ISBN: " + this.isbn + ". " + this.descripcion() + " (" + this.ejemplaresDisponibles +
                " ejemplares disponibles)";
    }
}