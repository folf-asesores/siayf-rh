/*
 * PagoGeneralReporteDTO.java
 * Creado el 13/Feb/2017 5:02:48 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.pagogeneral;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class PagoGeneralReporteDTO implements Serializable {

    private static final long serialVersionUID = -7771614443915548225L;
    private static final Logger LOGGER = Logger.getLogger(PagoGeneralReporteDTO.class.getName());

    private String programa;
    private Integer numeroConsecutivoRegistro;
    private String mes;
    private String entidad;
    private String tipoCentroSalud;
    private String claveClues;
    private String nombreUnidad;
    private String areaAdscripcion;
    private String puesto;
    private String clavePuesto;
    private String servicio;
    private String rama;
    private String nombre;
    private String rfc;
    private String turno;
    private Date fechaIngreso;
    private String centroResponsabilidad;
    private String funcion;
    private BigDecimal _01Sueldo;
    private BigDecimal _14pComp;
    private BigDecimal _26Subsidio;
    private BigDecimal _29rFaltas;
    private BigDecimal _30Retroa;
    private BigDecimal _52Ispt;
    private BigDecimal _56EmbargoSalario;
    private BigDecimal percepcionTotal;
    private BigDecimal percepcionNeta;
    private BigDecimal isr;
    private BigDecimal faltas;
    private BigDecimal pensionAlimenticia;
    private BigDecimal deduccionTotal;
    private BigDecimal total;

    public PagoGeneralReporteDTO() {
        this("", // programa
                0, // numeroConsecutivoRegistro
                "", // mes
                "", // entidad
                "", // tipoCentroSalud
                "", // claveClues
                "", // nombreUnidad
                "", // areaAdscripcion
                "", // puesto
                "", // clavePuesto
                "", // servicio
                "", // rama
                "", // nombre
                "", // rfc
                "", // turno
                Calendar.getInstance().getTime(),// fechaIngreso
                "", // centroResponsabilidad
                "", // funcion
                BigDecimal.ZERO,// _01Sueldo
                BigDecimal.ZERO,// _14pComp
                BigDecimal.ZERO,// _26Subsidio
                BigDecimal.ZERO,// _29rFaltas
                BigDecimal.ZERO,// _30Retroa
                BigDecimal.ZERO,// _52Ispt
                BigDecimal.ZERO,// _56EmbargoSalario
                BigDecimal.ZERO,// percepcionTotal
                BigDecimal.ZERO,// percepcionNeta
                BigDecimal.ZERO,// isr
                BigDecimal.ZERO,// faltas
                BigDecimal.ZERO,// pensionAlimenticia
                BigDecimal.ZERO,// deduccionTotal
                BigDecimal.ZERO// total
        );
    }

    public PagoGeneralReporteDTO(String programa, Integer numeroConsecutivoRegistro, String mes, String entidad, String tipoCentroSalud, String claveClues,
            String nombreUnidad, String areaAdscripcion, String puesto, String clavePuesto, String servicio, String rama, String nombre, String rfc,
            String turno, Date fechaIngreso, String centroResponsabilidad, String funcion, BigDecimal _01Sueldo, BigDecimal _14pComp, BigDecimal _26Subsidio,
            BigDecimal _29rFaltas, BigDecimal _30Retroa, BigDecimal _52Ispt, BigDecimal _56EmbargoSalario, BigDecimal percepcionTotal,
            BigDecimal percepcionNeta, BigDecimal isr, BigDecimal faltas, BigDecimal pensionAlimenticia, BigDecimal deduccionTotal, BigDecimal total) {
        this.programa = programa;
        this.numeroConsecutivoRegistro = numeroConsecutivoRegistro;
        this.mes = mes;
        this.entidad = entidad;
        this.tipoCentroSalud = tipoCentroSalud;
        this.claveClues = claveClues;
        this.nombreUnidad = nombreUnidad;
        this.areaAdscripcion = areaAdscripcion;
        this.puesto = puesto;
        this.clavePuesto = clavePuesto;
        this.servicio = servicio;
        this.rama = rama;
        this.nombre = nombre;
        this.rfc = rfc;
        this.turno = turno;
        this.fechaIngreso = fechaIngreso;
        this.centroResponsabilidad = centroResponsabilidad;
        this.funcion = funcion;
        this._01Sueldo = _01Sueldo;
        this._14pComp = _14pComp;
        this._26Subsidio = _26Subsidio;
        this._29rFaltas = _29rFaltas;
        this._30Retroa = _30Retroa;
        this._52Ispt = _52Ispt;
        this._56EmbargoSalario = _56EmbargoSalario;
        this.percepcionTotal = percepcionTotal;
        this.percepcionNeta = percepcionNeta;
        this.isr = isr;
        this.faltas = faltas;
        this.pensionAlimenticia = pensionAlimenticia;
        this.deduccionTotal = deduccionTotal;
        this.total = total;
    }

    /**
     * Get the value of programa
     *
     * @return the value of programa
     */
    public String getPrograma() {
        return programa;
    }

    /**
     * Set the value of programa
     *
     * @param programa
     *            new value of programa
     */
    public void setPrograma(String programa) {
        this.programa = programa;
    }

    /**
     * Get the value of numeroConsecutivoRegistro
     *
     * @return the value of numeroConsecutivoRegistro
     */
    public Integer getNumeroConsecutivoRegistro() {
        return numeroConsecutivoRegistro;
    }

    /**
     * Set the value of numeroConsecutivoRegistro
     *
     * @param numeroConsecutivoRegistro
     *            new value of numeroConsecutivoRegistro
     */
    public void setNumeroConsecutivoRegistro(Integer numeroConsecutivoRegistro) {
        this.numeroConsecutivoRegistro = numeroConsecutivoRegistro;
    }

    /**
     * Get the value of mes
     *
     * @return the value of mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * Set the value of mes
     *
     * @param mes
     *            new value of mes
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * Get the value of entidad
     *
     * @return the value of entidad
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * Set the value of entidad
     *
     * @param entidad
     *            new value of entidad
     */
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    /**
     * Get the value of tipoCentroSalud
     *
     * @return the value of tipoCentroSalud
     */
    public String getTipoCentroSalud() {
        return tipoCentroSalud;
    }

    /**
     * Set the value of tipoCentroSalud
     *
     * @param tipoCentroSalud
     *            new value of tipoCentroSalud
     */
    public void setTipoCentroSalud(String tipoCentroSalud) {
        this.tipoCentroSalud = tipoCentroSalud;
    }

    /**
     * Get the value of claveClues
     *
     * @return the value of claveClues
     */
    public String getClaveClues() {
        return claveClues;
    }

    /**
     * Set the value of claveClues
     *
     * @param claveClues
     *            new value of claveClues
     */
    public void setClaveClues(String claveClues) {
        this.claveClues = claveClues;
    }

    /**
     * Get the value of nombreUnidad
     *
     * @return the value of nombreUnidad
     */
    public String getNombreUnidad() {
        return nombreUnidad;
    }

    /**
     * Set the value of nombreUnidad
     *
     * @param nombreUnidad
     *            new value of nombreUnidad
     */
    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    /**
     * Get the value of areaAdscripcion
     *
     * @return the value of areaAdscripcion
     */
    public String getAreaAdscripcion() {
        return areaAdscripcion;
    }

    /**
     * Set the value of areaAdscripcion
     *
     * @param areaAdscripcion
     *            new value of areaAdscripcion
     */
    public void setAreaAdscripcion(String areaAdscripcion) {
        this.areaAdscripcion = areaAdscripcion;
    }

    /**
     * Get the value of puesto
     *
     * @return the value of puesto
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * Set the value of puesto
     *
     * @param puesto
     *            new value of puesto
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * Get the value of clavePuesto
     *
     * @return the value of clavePuesto
     */
    public String getClavePuesto() {
        return clavePuesto;
    }

    /**
     * Set the value of clavePuesto
     *
     * @param clavePuesto
     *            new value of clavePuesto
     */
    public void setClavePuesto(String clavePuesto) {
        this.clavePuesto = clavePuesto;
    }

    /**
     * Get the value of servicio
     *
     * @return the value of servicio
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * Set the value of servicio
     *
     * @param servicio
     *            new value of servicio
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     * Get the value of rama
     *
     * @return the value of rama
     */
    public String getRama() {
        return rama;
    }

    /**
     * Set the value of rama
     *
     * @param rama
     *            new value of rama
     */
    public void setRama(String rama) {
        this.rama = rama;
    }

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre
     *            new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the value of rfc
     *
     * @return the value of rfc
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Set the value of rfc
     *
     * @param rfc
     *            new value of rfc
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * Get the value of turno
     *
     * @return the value of turno
     */
    public String getTurno() {
        return turno;
    }

    /**
     * Set the value of turno
     *
     * @param turno
     *            new value of turno
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * Get the value of fechaIngreso
     *
     * @return the value of fechaIngreso
     */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Set the value of fechaIngreso
     *
     * @param fechaIngreso
     *            new value of fechaIngreso
     */
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * Get the value of centroResponsabilidad
     *
     * @return the value of centroResponsabilidad
     */
    public String getCentroResponsabilidad() {
        return centroResponsabilidad;
    }

    /**
     * Set the value of centroResponsabilidad
     *
     * @param centroResponsabilidad
     *            new value of centroResponsabilidad
     */
    public void setCentroResponsabilidad(String centroResponsabilidad) {
        this.centroResponsabilidad = centroResponsabilidad;
    }

    /**
     * Get the value of funcion
     *
     * @return the value of funcion
     */
    public String getFuncion() {
        return funcion;
    }

    /**
     * Set the value of funcion
     *
     * @param funcion
     *            new value of funcion
     */
    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    /**
     * Get the value of _01Sueldo
     *
     * @return the value of _01Sueldo
     */
    public BigDecimal get01Sueldo() {
        return _01Sueldo;
    }

    /**
     * Set the value of _01Sueldo
     *
     * @param _01Sueldo
     *            new value of _01Sueldo
     */
    public void set01Sueldo(BigDecimal _01Sueldo) {
        this._01Sueldo = _01Sueldo == null ? BigDecimal.ZERO : _01Sueldo;
    }

    /**
     * Get the value of _14pComp
     *
     * @return the value of _14pComp
     */
    public BigDecimal get14pComp() {
        return _14pComp;
    }

    /**
     * Set the value of _14pComp
     *
     * @param _14pComp
     *            new value of _14pComp
     */
    public void set14pComp(BigDecimal _14pComp) {
        this._14pComp = _14pComp == null ? BigDecimal.ZERO : _14pComp;
    }

    /**
     * Get the value of _26Subsidio
     *
     * @return the value of _26Subsidio
     */
    public BigDecimal get26Subsidio() {
        return _26Subsidio;
    }

    /**
     * Set the value of _26Subsidio
     *
     * @param _26Subsidio
     *            new value of _26Subsidio
     */
    public void set26Subsidio(BigDecimal _26Subsidio) {
        this._26Subsidio = _26Subsidio == null ? BigDecimal.ZERO : _26Subsidio;
    }

    /**
     * Get the value of _29rFaltas
     *
     * @return the value of _29rFaltas
     */
    public BigDecimal get29rFaltas() {
        return _29rFaltas;
    }

    /**
     * Set the value of _29rFaltas
     *
     * @param _29rFaltas
     *            new value of _29rFaltas
     */
    public void set29rFaltas(BigDecimal _29rFaltas) {
        this._29rFaltas = _29rFaltas == null ? BigDecimal.ZERO : _29rFaltas;
    }

    /**
     * Get the value of _30Retroa
     *
     * @return the value of _30Retroa
     */
    public BigDecimal get30Retroa() {
        return _30Retroa;
    }

    /**
     * Set the value of _30Retroa
     *
     * @param _30Retroa
     *            new value of _30Retroa
     */
    public void set30Retroa(BigDecimal _30Retroa) {
        this._30Retroa = _30Retroa == null ? BigDecimal.ZERO : _30Retroa;
    }

    /**
     * Get the value of _52Ispt
     *
     * @return the value of _52Ispt
     */
    public BigDecimal get52Ispt() {
        return _52Ispt;
    }

    /**
     * Set the value of _52Ispt
     *
     * @param _52Ispt
     *            new value of _52Ispt
     */
    public void set52Ispt(BigDecimal _52Ispt) {
        this._52Ispt = _52Ispt == null ? BigDecimal.ZERO : _52Ispt;
    }

    /**
     * Get the value of _56EmbargoSalario
     *
     * @return the value of _56EmbargoSalario
     */
    public BigDecimal get56EmbargoSalario() {
        return _56EmbargoSalario;
    }

    /**
     * Set the value of _56EmbargoSalario
     *
     * @param _56EmbargoSalario
     *            new value of _56EmbargoSalario
     */
    public void set56EmbargoSalario(BigDecimal _56EmbargoSalario) {
        this._56EmbargoSalario = _56EmbargoSalario == null ? BigDecimal.ZERO : _56EmbargoSalario;
    }

    /**
     * Get the value of percepcionTotal
     *
     * @return the value of percepcionTotal
     */
    public BigDecimal getPercepcionTotal() {
        return percepcionTotal;
    }

    /**
     * Set the value of percepcionTotal
     *
     * @param percepcionTotal
     *            new value of percepcionTotal
     */
    public void setPercepcionTotal(BigDecimal percepcionTotal) {
        this.percepcionTotal = percepcionTotal == null ? BigDecimal.ZERO : percepcionTotal;
    }

    /**
     * Get the value of percepcionNeta
     *
     * @return the value of percepcionNeta
     */
    public BigDecimal getPercepcionNeta() {
        return percepcionNeta;
    }

    /**
     * Set the value of percepcionNeta
     *
     * @param percepcionNeta
     *            new value of percepcionNeta
     */
    public void setPercepcionNeta(BigDecimal percepcionNeta) {
        this.percepcionNeta = percepcionNeta == null ? BigDecimal.ZERO : percepcionNeta;
    }

    /**
     * Get the value of isr
     *
     * @return the value of isr
     */
    public BigDecimal getIsr() {
        return isr;
    }

    /**
     * Set the value of isr
     *
     * @param isr
     *            new value of isr
     */
    public void setIsr(BigDecimal isr) {
        this.isr = isr == null ? BigDecimal.ZERO : isr;
    }

    /**
     * Get the value of faltas
     *
     * @return the value of faltas
     */
    public BigDecimal getFaltas() {
        return faltas;
    }

    /**
     * Set the value of faltas
     *
     * @param faltas
     *            new value of faltas
     */
    public void setFaltas(BigDecimal faltas) {
        this.faltas = faltas == null ? BigDecimal.ZERO : faltas;
    }

    /**
     * Get the value of pensionAlimenticia
     *
     * @return the value of pensionAlimenticia
     */
    public BigDecimal getPensionAlimenticia() {
        return pensionAlimenticia;
    }

    /**
     * Set the value of pensionAlimenticia
     *
     * @param pensionAlimenticia
     *            new value of pensionAlimenticia
     */
    public void setPensionAlimenticia(BigDecimal pensionAlimenticia) {
        this.pensionAlimenticia = pensionAlimenticia == null ? BigDecimal.ZERO : pensionAlimenticia;
    }

    /**
     * Get the value of deduccionTotal
     *
     * @return the value of deduccionTotal
     */
    public BigDecimal getDeduccionTotal() {
        return deduccionTotal;
    }

    /**
     * Set the value of deduccionTotal
     *
     * @param deduccionTotal
     *            new value of deduccionTotal
     */
    public void setDeduccionTotal(BigDecimal deduccionTotal) {
        this.deduccionTotal = deduccionTotal == null ? BigDecimal.ZERO : deduccionTotal;
    }

    /**
     * Get the value of total
     *
     * @return the value of total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Set the value of total
     *
     * @param total
     *            new value of total
     */
    public void setTotal(BigDecimal total) {
        this.total = total == null ? BigDecimal.ZERO : total;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(programa);
        hash = 61 * hash + Objects.hashCode(numeroConsecutivoRegistro);
        hash = 61 * hash + Objects.hashCode(mes);
        hash = 61 * hash + Objects.hashCode(entidad);
        hash = 61 * hash + Objects.hashCode(tipoCentroSalud);
        hash = 61 * hash + Objects.hashCode(claveClues);
        hash = 61 * hash + Objects.hashCode(nombreUnidad);
        hash = 61 * hash + Objects.hashCode(areaAdscripcion);
        hash = 61 * hash + Objects.hashCode(puesto);
        hash = 61 * hash + Objects.hashCode(clavePuesto);
        hash = 61 * hash + Objects.hashCode(servicio);
        hash = 61 * hash + Objects.hashCode(rama);
        hash = 61 * hash + Objects.hashCode(nombre);
        hash = 61 * hash + Objects.hashCode(rfc);
        hash = 61 * hash + Objects.hashCode(turno);
        hash = 61 * hash + Objects.hashCode(fechaIngreso);
        hash = 61 * hash + Objects.hashCode(centroResponsabilidad);
        hash = 61 * hash + Objects.hashCode(funcion);
        hash = 61 * hash + Objects.hashCode(_01Sueldo);
        hash = 61 * hash + Objects.hashCode(_14pComp);
        hash = 61 * hash + Objects.hashCode(_26Subsidio);
        hash = 61 * hash + Objects.hashCode(_29rFaltas);
        hash = 61 * hash + Objects.hashCode(_30Retroa);
        hash = 61 * hash + Objects.hashCode(_52Ispt);
        hash = 61 * hash + Objects.hashCode(_56EmbargoSalario);
        hash = 61 * hash + Objects.hashCode(percepcionTotal);
        hash = 61 * hash + Objects.hashCode(percepcionNeta);
        hash = 61 * hash + Objects.hashCode(isr);
        hash = 61 * hash + Objects.hashCode(faltas);
        hash = 61 * hash + Objects.hashCode(pensionAlimenticia);
        hash = 61 * hash + Objects.hashCode(deduccionTotal);
        hash = 61 * hash + Objects.hashCode(total);
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
        final PagoGeneralReporteDTO other = (PagoGeneralReporteDTO) obj;
        if (!Objects.equals(programa, other.programa)) {
            return false;
        }
        if (!Objects.equals(mes, other.mes)) {
            return false;
        }
        if (!Objects.equals(entidad, other.entidad)) {
            return false;
        }
        if (!Objects.equals(tipoCentroSalud, other.tipoCentroSalud)) {
            return false;
        }
        if (!Objects.equals(claveClues, other.claveClues)) {
            return false;
        }
        if (!Objects.equals(nombreUnidad, other.nombreUnidad)) {
            return false;
        }
        if (!Objects.equals(areaAdscripcion, other.areaAdscripcion)) {
            return false;
        }
        if (!Objects.equals(puesto, other.puesto)) {
            return false;
        }
        if (!Objects.equals(clavePuesto, other.clavePuesto)) {
            return false;
        }
        if (!Objects.equals(servicio, other.servicio)) {
            return false;
        }
        if (!Objects.equals(rama, other.rama)) {
            return false;
        }
        if (!Objects.equals(nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(rfc, other.rfc)) {
            return false;
        }
        if (!Objects.equals(turno, other.turno)) {
            return false;
        }
        if (!Objects.equals(centroResponsabilidad, other.centroResponsabilidad)) {
            return false;
        }
        if (!Objects.equals(funcion, other.funcion)) {
            return false;
        }
        if (!Objects.equals(numeroConsecutivoRegistro, other.numeroConsecutivoRegistro)) {
            return false;
        }
        if (!Objects.equals(fechaIngreso, other.fechaIngreso)) {
            return false;
        }
        if (!Objects.equals(_01Sueldo, other._01Sueldo)) {
            return false;
        }
        if (!Objects.equals(_14pComp, other._14pComp)) {
            return false;
        }
        if (!Objects.equals(_26Subsidio, other._26Subsidio)) {
            return false;
        }
        if (!Objects.equals(_29rFaltas, other._29rFaltas)) {
            return false;
        }
        if (!Objects.equals(_30Retroa, other._30Retroa)) {
            return false;
        }
        if (!Objects.equals(_52Ispt, other._52Ispt)) {
            return false;
        }
        if (!Objects.equals(_56EmbargoSalario, other._56EmbargoSalario)) {
            return false;
        }
        if (!Objects.equals(percepcionTotal, other.percepcionTotal)) {
            return false;
        }
        if (!Objects.equals(percepcionNeta, other.percepcionNeta)) {
            return false;
        }
        if (!Objects.equals(isr, other.isr)) {
            return false;
        }
        if (!Objects.equals(faltas, other.faltas)) {
            return false;
        }
        if (!Objects.equals(pensionAlimenticia, other.pensionAlimenticia)) {
            return false;
        }
        if (!Objects.equals(deduccionTotal, other.deduccionTotal)) {
            return false;
        }
        return Objects.equals(total, other.total);
    }

    @Override
    public String toString() {
        return "PagoQuincenaDTO{" + "programa=" + programa + ", numeroConsecutivoRegistro=" + numeroConsecutivoRegistro + ", mes=" + mes + ", entidad="
                + entidad + ", tipoCentroSalud=" + tipoCentroSalud + ", claveClues=" + claveClues + ", nombreUnidad=" + nombreUnidad + ", areaAdscripcion="
                + areaAdscripcion + ", puesto=" + puesto + ", clavePuesto=" + clavePuesto + ", servicio=" + servicio + ", rama=" + rama + ", nombre=" + nombre
                + ", rfc=" + rfc + ", turno=" + turno + ", fechaIngreso=" + fechaIngreso + ", centroResponsabilidad=" + centroResponsabilidad + ", funcion="
                + funcion + ", _01Sueldo=" + _01Sueldo + ", _14pComp=" + _14pComp + ", _26Subsidio=" + _26Subsidio + ", _29rFaltas=" + _29rFaltas
                + ", _30Retroa=" + _30Retroa + ", _52Ispt=" + _52Ispt + ", _56EmbargoSalario=" + _56EmbargoSalario + ", percepcionTotal=" + percepcionTotal
                + ", percepcionNeta=" + percepcionNeta + ", isr=" + isr + ", faltas=" + faltas + ", pensionAlimenticia=" + pensionAlimenticia
                + ", deduccionTotal=" + deduccionTotal + ", total=" + total + '}';
    }

}
