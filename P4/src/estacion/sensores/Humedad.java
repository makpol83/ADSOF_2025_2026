package estacion.sensores;


import estacion.estrategiasMedicion.EstrategiaMedicion;
import estacion.unidadesLectura.MHumedad;

/**
 * Sensores que miden la humedad.
 */
public class Humedad extends Sensor {
    /** Próxima ID a asignar al identificador */
    private static int nextId=1;

    /** Medida por defecto */
    private static final MHumedad medidaPorDefecto = MHumedad.Porcentaje;

    /**
     * Constructor sensor de humedad
     * @param medidaEmpleada medida empleada
     */
    public Humedad(MHumedad medidaEmpleada){
        super("HUM-" + String.format("%04d", nextId++), medidaEmpleada);
    }

    /**
     * Constructor sensor de humedad
     * @param estrategiaMedicion estrategia de medicion
     */
    public Humedad(EstrategiaMedicion estrategiaMedicion){
        this(medidaPorDefecto, estrategiaMedicion);
    }

    /**
     * Constructor sensor de humedad
     * @param medidaEmpleada medida empleada
      * @param estrategiaMedicion estrategia medicion
     */
    public Humedad(MHumedad medidaEmpleada, EstrategiaMedicion estrategiaMedicion){
        super("HUM-" + String.format("%04d", nextId++), medidaEmpleada, estrategiaMedicion);
    }
    
}
