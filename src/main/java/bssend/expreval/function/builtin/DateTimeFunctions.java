package bssend.expreval.function.builtin;

import bssend.expreval.annotation.Function;
import lombok.val;
import lombok.var;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateTimeFunctions {
    /**
     * <pre>
     * Convert string to date.
     * </pre>
     * @param dateString
     * @param format
     * @return
     * @throws ParseException
     */
    @Function("date")
    public static ZonedDateTime date(String dateString, String format) throws ParseException {

        val zoneId = ZoneId.systemDefault();
        val formatter = new SimpleDateFormat(format);

        val localDate = LocalDateTime.ofInstant(
                formatter.parse(dateString).toInstant(), zoneId);

        return ZonedDateTime.ofLocal(localDate, zoneId, null);
    }

    /**
     * <pre>
     * Convert string to date with timezone.
     * </pre>
     * @param dateString
     * @param format
     * @param timezone
     * @return
     * @throws ParseException
     */
    @Function("date")
    public static ZonedDateTime date(String dateString, String format, String timezone) throws ParseException {

        val zoneId = ZoneId.of(timezone);
        val formatter = new SimpleDateFormat(format);

        val localDate = LocalDateTime.ofInstant(
                formatter.parse(dateString).toInstant(), zoneId);

        return ZonedDateTime.ofLocal(localDate, zoneId, null);
    }

    /**
     * <pre>
     * Convert date to string with format.
     * </pre>
     * @param date
     * @param format
     * @return
     */
    @Function("format")
    public static String format(ZonedDateTime date, String format) {
        return DateTimeFormatter.ofPattern(format).format(date);
    }

    /**
     * <pre>
     * Return now datetime.
     * </pre>
     * @return
     */
    @Function("now")
    public static ZonedDateTime now() {
        return ZonedDateTime.now(ZoneId.systemDefault());
    }

    /**
     * <pre>
     * Return now datetime with timezone.
     * </pre>
     * @return
     */
    @Function("now")
    public static ZonedDateTime now(String timezone) {
        return ZonedDateTime.now(ZoneId.of(timezone));
    }

    /**
     * <pre>
     * Return today.
     * </pre>
     * @return
     */
    @Function("today")
    public static ZonedDateTime today() {
        return ZonedDateTime.now(ZoneId.systemDefault())
                .withHour(0)
                .withMinute(0)
                .withSecond(0);
    }

    /**
     * <pre>
     * Return today with timezone.
     * </pre>
     * @return
     */
    @Function("today")
    public static ZonedDateTime today(String timezone) {
        return ZonedDateTime.now(ZoneId.of(timezone))
                .withHour(0)
                .withMinute(0)
                .withSecond(0);
    }

    @Function("add_years")
    public static ZonedDateTime addYears(ZonedDateTime date, int years) throws ParseException {
        return date.plusYears(years);
    }

    @Function("add_months")
    public static ZonedDateTime addMonths(ZonedDateTime date, int months) {
        return date.plusMonths(months);
    }

    @Function("add_days")
    public static ZonedDateTime addDays(ZonedDateTime date, int days) {
        return date.plusDays(days);
    }

    @Function("add_hours")
    public static ZonedDateTime addHours(ZonedDateTime date, int hours) {
        return date.plusHours(hours);
    }

    @Function("add_minutes")
    public static ZonedDateTime addMinutes(ZonedDateTime date, int minutes) {
        return date.plusMinutes(minutes);
    }

    @Function("add_seconds")
    public static ZonedDateTime addSeconds(ZonedDateTime date, int seconds) {
        return date.plusSeconds(seconds);
    }

}
