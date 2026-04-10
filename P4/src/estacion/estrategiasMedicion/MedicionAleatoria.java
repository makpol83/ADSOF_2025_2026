package estacion.estrategiasMedicion;

import estacion.Sensor;

public class MedicionAleatoria implements EstrategiaMedicion{
    private double probFueraRango;

    public MedicionAleatoria(double probFueraRango){
        this.probFueraRango = probFueraRango;
    }

    public double medir(Sensor s){
        double valorMin = s.getValorLecturaMinimo();
        double valorMax = s.getValorLecturaMaximo();

        if(Math.random() < probFueraRango){
            if(Math.random() >= 0.5){
                return (valorMax - valorMin)*Math.random() + valorMin + valorMax;
            } else {
                return (valorMax - valorMin)*Math.random() - valorMax;
            }
        } else {
            return (valorMax - valorMin)*Math.random() + valorMin;
        }
    }
}
