package org.librae.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.common.Constants;
import org.librae.common.exception.LibraeException;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Date Utility Class used to convert Strings to Dates and Timestamps.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by
 *         <a href="mailto:dan@getrolling.com">Dan Kibler </a> to correct time
 *         pattern. Minutes should be mm not MM (MM is month).
 */
public final class DateUtil {
    /**
     * El Log.
     */
    private static Log          log                   = LogFactory
                                                              .getLog(DateUtil.class);

    /**
     * El patron para las horas.
     */
    private static final String TIME_PATTERN          = "HH:mm";

    /**
     * Patrón de fecha que representa fechas en formato ISO 8601
     */
    public static final String  DATETIME_PATTERN_NCIP = "yyyy-MM-dd'T'HH:mm:ssz";

    /**
     * Checkstyle rule: utility classes should not have public constructor.
     */
    private DateUtil() {
    }

    /**
     * Return default datePattern (MM/dd/yyyy).
     * 
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        final Locale locale = LocaleContextHolder.getLocale();
        String defaultDatePattern;
        try {
            defaultDatePattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY,
                    locale).getString("date.format");
        } catch (final MissingResourceException mse) {
            defaultDatePattern = "MM/dd/yyyy";
        }

        return defaultDatePattern;
    }

    public static String getDatePattern(String pattern, Date fecha) {
        SimpleDateFormat formatter;
        // String patternFecha = new String("dd/MM/yyyy-HH:mm:ss");
        final Locale currentLocale = new Locale("en", "US");
        formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(fecha);

    }

    /**
     * Return default datePattern (MM/dd/yyyy).
     * 
     * @return a string representing the date pattern on the UI
     */
    public static String getFechaHoraPattern() {
        return DateUtil.getDatePattern() + " HH:mm";
    }

    public static String getDatePatternNCIP() {
        return DATETIME_PATTERN_NCIP;
    }

    /**
     * Devuelve el patron de DateTime.
     * 
     * @return el patron de DateTime
     */
    public static String getDateTimePattern() {
        return DateUtil.getDatePattern() + " HH:mm:ss.S";
    }

