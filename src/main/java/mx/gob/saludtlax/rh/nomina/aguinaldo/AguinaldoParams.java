
package mx.gob.saludtlax.rh.nomina.aguinaldo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AguinaldoParams implements Serializable {
    private static final long serialVersionUID = -7372217042352578473L;
    private Integer idEmpleado;
    private BigDecimal diasPagar;
    private BigDecimal baseAguinaldo;
    private boolean calculoFiniquito;
    private Date fechaCalculo;
    private BigDecimal diasExentoPagar;

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public BigDecimal getDiasPagar() {
        return diasPagar;
    }

    public void setDiasPagar(BigDecimal diasPagar) {
        this.diasPagar = diasPagar;
    }

    public BigDecimal getBaseAguinaldo() {
        return baseAguinaldo;
    }

    public void setBaseAguinaldo(BigDecimal baseAguinaldo) {
        this.baseAguinaldo = baseAguinaldo;
    }

    public boolean isCalculoFiniquito() {
        return calculoFiniquito;
    }

    public void setCalculoFiniquito(boolean calculoFiniquito) {
        this.calculoFiniquito = calculoFiniquito;
    }

    public Date getFechaCalculo() {
        return fechaCalculo;
    }

    public void setFechaCalculo(Date fechaCalculo) {
        this.fechaCalculo = fechaCalculo;
    }

    public BigDecimal getDiasExentoPagar() {
        return diasExentoPagar;
    }

    public void setDiasExentoPagar(BigDecimal diasExentoPagar) {
        this.diasExentoPagar = diasExentoPagar;
    }

}