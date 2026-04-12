package programasPrueba;

import estacion.EstacionMeteorologica;
import estacion.estrategiasMedicion.MedicionAleatoria;
import estacion.estrategiasMedicion.MedicionCercana;
import estacion.exceptions.MismoIdException;
import estacion.formateadores.Formateador;
import estacion.formateadores.FormateadorHTML;
import estacion.formateadores.FormateadorMarkdown;
import estacion.sensores.Sensor;
import estacion.sensores.Temperatura;
import estacion.unidadesLectura.MTemperatura;
import estacion.unidadesLectura.conversores.ConversorCelsiusFahrenheit;

public class Apartado5 {
    public static void main(String ... args){
        EstacionMeteorologica estacion = new EstacionMeteorologica("Madrid Centro", -3.7038, 40.4168);
        Sensor sTemp1 = new Temperatura(MTemperatura.Celsius, new MedicionCercana(0));
        Sensor sTemp2 = new Temperatura(MTemperatura.Fahrenheit, new MedicionCercana(0));
        Sensor sTemp3 = new Temperatura(MTemperatura.Kelvin, new MedicionCercana(0));

        sTemp1.getProcesador().addConversor(ConversorCelsiusFahrenheit.getConversor());

        try{
            estacion.añadirSensor(sTemp1);
            estacion.añadirSensor(sTemp2);
        } catch(MismoIdException e){
            //No debe pasar
        }

        estacion.calibrarSensor(sTemp1, 0);
        estacion.calibrarSensor(sTemp2, 0);

        estacion.lecturaManual();
        estacion.lecturaManual();
        estacion.lecturaManual();

        Formateador formateador = new FormateadorMarkdown();

        System.out.println("Formateamos a Markdown sin alertas, parecido al enunciado:");
        System.out.print(formateador.formatear(estacion));

        
        System.out.println("Formateamos a Markdown añadiendo alguna alerta para ver cómo se comporta:");

        //Añadimos un sensor sin calibrar e intentamos medir:
        try{
            estacion.añadirSensor(sTemp3);
        } catch(MismoIdException e){
            //No puede pasar
        }

        estacion.lecturaPuntual(sTemp3);
        System.out.print(formateador.formatear(estacion));

        formateador = new FormateadorHTML();
        System.out.println("\nFormateamos a HTML:");
        System.out.print(formateador.formatear(estacion));
    }
}
