package estacion.unidadeslectura.conversores;

import estacion.unidadeslectura.MPresionAtmosferica;

public class ConversorPascalHectoPascal extends Conversor {
    private static final ConversorPascalHectoPascal INSTANCIA = new ConversorPascalHectoPascal();

    private ConversorPascalHectoPascal(){
        super(MPresionAtmosferica.Pa, MPresionAtmosferica.hPa);
    }

    @Override
    public Conversor getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // 1 Pa == 100 hPa
        return valor * 100;
    }
}
