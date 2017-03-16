/**
 * 
 */
package mx.gob.saludtlax.rh.puestosautorizados;

/**
 * @author
 *
 */
public class EstructuraNominaDTO {
	private Integer idPuesto;
	private Integer idUsuario;
	private String subfuncion;
	private String tabuladorPuesto;
	private String pagaduria;
	private String indicadorMando;
	private String tipoUnidad;
	private String tipoPago;
	private String financiamiento;
	private String jornadaTrabajo;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getSubfuncion() {
		return subfuncion;
	}

	public void setSubfuncion(String subfuncion) {
		this.subfuncion = subfuncion;
	}

	public String getTabuladorPuesto() {
		return tabuladorPuesto;
	}

	public void setTabuladorPuesto(String tabuladorPuesto) {
		this.tabuladorPuesto = tabuladorPuesto;
	}

	public String getPagaduria() {
		return pagaduria;
	}

	public void setPagaduria(String pagaduria) {
		this.pagaduria = pagaduria;
	}

	public String getIndicadorMando() {
		return indicadorMando;
	}

	public void setIndicadorMando(String indicadorMando) {
		this.indicadorMando = indicadorMando;
	}

	public String getTipoUnidad() {
		return tipoUnidad;
	}

	public void setTipoUnidad(String tipoUnidad) {
		this.tipoUnidad = tipoUnidad;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getFinanciamiento() {
		return financiamiento;
	}

	public void setFinanciamiento(String financiamiento) {
		this.financiamiento = financiamiento;
	}

	public String getJornadaTrabajo() {
		return jornadaTrabajo;
	}

	public void setJornadaTrabajo(String jornadaTrabajo) {
		this.jornadaTrabajo = jornadaTrabajo;
	}

}
