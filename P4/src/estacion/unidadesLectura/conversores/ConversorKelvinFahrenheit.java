package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

/**
 * Esta clase (singleton) extendida de Conversor realiza un cambio de unidades de Kelvin a Fahrenheit
 */
public class ConversorKelvinFahrenheit extends Conversor {
    /** Instancia */
    private static final ConversorKelvinFahrenheit INSTANCIA = new ConversorKelvinFahrenheit();

    /**
     * Constructor
     */
    private ConversorKelvinFahrenheit(){
        super(MTemperatura.Kelvin, MTemperatura.Fahrenheit);
    }

    /**
     * Retorna la instancia única (singleton) de esta clase
     * @return la instancia de esta clase
     */
    public static ConversorKelvinFahrenheit getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return (valor-273.15)*1.8 + 32;
    }
}