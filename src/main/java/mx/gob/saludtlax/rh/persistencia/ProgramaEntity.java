/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
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

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 25/07/2016 15:00:07
 */
@Entity
@Table(name = "programas")
public class ProgramaEntity implements Serializable {

	private static final long serialVersionUID = -4577968737825678924L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_programa")
	private Integer idPrograma;

	@Column(name = "programa")
	private String programa;

	@Column(name = "id_tipo_configuracion")
	private Integer idTipoConfiguracion;

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

	@Column(name = "fecha_alta")
	private Date fechaAlta;

	@Column(name = "fecha_ultima_actualizacion")
	private Date fechaUltimaActualizacion;

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public Integer getIdTipoConfiguracion() {
		return idTipoConfiguracion;
	}

	public void setIdTipoConfiguracion(Integer idTipoConfiguracion) {
		this.idTipoConfiguracion = idTipoConfiguracion;
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

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

}
