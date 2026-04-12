package estacion.sensores;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import estacion.estrategiasMedicion.EstrategiaMedicion;
import estacion.estrategiasMedicion.MedicionAleatoria;
import estacion.exceptions.CalibracionCaducadaException;
import estacion.exceptions.CambioBruscoLecturaException;
import estacion.exceptions.MedidaFueraRangoException;
import estacion.unidadesLectura.UnidadLectura;
import estacion.unidadesLectura.conversores.Conversor;
import estacion.unidadesLectura.conversores.Procesador;
import estacion.utils.Formatter;

/**
 * Describe el funcionamiento de un sensor, un sensor toma medidas mediante una estrategia de medicion
 * y las anota mediante el procesador. Un sensor tiene como estrategia por defecto una medicion aleatoria y el porcentaje
 * de cambio brusco por defecto es del 50%. Un sensor se identifica por un String de la forma {TIPOSENSOR}-{Número 4 dígitos}.
 * 
 * Al implantar el sensor en una estación se debe fijar su fecha de implementacion con setFechaImplementacion.
 * 
 * Un sensor tiene dos estados: calibrado, puede realizar medidas, descalibrado, no debe realizar medidas.
 * Un sensor se considera descalibrado si ha realizado una medida fuera de rango, si nunca se ha calibrado o si ha caducado
 * la calibración.
 */
public abstract class Sensor{
    /** Estrategia por defecto de medicion */
    private static final EstrategiaMedicion estrategiaPorDefecto = new MedicionAleatoria(0.2);
    /** Porcentaje de cambio brusco */
    private double porcentajeCambioBrusco = 0.5;
    /** Identificador del sensor */
    private String identificador;
    /** Offset actual del sensor */
    private double offset;
    /** Fecha de implementación, inicializado a null */
    private LocalDate fechaImplementacion;
    /** True si está calibrado o no se ha actualizado, false si no está calibrado */
    private boolean calibrado;
    /** Fecha de caducidad del sensor */
    private LocalDateTime fechaCaducidad;
    /** Variable medida por el sensor, no la que se muestra tras procesar */
    private UnidadLectura variableMedida;
    /** Estrategia actual del sensor */
    private EstrategiaMedicion estrategia;
    /** Procesador de las medidas */
    private Procesador procesador;

    /**
     * Constructor sensor completo
     * @param identificador Identificador del sensor: {TIPOSENSOR}-{Número 4 dígitos}
     * @param variableMedida Variable medida por el sensor
     * @param estrategia Estrategia a emplear
     * @param conversores Conversores del sensor, puede ser null
     */
    public Sensor(String identificador, UnidadLectura variableMedida, EstrategiaMedicion estrategia, Collection<Conversor> conversores){
        this.identificador = identificador;
        this.fechaImplementacion = null;
        this.estrategia = estrategia;
        this.fechaCaducidad = null;
        this.variableMedida = variableMedida;
        if(conversores != null)
            this.procesador = new Procesador(variableMedida, List.copyOf(conversores));
        else
            this.procesador = new Procesador(variableMedida);
    }

    /**
     * Constructor sensor sin estrategia especial
     * @param identificador Identificador del sensor: {TIPOSENSOR}-{Número 4 dígitos}
     * @param variableMedida Variable medida por el sensor
     * @param conversores Conversores del sensor, puede ser null
     */
    public Sensor(String identificador, UnidadLectura variableMedida, Collection<Conversor> conversores){
        this(identificador, variableMedida, estrategiaPorDefecto, conversores);
    }

    /**
     * Constructor sensor sin conversores especiales
     * @param identificador Identificador del sensor: {TIPOSENSOR}-{Número 4 dígitos}
     * @param variableMedida Variable medida por el sensor
     * @param estrategia Estrategia a emplear
     */
    public Sensor(String identificador, UnidadLectura variableMedida, EstrategiaMedicion estrategia){
        this(identificador, variableMedida, estrategia, null);
    }

    /**
     * Constructor sensor sin conversores especiales ni estrategia especial
     * @param identificador Identificador del sensor: {TIPOSENSOR}-{Número 4 dígitos}
     * @param variableMedida Variable medida por el sensor
     */
    public Sensor(String identificador, UnidadLectura variableMedida){
        this(identificador, variableMedida, estrategiaPorDefecto, null);
    }

