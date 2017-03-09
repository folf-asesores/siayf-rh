/*
 * DepenedienteEconomicoService.java
 * Creado el Sep 29, 2016 12:39:52 PM
 * 
 */
package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.persistencia.DependienteEconomicoEntity;
import mx.gob.saludtlax.rh.persistencia.DependienteEconomicoRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class DepenedienteEconomicoService {

	@Inject
	private DependienteEconomicoRepository dependienteEconomicoRepository;
	@Inject
	private EmpleadoRepository empleadoRepository;
	private static final Logger LOGGER = Logger.getLogger(DepenedienteEconomicoService.class.getName());

	@Deprecated
	protected void crearDependienteEconomicoEmpleado(DependienteEconomicoDTO dto) {
		EmpleadoEntity empleado = validarEmpleado(dto.getIdEmpleado());
		String contexto = "Registro Dependiente Economico: ";

		int numeroDependientes = dependienteEconomicoRepository
				.obtenerNumeroDependientesRegistradosParentesco(dto.getIdEmpleado(), dto.getParentesco());

		switch (dto.getParentesco()) {
		case "CONYUGE":
			if (empleado.getNumeroConyuges() == numeroDependientes) {

				throw new BusinessException(
						contexto + "Ya ha registrado a los " + empleado.getNumeroConyuges() + " conyuges.");
			}
			break;
		case "PADRES":
			if (empleado.getNumeroPadres() == numeroDependientes) {
				throw new BusinessException(
						contexto + "Ya ha registrado a los " + empleado.getNumeroPadres() + " padres.");
			}
			break;
		case "HIJOS":
			if (empleado.getNumeroHijos() == numeroDependientes) {
				throw new BusinessException(
						contexto + "Ya ha registrado a los " + empleado.getNumeroPadres() + " padres.");
			}
			break;

		}

		if (dependienteEconomicoRepository.existeDependientePorCurp(dto.getCurp())) {
			throw new BusinessException(contexto
					+ "Existe un dependiente economico registrado con la curp, por favor verifiquela o ingrese una nueva.");
		}

		DependienteEconomicoEntity entity = new DependienteEconomicoEntity();

		entity.setNombre(dto.getNombre());
		entity.setApellidoMaterno(dto.getApellidoMaterno());
		entity.setApellidoPaterno(dto.getApellidoPaterno());
		entity.setCurp(dto.getCurp());
		entity.setFechaNacimiento(dto.getFechaNacimiento());
		entity.setIdEmpleado(dto.getIdEmpleado());
		entity.setIdParentesco(dto.getParentesco());
		entity.setOtroParentesco(dto.getOtroParentesco());
		entity.setSexo(dto.getSexo());
		String nombreCompleto = entity.generarNombreCompleto();

		entity.setNombreCompleto(nombreCompleto);

		if (dependienteEconomicoRepository.existeDependientePorNombre(entity.getNombreCompleto())) {
			throw new BusinessException(contexto
					+ "Existe un dependiente economico registrado con ese nombre por favor verifique el nombre o ingrese uno nuevo.");
		}

		if (!ValidacionUtil.esCadenaVacia(dto.getCurp())) {
			if (dependienteEconomicoRepository.existeDependientePorCurp(dto.getCurp().trim())) {
				throw new BusinessException(
						"Existe un dependiente economico registrado con esa curp por favor verifique la curp o ingrese una nueva.");
			}
		}

		dependienteEconomicoRepository.crear(entity);
	}

	protected void crearDependienteEconomico(DependienteEconomicoDTO dependienteEconomicoDTO) {
		String contexto = "Registro Dependiente: ";
		EmpleadoEntity empleado = empleadoRepository.obtenerPorId(dependienteEconomicoDTO.getIdEmpleado());

		if (empleado == null) {
			throw new ReglaNegocioException(
					contexto + "El empleado con id " + dependienteEconomicoDTO.getIdEmpleado()
							+ " no está registrado en el sistema, verificar con soporte técnico.",
					ReglaNegocioCodigoError.EMPLEADO_NO_ENCONTRADO);

		}

		if (existeDependientePorCurp(dependienteEconomicoDTO.getCurp())) {
			throw new ReglaNegocioException(
					contexto + "Ya existe un dependiente económico registrado con la misma CURP, por favor verifiquela o ingrese una nueva.",
					ReglaNegocioCodigoError.CURP_DUPLICADA);
		}

		DependienteEconomicoEntity dependiente = convertirDtoAEntidad(dependienteEconomicoDTO, null);

		int numeroDependientes = dependienteEconomicoRepository.obtenerNumeroDependientesRegistradosParentesco(
				dependienteEconomicoDTO.getIdEmpleado(), dependienteEconomicoDTO.getParentesco()) + 1;

		if ("CONYUGE".equals(dependiente.getIdParentesco()) && numeroDependientes > 1) {
			throw new ReglaNegocioException(contexto + "Un empleado sólo puede tener un conyuge.",
					ReglaNegocioCodigoError.DEPENDIENTE_MAXIMO);
		}

		if ("PADRES".equals(dependiente.getIdParentesco()) && numeroDependientes > 2) {
			throw new ReglaNegocioException(contexto + "Un empleado sólo puede tener un conyuge.",
					ReglaNegocioCodigoError.DEPENDIENTE_MAXIMO);
		}

		dependienteEconomicoRepository.crear(dependiente);

		if (!empleado.getTienePersonasDependientes()) {
			empleado.setTienePersonasDependientes(true);
		}

		switch (dependienteEconomicoDTO.getParentesco()) {
		case "CONYUGE":
			empleado.setNumeroConyuges(numeroDependientes);
			break;
		case "PADRES":
			empleado.setNumeroPadres(numeroDependientes);
			break;
		case "HIJOS":
			empleado.setNumeroHijos(numeroDependientes);
			break;
		}

		empleadoRepository.actualizar(empleado);
	}

	protected boolean existeDependientePorCurp(String curp) {
		return dependienteEconomicoRepository.existeDependientePorCurp(curp);
	}

	protected void actualizarDependienteEconomico(DependienteEconomicoDTO dto) {
		String contexto = "Actualizar Dependiente Economico: ";

		LOGGER.infov("Service: Actualizar dependiente: {0}", dto);

		if (dependienteEconomicoRepository.existeDependientePorCurp(dto.getCurp(), dto.getIdDependienteEconomico())) {
			throw new ReglaNegocioException(
					contexto + "Ya existe un dependiente económico registrado con la misma CURP, por favor verifiquela o ingrese una nueva.",
					ReglaNegocioCodigoError.CURP_DUPLICADA);
		}

		DependienteEconomicoEntity dependiente = convertirDtoAEntidad(dto);

		if (dependienteEconomicoRepository.existeDependientePorNombre(dependiente.getNombreCompleto(),
				dto.getIdDependienteEconomico())) {
			throw new ReglaNegocioException(
					contexto + "Ya existe un dependiente económico registrado con ese nombre, por favor verifique el nombre o ingrese uno nuevo.",
					ReglaNegocioCodigoError.DEPENDIENTE_REGISTRADO);
		}

		EmpleadoEntity empleado = validarEmpleado(dto.getIdEmpleado());

		int numeroDependientes = dependienteEconomicoRepository
				.obtenerNumeroDependientesRegistradosParentesco(dto.getIdEmpleado(), dto.getParentesco()) + 1;

		if ("CONYUGE".equals(dependiente.getIdParentesco()) && numeroDependientes > 1) {
			throw new ReglaNegocioException(contexto + "Un empleado sólo puede tener un conyuge.",
					ReglaNegocioCodigoError.DEPENDIENTE_MAXIMO);
		}

		if ("PADRES".equals(dependiente.getIdParentesco()) && numeroDependientes > 2) {
			throw new ReglaNegocioException(contexto + "Un empleado sólo puede tener un conyuge.",
					ReglaNegocioCodigoError.DEPENDIENTE_MAXIMO);
		}

		dependienteEconomicoRepository.actualizar(dependiente);

		if (!empleado.getTienePersonasDependientes()) {
			empleado.setTienePersonasDependientes(true);
		}

		switch (dto.getParentesco()) {
		case "CONYUGE":
			empleado.setNumeroConyuges(numeroDependientes);
			break;
		case "PADRES":
			empleado.setNumeroPadres(numeroDependientes);
			break;
		case "HIJOS":
			empleado.setNumeroHijos(numeroDependientes);
			break;
		}

		empleadoRepository.actualizar(empleado);
	}

	protected DependienteEconomicoDTO obtenerDependienteEconimicoPorId(Integer idDependienteEconomico) {
		DependienteEconomicoEntity dependienteEconomicoEntity = dependienteEconomicoRepository
				.obtenerPorId(idDependienteEconomico);

		if (dependienteEconomicoEntity == null) {
			return null;
		}

		return convertirEntidadADto(dependienteEconomicoEntity);
	}

	protected DependienteEconomicoDTO obtenerDependienteEconimicoPorCurp(String curp) {
		DependienteEconomicoEntity dependienteEconomicoEntity = dependienteEconomicoRepository
				.obtenerDependienteEconimicoPorCurp(curp);

		if (dependienteEconomicoEntity == null) {
			return null;
		}

		return convertirEntidadADto(dependienteEconomicoEntity);
	}

	protected void eliminarDependienteEconomico(Integer idDependiente) {
		DependienteEconomicoEntity dependienteEconomicoEntity = dependienteEconomicoRepository
				.obtenerPorId(idDependiente);
		dependienteEconomicoRepository.eliminar(dependienteEconomicoEntity);

	}

	protected List<InfoDependienteEconomicoDTO> consultarDependientesEmpleado(Integer idEmpleado) {
		List<InfoDependienteEconomicoDTO> dependientes = new ArrayList<>();

		List<DependienteEconomicoEntity> consulta = dependienteEconomicoRepository
				.consultarDependientesEmpleado(idEmpleado);
		if (!consulta.isEmpty()) {
			for (DependienteEconomicoEntity entity : consulta) {
				InfoDependienteEconomicoDTO dto = new InfoDependienteEconomicoDTO();
				dto.setCurp(entity.getCurp());
				dto.setFechaNacimiento(entity.getFechaNacimiento());
				dto.setNombreCompleto(entity.getNombreCompleto());
				dto.setOtroParentesco(entity.getOtroParentesco());
				dto.setSexo(entity.getSexo());
				dto.setParentesco(entity.getIdParentesco());
				dto.setOtroParentesco(entity.getOtroParentesco());
				dto.setIdDependiente(entity.getIdDependiente());
				dependientes.add(dto);
			}
		}

		return dependientes;
	}

	protected List<InfoDependienteEconomicoDTO> consultarDependientesEconomicoPadres(Integer idEmpleado) {

		List<InfoDependienteEconomicoDTO> dependientes = new ArrayList<>();

		List<DependienteEconomicoEntity> consulta = dependienteEconomicoRepository
				.consultarDependientesEmpleado(idEmpleado);
		if (!consulta.isEmpty()) {
			for (DependienteEconomicoEntity entity : consulta) {
				InfoDependienteEconomicoDTO dto = new InfoDependienteEconomicoDTO();
				if (entity.getIdParentesco().equals("PADRES") || entity.getIdParentesco().equals("CONYUGE")) {

					dto.setCurp(entity.getCurp());
					dto.setFechaNacimiento(entity.getFechaNacimiento());
					dto.setNombreCompleto(entity.getNombreCompleto());
					dto.setOtroParentesco(entity.getOtroParentesco());
					dto.setSexo(entity.getSexo());
					dto.setParentesco(entity.getIdParentesco());
					dto.setOtroParentesco(entity.getOtroParentesco());
					dto.setIdDependiente(entity.getIdDependiente());
					dependientes.add(dto);
				}

			}
		}

		return dependientes;
	}

	private EmpleadoEntity validarEmpleado(Integer idEmpleado) {
		String contexto = "Actualizacion datos: ";
		EmpleadoEntity empleado = empleadoRepository.obtenerPorId(idEmpleado);
		if (empleado == null) {
			throw new ReglaNegocioException(
					contexto + "El empleado con identificador " + idEmpleado + " no esta registrado en el sistema",
					ReglaNegocioCodigoError.EMPLEADO_NO_ENCONTRADO);
		}

		if (empleado.getIdEstatus().equals("INACTIVO")) {
			throw new ReglaNegocioException(contexto + " El empleado no esta activo.",
					ReglaNegocioCodigoError.EMPLEADO_INACTIVO);
		}

		return empleado;
	}

	private DependienteEconomicoEntity convertirDtoAEntidad(DependienteEconomicoDTO dto) {
		return convertirDtoAEntidad(dto, null);
	}

	private DependienteEconomicoEntity convertirDtoAEntidad(DependienteEconomicoDTO dto,
			DependienteEconomicoEntity entidad) {

		if (entidad == null) {
			entidad = new DependienteEconomicoEntity();
			entidad.setIdDependiente(dto.getIdDependienteEconomico());
		}

		entidad.setNombre(dto.getNombre());
		entidad.setApellidoPaterno(dto.getApellidoPaterno() == null ? "" : dto.getApellidoPaterno());
		entidad.setApellidoMaterno(dto.getApellidoMaterno() == null ? "" : dto.getApellidoMaterno());
		entidad.setCurp(dto.getCurp());
		entidad.setFechaNacimiento(dto.getFechaNacimiento());
		entidad.setIdEmpleado(dto.getIdEmpleado());
		entidad.setIdParentesco(dto.getParentesco());
		entidad.setOtroParentesco(dto.getOtroParentesco());
		entidad.setSexo(dto.getSexo());
		entidad.setNombreCompleto(entidad.generarNombreCompleto());

		return entidad;
	}

	private DependienteEconomicoDTO convertirEntidadADto(DependienteEconomicoEntity entidad) {
		DependienteEconomicoDTO dto = new DependienteEconomicoDTO();

		dto.setIdDependienteEconomico(entidad.getIdDependiente());
		dto.setNombre(entidad.getNombre());
		dto.setApellidoPaterno(entidad.getApellidoPaterno());
		dto.setApellidoMaterno(entidad.getApellidoMaterno());
		dto.setFechaNacimiento(entidad.getFechaNacimiento());
		dto.setCurp(entidad.getCurp());
		dto.setSexo(entidad.getSexo());
		dto.setIdEmpleado(entidad.getIdEmpleado());
		dto.setParentesco(entidad.getIdParentesco());
		dto.setOtroParentesco(entidad.getOtroParentesco());

		return dto;
	}

}
