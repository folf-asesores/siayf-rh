
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductoNominaProgramasDTO implements Serializable {

    private static final long serialVersionUID = 7612690231742648339L;

    private Integer idProductoNomina;
    private Integer idTipoPeriodo;
    private Integer idPeriodoCalendario;
    private Integer idUsuario;
    private Integer idEjercicioFiscal;
    private Integer idEstatusProductoNomina;
    private Integer idBanco;
    private Integer idCuentaBancaria;
    private Integer idTipoContratacion;
    private Integer idFuenteFinanciamiento;
    private Integer idSubfuenteFinanciamiento;
    private Integer idTipoNomina;
    private Integer idArea;
    private String tipoPeriodo;
    private String periodoCalendario;
    private String usuario;
    private Integer ejercicioFiscal;
    private String estatusProductoNomina;
    private String banco;
    private String cuentaBancaria;
    private String tipoContratacion;
    private String fuenteFinanciamiento;
    private String subfuenteFinanciamiento;
    private String tipoNomina;
    private Boolean cambiarFuenteFinanciamiento;
    private Integer anyoEjercicioFiscal;
    private Date inicioPeriodo;
    private Date finPeriodo;
    private Integer numeroPeriodo;
    private Date fechaPago;
    private String nombreProducto;
    private Integer numeroInicioCheques;
    private BigDecimal totalPercepciones;
    private BigDecimal totalDeducciones;
    private BigDecimal totalNeto;
    private BigDecimal totalSubsidio;
    private BigDecimal totalIsr;
    private Integer tipoRecurso;
    private Integer diasPagarAguinaldo;
    // private Date fechaFinCalculoAguinaldo; // Propiedad no usado
    private Date inicioRangoFaltas;
    private Date finRangoFaltas;
    private BigDecimal diasPrimaVacasional;
    private BigDecimal diasAguinaldo;
    private BigDecimal diasExentoPrimaVacasional;
    private BigDecimal diasExentoAguinaldo;

    private Boolean calcularFaltas;

    private Integer idNominaEmpleado;

    public Integer getIdProductoNomina() {
        return idProductoNomina;
    }

    public void setIdProductoNomina(Integer idProductoNomina) {
        this.idProductoNomina = idProductoNomina;
    }

    public Integer getIdTipoPeriodo() {
        return idTipoPeriodo;
    }

    public void setIdTipoPeriodo(Integer idTipoPeriodo) {
        this.idTipoPeriodo = idTipoPeriodo;
    }

    public Integer getIdPeriodoCalendario() {
        return idPeriodoCalendario;
    }

    public void setIdPeriodoCalendario(Integer idPeriodoCalendario) {
        this.idPeriodoCalendario = idPeriodoCalendario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEjercicioFiscal() {
        return idEjercicioFiscal;
    }

    public void setIdEjercicioFiscal(Integer idEjercicioFiscal) {
        this.idEjercicioFiscal = idEjercicioFiscal;
    }

    public Integer getIdEstatusProductoNomina() {
        return idEstatusProductoNomina;
    }

    public void setIdEstatusProductoNomina(Integer idEstatusProductoNomina) {
        this.idEstatusProductoNomina = idEstatusProductoNomina;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Integer getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(Integer idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public Integer getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Integer idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
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

    public Integer getIdTipoNomina() {
        return idTipoNomina;
    }

    public void setIdTipoNomina(Integer idTipoNomina) {
        this.idTipoNomina = idTipoNomina;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public String getPeriodoCalendario() {
        return periodoCalendario;
    }

    public void setPeriodoCalendario(String periodoCalendario) {
        this.periodoCalendario = periodoCalendario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(Integer ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

    public String getEstatusProductoNomina() {
        return estatusProductoNomina;
    }

    public void setEstatusProductoNomina(String estatusProductoNomina) {
        this.estatusProductoNomina = estatusProductoNomina;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public String getFuenteFinanciamiento() {
        return fuenteFinanciamiento;
    }

    public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
        this.fuenteFinanciamiento = fuenteFinanciamiento;
    }

    public String getSubfuenteFinanciamiento() {
        return subfuenteFinanciamiento;
    }

    public void setSubfuenteFinanciamiento(String subfuenteFinanciamiento) {
        this.subfuenteFinanciamiento = subfuenteFinanciamiento;
    }

    public String getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(String tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public Boolean getCambiarFuenteFinanciamiento() {
        return cambiarFuenteFinanciamiento;
    }

    public void setCambiarFuenteFinanciamiento(
            Boolean cambiarFuenteFinanciamiento) {
        this.cambiarFuenteFinanciamiento = cambiarFuenteFinanciamiento;
    }

    public Integer getAnyoEjercicioFiscal() {
        return anyoEjercicioFiscal;
    }

    public void setAnyoEjercicioFiscal(Integer anyoEjercicioFiscal) {
        this.anyoEjercicioFiscal = anyoEjercicioFiscal;
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

    public Integer getNumeroPeriodo() {
        return numeroPeriodo;
    }

    public void setNumeroPeriodo(Integer numeroPeriodo) {
        this.numeroPeriodo = numeroPeriodo;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getNumeroInicioCheques() {
        return numeroInicioCheques;
    }

    public void setNumeroInicioCheques(Integer numeroInicioCheques) {
        this.numeroInicioCheques = numeroInicioCheques;
    }

    public BigDecimal getTotalPercepciones() {
        return totalPercepciones;
    }

    public void setTotalPercepciones(BigDecimal totalPercepciones) {
        this.totalPercepciones = totalPercepciones;
    }

    public BigDecimal getTotalDeducciones() {
        return totalDeducciones;
    }

    public void setTotalDeducciones(BigDecimal totalDeducciones) {
        this.totalDeducciones = totalDeducciones;
    }

    public BigDecimal getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(BigDecimal totalNeto) {
        this.totalNeto = totalNeto;
    }

    public BigDecimal getTotalSubsidio() {
        return totalSubsidio;
    }

    public void setTotalSubsidio(BigDecimal totalSubsidio) {
        this.totalSubsidio = totalSubsidio;
    }

    public BigDecimal getTotalIsr() {
        return totalIsr;
    }

    public void setTotalIsr(BigDecimal totalIsr) {
        this.totalIsr = totalIsr;
    }

    public Integer getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(Integer tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public Date getInicioRangoFaltas() {
        return inicioRangoFaltas;
    }

    public void setInicioRangoFaltas(Date inicioRangoFaltas) {
        this.inicioRangoFaltas = inicioRangoFaltas;
    }

    public Date getFinRangoFaltas() {
        return finRangoFaltas;
    }

    public void setFinRangoFaltas(Date finRangoFaltas) {
        this.finRangoFaltas = finRangoFaltas;
    }

    public Integer getIdNominaEmpleado() {
        return idNominaEmpleado;
    }

    public void setIdNominaEmpleado(Integer idNominaEmpleado) {
        this.idNominaEmpleado = idNominaEmpleado;
    }

    public Integer getDiasPagarAguinaldo() {
        return diasPagarAguinaldo;
    }

    public void setDiasPagarAguinaldo(Integer diasPagarAguinaldo) {
        this.diasPagarAguinaldo = diasPagarAguinaldo;
    }

    public BigDecimal getDiasPrimaVacasional() {
        return diasPrimaVacasional;
    }

    public void setDiasPrimaVacasional(BigDecimal diasPrimaVacasional) {
        this.diasPrimaVacasional = diasPrimaVacasional;
    }

    public BigDecimal getDiasAguinaldo() {
        return diasAguinaldo;
    }

    public void setDiasAguinaldo(BigDecimal diasAguinaldo) {
        this.diasAguinaldo = diasAguinaldo;
    }

    public BigDecimal getDiasExentoPrimaVacasional() {
        return diasExentoPrimaVacasional;
    }

    public void setDiasExentoPrimaVacasional(
            BigDecimal diasExentoPrimaVacasional) {
        this.diasExentoPrimaVacasional = diasExentoPrimaVacasional;
    }

    public BigDecimal getDiasExentoAguinaldo() {
        return diasExentoAguinaldo;
    }

    public void setDiasExentoAguinaldo(BigDecimal diasExentoAguinaldo) {
        this.diasExentoAguinaldo = diasExentoAguinaldo;
    }

    @Override
    public String toString() {
        return "ProductoNominaDTO{" + "idProductoNomina=" + idProductoNomina
                + ", idTipoPeriodo=" + idTipoPeriodo + ", idPeriodoCalendario="
                + idPeriodoCalendario + ", idUsuario=" + idUsuario
                + ", idEjercicioFiscal=" + idEjercicioFiscal
                + ", idEstatusProductoNomina=" + idEstatusProductoNomina
                + ", idBanco=" + idBanco + ", idCuentaBancaria="
                + idCuentaBancaria + ", idTipoContratacion="
                + idTipoContratacion + ", idFuenteFinanciamiento="
                + idFuenteFinanciamiento + ", idSubfuenteFinanciamiento="
                + idSubfuenteFinanciamiento + ", idTipoNomina=" + idTipoNomina
                + ", idArea=" + idArea + ", tipoPeriodo=" + tipoPeriodo
                + ", periodoCalendario=" + periodoCalendario + ", usuario="
                + usuario + ", ejercicioFiscal=" + ejercicioFiscal
                + ", estatusProductoNomina=" + estatusProductoNomina
                + ", banco=" + banco + ", cuentaBancaria=" + cuentaBancaria
                + ", tipoContratacion=" + tipoContratacion
                + ", fuenteFinanciamiento=" + fuenteFinanciamiento
                + ", subfuenteFinanciamiento=" + subfuenteFinanciamiento
                + ", tipoNomina=" + tipoNomina
                + ", cambiarFuenteFinanciamiento=" + cambiarFuenteFinanciamiento
                + ", anyoEjercicioFiscal=" + anyoEjercicioFiscal
                + ", inicioPeriodo=" + inicioPeriodo + ", finPeriodo="
                + finPeriodo + ", numeroPeriodo=" + numeroPeriodo
                + ", fechaPago=" + fechaPago + ", nombreProducto="
                + nombreProducto + ", numeroInicioCheques="
                + numeroInicioCheques + ", totalPercepciones="
                + totalPercepciones + ", totalDeducciones=" + totalDeducciones
                + ", totalNeto=" + totalNeto + ", totalSubsidio="
                + totalSubsidio + ", totalIsr=" + totalIsr + ", tipoRecurso="
                + tipoRecurso + ", diasPagarAguinaldo=" + diasPagarAguinaldo
                + ", inicioRangoFaltas=" + inicioRangoFaltas
                + ", finRangoFaltas=" + finRangoFaltas + ", idNominaEmpleado="
                + idNominaEmpleado + '}';
    }

    public Boolean getCalcularFaltas() {
        if (calcularFaltas == null) {
            calcularFaltas = Boolean.FALSE;
        }
        return calcularFaltas;
    }

    public void setCalcularFaltas(Boolean calcularFaltas) {
        this.calcularFaltas = calcularFaltas;
    }
}
