
package mx.gob.saludtlax.rh.seguridad.usuario;

import java.util.List;

public class VistaUsuarioView {

    private List<UsuarioDTO> usuarios;
    private UsuarioDTO seleccionarUsuario;
    private boolean principal;
    private boolean acciones = false;
    private boolean nueva;
    private UsuarioDTO usuarioSeleccionado = new UsuarioDTO();

    private boolean tabla;

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }

    public UsuarioDTO getSeleccionarUsuario() {
        return seleccionarUsuario;
    }

    public void setSeleccionarUsuario(UsuarioDTO seleccionarUsuario) {
        this.seleccionarUsuario = seleccionarUsuario;
    }

    public Boolean getAcciones() {
        return acciones;
    }

    public void setAcciones(Boolean acciones) {
        this.acciones = acciones;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public UsuarioDTO getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(UsuarioDTO usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public boolean isNueva() {
        return nueva;
    }

    public void setNueva(boolean nueva) {
        this.nueva = nueva;
    }

    public boolean isTabla() {
        return tabla;
    }

    public void setTabla(boolean tabla) {
        this.tabla = tabla;
    }

}
