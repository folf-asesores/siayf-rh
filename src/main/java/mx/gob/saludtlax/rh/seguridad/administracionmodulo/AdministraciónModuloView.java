/**
 * 
 */
package mx.gob.saludtlax.rh.seguridad.administracionmodulo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.modulos.ModuloDTO;

/**
 * @author Eduardo Mex
 *
 */
public class AdministraciónModuloView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4298049848018177738L;
	
	private List<ModuloDTO> listaModulo = new ArrayList<>();
	
	/**********Getters and Setters************/

	/**
	 * @return the listaModulo
	 */
	public List<ModuloDTO> getListaModulo() {
		return listaModulo;
	}

	/**
	 * @param listaModulo the listaModulo to set
	 */
	public void setListaModulo(List<ModuloDTO> listaModulo) {
		this.listaModulo = listaModulo;
	}

}
