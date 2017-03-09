/**
 * 
 */
package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import javax.inject.Inject;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.persistencia.AsentamientoEntity;
import mx.gob.saludtlax.rh.persistencia.AsentamientoRepository;
import mx.gob.saludtlax.rh.persistencia.AspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.DireccionEntity;
import mx.gob.saludtlax.rh.persistencia.DireccionRepository;
import mx.gob.saludtlax.rh.persistencia.DocumentacionEntity;
import mx.gob.saludtlax.rh.persistencia.DocumentacionRepository;
import mx.gob.saludtlax.rh.persistencia.EstadoEntity;
import mx.gob.saludtlax.rh.persistencia.EstadoRepository;
import mx.gob.saludtlax.rh.persistencia.MunicipioRepository;
import mx.gob.saludtlax.rh.persistencia.MunicipiosEntity;
import mx.gob.saludtlax.rh.persistencia.PaisEntity;
import mx.gob.saludtlax.rh.persistencia.PaisRepository;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-14:59:58
 */
public class AspiranteService {

	private static final Logger LOGGER = Logger.getLogger(AspiranteService.class);

	@Inject
	private AspiranteRepository aspiranteRepository;

	@Inject
	private DireccionRepository direccionAspiranteRepository;
	@Inject
	private DocumentacionRepository documentacionRepository;
	@Inject
	private EstadoRepository estadoRepository;
	@Inject
	private MunicipioRepository municipioRepository;
	@Inject
	private PaisRepository paisRepository;
	@Inject
	private AsentamientoRepository poblacionRepository;
	@Inject
	private PuestoGeneralRepository puestoRepository;

