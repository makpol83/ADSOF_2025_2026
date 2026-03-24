package estacion;
public class SHumedad extends Sensor {
    private static int nextId=1;

    private 

    public SHumedad(double offset, String unidad){
        super("HUM-" + nextId++, offset, unidad);
    }
    
}
