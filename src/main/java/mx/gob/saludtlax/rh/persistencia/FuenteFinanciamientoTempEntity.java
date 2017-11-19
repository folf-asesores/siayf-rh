/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 25/07/2016 14:13:27
 */
@Entity
@Table(name = "fuentes_financiamientos_temp")
public class FuenteFinanciamientoTempEntity {

    @Id
    //	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fuente_financiamiento")
    private Integer idFuenteFinanciamiento;

    @Column(name = "id_base_36")
    private String idBase36;

    @Column(name = "descripcion")
    private String descripcion;

    @Override
    public String toString() {
        return "FuenteFinanciamientoEntity [id fuente financiamiento=" + idFuenteFinanciamiento + ", id base 36=" + idBase36 + ", descripcion=" + descripcion
                + "]";
    }

    /**
     * @return the idFuenteFinanciamiento
     */
    public Integer getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    /**
     * @param idFuenteFinanciamiento
     *            the idFuenteFinanciamiento to set
     */
    public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    /**
     * @return the idBase36
     */
    public String getIdBase36() {
        return idBase36;
    }

    /**
     * @param idBase36
     *            the idBase36 to set
     */
    public void setIdBase36(String idBase36) {
        this.idBase36 = idBase36;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
