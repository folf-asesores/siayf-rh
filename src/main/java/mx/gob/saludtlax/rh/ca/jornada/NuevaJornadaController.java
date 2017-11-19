
package mx.gob.saludtlax.rh.ca.jornada;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.ca.incidencia.IncidenciaClienteRest;
import mx.gob.saludtlax.rh.ca.incidencia.IncidenciaModelView;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.excepciones.RESTClientJornadaException;
import mx.gob.saludtlax.rh.excepciones.RESTClientReglaAsistenciaJornadaException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ListadoMensajesSistema;

@ManagedBean(name = "nuevaJornadaController")
@ViewScoped
public class NuevaJornadaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4626234771786827853L;

    @Inject
    IncidenciaClienteRest incidenciaClienteRest;

    @Inject
    JornadaClienteRest jornadaClientRest;

    JornadaFormModel jornadaFormModel = new JornadaFormModel();
    List<ReglaAsistenciaViewModel> listadoReglasAsistencia = new ArrayList<>();
    ReglaAsistenciaViewModel reglaAsistenciaViewModelSelecionado = null;

    public List<IncidenciaModelView> incidenciasAutoacompletar(String query) {

        List<IncidenciaModelView> listadoIncidencias = null;

        try {
            listadoIncidencias = incidenciaClienteRest
                    .buscarIncidenciaPorDescripcion(query);
        } catch (RESTClientException e) {
            JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(),
                    e.getMessage());
            e.printStackTrace();
        }

        return listadoIncidencias;
    }

    public String guardarJornada() {
        if (jornadaFormModel.getDescripcion().length() == 0) {
            JSFUtils.errorMessage("Guardar",
                    ListadoMensajesSistema.CARA003.getMensaje());
            return "";
        }

        try {
            Integer idJornada = jornadaClientRest
                    .nuevaJornada(jornadaFormModel);
            JSFUtils.infoMessage("Guardar",
                    ListadoMensajesSistema.CA001.getMensaje() + " \n ");
            return "editar.xhtml?faces-redirect=true&id=" + idJornada;
        } catch (RESTClientJornadaException e) {
            JSFUtils.errorMessage("Guardar", e.getMessage());
            e.printStackTrace();
            return "";

        } catch (RESTClientReglaAsistenciaJornadaException e) {
            JSFUtils.warningMessage("Guardar",
                    ListadoMensajesSistema.CARA004 + " \n " + e.getMessage());
            e.printStackTrace();
            return "";
        }

    }

    public void eliminarReglaAsistencia(
            ReglaAsistenciaViewModel reglaEliminar) {

        listadoReglasAsistencia.remove(reglaEliminar);

    }

    public JornadaFormModel getJornadaFormModel() {
        return jornadaFormModel;
    }

    public void setJornadaFormModel(JornadaFormModel jornadaFormModel) {
        this.jornadaFormModel = jornadaFormModel;
    }

    public List<ReglaAsistenciaViewModel> getListadoReglasAsistencia() {
        return listadoReglasAsistencia;
    }

    public void setListadoReglasAsistencia(
            List<ReglaAsistenciaViewModel> listadoReglasAsistencia) {
        this.listadoReglasAsistencia = listadoReglasAsistencia;
    }

    public ReglaAsistenciaViewModel getReglaAsistenciaViewModelSelecionado() {
        return reglaAsistenciaViewModelSelecionado;
    }

    public void setReglaAsistenciaViewModelSelecionado(
            ReglaAsistenciaViewModel reglaAsistenciaViewModelSelecionado) {
        this.reglaAsistenciaViewModelSelecionado = reglaAsistenciaViewModelSelecionado;
    }

}
