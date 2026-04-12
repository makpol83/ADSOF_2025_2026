package estacion;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import estacion.exceptions.MismoIdException;
import estacion.sensores.Sensor;
import estacion.unidadeslectura.UnidadLectura;
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


    public EstacionMeteorologica(String nombre, double longitud, double latitud) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.latitud = latitud;
        this.fechaUltimaLectura = null;
        this.fechaProximaLecturaAutomatica = null;
        this.numLecturaAutomaticaMaxima = 0;
        this.sensores = new HashMap<>();
    }

    public EstacionMeteorologica(String nombre, double longitud, double latitud, Collection<Sensor> sensores) throws MismoIdException {
        this(nombre, longitud, latitud);
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

        if(periodoLecturaAutomatica != null){
            if(this.fechaUltimaLectura == null){
                this.fechaProximaLecturaAutomatica = addDates(LocalDateTime.now(), periodoLecturaAutomatica);
            }
            else{
                this.fechaProximaLecturaAutomatica = addDates(fechaUltimaLectura, periodoLecturaAutomatica);
            }
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
        if(LecturasCompletadas > 0){
            this.fechaUltimaLectura = LocalDateTime.now();
            if(this.periodoLecturaAutomatica != null)
                this.fechaProximaLecturaAutomatica = addDates(fechaUltimaLectura, periodoLecturaAutomatica);
        }
    }

    public void lecturaAutomatica(){
        if(fechaProximaLecturaAutomatica.isAfter(LocalDateTime.now()) == false)
            lecturaManual(this.numLecturaAutomaticaMaxima);
    }

    public void printEstacionMeteorologica(){
        System.out.println(this);
        System.out.println("------------------------------------------------");
        System.out.println("Sensores instalados: " + this.sensores.size());
        System.out.println("Última lectura: ");
        for(Tuple<Sensor, LocalDate> t : this.sensores.values()){
            Sensor s = t.getElement1();
            double min = s.getProcesador().getLecturaMinima();
            double max = s.getProcesador().getLecturaMaxima();
            double avg = s.getProcesador().getLecturaMedia();

            //insertar el historial entero pero formateando los datos para que tengan 2 decimales y solo se muestre el valor de lectura y no la fecha
            String historial = "[";
            for(Tuple<Double, LocalDateTime> tup : s.getProcesador().getHistorial()){
                historial = historial.concat(formatDouble(tup.getElement1()) + ", ");
            }
            // le quito el ", " extra que se le añadio en el bucle anterior
            historial = historial.substring(0, historial.length()-2);
            historial = historial.concat("]");

            String conversor = (s.getProcesador().convierteUnidades()) ? "con conversor a " + s.getProcesador().getUnidadAConvertir().toString() : "";


            System.out.println(s.getIdentificador() + " (" + s.getUnidadLectura() + ") " + conversor + ": " + historial +
                " --" + " MIN: " + formatDouble(min) + " MAX: " + formatDouble(max) + " AVG: " + formatDouble(avg));
        }
    }

    @Override
    public String toString(){
        return "Estación Meteorológica: " + nombre + "\nUbicación: " + latitud + ", " + longitud;
    }

    //añado la excepcion por si alguien la quisiera capturar (la lanza LocalDateTime.of())
    private LocalDateTime addDates(LocalDateTime date1, LocalDateTime date2) throws DateTimeException {
        return LocalDateTime.of(
            date1.getYear() + date2.getYear(),
            date1.getMonthValue() + date2.getMonthValue(),
            date1.getDayOfMonth() + date2.getDayOfMonth(),
            date1.getHour() + date2.getHour(),
            date1.getMinute() + date2.getMinute(),
            date1.getSecond() + date2.getSecond()               
        );
    }

    private String formatDouble(double d){
        return String.format("%.2f", d);
    }
}