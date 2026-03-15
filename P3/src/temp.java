package src;


import src.app.*;

public class temp {
    public static void main(String[] args){

        // Test EnlaceSeñuelo
        Usuario ana     = new Usuario("ana", 1);
        Usuario luis    = new Usuario("luis", 5);
        Usuario carmen  = new Usuario("carmen");
        Mensaje m = new Mensaje("Hi!", 50, ana);
        ana.addEnlace(new Enlace(ana, luis, 68));
        ana.addEnlace(carmen,33);
        carmen.addEnlace(new Enlace(carmen, luis, 11));

        System.out.println("Comprobamos si funcionan los enlaces señuelo");
        EnlaceSeñuelo sen = new EnlaceSeñuelo(carmen, ana, 5, 2, 1);
        carmen.addEnlace(sen);

        System.out.println();
        System.out.println("Difundimos el mensaje a Carmen por el enlace normal");
        m.difunde(carmen);

        System.out.println();
        System.out.println("Intentamos ver si la probabilidad de retorno funciona correctamente, " +
                "debería de devolver false siempre el próximo método");
        System.out.println(m.puedeDifundirPor(sen));

        System.out.println();
        System.out.println("Difundir por tanto debe fallar:");
        System.out.println(m.difunde(ana));

        System.out.println();
        System.out.println("Vemos si el coste del enlace se ha multiplicado por el factorCosteExtra (debe ser 15):");
        System.out.println(sen.costeReal());

        System.out.println();
        System.out.println("Creamos un enlace señuelo pero que esta vez siempre se va a mandar (probRetornoObligado = 0)");
        EnlaceSeñuelo sen2 = new EnlaceSeñuelo(carmen, luis, 5, 2, 0);
        carmen.addEnlace(sen2);

        System.out.println();
        System.out.println("El próximo método debe devolver true:");
        System.out.println(m.puedeDifundirPor(sen));

        System.out.println();
        System.out.println("Comprobamos el alcance del mensaje antes y despúes de difundirlo por el enlace señuelo:");
        System.out.println("El nuevo alcance debe ser: 50 - 33(Ana a Carmen) - 15(Carmen a ) = 2");
        System.out.println("Alcance anterior" + m.getAlcance());
        m.difunde(luis);
        System.out.println("Alcance nuevo: " + m.getAlcance());

        // Fin de tests EnlaceSeñuelo


        //MensajeControlado
        System.out.println();
        System.out.println("Creamos un MensajeControlado y se lo ponemos a un nuevo usuario Juan, que tiene enlaces señuelo:");
        Usuario juan = new Usuario("juan", 20, Exposicion.VIRAL);
        MensajeControlado mensajePresidencial = new MensajeControlado(
                "Este mensaje no puede pasar por enlaces señuelo.",
                100,
                juan,
                10 // Se pone la rigidez a 30 para que la exposición mínima a mandar sea MEDIA (requiere 10 o más)
        );

        System.out.println();
        System.out.println("Creo un usuario de exposición MEDIA y creo un enlace de juan a ese usuario:");
        Usuario rodolfo = new Usuario("rodolfo", 15, Exposicion.MEDIA);
        EnlaceSeñuelo enlacePeligroso = new EnlaceSeñuelo(juan, rodolfo, 10,3, 0);
        rodolfo.addEnlace(enlacePeligroso);

        System.out.println();
        System.out.println("Intento difundir el mensaje de juan a rodolfo por un enlace señuelo, debe fallar:");
        mensajePresidencial.difunde(enlacePeligroso);
        System.out.println("El usuario siguiente debe ser juan:");
        System.out.println(mensajePresidencial.getUsuarioActual().getNombre());

        System.out.println();
        System.out.println("Limpio los enlaces de juan");
        juan.getEnlaces().clear();

        System.out.println();
        System.out.println("Le añado ahora un enlace normal y cambio la exposición de rodolfo a BAJA");
        Enlace enlaceMuyNormal = new Enlace(juan, rodolfo, 10);
        juan.addEnlace(enlaceMuyNormal);
        rodolfo.cambiarExposicion(Exposicion.BAJA);

        System.out.println();
        System.out.println("Intento difundir el mensajePresidencial a través del nuevo enlace, debe fallar:");
        System.out.println("Debe fallar porque la exposición de rodolfo es BAJA");
        System.out.println("Debe ser false: " + mensajePresidencial.difunde(enlaceMuyNormal));

        System.out.println();
        System.out.println("Cambio la exposicion a MEDIA de rodolfo y veo que se difunde correctamente: ");
        rodolfo.cambiarExposicion(Exposicion.MEDIA);
        System.out.println("Debe ser true: " + mensajePresidencial.difunde(enlaceMuyNormal));
    }
}