	protected Integer crearAspirante(AspiranteDTO dto) {
		String contexto = "Registro Aspirante: ";

		AspiranteEntity aspiranteEntity = new AspiranteEntity();

		PuestoGeneralEntity puestoEntity = puestoRepository.obtenerPorId(dto.getIdPuesto());
		EstadoEntity estadoEntity = estadoRepository.estadoPorId(dto.getDireccionDTO().getIdEstado());

		if (aspiranteRepository.consultarAspiranteRFC(dto.getRfc())) {
			LOGGER.error(contexto + "Existe un aspirante registrada con el rfc, ingrese una nueva.");
			throw new BusinessException(contexto + "Existe un aspirante registrada con el rfc, ingrese una nueva.");
		}

		if (aspiranteRepository.existeAspiranteCurp(dto.getCurp())) {
			LOGGER.error(contexto + "Existe un aspirante registrada con la curp, ingrese una nueva.");
			throw new BusinessException(contexto + "Existe un aspirante registrada con la curp, ingrese una nueva.");
		}

		MunicipiosEntity municipiosEntity = municipioRepository.obtenerPorId(dto.getDireccionDTO().getIdMunicipio());
		AsentamientoEntity poblacionEntity = poblacionRepository
				.obtenerPorId(dto.getDireccionDTO().getIdAsentamiento());

		aspiranteEntity.setNombre(dto.getNombre());
		aspiranteEntity.setApellidoPaterno(dto.getApellidoPaterno());
		aspiranteEntity.setApellidoMaterno(dto.getApellidoMaterno());
		aspiranteEntity.setRfc(dto.getRfc());
		aspiranteEntity.setCurp(dto.getCurp());
		aspiranteEntity.setFechaNacimiento(dto.getFechaNacimiento());
		aspiranteEntity.setTelefono(dto.getTelefono());
		aspiranteEntity.setCorreoElectronico(dto.getCorreoElectronico());
		aspiranteEntity.setNumeroHijos(dto.getNumeroHijos());
		aspiranteEntity.setFechaAlta(FechaUtil.fechaActual());
		aspiranteEntity.setIdEstatus(EnumEstatusAspirante.DISPONIBLE);
		aspiranteEntity.setDireccionCompleta("-");
		aspiranteEntity.setTipoSangre(dto.getTipoSangre());
		aspiranteEntity.setNacionalidad(dto.getNacionalidad());// TODO la
																// nacionalidad
																// por
		// default es mexicana
		if (dto.getNacionalidad().equals(EnumNacionalidad.EXTRANJERA)) {
			PaisEntity paisNacionalidad = paisRepository.paisPorId(dto.getIdPaisNacionalidad());
			aspiranteEntity.setPaisNacionalidad(paisNacionalidad);
		}
		aspiranteEntity.setEstadoCivil(dto.getEstadoCivil());
		// Validando cargo o puesto
		if (puestoEntity != null) {
			aspiranteEntity.setCargo(puestoEntity);
		}

		aspiranteEntity.setIdSexo(dto.getSexo());
		aspiranteEntity.setLugarNacimiento(dto.getLugarNacimiento());
		aspiranteEntity.setEstatura(dto.getEstatura());
		aspiranteEntity.setPeso(dto.getPeso());
		aspiranteEntity.setViveCon(dto.getViveCon());

		// Validar Si Tiene Personas Dependientes
		if (dto.getNumeroHijos() != 0 || dto.getNumeroConyuges() != 0 || dto.getNumeroPadres() != 0
				|| dto.getNumeroOtros() != 0) {
			aspiranteEntity.setTienePersonasDependientes(true);
		} else {
			aspiranteEntity.setTienePersonasDependientes(false);
		}

		// Número de personas que dependen del empleado
		aspiranteEntity.setNumeroHijos(dto.getNumeroHijos());
		aspiranteEntity.setNumeroConyuges(dto.getNumeroConyuges());
		aspiranteEntity.setNumeroPadres(dto.getNumeroPadres());
		aspiranteEntity.setNumeroOtros(dto.getNumeroOtros());
		// fin
		aspiranteEntity.setTieneLicencia(dto.isTieneLicencia());
		// aspiranteEntity.setEsEmpleado(false);
		aspiranteEntity.toUpperCase();

		// Concatenando nombre completo
		String nombreCompleto = dto.getNombre() + " ";
		if (dto.getApellidoPaterno() != null || dto.getApellidoPaterno().trim().isEmpty()) {
			nombreCompleto = nombreCompleto + dto.getApellidoPaterno() + " ";
		}
		nombreCompleto = nombreCompleto + dto.getApellidoMaterno();

		// String nombreCompleto = aspiranteEntity.nombreCompleto();
		aspiranteEntity.setNombreCompleto(nombreCompleto);
		aspiranteRepository.crearActualizarAspirante(aspiranteEntity);

		// Registrar documentación
		if (!ValidacionUtil.esCadenaVacia(dto.getAfore())) {
			registrarDocumentacion(EnumTipoDocumento.AFORE, dto.getAfore(), aspiranteEntity.getIdAspirante(), " ");

		}
		if (!ValidacionUtil.esCadenaVacia(dto.getNumeroSeguroSocial())) {
			registrarDocumentacion(EnumTipoDocumento.SEGURO_SOCIAL, dto.getNumeroSeguroSocial(),
					aspiranteEntity.getIdAspirante(), " ");
		}
		if (!ValidacionUtil.esCadenaVacia(dto.getNumeroCartillaMilitar())) {
			registrarDocumentacion(EnumTipoDocumento.CARTILLA_MILITAR, dto.getNumeroCartillaMilitar(),
					aspiranteEntity.getIdAspirante(), " ");
		}
		if (!ValidacionUtil.esCadenaVacia(dto.getNumeroPasaporte())) {
			registrarDocumentacion(EnumTipoDocumento.PASAPORTE, dto.getNumeroPasaporte(),
					aspiranteEntity.getIdAspirante(), " ");
		}
		if (dto.isTieneLicencia()) {
			registrarDocumentacion(EnumTipoDocumento.LICENCIA, dto.getNumeroLicencia(),
					aspiranteEntity.getIdAspirante(), dto.getTipoLicencia());

		}
		DireccionEntity direccion = new DireccionEntity();
		direccion.setCalle(dto.getDireccionDTO().getCalle());
		direccion.setNumeroExterior(dto.getDireccionDTO().getNumeroExterior());
		direccion.setNumeroInterior(dto.getDireccionDTO().getNumeroInterior());
		direccion.setCodigoPostal(dto.getDireccionDTO().getCodigoPostal());
		direccion.setEstado(estadoEntity);
		direccion.setMunicipio(municipiosEntity);
		direccion.setAsentamiento(poblacionEntity);
		direccion.setIdAspirante(aspiranteEntity.getIdAspirante());
		// Registrando Dirección
		direccionAspiranteRepository.crear(direccion);
		// Registrar direccion completa
		aspiranteEntity.setDireccionCompleta(direccion.direccionCompleta());
		// Registra el aspirante y me regresa la entidad
		aspiranteRepository.crear(aspiranteEntity);

		return aspiranteEntity.getIdAspirante();

	}

