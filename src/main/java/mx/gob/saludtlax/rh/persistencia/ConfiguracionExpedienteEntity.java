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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Leila Schiaffini Ehuan
 * @since 13/06/2016 17:58:52
 *
 */
@Entity
@Table(name = "configuraciones_expedientes")
public class ConfiguracionExpedienteEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4710396927793202153L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_expediente")
    private Integer idConfiguracionExpediente;

    @ManyToOne
    @JoinColumn(name = "id_documento_adjuntable")
    private DocumentoAdjuntableEntity documentoAdjuntable;

    @Column(name = "clasificacion_expediente")
    private String clasificacionExpediente;

    public Integer getIdConfiguracionExpediente() {
        return idConfiguracionExpediente;
    }

    public void setIdConfiguracionExpediente(
            Integer idConfiguracionExpediente) {
        this.idConfiguracionExpediente = idConfiguracionExpediente;
    }

    public DocumentoAdjuntableEntity getDocumentoAdjuntable() {
        return documentoAdjuntable;
    }

    public void setDocumentoAdjuntable(
            DocumentoAdjuntableEntity documentoAdjuntable) {
        this.documentoAdjuntable = documentoAdjuntable;
    }

    public String getClasificacionExpediente() {
        return clasificacionExpediente;
    }

}
