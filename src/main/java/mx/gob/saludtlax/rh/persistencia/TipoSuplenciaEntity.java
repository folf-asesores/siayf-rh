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
 * @since 07/11/2016 00:12:37
 */
@Entity
@Table(name = "tipos_causas_suplencias")
public class TipoSuplenciaEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5845491679042475369L;

    @Id
    @Column(name = "id_causa_suplencia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCausaSuplenca;

    @Column(name = "causa_suplencia")
    private String causaSuplencia;

    public String getCausaSuplencia() {
        return causaSuplencia;
    }

    public void setCausaSuplencia(String causaSuplencia) {
        this.causaSuplencia = causaSuplencia;
    }

    public Integer getIdCausaSuplenca() {
        return idCausaSuplenca;
    }

}
