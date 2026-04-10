package estacion.unidadeslectura.conversores;

import java.util.ArrayList;
import java.util.List;

import estacion.unidadeslectura.UnidadLectura;

public class ProcesadorDatos {
    private final UnidadLectura variableMedida;
    private final List<ConversorInterface> conversores;

    public ProcesadorDatos(UnidadLectura variableMedida){
        this.variableMedida = variableMedida;
        this.conversores = new ArrayList<>();
    }

    public boolean addConversor(ConversorInterface conversorUnidades){
        //Comprobar que miden misma variable
        return false;
    }
}