    /**
     * Calibra el sensor a un nuevo offset y le fija la duracion de la calibracion en dias
     * @param duracionCalibracionDias numero de dias que dura la calibracion
     * @param nuevoOffset nuevo offset a fijar
     */
    public void calibrar(int duracionCalibracionDias, double nuevoOffset){
        this.calibrado = true;
        this.fechaCaducidad = LocalDateTime.now().plusDays(duracionCalibracionDias);
        this.offset = nuevoOffset;
    }

    /**
     * Comprueba si ha caducado el sensor
     * @throws CalibracionCaducadaException Si ha caducado el sensor
     */
    private void comprobarCalibracion() throws CalibracionCaducadaException{
        if(this.calibrado == false || this.fechaCaducidad == null)
            throw new CalibracionCaducadaException(this);

        if(LocalDateTime.now().isAfter(this.fechaCaducidad) == true)
            throw new CalibracionCaducadaException(this);
    }

    /**
     * Realiza una medida según la estrategia fijada en el sensor.
     * @throws CalibracionCaducadaException Si ha caducado el sensor
     * @throws CambioBruscoLecturaException Si se ha detectado un cambio brusco en la lectura respecto al último dato
     * @throws MedidaFueraRangoException Si el valor medido está fuera de rango
     */
    public void realizarMedida() throws CalibracionCaducadaException, CambioBruscoLecturaException, MedidaFueraRangoException{
        this.comprobarCalibracion();

        double valorUltimaLectura;
        LocalDateTime fechaUltimaLectura;

        //Usamos estrategia
        if(this.procesador.getNumMedidas() == 0){
                EstrategiaMedicion estrategiaAleatoria = new MedicionAleatoria(0);
                valorUltimaLectura = estrategiaAleatoria.medir(this);
        } else {
            valorUltimaLectura = this.estrategia.medir(this);
        }

        //Ajustamos offset
        valorUltimaLectura -= this.offset;
        
        //Procesamos el dato
        fechaUltimaLectura = LocalDateTime.now();
        fechaUltimaLectura = fechaUltimaLectura.withNano(0);

        //Comprobamos si la medida esta fuera de rango o tiene un cambio brusco
        Medida penultimaMedida = this.getUltimaMedida();
        Medida ultimaMedida = new Medida(valorUltimaLectura, fechaUltimaLectura);

        if(this.getUnidadConvertida().esRangoValido(ultimaMedida.getValorMedido()) == false)
            throw new MedidaFueraRangoException(this, ultimaMedida);

        //A partir de aquí se puede registrar
        procesador.procesarDato(ultimaMedida);
        
        if(penultimaMedida != null){
            if(Math.abs((penultimaMedida.getValorMedido() - ultimaMedida.getValorMedido())/penultimaMedida.getValorMedido()) > this.porcentajeCambioBrusco)
                throw new CambioBruscoLecturaException(this, penultimaMedida, ultimaMedida);
        }
    }

    /**
     * Getter unidad de lectura
     * @return UnidadLectura
     */
    public UnidadLectura getUnidadLectura(){
        return this.variableMedida;
    }

    /**
     * Getter unidad final a mostrar
     * @return UnidadLectura
     */
    public UnidadLectura getUnidadConvertida(){
        return this.procesador.getUnidadAConvertir();
    }

    /**
     * Getter identificador
     * @return String
     */
    public String getIdentificador(){return this.identificador;}

    /**
     * Getter media historica de las medidas, 0 si no hay ninguna
     * @return double 
     */
    public double getMediaHistorica(){ return this.procesador.getMediaHistorica(); }

    /**
     * Get ultima medida, null si no hay
     * @return Medida
     */
    public Medida getUltimaMedida(){ return this.procesador.getMedida(this.procesador.getNumMedidas() - 1); }

    /**
     * Getter valor lectura minimo posible
     * @return double
     */
    public double getValorLecturaMinimo(){ return this.procesador.getUnidadAConvertir().getValorMinimo(); }

    /**
     * Getter valor lectura maximo posible
     * @return double
     */
    public double getValorLecturaMaximo(){ return this.procesador.getUnidadAConvertir().getValorMaximo(); }

