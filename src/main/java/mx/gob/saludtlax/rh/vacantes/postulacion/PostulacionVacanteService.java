/*
 *
 */

package mx.gob.saludtlax.rh.vacantes.postulacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.ConsultaAspiranteService;
import mx.gob.saludtlax.rh.empleados.administracion.BusquedaEmpleadoService;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.AspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.CandidatoVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.CandidatoVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.PostulacionVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.PostulacionVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralRepository;
import mx.gob.saludtlax.rh.persistencia.TipoPostulacionEntity;
import mx.gob.saludtlax.rh.persistencia.TipoPostulacionRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;
import mx.gob.saludtlax.rh.puestosautorizados.EnumEstatusPuesto;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import mx.gob.saludtlax.rh.vacantes.seleccion.EnumTipoCandidato;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 24/10/2016 10:34:25
 */
public class PostulacionVacanteService implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8439746627336080245L;
    @Inject
    private AspiranteRepository aspiranteRepository;
    @Inject
    private BusquedaEmpleadoService busquedaEmpleadoService;
    @Inject
    private CandidatoVacanteRepository candidatoVacanteRepository;
    @Inject
    private ConfiguracionPresupuestoRepository datosLaboralesRepository;
    @Inject
    private ConsultaAspiranteService consultaAspiranteService;
    @Inject
    private EmpleadoRepository empleadoRepository;
    @Inject
    private InventarioVacanteRepository inventarioVacanteRepository;
    @Inject
    private PostulacionVacanteRepository postulacionVacanteRepository;
    @Inject
    private PuestoGeneralRepository puestoGeneralRepository;
    @Inject
    private TipoPostulacionRepository tipoPostulacionRepository;
    @Inject
    private UsuarioRepository usuarioRepository;

    protected List<InfoCandidatoDTO> consultarCandidatosPostulacion(
            Integer idPostulacion) {

        if (!ValidacionUtil.esNumeroPositivo(idPostulacion)) {
            throw new ValidacionException(
                    "La postulación es requerida para obtener a los candidatos",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        List<CandidatoVacanteEntity> candidatosPostulados = candidatoVacanteRepository
                .listaCandidatosVacantesNoSeleccionadosPorIdPostulado(
                        idPostulacion);

        List<InfoCandidatoDTO> candidatos = new ArrayList<>();
        if (!candidatosPostulados.isEmpty()) {
            for (CandidatoVacanteEntity c : candidatosPostulados) {

                InfoCandidatoDTO dto = new InfoCandidatoDTO();
                dto.setIdCandidatoPostulado(c.getIdCandidatoVacante());
                dto.setIdContexto(c.getIdContexto());

                if (c.getTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {
                    AspiranteEntity aspiranteEntity = aspiranteRepository
                            .obtenerPorId(dto.getIdContexto());
                    dto.setNombre(aspiranteEntity.getNombreCompleto());
                    dto.setRfc(aspiranteEntity.getRfc());
                    dto.setCurp(aspiranteEntity.getCurp());
                    if (aspiranteEntity.getFechaNacimiento() != null) {
                        dto.setEdad(FechaUtil.calcularEdad(
                                aspiranteEntity.getFechaNacimiento()));
                    }
                    dto.setNacionalidad(aspiranteEntity.getNacionalidad());
                    dto.setSexo(aspiranteEntity.getIdSexo());
                    dto.setEstadoCivil(aspiranteEntity.getEstadoCivil());
                    dto.setDomicilio(aspiranteEntity.getDireccionCompleta());
                    if (ValidacionUtil.esCadenaVacia(
                            aspiranteEntity.getPerfilAcademico())) {
                        dto.setEstudios("SIN HISTORIAL ACADEMICO REGISTRADO");
                    } else {
                        dto.setEstudios(aspiranteEntity.getPerfilAcademico());
                    }

                    dto.setTipoCandidato("ASPIRANTE");
                    dto.setEstatus(aspiranteEntity.getIdEstatus());

                } else if (c.getTipoCandidato() == EnumTipoCandidato.EMPLEADO) {
                    EmpleadoEntity empleadoEntity = empleadoRepository
                            .obtenerPorId(dto.getIdContexto());
                    dto.setNombre(empleadoEntity.getNombreCompleto());
                    dto.setRfc(empleadoEntity.getRfc());
                    dto.setCurp(empleadoEntity.getCurp());
                    if (empleadoEntity.getFechaNacimiento() != null) {
                        dto.setEdad(FechaUtil.calcularEdad(
                                empleadoEntity.getFechaNacimiento()));
                    }
                    dto.setNacionalidad(empleadoEntity.getNacionalidad());
                    dto.setSexo(empleadoEntity.getIdSexo());
                    dto.setEstadoCivil(empleadoEntity.getEstadoCivil());
                    dto.setDomicilio(empleadoEntity.getDireccionCompleta());
                    if (ValidacionUtil.esCadenaVacia(
                            empleadoEntity.getPerfilAcademico())) {
                        dto.setEstudios("SIN HISTORIAL ACADEMICO REGISTRADO");
                    } else {
                        dto.setEstudios(empleadoEntity.getPerfilAcademico());
                    }

                    dto.setTipoCandidato("EMPLEADO");
                    dto.setEstatus(empleadoEntity.getIdEstatus());

                }
                dto.setIdTipoCandidato(c.getTipoCandidato());
                candidatos.add(dto);

            }
        }
        return candidatos;

    }

    protected List<InfoPostulacionDTO> consultarPostulacionesDisponibles() {
        List<InfoPostulacionDTO> postulacionesDisponibles = new ArrayList<>();

        List<PostulacionVacanteEntity> resultadotaPostuacionesActivas = postulacionVacanteRepository
                .obtenerListaPostuladoVacanteDisponibles();

        if (!resultadotaPostuacionesActivas.isEmpty()) {
            for (PostulacionVacanteEntity p : resultadotaPostuacionesActivas) {
                InfoPostulacionDTO postulacionDisponible = new InfoPostulacionDTO();
                postulacionDisponible
                        .setFechaPostulacion(p.getFechaPostulacion());
                postulacionDisponible
                        .setIdPostulacion(p.getIdPostuladoVacante());
                postulacionDisponible.setPuesto(p.getInventarioVacante()
                        .getConfiguracion().getPuesto().getCodigo() + "-"
                        + p.getInventarioVacante().getConfiguracion()
                                .getPuesto().getPuesto());
                postulacionDisponible.setSueldo(p.getInventarioVacante()
                        .getConfiguracion().getSueldo());
                postulacionDisponible
                        .setTipoContratacion(p.getInventarioVacante()
                                .getTipoContratacion().getTipoContratacion());
                postulacionDisponible.setTipoPostulacion(
                        p.getTipoPostulacion().getTipoPostulacion());
                if (p.getInventarioVacante().getUltimoEmpleado() != null) {
                    postulacionDisponible
                            .setUltimoEmpleado(p.getInventarioVacante()
                                    .getUltimoEmpleado().getNombreCompleto());
                }
                postulacionesDisponibles.add(postulacionDisponible);

            }

        }

        return postulacionesDisponibles;
    }

    protected Integer postularCandidato(PostulacionDTO dto) {
        if (!ValidacionUtil.esNumeroPositivo(dto.getIdUsuario())) {
            throw new ValidacionException("No se ha detectado usuario logeado.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdVacante())) {
            throw new ValidacionException(
                    "Es requerido indicar la vacante que quiere asignar.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdAspirante())
                && !ValidacionUtil.esNumeroPositivo(dto.getIdEmpleado())) {
            throw new ValidacionException(
                    "Es requerido seleccionar un candidato.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        int tipoCandidato = 0;
        Integer idCandidato = null;

        if (ValidacionUtil.esNumeroPositivo(dto.getIdAspirante())) {
            tipoCandidato = EnumTipoCandidato.ASPIRANTE;
            idCandidato = dto.getIdAspirante();
        } else {
            tipoCandidato = EnumTipoCandidato.EMPLEADO;
            idCandidato = dto.getIdEmpleado();
        }

        InventarioVacanteEntity puestoVacante = inventarioVacanteRepository
                .obtenerPorId(dto.getIdVacante());

        if (puestoVacante == null) {
            throw new ValidacionException(
                    "El puesto vacante  con identificador " + dto.getIdVacante()
                            + " no existe.",
                    ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
        }

        if (puestoVacante.getDisponible().equals("NO")) {
            throw new ReglaNegocioException(
                    "La vacante seleccionada ya no se encuentra disponible.",
                    ReglaNegocioCodigoError.VACANTE_NO_DISPONIBLE);
        }

        // Aplica para puestos que se crean por lotes y pueden tener cualquier
        // tipo de puesto, como los contratos federales.
        if (puestoVacante.getConfiguracion().getPuesto().getPuesto()
                .equals("SIN PUESTO")) {

            if (!ValidacionUtil.esNumeroPositivo(dto.getIdPuesto())) {
                throw new ValidacionException(
                        "Es requerido indicar el puesto del trabajador.",
                        ValidacionCodigoError.VALOR_REQUERIDO);
            }
            PuestoGeneralEntity puesto = puestoGeneralRepository
                    .obtenerPorId(dto.getIdPuesto());
            ConfiguracionPresupuestoEntity datosLaborales = puestoVacante
                    .getConfiguracion();
            datosLaborales.setPuesto(puesto);
            datosLaboralesRepository.actualizar(datosLaborales);
        }

        PostulacionVacanteEntity postulacion = postulacionVacanteRepository
                .tienePuestoPostulacionActiva(dto.getIdVacante());

        UsuarioEntity usuario = usuarioRepository
                .obtenerPorId(dto.getIdUsuario());
        // Validar que no tenga una postulación activa.
        if (candidatoVacanteRepository
                .tienePostulacionActivaCandidato(idCandidato, tipoCandidato)) {
            throw new ReglaNegocioException(
                    "El candidato tiene una postulación activa.",
                    ReglaNegocioCodigoError.POSTULACION_ACTIVA);
        }

        int tipoPostulacion = 0;
        if (puestoVacante.getEstatus().getIdEstatus()
                .equals(EnumEstatusPuesto.EMPLEADO_ACTIVO)) {
            tipoPostulacion = EnumTipoPostulacion.NUEVA_CREACION;
        } else {
            tipoPostulacion = EnumTipoPostulacion.VACANTE_LIBERADA;
        }

        TipoPostulacionEntity tipoPostulacionVacante = tipoPostulacionRepository
                .obtenerPorId(tipoPostulacion);

        CandidatoVacanteEntity candidato = new CandidatoVacanteEntity();
        if (postulacion == null) {
            PostulacionVacanteEntity nuevaPostulacion = new PostulacionVacanteEntity();
            nuevaPostulacion.setDisponible("SI");
            nuevaPostulacion.setFechaPostulacion(FechaUtil.fechaActual());
            nuevaPostulacion.setHoraPostulacion(FechaUtil.horaActual());
            nuevaPostulacion.setTipoPostulacion(tipoPostulacionVacante);
            nuevaPostulacion.setInventarioVacante(puestoVacante);
            nuevaPostulacion.setUsuario(usuario);
            postulacionVacanteRepository.crear(nuevaPostulacion);

            candidato.setPostuladoVacante(nuevaPostulacion);

        } else {
            candidato.setPostuladoVacante(postulacion);
        }

        candidato.setIdContexto(idCandidato);
        candidato.setSeleccionado(false);
        candidato.setTipoCandidato(tipoCandidato);
        candidatoVacanteRepository.crear(candidato);
        Integer idPostulacion = candidato.getPostuladoVacante()
                .getIdPostuladoVacante();

        return idPostulacion;
    }

    protected InfoCandidatoDTO obtenerInformacionCandidato(Integer idVacante) {

        Integer idCandidatoPostulado = inventarioVacanteRepository
                .obtenerIdCandidatoPostulado(idVacante);
        if (idCandidatoPostulado == null) {
            throw new ReglaNegocioException(
                    "La vacante no tiene asignada un candidato",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        CandidatoVacanteEntity candidato = candidatoVacanteRepository
                .obtenerPorId(idCandidatoPostulado);
        if (candidato == null) {
            throw new ReglaNegocioException(
                    "No se encontro el candidato con identificador "
                            + idCandidatoPostulado,
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }
        InfoCandidatoDTO infoCandidatoDTO = new InfoCandidatoDTO();
        if (candidato.getTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {
            infoCandidatoDTO = consultaAspiranteService
                    .obtenerInformacionCandidatoAspirante(
                            candidato.getIdContexto());

        } else if (candidato.getTipoCandidato() == EnumTipoCandidato.EMPLEADO) {
            infoCandidatoDTO = busquedaEmpleadoService
                    .obtenerInformacionCandidatoEmpleado(
                            candidato.getIdContexto());
        }
        infoCandidatoDTO.setIdTipoCandidato(candidato.getTipoCandidato());
        infoCandidatoDTO.setIdContexto(candidato.getIdContexto());
        return infoCandidatoDTO;

    }

    protected Integer obtenerIdPostulacionActiva(Integer idPuesto) {
        if (!ValidacionUtil.esNumeroPositivo(idPuesto)) {
            throw new ValidacionException(
                    "Es requerido el puesto para obtener la postulación.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        return postulacionVacanteRepository
                .obtenerIdPostulacionActiva(idPuesto);
    }

    protected void aprobarCandidatoPostulacion(Integer idPostulacion,
            Integer idCandidato) {

        if (!ValidacionUtil.esNumeroPositivo(idPostulacion)) {
            throw new ValidacionException("La postulación es requerido.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(idCandidato)) {
            throw new ValidacionException(
                    "Para realizar la aprobación es requerido seleccionar un candidato.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        PostulacionVacanteEntity postulacionVacante = postulacionVacanteRepository
                .obtenerPorId(idPostulacion);

        if (postulacionVacante == null) {
            throw new ValidacionException(
                    "No se encontró postulación con el identificador "
                            + idPostulacion,
                    ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
        }

        String noDisponible = "NO";

        postulacionVacante.setDisponible(noDisponible);

        postulacionVacanteRepository.actualizar(postulacionVacante);

        CandidatoVacanteEntity candidatoVacanteEntity = candidatoVacanteRepository
                .obtenerPorId(idCandidato);

        if (candidatoVacanteEntity
                .getTipoCandidato() == EnumTipoCandidato.EMPLEADO) {

            String tipoContratacion = inventarioVacanteRepository
                    .obtenerTipoContratacionEmpleadoActivo(
                            candidatoVacanteEntity.getIdContexto());

            if (tipoContratacion != null) {
                throw new ReglaNegocioException(
                        "El empleado cuenta con un puesto activo en "
                                + tipoContratacion.toLowerCase()
                                + " es requerido darle de baja para poder aprobarlo en el nuevo puesto.",
                        ReglaNegocioCodigoError.EMPLEADO_CON_PUESTO_ACTIVO);
            }

        }

        boolean haSidoAprobado = true;

        candidatoVacanteEntity.setSeleccionado(haSidoAprobado);

        candidatoVacanteRepository.actualizar(candidatoVacanteEntity);

        InventarioVacanteEntity vacanteSeleccionada = postulacionVacante
                .getInventarioVacante();

        if (vacanteSeleccionada.getIdCandidatoPostulado() != null) {
            throw new ReglaNegocioException("La vacante ya ha sido ocupada",
                    ReglaNegocioCodigoError.YA_AUTORIZADO);
        }
        vacanteSeleccionada.setIdCandidatoPostulado(
                candidatoVacanteEntity.getIdCandidatoVacante());
        inventarioVacanteRepository.actualizar(vacanteSeleccionada);

    }

    protected List<PuestoDisponibleDTO> consultarPuestosDisponibles() {
        List<PuestoDisponibleDTO> puestosDisponibles = new ArrayList<>();
        List<InventarioVacanteEntity> resultadoPuestosDisponibles = inventarioVacanteRepository
                .consultarPuestosDisponibles();
        if (!resultadoPuestosDisponibles.isEmpty()) {
            for (InventarioVacanteEntity i : resultadoPuestosDisponibles) {
                PuestoDisponibleDTO dto = new PuestoDisponibleDTO();
                if (i.getUltimoEmpleado() != null) {
                    dto.setEmpleadoAnterior(
                            i.getUltimoEmpleado().getNombreCompleto());
                }
                dto.setFolioVacante(i.getFolioVacante());
                dto.setIdPuesto(i.getIdVacante());
                dto.setSueldo(i.getConfiguracion().getSueldo());
                dto.setTipoContratacion(
                        i.getTipoContratacion().getTipoContratacion());
                dto.setTipoNombramiento(i.getConfiguracion().getNombramiento()
                        .getDescripcion());
                dto.setPuesto(i.getConfiguracion().getPuesto().getCodigo() + "-"
                        + i.getConfiguracion().getPuesto().getPuesto());

                if (i.getEstatus()
                        .getIdEstatus() == EnumEstatusPuesto.APERTURA_DESIGNACION) {
                    dto.setTipoVacante("APERTURA DESIGNACIÓN");
                } else if (i.getEstatus()
                        .getIdEstatus() == EnumEstatusPuesto.LIBERADA) {
                    dto.setTipoVacante("POR LIBERACION");
                }

                if (i.getAdscripcion() != null) {
                    dto.setAdscripcion(i.getAdscripcion().getAdscripcion());
                }
                puestosDisponibles.add(dto);
            }
        }

        return puestosDisponibles;
    }
}
