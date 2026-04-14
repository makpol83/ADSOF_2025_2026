package estacion.utils;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Clase de utilidad para llamar a metodos que operan con fechas.
 */
public abstract class DateUtils {
    private DateUtils(){}


    //añado la excepcion por si alguien la quisiera capturar (la lanza LocalDateTime.of())
    /**
     * Suma dos fechas y retorna el resultado (no tiene en cuenta los nanosegundos)
     * @param date1 fecha a sumar
     * @param date2 fecha a sumar
     * @return resultado de la suma de ambas fechas
     * @throws DateTimeException en las mismas condiciones que LocalDateTime.of();
     */
    public static LocalDateTime addDates(LocalDateTime date1, LocalDateTime date2) throws DateTimeException {
        return LocalDateTime.of(
            date1.getYear() + date2.getYear(),
            date1.getMonthValue() + date2.getMonthValue(),
            date1.getDayOfMonth() + date2.getDayOfMonth(),
            date1.getHour() + date2.getHour(),
            date1.getMinute() + date2.getMinute(),
            date1.getSecond() + date2.getSecond()               
        );
    }
}
