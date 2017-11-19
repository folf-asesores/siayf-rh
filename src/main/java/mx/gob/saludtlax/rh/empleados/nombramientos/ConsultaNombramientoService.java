/*
 *
 */

package mx.gob.saludtlax.rh.empleados.nombramientos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.administracion.EnumEstadoCivil;
import mx.gob.saludtlax.rh.empleados.administracion.EnumTipoSexo;
import mx.gob.saludtlax.rh.empleados.nombramientos.impresion.InfoLugarAdscripcionNombramientoDTO;
import mx.gob.saludtlax.rh.empleados.nombramientos.impresion.InfoNombramientoDTO;
import mx.gob.saludtlax.rh.empleados.nombramientos.impresion.NombramientoDetalleDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.AdscripcionRepository;
import mx.gob.saludtlax.rh.persistencia.EstructuraReporteEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraReporteRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.LugarAdscripcionRepository;
import mx.gob.saludtlax.rh.persistencia.NombramientoEntity;
import mx.gob.saludtlax.rh.persistencia.NombramientoRepository;
import mx.gob.saludtlax.rh.persistencia.SubadscripcionRepository;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 18:27:59 12/09/2016
 */
public class ConsultaNombramientoService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 693836394387338656L;

    @Inject
    private NombramientoRepository nombramientoRepository;
    @Inject
    private InventarioVacanteRepository inventarioVacanteRepository;
    @Inject
    private AdscripcionRepository adscripcionRepository;
    @Inject
    private SubadscripcionRepository areaAdscripcionRepository;
    @Inject
    private LugarAdscripcionRepository lugarAdscripcionRepository;
    @Inject
    private EstructuraReporteRepository estructuraReporteRepository;

    protected NombramientoDetalleDTO obtenerNombramientoReportePorId(
            Integer idNombramiento, Integer idClasificacion) {

        String contexto = "obtenerNombramientoReportePorId: ";

        if (idNombramiento == null) {
            throw new ValidacionException(contexto
                    + "El idenfiticador del nombramiento es requerido.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (idClasificacion == null) {
            throw new ValidacionException(contexto
                    + "El idenfiticador de estructura reporte es requerido.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        NombramientoEntity nombramientoEntity = nombramientoRepository
                .obtenerPorId(idNombramiento);

        if (nombramientoEntity == null) {
            throw new ReglaNegocioException(contexto
                    + "El idenfiticador del nombramiento es requerido.",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        EstructuraReporteEntity estructuraReporteEntity = estructuraReporteRepository
                .obtenerPorIdClasificacion(idClasificacion);

        if (estructuraReporteEntity == null) {
            throw new ReglaNegocioException(contexto
                    + "La estructura del reporte no se encuentra registrado.",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        NombramientoDetalleDTO dto = new NombramientoDetalleDTO();

        dto.setTextoPosicionUno(estructuraReporteEntity.getTextoUno() == null
                ? "" : estructuraReporteEntity.getTextoUno());
        dto.setNombreTipoNombramiento(estructuraReporteEntity
                .getClasificacionReporte().getClasificacionReporte() == null
                        ? "" : estructuraReporteEntity.getClasificacionReporte()
                                .getClasificacionReporte());
        dto.setNombreEmpleado(
                nombramientoEntity.getEmpleado().getNombreCompleto() == null
                        ? ""
                        : nombramientoEntity.getEmpleado().getNombreCompleto());
        dto.setRfc(nombramientoEntity.getEmpleado().getRfc() == null ? ""
                : nombramientoEntity.getEmpleado().getRfc());
        dto.setCurp(nombramientoEntity.getEmpleado().getCurp() == null ? ""
                : nombramientoEntity.getEmpleado().getCurp());
        dto.setEdad(nombramientoEntity.getEdad() == null ? ""
                : nombramientoEntity.getEdad().toString() + " AÑOS");
        dto.setNacionalidad(
                nombramientoEntity.getEmpleado().getNacionalidad() == null ? ""
                        : nombramientoEntity.getEmpleado().getNacionalidad());
        dto.setSexo(nombramientoEntity.getEmpleado().getIdSexo() == null ? ""
                : nombramientoEntity.getEmpleado().getIdSexo());
        dto.setEstadoCivil(generarEstadoCivil(
                nombramientoEntity.getEmpleado().getIdSexo() == null ? ""
                        : nombramientoEntity.getEmpleado().getIdSexo(),
                nombramientoEntity.getEmpleado().getEstadoCivil() == null ? ""
                        : nombramientoEntity.getEmpleado().getEstadoCivil()));
        dto.setDomicilioEmpleado(
                nombramientoEntity.getEmpleado().getDireccionCompleta() == null
                        ? "" : nombramientoEntity.getEmpleado()
                                .getDireccionCompleta());
        dto.setClavePresupuestal(
                nombramientoEntity.getClavePresupuestal() == null ? ""
                        : nombramientoEntity.getClavePresupuestal());
        dto.setFuncion(nombramientoEntity.getFuncion() == null ? ""
                : nombramientoEntity.getFuncion());

        // Cuando sea provisional se le agrega el tipo de nombramiento
        if (nombramientoEntity.getClasificacionNombramiento()
                .getClasificacionNombramiento()
                .equals(EnumClasificacionNombramiento.PROVISIONAL)) {

            String descripcionTipoNombramiento = nombramientoEntity
                    .getTiposNombramientos().getDescripcion() == null ? ""
                            : nombramientoEntity.getTiposNombramientos()
                                    .getDescripcion();

            String descripcionClasificacionNombramiento = nombramientoEntity
                    .getClasificacionNombramiento()
                    .getClasificacionNombramiento() == null
                            ? "()"
                            : "( " + nombramientoEntity
                                    .getClasificacionNombramiento()
                                    .getClasificacionNombramiento() + " )";

            String tipoNombramiento = descripcionTipoNombramiento
                    + descripcionClasificacionNombramiento;

            dto.setTipoNombramiento(tipoNombramiento);

        } else {
            dto.setTipoNombramiento(
                    nombramientoEntity.getClasificacionNombramiento()
                            .getClasificacionNombramiento() == null
                                    ? ""
                                    : nombramientoEntity
                                            .getClasificacionNombramiento()
                                            .getClasificacionNombramiento());
        }

        dto.setJornadaTrabajo(
                nombramientoEntity.getTipoJornada().getJornada() == null ? ""
                        : nombramientoEntity.getTipoJornada().getJornada());
        dto.setHorarioTrabajo(
                estructuraReporteEntity.getHorarioTrabajo() == null ? ""
                        : estructuraReporteEntity.getHorarioTrabajo());
        dto.setSueldo(nombramientoEntity.getSueldo() == null ? new BigDecimal(0)
                : nombramientoEntity.getSueldo());
        dto.setLugarAdscripcion(nombramientoEntity.getAdscripcion() == null ? ""
                : nombramientoEntity.getAdscripcion());
        dto.setVigenciaFechaIngresoEmpleado(
                nombramientoEntity.getEmpleado().getFechaIngreso());
        dto.setSustituye(estructuraReporteEntity.getSustituye() == null ? ""
                : estructuraReporteEntity.getSustituye());

        dto.setTextoPosicionDos(estructuraReporteEntity.getTextoDos() == null
                ? "" : estructuraReporteEntity.getTextoDos());
        dto.setNombreSecretario(
                estructuraReporteEntity.getNombreSecretario() == null ? ""
                        : estructuraReporteEntity.getNombreSecretario());

        return dto;
    }

    protected NombramientoDetalleDTO obtenerNombramientoReporteFormalizaFaseIIPorId(
            Integer idNombramiento, Integer idClasificacion) {

        String contexto = "obtenerNombramientoReporteFormalizaFaseIIPorId: ";

        if (idNombramiento == null) {
            throw new ValidacionException(contexto
                    + "El idenfiticador del nombramiento es requerido.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (idClasificacion == null) {
            throw new ValidacionException(contexto
                    + "El idenfiticador de estructura reporte es requerido.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        NombramientoEntity nombramientoEntity = nombramientoRepository
                .obtenerPorId(idNombramiento);

        if (nombramientoEntity == null) {
            throw new ReglaNegocioException(contexto
                    + "El idenfiticador del nombramiento es requerido.",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        EstructuraReporteEntity estructuraReporteEntity = estructuraReporteRepository
                .obtenerPorIdClasificacion(idClasificacion);

        if (estructuraReporteEntity == null) {
            throw new ReglaNegocioException(contexto
                    + "La estructura del reporte no se encuentra registrado.",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        NombramientoDetalleDTO dto = new NombramientoDetalleDTO();

        dto.setTextoPosicionUno(estructuraReporteEntity.getTextoUno() == null
                ? "" : estructuraReporteEntity.getTextoUno());
        dto.setNombreTipoNombramiento(estructuraReporteEntity
                .getClasificacionReporte().getClasificacionReporte() == null
                        ? "" : estructuraReporteEntity.getClasificacionReporte()
                                .getClasificacionReporte());
        dto.setNombreEmpleado(
                nombramientoEntity.getEmpleado().getNombreCompleto() == null
                        ? ""
                        : nombramientoEntity.getEmpleado().getNombreCompleto());
        dto.setRfc(nombramientoEntity.getEmpleado().getRfc() == null ? ""
                : nombramientoEntity.getEmpleado().getRfc());
        dto.setCurp(nombramientoEntity.getEmpleado().getCurp() == null ? ""
                : nombramientoEntity.getEmpleado().getCurp());
        dto.setEdad(nombramientoEntity.getEdad() == null ? ""
                : nombramientoEntity.getEdad().toString() + " AÑOS");
        dto.setNacionalidad(
                nombramientoEntity.getEmpleado().getNacionalidad() == null ? ""
                        : nombramientoEntity.getEmpleado().getNacionalidad());
        dto.setSexo(nombramientoEntity.getEmpleado().getIdSexo() == null ? ""
                : nombramientoEntity.getEmpleado().getIdSexo());
        dto.setEstadoCivil(generarEstadoCivil(
                nombramientoEntity.getEmpleado().getIdSexo() == null ? ""
                        : nombramientoEntity.getEmpleado().getIdSexo(),
                nombramientoEntity.getEmpleado().getEstadoCivil() == null ? ""
                        : nombramientoEntity.getEmpleado().getEstadoCivil()));
        dto.setDomicilioEmpleado(
                nombramientoEntity.getEmpleado().getDireccionCompleta() == null
                        ? "" : nombramientoEntity.getEmpleado()
                                .getDireccionCompleta());
        dto.setClavePresupuestal(
                nombramientoEntity.getClavePresupuestal() == null ? ""
                        : nombramientoEntity.getClavePresupuestal());
        dto.setFuncion(nombramientoEntity.getFuncion() == null ? ""
                : nombramientoEntity.getFuncion());
        // Por definir
        dto.setRama("");
        dto.setFechaPosicionUno(estructuraReporteEntity.getFechaPosicionUno());

        // Cuando sea provisional se le agrega el tipo de nombramiento
        if (nombramientoEntity.getClasificacionNombramiento()
                .getClasificacionNombramiento()
                .equals(EnumClasificacionNombramiento.PROVISIONAL)) {

            String descripcionTipoNombramiento = nombramientoEntity
                    .getTiposNombramientos().getDescripcion() == null ? ""
                            : nombramientoEntity.getTiposNombramientos()
                                    .getDescripcion();

            String descripcionClasificacionNombramiento = nombramientoEntity
                    .getClasificacionNombramiento()
                    .getClasificacionNombramiento() == null
                            ? "()"
                            : "( " + nombramientoEntity
                                    .getClasificacionNombramiento()
                                    .getClasificacionNombramiento() + " )";

            String tipoNombramiento = descripcionTipoNombramiento
                    + descripcionClasificacionNombramiento;

            dto.setTipoNombramiento(tipoNombramiento);

        } else {
            dto.setTipoNombramiento(
                    nombramientoEntity.getClasificacionNombramiento()
                            .getClasificacionNombramiento() == null
                                    ? ""
                                    : nombramientoEntity
                                            .getClasificacionNombramiento()
                                            .getClasificacionNombramiento());
        }

        dto.setJornadaTrabajo(
                nombramientoEntity.getTipoJornada().getJornada() == null ? ""
                        : nombramientoEntity.getTipoJornada().getJornada());
        // dto.setHorarioTrabajo(
        // estructuraReporteEntity.getHorarioTrabajo() == null ? "" :
        // estructuraReporteEntity.getHorarioTrabajo());
        dto.setSueldo(nombramientoEntity.getSueldo() == null ? new BigDecimal(0)
                : nombramientoEntity.getSueldo());
        dto.setLugarAdscripcion(nombramientoEntity.getAdscripcion() == null ? ""
                : nombramientoEntity.getAdscripcion());
        // dto.setVigenciaFechaIngresoEmpleado(nombramientoEntity.getEmpleado().getFechaIngreso());
        // dto.setSustituye(estructuraReporteEntity.getSustituye() == null ? ""
        // : estructuraReporteEntity.getSustituye());

        dto.setTextoPosicionDos(estructuraReporteEntity.getTextoDos() == null
                ? "" : estructuraReporteEntity.getTextoDos());
        dto.setNombreSecretario(
                estructuraReporteEntity.getNombreSecretario() == null ? ""
                        : estructuraReporteEntity.getNombreSecretario());

        return dto;

    }

    protected NombramientoInterinatoDTO obtenerNombramientoReporteInterinato(
            Integer idNombramiento, Integer idClasificacion) {

        String contexto = "obtenerNombramientoReporteInterinato: ";

        if (idNombramiento == null) {
            throw new ValidacionException(contexto
                    + "El idenfiticador del nombramiento es requerido.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (idClasificacion == null) {
            throw new ValidacionException(contexto
                    + "El idenfiticador de estructura reporte es requerido.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        NombramientoEntity nombramientoEntity = nombramientoRepository
                .obtenerPorId(idNombramiento);

        if (nombramientoEntity == null) {
            throw new ReglaNegocioException(contexto
                    + "El idenfiticador del nombramiento es requerido.",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        EstructuraReporteEntity estructuraReporteEntity = estructuraReporteRepository
                .obtenerPorIdClasificacion(idClasificacion);

        if (estructuraReporteEntity == null) {
            throw new ReglaNegocioException(contexto
                    + "La estructura del reporte no se encuentra registrado.",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        NombramientoInterinatoDTO nombramientoInterinatoDTO = new NombramientoInterinatoDTO();

        nombramientoInterinatoDTO.setPresenteNombre("");
        nombramientoInterinatoDTO.setPresenteClaveUno("");
        nombramientoInterinatoDTO.setPresenteClaveDos("");
        nombramientoInterinatoDTO
                .setTextoPosicionUno(estructuraReporteEntity.getTextoUno());
        nombramientoInterinatoDTO.setFechaNombramiento(
                estructuraReporteEntity.getFechaPosicionUno());
        nombramientoInterinatoDTO
                .setFuncion(nombramientoEntity.getFuncion() == null ? ""
                        : nombramientoEntity.getFuncion());
        nombramientoInterinatoDTO.setClavePresupuestal(
                nombramientoEntity.getClavePresupuestal() == null ? ""
                        : nombramientoEntity.getClavePresupuestal());
        nombramientoInterinatoDTO.setPropietarioPlaza("");
        nombramientoInterinatoDTO.setTipoRecurso("");
        // Cuando sea provisional se le agrega el tipo de nombramiento
        if (nombramientoEntity.getClasificacionNombramiento()
                .getClasificacionNombramiento()
                .equals(EnumClasificacionNombramiento.PROVISIONAL)) {

            String descripcionTipoNombramiento = nombramientoEntity
                    .getTiposNombramientos().getDescripcion() == null ? ""
                            : nombramientoEntity.getTiposNombramientos()
                                    .getDescripcion();

            String descripcionClasificacionNombramiento = nombramientoEntity
                    .getClasificacionNombramiento()
                    .getClasificacionNombramiento() == null
                            ? "()"
                            : "( " + nombramientoEntity
                                    .getClasificacionNombramiento()
                                    .getClasificacionNombramiento() + " )";

            String tipoNombramiento = descripcionTipoNombramiento
                    + descripcionClasificacionNombramiento;

            nombramientoInterinatoDTO.setTipoNombramiento(tipoNombramiento);

        } else {
            nombramientoInterinatoDTO.setTipoNombramiento(
                    nombramientoEntity.getClasificacionNombramiento()
                            .getClasificacionNombramiento() == null
                                    ? ""
                                    : nombramientoEntity
                                            .getClasificacionNombramiento()
                                            .getClasificacionNombramiento());
        }
        nombramientoInterinatoDTO.setVigencia(
                nombramientoEntity.getEmpleado().getFechaIngreso());

        nombramientoInterinatoDTO
                .setPosicionDos(estructuraReporteEntity.getTextoDos());

        return nombramientoInterinatoDTO;
    }

    private String generarEstadoCivil(String sexo, String estadoCivilEmpleado) {
        String estadoCivil = "";
        if (sexo.equals(EnumTipoSexo.FEMENINO)) {
            switch (estadoCivilEmpleado) {
                case EnumEstadoCivil.CASADO:
                    estadoCivil = "CASADA";
                    break;
                case EnumEstadoCivil.SOLTERO:
                    estadoCivil = "SOLTERA";
                    break;
                case EnumEstadoCivil.DIVORCIADO:
                    estadoCivil = "DIVORCIADA";
                    break;
                case EnumEstadoCivil.VIUDO:
                    estadoCivil = "VIUDA";
                    break;
            }
        } else if (sexo.equals(EnumTipoSexo.MASCULINO)) {
            switch (estadoCivilEmpleado) {
                case EnumEstadoCivil.CASADO:
                    estadoCivil = "CASADO";
                    break;
                case EnumEstadoCivil.SOLTERO:
                    estadoCivil = "SOLTERO";
                    break;
                case EnumEstadoCivil.DIVORCIADO:
                    estadoCivil = "DIVORCIADO";
                    break;
                case EnumEstadoCivil.VIUDO:
                    estadoCivil = "VIUDO";
                    break;
            }
        }

        return estadoCivil;
    }

    protected List<InfoNombramientoDTO> obtenerListaInfoNombramiento() {
        return nombramientoRepository.obtenerListaInfoNombramiento();
    }

    protected List<InfoNombramientoDTO> obtenerListaInfoNombramientoPorTipo(
            Integer tipoNombramiento) {

        String contexto = "obtenerListaInfoNombramientoPorTipo: ";

        if (tipoNombramiento == null) {
            throw new ValidacionException(
                    contexto + "El tipo del nombramiento es requerido.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        return nombramientoRepository
                .obtenerListaInfoNombramientoPorTipo(tipoNombramiento);
    }

    protected Integer obtenerInventarioVacantePorIdNombramiento(
            Integer idNombramiento) {

        String contexto = "obtenerInventarioVacantePorIdNombramiento: ";

        if (idNombramiento == null) {
            throw new ValidacionException(contexto
                    + "El identificador del nombramiento es requerido.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        Integer idEmpleado = nombramientoRepository
                .obtenerIdEmpleadoPorIdNombramiento(idNombramiento);

        if (idEmpleado == null) {
            throw new ReglaNegocioException(
                    contexto + "El identificador del empleado no existe.",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        Integer idInventarioVacante = inventarioVacanteRepository
                .obtenerIdInventarioPorIdEmpleado(idEmpleado);

        if (idInventarioVacante == null) {
            throw new ReglaNegocioException(contexto
                    + "El identificador del inventario vacante no existe.",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        return idInventarioVacante;
    }

    protected InfoLugarAdscripcionNombramientoDTO obtenerInfoLugarAdscripcion(
            Integer adscripcion, Integer areaAdscripcion,
            Integer lugarAdscripcion) {

        // String contexto = "obtenerInfoLugarAdscripcion: ";

        // if (adscripcion == null) {
        // throw new ValidacionException(contexto + "El identificador de
        // adscripción es requerido.",
        // ValidacionCodigoError.VALOR_REQUERIDO);
        // }
        //
        // if (areaAdscripcion == null) {
        // throw new ValidacionException(contexto + "El identificador de area
        // adscripción es requerido.",
        // ValidacionCodigoError.VALOR_REQUERIDO);
        // }
        //
        // if (lugarAdscripcion == null) {
        // throw new ValidacionException(contexto + "El identificador del lugar
        // adscripción es requerido.",
        // ValidacionCodigoError.VALOR_REQUERIDO);
        // }

        String descripcionAdscripcion = adscripcionRepository
                .obtenerDescripcionAdscripcionPorId(adscripcion);

        // if (descripcionAdscripcion == null) {
        // throw new ReglaNegocioException(contexto + "La descripción del
        // adscripción no existe.",
        // ReglaNegocioCodigoError.SIN_REGISTRO);
        // }

        String descripcionAreaAdscripcion = areaAdscripcionRepository
                .obtenerDescripcionAreaAdscripcionPorId(areaAdscripcion);

        // if (descripcionAreaAdscripcion == null) {
        // throw new ReglaNegocioException(contexto + "La descripción del area
        // adscripción no existe.",
        // ReglaNegocioCodigoError.SIN_REGISTRO);
        // }

        String descripcionLugarAdscripcion = lugarAdscripcionRepository
                .obtenerDescripcionLugarAdscripcionPorId(lugarAdscripcion);

        // if (descripcionLugarAdscripcion == null) {
        // throw new ReglaNegocioException(contexto + "La descripción del lugar
        // adscripción no existe.",
        // ReglaNegocioCodigoError.SIN_REGISTRO);
        // }

        InfoLugarAdscripcionNombramientoDTO dto = new InfoLugarAdscripcionNombramientoDTO();

        dto.setAdscripcion(descripcionAdscripcion);
        dto.setLugarAdscripcion(descripcionLugarAdscripcion);
        dto.setAreaAdscripcion(descripcionAreaAdscripcion);

        return dto;
    }

}
