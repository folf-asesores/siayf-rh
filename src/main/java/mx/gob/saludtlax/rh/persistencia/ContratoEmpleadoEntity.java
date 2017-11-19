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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Leila Schiaffini Ehuan
 * @since 05/09/2016 12:27:40
 *
 */
@Entity
@Table(name = "contratos_empleados")
public class ContratoEmpleadoEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8170143972946495205L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato")
    private Integer idContrato;

    @Column(name = "tipo_contrato")
    private Integer tipoContrato;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Column(name = "numero_contrato")
    private String numeroContrato;

    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "id_vacante")
    private Integer idVacante;

    @Column(name = "activo")
    private boolean activo;

    @Column(name = "sueldo_mensual")
    private BigDecimal sueldoMensual;

    @Column(name = "descripcion_sueldo")
    private String descripcionSueldo;

    @Column(name = "impreso")
    private boolean impreso;

    @Column(name = "fecha_impresion")
    @Temporal(TemporalType.DATE)
    private Date fechaImpreso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programa")
    private ProgramaEntity programa;

    public ProgramaEntity getPrograma() {
        return programa;
    }

    public void setPrograma(ProgramaEntity programa) {
        this.programa = programa;
    }

    /**
     * @return the impreso
     */
    public boolean isImpreso() {
        return impreso;
    }

    /**
     * @param impreso
     *            the impreso to set
     */
    public void setImpreso(boolean impreso) {
        this.impreso = impreso;
    }

    /**
     * @return the fechaImpreso
     */
    public Date getFechaImpreso() {
        return fechaImpreso;
    }

    /**
     * @param fechaImpreso
     *            the fechaImpreso to set
     */
    public void setFechaImpreso(Date fechaImpreso) {
        this.fechaImpreso = fechaImpreso;
    }

    /**
     * @param idContrato
     *            the idContrato to set
     */
    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(Integer tipoContrato) {
        this.tipoContrato = tipoContrato;
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

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public BigDecimal getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(BigDecimal sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public String getDescripcionSueldo() {
        return descripcionSueldo;
    }

    public void setDescripcionSueldo(String descripcionSueldo) {
        this.descripcionSueldo = descripcionSueldo;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

}
