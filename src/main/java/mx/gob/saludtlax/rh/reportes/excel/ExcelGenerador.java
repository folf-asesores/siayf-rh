/*
 * ExcelGenerador.java
 * Creado el Sep 23, 2016 7:21:13 PM
 * 
 */
package mx.gob.saludtlax.rh.reportes.excel;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import mx.gob.saludtlax.rh.acumulados.AcumuladoExcel;
import mx.gob.saludtlax.rh.acumulados.AcumuladosDTO;
import mx.gob.saludtlax.rh.empleados.detallesempleado.DetalleEmpleado;
import mx.gob.saludtlax.rh.empleados.detallesempleado.DetalleEmpleadoDTO;
import mx.gob.saludtlax.rh.empleados.movimientos.reportes.ComisionadoLicenciaExcel;
import mx.gob.saludtlax.rh.empleados.movimientos.reportes.ComisionadoLicenciaExcelDTO;
import mx.gob.saludtlax.rh.empleados.movimientos.reportes.ConsentradoAltaBajaExcel;
import mx.gob.saludtlax.rh.empleados.movimientos.reportes.ConsentradoAltaBajaExcelDTO;
import mx.gob.saludtlax.rh.empleados.movimientos.reportes.MovimientoEmpleadoReporteService;
import mx.gob.saludtlax.rh.empleados.suplencia.relacionpersonal.RelacionPersonalSuplente;
import mx.gob.saludtlax.rh.empleados.suplencia.relacionpersonal.RelacionPersonalSuplenteDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.nomina.historialpago.HistorialPago;
import mx.gob.saludtlax.rh.nomina.historialpago.HistorialPagoDetalleDTO;
import mx.gob.saludtlax.rh.nomina.productosnomina.EnumEstatusProductoNomina;
import mx.gob.saludtlax.rh.nomina.productosnomina.ProductoNomina;
import mx.gob.saludtlax.rh.nomina.reportes.dispersion.Dispersion;
import mx.gob.saludtlax.rh.nomina.reportes.pagogeneral.PagoGeneralReporte;
import mx.gob.saludtlax.rh.nomina.reportes.productonomina.ProductosNominaExcelDTO;
import mx.gob.saludtlax.rh.presupuesto.DistribucionPresupuestoDTO;
import mx.gob.saludtlax.rh.presupuesto.DistribucionPresupuestoEJB;
import mx.gob.saludtlax.rh.presupuesto.ProyeccionesPresupuestalesDTO;
import mx.gob.saludtlax.rh.presupuesto.ProyeccionesPresupuestalesEJB;
import mx.gob.saludtlax.rh.presupuesto.ReporteDistribucionPresupuesto;
import mx.gob.saludtlax.rh.reportes.Generador;
import mx.gob.saludtlax.rh.reporteslaborales.detallesempleado.DetalleEmpleadoExcel;
import mx.gob.saludtlax.rh.reporteslaborales.historialpago.HistorialPagoExcel;
import mx.gob.saludtlax.rh.reporteslaborales.productonomina.ProductoNominaExcel;
import mx.gob.saludtlax.rh.reporteslaborales.proyeccion.ContratoExcel;
import mx.gob.saludtlax.rh.reporteslaborales.proyeccion.ContratoProyeccionExcel;
import mx.gob.saludtlax.rh.reporteslaborales.relacionpersonalsuplente.RelacionPersonalSuplenteExcel;
import mx.gob.saludtlax.rh.siif.ConsultaNominaService;
import mx.gob.saludtlax.rh.siif.seguropopular.SeguroPopularReporte;
import mx.gob.saludtlax.rh.util.FechaUtil;

import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 * @author Eduardo Mex
 */
