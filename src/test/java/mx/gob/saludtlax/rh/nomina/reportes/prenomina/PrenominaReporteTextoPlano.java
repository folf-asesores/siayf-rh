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
import java.util.HashMap;
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
            Integer lineasTotales = 1;
            Integer lineasHojaActual;
            Integer numeroHoja = 1;
            Integer lineasRequeridasFinHoja;

            try(BufferedWriter out = Files.newBufferedWriter(pathReporteTemporal, ArchivoUtil.UTF8_CHARSET)) {

                Map<Integer, BigDecimal> percepcionesProductoNomina = new HashMap<>();
                Map<Integer, BigDecimal> deduccionesProductoNomina = new HashMap<>();
                int empleadosProductoNomina = 1;
                BigDecimal granTotal = BigDecimal.ZERO;

                for(Programa programa : productoNomina) {
                    int empleadosPrograma = 1;
                    Map<Integer, BigDecimal> percepcionesPrograma = new HashMap<>();
                    Map<Integer, BigDecimal> deduccionesPrograma = new HashMap<>();

                    for (UnidadResponsable unidadResponsable : programa) {
                        lineasHojaActual = lineasTotales % LINEAS_POR_HOJA;

                        if (lineasHojaActual == 1) {
                            out.write(tools.getEncabezado(numeroHoja, programa.getPrograma(), productoNomina.getQuincena(), productoNomina.getFechaPago(), unidadResponsable.getUnidadResponsable(), unidadResponsable.getNumeroUnidadResponsable()));
                            lineasTotales += tools.getNumeroLineasEncabezado();
                        }

                        int ordinal = 1;
                        Map<Integer, BigDecimal> percepcionesUnidadResponsable = new HashMap<>();
                        Map<Integer, BigDecimal> deduccionesUnidadResponsable = new HashMap<>();

                        for (NominaEmpleado nominaEmpleado : unidadResponsable) {
                            lineasHojaActual = lineasTotales % LINEAS_POR_HOJA;
                            String detalle = tools.getDetalle(ordinal, nominaEmpleado.getRfc(), nominaEmpleado.getNombre(), programa.getInicioPeriodo(), programa.getFinPeriodo(), nominaEmpleado.getPercepciones(), nominaEmpleado.getDeducciones());

                            if (nominaEmpleado.getPercepciones() != null) for (Percepcion percepcion : nominaEmpleado.getPercepciones()) {
                                Integer clave = percepcion.getClave();
                                BigDecimal monto = percepcion.getMonto();

                                if (percepcionesUnidadResponsable.containsKey(clave)) {
                                    BigDecimal total = percepcionesUnidadResponsable.get(clave);
                                    total = total.add(monto);
                                    percepcionesUnidadResponsable.put(clave, total);
                                } else {
                                    percepcionesUnidadResponsable.put(clave, monto);
                                }
                            }

                            if (nominaEmpleado.getDeducciones() != null) for (Deduccion deduccion : nominaEmpleado.getDeducciones()) {
                                Integer clave = deduccion.getClave();
                                BigDecimal monto = deduccion.getMonto();

                                if (deduccionesUnidadResponsable.containsKey(clave)) {
                                    BigDecimal total = deduccionesUnidadResponsable.get(clave);
                                    total = total.add(monto);
                                    deduccionesUnidadResponsable.put(clave, total);
                                } else {
                                    deduccionesUnidadResponsable.put(clave, monto);
                                }
                            }

                            lineasRequeridasFinHoja = lineasHojaActual + tools.getNumeroLineasDetalle();

                            if (lineasRequeridasFinHoja >= LINEAS_POR_HOJA) {
                                int lineasRestantes = LINEAS_POR_HOJA - lineasHojaActual;
                                out.write(PrenominaReporteTextoPlanoTools.agregarLineasEnBlanco(lineasRestantes));
                                lineasTotales += lineasRestantes;
                                numeroHoja++;

                                // Escribe de nuevo el encabezado
                                out.write(tools.getEncabezado(numeroHoja, programa.getPrograma(), productoNomina.getQuincena(), productoNomina.getFechaPago(), unidadResponsable.getUnidadResponsable(), unidadResponsable.getNumeroUnidadResponsable()));
                                lineasTotales += tools.getNumeroLineasEncabezado();
                            }

                            out.write(detalle);
                            lineasTotales += tools.getNumeroLineasDetalle();

                            numeroHoja = lineasTotales / LINEAS_POR_HOJA;
                            ordinal++;
                            empleadosPrograma++;
                        }

                        // Agrega los totales por unidad responsable
                        numeroHoja = lineasTotales / LINEAS_POR_HOJA;
                        lineasTotales += tools.getNumeroLineasUnidadResponsable();
                        String totales = tools.getTotales(PrenominaReporteTextoPlanoTools.TOTAL_NOMINA_POR_UNIDAD, percepcionesUnidadResponsable, deduccionesUnidadResponsable, ordinal - 1);
                        lineasRequeridasFinHoja = lineasHojaActual + tools.getNumeroLineasUnidadResponsable();

                        if (lineasRequeridasFinHoja <= LINEAS_POR_HOJA) {

                        }
                        out.write(totales);
                    }

                    // Agrega los totales por programa
                    String totales = tools.getTotales(PrenominaReporteTextoPlanoTools.TOTAL_NOMINA_POR_PROGRAMA, percepcionesPrograma, deduccionesPrograma, empleadosPrograma - 1);
                    out.write(totales);
                }

                // Agrega los totales por programa
                String totales = tools.getTotales(PrenominaReporteTextoPlanoTools.TOTAL_NOMINA_GENERAL, percepcionesProductoNomina, deduccionesProductoNomina, empleadosProductoNomina - 1);
                out.write(totales);
                out.write('\n');

                String firmas = tools.getFirmas(granTotal, productoNomina.getNombreElaboro(), productoNomina.getCargoElaboro(), productoNomina.getNombreReviso(), productoNomina.getCargoReviso(), productoNomina.getNombreAutorizo(), productoNomina.getCargoAutorizo());
                out.write(firmas);
                out.write('\n');
            }

            bytes = Files.readAllBytes(pathReporteTemporal);
            Files.delete(pathReporteTemporal);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }

        return bytes;
    }
}
