/*
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

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 03/05/2016 03/05/2016
 */
@Entity
@Table(name = "tipos_movimientos_empleados")
public class TipoMovimientoEmpleadoEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8521438247396175722L;

    @Id
    @Column(name = "id_tipo_movimiento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoMovimiento;

    @Column(name = "movimiento")
    private String movimiento;

    @Column(name = "id_padre")
    private Integer idPadre;

    @Column(name = "clave")
    private Integer clave;

    @Column(name = "antecedente")
    private boolean conAntecedente;

    @Column(name = "visualizar_movimiento")
    private boolean visualizarMovimiento;

    @Column(name = "autorizacion")
    private boolean autorizacion;

    public boolean isAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(boolean autorizacion) {
        this.autorizacion = autorizacion;
    }

    public boolean isVisualizarMovimiento() {
        return visualizarMovimiento;
    }

    public void setVisualizarMovimiento(boolean visualizarMovimiento) {
        this.visualizarMovimiento = visualizarMovimiento;
    }

    public boolean isConAntecedente() {
        return conAntecedente;
    }

    public void setConAntecedente(boolean conAntecedente) {
        this.conAntecedente = conAntecedente;
    }

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public Integer getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(Integer idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

}
