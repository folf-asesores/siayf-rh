/*
 * ComprobanteEmpleadoRep.java
 * Creado el 18/nov/2016 6:15:21 PM
 * 
 */

package mx.gob.saludtlax.rh.nomina.reportes.comprobante;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.util.ArchivoUtil;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.NumeroUtil;

import static mx.gob.saludtlax.rh.util.ArchivoUtil.WINDOWS_LATIN_CHARSET;

/**
 * Esta clase es el motor del reporte el cual se encarga de la generación del
 * archivo en formato de texto plano (txt) para imprimir los comprobantes
 * de los empleados.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class ComprobanteEmpleadoMotor implements Serializable {

    private static final long serialVersionUID = -6816011718228601345L;
    private static final Logger LOGGER = Logger.getLogger(ComprobanteEmpleadoMotor.class.getName());

    private static final Integer LINEAS_POR_COMPROBANTE = 18;
    private static final short TAMANYO_MAXIMO_COLUMNA_CONCEPTO = 14;

    private static final String PATRON_NOMBRE_Y_RFC = "%1$-37.37s %2$-13.13s";
    private static final String PATRON_FECHA_DE_PAGO_Y_CENTRO_DE_RESPONSABILIDAD = "%1$td%1$Tb%1$tY  %2$-4.4s";
    private static final String PATRON_FINAL_DE_LINEA = "NO  DOBLE NI MUTILE EL CHEQUE                  %1$-6.6s";
    private static final String PATRON_PERIODOS_Y_PAGOS = "%1$td%1$Tb%1$tY-%2$td%2$Tb%2$tY %3$10.10s %4$10.10s %5$10.10s";

    public byte [] obtenerArchivo(List<ComprobanteEmpleadoDTO> detalles) {
        try {
            if (!ArchivoUtil.SEPARADOR_DE_ARCHIVO_WINDOWS.equals(ArchivoUtil.SEPARADOR_DE_ARCHIVO)) {
                System.setProperty("line.separator", ArchivoUtil.SEPARADOR_DE_ARCHIVO_WINDOWS);
            }

            Path ruta = Files.createTempFile("comprobante", ".txt");
            
            try (BufferedWriter out = Files.newBufferedWriter(ruta, ArchivoUtil.WINDOWS_LATIN_CHARSET)) {
                StringBuilder sb = llenarDetalles(detalles);
                out.append(sb);
                out.flush();
            }

            ArchivoUtil.eliminarEspaciosAlFinalLinea(ruta, WINDOWS_LATIN_CHARSET);
            byte[] reporte = Files.readAllBytes(ruta);
            Files.deleteIfExists(ruta);

            System.setProperty("line.separator", ArchivoUtil.SEPARADOR_DE_ARCHIVO);
            return reporte;
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
        
        return null;
    }

    private StringBuilder llenarDetalles(List<ComprobanteEmpleadoDTO> comprobantes) throws IOException {
        int contadorLineas = 0;
        Iterator<ComprobanteEmpleadoDTO> iterator = comprobantes.iterator();
        StringBuilder sb = new StringBuilder();

        while (iterator.hasNext()) {
            ComprobanteEmpleadoDTO comprobante = iterator.next();
            ComprobanteEmpleadoDTO comprobanteSiguiente 
                    = iterator.hasNext() ? iterator.next() : null;
            
            try (Formatter formatter = new Formatter(sb, FechaUtil.LUGAR_MEXICO)) {
                agregarLineas(1, formatter);
                contadorLineas = contadorLineas + 1;
                agregarEspacio(4, formatter);
                formatter.format(PATRON_NOMBRE_Y_RFC, comprobante.getNombre(), comprobante.getFiliacion());
                
                if (comprobanteSiguiente != null) {
                    agregarEspacio(19, formatter);
                    formatter.format(PATRON_NOMBRE_Y_RFC, comprobanteSiguiente.getNombre(), comprobanteSiguiente.getFiliacion());
                }

                agregarLineas(2, formatter);
                contadorLineas = contadorLineas + 2;
                
                agregarEspacio(40, formatter);
                formatter.format(PATRON_FECHA_DE_PAGO_Y_CENTRO_DE_RESPONSABILIDAD, comprobante.getFechaPago(), comprobante.getClaveCentroResponsabilidad());
                
                if (comprobanteSiguiente != null) {
                    agregarEspacio(55, formatter);
                    formatter.format(PATRON_FECHA_DE_PAGO_Y_CENTRO_DE_RESPONSABILIDAD, comprobanteSiguiente.getFechaPago(), comprobanteSiguiente.getClaveCentroResponsabilidad());
                }

                agregarLineas(2, formatter);
                contadorLineas = contadorLineas + 2;

                agregarEspacio(16, formatter);
                formatter.format(PATRON_PERIODOS_Y_PAGOS, comprobante.getInicioPeriodo(), comprobante.getFinPeriodo(), NumeroUtil.formatoMoneda(comprobante.getPercepciones()), NumeroUtil.formatoMoneda(comprobante.getDeducciones()), NumeroUtil.formatoMoneda(comprobante.getNeto()));

                if (comprobanteSiguiente != null) {
                    agregarEspacio(18, formatter);
                    formatter.format(PATRON_PERIODOS_Y_PAGOS, comprobanteSiguiente.getInicioPeriodo(), comprobanteSiguiente.getFinPeriodo(), NumeroUtil.formatoMoneda(comprobante.getPercepciones()), NumeroUtil.formatoMoneda(comprobante.getDeducciones()), NumeroUtil.formatoMoneda(comprobanteSiguiente.getNeto()));
                }

                agregarLineas(3, formatter);
                contadorLineas = contadorLineas + 3;
                
                contadorLineas = contadorLineas + llenarConceptos(formatter, comprobante.getConceptos(), comprobanteSiguiente != null ? comprobanteSiguiente.getConceptos() : null);

                int diferencia = LINEAS_POR_COMPROBANTE - (contadorLineas % LINEAS_POR_COMPROBANTE);
                agregarLineas(diferencia - 4, formatter);
                contadorLineas = contadorLineas + diferencia - 4;
                
                agregarEspacio(2, formatter);
                formatter.format(PATRON_FINAL_DE_LINEA, comprobante.getNumeroCheque());

                if (comprobanteSiguiente != null) {
                    agregarEspacio(2, formatter);
                    formatter.format(PATRON_FINAL_DE_LINEA, comprobanteSiguiente.getNumeroCheque());
                }

                agregarLineas(4, formatter);
                contadorLineas = contadorLineas + 4;
            }
        }

        return sb;
    }
    
    private int llenarConceptos(Formatter formatter, List<ConceptoComprobanteDTO> conceptosA, List<ConceptoComprobanteDTO> conceptosB) {
        int cantidadConceptosA;
        int cantidadFilasA;
        int cantidadConceptosB;
        int cantidadFilasB;

        if(conceptosA != null) {
            cantidadConceptosA = conceptosA.size();
            cantidadFilasA = (cantidadConceptosA > 0) && ((cantidadConceptosA % 5) > 0) ? (cantidadConceptosA / 5) + 1 : (cantidadConceptosA / 5);
        } else {
            cantidadConceptosA = 0;
            cantidadFilasA = 0;
        }

        if(conceptosB != null) {
            cantidadConceptosB = conceptosB.size();
            cantidadFilasB = (cantidadConceptosB > 0) && ((cantidadConceptosB / 5) > 5.0) ? (cantidadConceptosB / 5) + 1 : (cantidadConceptosB / 5);
        } else {
            cantidadConceptosB = 0;
            cantidadFilasB = 0;
        }
        
        int total = cantidadFilasB > cantidadFilasA ? cantidadFilasB * 10 : cantidadFilasA * 10;
        int countA = 0;
        int countB = 0;
        int contadorLineas = 0;

        for(int i = 0; i < total; i++) {
            int aux = i % 10;
            
            if(aux < 5) {
                if (countA < cantidadConceptosA && conceptosA != null) {
                    agregarConcepto(conceptosA.get(countA++), formatter); 
                } else {
                    agregarEspacio(TAMANYO_MAXIMO_COLUMNA_CONCEPTO, formatter);
                }                
            } 
            
            if(aux > 4) {
                if (countB < cantidadConceptosB && conceptosB != null) {
                    agregarConcepto(conceptosB.get(countB++), formatter);
                } else {
                    agregarEspacio(TAMANYO_MAXIMO_COLUMNA_CONCEPTO, formatter);
                }
            }

            if(aux == 9) {
                agregarLineas(1, formatter);
                contadorLineas = contadorLineas + 1;
            }
        }
            
        agregarLineas(1, formatter);
        contadorLineas = contadorLineas + 1;

        return contadorLineas;
    }

    private void agregarConcepto(ConceptoComprobanteDTO concepto, Formatter formatter) {
        if(concepto != null) {
            agregarEspacio(2, formatter);
            String patronConcepto = "%1$2d %2$9.9s";
            formatter.format(patronConcepto, concepto.getClave(), NumeroUtil.formatoMoneda(concepto.getImporte()));
        } else {
            agregarEspacio(TAMANYO_MAXIMO_COLUMNA_CONCEPTO, formatter);
        }
    }
    
    private void agregarEspacio(int cantidad, Formatter formatter) {
        Appendable appendable = formatter.out();

        for (int i = 0; i < cantidad; i++){
            try {
                appendable.append(' ');
            } catch (IOException ex) {
                LOGGER.warn(ex);
            }
        }
    }

    private void agregarLineas(int cantidad, Formatter formatter) {
        Appendable appendable = formatter.out();

        for (int i = 0; i < cantidad; i++){
            try {
                appendable.append(System.getProperty("line.separator"));
            } catch (IOException ex) {
                LOGGER.warn(ex);
            }
        }
    }

}
