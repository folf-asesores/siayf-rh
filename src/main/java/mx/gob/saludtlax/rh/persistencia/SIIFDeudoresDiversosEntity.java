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

@Entity
@Table(name = "siif_deudores_diversos")
public class SIIFDeudoresDiversosEntity implements Serializable {

    private static final long serialVersionUID = -3491218769714297031L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deudores_diversos")
    private Integer idDeudoresDiversos;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "quincena")
    private Integer quincena;

    @Column(name = "importe")
    private BigDecimal importe;

    public Integer getIdDeudoresDiversos() {
        return idDeudoresDiversos;
    }

    public void setIdDeudoresDiversos(Integer idDeudoresDiversos) {
        this.idDeudoresDiversos = idDeudoresDiversos;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Integer getQuincena() {
        return quincena;
    }

    public void setQuincena(Integer quincena) {
        this.quincena = quincena;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

}
