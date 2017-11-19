
package mx.gob.saludtlax.rh.perfiles;

import java.io.Serializable;

public class PerfilDTO implements Serializable {

    private static final long serialVersionUID = -5446595931417700758L;

    private Integer idPerfil;
    private String nombre;
    private String descripcion;

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}