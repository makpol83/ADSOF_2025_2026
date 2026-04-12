package estacion.unidadesLectura.conversores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.time.LocalDateTime;

import estacion.sensores.Medicion;
import estacion.sensores.Medida;
import estacion.unidadesLectura.UnidadLectura;

public class Procesador {
    /** Unidad inicial sobre la que procesar los datos y convertirlos a otras unidades si es posible */
    private final UnidadLectura variableMedida;
    /** Lista de conversores sobre la que se realizará la conversión de unidades. Se asume que los conversores vienen en un orden correcto. Ejemplo [(hPa -> Pa), (Pa -> mbar)] */
    private final List<Conversor> conversores;
    private Medicion historial;


    public Procesador(UnidadLectura variableMedida){
        this(variableMedida, null);
    }
    public Procesador(UnidadLectura variableMedida, List<Conversor> conversores){
        this.variableMedida = variableMedida;
        this.historial = new Medicion();
        this.conversores = new ArrayList<>();
        this.conversores.add(ConversorIdentidad.getConversor());
        if(conversores != null)
            this.conversores.addAll(conversores);


        UnidadLectura unidadActual = this.variableMedida;
        UnidadLectura unidadSiguiente;
        for(Conversor c : this.conversores){
            if(c != ConversorIdentidad.getConversor()){
                unidadSiguiente = c.getUnidadOrigen();
                if(unidadActual.equals(unidadSiguiente) == false)
                    //cambiar por excepcion nuestra
                    throw new RuntimeException();
                else
                    unidadActual = c.getUnidadDestino();
            }
        }
    }

    public boolean addConversor(Conversor conversorUnidades){
        UnidadLectura unidadFinal = this.conversores.get(this.conversores.size()-1).getUnidadDestino();

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
            return true;
        }
        else
            return false;
    }

    public void procesarDato(Medida medida){
        this.historial.añadirMedida(medida);
    }

    public UnidadLectura getUnidadAConvertir(){
        //En caso de que sea la identidad
        if(convierteUnidades() == false){
            return this.variableMedida;
        }
        return this.conversores.get(this.conversores.size()-1).getUnidadDestino();
    }

    public boolean convierteUnidades(){
        //si es igual a 1, solo tiene el conversor identidad
        return this.conversores.size() > 1;
    }

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

    public Collection<Medida> getHistorial(){
        return this.historial.values();
    }

    public Medida getMedida(int i){
        return this.historial.getMedida(i);
    }

    public int getNumMedidas(){ return this.historial.getNumMedidas(); }

    public double getMediaHistorica(){
        if(this.historial.getNumMedidas() == 0)
            return 0;
        
        return this.historial.getMediaHistorica();
    }


}
