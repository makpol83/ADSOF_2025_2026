package programasPrueba;

import estacion.EstacionMeteorologica;
import estacion.estrategiasMedicion.MedicionAleatoria;
import estacion.estrategiasMedicion.MedicionCercana;
import estacion.estrategiasMedicion.MedicionHistorica;
import estacion.estrategiasMedicion.MedicionLogaritmica;
import estacion.exceptions.MismoIdException;
import estacion.sensores.*;
import estacion.unidadesLectura.MPresionAtmosferica;
import estacion.unidadesLectura.MTemperatura;

public class Apartado1y2 {
    private Apartado1y2(){}

    
    public static void main(){
        EstacionMeteorologica estacion = new EstacionMeteorologica("Mi Estacion", 6.7, 6.7);

        try{
            estacion.añadirSensor(new Temperatura(new MedicionAleatoria(0)));
            estacion.añadirSensor(new Presion(new MedicionCercana(10)));
            estacion.añadirSensor(new Humedad(new MedicionHistorica(15)));

            estacion.añadirSensor(new Temperatura(MTemperatura.Fahrenheit, new MedicionAleatoria(0.5)));
            estacion.añadirSensor(new Presion(MPresionAtmosferica.Pa, new MedicionCercana(20)));
            estacion.añadirSensor(new Humedad(new MedicionHistorica(20)));
        } catch(MismoIdException e){
            System.out.println("error");
            return;
        }
        System.out.println("Estacion creada. Sensores añadidos:");
        System.out.println();
        System.out.println(estacion);
        System.out.println();

        System.out.println("Sensores:");
        //impresion de sensores
        for(Sensor s : estacion.getSensores()){
            System.out.println(s);
        }

        System.out.println();
        System.out.println("--calibrando sensores--");
        System.out.println();

        boolean exito = true;

        //calibracion de sensores
        for(Sensor s : estacion.getSensores()){
            if(estacion.calibrarSensor(s, 0) == false)
                exito = false;
        }

        if(exito == false){
            System.out.println("error en calibracion");
            return;
        }

        System.out.println();
        System.out.println("-- realizando lectura de sensores --");
        System.out.println();
        estacion.lecturaManual();

        System.out.println("Sensores agrupados por tipo:");
        //impresion de sensores
        for(Sensor s : estacion.getSensores("TEMP"))
            System.out.println(s);
        for(Sensor s : estacion.getSensores("PRES"))
            System.out.println(s);
        for(Sensor s : estacion.getSensores("HUM"))
            System.out.println(s);

        System.out.println();
        System.out.println("-- realizando 4 lecturas de sensores --");
        System.out.println();
        estacion.lecturaManual();
        estacion.lecturaManual();
        estacion.lecturaManual();
        estacion.lecturaManual();
        
        System.out.println("Sensores agrupados por tipo:");
        for(Sensor s : estacion.getSensores("TEMP"))
            System.out.println(s);
        for(Sensor s : estacion.getSensores("PRES"))
            System.out.println(s);
        for(Sensor s : estacion.getSensores("HUM"))
            System.out.println(s);


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Limpieza de estacion: ");
        estacion = new EstacionMeteorologica("Mi Estacion", 6.7, 6.7);

        System.out.println("Añadir Sensor con estrategia personalizada (MedicionLogaritmica)");
        Sensor s = new Temperatura(MTemperatura.Kelvin ,new MedicionLogaritmica());
        
        try{
            estacion.añadirSensor(s);
        } catch(MismoIdException e){
            System.out.println("error");
            return;
        }
        System.out.println(s);
        
        
        s.calibrar(0);

        System.out.println();
        System.out.println("Sensor calibrado");
        System.out.println();

        for(int i=0; i<5; i++)
            estacion.lecturaManual();
        System.out.println("5 lecturas realizdas");

        System.out.println();
        System.out.println("Estado final del sensor:");
        System.out.println(s);
        System.out.println();
        System.out.println();
        System.out.println("Estado de la estacion:");
        estacion.shortPrint(true);
        System.out.println();
        System.out.println("NOTA: la primera lectura siempre es Aleatoria. De ahi que el valor sea enorme en comparacion al resto" + 
                            "a los que si se le ha aplicado la medicion logaritmica");
        System.out.println();

        System.out.println("Si se imprime esta linea se ha llegado al final del test");

    }
}
