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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Leila Schiaffini Ehuan
 * @since 08/08/2016 17:27:40
 * 
 */
@Entity
@Table(name = "inventario_vacantes")
public class InventarioVacanteEntity implements Serializable {
	private static final long serialVersionUID = 5580848279494109729L;

	@Id
	@Column(name = "id_inventario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVacante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_contratacion")
	private TipoContratacionEntity tipoContratacion;

	@Column(name = "numero_vacante")
	private Integer numeroVacante;

	@Column(name = "codigo_vacante")
	private String codigoVacante;

	@Column(name = "folio_vacante")
	private String folioVacante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estatus")
	private EstatusPuestosEntity estatus;

	@Column(name = "disponible")
	private String disponible;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_configuracion_presupuestal")
	private ConfiguracionPresupuestoEntity configuracion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_programa")
	private ProgramaEntity programa;

	@Column(name = "id_candidato_postulado")
	private Integer idCandidatoPostulado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_adscripcion")
	private AdscripcionEntity adscripcion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_lugar_adscripcion")
	private LugarAdscripcionEntity lugarAdscripcion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_area_adscripcion")
	private SubadscripcionEntity subadscripcion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_actividad")
	private ServicioEntity servicio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcion")
	private FuncionEntity funcion;

	@Column(name = "fecha_inicio")
	private Date fechaInicio;

	@Column(name = "fecha_fin")
	private Date fechaFin;

	@Column(name = "provisional")
	private Boolean provisional;

	@Column(name = "fecha_inicio_permiso")
	private Date fechaInicioPermiso;

	@Column(name = "fecha_fin_permiso")
	private Date fechaFinPermiso;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_movimiento_permiso")
	private MovimientoEmpleadoEntity movimientoPermiso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_detalle_programa")
	private DetalleProgramaEntity detallePrograma;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empleado_anterior")
	private EmpleadoEntity ultimoEmpleado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_clue")
	private CluesEntity clue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empleado")
	private EmpleadoEntity empleadoActivo;

	@Column(name = "interino")
	private Boolean interino;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_voluntario")
	private VoluntarioEntity voluntario;

	@Column(name = "seguro_popular")
	private boolean seguroPopular;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_jornada")
	private TipoJornadaEntity tipoJornada;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_puesto_autorizado")
	private PuestoGeneralEntity puestoAutorizado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nombramiento")
	private TiposNombramientosEntity nombramiento;

	@Column(name = "id_plaza")
	private String numeroPuestoAutorizado;

	@Column(name = "funcion")
	private String funcionEspecifica;

	@Column(name = "subfuncion")
	private String subfuncion;

	@Column(name = "financiamiento")
	private String financiamiento;

	@Column(name = "jornada")
	private String jornada;

	public String lccEstructuraContrato() {
		return "InventarioVacanteEntity [funcionEspecifica=" + funcionEspecifica + ", subfuncion=" + subfuncion
				+ ", financiamiento=" + financiamiento + ", jornada=" + jornada + "]";
	}

	public String getFuncionEspecifica() {
		return funcionEspecifica;
	}

	public void setFuncionEspecifica(String funcionEspecifica) {
		this.funcionEspecifica = funcionEspecifica;
	}

	public String getSubfuncion() {
		return subfuncion;
	}

	public void setSubfuncion(String subfuncion) {
		this.subfuncion = subfuncion;
	}

	public String getFinanciamiento() {
		return financiamiento;
	}

	public void setFinanciamiento(String financiamiento) {
		this.financiamiento = financiamiento;
	}

	public String getJornada() {
		return jornada;
	}

	public void setJornada(String jornada) {
		this.jornada = jornada;
	}

	public PuestoGeneralEntity getPuestoAutorizado() {
		return puestoAutorizado;
	}

	public void setPuestoAutorizado(PuestoGeneralEntity puestoAutorizado) {
		this.puestoAutorizado = puestoAutorizado;
	}

	public TiposNombramientosEntity getNombramiento() {
		return nombramiento;
	}

	public void setNombramiento(TiposNombramientosEntity nombramiento) {
		this.nombramiento = nombramiento;
	}

	public String getNumeroPuestoAutorizado() {
		return numeroPuestoAutorizado;
	}

	public void setNumeroPuestoAutorizado(String numeroPuestoAutorizado) {
		this.numeroPuestoAutorizado = numeroPuestoAutorizado;
	}

	public TipoJornadaEntity getTipoJornada() {
		return tipoJornada;
	}

	public void setTipoJornada(TipoJornadaEntity tipoJornada) {
		this.tipoJornada = tipoJornada;
	}

	public boolean isSeguroPopular() {
		return seguroPopular;
	}

