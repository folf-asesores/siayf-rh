/*
 *
 */

package mx.gob.saludtlax.rh.empleados.movimientos;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.autorizaciones.AutorizacionesService;
import mx.gob.saludtlax.rh.autorizaciones.EnumTiposAccionesAutorizacion;
import mx.gob.saludtlax.rh.autorizaciones.NuevaAutorizacionDTO;
import mx.gob.saludtlax.rh.empleados.administracion.BitacoraEmpleadoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.BitacoraModificacionService;
import mx.gob.saludtlax.rh.empleados.administracion.EnumEstatusEmpleado;
import mx.gob.saludtlax.rh.empleados.administracion.EnumTipoModificacionEmpleado;
import mx.gob.saludtlax.rh.empleados.datolaboral.EnumEstatusConfiguracion;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionAprobacionRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusConfiguracionesEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusConfiguracionesRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusPuestosEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusPuestosRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralRepository;
import mx.gob.saludtlax.rh.persistencia.TipoMovimientoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoMovimientoEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;
import mx.gob.saludtlax.rh.puestosautorizados.EnumEstatusPuesto;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.util.FechaUtil;

/**
 * O
 *
 * @author Leila Schiaffini Ehuan
 *
 * @since 03/05/2016 03/05/2016
 */

public class MovimientosEmpleadosService {
    @Inject
    private AutorizacionesService autorizacionService;
    @Inject
    private BitacoraModificacionService bitacoraModificacionService;

    @Inject
    private ConfiguracionAprobacionRepository configuracionAprobacionRepository;
    @Inject
    private MovimientoEmpleadoRepository bitacoraMovimientoAutorizadoRepository;
    @Inject
    private ConfiguracionPresupuestoRepository configuracionPresupuestoRepository;
    @Inject
    private EmpleadoRepository empleadoRepository;
    @Inject
    private EstatusConfiguracionesRepository estatusConfiguracionesRepository;
    @Inject
    private EstatusPuestosRepository estatusPuestoRepository;
    @Inject
    private InventarioVacanteRepository inventarioVacanteRepository;
    @Inject
    private PuestoGeneralRepository puestogeneralRepository;
    @Inject
    private TipoMovimientoEmpleadoRepository tipoMovimientoEmpleadoRepository;
    @Inject
    private UsuarioRepository usuarioRepository;

