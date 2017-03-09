package mx.gob.saludtlax.rh.persistencia;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 
 * @author José Pablo
 *
 */

@Entity
@Table (name="conceptos_nomina_empleados")
public class ConceptosNominaEmpleadosEntity {
	
	@Id
	@Column(name = "id_conceptos_nomina_empleados")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConceptosNominaEmpleados;
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name="id_nomina_empleado")
	private NominaEmpleadoEntity nominaEmpleado;
	@Column (name = "tipo")
	private Integer tipo;
	@Column (name = "clave")
	private String clave;
	@Column (name = "tipo_sat")
	private String tipoSat;
	@Column (name = "concepto")
	private String concepto;
	@Column (name = "importe_gravado")
	private BigDecimal importeGravado;
	@Column (name = "importe_excento")
	private BigDecimal importeExcento;
    @Column (name = "id_concepto_nomina_eventuales") 
    private Integer idConceptoEventuales;
    @Column (name = "id_concepto_nomina_federales") 
    private Integer idConceptoFederales;

	
	public Integer getIdConceptosNominaEmpleados() {
		return idConceptosNominaEmpleados;
	}
	public void setIdConceptosNominaEmpleados(Integer idConceptosNominaEmpleados) {
		this.idConceptosNominaEmpleados = idConceptosNominaEmpleados;
	}
	public NominaEmpleadoEntity getNominaEmpleado() {
		return nominaEmpleado;
	}
	public void setNominaEmpleado(NominaEmpleadoEntity nominaEmpleado) {
		this.nominaEmpleado = nominaEmpleado;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getTipoSat() {
		return tipoSat;
	}
	public void setTipoSat(String tipoSat) {
		this.tipoSat = tipoSat;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public BigDecimal getImporteGravado() {
		return importeGravado;
	}
	public void setImporteGravado(BigDecimal importeGravado) {
		this.importeGravado = importeGravado;
	}
	public BigDecimal getImporteExcento() {
		return importeExcento;
	}
	public void setImporteExcento(BigDecimal importeExcento) {
		this.importeExcento = importeExcento;
	}
    public Integer getIdConceptoEventuales() {
        return idConceptoEventuales;
    }
    public void setIdConceptoEventuales(Integer idConceptoEventuales) {
        this.idConceptoEventuales = idConceptoEventuales;
    }
    public Integer getIdConceptoFederales() {
        return idConceptoFederales;
    }
    public void setIdConceptoFederales(Integer idConceptoFederales) {
        this.idConceptoFederales = idConceptoFederales;
    }
}