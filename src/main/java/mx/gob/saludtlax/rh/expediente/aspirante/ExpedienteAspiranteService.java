/**
 * 
 */
package mx.gob.saludtlax.rh.expediente.aspirante;

import java.io.Serializable;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.ExpedienteAspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.ExpedienteAspiranteRepository;

/**
 * @author eduardo
 *
 */
public class ExpedienteAspiranteService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9151388144530782590L;

	@Inject
	private AspiranteRepository aspiranteRepository;
	@Inject
	private ExpedienteAspiranteRepository expedienteAspiranteRepository;

	protected void crearExpediente(ExpedienteAspiranteDTO dto) {
		String contexto = "Apertura expediente: ";

		if (!aspiranteRepository.existeIdAspirante(dto.getIdAspirante())) {
			throw new BusinessException(
					contexto + "El identificador enviado no corresponde a un aspirante, seleccione otra opcion.");
		}

		if (expedienteAspiranteRepository.existeExpedienteAsignadoAspirante(dto.getIdAspirante())) {
			throw new BusinessException(contexto + "El aspirante ya tiene aperturado un expediente.");
		}

		if (expedienteAspiranteRepository.existeNumeroExpediente(dto.getNumeroExpediente())) {
			throw new BusinessException(
					contexto + "El numero de expediente ya ha sido asignado, ingrese un nuevo número.");
		}

		ExpedienteAspiranteEntity expediente = new ExpedienteAspiranteEntity();
		expediente.setIdAspirante(dto.getIdAspirante());
		;
		expediente.setNumeroExpediente(dto.getNumeroExpediente().trim().toUpperCase());
		// Creando expediente del aspirante
		expedienteAspiranteRepository.crear(expediente);

	}

	protected boolean tieneExpedienteAperturado(Integer idAspirante) {
		return expedienteAspiranteRepository.existeExpedienteAsignadoAspirante(idAspirante);
	}

	protected String numeroExpedienteAspirante(Integer idAspirante) {

		return expedienteAspiranteRepository.numeroExpedienteAspirante(idAspirante);
	}

	protected Integer obtenerIdExpedienteAspirante(Integer idAspirante) {
		return expedienteAspiranteRepository.obtenerIdExpedienteAspirante(idAspirante);
	}

	protected boolean validarNumeroExpedienteAspirante(String numeroExpediente) {

		String contexto = "Apertura expediente: ";
		boolean resultado = false;

		if (expedienteAspiranteRepository.existeNumeroExpediente(numeroExpediente)) {
			resultado = true;
			throw new BusinessException(
					contexto + "El numero de expediente ya ha sido asignado, ingrese un nuevo número.");

		}

		return resultado;
	}

}
