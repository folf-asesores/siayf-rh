/*
 * AlmacenReportesJasperReports.java
 * Creado el 9/Sep/2016 1:37:04 PM
 *
 */

package mx.gob.saludtlax.rh.reportes.jasperreports;

import java.util.HashMap;
import java.util.Map;

import mx.gob.saludtlax.rh.reportes.AlmacenReportes;

/**
 * @author Eduardo Mex
 *
 */
public class AlmacenReportesJasperReports implements AlmacenReportes<JasperReporte> {

    private static final Map<String, JasperReporte> REPORTES = new HashMap<>();

    static {
        JasperReporte comprobanteFiscal = new JasperReporte("comprobante-fiscal.jrxml", "plantillas/jasperreports/");
        JasperReporte comprobanteFiscalPercepciones = new JasperReporte("comprobante-fiscal__percepciones.jrxml",
                "plantillas/jasperreports/");
        JasperReporte comprobanteFiscalDeducciones = new JasperReporte("comprobante-fiscal__deducciones.jrxml",
                "plantillas/jasperreports/");
        comprobanteFiscal.agregarSubreporte("SUB_REPORTE_PERCEPCIONES", comprobanteFiscalPercepciones);
        comprobanteFiscal.agregarSubreporte("SUB_REPORTE_DEDUCCIONES", comprobanteFiscalDeducciones);
        comprobanteFiscal.agregarParametro("ID_COMPROBANTE", Integer.class);
        REPORTES.put("comprobante-fiscal", comprobanteFiscal);

        JasperReporte formatoFiliacion = new JasperReporte("FORMATO_FILIACION.jrxml", "reportes/");
        formatoFiliacion.agregarParametro("ID_EMPLEADO", Integer.class);
//        formatoFiliacion.agregarParametro("ID_FORMATO", Integer.class);
        REPORTES.put("formato-filiacion", formatoFiliacion);

        // Reportes de control de asistencia.
        JasperReporte _8001Descuentos = new JasperReporte("8001-descuentos.jrxml", "reportes/");
        _8001Descuentos.agregarParametro("ID_ADSCRIPCION", Integer.class);
        _8001Descuentos.agregarParametro("TIPO_CONTRATACION", Integer.class);
        _8001Descuentos.agregarParametro("FECHA_INICIO", String.class);
        _8001Descuentos.agregarParametro("FECHA_FIN", String.class);
        _8001Descuentos.agregarParametro("QUINCENA_APLICACION_MOVIMIENTO", Integer.class);
        _8001Descuentos.agregarParametro("ANYO_APLICACION_MOVIMIENTO", Integer.class);
        REPORTES.put("8001-descuentos", _8001Descuentos);

        JasperReporte _9201PrimaDominical = new JasperReporte("9201-prima_dominical.jrxml", "reportes/");
        _9201PrimaDominical.agregarParametro("ID_ADSCRIPCION", Integer.class);
        _9201PrimaDominical.agregarParametro("TIPO_CONTRATACION", Integer.class);
        _9201PrimaDominical.agregarParametro("FECHA_INICIO", String.class);
        _9201PrimaDominical.agregarParametro("FECHA_FIN", String.class);
        _9201PrimaDominical.agregarParametro("QUINCENA_APLICACION_MOVIMIENTO", Integer.class);
        _9201PrimaDominical.agregarParametro("ANYO_APLICACION_MOVIMIENTO", Integer.class);
        REPORTES.put("9201-prima_dominical", _9201PrimaDominical);

        JasperReporte _9204Trimestral = new JasperReporte("9204-trimestral.jrxml", "reportes/");
        _9204Trimestral.agregarParametro("ID_ADSCRIPCION", Integer.class);
        _9204Trimestral.agregarParametro("TIPO_CONTRATACION", Integer.class);
        _9204Trimestral.agregarParametro("FECHA_INICIO", String.class);
        _9204Trimestral.agregarParametro("FECHA_FIN", String.class);
        _9204Trimestral.agregarParametro("QUINCENA_APLICACION_MOVIMIENTO", Integer.class);
        _9204Trimestral.agregarParametro("ANYO_APLICACION_MOVIMIENTO", Integer.class);
        REPORTES.put("9204-trimestral", _9204Trimestral);

        JasperReporte _9205Anual = new JasperReporte("9205-anual.jrxml", "reportes/");
        _9205Anual.agregarParametro("ID_ADSCRIPCION", Integer.class);
        _9205Anual.agregarParametro("TIPO_CONTRATACION", Integer.class);
        _9205Anual.agregarParametro("FECHA_INICIO", String.class);
        _9205Anual.agregarParametro("FECHA_FIN", String.class);
        _9205Anual.agregarParametro("QUINCENA_APLICACION_MOVIMIENTO", Integer.class);
        _9205Anual.agregarParametro("ANYO_APLICACION_MOVIMIENTO", Integer.class);
        REPORTES.put("9205-anual", _9205Anual);

        JasperReporte _9207AsistenciaPerfecta = new JasperReporte("9207-asistencia_perfecta.jrxml", "reportes/");
        _9207AsistenciaPerfecta.agregarParametro("ID_ADSCRIPCION", Integer.class);
        _9207AsistenciaPerfecta.agregarParametro("TIPO_CONTRATACION", Integer.class);
        _9207AsistenciaPerfecta.agregarParametro("FECHA_INICIO", String.class);
        _9207AsistenciaPerfecta.agregarParametro("FECHA_FIN", String.class);
        _9207AsistenciaPerfecta.agregarParametro("QUINCENA_APLICACION_MOVIMIENTO", Integer.class);
        _9207AsistenciaPerfecta.agregarParametro("ANYO_APLICACION_MOVIMIENTO", Integer.class);
        REPORTES.put("9207-asistencia_perfecta", _9207AsistenciaPerfecta);

        JasperReporte reporteUnicoIncidencias = new JasperReporte("reporte_unico_incidencias.jrxml", "reportes/");
        reporteUnicoIncidencias.agregarParametro("ID_ADSCRIPCION", Integer.class);
        reporteUnicoIncidencias.agregarParametro("TIPO_CONTRATACION", Integer.class);
        reporteUnicoIncidencias.agregarParametro("FECHA_INICIO", String.class);
        reporteUnicoIncidencias.agregarParametro("FECHA_FIN", String.class);
        REPORTES.put("reporte_unico_incidencias", reporteUnicoIncidencias);

        JasperReporte listadoFirmas = new JasperReporte("listado-firmas.jrxml", "reportes/");
        listadoFirmas.agregarParametro("ID_PRODUCTO_NOMINA", Integer.class);
        REPORTES.put("listado-firmas", listadoFirmas);

//        JasperReporte prenominaEventualesPercepciones = new JasperReporte("prenomina_eventuales--percepciones.jrxml", "reportes/");
//        JasperReporte prenominaEventualesPercepcionesTotal = new JasperReporte("prenomina_eventuales--percepciones-total.jrxml", "reportes/");
//        JasperReporte prenominaEventualesPercepcionesCentroResponsabilidad = new JasperReporte("prenomina_eventuales--percepciones-centro-responsabilidad.jrxml", "reportes/");
//        JasperReporte prenominaEventualesPercepcionesPrograma = new JasperReporte("prenomina_eventuales--percepciones-programa.jrxml", "reportes/");
//        JasperReporte prenominaEventualesDeduccciones = new JasperReporte("prenomina_eventuales--deducciones.jrxml", "reportes/");
//        JasperReporte prenominaEventualesDeducccionesTotal = new JasperReporte("prenomina_eventuales--deducciones-total.jrxml", "reportes/");
//        JasperReporte prenominaEventualesDeducccionesCentroResponsabilidad = new JasperReporte("prenomina_eventuales--deducciones-centro-responsabilidad.jrxml", "reportes/");
//        JasperReporte prenominaEventualesDeducccionesPrograma = new JasperReporte("prenomina_eventuales--deducciones-programa.jrxml", "reportes/");

//        JasperReporte prenominaEventuales = new JasperReporte("prenomina_eventuales.jrxml", "reportes/");

//        prenominaEventuales.agregarParametro("ID_PRODUCTO_NOMINA", Integer.class);
//        prenominaEventuales.agregarSubreporte("SUB_REPORTE_PERCEPCIONES", prenominaEventualesPercepciones);
//        prenominaEventuales.agregarSubreporte("SUB_REPORTE_PERCEPCIONES_TOTAL", prenominaEventualesPercepcionesTotal);
//        prenominaEventuales.agregarSubreporte("SUB_REPORTE_PERCEPCIONES_CENTRO_RESPONSABILIDAD", prenominaEventualesPercepcionesCentroResponsabilidad);
//        prenominaEventuales.agregarSubreporte("SUB_REPORTE_PERCEPCIONES_PROGRAMA", prenominaEventualesPercepcionesPrograma);
//        prenominaEventuales.agregarSubreporte("SUB_REPORTE_DEDUCCIONES", prenominaEventualesDeduccciones);
//        prenominaEventuales.agregarSubreporte("SUB_REPORTE_DEDUCCIONES_TOTAL", prenominaEventualesDeducccionesTotal);
//        prenominaEventuales.agregarSubreporte("SUB_REPORTE_DEDUCCIONES_CENTRO_RESPONSABILIDAD", prenominaEventualesDeducccionesCentroResponsabilidad);
//        prenominaEventuales.agregarSubreporte("SUB_REPORTE_DEDUCCIONES_PROGRAMA", prenominaEventualesDeducccionesPrograma);
//        REPORTES.put("prenomina_eventuales", prenominaEventuales);

        JasperReporte nominaFederalesPercepciones = new JasperReporte("nomina_federales--percepciones.jrxml", "reportes/");
        JasperReporte nominaFederalesPercepcionesTotal = new JasperReporte("nomina_federales--percepciones-total.jrxml", "reportes/");
        JasperReporte nominaFederalesPercepcionesCentroResponsabilidad = new JasperReporte("nomina_federales--percepciones-centro-responsabilidad.jrxml", "reportes/");

        JasperReporte nominaFederalesDeducccionesTotal = new JasperReporte("nomina_federales--deducciones-total.jrxml", "reportes/");
        JasperReporte nominaFederalesDeducccionesCentroResponsabilidad = new JasperReporte("nomina_federales--deducciones-centro-responsabilidad.jrxml", "reportes/");
        JasperReporte nominaFederalesDeduccciones = new JasperReporte("nomina_federales--deducciones.jrxml", "reportes/");
        JasperReporte nominaFederales = new JasperReporte("nomina_federales.jrxml", "reportes/");

        nominaFederales.agregarParametro("ID_PRODUCTO_NOMINA", Integer.class);
        nominaFederales.agregarSubreporte("SUB_REPORTE_PERCEPCIONES", nominaFederalesPercepciones);
        nominaFederales.agregarSubreporte("SUB_REPORTE_PERCEPCIONES_CENTRO_RESPONSABILIDAD", nominaFederalesPercepcionesCentroResponsabilidad);
        nominaFederales.agregarSubreporte("SUB_REPORTE_PERCEPCIONES_TOTAL", nominaFederalesPercepcionesTotal);
        nominaFederales.agregarSubreporte("SUB_REPORTE_DEDUCCIONES", nominaFederalesDeduccciones);
        nominaFederales.agregarSubreporte("SUB_REPORTE_DEDUCCIONES_CENTRO_RESPONSABILIDAD", nominaFederalesDeducccionesCentroResponsabilidad);
        nominaFederales.agregarSubreporte("SUB_REPORTE_DEDUCCIONES_TOTAL", nominaFederalesDeducccionesTotal);
        REPORTES.put("nomina_federales", nominaFederales);

        JasperReporte nominaSuplencia = new JasperReporte("nomina_suplencias.jrxml", "reportes/");
        JasperReporte nominaSuplenciaPercepciones = new JasperReporte("nomina_suplencias--percepciones.jrxml", "reportes/");
        JasperReporte nominaSuplenciaPercepcionesCentroResponsabilidad = new JasperReporte("nomina_suplencias--percepciones-centro-responsabilidad.jrxml", "reportes/");
        JasperReporte nominaSuplenciaPercepcionesTotal = new JasperReporte("nomina_suplencias--percepciones-total.jrxml", "reportes/");
        JasperReporte nominaSuplenciaDeduccciones = new JasperReporte("nomina_suplencias--deducciones.jrxml", "reportes/");
        JasperReporte nominaSuplenciaDeducccionesCentroResponsabilidad = new JasperReporte("nomina_suplencias--deducciones-centro-responsabilidad.jrxml", "reportes/");
        JasperReporte nominaSuplenciaDeducccionesTotal = new JasperReporte("nomina_suplencias--deducciones-total.jrxml", "reportes/");
        nominaSuplencia.agregarSubreporte("SUB_REPORTE_PERCEPCIONES", nominaSuplenciaPercepciones);
        nominaSuplencia.agregarSubreporte("SUB_REPORTE_PERCEPCIONES_CENTRO_RESPONSABILIDAD", nominaSuplenciaPercepcionesCentroResponsabilidad);
        nominaSuplencia.agregarSubreporte("SUB_REPORTE_PERCEPCIONES_TOTAL", nominaSuplenciaPercepcionesTotal);
        nominaSuplencia.agregarSubreporte("SUB_REPORTE_DEDUCCIONES", nominaSuplenciaDeduccciones);
        nominaSuplencia.agregarSubreporte("SUB_REPORTE_DEDUCCIONES_CENTRO_RESPONSABILIDAD", nominaSuplenciaDeducccionesCentroResponsabilidad);
        nominaSuplencia.agregarSubreporte("SUB_REPORTE_DEDUCCIONES_TOTAL", nominaSuplenciaDeducccionesTotal);
        nominaSuplencia.agregarParametro("ID_PRODUCTO_NOMINA", Integer.class);
        REPORTES.put("nomina_suplencias", nominaSuplencia);
    }

    @Override
    public JasperReporte obtenerReporte(String nombreReporte) {
        return REPORTES.get(nombreReporte);
    }

    @Override
    public boolean extisteReporte(String nombreReporte) {
        return REPORTES.containsKey(nombreReporte);
    }

}
