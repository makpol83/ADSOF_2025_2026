package src;

import src.app.Enlace;
import src.app.Mensaje;
import src.app.Usuario;

public class EjemploUsoMensajesBasicos {
    public static void main(String[] args){

        /**Test 1 - Inicio */
        /**Crea 3 usuarios, añade enlaces entre ellos con distintos constructores y difunde el mensaje entre los usuarios*/
        
        Usuario ana     = new Usuario("ana", 1);
        Usuario luis    = new Usuario("luis", 5);
        Usuario carmen  = new Usuario("carmen");
        Mensaje m = new Mensaje("Hi!", 50, ana);
        ana.addEnlace(new Enlace(ana, luis, 68));
        ana.addEnlace(carmen,33);
        System.out.println(m);
        m.difunde(luis, carmen);
        System.out.println(m);
        carmen.addEnlace(new Enlace(carmen, luis, 11));
        m.difunde(carmen.getEnlace(luis));
        System.out.println(m);

        // Comprobamos si se han sumado los costes de todos los enlaces en el atributo de clase
        // de enlace:

        if(carmen.getEnlace(luis).getCosteAcumulado() != (33 + 68 + 11))
            System.out.println("Test 1 - No coincide la suma de los costes de los enlaces con el valor del atributo en en.");
        
        /**Test 1 - Fin */

        /**Test 2 - Inicio */
        Usuario juan   = new Usuario("juan", 1);
        Usuario carlos = new Usuario("carlos", 5);

        Enlace enlace1 = new Enlace(juan, carlos);
        Enlace enlace2 = new Enlace(juan, carlos, 2);

        // Comprobamos si podemos añadir a juan los dos enlaces y sí mismo,
        // Sólo debe dejar el primero

        if(juan.addEnlace(enlace1) == false)
            System.out.println("Test 2 - Ha fallado la inserción de un enlace correcto a juan.");

        if(juan.addEnlace(enlace2) == true)
            System.out.println("Test 2 - Se ha insertado un enlace con el mismo destino en juan.");

        if(juan.addEnlace(enlace1) == true)
            System.out.println("Test 2 - Se ha insertado un enlace1 ya existente en juan.");

        Enlace enlace3 = new Enlace(juan, carlos, 15);
        Enlace enlace4 = new Enlace(juan, juan, 15);
        Usuario alberto    = new Usuario("alberto", 20);
        
        if(alberto.addEnlace(enlace3) == true)
            System.out.println("Test 2 - Se ha añadido enlace3 a alberto cuando el origen del enlace no es alberto.");
        
        if(juan.addEnlace(enlace4) == true)
            System.out.println("Test 2 - Se ha añadido un enlace4 que apunta de juan a juan.");

        // Comprobamos si el metodo cambiarDestino cumple su función, nótese que 
        // el enlace NO debe estar asignado en ningún usuario porque puede generar duplicados
        // de destino.

        Usuario daniel    = new Usuario("daniel", 5);
        Enlace enlace5 = new Enlace(juan, carlos, 5);

        enlace5.cambiarDestino(daniel, 10);

        if(enlace5.getUsuarioDestino() != daniel)
            System.out.println("Test 2 - No se ha modificado correctamente el nuevo destino de enlace 5.");

        if(enlace5.getCoste() != 10)
            System.out.println("Test 2 - No se ha modificado correctamente el nuevo coste de enlace 5.");

        

        // Comprobamos si el method toString de enlace coincide con el pedido:
        if(enlace5.toString().compareTo("(@juan--10-->@daniel)") != 0)
            System.out.println("Test 2 - No se imprime el toString de enlace 5 correctamente.");

        /**Test 2 - Fin */
        
        /**Test 3 - Inicio */
        Usuario aaron = new Usuario("aaron"); //amplificacion por defecto = 2
        Usuario maksym = new Usuario("Maksym", 8);
        Usuario daniel3 = new Usuario("daniel3", 0);
        Usuario alejandro = new Usuario("alejandro", 8);
        Usuario atrapaMensajes = new Usuario("bot", -999999);
        
        Enlace e1 = new Enlace(aaron, maksym, 1);
        Enlace e2 = new Enlace(daniel3, alejandro); //coste por defecto == 1

        aaron.addEnlace(e1);
        daniel3.addEnlace(e2);
        maksym.addEnlace(daniel3, 1);

        Mensaje mensaje1 = new Mensaje("Que tal?", 1, aaron);
        Mensaje mensaje2 = new Mensaje("AAAAAAAA", -1, aaron);
        Mensaje fakeNews = new Mensaje("BBBBBB", 100, aaron);
        Mensaje mensaje4 = new Mensaje("CCCCCC", 1, aaron);
        
        //se comprueba si el mensaje1 (con alcance 1) se puede difundir hasta maksym a traves de aaron
        //Resultado esperado: (True) Si se puede difundir.
        if(mensaje1.difunde(aaron.getEnlace(maksym)) == false)
            System.out.println("Test 3 - No se puede difundir el mensaje1 de aaron a maksym");


        //se comprueba el correcto valor del alcance de un mensjaje tras su difusion
        //Resultado esperado: alcance == 8
        if(mensaje1.getAlcance() != 8)
            System.out.println("Test 3 - El alcance del mensaje1 es distinto de 8");


        //se comprueba si mensaje2 (con alcance -1) se puede difundir hasta maksym a traves de aaron
        //Resultado esperado: (False) No se puede difundir
        if(mensaje2.difunde(aaron.getEnlace(maksym)) == true)
            System.out.println("Test 3 - Se ha difundido incorrectamente el mensaje2 de aaron a maksym");
            

        //se comprueba si el mensaje3 (con alcance 100) puede difundirse desde aaron hasta alejandro pasando por todos los usuarios
        //Resultado esperado: (True) Si se puede difundir
        if(fakeNews.difunde(maksym, daniel3, alejandro) == false)
            System.out.println("Test 3 - No se ha difundido el mensaje fakeNews de aaron a alejandro");
    

        daniel3.addEnlace(atrapaMensajes, 5);
        atrapaMensajes.addEnlace(alejandro, 5);

        //se comprueba que el mensaje3 no pueda seguir difundiendose tras llegar a un alcance menor a cualquier coste
        //Resultado esperado: (False) No se puede difundir
        if(fakeNews.difunde(maksym, daniel3, atrapaMensajes, alejandro) == true)
            System.out.println("Test 3 - Se ha difundido incorrectamente el mensaje fakeNews");

        

        /**Test 3 - Fin */
    }
}
