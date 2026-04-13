package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MTemperatura;

/**
 * Esta clase (singleton) extendida de Conversor realiza un cambio de unidades de Celsius a Fahrenheit
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
     * Retorna la instancia única (singleton) de esta clase
     * @return la instancia de esta clase
     */
    public static ConversorCelsiusFahrenheit getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return valor*1.8 + 32;
    }
}