	protected Integer actualizarAspirante(AspiranteDTO dto) {
		String contexto = "Actualización Aspirante: ";

		AspiranteEntity aspiranteEntity = aspiranteRepository.obtenerPorId(dto.getIdAspirante());

		if (aspiranteEntity == null) {
			throw new BusinessException(contexto + "No se encontro el aspirante, realice la actualización nuevamente");
		}

		PuestoGeneralEntity puestoEntity = puestoRepository.obtenerPorId(dto.getIdPuesto());
		EstadoEntity estadoEntity = estadoRepository.estadoPorId(dto.getDireccionDTO().getIdEstado());

		MunicipiosEntity municipiosEntity = municipioRepository.obtenerPorId(dto.getDireccionDTO().getIdMunicipio());
		AsentamientoEntity poblacionEntity = poblacionRepository
				.obtenerPorId(dto.getDireccionDTO().getIdAsentamiento());

		aspiranteEntity.setNombre(dto.getNombre());
		aspiranteEntity.setApellidoPaterno(dto.getApellidoPaterno());
		aspiranteEntity.setApellidoMaterno(dto.getApellidoMaterno());
		aspiranteEntity.setRfc(dto.getRfc());
		aspiranteEntity.setCurp(dto.getCurp());
		aspiranteEntity.setFechaNacimiento(dto.getFechaNacimiento());
		aspiranteEntity.setTelefono(dto.getTelefono());
		aspiranteEntity.setCorreoElectronico(dto.getCorreoElectronico());
		aspiranteEntity.setNumeroHijos(dto.getNumeroHijos());
		aspiranteEntity.setIdEstatus(EnumEstatusAspirante.DISPONIBLE);
		aspiranteEntity.setDireccionCompleta("-");
		aspiranteEntity.setTipoSangre(dto.getTipoSangre());
		aspiranteEntity.setNacionalidad(dto.getNacionalidad());// TODO la
																// nacionalidad
																// por
		// default es mexicana
		if (dto.getNacionalidad().equals(EnumNacionalidad.EXTRANJERA)) {
			PaisEntity paisNacionalidad = paisRepository.paisPorId(dto.getIdPaisNacionalidad());
			aspiranteEntity.setPaisNacionalidad(paisNacionalidad);
		}
		aspiranteEntity.setEstadoCivil(dto.getEstadoCivil());
		// Validando cargo o puesto
		if (puestoEntity != null) {
			aspiranteEntity.setCargo(puestoEntity);
		}

		aspiranteEntity.setIdSexo(dto.getSexo());
		aspiranteEntity.setLugarNacimiento(dto.getLugarNacimiento());
		aspiranteEntity.setEstatura(dto.getEstatura());
		aspiranteEntity.setPeso(dto.getPeso());
		aspiranteEntity.setViveCon(dto.getViveCon());

		// Validar Si Tiene Personas Dependientes
		if (dto.getNumeroHijos() != 0 || dto.getNumeroConyuges() != 0 || dto.getNumeroPadres() != 0
				|| dto.getNumeroOtros() != 0) {
			aspiranteEntity.setTienePersonasDependientes(true);
		}

		// aspiranteEntity.setPersonasDependientes(dto.getPersonasDependientes());
		// Número de personas que dependen del empleado
		aspiranteEntity.setNumeroHijos(dto.getNumeroHijos());
		aspiranteEntity.setNumeroConyuges(dto.getNumeroConyuges());
		aspiranteEntity.setNumeroPadres(dto.getNumeroPadres());
		aspiranteEntity.setNumeroOtros(dto.getNumeroOtros());
		// fin
		aspiranteEntity.setTieneLicencia(dto.isTieneLicencia());
		aspiranteEntity.toUpperCase();

		// Concatenando nombre completo
		String nombreCompleto = dto.getNombre() + " ";
		if (dto.getApellidoPaterno() != null || dto.getApellidoPaterno().trim().isEmpty()) {
			nombreCompleto = nombreCompleto + dto.getApellidoPaterno() + " ";
		}
		nombreCompleto = nombreCompleto + dto.getApellidoMaterno();

		// String nombreCompleto = aspiranteEntity.nombreCompleto();
		aspiranteEntity.setNombreCompleto(nombreCompleto);
		aspiranteRepository.crearActualizarAspirante(aspiranteEntity);

		// Registrar documentación
		if (!ValidacionUtil.esCadenaVacia(dto.getAfore())) {
			registrarDocumentacion(EnumTipoDocumento.AFORE, dto.getAfore(), aspiranteEntity.getIdAspirante(), " ");

		}
		if (!ValidacionUtil.esCadenaVacia(dto.getNumeroSeguroSocial())) {
			registrarDocumentacion(EnumTipoDocumento.SEGURO_SOCIAL, dto.getNumeroSeguroSocial(),
					aspiranteEntity.getIdAspirante(), " ");
		}
		if (!ValidacionUtil.esCadenaVacia(dto.getNumeroCartillaMilitar())) {
			registrarDocumentacion(EnumTipoDocumento.CARTILLA_MILITAR, dto.getNumeroCartillaMilitar(),
					aspiranteEntity.getIdAspirante(), " ");
		}
		if (!ValidacionUtil.esCadenaVacia(dto.getNumeroPasaporte())) {
			registrarDocumentacion(EnumTipoDocumento.PASAPORTE, dto.getNumeroPasaporte(),
					aspiranteEntity.getIdAspirante(), " ");
		}
		if (dto.isTieneLicencia()) {
			registrarDocumentacion(EnumTipoDocumento.LICENCIA, dto.getNumeroLicencia(),
					aspiranteEntity.getIdAspirante(), dto.getTipoLicencia());

		}
		DireccionEntity direccion = direccionAspiranteRepository
				.consultarDireccionAspirantePorId(aspiranteEntity.getIdAspirante());
		// Actualizando Dirección
		if (direccion != null) {
			direccion.setCalle(dto.getDireccionDTO().getCalle());
			direccion.setNumeroExterior(dto.getDireccionDTO().getNumeroExterior());
			direccion.setNumeroInterior(dto.getDireccionDTO().getNumeroInterior());
			direccion.setCodigoPostal(dto.getDireccionDTO().getCodigoPostal());
			direccion.setEstado(estadoEntity);
			direccion.setMunicipio(municipiosEntity);
			direccion.setAsentamiento(poblacionEntity);
			direccion.setIdAspirante(aspiranteEntity.getIdAspirante());

			direccionAspiranteRepository.actualizar(direccion);

			// Actualizar direccion completa
			aspiranteEntity.setDireccionCompleta(direccion.direccionCompleta());
		}

		// Actualizando Aspirante
		aspiranteEntity = aspiranteRepository.actualizar(aspiranteEntity);

		return aspiranteEntity.getIdAspirante();
	}

