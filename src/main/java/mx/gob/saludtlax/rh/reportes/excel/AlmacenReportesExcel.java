/*
 * AlmacenReportesExcel.java
 * Creado el Sep 23, 2016 7:17:59 PM
 * 
 */
package mx.gob.saludtlax.rh.reportes.excel;

import java.util.HashMap;
import java.util.Map;
import mx.gob.saludtlax.rh.reportes.AlmacenReportes;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class AlmacenReportesExcel implements AlmacenReportes<ExcelReporte> {

    private static final Map<String, ExcelReporte> REPORTES;

    // 1. El reporte no usa plantillas para su generación.
    static {
        ExcelReporte acumulados = new ExcelReporte(
                "acumulados--plantilla.xlsx", "plantillas/acumulados/");

        ExcelReporte comisionadoLicencia = new ExcelReporte(
                "Comisionado_Licencia.xlsx", "plantillas/comisionadoLicencia/");

        ExcelReporte consentradoAltaBaja = new ExcelReporte(
                "Concentrado_Altas_Bajas.xlsx",
                "plantillas/consentradoAltaBaja/");

        ExcelReporte seguroPopular = new ExcelReporte(
                "plantilla--seguro-popular.xlsx", "plantillas/siif/");

        ExcelReporte seguroPopularReporte = new ExcelReporte(
                "plantilla--seguro-popular.xlsx", "plantillas/siif/");

        ExcelReporte proyeccionesPresupuestalesContratoPorMes 
                = new ExcelReporte("Contrato_Estatal_Federal.xlsx",
                        "plantillas/contrato/");

        ExcelReporte proyeccionesPresupuestalesContrato = new ExcelReporte(
                "Contrato_Estatal_Federal.xlsx", "plantillas/contrato/");

        ExcelReporte detalleEmpleado = new ExcelReporte(
                "Detalle_Empleado.xlsx", "plantillas/empleado/");

        ExcelReporte productoNominaReporte = new ExcelReporte(
                "Producto_Nomina.xlsx", "plantillas/nommina/");

        ExcelReporte historialPagoReporte = new ExcelReporte(
                "Historial_Pago.xlsx", "plantillas/nommina/");

        ExcelReporte relacionPersonalSuplenteReporte = new ExcelReporte(
                "Relacion_Personal_Suplente.xlsx", "plantillas/suplencia/");

        ExcelReporte dispercionReporte = new ExcelReporte(null, null); // 1.

        REPORTES = new HashMap<>();
        REPORTES.put("acumulados", acumulados);
        REPORTES.put("comisionado_licencia", comisionadoLicencia);
        REPORTES.put("consentrado_alta_baja", consentradoAltaBaja);
        REPORTES.put("seguro_popular", seguroPopular);
        REPORTES.put("seguro_popular_reporte", seguroPopularReporte);
        REPORTES.put("contrato_estatal_federal", proyeccionesPresupuestalesContratoPorMes);
        REPORTES.put("contrato_estatal_federal_proyeccion", proyeccionesPresupuestalesContrato);
        REPORTES.put("detalle_empleado", detalleEmpleado);
        REPORTES.put("producto_nomina", productoNominaReporte);
        REPORTES.put("historial_pago", historialPagoReporte);
        REPORTES.put("relacion_personal_suplente", relacionPersonalSuplenteReporte);
        REPORTES.put("dispersion_nomina", dispercionReporte);
    }

    @Override
    public ExcelReporte obtenerReporte(String nombreReporte) {
        return REPORTES.get(nombreReporte);
    }

    @Override
    public boolean extisteReporte(String nombreReporte) {
        return REPORTES.containsKey(nombreReporte);
    }

}
