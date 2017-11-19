
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;

public class AltaBajaNominaEmpleadoDTO implements Serializable {
    private static final long serialVersionUID = -1911889925744673124L;

    private String rfc;
    private String nombre;
    private Integer idNominaempleado;
    private Integer idConfiguracionPresupuestalValue;
    private String tipoCambio;

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdNominaempleado() {
        return idNominaempleado;
    }

    public void setIdNominaempleado(Integer idNominaempleado) {
        this.idNominaempleado = idNominaempleado;
    }

    public Integer getIdConfiguracionPresupuestalValue() {
        return idConfiguracionPresupuestalValue;
    }

    public void setIdConfiguracionPresupuestalValue(
            Integer idConfiguracionPresupuestalValue) {
        this.idConfiguracionPresupuestalValue = idConfiguracionPresupuestalValue;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
}