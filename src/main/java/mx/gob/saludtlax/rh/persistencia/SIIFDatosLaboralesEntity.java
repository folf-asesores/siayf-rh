/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "siif_datos_laborales")
public class SIIFDatosLaboralesEntity implements Serializable {

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

    @Column(name = "id_rfc")
    private Integer idRfc;

    @Column(name = "id_plaza")
    private String idPlaza;

    @Column(name = "id_proyecto")
    private Integer idProyecto;

    @Column(name = "id_dependencia")
    private Integer idDependencia;

    @Column(name = "id_unidad_responsable")
    private Integer idUnidadResponsable;

    @Column(name = "nombramiento")
    private String nombramiento;

    @Column(name = "id_puesto")
    private String idPuesto;

    @Column(name = "id_sindicato")
    private Integer idSindicato;

    @Column(name = "id_habilitado")
    private Integer idHabilitado;

    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;

    @Column(name = "no_quinquenios")
    private Integer noQuinquenios;

    @Column(name = "sueldo_mensual")
    private BigDecimal sueldoMensual;

    @Column(name = "percepcion_complementaria")
    private BigDecimal percepcionComplementaria;

    @Column(name = "despensa")
    private BigDecimal despensa;

    @Column(name = "incentivo_ahorro")
    private BigDecimal incentivoAhorro;

    @Column(name = "compensacion")
    private BigDecimal compensacion;

    @Column(name = "quinquenio")
    private BigDecimal quinquenio;

    @Column(name = "no_cuenta")
    private String noCuenta;

    @Column(name = "policia")
    private Integer policia;

    @Column(name = "id_fuente_financiamiento")
    private Integer idFuenteFinanciamiento;

    @Column(name = "id_subfuente_financiamiento")
    private Integer idSubfuenteFinanciamiento;

    @Column(name = "id_tipo_recurso")
    private Integer idTipoRecurso;

    @Column(name = "id_estado_empleado")
    private String idEstadoEmpleado;

    @Column(name = "id_nomina")
    private Integer idNomina;

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

    public void setIdEmpleadoDatosPersonales(
            Integer idEmpleadoDatosPersonales) {
        this.idEmpleadoDatosPersonales = idEmpleadoDatosPersonales;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Integer getIdRfc() {
        return idRfc;
    }

    public void setIdRfc(Integer idRfc) {
        this.idRfc = idRfc;
    }

    public String getIdPlaza() {
        return idPlaza;
    }

    public void setIdPlaza(String idPlaza) {
        this.idPlaza = idPlaza;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Integer getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    public void setIdUnidadResponsable(Integer idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    public String getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    public String getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        this.idPuesto = idPuesto;
    }

    public Integer getIdSindicato() {
        return idSindicato;
    }

    public void setIdSindicato(Integer idSindicato) {
        this.idSindicato = idSindicato;
    }

    public Integer getIdHabilitado() {
        return idHabilitado;
    }

    public void setIdHabilitado(Integer idHabilitado) {
        this.idHabilitado = idHabilitado;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getNoQuinquenios() {
        return noQuinquenios;
    }

    public void setNoQuinquenios(Integer noQuinquenios) {
        this.noQuinquenios = noQuinquenios;
    }

    public BigDecimal getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(BigDecimal sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public BigDecimal getPercepcionComplementaria() {
        return percepcionComplementaria;
    }

    public void setPercepcionComplementaria(
            BigDecimal percepcionComplementaria) {
        this.percepcionComplementaria = percepcionComplementaria;
    }

    public BigDecimal getDespensa() {
        return despensa;
    }

    public void setDespensa(BigDecimal despensa) {
        this.despensa = despensa;
    }

    public BigDecimal getIncentivoAhorro() {
        return incentivoAhorro;
    }

    public void setIncentivoAhorro(BigDecimal incentivoAhorro) {
        this.incentivoAhorro = incentivoAhorro;
    }

    public BigDecimal getCompensacion() {
        return compensacion;
    }

    public void setCompensacion(BigDecimal compensacion) {
        this.compensacion = compensacion;
    }

    public BigDecimal getQuinquenio() {
        return quinquenio;
    }

    public void setQuinquenio(BigDecimal quinquenio) {
        this.quinquenio = quinquenio;
    }

    public String getNoCuenta() {
        return noCuenta;
    }

    public void setNoCuenta(String noCuenta) {
        this.noCuenta = noCuenta;
    }

    public Integer getPolicia() {
        return policia;
    }

    public void setPolicia(Integer policia) {
        this.policia = policia;
    }

    public Integer getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    public Integer getIdSubfuenteFinanciamiento() {
        return idSubfuenteFinanciamiento;
    }

    public void setIdSubfuenteFinanciamiento(
            Integer idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
    }

    public Integer getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(Integer idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    public String getIdEstadoEmpleado() {
        return idEstadoEmpleado;
    }

    public void setIdEstadoEmpleado(String idEstadoEmpleado) {
        this.idEstadoEmpleado = idEstadoEmpleado;
    }

    public Integer getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(Integer idNomina) {
        this.idNomina = idNomina;
    }

}
