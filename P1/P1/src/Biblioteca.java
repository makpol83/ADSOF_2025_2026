import java.util.*;

/**
 * Esta clase incorpora la interfaz de una Biblioteca que contiene objetos de la clase Libro
 * Autores: Maksym Polyak y Aarón Charameli Mair
 * Version: 1.0
 * Nombre del fichero: Biblioteca.java
 */
public class Biblioteca {
    private String nombre;
    private HashMap<String, List<Libro>> libros; //hashmap donde almacenar los libros de la biblioteca
    private int tamanno_inicial = 10;

    /**
     * Método constructor de una Biblioteca vacía.
     * @param nombre nombre de la Biblioteca
     */
    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new HashMap<>(tamanno_inicial);
    }

    /**
     * Método constructor de una Biblioteca que en el momento de su creación tendrá los Libros
     * contenidos en la Lista de entrada.
     * @param nombre nombre de la Biblioteca
     * @param libros lista de libros que contiene la Biblioteca
     */
    public Biblioteca(String nombre, List<Libro> libros) {
        this(nombre);
        for (Libro l : libros) {
            String genero = l.tomarGenero();

            if(this.libros.containsKey(genero) == false) {
                this.libros.put(genero, new ArrayList<> (List.of(l)));

            } else {
                List<Libro> lista = this.libros.get(genero);
                lista.add(l);
            }
        }
    }

    /**
     * Método para añadir un libro a la biblioteca. Si la biblioteca ya contiene el libro especificado,
     * se volverá a añadir una referencia al mismo Libro.
     * @param l libro que se añadirá a la biblioteca.
     */
    public void annadirLibro(Libro l) {
        if(this.libros.containsKey(l.tomarGenero())) {
            List<Libro> lst = this.libros.get(l.tomarGenero());
            lst.add(l);
        }
        else {
            List<Libro> lst = new ArrayList<>();
            lst.add(l);
            this.libros.put(l.tomarGenero(),lst);
        }
    }

    /**
     * Método con el que obtener una lista con todos los libros de un género.
     * Si la biblioteca no contiene ningún libro con el género especificado se volverá una lista vacía.
     * Si se quiere obtener libros cuyo género no ha sido especificado, se debe introducir como argumento
     * "Sin género".
     * @param genero género del que se obtendrá la lista con los libros
     * @return Lista de libros del mismo género.
     */
    public List<Libro> librosPorGenero(String genero) {
        List<Libro> libros = new ArrayList<> (List.of());
        if(this.libros.containsKey(genero) == false) {
            return libros;
        }
        // Devuelve la lista, no una copia
        return this.libros.get(genero);
    }

    /**
     * Método para obtener una lista de libros cuyo año de publicación es posterior al especificado
     * (sin incluirlo). No se admiten libros anteriores al año 0.
     * Para obtener los libros cuyo año de publicación no ha sido especificado, se debe introducir como
     * argumento '-1'.
     * Si se introduce un número negativo distinto de -1, se mostrarán, al menos, todos los libros
     * contenidos en la biblioteca con fecha de publicación válida.
     * @param añoPublicacion año de publicación tras el que se muestran los libros
     * @return Lista de libros con año de publicación posterior al argumento
     */
    public List<Libro> librosPosterioresA(int añoPublicacion) {
        List<Libro> libros = new ArrayList<> (List.of());

        //lista con libros cuyo año no ha sido especificado
        if(añoPublicacion == -1) {
            for (List<Libro> lista : this.libros.values()) {
                for (Libro l : lista) {
                    int fecha = l.tomarAnnoPublicacion();
                    if (fecha == -1) {
                        libros.add(l);
                    }
                }
            }
            return libros;
        }

        //lista con libros de año posterior al argumento.
        for (List<Libro> lista : this.libros.values()) {
            for (Libro l : lista) {
                int fecha = l.tomarAnnoPublicacion();
                if (fecha > añoPublicacion && fecha != -1) {
                    libros.add(l);
                }
            }
        }

        return libros;
    }


}
