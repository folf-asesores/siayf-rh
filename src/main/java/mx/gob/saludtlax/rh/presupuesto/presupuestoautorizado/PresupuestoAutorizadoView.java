package mx.gob.saludtlax.rh.presupuesto.presupuestoautorizado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author José Pablo
 *
 */
public class PresupuestoAutorizadoView  implements Serializable{
	private static final long serialVersionUID = 7019453037498661233L;

	private List<PresupuestoAutorizadoDTO> listaPresupuestoAutorizado = new ArrayList<>();

	private PresupuestoAutorizadoDTO creaPresupuestoAutorizado = new PresupuestoAutorizadoDTO();

	private PresupuestoAutorizadoDTO actualizarPresupuestoAutorizado = new PresupuestoAutorizadoDTO();

	private Integer idPresupuestoAutorizadoSeleccionado;
	
	private boolean panelPrincipal = true;
	private boolean panelCrear = false;
	private boolean panelActualizar;
	private boolean dialogEliminar = false;
	
	
	
	public List<PresupuestoAutorizadoDTO> getListaPresupuestoAutorizado() {
		return listaPresupuestoAutorizado;
	}
	public void setListaPresupuestoAutorizado(List<PresupuestoAutorizadoDTO> listaPresupuestoAutorizado) {
		this.listaPresupuestoAutorizado = listaPresupuestoAutorizado;
	}
	public PresupuestoAutorizadoDTO getCreaPresupuestoAutorizado() {
		return creaPresupuestoAutorizado;
	}
	public void setCreaPresupuestoAutorizado(PresupuestoAutorizadoDTO creaPresupuestoAutorizado) {
		this.creaPresupuestoAutorizado = creaPresupuestoAutorizado;
	}
	public PresupuestoAutorizadoDTO getActualizarPresupuestoAutorizado() {
		return actualizarPresupuestoAutorizado;
	}
	public void setActualizarPresupuestoAutorizado(PresupuestoAutorizadoDTO actualizarPresupuestoAutorizado) {
		this.actualizarPresupuestoAutorizado = actualizarPresupuestoAutorizado;
	}
	public Integer getIdPresupuestoAutorizadoSeleccionado() {
		return idPresupuestoAutorizadoSeleccionado;
	}
	public void setIdPresupuestoAutorizadoSeleccionado(Integer idPresupuestoAutorizadoSeleccionado) {
		this.idPresupuestoAutorizadoSeleccionado = idPresupuestoAutorizadoSeleccionado;
	}
	public boolean isPanelPrincipal() {
		return panelPrincipal;
	}
	public void setPanelPrincipal(boolean panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}
	public boolean isPanelCrear() {
		return panelCrear;
	}
	public void setPanelCrear(boolean panelCrear) {
		this.panelCrear = panelCrear;
	}
	public boolean isPanelActualizar() {
		return panelActualizar;
	}
	public void setPanelActualizar(boolean panelActualizar) {
		this.panelActualizar = panelActualizar;
	}
	public boolean isDialogEliminar() {
		return dialogEliminar;
	}
	public void setDialogEliminar(boolean dialogEliminar) {
		this.dialogEliminar = dialogEliminar;
	}
	
	
}
