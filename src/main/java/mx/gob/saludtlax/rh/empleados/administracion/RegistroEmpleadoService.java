
package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.plazas.EnumEstatusPlaza;
import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.persistencia.AsentamientoEntity;
import mx.gob.saludtlax.rh.persistencia.AsentamientoRepository;
import mx.gob.saludtlax.rh.persistencia.AspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.DepartamentoEntity;
import mx.gob.saludtlax.rh.persistencia.DepartamentoRepository;
import mx.gob.saludtlax.rh.persistencia.DependienteEconomicoRepository;
import mx.gob.saludtlax.rh.persistencia.DireccionEntity;
import mx.gob.saludtlax.rh.persistencia.DireccionRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.ExperienciaLaboralEntity;
import mx.gob.saludtlax.rh.persistencia.ExperienciaLaboralRepository;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoEntity;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoRepository;
import mx.gob.saludtlax.rh.persistencia.MunicipioRepository;
import mx.gob.saludtlax.rh.persistencia.MunicipiosEntity;
import mx.gob.saludtlax.rh.persistencia.PlazaEntity;
import mx.gob.saludtlax.rh.persistencia.PlazaRepository;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 13/04/2016-15:03:14
 */
public class RegistroEmpleadoService {

    @Inject
    private AspiranteRepository aspiranteRepository;
    @Inject
    private PuestoGeneralRepository cargoRepository;
    @Inject
    private DepartamentoRepository departamentoRepository;
    @Inject
    private DependienteEconomicoRepository dependienteEconomicoRepository;
    @Inject
    private DireccionRepository direccionEmpleadoRepository;
    @Inject
    private EmpleadoRepository empleadoRepository;
    @Inject
    private ExperienciaLaboralRepository experienciaLaboralRepository;
    @Inject
    private HistorialAcademicoRepository historialAcademicoRepository;
    @Inject
    private MunicipioRepository municipioRepository;
    @Inject
    private PlazaRepository plazaRepository;
    @Inject
    private AsentamientoRepository poblacionRepository;

    /**
     * Aprueba los datos generales como aspirante para ser un empleado o permite
     * la correción de los mismos.
     *
     * @param datosGeneralesDTO
     *            datos generales del aspirante a empleado.
     * @param idAspirante
     *            identificador del aspirante.
     * @return int numero de fase del registro del empleado.
     */
    protected RegistroGeneralesDTO aprobarDatosGenerales(DatosGeneralesDTO datosGeneralesDTO, Integer idAspirante) {

        String contexto = "Aprobacion Datos Generales: ";
        AspiranteEntity aspirante = aspiranteRepository.obtenerPorId(idAspirante);
        if (aspirante == null) {
            throw new BusinessException(contexto + "El aspirante con id " + idAspirante + " no está registrado en el sistema, verificar con soporte técnico.");

        }

        EmpleadoEntity empleadoEntity = new EmpleadoEntity();
        empleadoEntity.setFechaAlta(FechaUtil.fechaActual());
        empleadoEntity.setIdSexo(datosGeneralesDTO.getIdSexo());
        empleadoEntity.setNombre(datosGeneralesDTO.getNombre());
        empleadoEntity.setApellidoPaterno(datosGeneralesDTO.getApellidoPaterno());
        empleadoEntity.setApellidoMaterno(datosGeneralesDTO.getApellidoMaterno());
        empleadoEntity.setRfc(datosGeneralesDTO.getRfc());
        empleadoEntity.setCurp(datosGeneralesDTO.getCurp());
        empleadoEntity.setEstadoCivil(datosGeneralesDTO.getIdEstadoCivil());
        empleadoEntity.setLugarNacimiento(datosGeneralesDTO.getLugarNacimiento());
        empleadoEntity.setTelefono(datosGeneralesDTO.getTelefonos());
        empleadoEntity.setCorreoElectronico(datosGeneralesDTO.getCorreo());
        empleadoEntity.setTipoSangre(datosGeneralesDTO.getIdTipoSangre());
        empleadoEntity.setPeso(datosGeneralesDTO.getPeso());
        empleadoEntity.setEstatura(datosGeneralesDTO.getEstatura());
        empleadoEntity.setTienePersonasDependientes(datosGeneralesDTO.getTienePersonasDependientes());
        empleadoEntity.setFechaNacimiento(datosGeneralesDTO.getFechaNacimiento());
        empleadoEntity.setNacionalidad(datosGeneralesDTO.getNacionalidad());

        String nombreCompleto = empleadoEntity.getNombre() + " ";
        if (!ValidacionUtil.esCadenaVacia(empleadoEntity.getApellidoPaterno())) {
            nombreCompleto = nombreCompleto + empleadoEntity.getApellidoPaterno() + " ";
        }
        nombreCompleto = nombreCompleto + empleadoEntity.getApellidoMaterno();
        empleadoEntity.setNombreCompleto(nombreCompleto);
        empleadoEntity.setNumeroPadres(0);
        empleadoEntity.setNumeroHijos(0);
        empleadoEntity.setNumeroConyuges(0);
        empleadoEntity.setNumeroOtros(0);
        empleadoEntity.setOtroParentesco("");

        if (aspirante.getTienePersonasDependientes()) {
            for (String parentesco : datosGeneralesDTO.getParentescos()) {
                if (parentesco.equals("PADRE")) {
                    empleadoEntity.setNumeroPadres(datosGeneralesDTO.getNumeroPadres());
                }
                if (parentesco.equals("HIJOS")) {
                    empleadoEntity.setNumeroHijos(datosGeneralesDTO.getNumeroHijos());
                }
                if (parentesco.equals("CONYUGE")) {
                    empleadoEntity.setNumeroConyuges(datosGeneralesDTO.getNumeroConyuges());
                }
                if (parentesco.equals("OTRO")) {
                    empleadoEntity.setNumeroOtros(datosGeneralesDTO.getNumeroOtros());
                    empleadoEntity.setOtroParentesco(datosGeneralesDTO.getOtroParentesco());
                }
            }
        }

        // empleadoEntity.setFase(EnumFasesRegistroEmpleado.GENERALES);
        // empleadoRepository.crear(empleadoEntity);

        aspirante.setEnProceso(true);
        aspirante.setEmpleado(empleadoEntity);
        aspiranteRepository.crearActualizarAspirante(aspirante);

        RegistroGeneralesDTO dto = new RegistroGeneralesDTO();
        dto.setIdEmpleado(empleadoEntity.getIdEmpleado());
        dto.setFase(EnumFasesRegistroEmpleado.GENERALES);
        return dto;
    }

