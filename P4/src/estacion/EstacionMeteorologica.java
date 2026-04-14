package estacion;

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
import estacion.exceptions.SensorException;
import estacion.formateadores.IDocumento;
import estacion.formateadores.SeccionSecundaria;
import estacion.sensores.Sensor;
import estacion.unidadesLectura.UnidadMedida;
import estacion.utils.DateUtils;

/**
 * Estacion meteorológica que almacena los sensores, los calibra y los usa.
 */
public class EstacionMeteorologica implements IDocumento{
    /** Nombre */
    private String nombre;
    /** Longitud en coordenadas*/
    private double longitud;
    /** Latitud en coordenadas*/
    private double latitud;
    /** Mapa de sensores, relaciona Identificador con el sensor */
    private Map<String,Sensor> sensores;
    /** Guarda la fecha de última lectura automática */
    private LocalDateTime fechaUltimaLectura;
    /** Guarda la siguiente fecha a la que se va a hacer una lectura automática */
    private LocalDateTime fechaProximaLecturaAutomatica;
    /** Guarda el periodo de lectura automática */
    private LocalDateTime periodoLecturaAutomatica;
    /** Guarda el número de lecturas que hace en la lectura automática */
    private int numLecturaAutomaticaMaxima;
    /** Guarda las excepciones de los sensores */
    private List<SensorException> alertas;
    /** Guarda los sensores que no han podido realizar su medida dado que han lanzado un SensorException */
    private List<Sensor> sensoresDetenidos;

    /**
     * Constructor estacion sin sensores
     * @param nombre Nombre
     * @param longitud Longitud coordenadas
     * @param latitud Latitud coordenadas
     */
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

    /**
     * Constructor estación con sensores
     * @param nombre Nombre
     * @param longitud Longitud coordenadas
     * @param latitud Latitud coordenadas
     * @param sensores Sensores a añadir
     * @throws MismoIdException Si hay al menos dos sensores que comparten identificador
     */
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

    /**
     * Getter sensores de la estación
     * @return List Sensor, inmutable
     */
    public List<Sensor> getSensores(){ return List.copyOf(this.sensores.values()); }

    /**
     * Getter sensores de un tipo concreto pertenecientes a la estacion. El tipo debera ser un string igual al mostrado en su ID
     * Por ejemplo, para sensores de temperatura el tipo debera ser "TEMP" (no es case sensitive)
     * @param tipo tipo del sensor
     * @return lista de sensores del tipo especificado
     */
    public List<Sensor> getSensores(String tipo){
        List<Sensor> sensores = new ArrayList<>();
        for(Sensor sensor : getSensores()){
            String tipoDelSensor = sensor.getIdentificador().substring(0, tipo.length());
            if(tipoDelSensor.equalsIgnoreCase(tipo))
                sensores.add(sensor);
        }
        return sensores;
    }

    /**
     * Consigue un sensor por identificador
     * @param identificador Identificador
     * @return Sensor o null si no está
     */
    public Sensor getSensor(String identificador){
        return this.sensores.get(identificador);
    }

    /**
     * Getter de los sensores por variable medida por el sensor
     * @param variableMedida Variable medida por el sensor
     * @return List Sensor, inmutable
     */
    public List<Sensor> getSensores(UnidadMedida variableMedida){
        List<Sensor> sensores = new ArrayList<>();
        for(Sensor sensor : this.sensores.values()){
            if(sensor.getUnidadMedida().equals(variableMedida) == true){
                sensores.add(sensor);
            }
        }

        return List.copyOf(sensores);
    }

    /**
     * Configura la lectura automática de la estación
     * @param periodoLecturaAutomatica periodo a establecer
     * @param numLecturasMaximas Número de lecturas a realizar
     * @return true si se ha configurado, false si algo ha fallado
     */
    public boolean configurarLecturaAutomatica(LocalDateTime periodoLecturaAutomatica, int numLecturasMaximas){
        if(numLecturasMaximas < 1) return false;

        this.periodoLecturaAutomatica = periodoLecturaAutomatica;
        this.numLecturaAutomaticaMaxima = numLecturasMaximas;

        if(periodoLecturaAutomatica != null){
            if(this.fechaUltimaLectura == null){
                this.fechaProximaLecturaAutomatica = DateUtils.addDates(LocalDateTime.now(), periodoLecturaAutomatica);
            }
            else{
                this.fechaProximaLecturaAutomatica = DateUtils.addDates(fechaUltimaLectura, periodoLecturaAutomatica);
            }
        }

        //solo va a hacer el intento, no necesariamente se realiza la lectura
        this.lecturaAutomatica();

        return true;
    }

    /**
     * Añade un sensor a la estación
     * @param s sensor a añadir
     * @throws MismoIdException Si ya existe algún sensor en la estación el identificador de s
     */
    public void añadirSensor(Sensor s) throws MismoIdException {
        if(sensores.containsKey(s.getIdentificador()))
            throw new MismoIdException(sensores.get(s.getIdentificador()), s);

        s.setFechaImplementación(LocalDate.now());
        sensores.put(s.getIdentificador(), s);
    }

    /**
     * Getter fecha de instalación de un sensor
     * @param s sensor
     * @return LocalDate o null si no está el sensor en la estación
     */
    public LocalDate getFechaInstalacion(Sensor s) {
        if(this.sensores.containsKey(s.getIdentificador()))
            return this.sensores.get(s.getIdentificador()).getFechaImplementacion();
        else
            return null;
    }

