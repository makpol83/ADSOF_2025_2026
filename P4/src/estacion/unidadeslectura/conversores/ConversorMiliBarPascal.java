package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

/**
 * Conversor Milibar --> Pascal
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
     * Getter instancia
     * @return ConversorMilibarPascal
     */
    public static ConversorMiliBarPascal getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // 100 mbar == 100 hPa == 1 Pa
        return valor / 100 ;
    }
}
