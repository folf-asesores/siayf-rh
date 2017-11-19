
package mx.gob.saludtlax.rh.perfiles;

import java.io.Serializable;
import java.util.Date;

import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;

public class ConfiguracionPerfilModuloDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8401921624552519698L;

    private Integer id_perfil_modulo;

    private PerfilDTO perfil;

    private ConfiguracionModuloAccionDTO configuracionModuloAccionDTO;

    private Date fechaCreacion;

    public Integer getId_perfil_modulo() {
        return id_perfil_modulo;
    }

    public void setId_perfil_modulo(Integer id_perfil_modulo) {
        this.id_perfil_modulo = id_perfil_modulo;
    }

    public PerfilDTO getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilDTO perfil) {
        this.perfil = perfil;
    }

    public ConfiguracionModuloAccionDTO getConfiguracionModuloAccionDTO() {
        return configuracionModuloAccionDTO;
    }

    public void setConfiguracionModuloAccionDTO(
            ConfiguracionModuloAccionDTO configuracionModuloAccionDTO) {
        this.configuracionModuloAccionDTO = configuracionModuloAccionDTO;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
