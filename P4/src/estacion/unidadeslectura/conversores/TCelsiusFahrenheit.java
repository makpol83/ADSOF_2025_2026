package estacion.unidadeslectura.conversores;

import estacion.unidadeslectura.MTemperatura;
import estacion.unidadeslectura.UnidadLectura;

public class TCelsiusFahrenheit extends Conversor {
    private static final UnidadLectura variableOrigen = MTemperatura.Celsius;
    private static final UnidadLectura variableDestino = MTemperatura.Fahrenheit;
    private static final ConversorInterface INSTANCIA = new TCelsiusFahrenheit();

    private TCelsiusFahrenheit(){}

    public ConversorInterface getConversor(){
        return TCelsiusFahrenheit.INSTANCIA;
    }

    public UnidadLectura getVariableOrigen(){
        return TCelsiusFahrenheit.variableOrigen;
    }

    public UnidadLectura getVariableDestino(){
        return TCelsiusFahrenheit.variableDestino;
    }

    public double convertirUnidades(double valor){
        return -1;
    }
}
