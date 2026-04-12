package estacion.unidadesLectura.conversores;

import estacion.unidadesLectura.MPresionAtmosferica;

public class ConversorHectoPascalPascal extends Conversor {
    private static final ConversorHectoPascalPascal INSTANCIA = new ConversorHectoPascalPascal();

    private ConversorHectoPascalPascal(){
        super(MPresionAtmosferica.hPa, MPresionAtmosferica.Pa);
    }

    public static ConversorHectoPascalPascal getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // 1 Pa == 100 hPa
        return valor / 100;
    }
}
