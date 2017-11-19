/*
 *
 */

package mx.gob.saludtlax.rh.empleados.nombramientos.segurovidainstitucional;

import java.io.Serializable;
import java.util.Date;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class BeneficiariosDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8266721182160312991L;

    private Integer idBeneficiario;

    private Integer idSeguroVida;

    private Integer idDependienteEconomico;

    private Integer porcetaje;

    private String nombreCompleto;
    private Date fechaNacimiento;
    private String curp;
    private String sexo;
    private String parentesco;
    private String otroParentesco;

    public Integer getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(Integer idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public Integer getIdSeguroVida() {
        return idSeguroVida;
    }

    public void setIdSeguroVida(Integer idSeguroVida) {
        this.idSeguroVida = idSeguroVida;
    }

    public Integer getIdDependienteEconomico() {
        return idDependienteEconomico;
    }

    public void setIdDependienteEconomico(Integer idDependienteEconomico) {
        this.idDependienteEconomico = idDependienteEconomico;
    }

    public Integer getPorcetaje() {
        return porcetaje;
    }

    public void setPorcetaje(Integer porcetaje) {
        this.porcetaje = porcetaje;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getOtroParentesco() {
        return otroParentesco;
    }

    public void setOtroParentesco(String otroParentesco) {
        this.otroParentesco = otroParentesco;
    }

}
