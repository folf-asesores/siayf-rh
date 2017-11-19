/*
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 15/04/2016-12:12:34
 */
public class DependienteEconomicoDTO {

    private Integer idDependienteEconomico;
    private Integer idEmpleado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String curp;
    private Date fechaNacimiento;
    private String sexo;
    private String parentesco;
    private String otroParentesco;

    public Integer getIdDependienteEconomico() {
        return idDependienteEconomico;
    }

    public void setIdDependienteEconomico(Integer idDependienteEconomico) {
        this.idDependienteEconomico = idDependienteEconomico;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    @Override
    public String toString() {
        return "DependienteEconomicoDTO{" + "idDependienteEconomico="
                + idDependienteEconomico + ", idEmpleado=" + idEmpleado
                + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno
                + ", apellidoMaterno=" + apellidoMaterno + ", curp=" + curp
                + ", fechaNacimiento=" + fechaNacimiento + ", sexo=" + sexo
                + ", parentesco=" + parentesco + ", otroParentesco="
                + otroParentesco + '}';
    }
}
