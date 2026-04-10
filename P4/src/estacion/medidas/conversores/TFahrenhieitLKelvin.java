package estacion.medidas.conversores;

import estacion.medidas.*;

public class TFahrenhieitLKelvin implements ConversorInterface {
    private static final VariableMedida variableOrigen = MTemperatura.Fahrenheit;
    private static final VariableMedida variableDestino = MTemperatura.Kelvin;
    private static final ConversorInterface INSTANCIA = new TFahrenhieitLKelvin();

    private TFahrenhieitLKelvin(){}

    public ConversorInterface getConversor(){
        return TFahrenhieitLKelvin.INSTANCIA;
    }

    public VariableMedida getVariableOrigen(){
        return TFahrenhieitLKelvin.variableOrigen;
    }

    public VariableMedida getVariableDestino(){
        return TFahrenhieitLKelvin.variableDestino;
    }

    public double convertirUnidades(double valor){
        return -1;
    }
}