
package mx.gob.saludtlax.rh.seguridad.usuario;

import java.io.Serializable;
import java.util.Date;

import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;

public class ConfiguracionUsuarioModuloDTO implements Serializable {

    private static final long serialVersionUID = -1142728198588303592L;

    private Integer idConfiguracionUsuarioModulo;
    private UsuarioDTO usuario = new UsuarioDTO();
    private ConfiguracionModuloAccionDTO configuracionModulo = new ConfiguracionModuloAccionDTO();
    private Date fechaCreacion;

    public Integer getIdConfiguracionUsuarioModulo() {
        return idConfiguracionUsuarioModulo;
    }

    public void setIdConfiguracionUsuarioModulo(
            Integer idConfiguracionUsuarioModulo) {
        this.idConfiguracionUsuarioModulo = idConfiguracionUsuarioModulo;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public ConfiguracionModuloAccionDTO getConfiguracionModulo() {
        return configuracionModulo;
    }

    public void setConfiguracionModulo(
            ConfiguracionModuloAccionDTO configuracionModulo) {
        this.configuracionModulo = configuracionModulo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
