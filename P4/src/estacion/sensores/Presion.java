package estacion.sensores;

import estacion.estrategiasMedicion.EstrategiaMedicion;
import estacion.unidadesLectura.MPresionAtmosferica;

/**
 * Sensores que miden la presión
 */
public class Presion extends Sensor {
    /** Próximo ID a asignar */
    private static int nextId=1;

    /** Medida por defecto */
    private static final MPresionAtmosferica medidaPorDefecto = MPresionAtmosferica.hPa;

    /**
     * Constructor sensor de presión
     * @param medidaEmpleada medida empleada
     */
    public Presion(MPresionAtmosferica medidaEmpleada){
        super("PRES-" + String.format("%04d", nextId++), medidaEmpleada);
    }

    /**
     * Constructor sensor de presión con estrategia especial
     * @param estrategiaMedicion
     */
    public Presion(EstrategiaMedicion estrategiaMedicion){
        this(medidaPorDefecto, estrategiaMedicion);
    }

    /**
     * Constructor sensor de presión con medida empelada especial y estrategia de medicion especial
     * @param medidaEmpleada medida empleada
     * @param estrategiaMedicion estrategia medicion
     */
    public Presion(MPresionAtmosferica medidaEmpleada, EstrategiaMedicion estrategiaMedicion){
        super("PRES-" + String.format("%04d", nextId++), medidaEmpleada, estrategiaMedicion);
    }

    
    
}
