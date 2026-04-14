package estacion.sensores;


import java.util.Collection;

import estacion.estrategiasMedicion.EstrategiaMedicion;
import estacion.unidadesLectura.MHumedad;
import estacion.unidadesLectura.conversores.Conversor;

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
     * @param estrategiaMedicion estrategia empleada en las lecturas
     */
    public Humedad(EstrategiaMedicion estrategiaMedicion){
        this(medidaPorDefecto, estrategiaMedicion);
    }

    /**
     * Constructor sensor de humedad
     * @param medidaEmpleada medida empleada
      * @param estrategiaMedicion estrategia empleada en las lecturas
     */
    public Humedad(MHumedad medidaEmpleada, EstrategiaMedicion estrategiaMedicion){
        super("HUM-" + String.format("%04d", nextId++), medidaEmpleada, estrategiaMedicion);
    }

    /**
     * Constructor sensor de humedad
     * @param medidaEmpleada medida empleada
     * @param estrategiaMedicion estrategia empleada en las lecturas
     * @param duracionCalibracionDias periodo en dias hasta la proxima descalibración automática
     */
    public Humedad(MHumedad medidaEmpleada, EstrategiaMedicion estrategiaMedicion, int duracionCalibracionDias){
        this(medidaEmpleada, estrategiaMedicion, null, duracionCalibracionDias);
    }

    /**
     * Constructor sensor de humedad
     * @param medidaEmpleada medida empleada
     * @param estrategiaMedicion estrategia empleada en las lecturas
     * @param conversores coleccion de conversores (ordenados) con los que inicializar el sensor. Si alguno de los sensores es incompatible,
     * se inicializará el sensor sin conversores.
     * @param duracionCalibracionDias periodo en dias hasta la proxima descalibración automática
     */
    public Humedad(MHumedad medidaEmpleada, EstrategiaMedicion estrategiaMedicion, Collection<Conversor> conversores, int duracionCalibracionDias){
        super("HUM-" + String.format("%04d", nextId++), medidaEmpleada, estrategiaMedicion, conversores, duracionCalibracionDias);
    }
    
}
