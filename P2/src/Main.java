import trayectos.*;

/**
 * Esta clase comprueba el funcionamiento de las clases TramoAPie, TramoTren, TramoTrayecto y las enumeraciones.
 * Autores: Maksym Polyak y Aarón Charameli Mair
 * Version: 1.0
 * Nombre del fichero: Main.java
 */
public class Main {
    /**
     * Método de inicialización del programa
     * @param args argumentos recibidos
     */
    public static void main(String[] args) {
        TramoTrayecto[] trayecto = {
                new TramoAPie("Hotel Puerta del Sol", "Sol Renfe", 1),
                new TramoTren("Sol Renfe", "Cantoblanco Renfe", Linea.C4, 4),
                new TramoAPie("Cantoblanco Renfe", "EPS", 2.6, Ritmo.RAPIDO),
        };

        for(TramoTrayecto tramo: trayecto)
            System.out.println(tramo);
    }

    /**
     * Método constructor del main, vacío para evitar warnings de javadoc
     */
    public Main() {
    }
}
