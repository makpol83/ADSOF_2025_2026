package estacion.estrategiasMedicion;

import estacion.sensores.Sensor;

/**
 * Describe el funcionamiento de las mediciones aleatorias.
 */
public class MedicionAleatoria implements EstrategiaMedicion{
    /** Probabilidad de que la medición esté fuera de rango, rango 0 a 1 */
    private double probFueraRango;

    /**
     * Constructor de la medicion aleatoria
     * @param probFueraRango en el rango 0 a 1
     */
    public MedicionAleatoria(double probFueraRango){
        this.probFueraRango = probFueraRango;
    }

    public double medir(Sensor s){
        double valorMin = s.getValorLecturaMinimo();
        double valorMax = s.getValorLecturaMaximo();

        //Miramos si va a estar fuera de rango o no
        if(Math.random() < probFueraRango){
            //En caso de estar fuera de rango, vemos si está por arriba o por abajo
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
