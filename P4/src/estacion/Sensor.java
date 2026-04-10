package estacion;
import java.time.LocalDate;
import java.time.LocalDateTime;

import estacion.estrategiasMedicion.EstrategiaMedicion;
import estacion.estrategiasMedicion.MedicionAleatoria;
import estacion.medidas.VariableMedida;

public abstract class Sensor{
    private static final EstrategiaMedicion estrategiaPorDefecto = new MedicionAleatoria(0.2);

    private String identificador;
    private double offset;

    private LocalDateTime fechaUltimaLectura;
    private double valorUltimaLectura;

    private LocalDate fechaImplementacion;

    private boolean estaCalibrado;
    private LocalDateTime tiempoCaducidad;

    private VariableMedida variableMedida;

    private EstrategiaMedicion estrategia;

    private double probFueraRango;
    private double rangoCercano;

    private double sumaMediciones;
    private long numMediciones;

    

    public Sensor(String identificador, double offset, VariableMedida variableMedida, EstrategiaMedicion estrategia){
        this.identificador = identificador;
        this.offset = offset;
        this.valorUltimaLectura = 0;
        this.fechaUltimaLectura = null;
        this.fechaImplementacion = LocalDate.now();
        this.sumaMediciones = 0;
        this.numMediciones = 0;
    }

    public Sensor(String identificador, double offset, VariableMedida variableMedida){
        this(identificador, offset, variableMedida, estrategiaPorDefecto);
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

    public EstrategiaMedicion getEstrategiaMedicion(){return this.estrategia;}

    public void realizarMedida(){
        if(numMediciones == 0){
            EstrategiaMedicion estrategiaAleatoria = new MedicionAleatoria(0);
            this.valorUltimaLectura = estrategiaAleatoria.medir(this);
        } else {
            this.valorUltimaLectura = this.estrategia.medir(this);
        }
        this.valorUltimaLectura -= this.offset;
        sumaMediciones += valorUltimaLectura;
        numMediciones++;
        this.fechaUltimaLectura = LocalDateTime.now();
    }
    
    public String getUnidadLectura(){
        return this.variableMedida.name();
    }

    public String getIdentificador(){return this.identificador;}
    public double getMediaHistorica(){ return (numMediciones==0) ? 0 : sumaMediciones/numMediciones; }
    public double getValorUltimaLectura(){ return this.valorUltimaLectura; }
    public double getValorLecturaMinimo(){ return this.variableMedida.getValorMinimo(); }
    public double getValorLecturaMaximo(){ return this.variableMedida.getValorMaximo(); }

    @Override
    public String toString(){
        String fechaUltLectura = this.fechaUltimaLectura.toString();
        String valorUltLectura = this.valorUltimaLectura + this.getUnidadLectura();

        if(fechaUltLectura == null){
            fechaUltLectura = "No hay lecturas.";
            valorUltLectura = "...";
        }

        return this.identificador + " (desde: " + this.fechaImplementacion + "): Sensor " + this.getClass().getSimpleName() +
        " (" + valorUltLectura + ") última lectura: " + fechaUltLectura;
    }
}
