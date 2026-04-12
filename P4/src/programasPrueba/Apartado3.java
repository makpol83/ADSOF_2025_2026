package programasPrueba;
import estacion.EstacionMeteorologica;
import estacion.exceptions.MismoIdException;
import estacion.sensores.*;
import estacion.unidadesLectura.MPresionAtmosferica;
import estacion.unidadesLectura.MTemperatura;
import estacion.unidadesLectura.conversores.ConversorCelsiusFahrenheit;
import estacion.unidadesLectura.conversores.ConversorFahrenheitKelvin;
import estacion.unidadesLectura.conversores.ConversorPascalHectoPascal;

public class Apartado3 {

    public void main(String ... args){
        EstacionMeteorologica estacion = new EstacionMeteorologica("Madrid Centro", 40.4168, -37038);
        Sensor sTemp = new Temperatura(MTemperatura.Celsius);
        Sensor sPresion = new Presion(MPresionAtmosferica.Pa);

        sTemp.calibrar(15, 0);
        sPresion.calibrar(15, 0);

        try{
            estacion.añadirSensor(sTemp);
            estacion.añadirSensor(sPresion);

        } catch(MismoIdException e){
            System.out.println(e.getMessage());
            System.out.println("conflicto entre: ");
            System.out.println(e.getExistente());
            System.out.println(e.getNuevo());
        } 
        

        //FUNCIONA EL CONVERSOR DE CELSIUS A FAHRENHEIT Y DE FAHRENHEIT A KELVIN
        //EL RESTO YA LO PRUEBO MAÑANA SI PUEDO
        
        estacion.lecturaManual();
        estacion.lecturaManual();
        estacion.lecturaManual();
        estacion.printEstacionMeteorologica();
        System.out.println();
        
        sTemp.getProcesador().addConversor(ConversorCelsiusFahrenheit.getConversor());
        sTemp.getProcesador().addConversor(ConversorFahrenheitKelvin.getConversor());

        sPresion.getProcesador().addConversor(ConversorPascalHectoPascal.getConversor());
        
        estacion.printEstacionMeteorologica();
    }
    
}