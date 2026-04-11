package estacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Collection;
import java.util.Map;

import estacion.exceptions.MismoIdException;
import estacion.sensores.Sensor;
import estacion.utils.Tuple;

public class EstacionMeteorologica {
    private String nombre;
    private double longitud;
    private double latitud;
    private Map<String,Tuple<Sensor, LocalDate>> sensores;
    private LocalDateTime fechaUltimaLectura;

    private LocalDateTime fechaProximaLecturaAutomatica;
    private LocalDateTime periodoLecturaAutomatica;
    private int numLecturaAutomaticaMaxima;


    public EstacionMeteorologica(String nombre, double longitud, double latitud, Collection<Sensor> sensores) throws MismoIdException {
        this.nombre = nombre;
        this.longitud = longitud;
        this.latitud = latitud;
        this.fechaUltimaLectura = null;
        for(Sensor s : sensores){
            if(this.sensores.containsKey(s.getIdentificador())
            && this.sensores.get(s.getIdentificador()).getElement1() != s)
                throw new MismoIdException(this.sensores.get(s.getIdentificador()).getElement1(), s);
            else{
                Tuple<Sensor, LocalDate> t = new Tuple<>(s, LocalDate.now());
                this.sensores.put(s.getIdentificador(), t);
            }
            
        }
    }

    public boolean configurarLecturaAutomatica(LocalDateTime periodoLecturaAutomatica, int numLecturasMaximas){
        if(numLecturasMaximas < 1) return false;

        this.periodoLecturaAutomatica = periodoLecturaAutomatica;
        this.numLecturaAutomaticaMaxima = numLecturasMaximas;

        if(this.fechaUltimaLectura == null){
            this.fechaProximaLecturaAutomatica = LocalDateTime.of(
                LocalDateTime.now().getYear() + periodoLecturaAutomatica.getYear(),
                LocalDateTime.now().getMonthValue() + periodoLecturaAutomatica.getMonthValue(),
                LocalDateTime.now().getDayOfMonth() + periodoLecturaAutomatica.getDayOfMonth(),
                LocalDateTime.now().getHour() + periodoLecturaAutomatica.getHour(),
                LocalDateTime.now().getMinute() + periodoLecturaAutomatica.getMinute(),
                LocalDateTime.now().getSecond() + periodoLecturaAutomatica.getSecond()    
            );
        }
        else{
            this.fechaProximaLecturaAutomatica = LocalDateTime.of(
               fechaUltimaLectura.getYear() + periodoLecturaAutomatica.getYear(),
                fechaUltimaLectura.getMonthValue() + periodoLecturaAutomatica.getMonthValue(),
                fechaUltimaLectura.getDayOfMonth() + periodoLecturaAutomatica.getDayOfMonth(),
                fechaUltimaLectura.getHour() + periodoLecturaAutomatica.getHour(),
                fechaUltimaLectura.getMinute() + periodoLecturaAutomatica.getMinute(),
                fechaUltimaLectura.getSecond() + periodoLecturaAutomatica.getSecond()    
            );
        }

        //solo va a hacer el intento, no necesariamente se realiza la lectura
        this.lecturaAutomatica();

        return true;
    }

    public boolean añadirSensor(Sensor s) throws MismoIdException {
        if(sensores.containsKey(s.getIdentificador()))
            throw new MismoIdException(sensores.get(s.getIdentificador()).getElement1(), s);

        sensores.put(s.getIdentificador(), new Tuple<>(s, LocalDate.now()));
        return true;
    }

    public Sensor getSensor(String identificador){
        return this.sensores.get(identificador).getElement1();
    }

    public LocalDate getFechaInstalacion(Sensor s) {
        if(this.sensores.containsKey(s.getIdentificador()))
            return this.sensores.get(s.getIdentificador()).getElement2();
        else
            return null;
    }

    public LocalDate getFechaInstalacion(String sensorId) {
        if(this.sensores.containsKey(sensorId))
            return this.sensores.get(sensorId).getElement2();
        else
            return null;
    }

    public void lecturaManual(){ this.lecturaManual(this.sensores.size()); }

    public void lecturaManual(int numLecturas){
        int LecturasCompletadas = 0;
        for(Tuple<Sensor, LocalDate> t : sensores.values()){
            Sensor s = t.getElement1();
            s.realizarMedida();
            LecturasCompletadas++;
            if(LecturasCompletadas >= numLecturas){
                break;
            }
        }
        if(LecturasCompletadas > 0)
            this.fechaUltimaLectura = LocalDateTime.now();
    }

    public void lecturaAutomatica(){
        if(fechaProximaLecturaAutomatica.isAfter(LocalDateTime.now()) == false){
            lecturaManual(this.numLecturaAutomaticaMaxima);
        }
    }

    public void printEstacionMeteorologica(){
        System.out.println(this);
        System.out.println("------------------------------------------------");
        System.out.println("Sensores instalados: " + this.sensores.size());
        System.out.println("Última lectura: ");
        for(Tuple<Sensor, LocalDate> t : this.sensores.values()){
            System.out.println(t.getElement1());
        }
    }

    @Override
    public String toString(){
        return "Estación Meteorológica: " + nombre + "\nUbicación: " + latitud + ", " + longitud;
    }
}