    protected int aprobarDireccion(DomicilioDTO domicilioDTO, Integer idAspirante, Integer idEmpleado) {

        String contexto = "Aprobacion Direccion: ";
        EmpleadoEntity empleadoEntity = empleadoRepository.obtenerPorId(idEmpleado);
        if (empleadoEntity == null) {
            throw new BusinessException(contexto + "El aspirante con id " + idAspirante + " no está registrado en el sistema, verificar con soporte técnico.");

        }

        DireccionEntity direccionAspiranteEntity = direccionEmpleadoRepository.consultarDireccionAspirantePorId(idAspirante);

        if (direccionAspiranteEntity == null) {
            throw new BusinessException(contexto + "No se ha encontrado registro de direccion con el identificador " + idAspirante);
        }

        String lccDomicilioEmpleado = domicilioDTO.toString();
        String lccDomicilioAspirante = direccionAspiranteEntity.lccDomicilio();

        if (!lccDomicilioAspirante.equals(lccDomicilioEmpleado)) {
            AsentamientoEntity poblacion = poblacionRepository.obtenerPorId(domicilioDTO.getIdAsentamiento());

            MunicipiosEntity municipio = municipioRepository.obtenerPorId(domicilioDTO.getIdMunicipio());

            direccionAspiranteEntity.setCalle(domicilioDTO.getCalle());
            direccionAspiranteEntity.setCodigoPostal(domicilioDTO.getCodigoPostal());
            direccionAspiranteEntity.setMunicipio(municipio);
            direccionAspiranteEntity.setNumeroExterior(domicilioDTO.getNumeroExterior());
            direccionAspiranteEntity.setNumeroInterior(domicilioDTO.getNumeroInterior());
            direccionAspiranteEntity.setAsentamiento(poblacion);
            direccionAspiranteEntity.setIdEmpleado(idEmpleado);
            direccionEmpleadoRepository.crear(direccionAspiranteEntity);
            String direccionCompleta = direccionAspiranteEntity.direccionCompleta();
            empleadoEntity.setDireccionCompleta(direccionCompleta);

        }

        // empleadoEntity.setFase(EnumFasesRegistroEmpleado.DOMICILIO);
        // empleadoRepository.crear(empleadoEntity);

        return EnumFasesRegistroEmpleado.DOMICILIO;
    }

