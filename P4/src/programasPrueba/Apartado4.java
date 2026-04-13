package programasPrueba;

import java.time.LocalDateTime;

import estacion.EstacionMeteorologica;
import estacion.estrategiasMedicion.MedicionAleatoria;
import estacion.estrategiasMedicion.MedicionCercana;
import estacion.exceptions.MismoIdException;
import estacion.sensores.Humedad;
import estacion.sensores.Medida;
import estacion.sensores.Presion;
import estacion.sensores.Sensor;
import estacion.sensores.Temperatura;
import estacion.unidadesLectura.MHumedad;
import estacion.unidadesLectura.MPresionAtmosferica;
import estacion.unidadesLectura.MTemperatura;

public class Apartado4 {
    public static void main(String ... args){
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
        System.out.println("Imprimimos con sólo los sensores añadidos:");
        //Vemosq ue esta vacio pero hay sensores
        estacion.print();

        //Realizamos lectura
        estacion.lecturaManual();
        
        System.out.println("Imprimimos con errores de calibración:");
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

        System.out.println("Imprimos tras calibrar y vemos que han retomado las medidas:");
        //Vemos que está vacío pero esta vez están calibrados
        estacion.print();

        //Realizamos lectura
        estacion.lecturaManual();

        System.out.println("Volvemos a hacer lecturas y vemos que sTemp1 sale de rango siempre");
        estacion.print();

        System.out.println("Forzamos un cambio brusco en HUM-0001 cambiando el offset un poco y aprovechando que es medicion cercana");
        sHum.forzarMedida(new Medida(80, LocalDateTime.now().withNano(0)));
        
        sHum.calibrar(10, 50);

        estacion.lecturaPuntual(sHum);

        estacion.print();

        System.out.println("Restauro el offset de HUM-0001 para que no salgan sus alertas");
        sHum.calibrar(10, 0);


        System.out.println("\nSi se ha llegado aquí y se cumple lo que dicen las impresiones, mostrando las 3 distintas alertas\nA lo largo de la ejecución, se consideran correctas las alertas.");
    }
    
}
