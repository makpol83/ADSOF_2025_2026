package estacion;

import estacion.medidas.MTemperatura;

public class STemperatura extends Sensor{
    private static int nextId=1;

    private MTemperatura medidaEmpleada;

    public STemperatura(double offset){
        super("TEMP-" + nextId++, offset);
        this.medidaEmpleada = MTemperatura.Celsius;
    }

    public STemperatura(double offset, MTemperatura medidaEmpleada){
        this(offset);
        this.medidaEmpleada = medidaEmpleada;
    }
}
