package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

/**
 * Esta clase (singleton) extendida de Conversor realiza un cambio de unidades de Fahrenheit a Kelvin
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
     * Retorna la instancia única (singleton) de esta clase
     * @return la instancia de esta clase
     */
    public static ConversorFahrenheitKelvin getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return (valor-32)*5/9 + 273.15;
    }
}