    protected void crearMovimiento(RegistroMovimientoDTO dto) {

        InventarioVacanteEntity puesto = inventarioVacanteRepository.obtenerPorId(dto.getIdVacante());
        if (puesto == null) {
            throw new ReglaNegocioException("No se encontró el puesto del empleado con identificador " + dto.getIdVacante(),
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        UsuarioEntity usuarioEntity = usuarioRepository.obtenerPorId(dto.getIdUsuario());
        if (usuarioEntity == null) {
            throw new ReglaNegocioException("Usuario no encontrado", ReglaNegocioCodigoError.USUARIO_NO_ENCONTRADO);
        }

        TipoMovimientoEmpleadoEntity movimiento = tipoMovimientoEmpleadoRepository.obtenerPorId(dto.getIdMovimiento());

        if (puesto.getDisponible().equals("SI")) {
            throw new ReglaNegocioException("La vacante seleccionada no tiene asignado un empleado activo, se encuentra disponible",
                    ReglaNegocioCodigoError.VACANTE_OCUPADA);
        }
        // validarMovimiento(puesto.getTipoContratacion().getId(),
        // movimiento.getIdPadre());

        ConfiguracionPresupuestoEntity configuracionPresupuestal = puesto.getConfiguracion();
        EmpleadoEntity empleado = configuracionPresupuestal.getEmpleado();
        MovimientoEmpleadoEntity movimientoEmpleado = new MovimientoEmpleadoEntity();

        if (movimiento.getIdPadre() == EnumTipoMovimiento.LICENCIA_POR_INCAPACIDAD_MEDICA || movimiento.getIdPadre() == EnumTipoMovimiento.LICENCIAS_C_C_S
                || movimiento.getIdPadre() == EnumTipoMovimiento.LICENCIAS_C_S_S || movimiento.getIdPadre() == EnumTipoMovimiento.LICENCIAS_D_C_S
                || movimiento.getIdPadre() == EnumTipoMovimiento.LICENCIAS_D_S_S) {

            movimientoEmpleado.setFechaInicioPermiso(dto.getFechaInicio());
            movimientoEmpleado.setFechaFinPermiso(dto.getFechaFin());

        }

        movimientoEmpleado.setEmpleado(empleado);
        movimientoEmpleado.setFechaIngreso(FechaUtil.fechaActual());
        movimientoEmpleado.setHoraIngreso(FechaUtil.fechaActual());
        movimientoEmpleado.setUsuarioEntity(usuarioEntity);
        movimientoEmpleado.setMovimiento(movimiento);
        movimientoEmpleado.setNumeroOficio(dto.getNumeroOficio());
        movimientoEmpleado.setObservaciones(dto.getObservaciones());
        movimientoEmpleado.setInventarioVacante(puesto);
        movimientoEmpleado.setIdPuestoGeneral(dto.getIdPuestoGeneral());

        bitacoraMovimientoAutorizadoRepository.crear(movimientoEmpleado);

        if (movimiento.isAutorizacion()) {
            boolean tieneConfiguradoUsuariosAprobacion = configuracionAprobacionRepository.tieneProcesoAprobacionMovimientoPersonal(movimiento.getIdPadre());
            if (!tieneConfiguradoUsuariosAprobacion) {
                throw new ReglaNegocioException("El movimiento requiere de autorización pero no tiene configurados usuarios.",
                        ReglaNegocioCodigoError.CONFIGURACION_NO_EXISTE);
            }
            movimientoEmpleado.setEstatusMovimiento(EnumEstatusMovimiento.PENDIENTE);
            String mensaje = "el movimiento " + movimiento.getMovimiento().toLowerCase() + " al empleado " + empleado.getNombreCompleto();
            // Iniciar proceso de autorizacion
            NuevaAutorizacionDTO autorizacion = new NuevaAutorizacionDTO();
            autorizacion.setIdAccion(EnumTiposAccionesAutorizacion.MOVIMIENTOS_PERSONAL);
            autorizacion.setIdEntidadContexto(movimientoEmpleado.getIdMovimientoEmpleado());
            autorizacion.setIdUsuarioLogeado(dto.getIdUsuario());
            autorizacion.setMensajeNotificacion(mensaje);
            autorizacion.setTipoMovimiento(movimiento.getIdPadre());
            autorizacionService.iniciarProcesoAprobacion(autorizacion);

            registrarBitacora(movimientoEmpleado.getEmpleado().getIdEmpleado(), movimientoEmpleado.getUsuarioEntity().getIdUsuario(), "-", "-",
                    EnumTipoModificacionEmpleado.SOLICITUD_MOVIMIENTO, movimiento.getMovimiento());

        } else {
            movimientoEmpleado.setEstatusMovimiento(EnumEstatusMovimiento.AUTORIZADO);

            registrarBitacora(movimientoEmpleado.getEmpleado().getIdEmpleado(), movimientoEmpleado.getUsuarioEntity().getIdUsuario(), "-", "-",
                    EnumTipoModificacionEmpleado.MOVIMIENTO_AUTORIZADO, movimiento.getMovimiento());
        }

        bitacoraMovimientoAutorizadoRepository.actualizar(movimientoEmpleado);

    }

    private void registrarBitacora(Integer idEmpleado, Integer idUsuario, String lccNueva, String lccActual, Integer idMovimientoBitacora, String comentarios) {
        BitacoraEmpleadoDTO bitacora = new BitacoraEmpleadoDTO();
        bitacora.setComentarios(comentarios);
        bitacora.setEmpleado(idEmpleado);
        bitacora.setIdUsuario(idUsuario);
        bitacora.setLccActual(lccNueva);
        bitacora.setLccAnterior(lccActual);
        bitacora.setTipoMovimientoEmpleado(idMovimientoBitacora);
        bitacoraModificacionService.registrarBitacora(bitacora);
    }

    public String autorizarMovimiento(Integer idMovimiento) {
        MovimientoEmpleadoEntity movimientoEmpleado = bitacoraMovimientoAutorizadoRepository.obtenerPorId(idMovimiento);
        if (movimientoEmpleado == null) {
            throw new ValidacionException("El movimiento con identificador " + idMovimiento + " no se ha encontrado",
                    ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
        }
        TipoMovimientoEmpleadoEntity movimiento = tipoMovimientoEmpleadoRepository.obtenerPorId(movimientoEmpleado.getMovimiento().getIdTipoMovimiento());
        InventarioVacanteEntity puesto = movimientoEmpleado.getInventarioVacante();
        EmpleadoEntity empleadoMovimiento = movimientoEmpleado.getEmpleado();
        ConfiguracionPresupuestoEntity configuracionPresupuestal = puesto.getConfiguracion();
        if (movimiento.getIdPadre() == EnumTipoMovimiento.BAJAS_DEFINITIVAS) {
            if (movimiento.getClave() != 1113) {
                EstatusPuestosEntity estatusPuesto = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.LIBERADA);
                puesto.setDisponible("SI");
                puesto.setUltimoEmpleado(empleadoMovimiento);
                puesto.setEstatus(estatusPuesto);

                inventarioVacanteRepository.actualizar(puesto);

                empleadoMovimiento.setIdEstatus(EnumEstatusEmpleado.INACTIVO);
                empleadoMovimiento.setTipoBaja(movimiento.getIdTipoMovimiento());
                empleadoMovimiento.setFechaBaja(FechaUtil.fechaActual());
                empleadoRepository.actualizar(empleadoMovimiento);

                EstatusConfiguracionesEntity estatus = estatusConfiguracionesRepository.obtenerPorId(EnumEstatusConfiguracion.INACTIVO);
                configuracionPresupuestal.setEstatus(estatus);
                configuracionPresupuestoRepository.actualizar(configuracionPresupuestal);
            } else {
                EstatusPuestosEntity estatusPuesto = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.EMPLEADO_EN_PERMISO);
                puesto.setEstatus(estatusPuesto);
                puesto.setMovimientoPermiso(movimientoEmpleado);
                inventarioVacanteRepository.actualizar(puesto);
            }

            registrarBitacora(movimientoEmpleado.getEmpleado().getIdEmpleado(), movimientoEmpleado.getUsuarioEntity().getIdUsuario(), "-", "-",
                    EnumTipoModificacionEmpleado.MOVIMIENTO_AUTORIZADO, movimiento.getMovimiento());

        } else if (movimiento.getIdPadre() == EnumTipoMovimiento.LICENCIA_POR_INCAPACIDAD_MEDICA
                || movimiento.getIdPadre() == EnumTipoMovimiento.LICENCIAS_C_C_S || movimiento.getIdPadre() == EnumTipoMovimiento.LICENCIAS_C_S_S
                || movimiento.getIdPadre() == EnumTipoMovimiento.LICENCIAS_D_C_S || movimiento.getIdPadre() == EnumTipoMovimiento.LICENCIAS_D_S_S) {
            EstatusPuestosEntity estatus = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.EMPLEADO_EN_PERMISO);
            puesto.setFechaInicioPermiso(movimientoEmpleado.getFechaInicioPermiso());
            puesto.setFechaFinPermiso(movimientoEmpleado.getFechaFinPermiso());
            puesto.setMovimientoPermiso(movimientoEmpleado);
            puesto.setEstatus(estatus);
            inventarioVacanteRepository.actualizar(puesto);

            String comentarios = movimiento.getMovimiento() + "EN EL PERIODO " + FechaUtil.formatoFecha(movimientoEmpleado.getFechaInicioPermiso()) + "-"
                    + FechaUtil.formatoFecha(movimientoEmpleado.getFechaFinPermiso());

            registrarBitacora(movimientoEmpleado.getEmpleado().getIdEmpleado(), movimientoEmpleado.getUsuarioEntity().getIdUsuario(), "-", "-",
                    EnumTipoModificacionEmpleado.MOVIMIENTO_AUTORIZADO, comentarios);

        } else if (movimiento.getIdPadre() == EnumTipoMovimiento.BAJAS_TEMPORALES) {
            EstatusPuestosEntity estatusPuesto = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.EMPLEADO_EN_PERMISO);
            puesto.setEstatus(estatusPuesto);
            puesto.setMovimientoPermiso(movimientoEmpleado);
            inventarioVacanteRepository.actualizar(puesto);
            registrarBitacora(movimientoEmpleado.getEmpleado().getIdEmpleado(), movimientoEmpleado.getUsuarioEntity().getIdUsuario(), "-", "-",
                    EnumTipoModificacionEmpleado.MOVIMIENTO_AUTORIZADO, movimiento.getMovimiento());

        } else if (movimiento.getIdPadre() == EnumTipoMovimiento.PROMOCIONES) {
            PuestoGeneralEntity puestoPromocion = puestogeneralRepository.obtenerPorId(movimientoEmpleado.getIdPuestoGeneral());
            String lccNueva = "PUESTO NUEVO: " + puestoPromocion.getCodigo() + " " + puestoPromocion.getPuesto();
            ConfiguracionPresupuestoEntity datoLaboral = movimientoEmpleado.getInventarioVacante().getConfiguracion();

            String lccActual = "PUESTO ANTERIOR: " + datoLaboral.getPuesto().getCodigo() + " " + datoLaboral.getPuesto().getPuesto();

            registrarBitacora(movimientoEmpleado.getEmpleado().getIdEmpleado(), movimientoEmpleado.getUsuarioEntity().getIdUsuario(), lccNueva, lccActual,
                    EnumTipoModificacionEmpleado.MOVIMIENTO_AUTORIZADO, " ");

            datoLaboral.setPuesto(puestoPromocion);
            configuracionPresupuestoRepository.actualizar(datoLaboral);

        }