	public void setSeguroPopular(boolean seguroPopular) {
		this.seguroPopular = seguroPopular;
	}

	public VoluntarioEntity getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(VoluntarioEntity voluntario) {
		this.voluntario = voluntario;
	}

	public Boolean getInterino() {
		return interino;
	}

	public void setInterino(Boolean interino) {
		this.interino = interino;
	}

	public EmpleadoEntity getEmpleadoActivo() {
		return empleadoActivo;
	}

	public void setEmpleadoActivo(EmpleadoEntity empleadoActivo) {
		this.empleadoActivo = empleadoActivo;
	}

	public CluesEntity getClue() {
		return clue;
	}

	public void setClue(CluesEntity clue) {
		this.clue = clue;
	}

	public EmpleadoEntity getUltimoEmpleado() {
		return ultimoEmpleado;
	}

	public void setUltimoEmpleado(EmpleadoEntity ultimoEmpleado) {
		this.ultimoEmpleado = ultimoEmpleado;
	}

	public DetalleProgramaEntity getDetallePrograma() {
		return detallePrograma;
	}

	public void setDetallePrograma(DetalleProgramaEntity detallePrograma) {
		this.detallePrograma = detallePrograma;
	}

	public EstatusPuestosEntity getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusPuestosEntity estatus) {
		this.estatus = estatus;
	}

	public MovimientoEmpleadoEntity getMovimientoPermiso() {
		return movimientoPermiso;
	}

	public void setMovimientoPermiso(MovimientoEmpleadoEntity movimientoPermiso) {
		this.movimientoPermiso = movimientoPermiso;
	}

	public Date getFechaInicioPermiso() {
		return fechaInicioPermiso;
	}

	public void setFechaInicioPermiso(Date fechaInicioPermiso) {
		this.fechaInicioPermiso = fechaInicioPermiso;
	}

	public Date getFechaFinPermiso() {
		return fechaFinPermiso;
	}

	public void setFechaFinPermiso(Date fechaFinPermiso) {
		this.fechaFinPermiso = fechaFinPermiso;
	}

	public ServicioEntity getServicio() {
		return servicio;
	}

	public void setServicio(ServicioEntity servicio) {
		this.servicio = servicio;
	}

	public FuncionEntity getFuncion() {
		return funcion;
	}

	public void setFuncion(FuncionEntity funcion) {
		this.funcion = funcion;
	}

	public Boolean getProvisional() {
		return provisional;
	}

	public void setProvisional(Boolean provisional) {
		this.provisional = provisional;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public AdscripcionEntity getAdscripcion() {
		return adscripcion;
	}

	public void setAdscripcion(AdscripcionEntity adscripcion) {
		this.adscripcion = adscripcion;
	}

	public SubadscripcionEntity getSubadscripcion() {
		return subadscripcion;
	}

	public void setSubadscripcion(SubadscripcionEntity subadscripcion) {
		this.subadscripcion = subadscripcion;
	}

	public Integer getIdCandidatoPostulado() {
		return idCandidatoPostulado;
	}

	public void setIdCandidatoPostulado(Integer idCandidatoPostulado) {
		this.idCandidatoPostulado = idCandidatoPostulado;
	}

	public ProgramaEntity getPrograma() {
		return programa;
	}

	public void setPrograma(ProgramaEntity programa) {
		this.programa = programa;
	}

	public TipoContratacionEntity getTipoContratacion() {
		return tipoContratacion;
	}

	public void setTipoContratacion(TipoContratacionEntity tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
	}

	public Integer getNumeroVacante() {
		return numeroVacante;
	}

	public void setNumeroVacante(Integer numeroVacante) {
		this.numeroVacante = numeroVacante;
	}

	public String getCodigoVacante() {
		return codigoVacante;
	}

	public void setCodigoVacante(String codigoVacante) {
		this.codigoVacante = codigoVacante;
	}

	public String getFolioVacante() {
		return folioVacante;
	}

	public void setFolioVacante(String folioVacante) {
		this.folioVacante = folioVacante;
	}

	public void setIdVacante(Integer idVacante) {
		this.idVacante = idVacante;
	}

	public String getDisponible() {
		return disponible;
	}

	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}

	public ConfiguracionPresupuestoEntity getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(ConfiguracionPresupuestoEntity configuracion) {
		this.configuracion = configuracion;
	}

	public Integer getIdVacante() {
		return idVacante;
	}

	public LugarAdscripcionEntity getLugarAdscripcion() {
		return lugarAdscripcion;
	}

	public void setLugarAdscripcion(LugarAdscripcionEntity lugarAdscripcion) {
		this.lugarAdscripcion = lugarAdscripcion;
	}
	
	
	

}
