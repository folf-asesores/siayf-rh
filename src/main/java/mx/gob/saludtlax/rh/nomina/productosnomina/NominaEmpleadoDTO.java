
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author José Pablo
 *
 */
public class NominaEmpleadoDTO implements Serializable {
    private static final long serialVersionUID = -6726964745827298938L;

    private Integer idNominaEmpleado;
    private Integer idProductoNomina;
    private String productoNomina;
    private Integer idEmpleado;
    private String rfc;
    private String empleado;
    private BigDecimal percepciones;
    private BigDecimal deducciones;
    private BigDecimal neto;
    private Integer idProyecto;
    private String proyecto;
    private Integer idDependencia;
    private String dependencia;
    private Integer idUnidadResponsable;
    private String unidadResponsable;
    private Integer idTipoContratacion;
    private String tipoContratacion;
    private Integer idTipoNombramiento;
    private String tipoNombramiento;
    private Integer idPuestoGeneral;
    private String puestoGeneral;
    private Integer idFuenteFinanciamiento;
    private String fuenteFinanciamiento;
    private Integer idSubfuenteFinanciamiento;
    private String subfuenteFinanciamiento;
    private Integer idTipoRecurso;
    private String tipoRecurso;
    private Date fechaAltaConfiguracion;
    private BigDecimal sueldo;
    private Integer idTabulador;
    private String tabulador;
    private Integer idCentroResponsabilidad;
    private String centroResponsabilidad;
    private Integer idPrograma;
    private String programa;
    private Date fechaInicioLabores;
    private Integer idConfiguracionPresupuestal;
    private String configuracionPresupuestal;
    private String numeroCuenta;
    private Integer idCuentaBancaria;
    private String cuentaBancaria;
    private Integer idMetodoPago;
    private String metodoPago;
    private Date inicioPeriodo;
    private Date finPeriodo;
    private Date fechaPago;
    private Integer numeroDiasPagados;
    private Integer consecutivo;
    private String numeroCheque;
    private Integer idEstatusNominaEmpleado;
    private String estatusNominaEmpleado;
    private Integer numeroIdPersonal;
    private Integer numeroIdLaboral;
    private Integer idFuncion;
    private String funcion;
    private String curp;
    private String nombreCompleto;
    private String fechaIngreso;
    private String subfuncion;
    private String financiamiento;
    private Date fechaInicio;
    private Date fechaFin;
    private String jornada;
    private String clues;
    private List<ConceptosNominaEmpleadosDTO> percepcionesList;
    private List<ConceptosNominaEmpleadosDTO> deduccionesList;

    public Integer getIdNominaEmpleado() {
        return idNominaEmpleado;
    }

    public void setIdNominaEmpleado(Integer idNominaEmpleado) {
        this.idNominaEmpleado = idNominaEmpleado;
    }

    public Integer getIdProductoNomina() {
        return idProductoNomina;
    }

    public void setIdProductoNomina(Integer idProductoNomina) {
        this.idProductoNomina = idProductoNomina;
    }

    public String getProductoNomina() {
        return productoNomina;
    }

