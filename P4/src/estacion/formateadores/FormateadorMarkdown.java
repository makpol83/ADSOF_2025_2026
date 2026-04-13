package estacion.formateadores;

import java.util.List;

public class FormateadorMarkdown implements Formateador{
    public String formatear(IDocumento informacion){
        String tituloDocumento = informacion.getTituloDocumento();
        String tituloPrincipal = informacion.getTituloSeccionPrincipal();
        List<String> parrafosSeccionPrincipal = informacion.getParrafosSeccionPrincipal();
        List<SeccionSecundaria> seccionesSecundarias = informacion.getSeccionesSecundarias();

        String tituloFinal = "# " + tituloDocumento + "\n\n";
        String tituloPrincipalFinal = "## " + tituloPrincipal + "\n\n";
        String parrafosSeccionPrincipalFinal = "";
        String seccionesSecundariasFinal = "";

        for(String s : parrafosSeccionPrincipal){
            parrafosSeccionPrincipalFinal += s + "\n\n";
        }

        
        int i = 0;
        for(SeccionSecundaria seccion : seccionesSecundarias){
            //Guardamos cada sección secundaria
            seccionesSecundariasFinal += "### " + seccion.getTitulo() + "\n";
            for(String s : seccion.getElementos()){
                seccionesSecundariasFinal += "- " + s + "\n";
            }

            //Separamos las secciones excepto la última
            if(i < seccionesSecundarias.size() - 1)
                seccionesSecundariasFinal += "\n";

            i++;
        }

        return tituloFinal + tituloPrincipalFinal + parrafosSeccionPrincipalFinal + seccionesSecundariasFinal;
    }
}
