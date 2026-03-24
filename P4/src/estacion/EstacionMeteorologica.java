package estacion;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

import estacion.exceptions.MismoIdException;

public class EstacionMeteorologica {
    private String nombre;
    private double longitud;
    private double latitud;
    private Map<String,Sensor> sensores;
    private LocalDateTime periodoLecturaAutomatica;


    public EstacionMeteorologica(String nombre, double longitud, double latitud, Collection<Sensor> sensores) throws MismoIdException {
        this.nombre = nombre;
        this.longitud = longitud;
        this.latitud = latitud;
        for(Sensor s : sensores){
            if(this.sensores.containsKey(s.getIdentificador())
            && this.sensores.get(s.getIdentificador()) != s)
                throw new MismoIdException(this.sensores.get(s.getIdentificador()), s);
            this.sensores.put(s.getIdentificador(), s);
        }
    }

    public boolean añadirSensor(Sensor s) throws MismoIdException {
        if(sensores.containsKey(s.getIdentificador()))
            throw new MismoIdException(sensores.get(s.getIdentificador()), s);

        sensores.put(s.getIdentificador(), s);
        return true;
    }

    public boolean lecturaManual(){
        for(Sensor s : sensores.values()){
            s.lectura();
        }
    }
}