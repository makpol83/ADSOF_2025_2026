package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

/**
 * Esta clase (singleton) extendida de Conversor realiza un cambio de unidades de Celsius a Kelvin
 */
public class ConversorCelsiusKelvin extends Conversor {
    /** Instancia */
    private static final ConversorCelsiusKelvin INSTANCIA = new ConversorCelsiusKelvin();

    /**
     * Constructor
     */
    private ConversorCelsiusKelvin(){
        super(MTemperatura.Celsius, MTemperatura.Kelvin);
    }

    /**
     * Retorna la instancia única (singleton) de esta clase
     * @return la instancia de esta clase
     */
    public static ConversorCelsiusKelvin getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return valor + 273.15;
    }
}
