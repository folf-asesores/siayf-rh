/*
 * PrenominaReporteTextoPlano.java
 * Creado el 28/Jun/2017 11:44:57 AM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class PrenominaReporteTextoPlano {

    private static final Logger LOGGER = Logger.getLogger(PrenominaReporteTextoPlano.class.getName());

    private static final Integer LINEAS_HOJA = 66;

    private static final Locale LOCALIZACION_MEXICO = new Locale("es", "MX");
    private static final String PATRON_ENCABEZADO_SALUD = "\n                                                                                                         SALUD DE TLAXCALA                                                                                             PÁGINA: %1$ ,7d\n";
    private static final String ENCABEZADO_SUBDIRECCION = "                                                                                                  SUBDIRECCION DE RECURSOS HUMANOS\n";
    private static final String PATRON_ENCABEZADO_SISTEMA = "                                                                                               SISTEMA DE ADMINISTRACION DE PERSONAL\n";
    private static final String PATRON_ENCABEZADO_DEL_PROGRAMA = "                                                                            NOMINA DE %1$s CORRESPONDIENTE A LA %2$s QUINCENA DE %3$tB DE %3$tY\n";
    private static final String ENCABEZADO_LINEA_DE_DIVISION = " ======================================================================================================================================================================================================================================\n";
    private static final String ENCABEZADO_TITULOS_DE_LAS_COLUMNAS = "                                                                                                                                                                                                                            NETO\n No.     R.F.C.               N   O   M   B   R   E                           PERIODO DE PAGO                      CL   DESCRIPCIÓN                   PERCEPCIÓN     CL   DESCRIPCIÓN                   DEDUCCIÓN         A PAGAR\n";
    private static final String PATRON_UNIDAD_RESPONSABLE = "\n     %1s ( %2d )\n";
    private static final String PATRON_DETALLE_PRIMERA_PARTE = " %1$ ,4d  %2$.13s   %3$-48s  %4$td-%4$tb-%4$tY AL %5$td-%5$tb-%5$tY";
    private static final String PATRON_DETALLE_PERCEPCIONES_MISMA_LINEA = "%1$ 18d   %2$-26s   %3$ ,11.2f";
    private static final String PATRON_DETALLE_DEDUCCIONES_MISMA_LINEA = "%1$ 7d   %2$-26s   %3$ ,11.2f";
    private static final String PATRON_DETALLE_PERCEPCIONES_NUEVA_LINEA = "\n%1$ 117d   %2$-26s   %3$ ,11.2f";
    private static final String PATRON_DETALLE_DEDUCCIONES_NUEVA_LINEA = "%1$ 7d   %2$-26s   %3$ ,11.2f";
    private static final String PATRON_DETALLE_TOTALES = "\n%1$ ,160.2f%2$ ,50.2f%3$ ,15.2f";

    private int numeroLineasEncabezado = 0;
    private int numeroLineasDetalle = 0;

    public byte[] generar(ProductoNomina productoNomina) {
        byte[] bytes = null;

        try {
            Path pathReporteTemporal = Files.createTempFile("prenomina", ".txt");

            Integer lineasTotales = 1;
            Integer lineasHojaActual = 1;
            Integer numeroHoja = 1;
            Integer lineasRequeridasFinHoja = 0;

            try(BufferedWriter out = Files.newBufferedWriter(pathReporteTemporal, ArchivoUtil.UTF8_CHARSET)) {

                for(Programa programa : productoNomina) {
                    for (UnidadResponsable unidadResponsable : programa) {
                        lineasHojaActual = lineasTotales % LINEAS_HOJA;
                        if (lineasHojaActual == 1) {
                            out.write(getEncabezado(numeroHoja, programa.getPrograma(), productoNomina.getQuincena(), productoNomina.getFechaPago(), unidadResponsable.getUnidadResponsable(), unidadResponsable.getNumeroUnidadResponsable()));
                            lineasTotales += numeroLineasEncabezado;
                        }

                        int ordinal = 1;

                        for (NominaEmpleado nominaEmpleado : unidadResponsable) {
                            lineasHojaActual = lineasTotales % LINEAS_HOJA;
                            String detalle = getDetalle(ordinal, nominaEmpleado.getRfc(), nominaEmpleado.getNombre(), programa.getInicioPeriodo(), programa.getFinPeriodo(), nominaEmpleado.getPercepciones(), nominaEmpleado.getDeducciones());
                            LOGGER.infov("Lineas ocupara: {0}", numeroLineasDetalle);
                            lineasRequeridasFinHoja = lineasHojaActual + numeroLineasDetalle;

                            if (lineasRequeridasFinHoja >= LINEAS_HOJA) {
                                int lineasRestantes = LINEAS_HOJA - lineasHojaActual;
                                out.write(agregarLineasEnBlanco(lineasRestantes));
                                lineasTotales += lineasRestantes;
                                numeroHoja++;

                                // Escribe de nuevo el encabezado
                                out.write(getEncabezado(numeroHoja, programa.getPrograma(), productoNomina.getQuincena(), productoNomina.getFechaPago(), unidadResponsable.getUnidadResponsable(), unidadResponsable.getNumeroUnidadResponsable()));
                                lineasTotales += numeroLineasEncabezado;
                            }

                            out.write(detalle);
                            lineasTotales += numeroLineasDetalle;

                            numeroHoja = lineasTotales / LINEAS_HOJA;
                            ordinal++;
                        }
                    }
                }
            }

            bytes = Files.readAllBytes(pathReporteTemporal);
            Files.delete(pathReporteTemporal);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }

        return bytes;
    }

    private String getEncabezado(int numeroPagina, String programa, String quincena, Date fechaPago, String unidadResponsable, int numeroUnidadResponsable) {
        StringBuilder sb = new StringBuilder();
        sb.append((new Formatter()).format(PATRON_ENCABEZADO_SALUD, numeroPagina).toString());
        sb.append(ENCABEZADO_SUBDIRECCION);
        sb.append(PATRON_ENCABEZADO_SISTEMA);
        sb.append((new Formatter(LOCALIZACION_MEXICO)).format(PATRON_ENCABEZADO_DEL_PROGRAMA, programa, quincena, fechaPago).toString().toUpperCase());
        sb.append(ENCABEZADO_LINEA_DE_DIVISION);
        sb.append(ENCABEZADO_TITULOS_DE_LAS_COLUMNAS);
        sb.append(ENCABEZADO_LINEA_DE_DIVISION);
        sb.append('\n');
        sb.append((new Formatter()).format(PATRON_UNIDAD_RESPONSABLE, unidadResponsable, numeroUnidadResponsable).toString());
        sb.append('\n');
        sb.append('\n');

        String encabezado = sb.toString();
        Pattern pattern = Pattern.compile("\n");
        Matcher  matcher = pattern.matcher(encabezado);

        if (numeroLineasEncabezado != 0) {
            numeroLineasEncabezado = 0;
        }

        while (matcher.find())
            numeroLineasEncabezado++;

        return encabezado;
    }

    private String getDetalle(int ordinal, String rfc, String nombre, Date inicioPeriodoPago, Date finPeriodoPago, List<Percepcion> percepciones, List<Deduccion> deducciones) {
        StringBuilder sb = new StringBuilder();
        sb.append((new Formatter(LOCALIZACION_MEXICO)).format(PATRON_DETALLE_PRIMERA_PARTE, ordinal, rfc, nombre, inicioPeriodoPago, finPeriodoPago).toString().toUpperCase());
        if (percepciones != null && deducciones != null) {
            BigDecimal totalPercepciones = BigDecimal.ZERO;
            BigDecimal totalDeducciones = BigDecimal.ZERO;
            int limiteContador = percepciones.size() > deducciones.size()
                    ? percepciones.size() : deducciones.size();

            for (int i = 0; i < limiteContador; i++) {
                Percepcion percepcion = i < percepciones.size() ? percepciones.get(i) : null;

                if (percepcion != null && i == 0) {
                    sb.append((new Formatter()).format(PATRON_DETALLE_PERCEPCIONES_MISMA_LINEA, percepcion.getClave(), percepcion.getNombre(), percepcion.getMonto()));
                    totalPercepciones = totalPercepciones.add(percepcion.getMonto());
                } else if (percepcion != null) {
                    sb.append((new Formatter()).format(PATRON_DETALLE_PERCEPCIONES_NUEVA_LINEA, percepcion.getClave(), percepcion.getNombre(), percepcion.getMonto()));
                    totalPercepciones = totalPercepciones.add(percepcion.getMonto());
                }

                Deduccion deduccion = i < deducciones.size() ? deducciones.get(i) : null;

                if (deduccion != null && i == 0) {
                    sb.append((new Formatter()).format(PATRON_DETALLE_DEDUCCIONES_MISMA_LINEA, deduccion.getClave(), deduccion.getNombre(), deduccion.getMonto()));
                    totalDeducciones = totalDeducciones.add(deduccion.getMonto());
                } else if (deduccion != null) {
                    if (percepciones.size() < deducciones.size() && i >= percepciones.size()) {
                        sb.append("\n                                                                                                                                                                ");
                    }
                    sb.append((new Formatter()).format(PATRON_DETALLE_DEDUCCIONES_NUEVA_LINEA, deduccion.getClave(), deduccion.getNombre(), deduccion.getMonto()));
                    totalDeducciones = totalDeducciones.add(deduccion.getMonto());
                }
            }
            sb.append((new Formatter()).format(PATRON_DETALLE_TOTALES, totalPercepciones, totalDeducciones, totalPercepciones.subtract(totalDeducciones)));
            sb.append('\n');
        } else {
            sb.append('\n');
        }
        sb.append('\n');

        String detalle = sb.toString();
        Pattern pattern = Pattern.compile("\n");
        Matcher  matcher = pattern.matcher(detalle);

        if (numeroLineasDetalle != 0) {
            numeroLineasDetalle = 0;
        }

        while (matcher.find())
            numeroLineasDetalle++;

        return detalle;
    }

    private String agregarLineasEnBlanco(int lineasRestantes) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < lineasRestantes; i++) {
            sb.append('\n');
        }
        return sb.toString();
    }
}
