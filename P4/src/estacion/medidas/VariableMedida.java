package estacion.medidas;

public interface VariableMedida {
    double getValorMinimo();
    double getValorMaximo();
    boolean esRangoValido(double value);
    String name();
}
