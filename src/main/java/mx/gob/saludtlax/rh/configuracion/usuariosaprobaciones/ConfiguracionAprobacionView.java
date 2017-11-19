/**
 * 
 */
package mx.gob.saludtlax.rh.configuracion.usuariosaprobaciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.seguridad.usuario.InfoUsuarioDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 04/11/2016 12:48:42
 */
public class ConfiguracionAprobacionView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5203838033131224051L;

	private List<SelectItem> listaOperaciones;
	private List<SelectItem> listaTipoMovimientoEmpleado;
	private List<SelectItem> listaUsuarioActivoItems;
	private List<UsuarioConfiguracionDTO> usuarios = new ArrayList<>();
	private List<InfoUsuarioDTO> usuariosActivos = new ArrayList<>();
	private List<InfoUsuarioDTO> usuariosSeleccionados = new ArrayList<>();
	private RegistrarUsuarioDTO registrarUsuario = new RegistrarUsuarioDTO();
	private ActualizacionConfiguracionAprobacionDTO actualizarConfiguracionAprobacion = new ActualizacionConfiguracionAprobacionDTO();

	private Integer idOperacion;
	private Integer idConfiguracionAprobacion;

	private String operacionSeleccionada;

	private boolean mostrarRegistro = false;
	private boolean panelPrincipal = true;
	private boolean panelActualizar = false;
	private boolean dialogEliminar = false;
	private Boolean aplicaMovimientos;
	
	public Boolean getPanelPrincipal() {
		return panelPrincipal;
	}

	public void setPanelPrincipal(Boolean panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}

	public Boolean getPanelActualizar() {
		return panelActualizar;
	}

	public void setPanelActualizar(Boolean panelActualizar) {
		this.panelActualizar = panelActualizar;
	}

	public String getOperacionSeleccionada() {
		return operacionSeleccionada;
	}

	public RegistrarUsuarioDTO getRegistrarUsuario() {
		return registrarUsuario;
	}

	public void setRegistrarUsuario(RegistrarUsuarioDTO registrarUsuario) {
		this.registrarUsuario = registrarUsuario;
	}

	public void setOperacionSeleccionada(String operacionSeleccionada) {
		this.operacionSeleccionada = operacionSeleccionada;
	}

	public List<InfoUsuarioDTO> getUsuariosSeleccionados() {
		return usuariosSeleccionados;
	}

	public void setUsuariosSeleccionados(List<InfoUsuarioDTO> usuariosSeleccionados) {
		this.usuariosSeleccionados = usuariosSeleccionados;
	}

	public boolean isMostrarRegistro() {
		return mostrarRegistro;
	}

	public void setMostrarRegistro(boolean mostrarRegistro) {
		this.mostrarRegistro = mostrarRegistro;
	}

	public List<InfoUsuarioDTO> getUsuariosActivos() {
		return usuariosActivos;
	}

	public void setUsuariosActivos(List<InfoUsuarioDTO> usuariosActivos) {
		this.usuariosActivos = usuariosActivos;
	}

	public Integer getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
	}

	public List<UsuarioConfiguracionDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioConfiguracionDTO> usuarios) {
		this.usuarios = usuarios;
	}

	public List<SelectItem> getListaOperaciones() {
		return listaOperaciones;
	}

	public void setListaOperaciones(List<SelectItem> listaOperaciones) {
		this.listaOperaciones = listaOperaciones;
	}

	/**
	 * @return the listaTipoMovimientoEmpleado
	 */
	public List<SelectItem> getListaTipoMovimientoEmpleado() {
		return listaTipoMovimientoEmpleado;
	}

	/**
	 * @param listaTipoMovimientoEmpleado
	 *            the listaTipoMovimientoEmpleado to set
	 */
	public void setListaTipoMovimientoEmpleado(List<SelectItem> listaTipoMovimientoEmpleado) {
		this.listaTipoMovimientoEmpleado = listaTipoMovimientoEmpleado;
	}

	/**
	 * @return the listaUsuarioActivoItems
	 */
	public List<SelectItem> getListaUsuarioActivoItems() {
		return listaUsuarioActivoItems;
	}

	/**
	 * @param listaUsuarioActivoItems
	 *            the listaUsuarioActivoItems to set
	 */
	public void setListaUsuarioActivoItems(List<SelectItem> listaUsuarioActivoItems) {
		this.listaUsuarioActivoItems = listaUsuarioActivoItems;
	}

	/**
	 * @return the actualizarConfiguracionAprobacion
	 */
	public ActualizacionConfiguracionAprobacionDTO getActualizarConfiguracionAprobacion() {
		return actualizarConfiguracionAprobacion;
	}

	/**
	 * @param actualizarConfiguracionAprobacion
	 *            the actualizarConfiguracionAprobacion to set
	 */
	public void setActualizarConfiguracionAprobacion(
			ActualizacionConfiguracionAprobacionDTO actualizarConfiguracionAprobacion) {
		this.actualizarConfiguracionAprobacion = actualizarConfiguracionAprobacion;
	}

	/**
	 * @return the idConfiguracionAprobacion
	 */
	public Integer getIdConfiguracionAprobacion() {
		return idConfiguracionAprobacion;
	}

	/**
	 * @param idConfiguracionAprobacion
	 *            the idConfiguracionAprobacion to set
	 */
	public void setIdConfiguracionAprobacion(Integer idConfiguracionAprobacion) {
		this.idConfiguracionAprobacion = idConfiguracionAprobacion;
	}

	/**
	 * @return the dialogEliminar
	 */
	public boolean isDialogEliminar() {
		return dialogEliminar;
	}

	/**
	 * @param dialogEliminar the dialogEliminar to set
	 */
	public void setDialogEliminar(boolean dialogEliminar) {
		this.dialogEliminar = dialogEliminar;
	}

	public Boolean getAplicaMovimientos() {
		return aplicaMovimientos;
	}

	public void setAplicaMovimientos(Boolean aplicaMovimientos) {
		this.aplicaMovimientos = aplicaMovimientos;
	}

}
