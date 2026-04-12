package estacion.formateadores;

import java.util.ArrayList;
import java.util.List;

public class FormateadorHTML implements Formateador{
    public String formatear(IDocumento informacion){
        String tituloDocumento = informacion.getTituloDocumento();
        String tituloPrincipal = informacion.getTituloSeccionPrincipal();
        List<String> parrafosSeccionPrincipal = informacion.getParrafosSeccionPrincipal();
        List<SeccionSecundaria> seccionesSecundarias = informacion.getSeccionesSecundarias();

        String inicioHTML = "<!DOCTYPE html>\n<html lang=\"es\">";
        String tituloFinal = "<head>\n    <title>" + tituloDocumento + "</title>\n</head>\n";
        String tituloPrincipalFinal = "<body>\n    <h1>" + tituloPrincipal + "</h1>\n";
        String parrafosSeccionPrincipalFinal = "";
        String seccionesSecundariasFinal = "";

        for(String s : parrafosSeccionPrincipal){
            parrafosSeccionPrincipalFinal +="    <p>" + s + "</p>\n";
        }

        
        int i = 0;
        for(SeccionSecundaria seccion : seccionesSecundarias){
            //Guardamos cada sección secundaria
            seccionesSecundariasFinal += "    <p>" + seccion.getTitulo() + "</p>\n    <ul>\n";
            for(String s : seccion.getElementos()){
                seccionesSecundariasFinal += "        <li>" + s + "</li>\n";
            }
            seccionesSecundariasFinal += "    </ul>\n";
        }

        String finalHTML = "</body>\n</html>\n";

        return inicioHTML + tituloFinal + tituloPrincipalFinal + parrafosSeccionPrincipalFinal + seccionesSecundariasFinal + finalHTML;
    }
}
