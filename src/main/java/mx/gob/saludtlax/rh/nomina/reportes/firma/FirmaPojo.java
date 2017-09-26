/*
 * FirmaPojo.java
 * Creado el 08/sep/2017 4:32:12 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.firma;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class FirmaPojo {

    private Integer idProductoNomina;
    private Integer idPrograma;
    private String programa;
    private String clave;
    private String descripcion;
    private Date fechaPago;
    private String filiacion;
    private String nombre;
    private Date inicioPeriodo;
    private Date finPeriodo;
    private String numeroCheque;
    private BigDecimal neto;
    private Integer consecutivo;
    private String jefe1Nombre;
    private String jefe1Cargo;
    private String jefe2Nombre;
    private String jefe2Cargo;
    private String jefe3Nombre;
    private String jefe3Cargo;

    public Integer getIdProductoNomina() {
        return idProductoNomina;
    }

    public void setIdProductoNomina(Integer idProductoNomina) {
        this.idProductoNomina = idProductoNomina;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFiliacion() {
        return filiacion;
    }

    public void setFiliacion(String filiacion) {
        this.filiacion = filiacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public BigDecimal getNeto() {
        return neto;
    }

    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getJefe1Nombre() {
        return jefe1Nombre;
    }

    public void setJefe1Nombre(String jefe1Nombre) {
        this.jefe1Nombre = jefe1Nombre;
    }

    public String getJefe1Cargo() {
        return jefe1Cargo;
    }

    public void setJefe1Cargo(String jefe1Cargo) {
        this.jefe1Cargo = jefe1Cargo;
    }

    public String getJefe2Nombre() {
        return jefe2Nombre;
    }

    public void setJefe2Nombre(String jefe2Nombre) {
        this.jefe2Nombre = jefe2Nombre;
    }

    public String getJefe2Cargo() {
        return jefe2Cargo;
    }

    public void setJefe2Cargo(String jefe2Cargo) {
        this.jefe2Cargo = jefe2Cargo;
    }

    public String getJefe3Nombre() {
        return jefe3Nombre;
    }

    public void setJefe3Nombre(String jefe3Nombre) {
        this.jefe3Nombre = jefe3Nombre;
    }

    public String getJefe3Cargo() {
        return jefe3Cargo;
    }

    public void setJefe3Cargo(String jefe3Cargo) {
        this.jefe3Cargo = jefe3Cargo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idProductoNomina);
        hash = 67 * hash + Objects.hashCode(this.idPrograma);
        hash = 67 * hash + Objects.hashCode(this.programa);
        hash = 67 * hash + Objects.hashCode(this.clave);
        hash = 67 * hash + Objects.hashCode(this.descripcion);
        hash = 67 * hash + Objects.hashCode(this.fechaPago);
        hash = 67 * hash + Objects.hashCode(this.filiacion);
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.inicioPeriodo);
        hash = 67 * hash + Objects.hashCode(this.finPeriodo);
        hash = 67 * hash + Objects.hashCode(this.numeroCheque);
        hash = 67 * hash + Objects.hashCode(this.neto);
        hash = 67 * hash + Objects.hashCode(this.consecutivo);
        hash = 67 * hash + Objects.hashCode(this.jefe1Nombre);
        hash = 67 * hash + Objects.hashCode(this.jefe1Cargo);
        hash = 67 * hash + Objects.hashCode(this.jefe2Nombre);
        hash = 67 * hash + Objects.hashCode(this.jefe2Cargo);
        hash = 67 * hash + Objects.hashCode(this.jefe3Nombre);
        hash = 67 * hash + Objects.hashCode(this.jefe3Cargo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FirmaPojo other = (FirmaPojo) obj;
        if (!Objects.equals(this.programa, other.programa)) {
            return false;
        }
        if (!Objects.equals(this.clave, other.clave)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.filiacion, other.filiacion)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.numeroCheque, other.numeroCheque)) {
            return false;
        }
        if (!Objects.equals(this.jefe1Nombre, other.jefe1Nombre)) {
            return false;
        }
        if (!Objects.equals(this.jefe1Cargo, other.jefe1Cargo)) {
            return false;
        }
        if (!Objects.equals(this.jefe2Nombre, other.jefe2Nombre)) {
            return false;
        }
        if (!Objects.equals(this.jefe2Cargo, other.jefe2Cargo)) {
            return false;
        }
        if (!Objects.equals(this.jefe3Nombre, other.jefe3Nombre)) {
            return false;
        }
        if (!Objects.equals(this.jefe3Cargo, other.jefe3Cargo)) {
            return false;
        }
        if (!Objects.equals(this.idProductoNomina, other.idProductoNomina)) {
            return false;
        }
        if (!Objects.equals(this.idPrograma, other.idPrograma)) {
            return false;
        }
        if (!Objects.equals(this.fechaPago, other.fechaPago)) {
            return false;
        }
        if (!Objects.equals(this.inicioPeriodo, other.inicioPeriodo)) {
            return false;
        }
        if (!Objects.equals(this.finPeriodo, other.finPeriodo)) {
            return false;
        }
        if (!Objects.equals(this.neto, other.neto)) {
            return false;
        }
        return Objects.equals(this.consecutivo, other.consecutivo);
    }

    @Override
    public String toString() {
        return "FirmaPojo{" 
                + "idProductoNomina : " + idProductoNomina
                + ", idPrograma : " + idPrograma
                + ", programa : " + programa
                + ", clave : " + clave
                + ", descripcion : " + descripcion
                + ", fechaPago : " + fechaPago
                + ", filiacion : " + filiacion
                + ", nombre : " + nombre
                + ", inicioPeriodo : " + inicioPeriodo
                + ", finPeriodo : " + finPeriodo
                + ", numeroCheque : " + numeroCheque
                + ", neto : " + neto
                + ", consecutivo : " + consecutivo
                + ", jefe1Nombre : " + jefe1Nombre
                + ", jefe1Cargo : " + jefe1Cargo
                + ", jefe2Nombre : " + jefe2Nombre
                + ", jefe2Cargo : " + jefe2Cargo
                + ", jefe3Nombre : " + jefe3Nombre
                + ", jefe3Cargo : " + jefe3Cargo
                + '}';
    }

}
