package mx.gob.saludtlax.rh.nomina.consultamovimientos;
import java.math.BigDecimal;
import java.sql.Date;

public class MovimientosNominaEmpleadoDTO {
	
	private Integer idMovimientoFijo;
	private Integer terceroInstitucional;
	private Integer idEmpleado ;
	private String rfc;
	private Integer quincenaOperacion;
	private Integer anyoOperacion;
	private BigDecimal importeDescuento;
	private Integer quincenaInicial;
	private Integer anioInicial;
	private Integer quincenaFinal;
	private Integer anioFinal;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private String folioDocumento;
	private Date fechaDocumento;
	private Integer idTipoMovimiento;
	private Integer dias ;
	
	public Integer getIdMovimientoFijo() {
		return idMovimientoFijo;
	}
	public void setIdMovimientoFijo(Integer idMovimientoFijo) {
		this.idMovimientoFijo = idMovimientoFijo;
	}
	public Integer getTerceroInstitucional() {
		return terceroInstitucional;
	}
	public void setTerceroInstitucional(Integer terceroInstitucional) {
		this.terceroInstitucional = terceroInstitucional;
	}
	public Integer getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public Integer getQuincenaOperacion() {
		return quincenaOperacion;
	}
	public void setQuincenaOperacion(Integer quincenaOperacion) {
		this.quincenaOperacion = quincenaOperacion;
	}
	public Integer getAnyoOperacion() {
		return anyoOperacion;
	}
	public void setAnyoOperacion(Integer anyoOperacion) {
		this.anyoOperacion = anyoOperacion;
	}
	public BigDecimal getImporteDescuento() {
		return importeDescuento;
	}
	public void setImporteDescuento(BigDecimal importeDescuento) {
		this.importeDescuento = importeDescuento;
	}
	public Integer getQuincenaInicial() {
		return quincenaInicial;
	}
	public void setQuincenaInicial(Integer quincenaInicial) {
		this.quincenaInicial = quincenaInicial;
	}
	public Integer getAnioInicial() {
		return anioInicial;
	}
	public void setAnioInicial(Integer anioInicial) {
		this.anioInicial = anioInicial;
	}
	public Integer getQuincenaFinal() {
		return quincenaFinal;
	}
	public void setQuincenaFinal(Integer quincenaFinal) {
		this.quincenaFinal = quincenaFinal;
	}
	public Integer getAnioFinal() {
		return anioFinal;
	}
	public void setAnioFinal(Integer anioFinal) {
		this.anioFinal = anioFinal;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getFolioDocumento() {
		return folioDocumento;
	}
	public void setFolioDocumento(String folioDocumento) {
		this.folioDocumento = folioDocumento;
	}
	public Date getFechaDocumento() {
		return fechaDocumento;
	}
	public void setFechaDocumento(Date fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}
	public Integer getIdTipoMovimiento() {
		return idTipoMovimiento;
	}
	public void setIdTipoMovimiento(Integer idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}
	public Integer getDias() {
		return dias;
	}
	public void setDias(Integer dias) {
		this.dias = dias;
	}

}
