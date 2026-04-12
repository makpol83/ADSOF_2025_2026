package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

public class ConversorMiliBarHectoPascal extends Conversor {
    private static final ConversorMiliBarHectoPascal INSTANCIA = new ConversorMiliBarHectoPascal();

    private ConversorMiliBarHectoPascal(){
        super(MPresionAtmosferica.mbar, MPresionAtmosferica.hPa);
    }

    public static ConversorMiliBarHectoPascal getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // hpa == mbar
        return valor;
    }
}