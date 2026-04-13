package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

/**
 * Esta clase (singleton) extendida de Conversor realiza un cambio de unidades de milibar a Pascal
 */
public class ConversorMiliBarPascal extends Conversor {
    /** Instancia */
    private static final ConversorMiliBarPascal INSTANCIA = new ConversorMiliBarPascal();

    /**
     * Constructor
     */
    private ConversorMiliBarPascal(){
        super(MPresionAtmosferica.mbar, MPresionAtmosferica.Pa);
    }

    /**
     * Retorna la instancia única (singleton) de esta clase
     * @return la instancia de esta clase
     */
    public static ConversorMiliBarPascal getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // 100 mbar == 100 hPa == 1 Pa
        return valor / 100 ;
    }
}
