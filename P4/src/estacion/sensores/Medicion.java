package estacion.sensores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Almacena las medidas de un sensor
 */
public class Medicion {
    /** Lista de medidas */
    private List<Medida> mediciones;
    
    /** Suma de todas las medidas */
    private double sumaMedidas;
    
    /**
     * Constructor medicion, medicionesAñadir puede estar vacío
     * @param medicionesAñadir Antigua lista de mediciones
     */
    public Medicion(List<Medida> medicionesAñadir){
        this.mediciones = new ArrayList<>();
        this.sumaMedidas = 0;
        for(Medida medida : medicionesAñadir){
            this.añadirMedida(medida);
        }
    }

    /**
     * Constructor por defecto
     */
    public Medicion(){
        this(List.of());
    }

    /**
     * Getter media histórica
     * @return double, 0 si no hay medidas
     */
    public double getMediaHistorica(){ 
        if(this.mediciones.size() == 0)
            return 0;
        return this.sumaMedidas / this.mediciones.size();
    }

    /**
     * Getter número de medidas
     * @return int
     */
    public int getNumMedidas(){ return this.mediciones.size(); }

    /**
     * Getter de la medida en la posición i, devuelve null si i no es válido
     * @param i índice a extraer
     * @return Medida o null
     */
    public Medida getMedida(int i){ 
        try{
            return this.mediciones.get(i);
        } catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    /**
     * Añade una medida a la lista
     * @param medida A añadir
     * @return true si se ha añadido, false si no
     */
    public boolean añadirMedida(Medida medida){
        sumaMedidas += medida.getValorMedido();
        return mediciones.add(medida);
    }

    /**
     * Getter colección de medidas
     * @return Collection Medida, lista inmutable
     */
    public Collection<Medida> values(){ return List.copyOf(this.mediciones);}
}
