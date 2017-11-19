/*
 *
 */

package mx.gob.saludtlax.rh.seguridad.usuario;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 04/11/2016 13:35:20
 */
public class InfoUsuarioDTO {
    private Integer idUsuario;
    private String nombre;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
