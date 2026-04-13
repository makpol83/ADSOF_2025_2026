package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

/**
 * Conversor Fahrenheit --> Celsius
 */
public class ConversorFahrenheitCelsius extends Conversor {
    /** Instancia */
    private static final ConversorFahrenheitCelsius INSTANCIA = new ConversorFahrenheitCelsius();

    /**
     * Constructor
     */
    private ConversorFahrenheitCelsius(){
        super(MTemperatura.Fahrenheit, MTemperatura.Celsius);
    }

    /**
     * Getter instancia
     * @return ConversorFahrenheitCelsius
     */
    public static ConversorFahrenheitCelsius getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return (valor - 32)*(5/9);
    }
}
