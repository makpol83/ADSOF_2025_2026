package estacion;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import estacion.exceptions.CalibracionCaducadaException;
import estacion.exceptions.CambioBruscoLecturaException;
import estacion.exceptions.MedidaFueraRangoException;
import estacion.exceptions.MismoIdException;
import estacion.sensores.Medida;
import estacion.sensores.Sensor;
import estacion.unidadesLectura.UnidadLectura;
import estacion.utils.Tuple;

public class EstacionMeteorologica {
    private String nombre;
    private double longitud;
    private double latitud;
    private Map<String,Sensor> sensores;
    private LocalDateTime fechaUltimaLectura;

    private LocalDateTime fechaProximaLecturaAutomatica;
    private LocalDateTime periodoLecturaAutomatica;
    private int numLecturaAutomaticaMaxima;

    private List<Exception> alertas;

    private List<Sensor> sensoresDetenidos;

    private static final int duracionDiasCalibracionPorDefecto = 365;

    public EstacionMeteorologica(String nombre, double longitud, double latitud) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.latitud = latitud;
        this.fechaUltimaLectura = null;
        this.fechaProximaLecturaAutomatica = null;
        this.numLecturaAutomaticaMaxima = 0;
        this.sensoresDetenidos = new ArrayList<>();
        this.sensores = new HashMap<>();
        this.alertas = new ArrayList<>();
    }

    public EstacionMeteorologica(String nombre, double longitud, double latitud, Collection<Sensor> sensores) throws MismoIdException {
        this(nombre, longitud, latitud);
        for(Sensor s : sensores){
            if(this.sensores.containsKey(s.getIdentificador())
            && this.sensores.get(s.getIdentificador()) != s)
                throw new MismoIdException(this.sensores.get(s.getIdentificador()), s);
            else{
                s.setFechaImplementación(LocalDate.now());
                this.sensores.put(s.getIdentificador(), s);
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
            throw new MismoIdException(sensores.get(s.getIdentificador()), s);

        s.setFechaImplementación(LocalDate.now());
        sensores.put(s.getIdentificador(), s);
        return true;
    }

    public Sensor getSensor(String identificador){
        return this.sensores.get(identificador);
    }

    public LocalDate getFechaInstalacion(Sensor s) {
        if(this.sensores.containsKey(s.getIdentificador()))
            return this.sensores.get(s.getIdentificador()).getFechaImplementacion();
        else
            return null;
    }

    public LocalDate getFechaInstalacion(String sensorId) {
        if(this.sensores.containsKey(sensorId))
            return this.sensores.get(sensorId).getFechaImplementacion();
        else
            return null;
    }

    public void lecturaManual(){ this.lecturaManual(this.sensores.size()); }

    public void lecturaManual(int numLecturas){
        int lecturasCompletadas = 0;
        for(Sensor sensor : sensores.values()){
            if(this.lecturaPuntual(sensor) == true){
                lecturasCompletadas++;
            }

            if(lecturasCompletadas > numLecturas)
                break;
        }

        if(lecturasCompletadas > 0){
            this.fechaUltimaLectura = LocalDateTime.now();
            if(this.periodoLecturaAutomatica != null)
                this.fechaProximaLecturaAutomatica = addDates(fechaUltimaLectura, periodoLecturaAutomatica);
        }
    }

    public boolean lecturaPuntual(Sensor sensor){
        try{
            sensor.realizarMedida();
        } catch (CalibracionCaducadaException | MedidaFueraRangoException e){
            if(sensor.estaCalibrado() == true){
                sensor.setDescalibrado();
                this.alertas.add(e);
                this.sensoresDetenidos.add(sensor);
            } else if (sensor.estaCalibrado() == false && this.sensoresDetenidos.contains(sensor) == false){
                // En caso de que nunca se calibro
                this.alertas.add(e);
                this.sensoresDetenidos.add(sensor);
            }

            return false;
        }  catch (CambioBruscoLecturaException e){
            //Puede continuar como lectura completada
            this.alertas.add(e);
        }

        return true;
    }

    public boolean lecturaAutomatica(){
        if(fechaProximaLecturaAutomatica == null)
            return false;

        if(fechaProximaLecturaAutomatica.isAfter(LocalDateTime.now()) == false)
            lecturaManual(this.numLecturaAutomaticaMaxima);

        return true;
    }

    public void printEstacionMeteorologica(){
        System.out.println(this);
        System.out.println("------------------------------------------------");
        System.out.println("Sensores instalados: " + this.sensores.size());
        System.out.println("Última lectura: ");
        for(Sensor s : this.sensores.values()){
            double min = s.getProcesador().getLecturaMinima();
            double max = s.getProcesador().getLecturaMaxima();
            double avg = s.getProcesador().getMediaHistorica();

            //insertar el historial entero pero formateando los datos para que tengan 2 decimales y solo se muestre el valor de lectura y no la fecha
            String historial = "[";
            for(Medida medida : s.getProcesador().getHistorial()){
                historial = historial.concat(formatDouble(medida.getValorMedido()) + ", ");
            }
            // le quito el ", " extra que se le añadio en el bucle anterior
            historial = historial.substring(0, historial.length()-2);
            historial = historial.concat("]");

            String conversor = (s.getProcesador().convierteUnidades()) ? "con conversor a " + s.getProcesador().getUnidadAConvertir().toString() : "";


            System.out.println(s.getIdentificador() + " (" + s.getUnidadLectura() + ") " + conversor + ": " + historial +
                " --" + " MIN: " + formatDouble(min) + " MAX: " + formatDouble(max) + " AVG: " + formatDouble(avg));
        }
    }

    public boolean calibrarSensor(Sensor sensor, double nuevoOffset, int diasDuracionCalibracion){
        if(this.sensores.containsKey(sensor.getIdentificador()) == false)
            return false;

        sensor.calibrar(diasDuracionCalibracion, nuevoOffset);
        if(this.sensoresDetenidos.contains(sensor) == true){
            this.sensoresDetenidos.remove(sensor);
            this.lecturaPuntual(sensor);
        }
        return true;
    }

    public boolean calibrarSensor(Sensor sensor, double nuevoOffset){
        return this.calibrarSensor(sensor, nuevoOffset, duracionDiasCalibracionPorDefecto);
    }

    @Override
    public String toString(){
        String titulo = "Estación Meteorológica: " + nombre + "\nUbicación: " + latitud + ", " + longitud + "\n";
        String separador = "--------------------------------------------------------------------------------\n";
        String comienzoSensores = "Sensores instalados: " + this.sensores.size() + "\nÚltima lectura: " + this.fechaUltimaLectura + "\n";

        String stringSensores = "";

        for(Sensor sensor : this.sensores.values()){
            if(sensor.estaCalibrado() == false)
                continue;

            stringSensores += sensor.toString() + "\n";
        }

        String tituloAlertas = "\nAlertas activas: " + this.alertas.size();
        String alertas = "";

        for(Exception exception : this.alertas){
            alertas += exception.getMessage() + "\n";
        }

        return titulo + separador + comienzoSensores + stringSensores + tituloAlertas + alertas;
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