package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

/**
 * Esta clase (singleton) extendida de Conversor realiza un cambio de unidades de Fahrenheit a Celsius
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
     * Retorna la instancia única (singleton) de esta clase
     * @return la instancia de esta clase
     */
    public static ConversorFahrenheitCelsius getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return (valor - 32)*(5/9);
    }
}
