package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

/**
 * Conversor Kelvin --> Fahrenheit
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
     * Getter instancia
     * @return ConversorKelvinFahrenheit
     */
    public static ConversorKelvinFahrenheit getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return (valor-273.15)*1.8 + 32;
    }
}