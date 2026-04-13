package estacion.sensores;

import java.util.Collection;

import estacion.estrategiasMedicion.EstrategiaMedicion;
import estacion.unidadesLectura.MPresionAtmosferica;
import estacion.unidadesLectura.conversores.Conversor;

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
     * @param estrategiaMedicion Estrategia a fijar
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

    /**
     * Constructor sensor de presión
     * @param medidaEmpleada medida empleada
     * @param estrategiaMedicion estrategia empleada
     * @param duracionCalibracion periodo en dias hasta la proxima descalibración automática
     */
    public Presion(MPresionAtmosferica medidaEmpleada, EstrategiaMedicion estrategiaMedicion, int duracionCalibracionDias){
        this(medidaEmpleada, estrategiaMedicion, null, duracionCalibracionDias);
    }

    /**
     * Constructor sensor de presión
     * @param medidaEmpleada medida empleada
     * @param estrategiaMedicion estrategia empleada
     * @param conversores coleccion de conversores (ordenados) con los que inicializar el sensor. Si alguno de los sensores es incompatible,
     * se inicializará el sensor sin conversores.
     * @param duracionCalibracion periodo en dias hasta la proxima descalibración automática
     */
    public Presion(MPresionAtmosferica medidaEmpleada, EstrategiaMedicion estrategiaMedicion, Collection<Conversor> conversores, int duracionCalibracionDias){
        super("PRES-" + String.format("%04d", nextId++), medidaEmpleada, estrategiaMedicion, conversores, duracionCalibracionDias);
    }

    
    
}
