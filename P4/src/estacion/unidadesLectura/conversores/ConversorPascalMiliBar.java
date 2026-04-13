package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

/**
 * Esta clase (singleton) extendida de Conversor realiza un cambio de unidades de Pascal a Milibar
 */
public class ConversorPascalMiliBar extends Conversor {
    /** Instancia */
    private static final ConversorPascalMiliBar INSTANCIA = new ConversorPascalMiliBar();

    /**
     * Constructor
     */
    private ConversorPascalMiliBar(){
        super(MPresionAtmosferica.Pa, MPresionAtmosferica.mbar);
    }

    /**
     * Retorna la instancia única (singleton) de esta clase
     * @return la instancia de esta clase
     */
    public static ConversorPascalMiliBar getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // 100 mbar == 100 hPa == 1 Pa
        return valor * 100 ;
    }
}
