/*
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 15/06/2016 22:25:13
 */
public class InfoDependienteEconomicoDTO {

    private Integer idDependiente;
    private String nombreCompleto;
    private Date fechaNacimiento;
    private String curp;
    private String sexo;
    private String parentesco;
    private String otroParentesco;

    public Integer getIdDependiente() {
        return idDependiente;
    }

    public void setIdDependiente(Integer idDependiente) {
        this.idDependiente = idDependiente;
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
