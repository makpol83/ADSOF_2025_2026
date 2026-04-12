package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

public class ConversorKelvinFahrenheit extends Conversor {
    private static final ConversorKelvinFahrenheit INSTANCIA = new ConversorKelvinFahrenheit();

    private ConversorKelvinFahrenheit(){
        super(MTemperatura.Kelvin, MTemperatura.Fahrenheit);
    }

    public static ConversorKelvinFahrenheit getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return (valor-273.15)*1.8 + 32;
    }
}