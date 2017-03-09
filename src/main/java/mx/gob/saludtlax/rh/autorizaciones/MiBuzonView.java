/**
 * 
 */
package mx.gob.saludtlax.rh.autorizaciones;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 * @since 11/08/2016 11:57:46
 * 
 */
public class MiBuzonView {

	private List<BuzonAutorizacionDTO> misNotificaciones = new ArrayList<BuzonAutorizacionDTO>();
	private DetalleAutorizacionDTO detalleAutorizacion = new DetalleAutorizacionDTO();
	private AutorizacionDTO autorizacion = new AutorizacionDTO();

	private boolean mostrarAutorizacion;
	private boolean mostrarDetalleAperturaVacante;
	private boolean mostrarDetalleAperturaVacantePrograma;
	private boolean mostrarDetalleLaboralPrograma;
	private boolean mostrarDetalleMovimiento;
	private boolean mostrarDetalleSuplencia;
	private Integer idAccion;
	private Integer idUsuarioLogeado;

	public boolean isMostrarDetalleSuplencia() {
		return mostrarDetalleSuplencia;
	}

	public void setMostrarDetalleSuplencia(boolean mostrarDetalleSuplencia) {
		this.mostrarDetalleSuplencia = mostrarDetalleSuplencia;
	}

	public boolean isMostrarDetalleMovimiento() {
		return mostrarDetalleMovimiento;
	}

	public void setMostrarDetalleMovimiento(boolean mostrarDetalleMovimiento) {
		this.mostrarDetalleMovimiento = mostrarDetalleMovimiento;
	}

	public boolean isMostrarDetalleLaboralPrograma() {
		return mostrarDetalleLaboralPrograma;
	}

	public void setMostrarDetalleLaboralPrograma(boolean mostrarDetalleLaboralPrograma) {
		this.mostrarDetalleLaboralPrograma = mostrarDetalleLaboralPrograma;
	}

	public boolean isMostrarAutorizacion() {
		return mostrarAutorizacion;
	}

	public void setMostrarAutorizacion(boolean mostrarAutorizacion) {
		this.mostrarAutorizacion = mostrarAutorizacion;
	}

	public boolean isMostrarDetalleAperturaVacantePrograma() {
		return mostrarDetalleAperturaVacantePrograma;
	}

	public void setMostrarDetalleAperturaVacantePrograma(boolean mostrarDetalleAperturaVacantePrograma) {
		this.mostrarDetalleAperturaVacantePrograma = mostrarDetalleAperturaVacantePrograma;
	}

	public Integer getIdUsuarioLogeado() {
		return idUsuarioLogeado;
	}

	public void setIdUsuarioLogeado(Integer idUsuarioLogeado) {
		this.idUsuarioLogeado = idUsuarioLogeado;
	}

	public Integer getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(Integer idAccion) {
		this.idAccion = idAccion;
	}

	public AutorizacionDTO getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(AutorizacionDTO autorizacion) {
		this.autorizacion = autorizacion;
	}

	public DetalleAutorizacionDTO getDetalleAutorizacion() {
		return detalleAutorizacion;
	}

	public void setDetalleAutorizacion(DetalleAutorizacionDTO detalleAutorizacion) {
		this.detalleAutorizacion = detalleAutorizacion;
	}

	public boolean isMostrarDetalleAperturaVacante() {
		return mostrarDetalleAperturaVacante;
	}

	public void setMostrarDetalleAperturaVacante(boolean mostrarDetalleAperturaVacante) {
		this.mostrarDetalleAperturaVacante = mostrarDetalleAperturaVacante;
	}

	public List<BuzonAutorizacionDTO> getMisNotificaciones() {
		return misNotificaciones;
	}

	public void setMisNotificaciones(List<BuzonAutorizacionDTO> misNotificaciones) {
		this.misNotificaciones = misNotificaciones;
	}

}
