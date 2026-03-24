package estacion;

import estacion.medidas.MPresionAtmosferica;

public class Presion extends Sensor implements Medicion {
    private static int nextId=1;

    private MPresionAtmosferica medidaEmpleada;

    public Presion(double offset){
        super("PRES-" + nextId++, offset);

        this.medidaEmpleada = MPresionAtmosferica.hPa;
    }

    public Presion(double offset, MPresionAtmosferica medidaEmpleada){
        this(offset);

        this.medidaEmpleada = medidaEmpleada;
    }

    public void medicionAleatoria(){};
    public void medicionCercana(){};
    public void medicionHistorica(){};
    
}
