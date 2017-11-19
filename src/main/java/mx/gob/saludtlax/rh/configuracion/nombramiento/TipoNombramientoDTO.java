
package mx.gob.saludtlax.rh.configuracion.nombramiento;

public class TipoNombramientoDTO {
    private Integer idTipoNombramiento;
    private String nombramiento;
    private String idPoder;
    private String descripcion;
    private String clave;

    public String getNombramiento() {
        return nombramiento;
    }

    public Integer getIdTipoNombramiento() {
        return idTipoNombramiento;
    }

    public void setIdTipoNombramiento(Integer idTipoNombramiento) {
        this.idTipoNombramiento = idTipoNombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    public String getIdPoder() {
        return idPoder;
    }

    public void setIdPoder(String id_poder) {
        idPoder = id_poder;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
