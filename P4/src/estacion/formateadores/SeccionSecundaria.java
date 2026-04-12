package estacion.formateadores;

import java.util.ArrayList;
import java.util.List;

public class SeccionSecundaria {
    private String titulo;
    private List<String> elementos;

    public SeccionSecundaria(String titulo, List<String> elementos){
        this.titulo = titulo;
        this.elementos = new ArrayList<>();
        this.elementos.addAll(elementos);
    }

    public String getTitulo(){ return this.titulo; }

    public List<String> getElementos(){ return List.copyOf(this.elementos); }
}