	protected void registrarDocumentacion(String tipoDocumento, String documento, Integer idAspirante,
			String tipoLicencia) {

		AspiranteEntity aspirante = aspiranteRepository.obtenerPorId(idAspirante);

		DocumentacionEntity documentacionEntity = new DocumentacionEntity();
		documentacionEntity.setDocumento(documento);
		documentacionEntity.setIdAspirante(aspirante);
		documentacionEntity.setTipoDocumento(tipoDocumento);
		documentacionEntity.setTipoLicencia(tipoLicencia);

		documentacionRepository.crear(documentacionEntity);

	}

	protected void validarAspiranteRfc(String rfc) {

		rfc = rfc.toUpperCase().trim();

		if (ValidacionUtil.esCadenaVacia(rfc)) {
			throw new BusinessException("Por favor envie una rfc.");
		}
		if (rfc.length() == 13) {
			if (!rfc.trim().matches("([A-Z]{4})([0-9]{6})([A-Z0-9]{3})")) {
				throw new BusinessException("El formato del rfc  es invalido, por favor ingrese un rfc valida.");

			}
		}

		if (rfc.length() == 12) {
			if (!rfc.trim().matches("[A-Z]{3}[0-9]{6}[A-Z0-9]{3}")) {
				throw new BusinessException("El formato del rfc es invalido, por favor ingrese un rfc valida.");

			}
		}

		if (rfc.length() < 12) {
			throw new BusinessException("El tamaño del rfc es invalido, por favor ingrese un rfc valida.");
		}

		if (aspiranteRepository.consultarAspiranteRFC(rfc)) {
			throw new BusinessException("El rfc que ha ingresado ya ha sido asignada, ingrese una nueva.");
		}

	}

