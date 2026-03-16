/**
 * Clase EnlaceSeñuelo, incluye la funcionalidad de Enlace, pero añade dos funcionalidades nuevas:
 * CosteExtra: Ahora a parte del coste original de un enlace, un enlaceSeñuelo tiene un coste especial
 * que se calcula como el coste original * factorCosteExtra, de forma que el coste real de enlace es
 * la suma del coste original y el coste especial.
 * Probabilidad de Retorno Obligado: Cada enlace señuelo tiene una probabilidad de no propagar un mensaje
 * que intente pasar por el enlace, en cambio, lo mantiene en el usuario que lo intentó mandar.
 */
public class EnlaceSeñuelo extends Enlace{
    /** Factor de coste extra del EnlaceSeñuelo >= 0*/
    private int factorCosteExtra;
    /** Probabilidad de retorno obligado >= 0 y <= 1*/
    private double probRetornoObligado;

    /**
     * Constructor EnlaceSeñuelo, similar a enlace pero añade factorCosteExtra y probRetornoObligado
     * @param usuarioOrigen usuario origen del enlace
     * @param usuarioDestino usuario destino del enlace
     * @param coste coste base del enlace
     * @param factorCosteExtra factor de coste extra para el coste especial (>= 0)
     * @param probRetornoObligado probabilidad de que el retorno sea obligado (0 a 1)
     */
    public EnlaceSeñuelo(Usuario usuarioOrigen, Usuario usuarioDestino, int coste, int factorCosteExtra, double probRetornoObligado){
        super(usuarioOrigen, usuarioDestino, coste);
        this.factorCosteExtra = factorCosteExtra;
        this.probRetornoObligado = probRetornoObligado;
    }

    /**
     * Constructor sin el coste base del enlace, se asume como 1
     * @param usuarioOrigen usuario origen del enlace
     * @param usuarioDestino usuario destino del enlace
     * @param factorCosteExtra factor de coste extra para el coste especial (>= 0)
     * @param probRetornoObligado probabilidad de que el retorno sea obligado (0 a 1)
     */
    public EnlaceSeñuelo(Usuario usuarioOrigen, Usuario usuarioDestino, int factorCosteExtra, double probRetornoObligado){
        this(usuarioOrigen, usuarioDestino, 1, factorCosteExtra, probRetornoObligado);
    }

    @Override
    /**
     * Sobreescritura del método esRetornoObligado de Enlace para establecer la probabilidad
     * de que el retorno sea obligado.
     * @return true si se debe retornar al emisario, false si el mensaje
     * pasa correctamente.
     */
    public boolean esRetornoObligado(){
        double value = Math.random();
        return ( value <= this.probRetornoObligado);
    }

    @Override
    /**
     * Sobreescritura de costeEspecial de Enlace para añadir el coste especial asignado a un EnlaceSeñuelo.
     * @return Coste especial del enlace
     */
    public int costeEspecial(){
        return this.getCoste() * this.factorCosteExtra;
    }

    @Override
    /**
     * Dado que este enlace es Señuelo retorna false.
     * @return false
     */
    public boolean esSeguro(){
        return false;
    }

}
