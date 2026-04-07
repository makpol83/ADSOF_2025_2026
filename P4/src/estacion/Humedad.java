package estacion;

import java.time.LocalDate;
import java.time.LocalDateTime;

import estacion.estrategiasMedicion.EstrategiaMedicion;
import estacion.medidas.MHumedad;
import estacion.medidas.MTemperatura;

public class Humedad extends Sensor {
    private static int nextId=1;

    private static final MHumedad medidaPorDefecto = MHumedad.Porcentaje;

    public Humedad(double offset){
        this(offset, medidaPorDefecto);
    }

    public Humedad(double offset, MHumedad medidaEmpleada){
        super("HUM-" + nextId++, offset, medidaEmpleada);
    }

    public Humedad(double offset, EstrategiaMedicion estrategiaMedicion){
        this(offset, medidaPorDefecto, estrategiaMedicion);
    }

    public Humedad(double offset, MHumedad medidaEmpleada, EstrategiaMedicion estrategiaMedicion){
        super("HUM-" + nextId++, offset, medidaEmpleada, estrategiaMedicion);
    }
    
}
