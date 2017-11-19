
package mx.gob.saludtlax.rh.configuracion.usuariosaprobaciones;

import java.util.List;

public class RegistrarUsuarioDTO {

    private Integer idAccion;
    private List<Integer> usuarios;

    public Integer getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(Integer idAccion) {
        this.idAccion = idAccion;
    }

    public List<Integer> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Integer> usuarios) {
        this.usuarios = usuarios;
    }

}
