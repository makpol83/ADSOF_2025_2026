package estacion.estrategiasMedicion;

import estacion.sensores.Sensor;

/**
 * Esta clase se encarga de realizar mediciones logaritmicas. Su fucionamiento es el resultado de aplicar un logaritmo natural a una
 * MedicionAleatoria. Si el resultado es negativo retornara 0. Es posible que termine fuera del rango tras aplicar el logaritmo.
 */
public class MedicionLogaritmica extends MedicionAleatoria{
    public MedicionLogaritmica(){
        super(0);
    }

    @Override
    public double medir(Sensor s){
        double medicion = super.medir(s);
        if(medicion <= 0)
            return 0;
        else
            return Math.log(medicion);
    }
}
