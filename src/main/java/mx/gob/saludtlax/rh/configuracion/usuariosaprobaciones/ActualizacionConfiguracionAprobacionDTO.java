/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.usuariosaprobaciones;

import java.io.Serializable;

/**
 * @author Eduardo Mex
 *
 */
public class ActualizacionConfiguracionAprobacionDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4656913965342597491L;

    private Integer idConfiguracionAprobacion;
    private Integer idAccionUsuario;
    private Integer idUsuario;
    private Integer idTipoMovimiento;
    private Integer estatus;// 1 inactivo, 2 activo

    public Integer getIdConfiguracionAprobacion() {
        return idConfiguracionAprobacion;
    }

    public void setIdConfiguracionAprobacion(Integer idConfiguracionAprobacion) {
        this.idConfiguracionAprobacion = idConfiguracionAprobacion;
    }

    public Integer getIdAccionUsuario() {
        return idAccionUsuario;
    }

    public void setIdAccionUsuario(Integer idAccionUsuario) {
        this.idAccionUsuario = idAccionUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(Integer idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }
}
