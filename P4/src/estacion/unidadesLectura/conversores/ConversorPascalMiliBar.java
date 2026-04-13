package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

/**
 * Conversor Pascal --> MiliBar
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
     * Getter instancia
     * @return ConverosrPascalMilibar
     */
    public static ConversorPascalMiliBar getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // 100 mbar == 100 hPa == 1 Pa
        return valor * 100 ;
    }
}
