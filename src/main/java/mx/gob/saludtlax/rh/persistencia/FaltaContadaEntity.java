/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "nomina_faltas_contadas")
public class FaltaContadaEntity implements Serializable {
    private static final long serialVersionUID = 2227647891147784795L;

    @Id
    @Column(name = "id_falta_contada")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFaltaContada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nomina_empleado")
    private NominaEmpleadoEntity nominaEmpleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado")
    private EmpleadoEntity empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_movimiento_nomina_eventual")
    private MovimientosNominaContratosEntity movimientoNominaEventual;

    @Column(name = "id_falta")
    private Integer idFalta;

    @Column(name = "fecha_falta")
    private Date fechaFalta;

    public Integer getIdFaltaContada() {
        return idFaltaContada;
    }

    public void setIdFaltaContada(Integer idFaltaContada) {
        this.idFaltaContada = idFaltaContada;
    }

    public NominaEmpleadoEntity getNominaEmpleado() {
        return nominaEmpleado;
    }

    public void setNominaEmpleado(NominaEmpleadoEntity nominaEmpleado) {
        this.nominaEmpleado = nominaEmpleado;
    }

    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    public MovimientosNominaContratosEntity getMovimientoNominaEventual() {
        return movimientoNominaEventual;
    }

    public void setMovimientoNominaEventual(MovimientosNominaContratosEntity movimientoNominaEventual) {
        this.movimientoNominaEventual = movimientoNominaEventual;
    }

    public Integer getIdFalta() {
        return idFalta;
    }

    public void setIdFalta(Integer idFalta) {
        this.idFalta = idFalta;
    }

    public Date getFechaFalta() {
        return fechaFalta;
    }

    public void setFechaFalta(Date fechaFalta) {
        this.fechaFalta = fechaFalta;
    }
}
