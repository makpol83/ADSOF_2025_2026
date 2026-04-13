package estacion.unidadesLectura.conversores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import estacion.exceptions.UnidadesIncorrectasException;
import estacion.exceptions.ConversorIncompatibleException;
import estacion.sensores.Medicion;
import estacion.sensores.Medida;
import estacion.unidadesLectura.UnidadMedida;

/**
 * Clase encargada de transformar datos que recibe del sensor en las unidades de medida de salida,
 * también almacena las medidas del sensor mediante historial. Tiene una lista de conversores en
 * orden.
 */
public class Procesador {
    /** Unidad inicial sobre la que procesar los datos y convertirlos a otras unidades si es posible */
    private final UnidadMedida variableMedida;
    /** Lista de conversores sobre la que se realizará la conversión de unidades. Se asume que los conversores vienen en un orden correcto. Ejemplo [(hPa -> Pa), (Pa -> mbar)] */
    private final List<Conversor> conversores;
    /** Historial de medidas del sensor, sólo debe tener medidas correctas. */
    private Medicion historial;

    /**
     * Constructor básico
     * @param variableMedida Variable original que mide el sensor
     */
    public Procesador(UnidadMedida variableMedida){
        this.variableMedida = variableMedida;
        this.historial = new Medicion();
        this.conversores = new ArrayList<>();
        this.conversores.add(ConversorIdentidad.getConversor());
    }

    /**
     * Constructor con lista de conversores
     * @param variableMedida Variable original que mide el sensor
     * @param conversores Lista de conversores en el orden correcto
     * @throws UnidadesIncorrectasException Si un conversor genera problemas entre unidades de medida
     */
    public Procesador(UnidadMedida variableMedida, List<Conversor> conversores)
    throws UnidadesIncorrectasException
    {
        this(variableMedida);

        UnidadMedida unidadActual = this.variableMedida;
        UnidadMedida unidadSiguiente;
        for(Conversor c : conversores){
            unidadSiguiente = c.getUnidadOrigen();
            if(unidadActual.equals(unidadSiguiente) == false)
                throw new ConversorIncompatibleException(c, unidadActual);
            else{
                unidadActual = c.getUnidadDestino();
                this.conversores.add(c);
            }
        }
    }

    /**
     * Añade un conversor al procesador
     * @param conversorUnidades a añadir
     * @throws UnidadesIncorrectasException Si el conversor genera problemas entre unidades de medida
     */
    public void addConversor(Conversor conversorUnidades)
    throws UnidadesIncorrectasException
    {
        UnidadMedida unidadFinal = this.conversores.get(this.conversores.size()-1).getUnidadDestino();

        //si unidadFinal == null => solo esta el ConversorIdentidad
        if(unidadFinal == null){
            unidadFinal = this.variableMedida;
        }

        if(unidadFinal.equals(conversorUnidades.getUnidadOrigen())){
            this.conversores.add(conversorUnidades);

            Medicion nuevoHistorial = new Medicion();

            //realiza la conversion de unidad del nuevo conversor a todos los datos del historial
            for(Medida medida : this.historial.values()){ 
                double valorMedido = conversorUnidades.convertirUnidades(medida.getValorMedido());
                Medida datoModificado = new Medida(valorMedido, medida.getFechaMedida());
                
                nuevoHistorial.añadirMedida(datoModificado);
            }

            this.historial = nuevoHistorial;
            return;
        }
        else
            throw new UnidadesIncorrectasException(this);
    }

    /**
     * Añade un dato al historial si está en rango válido
     * @param medida Medida a introducir
     * @return true si se ha introducido, false si no está en rango o algo ha fallado
     */
    public boolean procesarDato(Medida medida){
        if(this.variableMedida.esRangoValido(medida.getValorMedido()) == true){
            return this.historial.añadirMedida(medida);
        }

        return false;
    }

    /**
     * Consigue la unidad final a la que se convierte la unidad medida por el sensor
     * @return UnidadMedida
     */
    public UnidadMedida getUnidadAConvertir(){
        //En caso de que sea la identidad
        if(convierteUnidades() == false){
            return this.variableMedida;
        }
        return this.conversores.get(this.conversores.size()-1).getUnidadDestino();
    }

    /**
     * Comprueba si este Procesador transforma a una unidad de lectura distinta a la inicial
     * @return True si transforma a otra unidad o False.
     */
    public boolean convierteUnidades(){
        //si es igual a 1, solo tiene el conversor identidad
        return this.conversores.size() > 1;
    }

    /**
     * Consigue la lectura con el valor medido más bajo
     * @return double o 0 si no hay medidas
     */
    public double getLecturaMinima(){
        if(this.historial.getNumMedidas() == 0)
            return 0;

        double min = Double.MAX_VALUE;
        for(Medida medida : this.historial.values()){
            if(medida.getValorMedido() < min)
                min = medida.getValorMedido();
        }
        return min;
    }

    /**
     * Consigue la lectura con el valor medido más alto
     * @return double o 0 si no hay medidas
     */
    public double getLecturaMaxima(){
        if(this.historial.getNumMedidas() == 0)
            return 0;

        double max = -Double.MAX_VALUE;
        for(Medida medida : this.historial.values()){
            if(medida.getValorMedido() > max)
                max = medida.getValorMedido();
        }
        return max;
    }

    /**
     * Consigue el historial de medidas como colleccion inmutable
     * @return Collection Medida, inmutable
     */
    public Collection<Medida> getHistorial(){
        return List.copyOf(this.historial.values());
    }

    /**
     * Consigue la medida en la posición i
     * @param i índice
     * @return Medida o null si no existe esa posición
     */
    public Medida getMedida(int i){
        return this.historial.getMedida(i);
    }

    /**
     * Consigue el número de medidas almacenadas
     * @return int >= 0
     */
    public int getNumMedidas(){ return this.historial.getNumMedidas(); }

    /**
     * Obtiene la media histórica de las medidas del procesador
     * @return double o 0 si no hay medidas.
     */
    public double getMediaHistorica(){
        if(this.historial.getNumMedidas() == 0)
            return 0;
        
        return this.historial.getMediaHistorica();
    }


}
