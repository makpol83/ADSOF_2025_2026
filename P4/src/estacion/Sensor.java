package estacion;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Sensor {
    private String identificador;
    private double offset;

    private LocalDateTime ultimaLectura;
    private String valorUltimaLectura;

    public Sensor(String identificador, double offset){
        this.identificador = identificador;
        this.offset = offset;
        this.valorUltimaLectura = null;
        this.ultimaLectura = null;
    }

    private boolean estaCalibrado(){
        return false;
    }

    public String getIdentificador(){return this.identificador;}
    
}
