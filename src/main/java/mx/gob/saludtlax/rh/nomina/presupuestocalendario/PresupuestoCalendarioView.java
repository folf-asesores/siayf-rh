/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.presupuestocalendario;

import java.io.Serializable;
import java.util.List;

/**
 * @author Eduardo Mex
 *
 */
public class PresupuestoCalendarioView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7181623592648906555L;

	private List<PresupuestoCalendarioDTO> listaPresupuestoCalendario;

	private PresupuestoCalendarioDTO creaPresupuestoCalendario;

	private PresupuestoCalendarioDTO actualizarPresupuestoCalendario;

	private boolean mostrarVistaPrincipal;

	private boolean mostrarVistaCrear;

	private boolean mostrarVistaActualizar;
	
	private Integer anioCriterio;

	/****************** Gettters and Setters **************/

	public List<PresupuestoCalendarioDTO> getListaPresupuestoCalendario() {
		return listaPresupuestoCalendario;
	}

	public void setListaPresupuestoCalendario(List<PresupuestoCalendarioDTO> listaPresupuestoCalendario) {
		this.listaPresupuestoCalendario = listaPresupuestoCalendario;
	}

	public PresupuestoCalendarioDTO getCreaPresupuestoCalendario() {
		return creaPresupuestoCalendario;
	}

	public void setCreaPresupuestoCalendario(PresupuestoCalendarioDTO creaPresupuestoCalendario) {
		this.creaPresupuestoCalendario = creaPresupuestoCalendario;
	}

	public PresupuestoCalendarioDTO getActualizarPresupuestoCalendario() {
		return actualizarPresupuestoCalendario;
	}

	public void setActualizarPresupuestoCalendario(PresupuestoCalendarioDTO actualizarPresupuestoCalendario) {
		this.actualizarPresupuestoCalendario = actualizarPresupuestoCalendario;
	}

	public boolean isMostrarVistaPrincipal() {
		return mostrarVistaPrincipal;
	}

	public void setMostrarVistaPrincipal(boolean mostrarVistaPrincipal) {
		this.mostrarVistaPrincipal = mostrarVistaPrincipal;
	}

	public boolean isMostrarVistaCrear() {
		return mostrarVistaCrear;
	}

	public void setMostrarVistaCrear(boolean mostrarVistaCrear) {
		this.mostrarVistaCrear = mostrarVistaCrear;
	}

	public boolean isMostrarVistaActualizar() {
		return mostrarVistaActualizar;
	}

	public void setMostrarVistaActualizar(boolean mostrarVistaActualizar) {
		this.mostrarVistaActualizar = mostrarVistaActualizar;
	}

	/**
	 * @return the anioCriterio
	 */
	public Integer getAnioCriterio() {
		return anioCriterio;
	}

	/**
	 * @param anioCriterio the anioCriterio to set
	 */
	public void setAnioCriterio(Integer anioCriterio) {
		this.anioCriterio = anioCriterio;
	}

}
