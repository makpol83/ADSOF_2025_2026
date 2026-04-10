package estacion.medidas.conversores;

import estacion.medidas.MTemperatura;
import estacion.medidas.VariableMedida;

public class TCelsiusFahrenheit extends Conversor {
    private static final VariableMedida variableOrigen = MTemperatura.Celsius;
    private static final VariableMedida variableDestino = MTemperatura.Fahrenheit;
    private static final ConversorInterface INSTANCIA = new TCelsiusFahrenheit();

    private TCelsiusFahrenheit(){}

    public ConversorInterface getConversor(){
        return TCelsiusFahrenheit.INSTANCIA;
    }

    public VariableMedida getVariableOrigen(){
        return TCelsiusFahrenheit.variableOrigen;
    }

    public VariableMedida getVariableDestino(){
        return TCelsiusFahrenheit.variableDestino;
    }

    public double convertirUnidades(double valor){
        return -1;
    }
}
