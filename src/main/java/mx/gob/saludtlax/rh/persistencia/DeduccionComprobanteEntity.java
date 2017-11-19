
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "deducciones_comprobantes")
public class DeduccionComprobanteEntity implements Serializable {
    private static final long serialVersionUID = -6455177533660397428L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deduccion")
    private Integer idDeduccion;
    @Column(name = "tipo_deduccion")
    private String tipoDeduccion;
    @Column(name = "clave")
    private String clave;
    @Column(name = "concepto")
    private String concepto;
    @Column(name = "importe_gravado")
    private BigDecimal importeGravado;
    @Column(name = "importe_excento")
    private BigDecimal importeExcento;
    @Column(name = "id_comprobante")
    private Integer idComprobante;

    public Integer getIdDeduccion() {
        return idDeduccion;
    }

    public void setIdDeduccion(Integer idDeduccion) {
        this.idDeduccion = idDeduccion;
    }

    public String getTipoDeduccion() {
        return tipoDeduccion;
    }

    public void setTipoDeduccion(String tipoDeduccion) {
        this.tipoDeduccion = tipoDeduccion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getImporteGravado() {
        return importeGravado;
    }

    public void setImporteGravado(BigDecimal importeGravado) {
        this.importeGravado = importeGravado;
    }

    public BigDecimal getImporteExcento() {
        return importeExcento;
    }

    public void setImporteExcento(BigDecimal importeExcento) {
        this.importeExcento = importeExcento;
    }

    public Integer getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Integer idComprobante) {
        this.idComprobante = idComprobante;
    }

}
