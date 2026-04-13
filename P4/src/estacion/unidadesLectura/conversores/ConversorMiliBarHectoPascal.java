package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

/**
 * Conversor Milibar --> HectoPascal
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
     * Getter instancia
     * @return ConversorMiliBarHectoPascal
     */
    public static ConversorMiliBarHectoPascal getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // hpa == mbar
        return valor;
    }
}