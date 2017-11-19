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
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * @since 27/06/2016 18:08:21
 * @version 1.0
 * 
 */
@Entity
@Table(name = "expedientes_aspirantes")
public class ExpedienteAspiranteEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8049854260231675846L;

    @Id
    @Column(name = "id_expediente_aspirante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExpedienteAspirante;

    @Column(name = "id_aspirante")
    private Integer idAspirante;

    @Column(name = "numero_expediente")
    private String numeroExpediente;

    /**
     * @return the idExpedienteAspirante
     */
    public Integer getIdExpedienteAspirante() {
        return idExpedienteAspirante;
    }

    /**
     * @param idExpedienteAspirante
     *            the idExpedienteAspirante to set
     */
    public void setIdExpedienteAspirante(Integer idExpedienteAspirante) {
        this.idExpedienteAspirante = idExpedienteAspirante;
    }

    /**
     * @return the idAspirante
     */
    public Integer getIdAspirante() {
        return idAspirante;
    }

    /**
     * @param idAspirante
     *            the idAspirante to set
     */
    public void setIdAspirante(Integer idAspirante) {
        this.idAspirante = idAspirante;
    }

    /**
     * @return the numeroExpediente
     */
    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    /**
     * @param numeroExpediente
     *            the numeroExpediente to set
     */
    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

}
