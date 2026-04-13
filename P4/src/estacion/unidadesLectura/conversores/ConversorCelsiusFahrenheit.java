package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

/**
 * Conversor Celsius --> Fahrenheit
 */
public class ConversorCelsiusFahrenheit extends Conversor {
    /** Instancia */
    private static final ConversorCelsiusFahrenheit INSTANCIA = new ConversorCelsiusFahrenheit();

    /**
     * Constructor privado
     */
    private ConversorCelsiusFahrenheit(){
        super(MTemperatura.Celsius, MTemperatura.Fahrenheit);
    }

    /**
     * Getter instancia
     * @return ConversorCelsiusFahrenheit
     */
    public static Conversor getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return valor*1.8 + 32;
    }
}
