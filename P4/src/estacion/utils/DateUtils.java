package estacion.utils;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public abstract class DateUtils {
    //añado la excepcion por si alguien la quisiera capturar (la lanza LocalDateTime.of())
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