    public void setProductoNomina(String productoNomina) {
        this.productoNomina = productoNomina;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public BigDecimal getPercepciones() {
        return percepciones;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public void setPercepciones(BigDecimal percepciones) {
        this.percepciones = percepciones;
    }

    public BigDecimal getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(BigDecimal deducciones) {
        this.deducciones = deducciones;
    }

    public BigDecimal getNeto() {
        return neto;
    }

    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public Integer getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    public void setIdUnidadResponsable(Integer idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    public String getUnidadResponsable() {
        return unidadResponsable;
    }

    public void setUnidadResponsable(String unidadResponsable) {
        this.unidadResponsable = unidadResponsable;
    }

    public Integer getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Integer idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public String getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public Integer getIdTipoNombramiento() {
        return idTipoNombramiento;
    }

    public void setIdTipoNombramiento(Integer idTipoNombramiento) {
        this.idTipoNombramiento = idTipoNombramiento;
    }

    public String getTipoNombramiento() {
        return tipoNombramiento;
    }

    public void setTipoNombramiento(String tipoNombramiento) {
        this.tipoNombramiento = tipoNombramiento;
    }

    public Integer getIdPuestoGeneral() {
        return idPuestoGeneral;
    }

    public void setIdPuestoGeneral(Integer idPuestoGeneral) {
        this.idPuestoGeneral = idPuestoGeneral;
    }

    public String getPuestoGeneral() {
        return puestoGeneral;
    }

    public void setPuestoGeneral(String puestoGeneral) {
        this.puestoGeneral = puestoGeneral;
    }

    public Integer getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    public String getFuenteFinanciamiento() {
        return fuenteFinanciamiento;
    }

    public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
        this.fuenteFinanciamiento = fuenteFinanciamiento;
    }

    public Integer getIdSubfuenteFinanciamiento() {
        return idSubfuenteFinanciamiento;
    }

    public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
    }

    public String getSubfuenteFinanciamiento() {
        return subfuenteFinanciamiento;
    }

    public void setSubfuenteFinanciamiento(String subfuenteFinanciamiento) {
        this.subfuenteFinanciamiento = subfuenteFinanciamiento;
    }

    public Integer getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(Integer idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public Date getFechaAltaConfiguracion() {
        return fechaAltaConfiguracion;
    }

    public void setFechaAltaConfiguracion(Date fechaAltaConfiguracion) {
        this.fechaAltaConfiguracion = fechaAltaConfiguracion;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public Integer getIdTabulador() {
        return idTabulador;
    }

    public void setIdTabulador(Integer idTabulador) {
        this.idTabulador = idTabulador;
    }

    public String getTabulador() {
        return tabulador;
    }

    public void setTabulador(String tabulador) {
        this.tabulador = tabulador;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Date getFechaInicioLabores() {
        return fechaInicioLabores;
    }

    public void setFechaInicioLabores(Date fechaInicioLabores) {
        this.fechaInicioLabores = fechaInicioLabores;
    }

    public Integer getIdConfiguracionPresupuestal() {
        return idConfiguracionPresupuestal;
    }

    public void setIdConfiguracionPresupuestal(Integer idConfiguracionPresupuestal) {
        this.idConfiguracionPresupuestal = idConfiguracionPresupuestal;
    }

    public String getConfiguracionPresupuestal() {
        return configuracionPresupuestal;
    }

    public void setConfiguracionPresupuestal(String configuracionPresupuestal) {
        this.configuracionPresupuestal = configuracionPresupuestal;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Integer getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(Integer idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public Integer getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(Integer idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    public void setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    public Date getFinPeriodo() {
        return finPeriodo;
    }

    public void setFinPeriodo(Date finPeriodo) {
        this.finPeriodo = finPeriodo;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getNumeroDiasPagados() {
        return numeroDiasPagados;
    }

    public void setNumeroDiasPagados(Integer numeroDiasPagados) {
        this.numeroDiasPagados = numeroDiasPagados;
    }

    public Integer getIdCentroResponsabilidad() {
        return idCentroResponsabilidad;
    }

    public void setIdCentroResponsabilidad(Integer idCentroResponsabilidad) {
        this.idCentroResponsabilidad = idCentroResponsabilidad;
    }

    public String getCentroResponsabilidad() {
        return centroResponsabilidad;
    }

    public void setCentroResponsabilidad(String centroResponsabilidad) {
        this.centroResponsabilidad = centroResponsabilidad;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public Integer getIdEstatusNominaEmpleado() {
        return idEstatusNominaEmpleado;
    }

    public void setIdEstatusNominaEmpleado(Integer idEstatusNominaEmpleado) {
        this.idEstatusNominaEmpleado = idEstatusNominaEmpleado;
    }

    public String getEstatusNominaEmpleado() {
        return estatusNominaEmpleado;
    }

    public void setEstatusNominaEmpleado(String estatusNominaEmpleado) {
        this.estatusNominaEmpleado = estatusNominaEmpleado;
    }

    public Integer getNumeroIdPersonal() {
        return numeroIdPersonal;
    }

    public void setNumeroIdPersonal(Integer numeroIdPersonal) {
        this.numeroIdPersonal = numeroIdPersonal;
    }

    public Integer getNumeroIdLaboral() {
        return numeroIdLaboral;
    }

    public void setNumeroIdLaboral(Integer numeroIdLaboral) {
        this.numeroIdLaboral = numeroIdLaboral;
    }

    public Integer getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(Integer idFuncion) {
        this.idFuncion = idFuncion;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public List<ConceptosNominaEmpleadosDTO> getPercepcionesList() {
        return percepcionesList;
    }

    public void setPercepcionesList(List<ConceptosNominaEmpleadosDTO> percepcionesList) {
        this.percepcionesList = percepcionesList;
    }

    public List<ConceptosNominaEmpleadosDTO> getDeduccionesList() {
        return deduccionesList;
    }

    public void setDeduccionesList(List<ConceptosNominaEmpleadosDTO> deduccionesList) {
        this.deduccionesList = deduccionesList;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
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

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getClues() {
        return clues;
    }

    public void setClues(String clues) {
        this.clues = clues;
    }
}