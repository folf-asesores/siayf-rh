/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.AspiranteDTO;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.DireccionDTO;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumTipoDocumento;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumTipoFiltro;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.InfoAspiranteDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.experiencialaboral.ExperienciaLaboralDTO;
import mx.gob.saludtlax.rh.persistencia.AspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.DireccionEntity;
import mx.gob.saludtlax.rh.persistencia.DireccionRepository;
import mx.gob.saludtlax.rh.persistencia.DocumentacionEntity;
import mx.gob.saludtlax.rh.persistencia.DocumentacionRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.ExperienciaLaboralEntity;
import mx.gob.saludtlax.rh.persistencia.ExperienciaLaboralRepository;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import mx.gob.saludtlax.rh.vacantes.postulacion.InfoCandidatoDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/04/2016-12:15:50
 */
public class BusquedaEmpleadoService {

	@Inject
	private AspiranteRepository aspiranteRepository;
	@Inject
	private EmpleadoRepository empleadoRepository;

	@Inject
	private ExperienciaLaboralRepository experienciaLaboralRepository;
	@Inject
	private DireccionRepository direccionEmpleadoRepository;
	@Inject
	private DocumentacionRepository documentacionRepository;
	@Inject
	private HistorialAcademicoRepository historialAcademicoRepository;

	@Inject
	private InventarioVacanteRepository inventarioPuestosRepository;

	@Inject
	private InventarioVacanteRepository inventarioVacanteRepository;

