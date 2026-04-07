package estacion;

public interface EstrategiaMedicion {

    void medicionAleatoria(double probabilidadFueraRango);
    void medicionCercana(double offsetPorcentaje);
    void medicionHistorica(double offsetPorcentaje);
}
