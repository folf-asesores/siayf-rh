
package mx.gob.saludtlax.rh.configuracion.centroresponsabilidad;

/**
 *
 * @author José Pablo
 *
 */
public class CentroResponsabilidadDTO {
    private Integer idCentroResponsabilidad;
    private String clave;
    private String descripcion;

    public Integer getIdCentroResponsabilidad() {
        return idCentroResponsabilidad;
    }

    public void setIdCentroResponsabilidad(Integer idCentroResponsabilidad) {
        this.idCentroResponsabilidad = idCentroResponsabilidad;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
