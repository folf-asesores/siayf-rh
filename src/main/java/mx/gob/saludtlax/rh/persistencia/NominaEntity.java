
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Nomina")
public class NominaEntity implements Serializable {
    private static final long serialVersionUID = -1561853853521389289L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdNomina")
    private Integer idNomina;
    @Column(name = "Version")
    private String version;
    @Column(name = "RegistroPatronal")
    private String registroPatronal;
    @Column(name = "NumEmpleado")
    private String numEmpleado;
    @Column(name = "CURP")
    private String curp;
    @Column(name = "TipoRegimen")
    private int tipoRegimen;
    @Column(name = "NumSeguridadSocial")
    private String numSeguridadSocial;
    @Column(name = "FechaPago")
    private Date fechaPago;
    @Column(name = "FechaInicialPago")
    private Date fechaInicialPago;
    @Column(name = "FechaFinalPago")
    private Date fechaFinalPago;
    @Column(name = "NumDiasPagados")
    private BigDecimal numDiasPagados;
    @Column(name = "Departamento")
    private String departamento;
    @Column(name = "Clabe")
    private BigInteger clabe;
    @Column(name = "Banco")
    private Integer banco;
    @Column(name = "FechaInicioRelLaboral")
    private Date fechaInicioRelLaboral;
    @Column(name = "Antiguedad")
    private Integer antiguedad;
    @Column(name = "Puesto")
    private String puesto;
    @Column(name = "TipoContrato")
    private String tipoContrato;
    @Column(name = "TipoJornada")
    private String tipoJornada;
    @Column(name = "PeriodicidadPago")
    private String periodicidadPago;
    @Column(name = "SalarioBaseCotApor")
    private BigDecimal salarioBaseCotApor;
    @Column(name = "RiesgoPuesto")
    private Integer riesgoPuesto;
    @Column(name = "SalarioDiarioIntegrado")
    private BigDecimal salarioDiarioIntegrado;

    @Column(name = "PercepcionesTotalGravado")
    private BigDecimal percepcionesTotalGravado;
    @Column(name = "PercepcionesTotalExento")
    private BigDecimal percepcionesTotalExento;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdNomina", referencedColumnName = "IdNomina")
    private List<PercepcionComprobanteEntity> percepciones;

    @Column(name = "DeduccionesTotalGravado")
    private BigDecimal deduccionesTotalGravado;
    @Column(name = "DeduccionesTotalExento")
    private BigDecimal deduccionesTotalExento;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdNomina", referencedColumnName = "IdNomina")
    private List<DeduccionComprobanteEntity> deducciones;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdNomina", referencedColumnName = "IdNomina")
    private List<IncapacidadComprobanteEntity> incapacidades;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdNomina", referencedColumnName = "IdNomina")
    private List<HoraExtraComprobanteEntity> horasExtra;

    public Integer getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(Integer idNomina) {
        this.idNomina = idNomina;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRegistroPatronal() {
        return registroPatronal;
    }

    public void setRegistroPatronal(String registroPatronal) {
        this.registroPatronal = registroPatronal;
    }

    public String getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(String numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public int getTipoRegimen() {
        return tipoRegimen;
    }

    public void setTipoRegimen(int tipoRegimen) {
        this.tipoRegimen = tipoRegimen;
    }

    public String getNumSeguridadSocial() {
        return numSeguridadSocial;
    }

    public void setNumSeguridadSocial(String numSeguridadSocial) {
        this.numSeguridadSocial = numSeguridadSocial;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaInicialPago() {
        return fechaInicialPago;
    }

    public void setFechaInicialPago(Date fechaInicialPago) {
        this.fechaInicialPago = fechaInicialPago;
    }

    public Date getFechaFinalPago() {
        return fechaFinalPago;
    }

    public void setFechaFinalPago(Date fechaFinalPago) {
        this.fechaFinalPago = fechaFinalPago;
    }

    public BigDecimal getNumDiasPagados() {
        return numDiasPagados;
    }

    public void setNumDiasPagados(BigDecimal numDiasPagados) {
        this.numDiasPagados = numDiasPagados;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public BigInteger getClabe() {
        return clabe;
    }

    public void setClabe(BigInteger clabe) {
        this.clabe = clabe;
    }

    public Integer getBanco() {
        return banco;
    }

    public void setBanco(Integer banco) {
        this.banco = banco;
    }

    public Date getFechaInicioRelLaboral() {
        return fechaInicioRelLaboral;
    }

    public void setFechaInicioRelLaboral(Date fechaInicioRelLaboral) {
        this.fechaInicioRelLaboral = fechaInicioRelLaboral;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getTipoJornada() {
        return tipoJornada;
    }

    public void setTipoJornada(String tipoJornada) {
        this.tipoJornada = tipoJornada;
    }

    public String getPeriodicidadPago() {
        return periodicidadPago;
    }

    public void setPeriodicidadPago(String periodicidadPago) {
        this.periodicidadPago = periodicidadPago;
    }

    public BigDecimal getSalarioBaseCotApor() {
        return salarioBaseCotApor;
    }

    public void setSalarioBaseCotApor(BigDecimal salarioBaseCotApor) {
        this.salarioBaseCotApor = salarioBaseCotApor;
    }

    public Integer getRiesgoPuesto() {
        return riesgoPuesto;
    }

    public void setRiesgoPuesto(Integer riesgoPuesto) {
        this.riesgoPuesto = riesgoPuesto;
    }

    public BigDecimal getSalarioDiarioIntegrado() {
        return salarioDiarioIntegrado;
    }

    public void setSalarioDiarioIntegrado(BigDecimal salarioDiarioIntegrado) {
        this.salarioDiarioIntegrado = salarioDiarioIntegrado;
    }

    public BigDecimal getPercepcionesTotalGravado() {
        return percepcionesTotalGravado;
    }

    public void setPercepcionesTotalGravado(BigDecimal percepcionesTotalGravado) {
        this.percepcionesTotalGravado = percepcionesTotalGravado;
    }

    public BigDecimal getPercepcionesTotalExento() {
        return percepcionesTotalExento;
    }

    public void setPercepcionesTotalExento(BigDecimal percepcionesTotalExento) {
        this.percepcionesTotalExento = percepcionesTotalExento;
    }

    public List<PercepcionComprobanteEntity> getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(List<PercepcionComprobanteEntity> percepciones) {
        this.percepciones = percepciones;
    }

    public BigDecimal getDeduccionesTotalGravado() {
        return deduccionesTotalGravado;
    }

    public void setDeduccionesTotalGravado(BigDecimal deduccionesTotalGravado) {
        this.deduccionesTotalGravado = deduccionesTotalGravado;
    }

    public BigDecimal getDeduccionesTotalExento() {
        return deduccionesTotalExento;
    }

    public void setDeduccionesTotalExento(BigDecimal deduccionesTotalExento) {
        this.deduccionesTotalExento = deduccionesTotalExento;
    }

    public List<DeduccionComprobanteEntity> getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(List<DeduccionComprobanteEntity> deducciones) {
        this.deducciones = deducciones;
    }

    public List<IncapacidadComprobanteEntity> getIncapacidades() {
        return incapacidades;
    }

    public void setIncapacidades(List<IncapacidadComprobanteEntity> incapacidades) {
        this.incapacidades = incapacidades;
    }

    public List<HoraExtraComprobanteEntity> getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(List<HoraExtraComprobanteEntity> horasExtra) {
        this.horasExtra = horasExtra;
    }
}