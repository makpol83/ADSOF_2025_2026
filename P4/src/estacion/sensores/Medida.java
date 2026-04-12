package estacion.sensores;

import java.time.LocalDateTime;

/**
 * Agrupa el contenido de una medida, creado para extensiones futuras
 */
public class Medida {
    /** Valor medido por el sensor */
    private double valorMedido;
    /** Fecha de la medición */
    private LocalDateTime fechaMedida;

    /**
     * Constructor de la medida
     * @param valorMedido Valor medido
     * @param fechaMedida Fecha de la medición
     */
    public Medida(double valorMedido, LocalDateTime fechaMedida){
        this.valorMedido = valorMedido;
        this.fechaMedida = fechaMedida;
    }

    /**
     * Getter valor medido
     * @return double
     */
    public double getValorMedido(){ return this.valorMedido; }

    /**
     * Getter fecha de medida
     * @return double
     */
    public LocalDateTime getFechaMedida(){ return this.fechaMedida; }
}