	/**
	 * Retorna las coincidencias de aspirantes con estatus disponible segun el
	 * criterio ingresado con la informacion basica.
	 * 
	 * @param criterio
	 *            nombre, rfc o curp.
	 */
	public List<InfoAspiranteDTO> aspirantesDisponiblesPorCriterio(String criterio) {
		if (ValidacionUtil.esCadenaVacia(criterio)) {
			throw new ValidacionException(
					"Busqueda aspirantes: El criterio de busqueda es requerido, ingrese un criterio.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		// if (criterio.trim().length() <= 4) {
		// throw new ValidationException(
		// "Busqueda aspirantes: Ingrese un criterio mayor a 4 caracteres.");
		// }
		return aspiranteRepository.aspirantesDisponiblesPorCriterio(criterio);
	}

	/**
	 * Consulta al aspirante por identificador.
	 * 
	 * @param id
	 *            identificador del aspirante
	 */
	public EmpleadoDTO aspirantePorId(Integer id) {
		/*
		 * String contexto = "Consulta: ";
		 * 
		 * AspiranteEntity aspiranteEntity =
		 * aspiranteRepository.obtenerPorId(id); EmpleadoDTO empleadoDTO = null;
		 * if (aspiranteEntity == null) { throw new
		 * ReglaNegocioException(contexto +
		 * " No se ha encontrado registro con el identificador " + id,
		 * ReglaNegocioCodigoError.SIN_REGISTRO); } else {
		 * 
		 * if (!aspiranteEntity.isEnProceso()) { DireccionEntity direccionEntity
		 * = direccionEmpleadoRepository .consultarDireccionAspirantePorId(id);
		 * 
		 * if (direccionEntity == null) { throw new ReglaNegocioException(
		 * contexto +
		 * "No se ha encontrado registro de direccion con el identificador " +
		 * id, ReglaNegocioCodigoError.SIN_REGISTRO); }
		 * 
		 * DomicilioDTO direccionDTO = new DomicilioDTO();
		 * direccionDTO.setCalle(direccionEntity.getCalle());
		 * direccionDTO.setCodigoPostal(direccionEntity.getCodigoPostal());
		 * 
		 * direccionDTO.setIdMunicipio(direccionEntity.getMunicipio()
		 * .getIdMunicipio()); direccionDTO.setIdAsentamiento(direccionEntity
		 * .getAsentamiento().getIdAsentamiento());
		 * direccionDTO.setNumeroExterior(direccionEntity .getNumeroExterior());
		 * direccionDTO.setNumeroInterior(direccionEntity .getNumeroInterior());
		 * 
		 * DatosGeneralesDTO datosGeneralesDTO = new DatosGeneralesDTO();
		 * datosGeneralesDTO.setApellidoMaterno(aspiranteEntity
		 * .getApellidoMaterno());
		 * datosGeneralesDTO.setApellidoPaterno(aspiranteEntity
		 * .getApellidoPaterno()); datosGeneralesDTO.setCorreo(aspiranteEntity
		 * .getCorreoElectronico());
		 * datosGeneralesDTO.setCurp(aspiranteEntity.getCurp());
		 * datosGeneralesDTO.setEstatura(aspiranteEntity.getEstatura());
		 * datosGeneralesDTO.setFechaNacimiento(aspiranteEntity
		 * .getFechaNacimiento());
		 * datosGeneralesDTO.setNombre(aspiranteEntity.getNombre());
		 * datosGeneralesDTO.setPeso(aspiranteEntity.getPeso());
		 * datosGeneralesDTO.setRfc(aspiranteEntity.getRfc());
		 * datosGeneralesDTO.setTelefonos(aspiranteEntity.getTelefono());
		 * datosGeneralesDTO.setIdSexo(aspiranteEntity.getIdSexo());
		 * datosGeneralesDTO.setIdEstadoCivil(aspiranteEntity
		 * .getEstadoCivil()); datosGeneralesDTO.setIdTipoSangre(aspiranteEntity
		 * .getTipoSangre());
		 * datosGeneralesDTO.setTienePersonasDependientes(aspiranteEntity
		 * .getTienePersonasDependientes());
		 * datosGeneralesDTO.setLugarNacimiento(aspiranteEntity
		 * .getLugarNacimiento());
		 * datosGeneralesDTO.setNacionalidad(aspiranteEntity
		 * .getNacionalidad());
		 * 
		 * if (datosGeneralesDTO.getTienePersonasDependientes()) { List<String>
		 * parentescos = new ArrayList<String>(); if
		 * (aspiranteEntity.getNumeroConyuges() > 0) {
		 * datosGeneralesDTO.setNumeroConyuges(aspiranteEntity
		 * .getNumeroConyuges()); parentescos.add("CONYUGE"); } if
		 * (aspiranteEntity.getNumeroHijos() > 0) {
		 * datosGeneralesDTO.setNumeroHijos(aspiranteEntity .getNumeroHijos());
		 * parentescos.add("HIJOS"); } if (aspiranteEntity.getNumeroPadres() >
		 * 0) { datosGeneralesDTO.setNumeroPadres(aspiranteEntity
		 * .getNumeroPadres()); parentescos.add("PADRE"); } if
		 * (aspiranteEntity.getNumeroOtros() > 0) {
		 * datosGeneralesDTO.setNumeroOtros(aspiranteEntity .getNumeroOtros());
		 * parentescos.add("OTRO"); }
		 * 
		 * datosGeneralesDTO.setParentescos(parentescos);
		 * 
		 * }
		 * 
		 * empleadoDTO = new EmpleadoDTO();
		 * empleadoDTO.setDatosGeneralesDTO(datosGeneralesDTO);
		 * empleadoDTO.setDomicilio(direccionDTO);
		 * empleadoDTO.setFaseRegistro(0);
		 * empleadoDTO.setIdAspirante(aspiranteEntity.getIdAspirante());
		 * 
		 * } else {
		 * 
		 * EmpleadoEntity empleadoEntity = empleadoRepository
		 * .obtenerPorId(aspiranteEntity.getEmpleado() .getIdEmpleado()); if
		 * (empleadoEntity == null) { throw new ReglaNegocioException( contexto
		 * + " No se ha encontrado registro con el identificador " + id,
		 * ReglaNegocioCodigoError.SIN_REGISTRO); }
		 * 
		 * if ( == EnumFasesRegistroEmpleado.GENERALES) { DireccionEntity
		 * direccionEmpleadoEntity = direccionEmpleadoRepository
		 * .consultarDireccionEmpleadoPorId(aspiranteEntity
		 * .getEmpleado().getIdEmpleado());
		 * 
		 * if (direccionEmpleadoEntity == null) { throw new
		 * ReglaNegocioException( contexto +
		 * "No se ha encontrado registro de direccion con el identificador " +
		 * id, ReglaNegocioCodigoError.SIN_REGISTRO); }
		 * 
		 * DomicilioDTO direccionDTO = new DomicilioDTO();
		 * direccionDTO.setCalle(direccionEmpleadoEntity.getCalle());
		 * direccionDTO.setCodigoPostal(direccionEmpleadoEntity
		 * .getCodigoPostal());
		 * 
		 * direccionDTO.setIdMunicipio(direccionEmpleadoEntity
		 * .getMunicipio().getIdMunicipio());
		 * direccionDTO.setIdAsentamiento(direccionEmpleadoEntity
		 * .getAsentamiento().getIdAsentamiento());
		 * direccionDTO.setNumeroExterior(direccionEmpleadoEntity
		 * .getNumeroExterior());
		 * direccionDTO.setNumeroInterior(direccionEmpleadoEntity
		 * .getNumeroInterior());
		 * 
		 * empleadoDTO = new EmpleadoDTO();
		 * empleadoDTO.setIdEmpleado(empleadoEntity.getIdEmpleado());
		 * empleadoDTO .setIdAspirante(aspiranteEntity.getIdAspirante());
		 * //empleadoDTO.setFaseRegistro(empleadoEntity.getFase());
		 * empleadoDTO.setDomicilio(direccionDTO); } else {
		 * 
		 * DatosGeneralesDTO datosGeneralesDTO = new DatosGeneralesDTO();
		 * 
		 * datosGeneralesDTO .setTienePersonasDependientes(empleadoEntity
		 * .getTienePersonasDependientes());
		 * 
		 * if (datosGeneralesDTO.getTienePersonasDependientes()) { List<String>
		 * parentescos = new ArrayList<String>(); if
		 * (empleadoEntity.getNumeroConyuges() > 0) {
		 * datosGeneralesDTO.setNumeroConyuges(empleadoEntity
		 * .getNumeroConyuges()); parentescos.add("CONYUGE"); } if
		 * (empleadoEntity.getNumeroHijos() > 0) {
		 * datosGeneralesDTO.setNumeroHijos(empleadoEntity .getNumeroHijos());
		 * parentescos.add("HIJOS"); } if (empleadoEntity.getNumeroPadres() > 0)
		 * { datosGeneralesDTO.setNumeroPadres(empleadoEntity
		 * .getNumeroPadres()); parentescos.add("PADRE"); } if
		 * (empleadoEntity.getNumeroOtros() > 0) {
		 * datosGeneralesDTO.setNumeroOtros(empleadoEntity .getNumeroOtros());
		 * parentescos.add("OTRO"); }
		 * 
		 * datosGeneralesDTO.setParentescos(parentescos);
		 * 
		 * }
		 * 
		 * empleadoDTO = new EmpleadoDTO();
		 * empleadoDTO.setIdEmpleado(empleadoEntity.getIdEmpleado());
		 * empleadoDTO .setIdAspirante(aspiranteEntity.getIdAspirante()); //
		 * empleadoDTO.setFaseRegistro(empleadoEntity.getFase());
		 * empleadoDTO.setDatosGeneralesDTO(datosGeneralesDTO);
		 * 
		 * }
		 * 
		 * } }
		 * 
		 * return empleadoDTO;
		 */
		return null;
	}

	protected AspiranteDTO consultaAspirantePorIdentificador(Integer IdAspirante) {

		String contexto = "Actualizacion Aspirante: ";

		AspiranteDTO aspiranteDTO = new AspiranteDTO();

		AspiranteEntity aspiranteEntity = aspiranteRepository.obtenerPorId(IdAspirante);

		if (aspiranteEntity == null) {
			throw new ReglaNegocioException(
					contexto + " No se ha encontrado registro con el identificador " + IdAspirante,
					ReglaNegocioCodigoError.SIN_REGISTRO);
		} else {
			aspiranteDTO.setIdAspirante(aspiranteEntity.getIdAspirante());
			aspiranteDTO.setNombre(aspiranteEntity.getNombre());
			aspiranteDTO.setApellidoPaterno(aspiranteEntity.getApellidoPaterno());
			aspiranteDTO.setApellidoMaterno(aspiranteEntity.getApellidoMaterno());
			aspiranteDTO.setRfc(aspiranteEntity.getRfc());
			aspiranteDTO.setCurp(aspiranteEntity.getCurp());
			aspiranteDTO.setFechaNacimiento(aspiranteEntity.getFechaNacimiento());
			aspiranteDTO.setTelefono(aspiranteEntity.getTelefono());
			aspiranteDTO.setCorreoElectronico(aspiranteEntity.getCorreoElectronico());
			aspiranteDTO.setNumeroHijos(aspiranteEntity.getNumeroHijos());
			aspiranteDTO.setNumeroConyuges(aspiranteEntity.getNumeroConyuges());
			aspiranteDTO.setNumeroPadres(aspiranteEntity.getNumeroPadres());
			aspiranteDTO.setNumeroOtros(aspiranteEntity.getNumeroOtros());
			aspiranteDTO.setTipoSangre(aspiranteEntity.getTipoSangre());
			aspiranteDTO.setNacionalidad(aspiranteEntity.getNacionalidad());

			// Validando si existe nacionalidad
			if (aspiranteEntity.getPaisNacionalidad() != null) {
				aspiranteDTO.setIdPaisNacionalidad(aspiranteEntity.getPaisNacionalidad().getIdPais());
			}

			aspiranteDTO.setEstadoCivil(aspiranteEntity.getEstadoCivil());
			// Validando cargo o puesto si existe
			if (aspiranteEntity.getCargo() != null) {
				aspiranteDTO.setIdPuesto(aspiranteEntity.getCargo().getIdPuestoGeneral());
			}

			aspiranteDTO.setSexo(aspiranteEntity.getIdSexo());
			aspiranteDTO.setLugarNacimiento(aspiranteEntity.getLugarNacimiento());

			aspiranteDTO.setPeso(aspiranteEntity.getPeso());
			aspiranteDTO.setEstatura(aspiranteEntity.getEstatura());
			aspiranteDTO.setViveCon(aspiranteEntity.getViveCon());
			aspiranteDTO.setTienePersonasDependientes(aspiranteEntity.getTienePersonasDependientes());

			List<DocumentacionEntity> listaDocumentacion = documentacionRepository
					.documentacionesPorIdAspirante(aspiranteEntity.getIdAspirante());

			if (!listaDocumentacion.isEmpty()) {

				for (DocumentacionEntity documentacionEntity : listaDocumentacion) {

					// Documentación
					if (documentacionEntity.getTipoDocumento().equals(EnumTipoDocumento.AFORE)) {

						aspiranteDTO.setAfore(documentacionEntity.getDocumento());

					}
					if (documentacionEntity.getTipoDocumento().equals(EnumTipoDocumento.SEGURO_SOCIAL)) {
						aspiranteDTO.setNumeroSeguroSocial(documentacionEntity.getDocumento());

					}
					if (documentacionEntity.getTipoDocumento().equals(EnumTipoDocumento.CARTILLA_MILITAR)) {
						aspiranteDTO.setNumeroCartillaMilitar(documentacionEntity.getDocumento());

					}
					if (documentacionEntity.getTipoDocumento().equals(EnumTipoDocumento.PASAPORTE)) {
						aspiranteDTO.setNumeroPasaporte(documentacionEntity.getDocumento());

					}
					if (documentacionEntity.getTipoDocumento().equals(EnumTipoDocumento.LICENCIA)) {
						aspiranteDTO.setTieneLicencia(true);
						aspiranteDTO.setNumeroLicencia(documentacionEntity.getDocumento());
						aspiranteDTO.setTipoLicencia(documentacionEntity.getTipoLicencia());

					}
				}

			}

			DireccionEntity direccionEntity = direccionEmpleadoRepository
					.consultarDireccionAspirantePorId(aspiranteEntity.getIdAspirante());

			if (direccionEntity != null) {

				DireccionDTO direccionDTO = new DireccionDTO();

				direccionDTO.setCalle(direccionEntity.getCalle());
				direccionDTO.setNumeroExterior(direccionEntity.getNumeroExterior());
				direccionDTO.setNumeroInterior(direccionEntity.getNumeroInterior());
				direccionDTO.setCodigoPostal(direccionEntity.getCodigoPostal());

				direccionDTO.setIdEstado(
						direccionEntity.getEstado() == null ? 0 : direccionEntity.getEstado().getIdEstado());

				direccionDTO.setIdMunicipio(
						direccionEntity.getMunicipio() == null ? 0 : direccionEntity.getMunicipio().getIdMunicipio());

				direccionDTO.setIdAsentamiento(direccionEntity.getAsentamiento() == null ? 0
						: direccionEntity.getAsentamiento().getIdAsentamiento());

				aspiranteDTO.setDireccionDTO(direccionDTO);
			}

		}

		return aspiranteDTO;

	}

	protected List<InfoEmpleadoDTO> consultaEmpleadoPorCriterio(String criterio) {
		if (ValidacionUtil.esCadenaVacia(criterio)) {
			throw new ValidacionException("El criterio de busqueda es requerido, ingrese uno.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (criterio.trim().length() < 4) {
			throw new ValidacionException("El criterio de la busqueda debe contener minimo 4 letras.",
					ValidacionCodigoError.VALOR_MUY_CORTO);
		}
		List<InfoEmpleadoDTO> empleados = empleadoRepository.consultarEmpleadoPorCriterio(criterio);
		return empleados;

	}

	protected List<InfoEmpleadoDTO> consultarEmpleado(FiltroDTO filtroDTO) {

		List<InfoEmpleadoDTO> empleados = new ArrayList<InfoEmpleadoDTO>();

		if (filtroDTO.getTipoFiltro() == EnumTipoFiltro.NOMBRE_RFC_CURP) {
			empleados = empleadoRepository.consultarEmpleadoPorCriterio(filtroDTO.getCriterio());
		} else if (filtroDTO.getTipoFiltro() == EnumTipoFiltro.NOMBRE_RFC_CURP_PROFESION) {
			empleados = empleadoRepository.empleadosPorCriteriosPerfil(filtroDTO.getCriterio());
		} else if (filtroDTO.getTipoFiltro() == EnumTipoFiltro.NOMBRE_RFC_CURP_ACTIVOS) {
			System.out.println("entro a consulta de activos");
			empleados = empleadoRepository.consultarEmpleadosActivosPorCriterio(filtroDTO.getCriterio());
		}

		return empleados;
	}

	protected List<InfoEmpleadoDTO> consultarEmpleadosConPuestosActivos(FiltroDTO filtroDTO) {
		List<InfoEmpleadoDTO> empleados = new ArrayList<InfoEmpleadoDTO>();
		if (filtroDTO.getTipoFiltro() == EnumTipoFiltro.NOMBRE_RFC_CURP) {
			empleados = inventarioPuestosRepository.empleadosPorCriterio(filtroDTO.getCriterio());
		} else if (filtroDTO.getTipoFiltro() == EnumTipoFiltro.NOMBRE_RFC_CURP_CONTRATACION) {

			empleados = inventarioPuestosRepository.empleadosPorCriterioTipoContratacion(filtroDTO.getCriterio(),
					filtroDTO.getId());
		} else if (filtroDTO.getTipoFiltro() == EnumTipoFiltro.CRITERIO_COMBO_TODAS_ADSCRIPCIONES) {
			empleados = inventarioPuestosRepository.empleadosPorCriterioCombo(filtroDTO.getCriterio());
		} else if (filtroDTO.getTipoFiltro() == EnumTipoFiltro.CRITERIO_COMBO_ADSCRIPCION_ASIGNADA) {
			empleados = inventarioPuestosRepository.empleadosPorCriterioAdscripcionCombo(filtroDTO.getCriterio(),
					filtroDTO.getId());
		} else if (filtroDTO.getTipoFiltro() == EnumTipoFiltro.CRITERIO_FEDERALES) {
			empleados = inventarioPuestosRepository.empleadosFederalesPorCriterio(filtroDTO.getCriterio());
		}

		return empleados;
	}

	protected DatosGeneralesDTO obtenerDatosGeneralesEmpleado(Integer idEmpleado) {
		String contexto = "Consulta Empleado: ";
		EmpleadoEntity empleado = empleadoRepository.obtenerPorId(idEmpleado);
		if (empleado == null) {
			throw new ReglaNegocioException(contexto + "No se ha encontrado empleado con identificador " + idEmpleado,
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		DatosGeneralesDTO dto = new DatosGeneralesDTO();
		dto.setTipoEmpleado(empleado.getTipoEmpleado().getTipoEmpleado());
		dto.setEstatus(empleado.getIdEstatus());
		dto.setCorreo(empleado.getCorreoElectronico());
		dto.setCurp(empleado.getCurp());
		dto.setEstatura(empleado.getEstatura());
		dto.setFechaNacimiento(empleado.getFechaNacimiento());
		dto.setIdEmpleado(empleado.getIdEmpleado());
		dto.setIdEstadoCivil(empleado.getEstadoCivil());
		dto.setIdSexo(empleado.getIdSexo());
		dto.setIdTipoSangre(empleado.getTipoSangre());
		dto.setLugarNacimiento(empleado.getLugarNacimiento());
		dto.setNacionalidad(empleado.getNacionalidad());
		dto.setNombreCompleto(empleado.getNombreCompleto());
		dto.setPeso(empleado.getPeso());
		dto.setRfc(empleado.getRfc());
		dto.setTelefonos(empleado.getTelefono());
		dto.setTienePersonasDependientes(empleado.getTienePersonasDependientes());
		dto.setDireccionCompleta(empleado.getDireccionCompleta());
		dto.setIdFoto(empleado.getIdFoto());
		dto.setNombre(empleado.getNombre());
		dto.setApellidoMaterno(empleado.getApellidoMaterno());
		dto.setApellidoPaterno(empleado.getApellidoPaterno());

		if (dto.getTienePersonasDependientes() != null && dto.getTienePersonasDependientes()) {
			List<String> parentescos = new ArrayList<>();
			if (empleado.getNumeroConyuges() != null && empleado.getNumeroConyuges() > 0) {
				dto.setNumeroConyuges(empleado.getNumeroConyuges());
				parentescos.add("CONYUGE");
			}
			if (empleado.getNumeroHijos() > 0) {
				dto.setNumeroHijos(empleado.getNumeroHijos());
				parentescos.add("HIJOS");
			}
			if (empleado.getNumeroPadres() != null && empleado.getNumeroPadres() > 0) {
				dto.setNumeroPadres(empleado.getNumeroPadres());
				parentescos.add("PADRE");
			}
			if (empleado.getNumeroOtros() != null && empleado.getNumeroOtros() > 0) {
				dto.setNumeroOtros(empleado.getNumeroOtros());
				parentescos.add("OTRO");
			}

			dto.setParentescos(parentescos);

		}
		return dto;
	}

	protected DomicilioDTO obtenerDomicilioEmpleado(Integer idEmpleado) {

		DireccionEntity direccionEntity = direccionEmpleadoRepository.consultarDireccionEmpleadoPorId(idEmpleado);

		DomicilioDTO direccionDTO = new DomicilioDTO();
		if (direccionEntity == null) {
			direccionDTO.setTieneDireccion(false);
		} else {
			direccionDTO.setIdEstado(direccionEntity.getEstado().getIdEstado());
			direccionDTO.setCalle(direccionEntity.getCalle());
			direccionDTO.setCodigoPostal(direccionEntity.getCodigoPostal());

			direccionDTO.setIdMunicipio(direccionEntity.getMunicipio().getIdMunicipio());
			direccionDTO.setIdAsentamiento(direccionEntity.getAsentamiento().getIdAsentamiento());
			direccionDTO.setNumeroExterior(direccionEntity.getNumeroExterior());
			direccionDTO.setNumeroInterior(direccionEntity.getNumeroInterior());
			direccionDTO.setTieneDireccion(true);
		}

		return direccionDTO;
	}

	protected List<ExperienciaLaboralDTO> listaExperienciaLaboralPorIdEmpleado(Integer idAspirante) {
		List<ExperienciaLaboralDTO> experienciasLaboralesDTO = new ArrayList<>();

		List<ExperienciaLaboralEntity> experienciasLaboralesEntities = experienciaLaboralRepository
				.consultarExperienciasLaboralesAspirante(idAspirante);

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

	protected List<InfoVacantePostularDTO> obtenerListaEmpleadoCandidato() {

		return empleadoRepository.obtenerListaEmpleadoCandidato();
	}

	public InfoCandidatoDTO obtenerInformacionCandidatoEmpleado(Integer idEmpleado) {
		EmpleadoEntity empleadoEntity = empleadoRepository.obtenerPorId(idEmpleado);
		if (empleadoEntity == null) {
			throw new ReglaNegocioException("No se ha encontrado empleado con identificador " + idEmpleado,
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		InfoCandidatoDTO infoCandidatoDTO = new InfoCandidatoDTO();
		infoCandidatoDTO.setCurp(empleadoEntity.getCurp());
		infoCandidatoDTO.setDomicilio(empleadoEntity.getDireccionCompleta());
		infoCandidatoDTO.setEdad(FechaUtil.calcularEdad(empleadoEntity.getFechaNacimiento()));
		infoCandidatoDTO.setEstadoCivil(empleadoEntity.getEstadoCivil());
		infoCandidatoDTO.setNacionalidad(empleadoEntity.getNacionalidad());
		infoCandidatoDTO.setNombre(empleadoEntity.getNombreCompleto());
		infoCandidatoDTO.setRfc(empleadoEntity.getRfc());
		infoCandidatoDTO.setSexo(empleadoEntity.getIdSexo());

		String estudios = "";
		List<String> listaEstudios = historialAcademicoRepository.consultaEstudiosProfesionistasEmpleado(idEmpleado);
		if (!listaEstudios.isEmpty()) {
			for (String estudio : listaEstudios) {
				estudios = estudio + "/";
			}
		}
		infoCandidatoDTO.setEstudios(estudios);

		return infoCandidatoDTO;
	}

	/*
	 * 
	 * */
	protected EmpleadoDetalladoDTO obtenerInformacionEmpleado(Integer idEmpleado) {

		if (idEmpleado == null) {
			throw new ValidacionException("Es requerido el identificador del empleado para obtener su información",
					ValidacionCodigoError.VALOR_REQUERIDO);

		}

		String estatusEmpleado = empleadoRepository.obtenerEstatusEmpleado(idEmpleado);
		Integer idTipoEmpleado = empleadoRepository.obtenerTipoEmpleado(idEmpleado);

		EmpleadoDetalladoDTO dto = new EmpleadoDetalladoDTO();
		dto.setEstatus(estatusEmpleado);

		if (estatusEmpleado.equals(EnumEstatusEmpleado.INACTIVO) || idTipoEmpleado == EnumTipoEmpleado.SUPLENTE) {

			EmpleadoEntity empleado = empleadoRepository.obtenerPorId(idEmpleado);
			dto.setCurp(empleado.getCurp());
			dto.setDomicilio(empleado.getDireccionCompleta());
			dto.setEdad(FechaUtil.calcularEdad(empleado.getFechaNacimiento()));
			dto.setEstadoCivil(empleado.getEstadoCivil());

			if (!ValidacionUtil.esCadenaVacia(empleado.getPerfilAcademico())) {
				dto.setEstudios(empleado.getPerfilAcademico());
			} else {
				dto.setEstudios("SIN REGISTRO");
			}

			dto.setNacionalidad(empleado.getNacionalidad());
			dto.setNombre(empleado.getNombreCompleto());
			dto.setRfc(empleado.getRfc());
			dto.setSexo(empleado.getIdSexo());
			dto.setTipoEmpleado(empleado.getTipoEmpleado().getTipoEmpleado());

		} else {

			InventarioVacanteEntity puesto = inventarioVacanteRepository.obtenerPuestoPorIdEmpleado(idEmpleado);

			if (puesto == null) {

				throw new ReglaNegocioException("El empleado no se encuentra activo.",
						ReglaNegocioCodigoError.CONFIGURACION_NO_EXISTE);

			}

			if (puesto.getConfiguracion() == null) {
				throw new ValidacionException("El empleado no tiene asignado dato laboral",
						ValidacionCodigoError.VALOR_REQUERIDO);
			}

			if (puesto.getAdscripcion() != null) {
				dto.setAdscripcion(puesto.getAdscripcion().getAdscripcion());

			} else {
				dto.setAdscripcion("SIN ASIGNAR");
			}

			if (puesto.getSubadscripcion() != null) {
				dto.setAreaAdscripcion(puesto.getSubadscripcion().getSubadscripcion());
			} else {
				dto.setAreaAdscripcion("SIN ASIGNAR");
			}
			if (puesto.getServicio() != null) {
				dto.setServicio(puesto.getServicio().getServicio());
			} else {
				dto.setServicio("SIN ASIGNAR");
			}
			if (puesto.getFuncion() != null) {
				dto.setFuncion(puesto.getFuncion().getFuncion());
			} else {
				dto.setFuncion("SIN ASIGNAR");
			}

			dto.setCodigoPuesto(puesto.getConfiguracion().getPuesto().getCodigo());
			dto.setCurp(puesto.getConfiguracion().getEmpleado().getCurp());
			dto.setDomicilio(puesto.getConfiguracion().getEmpleado().getDireccionCompleta());
			dto.setEdad(FechaUtil.calcularEdad(puesto.getConfiguracion().getEmpleado().getFechaNacimiento()));
			dto.setEstadoCivil(puesto.getConfiguracion().getEmpleado().getEstadoCivil());
			if (!ValidacionUtil.esCadenaVacia(puesto.getConfiguracion().getEmpleado().getPerfilAcademico())) {
				dto.setEstudios(puesto.getConfiguracion().getEmpleado().getPerfilAcademico());
			} else {
				dto.setEstudios("SIN REGISTRO");
			}

			dto.setIdTipoContratacion(puesto.getTipoContratacion().getId());

			dto.setNacionalidad(puesto.getConfiguracion().getEmpleado().getNacionalidad());
			dto.setNombramiento(puesto.getConfiguracion().getNombramiento().getNombramiento());
			dto.setNombre(puesto.getConfiguracion().getEmpleado().getNombreCompleto());
			dto.setPuesto(puesto.getConfiguracion().getPuesto().getPuesto());
			dto.setRfc(puesto.getConfiguracion().getEmpleado().getRfc());
			dto.setSexo(puesto.getConfiguracion().getEmpleado().getIdSexo());
			dto.setTipoContratacion(puesto.getTipoContratacion().getTipoContratacion());
			dto.setUnidadResponsable(puesto.getConfiguracion().getUnidadResponsable().getDescripcion());
			dto.setTipoEmpleado(puesto.getEmpleadoActivo().getTipoEmpleado().getTipoEmpleado());

			// TODO checar
			/*
			 * MovimientoEmpleadoEntity ultimaLicencia =
			 * movimientoEmpleadoRepository .obtenerUltimoMovimientoPorPadre(
			 * EnumTipoMovimiento.LICENCIAS, idEmpleado);
			 * 
			 * if (ultimaLicencia != null) {
			 * dto.setLicencia(ultimaLicencia.getMovimiento ().getMovimiento());
			 * dto.setUltimaLicencia(ultimaLicencia.getFechaIngreso()); }
			 */

		}

		return dto;
	}

	protected List<InfoEmpleadoDTO> empleadosPorCriterioTipoContratacion(String criterio) {

		return inventarioVacanteRepository.consultaEmpleadosFederales(criterio);
	}

	protected List<InfoEmpleadoDTO> empleadosPorCriterioConNombramiento(String criterio) {

		return inventarioVacanteRepository.empleadosPorCriterioConNombramiento(criterio);
	}

}
