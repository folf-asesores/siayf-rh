/*
 *
 */

package mx.gob.saludtlax.rh.puestosautorizados;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.administracion.BitacoraEmpleadoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.BitacoraModificacionService;
import mx.gob.saludtlax.rh.empleados.administracion.EnumTipoModificacionEmpleado;
import mx.gob.saludtlax.rh.empleados.administracion.UbicacionEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.AdscripcionEntity;
import mx.gob.saludtlax.rh.persistencia.AdscripcionRepository;
import mx.gob.saludtlax.rh.persistencia.CentroResponsabilidadEntity;
import mx.gob.saludtlax.rh.persistencia.CentroResponsabilidadRepository;
import mx.gob.saludtlax.rh.persistencia.CluesEntity;
import mx.gob.saludtlax.rh.persistencia.CluesRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoRepository;
import mx.gob.saludtlax.rh.persistencia.FuncionEntity;
import mx.gob.saludtlax.rh.persistencia.FuncionRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.ProgramaEntity;
import mx.gob.saludtlax.rh.persistencia.ProgramaRepository;
import mx.gob.saludtlax.rh.persistencia.ServicioEntity;
import mx.gob.saludtlax.rh.persistencia.ServicioRepository;
import mx.gob.saludtlax.rh.persistencia.SubadscripcionEntity;
import mx.gob.saludtlax.rh.persistencia.SubadscripcionRepository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 * @since 13/09/2016 16:42:31
 *
 */
public class PuestosEmpleadosService {
    @Inject
    private ServicioRepository actividadRepository;
    @Inject
    private AdscripcionRepository adscripcionRepository;
    @Inject
    private ConfiguracionPresupuestoRepository datoLaboralRepository;
    @Inject
    private CluesRepository clueRepository;
    @Inject
    private CentroResponsabilidadRepository centroResponsabilidadRepository;
    @Inject
    private SubadscripcionRepository areaAdscripcionRepository;
    @Inject
    private FuncionRepository funcionRepository;
    @Inject
    private InventarioVacanteRepository inventarioVacanteRepository;
    @Inject
    private ProgramaRepository programaRepository;

    @Inject
    private BitacoraModificacionService bitacoraModificacionService;

