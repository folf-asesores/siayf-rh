
package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.io.Serializable;
import java.math.BigDecimal;

public class SiifDispersionChequesDTO implements Serializable {

    private static final long serialVersionUID = -4226990086634174773L;

    private Integer idDispersionCheques;
    private String num;
    private String numCheque;
    private String nombre;
    private BigDecimal importe;
    private String tipoNomina;
    private Integer quincena;
    private String rfc;

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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