	protected void validarAspiranteCurp(String curp) {
		curp = curp.toUpperCase().trim();

		if (ValidacionUtil.esCadenaVacia(curp)) {
			throw new BusinessException("Por favor envie una curp.");
		}
		if (!curp.matches("[A-Z]{4}[0-9]{6}[H,M][A-Z]{5}[0-9]{2}")) {
			throw new BusinessException("El formato de la curp  es invalido, por favor ingrese una curp valida.");
		}
		if (aspiranteRepository.existeAspiranteCurp(curp.trim().toUpperCase())) {
			throw new BusinessException("La curp que ha ingresado ya ha sido asignada, ingrese una nueva.");
		}

	}

	protected boolean validarRfcyIdAspirante(Integer idAspirante, String rfc) {
		rfc = rfc.toUpperCase().trim();

		if (ValidacionUtil.esCadenaVacia(rfc)) {

			throw new ReglaNegocioException("Por favor envie una rfc.", ReglaNegocioCodigoError.SIN_REGISTRO);
		}
		if (rfc.length() == 13) {
			if (!rfc.trim().matches("([A-Z]{4})([0-9]{6})([A-Z0-9]{3})")) {
				throw new ReglaNegocioException("El formato del rfc  es invalido, por favor ingrese un rfc valida.",
						ReglaNegocioCodigoError.SIN_REGISTRO);

			}
		}

		if (rfc.length() == 12) {
			if (!rfc.trim().matches("[A-Z]{3}[0-9]{6}[A-Z0-9]{3}")) {
				throw new ReglaNegocioException("El formato del rfc es invalido, por favor ingrese un rfc valida.",
						ReglaNegocioCodigoError.SIN_REGISTRO);

			}
		}

		if (rfc.length() < 12) {
			throw new ReglaNegocioException("El tamaño del rfc es invalido, por favor ingrese un rfc valida.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		return aspiranteRepository.consultarAspiranteRFCyId(idAspirante, rfc);

	}

	protected boolean validarCurpyIdAspirante(Integer idAspirante, String curp) {
		curp = curp.toUpperCase().trim();

		if (ValidacionUtil.esCadenaVacia(curp)) {
			throw new ReglaNegocioException("Por favor envie una curp.", ReglaNegocioCodigoError.SIN_REGISTRO);
		}
		if (!curp.matches("[A-Z]{4}[0-9]{6}[H,M][A-Z]{5}[0-9]{2}")) {
			throw new ReglaNegocioException("El formato de la curp  es invalido, por favor ingrese una curp valida.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		return aspiranteRepository.existeAspiranteCurpyId(idAspirante, curp);

	}

}
