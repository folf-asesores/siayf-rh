
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
@Table(name = "percepciones_comprobantes")
public class PercepcionEstatalEntity implements Serializable {
    private static final long serialVersionUID = -8144442072383606920L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPercepcion")
    private Integer idPercepcion;
    @Column(name = "TipoPercepcion")
    private String tipoPercepcion;
    @Column(name = "Clave")
    private String clave;
    @Column(name = "Concepto")
    private String concepto;
    @Column(name = "ImporteGravado")
    private BigDecimal importeGravado;
    @Column(name = "ImporteExento")
    private BigDecimal importeExento;

    public Integer getIdPercepcion() {
        return idPercepcion;
    }

    public void setIdPercepcion(Integer idPercepcion) {
        this.idPercepcion = idPercepcion;
    }

    public String getTipoPercepcion() {
        return tipoPercepcion;
    }

    public void setTipoPercepcion(String tipoPercepcion) {
        this.tipoPercepcion = tipoPercepcion;
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