    /**
     * Ubica al empleado en su adscripcion, area adscripcion, lugar adscripcion,
     * actividad o funcion
     *
     * @param ubicacionEmpleadoDTO
     */
    protected void ubicarEmpleado(UbicacionEmpleadoDTO ubicacionEmpleadoDTO) {
        InventarioVacanteEntity puesto = inventarioVacanteRepository
                .obtenerPorId(ubicacionEmpleadoDTO.getIdInventarioVacante());
        if (puesto == null) {
            throw new ReglaNegocioException(
                    "El puesto con identificador "
                            + ubicacionEmpleadoDTO.getIdInventarioVacante()
                            + " no se encuentra registrado",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        if (puesto.getDisponible().equals("SI")) {
            throw new ReglaNegocioException(
                    "El puesto no tiene asignado un empleado.",
                    ReglaNegocioCodigoError.VACANTE_DISPONIBLE);
        }

        String lccActual = "";
        String lccAnterior = "";

        if (ValidacionUtil
                .esNumeroPositivo(ubicacionEmpleadoDTO.getIdAdscripcion())) {

            if (puesto.getAdscripcion() != null) {
                if (!puesto.getAdscripcion().getIdAdscripcion()
                        .equals(ubicacionEmpleadoDTO.getIdAdscripcion())) {

                    lccAnterior = "ADSCRIPCIÓN: "
                            + puesto.getAdscripcion().getAdscripcion();

                    AdscripcionEntity adscripcionEntity = adscripcionRepository
                            .obtenerPorId(
                                    ubicacionEmpleadoDTO.getIdAdscripcion());
                    puesto.setAdscripcion(adscripcionEntity);

                    lccActual = "ADSCRIPCIÓN: "
                            + puesto.getAdscripcion().getAdscripcion();
                }
            } else {
                AdscripcionEntity adscripcionEntity = adscripcionRepository
                        .obtenerPorId(ubicacionEmpleadoDTO.getIdAdscripcion());
                puesto.setAdscripcion(adscripcionEntity);

                lccActual = "ADSCRIPCIÓN: "
                        + puesto.getAdscripcion().getAdscripcion();
            }

        }

        if (ValidacionUtil
                .esNumeroPositivo(ubicacionEmpleadoDTO.getIdSubadscripcion())) {
            if (puesto.getAdscripcion() == null) {
                throw new ReglaNegocioException(
                        "Para asignar el area de subadscripcion , es requerido que indique su adscripción.",
                        ReglaNegocioCodigoError.UBICACION_INCORRECTA);
            }

            if (puesto.getSubadscripcion() != null) {
                if (!puesto.getSubadscripcion().getIdSubadscripcion()
                        .equals(ubicacionEmpleadoDTO.getIdSubadscripcion())) {

                    lccAnterior = lccAnterior + " SUBADSCRIPCIÓN: "
                            + puesto.getSubadscripcion().getSubadscripcion();

                    SubadscripcionEntity areaAdscripcionEntity = areaAdscripcionRepository
                            .obtenerPorId(
                                    ubicacionEmpleadoDTO.getIdSubadscripcion());
                    puesto.setSubadscripcion(areaAdscripcionEntity);

                    lccActual = lccActual + " SUBADSCRIPCIÓN: "
                            + puesto.getSubadscripcion().getSubadscripcion();
                }

            } else {
                SubadscripcionEntity areaAdscripcionEntity = areaAdscripcionRepository
                        .obtenerPorId(
                                ubicacionEmpleadoDTO.getIdSubadscripcion());
                puesto.setSubadscripcion(areaAdscripcionEntity);

                lccActual = lccActual + " SUBADSCRIPCIÓN: "
                        + puesto.getSubadscripcion().getSubadscripcion();
            }

        }

        if (ValidacionUtil
                .esNumeroPositivo(ubicacionEmpleadoDTO.getIdServicio())) {

            if (puesto.getSubadscripcion() == null) {
                throw new ReglaNegocioException(
                        "Para asignar la subadscripción es requerido tener asignada una adscripción.",
                        ReglaNegocioCodigoError.UBICACION_INCORRECTA);
            }

            if (puesto.getServicio() != null) {
                if (!puesto.getServicio().getIdServicio()
                        .equals(ubicacionEmpleadoDTO.getIdServicio())) {
                    lccAnterior = lccAnterior + " SERVICIO: "
                            + puesto.getServicio().getServicio();

                    ServicioEntity actividadEntity = actividadRepository
                            .obtenerPorId(ubicacionEmpleadoDTO.getIdServicio());
                    puesto.setServicio(actividadEntity);

                    lccActual = lccActual + " SERVICIO: "
                            + puesto.getServicio().getServicio();
                }

            } else {
                ServicioEntity actividadEntity = actividadRepository
                        .obtenerPorId(ubicacionEmpleadoDTO.getIdServicio());
                puesto.setServicio(actividadEntity);

                lccActual = lccActual + " SERVICIO: "
                        + puesto.getServicio().getServicio();
            }

        }
        if (ValidacionUtil
                .esNumeroPositivo(ubicacionEmpleadoDTO.getIdFuncion())) {
            if (puesto.getServicio() == null) {
                throw new ReglaNegocioException(
                        "Para asignar una función , es requerido que indique su servicio.",
                        ReglaNegocioCodigoError.UBICACION_INCORRECTA);
            }

            if (puesto.getFuncion() != null) {

                if (!puesto.getFuncion().getIdFuncion()
                        .equals(ubicacionEmpleadoDTO.getIdFuncion())) {

                    lccAnterior = lccAnterior + " FUNCIÓN: "
                            + puesto.getFuncion().getFuncion();

                    FuncionEntity funcionEntity = funcionRepository
                            .obtenerPorId(ubicacionEmpleadoDTO.getIdFuncion());
                    puesto.setFuncion(funcionEntity);

                    lccActual = lccActual + " FUNCIÓN: "
                            + puesto.getFuncion().getFuncion();
                }

            } else {
                FuncionEntity funcionEntity = funcionRepository
                        .obtenerPorId(ubicacionEmpleadoDTO.getIdFuncion());
                puesto.setFuncion(funcionEntity);

                lccActual = lccActual + " FUNCIÓN: "
                        + puesto.getFuncion().getFuncion();
            }

        }

        if (ValidacionUtil.esNumeroPositivo(ubicacionEmpleadoDTO.getIdClue())) {
            if (puesto.getClue() != null) {
                if (!puesto.getClue().getIdClues()
                        .equals(ubicacionEmpleadoDTO.getIdClue())) {
                    lccAnterior = lccAnterior + " CLUE: "
                            + puesto.getClue().getClues();
                    CluesEntity clue = clueRepository
                            .obtenerPorId(ubicacionEmpleadoDTO.getIdClue());
                    puesto.setClue(clue);
                    lccActual = lccActual + " CLUE: "
                            + puesto.getClue().getClues();

                }
            } else {

                CluesEntity clue = clueRepository
                        .obtenerPorId(ubicacionEmpleadoDTO.getIdClue());
                puesto.setClue(clue);
                lccActual = lccActual + " CLUE: " + puesto.getClue().getClues();
            }
        }

        if (ValidacionUtil.esNumeroPositivo(
                ubicacionEmpleadoDTO.getIdCentroResponsabilidad())) {

            ConfiguracionPresupuestoEntity datoLaboral = puesto
                    .getConfiguracion();

            if (datoLaboral != null) {
                if (datoLaboral.getCentroResponsabilidad() != null) {
                    if (!datoLaboral.getCentroResponsabilidad()
                            .getIdCentroResponsabilidad()
                            .equals(datoLaboral.getCentroResponsabilidad()
                                    .getIdCentroResponsabilidad())) {
                        lccAnterior = lccAnterior + " CENTRO RESPONSABILIDAD: "
                                + puesto.getConfiguracion()
                                        .getCentroResponsabilidad()
                                        .getDescripcion();

                        CentroResponsabilidadEntity centroResponsabilidadEntity = centroResponsabilidadRepository
                                .obtenerPorId(ubicacionEmpleadoDTO
                                        .getIdCentroResponsabilidad());
                        datoLaboral.setCentroResponsabilidad(
                                centroResponsabilidadEntity);
                        datoLaboralRepository.actualizar(datoLaboral);

                        lccActual = lccActual + " CENTRO RESPONSABILIDAD: "
                                + puesto.getConfiguracion()
                                        .getCentroResponsabilidad()
                                        .getDescripcion();

                    }

                } else {
                    CentroResponsabilidadEntity centroResponsabilidadEntity = centroResponsabilidadRepository
                            .obtenerPorId(ubicacionEmpleadoDTO
                                    .getIdCentroResponsabilidad());
                    datoLaboral.setCentroResponsabilidad(
                            centroResponsabilidadEntity);
                    datoLaboralRepository.actualizar(datoLaboral);

                    lccActual = lccActual + " CENTRO RESPONSABILIDAD: "
                            + puesto.getConfiguracion()
                                    .getCentroResponsabilidad()
                                    .getDescripcion();
                }

            }

        }

        inventarioVacanteRepository.actualizar(puesto);

        BitacoraEmpleadoDTO bitacora = new BitacoraEmpleadoDTO();
        bitacora.setComentarios("Actualización configuración laboral");
        bitacora.setEmpleado(puesto.getEmpleadoActivo().getIdEmpleado());
        bitacora.setIdUsuario(ubicacionEmpleadoDTO.getIdUsuario());
        bitacora.setLccActual(lccActual);
        bitacora.setLccAnterior(lccAnterior);
        bitacora.setTipoMovimientoEmpleado(
                EnumTipoModificacionEmpleado.AREA_LABORAL);
        bitacoraModificacionService.registrarBitacora(bitacora);

    }

    public void modificacionPrograma(Integer idPuesto, Integer idPrograma) {
        if (!ValidacionUtil.esNumeroPositivo(idPuesto)) {
            throw new ValidacionException("El puesto es requerido.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }
        if (!ValidacionUtil.esNumeroPositivo(idPrograma)) {
            throw new ValidacionException("El programa es requerido.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        InventarioVacanteEntity puesto = inventarioVacanteRepository
                .obtenerPorId(idPuesto);
        if (puesto == null) {
            throw new SistemaException(
                    "No se encontró el puesto con identificador " + idPuesto,
                    SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
        }

        if (puesto.getTipoContratacion()
                .getId() != EnumTipoContratacion.CONTRATO_ESTATAL) {
            throw new ReglaNegocioException(
                    "La modificación de programa solo está permitida para personal de contrato estatal.",
                    ReglaNegocioCodigoError.MOVIMIENTO_NO_AUTORIZADO);
        }

        ProgramaEntity programa = programaRepository.obtenerPorId(idPrograma);

        if (programa == null) {
            throw new SistemaException(
                    "No se encontró el programa con identificador "
                            + idPrograma,
                    SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
        }

        puesto.setPrograma(programa);
        inventarioVacanteRepository.actualizar(puesto);

    }
}
