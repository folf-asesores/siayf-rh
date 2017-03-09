package mx.gob.saludtlax.rh.seguridad.usuario;

import java.util.List;

import mx.gob.saludtlax.rh.perfiles.PerfilDTO;

public class UsuarioView {

    private UsuarioDTO usuarioDTO = new UsuarioDTO();
    private UsuarioDTO editarUsuarioDTO = new UsuarioDTO();
    private UsuarioDTO seleccionarUsuario;
    private List<UsuarioDTO> usuarios;
    private List<PerfilDTO> perfiles;

    private boolean habilitarTabla;
    private boolean habilitarBotonesEdicion;
    private boolean habilitarBotonNuevoUsuario = true;
    private boolean habilitarPanelInicial = true;
    private boolean habilitarPanelBusqueda;
    private boolean habilitarPanelUsuario;
    private boolean habilitarFormulario;
    private boolean habilitarFormularioEdicion;

    public void habilitarEdicion() {
        habilitarTabla = true;
        habilitarBotonNuevoUsuario = false;
        habilitarBotonesEdicion = true;
        habilitarPanelUsuario = false;
    }

    public void cancelar() {
        habilitarTabla = false;
        habilitarBotonNuevoUsuario = true;
        habilitarBotonesEdicion = false;
        habilitarPanelUsuario = false;
        habilitarFormulario = false;
        habilitarPanelInicial = true;
    }

    public void habilitarFormulario() {
        habilitarTabla = false;
        habilitarBotonNuevoUsuario = false;
        habilitarBotonesEdicion = false;
        habilitarPanelUsuario = false;
        habilitarFormulario = true;
        habilitarPanelInicial = false;
    }

    public void habilitarFormularioEdicion() {
        habilitarTabla = false;
        habilitarBotonNuevoUsuario = false;
        habilitarBotonesEdicion = false;
        habilitarPanelUsuario = false;
        habilitarFormulario = false;
        habilitarFormularioEdicion = true;
        habilitarPanelInicial = false;
    }

    public void cancelarEdicion() {
        habilitarBotonNuevoUsuario = true;
        habilitarBotonesEdicion = false;
    }

    /* ************ Getters and Setters ************ */
    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public UsuarioDTO getEditarUsuarioDTO() {
        return editarUsuarioDTO;
    }

    public void setEditarUsuarioDTO(UsuarioDTO editarUsuarioDTO) {
        this.editarUsuarioDTO = editarUsuarioDTO;
    }

    public UsuarioDTO getSeleccionarUsuario() {
        return seleccionarUsuario;
    }

    public void setSeleccionarUsuario(UsuarioDTO seleccionarUsuario) {
        this.seleccionarUsuario = seleccionarUsuario;
    }

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean isHabilitarBotonesEdicion() {
        return habilitarBotonesEdicion;
    }

    public void setHabilitarBotonesEdicion(boolean habilitarBotonesEdicion) {
        this.habilitarBotonesEdicion = habilitarBotonesEdicion;
    }

    public boolean isHabilitarBotonNuevoUsuario() {
        return habilitarBotonNuevoUsuario;
    }

    public void setHabilitarBotonNuevoUsuario(boolean habilitarBotonNuevoUsuario) {
        this.habilitarBotonNuevoUsuario = habilitarBotonNuevoUsuario;
    }

    public boolean isHabilitarPanelInicial() {
        return habilitarPanelInicial;
    }

    public void setHabilitarPanelInicial(boolean habilitarPanelInicial) {
        this.habilitarPanelInicial = habilitarPanelInicial;
    }

    public boolean isHabilitarPanelBusqueda() {
        return habilitarPanelBusqueda;
    }

    public void setHabilitarPanelBusqueda(boolean habilitarPanelBusqueda) {
        this.habilitarPanelBusqueda = habilitarPanelBusqueda;
    }

    public List<PerfilDTO> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<PerfilDTO> perfiles) {
        this.perfiles = perfiles;
    }

    public boolean isHabilitarTabla() {
        return habilitarTabla;
    }

    public void setHabilitarTabla(boolean habilitarTabla) {
        this.habilitarTabla = habilitarTabla;
    }

    public boolean isHabilitarPanelUsuario() {
        return habilitarPanelUsuario;
    }

    public void setHabilitarPanelUsuario(boolean habilitarPanelUsuario) {
        this.habilitarPanelUsuario = habilitarPanelUsuario;
    }

    public boolean isHabilitarFormulario() {
        return habilitarFormulario;
    }

    public void setHabilitarFormulario(boolean habilitarFormulario) {
        this.habilitarFormulario = habilitarFormulario;
    }

    public boolean isHabilitarFormularioEdicion() {
        return habilitarFormularioEdicion;
    }

    public void setHabilitarFormularioEdicion(boolean habilitarFormularioEdicion) {
        this.habilitarFormularioEdicion = habilitarFormularioEdicion;
    }

}
