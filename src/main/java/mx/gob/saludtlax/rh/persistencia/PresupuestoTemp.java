/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 25/07/2016 14:16:03
 */
@Entity
@Table(name = "presupuestos_temp")
public class PresupuestoTemp {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clave_presupuestal")
    private String clavePresupuestal;

    @Column(name = "id_mes")
    private Integer idMes;

    @Column(name = "por_ejercer")
    private BigDecimal porEjercer;

    @Column(name = "comprometido")
    private BigDecimal comprometido;

    @Column(name = "devengado")
    private BigDecimal devengado;

    @Column(name = "ejercido")
    private BigDecimal ejercido;

    @Column(name = "pagado")
    private BigDecimal pagado;

    @Override
    public String toString() {
        return "PresupuestoEntity [clave presupuestal=" + clavePresupuestal
                + ", id mes=" + idMes + ", por ejercer=" + porEjercer
                + ", compremetido=" + comprometido + ", devengado=" + devengado
                + ", ejercicio=" + ejercido + ", pagado=" + pagado + "]";
    }

    /**
     * @return the clavePresupuestal
     */
    public String getClavePresupuestal() {
        return clavePresupuestal;
    }

    /**
     * @param clavePresupuestal
     *            the clavePresupuestal to set
     */
    public void setClavePresupuestal(String clavePresupuestal) {
        this.clavePresupuestal = clavePresupuestal;
    }

    /**
     * @return the idMes
     */
    public Integer getIdMes() {
        return idMes;
    }

    /**
     * @param idMes
     *            the idMes to set
     */
    public void setIdMes(Integer idMes) {
        this.idMes = idMes;
    }

    /**
     * @return the porEjercer
     */
    public BigDecimal getPorEjercer() {
        return porEjercer;
    }

    /**
     * @param porEjercer
     *            the porEjercer to set
     */
    public void setPorEjercer(BigDecimal porEjercer) {
        this.porEjercer = porEjercer;
    }

    /**
     * @return the comprometido
     */
    public BigDecimal getComprometido() {
        return comprometido;
    }

    /**
     * @param comprometido
     *            the comprometido to set
     */
    public void setComprometido(BigDecimal comprometido) {
        this.comprometido = comprometido;
    }

    /**
     * @return the devengado
     */
    public BigDecimal getDevengado() {
        return devengado;
    }

    /**
     * @param devengado
     *            the devengado to set
     */
    public void setDevengado(BigDecimal devengado) {
        this.devengado = devengado;
    }

    /**
     * @return the ejercido
     */
    public BigDecimal getEjercido() {
        return ejercido;
    }

    /**
     * @param ejercido
     *            the ejercido to set
     */
    public void setEjercido(BigDecimal ejercido) {
        this.ejercido = ejercido;
    }

    /**
     * @return the pagado
     */
    public BigDecimal getPagado() {
        return pagado;
    }

    /**
     * @param pagado
     *            the pagado to set
     */
    public void setPagado(BigDecimal pagado) {
        this.pagado = pagado;
    }

}
