/*
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
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 *
 */
@Entity
@Table(name = "adjuntos_empleados")
public class AdjuntoEmpleadoEntity implements Serializable {

    private static final long serialVersionUID = -901870899360620267L;

    @Id
    @Column(name = "id_adjunto_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdjunto;

    @Basic(fetch = FetchType.LAZY, optional = true)
    @Column(name = "archivo")
    @Lob
    private byte[] archivo;

    /**
     * @return the idAdjunto
     */
    public Integer getIdAdjunto() {
        return idAdjunto;
    }

    /**
     * @param idAdjunto
     *            the idAdjunto to set
     */
    public void setIdAdjunto(Integer idAdjunto) {
        this.idAdjunto = idAdjunto;
    }

    /**
     * @return the archivo
     */
    public byte[] getArchivo() {
        return archivo;
    }

    /**
     * @param archivo
     *            the archivo to set
     */
    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }
}
