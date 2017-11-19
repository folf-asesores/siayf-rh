
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cheques")
public class RevisarChequesEntity implements Serializable {

    private static final long serialVersionUID = -5862446207985560825L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cheque")
    private Integer idRevisarCheque;

    @Column(name = "num_cta")
    private String numeroCuenta;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "num_cheq")
    private String numCheq;

    @Column(name = "nombramiento_descripcion")
    private String nombramiento;

    public Integer getIdRevisarCheque() {
        return idRevisarCheque;
    }

    public void setIdRevisarCheque(Integer idRevisarCheque) {
        this.idRevisarCheque = idRevisarCheque;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNumCuenta() {
        return numeroCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        numeroCuenta = numCuenta;
    }

    public String getNumCheq() {
        return numCheq;
    }

    public void setNumCheq(String numCheq) {
        this.numCheq = numCheq;
    }

    public String getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

}
