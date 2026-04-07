package estacion.estrategiasMedicion;

public interface EstrategiaMedicion {
    double medir(double valorMinimo, double valorMaximo, double ultimoValorMedido, double mediaHistorica);
}
