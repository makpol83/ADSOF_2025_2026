import java.io.IOException;

public class EjemploDeUsoRedSocial {
    public static void main(String[] args){
        try {
            RedSocial s;
            s = new RedSocial("txt/USUARIOS.txt", "txt/ENLACES.txt", "txt/MENSAJE.txt");
            s = new RedSocial(".txt/USUARIOS.txt", ".txt/ENLACES.txt", ".txt/MENSAJE2.txt");
        } catch (IOException e) {
            System.out.println("Error en archivos");
        }

        SEGUIRRRRRRRRRRRRRRRR
    }
}
