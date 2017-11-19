
package mx.gob.saludtlax.rh.acciones;

import java.io.Serializable;

public class AccionDTO implements Serializable {

    private static final long serialVersionUID = 1268725915946611463L;

    private Integer idAccion;
    private String clave;
    private String descripcion;
    private Integer idArea;
    private Integer idModulo;
    private String nombreArea;

    public AccionDTO() {
    }

    public AccionDTO(Integer idAccion, String clave, String descripcion,
            Integer idArea, Integer idModulo, String nombreArea) {
        this.idAccion = idAccion;
        this.clave = clave;
        this.descripcion = descripcion;
        this.idArea = idArea;
        this.idModulo = idModulo;
        this.nombreArea = nombreArea;
    }

    public Integer getIdAccion() {
        return this.idAccion;
    }

    public void setIdAccion(Integer idAccion) {
        this.idAccion = idAccion;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdArea() {
        return this.idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public Integer getIdModulo() {
        return this.idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombreArea() {
        return this.nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    @Override
    public String toString() {
        return "AccionDTO{" + "idAccion=" + this.idAccion + ", clave="
                + this.clave + ", descripcion=" + this.descripcion + ", idArea="
                + this.idArea + ", idModulo=" + this.idModulo + ", nombreArea="
                + this.nombreArea + '}';
    }

}
