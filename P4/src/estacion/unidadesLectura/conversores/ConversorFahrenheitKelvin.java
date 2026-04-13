package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

/**
 * Conversor Fahrenheit --> Kelvin
 */
public class ConversorFahrenheitKelvin extends Conversor {
    /** Instancia */
    private static final ConversorFahrenheitKelvin INSTANCIA = new ConversorFahrenheitKelvin();

    /**
     * Constructor
     */
    private ConversorFahrenheitKelvin(){
        super(MTemperatura.Fahrenheit, MTemperatura.Kelvin);
    }

    /**
     * Getter instancia
     * @return ConversorFahrenheitKelvin
     */
    public static ConversorFahrenheitKelvin getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return (valor-32)*5/9 + 273.15;
    }
}