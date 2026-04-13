package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

/**
 * Esta clase (singleton) extendida de Conversor realiza un cambio de unidades de Milibar a hecto Pascal
 */
public class ConversorMiliBarHectoPascal extends Conversor {
    /** Instancia */
    private static final ConversorMiliBarHectoPascal INSTANCIA = new ConversorMiliBarHectoPascal();

    /**
     * Constructor
     */
    private ConversorMiliBarHectoPascal(){
        super(MPresionAtmosferica.mbar, MPresionAtmosferica.hPa);
    }

    /**
     * Retorna la instancia única (singleton) de esta clase
     * @return la instancia de esta clase
     */
    public static ConversorMiliBarHectoPascal getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // hpa == mbar
        return valor;
    }
}