    /**
     * This method attempts to convert an Oracle-formatted date in the form
     * dd-MMM-yyyy to mm/dd/yyyy.
     * 
     * @param aDate
     *            date from database as a string
     * @return formatted string for the ui
     */
    public static String getDate(final Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(DateUtil.getDatePattern(),
                    LocaleContextHolder.getLocale());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * Muestra la fecha y la hora con formato dd/mm/yyyy hh:mm.
     * 
     * @param aDate
     *            date from database as a string
     * @return formatted string for the ui
     */
    public static String getFechaHora(final Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(DateUtil.getFechaHoraPattern(),
                    LocaleContextHolder.getLocale());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method return a date in NCIP standard format, according to the
     * constant <code>DATE_PATTERN_NCIP</code>
     * 
     * @param aDate
     * @return
     */
    public static String getNcipDate(final Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(DATETIME_PATTERN_NCIP);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date/time in the
     * format you specify on input.
     * 
     * @param aMask
     *            the date pattern the string is in
     * @param strDate
     *            a string representation of a date
     * @return a converted Date object
     * @see java.text.SimpleDateFormat
     * @throws ParseException
     *             when String doesn't match the expected format
     */
    public static Date convertStringToDate(final String aMask,
            final String strDate) throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask, LocaleContextHolder.getLocale());
        if (DateUtil.log.isDebugEnabled()) {
            DateUtil.log.debug("converting '" + strDate
                    + "' to date with mask '" + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (final ParseException pe) {
            // log.error("ParseException: " + pe);
            // throw new ParseException(pe.getMessage(),pe.getErrorOffset());
            throw new LibraeException(pe.getMessage() + " ErrorOffset: "
                    + pe.getErrorOffset(), pe);
        }
        return (date);
    }

    /**
     * This method returns the current date time in the format: MM/dd/yyyy HH:MM
     * a.
     * 
     * @param theTime
     *            the current time
     * @return the current date/time
     */
    public static String getTimeNow(final Date theTime) {
        return DateUtil.getDateTime(DateUtil.TIME_PATTERN, theTime);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy.
     * 
     * @return the current date
     * @throws ParseException
     *             when String doesn't match the expected format
     */
    public static Calendar getToday() throws ParseException {
        final Date today = new Date();
        final SimpleDateFormat df = new SimpleDateFormat(DateUtil
                .getDatePattern(), LocaleContextHolder.getLocale());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        final String todayAsString = df.format(today);
        final Calendar cal = new GregorianCalendar();
        cal.setTime(DateUtil.convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * This method generates a string representation of a date's date/time in
     * the format you specify on input.
     * 
     * @param aMask
     *            the date pattern the string is in
     * @param aDate
     *            a date object
     * @return a formatted string representation of the date
     * @see java.text.SimpleDateFormat
     */
    public static String getDateTime(final String aMask, final Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            DateUtil.log.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask, LocaleContextHolder.getLocale());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based on the
     * System Property 'dateFormat' in the format you specify on input.
     * 
     * @param aDate
     *            A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(final Date aDate) {
        return DateUtil.getDateTime(DateUtil.getDatePattern(), aDate);
    }

    /**
     * This method converts a String to a date using the datePattern.
     * 
     * @param strDate
     *            the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * @throws ParseException
     *             when String doesn't match the expected format
     */
    public static Date convertStringToDate(final String strDate)
            throws ParseException {
        Date aDate = null;

        try {
            if (DateUtil.log.isDebugEnabled()) {
                DateUtil.log.debug("converting date with pattern: "
                        + DateUtil.getDatePattern());
            }

            aDate = DateUtil.convertStringToDate(DateUtil.getDatePattern(),
                    strDate);
        } catch (final ParseException pe) {
            DateUtil.log.error("Could not convert '" + strDate
                    + "' to a date, throwing exception");
            // pe.printStackTrace();
            // throw new ParseException(pe.getMessage(), pe.getErrorOffset());
            throw new LibraeException(pe.getMessage() + " ErrorOffset: "
                    + pe.getErrorOffset(), pe);
        }

        return aDate;
    }

    /**
     * Retuns current date.
     * 
     * @return current date
     */
    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * convert days to milliseconds.
     * 
     * @param days
     *            to convert
     * @return milliseconds results
     */
    public static long daysToMillis(final long days) {

        // 1 día = 24 h
        // 1 h = 60 min
        // 1 min = 60 sg
        // 1 sg = 1000 ms

        final long result = days * 24 * 60 * 60 * 1000;

        return result;
    }

    /**
     * postponeDate.
     * 
     * @param date
     *            the date
     * @param offsetDays
     *            number of days to apply
     * @return the date result
     */
    public static Date postponeDate(final Date date, final long offsetDays) {
        final Calendar c = GregorianCalendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, ((Long) new Long(offsetDays)).intValue());
        return c.getTime();
    }

    /**
     * prependDate.
     * 
     * @param date
     *            the date
     * @param decreaseDays
     *            number of days to subtract
     * @return the date result
     */
    public static Date prependDate(final Date date, final long decreaseDays) {

        final long newMillisDate = date.getTime()
                - DateUtil.daysToMillis(decreaseDays);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(newMillisDate);

        return calendar.getTime();
    }

    /**
     * postponeDate one day
     * 
     * @param date
     *            the date
     * @return the date result
     */
    public static Date nextDay(final Date date) {
        return DateUtil.postponeDate(date, 1);
    }

    /**
     * calcularPeriodoLabel.
     * 
     * @param initialDate
     *            initial date
     * @param finalDate
     *            final date
     * @return an String with the number of days or hours (5D, 3D, 1D, 23H,
     *         4H...)
     */
    public static String calcularPeriodoLabel(final Date initialDate,
            final Date finalDate) {

        String result = "";

        if (initialDate != null && finalDate != null) {

            final Long periodMillis = calcularPeriodoEnMillis(initialDate,
                    finalDate);
            final Integer days = calcularPeriodoEnDias(periodMillis);
            final Integer hours = calcularPeriodoEnHoras(periodMillis);

            if (days > 0) {
                result = days + "D";
            } else {
                result = hours + "H";
            }
        }

        return result;
    }

    /**
     * calcula el período de dos fechas en milisegundos
     * 
     * @param initialDate
     * @param finalDate
     * @return
     */
    public static Long calcularPeriodoEnMillis(final Date initialDate,
            final Date finalDate) {
        long result = -1;

        if (initialDate != null && finalDate != null) {
            final long date1Ms = initialDate.getTime();
            final long date2Ms = finalDate.getTime();

            result = date2Ms - date1Ms;
        }

        return result;
    }

    /**
     * calcula el período de dos fechas en días
     * 
     * @param initialDate
     * @param finalDate
     * @return
     */
    public static Integer calcularPeriodoEnDias(final Date initialDate,
            final Date finalDate) {

        final Long periodMillis = calcularPeriodoEnMillis(initialDate,
                finalDate);

        return calcularPeriodoEnDias(periodMillis);
    }

    /**
     * calcula el período de dos fechas en días
     * 
     * @param periodMillis
     * @return
     */
    public static Integer calcularPeriodoEnDias(final Long periodMillis) {
        int result = -1;

        if (periodMillis > 0) {
            result = (int) Math.floor(periodMillis / (1000 * 60 * 60 * 24));
        }

        return result;
    }

    /**
     * calcula el período de dos fechas en horas
     * 
     * @param initialDate
     * @param finalDate
     * @return
     */
    public static Integer calcularPeriodoEnHoras(final Date initialDate,
            final Date finalDate) {

        final Long periodMillis = calcularPeriodoEnMillis(initialDate,
                finalDate);
        return calcularPeriodoEnHoras(periodMillis);
    }

    /**
     * calcula el período de dos fechas en horas
     * 
     * @param periodMillis
     * @return
     */
    public static Integer calcularPeriodoEnHoras(final Long periodMillis) {
        int result = -1;
        if (periodMillis > 0) {
            result = (int) Math.floor(periodMillis / (1000 * 60 * 60));
        }
        return result;
    }

    /**
     * Devuelve el día de la semana de un <code>Date</code>
     * 
     * @param aDate
     * @return
     */
    public static int getDayOfWeek(final Date aDate) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(aDate);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Devuelve <code>true</code> si un <code>Date</code> corresponde a un día
     * del fin de semana.
     * 
     * @param aDate
     * @return
     */
    public static boolean isWeekend(final Date aDate) {
        final int dayOfWeek = DateUtil.getDayOfWeek(aDate);

        return (dayOfWeek == Calendar.SATURDAY)
                || (dayOfWeek == Calendar.SUNDAY);
    }

    /**
     * Devuelve <code>true</code> si dos objetos de tipo <code>Calendar</code>
     * son coincidentes en el campo <code>field</code>
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqualCalendarFields(final Calendar calendar1,
            final Calendar calendar2, final int field) {
        return calendar1.get(field) == calendar2.get(field);
    }

    /**
     * Devuelve <code>true</code> si dos objetos de tipo <code>Calendar</code>
     * son coincidentes en fecha, sin tener en cuenta la hora
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqualCalendarsByFecha(final Calendar calendar1,
            final Calendar calendar2) {

        return (DateUtil.isEqualCalendarFields(calendar1, calendar2,
                Calendar.YEAR))
                && (DateUtil.isEqualCalendarFields(calendar1, calendar2,
                        Calendar.DAY_OF_YEAR));
    }

    /**
     * Devuelve <code>true</code> si dos objetos de tipo <code>Date</code> son
     * coincidentes en fecha, sin tener en cuenta la hora
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqualDatesByFecha(final Date date1, final Date date2) {

        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return date1.equals(date2)
                || DateUtil.isEqualCalendarsByFecha(calendar1, calendar2);
    }

    /**
     * Este metodo intenta corregir las carencias del compareTo de Date que
     * lanza un ClassCastException. Compara dos fechas.
     * 
     * @since 3'
     * @param fecha1
     *            primera fecha a comparar
     * @param fecha2
     *            segunda fecha a comparar
     * @return -1 si la primera fecha es menor a la segunda. 0 si son iguales. 1
     *         si la primera fecha es posterior a la segunda
     */
    public static int compararFechas(Date fecha1, Date fecha2) {

        if (fecha1.before(fecha2)) {
            return -1;
        } else if (fecha1.equals(fecha2)) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Incluye en una fecha la hora y el minuto
     * 
     * @param fecha
     * @param hour
     * @param minute
     * @return
     */
    public static Date getFechaHora(Date fecha, Integer hour, Integer minute) {
        Date result = null;

        if (fecha != null) {
            final Calendar calendar = Calendar.getInstance();

            calendar.setTime(fecha);

            if (hour != null) {
                calendar.set(Calendar.HOUR, hour);
            }

            if (minute != null) {
                calendar.set(Calendar.MINUTE, minute);
            }

            result = calendar.getTime();
        }

        return result;
    }

    /**
     * Devuelve la hora de una fecha
     * 
     * @param fecha
     * @return
     */
    public static Long getHoras(Date fecha) {
        return getCalendarComponent(fecha, Calendar.HOUR_OF_DAY);
    }

    /**
     * Devuelve el minuto de una fecha
     * 
     * @param fecha
     * @return
     */
    public static Long getMinutos(Date fecha) {
        return getCalendarComponent(fecha, Calendar.MINUTE);
    }

    /**
     * Devuelve el valor de un componente de fecha
     * 
     * @param fecha
     * @param component
     * @return
     */
    public static Long getCalendarComponent(Date fecha, int component) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);

        final Integer value = calendar.get(component);

        return value.longValue();
    }

    /**
     * Devuelve el valor de hora o minuto para ser cargado en los combos de
     * horas
     * 
     * @param value
     * @return
     */
    public static String getComboValueHoraMinuto(Long value) {
        final StringBuilder result = new StringBuilder();

        if (value < 10) {
            result.append("0");
        }

        result.append(value);

        return result.toString();
    }

    public static XMLGregorianCalendar dateToXMLGregorianCalendar(Date date) {
        XMLGregorianCalendar xmlgc = null;
        if (date != null) {
            final GregorianCalendar gc = (new GregorianCalendar());
            gc.setTime(date);

            try {
                final DatatypeFactory dtf = DatatypeFactory.newInstance();
                xmlgc = dtf.newXMLGregorianCalendar(gc);
            } catch (final Exception e) {
                // do nothing
            }
        }
        return xmlgc;
    }

    public static boolean isLaborable(Date iFecha, String laborables) {
        boolean laborable = false;
        final int dayOfWeek = DateUtil.getDayOfWeek(iFecha);
        if (dayOfWeek == Calendar.MONDAY) {
            laborable = (laborables.contains(Constants.LUNES));
        } else if (dayOfWeek == Calendar.TUESDAY) {
            laborable = (laborables.contains(Constants.MARTES));
        } else if (dayOfWeek == Calendar.WEDNESDAY) {
            laborable = (laborables.contains(Constants.MIERCOLES));
        } else if (dayOfWeek == Calendar.THURSDAY) {
            laborable = (laborables.contains(Constants.JUEVES));
        } else if (dayOfWeek == Calendar.FRIDAY) {
            laborable = (laborables.contains(Constants.VIERNES));
        } else if (dayOfWeek == Calendar.SATURDAY) {
            laborable = (laborables.contains(Constants.SABADO));
        } else if (dayOfWeek == Calendar.SUNDAY) {
            laborable = (laborables.contains(Constants.DOMINGO));
        }
        return laborable;
    }
}
