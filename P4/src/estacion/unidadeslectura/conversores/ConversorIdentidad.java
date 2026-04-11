package estacion.unidadeslectura.conversores;



public class ConversorIdentidad extends Conversor {
    private static final ConversorIdentidad INSTANCIA = new ConversorIdentidad();

    private ConversorIdentidad(){
        super(null, null);
    }

    @Override
    public Conversor getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        return valor;
    }
}
