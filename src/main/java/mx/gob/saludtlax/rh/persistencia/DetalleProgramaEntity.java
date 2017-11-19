/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Leila
 *
 */
@Entity
@Table(name = "detalle_programa")
public class DetalleProgramaEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6603403749008150485L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_programa")
    private Integer id;

    @Column(name = "id_programa")
    private Integer idPrograma;

    @Column(name = "clave")
    private String clave;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "unitario")
    private BigDecimal costoUnitario;

    @Column(name = "numero_personas")
    private Integer numeroPersonas;

    @Column(name = "total_global")
    private BigDecimal totalGlobal;

    @Column(name = "meses_contratacion")
    private Integer mesesContratacion;

    @Column(name = "id_estatus")
    private String estatus;

    @Column(name = "id_tipo_detalle_programa")
    private String tipoDetallePrograma;

    @Column(name = "es_honorario")
    private Boolean esHonorario;

    public Boolean getEsHonorario() {
        return esHonorario;
    }

    public void setEsHonorario(Boolean esHonorario) {
        this.esHonorario = esHonorario;
    }

    public String getTipoDetallePrograma() {
        return tipoDetallePrograma;
    }

    public void setTipoDetallePrograma(String tipoDetallePrograma) {
        this.tipoDetallePrograma = tipoDetallePrograma;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(BigDecimal costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public Integer getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(Integer numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public BigDecimal getTotalGlobal() {
        return totalGlobal;
    }

    public void setTotalGlobal(BigDecimal totalGlobal) {
        this.totalGlobal = totalGlobal;
    }

    public Integer getMesesContratacion() {
        return mesesContratacion;
    }

    public void setMesesContratacion(Integer mesesContratacion) {
        this.mesesContratacion = mesesContratacion;
    }

    public Integer getId() {
        return id;
    }

}
