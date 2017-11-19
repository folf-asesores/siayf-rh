
package mx.gob.saludtlax.rh.siif.layout;

import java.math.BigDecimal;
import java.util.Date;

public class DatosLaboralesDTO {
    private Integer idEmpleadoDatosLaborales;
    private Integer idEmpleadoDatosPersonales;
    private String rfc;
    private Integer idRfc;
    private String idPlaza;
    private Integer idProyecto;
    private Integer idDependencia;
    private Integer idUnidadResponsable;
    private Character nombramiento;
    private String idPuesto;
    private Integer idSindicato;
    private Integer idHabilitado;
    private Date fechaIngreso;
    private Integer noQuinquenios;
    private BigDecimal sueldoMensual;
    private BigDecimal percepcionComplementaria;
    private BigDecimal despensa;
    private BigDecimal incentivoAhorro;
    private BigDecimal compensacion;
    private BigDecimal quinquenio;
    private String noCuenta;
    private Integer policia;
    private Integer idFuenteFinanciamiento;
    private Integer idSubfuentefinanciamiento;
    private Integer idTipoRecurso;
    private Character idEstadoEmpleado;
    private Integer idNomina;

    public DatosLaboralesDTO() {
    }

    public DatosLaboralesDTO(Integer idEmpleadoDatosLaborales,
            Integer idEmpleadoDatosPersonales, String rfc, Integer idRfc,
            String idPlaza, Integer idProyecto, Integer idDependencia,
            Integer idUnidadResponsable, Character nombramiento,
            String idPuesto, Integer idSindicato, Integer idHabilitado,
            Date fechaIngreso, Integer noQuinquenios, BigDecimal sueldoMensual,
            BigDecimal percepcionComplementaria, BigDecimal despensa,
            BigDecimal incentivoAhorro, BigDecimal compensacion,
            BigDecimal quinquenio, String noCuenta, Integer policia,
            Integer idFuenteFinanciamiento, Integer idSubfuentefinanciamiento,
            Integer idTipoRecurso, Character idEstadoEmpleado,
            Integer idNomina) {
        this.idEmpleadoDatosLaborales = idEmpleadoDatosLaborales;
        this.idEmpleadoDatosPersonales = idEmpleadoDatosPersonales;
        this.rfc = rfc;
        this.idRfc = idRfc;
        this.idPlaza = idPlaza;
        this.idProyecto = idProyecto;
        this.idDependencia = idDependencia;
        this.idUnidadResponsable = idUnidadResponsable;
        this.nombramiento = nombramiento;
        this.idPuesto = idPuesto;
        this.idSindicato = idSindicato;
        this.idHabilitado = idHabilitado;
        this.fechaIngreso = fechaIngreso;
        this.noQuinquenios = noQuinquenios;
        this.sueldoMensual = sueldoMensual;
        this.percepcionComplementaria = percepcionComplementaria;
        this.despensa = despensa;
        this.incentivoAhorro = incentivoAhorro;
        this.compensacion = compensacion;
        this.quinquenio = quinquenio;
        this.noCuenta = noCuenta;
        this.policia = policia;
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
        this.idSubfuentefinanciamiento = idSubfuentefinanciamiento;
        this.idTipoRecurso = idTipoRecurso;
        this.idEstadoEmpleado = idEstadoEmpleado;
        this.idNomina = idNomina;
    }

    @MethodOrder(value = 1)
    public Integer getIdEmpleadoDatosLaborales() {
        return idEmpleadoDatosLaborales;
    }

    public void setIdEmpleadoDatosLaborales(Integer idEmpleadoDatosLaborales) {
        this.idEmpleadoDatosLaborales = idEmpleadoDatosLaborales;
    }

    @MethodOrder(value = 2)
    public Integer getIdEmpleadoDatosPersonales() {
        return idEmpleadoDatosPersonales;
    }

    public void setIdEmpleadoDatosPersonales(
            Integer idEmpleadoDatosPersonales) {
        this.idEmpleadoDatosPersonales = idEmpleadoDatosPersonales;
    }

