/**
 * 
 */
package mx.gob.saludtlax.rh.experiencialaboral;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.administracion.BitacoraModificacionService;
import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.ExperienciaLaboralEntity;
import mx.gob.saludtlax.rh.persistencia.ExperienciaLaboralRepository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 * @since 09/06/2016 13:16:29
 * 
 */
public class ExperienciaLaboralService {
	@Inject
	private EmpleadoRepository empleadoRepository;
	@Inject
	private ExperienciaLaboralRepository experienciaLaboralRepository;
	@Inject
	private BitacoraModificacionService bitacoraModificacionService;

	protected List<ExperienciaLaboralDTO> consultaExperienciaLaboralEmpleado(Integer idEmpleado) {
		List<ExperienciaLaboralDTO> experienciasLaboralesDTO = new ArrayList<>();

		List<ExperienciaLaboralEntity> experienciasLaboralesEntities = experienciaLaboralRepository
				.consultarExperienciasEmpleado(idEmpleado);
		if (!experienciasLaboralesEntities.isEmpty()) {
			for (ExperienciaLaboralEntity entity : experienciasLaboralesEntities) {
				ExperienciaLaboralDTO dto = new ExperienciaLaboralDTO();

				dto.setIdExperienciaLaboral(entity.getIdExperienciaLaboral());

				dto.setNombreEmpresa(entity.getNombreEmpresa());
				dto.setPuestoAspirante(entity.getPuesto());
				dto.setFechaInicial(entity.getFechaInicial());
				dto.setFechaFinal(entity.getFechaFinal());
				dto.setDireccionEmpresa(entity.getNombreEmpresa());
				dto.setCorreoEmpresa(entity.getCorreoContacto());
				dto.setMotivoSeparacion(entity.getMotivoSeparacion());
				dto.setSueldoMensual(entity.getSueldoMensual());
				dto.setComentarios(entity.getComentarios());
				dto.setTelefonoEmpresa(entity.getTelefono());
				dto.setNombreJefe(entity.getNombreJefe());
				dto.setPuestoJefe(entity.getPuestoJefe());
				dto.setSolicitarInformacion(entity.getSolicitarInformacion());
				dto.setRazonNoSolicitar(entity.getRazonNoSolicitar());

				experienciasLaboralesDTO.add(dto);

			}
		} else {
			experienciasLaboralesDTO = new ArrayList<ExperienciaLaboralDTO>();
		}

		return experienciasLaboralesDTO;
	}

	protected void crearExperienciaLaboralEmpleado(ExperienciaLaboralDTO experienciaLaboral, Integer idEmpleado) {
		String contexto = "Registro de la experiencia laboral: ";

		if (experienciaLaboral == null) {
			throw new BusinessException(contexto + "Es necesario los datos importantes de la experiencia laboral");
		}

		EmpleadoEntity empleado = validarEmpleado(idEmpleado);

		ExperienciaLaboralEntity registroExperienciaLaboral = new ExperienciaLaboralEntity();

		registroExperienciaLaboral.setNombreEmpresa(experienciaLaboral.getNombreEmpresa());
		registroExperienciaLaboral.setTelefono(experienciaLaboral.getTelefonoEmpresa());
		registroExperienciaLaboral.setCorreoContacto(experienciaLaboral.getCorreoEmpresa());
		registroExperienciaLaboral.setPuesto(experienciaLaboral.getPuestoAspirante());
		registroExperienciaLaboral.setFechaInicial(experienciaLaboral.getFechaInicial());
		registroExperienciaLaboral.setFechaFinal(experienciaLaboral.getFechaFinal());
		registroExperienciaLaboral.setDireccionEmpresa(experienciaLaboral.getDireccionEmpresa());
		registroExperienciaLaboral.setMotivoSeparacion(experienciaLaboral.getMotivoSeparacion());
		registroExperienciaLaboral.setSueldoMensual(experienciaLaboral.getSueldoMensual());

		registroExperienciaLaboral.setIdEmpleado(empleado.getIdEmpleado());
		registroExperienciaLaboral.setNombreJefe(experienciaLaboral.getNombreJefe());
		registroExperienciaLaboral.setPuestoJefe(experienciaLaboral.getPuestoJefe());
		registroExperienciaLaboral.setSolicitarInformacion(experienciaLaboral.getSolicitarInformacion());
		registroExperienciaLaboral.setRazonNoSolicitar(experienciaLaboral.getRazonNoSolicitar());
		registroExperienciaLaboral.setComentarios(experienciaLaboral.getComentarios());

		experienciaLaboralRepository.crear(registroExperienciaLaboral);

	}

