package programasPrueba;
import estacion.EstacionMeteorologica;
import estacion.estrategiasMedicion.MedicionAleatoria;
import estacion.estrategiasMedicion.MedicionCercana;
import estacion.exceptions.ConversorIncompatibleException;
import estacion.exceptions.MismoIdException;
import estacion.sensores.*;
import estacion.unidadesLectura.MPresionAtmosferica;
import estacion.unidadesLectura.MTemperatura;
import estacion.unidadesLectura.conversores.ConversorCelsiusFahrenheit;
import estacion.unidadesLectura.conversores.ConversorFahrenheitKelvin;
import estacion.unidadesLectura.conversores.ConversorKelvinCelsius;
import estacion.unidadesLectura.conversores.ConversorPascalHectoPascal;

/**
 * Clase para testear el apartado 3
 */
public class Apartado3 {
    private Apartado3(){}
    /**
     * Programa tester del apartado 3. Añade un par de sensores a la estacion, realiza varias lecturas e imprime sus medidas. Luego
     * añade algunos conversores y reimprime las medidas con el historial del procesador actualizado. A continuación inserta otro conversor
     * para que uno de los sensores vuelva a la unidad de lectura original, mostrando como es posible concatenar conversores y los valores
     * inicial y final son identicos.
     * 
     * Finalmente añade un conversor incompatible para recoger la exepcion lanzada.
     */
    public void main(){
        EstacionMeteorologica estacion = new EstacionMeteorologica("Madrid Centro", 40.4168, -37038);
        Sensor sTemp = new Temperatura(MTemperatura.Celsius, new MedicionCercana(5));
        Sensor sPresion = new Presion(MPresionAtmosferica.Pa, new MedicionAleatoria(0));

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
            System.out.println("SI SE IMPRIME ESTE MENAJE, EL TEST HA FALLADO");
            return;
        } 
        
        estacion.lecturaManual();
        estacion.lecturaManual();
        estacion.lecturaManual();

        estacion.shortPrint(true);
        
        System.out.println();
        System.out.println();
    
        try{
            sTemp.getProcesador().addConversor(ConversorCelsiusFahrenheit.getConversor());
            System.out.println("Añadimos conversor ºC --> ºF en sensor " + sTemp.getIdentificador());
            sTemp.getProcesador().addConversor(ConversorFahrenheitKelvin.getConversor());
            System.out.println("Añadimos conversor ºF --> ºK en sensor " + sTemp.getIdentificador());
            sPresion.getProcesador().addConversor(ConversorPascalHectoPascal.getConversor());
            System.out.println("Añadimos conversor Pa --> hPa en sensor" + sPresion.getIdentificador());
        } catch (ConversorIncompatibleException e){
            System.out.println(e);
            System.out.println("SI SE IMPRIME ESTE MENAJE, EL TEST HA FALLADO");
            return;
        }
        System.out.println();
        System.out.println();

        estacion.shortPrint(true);

        System.out.println();
        System.out.println();

        try{
            sTemp.getProcesador().addConversor(ConversorKelvinCelsius.getConversor());
            System.out.println("Añadimos conversor ºK --> ºC en sensor " + sTemp.getIdentificador());
        } catch (ConversorIncompatibleException e){
            System.out.println(e);
            System.out.println("SI SE IMPRIME ESTE MENAJE, EL TEST HA FALLADO");
            return;
        }
        System.out.println();
        System.out.println();
        
        estacion.shortPrint(true);

        System.out.println();
        System.out.println();
        System.out.println("FINALMENTE, AÑADIMOS UN CONVERSOR INCOMPATIBLE PARA TESTEAR EL CORRECTO LANZAMIENTO DE LA EXCEPCION");
        System.out.println();

        try{
            System.out.println("Añadimos conversor ºK --> ºC en sensor " + sTemp.getIdentificador() + ", sabiendo que ahora mismo" +
                                "el sensor tiene medidas en ºC en lugar de ºK");
            sTemp.getProcesador().addConversor(ConversorKelvinCelsius.getConversor());
            System.out.println("SI SE IMPRIME ESTE MENAJE, EL TEST HA FALLADO");
            return;
            
        } catch (ConversorIncompatibleException e){
            System.out.println();
            System.out.println("Excepcion obtenida correctamente");
            System.out.println();
        }

        System.out.println("SI SE IMPRIME ESTE MENAJE, EL TEST HA PASADO CON EXITO");
        System.out.println();
        return;
    }
    
}