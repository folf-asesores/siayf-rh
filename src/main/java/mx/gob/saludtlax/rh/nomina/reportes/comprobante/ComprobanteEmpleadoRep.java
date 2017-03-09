/*
 * ComprobanteEmpleadoRep.java
 * Creado el 18/Nov/2016 6:15:21 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.comprobante;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.NumeroUtil;
import org.jboss.logging.Logger;

/**
 * Esta clase ayuda en la generadion del archivo .per para imprimir los 
 * comprobantes de los empleados.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class ComprobanteEmpleadoRep implements Serializable {

    private static final long serialVersionUID = -6816011718228601345L;

    private static final Logger LOGGER = Logger.getLogger(ComprobanteEmpleadoRep.class.getName());
    private static final String PATRON_FECHA = "ddMMMyyyy";
    
    private static final short TAMANYO_MAXIMO_COLUMNA_CONCEPTO = 14;

    // Para pares
    private static final short ESPACIOS_ANTES_DEL_NOMBRE_1 = 4;
    private static final short ESPACIOS_ANTES_DE_LA_FILIACION_1 = 42;
    private static final short ESPACIOS_ANTES_DE_LA_FECHA_PAGO_1 = 40;
    private static final short ESPACIOS_ANTES_DE_LA_CLAVE_DE_CENTRO_DE_RESPONSABILIDAD_1 = 51;
    private static final short ESPACIOS_ANTES_DEL_PERIODO_DE_PAGO_1 = 16;
    private static final short ESPACIOS_ANTES_DEL_PERCEPCIONES_1 = 35;
    private static final short ESPACIOS_ANTES_DEL_DEDUCCIONES_1 = 47;
    private static final short ESPACIOS_ANTES_DEL_NETO_A_PAGAR_1 = 57;

    // Para impares
    private static final short ESPACIOS_ANTES_DEL_NOMBRE_2 = 74;
    private static final short ESPACIOS_ANTES_DE_LA_FILIACION_2 = 112;
    private static final short ESPACIOS_ANTES_DE_LA_FECHA_PAGO_2 = 110;
    private static final short ESPACIOS_ANTES_DE_LA_CLAVE_DE_CENTRO_DE_RESPONSABILIDAD_2 = 121;
    private static final short ESPACIOS_ANTES_DEL_PERIODO_DE_PAGO_2 = 85;
    private static final short ESPACIOS_ANTES_DEL_PERCEPCIONES_2 = 104;
    private static final short ESPACIOS_ANTES_DEL_DEDUCCIONES_2 = 116;
    private static final short ESPACIOS_ANTES_DEL_NETO_A_PAGAR_2 = 126;

    public byte [] obtenerArchivo(List<ComprobanteEmpleadoDTO> detalles) {
        try {
            StringBuilder sb = llenarDetalles(detalles);
            File file = File.createTempFile("comprobante", ".txt");

            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.append(sb);
                fileWriter.flush();
            }
            
            Path path = Paths.get(file.toURI());
            return Files.readAllBytes(path);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
        
        return null;
    }

    private StringBuilder llenarDetalles(List<ComprobanteEmpleadoDTO> comprobantes) throws IOException {
        Iterator<ComprobanteEmpleadoDTO> iterator = comprobantes.iterator();
        StringBuilder sb = new StringBuilder("\n");

        while (iterator.hasNext()) {
            ComprobanteEmpleadoDTO comprobante = iterator.next();
            ComprobanteEmpleadoDTO comprobanteSiguiente 
                    = iterator.hasNext() ? iterator.next() : null;
            
            agregarEspacio(ESPACIOS_ANTES_DEL_NOMBRE_1, sb);
            sb.append(comprobante.getNombre());
            agregarEspacio(obtenerEspacio(ESPACIOS_ANTES_DE_LA_FILIACION_1, ESPACIOS_ANTES_DEL_NOMBRE_1, comprobante.getNombre().length()), sb);
            sb.append(comprobante.getFiliacion());
            
            if(comprobanteSiguiente != null) {
                agregarEspacio(obtenerEspacio(ESPACIOS_ANTES_DEL_NOMBRE_2, ESPACIOS_ANTES_DE_LA_FILIACION_1, comprobante.getFiliacion().length()), sb);
                sb.append(comprobanteSiguiente.getNombre());
                agregarEspacio(obtenerEspacio(ESPACIOS_ANTES_DE_LA_FILIACION_2, ESPACIOS_ANTES_DEL_NOMBRE_2, comprobanteSiguiente.getNombre().length()), sb);
                sb.append(comprobanteSiguiente.getFiliacion());
            }

            sb.append('\n');
            sb.append('\n');

            agregarEspacio(ESPACIOS_ANTES_DE_LA_FECHA_PAGO_1, sb);
            sb.append(FechaUtil.formatearFecha(PATRON_FECHA, comprobante.getFechaPago()).toUpperCase());
            agregarEspacio(obtenerEspacio(ESPACIOS_ANTES_DE_LA_CLAVE_DE_CENTRO_DE_RESPONSABILIDAD_1, ESPACIOS_ANTES_DE_LA_FECHA_PAGO_1, PATRON_FECHA.length()), sb);
            sb.append(comprobante.getClaveCentroResponsabilidad() == null ? "" : comprobante.getClaveCentroResponsabilidad());

            if(comprobanteSiguiente != null) {
                agregarEspacio(obtenerEspacio(ESPACIOS_ANTES_DE_LA_FECHA_PAGO_2, ESPACIOS_ANTES_DE_LA_CLAVE_DE_CENTRO_DE_RESPONSABILIDAD_1, (comprobante.getClaveCentroResponsabilidad() == null ? "" : comprobante.getClaveCentroResponsabilidad()).length()), sb);
                sb.append(FechaUtil.formatearFecha(PATRON_FECHA, comprobanteSiguiente.getFechaPago()).toUpperCase());
                agregarEspacio(obtenerEspacio(ESPACIOS_ANTES_DE_LA_CLAVE_DE_CENTRO_DE_RESPONSABILIDAD_2, ESPACIOS_ANTES_DE_LA_FECHA_PAGO_2, PATRON_FECHA.length()), sb);
                sb.append(comprobanteSiguiente.getClaveCentroResponsabilidad());
            }

            sb.append('\n');
            sb.append('\n');
            
            agregarEspacio(ESPACIOS_ANTES_DEL_PERIODO_DE_PAGO_1, sb);
            sb.append(FechaUtil.formatearFecha(PATRON_FECHA, comprobante.getInicioPeriodo()).toUpperCase());
            sb.append('-');
            sb.append(FechaUtil.formatearFecha(PATRON_FECHA, comprobante.getFinPeriodo()).toUpperCase());
            sb.append(NumeroUtil.formatoMoneda(comprobante.getPercepciones()));
            agregarEspacio(obtenerEspacio(ESPACIOS_ANTES_DEL_DEDUCCIONES_1, ESPACIOS_ANTES_DEL_PERCEPCIONES_1, NumeroUtil.formatoMoneda(comprobante.getPercepciones()).length()), sb);
            sb.append(NumeroUtil.formatoMoneda(comprobante.getDeducciones()));
            agregarEspacio(obtenerEspacio(ESPACIOS_ANTES_DEL_NETO_A_PAGAR_1, ESPACIOS_ANTES_DEL_DEDUCCIONES_1, NumeroUtil.formatoMoneda(comprobante.getDeducciones()).length()), sb);
            sb.append(NumeroUtil.formatoMoneda(comprobante.getNeto()));
            
            if(comprobanteSiguiente != null) {
                agregarEspacio(obtenerEspacio(ESPACIOS_ANTES_DEL_PERIODO_DE_PAGO_2, ESPACIOS_ANTES_DEL_NETO_A_PAGAR_1, NumeroUtil.formatoMoneda(comprobante.getNeto()).length()), sb);
                sb.append(FechaUtil.formatearFecha(PATRON_FECHA, comprobanteSiguiente.getInicioPeriodo()).toUpperCase());
                sb.append('-');
                sb.append(FechaUtil.formatearFecha(PATRON_FECHA, comprobanteSiguiente.getFinPeriodo()).toUpperCase());
                sb.append(NumeroUtil.formatoMoneda(comprobanteSiguiente.getPercepciones()));
                agregarEspacio(obtenerEspacio(ESPACIOS_ANTES_DEL_DEDUCCIONES_2, ESPACIOS_ANTES_DEL_PERCEPCIONES_2, NumeroUtil.formatoMoneda(comprobanteSiguiente.getPercepciones()).length()), sb);
                sb.append(NumeroUtil.formatoMoneda(comprobanteSiguiente.getDeducciones()));
                agregarEspacio(obtenerEspacio(ESPACIOS_ANTES_DEL_NETO_A_PAGAR_2, ESPACIOS_ANTES_DEL_DEDUCCIONES_2, NumeroUtil.formatoMoneda(comprobanteSiguiente.getDeducciones()).length()), sb);
                sb.append(NumeroUtil.formatoMoneda(comprobanteSiguiente.getNeto()));
            }

            sb.append('\n');
            sb.append('\n');
            
            llenarConceptos(sb, comprobante.getConceptos(), comprobanteSiguiente == null ? null : comprobanteSiguiente.getConceptos());

            sb.append("  NO  DOBLE NI MUTILE EL CHEQUE                                         NO  DOBLE NI MUTILE EL CHEQUE\n");
            sb.append('\n');
            sb.append('\n');
            sb.append('\n');
            sb.append('\n');
        }

        return sb;
    }

    private void llenarConceptos(StringBuilder sb, List<ConceptoComprobanteDTO> conceptosA, List<ConceptoComprobanteDTO> conceptosB) {
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

        for(int i = 0; i < total; i++) {
            int aux = i % 10;
            String concepto = null;
            
            if(aux < 5) {
                concepto = countA < cantidadConceptosA 
                        ? conceptoNuevo(conceptosA.get(countA++)) 
                        : conceptoVacio();
            } 
            
            if(aux > 4) {
                concepto = countB < cantidadConceptosB 
                        ? conceptoNuevo(conceptosB.get(countB++)) 
                        : conceptoVacio();
            }

            sb.append(concepto);

            if(aux == 9) {
                sb.append('\n');
            }
        }

        sb.append('\n');
        sb.append('\n');
        sb.append('\n');
        sb.append('\n');
    }

    // TODO: Verificar la construcción de la cadena de concepto.
    private String conceptoNuevo(ConceptoComprobanteDTO concepto) {
        if(concepto != null) {
            StringBuilder sb = new StringBuilder(conceptoVacio());

            sb.replace(0, 2, String.valueOf(concepto.getClave()));
            sb.replace(3, NumeroUtil.formatoMoneda(concepto.getImporte()).length() + 3, 
                    NumeroUtil.formatoMoneda(concepto.getImporte()));

            return sb.toString();
        } else {
            return "";
        }
    }

    private String conceptoVacio() {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < TAMANYO_MAXIMO_COLUMNA_CONCEPTO; i++){
            sb.append(' ');
        }

        return  sb.toString();
    }

    private int obtenerEspacio(int posicionDestino, int ultimaPosicion, int tamanyoUltimaCadena) {
        return posicionDestino - (ultimaPosicion + tamanyoUltimaCadena);
    }
    
    private void agregarEspacio(int cantidad, StringBuilder sb) {
        for(int i = 0; i < cantidad; i++) {
            sb.append(' ');
        }
    }

}