	protected void actualizarExperienciaLaboralEmpleado(ExperienciaLaboralDTO experienciaLaboral, Integer idEmpleado) {
		String contexto = "Actualización de la experiencia laboral: ";

		if (experienciaLaboral == null) {
			throw new ValidacionException(contexto + "Es necesario los datos importantes de la experiencia laboral",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		// Valida datos los para la bitacora
		if (ValidacionUtil.esCadenaVacia(experienciaLaboral.getComentarioMovimiento())
				| ValidacionUtil.esNumeroPositivo(experienciaLaboral.getIdUsuarioEnSesion())) {

			bitacoraModificacionService.registrarBitacoraModificacionExperiencia(experienciaLaboral, idEmpleado);

		}
		
		EmpleadoEntity empleado = validarEmpleado(idEmpleado);

		ExperienciaLaboralEntity actualizacionExperienciaLaboral = experienciaLaboralRepository
				.obtenerPorId(experienciaLaboral.getIdExperienciaLaboral());

		actualizacionExperienciaLaboral.setNombreEmpresa(experienciaLaboral.getNombreEmpresa());
		actualizacionExperienciaLaboral.setTelefono(experienciaLaboral.getTelefonoEmpresa());
		actualizacionExperienciaLaboral.setCorreoContacto(experienciaLaboral.getCorreoEmpresa());
		actualizacionExperienciaLaboral.setPuesto(experienciaLaboral.getPuestoAspirante());
		actualizacionExperienciaLaboral.setFechaInicial(experienciaLaboral.getFechaInicial());
		actualizacionExperienciaLaboral.setFechaFinal(experienciaLaboral.getFechaFinal());
		actualizacionExperienciaLaboral.setDireccionEmpresa(experienciaLaboral.getDireccionEmpresa());
		actualizacionExperienciaLaboral.setMotivoSeparacion(experienciaLaboral.getMotivoSeparacion());
		actualizacionExperienciaLaboral.setSueldoMensual(experienciaLaboral.getSueldoMensual());
		actualizacionExperienciaLaboral.setIdEmpleado(empleado.getIdEmpleado());
		actualizacionExperienciaLaboral.setNombreJefe(experienciaLaboral.getNombreJefe());
		actualizacionExperienciaLaboral.setPuestoJefe(experienciaLaboral.getPuestoJefe());
		actualizacionExperienciaLaboral.setSolicitarInformacion(experienciaLaboral.getSolicitarInformacion());
		actualizacionExperienciaLaboral.setRazonNoSolicitar(experienciaLaboral.getRazonNoSolicitar());
		actualizacionExperienciaLaboral.setComentarios(experienciaLaboral.getComentarios());

		experienciaLaboralRepository.actualizar(actualizacionExperienciaLaboral);
	}

	private EmpleadoEntity validarEmpleado(Integer idEmpleado) {
		String contexto = "Actualizacion datos: ";
		EmpleadoEntity empleado = empleadoRepository.obtenerPorId(idEmpleado);
		if (empleado == null) {
			throw new BusinessException(
					contexto + "El empleado con identificador " + idEmpleado + " no esta registrado en el sistema");
		}

		if (empleado.getIdEstatus().equals("INACTIVO")) {
			throw new BusinessException(contexto + " El empleado no esta activo.");
		}

		return empleado;
	}

}
