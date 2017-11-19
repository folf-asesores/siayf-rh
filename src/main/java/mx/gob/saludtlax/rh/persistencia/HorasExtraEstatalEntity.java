
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
@Table(name = "horas_extras_comprobantes")
public class HorasExtraEstatalEntity implements Serializable {
    private static final long serialVersionUID = 3111008823681449423L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHorasExtra")
    private Integer idHorasExtra;
    @Column(name = "Dias")
    private int dias;
    @Column(name = "TipoHoras")
    private String tipoHoras;
    @Column(name = "HorasExtra")
    private int horasExtra;
    @Column(name = "ImportePagado")
    private BigDecimal importePagado;

    public Integer getIdHorasExtra() {
        return idHorasExtra;
    }

    public void setIdHorasExtra(Integer idHorasExtra) {
        this.idHorasExtra = idHorasExtra;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getTipoHoras() {
        return tipoHoras;
    }

    public void setTipoHoras(String tipoHoras) {
        this.tipoHoras = tipoHoras;
    }

    public int getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(int horasExtra) {
        this.horasExtra = horasExtra;
    }

    public BigDecimal getImportePagado() {
        return importePagado;
    }

    public void setImportePagado(BigDecimal importePagado) {
        this.importePagado = importePagado;
    }
}