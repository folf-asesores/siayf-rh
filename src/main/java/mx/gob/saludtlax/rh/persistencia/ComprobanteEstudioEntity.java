/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 04/03/2016-18:28:27
 */

@Entity
@Table(name = "comprobantes_estudios")
public class ComprobanteEstudioEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -608962722279212532L;

    @Id
    @Column(name = "id_comprobante_estudio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComprobanteEstudio;

    @Column(name = "estatus")
    private String estatus;

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdComprobanteEstudio() {
        return idComprobanteEstudio;
    }

}
