package estacion.unidadeslectura.conversores;

import estacion.unidadeslectura.MTemperatura;

public class ConversorFahrenhieitKelvin extends Conversor {
    private static final ConversorFahrenhieitKelvin INSTANCIA = new ConversorFahrenhieitKelvin();

    private ConversorFahrenhieitKelvin(){
        super(MTemperatura.Fahrenheit, MTemperatura.Kelvin);
    }

    public static ConversorFahrenhieitKelvin getConversor(){
        return INSTANCIA;
    }

    @Override
    public double convertirUnidades(double valor){
        return (valor-32)*1.8 + 273.15;
    }
}