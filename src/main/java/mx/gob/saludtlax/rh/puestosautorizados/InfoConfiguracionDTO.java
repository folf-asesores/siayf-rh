package mx.gob.saludtlax.rh.puestosautorizados;

import java.util.Date;

public class InfoConfiguracionDTO {

	private Integer idConfiguracionPresupuesto;
	private String rfc;
	private String nombreCompleto;
	private Integer numeroEmpleado;
	private String contratacion;
	private String nombramiento;
	private String financiamiento;
	private String subfuenteFinanciamiento;
	private String programa;
	private Date inicio;
	private Date fin;

	public InfoConfiguracionDTO() {
		super();

	}

	public InfoConfiguracionDTO(Integer idConfiguracionPresupuesto,String rfc, String nombreCompleto,
			Integer numeroEmpleado, String contratacion, String nombramiento,
			String financiamiento, String subfuenteFinanciamiento,
			String programa, Date inicio, Date fin) {

		this.idConfiguracionPresupuesto = idConfiguracionPresupuesto;
		this.rfc = rfc;
		this.nombreCompleto = nombreCompleto;
		this.numeroEmpleado = numeroEmpleado;
		this.contratacion = contratacion;
		this.nombramiento = nombramiento;
		this.financiamiento = financiamiento;
		this.subfuenteFinanciamiento = subfuenteFinanciamiento;
		this.programa = programa;
		this.inicio = inicio;
		this.fin = fin;
	}
	
	

	public Integer getIdConfiguracionPresupuesto() {
		return idConfiguracionPresupuesto;
	}

	public void setIdConfiguracionPresupuesto(Integer idConfiguracionPresupuesto) {
		this.idConfiguracionPresupuesto = idConfiguracionPresupuesto;
	}

	public String getFinanciamiento() {
		return financiamiento;
	}

	public void setFinanciamiento(String financiamiento) {
		this.financiamiento = financiamiento;
	}

	public String getSubfuenteFinanciamiento() {
		return subfuenteFinanciamiento;
	}

	public void setSubfuenteFinanciamiento(String subfuenteFinanciamiento) {
		this.subfuenteFinanciamiento = subfuenteFinanciamiento;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Integer getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public void setNumeroEmpleado(Integer numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	public String getContratacion() {
		return contratacion;
	}

	public void setContratacion(String contratacion) {
		this.contratacion = contratacion;
	}

	public String getNombramiento() {
		return nombramiento;
	}

	public void setNombramiento(String nombramiento) {
		this.nombramiento = nombramiento;
	}

}
