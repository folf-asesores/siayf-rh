
package mx.gob.saludtlax.rh.nomina.movimientosnomina;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProgramarMovimientoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4972078020063455366L;

    private Integer idProgramacionMovimiento;

    private Integer idTipoMovimiento;

    private String movimiento;

    private Integer periodoAplicacion;

    private Integer tipoAplicacion;

    private BigDecimal importe;

    private String descripcion;
    private List<DetalleProgramacionMovimientoDTO> listaDetalles = new ArrayList<>();

    public Integer getIdProgramacionMovimiento() {
        return idProgramacionMovimiento;
    }

    public void setIdProgramacionMovimiento(Integer idProgramacionMovimiento) {
        this.idProgramacionMovimiento = idProgramacionMovimiento;
    }

    public Integer getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(Integer idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public Integer getPeriodoAplicacion() {
        return periodoAplicacion;
    }

    public void setPeriodoAplicacion(Integer periodoAplicacion) {
        this.periodoAplicacion = periodoAplicacion;
    }

    public Integer getTipoAplicacion() {
        return tipoAplicacion;
    }

    public void setTipoAplicacion(Integer tipoAplicacion) {
        this.tipoAplicacion = tipoAplicacion;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public List<DetalleProgramacionMovimientoDTO> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(
            List<DetalleProgramacionMovimientoDTO> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
