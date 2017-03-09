/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author eduardo
 *
 */
@Entity
@Table(name = "bitacoras_reportes")
public class BitacoraReporteEntity {

	@Id
	@Column(name = "id_referencia")
	private String idReferencia;

	@Column(name = "nombre_reporte", nullable = false)
	private String nombreReporte;

	@Column(name = "fecha_generacion", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaGeneracion;

	@ManyToOne(targetEntity = UsuarioEntity.class)
	@JoinColumn(name = "id_usuario", nullable = false)
	private UsuarioEntity usuario;

	@OneToMany(mappedBy = "bitacoraReporte", cascade = CascadeType.ALL)
	private Set<ReporteParametroEntity> reporteParametros;

	public String getIdReferencia() {
		return idReferencia;
	}

	public void setIdReferencia(String idReferencia) {
		this.idReferencia = idReferencia;
	}

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public Set<ReporteParametroEntity> getReporteParametros() {
		return reporteParametros;
	}

	public void setReporteParametros(Set<ReporteParametroEntity> reporteParametros) {
		this.reporteParametros = reporteParametros;
	}

}