        movimientoEmpleado.setFechaAutorizacion(FechaUtil.fechaActual());
        bitacoraMovimientoAutorizadoRepository.actualizar(movimientoEmpleado);

        String mensaje = movimiento.getMovimiento() + " al empleado " + movimientoEmpleado.getEmpleado().getNombreCompleto();
        return mensaje;
    }

    protected void validarMovimiento(Integer idPuesto, Integer tipoMovimiento) {
        int tipoContratacion = inventarioVacanteRepository.obtenerIdTipoContratacionPuesto(idPuesto);
        // int movimientoPadre = obtenerPadreMovimiento(tipoMovimiento);

        if (tipoMovimiento == EnumTipoMovimiento.PROMOCION_PUESTO_AUMENTO_PERCEPCIONES) {
            if (tipoContratacion != EnumTipoContratacion.BASE && tipoContratacion != EnumTipoContratacion.FORMALIZADOS
                    && tipoContratacion != EnumTipoContratacion.REGULARIZADOS && tipoContratacion != EnumTipoContratacion.HOMOLOGADOS) {

                throw new ReglaNegocioException("El tipo de contratación no tiene permitido el movimiento seleccionado.",
                        ReglaNegocioCodigoError.MOVIMIENTO_NO_AUTORIZADO);
            }
        }

    }

    protected Integer obtenerPadreMovimiento(Integer idMovimiento) {
        if (idMovimiento == null) {
            throw new ValidacionException("El movimiento es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        return tipoMovimientoEmpleadoRepository.obtenerMovimientoPadre(idMovimiento);
    }

}
