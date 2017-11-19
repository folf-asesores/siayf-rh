/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 21/07/2016 10:11:22
 */
@Entity
@Table(name = "puestos_generales")
public class PuestoGeneralEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4969884053592817780L;

    @Id
    @Column(name = "id_puesto_general")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPuestoGeneral;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "puesto")
    private String puesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_puesto")
    private TipoPuestoEntity idTipoPuesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rama")
    private RamaEntity idRama;

    @Column(name = "puesto_timbre")
    private String puestoTimbre;

    /**
     * @return the idPuestoGeneral
     */
    public Integer getIdPuestoGeneral() {
        return idPuestoGeneral;
    }

    /**
     * @param idPuestoGeneral
     *            the idPuestoGeneral to set
     */
    public void setIdPuestoGeneral(Integer idPuestoGeneral) {
        this.idPuestoGeneral = idPuestoGeneral;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the puesto
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * @param puesto
     *            the puesto to set
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * @return the idRama
     */
    public RamaEntity getIdRama() {
        return idRama;
    }

    /**
     * @param idRama
     *            the idRama to set
     */
    public void setIdRama(RamaEntity idRama) {
        this.idRama = idRama;
    }

    public TipoPuestoEntity getIdTipoPuesto() {
        return idTipoPuesto;
    }

    public void setIdTipoPuesto(TipoPuestoEntity idTipoPuesto) {
        this.idTipoPuesto = idTipoPuesto;
    }

    /**
     * @return the puestoTimbre
     */
    public String getPuestoTimbre() {
        return puestoTimbre;
    }

    /**
     * @param puestoTimbre
     *            the puestoTimbre to set
     */
    public void setPuestoTimbre(String puestoTimbre) {
        this.puestoTimbre = puestoTimbre;
    }

}
