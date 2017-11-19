/*
 *
 */

package mx.gob.saludtlax.rh.expediente.empleado;

/**
 * @author Leila Schiaffini Ehuan
 * @since 13/06/2016 11:10:09
 *
 */
public class ExpedienteDTO {

    private Integer idEmpleado;
    private String numeroExpediente;

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

}
