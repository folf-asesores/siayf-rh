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

import org.jboss.logging.Logger;

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
import mx.gob.saludtlax.rh.nomina.productosnomina.ProductoNominaDTO;
import mx.gob.saludtlax.rh.nomina.reportes.dispersion.Dispersion;
import mx.gob.saludtlax.rh.nomina.reportes.pagogeneral.PagoGeneralReporte;
import mx.gob.saludtlax.rh.nomina.reportes.productonomina.ProductosNominaExcelDTO;
import mx.gob.saludtlax.rh.nomina.reportes.productonomina.ProductosNominaProgramasExcelDTO;
import mx.gob.saludtlax.rh.presupuesto.DistribucionPresupuestoDTO;
import mx.gob.saludtlax.rh.presupuesto.DistribucionPresupuestoEJB;
import mx.gob.saludtlax.rh.presupuesto.ProyeccionesPresupuestalesDTO;
import mx.gob.saludtlax.rh.presupuesto.ProyeccionesPresupuestalesEJB;
import mx.gob.saludtlax.rh.presupuesto.ReporteDistribucionPresupuesto;
import mx.gob.saludtlax.rh.reportes.Generador;
import mx.gob.saludtlax.rh.reporteslaborales.detallesempleado.DetalleEmpleadoExcel;
import mx.gob.saludtlax.rh.reporteslaborales.historialpago.HistorialPagoExcel;
import mx.gob.saludtlax.rh.reporteslaborales.productonomina.ProductoNominaExcel;
import mx.gob.saludtlax.rh.reporteslaborales.productonomina.ProductoNominaProgramasExcel;
import mx.gob.saludtlax.rh.reporteslaborales.proyeccion.ContratoExcel;
import mx.gob.saludtlax.rh.reporteslaborales.proyeccion.ContratoProyeccionExcel;
import mx.gob.saludtlax.rh.reporteslaborales.relacionpersonalsuplente.RelacionPersonalSuplenteExcel;
import mx.gob.saludtlax.rh.siif.ConsultaNominaService;
import mx.gob.saludtlax.rh.siif.seguropopular.SeguroPopularReporte;
import mx.gob.saludtlax.rh.util.FechaUtil;

