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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class PrenominaReporteTextoPlano {

    private static final Logger LOGGER = Logger.getLogger(PrenominaReporteTextoPlano.class.getName());
    private static final Integer LINEAS_POR_HOJA = 66;
    private final PrenominaReporteTextoPlanoTools tools = new PrenominaReporteTextoPlanoTools();

    public byte[] generar(ProductoNomina productoNomina) {
        byte[] bytes = null;

        try {
            Path pathReporteTemporal = Files.createTempFile("prenomina", ".txt");
            Integer lineasTotales = 0;
            Integer lineasHojaActual;
            Integer numeroHoja = 0;

            try(BufferedWriter out = Files.newBufferedWriter(pathReporteTemporal, ArchivoUtil.UTF8_CHARSET)) {
                Map<String, BigDecimal> percepcionesGeneral = new HashMap<>();
                Map<String, BigDecimal> deduccionesGeneral = new HashMap<>();
                int totalEmpleadosGeneral = 0;
                BigDecimal granTotal = BigDecimal.ZERO;

                for(Programa programa : productoNomina) {
                    Map<String, BigDecimal> percepcionesPrograma = new HashMap<>();
                    Map<String, BigDecimal> deduccionesPrograma = new HashMap<>();

                    int ordinal = 1;
                    for (UnidadResponsable unidadResponsable : programa) {

                        // Crea el encabezado por unidad responsable
                        numeroHoja += 1;
                        String encabezadoUnidadResponsable = tools.getEncabezado(numeroHoja, programa.getPrograma(), productoNomina.getQuincena(), productoNomina.getFechaPago(), unidadResponsable.getUnidadResponsable(), unidadResponsable.getNumeroUnidadResponsable());
                        out.write(encabezadoUnidadResponsable);
                        lineasTotales += PrenominaReporteTextoPlanoTools.contadorDeLineas(encabezadoUnidadResponsable);
                        LOGGER.debugv("lineas totales (encabezado unidad responsable): {0}", lineasTotales);
                        Map<String, BigDecimal> percepcionesUnidadResponsable = new HashMap<>();
                        Map<String, BigDecimal> deduccionesUnidadResponsable = new HashMap<>();

                        for (NominaEmpleado nominaEmpleado : unidadResponsable) {
                            List<Deduccion> deducciones;
                            List<Percepcion> percepciones;

                            if (nominaEmpleado.getDeducciones() != null) {
                                deducciones = new ArrayList<>();
                                deducciones.addAll(nominaEmpleado.getDeducciones().values());
                            } else {
                                deducciones = null;
                            }

                            if (nominaEmpleado.getPercepciones() != null) {
                                percepciones = new ArrayList<>();
                                percepciones.addAll(nominaEmpleado.getPercepciones().values());
                            } else {
                                percepciones = null;
                            }

                            lineasHojaActual = lineasTotales % LINEAS_POR_HOJA;

                            String detalle = tools.getDetalle(ordinal, nominaEmpleado.getRfc(), nominaEmpleado.getNombre(), programa.getInicioPeriodo(), programa.getFinPeriodo(), percepciones, deducciones);
                            int lineasRequeridas = PrenominaReporteTextoPlanoTools.contadorDeLineas(detalle);

                            if ((lineasRequeridas + lineasHojaActual) >= LINEAS_POR_HOJA) {
                                numeroHoja += 1;
                                int lineasRestantes = LINEAS_POR_HOJA - lineasHojaActual;
                                out.write(PrenominaReporteTextoPlanoTools.agregarLineasEnBlanco(lineasRestantes));
                                lineasTotales += lineasRestantes;
                                LOGGER.debugv("lineas totales (encabezado en detalle): {0}", lineasTotales);

                                String encabezado = tools.getEncabezado(numeroHoja, programa.getPrograma(), productoNomina.getQuincena(), productoNomina.getFechaPago(), unidadResponsable.getUnidadResponsable(), unidadResponsable.getNumeroUnidadResponsable());
                                int lineasEncabezado = PrenominaReporteTextoPlanoTools.contadorDeLineas(encabezado);
                                out.write(encabezado);
                                lineasTotales += lineasEncabezado;
                                LOGGER.debugv("lineas totales (encabezado en detalle): {0}", lineasTotales);
                            }

                            out.write(detalle);
                            lineasTotales += lineasRequeridas;
                            LOGGER.debugv("lineas totales (detalle): {0}", lineasTotales);
                            ordinal++;
                        }

                        // Crea los totales por unidad responsable
                        lineasHojaActual = lineasTotales % LINEAS_POR_HOJA;
                        String totalesUnidadResponsable = tools.getTotales(PrenominaReporteTextoPlanoTools.TOTAL_NOMINA_POR_UNIDAD, percepcionesUnidadResponsable, deduccionesUnidadResponsable, ordinal - 1);
                        int lineasRequeridas = lineasHojaActual + PrenominaReporteTextoPlanoTools.contadorDeLineas(totalesUnidadResponsable);

                        if ((lineasRequeridas + lineasHojaActual) >= LINEAS_POR_HOJA) {
                            numeroHoja += 1;
                            int lineasRestantes = LINEAS_POR_HOJA - lineasHojaActual;
                            out.write(PrenominaReporteTextoPlanoTools.agregarLineasEnBlanco(lineasRestantes));
                            lineasTotales += lineasRestantes;
                            LOGGER.debugv("lineas totales (encabezado en total unidad responsable): {0}", lineasTotales);

                            String encabezado = tools.getEncabezado(numeroHoja, programa.getPrograma(), productoNomina.getQuincena(), productoNomina.getFechaPago(), unidadResponsable.getUnidadResponsable(), unidadResponsable.getNumeroUnidadResponsable());
                            int lineasEncabezado = PrenominaReporteTextoPlanoTools.contadorDeLineas(encabezado);
                            out.write(encabezado);
                            lineasTotales += lineasEncabezado;
                            LOGGER.debugv("lineas totales (encabezado en total unidad responsable): {0}", lineasTotales);
                        }

                        out.write(totalesUnidadResponsable);
                        lineasTotales += PrenominaReporteTextoPlanoTools.contadorDeLineas(totalesUnidadResponsable);
                        LOGGER.debugv("Lineas totales (total unidad responsable): {0}", lineasTotales);
                        lineasHojaActual = lineasTotales % LINEAS_POR_HOJA;
//                        LOGGER.debugv("Linea actual: {0}", lineasHojaActual);
                        int lineasRestantes = LINEAS_POR_HOJA - lineasHojaActual;
//                        LOGGER.debugv("Lineas restantes: {0}", lineasRestantes);
                        out.write(PrenominaReporteTextoPlanoTools.agregarLineasEnBlanco(lineasRestantes));
                        lineasTotales += lineasRestantes;
                    }

                    // Nueva página con un resumen del programa
                    numeroHoja += 1;
                    String encabezadoPrograma = tools.getEncabezado(numeroHoja, programa.getPrograma(), productoNomina.getQuincena(), productoNomina.getFechaPago());
                    int lineasEncabezado = PrenominaReporteTextoPlanoTools.contadorDeLineas(encabezadoPrograma);
                    out.write(encabezadoPrograma);
                    lineasTotales += lineasEncabezado;

                    String totalesPrograma = tools.getTotales(PrenominaReporteTextoPlanoTools.TOTAL_NOMINA_POR_PROGRAMA, percepcionesPrograma, deduccionesPrograma, ordinal - 1);
                    out.write(totalesPrograma);
                    lineasTotales += PrenominaReporteTextoPlanoTools.contadorDeLineas(totalesPrograma);

                    lineasHojaActual = lineasTotales % LINEAS_POR_HOJA;
                    int lineasRestantes = LINEAS_POR_HOJA - lineasHojaActual;
                    out.write(PrenominaReporteTextoPlanoTools.agregarLineasEnBlanco(lineasRestantes));
                    lineasTotales += lineasRestantes;
                }

                // Nueva página con un resumen del programa
                numeroHoja += 1;
                String encabezadoPrograma = tools.getEncabezado(numeroHoja, "-", productoNomina.getQuincena(), productoNomina.getFechaPago());
                int lineasEncabezado = PrenominaReporteTextoPlanoTools.contadorDeLineas(encabezadoPrograma);
                out.write(encabezadoPrograma);
                lineasTotales += lineasEncabezado;


                String totalesPrograma = tools.getTotales(PrenominaReporteTextoPlanoTools.TOTAL_NOMINA_GENERAL, percepcionesGeneral, deduccionesGeneral, totalEmpleadosGeneral);
                out.write(totalesPrograma);
                lineasTotales += PrenominaReporteTextoPlanoTools.contadorDeLineas(totalesPrograma);

                String firmas = tools.getFirmas(granTotal, productoNomina.getNombreElaboro(), productoNomina.getCargoElaboro(), productoNomina.getNombreReviso(), productoNomina.getCargoReviso(), productoNomina.getNombreAutorizo(), productoNomina.getCargoAutorizo());
                out.write(firmas);
            }

            bytes = Files.readAllBytes(pathReporteTemporal);
            Files.delete(pathReporteTemporal);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }

        return bytes;
    }
}
