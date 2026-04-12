package estacion.sensores;

import estacion.estrategiasMedicion.EstrategiaMedicion;
import estacion.unidadeslectura.MPresionAtmosferica;

public class Presion extends Sensor {
    private static int nextId=1;

    private static final MPresionAtmosferica medidaPorDefecto = MPresionAtmosferica.hPa;

    public Presion(double offset){
        this(offset, medidaPorDefecto);
    }

    public Presion(double offset, MPresionAtmosferica medidaEmpleada){
        super("PRES-" + String.format("%04d", nextId++), offset, medidaEmpleada);
    }

    public Presion(double offset, EstrategiaMedicion estrategiaMedicion){
        this(offset, medidaPorDefecto, estrategiaMedicion);
    }

    public Presion(double offset, MPresionAtmosferica medidaEmpleada, EstrategiaMedicion estrategiaMedicion){
        super("PRES-" + String.format("%04d", nextId++), offset, medidaEmpleada, estrategiaMedicion, null);
    }

    
    
}
