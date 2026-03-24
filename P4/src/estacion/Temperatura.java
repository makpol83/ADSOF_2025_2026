package estacion;

import estacion.medidas.MTemperatura;

public class Temperatura extends Sensor{
    private static int nextId=1;

    private MTemperatura medidaEmpleada;

    public Temperatura(double offset){
        super("TEMP-" + nextId++, offset);
        this.medidaEmpleada = MTemperatura.Celsius;
    }

    public Temperatura(double offset, MTemperatura medidaEmpleada){
        this(offset);
        this.medidaEmpleada = medidaEmpleada;
    }
}
