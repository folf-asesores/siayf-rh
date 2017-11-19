
package mx.gob.saludtlax.rh.seguridad.usuario;

import java.io.Serializable;

public class VistaUsuarioDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8769181450014386622L;

    private Integer id_configuracion_usuario_modulo;
    private UsuarioDTO usuario = new UsuarioDTO();
    private ConfiguracionUsuarioModuloDTO configuracionUsuario = new ConfiguracionUsuarioModuloDTO();

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public Integer getId_configuracion_usuario_modulo() {
        return id_configuracion_usuario_modulo;
    }

    public void setId_configuracion_usuario_modulo(
            Integer id_configuracion_usuario_modulo) {
        this.id_configuracion_usuario_modulo = id_configuracion_usuario_modulo;
    }

    public ConfiguracionUsuarioModuloDTO getConfiguracionUsuario() {
        return configuracionUsuario;
    }

    public void setConfiguracionUsuario(
            ConfiguracionUsuarioModuloDTO configuracionUsuario) {
        this.configuracionUsuario = configuracionUsuario;
    }

}
