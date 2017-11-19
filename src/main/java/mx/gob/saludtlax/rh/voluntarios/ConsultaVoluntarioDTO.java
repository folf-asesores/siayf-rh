/*
 *
 */

package mx.gob.saludtlax.rh.voluntarios;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 24/11/2016 19:03:44
 */
public class ConsultaVoluntarioDTO {
    private int tipoConsulta;
    private String criterio;

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
