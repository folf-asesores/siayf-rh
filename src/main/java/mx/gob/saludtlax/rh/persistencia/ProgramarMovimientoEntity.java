
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "programacion_movimientos_nomina")
public class ProgramarMovimientoEntity implements Serializable {

    private static final long serialVersionUID = -2304509802031944131L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programacion_movimiento")
    private Integer idProgramacionMovimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_movimiento")
    private TiposMovimientosNominaEntity idTipoMovimiento;

    @Column(name = "periodo_aplicacion")
    private Integer periodoAplicacion;

    @Column(name = "tipo_aplicacion")
    private Integer tipoAplicacion;

    @Column(name = "importe")
    private BigDecimal importe;

    @Column(name = "descripcion")
    private String Descripcion;

    public Integer getIdProgramacionMovimiento() {
        return idProgramacionMovimiento;
    }

    public void setIdProgramacionMovimiento(Integer idProgramacionMovimiento) {
        this.idProgramacionMovimiento = idProgramacionMovimiento;
    }

    public Integer getPeriodoAplicacion() {
        return periodoAplicacion;
    }

    public void setPeriodoAplicacion(Integer periodoAplicacion) {
        this.periodoAplicacion = periodoAplicacion;
    }

    public TiposMovimientosNominaEntity getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(TiposMovimientosNominaEntity idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public Integer getTipoAplicacion() {
        return tipoAplicacion;
    }

    public void setTipoAplicacion(Integer tipoAplicacion) {
        this.tipoAplicacion = tipoAplicacion;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

}