import static mx.gob.saludtlax.rh.util.BeanFactory.getBean;
import static mx.gob.saludtlax.rh.util.FechaUtil.PATRON_FECHA_BASE_DE_DATOS;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 */
public class ExcelGenerador implements Generador, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5384835789936086358L;

    private static final Logger LOGGER = Logger.getLogger(ExcelGenerador.class);

    @Override
    public byte[] obtenerReporte(Map<String, String> parametros) {
        AlmacenReportesExcel almacenReportesExcel = new AlmacenReportesExcel();
        String nombreReporte = parametros.get("REPORTE_NOMBRE");
        byte[] bytes = null;

        if (almacenReportesExcel.extisteReporte(nombreReporte)) {
            switch (nombreReporte) {
                case "acumulados": {
                    Integer tipoNombramiento = Integer
                            .parseInt(parametros.get("TIPO_NOMBRAMIENTO"));
                    Integer quincenaInicial = Integer
                            .parseInt(parametros.get("QUINCENA_INICIAL"));
                    Integer quincenaFinal = Integer
                            .parseInt(parametros.get("QUINCENA_FINAL"));
                    Integer anio = Integer
                            .parseInt(parametros.get("ANIO_REAL"));

                    ConsultaNominaService consultaNominaService = getBean(
                            ConsultaNominaService.class);
                    List<AcumuladosDTO> detalles = consultaNominaService
                            .listaConsultaNominaPorNombramiento(
                                    tipoNombramiento, quincenaInicial,
                                    quincenaFinal, anio);
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

                    MovimientoEmpleadoReporteService movimientoEmpleadoReporteService = getBean(
                            MovimientoEmpleadoReporteService.class);
                    List<ComisionadoLicenciaExcelDTO> comisionadoLicenciaExcelDTOs = movimientoEmpleadoReporteService
                            .listaConsultaComisionadoLicenciaPorRangoFecha(
                                    fechaInicial, fechaFinal);

                    if (!comisionadoLicenciaExcelDTOs.isEmpty()) {
                        ComisionadoLicenciaExcel comisionadoLicenciaExcel = new ComisionadoLicenciaExcel();

                        bytes = comisionadoLicenciaExcel
                                .generar(comisionadoLicenciaExcelDTOs);
                    } else {
                        throw new ReglaNegocioException(
                                "No se encontrarón resultados, intentelo de nuevo.",
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }

                }
                    break;

                case "consentrado_alta_baja": {
                    Integer idTipoContratacionConsentrado = Integer
                            .parseInt(parametros.get("ID_TIPO_CONTRATACION"));
                    Date fechaInicial = FechaUtil.getFecha(
                            parametros.get("FECHA_INICIAL"),
                            PATRON_FECHA_BASE_DE_DATOS);
                    Date fechaFinal = FechaUtil.getFecha(
                            parametros.get("FECHA_FINAL"),
                            PATRON_FECHA_BASE_DE_DATOS);

                    MovimientoEmpleadoReporteService movimientoEmpleadoReporteService = getBean(
                            MovimientoEmpleadoReporteService.class);
                    List<ConsentradoAltaBajaExcelDTO> consentradoAltaBajaExcelDTOs = movimientoEmpleadoReporteService
                            .listaConsultaConsentradoAltaBajaPorRangoFecha(
                                    idTipoContratacionConsentrado, fechaInicial,
                                    fechaFinal);

                    if (!consentradoAltaBajaExcelDTOs.isEmpty()) {
                        ConsentradoAltaBajaExcel consentradoAltaBajaExcel = new ConsentradoAltaBajaExcel();

                        bytes = consentradoAltaBajaExcel
                                .generar(consentradoAltaBajaExcelDTOs);
                    } else {
                        throw new ReglaNegocioException(
                                "No se encontrarón resultados, intentelo de nuevo.",
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }
                }
                    break;

                case "seguro_popular": {
                    SeguroPopularReporte seguroPopularBean = getBean(
                            SeguroPopularReporte.class);
                    bytes = seguroPopularBean.obtenerReporte();
                }
                    break;

                case "seguro_popular_reporte": {
                    String anyo = parametros.get("ANYO");
                    Integer quincena = Integer
                            .parseInt(parametros.get("QUINCENA"));
                    SeguroPopularReporte seguroPopularBean = getBean(
                            SeguroPopularReporte.class);
                    bytes = seguroPopularBean.obtenerReporte(anyo, quincena);
                }
                    break;

                case "contrato_estatal_federal": {
                    Integer anyoPresupuesto = Integer
                            .parseInt(parametros.get("ANYO_PRESUPUESTO"));
                    Integer idTipoNombramiento = Integer
                            .parseInt(parametros.get("ID_TIPO_NOMBRAMIENTO"));
                    ProyeccionesPresupuestalesEJB proyeccionesPresupuestalesBean = getBean(
                            ProyeccionesPresupuestalesEJB.class);
                    List<ProyeccionesPresupuestalesDTO> proyeccionesPresupuestales = proyeccionesPresupuestalesBean
                            .proyeccionesPresupuestales(anyoPresupuesto,
                                    idTipoNombramiento);

                    ContratoExcel contratoExcel = new ContratoExcel();

                    bytes = contratoExcel.generar(proyeccionesPresupuestales);
                }
                    break;

                case "contrato_estatal_federal_proyeccion": {
                    Integer anioPresupuesto = Integer
                            .parseInt(parametros.get("ANYO_PRESUPUESTO"));
                    Integer idTipoNombramientos = Integer
                            .parseInt(parametros.get("ID_TIPO_NOMBRAMIENTO"));
                    ProyeccionesPresupuestalesEJB proyeccionesPresupuestalesBean = getBean(
                            ProyeccionesPresupuestalesEJB.class);
                    List<ProyeccionesPresupuestalesDTO> proyecciones = proyeccionesPresupuestalesBean
                            .proyeccionesPresupuestales(anioPresupuesto,
                                    idTipoNombramientos);

                    ContratoProyeccionExcel contratoProyeccionExcel = new ContratoProyeccionExcel();

                    bytes = contratoProyeccionExcel.generar(proyecciones);
                }
                    break;

                case "detalle_empleado": {

                    Integer idTipoContratacion = Integer
                            .parseInt(parametros.get("ID_TIPO_CONTRATACION"));
                    DetalleEmpleado detalleEmpleadoBean = getBean(
                            DetalleEmpleado.class);
                    List<DetalleEmpleadoDTO> detalleEmpleado = detalleEmpleadoBean
                            .detalleEmpleadoPorIdTipoContratacion(
                                    idTipoContratacion);

                    DetalleEmpleadoExcel detalleEmpleadoExcel = new DetalleEmpleadoExcel();

                    bytes = detalleEmpleadoExcel.generar(detalleEmpleado);
                }
                    break;

                case "producto_nomina": {

                    Integer idProducto = Integer
                            .parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    ProductoNomina productoNominaBean = getBean(
                            ProductoNomina.class);
                    List<ProductosNominaExcelDTO> listaProductoNomina = productoNominaBean
                            .obtenerListaProductoNominaPorIdProducto(
                                    idProducto);

                    if (!listaProductoNomina.isEmpty()) {
                        ProductoNominaExcel productoNominaExcel = new ProductoNominaExcel();
                        bytes = productoNominaExcel
                                .generar(listaProductoNomina);
                    } else {
                        throw new ReglaNegocioException(
                                "No se encontrarón resultados en el producto nomina: ",
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }
                }
                    break;

                case "producto_nomina_programas": {
                    Integer idProducto = Integer
                            .parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    ProductoNomina productoNominaBean = getBean(
                            ProductoNomina.class);
                    List<ProductosNominaProgramasExcelDTO> listaProductoNominaProgramas = productoNominaBean
                            .obtenerListaProductoNominaProgramasPorIdProducto(
                                    idProducto);
                    List<String> listaProgramas = productoNominaBean
                            .obtenerListaProgramasPorIdProducto(idProducto);
                    ProductoNominaDTO producto = productoNominaBean
                            .obtenerProductoNominaPorIdProducto(idProducto);

                    if (!listaProductoNominaProgramas.isEmpty()) {
                        ProductoNominaProgramasExcel productoNominaProgramasExcel = new ProductoNominaProgramasExcel();
                        bytes = productoNominaProgramasExcel.generar(
                                listaProductoNominaProgramas, listaProgramas,
                                producto);
                    } else {
                        throw new ReglaNegocioException(
                                "No se encontrarón resultados en el producto nomina: ",
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }
                }
                    break;

                case "producto_nomina_estatus": {
                    Integer idProducto = Integer
                            .parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    Integer idEstatus = Integer
                            .parseInt(parametros.get("ID_ESTATUS"));
                    ProductoNomina productoNominaBean = getBean(
                            ProductoNomina.class);
                    List<ProductosNominaExcelDTO> listaProductoNomina = productoNominaBean
                            .obtenerListaProductoNominaPorIdProductoEstatus(
                                    idProducto, idEstatus);

                    if (!listaProductoNomina.isEmpty()) {
                        ProductoNominaExcel productoNominaExcel = new ProductoNominaExcel();

                        bytes = productoNominaExcel
                                .generar(listaProductoNomina);
                    } else {
                        throw new ReglaNegocioException(
                                "No se encontrarón resultados en el producto nomina con el estatus "
                                        + EnumEstatusProductoNomina
                                                .obtenerEstatus(idEstatus),
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }
                }
                    break;
                case "producto_nomina_suplencia": {
                    Integer idProducto = Integer
                            .parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    ProductoNomina productoNominaBean = getBean(
                            ProductoNomina.class);
                    List<ProductosNominaExcelDTO> listaProductoNomina = productoNominaBean
                            .obtenerListaProductoNominaPorIdProducto(
                                    idProducto);

                    if (!listaProductoNomina.isEmpty()) {
                        ProductoNominaExcel productoNominaExcel = new ProductoNominaExcel();
                        bytes = productoNominaExcel
                                .generar(listaProductoNomina);
                    } else {
                        throw new ReglaNegocioException(
                                "No se encontrarón resultados con el identificador del producto nomina: "
                                        + idProducto.toString(),
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }
                }
                    break;

                case "historial_pago": {

                    Integer idEmpleado = Integer
                            .parseInt(parametros.get("ID_EMPLEADO"));

                    HistorialPago historialPagoBean = getBean(
                            HistorialPago.class);
                    List<HistorialPagoDetalleDTO> listaHistorialPago = historialPagoBean
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

                    Integer numeroQuincena = Integer
                            .parseInt(parametros.get("NUMERO_QUINCENA"));
                    Integer ejercicioFiscal = Integer
                            .parseInt(parametros.get("EJERCICIO_FISCAL"));
                    Integer idCentroResponsabilidad = Integer.parseInt(
                            parametros.get("ID_CENTRO_RESPONSABILIDAD"));

                    RelacionPersonalSuplente relacionPersonalSuplenteBean = getBean(
                            RelacionPersonalSuplente.class);
                    List<RelacionPersonalSuplenteDTO> listaRelacionPersonalSuplenteDTOs = relacionPersonalSuplenteBean
                            .obtenerListaRelacionPersonalSuplente(
                                    numeroQuincena, ejercicioFiscal,
                                    idCentroResponsabilidad);

                    if (!listaRelacionPersonalSuplenteDTOs.isEmpty()) {

                        RelacionPersonalSuplenteExcel relacionPersonalSuplenteExcel = new RelacionPersonalSuplenteExcel();

                        bytes = relacionPersonalSuplenteExcel
                                .generar(listaRelacionPersonalSuplenteDTOs);

                    } else {
                        throw new ReglaNegocioException(
                                "No se encontrarón resultados, intentelo de nuevo.",
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }

                }
                    break;

                case "dispersion_nomina": {
                    Integer idProducto = Integer
                            .parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    Dispersion dispersionBean = getBean(Dispersion.class);
                    bytes = dispersionBean.generarReporte(idProducto, true);
                }
                    break;

                case "pago_general": {
                    Integer idProducto = Integer
                            .parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    PagoGeneralReporte pagoGeneral = getBean(
                            PagoGeneralReporte.class);
                    bytes = pagoGeneral == null ? ReporteVacio.obtenerBytes()
                            : pagoGeneral.generarReporte(idProducto);
                }
                    break;

                case "reporte_distribucion_presupuestal": {

                    Integer anioPresupuesto = Integer
                            .parseInt(parametros.get("ANYO_PRESUPUESTO"));
                    Integer idTipoNombramiento = Integer
                            .parseInt(parametros.get("ID_TIPO_NOMBRAMIENTO"));
                    Integer idDependencia = Integer
                            .parseInt(parametros.get("DEPENDENCIA"));
                    Integer idSubfuenteFinanciamiento = Integer.parseInt(
                            parametros.get("ID_SUBFUENTE_FINANCIAMIENTO"));

                    DistribucionPresupuestoEJB distribucionPresupuestoBean = getBean(
                            DistribucionPresupuestoEJB.class);
                    List<DistribucionPresupuestoDTO> listaDistribucionPresupuestoDTOs = distribucionPresupuestoBean
                            .distribucionPresupuesto(anioPresupuesto,
                                    idTipoNombramiento, idDependencia,
                                    idSubfuenteFinanciamiento);

                    if (!listaDistribucionPresupuestoDTOs.isEmpty()) {

                        ReporteDistribucionPresupuesto reporteDistribucionPresupuesto = new ReporteDistribucionPresupuesto();

                        try {

                            bytes = reporteDistribucionPresupuesto
                                    .generarArchivoExcel(
                                            listaDistribucionPresupuestoDTOs);
                        } catch (IOException ex) {
                            bytes = ReporteVacio.obtenerBytes();
                            LOGGER.warn(
                                    "Se va a generar un archivo excel vacio por error de entrada/salida");
                        }

                    } else {
                        throw new ReglaNegocioException(
                                "No se encontrarón resultados, intentelo de nuevo.",
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }

                }
            }
        }
        return bytes;
    }

}
