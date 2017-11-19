
package mx.gob.saludtlax.rh.configuracion.usuariosaprobaciones;

import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;

public class AutorizacionesView {

    private RegistrarUsuarioDTO autorizacion = new RegistrarUsuarioDTO();
    public Integer des;

    private List<UsuarioConfiguracionDTO> listaAutorizados;
    private List<UsuarioConfiguracionDTO> autorizadosSelect;
    private List<UsuarioConfiguracionDTO> selectAutorizados;
    private UsuarioConfiguracionDTO autorizados;

    private List<SelectItem> listAutorizaciones;
    private List<UsuarioDTO> listaUsuarios;
    private List<UsuarioDTO> usuariosLista;

    private Boolean panelPrincipal;
    private Boolean panelCrear;
    private Boolean panelActualizar;
    private Boolean panelEliminar;
    private Boolean panelAccion;
    private Boolean dialogoEliminar = Boolean.FALSE;
    private Boolean dialogoActivo = Boolean.FALSE;

    public void panelPrincipal() {
        panelPrincipal = Boolean.TRUE;
        panelCrear = Boolean.FALSE;
        panelActualizar = Boolean.FALSE;
        panelEliminar = Boolean.FALSE;
        panelAccion = Boolean.FALSE;
    }

    public void panelCrear() {
        panelPrincipal = Boolean.FALSE;
        panelCrear = Boolean.TRUE;
        panelActualizar = Boolean.FALSE;
        panelEliminar = Boolean.FALSE;
    }

    public void panelActualizar() {
        panelPrincipal = Boolean.FALSE;
        panelCrear = Boolean.FALSE;
        panelActualizar = Boolean.TRUE;
        panelEliminar = Boolean.FALSE;
        panelAccion = Boolean.FALSE;
        autorizadosSelect = null;
    }

    public void panelEliminar() {
        panelPrincipal = Boolean.FALSE;
        panelCrear = Boolean.FALSE;
        panelActualizar = Boolean.FALSE;
        panelEliminar = Boolean.TRUE;
        panelAccion = Boolean.FALSE;
        autorizadosSelect = null;
    }

    public void panelAccion() {
        panelCrear = Boolean.FALSE;
        panelActualizar = Boolean.FALSE;
        panelEliminar = Boolean.TRUE;
        panelAccion = Boolean.TRUE;
        autorizadosSelect = null;
    }

    public RegistrarUsuarioDTO getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(RegistrarUsuarioDTO autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Integer getDes() {
        return des;
    }

    public void setDes(Integer des) {
        this.des = des;
    }

    public List<UsuarioConfiguracionDTO> getListaAutorizados() {
        return listaAutorizados;
    }

    public void setListaAutorizados(List<UsuarioConfiguracionDTO> listaAutorizados) {
        this.listaAutorizados = listaAutorizados;
    }

    public UsuarioConfiguracionDTO getAutorizados() {
        return autorizados;
    }

    public void setAutorizados(UsuarioConfiguracionDTO autorizados) {
        this.autorizados = autorizados;
    }

    public List<UsuarioConfiguracionDTO> getAutorizadosSelect() {
        return autorizadosSelect;
    }

    public void setAutorizadosSelect(List<UsuarioConfiguracionDTO> autorizadosSelect) {
        this.autorizadosSelect = autorizadosSelect;
    }

    public List<SelectItem> getListAutorizaciones() {
        return listAutorizaciones;
    }

    public void setListAutorizaciones(List<SelectItem> listAutorizaciones) {
        this.listAutorizaciones = listAutorizaciones;
    }

    public List<UsuarioDTO> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioDTO> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<UsuarioDTO> getUsuariosLista() {
        return usuariosLista;
    }

    public void setUsuariosLista(List<UsuarioDTO> usuariosLista) {
        this.usuariosLista = usuariosLista;
    }

    public Boolean getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(Boolean panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public Boolean getPanelCrear() {
        return panelCrear;
    }

    public void setPanelCrear(Boolean panelCrear) {
        this.panelCrear = panelCrear;
    }

    public Boolean getPanelActualizar() {
        return panelActualizar;
    }

    public void setPanelActualizar(Boolean panelActualizar) {
        this.panelActualizar = panelActualizar;
    }

    public Boolean getPanelEliminar() {
        return panelEliminar;
    }

    public void setPanelEliminar(Boolean panelEliminar) {
        this.panelEliminar = panelEliminar;
    }

    public Boolean getDialogoEliminar() {
        return dialogoEliminar;
    }

    public void setDialogoEliminar(Boolean dialogoEliminar) {
        this.dialogoEliminar = dialogoEliminar;
    }

    public Boolean getDialogoActivo() {
        return dialogoActivo;
    }

    public void setDialogoActivo(Boolean dialogoActivo) {
        this.dialogoActivo = dialogoActivo;
    }

    public Boolean getPanelAccion() {
        return panelAccion;
    }

    public void setPanelAccion(Boolean panelAccion) {
        this.panelAccion = panelAccion;
    }

    public List<UsuarioConfiguracionDTO> getSelectAutorizados() {
        return selectAutorizados;
    }

    public void setSelectAutorizados(List<UsuarioConfiguracionDTO> selectAutorizados) {
        this.selectAutorizados = selectAutorizados;
    }

}
