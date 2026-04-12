package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

public class ConversorMiliBarPascal extends Conversor {
    private static final ConversorMiliBarPascal INSTANCIA = new ConversorMiliBarPascal();

    private ConversorMiliBarPascal(){
        super(MPresionAtmosferica.mbar, MPresionAtmosferica.Pa);
    }

    public static ConversorMiliBarPascal getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // 100 mbar == 100 hPa == 1 Pa
        return valor / 100 ;
    }
}
