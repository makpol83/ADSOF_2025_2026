package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

/**
 * Conversor Celsius --> Kelvin
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
     * Getter instancia
     * @return ConversorCelsiusKelvin
     */
    public static ConversorCelsiusKelvin getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return valor + 273.15;
    }
}
