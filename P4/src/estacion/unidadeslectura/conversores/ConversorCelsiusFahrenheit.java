package estacion.unidadeslectura.conversores;

import estacion.unidadeslectura.MTemperatura;

public class ConversorCelsiusFahrenheit extends Conversor {
    private static final ConversorCelsiusFahrenheit INSTANCIA = new ConversorCelsiusFahrenheit();

    private ConversorCelsiusFahrenheit(){
        super(MTemperatura.Celsius, MTemperatura.Fahrenheit);
    }

    public static ConversorCelsiusFahrenheit getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return valor*1.8 + 32;
    }
}
