package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.Serializable;
import java.util.Date;

public class ProductoNominaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8762112277485838714L;

	private int idProductoNomina;

	private String tipoPeriodo;

	private Date inicioPeriodo;

	private Date finPeriodo;

	private String tipoNomina;

	private String ejercicioFiscal;

	private String tipoContrato;

	private Integer numeroPeriodo;

	private String nombreProducto;

	

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getIdProductoNomina() {
		return idProductoNomina;
	}

	public void setIdProductoNomina(int idProductoNomina) {
		this.idProductoNomina = idProductoNomina;
	}

	public String getTipoPeriodo() {
		return tipoPeriodo;
	}

	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	public Date getInicioPeriodo() {
		return inicioPeriodo;
	}

	public void setInicioPeriodo(Date inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}

	public Date getFinPeriodo() {
		return finPeriodo;
	}

	public void setFinPeriodo(Date finPeriodo) {
		this.finPeriodo = finPeriodo;
	}

	public String getTipoNomina() {
		return tipoNomina;
	}

	public void setTipoNomina(String tipoNomina) {
		this.tipoNomina = tipoNomina;
	}

	public String getEjercicioFiscal() {
		return ejercicioFiscal;
	}

	public void setEjercicioFiscal(String ejercicioFiscal) {
		this.ejercicioFiscal = ejercicioFiscal;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public Integer getNumeroPeriodo() {
		return numeroPeriodo;
	}

	public void setNumeroPeriodo(Integer numeroPeriodo) {
		this.numeroPeriodo = numeroPeriodo;
	}

}
