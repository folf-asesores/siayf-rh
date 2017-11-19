/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 01/04/2016-11:58:08
 */
@Entity
@Table(name = "dependientes_economicos")
public class DependienteEconomicoEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1007133551768044288L;
    @Id
    @Column(name = "id_dependiente_economico")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDependiente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "fecha_nacimiento")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "curp")
    private String curp;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "id_parentesco")
    private String idParentesco;

    @Column(name = "otro_parentesco")
    private String otroParentesco;

    public String generarNombreCompleto() {
        StringBuilder nombreCompletoSB = new StringBuilder(nombre);
        nombreCompletoSB.append(' ');

        if (apellidoPaterno != null || !apellidoPaterno.trim().isEmpty()) {
            nombreCompletoSB.append(apellidoPaterno);
            nombreCompletoSB.append(' ');
        }

        nombreCompletoSB.append(apellidoMaterno);

        return nombreCompletoSB.toString();
    }

    public Integer getIdDependiente() {
        return idDependiente;
    }

    public void setIdDependiente(Integer idDependiente) {
        this.idDependiente = idDependiente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre != null ? nombre.toUpperCase() : null;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno != null
                ? apellidoPaterno.toUpperCase() : null;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno != null
                ? apellidoMaterno.toUpperCase() : null;
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
        this.curp = curp != null ? curp.toUpperCase() : null;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getIdParentesco() {
        return idParentesco;
    }

    public void setIdParentesco(String idParentesco) {
        this.idParentesco = idParentesco;
    }

    public String getOtroParentesco() {
        return otroParentesco;
    }

    public void setOtroParentesco(String otroParentesco) {
        this.otroParentesco = otroParentesco != null
                ? otroParentesco.toUpperCase() : null;
    }
}