    /**
     * Aprueba el historial academico, experiencia laboral
     */
    protected int aprobarInformacion(int fase, Integer idAspirante) {
        String contexto = "Aprobacion Historiales: ";
        AspiranteEntity aspirante = aspiranteRepository.obtenerPorId(idAspirante);

        if (aspirante == null) {
            throw new BusinessException(contexto + "El aspirante con id " + idAspirante + " no está registrado en el sistema, verificar con soporte técnico.");

        }

        EmpleadoEntity empleado = empleadoRepository.obtenerPorId(aspirante.getEmpleado().getIdEmpleado());

        // empleado.setFase(fase);
        empleadoRepository.crear(empleado);

        if (fase == EnumFasesRegistroEmpleado.HISTORIAL_ACADEMICO) {
            List<HistorialAcademicoEntity> historiales = historialAcademicoRepository.consultarHistorialAcademicoAspirante(idAspirante);
            for (HistorialAcademicoEntity entity : historiales) {
                entity.setIdEmpleado(empleado.getIdEmpleado());
                historialAcademicoRepository.crear(entity);
            }

        } else if (fase == EnumFasesRegistroEmpleado.EXPERIENCIA_LABORAL) {
            List<ExperienciaLaboralEntity> experiencias = experienciaLaboralRepository.consultarExperienciasLaboralesAspirante(idAspirante);
            for (ExperienciaLaboralEntity entity : experiencias) {
                entity.setIdEmpleado(empleado.getIdEmpleado());
                experienciaLaboralRepository.crear(entity);
            }
        }

        return fase;
    }

    protected int registrarEmpleado(DatosEmpleadoDTO datosEmpleadoDTO, Integer idAspirante) {

        String contexto = "Registro Empleado: ";
        EmpleadoEntity empleado = empleadoRepository.obtenerPorId(idAspirante);
        if (empleado == null) {
            throw new BusinessException(contexto + "El aspirante con id " + idAspirante + " no está registrado en el sistema, verificar con soporte técnico.");

        }

        boolean existeNumeroEmpleado = empleadoRepository.existeEmpleadoNumeroEmpleado(datosEmpleadoDTO.getNumeroEmpleado());
        if (existeNumeroEmpleado) {
            throw new BusinessException(
                    contexto + "El numero de empleado " + datosEmpleadoDTO.getNumeroEmpleado() + " no esta disponible, ingrese un numero diferente.");
        }

        PlazaEntity plazaEmpleado = plazaRepository.obtenerPlazaPorId(datosEmpleadoDTO.getIdPlaza());
        if (plazaEmpleado == null) {
            throw new BusinessException(contexto + "La plaza con identificador " + datosEmpleadoDTO.getIdPlaza() + " no existe, comuniquese con soporte.");

        }

        if (!plazaEmpleado.getEstatus().equals(EnumEstatusPlaza.VACANTE)) {
            throw new BusinessException(contexto + "La plaza seleccionada ya no se encuentra vacante, seleccione otra plaza.");
        }

        PuestoGeneralEntity cargoEntity = cargoRepository.obtenerPorId(datosEmpleadoDTO.getIdCargo());
        // AreaAdscripcionEntity areaEntity =
        // areaRepository.obtenerPorId(idAspirante);
        DepartamentoEntity departamentoEntity = departamentoRepository.departamentoPorId(datosEmpleadoDTO.getIdDepartamento());

        // empleado.setNumeroEmpleado(datosEmpleadoDTO.getNumeroEmpleado());
        // empleado.setCargo(cargoEntity);
        // empleado.setTipoNombramiento(datosEmpleadoDTO.getNombramiento());
        empleado.setFechaIngreso(datosEmpleadoDTO.getFechaIngreso());
        // empleado.setAreaAdscripcion(areaEntity);
        // empleado.setNivel(datosEmpleadoDTO.getNivel());
        // empleado.setCargo(cargoEntity);
        // empleado.setDepartamento(departamentoEntity);
        empleado.setFechaAlta(FechaUtil.fechaActual());
        // empleado.setFase(EnumFasesRegistroEmpleado.INFORMACION_EMPLEADO);
        empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
        empleadoRepository.crear(empleado);

        plazaEmpleado.setEmpleado(empleado);
        plazaEmpleado.setFechaInicio(FechaUtil.fechaActual());
        plazaEmpleado.setEstatus(EnumEstatusPlaza.OCUPADA);
        plazaRepository.actualizarPlaza(plazaEmpleado);

        return EnumFasesRegistroEmpleado.INFORMACION_EMPLEADO;
    }
}
