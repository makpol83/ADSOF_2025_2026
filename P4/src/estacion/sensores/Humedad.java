package estacion.sensores;


import estacion.estrategiasMedicion.EstrategiaMedicion;
import estacion.unidadeslectura.MHumedad;

public class Humedad extends Sensor {
    private static int nextId=1;

    private static final MHumedad medidaPorDefecto = MHumedad.Porcentaje;

    public Humedad(double offset){
        this(offset, medidaPorDefecto);
    }

    public Humedad(double offset, MHumedad medidaEmpleada){
        super("HUM-" + String.format("%04d", nextId++), offset, medidaEmpleada);
    }

    public Humedad(double offset, EstrategiaMedicion estrategiaMedicion){
        this(offset, medidaPorDefecto, estrategiaMedicion);
    }

    public Humedad(double offset, MHumedad medidaEmpleada, EstrategiaMedicion estrategiaMedicion){
        super("HUM-" + String.format("%04d", nextId++), offset, medidaEmpleada, estrategiaMedicion, null);
    }
    
}
