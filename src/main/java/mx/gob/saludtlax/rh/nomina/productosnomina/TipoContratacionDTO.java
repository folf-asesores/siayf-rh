
package mx.gob.saludtlax.rh.nomina.productosnomina;

public class TipoContratacionDTO {
    private Integer idTipoContratacion;
    private String tipoContratacion;
    private String codigo;
    private Integer areaResponsable;
    private boolean inventario;

    public Integer getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Integer idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public String getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getAreaResponsable() {
        return areaResponsable;
    }

    public void setAreaResponsable(Integer areaResponsable) {
        this.areaResponsable = areaResponsable;
    }

    public boolean isInventario() {
        return inventario;
    }

    public void setInventario(boolean inventario) {
        this.inventario = inventario;
    }
}