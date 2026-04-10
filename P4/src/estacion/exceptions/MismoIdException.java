package estacion.exceptions;

import estacion.sensores.Sensor;

public class MismoIdException extends Exception {
    private Sensor s1;
    private Sensor s2;
    private static String excepcion = "Se ha intentado crear un sensor con un ID ya existente";

    public MismoIdException(Sensor s1, Sensor s2){
        super(excepcion);
        this.s1 = s1;
        this.s2 = s2;
    }

    public Sensor getS1(){ return this.s1; }
    public Sensor getS2(){ return this.s2; }
    
}