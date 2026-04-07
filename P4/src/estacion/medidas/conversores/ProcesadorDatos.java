package estacion.medidas.conversores;

import java.util.ArrayList;
import java.util.List;

import estacion.medidas.VariableMedida;

public class ProcesadorDatos {
    private final VariableMedida variableMedida;
    private final List<ConversorInterface> conversores;

    public ProcesadorDatos(VariableMedida variableMedida){
        this.variableMedida = variableMedida;
        this.conversores = new ArrayList<>();
    }

    public boolean addConversor(ConversorInterface conversorUnidades){
        //Comprobar que miden misma variable
        return false;
    }
}
