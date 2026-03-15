package src;

import src.app.Enlace;
import src.app.Mensaje;
import src.app.RedSocial;
import src.app.Usuario;

import java.io.IOException;

public class EjemploDeUsoFachadaRedSocial {
    public static void main(String[] args){
        RedSocial s;
        try {
            s = new RedSocial("txt/USUARIOS.txt", "txt/ENLACES.txt",
                    "txt/MENSAJE.txt");
            //s = new RedSocial(".txt/USUARIOS.txt", ".txt/ENLACES.txt", ".txt/MENSAJE2.txt");
        } catch (IOException e) {
            System.out.println("Error en archivos");
            return;
        }

        System.out.println("Mostramos los usuarios de la red social: ");
        for(Usuario i : s.getUsuarios())
            System.out.println(i);

        System.out.println("Mostramos los enlaces de la red social: ");
        for(Enlace i : s.getEnlaces())
            System.out.println(i);

        System.out.println("Mostramos los mensajes de la red social: ");
        for(Mensaje i : s.getMensajes())
            System.out.println(i);

        System.out.println("El guardado en ficheros se comprueba al final.");
        System.out.println();

        System.out.println("Comprobamos todos los métodos de fachada de RedSocial:");
        System.out.println();
        System.out.println("Intentamos añadir un nuevo usuario mediante addUsuario:");
        System.out.println("Si existe un usuario con el nombre Alberto si se ha añadido, se imprime(es correcto):");
        Usuario user = new Usuario("Alberto", 15);
        s.addUsuario(user);
        for(Usuario i : s.getUsuarios())
            if(i.getNombre().equalsIgnoreCase(user.getNombre()))
                System.out.println(i);

        System.out.println("Intentamos añadir un nuevo enlace mediante addEnlace:");
        System.out.println("Creamos un enlace entre el nuevo usuario y el primer usuario de la RedSocial:");
        System.out.println("Si existe el enlace, se imprime(es correcto):");
        Enlace link = new Enlace(user, s.getUsuarios().getFirst());
        s.addEnlace(link);
        for(Enlace i : s.getEnlaces())
            if(i.getUsuarioOrigen().equals(user) && i.getUsuarioDestino().equals(s.getUsuarios().getFirst()))
                System.out.println(i);

        System.out.println("Intentamos añadir un nuevo mensaje mediante addMensaje:");
        System.out.println("Lo creamos en el nuevo usuario y vemos si está en la RedSocial:");
        System.out.println("Si existe el enlace, se imprime(es correcto):");
        Mensaje message = new Mensaje("Este mensaje es de la fachada", 100, user);
        s.addMensaje(message);
        for(Mensaje i : s.getMensajes())
            if(i.getAutor().equals(user))
                System.out.println(i);

        System.out.println("Intentamos difundir el nuevo mensaje a través del nuevo enlace:");
        System.out.println("Vemos si el nuevo autor es el destino del enlace(ana):");
        System.out.println("El alcance del mensaje debe ser 102 (100 -1(coste enlace) " +
                "+ 3(capacidad amplificación de Ana)):");
        s.difundirMensaje(message, s.getUsuarios().getFirst());
        for(Mensaje i : s.getMensajes())
            if(i.getMensaje().equalsIgnoreCase(message.getMensaje()))
                System.out.println(i);

        System.out.println("Vemos si el guardado en ficheros funciona: ");
        try {
            s.saveToLocal("txt/usuarios_guardados.txt", "txt/enlaces_guardados.txt", "txt/mensaje_guardado");
        } catch (IOException e){
            System.out.println("Error al guardar en ficheros.");
            return;
        }
        System.out.println("Se han guardado los ficheros correctamente.");

        System.out.println("Ahora volvemos a cargarlo de esos ficheros para ver si volvemos a tener la misma red social: ");
        RedSocial d;
        try {
            d = new RedSocial("txt/usuarios_guardados.txt", "txt/enlaces_guardados.txt", "txt/mensaje_guardado1.txt", "txt/mensaje_guardado2.txt");
        } catch (IOException e){
            System.out.println("Se ha producido un error al cargar los fichero nuevos guardados.");
            return;
        }
        System.out.println("Vemos si son iguales las dos redes sociales: ");
        System.out.println();
        System.out.println("Usuarios: ");
        System.out.println("Red social original:");
        for(Usuario i : s.getUsuarios())
            System.out.println(i);
        System.out.println("Red social cargada: ");
        for(Usuario i : d.getUsuarios())
            System.out.println(i);

        System.out.println();
        System.out.println("Enlaces: ");
        System.out.println("Red social original: ");
        for(Enlace i : s.getEnlaces())
            System.out.println(i);
        System.out.println("Red social cargada: ");
        for(Enlace i : d.getEnlaces())
            System.out.println(i);

        System.out.println();
        System.out.println("Mensajes: ");
        System.out.println("Red social original: ");
        for(Mensaje i : s.getMensajes())
            System.out.println(i);
        System.out.println("Red social cargada: ");
        for(Mensaje i : d.getMensajes())
            System.out.println(i);

        return;
    }
}
