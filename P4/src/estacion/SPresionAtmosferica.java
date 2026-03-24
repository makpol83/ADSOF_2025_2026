package estacion;
public class SPresionAtmosferica extends Sensor {
    private static int nextId=1;

    public SPresionAtmosferica(double offset, String unidad){
        super("PRES-" + nextId++, offset, unidad);
    }
    
}
