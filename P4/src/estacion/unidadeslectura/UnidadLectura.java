package estacion.unidadesLectura;

public interface UnidadLectura {
    double getValorMinimo();
    double getValorMaximo();
    boolean esRangoValido(double value);
    String name();
}
