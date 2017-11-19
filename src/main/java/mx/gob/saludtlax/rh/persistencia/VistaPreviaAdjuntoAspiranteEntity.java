/*
 * VistaPreviaAdjuntoAspiranteEntity.java
 * Creado el Sep 2, 2016 10:11:33 AM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "vistas_previas_adjuntos_aspirantes")
public class VistaPreviaAdjuntoAspiranteEntity implements Serializable {

    private static final long serialVersionUID = -8427300619885093399L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vista_previa_adjunto_aspirante")
    private Integer idVistaPreviaAdjuntoAspirante;

    @Lob
    @Column(name = "vista_previa")
    private byte[] vistaPrevia;

    @JoinColumn(name = "id_informacion_adjunto_aspirante", referencedColumnName = "id_informacion_adjunto_aspirante")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InformacionAdjuntoAspiranteEntity informacionAdjuntoAspirante;

    public VistaPreviaAdjuntoAspiranteEntity() {
    }

    public VistaPreviaAdjuntoAspiranteEntity(Integer idVistaPreviaAdjuntoAspirante) {
        this.idVistaPreviaAdjuntoAspirante = idVistaPreviaAdjuntoAspirante;
    }

    public Integer getIdVistaPreviaAdjuntoAspirante() {
        return idVistaPreviaAdjuntoAspirante;
    }

    public void setIdVistaPreviaAdjuntoAspirante(Integer idVistaPreviaAdjuntoAspirante) {
        this.idVistaPreviaAdjuntoAspirante = idVistaPreviaAdjuntoAspirante;
    }

    public byte[] getVistaPrevia() {
        return vistaPrevia;
    }

    public void setVistaPrevia(byte[] vistaPrevia) {
        this.vistaPrevia = vistaPrevia;
    }

    public InformacionAdjuntoAspiranteEntity getInformacionAdjuntoAspirante() {
        return informacionAdjuntoAspirante;
    }

    public void setInformacionAdjuntoAspirante(InformacionAdjuntoAspiranteEntity informacionAdjuntoAspirante) {
        this.informacionAdjuntoAspirante = informacionAdjuntoAspirante;
    }
}
