/**
 * 
 */
package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.AspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.DireccionEntity;
import mx.gob.saludtlax.rh.persistencia.DireccionRepository;
import mx.gob.saludtlax.rh.persistencia.DocumentacionEntity;
import mx.gob.saludtlax.rh.persistencia.DocumentacionRepository;
import mx.gob.saludtlax.rh.persistencia.EspecialidadAspiranteEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoRepository;
import mx.gob.saludtlax.rh.persistencia.ProfesionAspiranteEmpleadoRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import mx.gob.saludtlax.rh.vacantes.postulacion.InfoCandidatoDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

/**
 * @author Leila Schiaffini Ehuan
 * @since 01/08/2016 15:55:16
 * 
 */
public class ConsultaAspiranteService {

	@Inject
	private AspiranteRepository aspiranteRepository;
	@Inject
	private DireccionRepository direccionEmpleadoRepository;
	@Inject
	private DocumentacionRepository documentacionRepository;

	protected List<InfoAspiranteDTO> consultarAspirantePorCriterio(FiltroDTO filtroDTO) {
		List<InfoAspiranteDTO> aspirantes = new ArrayList<InfoAspiranteDTO>();
		if (filtroDTO.getTipoFiltro() == EnumTipoFiltro.NOMBRE_RFC_CURP) {
			if (ValidacionUtil.esCadenaVacia(filtroDTO.getCriterio())) {
				throw new ValidacionException("El criterio de búsqueda es requerido.",
						ValidacionCodigoError.VALOR_REQUERIDO);
			}
			aspirantes = aspiranteRepository.aspirantesDisponiblesPorCriterio(filtroDTO.getCriterio());
		}

		if (filtroDTO.getTipoFiltro() == EnumTipoFiltro.NOMBRE_RFC_CURP_PROFESION) {
			aspirantes = aspiranteRepository.aspirantesDisponiblesPorCriterioPerfil(filtroDTO.getCriterio());
		}

		return aspirantes;

	}

	public InfoCandidatoDTO obtenerInformacionCandidatoAspirante(Integer idAspirante) {
		AspiranteEntity aspiranteEntity = aspiranteRepository.obtenerPorId(idAspirante);

		if (aspiranteEntity == null) {
			throw new ReglaNegocioException("No se ha encontrado aspirante con identificador" + idAspirante,
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		InfoCandidatoDTO infoCandidatoDTO = new InfoCandidatoDTO();
		infoCandidatoDTO.setCurp(aspiranteEntity.getCurp());
		infoCandidatoDTO.setDomicilio(aspiranteEntity.getDireccionCompleta());
		infoCandidatoDTO.setEdad(FechaUtil.calcularEdad(aspiranteEntity.getFechaNacimiento()));
		infoCandidatoDTO.setEstadoCivil(aspiranteEntity.getEstadoCivil());
		infoCandidatoDTO.setNacionalidad(aspiranteEntity.getNacionalidad());
		infoCandidatoDTO.setNombre(aspiranteEntity.getNombreCompleto());
		infoCandidatoDTO.setRfc(aspiranteEntity.getRfc());
		infoCandidatoDTO.setSexo(aspiranteEntity.getIdSexo());

		infoCandidatoDTO.setEstudios(aspiranteEntity.getPerfilAcademico());

		return infoCandidatoDTO;
	}

	protected InfoAspiranteDTO obtenerDetalleAspirantePorId(Integer idAspirante) {
		if (!ValidacionUtil.esNumeroPositivo(idAspirante)) {
			throw new ValidacionException("", ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}
		AspiranteEntity aspiranteEntity = aspiranteRepository.obtenerPorId(idAspirante);

		if (aspiranteEntity == null) {
			throw new ValidacionException("No se ha encontrado aspirante con identificador" + idAspirante,
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		if (aspiranteEntity.getIdEstatus().equals(EnumEstatusAspirante.EMPLEADO)) {
			throw new ReglaNegocioException("El aspirante ha sido registrado como empleado.",
					ReglaNegocioCodigoError.ASPIRANTE_AUTORIZADO);
		}

		if (aspiranteEntity.getIdEstatus().equals(EnumEstatusAspirante.RECHAZADO)) {
			throw new ReglaNegocioException("El aspirante ha sido rechazado, actualice a disponible.",
					ReglaNegocioCodigoError.ASPIRANTE_AUTORIZADO);
		}

		InfoAspiranteDTO dto = new InfoAspiranteDTO();
		dto.setCurp(aspiranteEntity.getCurp());
		dto.setDireccion(aspiranteEntity.getDireccionCompleta());
		dto.setEdad(FechaUtil.calcularEdad(aspiranteEntity.getFechaNacimiento()));
		dto.setEstadoCivil(aspiranteEntity.getEstadoCivil());
		dto.setEstatus(EnumEstatusAspirante.RECHAZADO);
		dto.setEstudios(aspiranteEntity.getPerfilAcademico());
		dto.setNacionalidad(aspiranteEntity.getNacionalidad());
		dto.setNombre(aspiranteEntity.getNombreCompleto());
		dto.setRfc(aspiranteEntity.getRfc());
		dto.setSexo(aspiranteEntity.getIdSexo());
		return dto;
	}

	protected AspiranteDTO obtenerAspirantePorIdentificador(Integer idAspirante) {

		AspiranteDTO aspiranteDTO = new AspiranteDTO();

		AspiranteEntity aspiranteEntity = aspiranteRepository.obtenerPorId(idAspirante);

		if (aspiranteEntity == null) {
			throw new ReglaNegocioException("No se ha encontrado aspirante con identificador" + idAspirante,
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
				direccionDTO.setIdEstado(direccionEntity.getEstado().getIdEstado() == null ? 0
						: direccionEntity.getEstado().getIdEstado());
				direccionDTO.setIdMunicipio(
						direccionEntity.getMunicipio() == null ? 0 : direccionEntity.getMunicipio().getIdMunicipio());
				direccionDTO.setIdAsentamiento(direccionEntity.getAsentamiento() == null ? 0
						: direccionEntity.getAsentamiento().getIdAsentamiento());

				aspiranteDTO.setDireccionDTO(direccionDTO);
			}

		}

		return aspiranteDTO;

	}

	protected List<InfoVacantePostularDTO> obtenerListaAspiranteCandidato() {

		return aspiranteRepository.obtenerListaAspiranteCandidato();
	}

}
