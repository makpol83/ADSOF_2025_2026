package estacion.unidadeslectura.conversores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.time.LocalDateTime;

import estacion.utils.Tuple;
import estacion.unidadeslectura.UnidadLectura;

public class Procesador {
    /** Unidad inicial sobre la que procesar los datos y convertirlos a otras unidades si es posible */
    private final UnidadLectura variableMedida;
    /** Lista de conversores sobre la que se realizará la conversión de unidades. Se asume que los conversores vienen en un orden correcto. Ejemplo [(hPa -> Pa), (Pa -> mbar)] */
    private final List<Conversor> conversores;
    private List<Tuple<Double, LocalDateTime>> historial;


    public Procesador(UnidadLectura variableMedida){
        this(variableMedida, null);
    }
    public Procesador(UnidadLectura variableMedida, List<Conversor> conversores){
        this.variableMedida = variableMedida;
        this.historial = new ArrayList<>();
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

            List<Tuple<Double, LocalDateTime>> nuevoHistorial = new ArrayList<>();

            //realiza la conversion de unidad del nuevo conversor a todos los datos del historial
            for(Tuple<Double, LocalDateTime> t : this.historial){ 
                double e1 = t.getElement1();
                LocalDateTime e2 = t.getElement2();
                double nuevoE1 = conversorUnidades.convertirUnidades(e1);
                Tuple<Double, LocalDateTime> datoModificado = new Tuple<>(nuevoE1, e2);
                nuevoHistorial.add(datoModificado);
            }

            this.historial = nuevoHistorial;
            return true;
        }
        else
            return false;
    }

    public void procesarDato(double dato, LocalDateTime fechaDeLectura){
        Tuple<Double, LocalDateTime> tupla = new Tuple<>(dato, fechaDeLectura);
        this.historial.add(tupla);
    }

    public UnidadLectura getUnidadAConvertir(){
        return this.conversores.get(this.conversores.size()-1).getUnidadDestino();
    }

    public boolean convierteUnidades(){
        //si es igual a 1, solo tiene el conversor identidad
        return this.conversores.size() > 1;
    }

    public double getLecturaMinima(){
        double min = Double.MAX_VALUE;
        for(Tuple<Double, LocalDateTime> t : this.historial){
            if(t.getElement1() < min)
                min = t.getElement1();
        }
        return min;
    }

    public double getLecturaMaxima(){
        double max = Double.MIN_VALUE;
        for(Tuple<Double, LocalDateTime> t : this.historial){
            if(t.getElement1() > max)
                max = t.getElement1();
        }
        return max;
    }

    public Collection<Tuple<Double, LocalDateTime>> getHistorial(){
        return List.copyOf(this.historial);
    }

    public double getLecturaMedia(){
        if(this.historial.isEmpty())
            return 0;

        double media = 0;
        for(Tuple<Double, LocalDateTime> t : this.historial){
            media += t.getElement1();
        }
        return media / this.historial.size();
    }

}
