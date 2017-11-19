
package mx.gob.saludtlax.rh.catalogos.areasadscripcion;

public class AreaAdscripcionDTO {

    private Integer idAreaAdscripcion;
    private String clave;
    private String areaAdscripcion;
    private String clues;
    private String centros_pago;

    public Integer getIdAreaAdscripcion() {
        return idAreaAdscripcion;
    }

    public void setIdAreaAdscripcion(Integer idAreaAdscripcion) {
        this.idAreaAdscripcion = idAreaAdscripcion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getAreaAdscripcion() {
        return areaAdscripcion;
    }

    public void setAreaAdscripcion(String areaAdscripcion) {
        this.areaAdscripcion = areaAdscripcion;
    }

    public String getClues() {
        return clues;
    }

    public void setClues(String clues) {
        this.clues = clues;
    }

    public String getCentros_pago() {
        return centros_pago;
    }

    public void setCentros_pago(String centros_pago) {
        this.centros_pago = centros_pago;
    }

}
