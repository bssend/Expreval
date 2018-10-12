package bssend.expreval.function.builtin;

import bssend.expreval.annotation.FunctionName;
import bssend.expreval.value.DateTimeValue;
import bssend.expreval.value.IntegerValue;
import bssend.expreval.value.StringValue;
import lombok.val;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeFunctions {

    private static ZonedDateTime createDateTime(String dateString, String format, ZoneId zoneId)
            throws ParseException {

        val formatter = new SimpleDateFormat(format);
        val localDate = LocalDateTime
                .ofInstant(formatter.parse(dateString).toInstant(), zoneId);

        return ZonedDateTime
                .ofLocal(localDate, zoneId, null);
    }

    /**
     * <pre>
     * Convert string to date.
     * </pre>
     * @param dateString
     * @param format
     * @return
     * @throws ParseException
     */
//    @FunctionName(
//            name = "date",
//            returnType = DateTimeType.class,
//            parameterTypes = {
//                    StringType.class ,
//                    StringType.class
//            })
    @FunctionName(name = "date")
    public static DateTimeValue date(
            final StringValue dateString, final StringValue format)
                throws ParseException {

        val zoneId = ZoneId.systemDefault();

        return DateTimeValue.of(createDateTime(
                dateString.stringValue(), format.stringValue(), zoneId));
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
//    @FunctionName(
//            name = "date",
//            returnType = DateTimeType.class,
//            parameterTypes = {
//                    StringType.class ,
//                    StringType.class ,
//                    StringType.class
//            })
    @FunctionName(name = "date")
    public static DateTimeValue date(
            final StringValue dateString,
            final StringValue format,
            final StringValue timezone) throws ParseException {

        val zoneId = ZoneId.of(timezone.stringValue());

        return DateTimeValue.of(createDateTime(
                dateString.stringValue(), format.stringValue(), zoneId));
    }

    /**
     * <pre>
     * Convert date to string with format.
     * </pre>
     * @param date
     * @param format
     * @return
     */
//    @FunctionName(
//            name = "format",
//            returnType = StringType.class,
//            parameterTypes = {
//                    DateTimeType.class ,
//                    StringType.class
//            })
    @FunctionName(name = "format")
    public static StringValue format(DateTimeValue date, StringValue format) {
        val formatter = DateTimeFormatter.ofPattern(format.stringValue());
        return StringValue.of(formatter.format(date.dateTimeValue()));
    }

    /**
     * <pre>
     * Return now datetime.
     * </pre>
     * @return
     */
//    @FunctionName(
//            name = "now",
//            returnType = DateTimeType.class,
//            parameterTypes = {})
    @FunctionName(name = "now")
    public static DateTimeValue now() {
        return DateTimeValue.of(ZonedDateTime.now(ZoneId.systemDefault()));
    }

    /**
     * <pre>
     * Return now datetime with timezone.
     * </pre>
     * @return
     */
//    @FunctionName(
//            name = "now",
//            returnType = DateTimeType.class,
//            parameterTypes = {
//                    StringType.class
//            })
    @FunctionName(name = "now")
    public static DateTimeValue now(StringValue timezone) {
        return DateTimeValue.of(ZonedDateTime.now(ZoneId.of(timezone.stringValue())));
    }

    /**
     * <pre>
     * Return today.
     * </pre>
     * @return
     */
//    @FunctionName(
//            name = "today",
//            returnType = DateTimeType.class,
//            parameterTypes = {})
    @FunctionName(name = "today")
    public static DateTimeValue today() {
        return DateTimeValue.of(ZonedDateTime.now(ZoneId.systemDefault())
                .withHour(0)
                .withMinute(0)
                .withSecond(0));
    }

    /**
     * <pre>
     * Return today with timezone.
     * </pre>
     * @return
     */
//    @FunctionName(
//            name = "today",
//            returnType = DateTimeType.class,
//            parameterTypes = {
//                    StringType.class
//            })
    @FunctionName(name = "today")
    public static DateTimeValue today(StringValue timezone) {
        return DateTimeValue.of(ZonedDateTime.now(ZoneId.of(timezone.stringValue()))
                .withHour(0)
                .withMinute(0)
                .withSecond(0));
    }

//    @FunctionName(
//            name = "add_years",
//            returnType = DateTimeType.class,
//            parameterTypes = {
//                    DateTimeType.class,
//                    IntegerType.class
//            })
    @FunctionName(name = "add_years")
    public static DateTimeValue addYears(DateTimeValue date, IntegerValue years) {
        return DateTimeValue.of(date.dateTimeValue().plusYears(years.intValue()));
    }

//    @FunctionName(
//            name = "add_months",
//            returnType = DateTimeType.class,
//            parameterTypes = {
//                    DateTimeType.class,
//                    IntegerType.class
//            })
    @FunctionName(name = "add_months")
    public static DateTimeValue addMonths(DateTimeValue date, IntegerValue months) {
        return DateTimeValue.of(date.dateTimeValue().plusMonths(months.intValue()));
    }

//    @FunctionName(
//            name = "add_days",
//            returnType = DateTimeType.class,
//            parameterTypes = {
//                    DateTimeType.class,
//                    IntegerType.class
//            })
    @FunctionName(name = "add_days")
    public static DateTimeValue addDays(DateTimeValue date, IntegerValue days) {
        return DateTimeValue.of(date.dateTimeValue().plusDays(days.intValue()));
    }

//    @FunctionName(
//            name = "add_hours",
//            returnType = DateTimeType.class,
//            parameterTypes = {
//                    DateTimeType.class,
//                    IntegerType.class
//            })
    @FunctionName(name = "add_hours")
    public static DateTimeValue addHours(DateTimeValue date, IntegerValue hours) {
        return DateTimeValue.of(date.dateTimeValue().plusHours(hours.intValue()));
    }

//    @FunctionName(
//            name = "add_minutes",
//            returnType = DateTimeType.class,
//            parameterTypes = { DateTimeType.class, IntegerType.class })
    @FunctionName(name = "add_minutes")
    public static DateTimeValue addMinutes(DateTimeValue date, IntegerValue minutes) {
        return DateTimeValue.of(
                date.dateTimeValue().plusMinutes(minutes.intValue()));
    }

//    @FunctionName(
//            name = "add_seconds",
//            returnType = DateTimeType.class,
//            parameterTypes = {
//                    DateTimeType.class,
//                    IntegerType.class
//            })
    @FunctionName(name = "add_seconds")
    public static DateTimeValue addSeconds(DateTimeValue date, IntegerValue seconds) {
        return DateTimeValue.of(date.dateTimeValue().plusSeconds(seconds.intValue()));
    }
}
