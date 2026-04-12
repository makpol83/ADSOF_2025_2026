import estacion.EstacionMeteorologica;
import estacion.estrategiasMedicion.MedicionCercana;
import estacion.exceptions.MismoIdException;
import estacion.sensores.*;
import estacion.unidadeslectura.MPresionAtmosferica;
import estacion.unidadeslectura.MTemperatura;
import estacion.unidadeslectura.conversores.Conversor;
import estacion.unidadeslectura.conversores.ConversorCelsiusFahrenheit;
import estacion.unidadeslectura.conversores.ConversorFahrenhieitKelvin;
import estacion.unidadeslectura.conversores.ConversorPascalHectoPascal;

public class Apartado3 {

    public void main(String ... args){
        EstacionMeteorologica estacion = new EstacionMeteorologica("Madrid Centro", 40.4168, -37038);
        Sensor sTemp = new Temperatura(0, MTemperatura.Celsius);
        Sensor sPresion = new Presion(0, MPresionAtmosferica.Pa);

        try{
            estacion.añadirSensor(sTemp);
            estacion.añadirSensor(sPresion);

        } catch(MismoIdException e){
            System.out.println(e.getMessage());
            System.out.println("conflicto entre: ");
            System.out.println(e.getS1());
            System.out.println(e.getS2());
        } 
        

        //FUNCIONA EL CONVERSOR DE CELSIUS A FAHRENHEIT Y DE FAHRENHEIT A KELVIN
        //EL RESTO YA LO PRUEBO MAÑANA SI PUEDO
        
        estacion.lecturaManual();
        estacion.lecturaManual();
        estacion.lecturaManual();
        estacion.printEstacionMeteorologica();
        System.out.println();
        
        sTemp.getProcesador().addConversor(ConversorCelsiusFahrenheit.getConversor());
        sTemp.getProcesador().addConversor(ConversorFahrenhieitKelvin.getConversor());

        sPresion.getProcesador().addConversor(ConversorPascalHectoPascal.getConversor());
        
        estacion.printEstacionMeteorologica();
    }
    
}