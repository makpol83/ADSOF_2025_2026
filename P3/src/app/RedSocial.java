package src.app;
import java.util.List;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class RedSocial {
    private List<Usuario> usuarios;
    private List<Enlace> enlaces;
    private List<Mensaje> mensajes;

    public RedSocial(String pathArchivoUsuarios, String pathArchivoEnlaces, 
        String... pathArchivoMensaje) throws IOException {
        try {
            this.usuarios = readUsuarios(pathArchivoUsuarios);
        } catch (IOException e) {
            throw new IOException();
        }

        try {
            this.enlaces = readEnlaces(pathArchivoEnlaces, this.usuarios);
        } catch (IOException e) {
            throw new IOException();
        }

        try {
            this.mensajes = readMensaje(this.usuarios, pathArchivoMensaje);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public boolean saveToLocal(String pathArchivoUsuarios, String pathArchivoEnlaces,
        String pathBaseMensaje) throws IOException{
        try{
            this.writeUsuarios(pathArchivoUsuarios);
            this.writeEnlace(pathArchivoEnlaces);
            this.writeMensajes(pathBaseMensaje);
        } catch (IOException e){
            throw new IOException();
        }

        return true;
    }

    private List<Usuario> readUsuarios(String pathArchivoUsuarios) throws IOException{
        File file = new File(pathArchivoUsuarios);
        List<Usuario> usuarios = new ArrayList<Usuario>();

        String nombreUsuario = null;
        int capacidadAmplificacion = 0;

        try(Scanner lector = new Scanner(file)){
            //El lector.hasNext es para ver si hay un token
            while(lector.hasNextLine() == true && lector.hasNext()) {

                nombreUsuario = lector.next();
                capacidadAmplificacion = Integer.parseInt(lector.next());

                Usuario usuarioNuevo = new Usuario(nombreUsuario, capacidadAmplificacion);

                if(usuarios.add(usuarioNuevo) == false)
                    throw new IOException();
            }
            lector.close();
        } catch (FileNotFoundException e) {
            throw new IOException();
        } catch (NoSuchElementException e) {
            throw new IOException();
        } catch (IllegalStateException e) {
            throw new IOException();
        } catch (NumberFormatException e) {
            throw new IOException();
        } 

        return usuarios;
    }

    private List<Enlace> readEnlaces(String pathArchivoEnlaces, List<Usuario> usuarios) throws IOException {
        File file = new File(pathArchivoEnlaces);
        List<Enlace> enlaces = new ArrayList<Enlace>();

        String origen = null;
        String destino = null;
        int coste = -1;

        try(Scanner lector = new Scanner(file)){
            while(lector.hasNextLine() == true && lector.hasNext()) {
                origen = lector.next();
                destino = lector.next();
                coste = Integer.parseInt(lector.next());

                Usuario usuarioOrigen = null;
                Usuario usuarioDestino = null;

                for(Usuario user : usuarios)
                    if(user.getNombre().equalsIgnoreCase(origen) == true)
                        usuarioOrigen = user;
                    else if(user.getNombre().equalsIgnoreCase(destino) == true)
                        usuarioDestino = user;

                if(usuarioOrigen == null || usuarioDestino == null)
                    throw new IOException();

                Enlace enlaceNuevo = new Enlace(usuarioOrigen, usuarioDestino, coste);
                if(usuarioOrigen.addEnlace(enlaceNuevo) == false)
                    throw new IOException();

                if(enlaces.add(enlaceNuevo) == false)
                    throw new IOException();
            }
            lector.close();
        } catch (FileNotFoundException e) {
            throw new IOException();
        } catch (NoSuchElementException e) {
            throw new IOException();
        } catch (IllegalStateException e) {
            throw new IOException();
        } catch (NumberFormatException e) {
            throw new IOException();
        } 

        return enlaces;
    }

    private List<Mensaje> readMensaje(List<Usuario> usuarios, String ... pathArchivoMensaje) throws IOException {
        List<Mensaje> msgList = new ArrayList<>();
        
        String str_mensaje = null;
        String autor = null;
        String destino_aux = null;
        int alcance=0;

        Mensaje mensaje = null;
        for(String path : pathArchivoMensaje){
            mensaje = null;
            File file = new File(path);
            //Ahora mismo no se leen espacios si los tiene el mensaje --> ARREGLAR
            try(Scanner lector = new Scanner(file)){
            if(lector.hasNextLine() == true && lector.hasNext()) {
                Pattern entreComillas = Pattern.compile("\"[^\"]*\""); // [^\"] = cualquier caracter menos comillas --> "....."
                str_mensaje = lector.next(entreComillas);
                str_mensaje = str_mensaje.substring(1, str_mensaje.length() - 1); //quita comilla inicial y final
                alcance = Integer.parseInt(lector.next());
                autor = lector.next();

                for(Usuario u : usuarios){
                    if(u.getNombre().equalsIgnoreCase(autor)){
                        mensaje = new Mensaje(str_mensaje, alcance, u);
                        msgList.add(mensaje);
                        break;
                    }
                }

                if(mensaje == null)
                        throw new IOException();
                
                //Se añade el lector.hasNext para ver si hay usuarios a los que mandar el mensaje
                while (lector.hasNextLine() == true && lector.hasNext()) {
                    destino_aux = lector.next();
                    for(Usuario u : usuarios){
                        if(u.getNombre().equalsIgnoreCase(destino_aux)){
                            if(mensaje.difunde(u) == true){
                                System.out.println(mensaje);
                                break;
                            }
                        }
                    }
                }
            }
            lector.close();
        } catch (FileNotFoundException e) {
            throw new IOException();
        } catch (NoSuchElementException e) {
            throw new IOException();
        } catch (IllegalStateException e) {
            throw new IOException();
        } catch (NumberFormatException e) {
            throw new IOException();
        } 
        }

        return msgList;
    }
    
    private Boolean writeUsuarios(String pathArchivoUsuario) throws IOException{
        try {
            FileWriter myWriter = new FileWriter(pathArchivoUsuario);
            for(Usuario user : this.usuarios){
                String output = new String(user.getNombre() + " " + user.getCapacidadAmplificacion() + "\n");
                myWriter.write(output);
            }
            myWriter.close();
        } catch (IOException e) {
            throw new IOException();
        }
        
        return true;
    }
    private Boolean writeEnlace(String pathArchivoEnlace) throws IOException{
        try {
            FileWriter myWriter = new FileWriter(pathArchivoEnlace);
            for(Enlace enlace : this.enlaces){
                String nombre_origen = enlace.getUsuarioOrigen().getNombre();
                String nombre_destino = enlace.getUsuarioDestino().getNombre();
                String output = new String(nombre_origen + " " + nombre_destino + " " + enlace.getCoste() + "\n");
                myWriter.write(output);
            }
            myWriter.close();
        } catch (IOException e) {
            throw new IOException();
        }
        
        return true;
    }
    private Boolean writeMensajes(String pathBaseArchivoMensaje) throws IOException{
        int indice_mensaje = 1;
        for(Mensaje mensaje : this.mensajes){
            try {
            FileWriter myWriter = new FileWriter(pathBaseArchivoMensaje + indice_mensaje + ".txt");
            myWriter.write("\"" + mensaje.getMensaje() + "\" " + mensaje.getAlcance() + " " + mensaje.getAutor().getNombre() + "\n");
            myWriter.close();
        } catch (IOException e) {
            throw new IOException();
        }

            indice_mensaje++;
        }

        return true;
    }

    public Boolean addUsuario(Usuario user){
        boolean alreadyExists = false;
        for(Usuario user_app : this.usuarios){
            if(user_app.getNombre().equalsIgnoreCase(user.getNombre()) == true){
                alreadyExists = true;
            }
        }

        if (alreadyExists == true)
            return false;

        return this.usuarios.add(user);
    }

    public Boolean addEnlace(Enlace link){
        Usuario usuarioOrigen = null;
        Usuario userOrigen = link.getUsuarioOrigen();
        for(Usuario user : this.usuarios){
            if(user.getNombre().equalsIgnoreCase(userOrigen.getNombre()) == true){
                usuarioOrigen = userOrigen;
                break;
            }
        }

        if (usuarioOrigen == null)
            return false;

        String nombre_destino = link.getUsuarioDestino().getNombre();
        int num_enlaces = usuarioOrigen.getNumEnlaces();

        for(int i = 0; i < num_enlaces; i++){
            Enlace enlace = usuarioOrigen.getEnlace(i);
            if(enlace.getUsuarioDestino().getNombre().equalsIgnoreCase(nombre_destino) == true)
                return false;
        }

        if(usuarioOrigen.addEnlace(link) == false)
            return false;

        return this.enlaces.add(link);
    }

    public Boolean addMensaje(Mensaje msg){
        if(this.usuarios.contains(msg.getAutor()) == false ||
            this.usuarios.contains(msg.getUsuarioActual()) == false){
            return false;
        }       
        return this.mensajes.add(msg);
    }

    public Boolean difundirMensaje(Mensaje msg, Usuario ... usuarios){
        Boolean difundido = true;

        if(this.mensajes.contains(msg) == false)
            this.addMensaje(msg);

        for(Usuario u : usuarios){
            if(this.usuarios.contains(u) == false)
                difundido = false;
            else if(msg.difunde(u) == false)
                difundido = false;
        }

        return difundido;
    }

    public List<Usuario> getUsuarios(){
        return this.usuarios;
    }
    public List<Mensaje> getMensajes(){
        return this.mensajes;
    }
    public List<Enlace> getEnlaces(){ 
        return this.enlaces;
    }
}

