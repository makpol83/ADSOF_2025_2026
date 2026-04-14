package estacion.sensores;

import estacion.estrategiasMedicion.EstrategiaMedicion;
import estacion.unidadesLectura.MTemperatura;
import estacion.unidadesLectura.conversores.Conversor;
import java.util.Collection;

/**
 * Sensores que miden temperatura
 */
public class Temperatura extends Sensor{
    /** Próximo ID a asignar */
    private static int nextId=1;
    /** Medida por defecto */
    private static final MTemperatura medidaPorDefecto = MTemperatura.Celsius;

    /**
     * Constructor sensor de temperatura
     * @param medidaEmpleada medida empleada
     */
    public Temperatura(MTemperatura medidaEmpleada){
        super("TEMP-" + String.format("%04d", nextId++), medidaEmpleada);
    }

    /**
     * Constructor sensor de temperatura
     * @param estrategiaMedicion estrategia empleada en las lecturas
     */
    public Temperatura(EstrategiaMedicion estrategiaMedicion){
        this(medidaPorDefecto, estrategiaMedicion);
    }

    /**
     * Constructor sensor de temperatura
     * @param medidaEmpleada medida empleada
     * @param estrategiaMedicion estrategia empleada en las lecturas
     */
    public Temperatura(MTemperatura medidaEmpleada, EstrategiaMedicion estrategiaMedicion){
        super("TEMP-" + String.format("%04d", nextId++), medidaEmpleada, estrategiaMedicion);
    }
    
    /**
     * Constructor sensor de temperatura
     * @param medidaEmpleada medida empleada
     * @param estrategiaMedicion estrategia empleada en las lecturas
     * @param duracionCalibracionDias periodo en dias hasta la proxima descalibración automática
     */
    public Temperatura(MTemperatura medidaEmpleada, EstrategiaMedicion estrategiaMedicion, int duracionCalibracionDias){
        this(medidaEmpleada, estrategiaMedicion, null, duracionCalibracionDias);
    }

    /**
     * Constructor sensor de temperatura
     * @param medidaEmpleada medida empleada
     * @param estrategiaMedicion estrategia empleada en las lecturas
     * @param conversores coleccion de conversores (ordenados) con los que inicializar el sensor. Si alguno de los sensores es incompatible,
     * se inicializará el sensor sin conversores.
     * @param duracionCalibracionDias periodo en dias hasta la proxima descalibración automática
     */
    public Temperatura(MTemperatura medidaEmpleada, EstrategiaMedicion estrategiaMedicion, Collection<Conversor> conversores, int duracionCalibracionDias){
        super("TEMP-" + String.format("%04d", nextId++), medidaEmpleada, estrategiaMedicion, conversores, duracionCalibracionDias);
    }
    
}
