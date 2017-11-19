/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 09/02/2017 22:36:48
 */
public class FiltroMovimientoSuplenteDTO {

    private int tipoConsulta;
    private String criterio;
    private Integer idSuplente;

    public Integer getIdSuplente() {
        return idSuplente;
    }

    public void setIdSuplente(Integer idSuplente) {
        this.idSuplente = idSuplente;
    }

    public int getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(int tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

}
