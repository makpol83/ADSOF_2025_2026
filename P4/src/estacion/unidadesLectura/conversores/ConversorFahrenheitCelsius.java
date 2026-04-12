package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

public class ConversorFahrenheitCelsius extends Conversor {
    private static final ConversorFahrenheitCelsius INSTANCIA = new ConversorFahrenheitCelsius();

    private ConversorFahrenheitCelsius(){
        super(MTemperatura.Fahrenheit, MTemperatura.Celsius);
    }

    public static ConversorFahrenheitCelsius getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return (valor - 32)*(5/9);
    }
}
