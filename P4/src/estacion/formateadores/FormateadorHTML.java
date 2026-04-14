package estacion.formateadores;

import java.util.List;

/**
 * Clase que formatea un IDocumento a formato HTML
 */
public class FormateadorHTML implements Formateador{

    /**
     * Constructor por defecto. Retorna y devuelvo un objeto de tipo FormateadorHTML necesario para pasar a formato HTML
     * un IDocumento
     */
    public FormateadorHTML(){}

    public String formatear(IDocumento informacion){
        // Extraemos los datos de informacion
        String tituloDocumento = informacion.getTituloDocumento();
        String tituloPrincipal = informacion.getTituloSeccionPrincipal();
        List<String> parrafosSeccionPrincipal = informacion.getParrafosSeccionPrincipal();
        List<SeccionSecundaria> seccionesSecundarias = informacion.getSeccionesSecundarias();

        // Formateamos el inicio del documento
        String inicioHTML = "<!DOCTYPE html>\n<html lang=\"es\">";
        String tituloFinal = "<head>\n    <title>" + tituloDocumento + "</title>\n</head>\n";
        String tituloPrincipalFinal = "<body>\n    <h1>" + tituloPrincipal + "</h1>\n";
        String parrafosSeccionPrincipalFinal = "";
        String seccionesSecundariasFinal = "";

        // Añadimos los párrafos de la sección principal
        for(String s : parrafosSeccionPrincipal){
            parrafosSeccionPrincipalFinal +="    <p>" + s + "</p>\n";
        }

        // Por cada sección secundaria formateamos los datos
        for(SeccionSecundaria seccion : seccionesSecundarias){
            //Guardamos cada sección secundaria
            seccionesSecundariasFinal += "    <p>" + seccion.getTitulo() + "</p>\n    <ul>\n";
            for(String s : seccion.getElementos()){
                seccionesSecundariasFinal += "        <li>" + s + "</li>\n";
            }
            seccionesSecundariasFinal += "    </ul>\n";
        }

        //Añadimos el final de HTML
        String finalHTML = "</body>\n</html>\n";

        return inicioHTML + tituloFinal + tituloPrincipalFinal + parrafosSeccionPrincipalFinal + seccionesSecundariasFinal + finalHTML;
    }
}
