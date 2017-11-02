/*
 * 
 * AdjuntoAspiranteEntity.java
 * Creado el Jun 11, 2016 3:11:25 PM
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
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "adjuntos_aspirantes")
public class AdjuntoAspiranteEntity implements Serializable {

    private static final long serialVersionUID = -7529627039309209917L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adjunto_aspirante")
    private Integer idAdjuntoAspirante;
    
    @Basic(fetch = FetchType.LAZY, optional = true)
    @Column(name = "archivo")
    @Lob
    private byte[] archivo;

    /**
     * 
     * @return 
     */
    public Integer getIdAdjuntoAspirante() {
        return idAdjuntoAspirante;
    }

    /**
     * 
     * @param idAdjuntoAspirante 
     */
    public void setIdAdjuntoAspirante(Integer idAdjuntoAspirante) {
        this.idAdjuntoAspirante = idAdjuntoAspirante;
    }

    /**
     * 
     * @return 
     */
    public byte[] getArchivo() {
        return archivo;
    }

    /**
     * 
     * @param archivo 
     */
    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }
}
