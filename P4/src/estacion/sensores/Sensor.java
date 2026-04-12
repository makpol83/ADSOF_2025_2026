package estacion.sensores;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import estacion.estrategiasMedicion.EstrategiaMedicion;
import estacion.estrategiasMedicion.MedicionAleatoria;
import estacion.unidadeslectura.UnidadLectura;
import estacion.unidadeslectura.conversores.Conversor;
import estacion.unidadeslectura.conversores.Procesador;

public abstract class Sensor{
    private static final EstrategiaMedicion estrategiaPorDefecto = new MedicionAleatoria(0.2);

    private String identificador;
    private double offset;

    private LocalDateTime fechaUltimaLectura;
    private double valorUltimaLectura;

    private LocalDate fechaImplementacion;

    private boolean estaCalibrado;
    private LocalDateTime tiempoCaducidad;

    private UnidadLectura variableMedida;

    private EstrategiaMedicion estrategia;

    private Procesador procesador;

    private double sumaMediciones;
    private long numMediciones;

    

    public Sensor(String identificador, double offset, UnidadLectura variableMedida, EstrategiaMedicion estrategia, Collection<Conversor> conversores){
        this.identificador = identificador;
        this.offset = offset;
        this.valorUltimaLectura = 0;
        this.fechaUltimaLectura = null;
        this.fechaImplementacion = LocalDate.now();
        this.variableMedida = variableMedida;
        this.estrategia = estrategia;
        this.sumaMediciones = 0;
        this.numMediciones = 0;
        if(conversores != null)
            this.procesador = new Procesador(this.variableMedida, List.copyOf(conversores));
        else
            this.procesador = new Procesador(this.variableMedida);
    }

    public Sensor(String identificador, double offset, UnidadLectura variableMedida){
        this(identificador, offset, variableMedida, estrategiaPorDefecto, null);
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
                fechaUltimaLectura.getSecond() + tiempoCaducidad.getSecond());
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
        this.fechaUltimaLectura = this.fechaUltimaLectura.minusNanos(fechaUltimaLectura.getNano());
        this.procesador.procesarDato(valorUltimaLectura, fechaUltimaLectura);
    }
    
    public String getUnidadLectura(){
        return this.variableMedida.toString();
    }

    public String getIdentificador(){return this.identificador;}
    public double getMediaHistorica(){ return (numMediciones==0) ? 0 : sumaMediciones/numMediciones; }
    public double getValorUltimaLectura(){ return this.valorUltimaLectura; }
    public double getValorLecturaMinimo(){ return this.variableMedida.getValorMinimo(); }
    public double getValorLecturaMaximo(){ return this.variableMedida.getValorMaximo(); }
    public Procesador getProcesador(){ return this.procesador; }

    @Override
    public String toString(){
        String fechaUltLectura="";
        String valorUltLectura="";

        if(fechaUltimaLectura == null){
            fechaUltLectura = "No hay lecturas.";
            valorUltLectura = "...";
        }
        else{
            fechaUltLectura = this.fechaUltimaLectura.toString();
            valorUltLectura = String.format("%.2f%s", this.valorUltimaLectura, this.getUnidadLectura());
        }

        return this.identificador + " (desde: " + this.fechaImplementacion + "): Sensor " + this.getClass().getSimpleName() +
        " (" + valorUltLectura + ") última lectura: " + fechaUltLectura;
    }
}
