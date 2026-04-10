package estacion.estrategiasMedicion;

import estacion.sensores.Sensor;

public class MedicionHistorica implements EstrategiaMedicion{
    private double offsetPorcentaje;
    
    public MedicionHistorica(double offsetPorcentaje){
        this.offsetPorcentaje = offsetPorcentaje;
    }

    public double medir(Sensor s){
        double p = Math.random() * offsetPorcentaje/100;
        return s.getMediaHistorica() + s.getMediaHistorica() * p;
    }
}
