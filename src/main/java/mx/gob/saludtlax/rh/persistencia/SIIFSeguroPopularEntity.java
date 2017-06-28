/**
 *
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "siif_seguro_popular_federal")
public class SIIFSeguroPopularEntity implements Serializable {

    private static final long serialVersionUID = -3491218769714297031L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguro_popular_federal")
    private Integer idSeguroPopularFederal;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "quincena")
    private Integer quincena;

//    <<<Getters & Setters>>>
    public Integer getIdSeguroPopularFederal() {
        return idSeguroPopularFederal;
    }

    public void setIdSeguroPopularFederal(Integer idSeguroPopularFederal) {
        this.idSeguroPopularFederal = idSeguroPopularFederal;
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

}
