package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

public class ConversorFahrenheitKelvin extends Conversor {
    private static final ConversorFahrenheitKelvin INSTANCIA = new ConversorFahrenheitKelvin();

    private ConversorFahrenheitKelvin(){
        super(MTemperatura.Fahrenheit, MTemperatura.Kelvin);
    }

    public static ConversorFahrenheitKelvin getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return (valor-32)*5/9 + 273.15;
    }
}