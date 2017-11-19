/*
 *
 */

package mx.gob.saludtlax.rh.autorizaciones;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/08/2016 22:19:41
 */
public class NuevaAutorizacionDTO {
    private Integer idEntidadContexto;
    private Integer idAccion;
    private Integer idUsuarioLogeado;
    private String mensajeNotificacion;
    private Integer tipoMovimiento;

    public Integer getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(Integer tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getMensajeNotificacion() {
        return mensajeNotificacion;
    }

    public void setMensajeNotificacion(String mensajeNotificacion) {
        this.mensajeNotificacion = mensajeNotificacion;
    }

    public Integer getIdUsuarioLogeado() {
        return idUsuarioLogeado;
    }

    public void setIdUsuarioLogeado(Integer idUsuarioLogeado) {
        this.idUsuarioLogeado = idUsuarioLogeado;
    }

    public Integer getIdEntidadContexto() {
        return idEntidadContexto;
    }

    public void setIdEntidadContexto(Integer idEntidadContexto) {
        this.idEntidadContexto = idEntidadContexto;
    }

    public Integer getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(Integer idAccion) {
        this.idAccion = idAccion;
    }

}
