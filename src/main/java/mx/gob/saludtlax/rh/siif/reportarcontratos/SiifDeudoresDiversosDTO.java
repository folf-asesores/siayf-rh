
package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.io.Serializable;
import java.math.BigDecimal;

public class SiifDeudoresDiversosDTO implements Serializable {

    private static final long serialVersionUID = -4226990086634174773L;

    private Integer idDeudoresDiversos;
    private String rfc;
    private Integer quincena;
    private BigDecimal importe;

    public String getRfc() {
        return rfc;
    }

    public Integer getIdDeudoresDiversos() {
        return idDeudoresDiversos;
    }

    public void setIdDeudoresDiversos(Integer idDeudoresDiversos) {
        this.idDeudoresDiversos = idDeudoresDiversos;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}