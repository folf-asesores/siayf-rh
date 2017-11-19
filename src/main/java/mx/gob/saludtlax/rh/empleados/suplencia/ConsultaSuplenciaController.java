
package mx.gob.saludtlax.rh.empleados.suplencia;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean(name = "consultaSuplencia")
@ViewScoped()
public class ConsultaSuplenciaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3625430171934892717L;

    @Inject
    private Suplencia suplencia;
    private ConsultaSuplenciaView view = new ConsultaSuplenciaView();

    public ConsultaSuplenciaView getView() {
        return view;
    }

    public void setView(ConsultaSuplenciaView view) {
        this.view = view;
    }

    public void buscarSuplente() {
        view.getFiltro().setTipoConsulta(EnumTipoConsultaSuplencia.NOMBRE);
        view.setSuplentesAutorizados(
                suplencia.consultarSuplentesPorCriterio(view.getFiltro()));

        if (view.getSuplentesAutorizados().isEmpty()) {
            JSFUtils.errorMessage("",
                    "No se encontraron resultado con el criterio ingresado.");
        }

    }

    public void seleccionarSuplente(Integer idSuplente) {

        view.setSuplencias(suplencia.consultarSuplencias(idSuplente));

    }

}
