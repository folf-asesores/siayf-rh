/*
 *
 */

package mx.gob.saludtlax.rh.puestosautorizados;

/**
 * @author Leila Schiaffini Ehuan
 * @since 16/08/2016 17:44:00
 *
 */
public class AperturaVacanteDTO {
    private Integer idConfiguracionPresupuesto;
    private Integer tipoApertura;

    public Integer getTipoApertura() {
        return tipoApertura;
    }

    public void setTipoApertura(Integer tipoApertura) {
        this.tipoApertura = tipoApertura;
    }

    public Integer getIdConfiguracionPresupuesto() {
        return idConfiguracionPresupuesto;
    }

    public void setIdConfiguracionPresupuesto(Integer idConfiguracionPresupuesto) {
        this.idConfiguracionPresupuesto = idConfiguracionPresupuesto;
    }

}
