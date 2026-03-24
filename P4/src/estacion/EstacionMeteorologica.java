package estacion;

import java.util.Map;

import estacion.exceptions.MismoIdException;

public class EstacionMeteorologica {
    private String nombre;
    private double longitud;
    private double latitud;
    private Map<String,Sensor> sensores;





    public boolean añadirSensor(Sensor s) throws MismoIdException {
        if(sensores.containsKey(s.getIdentificador()))
            throw new MismoIdException(sensores.get(s.getIdentificador()), s);

        sensores.put(s.getIdentificador(), s);
        return true;
    }

}
