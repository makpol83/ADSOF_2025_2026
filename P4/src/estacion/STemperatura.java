package estacion;
public class STemperatura extends Sensor{
    private static int nextId=1;



    public STemperatura(double offset, String unidad){
        super("TEMP-" + nextId++, offset, unidad);
    }
}
