package estacion;

import estacion.medidas.MHumedad;

public class Humedad extends Sensor {
    private static int nextId=1;

    private MHumedad medidaEmpleada;

    public Humedad(double offset){
        super("HUM-" + nextId++, offset);

        this.medidaEmpleada = MHumedad.Porcentaje;
    }

    public Humedad(double offset, String unidad, MHumedad medidaEmpleada){
        this(offset);

        this.medidaEmpleada = medidaEmpleada;
    }
    
}
