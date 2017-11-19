/*
 *
 */

package mx.gob.saludtlax.rh.empleados.interinatos;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.autorizaciones.AutorizacionesService;
import mx.gob.saludtlax.rh.autorizaciones.EnumTiposAccionesAutorizacion;
import mx.gob.saludtlax.rh.autorizaciones.NuevaAutorizacionDTO;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumEstatusAspirante;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.InterinatoEntity;
import mx.gob.saludtlax.rh.persistencia.InterinatoRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.vacantes.seleccion.EnumTipoCandidato;

/**
 * @author Leila Schiaffini Ehuan
 * @since 15/09/2016 17:37:31
 *
 */
public class InterinatoService {

    @Inject
    private AspiranteRepository aspiranteRepository;
    @Inject
    private EmpleadoRepository empleadoRepository;
    @Inject
    private InterinatoRepository interinatoRepository;
    @Inject
    private InventarioVacanteRepository inventarioVacanteRepository;
    @Inject
    private UsuarioRepository usuarioRepository;

    @Inject
    private AutorizacionesService autorizacionesService;

    protected List<DisponiblesInterinatoDTO> consultarDisponiblesInterinato(Integer tipoBusqueda) {

        List<InventarioVacanteEntity> puestosDisponibles = null;

        if (tipoBusqueda == EnumTipoBusquedaInterinato.POR_PERMISO) {
            puestosDisponibles = inventarioVacanteRepository.consultarDisponiblesInterinatoPorPermiso();
        } else {
            List<Integer> tiposContratacion = new ArrayList<>();
            tiposContratacion.add(EnumTipoContratacion.BASE);
            tiposContratacion.add(EnumTipoContratacion.REGULARIZADOS);
            tiposContratacion.add(EnumTipoContratacion.FORMALIZADOS);
        }

        List<DisponiblesInterinatoDTO> candidatos = new ArrayList<>();

        if (!puestosDisponibles.isEmpty()) {
            for (InventarioVacanteEntity e : puestosDisponibles) {
                DisponiblesInterinatoDTO dto = new DisponiblesInterinatoDTO();
                if (e.getAdscripcion() != null) {
                    dto.setAdscripcion(e.getAdscripcion().getAdscripcion());
                } else {
                    dto.setAdscripcion("SIN ADSCRIPCION ASIGNADA");
                }

                dto.setContratacion(e.getTipoContratacion().getTipoContratacion());
                dto.setFechaFin(e.getMovimientoPermiso().getFechaFinPermiso());
                dto.setFechaInicio(e.getMovimientoPermiso().getFechaInicioPermiso());
                dto.setIdMovimiento(e.getMovimientoPermiso().getIdMovimientoEmpleado());
                if (e.getFuncion() != null) {
                    dto.setFuncion(e.getFuncion().getFuncion());
                } else {
                    dto.setFuncion("SIN FUNCION ASIGNADA");
                }
                dto.setIdPuesto(e.getIdVacante());
                if (e.getServicio() != null) {
                    dto.setServicio(e.getServicio().getServicio());
                } else {
                    dto.setServicio("SIN SERVICIO ASIGNADO");
                }
                if (e.getSubadscripcion() != null) {
                    dto.setSubadscripcion(e.getSubadscripcion().getSubadscripcion());
                } else {
                    dto.setSubadscripcion("SIN SUBADSCRIPCION ASIGNADA.");
                }

                dto.setMotivoPermiso(e.getMovimientoPermiso().getMovimiento().getMovimiento());
                dto.setEmpleado(e.getEmpleadoActivo().getNombre());

                candidatos.add(dto);

            }

        }

        return candidatos;

    }

    protected void crearSolicitudInterinato(InterinatoDTO interinato) {

        UsuarioEntity usuarioEntity = usuarioRepository.obtenerPorId(interinato.getIdUsuario());
        if (usuarioEntity == null) {
            throw new ReglaNegocioException("Usuario no encontrado", ReglaNegocioCodigoError.USUARIO_NO_ENCONTRADO);
        }
        InventarioVacanteEntity puestoPropietario = inventarioVacanteRepository.obtenerPorId(interinato.getIdPuesto());

        if (puestoPropietario == null) {
            throw new ValidacionException("No se encontró registro del puesto solicitado.", ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
        }

        if (puestoPropietario.getDisponible().equals("SI")) {
            throw new ReglaNegocioException("El puesto " + puestoPropietario.getFolioVacante() + " no tiene asignado un empleado.",
                    ReglaNegocioCodigoError.ESTATUS_INCORRECTO);
        }

        if (puestoPropietario.getInterino()) {
            throw new ReglaNegocioException("El puesto ya tiene asignado un interino.", ReglaNegocioCodigoError.ESTATUS_INCORRECTO);
        }
        String candidato = "";

        if (interinato.getTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {

            String estatusAspirante = aspiranteRepository.obtenerEstatusAspirantePorId(interinato.getIdContexto());
            if (estatusAspirante.equals(EnumEstatusAspirante.EMPLEADO)) {
                throw new ReglaNegocioException("El aspirante se encuentra activo como empleado", ReglaNegocioCodigoError.ESTATUS_INCORRECTO);
            }

            candidato = aspiranteRepository.obtenerNombreAspirante(interinato.getIdContexto());
        } else if (interinato.getTipoCandidato() == EnumTipoCandidato.EMPLEADO) {
            if (!empleadoRepository.existeIdEmpleado(interinato.getIdContexto())) {
                throw new ValidacionException("No se encontró al empleado con identificador " + interinato.getIdContexto(),
                        ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
            }

            String tipoContratacion = inventarioVacanteRepository.obtenerTipoContratacionEmpleadoActivo(interinato.getIdContexto());

            if (tipoContratacion != null) {
                throw new ReglaNegocioException("El empleado cuenta con un puesto activo en " + tipoContratacion.toLowerCase()
                        + " es requerido darle de baja para poder asignarlo a un interinato.", ReglaNegocioCodigoError.EMPLEADO_CON_PUESTO_ACTIVO);
            }

            candidato = empleadoRepository.obtenerNombreEmpleadoId(interinato.getIdContexto());

        }

        InterinatoEntity nuevoInterinato = new InterinatoEntity();
        nuevoInterinato.setTipoCandidato(interinato.getTipoCandidato());
        nuevoInterinato.setEstatus(EnumEstatusInterinatos.PENDIENTE);
        nuevoInterinato.setPuestoPropietario(puestoPropietario);
        nuevoInterinato.setIdUsuario(usuarioEntity.getIdUsuario());
        nuevoInterinato.setIdContexto(interinato.getIdContexto());
        interinatoRepository.crear(nuevoInterinato);

        String mensaje = " interinato para el candidato " + candidato.toLowerCase() + " para cubrir la "
                + puestoPropietario.getMovimientoPermiso().getMovimiento().getMovimiento() + " del empleado "
                + puestoPropietario.getEmpleadoActivo().getNombreCompleto().toLowerCase();
        // Iniciar proceso de autorizacion
        NuevaAutorizacionDTO autorizacion = new NuevaAutorizacionDTO();
        autorizacion.setIdAccion(EnumTiposAccionesAutorizacion.APERTURA_INTERINATO);
        autorizacion.setIdEntidadContexto(nuevoInterinato.getIdInterinato());
        autorizacion.setIdUsuarioLogeado(usuarioEntity.getIdUsuario());
        autorizacion.setMensajeNotificacion(mensaje);

        autorizacionesService.iniciarProcesoAprobacion(autorizacion);

    }

}
