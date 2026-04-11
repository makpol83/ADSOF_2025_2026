package estacion.unidadeslectura.conversores;

import estacion.unidadeslectura.MPresionAtmosferica;

public class ConversorHectoPascalMiliBar extends Conversor {
    private static final ConversorHectoPascalMiliBar INSTANCIA = new ConversorHectoPascalMiliBar();

    private ConversorHectoPascalMiliBar(){
        super(MPresionAtmosferica.hPa, MPresionAtmosferica.mbar);
    }

    @Override
    public Conversor getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        // hpa == mbar
        return valor;
    }
}