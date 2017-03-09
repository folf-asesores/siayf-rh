package mx.gob.saludtlax.rh.nomina.pensionalimenticia;

import java.io.Serializable;
import java.util.List;

public class InformacionEmpleadoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8279875525064650029L;

	private int idEmpleado;

	private String numeroEmpleado;

	private String rfc;

	private String curp;

	private String nombre;

	private List<BeneficiarioPensionAlimienticiaDTO> BeneficiarioRegistrados;

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public void setNumeroEmpleado(String numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<BeneficiarioPensionAlimienticiaDTO> getBeneficiarioRegistrados() {
		return BeneficiarioRegistrados;
	}

	public void setBeneficiarioRegistrados(List<BeneficiarioPensionAlimienticiaDTO> beneficiarioRegistrados) {
		BeneficiarioRegistrados = beneficiarioRegistrados;
	}
	
	

}
