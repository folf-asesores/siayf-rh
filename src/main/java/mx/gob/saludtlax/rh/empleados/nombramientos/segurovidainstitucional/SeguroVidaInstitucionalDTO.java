/*
 *
 */

package mx.gob.saludtlax.rh.empleados.nombramientos.segurovidainstitucional;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Eduardo Mex
 *
 */
public class SeguroVidaInstitucionalDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7698734370495660475L;

    private Integer idSeguroVida;

    private Integer idEmpleado;

    private boolean estatus;

    private Date fechaFinDia;

    private Time horaFinDia;

    private String numeroExpediente;

    private List<BeneficiariosDTO> beneficiariosDTOs = new ArrayList<>();

    public Integer getIdSeguroVida() {
        return idSeguroVida;
    }

    public void setIdSeguroVida(Integer idSeguroVida) {
        this.idSeguroVida = idSeguroVida;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public Date getFechaFinDia() {
        return fechaFinDia;
    }

    public void setFechaFinDia(Date fechaFinDia) {
        this.fechaFinDia = fechaFinDia;
    }

    public Time getHoraFinDia() {
        return horaFinDia;
    }

    public void setHoraFinDia(Time horaFinDia) {
        this.horaFinDia = horaFinDia;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public List<BeneficiariosDTO> getBeneficiariosDTOs() {
        return beneficiariosDTOs;
    }

    public void setBeneficiariosDTOs(List<BeneficiariosDTO> beneficiariosDTOs) {
        this.beneficiariosDTOs = beneficiariosDTOs;
    }

}
