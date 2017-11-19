
package mx.gob.saludtlax.rh.nomina.movimientosnomina;

import java.io.Serializable;
import java.math.BigDecimal;

public class DetalleProgramacionMovimientoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6002681250220998470L;

    private Integer idDetalleProgramacionMovimiento;

    private Integer idProgramacionMovimiento;

    private Integer idPuesto;

    private String descripcionPuesto;

    private Integer idTipoContratacion;
    private String descripcionTipoContratacion;

    private BigDecimal importe;

    public Integer getIdDetalleProgramacionMovimiento() {
        return idDetalleProgramacionMovimiento;
    }

    public void setIdDetalleProgramacionMovimiento(Integer idDetalleProgramacionMovimiento) {
        this.idDetalleProgramacionMovimiento = idDetalleProgramacionMovimiento;
    }

    public Integer getIdProgramacionMovimiento() {
        return idProgramacionMovimiento;
    }

    public void setIdProgramacionMovimiento(Integer idProgramacionMovimiento) {
        this.idProgramacionMovimiento = idProgramacionMovimiento;
    }

    public Integer getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getDescripcionPuesto() {
        return descripcionPuesto;
    }

    public void setDescripcionPuesto(String descripcionPuesto) {
        this.descripcionPuesto = descripcionPuesto;
    }

    public Integer getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Integer idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public String getDescripcionTipoContratacion() {
        return descripcionTipoContratacion;
    }

    public void setDescripcionTipoContratacion(String descripcionTipoContratacion) {
        this.descripcionTipoContratacion = descripcionTipoContratacion;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

}
