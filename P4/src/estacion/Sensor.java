package estacion;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Sensor {
    private String identificador;
    private double offset;

    private String unidad;

    private LocalDateTime ultimaLectura;
    private String valorUltimaLectura;

    public Sensor(String identificador, double offset, String unidad){
        this.identificador = identificador;
        this.offset = offset;
        this.unidad = unidad;
        this.valorUltimaLectura = null;
        this.ultimaLectura = null;
    }

    private boolean estaCalibrado(){
        return false;
    }

    public boolean esRangoValido(double value){
        if(value < this.minValor ||  value > this.maxValor)
            return false;

        return true;
    }
    
}
