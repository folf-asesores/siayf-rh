
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

    public AccionDTO(Integer idAccion, String clave, String descripcion, Integer idArea, Integer idModulo, String nombreArea) {
        super();
        this.idAccion = idAccion;
        this.clave = clave;
        this.descripcion = descripcion;
        this.idArea = idArea;
        this.idModulo = idModulo;
        this.nombreArea = nombreArea;
    }

    public Integer getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(Integer idAccion) {
        this.idAccion = idAccion;
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

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    @Override
    public String toString() {
        return "AccionDTO{" + "idAccion=" + idAccion + ", clave=" + clave + ", descripcion=" + descripcion + ", idArea=" + idArea + ", idModulo=" + idModulo
                + ", nombreArea=" + nombreArea + '}';
    }

}
