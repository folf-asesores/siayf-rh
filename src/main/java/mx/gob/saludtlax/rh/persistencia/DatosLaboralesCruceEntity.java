/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "siif_datos_laborales")
public class DatosLaboralesCruceEntity implements Serializable {

    private static final long serialVersionUID = -3491218769714297031L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dato_laboral")
    private Integer idDatoLaboral;

    @Column(name = "id_empleado_datos_laborales")
    private Integer idEmpleadoDatosLaborales;

    @Column(name = "id_empleado_datos_personales")
    private Integer idEmpleadoDatosPersonales;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "id_plaza")
    private String idPlaza;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proyecto")
    private ProyectoTempEntity proyecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dependencia")
    private DependenciaTempEntity dependencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad_responsable")
    private UnidadResponsableEntity unidadResponsable;

    @Column(name = "nombramiento")
    private String nombramiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fuente_financiamiento")
    private FuenteFinanciamientoEntity fuenteFinanciamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subfuente_financiamiento")
    private SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_recurso")
    private TipoRecursoTempEntity tipoRecurso;

    @Column(name = "id_puesto")
    private String codigoPuesto;

    @Column(name = "id_estado_empleado")
    private String estatus;

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getCodigoPuesto() {
        return codigoPuesto;
    }

    public void setCodigoPuesto(String codigoPuesto) {
        this.codigoPuesto = codigoPuesto;
    }

    public Integer getIdDatoLaboral() {
        return idDatoLaboral;
    }

    public void setIdDatoLaboral(Integer idDatoLaboral) {
        this.idDatoLaboral = idDatoLaboral;
    }

    public Integer getIdEmpleadoDatosLaborales() {
        return idEmpleadoDatosLaborales;
    }

    public void setIdEmpleadoDatosLaborales(Integer idEmpleadoDatosLaborales) {
        this.idEmpleadoDatosLaborales = idEmpleadoDatosLaborales;
    }

    public Integer getIdEmpleadoDatosPersonales() {
        return idEmpleadoDatosPersonales;
    }

    public void setIdEmpleadoDatosPersonales(Integer idEmpleadoDatosPersonales) {
        this.idEmpleadoDatosPersonales = idEmpleadoDatosPersonales;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getIdPlaza() {
        return idPlaza;
    }

    public void setIdPlaza(String idPlaza) {
        this.idPlaza = idPlaza;
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

    public String getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
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

}