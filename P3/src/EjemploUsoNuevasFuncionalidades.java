package src;


import src.app.*;

public class EjemploUsoNuevasFuncionalidades {
    public static void main(String[] args){

        // Test EnlaceSeñuelo
        Usuario ana     = new Usuario("ana", 1);
        Usuario luis    = new Usuario("luis", 5);
        Usuario carmen  = new Usuario("carmen");
        Mensaje m = new Mensaje("Hi!", 50, ana);
        ana.addEnlace(new Enlace(ana, luis, 68));
        ana.addEnlace(carmen,33);

        System.out.println("Comprobamos si funcionan los enlaces señuelo");
        EnlaceSeñuelo sen = new EnlaceSeñuelo(carmen, ana, 5, 2, 1);
        carmen.addEnlace(sen);

        System.out.println();
        System.out.println("Difundimos el mensaje a Carmen por el enlace normal");
        m.difunde(carmen);

        System.out.println();
        System.out.println("Intentamos ver si la probabilidad de retorno funciona correctamente, " +
            "debería de devolver true el próximo método");
        System.out.println(sen.esRetornoObligado());


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
        System.out.println("El próximo método debe devolver false:");
        System.out.println(sen2.esRetornoObligado());

        System.out.println();
        System.out.println("Comprobamos el alcance del mensaje antes y despúes de difundirlo por el enlace señuelo:");
        System.out.println("El nuevo alcance debe ser: 50 - 33(Ana a Carmen) + 2(amplificacion carmen) - 15(Carmen a Luis) + 5(amplifiacion luis) = 9");
        System.out.println("Alcance anterior: " + m.getAlcance());
        m.difunde(luis);
        System.out.println("Alcance nuevo: " + m.getAlcance());

        // Fin de tests EnlaceSeñuelo

        //UsuarioInteresado & Exposición & historial

        ana = new Usuario("ana", Exposicion.BAJA);
        UsuarioInteresado interesado = new UsuarioInteresado("interesado", Exposicion.MEDIA);
        Usuario famoso = new Usuario("famoso", Exposicion.VIRAL);
        interesado.addEnlace(ana, 1);
        interesado.addEnlace(famoso, 1);
        Mensaje bulo1 = new Mensaje("bulo2", 50, interesado);
       
        System.out.println();
        System.out.println("Comprobamos si UsuarioInteresado difunde a Usuario con Exposicion >= Alta antes que al destino especificado:");
        System.out.println("El mensaje comienza en interesado y debe transmitirse a famoso en lugar de ana");
        System.out.println(bulo1);
        System.out.println(interesado);
        bulo1.difunde(ana);
        System.out.println(bulo1);

        System.out.println();
        System.out.println("Comprobamos si UsuarioInteresado difunde al usuario especificado en caso de no tener un contacto con exposicion alta");
        Mensaje bulo2 = new Mensaje("bulo2", 50, interesado);
        famoso.cambiarExposicion(Exposicion.BAJA);
        System.out.println("ahora, exposicion de famoso = MEDIA");
        System.out.println(bulo2);
        System.out.println("difundimos bulo 2 de interesado a ana. Resultado esperado: mensaje termina en ana");
        bulo2.difunde(ana);
        
        System.out.println();
        System.out.println("Enviamos otro mensaje a ana y observamos su historial");
        System.out.println("Resultado esperado: bulo1 y felicitacion. Ademas, como felicitacion tiene mayor alcance a bulo1, la exposicion de ana" +
                            "debe haber incrementado en un nivel");
        System.out.println("exposicion ana antes de enviar felicitacion: " + ana.getExposicion());
        Mensaje felicitacion = new Mensaje("felicitacion", 100, famoso);
        famoso.addEnlace(ana, 1);
        felicitacion.difunde(ana);
        System.out.println("exposicion ana tras enviar felicitacion: " + ana.getExposicion());
        System.out.println("Observamos el historial de famoso y de ana");
        System.out.println("ana: " + ana.getHistorial());
        System.out.println("famoso: " + famoso.getHistorial());
        
        System.out.println();
        System.out.println("Ahora vemos si al enviar a ana otro mensaje con menor alcance promedio, le decrementa la exposicion");
        System.out.println("exposicion ana actual: " + ana.getExposicion());
        Mensaje pesame = new Mensaje("pesame", 2, interesado);
        System.out.println("mensaje: " + pesame);
        pesame.difunde(ana);
        System.out.println("exposicion ana tras difusion: " + ana.getExposicion());



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
