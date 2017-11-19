/*
 *
 */

package mx.gob.saludtlax.rh.nomina.productosnomina;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 30/11/2016 15:47:54
 */
public class NominaErroneaDTO {
    private Integer idNominaEmpleado;
    private String rfc;
    private String empleado;
    private String motivo;

    public NominaErroneaDTO(Integer idNominaEmpleado, String rfc, String empleado, String motivo) {
        this.rfc = rfc;
        this.idNominaEmpleado = idNominaEmpleado;
        this.empleado = empleado;
        this.motivo = motivo;
    }

    public NominaErroneaDTO() {
    }

    public Integer getIdNominaEmpleado() {
        return idNominaEmpleado;
    }

    public void setIdNominaEmpleado(Integer idNominaEmpleado) {
        this.idNominaEmpleado = idNominaEmpleado;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

}
