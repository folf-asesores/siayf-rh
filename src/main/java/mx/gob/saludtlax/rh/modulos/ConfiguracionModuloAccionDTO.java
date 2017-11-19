
package mx.gob.saludtlax.rh.modulos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.acciones.AccionDTO;

public class ConfiguracionModuloAccionDTO implements Serializable {

    private static final long serialVersionUID = -6726015902023365547L;

    private Integer idConfiguracionModuloAccion;
    private ModuloDTO modulo = new ModuloDTO();
    private String nombreConfiguracion;
    private List<AccionDTO> acciones = new ArrayList<>();

    public Integer getIdConfiguracionModuloAccion() {
        return idConfiguracionModuloAccion;
    }

    public void setIdConfiguracionModuloAccion(
            Integer idConfiguracionModuloAccion) {
        this.idConfiguracionModuloAccion = idConfiguracionModuloAccion;
    }

    public ModuloDTO getModulo() {
        return modulo;
    }

    public void setModulo(ModuloDTO modulo) {
        this.modulo = modulo;
    }

    public String getNombreConfiguracion() {
        return nombreConfiguracion;
    }

    public void setNombreConfiguracion(String nombreConfiguracion) {
        this.nombreConfiguracion = nombreConfiguracion;
    }

    public List<AccionDTO> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<AccionDTO> acciones) {
        this.acciones = acciones;
    }

}
