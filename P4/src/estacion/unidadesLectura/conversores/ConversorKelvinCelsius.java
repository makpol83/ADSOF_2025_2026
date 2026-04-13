package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

/**
 * Conversor Kelvin --> Celsius
 */
public class ConversorKelvinCelsius extends Conversor {
    /** Instancia */
    private static final ConversorKelvinCelsius INSTANCIA = new ConversorKelvinCelsius();

    /**
     * Conversor
     */
    private ConversorKelvinCelsius(){
        super(MTemperatura.Kelvin, MTemperatura.Celsius);
    }

    /**
     * Getter instancia
     * @return ConversorKelvinCelsius
     */
    public static ConversorKelvinCelsius getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return valor - 273.15;
    }
}