    @MethodOrder(value = 3)
    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @MethodOrder(value = 4)
    public Integer getIdRfc() {
        return idRfc;
    }

    public void setIdRfc(Integer idRfc) {
        this.idRfc = idRfc;
    }

    @MethodOrder(value = 5)
    public String getIdPlaza() {
        return idPlaza;
    }

    public void setIdPlaza(String idPlaza) {
        this.idPlaza = idPlaza;
    }

    @MethodOrder(value = 6)
    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    @MethodOrder(value = 7)
    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    @MethodOrder(value = 8)
    public Integer getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    public void setIdUnidadResponsable(Integer idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    @MethodOrder(value = 9)
    public Character getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(Character nombramiento) {
        this.nombramiento = nombramiento;
    }

    @MethodOrder(value = 10)
    public String getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        this.idPuesto = idPuesto;
    }

    @MethodOrder(value = 11)
    public Integer getIdSindicato() {
        return idSindicato;
    }

    public void setIdSindicato(Integer idSindicato) {
        this.idSindicato = idSindicato;
    }

    @MethodOrder(value = 12)
    public Integer getIdHabilitado() {
        return idHabilitado;
    }

    public void setIdHabilitado(Integer idHabilitado) {
        this.idHabilitado = idHabilitado;
    }

    @MethodOrder(value = 13)
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @MethodOrder(value = 14)
    public Integer getNoQuinquenios() {
        return noQuinquenios;
    }

    public void setNoQuinquenios(Integer noQuinquenios) {
        this.noQuinquenios = noQuinquenios;
    }

    @MethodOrder(value = 15)
    public BigDecimal getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(BigDecimal sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    @MethodOrder(value = 16)
    public BigDecimal getPercepcionComplementaria() {
        return percepcionComplementaria;
    }

    public void setPercepcionComplementaria(
            BigDecimal percepcionComplementaria) {
        this.percepcionComplementaria = percepcionComplementaria;
    }

    @MethodOrder(value = 17)
    public BigDecimal getDespensa() {
        return despensa;
    }

    public void setDespensa(BigDecimal despensa) {
        this.despensa = despensa;
    }

    @MethodOrder(value = 18)
    public BigDecimal getIncentivoAhorro() {
        return incentivoAhorro;
    }

    public void setIncentivoAhorro(BigDecimal incentivoAhorro) {
        this.incentivoAhorro = incentivoAhorro;
    }

    @MethodOrder(value = 19)
    public BigDecimal getCompensacion() {
        return compensacion;
    }

    public void setCompensacion(BigDecimal compensacion) {
        this.compensacion = compensacion;
    }

    @MethodOrder(value = 20)
    public BigDecimal getQuinquenio() {
        return quinquenio;
    }

    public void setQuinquenio(BigDecimal quinquenio) {
        this.quinquenio = quinquenio;
    }

    @MethodOrder(value = 21)
    public String getNoCuenta() {
        return noCuenta;
    }

    public void setNoCuenta(String noCuenta) {
        this.noCuenta = noCuenta;
    }

    @MethodOrder(value = 22)
    public Integer getPolicia() {
        return policia;
    }

    public void setPolicia(Integer policia) {
        this.policia = policia;
    }

    @MethodOrder(value = 23)
    public Integer getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    @MethodOrder(value = 24)
    public Integer getIdSubfuentefinanciamiento() {
        return idSubfuentefinanciamiento;
    }

    public void setIdSubfuentefinanciamiento(
            Integer idSubfuentefinanciamiento) {
        this.idSubfuentefinanciamiento = idSubfuentefinanciamiento;
    }

    @MethodOrder(value = 25)
    public Integer getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(Integer idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    @MethodOrder(value = 26)
    public Character getIdEstadoEmpleado() {
        return idEstadoEmpleado;
    }

    public void setIdEstadoEmpleado(Character idEstadoEmpleado) {
        this.idEstadoEmpleado = idEstadoEmpleado;
    }

    @MethodOrder(value = 27)
    public Integer getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(Integer idNomina) {
        this.idNomina = idNomina;
    }
}