public class ExcelGenerador implements Generador, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5384835789936086358L;

    private static final Logger LOGGER = Logger.getLogger(ExcelGenerador.class);

    private static final String PATRON_FECHA_BASE_DE_DATOS = "yyyy-MM-dd";
    private static final String CONSULTA_NOMINA_SERVICE_BEAN = "java:module/ConsultaNominaService";
    private static final String CONSULTA_COMISIONADO_LICENCIA_SERVICE_BEAN = "java:module/MovimientoEmpleadoReporteService";
    private static final String SEGURO_POPULAR_REPORTE_BEAN = "java:module/SeguroPopularReporteEJB";
    private static final String PROYECCIONES_PRESUPUESTALES_BEAN = "java:module/ProyeccionesPresupuestalesEJB";
    private static final String DETALLE_EMPLEADO_BEAN = "java:module/DetalleEmpleadoEJB";
    private static final String PRODUCTO_NOMINA_BEAN = "java:module/ProductoNominaEJB";
    private static final String HISTORIAL_PAGO_BEAN = "java:module/HistorialPagoEJB";
    private static final String RELACION_PERSONAL_SUPLENTE_BEAN = "java:module/RelacionPersonalSuplenteEJB";
    private static final String DISPERSION_BEAN = "java:module/DispersionEJB";
    private static final String PAGO_GENERAL_BEAN = "java:module/PagoGeneralReporteEJB";
    private static final String DISTRIBUCION_PRESUPUESTO_BEAN = "java:module/DistribucionPresupuestoEJB";

    @Override
    public byte[] obtenerReporte(Map<String, String> parametros) {
        AlmacenReportesExcel almacenReportesExcel = new AlmacenReportesExcel();
        String nombreReporte = parametros.get("REPORTE_NOMBRE");
        byte[] bytes = null;

        if (almacenReportesExcel.extisteReporte(nombreReporte)) {
            switch (nombreReporte) {
                case "acumulados": {
                    Integer tipoNombramiento = Integer.parseInt(parametros.get("TIPO_NOMBRAMIENTO"));
                    Integer quincenaInicial = Integer.parseInt(parametros.get("QUINCENA_INICIAL"));
                    Integer quincenaFinal = Integer.parseInt(parametros.get("QUINCENA_FINAL"));
                    Integer anio = Integer.parseInt(parametros.get("ANIO_REAL"));

                    List<AcumuladosDTO> detalles = getConsultaNominaService()
                            .listaConsultaNominaPorNombramiento(tipoNombramiento, quincenaInicial, quincenaFinal, anio);
                    AcumuladoExcel acumuladosExcel = new AcumuladoExcel();
                    bytes = acumuladosExcel.generar(detalles);
                }
                break;

                case "comisionado_licencia": {
                    Date fechaInicial = FechaUtil.getFecha(
                            parametros.get("FECHA_INICIAL"),
                            PATRON_FECHA_BASE_DE_DATOS);
                    Date fechaFinal = FechaUtil.getFecha(
                            parametros.get("FECHA_FINAL"),
                            PATRON_FECHA_BASE_DE_DATOS);

                    List<ComisionadoLicenciaExcelDTO> comisionadoLicenciaExcelDTOs = getMovimientoEmpleadoReporteService()
                            .listaConsultaComisionadoLicenciaPorRangoFecha(fechaInicial, fechaFinal);

                    if (!comisionadoLicenciaExcelDTOs.isEmpty()) {
                        ComisionadoLicenciaExcel comisionadoLicenciaExcel = new ComisionadoLicenciaExcel();

                        bytes = comisionadoLicenciaExcel.generar(comisionadoLicenciaExcelDTOs);
                    } else {
                        throw new ReglaNegocioException("No se encontrarón resultados, intentelo de nuevo.",
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }

                }
                break;

                case "consentrado_alta_baja": {
                    Integer idTipoContratacionConsentrado = Integer.parseInt(parametros.get("ID_TIPO_CONTRATACION"));
                    Date fechaInicial = FechaUtil.getFecha(
                            parametros.get("FECHA_INICIAL"),
                            PATRON_FECHA_BASE_DE_DATOS);
                    Date fechaFinal = FechaUtil.getFecha(
                            parametros.get("FECHA_FINAL"),
                            PATRON_FECHA_BASE_DE_DATOS);

                    List<ConsentradoAltaBajaExcelDTO> consentradoAltaBajaExcelDTOs = getMovimientoEmpleadoReporteService()
                            .listaConsultaConsentradoAltaBajaPorRangoFecha(idTipoContratacionConsentrado, fechaInicial,
                                    fechaFinal);

                    if (!consentradoAltaBajaExcelDTOs.isEmpty()) {
                        ConsentradoAltaBajaExcel consentradoAltaBajaExcel = new ConsentradoAltaBajaExcel();

                        bytes = consentradoAltaBajaExcel.generar(consentradoAltaBajaExcelDTOs);
                    } else {
                        throw new ReglaNegocioException("No se encontrarón resultados, intentelo de nuevo.",
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }

                }
                break;

                case "seguro_popular": {
                    SeguroPopularReporte seguroPopularBean = getSeguroPopularReporteBean();
                    bytes = seguroPopularBean.obtenerReporte();
                }
                break;

                case "seguro_popular_reporte": {
                    String anyo = parametros.get("ANYO");
                    Integer quincena = Integer.parseInt(parametros.get("QUINCENA"));
                    SeguroPopularReporte seguroPopularBeanReporte = getSeguroPopularReporteBean();
                    bytes = seguroPopularBeanReporte.obtenerReporte(anyo, quincena);
                }
                break;

                case "contrato_estatal_federal": {
                    Integer anyoPresupuesto = Integer.parseInt(parametros.get("ANYO_PRESUPUESTO"));
                    Integer idTipoNombramiento = Integer.parseInt(parametros.get("ID_TIPO_NOMBRAMIENTO"));
                    List<ProyeccionesPresupuestalesDTO> proyeccionesPresupuestales = getProyeccionesPresupuestalesBean()
                            .proyeccionesPresupuestales(anyoPresupuesto, idTipoNombramiento);

                    ContratoExcel contratoExcel = new ContratoExcel();

                    bytes = contratoExcel.generar(proyeccionesPresupuestales);
                }
                break;

                case "contrato_estatal_federal_proyeccion": {
                    Integer anioPresupuesto = Integer.parseInt(parametros.get("ANYO_PRESUPUESTO"));
                    Integer idTipoNombramientos = Integer.parseInt(parametros.get("ID_TIPO_NOMBRAMIENTO"));

                    List<ProyeccionesPresupuestalesDTO> proyecciones = getProyeccionesPresupuestalesBean()
                            .proyeccionesPresupuestales(anioPresupuesto, idTipoNombramientos);

                    ContratoProyeccionExcel contratoProyeccionExcel = new ContratoProyeccionExcel();

                    bytes = contratoProyeccionExcel.generar(proyecciones);
                }
                break;

                case "detalle_empleado": {

                    Integer idTipoContratacion = Integer.parseInt(parametros.get("ID_TIPO_CONTRATACION"));

                    List<DetalleEmpleadoDTO> detalleEmpleado = getDetalleEmpleadoBean()
                            .detalleEmpleadoPorIdTipoContratacion(idTipoContratacion);

                    DetalleEmpleadoExcel detalleEmpleadoExcel = new DetalleEmpleadoExcel();

                    bytes = detalleEmpleadoExcel.generar(detalleEmpleado);
                }
                break;

                case "producto_nomina": {

                    Integer idProducto = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));

                    List<ProductosNominaExcelDTO> listaProductoNomina = getProductoNomina()
                            .obtenerListaProductoNominaPorIdProducto(idProducto);

                    if (!listaProductoNomina.isEmpty()) {
                        ProductoNominaExcel productoNominaExcel = new ProductoNominaExcel();
                        bytes = productoNominaExcel.generar(listaProductoNomina);
                    } else {
                        throw new ReglaNegocioException(
                                "No se encontrarón resultados en el producto nomina: ",
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }
                }
                break;

                case "producto_nomina_estatus": {

                    Integer idProducto = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    Integer idEstatus = Integer.parseInt(parametros.get("ID_ESTATUS"));

                    List<ProductosNominaExcelDTO> listaProductoNomina = getProductoNomina()
                            .obtenerListaProductoNominaPorIdProductoEstatus(idProducto, idEstatus);

                    if (!listaProductoNomina.isEmpty()) {
                        ProductoNominaExcel productoNominaExcel = new ProductoNominaExcel();

                        bytes = productoNominaExcel.generar(listaProductoNomina);
                    } else {
                        throw new ReglaNegocioException(
                                "No se encontrarón resultados en el producto nomina con el estatus "
                                + EnumEstatusProductoNomina.obtenerEstatus(idEstatus),
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }
                }
                break;
                case "producto_nomina_suplencia": {
                    Integer idProducto = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    List<ProductosNominaExcelDTO> listaProductoNomina = getProductoNomina()
                            .obtenerListaProductoNominaPorIdProducto(idProducto);

                    if (!listaProductoNomina.isEmpty()) {
                        ProductoNominaExcel productoNominaExcel = new ProductoNominaExcel();
                        bytes = productoNominaExcel.generar(listaProductoNomina);
                    } else {
                        throw new ReglaNegocioException(
                                "No se encontrarón resultados con el identificador del producto nomina: "
                                + idProducto.toString(),
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }
                }
                break;

                case "historial_pago": {

                    Integer idEmpleado = Integer.parseInt(parametros.get("ID_EMPLEADO"));

                    List<HistorialPagoDetalleDTO> listaHistorialPago = getHistorialPago()
                            .obtenerListaHistorialPagoPorIdEmpleado(idEmpleado);

                    if (!listaHistorialPago.isEmpty()) {
                        HistorialPagoExcel historialPagoExcel = new HistorialPagoExcel();

                        bytes = historialPagoExcel.generar(listaHistorialPago);
                    } else {
                        throw new ReglaNegocioException(
                                "No se encontrarón resultados con el identificador del empleado: "
                                + idEmpleado.toString(),
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }

                }
                break;

                case "relacion_personal_suplente": {

                    Integer numeroQuincena = Integer.parseInt(parametros.get("NUMERO_QUINCENA"));
                    Integer ejercicioFiscal = Integer.parseInt(parametros.get("EJERCICIO_FISCAL"));
                    Integer idCentroResponsabilidad = Integer.parseInt(parametros.get("ID_CENTRO_RESPONSABILIDAD"));

                    List<RelacionPersonalSuplenteDTO> listaRelacionPersonalSuplenteDTOs = getRelacionPersonalSuplente()
                            .obtenerListaRelacionPersonalSuplente(numeroQuincena, ejercicioFiscal,
                                    idCentroResponsabilidad);

                    if (!listaRelacionPersonalSuplenteDTOs.isEmpty()) {

                        RelacionPersonalSuplenteExcel relacionPersonalSuplenteExcel = new RelacionPersonalSuplenteExcel();

                        bytes = relacionPersonalSuplenteExcel.generar(listaRelacionPersonalSuplenteDTOs);

                    } else {
                        throw new ReglaNegocioException("No se encontrarón resultados, intentelo de nuevo.",
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }

                }
                break;

                case "dispersion_nomina": {
                    Integer idProducto = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    bytes = getDispersion().generarReporte(idProducto, true);
                }
                break;

                case "pago_general": {
                    Integer idProducto = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    PagoGeneralReporte pagoGeneral = getBean(PagoGeneralReporte.class);
                    bytes = pagoGeneral == null ? ReporteVacio.obtenerBytes() : pagoGeneral.generarReporte(idProducto);
                }
                break;
                
                case "reporte_distribucion_presupuestal": {

                    Integer anioPresupuesto = Integer.parseInt(parametros.get("ANYO_PRESUPUESTO"));
                    Integer idTipoNombramiento = Integer.parseInt(parametros.get("ID_TIPO_NOMBRAMIENTO"));
                    Integer idDependencia = Integer.parseInt(parametros.get("DEPENDENCIA"));
                    Integer idSubfuenteFinanciamiento = Integer.parseInt(parametros.get("ID_SUBFUENTE_FINANCIAMIENTO"));

                    List<DistribucionPresupuestoDTO> listaDistribucionPresupuestoDTOs = getDistribucionPresupuestal()
                            .distribucionPresupuesto(anioPresupuesto, idTipoNombramiento,
                            		idDependencia, idSubfuenteFinanciamiento);

                    if (!listaDistribucionPresupuestoDTOs.isEmpty()) {

                    	ReporteDistribucionPresupuesto reporteDistribucionPresupuesto = new ReporteDistribucionPresupuesto();
                    	
                    	try{

                        bytes = reporteDistribucionPresupuesto.generarArchivoExcel(listaDistribucionPresupuestoDTOs);
                    	}catch(IOException ex){
                    		bytes = ReporteVacio.obtenerBytes();
                    		LOGGER.warn("Se va a generar un archivo excel vacio por error de entrada/salida");
                    	}

                    } else {
                        throw new ReglaNegocioException("No se encontrarón resultados, intentelo de nuevo.",
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }

                }
            }
        }
        return bytes;
    }

    private ConsultaNominaService getConsultaNominaService() {
        try {
            Context initContext = new InitialContext();

            ConsultaNominaService consultaNominaService = (ConsultaNominaService) initContext
                    .lookup(CONSULTA_NOMINA_SERVICE_BEAN);

            return consultaNominaService;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\n{1}", CONSULTA_NOMINA_SERVICE_BEAN, ex.getCause());
            return null;
        }
    }

    private MovimientoEmpleadoReporteService getMovimientoEmpleadoReporteService() {
        try {
            Context initContext = new InitialContext();

            MovimientoEmpleadoReporteService consultaMovimientosEmpleados = (MovimientoEmpleadoReporteService) initContext
                    .lookup(CONSULTA_COMISIONADO_LICENCIA_SERVICE_BEAN);
            return consultaMovimientosEmpleados;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\n{1}", CONSULTA_COMISIONADO_LICENCIA_SERVICE_BEAN,
                    ex.getCause());
            return null;
        }
    }

    private SeguroPopularReporte getSeguroPopularReporteBean() {
        try {
            Context initContext = new InitialContext();
            SeguroPopularReporte seguroPopularReporteBean = (SeguroPopularReporte) initContext
                    .lookup(SEGURO_POPULAR_REPORTE_BEAN);
            return seguroPopularReporteBean;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\t{1}", SEGURO_POPULAR_REPORTE_BEAN, ex.getCause());
            return null;
        }
    }

    private ProyeccionesPresupuestalesEJB getProyeccionesPresupuestalesBean() {
        try {
            Context initContext = new InitialContext();
            ProyeccionesPresupuestalesEJB proyeccionesPresupuestalesBean = (ProyeccionesPresupuestalesEJB) initContext
                    .lookup(PROYECCIONES_PRESUPUESTALES_BEAN);
            return proyeccionesPresupuestalesBean;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\t{1}", PROYECCIONES_PRESUPUESTALES_BEAN, ex.getCause());
            return null;
        }
    }

    private DetalleEmpleado getDetalleEmpleadoBean() {
        try {
            Context initContext = new InitialContext();

            DetalleEmpleado detalleEmpleado = (DetalleEmpleado) initContext.lookup(DETALLE_EMPLEADO_BEAN);

            return detalleEmpleado;

        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\t{1}", DETALLE_EMPLEADO_BEAN, ex.getCause());
            return null;
        }

    }

    private ProductoNomina getProductoNomina() {
        try {
            Context initContext = new InitialContext();

            ProductoNomina productoNomina = (ProductoNomina) initContext.lookup(PRODUCTO_NOMINA_BEAN);

            return productoNomina;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\t{1}", PRODUCTO_NOMINA_BEAN, ex.getCause());
            return null;
        }
    }

    private HistorialPago getHistorialPago() {
        try {
            Context initContext = new InitialContext();

            HistorialPago historialPago = (HistorialPago) initContext.lookup(HISTORIAL_PAGO_BEAN);

            return historialPago;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\t{1}", HISTORIAL_PAGO_BEAN, ex.getCause());
            return null;
        }
    }

    private RelacionPersonalSuplente getRelacionPersonalSuplente() {
        try {
            Context initContext = new InitialContext();

            RelacionPersonalSuplente relacionPersonalSuplente = (RelacionPersonalSuplente) initContext
                    .lookup(RELACION_PERSONAL_SUPLENTE_BEAN);

            return relacionPersonalSuplente;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\t{1}", RELACION_PERSONAL_SUPLENTE_BEAN, ex.getCause());
            return null;
        }
    }

    private Dispersion getDispersion() {
        try {
            Context initContext = new InitialContext();
            Dispersion dispersion = (Dispersion) initContext
                    .lookup(DISPERSION_BEAN);
            return dispersion;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\t{1}", DISPERSION_BEAN, ex.getCause());
            return null;
        }
    }

    private <T> T getBean(Class<T> clase) {
        String bean = "";
        try {
            switch (clase.getName()) {
                case "mx.gob.saludtlax.rh.nomina.reportes.pagogeneral.PagoGeneralReporte":
                    bean = PAGO_GENERAL_BEAN;
                    break;
                default:
                    return null;
            }

            Context initContext = new InitialContext();
            return (T) initContext.lookup(bean);
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\t{1}", bean, ex.getCause());
            return null;
        }
    }
    
    private DistribucionPresupuestoEJB getDistribucionPresupuestal() {
        try {
            Context initContext = new InitialContext();

            DistribucionPresupuestoEJB relacionPersonalSuplente = (DistribucionPresupuestoEJB) initContext
                    .lookup(DISTRIBUCION_PRESUPUESTO_BEAN);

            return relacionPersonalSuplente;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\t{1}", DISTRIBUCION_PRESUPUESTO_BEAN, ex.getCause());
            return null;
        }
    }

}
