package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

public class ConversorHectoPascalMiliBar extends Conversor {
    private static final ConversorHectoPascalMiliBar INSTANCIA = new ConversorHectoPascalMiliBar();

    private ConversorHectoPascalMiliBar(){
        super(MPresionAtmosferica.hPa, MPresionAtmosferica.mbar);
    }

    public static ConversorHectoPascalMiliBar getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // hpa == mbar
        return valor;
    }
}