
package mx.gob.saludtlax.rh.nomina.calculoisr;

import java.math.BigDecimal;

public class ConfiguracionIsrDTO {
    private BigDecimal baseGravable;
    private Integer idTipoPeriodo;
    private Integer idTabulador;
    private Integer idEmpleado;

    public BigDecimal getBaseGravable() {
        return baseGravable;
    }

    public void setBaseGravable(BigDecimal baseGravable) {
        this.baseGravable = baseGravable;
    }

    public Integer getIdTipoPeriodo() {
        return idTipoPeriodo;
    }

    public void setIdTipoPeriodo(Integer idTipoPeriodo) {
        this.idTipoPeriodo = idTipoPeriodo;
    }

    public Integer getIdTabulador() {
        return idTabulador;
    }

    public void setIdTabulador(Integer idTabulador) {
        this.idTabulador = idTabulador;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}