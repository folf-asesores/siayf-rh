/*
 * InformacionAdjuntosEmpleadosOldEntity.java
 * Creado el Sep 5, 2016 11:04:32 AM
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.gob.saludtlax.rh.expediente.EntidadContexto;
import mx.gob.saludtlax.rh.util.TipoArchivo;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Entity
@Table(name = "informacion_adjuntos_empleados_old")
public class InformacionAdjuntosEmpleadosOldEntity implements Serializable {

    private static final long serialVersionUID = -31857871466049877L;

    @Id
    @Column(name = "id_informacion_adjunto_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInformacionAdjuntoEmpleado;

    @Enumerated(EnumType.STRING)
    @Column(name = "entidad_contexto")
    private EntidadContexto entidadContexto;

    @Column(name = "id_entidad_contexto")
    private Integer idEntidadContexto;

    @JoinColumn(name = "id_adjunto_empleado")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private  AdjuntosEmpleadosOldEntity adjunto;

    @Basic(optional = false)
    @Column(name = "nombre_adjunto")
    private String nombreAdjunto;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(name = "extension")
    private TipoArchivo extension;

    @Basic(optional = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_documento_adjuntable")
    private DocumentoAdjuntableEntity documentoAdjuntable;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_empleado")
    private EmpleadoEntity empleado;

    @Basic(fetch = FetchType.LAZY, optional = true)
    @Column(name = "vista_previa")
    @Lob
    private byte[] vistaPrevia;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_expediente")
    private ExpedienteEmpleadoEntity expediente;

    public InformacionAdjuntosEmpleadosOldEntity() {
    }    

    public Integer getIdInformacionAdjuntoEmpleado() {
        return idInformacionAdjuntoEmpleado;
    }

    public void setIdInformacionAdjuntoEmpleado(Integer idInformacionAdjuntoEmpleado) {
        this.idInformacionAdjuntoEmpleado = idInformacionAdjuntoEmpleado;
    }

    public EntidadContexto getEntidadContexto() {
        return entidadContexto;
    }

    public void setEntidadContexto(EntidadContexto entidadContexto) {
        this.entidadContexto = entidadContexto;
    }

    public Integer getIdEntidadContexto() {
        return idEntidadContexto;
    }

    public void setIdEntidadContexto(Integer idEntidadContexto) {
        this.idEntidadContexto = idEntidadContexto;
    }

    public AdjuntosEmpleadosOldEntity getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(AdjuntosEmpleadosOldEntity adjunto) {
        this.adjunto = adjunto;
    }

    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

    public TipoArchivo getExtension() {
        return extension;
    }

    public void setExtension(TipoArchivo extension) {
        this.extension = extension;
    }

    public DocumentoAdjuntableEntity getDocumentoAdjuntable() {
        return documentoAdjuntable;
    }

    public void setDocumentoAdjuntable(DocumentoAdjuntableEntity documentoAdjuntable) {
        this.documentoAdjuntable = documentoAdjuntable;
    }

    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    public byte[] getVistaPrevia() {
        return vistaPrevia;
    }

    public void setVistaPrevia(byte[] vistaPrevia) {
        this.vistaPrevia = vistaPrevia;
    }

    public ExpedienteEmpleadoEntity getExpediente() {
        return expediente;
    }

    public void setExpediente(ExpedienteEmpleadoEntity expediente) {
        this.expediente = expediente;
    }
}
