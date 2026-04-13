package estacion.formateadores;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que almacena los datos asociados a una Seccion secundaria
 */
public class SeccionSecundaria {
    /** Titulo de la seccion */
    private String titulo;
    /** Lista de líneas asociadas a la sección */
    private List<String> elementos;

    /**
     * Constructor seccion secundaria
     * @param titulo de la sección
     * @param elementos líneas de la sección
     */
    public SeccionSecundaria(String titulo, List<String> elementos){
        this.titulo = titulo;
        this.elementos = new ArrayList<>();
        this.elementos.addAll(elementos);
    }

    /**
     * Getter del titulo
     * @return String
     */
    public String getTitulo(){ return this.titulo; }

    /**
     * Getter de las líneas
     * @return List String, inmutable
     */
    public List<String> getElementos(){ return List.copyOf(this.elementos); }
}
