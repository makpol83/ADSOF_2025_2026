package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

public class ConversorPascalMiliBar extends Conversor {
    private static final ConversorPascalMiliBar INSTANCIA = new ConversorPascalMiliBar();

    private ConversorPascalMiliBar(){
        super(MPresionAtmosferica.Pa, MPresionAtmosferica.mbar);
    }

    public static ConversorPascalMiliBar getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // 100 mbar == 100 hPa == 1 Pa
        return valor * 100 ;
    }
}
