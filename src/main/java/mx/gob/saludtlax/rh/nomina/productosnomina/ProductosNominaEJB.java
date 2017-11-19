
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.ejb3.annotation.TransactionTimeout;

import mx.gob.saludtlax.rh.autorizaciones.AutorizacionesService;
import mx.gob.saludtlax.rh.autorizaciones.EnumTiposAccionesAutorizacion;
import mx.gob.saludtlax.rh.autorizaciones.NuevaAutorizacionDTO;
import mx.gob.saludtlax.rh.configuracion.banco.BancoDTO;
import mx.gob.saludtlax.rh.configuracion.cuentabancaria.CuentaBancariaDTO;
import mx.gob.saludtlax.rh.configuracion.ejerciciofiscal.EjercicioFiscalService;
import mx.gob.saludtlax.rh.configuracion.ejerciciofiscal.PeriodoCalendarioDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.FuenteFinanciamientoOPDDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.FuenteFinanciamientoService;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.SubfuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.tiponomina.TipoNominaDTO;
import mx.gob.saludtlax.rh.configuracion.tiponomina.TipoNominaService;
import mx.gob.saludtlax.rh.configuracion.tipoperiodo.TipoPeriodoDTO;
import mx.gob.saludtlax.rh.configuracion.tipoperiodo.TipoPeriodoService;
import mx.gob.saludtlax.rh.empleados.suplencia.EnumEstatusSuplencia;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.PagoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.ProcesoCalculoEntity;
import mx.gob.saludtlax.rh.persistencia.ProcesoCalculoRepository;
import mx.gob.saludtlax.rh.persistencia.QuincenasSuplenciasEntity;
import mx.gob.saludtlax.rh.persistencia.QuincenasSuplenciasRepository;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.seguridad.autenticacion.AutenticacionUtil;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author José Pablo
 */
@Stateless
public class ProductosNominaEJB {
    @Inject
    private FuenteFinanciamientoService fuenteFinanciamientoService;
    @Inject
    private TipoNominaService tipoNominaService;
    @Inject
    private EjercicioFiscalService ejercicioFiscalService;
    @Inject
    private ProductosNominaService productoNominaService;
    @Inject
    private ProcesoCalculoRepository procesoCalculoRepository;
    @Inject
    private NominaEmpleadoService nominaEmpleadoEventualService;
    @Inject
    private NominaEmpleadoFederalizadoService nominaEmpleadoFederalizadoService;
    @Inject
    private TipoPeriodoService tipoPeriodoService;
    @Inject
    private QuincenasSuplenciasRepository quincenaSuplenciaRepository;
    @Inject
    private AutorizacionesService autorizacionesService;
    @Inject
    private EstatusNominaService estatusNominaService;
    @Inject
    private ActualizarNominaEmpleadoService actualizarNominaEmpleadoService;
    @Inject
    private PagoNominaService pagoNominaService;

    private static final Logger LOGGER = Logger.getLogger(ProductosNominaEJB.class);

    public List<PeriodoCalendarioDTO> obtenerPeriodoCalendarioList(Integer idEjercicioFiscal) {
        return ejercicioFiscalService.listaPeriodoCalendarioPorIdEjercicioFiscal(idEjercicioFiscal);
    }

    public List<SubfuenteFinanciamientoDTO> obtenerSubfuenteFinanciamientoNominaList() {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        Integer tipoDepartamento = null;
        if (usuario.getUserName().equals("veronica") || usuario.getUserName().equals("rosaA")) {
            tipoDepartamento = 2;
        } else {
            tipoDepartamento = 1;
        }
        return fuenteFinanciamientoService.listaSubfuenteFinanciamientoNominaPorDepartamento(tipoDepartamento);
    }

    public List<SubfuenteFinanciamientoDTO> obtenerSubfuenteFinanciamientoNominaList(ProductoNominaDTO productoNomina) {
        return fuenteFinanciamientoService.listaSubfuenteFinanciamientoNomina();
    }

    public List<TipoNominaDTO> obtenerTipoNominaList() {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        Integer tipoDepartamento = null;
        if (usuario.getUserName().equals("veronica") || usuario.getUserName().equals("rosaA")) {
            tipoDepartamento = 2;
        } else {
            tipoDepartamento = 1;
        }
        return tipoNominaService.listaTipoNominaPorDepartamento(tipoDepartamento);
    }

