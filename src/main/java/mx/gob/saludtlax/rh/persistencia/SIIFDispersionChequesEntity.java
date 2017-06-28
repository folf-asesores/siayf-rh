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
 * Entity implementation class for Entity: SIIFDispersionChequesEntity
 *
 */
@Entity
@Table(name = "siif_dispersion")
public class SIIFDispersionChequesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_siif_dispersion")
    private Integer idDispersionCheques;

    @Column(name = "num")
    private String num;

    @Column(name = "cheque")
    private String numCheque;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "importe")
    private BigDecimal importe;

    @Column(name = "tipo_nomina")
    private String tipoNomina;

    @Column(name = "qna")
    private Integer quincena;

    @Column(name = "rfc")
    private String rfc;

    //  <<<Getters & Setters>>>
    public Integer getIdDispersionCheques() {
        return idDispersionCheques;
    }

    public void setIdDispersionCheques(Integer idDispersionCheques) {
        this.idDispersionCheques = idDispersionCheques;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNumCheque() {
        return numCheque;
    }

    public void setNumCheque(String numCheque) {
        this.numCheque = numCheque;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(String tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public Integer getQuincena() {
        return quincena;
    }

    public void setQuincena(Integer quincena) {
        this.quincena = quincena;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

}
