package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

/**
 * Conversor HectoPascal --> Pascal
 */
public class ConversorHectoPascalPascal extends Conversor {
    /** Instancia */
    private static final ConversorHectoPascalPascal INSTANCIA = new ConversorHectoPascalPascal();

    /** Constructor */
    private ConversorHectoPascalPascal(){
        super(MPresionAtmosferica.hPa, MPresionAtmosferica.Pa);
    }

    /**
     * Getter instancia
     * @return ConversorHectoPascalPascal
     */
    public static ConversorHectoPascalPascal getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // 1 Pa == 100 hPa
        return valor / 100;
    }
}
