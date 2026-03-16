import java.io.IOException;

public class EjemploDeUsoRedSocial {
    public static void main(String[] args){
        RedSocial s;
        try {
            s = new RedSocial(
                    "txt/USUARIOS.txt", "txt/ENLACES.txt", "txt/MENSAJE.txt");
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

        System.out.println("Añadimos un usuario a la red social: ");
        Usuario user = new Usuario("Alberto", 10);
        s.addUsuario(user);
        for(Usuario i : s.getUsuarios())
            System.out.println(i);

        System.out.println("Añadimos un enlace a la red social con el nuevo usuario a Ana (Posición 0): ");
        Enlace link = new Enlace(user, s.getUsuarios().get(0), 27);
        s.addEnlace(link);
        for(Enlace i : s.getEnlaces())
            System.out.println(i);

        System.out.println("Añadimos un mensaje a la red social para el nuevo usuario: ");
        Mensaje message = new Mensaje("Hola, soy el nuevo mensaje.", 100, user);
        s.addMensaje(message);
        for(Mensaje i : s.getMensajes())
            System.out.println(i);

        System.out.println("Difundimos el mensaje a ver si se difunde por el enlace nuevo a Ana: ");
        s.difundirMensaje(message, s.getUsuarios().get(0));
        for(Mensaje i : s.getMensajes())
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
