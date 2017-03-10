package mx.gob.saludtlax.rh.puestosautorizados;

public class EstructuraContratoDTO {
	private Integer idPuesto;
	private Integer idUsuario;
	private String funcion ="";
	private String subfuncion ="";
	private String jornada ="";
	private String financiamiento ="";

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getFinanciamiento() {
		return financiamiento;
	}

	public void setFinanciamiento(String financiamiento) {
		this.financiamiento = financiamiento;
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getSubfuncion() {
		return subfuncion;
	}

	public void setSubfuncion(String subfuncion) {
		this.subfuncion = subfuncion;
	}

	public String getJornada() {
		return jornada;
	}

	public void setJornada(String jornada) {
		this.jornada = jornada;
	}

}
