package estacion.unidadeslectura.conversores;

import estacion.unidadeslectura.UnidadLectura;

public abstract class Conversor {
    private final UnidadLectura variableOrigen;
    private final UnidadLectura variableDestino;

    //si lo pongo privado, no puedo llamar a super() en las clases hijas para hacer el singleton, asi evito repetir codigo
    protected Conversor(UnidadLectura variableOrigen, UnidadLectura variableDestino){
        this.variableOrigen = variableOrigen;
        this.variableDestino = variableDestino;
    }

    public UnidadLectura getUnidadOrigen(){ return this.variableOrigen; }
    public UnidadLectura getUnidadDestino(){ return this.variableDestino; }
    public abstract double convertirUnidades(double valor);
}
