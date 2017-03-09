/*
 * AdjuntosEmpleadosOldEntity.java
 * Creado el Sep 5, 2016 11:04:31 AM
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Entity
@Table(name = "adjuntos_empleados_old")
public class AdjuntosEmpleadosOldEntity implements Serializable {

    private static final long serialVersionUID = -3093385397767218785L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_adjunto_empleado")
    private Integer idAdjuntoEmpleado;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "archivo")
    private byte[] archivo;

    public AdjuntosEmpleadosOldEntity() {
    }

    public AdjuntosEmpleadosOldEntity(Integer idAdjuntoEmpleado) {
        this.idAdjuntoEmpleado = idAdjuntoEmpleado;
    }

    public AdjuntosEmpleadosOldEntity(Integer idAdjuntoEmpleado, byte[] archivo) {
        this.idAdjuntoEmpleado = idAdjuntoEmpleado;
        this.archivo = archivo;
    }

    public Integer getIdAdjuntoEmpleado() {
        return idAdjuntoEmpleado;
    }

    public void setIdAdjuntoEmpleado(Integer idAdjuntoEmpleado) {
        this.idAdjuntoEmpleado = idAdjuntoEmpleado;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }
}
