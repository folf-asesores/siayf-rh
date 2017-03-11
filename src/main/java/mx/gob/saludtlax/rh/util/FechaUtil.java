/**
 *
 */
package mx.gob.saludtlax.rh.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;

import org.jboss.logging.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * Esta clase es una utilería que permite trabajar con la fecha y la hora, tanto
 * formateo como conversión.
 * 
 * @author Leila Schiaffini Ehuan
 * @since 07/03/2016-15:12:52
 */
public class FechaUtil {

    private static final Logger LOGGER = Logger.getLogger(FechaUtil.class.getName());
    public static final Locale LUGAR_MEXICO = new Locale("es", "MX");
    public static final int FECHAS_POR_MES = 0;
    public static final int FECHAS_POR_QUINCENA = 1;
    public static final int FECHAS_POR_SEMANA = 2;
    public static final String PATRON_FECHA_CORTA = "dd/MM/yyyy";
    public static final String PATRON_FECHA_HORA_CORTA = "dd/MM/yyyy HH:mm:ss";
    
    private FechaUtil() {
    }

    /**
     * Retorna la fecha actual en el sistema.
     *
     * @return
     */
    public static Date fechaActual() {
        DateTime fechaActual = DateTime.now();
        return fechaActual.toDate();
    }

    public static Time horaActual() {
        Time horaActual = new Time(new Date().getTime());
        return horaActual;
    }

    /**
     * Retorna la fecha actual en el sistema.
     *
     * @return
     */
    public static Date fechaActualSinHora() {
        DateTime fechaActual = fechaActualSinTiempo();
        return fechaActual.toDate();
    }

    /**
     * Retorna la fecha actual sin horas ni minutos ni segundos
     *
     * @return
     */
    public static DateTime fechaActualSinTiempo() {
        DateTime fechaActual = DateTime.now();
        fechaActual = fechaActual.withTime(0, 0, 0, 0);
        return fechaActual;
    }

    /**
     * Retorna la fecha sin horas ni minutos ni segundos
     *
     * @param fecha
     *            fecha con tiempo.
     * @return
     *
     */
    public static DateTime fechaSinTiempo(Date fecha) {
        DateTime fechaSinTiempo = new DateTime(fecha);
        fechaSinTiempo = fechaSinTiempo.withTime(0, 0, 0, 0);
        return fechaSinTiempo;
    }

/**
     * Retorna el ejercicio fiscal actual
     *
     * @return
     */
    public static int ejercicioActual() {
        int ejercicio = DateTime.now().getYear();
        return ejercicio;
    }

    /**
     * Retorna el año de la fecha
     *
     * @param fecha
     * @return
     */
    public static int anioFecha(Date fecha) {
        DateTime fechaTime = new DateTime(fecha);
        int anio = fechaTime.getYear();
        return anio;
    }

    /**
     * * Retorna la fecha con horas, minutos y segundos
     *
     * @return
     */
    public static DateTime fechaConTiempo() {
        DateTime fechaActual = DateTime.now();
        return fechaActual;
    }

    /**
     * Retorna el mes Actual
     *
     * @return
     */
    public static int mesActual() {
        int mes = DateTime.now().getMonthOfYear();
        return mes;
    }

    /**
     * Compara que la fecha ingresada no sea mayor a la actual
     *
     * @param fecha
     * @return
     */
    public static boolean validarFechaFutura(Date fecha) {
        boolean esfechaFutura = false;
        DateTime fechaIngresada = new DateTime(fecha);
        fechaIngresada.withHourOfDay(0);
        fechaIngresada.withMinuteOfHour(0);
        fechaIngresada.withSecondOfMinute(0);
        fechaIngresada.withMillisOfSecond(0);

        DateTime fechaActual = DateTime.now();
        fechaActual.withHourOfDay(0);
        fechaActual.withMinuteOfHour(0);
        fechaActual.withSecondOfMinute(0);
        fechaActual.withMillisOfSecond(0);

        if (fechaIngresada.isAfter(fechaActual)) {
            esfechaFutura = true;
        }

        return esfechaFutura;
    }

    /**
     * Este método devuelve la fecha en formato: dd/MM/yyyy.
     *
     * @param fecha una fecha.
     * @return la fecha formateada.
     */
    public static String formatoFecha(Date fecha) {
        return formatearFecha(PATRON_FECHA_CORTA, fecha);
    }

    /**
     * Este método devuelve la fecha y la hora en formato: dd/MM/yyyy HH:mm:ss
     *
     * @param fechaHora una fecha.
     * @return la fecha formateada.
     */
    public static String formatoFechaHora(Date fechaHora) {
        return formatearFecha(PATRON_FECHA_HORA_CORTA, fechaHora);
    }

    /**
     *
     * @param time
     * @return
     */
    public static String formatoHora(Time time) {
        return formatearFecha("HH:mm", time);
    }

