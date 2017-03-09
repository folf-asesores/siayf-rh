/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia;

import java.math.BigDecimal;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 17/11/2016 20:43:48
 */
public class DescuentoSuplenciaDTO {
	private Integer idDetalleSuplencia;
	private BigDecimal importeADescontar;
	private Integer idUsuarioLogeado;
	private String motivoDescuento;
	private boolean regresarImporteAnterior;

	public boolean isRegresarImporteAnterior() {
		return regresarImporteAnterior;
	}

	public void setRegresarImporteAnterior(boolean regresarImporteAnterior) {
		this.regresarImporteAnterior = regresarImporteAnterior;
	}

	public String getMotivoDescuento() {
		return motivoDescuento;
	}

	public void setMotivoDescuento(String motivoDescuento) {
		this.motivoDescuento = motivoDescuento;
	}

	public Integer getIdDetalleSuplencia() {
		return idDetalleSuplencia;
	}

	public void setIdDetalleSuplencia(Integer idDetalleSuplencia) {
		this.idDetalleSuplencia = idDetalleSuplencia;
	}

	public BigDecimal getImporteADescontar() {
		return importeADescontar;
	}

	public void setImporteADescontar(BigDecimal importeADescontar) {
		this.importeADescontar = importeADescontar;
	}

	public Integer getIdUsuarioLogeado() {
		return idUsuarioLogeado;
	}

	public void setIdUsuarioLogeado(Integer idUsuarioLogeado) {
		this.idUsuarioLogeado = idUsuarioLogeado;
	}

}
