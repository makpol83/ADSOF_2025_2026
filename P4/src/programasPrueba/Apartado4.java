package programasPrueba;

import estacion.EstacionMeteorologica;
import estacion.exceptions.MismoIdException;
import estacion.sensores.Humedad;
import estacion.sensores.Presion;
import estacion.sensores.Sensor;
import estacion.sensores.Temperatura;
import estacion.unidadesLectura.MHumedad;
import estacion.unidadesLectura.MPresionAtmosferica;
import estacion.unidadesLectura.MTemperatura;

public class Apartado4 {
    public static void main(String ... args){
        EstacionMeteorologica estacion = new EstacionMeteorologica("Madrid Centro", 40.4168, -3.7038);
        Sensor sTemp1 = new Temperatura(MTemperatura.Celsius);
        Sensor sTemp2 = new Temperatura(MTemperatura.Fahrenheit);
        Sensor sTemp3 = new Temperatura(MTemperatura.Kelvin);
        Sensor sPresion1 = new Presion(MPresionAtmosferica.Pa);
        Sensor sPresion2 = new Presion(MPresionAtmosferica.hPa);
        Sensor sPresion3 = new Presion(MPresionAtmosferica.mbar);
        Sensor sHum = new Humedad(MHumedad.Porcentaje);

        sTemp1.calibrar(14, 0);
        sTemp2.calibrar(15, 0);
        sTemp3.calibrar(16, 0);
        sPresion1.calibrar(17, 0);
        sPresion2.calibrar(18, 0);
        sPresion3.calibrar(19, 0);
        sHum.calibrar(20, 0);

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

        estacion.printEstacionMeteorologica();

        estacion.lecturaManual();
        
        estacion.printEstacionMeteorologica();
    }
    
}
