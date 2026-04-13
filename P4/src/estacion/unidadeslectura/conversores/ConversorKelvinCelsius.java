package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

/**
 * Esta clase (singleton) extendida de Conversor realiza un cambio de unidades de Kelvin a Celsius
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
     * Retorna la instancia única (singleton) de esta clase
     * @return la instancia de esta clase
     */
    public static ConversorKelvinCelsius getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return valor - 273.15;
    }
}
