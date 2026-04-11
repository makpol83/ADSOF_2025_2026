package estacion.unidadeslectura.conversores;

import estacion.unidadeslectura.MTemperatura;

public class ConversorKelvinCelsius extends Conversor {
    private static final ConversorKelvinCelsius INSTANCIA = new ConversorKelvinCelsius();

    private ConversorKelvinCelsius(){
        super(MTemperatura.Kelvin, MTemperatura.Celsius);
    }

    public static ConversorKelvinCelsius getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return valor - 273.15;
    }
}
