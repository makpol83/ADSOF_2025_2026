package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

/**
 * Conversor Pascal --> HectoPascal
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
     * Getter instancia
     * @return ConversorPascalHectoPascal
     */
    public static ConversorPascalHectoPascal getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // 1 Pa == 100 hPa
        return valor * 100;
    }
}
