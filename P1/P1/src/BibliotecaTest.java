import java.util.*;

/**
 * Esta clase pone a prueba el correcto funcionamiento de las clases Biblioteca y Libro.
 * Autores: Maksym Polyak, Aaron Charameli Mair.
 * Version: 1.0.
 * Nombre del fichero: BibliotecaTest.java
 */
public class BibliotecaTest {
    /**
     * Punto de entrada de la aplicación.
     * El programa muestra en pantalla una serie de libros que se van a insertar en la biblioteca, para luego
     * filtrarlos por género y por año de publicación posterior. Es una prueba para verificar que la clase Biblioteca
     * funciona correctamente.
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        List<Libro> libros = new ArrayList<> (List.of(
                new Libro("1", "El Quijote", "Miguel de Cervantes", 5, "Fantasía", 1995), // ISBN, titulo, autor, #ejemplares
                new Libro("2", "El murciélago", "Jo Nesbo", 1, "Fantasía", 1990),
                new Libro("3", "Learn Java", "David Hoffman", 6, "Misterio", 2005)));

        Biblioteca b = new Biblioteca("BibliotecaTest", libros);

        System.out.println("Lista de libros insertados: ");
        System.out.println(libros);

        System.out.println("Comprobamos si funciona el filtrado por género:");

        System.out.println("Mostramos los libros de Fantasía:");
        List<Libro> temp = b.librosPorGenero("Fantasía");
        System.out.println(temp);

        System.out.println("Mostramos los libros de Misterio:");
        temp = b.librosPorGenero("Misterio");
        System.out.println(temp);

        System.out.println("Mostramos los libros de Drama (No hay ninguno):");
        temp = b.librosPorGenero("Drama");
        System.out.println(temp);

        System.out.println("Comprobamos si funciona el filtrado por fecha:");

        System.out.println("Mostramos los libros posteriores a 1994(Muestra parcial):");
        temp = b.librosPosterioresA(1994);
        System.out.println(temp);

        System.out.println("Mostramos los libros posteriores a 1989(Todos los libros):");
        temp = b.librosPosterioresA(1989);
        System.out.println(temp);

        System.out.println("Mostramos los libros posteriores a 2010(No hay):");
        temp = b.librosPosterioresA(2010);
        System.out.println(temp);

        System.out.println("Comprobar si todos los libros se han mostrado correctamente.");

        Libro l = new Libro("4", "La isla de la POO", "Linus Torvald");
        System.out.println("Mostramos el libro sin género y sin fecha de publicación y lo insertamos:");
        System.out.println(l);

        b.annadirLibro(l);

        System.out.println("Mostramos que se pueden obtener los libros sin género en la biblioteca:");
        temp = b.librosPorGenero("Sin género");
        System.out.println(temp);

        System.out.println("Si queremos mostrar los libros sin año de publicación pasamos de argumento -1:");
        temp = b.librosPosterioresA(-1);
        System.out.println(temp);
    }
}