    public List<NominaEmpleadoDTO> obtenerNominaEmpleadoList(ProductoNominaDTO productoNomina) {
        return nominaEmpleadoEventualService.listaNominaEmpleado(productoNomina);
    }

    public ProductoNominaDTO crearProductoNomina(ProductoNominaDTO dto) {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        dto.setIdUsuario(usuario.getIdUsuario());
        ProductoNominaDTO productoNomina = productoNominaService.crearProductoNomina(dto);
        return productoNomina;
    }

    public List<ProductoNominaListaDTO> filtrarProductoNomina(ProductoNominaFiltroDTO filtro) {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        if (usuario.getUserName().equals("veronica") || usuario.getUserName().equals("rosaA")) {
            filtro.setIdArea(2);
        } else {
            filtro.setIdArea(1);
        }
        return productoNominaService.filtrarProductoNomina(filtro);
    }

    public ProductoNominaDTO obtenerProductoNomina(Integer idProductoNomina) {
        ProductoNominaDTO productoNomina = productoNominaService.obtenerProductoNomina(idProductoNomina);
        return productoNomina;
    }

    @TransactionTimeout(value = 10, unit = TimeUnit.HOURS)
    public ProductoNominaDTO abrirProductoNomina(ProductoNominaDTO productoNomina) {
        if (productoNomina.getIdTipoContratacion() == EnumTipoContratacion.SUPLENCIA) {
            // ProcesoCalculoEntity proceso =
            // procesoCalculoRepository.obtenerPorId(2);
            List<QuincenasSuplenciasEntity> quincenasSuplencias = quincenaSuplenciaRepository.consultarSuplenciasPorQuincena(productoNomina.getNumeroPeriodo(),
                    productoNomina.getEjercicioFiscal(), EnumEstatusSuplencia.CERRADA);
            if (quincenasSuplencias.isEmpty()) {
                throw new ValidacionException(
                        "No existen suplencias en la quincena " + productoNomina.getNumeroPeriodo() + " " + productoNomina.getAnyoEjercicioFiscal(),
                        ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
            }
            for (QuincenasSuplenciasEntity q : quincenasSuplencias) {
                productoNominaService.abrirProductoNominaSuplencia(productoNomina.getIdProductoNomina(), q);
            }
        } else {
            LOGGER.info("abrirProductoNomina");
            productoNominaService.abrirProductoNomina(productoNomina);
        }
        productoNomina.setIdEstatusProductoNomina(2);
        productoNominaService.aplicarConsecutivosProductoNomina(productoNomina);
        productoNominaService.actualizarProductoNomina(productoNomina);
        return productoNominaService.obtenerProductoNomina(productoNomina.getIdProductoNomina());
    }

    public void actualizarProductoNomina(ProductoNominaDTO productoNomina) {
        productoNominaService.actualizarProductoNomina(productoNomina);
    }

    public NominaEmpleadoDTO obtenerNominaEmpleadoDetalle(NominaEmpleadoDTO nominaEmpleado) {
        return nominaEmpleadoEventualService.obtenerNominaEmpleadoDetalle(nominaEmpleado);
    }

    public List<TipoPeriodoDTO> obtenerTipoPeriodoLista(Integer ejercicioFiscal) {
        return tipoPeriodoService.listaTipoPeriodoPorEjercicioFiscal(ejercicioFiscal);
    }

    public Integer obtenerIdEjercicioFiscal(ProductoNominaDTO productoNomina) {
        Integer idEjercicioFiscal = ejercicioFiscalService.obtenerIdEjercicioFiscal(productoNomina.getEjercicioFiscal(), productoNomina.getIdTipoPeriodo());
        return idEjercicioFiscal;
    }

    public ProductoNominaDTO cambiarFechasPerido(ProductoNominaDTO productoNomina) {
        PeriodoCalendarioDTO periodoCalendario = ejercicioFiscalService.obtenerPeriodoCalendario(productoNomina.getIdEjercicioFiscal(),
                productoNomina.getNumeroPeriodo());
        productoNomina.setInicioPeriodo(periodoCalendario.getInicioPeriodo());
        productoNomina.setFinPeriodo(periodoCalendario.getFinPeriodo());
        productoNomina.setFechaPago(periodoCalendario.getFinPeriodo());
        productoNomina.setIdPeriodoCalendario(periodoCalendario.getIdPeriodoCalendario());
        return productoNomina;
    }

    public ProductoNominaDTO obtenerFuentePorSubfuente(ProductoNominaDTO productoNomina) {
        SubfuenteFinanciamientoDTO subfuenteFinanciamiento = fuenteFinanciamientoService
                .obtenerSubfuenteFinanciamientoPorId(productoNomina.getIdSubfuenteFinanciamiento());
        FuenteFinanciamientoOPDDTO fuenteFinanciamiento = fuenteFinanciamientoService.obtenerFuenteFinanciamientoOPDPorSubFuente(subfuenteFinanciamiento);
        productoNomina.setIdFuenteFinanciamiento(fuenteFinanciamiento.getIdFuenteFinanciamientoOPD());
        return productoNomina;
    }

    public List<FuenteFinanciamientoOPDDTO> obtenerFuentesFinanciamientoNominaList() {
        return fuenteFinanciamientoService.listaFuenteFinanciamientoOPD();
    }

    public List<EstatusProductoNominaDTO> obtenerEstatusProductoNominaList() {
        return productoNominaService.obtenerEstatusProductoNominaLista();
    }

    @TransactionTimeout(value = 10, unit = TimeUnit.HOURS)
    public ProductoNominaDTO calcularProductoNomina(ProductoNominaDTO productoNomina) {
        System.out.println("calcularProductoNomina" + "calculara faltas?" + productoNomina.getCalcularFaltas());
        productoNomina.setIdEstatusProductoNomina(3);
        productoNominaService.actualizarProductoNomina(productoNomina);
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        int contador = 1;
        if (usuario.getUserName().equals("veronica") || usuario.getUserName().equals("rosaA")) {
            List<NominaEmpleadoDTO> nominaEmpleadoList = nominaEmpleadoEventualService.obtenerNominaEmpleadoList(productoNomina);
            if (productoNomina.getIdTipoContratacion() == EnumTipoContratacion.SUPLENCIA) {
                // ProcesoCalculoEntity proceso =
                // procesoCalculoRepository.obtenerPorId(1);
                // proceso.setEnProceso(true);
                for (NominaEmpleadoDTO nominaEmpleado : nominaEmpleadoList) {
                    nominaEmpleadoEventualService.calcularProductoNominaSuplencias(productoNomina, nominaEmpleado);
                    // procesoCalculoRepository.actualizar(proceso);
                    // proceso.setNumeroProcesado(++contador);
                }
            }
            if (productoNomina.getIdTipoContratacion() == EnumTipoContratacion.CONTRATO_ESTATAL) {
                ProcesoCalculoEntity proceso = procesoCalculoRepository.obtenerPorId(3);
                proceso.setEnProceso(true);
                try {
                    for (NominaEmpleadoDTO nominaEmpleado : nominaEmpleadoList) {
                        switch (productoNomina.getIdTipoNomina()) {
                            case 16:
                                nominaEmpleadoEventualService.calcularProductoNominaAguinaldoContrato(productoNomina, nominaEmpleado);
                                break;
                            // case 52:
                            // nominaEmpleadoEventualService
                            // .calcularProductoNominaPrimaVacacionalContrato(productoNomina,
                            // nominaEmpleado);
                            // break;
                            default:
                                System.out.println("productoNomina.getCalcularFaltas():: " + productoNomina.getCalcularFaltas());
                                nominaEmpleadoEventualService.calcularProductoNomina(productoNomina, nominaEmpleado, productoNomina.getCalcularFaltas());
                                break;
                        }

                        procesoCalculoRepository.actualizar(proceso);

                        proceso.setNumeroProcesado(++contador);
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
            if (productoNomina.getIdTipoContratacion() == EnumTipoContratacion.CONTRATO_FEDERAL) {
                ProcesoCalculoEntity proceso = procesoCalculoRepository.obtenerPorId(4);
                proceso.setEnProceso(true);
                for (NominaEmpleadoDTO nominaEmpleado : nominaEmpleadoList) {
                    nominaEmpleadoEventualService.calcularProductoNominaContratoFederal(productoNomina, nominaEmpleado, productoNomina.getCalcularFaltas());
                    procesoCalculoRepository.actualizar(proceso);
                    proceso.setNumeroProcesado(++contador);
                }
            }
        } else {
            List<NominaEmpleadoDTO> nominaEmpleadoList = nominaEmpleadoEventualService.obtenerNominaEmpleadoList(productoNomina);
            ProcesoCalculoEntity proceso = procesoCalculoRepository.obtenerPorId(5);
            proceso.setEnProceso(true);
            for (NominaEmpleadoDTO nominaEmpleado : nominaEmpleadoList) {
                nominaEmpleadoFederalizadoService.calcularProductoNominaFederales(productoNomina, nominaEmpleado, productoNomina.getCalcularFaltas());
                procesoCalculoRepository.actualizar(proceso);
                proceso.setNumeroProcesado(++contador);
            }
        }

        nominaEmpleadoEventualService.actualizarTotalesNominaEmpleado(productoNomina);
        nominaEmpleadoEventualService.validarProductoNomina(productoNomina);

        productoNomina.setIdEstatusProductoNomina(4);
        productoNominaService.actualizarProductoNomina(productoNomina);

        return productoNominaService.obtenerProductoNomina(productoNomina.getIdProductoNomina());
    }

    public List<TipoContratacionDTO> obtenerTipoContratacionList() {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        Integer tipoArea = null;
        if (usuario.getUserName().equals("veronica") || usuario.getUserName().equals("rosaA")) {
            tipoArea = 2;
        } else {
            tipoArea = 1;
        }
        return productoNominaService.listaTipoContratacionListPorArea(tipoArea);
    }

    public void validarProductoNomina(ProductoNominaDTO productoNomina) {
        productoNominaService.validarProductoNomina(productoNomina);
        nominaEmpleadoEventualService.cambiarEstatusNominaEmpleado(productoNomina, 3);
    }

    public List<NominaErroneaDTO> consultarNominasErroneas(Integer idProductoNomina) {
        if (!ValidacionUtil.esNumeroPositivo(idProductoNomina)) {
            throw new ValidacionException("Para obtener los cálculos erroneos es requerido el  producto nómina", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        return nominaEmpleadoEventualService.consultarNominasErroneas(idProductoNomina);
    }

    public List<FaltaContadaDTO> obtenerFaltasContadas(ConceptosNominaEmpleadosDTO conceptoNominaEmpleado) {
        return nominaEmpleadoEventualService.obtenerFaltasContadas(conceptoNominaEmpleado);
    }

    public List<FaltaContadaDTO> obtenerFaltasNoContadas(List<FaltaContadaDTO> faltasContadas, ConceptosNominaEmpleadosDTO conceptoNominaEmpleado) {
        return nominaEmpleadoEventualService.obtenerFaltasNoContadas(faltasContadas, conceptoNominaEmpleado);
    }

    public void actualizarConcepto(ConceptosNominaEmpleadosDTO conceptosNomina, List<FaltaContadaDTO> faltasContadas) {
        nominaEmpleadoEventualService.actualizarConcepto(conceptosNomina, faltasContadas);
    }

    public void recalcularNominaEmpleado(ProductoNominaDTO productoNomina, NominaEmpleadoDTO nominaEmpleado) {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        if (usuario.getUserName().equals("veronica") || usuario.getUserName().equals("rosaA")) {
            if (productoNomina.getIdTipoContratacion() == EnumTipoContratacion.SUPLENCIA) {
                try {
                    nominaEmpleadoEventualService.calcularProductoNominaSuplencias(productoNomina, nominaEmpleado);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
            if (productoNomina.getIdTipoContratacion() == EnumTipoContratacion.CONTRATO_ESTATAL) {
                try {
                    switch (productoNomina.getIdTipoNomina()) {
                        case 16:
                            nominaEmpleadoEventualService.calcularProductoNominaAguinaldoContrato(productoNomina, nominaEmpleado);
                            break;
                        // case 52:
                        // nominaEmpleadoEventualService
                        // .calcularProductoNominaPrimaVacacionalContrato(productoNomina,
                        // nominaEmpleado);
                        // break;
                        default:
                            nominaEmpleadoEventualService.calcularProductoNomina(productoNomina, nominaEmpleado, false);
                            break;
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
            if (productoNomina.getIdTipoContratacion() == EnumTipoContratacion.CONTRATO_FEDERAL) {
                try {
                    nominaEmpleadoEventualService.calcularProductoNominaContratoFederal(productoNomina, nominaEmpleado, false);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
        } else {
            try {
                nominaEmpleadoFederalizadoService.calcularProductoNominaFederales(productoNomina, nominaEmpleado, false);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        nominaEmpleadoEventualService.actualizarTotalesPorNominaEmpleado(nominaEmpleado);
    }

    public List<PensionesNominaDTO> obtenerPensionesNominaList(ProductoNominaDTO productoNomina) {
        return nominaEmpleadoEventualService.obtenerPensionesNominaList(productoNomina);
    }

    public void autorizarProductoNomina(ProductoNominaDTO productoNomina) {
        productoNomina.setIdEstatusProductoNomina(7);
        productoNominaService.actualizarProductoNomina(productoNomina);
        nominaEmpleadoEventualService.cambiarEstatusNominaEmpleado(productoNomina, 4);
    }

    public void devolverProductoNomina(ProductoNominaDTO productoNomina) {
        productoNomina.setIdEstatusProductoNomina(4);
        productoNominaService.actualizarProductoNomina(productoNomina);
        nominaEmpleadoEventualService.cambiarEstatusNominaEmpleado(productoNomina, 2);
    }

    public List<CuentaBancariaDTO> obtenerCuentaBancariaList(ProductoNominaDTO productoNomina) {
        return productoNominaService.obtenerCuentaBancariaList(productoNomina);
    }

    public List<BancoDTO> obtenerBancoList() {
        return productoNominaService.obtenerBancoList();
    }

    public void eliminarProductoNomina(ProductoNominaDTO productoNomina) {
        productoNominaService.eliminarProductoNomina(productoNomina);
    }

    public void actualizarEstatusNominaEmpleado(Integer idEstatusNominaEmpleado, Integer idNominaEmpleado, Integer idUsuario) {
        estatusNominaService.actualizarEstatusNominaEmpleado(idEstatusNominaEmpleado, idNominaEmpleado, idUsuario);
    }

    public Integer obtenerEstatusPorIdProductoNomina(Integer idProductoNominaEmpleado) {
        return estatusNominaService.obtenerEstatusPorIdProductoNomina(idProductoNominaEmpleado);
    }

    public List<ActualizarNominaEmpleadoDTO> obtenerActualizarNomina(ProductoNominaDTO productoNomina) {
        List<ActualizarNominaEmpleadoDTO> actualizarNominaEmpleadoList = actualizarNominaEmpleadoService.obtenerActualizarNomina(productoNomina);
        return actualizarNominaEmpleadoList;
    }

    public void actualizarNomina(List<ActualizarNominaEmpleadoDTO> actualizarNominaEmpleadoList, ProductoNominaDTO productoNomina) {
        List<ActualizarNominaEmpleadoDTO> actualizarNominaEmpleadoListTem = new ArrayList<>();
        for (ActualizarNominaEmpleadoDTO actualizarNominaEmpleado : actualizarNominaEmpleadoList) {
            LOGGER.info("TipoCambio:: " + actualizarNominaEmpleado.getTipoCambio());
            switch (actualizarNominaEmpleado.getTipoCambio()) {
                case "1":
                    actualizarNominaEmpleadoListTem.add(actualizarNominaEmpleado);
                    break;
                case "2":
                    actualizarNominaEmpleadoService.agregarNominaEmpleado(actualizarNominaEmpleado, productoNomina);
                    break;
                case "3":
                    productoNominaService.eliminarNominaEmpleado(actualizarNominaEmpleado.getIdNominaempleado());
                    break;
            }
        }
        if (!actualizarNominaEmpleadoListTem.isEmpty()) {
            actualizarNominaEmpleadoService.actualizarNomina(actualizarNominaEmpleadoListTem);
        }
    }

    public List<PagoNominaDTO> obtenerPagosNomina(ProductoNominaDTO productoNomina) {
        List<PagoNominaEntity> pagoNominaList = pagoNominaService.obtenerPagosNomina(productoNomina);
        if (pagoNominaList.isEmpty()) {
            pagoNominaList = pagoNominaService.crearPagosNomina(productoNomina);
        }
        List<PagoNominaDTO> pagoNominaDTOList = new ArrayList<>();
        for (PagoNominaEntity pagoNominaEntity : pagoNominaList) {
            PagoNominaDTO pagoNomina = pagoNominaService.toPagoNominaDTO(pagoNominaEntity);
            pagoNominaDTOList.add(pagoNomina);
        }
        return pagoNominaDTOList;
    }

    public PagoNominaDTO obtenerPagoNomina(PagoNominaDTO pagoNominaSelect) {
        return pagoNominaService.obtenerPagoNomina(pagoNominaSelect);
    }

    public PagoNominaDTO crearPagoNomina(PagoNominaDTO pagoNominaSelect) {
        return pagoNominaService.crearPagoNomina(pagoNominaSelect);
    }

    public PagoNominaDTO actualizarPagoNomina(PagoNominaDTO pagoNominaSelect) {
        return pagoNominaService.actualizarPagoNomina(pagoNominaSelect);
    }

    public void eliminarPagoNomina(PagoNominaDTO pagoNominaSelect) {
        pagoNominaService.eliminarPagoNomina(pagoNominaSelect);
    }

    public PagoNominaDTO obtenerNuevoPagoNomina(ProductoNominaDTO productoNomina) {
        PagoNominaDTO pagoNomina = pagoNominaService.obtenerListaRfcSinPago(productoNomina);
        if (pagoNomina == null) {
            pagoNomina = new PagoNominaDTO();
        }
        pagoNomina.setIdProductoNomina(productoNomina.getIdProductoNomina());
        pagoNomina.setIdBanco(7);
        pagoNomina.setFechaPago(productoNomina.getFinPeriodo());
        return pagoNomina;
    }

    public List<NominaEmpleadoDTO> obtenerNominaEmpleadoListPorPago(PagoNominaDTO pagoNomina) {
        return nominaEmpleadoEventualService.obtenerNominaEmpleadoListPorPago(pagoNomina);
    }

    public List<NominaEmpleadoDTO> obtenerNominaEmpleadoListSinPago(ProductoNominaDTO productoNomina) {
        return nominaEmpleadoEventualService.obtenerNominaEmpleadoListSinPago(productoNomina);
    }

    public Integer obtenerUltimoNumeroCheque(ProductoNominaDTO productoNomina) {
        return nominaEmpleadoEventualService.obtenerUltimoNumeroCheque();
    }

    public void generarNumeracionCheques(ProductoNominaDTO productoNomina) {
        productoNominaService.aplicarChequesProductoNomina(productoNomina);
    }

    public void pedirAutorizacionProductoNomina(ProductoNominaDTO productoNomina) {
        productoNomina.setIdEstatusProductoNomina(EstatusProductoNomina.PREAUTORIZADO);
        productoNominaService.actualizarProductoNomina(productoNomina);
        NuevaAutorizacionDTO dto = new NuevaAutorizacionDTO();
        dto.setMensajeNotificacion(" su autorización del producto de nomina: " + productoNomina.getNombreProducto());
        dto.setIdAccion(EnumTiposAccionesAutorizacion.AUTORIZAR_PRODUCTO_NOMINA_ESTATAL);
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        dto.setIdEntidadContexto(productoNomina.getIdProductoNomina());
        dto.setIdUsuarioLogeado(usuario.getIdUsuario());
        autorizacionesService.iniciarProcesoAprobacion(dto);
    }

    public Boolean esUsuarioAutorizaNomina(ProductoNominaDTO productoNomina) {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        Integer idOperacion = EnumTiposAccionesAutorizacion.AUTORIZAR_PRODUCTO_NOMINA_ESTATAL;
        Integer idEntidadContexto = productoNomina.getIdProductoNomina();
        Integer idUsuario = usuario.getIdUsuario();
        return autorizacionesService.esUsuarioAutoriza(idOperacion, idEntidadContexto, idUsuario);
    }
}