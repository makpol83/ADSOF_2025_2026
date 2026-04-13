package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

/**
 * Esta clase (singleton) extendida de Conversor realiza un cambio de unidades de hecto Pascal a Milibar
 */
public class ConversorHectoPascalMiliBar extends Conversor {
    /** Instancia */
    private static final ConversorHectoPascalMiliBar INSTANCIA = new ConversorHectoPascalMiliBar();

    /**
     * Constructor
     */
    private ConversorHectoPascalMiliBar(){
        super(MPresionAtmosferica.hPa, MPresionAtmosferica.mbar);
    }

    /**
     * Retorna la instancia única (singleton) de esta clase
     * @return la instancia de esta clase
     */
    public static ConversorHectoPascalMiliBar getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // hpa == mbar
        return valor;
    }
}