    /**
     * Getter fecha de instalación de un sensor por identificador
     * @param sensorId Identificador del sensor
     * @return LocalDate o null si no está el sensor en la estación
     */
    public LocalDate getFechaInstalacion(String sensorId) {
        if(this.sensores.containsKey(sensorId))
            return this.sensores.get(sensorId).getFechaImplementacion();
        else
            return null;
    }

    /**
     * Realiza una lectura manual de todos los sensores
     */
    public void lecturaManual(){ this.lecturaManual(this.sensores.size()); }

    /**
     * Realiza una lectura manual para numLecturas
     * @param numLecturas número de sensores que van a medir
     */
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
            this.fechaUltimaLectura = LocalDateTime.now().withNano(0);
            if(this.periodoLecturaAutomatica != null)
                this.fechaProximaLecturaAutomatica = DateUtils.addDates(fechaUltimaLectura, periodoLecturaAutomatica);
        }
    }

    /**
     * Lectura puntual para un sensor
     * @param sensor a medir
     * @return true si se ha realizado, false si algo ha fallado
     */
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

    /**
     * Lectura automática, debe ejecutarse de vez en cuando para que se realice la lectura automática
     * @return true si se ha realizado, false si no ha llegado la fecha de lectura automática
     */
    public boolean lecturaAutomatica(){
        if(fechaProximaLecturaAutomatica == null)
            return false;

        if(fechaProximaLecturaAutomatica.isAfter(LocalDateTime.now()) == false)
            lecturaManual(this.numLecturaAutomaticaMaxima);

        return true;
    }

    /**
     * Imprime por pantalla información de la estación sin incluir las alertas (usado para facilitar la lectura en los tests iniciales)
     * @param printNotCalibrated indicador para imprimir o no los sensores no calibrados (si es true se imprimen los NO calibrados)
     */
    public void shortPrint(boolean printNotCalibrated){
        System.out.println(this);
        System.out.println("------------------------------------------------");
        System.out.println("Sensores instalados: " + this.sensores.size());
        //Sale el carácter ? si se pone tilde en última
        System.out.println("Ultima lectura: " + (this.fechaUltimaLectura == null ? "Sin lecturas" : this.fechaUltimaLectura));
        for(Sensor s : this.sensores.values()){
            if(printNotCalibrated == false){
                //Si no está calibrado lo ignoramos
                if(s.estaCalibrado() == false){
                    continue;
                }
            }

            System.out.println(s.stringSensor());
        }
    }

    /**
     * Imprime por pantalla información de la estación.
     */
    public void print(){

        this.shortPrint(false);

        System.out.println("\nAlertas activas: " + this.alertas.size());

        for(Exception exception : this.alertas){
            System.out.println("- " + exception.getMessage());
        }
        System.out.println();
    }

    /**
     * Calibra un sensor dentro de la estación, elimina las alertas asociadas al sensor
     * @param sensor a calibrar
     * @param nuevoOffset offset a configurar
     * @return true si calibrado, false si no está en la estación
     */
    public boolean calibrarSensor(Sensor sensor, double nuevoOffset){
        if(this.sensores.containsKey(sensor.getIdentificador()) == false)
            return false;

        sensor.calibrar(nuevoOffset);
        if(this.sensoresDetenidos.contains(sensor) == true){
            this.sensoresDetenidos.remove(sensor);
            this.lecturaPuntual(sensor);
        }

        List<SensorException> excepcionesAntiguas = new ArrayList<>();

        for(SensorException e : this.alertas){
            if(e.getSensor().equals(sensor) == true){
                excepcionesAntiguas.add(e);
            }
        }

        this.alertas.removeAll(excepcionesAntiguas);

        return true;
    }
    
    public String getTituloDocumento(){
        return "Estación meteorológica " + this.nombre;
    }
    public String getTituloSeccionPrincipal(){
        return this.nombre;
    }
    public List<String> getParrafosSeccionPrincipal(){
        List<String> parrafos = new ArrayList<>();

        parrafos.add("Ubicación: " + latitud + ", " + longitud);
        parrafos.add("Sensores instalados: " + this.sensores.size());
        parrafos.add("Ultima lectura: " + (this.fechaUltimaLectura == null ? "Sin lecturas" : this.fechaUltimaLectura));

        return parrafos;
    }
    public List<SeccionSecundaria> getSeccionesSecundarias(){
        List<SeccionSecundaria> secciones = new ArrayList<>();

        if(this.sensores.size() != 0){
            String tituloSeccion = "Sensores activos ";
            List<String> parrafosSeccion = new ArrayList<>();

            for(Sensor s : this.sensores.values()){
                parrafosSeccion.add(s.stringSensor());
            }

            secciones.add(new SeccionSecundaria(tituloSeccion, parrafosSeccion));
        }

        if(this.alertas.size() != 0){
            String tituloSeccion = "Alertas actuales ";
            List<String> parrafosSeccion = new ArrayList<>();

            for(SensorException e : this.alertas){
                parrafosSeccion.add(e.getMessage());
            }

            secciones.add(new SeccionSecundaria(tituloSeccion, parrafosSeccion));
        }

        return secciones;
    }

    @Override
    public String toString(){
        return "Estación Meteorológica: " + nombre + "\nUbicación: " + latitud + ", " + longitud;
    }

    
}