/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 07/11/2016 22:23:50
 */
@Entity
@Table(name = "quincenas_suplencias")
public class QuincenasSuplenciasEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4411374768727379682L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_quincena_suplencia")
	private Integer idQuincenaSuplencia;

	@Column(name = "numero_quincena")
	private int numeroQuincena;

	@Column(name = "ejercicio_fiscal")
	private int ejercicioFiscal;
	
	@Column(name ="id_mes")
	private Integer idMes;
	
	@Column(name ="total_dias")
	private Integer totalDias;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_suplente")
	private SuplenteAutorizadoEntity suplente;

	@Column(name = "fecha_cierre")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fechaCierre;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proyecto")
	private ProyectoTempEntity proyecto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dependencia")
	private DependenciaTempEntity dependencia;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidad_responsable")
	private UnidadResponsableEntity unidadResponsable;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fuente_financiamiento")
	private FuenteFinanciamientoEntity fuenteFinanciamiento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_subfuente_financiamiento")
	private SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_recurso")
	private TipoRecursoTempEntity tipoRecurso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cuenta")
	private CuentasBancariasEntity cuenta;

	@Column(name = "total")
	private BigDecimal total;

	@Column(name = "estatus")
	private String estatus;
	
	@Column(name ="id_nomina")
	private Integer idNomina;

	
	
	public Integer getIdNomina() {
		return idNomina;
	}

	public void setIdNomina(Integer idNomina) {
		this.idNomina = idNomina;
	}

	public Integer getIdMes() {
		return idMes;
	}

	public void setIdMes(Integer idMes) {
		this.idMes = idMes;
	}

	public Integer getTotalDias() {
		return totalDias;
	}

	public void setTotalDias(Integer totalDias) {
		this.totalDias = totalDias;
	}

	public void setNumeroQuincena(int numeroQuincena) {
		this.numeroQuincena = numeroQuincena;
	}

	public Integer getIdQuincenaSuplencia() {
		return idQuincenaSuplencia;
	}

	public int getEjercicioFiscal() {
		return ejercicioFiscal;
	}

	public void setEjercicioFiscal(int ejercicioFiscal) {
		this.ejercicioFiscal = ejercicioFiscal;
	}

	public SuplenteAutorizadoEntity getSuplente() {
		return suplente;
	}

	public void setSuplente(SuplenteAutorizadoEntity suplente) {
		this.suplente = suplente;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public ProyectoTempEntity getProyecto() {
		return proyecto;
	}

	public void setProyecto(ProyectoTempEntity proyecto) {
		this.proyecto = proyecto;
	}

	public DependenciaTempEntity getDependencia() {
		return dependencia;
	}

	public void setDependencia(DependenciaTempEntity dependencia) {
		this.dependencia = dependencia;
	}

	public UnidadResponsableEntity getUnidadResponsable() {
		return unidadResponsable;
	}

	public void setUnidadResponsable(UnidadResponsableEntity unidadResponsable) {
		this.unidadResponsable = unidadResponsable;
	}

	public FuenteFinanciamientoEntity getFuenteFinanciamiento() {
		return fuenteFinanciamiento;
	}

	public void setFuenteFinanciamiento(FuenteFinanciamientoEntity fuenteFinanciamiento) {
		this.fuenteFinanciamiento = fuenteFinanciamiento;
	}

	public SubFuenteFinanciamientoTempEntity getSubfuenteFinanciamiento() {
		return subfuenteFinanciamiento;
	}

	public void setSubfuenteFinanciamiento(SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento) {
		this.subfuenteFinanciamiento = subfuenteFinanciamiento;
	}

	public TipoRecursoTempEntity getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(TipoRecursoTempEntity tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public CuentasBancariasEntity getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentasBancariasEntity cuenta) {
		this.cuenta = cuenta;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public int getNumeroQuincena() {
		return numeroQuincena;
	}

}
