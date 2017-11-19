/*
 * VistaPreviaAdjuntoEmpleadoEntity.java
 * Creado el Aug 31, 2016 1:28:49 PM
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "vistas_previas_adjuntos_empleados")
public class VistaPreviaAdjuntoEmpleadoEntity implements Serializable {

    private static final long serialVersionUID = -9097745743667852611L;

    @Id
    @Column(name = "id_vista_previa_adjunto_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVistaPreviaAdjuntoEmpleado;

    @Basic(fetch = FetchType.LAZY, optional = true)
    @Column(name = "vista_previa")
    @Lob
    private byte[] vistaPrevia;

    @JoinColumn(name = "id_informacion_adjunto_empleado")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private InformacionAdjuntoEmpleadoEntity informacionAdjuntoEmpleado;

    public Integer getIdVistaPreviaAdjuntoEmpleado() {
        return idVistaPreviaAdjuntoEmpleado;
    }

    public void setIdVistaPreviaAdjuntoEmpleado(Integer idVistaPreviaAdjuntoEmpleado) {
        this.idVistaPreviaAdjuntoEmpleado = idVistaPreviaAdjuntoEmpleado;
    }

    public byte[] getVistaPrevia() {
        return vistaPrevia;
    }

    public void setVistaPrevia(byte[] vistaPrevia) {
        this.vistaPrevia = vistaPrevia;
    }

    public InformacionAdjuntoEmpleadoEntity getInformacionAdjuntoEmpleado() {
        return informacionAdjuntoEmpleado;
    }

    public void setInformacionAdjuntoEmpleado(InformacionAdjuntoEmpleadoEntity informacionAdjuntoEmpleado) {
        this.informacionAdjuntoEmpleado = informacionAdjuntoEmpleado;
    }
}
