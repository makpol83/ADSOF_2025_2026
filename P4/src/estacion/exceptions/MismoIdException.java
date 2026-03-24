package estacion.exceptions;

import estacion.Sensor;

public class MismoIdException extends Exception {
    private Sensor s1;
    private Sensor s2;
    private String excepcion;

    public MismoIdException(Sensor s1, Sensor s2){
        this.s1 = s1;
        this.s2 = s2;
    }
    
}