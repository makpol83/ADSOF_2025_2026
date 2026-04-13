package estacion.unidadesLectura.conversores;



/**
 * Esta clase (singleton) extendida de Conversor realiza un cambio de unidades de la unidad inicial a la unidad
 * inicial. O lo que es lo mismo, no realiza ningún cambio de la variable, no modificando el dato a convertir
 */
public class ConversorIdentidad extends Conversor {
    /** Instancia */
    private static final ConversorIdentidad INSTANCIA = new ConversorIdentidad();

    /** Constructor */
    private ConversorIdentidad(){
        super(null, null);
    }

    /**
     * Retorna la instancia única (singleton) de esta clase
     * @return la instancia de esta clase
     */
    public static ConversorIdentidad getConversor(){ return INSTANCIA; }

    /**
     * Retorna el valor introducido sin realizar ningún cambio de variable sobre él
     * @param valor valor a convertir
     * @return valor
     */
    @Override
    public double convertirUnidades(double valor){
        return valor;
    }
}
