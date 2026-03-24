package estacion;

import estacion.medidas.MPresionAtmosferica;

public class SPresionAtmosferica extends Sensor {
    private static int nextId=1;

    private MPresionAtmosferica medidaEmpleada;

    public SPresionAtmosferica(double offset){
        super("PRES-" + nextId++, offset);

        this.medidaEmpleada = MPresionAtmosferica.hPa;
    }

    public SPresionAtmosferica(double offset, MPresionAtmosferica medidaEmpleada){
        this(offset);

        this.medidaEmpleada = medidaEmpleada;
    }
    
}
