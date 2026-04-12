package estacion.sensores;

import estacion.estrategiasMedicion.EstrategiaMedicion;
import estacion.unidadesLectura.MTemperatura;

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
     * @param estrategiaMedicion estrategia empleada
     */
    public Temperatura(EstrategiaMedicion estrategiaMedicion){
        this(medidaPorDefecto, estrategiaMedicion);
    }

    /**
     * Constructor sensor de temperatura
     * @param medidaEmpleada medida empleada
     * @param estrategiaMedicion estrategia empleada
     */
    public Temperatura(MTemperatura medidaEmpleada, EstrategiaMedicion estrategiaMedicion){
        super("TEMP-" + String.format("%04d", nextId++), medidaEmpleada, estrategiaMedicion);
    }
    
}
