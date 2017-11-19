
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
@Table(name = "Deduccion")
public class DeduccionEntity implements Serializable {
    private static final long serialVersionUID = -6455177533660397428L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDeduccion")
    private Integer idDeduccion;
    @Column(name = "TipoDeduccion")
    private int tipoDeduccion;
    @Column(name = "Clave")
    private String clave;
    @Column(name = "Concepto")
    private String concepto;
    @Column(name = "ImporteGravado")
    private BigDecimal importeGravado;
    @Column(name = "ImporteExento")
    private BigDecimal importeExento;

    public Integer getIdDeduccion() {
        return idDeduccion;
    }

    public void setIdDeduccion(Integer idDeduccion) {
        this.idDeduccion = idDeduccion;
    }

    public int getTipoDeduccion() {
        return tipoDeduccion;
    }

    public void setTipoDeduccion(int tipoDeduccion) {
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

    public BigDecimal getImporteExento() {
        return importeExento;
    }

    public void setImporteExento(BigDecimal importeExento) {
        this.importeExento = importeExento;
    }
}
