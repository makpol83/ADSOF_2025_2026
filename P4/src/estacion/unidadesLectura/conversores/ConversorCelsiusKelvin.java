package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

public class ConversorCelsiusKelvin extends Conversor {
    private static final ConversorCelsiusKelvin INSTANCIA = new ConversorCelsiusKelvin();

    private ConversorCelsiusKelvin(){
        super(MTemperatura.Celsius, MTemperatura.Kelvin);
    }

    public static ConversorCelsiusKelvin getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return valor + 273.15;
    }
}