    /**
     * Getter procesador
     * @return Procesador
     */
    public Procesador getProcesador(){ return this.procesador; }

    /**
     * Getter fecha de caducidad, null si no se ha calibrado nunca
     * @return LocalDateTime
     */
    public LocalDateTime getFechaCaducidad(){ return this.fechaCaducidad; }

    /**
     * Getter estrategia medicion
     * @return EstrategiaMedicion
     */
    public EstrategiaMedicion getEstrategiaMedicion(){return this.estrategia;}

    /**
     * Getter fecha de implementación, null si no se ha implementado
     * @return LocalDate
     */
    public LocalDate getFechaImplementacion(){ return this.fechaImplementacion; }

    /**
     * Getter medida en la posición i de todas las medidas realizadas por el sensor
     * @param i indice
     * @return Medida, null si no existe una Medida en ese indice o no es valido i
     */
    public Medida getMedida(int i){ return this.procesador.getMedida(i);}

    /**
     * Comprueba si ya se comprobó anteriormente que estaba caducado el sensor
     * @return true si está calibrado, false si no
     */
    public boolean estaCalibrado(){ return this.calibrado;}

    /**
     * Configura un nuevo porcentaje de cambio brusco
     * @param nuevoPorcentaje rango 0 a 1
     * @return true si fijado, false si no
     */
    public boolean setPorcentajeCambioBrusco(double nuevoPorcentaje){ 
        if(nuevoPorcentaje < 0 || nuevoPorcentaje > 1)
            return false;

        this.porcentajeCambioBrusco = nuevoPorcentaje;
        return true;
    }

    /**
     * Configura la fecha de implementación.
     * @param fechaImplementacion LocalDate
     */
    public void setFechaImplementación(LocalDate fechaImplementacion){
        this.fechaImplementacion = fechaImplementacion;
    }

    /**
     * Configura el sensor como descalibrado para futuras comprobaciones.
     */
    public void setDescalibrado(){ this.calibrado = false; }

    /**
     * Método que fuerza la entrada de una medida, usado mayormente para testear
     * @param medida medida a añadir
     */
    public void forzarMedida(Medida medida){
        this.procesador.procesarDato(medida);
    }

    public String stringSensor(){
        double min = this.getProcesador().getLecturaMinima();
        double max = this.getProcesador().getLecturaMaxima();
        double avg = this.getProcesador().getMediaHistorica();

        //insertar el historial entero pero formateando los datos para que tengan 2 decimales y solo se muestre el valor de lectura y no la fecha
        String historial = "[";
        for(Medida medida : this.getProcesador().getHistorial()){
            historial = historial.concat(Formatter.formatDouble(medida.getValorMedido()) + ", ");
        }
        if(this.getProcesador().getHistorial().size() > 0){
            // le quito el ", " extra que se le añadio en el bucle anterior si hay al menos una medida
            historial = historial.substring(0, historial.length()-2);
        }
        //Concatenamos el ]
        historial = historial.concat("]");

        String conversor = (this.getProcesador().convierteUnidades()) ? "con conversor a " + this.getProcesador().getUnidadAConvertir().toString() : "";

        return this.getIdentificador() + " (" + this.getUnidadLectura() + ") " + conversor + ": " + historial +
            " --" + " MIN: " + Formatter.formatDouble(min) + " MAX: " + Formatter.formatDouble(max) + " AVG: " + Formatter.formatDouble(avg);
    }

    @Override
    public String toString(){
        String fechaUltLectura="";
        String valorUltLectura="";
        Medida ultimaMedida = this.getUltimaMedida();

        if(ultimaMedida.getFechaMedida() == null){
            fechaUltLectura = "No hay lecturas.";
            valorUltLectura = "...";
        }
        else{
            fechaUltLectura = ultimaMedida.getFechaMedida().toString();
            valorUltLectura = String.format("%.2f%s", ultimaMedida.getValorMedido(), this.getUnidadLectura());
        }

        return this.identificador + " (desde: " + this.fechaImplementacion + "): Sensor " + this.getClass().getSimpleName() +
        " (" + valorUltLectura + ") última lectura: " + fechaUltLectura;
    }
}
