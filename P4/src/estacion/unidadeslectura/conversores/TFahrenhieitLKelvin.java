package estacion.unidadeslectura.conversores;

import estacion.unidadeslectura.*;

public class TFahrenhieitLKelvin implements ConversorInterface {
    private static final UnidadLectura variableOrigen = MTemperatura.Fahrenheit;
    private static final UnidadLectura variableDestino = MTemperatura.Kelvin;
    private static final ConversorInterface INSTANCIA = new TFahrenhieitLKelvin();

    private TFahrenhieitLKelvin(){}

    public ConversorInterface getConversor(){
        return TFahrenhieitLKelvin.INSTANCIA;
    }

    public UnidadLectura getVariableOrigen(){
        return TFahrenhieitLKelvin.variableOrigen;
    }

    public UnidadLectura getVariableDestino(){
        return TFahrenhieitLKelvin.variableDestino;
    }

    public double convertirUnidades(double valor){
        return -1;
    }
}