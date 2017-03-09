package mx.gob.saludtlax.rh.nomina.primavacional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PrimaVacacionalResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5450228555013368432L;

	private BigDecimal total;
	private Integer antiguedadEmpleado;
	private Date fechaIngreso;
    private BigDecimal excento;
    private BigDecimal gravado;

	/**
	 * Regresa el total que se paga por concepto de prima vacacional
	 * 
	 * @return
	 */
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * Retorna los meses de antiguedad que tiene el empleado con respecto a su
	 * fecha de ingreso
	 * 
	 * @return
	 */
	public Integer getAntiguedadEmpleado() {
		return antiguedadEmpleado;
	}

	public void setAntiguedadEmpleado(Integer antiguedadEmpleado) {
		this.antiguedadEmpleado = antiguedadEmpleado;
	}

	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public BigDecimal getExcento() {
        return excento;
    }
    public void setExcento(BigDecimal excento) {
        this.excento = excento;
    }
    public BigDecimal getGravado() {
        return gravado;
    }
    public void setGravado(BigDecimal gravado) {
        this.gravado = gravado;
    }
    @Override
	public String toString() {
		return "Total: " + total + " \n Antiguedad Empleado: " + antiguedadEmpleado +" \n Fecha Ingreso: " + fechaIngreso.toString();
	}
}
