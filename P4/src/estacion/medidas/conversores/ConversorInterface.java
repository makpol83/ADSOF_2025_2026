package estacion.medidas.conversores;

import estacion.medidas.VariableMedida;

public interface ConversorInterface {
    ConversorInterface getConversor();
    VariableMedida getVariableOrigen();
    VariableMedida getVariableDestino();
    double convertirUnidades(double valor);
}
