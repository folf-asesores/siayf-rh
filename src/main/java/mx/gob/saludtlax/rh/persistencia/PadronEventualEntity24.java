/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 15/11/2016 16:17:04
 */
@Entity
@Table(name = "personal_eventual_24")
public class PadronEventualEntity24 implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 585549243060628495L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEventual;

    @Column(name = "id_tipo_contratacion")
    private Integer tipoContratacion;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;

    @Column(name = "clave_centro")
    private String claveCentroResponsabilidad;

    @Column(name = "centro_responsabilidad")
    private String centroResponsabilidad;

    @Column(name = "funcion")
    private String funcion;

    @Column(name = "sup_05")
    private BigDecimal sueldoSuplente;

    @Column(name = "sueldo_01")
    private BigDecimal sueldo1;

    @Column(name = "comp_14")
    private BigDecimal sueldo14;

    @Column(name = "activo")
    private boolean activo;

    @Column(name = "alta")
    private boolean alta;

    @Column(name = "baja")
    private boolean baja;

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getIdEventual() {
        return idEventual;
    }

    public void setIdEventual(Integer idEventual) {
        this.idEventual = idEventual;
    }

    public Integer getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(Integer tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getClaveCentroResponsabilidad() {
        return claveCentroResponsabilidad;
    }

    public void setClaveCentroResponsabilidad(
            String claveCentroResponsabilidad) {
        this.claveCentroResponsabilidad = claveCentroResponsabilidad;
    }

    public String getCentroResponsabilidad() {
        return centroResponsabilidad;
    }

    public void setCentroResponsabilidad(String centroResponsabilidad) {
        this.centroResponsabilidad = centroResponsabilidad;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public BigDecimal getSueldoSuplente() {
        return sueldoSuplente;
    }

    public void setSueldoSuplente(BigDecimal sueldoSuplente) {
        this.sueldoSuplente = sueldoSuplente;
    }

    public BigDecimal getSueldo1() {
        return sueldo1;
    }

    public void setSueldo1(BigDecimal sueldo1) {
        this.sueldo1 = sueldo1;
    }

    public BigDecimal getSueldo14() {
        return sueldo14;
    }

    public void setSueldo14(BigDecimal sueldo14) {
        this.sueldo14 = sueldo14;
    }

}
