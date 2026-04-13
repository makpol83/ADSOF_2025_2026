package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

/**
 * Conversor HectoPascal --> Milibar
 */
public class ConversorHectoPascalMiliBar extends Conversor {
    /** Instancia */
    private static final ConversorHectoPascalMiliBar INSTANCIA = new ConversorHectoPascalMiliBar();

    /**
     * Constructor
     */
    private ConversorHectoPascalMiliBar(){
        super(MPresionAtmosferica.hPa, MPresionAtmosferica.mbar);
    }

    /**
     * Getter instancia
     * @return ConversorHectoPascalMilibar
     */
    public static ConversorHectoPascalMiliBar getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // hpa == mbar
        return valor;
    }
}