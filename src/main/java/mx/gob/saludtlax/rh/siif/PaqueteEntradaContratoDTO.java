package mx.gob.saludtlax.rh.siif;

import org.primefaces.model.UploadedFile;

public class PaqueteEntradaContratoDTO {
	private UploadedFile dat;
	private UploadedFile tra;
	private Integer idTipoNomina;
	private Integer idCuentaBancaria;
	private String periodoAfectacion;
	private Integer anioAfectacion;

	public UploadedFile getDat() {
		return dat;
	}
	public void setDat(UploadedFile dat) {
		this.dat = dat;
	}
	public UploadedFile getTra() {
		return tra;
	}
	public void setTra(UploadedFile tra) {
		this.tra = tra;
	}
	public Integer getIdTipoNomina() {
		return idTipoNomina;
	}
	public void setIdTipoNomina(Integer idTipoNomina) {
		this.idTipoNomina = idTipoNomina;
	}
	public Integer getIdCuentaBancaria() {
		return idCuentaBancaria;
	}
	public void setIdCuentaBancaria(Integer idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}
	public String getPeriodoAfectacion() {
		return periodoAfectacion;
	}
	public void setPeriodoAfectacion(String periodoAfectacion) {
		this.periodoAfectacion = periodoAfectacion;
	}
	public Integer getAnioAfectacion() {
		return anioAfectacion;
	}
	public void setAnioAfectacion(Integer anioAfectacion) {
		this.anioAfectacion = anioAfectacion;
	}
}