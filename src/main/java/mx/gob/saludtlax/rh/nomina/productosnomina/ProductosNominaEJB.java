
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
        return this.ejercicioFiscalService.listaPeriodoCalendarioPorIdEjercicioFiscal(idEjercicioFiscal);
    }

    public List<SubfuenteFinanciamientoDTO> obtenerSubfuenteFinanciamientoNominaList() {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        Integer tipoDepartamento = null;
        if (usuario.getUserName().equals("veronica") || usuario.getUserName().equals("rosaA")) {
            tipoDepartamento = 2;
        } else {
            tipoDepartamento = 1;
        }
        return this.fuenteFinanciamientoService.listaSubfuenteFinanciamientoNominaPorDepartamento(tipoDepartamento);
    }

    public List<SubfuenteFinanciamientoDTO> obtenerSubfuenteFinanciamientoNominaList(ProductoNominaDTO productoNomina) {
        return this.fuenteFinanciamientoService.listaSubfuenteFinanciamientoNomina();
    }

    public List<TipoNominaDTO> obtenerTipoNominaList() {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        Integer tipoDepartamento = null;
        if (usuario.getUserName().equals("veronica") || usuario.getUserName().equals("rosaA")) {
            tipoDepartamento = 2;
        } else {
            tipoDepartamento = 1;
        }
        return this.tipoNominaService.listaTipoNominaPorDepartamento(tipoDepartamento);
    }

    public List<NominaEmpleadoDTO> obtenerNominaEmpleadoList(ProductoNominaDTO productoNomina) {
        return this.nominaEmpleadoEventualService.listaNominaEmpleado(productoNomina);
    }

    public ProductoNominaDTO crearProductoNomina(ProductoNominaDTO dto) {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        dto.setIdUsuario(usuario.getIdUsuario());
        ProductoNominaDTO productoNomina = this.productoNominaService.crearProductoNomina(dto);
        return productoNomina;
    }

    public List<ProductoNominaListaDTO> filtrarProductoNomina(ProductoNominaFiltroDTO filtro) {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        if (usuario.getUserName().equals("veronica") || usuario.getUserName().equals("rosaA")) {
            filtro.setIdArea(2);
        } else {
            filtro.setIdArea(1);
        }
        return this.productoNominaService.filtrarProductoNomina(filtro);
    }

    public ProductoNominaDTO obtenerProductoNomina(Integer idProductoNomina) {
        ProductoNominaDTO productoNomina = this.productoNominaService.obtenerProductoNomina(idProductoNomina);
        return productoNomina;
    }

    @TransactionTimeout(value = 10, unit = TimeUnit.HOURS)
    public ProductoNominaDTO abrirProductoNomina(ProductoNominaDTO productoNomina) {
        if (productoNomina.getIdTipoContratacion() == EnumTipoContratacion.SUPLENCIA) {
            // ProcesoCalculoEntity proceso =
            // procesoCalculoRepository.obtenerPorId(2);
            List<QuincenasSuplenciasEntity> quincenasSuplencias = this.quincenaSuplenciaRepository
                    .consultarSuplenciasPorQuincena(productoNomina.getNumeroPeriodo(), productoNomina.getEjercicioFiscal(), EnumEstatusSuplencia.CERRADA);
            if (quincenasSuplencias.isEmpty()) {
                throw new ValidacionException(
                        "No existen suplencias en la quincena " + productoNomina.getNumeroPeriodo() + " " + productoNomina.getAnyoEjercicioFiscal(),
                        ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
            }
            for (QuincenasSuplenciasEntity q : quincenasSuplencias) {
                this.productoNominaService.abrirProductoNominaSuplencia(productoNomina.getIdProductoNomina(), q);
            }
        } else {
            LOGGER.info("abrirProductoNomina");
            this.productoNominaService.abrirProductoNomina(productoNomina);
        }
        productoNomina.setIdEstatusProductoNomina(2);
        this.productoNominaService.aplicarConsecutivosProductoNomina(productoNomina);
        this.productoNominaService.actualizarProductoNomina(productoNomina);
        return this.productoNominaService.obtenerProductoNomina(productoNomina.getIdProductoNomina());
    }

    public void actualizarProductoNomina(ProductoNominaDTO productoNomina) {
        this.productoNominaService.actualizarProductoNomina(productoNomina);
    }

    public NominaEmpleadoDTO obtenerNominaEmpleadoDetalle(NominaEmpleadoDTO nominaEmpleado) {
        return this.nominaEmpleadoEventualService.obtenerNominaEmpleadoDetalle(nominaEmpleado);
    }

    public List<TipoPeriodoDTO> obtenerTipoPeriodoLista(Integer ejercicioFiscal) {
        return this.tipoPeriodoService.listaTipoPeriodoPorEjercicioFiscal(ejercicioFiscal);
    }

    public Integer obtenerIdEjercicioFiscal(ProductoNominaDTO productoNomina) {
        Integer idEjercicioFiscal = this.ejercicioFiscalService.obtenerIdEjercicioFiscal(productoNomina.getEjercicioFiscal(),
                productoNomina.getIdTipoPeriodo());
        return idEjercicioFiscal;
    }

    public ProductoNominaDTO cambiarFechasPerido(ProductoNominaDTO productoNomina) {
        PeriodoCalendarioDTO periodoCalendario = this.ejercicioFiscalService.obtenerPeriodoCalendario(productoNomina.getIdEjercicioFiscal(),
                productoNomina.getNumeroPeriodo());
        productoNomina.setInicioPeriodo(periodoCalendario.getInicioPeriodo());
        productoNomina.setFinPeriodo(periodoCalendario.getFinPeriodo());
        productoNomina.setFechaPago(periodoCalendario.getFinPeriodo());
        productoNomina.setIdPeriodoCalendario(periodoCalendario.getIdPeriodoCalendario());
        return productoNomina;
    }

    public ProductoNominaDTO obtenerFuentePorSubfuente(ProductoNominaDTO productoNomina) {
        SubfuenteFinanciamientoDTO subfuenteFinanciamiento = this.fuenteFinanciamientoService
                .obtenerSubfuenteFinanciamientoPorId(productoNomina.getIdSubfuenteFinanciamiento());
        FuenteFinanciamientoOPDDTO fuenteFinanciamiento = this.fuenteFinanciamientoService.obtenerFuenteFinanciamientoOPDPorSubFuente(subfuenteFinanciamiento);
        productoNomina.setIdFuenteFinanciamiento(fuenteFinanciamiento.getIdFuenteFinanciamientoOPD());
        return productoNomina;
    }

    public List<FuenteFinanciamientoOPDDTO> obtenerFuentesFinanciamientoNominaList() {
        return this.fuenteFinanciamientoService.listaFuenteFinanciamientoOPD();
    }

    public List<EstatusProductoNominaDTO> obtenerEstatusProductoNominaList() {
        return this.productoNominaService.obtenerEstatusProductoNominaLista();
    }

    @TransactionTimeout(value = 10, unit = TimeUnit.HOURS)
    public ProductoNominaDTO calcularProductoNomina(ProductoNominaDTO productoNomina) {
        productoNomina.setIdEstatusProductoNomina(3);
        this.productoNominaService.actualizarProductoNomina(productoNomina);
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        int contador = 1;
        if (usuario.getUserName().equals("veronica") || usuario.getUserName().equals("rosaA")) {
            List<NominaEmpleadoDTO> nominaEmpleadoList = this.nominaEmpleadoEventualService.obtenerNominaEmpleadoList(productoNomina);
            if (productoNomina.getIdTipoContratacion() == EnumTipoContratacion.SUPLENCIA) {
                // ProcesoCalculoEntity proceso =
                // procesoCalculoRepository.obtenerPorId(1);
                // proceso.setEnProceso(true);
                for (NominaEmpleadoDTO nominaEmpleado : nominaEmpleadoList) {
                    this.nominaEmpleadoEventualService.calcularProductoNominaSuplencias(productoNomina, nominaEmpleado);
                    // procesoCalculoRepository.actualizar(proceso);
                    // proceso.setNumeroProcesado(++contador);
                }
            }
            if (productoNomina.getIdTipoContratacion() == EnumTipoContratacion.CONTRATO_ESTATAL) {
                ProcesoCalculoEntity proceso = this.procesoCalculoRepository.obtenerPorId(3);
                proceso.setEnProceso(true);
                try {
                    for (NominaEmpleadoDTO nominaEmpleado : nominaEmpleadoList) {
                        switch (productoNomina.getIdTipoNomina()) {
                            case 16:
                                this.nominaEmpleadoEventualService.calcularProductoNominaAguinaldoContrato(productoNomina, nominaEmpleado);
                                break;
                            // case 52:
                            // nominaEmpleadoEventualService
                            // .calcularProductoNominaPrimaVacacionalContrato(productoNomina,
                            // nominaEmpleado);
                            // break;
                            default:

                                this.nominaEmpleadoEventualService.calcularProductoNomina(productoNomina, nominaEmpleado, productoNomina.getCalcularFaltas());
                                break;
                        }

                        this.procesoCalculoRepository.actualizar(proceso);

                        proceso.setNumeroProcesado(++contador);
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
            if (productoNomina.getIdTipoContratacion() == EnumTipoContratacion.CONTRATO_FEDERAL) {
                ProcesoCalculoEntity proceso = this.procesoCalculoRepository.obtenerPorId(4);
                proceso.setEnProceso(true);
                for (NominaEmpleadoDTO nominaEmpleado : nominaEmpleadoList) {
                    this.nominaEmpleadoEventualService.calcularProductoNominaContratoFederal(productoNomina, nominaEmpleado,
                            productoNomina.getCalcularFaltas());
                    this.procesoCalculoRepository.actualizar(proceso);
                    proceso.setNumeroProcesado(++contador);
                }
            }
        } else {
            List<NominaEmpleadoDTO> nominaEmpleadoList = this.nominaEmpleadoEventualService.obtenerNominaEmpleadoList(productoNomina);
            ProcesoCalculoEntity proceso = this.procesoCalculoRepository.obtenerPorId(5);
            proceso.setEnProceso(true);
            for (NominaEmpleadoDTO nominaEmpleado : nominaEmpleadoList) {
                this.nominaEmpleadoFederalizadoService.calcularProductoNominaFederales(productoNomina, nominaEmpleado, productoNomina.getCalcularFaltas());
                this.procesoCalculoRepository.actualizar(proceso);
                proceso.setNumeroProcesado(++contador);
            }
        }

        this.nominaEmpleadoEventualService.actualizarTotalesNominaEmpleado(productoNomina);
        this.nominaEmpleadoEventualService.validarProductoNomina(productoNomina);

        productoNomina.setIdEstatusProductoNomina(4);
        this.productoNominaService.actualizarProductoNomina(productoNomina);

        return this.productoNominaService.obtenerProductoNomina(productoNomina.getIdProductoNomina());
    }

    public List<TipoContratacionDTO> obtenerTipoContratacionList() {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        Integer tipoArea = null;
        if (usuario.getUserName().equals("veronica") || usuario.getUserName().equals("rosaA")) {
            tipoArea = 2;
        } else {
            tipoArea = 1;
        }
        return this.productoNominaService.listaTipoContratacionListPorArea(tipoArea);
    }

    public void validarProductoNomina(ProductoNominaDTO productoNomina) {
        this.productoNominaService.validarProductoNomina(productoNomina);
        this.nominaEmpleadoEventualService.cambiarEstatusNominaEmpleado(productoNomina, 3);
    }

    public List<NominaErroneaDTO> consultarNominasErroneas(Integer idProductoNomina) {
        if (!ValidacionUtil.esNumeroPositivo(idProductoNomina)) {
            throw new ValidacionException("Para obtener los cálculos erroneos es requerido el  producto nómina", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        return this.nominaEmpleadoEventualService.consultarNominasErroneas(idProductoNomina);
    }

    public List<FaltaContadaDTO> obtenerFaltasContadas(ConceptosNominaEmpleadosDTO conceptoNominaEmpleado) {
        return this.nominaEmpleadoEventualService.obtenerFaltasContadas(conceptoNominaEmpleado);
    }

    public List<FaltaContadaDTO> obtenerFaltasNoContadas(List<FaltaContadaDTO> faltasContadas, ConceptosNominaEmpleadosDTO conceptoNominaEmpleado) {
        return this.nominaEmpleadoEventualService.obtenerFaltasNoContadas(faltasContadas, conceptoNominaEmpleado);
    }

    public void actualizarConcepto(ConceptosNominaEmpleadosDTO conceptosNomina, List<FaltaContadaDTO> faltasContadas) {
        this.nominaEmpleadoEventualService.actualizarConcepto(conceptosNomina, faltasContadas);
    }

    public void recalcularNominaEmpleado(ProductoNominaDTO productoNomina, NominaEmpleadoDTO nominaEmpleado) {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        if (usuario.getUserName().equals("veronica") || usuario.getUserName().equals("rosaA")) {
            if (productoNomina.getIdTipoContratacion() == EnumTipoContratacion.SUPLENCIA) {
                try {
                    this.nominaEmpleadoEventualService.calcularProductoNominaSuplencias(productoNomina, nominaEmpleado);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
            if (productoNomina.getIdTipoContratacion() == EnumTipoContratacion.CONTRATO_ESTATAL) {
                try {
                    switch (productoNomina.getIdTipoNomina()) {
                        case 16:
                            this.nominaEmpleadoEventualService.calcularProductoNominaAguinaldoContrato(productoNomina, nominaEmpleado);
                            break;
                        // case 52:
                        // nominaEmpleadoEventualService
                        // .calcularProductoNominaPrimaVacacionalContrato(productoNomina,
                        // nominaEmpleado);
                        // break;
                        default:
                            this.nominaEmpleadoEventualService.calcularProductoNomina(productoNomina, nominaEmpleado, false);
                            break;
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
            if (productoNomina.getIdTipoContratacion() == EnumTipoContratacion.CONTRATO_FEDERAL) {
                try {
                    this.nominaEmpleadoEventualService.calcularProductoNominaContratoFederal(productoNomina, nominaEmpleado, false);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
        } else {
            try {
                this.nominaEmpleadoFederalizadoService.calcularProductoNominaFederales(productoNomina, nominaEmpleado, false);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        this.nominaEmpleadoEventualService.actualizarTotalesPorNominaEmpleado(nominaEmpleado);
    }

    public List<PensionesNominaDTO> obtenerPensionesNominaList(ProductoNominaDTO productoNomina) {
        return this.nominaEmpleadoEventualService.obtenerPensionesNominaList(productoNomina);
    }

    public void autorizarProductoNomina(ProductoNominaDTO productoNomina) {
        productoNomina.setIdEstatusProductoNomina(7);
        this.productoNominaService.actualizarProductoNomina(productoNomina);
        this.nominaEmpleadoEventualService.cambiarEstatusNominaEmpleado(productoNomina, 4);
    }

    public void devolverProductoNomina(ProductoNominaDTO productoNomina) {
        productoNomina.setIdEstatusProductoNomina(4);
        this.productoNominaService.actualizarProductoNomina(productoNomina);
        this.nominaEmpleadoEventualService.cambiarEstatusNominaEmpleado(productoNomina, 2);
    }

    public List<CuentaBancariaDTO> obtenerCuentaBancariaList(ProductoNominaDTO productoNomina) {
        return this.productoNominaService.obtenerCuentaBancariaList(productoNomina);
    }

    public List<BancoDTO> obtenerBancoList() {
        return this.productoNominaService.obtenerBancoList();
    }

    public void eliminarProductoNomina(ProductoNominaDTO productoNomina) {
        this.productoNominaService.eliminarProductoNomina(productoNomina);
    }

    public void actualizarEstatusNominaEmpleado(Integer idEstatusNominaEmpleado, Integer idNominaEmpleado, Integer idUsuario) {
        this.estatusNominaService.actualizarEstatusNominaEmpleado(idEstatusNominaEmpleado, idNominaEmpleado, idUsuario);
    }

    public Integer obtenerEstatusPorIdProductoNomina(Integer idProductoNominaEmpleado) {
        return this.estatusNominaService.obtenerEstatusPorIdProductoNomina(idProductoNominaEmpleado);
    }

    public List<ActualizarNominaEmpleadoDTO> obtenerActualizarNomina(ProductoNominaDTO productoNomina) {
        List<ActualizarNominaEmpleadoDTO> actualizarNominaEmpleadoList = this.actualizarNominaEmpleadoService.obtenerActualizarNomina(productoNomina);
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
                    this.actualizarNominaEmpleadoService.agregarNominaEmpleado(actualizarNominaEmpleado, productoNomina);
                    break;
                case "3":
                    this.productoNominaService.eliminarNominaEmpleado(actualizarNominaEmpleado.getIdNominaempleado());
                    break;
            }
        }
        if (!actualizarNominaEmpleadoListTem.isEmpty()) {
            this.actualizarNominaEmpleadoService.actualizarNomina(actualizarNominaEmpleadoListTem);
        }
    }

    public List<PagoNominaDTO> obtenerPagosNomina(ProductoNominaDTO productoNomina) {
        List<PagoNominaEntity> pagoNominaList = this.pagoNominaService.obtenerPagosNomina(productoNomina);
        if (pagoNominaList.isEmpty()) {
            pagoNominaList = this.pagoNominaService.crearPagosNomina(productoNomina);
        }
        List<PagoNominaDTO> pagoNominaDTOList = new ArrayList<>();
        for (PagoNominaEntity pagoNominaEntity : pagoNominaList) {
            PagoNominaDTO pagoNomina = this.pagoNominaService.toPagoNominaDTO(pagoNominaEntity);
            pagoNominaDTOList.add(pagoNomina);
        }
        return pagoNominaDTOList;
    }

    public PagoNominaDTO obtenerPagoNomina(PagoNominaDTO pagoNominaSelect) {
        return this.pagoNominaService.obtenerPagoNomina(pagoNominaSelect);
    }

    public PagoNominaDTO crearPagoNomina(PagoNominaDTO pagoNominaSelect) {
        return this.pagoNominaService.crearPagoNomina(pagoNominaSelect);
    }

    public PagoNominaDTO actualizarPagoNomina(PagoNominaDTO pagoNominaSelect) {
        return this.pagoNominaService.actualizarPagoNomina(pagoNominaSelect);
    }

    public void eliminarPagoNomina(PagoNominaDTO pagoNominaSelect) {
        this.pagoNominaService.eliminarPagoNomina(pagoNominaSelect);
    }

    public PagoNominaDTO obtenerNuevoPagoNomina(ProductoNominaDTO productoNomina) {
        PagoNominaDTO pagoNomina = this.pagoNominaService.obtenerListaRfcSinPago(productoNomina);
        if (pagoNomina == null) {
            pagoNomina = new PagoNominaDTO();
        }
        pagoNomina.setIdProductoNomina(productoNomina.getIdProductoNomina());
        pagoNomina.setIdBanco(7);
        pagoNomina.setFechaPago(productoNomina.getFinPeriodo());
        return pagoNomina;
    }

    public List<NominaEmpleadoDTO> obtenerNominaEmpleadoListPorPago(PagoNominaDTO pagoNomina) {
        return this.nominaEmpleadoEventualService.obtenerNominaEmpleadoListPorPago(pagoNomina);
    }

    public List<NominaEmpleadoDTO> obtenerNominaEmpleadoListSinPago(ProductoNominaDTO productoNomina) {
        return this.nominaEmpleadoEventualService.obtenerNominaEmpleadoListSinPago(productoNomina);
    }

    public Integer obtenerUltimoNumeroCheque(ProductoNominaDTO productoNomina) {
        return this.nominaEmpleadoEventualService.obtenerUltimoNumeroCheque();
    }

    public void generarNumeracionCheques(ProductoNominaDTO productoNomina) {
        this.productoNominaService.aplicarChequesProductoNomina(productoNomina);
    }

    public void pedirAutorizacionProductoNomina(ProductoNominaDTO productoNomina) {
        productoNomina.setIdEstatusProductoNomina(EstatusProductoNomina.PREAUTORIZADO);
        this.productoNominaService.actualizarProductoNomina(productoNomina);
        NuevaAutorizacionDTO dto = new NuevaAutorizacionDTO();
        dto.setMensajeNotificacion(" su autorización del producto de nomina: " + productoNomina.getNombreProducto());
        dto.setIdAccion(EnumTiposAccionesAutorizacion.AUTORIZAR_PRODUCTO_NOMINA_ESTATAL);
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        dto.setIdEntidadContexto(productoNomina.getIdProductoNomina());
        dto.setIdUsuarioLogeado(usuario.getIdUsuario());
        this.autorizacionesService.iniciarProcesoAprobacion(dto);
    }

    public Boolean esUsuarioAutorizaNomina(ProductoNominaDTO productoNomina) {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        Integer idOperacion = EnumTiposAccionesAutorizacion.AUTORIZAR_PRODUCTO_NOMINA_ESTATAL;
        Integer idEntidadContexto = productoNomina.getIdProductoNomina();
        Integer idUsuario = usuario.getIdUsuario();
        return this.autorizacionesService.esUsuarioAutoriza(idOperacion, idEntidadContexto, idUsuario);
    }
}