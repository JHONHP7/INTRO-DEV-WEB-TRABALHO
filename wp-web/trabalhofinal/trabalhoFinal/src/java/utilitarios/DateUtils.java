package utilitarios;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date; // java.util.Date

public class DateUtils {

    // Converte java.util.Date para LocalDate
    public static LocalDate utilDateToLocalDate(java.util.Date utilDate) {
        return utilDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    // Converte java.sql.Date para LocalDate
    public static LocalDate sqlDateToLocalDate(java.sql.Date sqlDate) {
        return sqlDate.toLocalDate();
    }

    // Converte LocalDate para java.util.Date
    public static java.util.Date localDateToUtilDate(LocalDate localDate) {
        return java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    // Converte LocalDate para java.sql.Date
    public static java.sql.Date localDateToSqlDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }
}
