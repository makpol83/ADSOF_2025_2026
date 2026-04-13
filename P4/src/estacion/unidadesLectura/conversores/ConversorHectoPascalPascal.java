package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

/**
 * Esta clase (singleton) extendida de Conversor realiza un cambio de unidades de hecto Pascal a Pascal
 */
public class ConversorHectoPascalPascal extends Conversor {
    /** Instancia */
    private static final ConversorHectoPascalPascal INSTANCIA = new ConversorHectoPascalPascal();

    /** Constructor */
    private ConversorHectoPascalPascal(){
        super(MPresionAtmosferica.hPa, MPresionAtmosferica.Pa);
    }

    /**
     * Retorna la instancia única (singleton) de esta clase
     * @return la instancia de esta clase
     */
    public static ConversorHectoPascalPascal getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // 1 Pa == 100 hPa
        return valor / 100;
    }
}
