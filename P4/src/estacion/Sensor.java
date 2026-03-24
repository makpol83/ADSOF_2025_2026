package estacion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Sensor {
    private String identificador;
    private double offset;

    private LocalDateTime fechaUltimaLectura;
    private String valorUltimaLectura;

    private LocalDate fechaImplementacion;

    private boolean estaCalibrado;
    private LocalDateTime tiempoCaducidad;

    public Sensor(String identificador, double offset){
        this.identificador = identificador;
        this.offset = offset;
        this.valorUltimaLectura = null;
        this.fechaUltimaLectura = null;
        this.fechaImplementacion = LocalDate.now();
    }

    private boolean estaCalibrado(){
        if(estaCalibrado == false)
            return false;

        if(fechaUltimaLectura.plus)
    }

    public String getIdentificador(){return this.identificador;}
    
    @Override
    public String toString(){
        String fechaUltLectura = this.fechaUltimaLectura.toString();
        String valorUltLectura = this.valorUltimaLectura.toString();

        if(fechaUltLectura == null)
            fechaUltLectura = "No hay lecturas.";

        if(valorUltLectura == null)
            fechaUltLectura = "No hay lecturas.";

        return this.identificador + " (desde: " + this.fechaImplementacion + "): Sensor " + this.getClass().getSimpleName() +
        " (" + valorUltLectura + ") última lectura: " + fechaUltLectura;
    }
}