    /**
     * Este método permite convertir una fecha según el patrón que recibe
     * siempre que el patrón sea valido.
     *
     * @param patronFecha el patrón que se seguirá para formatear la fecha.
     * @param fecha una fecha.
     * @return la fecha formateada.
     */
    public static String formatearFecha(String patronFecha, Date fecha) {
        if (fecha == null) {
            LOGGER.warn("No se puede formatear una fecha nula");
            return "";
        }

        if (patronFecha == null || patronFecha.trim().isEmpty()) {
            LOGGER.warnv(
                    "No se ha recibido un patrón por tanto se usar el patrón: \"{0}\" para transformar la fecha.", PATRON_FECHA_CORTA);
            patronFecha = PATRON_FECHA_CORTA;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(patronFecha, LUGAR_MEXICO);
        return dateFormat.format(fecha);
    }

    /**
     * Este método analiza una cadena y la transforma a un tipo fecha si el
     * formato es valido.
     *
     * @param fecha una cadena que representa una fecha u hora.
     * @param patronFecha el patrón (formato) que tiene la fecha.
     * @return si el formato es correcto una instancia de tipo fecha, en caso
     *         contrario retorna null.
     */
    public static Date getFecha(String fecha, String patronFecha) {
        if (fecha == null || patronFecha == null || fecha.trim().isEmpty() || patronFecha.trim().isEmpty()) {
            return null;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(patronFecha, LUGAR_MEXICO);
            return dateFormat.parse(fecha);
        } catch (ParseException ex) {
            LOGGER.warnv("Problemas al transformar la fecha \"{0}\" usando el patrón \"{1}\": {2}", fecha, patronFecha, ex.getCause());
            return null;
        }
    }

    /**
     *
     * @param mes el mes.
     * @param ejercicioFiscal el año.
     * @return
     */
    public static Date ultimoDiaMes(int mes, int ejercicioFiscal) {
        DateTime fecha = new DateTime(ejercicioFiscal, mes, 1, 0, 0);
        fecha = fecha.plusMonths(1);
        fecha = fecha.minusDays(1);
        return fecha.toDate();
    }

    /**
     * Regresa el primer día del Ejercicio Fiscal enviado.
     *
     * @param ejercicioFiscal
     * @return
     */
    public static Date primerDiaEjercicioFiscal(Integer ejercicioFiscal) {
        DateTime fecha = new DateTime(ejercicioFiscal, 1, 1, 0, 0);
        return fecha.toDate();
    }

    /**
     * Regresa el último día del Ejercicio Fiscal enviado.
     *
     * @param ejercicioFiscal
     * @return
     */
    public static Date ultimoDiaEjercicioFiscal(Integer ejercicioFiscal) {
        DateTime fecha = new DateTime(ejercicioFiscal, 12, 31, 0, 0);
        return fecha.toDate();
    }

    /**
     * Calcula la edad de la persona.
     * 
     * @param fechaNacimiento la fecha de nacimiento.
     * @return la edad.
     */
    public static int calcularEdad(Date fechaNacimiento) {
        int edad = 0;

        if (fechaNacimiento != null) {
            DateTime fechaNac = fechaSinTiempo(fechaNacimiento);
            DateTime fechaActual = fechaActualSinTiempo();
            int anyoNacimiento = fechaNac.getYear();
            int anyoActual = DateTime.now().getYear();
            edad = anyoActual - anyoNacimiento;

            if (fechaActual.getMonthOfYear() < fechaNac.getMonthOfYear()) {
                edad = edad - 1;
            } // Si son el mismo mes
            else if (fechaActual.getMonthOfYear() == fechaNac.getMonthOfYear()) {
                // Si aun no cumple años
                if (fechaActual.getDayOfMonth() > fechaNac.getDayOfMonth()) {
                    edad = edad - 1;
                }
            }
        }

        return edad;
    }

    /**
     * Permite la fecha de inicio de un mes, una quincena o de una semana.
     *
     * @param fecha la fecha que se tomara como base.
     * @param tipoFechas el tipo de fechas que se requiere ya sea
     *            {@link #FECHAS_POR_MES}, {@link #FECHAS_POR_QUINCENA},
     *            {@link #FECHAS_POR_SEMANA}.
     * @return un arreglo con dos valores el inicio y fin según sea el caso.
     */
    public static Date[] obtenerFechasInicioFinal(Date fecha, int tipoFechas) {
        if (fecha == null) {
            throw new ValidacionException("No se puede obtener las de inicio y fin sin una fecha origen",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        Date[] fechas = new Date[2];

        Calendar calendarInicio = Calendar.getInstance();
        calendarInicio.setTime(fecha);

        int dia = calendarInicio.get(Calendar.DAY_OF_MONTH);
        int mes = calendarInicio.get(Calendar.MONTH);
        int anyo = calendarInicio.get(Calendar.YEAR);

        if (mes < 1) {
            mes = 1;
        } else if (mes > 12) {
            mes = 12;
        } else {
            mes += 1;
        }

        switch (tipoFechas) {
        case FECHAS_POR_MES:
            calendarInicio.set(Calendar.DAY_OF_MONTH, 1);
            fechas[0] = calendarInicio.getTime();
            fechas[1] = ultimoDiaMes(mes, anyo);

            return fechas;
        case FECHAS_POR_QUINCENA:
            if (dia <= 15) {
                calendarInicio.set(Calendar.DAY_OF_MONTH, 1);
                Calendar calendarFin = Calendar.getInstance();
                calendarInicio.setTime(fecha);
                calendarInicio.set(Calendar.DAY_OF_MONTH, 15);

                fechas[0] = calendarInicio.getTime();
                fechas[1] = calendarFin.getTime();
            } else {
                calendarInicio.set(Calendar.DAY_OF_MONTH, 16);
                fechas[0] = calendarInicio.getTime();
                fechas[1] = ultimoDiaMes(mes, anyo);
            }

            return fechas;
        case FECHAS_POR_SEMANA:
            int diaSemana = calendarInicio.get(Calendar.DAY_OF_WEEK);

            switch (diaSemana) {
            case Calendar.MONDAY:
                fechas[0] = calendarInicio.getTime();
                calendarInicio.set(Calendar.DAY_OF_MONTH, calendarInicio.get(Calendar.DAY_OF_MONTH) + 6);
                fechas[1] = calendarInicio.getTime();
                break;
            case Calendar.TUESDAY:
            case Calendar.WEDNESDAY:
            case Calendar.THURSDAY:
            case Calendar.FRIDAY:
                calendarInicio.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            case Calendar.SATURDAY:
                calendarInicio.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                fechas[0] = calendarInicio.getTime();
                calendarInicio.set(Calendar.DAY_OF_MONTH, calendarInicio.get(Calendar.DAY_OF_MONTH) + 6);
                fechas[1] = calendarInicio.getTime();
                break;
            case Calendar.SUNDAY:
                fechas[1] = calendarInicio.getTime();
                calendarInicio.set(Calendar.DAY_OF_MONTH, calendarInicio.get(Calendar.DAY_OF_MONTH) - 6);
                fechas[0] = calendarInicio.getTime();
                break;
            }

            return fechas;
        default:
            return fechas;
        }
    }

    public static Date sumarDias(Date fecha, Integer dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DATE, dias);

        return cal.getTime();
    }

    public static int calcularDias(Date inicio, Date fin) {
        DateTime fechaInicio = fechaSinTiempo(inicio);
        DateTime fechaFin = fechaSinTiempo(fin);
        LOGGER.debugv("Fecha inicio: {0}", fechaInicio.toString());
        LOGGER.debugv("Fecha fin: {0}", fechaFin.toString());

        int dias = Days.daysBetween(fechaInicio.toLocalDate(), fechaFin.toLocalDate()).plus(1).getDays();

        return dias;

    }

    public static boolean esFechaInicialMayorQueFinal(Date inicio, Date fin) {
        DateTime fechaInicio = fechaSinTiempo(inicio);
        DateTime fechaFin = fechaSinTiempo(fin);

        return fechaInicio.isAfter(fechaFin);
    }

    public static Integer calcularNumeroPeriodos(Date inicio, Date fin, Integer tipoPeriodo) {
        Integer numeroPeriodos = 0;
        Calendar calendarInicio = Calendar.getInstance();
        calendarInicio.setTime(inicio);
        Calendar calendarFin = Calendar.getInstance();
        calendarFin.setTime(fin);

        int diaInicio = calendarInicio.get(Calendar.DAY_OF_MONTH);
        int mesInicio = calendarInicio.get(Calendar.MONTH);
        int anyoInicio = calendarInicio.get(Calendar.YEAR);

        int diaFin = calendarFin.get(Calendar.DAY_OF_MONTH);
        int mesFin = calendarFin.get(Calendar.MONTH);
        int anyoFin = calendarFin.get(Calendar.YEAR);

        if (anyoInicio == anyoFin) {
            if (mesInicio == mesFin) {
                if (diaInicio < diaFin) {
                    numeroPeriodos = 2;
                }
                if (diaInicio == diaFin) {
                    numeroPeriodos = 1;
                }
            } else {
                numeroPeriodos = (1 + (mesFin - mesInicio)) * 2;
                if (diaInicio == 16) {
                    numeroPeriodos--;
                }
                if (diaFin == 1) {
                    numeroPeriodos--;
                }
            }
        } else {
            numeroPeriodos = +(1 + (mesFin + (mesInicio - Calendar.DECEMBER))) * 2;
            if (diaInicio == 16) {
                numeroPeriodos--;
            }
            if (diaFin == 1) {
                numeroPeriodos--;
            }
        }
        return numeroPeriodos;
    }

    public boolean validarFechaInicioFin(Date fechaInicio, Date fechaFin) {
        DateTime fechaI = fechaSinTiempo(fechaInicio);
        DateTime fechaF = fechaSinTiempo(fechaFin);

        return fechaI.compareTo(fechaF) == 1;
    }
}
