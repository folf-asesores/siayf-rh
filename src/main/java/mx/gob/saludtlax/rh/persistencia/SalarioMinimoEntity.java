
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Juan Carlos Ivan Ganzo Dominguez
 * @since 12/11/2016
 */
@Entity
@Table(name = "salarios_minimos")
public class SalarioMinimoEntity implements Serializable {
    private static final long serialVersionUID = -6826681190131950279L;

    @Id
    @Column(name = "id_salario_minimo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSalarioMinimo;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "salario_minimo")
    private BigDecimal salarioMinimo;

    public Integer getIdSalarioMinimo() {
        return idSalarioMinimo;
    }

    public void setIdSalarioMinimo(Integer idSalarioMinimo) {
        this.idSalarioMinimo = idSalarioMinimo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getSalarioMinimo() {
        return salarioMinimo;
    }

    public void setSalarioMinimo(BigDecimal salarioMinimo) {
        this.salarioMinimo = salarioMinimo;
    }
}