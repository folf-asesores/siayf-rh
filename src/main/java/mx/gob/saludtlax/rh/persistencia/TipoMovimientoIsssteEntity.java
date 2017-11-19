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
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@Entity
@Table(name = "tipos_movimientos_issste")
public class TipoMovimientoIsssteEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2278180670196759533L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_movimiento_issste")
    private Integer idTipoMovimientoIsssteEmpleado;

    @Column(name = "tipo_movimiento_issste")
    private String tipoMovimientoIssste;

    public Integer getIdTipoMovimientoIsssteEmpleado() {
        return idTipoMovimientoIsssteEmpleado;
    }

    public void setIdTipoMovimientoIsssteEmpleado(
            Integer idTipoMovimientoIsssteEmpleado) {
        this.idTipoMovimientoIsssteEmpleado = idTipoMovimientoIsssteEmpleado;
    }

    public String getTipoMovimientoIssste() {
        return tipoMovimientoIssste;
    }

    public void setTipoMovimientoIssste(String tipoMovimientoIssste) {
        this.tipoMovimientoIssste = tipoMovimientoIssste;
    }

}
