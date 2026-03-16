import java.util.List;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Clase RedSocial, almacena un conjunto de usuarios, sus enlaces y los mensajes dentro de la red social.
 * Se crea mediante paths a los archivos que contienen los datos a leer. Nótese que los path deben empezar
 * desde la carpeta del proyecto. Por ejemplo: "txt/USUARIOS.txt".
 *
 * Implementa métodos para añadir usuarios, enlaces y mensajes, a parte de difundirlos.
 * Implementa métodos para guardar los datos en ficheros.
 */
public class RedSocial {
    /** Lista de usuarios */
    private List<Usuario> usuarios;
    /** Lista de enlaces de los usuarios */
    private List<Enlace> enlaces;
    /** Lista de mensajes de la aplicación */
    private List<Mensaje> mensajes;

    /**
     * Constructor RedSocial, recibe los paths a los archivos de los que se va a leer para cargar la RedSocial.
     * Los path deben empezar desde la carpeta anterior a src. Por ejemplo : "txt/USUARIOS.txt".
     * @param pathArchivoUsuarios Path al fichero con los usuarios en formato: "nombre capacidadAmplificacion"
     * @param pathArchivoEnlaces Path al fichero con los enlaces en formato: "nombreOrigen nombreDestino coste"
     * @param pathArchivoMensaje Paths a los ficheros con los distintos mensajes de la app, se deben introducir
     *       separados por comas, por ejemplo: {"txt/mensaje_guardado1.txt", "txt/mensaje_guardado2.txt"}.
     *       El formato de los ficheros debe ser:
     *                           \"Mensaje\" alcanceInicial nombreAutor
     *                           destino1
     *                           destino2
     *                           ...
     *                           destinoN
     *       Destino1, destino2, destino... Son los nombres de los usuarios a los que secuencialmente se les
     *       irá mandando el mensaje si es posible.
     * @throws IOException Si ha fallado la lectura o el formato es inválido.
     */
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

    /**
     * Guarda en ficheros con los paths indicados toda la información de la RedSocial.
     * Se sobreescriben los ficheros si ya existen al guardarse.
     * @param pathArchivoUsuarios path al fichero con los usuarios
     * @param pathArchivoEnlaces path al fichero con los enlaces de los usuarios
     * @param pathBaseMensaje path base con los mensajes:
     *           Por ejemplo, si la RedSocial tiene 3 mensajes y
     *           el pathBaseMensaje = "txt/mensaje_guardado",
     *           los ficheros creados serán:
     *              "txt/mensaje_guardado1.txt"
     *              "txt/mensaje_guardado2.txt"
     *              "txt/mensaje_guardado3.txt"
     * @return true si se ha guardado correctamente, false si ha fallado algo
     * @throws IOException Si se ha producido un error al escribir o usar algún fichero.
     */
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

    /**
     * Método privado para leer los usuarios.
     * @param pathArchivoUsuarios Path a los usuarios
     * @return Lista de Usuarios Con los usuarios de la RedSocial
     * @throws IOException Si ha fallado la lectura
     */
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

    /**
     * Método privado para leer los enlaces.
     * @param pathArchivoEnlaces Path a los enlaces
     * @param usuarios Lista con los usuarios de la RedSocial
     * @return Lista de Enlace Con los enlaces de la RedSocial
     * @throws IOException Si ha fallado la lectura
     */
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

