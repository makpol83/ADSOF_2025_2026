package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

/**
 * Esta clase (singleton) extendida de Conversor realiza un cambio de unidades de Pascal a hecto Pascal
 */
public class ConversorPascalHectoPascal extends Conversor {
    /** Instancia */
    private static final ConversorPascalHectoPascal INSTANCIA = new ConversorPascalHectoPascal();

    /**
     * Constructor
     */
    private ConversorPascalHectoPascal(){
        super(MPresionAtmosferica.Pa, MPresionAtmosferica.hPa);
    }

    /**
     * Retorna la instancia única (singleton) de esta clase
     * @return la instancia de esta clase
     */
    public static ConversorPascalHectoPascal getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // 1 Pa == 100 hPa
        return valor * 100;
    }
}
