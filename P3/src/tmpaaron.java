public class tmpaaron {

    public static void main(String[] args) {
        //UsuarioInteresado & Exposición & historial

        Usuario ana = new Usuario("ana", Exposicion.BAJA);
        UsuarioInteresado interesado = new UsuarioInteresado("interesado", Exposicion.MEDIA);
        Usuario famoso = new Usuario("famoso", Exposicion.VIRAL);
        interesado.addEnlace(ana, 1);
        interesado.addEnlace(famoso, 1);
        Mensaje bulo1 = new Mensaje("bulo1", 50, interesado);

        System.out.println();
        System.out.println("INICIO Test UsuarioInteresardo & Exposicion & Historial");
        System.out.println("Datos iniciales: ");
        System.out.println(ana);
        System.out.println("exposicion ana: " + ana.getExposicion());
        System.out.println(interesado);
        System.out.println("exposicion interesado: " + interesado.getExposicion());
        System.out.println(famoso);
        System.out.println("exposicion famoso: " + famoso.getExposicion());
        System.out.println();
        System.out.println("Comprobamos si UsuarioInteresado difunde a Usuario con Exposicion >= Alta antes que al destino especificado:");
        System.out.println("Difundimos bulo1 a ana. Resultado esperado: bulo1 en famoso");
        System.out.println(bulo1);
        System.out.println(interesado);
        bulo1.difunde(ana);
        System.out.println(bulo1);

        System.out.println();
        System.out.println("Comprobamos si UsuarioInteresado difunde al usuario especificado en caso de no tener un contacto con exposicion alta");
        Mensaje bulo2 = new Mensaje("bulo2", 50, interesado);
        famoso.cambiarExposicion(Exposicion.BAJA);
        System.out.println("ahora, exposicion de famoso: " + famoso.getExposicion());
        System.out.println(bulo2);
        System.out.println("difundimos bulo 2 de interesado a ana. Resultado esperado: mensaje termina en ana");
        bulo2.difunde(ana);
        System.out.println(bulo2);

        System.out.println();
        System.out.println("Enviamos otro mensaje a ana y observamos su historial");
        System.out.println("Resultado esperado: bulo1 y felicitacion. Ademas, como felicitacion tiene mayor alcance a bulo1, la exposicion de ana" +
                "debe haber incrementado en un nivel");
        System.out.println("exposicion ana antes de enviar felicitacion: " + ana.getExposicion());
        Mensaje felicitacion = new Mensaje("felicitacion", 100, famoso);
        System.out.println(felicitacion);
        famoso.addEnlace(ana, 1);
        felicitacion.difunde(ana);
        System.out.println("exposicion ana tras enviar felicitacion: " + ana.getExposicion());
        System.out.println(felicitacion);
        System.out.println("Observamos el historial de famoso y de ana");
        System.out.println("ana: " + ana.getHistorial());
        System.out.println("famoso: " + famoso.getHistorial());

        System.out.println();
        System.out.println("Ahora vemos si al enviar a ana otro mensaje con menor alcance promedio, le decrementa la exposicion");
        System.out.println("exposicion ana actual: " + ana.getExposicion());
        Mensaje pesame1 = new Mensaje("pesame1", 2, interesado);
        System.out.println("mensaje: " + pesame1);
        pesame1.difunde(ana);
        System.out.println("exposicion ana tras difusion: " + ana.getExposicion());
        System.out.println("Repetimos:");
        Mensaje pesame2 = new Mensaje("pesame2", 2, interesado);
        System.out.println(pesame2);
        pesame2.difunde(ana);
        System.out.println("difusion");
        System.out.println(pesame2);
        System.out.println("exposicion ana: " + ana.getExposicion());
        System.out.println("Repetimos. Ahora la exposicion no deberia bajar mas de oculta");
        Mensaje pesame3 = new Mensaje("pesame3", 2, interesado);
        System.out.println(pesame3);
        pesame3.difunde(ana);
        System.out.println("difusion");
        System.out.println(pesame3);
        System.out.println("exposicion ana: " + ana.getExposicion());
        System.out.println(ana.getHistorial());
        System.out.println();

        System.out.println("Finalmente veremos si al llegar un mensaje cuyo alcance es igual a la media del" +
                "historial, la exposicion no se ve modificada.");
        System.out.println("Del historial anterior vemos que la media del alcance de los mensajes de ana es:" +
                "51 + 101 + 3 + 3 + 3 = 161 | 161 / 5 = 32");
        System.out.println("creamos un mensaje con alcance 32 y lo mandamos a ana." +
                "Resultado esperado: su exposicion no cambia");
        System.out.println("exposicion ana actual:" + ana.getExposicion());
        Mensaje noCambiaExp = new Mensaje("noCambiaExp", 32, famoso);
        System.out.println(noCambiaExp);
        System.out.println("difundimos a ana");
        noCambiaExp.difunde(ana);
        System.out.println(noCambiaExp);
        System.out.println("exposicion de ana: " + ana.getExposicion());
        System.out.println("historial ana: " + ana.getHistorial());
        System.out.println();

        System.out.println("FIN test UsuarioInteresardo & Exposicion & Historial");

        //FIN Test UsuarioInteresado & Exposicion & Historial
    }
}