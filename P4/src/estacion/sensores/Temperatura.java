package estacion.sensores;

import estacion.estrategiasMedicion.EstrategiaMedicion;
import estacion.medidas.MTemperatura;

public class Temperatura extends Sensor{
    private static int nextId=1;

    private static final MTemperatura medidaPorDefecto = MTemperatura.Celsius;

    public Temperatura(double offset){
        this(offset, medidaPorDefecto);
    }

    public Temperatura(double offset, MTemperatura medidaEmpleada){
        super("TEMP-" + nextId++, offset, medidaEmpleada);
    }

    public Temperatura(double offset, EstrategiaMedicion estrategiaMedicion){
        this(offset, medidaPorDefecto, estrategiaMedicion);
    }

    public Temperatura(double offset, MTemperatura medidaEmpleada, EstrategiaMedicion estrategiaMedicion){
        super("TEMP-" + nextId++, offset, medidaEmpleada, estrategiaMedicion);
    }
    
}
