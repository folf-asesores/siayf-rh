/*
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

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 15/11/2016 16:17:04
 */
@Entity
@Table(name = "personal_eventual")
public class PadronEventualEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 585549243060628495L;
    @Id
    @Column(name = "id_eventual")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEventual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_contratacion")
    private TipoContratacionEntity tipoContratacion;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "rfc_sat")
    private String rfcsat;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;

    @Column(name = "centro_responsabilidad")
    private String centroResponsabilidad;

    @Column(name = "funcion")
    private String funcion;

    @Column(name = "rama")
    private String rama;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programa")
    private ProgramaEntity programa;

    @Column(name = "sueldo")
    private BigDecimal sueldo;

    @Column(name = "conPuesto")
    private boolean conPuesto;

    @Column(name = "id_inventario")
    private Integer idInventario;

    @Column(name = "duplicado_siif")
    private boolean duplicadoSiif;

    @Column(name = "suplencia")
    private boolean suplencia;

    @Column(name = "sueldo_01")
    private BigDecimal sueldo1;

    @Column(name = "sueldo_14")
    private BigDecimal sueldo14;

    @Column(name = "id_siif")
    private Integer idSiif;

    @Column(name = "sin_empleado")
    private Boolean sinEmpleado;

    @Column(name = " id_quincena_suplencia")
    private Integer idQuincenaSuplencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = " id_centro_responsabilidad")
    private CentroResponsabilidadEntity idCentroResponsabilidad;

    public CentroResponsabilidadEntity getIdCentroResponsabilidad() {
        return idCentroResponsabilidad;
    }

    public void setIdCentroResponsabilidad(
            CentroResponsabilidadEntity idCentroResponsabilidad) {
        this.idCentroResponsabilidad = idCentroResponsabilidad;
    }

    public Integer getIdQuincenaSuplencia() {
        return idQuincenaSuplencia;
    }

    public void setIdQuincenaSuplencia(Integer idQuincenaSuplencia) {
        this.idQuincenaSuplencia = idQuincenaSuplencia;
    }

    public Boolean getSinEmpleado() {
        return sinEmpleado;
    }

    public void setSinEmpleado(Boolean sinEmpleado) {
        this.sinEmpleado = sinEmpleado;
    }

    public BigDecimal getSueldo1() {
        return sueldo1;
    }

    public void setSueldo1(BigDecimal sueldo1) {
        this.sueldo1 = sueldo1;
    }

    public BigDecimal getSueldo14() {
        return sueldo14;
    }

    public void setSueldo14(BigDecimal sueldo14) {
        this.sueldo14 = sueldo14;
    }

    public Integer getIdSiif() {
        return idSiif;
    }

    public void setIdSiif(Integer idSiif) {
        this.idSiif = idSiif;
    }

    public boolean isSuplencia() {
        return suplencia;
    }

    public void setSuplencia(boolean suplencia) {
        this.suplencia = suplencia;
    }

    public boolean isDuplicadoSiif() {
        return duplicadoSiif;
    }

    public void setDuplicadoSiif(boolean duplicadoSiif) {
        this.duplicadoSiif = duplicadoSiif;
    }

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public TipoContratacionEntity getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(TipoContratacionEntity tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfcsat() {
        return rfcsat;
    }

    public void setRfcsat(String rfcsat) {
        this.rfcsat = rfcsat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCentroResponsabilidad() {
        return centroResponsabilidad;
    }

    public void setCentroResponsabilidad(String centroResponsabilidad) {
        this.centroResponsabilidad = centroResponsabilidad;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getRama() {
        return rama;
    }

    public void setRama(String rama) {
        this.rama = rama;
    }

    public ProgramaEntity getPrograma() {
        return programa;
    }

    public void setPrograma(ProgramaEntity programa) {
        this.programa = programa;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public boolean isConPuesto() {
        return conPuesto;
    }

    public void setConPuesto(boolean conPuesto) {
        this.conPuesto = conPuesto;
    }

    public Integer getIdEventual() {
        return idEventual;
    }

}
