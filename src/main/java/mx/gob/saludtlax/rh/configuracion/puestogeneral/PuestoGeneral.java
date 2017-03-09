package mx.gob.saludtlax.rh.configuracion.puestogeneral;

import java.util.List;

/**
 * 
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 21/07/2016 13:40:17
 */
public interface PuestoGeneral {

	/**
	 * Obtiene la lista de puesto generales registradas en la bd
	 * 
	 * @return
	 */
	
	public PuestoGeneralDTO puestoPorClave(String clave);
	
	public PuestoGeneralDTO puestoPorId(Integer id);
	
	public List<PuestoGeneralDTO> consultarListaPuestoGeneral();

	/**
	 * Crea un puesto general en la bd
	 * 
	 * @param puestoGeneralDTO
	 */
	public void crearPuestoGeneral(PuestoGeneralDTO puestoGeneralDTO);

	/**
	 * Actualiza el puesto general seleccionado
	 * 
	 * @param puestoGeneralDTO
	 */
	public void actualizarPuestoGeneral(PuestoGeneralDTO puestoGeneralDTO);

	/**
	 * Elimina el puesto general seleccionado
	 * 
	 * @param idPuestoGeneral
	 */
	public void eliminarPuestoGeneral(Integer idPuestoGeneral);

	/**
	 * Valida si existe el codigo en la bd
	 * @param codigo
	 * @return
	 */
	public Boolean existeCodigo(String codigo);
	
	public Boolean existeCodigoIdPuesto(Integer idPuestoGeneral, String codigo);

}
