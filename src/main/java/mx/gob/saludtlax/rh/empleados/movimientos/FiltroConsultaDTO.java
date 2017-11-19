/*
 *
 */

package mx.gob.saludtlax.rh.empleados.movimientos;

import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 * @since 14/09/2016 15:59:19
 *
 */
public class FiltroConsultaDTO {
    private int tipoBusqueda;
    private int identificador;
    private Date fechaInicial;
    private Date fechaFinal;
    private String criterio;

    public int getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setTipoBusqueda(int tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

}
