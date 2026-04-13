package estacion.unidadesLectura.conversores;

/**
 * Conversor Identidad
 */
public class ConversorIdentidad extends Conversor {
    /** Instancia */
    private static final ConversorIdentidad INSTANCIA = new ConversorIdentidad();

    /** Constructor */
    private ConversorIdentidad(){
        super(null, null);
    }

    /**
     * Getter instancia
     * @return ConversorIdentidad
     */
    public static ConversorIdentidad getConversor(){ return INSTANCIA; }

    @Override
    public double convertirUnidades(double valor){
        return valor;
    }
}
