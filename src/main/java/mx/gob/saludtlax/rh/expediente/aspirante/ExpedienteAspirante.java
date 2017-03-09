/**
 * 
 */
package mx.gob.saludtlax.rh.expediente.aspirante;


/**
 * @author eduardo
 *
 */
public interface ExpedienteAspirante {
	
	/**
	 * Apertura un nuevo expediente del aspirante
	 * @param expedienteDTO
	 */
	public void crearExpediente(ExpedienteAspiranteDTO expedienteDTO);
	
	/**
	 * Verifica si tiene un expediente aperturado
	 * @param idAspirante
	 * @return
	 */
	public boolean tieneExpedienteAperturado(Integer idAspirante);
	
	/**
	 * Regresa el numero de expediente del aspirante por identificador del aspirante
	 * @param idAspirante
	 * @return
	 */
	public String  numeroExpedienteAspirante(Integer idAspirante);
	
	/**
	 * Obtiene el id del expediente por identificador del aspirante 
	 * @param idAspirante
	 * @return
	 */
	public Integer obtenerIdExpedienteAspirante(Integer idAspirante);
	
	/**
	 * valida si el numero de expediente existe ya registrado en la bd
	 * @param numeroExpediente
	 * @return
	 */
	public boolean validarNumeroExpedienteAspirante(String numeroExpediente);

}
