/*
 *
 */

package mx.gob.saludtlax.rh.voluntarios;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 24/11/2016 19:27:02
 */
public class ConsultaVoluntarioView {
    private ConsultaVoluntarioDTO consulta = new ConsultaVoluntarioDTO();
    private List<SelectItem> listaTiposConsulta;
    private List<InfoVoluntarioDTO> voluntarios = new ArrayList<>();
    private boolean mostrarCriterio;

    public boolean isMostrarCriterio() {
        return mostrarCriterio;
    }

    public void setMostrarCriterio(boolean mostrarCriterio) {
        this.mostrarCriterio = mostrarCriterio;
    }

    public ConsultaVoluntarioDTO getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaVoluntarioDTO consulta) {
        this.consulta = consulta;
    }

    public List<SelectItem> getListaTiposConsulta() {
        return listaTiposConsulta;
    }

    public void setListaTiposConsulta(List<SelectItem> listaTiposConsulta) {
        this.listaTiposConsulta = listaTiposConsulta;
    }

    public List<InfoVoluntarioDTO> getVoluntarios() {
        return voluntarios;
    }

    public void setVoluntarios(List<InfoVoluntarioDTO> voluntarios) {
        this.voluntarios = voluntarios;
    }

}
