/*
 * NominaMandoMedioEntity.java
 * Creado el 29/Nov/2016 1:23:48 PM
 * 
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Entity
@Table(name = "nomina_mandos_medios")
public class NominaMandoMedioEntity implements Serializable {

    private static final long serialVersionUID = -3077702135837138909L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nomina_mando_medio")
    private Integer idNominaMandoMedio;
    
    @Size(max = 255)
    @Column(name = "puesto")
    private String puesto;
    
    @Size(max = 255)
    @Column(name = "descripcion_codigo")
    private String descripcionCodigo;
    
    @Size(max = 255)
    @Column(name = "tipo_contratacion")
    private String tipoContratacion;
    
    @Column(name = "complemento")
    private BigDecimal complemento;
    
    @Column(name = "isr")
    private BigDecimal isr;
    
    @Column(name = "neto")
    private BigDecimal neto;
    
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private EmpleadoEntity empleado;
    
    @JoinColumn(name = "id_adscripcion", referencedColumnName = "id_adscripcion")
    @ManyToOne(optional = false)
    private AdscripcionEntity adscripcion;
    
    @JoinColumn(name = "id_puesto_general", referencedColumnName = "id_puesto_general")
    @ManyToOne(optional = false)
    private PuestoGeneralEntity puestoGeneral;

    public NominaMandoMedioEntity() {
    }

    public NominaMandoMedioEntity(Integer idNominaMandoMedio) {
        this.idNominaMandoMedio = idNominaMandoMedio;
    }

    public Integer getIdNominaMandoMedio() {
        return idNominaMandoMedio;
    }

    public void setIdNominaMandoMedio(Integer idNominaMandoMedio) {
        this.idNominaMandoMedio = idNominaMandoMedio;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
    public String getDescripcionCodigo() {
        return descripcionCodigo;
    }

    public void setDescripcionCodigo(String descripcionCodigo) {
        this.descripcionCodigo = descripcionCodigo;
    }

    public String getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public BigDecimal getComplemento() {
        return complemento;
    }

    public void setComplemento(BigDecimal complemento) {
        this.complemento = complemento;
    }

    public BigDecimal getIsr() {
        return isr;
    }

    public void setIsr(BigDecimal isr) {
        this.isr = isr;
    }

    public BigDecimal getNeto() {
        return neto;
    }

    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    public AdscripcionEntity getAdscripcion() {
        return adscripcion;
    }

    public void setAdscripcion(AdscripcionEntity adscripcion) {
        this.adscripcion = adscripcion;
    }

    public PuestoGeneralEntity getPuestoGeneral() {
        return puestoGeneral;
    }

    public void setPuestoGeneral(PuestoGeneralEntity puestoGeneral) {
        this.puestoGeneral = puestoGeneral;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNominaMandoMedio != null ? idNominaMandoMedio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NominaMandoMedioEntity)) {
            return false;
        }
        NominaMandoMedioEntity other = (NominaMandoMedioEntity) object;
        return !((this.idNominaMandoMedio == null && other.idNominaMandoMedio != null) 
                || (this.idNominaMandoMedio != null && !this.idNominaMandoMedio.equals(other.idNominaMandoMedio)));
    }

    @Override
    public String toString() {
        return "mx.gob.saludtlax.rh.persistencia.NominaMandoMedioMedio[ idNominaMandoMedio=" + idNominaMandoMedio + " ]";
    }
    
}