    /**
     * Método privado para leer los mensajes. Los manda a través de los usuarios si tiene algún usuarioDestino.
     * @param pathArchivoMensaje Paths separados por comas, uno por cada mensaje
     * @param usuarios Lista con los usuarios de la RedSocial
     * @return List de Mensaje Con los mensajes de la RedSocial
     * @throws IOException Si ha fallado la lectura
     */
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
            if(lector.hasNextLine() == true) {
                lector.useDelimiter("\"");
                str_mensaje = lector.next();
                lector.reset();
                lector.next();

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

    /**
     * Escribe los usuarios en el path recibido.
     * @param pathArchivoUsuario Path en el que guardar los usuarios
     * @return true si se ha guardado correctamente, false si ha fallado
     * @throws IOException Si se ha producido un error al escribir
     */
    private boolean writeUsuarios(String pathArchivoUsuario) throws IOException{
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

    /**
     * Escribe los enlaces en el path recibido.
     * @param pathArchivoEnlace Path en el que guardar los enlaces
     * @return true si se ha guardado correctamente, false si ha fallado
     * @throws IOException Si se ha producido un error al escribir
     */
    private boolean writeEnlace(String pathArchivoEnlace) throws IOException{
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

    /**
     * Escribe los usuarios en el path base recibido + número de mensaje + ".txt"
     * @param pathBaseArchivoMensaje Path base para guardar los mensajes
     * @return true si se ha guardado correctamente, false si ha fallado
     * @throws IOException Si se ha producido un error al escribir
     */
    private boolean writeMensajes(String pathBaseArchivoMensaje) throws IOException{
        int indice_mensaje = 1;
        for(Mensaje mensaje : this.mensajes){
            try {
            FileWriter myWriter = new FileWriter(pathBaseArchivoMensaje + indice_mensaje + ".txt");
            myWriter.write("\"" + mensaje.getMensaje() + "\" " + mensaje.getAlcance() + " " + mensaje.getUsuarioActual().getNombre() + "\n");
            myWriter.close();
        } catch (IOException e) {
            throw new IOException();
        }

            indice_mensaje++;
        }

        return true;
    }

    /**
     * Método para añadir un Usuario a la RedSocial, no debería tener enlaces
     * asignados a usuarios que no estén en la RedSocial
     * @param user Usuario a añadir
     * @return true si se ha añadido, false si ya existía un usuario con ese nombre,
     * o no se puede añadir
     */
    public boolean addUsuario(Usuario user){
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

    /**
     * Añade un enlace a la RedSocial, los usuarios deberían estar almacenados en la RedSocial,
     * se comprueba si existe el usuario origen en la RedSocial y si ya existe un enlace al
     * usuario destino en el usuario origen.
     * @param link Enlace a añadir
     * @return true si se ha añadido o false si ya existía un enlace al usuarioDestino o
     * no existe el usuario origen en la RedSocial
     */
    public boolean addEnlace(Enlace link){
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

    /**
     * Añade un mensaje a la RedSocial, el autor y el usuario actual deben estar incluidos.
     * @param msg Mensaje a incluir
     * @return true si se ha añadido, false si el autor no existe o el usuarioActual no existe
     */
    public boolean addMensaje(Mensaje msg){
        if(this.usuarios.contains(msg.getAutor()) == false ||
            this.usuarios.contains(msg.getUsuarioActual()) == false){
            return false;
        }       
        return this.mensajes.add(msg);
    }

    /**
     * Difunde un mensaje por los usuarios a lo largo de la RedSocial, nótese que el mensaje debe estar
     * incluido en la RedSocial, al igual que los usuarios.
     * @param msg Mensaje a difundir
     * @param usuarios Usuarios a los que secuencialmente se les va a mandar el mensaje
     * @return true si se ha difundido al menos una vez, false si uno de los usuarios no está contenido en la
     * RedSocial o si la difusión ha fallado.
     */
    public boolean difundirMensaje(Mensaje msg, Usuario ... usuarios){
        boolean difundido = true;

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

    /**
     * Getter de los usuarios
     * @return Lista de Usuarios de la RedSocial
     */
    public List<Usuario> getUsuarios(){
        return this.usuarios;
    }

    /**
     * Getter de los Mensajes
     * @return Lista de Mensajes de la RedSocial
     */
    public List<Mensaje> getMensajes(){
        return this.mensajes;
    }

    /**
     * Getter de los Enlaces
     * @return Lista de Enlaces de la RedSocial
     */
    public List<Enlace> getEnlaces(){ 
        return this.enlaces;
    }
}

