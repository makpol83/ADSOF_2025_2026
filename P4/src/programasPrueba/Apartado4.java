package programasPrueba;

import estacion.EstacionMeteorologica;
import estacion.estrategiasMedicion.MedicionAleatoria;
import estacion.estrategiasMedicion.MedicionCercana;
import estacion.exceptions.MismoIdException;
import estacion.sensores.Humedad;
import estacion.sensores.Presion;
import estacion.sensores.Sensor;
import estacion.sensores.Temperatura;
import estacion.unidadesLectura.MHumedad;
import estacion.unidadesLectura.MPresionAtmosferica;
import estacion.unidadesLectura.MTemperatura;

/**
 * Clase para testear el apartado 4
 */
public class Apartado4 {
    private Apartado4(){}
    /**
     * Programa para testear el apartado 4.
     * Añade varios sensores a la estación e imprime la estación sin haberlos calibrado, por lo que no se muestran. A continuación realiza
     * una lectura aún sin haberlos calibrado, generando las alertas correspondientes. Tras ello calibra los sensores
     */
    public static void main(){
        EstacionMeteorologica estacion = new EstacionMeteorologica("Madrid Centro", -3.7038, 40.4168);
        Sensor sTemp1 = new Temperatura(MTemperatura.Celsius, new MedicionAleatoria(1));
        Sensor sTemp2 = new Temperatura(MTemperatura.Fahrenheit, new MedicionCercana(0));
        Sensor sTemp3 = new Temperatura(MTemperatura.Kelvin, new MedicionCercana(0));
        Sensor sPresion1 = new Presion(MPresionAtmosferica.Pa, new MedicionCercana(0));
        Sensor sPresion2 = new Presion(MPresionAtmosferica.hPa, new MedicionCercana(0));
        Sensor sPresion3 = new Presion(MPresionAtmosferica.mbar, new MedicionCercana(0));
        Sensor sHum = new Humedad(MHumedad.Porcentaje, new MedicionCercana(0));

        try{
            estacion.añadirSensor(sTemp1);
            estacion.añadirSensor(sTemp2);
            estacion.añadirSensor(sTemp3);
            estacion.añadirSensor(sPresion1);
            estacion.añadirSensor(sPresion2);
            estacion.añadirSensor(sPresion3);
            estacion.añadirSensor(sHum);
        } catch(MismoIdException e){
            //No debe pasar
        }
        System.out.println("---> Imprimimos con sólo los sensores añadidos:");
        System.out.println();
        //Vemos que esta vacio pero hay sensores
        estacion.print();

        //Realizamos lectura
        estacion.lecturaManual();
        
        System.out.println("---> Imprimimos con errores de calibración:");
        System.out.println();
        //Vemos que la lectura falla y se generan alertas de calibración
        estacion.print();

        //Calibramos
        //Aunque sTemp1 siempre da valores fuera de rango, la primera lectura siempre se hace dentro de rango
        //Por si la estrategia requiere el dato
        estacion.calibrarSensor(sTemp1, 0);
        estacion.calibrarSensor(sTemp2, 0);
        estacion.calibrarSensor(sTemp3, 0);
        estacion.calibrarSensor(sPresion1, 0);
        estacion.calibrarSensor(sPresion2, 0);
        estacion.calibrarSensor(sPresion3, 0);
        estacion.calibrarSensor(sHum, 0);

        System.out.println("---> Imprimos tras calibrar y vemos que han retomado las medidas:");
        System.out.println();
        //Vemos que está vacío pero esta vez están calibrados
        estacion.print();

        //Realizamos lectura
        estacion.lecturaManual();

        System.out.println("---> Volvemos a hacer lecturas y vemos que "+sTemp1.getIdentificador()+" sale de rango siempre");
        System.out.println();
        estacion.print();

        System.out.println("---> Forzamos un cambio brusco en "+sHum.getIdentificador()+" cambiando el offset un poco y aprovechando que es medicion cercana");
        System.out.println();
        
        //calibrado mal (con offset muy alto, para forzar el cambio brusco)
        estacion.calibrarSensor(sHum, 200);

        estacion.lecturaPuntual(sHum);

        estacion.print();

        System.out.println("---> Restauro el offset de " + sHum.getIdentificador() + " para que no salgan sus alertas");
        System.out.println();

        estacion.calibrarSensor(sHum, 0);

        estacion.print();
        System.out.println();


        System.out.println("\n---> Si se ha llegado aquí y se cumple lo que dicen las impresiones, mostrando las 3 distintas alertas\n\ta lo largo de la ejecución, se consideran correctas las alertas.");
    }
    
}
