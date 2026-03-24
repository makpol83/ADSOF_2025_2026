package estacion;

import estacion.medidas.MHumedad;

public class SHumedad extends Sensor {
    private static int nextId=1;

    private MHumedad medidaEmpleada;

    public SHumedad(double offset){
        super("HUM-" + nextId++, offset);

        this.medidaEmpleada = MHumedad.Porcentaje;
    }

    public SHumedad(double offset, String unidad, MHumedad medidaEmpleada){
        this(offset);

        this.medidaEmpleada = medidaEmpleada;
    }
    
}
