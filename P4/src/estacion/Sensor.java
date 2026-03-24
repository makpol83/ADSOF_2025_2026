package estacion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import estacion.EstrategiaMedicion;

public abstract class Sensor {
    private String identificador;
    private double offset;

    private LocalDateTime fechaUltimaLectura;
    private String valorUltimaLectura;

    private LocalDate fechaImplementacion;

    private boolean estaCalibrado;
    private LocalDateTime tiempoCaducidad;

    private EstrategiaMedicion estrategia;

    private double probFueraRango;
    private double rangoCercano;

    public Sensor(String identificador, double offset, EstrategiaMedicion estrategia) implements Medicion {
        this.identificador = identificador;
        this.offset = offset;
        this.valorUltimaLectura = null;
        this.fechaUltimaLectura = null;
        this.fechaImplementacion = LocalDate.now();
    }

    public Sensor(String identificador, double offset, EstrategiaMedicion estrategia) implements Medicion{
        this(identificador, )
    }

    private boolean estaCalibrado(){
        if(estaCalibrado == false)
            return false;

        LocalDateTime expireDate;

        if(fechaUltimaLectura != null)
            expireDate = LocalDateTime.of(
                fechaUltimaLectura.getYear() + tiempoCaducidad.getYear(),
                fechaUltimaLectura.getMonthValue() + tiempoCaducidad.getMonthValue(),
                fechaUltimaLectura.getDayOfMonth() + tiempoCaducidad.getDayOfMonth(),
                fechaUltimaLectura.getHour() + tiempoCaducidad.getHour(),
                fechaUltimaLectura.getMinute() + tiempoCaducidad.getMinute(),
                fechaUltimaLectura.getSecond() + tiempoCaducidad.getSecond(),
                0);
        else
            return false;

        if(expireDate.isBefore(LocalDateTime.now()) == true)
            return true;

        return false;
    }

    public void setFechaUltimaLectura(LocalDateTime fecha){this.fechaUltimaLectura = fecha;}
    public void setValorUltimaLectura(String valor){this.valorUltimaLectura = valor;}
    public void lectura(){};


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


    public abstract void medicionAleatoria();
    public void medicionCercana(){};
    public abstract void medicionHistorica();
}
