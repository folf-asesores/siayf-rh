/*
 * Copyright ® 2016
 */

package mx.gob.saludtlax.rh.nomina.movimientos;

import java.io.Serializable;
import java.util.List;

public class TipoMovimientoDTO implements Serializable {
    private static final long serialVersionUID = 7038973185477563021L;

    private Integer idMovimientoNomina;
    private String clave;
    private String descripcion;
    private Integer formaRegistro;
    private Boolean esMovimiento;
    private Integer idPadre;
    private List<TipoMovimientoDTO> movimientosLista;

    public Integer getIdMovimientoNomina() {
        return idMovimientoNomina;
    }

    public void setIdMovimientoNomina(Integer idMovimientoNomina) {
        this.idMovimientoNomina = idMovimientoNomina;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getFormaRegistro() {
        return formaRegistro;
    }

    public void setFormaRegistro(Integer formaRegistro) {
        this.formaRegistro = formaRegistro;
    }

    public Boolean getEsMovimiento() {
        return esMovimiento;
    }

    public void setEsMovimiento(Boolean esMovimiento) {
        this.esMovimiento = esMovimiento;
    }

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
    }

    public List<TipoMovimientoDTO> getMovimientosLista() {
        return movimientosLista;
    }

    public void setMovimientosLista(List<TipoMovimientoDTO> movimientosLista) {
        this.movimientosLista = movimientosLista;
    }
}