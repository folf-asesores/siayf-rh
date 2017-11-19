/*
 * PrenominaReporteTextoPlanoTools.java
 * Creado el 03/Jul/2017 2:02:27 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.NumeroALetra;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class PrenominaReporteTextoPlanoTools {

    private static final String ENCABEZADO_DIRECCION = "SALUD DE TLAXCALA";
    private static final String ENCABEZADO_SUBDIRECCION = "SUBDIRECCIÓN DE RECURSOS HUMANOS";
    private static final String ENCABEZADO_SISTEMA = "SISTEMA DE ADMINISTRACIÓN DE PERSONAL";
    private static final String ENCABEZADO_TITULOS_DE_LAS_COLUMNAS = "                                                                                                                                                                                                                            NETO\n No.     R.F.C.               N   O   M   B   R   E                           PERIODO DE PAGO                      CL   DESCRIPCIÓN                   PERCEPCIÓN     CL   DESCRIPCIÓN                   DEDUCCIÓN         A PAGAR\n";
    private static final String PATRON_ENCABEZADO_DEL_PROGRAMA = "NÓMINA DE %1$S CORRESPONDIENTE A LA %2$S QUINCENA DE %3$TB DE %3$TY\n";
    private static final String PATRON_ENCABEZADO_NUMERO_DE_PAGINA = "PÁGINA: %1$ ,7d\n";
    private static final String PATRON_UNIDAD_RESPONSABLE = "\n     %1s ( %2s )\n";
    private static final String PATRON_DETALLE_PRIMERA_PARTE = " %1$ ,4d  %2$.13s   %3$-48.48s  %4$td-%4$Tb-%4$tY AL %5$td-%5$Tb-%5$tY";
    private static final String PATRON_DETALLE_DEDUCCIONES_MISMA_LINEA = "%1$7S   %2$-26.26S   %3$ ,11.2f";
    private static final String PATRON_DETALLE_DEDUCCIONES_NUEVA_LINEA = "%1$7S   %2$-26.26S   %3$ ,11.2f";
    private static final String PATRON_DETALLE_PERCEPCIONES_NUEVA_LINEA = "\n%1$117S   %2$-26.26S   %3$ ,14.2f";
    private static final String PATRON_DETALLE_PERCEPCIONES_MISMA_LINEA = "%1$18S   %2$-26.26S   %3$ ,14.2f";
    private static final String PATRON_DETALLE_TOTALES = "\n%1$ ,163.2f%2$ ,50.2f%3$ ,15.2f";
    private static final String PATRON_TOTALES_PERCEPCIONES = "%1$117S   %2$-25S   %3$ ,15.2f";
    private static final String PATRON_TOTALES_DEDUCCIONES = "%1$4S   %2$-25S   %3$ ,15.2f\n";
    private static final String PATRON_GRAN_TOTAL = "%1$ ,163.2f%2$ ,50.2f%3$ ,15.2f\n";
    private static final String PATRON_RESUMEN_DE_TOTALES = "     %1$-37S%2$ ,121.2f%3$ ,50.2f%4$ ,15.2f\n";
    private static final String PATRON_RESUMEN_EMPLEADOS_PROCESADOS = "\n     %1$-29S%2$ 73d\n";

    protected static final short TOTAL_NOMINA_POR_UNIDAD = 1;
    protected static final short TOTAL_NOMINA_POR_PROGRAMA = 2;
    protected static final short TOTAL_NOMINA_GENERAL = 3;

    protected String getEncabezado(int numeroPagina, String programa,
            String quincena, Date fechaPago) {
        return getEncabezado(numeroPagina, programa, quincena, fechaPago, null,
                null);
    }

    protected String getEncabezado(int numeroPagina, String programa,
            String quincena, Date fechaPago, String unidadResponsable,
            String numeroUnidadResponsable) {
        String encabezado;

        try (Formatter formatter = new Formatter()) {
            formatter.format("\n");
            formatter.format(agregarEspacios(105));
            formatter.format(ENCABEZADO_DIRECCION);
            formatter.format(agregarEspacios(93));
            formatter.format(PATRON_ENCABEZADO_NUMERO_DE_PAGINA, numeroPagina);
            formatter.format(agregarEspacios(98));
            formatter.format(ENCABEZADO_SUBDIRECCION);
            formatter.format("\n");
            formatter.format(agregarEspacios(95));
            formatter.format(ENCABEZADO_SISTEMA);
            formatter.format("\n");
            formatter.format(agregarEspacios(76));
            formatter.format(FechaUtil.LUGAR_MEXICO,
                    PATRON_ENCABEZADO_DEL_PROGRAMA, programa, quincena,
                    fechaPago);
            formatter.format(getLineaDivision());
            formatter.format(ENCABEZADO_TITULOS_DE_LAS_COLUMNAS);
            formatter.format(getLineaDivision());

            if (unidadResponsable != null && numeroUnidadResponsable != null) {
                formatter.format("\n");
                formatter.format(PATRON_UNIDAD_RESPONSABLE, unidadResponsable,
                        numeroUnidadResponsable);
            }

            formatter.format("\n");
            formatter.format("\n");

            encabezado = formatter.toString();
        }

        return encabezado;
    }

    protected String getDetalle(int ordinal, String rfc, String nombre,
            Date inicioPeriodoPago, Date finPeriodoPago,
            List<PercepcionDTO> percepciones, List<DeduccionDTO> deducciones) {
        String detalle;

        try (Formatter formatter = new Formatter()) {
            formatter.format(FechaUtil.LUGAR_MEXICO,
                    PATRON_DETALLE_PRIMERA_PARTE, ordinal, rfc, nombre,
                    inicioPeriodoPago, finPeriodoPago);

            if (percepciones != null && !percepciones.isEmpty()
                    && deducciones != null && !deducciones.isEmpty()) {
                BigDecimal totalPercepciones = BigDecimal.ZERO;
                BigDecimal totalDeducciones = BigDecimal.ZERO;
                int limiteContador = percepciones.size() > deducciones.size()
                        ? percepciones.size() : deducciones.size();

                for (int i = 0; i < limiteContador; i++) {
                    PercepcionDTO percepcion = i < percepciones.size()
                            ? percepciones.get(i) : null;

                    if (percepcion != null && i == 0) {
                        formatter.format(
                                PATRON_DETALLE_PERCEPCIONES_MISMA_LINEA,
                                percepcion.getClave(), percepcion.getNombre(),
                                percepcion.getMonto());
                        totalPercepciones = totalPercepciones
                                .add(percepcion.getMonto());
                    } else if (percepcion != null) {
                        formatter.format(
                                PATRON_DETALLE_PERCEPCIONES_NUEVA_LINEA,
                                percepcion.getClave(), percepcion.getNombre(),
                                percepcion.getMonto());
                        totalPercepciones = totalPercepciones
                                .add(percepcion.getMonto());
                    }

                    DeduccionDTO deduccion = i < deducciones.size()
                            ? deducciones.get(i) : null;

                    if (deduccion != null && i == 0) {
                        formatter.format(PATRON_DETALLE_DEDUCCIONES_MISMA_LINEA,
                                deduccion.getClave(), deduccion.getNombre(),
                                deduccion.getMonto());
                        totalDeducciones = totalDeducciones
                                .add(deduccion.getMonto());
                    } else if (deduccion != null) {
                        if (percepciones.size() < deducciones.size()
                                && i >= percepciones.size()) {
                            formatter.format("\n");
                            formatter.format(agregarEspacios(160));
                        }

                        formatter.format(PATRON_DETALLE_DEDUCCIONES_NUEVA_LINEA,
                                deduccion.getClave(), deduccion.getNombre(),
                                deduccion.getMonto());
                        totalDeducciones = totalDeducciones
                                .add(deduccion.getMonto());
                    }
                }

                formatter.format(PATRON_DETALLE_TOTALES, totalPercepciones,
                        totalDeducciones,
                        totalPercepciones.subtract(totalDeducciones));
                formatter.format("\n");
            } else if (percepciones != null && !percepciones.isEmpty()
                    && deducciones == null) {
                BigDecimal totalPercepciones = BigDecimal.ZERO;

                for (int i = 0; i < percepciones.size(); i++) {
                    PercepcionDTO percepcion = percepciones.get(i);

                    if (percepcion != null && i == 0) {
                        formatter.format(
                                PATRON_DETALLE_PERCEPCIONES_MISMA_LINEA,
                                percepcion.getClave(), percepcion.getNombre(),
                                percepcion.getMonto());
                        totalPercepciones = totalPercepciones
                                .add(percepcion.getMonto());
                    } else if (percepcion != null) {
                        formatter.format(
                                PATRON_DETALLE_PERCEPCIONES_NUEVA_LINEA,
                                percepcion.getClave(), percepcion.getNombre(),
                                percepcion.getMonto());
                        totalPercepciones = totalPercepciones
                                .add(percepcion.getMonto());
                    }
                }

                formatter.format(PATRON_DETALLE_TOTALES, totalPercepciones,
                        BigDecimal.ZERO, totalPercepciones);
                formatter.format("\n");
            } else if (deducciones != null && !deducciones.isEmpty()
                    && percepciones == null) {
                BigDecimal totalDeducciones = BigDecimal.ZERO;

                for (int i = 0; i < deducciones.size(); i++) {
                    DeduccionDTO deduccion = deducciones.get(i);

                    if (deduccion != null && i == 0) {
                        formatter.format(agregarEspacios(64));
                        formatter.format(PATRON_DETALLE_DEDUCCIONES_MISMA_LINEA,
                                deduccion.getClave(), deduccion.getNombre(),
                                deduccion.getMonto());
                        totalDeducciones = totalDeducciones
                                .add(deduccion.getMonto());
                    } else if (deduccion != null) {
                        formatter.format(agregarEspacios(163));
                        formatter.format("\n");
                        formatter.format(PATRON_DETALLE_DEDUCCIONES_NUEVA_LINEA,
                                deduccion.getClave(), deduccion.getNombre(),
                                deduccion.getMonto());
                        totalDeducciones = totalDeducciones
                                .add(deduccion.getMonto());
                    }
                }

                formatter.format(PATRON_DETALLE_TOTALES, BigDecimal.ZERO,
                        totalDeducciones,
                        BigDecimal.ZERO.subtract(totalDeducciones));
                formatter.format("\n");
            } else {
                formatter.format("\n");
            }

            formatter.format("\n");
            detalle = formatter.toString();
        }

        return detalle;
    }

    protected String getTotales(short tipoNomina,
            Map<String, BigDecimal> percepciones,
            Map<String, BigDecimal> deducciones, int totalEmpleados,
            boolean pension) {
        // Percepciones
        BigDecimal honorariosAsimilares = BigDecimal.ZERO;
        BigDecimal honorarios = BigDecimal.ZERO;
        BigDecimal diasEconomicos = BigDecimal.ZERO;
        BigDecimal suplencias = BigDecimal.ZERO;
        BigDecimal percepcionComplementaria = BigDecimal.ZERO;
        BigDecimal valesFinAnyo = BigDecimal.ZERO;
        BigDecimal aguinaldo = BigDecimal.ZERO;
        BigDecimal subsiodio = BigDecimal.ZERO;
        BigDecimal primaVacacional = BigDecimal.ZERO;
        BigDecimal bonoFaltas = BigDecimal.ZERO;
        BigDecimal retroactivo = BigDecimal.ZERO;
        BigDecimal otros = BigDecimal.ZERO;
        BigDecimal totalPercepciones = BigDecimal.ZERO;

        for (Map.Entry<String, BigDecimal> percepcion : percepciones
                .entrySet()) {
            String clave = percepcion.getKey();
            BigDecimal monto = percepcion.getValue();

            switch (clave) {
                case "01":
                    honorariosAsimilares = monto;
                    break;
                case "02":
                    honorarios = monto;
                    break;
                case "05":
                    suplencias = monto;
                    break;
                case "08":
                    diasEconomicos = monto;
                    break;
                case "14":
                    percepcionComplementaria = monto;
                    break;
                case "17":
                    valesFinAnyo = monto;
                    break;
                case "24":
                    aguinaldo = monto;
                    break;
                case "26":
                    subsiodio = monto;
                    break;
                case "27":
                    primaVacacional = monto;
                    break;
                case "29":
                    bonoFaltas = monto;
                    break;
                case "30":
                    retroactivo = monto;
                    break;
                case "32":
                    otros = monto;
                    break;
            }

            totalPercepciones = totalPercepciones.add(monto);
        }

        // Deducciones
        BigDecimal faltasRetardos = BigDecimal.ZERO;
        BigDecimal isr = BigDecimal.ZERO;
        BigDecimal responsabilidades = BigDecimal.ZERO;
        BigDecimal prestamos = BigDecimal.ZERO;
        BigDecimal embargoSalario = BigDecimal.ZERO;
        BigDecimal pensionAlimenticia = BigDecimal.ZERO;
        BigDecimal totalDeducciones = BigDecimal.ZERO;

        for (Map.Entry<String, BigDecimal> deduccion : deducciones.entrySet()) {
            String clave = deduccion.getKey();
            BigDecimal monto = deduccion.getValue();

            switch (clave) {
                case "51":
                    faltasRetardos = monto;
                    break;
                case "52":
                    isr = monto;
                    break;
                case "53":
                    responsabilidades = monto;
                    break;
                case "55":
                    prestamos = monto;
                    break;
                case "56":
                    embargoSalario = monto;
                    break;
                case "62":
                    pensionAlimenticia = monto;
                    break;
            }

            totalDeducciones = totalDeducciones.add(monto);
        }

        String totales;

        try (Formatter totalesFormatter = new Formatter()) {
            totalesFormatter.format("\n");
            totalesFormatter
                    .format(PATRON_TOTALES_PERCEPCIONES, "01",
                            !pension ? "HONORARIOS ASIMILARES A S"
                                    : "PENSIÓN ALIMENTICIA",
                            honorariosAsimilares);
            totalesFormatter.format(PATRON_TOTALES_DEDUCCIONES, "51",
                    "FALTAS Y RETARDOS", faltasRetardos);

            totalesFormatter.format(PATRON_TOTALES_PERCEPCIONES, "02",
                    "HONORARIOS", honorarios);
            totalesFormatter.format(PATRON_TOTALES_DEDUCCIONES, "52", "I.S.R.",
                    isr);

            totalesFormatter.format(PATRON_TOTALES_PERCEPCIONES, "05",
                    "SUPLENCIAS", suplencias);
            totalesFormatter.format(PATRON_TOTALES_DEDUCCIONES, "53",
                    "RESPONSABILIDADES", responsabilidades);

            totalesFormatter.format(PATRON_TOTALES_PERCEPCIONES, "08",
                    "DÍAS ECONÓMICOS", diasEconomicos);
            totalesFormatter.format(PATRON_TOTALES_DEDUCCIONES, "55",
                    "PRESTAMOS", prestamos);

            totalesFormatter.format(PATRON_TOTALES_PERCEPCIONES, "14",
                    "PERCEPCIÓN COMPLEMENTARIA", percepcionComplementaria);
            totalesFormatter.format(PATRON_TOTALES_DEDUCCIONES, "56",
                    "EMBARGO DE SALARIO", embargoSalario);

            totalesFormatter.format(PATRON_TOTALES_PERCEPCIONES, "17",
                    "VALES FIN DE AÑO", valesFinAnyo);
            totalesFormatter.format(PATRON_TOTALES_DEDUCCIONES, "62",
                    "PENSIÓN ALIMENTICIA", pensionAlimenticia);

            totalesFormatter.format(PATRON_TOTALES_PERCEPCIONES, "24",
                    "AGUINALDO", aguinaldo);
            totalesFormatter.format("\n");
            totalesFormatter.format(PATRON_TOTALES_PERCEPCIONES, "26",
                    "SUBSIDIO", subsiodio);
            totalesFormatter.format("\n");
            totalesFormatter.format(PATRON_TOTALES_PERCEPCIONES, "27",
                    "P. VACACIONAL", primaVacacional);
            totalesFormatter.format("\n");
            totalesFormatter.format(PATRON_TOTALES_PERCEPCIONES, "29",
                    "B. DE FALTAS", bonoFaltas);
            totalesFormatter.format("\n");
            totalesFormatter.format(PATRON_TOTALES_PERCEPCIONES, "30",
                    "RETROACTIVO", retroactivo);
            totalesFormatter.format("\n");
            totalesFormatter.format(PATRON_TOTALES_PERCEPCIONES, "32", "OTROS",
                    otros);
            totalesFormatter.format("\n");
            totalesFormatter.format(PATRON_GRAN_TOTAL, totalPercepciones,
                    totalDeducciones,
                    totalPercepciones.subtract(totalDeducciones));
            totalesFormatter.format("\n");

            String totalPor;
            String totalEmpleadosProcesados;
            switch (tipoNomina) {
                case TOTAL_NOMINA_POR_UNIDAD:
                    totalPor = "TOTAL POR CENTRO DE RESPONSABILIDAD :";
                    totalEmpleadosProcesados = "TOTAL DE EMPLEADOS POR CENTRO";
                    break;
                case TOTAL_NOMINA_POR_PROGRAMA:
                    totalPor = "TOTAL POR TIPO DE NOMINA :";
                    totalEmpleadosProcesados = "TOTAL DE EMPLEADOS POR NOMINA";
                    break;
                case TOTAL_NOMINA_GENERAL:
                    totalPor = "TOTALES GENERALES DE LA NOMINA :";
                    totalEmpleadosProcesados = "TOTAL DE EMPLEADOS PROCESADOS";
                    break;
                default:
                    totalPor = "TOTALES POR :";
                    totalEmpleadosProcesados = "TOTAL DE EMPLEADOS PROCESADOS";
                    break;
            }

            totalesFormatter.format(PATRON_RESUMEN_DE_TOTALES, totalPor,
                    totalPercepciones, totalDeducciones,
                    totalPercepciones.subtract(totalDeducciones));
            totalesFormatter.format(PATRON_RESUMEN_EMPLEADOS_PROCESADOS,
                    totalEmpleadosProcesados, totalEmpleados);

            totales = totalesFormatter.toString();
        }

        return totales;
    }

    protected String getFirmas(BigDecimal total, String nombreElaboro,
            String cargoElaboro, String nombreReviso, String cargoReviso,
            String nombreAutorizo, String cargoAutorizo) {
        String firmas;

        try (Formatter firmasFormatter = new Formatter()) {
            String numeroConLetras = NumeroALetra.convertNumberToLetter(total,
                    true);
            firmasFormatter.format("\n");
            firmasFormatter.format(agregarEspacios(6));
            firmasFormatter.format(
                    "EL IMPORTE TOTAL DE LA NOMINA ES DE (%1$s)\n",
                    numeroConLetras);
            firmasFormatter.format(agregarEspacios(37));
            firmasFormatter.format("E  L  A  B  O  R  O");
            firmasFormatter.format(agregarEspacios(38));
            firmasFormatter.format("R  E  V  I  S  O");
            firmasFormatter.format(agregarEspacios(39));
            firmasFormatter.format("A  U  T  O  R  I  Z  O\n");
            firmasFormatter.format("\n");
            firmasFormatter.format("\n");
            firmasFormatter.format(getLineasFirma());

            firmasFormatter.format(agregarEspacios(30));
            firmasFormatter.format(centrarTexto(nombreElaboro, 35));
            firmasFormatter.format(agregarEspacios(20));
            firmasFormatter.format(centrarTexto(nombreReviso, 35));
            firmasFormatter.format(agregarEspacios(20));
            firmasFormatter.format(centrarTexto(nombreAutorizo, 35));
            firmasFormatter.format("\n");

            firmasFormatter.format(agregarEspacios(30));
            firmasFormatter.format(centrarTexto(cargoElaboro, 35));
            firmasFormatter.format(agregarEspacios(20));
            firmasFormatter.format(centrarTexto(cargoReviso, 35));
            firmasFormatter.format(agregarEspacios(20));
            firmasFormatter.format(centrarTexto(cargoAutorizo, 35));
            firmasFormatter.format("\n");

            firmas = firmasFormatter.toString();
        }

        return firmas;
    }

    protected static int contadorDeLineas(String texto) {
        Pattern pattern = Pattern.compile("\n");
        Matcher matcher = pattern.matcher(texto);
        int numeroLineas = 0;

        while (matcher.find()) {
            numeroLineas++;
        }

        return numeroLineas;
    }

    protected static String agregarEspacios(int numeroEspacios) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numeroEspacios; i++) {
            sb.append(' ');
        }

        return sb.toString();
    }

    protected static String agregarLineasEnBlanco(int lineasRestantes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lineasRestantes; i++) {
            sb.append('\n');
        }
        return sb.toString();
    }

    private String getLineaDivision() {
        StringBuilder sb = new StringBuilder();
        sb.append(' ');
        int numeroCaracteres = 229;

        for (int i = 0; i < numeroCaracteres; i++) {
            sb.append('=');
        }
        sb.append(' ');
        sb.append('\n');

        return sb.toString();
    }

    private String getLineasFirma() {
        StringBuilder sb = new StringBuilder();
        sb.append(' ');
        int numeroCaracteres = 31;

        for (int i = 0; i < 3; i++) {
            int espacios;

            switch (i) {
                case 0:
                    espacios = 30;
                    break;
                case 1:
                    espacios = 24;
                    break;
                case 2:
                    espacios = 25;
                    break;
                default:
                    espacios = 10;
                    break;

            }

            sb.append(agregarEspacios(espacios));
            for (int j = 0; j < numeroCaracteres; j++) {
                sb.append('_');
            }
        }

        sb.append('\n');

        return sb.toString();
    }

    protected static String centrarTexto(String texto, int limite) {
        if (texto == null) {
            return null;
        }

        if (texto.length() > limite) {
            return texto.substring(0, limite);
        }

        String patron = "%" + limite + "s%s%" + limite + "s";
        String salida = String.format(patron, "", texto, "");
        float mitad = (salida.length() / 2);
        float inicio = mitad - (limite / 2);
        float fin = inicio + limite;
        return salida.substring((int) inicio, (int) fin);
    }
}
