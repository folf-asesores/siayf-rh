
package mx.gob.saludtlax.rh.ca.jornada;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import mx.gob.saludtlax.rh.ca.incidencia.IncidenciaClienteRest;
import mx.gob.saludtlax.rh.ca.incidencia.IncidenciaModelView;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServicioWebException;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServiciosWebEJB;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.excepciones.RESTClientJornadaException;
import mx.gob.saludtlax.rh.excepciones.RESTClientReglaAsistenciaJornadaException;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ListadoMensajesSistema;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "editarJornadaController")
@ViewScoped
public class EditarJornadaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5660425710478010001L;

    @Inject
    ServiciosWebEJB serviocWebEJB;

    @Inject
    JornadaClienteRest jornadaClientRest;

    @Inject
    IncidenciaClienteRest incidenciaClienteRest;

    IncidenciaModelView incidenciaModelViewSelecionada;

    private JornadaFormModel jornadaFormModel;

    private int[] diasSelecionados;

    private List<HorarioJornadaViewModel> listadoHorarioJornada;

    private List<ReglaAsistenciaViewModel> listadoReglaAsistencia;

    private ReglaAsistenciaJornadaFormModel reglaAsistencia = new ReglaAsistenciaJornadaFormModel();

    private HorarioJornadaFormModel horarioJornada = new HorarioJornadaFormModel();

    private boolean bTrabajaDiasNoLaborales;

    public boolean isbTrabajaDiasNoLaborales() {
        if (jornadaFormModel.getTrabajaDiasNoLaborales() == 1) {
            return true;
        }
        return bTrabajaDiasNoLaborales;
    }

    public void setbTrabajaDiasNoLaborales(boolean bTrabajaDiasNoLaborales) {
        this.bTrabajaDiasNoLaborales = bTrabajaDiasNoLaborales;
        if (bTrabajaDiasNoLaborales) {
            jornadaFormModel.setTrabajaDiasNoLaborales(1);
        } else {
            jornadaFormModel.setTrabajaDiasNoLaborales(0);
        }
        ;
    }

    public void init() {

        try {
            ServiciosRSEntity servicioRSEntity = serviocWebEJB
                    .getServicioActivo(ServicioWebEnum.CONTROL_ASISTENCIA_RS);
            if (!servicioRSEntity.isProduccion()) {
                HttpServletRequest req = (HttpServletRequest) FacesContext
                        .getCurrentInstance().getExternalContext().getRequest();
                String url = req.getContextPath();
                FacesMessage facesMessage = new FacesMessage(
                        FacesMessage.SEVERITY_WARN, "Servicio en Modo Prueba",
                        "El servcio configurado como activo para este modulo es de pruebas consulte la <a href='"
                                + url
                                + "/contenido/configuracion/serviciosweb/index.xhtml'>configuracion</a>");
                FacesContext.getCurrentInstance().addMessage(null,
                        facesMessage);

            }

        } catch (ServicioWebException e1) {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, e1.getMessage(),
                    e1.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext()
                .getRequestParameterMap();
        String id = params.get("id");

        if (id != null) {
            Integer idJornada = new Integer(id);
            try {
                jornadaFormModel = jornadaClientRest
                        .obtenerJornadaPorId(idJornada);
                llenarHorario(idJornada);
                llenarReglasAsistencia(idJornada);
            } catch (RESTClientException e) {
                FacesMessage facesMessage = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, e.getMessage(),
                        e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null,
                        facesMessage);
            }
        }

    }

    public void nuevaReglaAsistencia() {

        reglaAsistencia = new ReglaAsistenciaJornadaFormModel();
        reglaAsistencia.setMinutoFinal(0);
        reglaAsistencia.setMinutoInicio(0);
        RequestContext.getCurrentInstance()
                .execute("PF('dlgNuevaRegla').show()");

    }

    public void nuevoHorario() {

        horarioJornada = new HorarioJornadaFormModel();
        horarioJornada.setHoraEntrada(null);
        horarioJornada.setHoraSalida(null);
        horarioJornada.setIdDia(null);
        horarioJornada.setIdHorarioJornada(null);
        horarioJornada.setIdJornada(jornadaFormModel.getIdJornada());
        RequestContext.getCurrentInstance()
                .execute("PF('dlgNuevoHorario').show()");

    }

    public String guardarJornada() {

        try {
            jornadaClientRest.actuializarJornada(jornadaFormModel);
        } catch (RESTClientJornadaException e) {
            JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(),
                    e.getMessage());
        } catch (RESTClientReglaAsistenciaJornadaException e) {
            JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(),
                    e.getMessage());
        }
        return "index.xhtml?faces-redirect=true";
    }

    public void agregarNuevoHorario() {

        for (int contDias = 0; contDias < diasSelecionados.length; contDias++) {
            horarioJornada.setIdDia(diasSelecionados[contDias]);

            try {
                jornadaClientRest.nuevoHorarioJornada(horarioJornada);
                llenarHorario(jornadaFormModel.getIdJornada());
            } catch (RESTClientJornadaException e) {
                JSFUtils.errorMessage("Horario", e.getMessage());
                e.printStackTrace();
                return;
            } catch (RESTClientReglaAsistenciaJornadaException e) {
                JSFUtils.errorMessage("Horario", e.getMessage());
                e.printStackTrace();
                return;
            } catch (RESTClientException e) {
                JSFUtils.errorMessage("Horario", e.getMessage());
                e.printStackTrace();
                return;
            }

        }

        RequestContext.getCurrentInstance()
                .execute("PF('dlgNuevoHorario').hide()");

    }

    public void agregarNuevaIncidencia() {

        if (reglaAsistencia.getIdIncidencia() == null) {
            JSFUtils.errorMessage("Agregar",
                    ListadoMensajesSistema.CARA001.getMensaje());
            return;
        }
        IncidenciaModelView incidenciaModelView = null;
        try {
            incidenciaModelView = incidenciaClienteRest
                    .buscarIncidenciaPorId(reglaAsistencia.getIdIncidencia());
        } catch (RESTClientException e) {
            JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(),
                    e.getMessage());
            e.printStackTrace();
            return;
        }

        if (reglaAsistencia.getMinutoInicio() > reglaAsistencia
                .getMinutoFinal()) {
            JSFUtils.errorMessage("Agregar",
                    ListadoMensajesSistema.CARA002.getMensaje());
            return;
        }

        try {
            // reglaAsistencia.setIdIncidencia(incidenciaModelViewSelecionada.getIdIncidencia());
            System.out.println("idReglas " + reglaAsistencia.getIdIncidencia());
            reglaAsistencia.setIdJornada(jornadaFormModel.getIdJornada());
            jornadaClientRest.nuevaReglaAsistenciaJornada(reglaAsistencia);
        } catch (RESTClientException e) {
            JSFUtils.errorMessage("Agregar", e.getMessage());

        }
        try {
            llenarReglasAsistencia(reglaAsistencia.getIdJornada());
        } catch (RESTClientException e) {
            JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(),
                    e.getMessage());

            e.printStackTrace();
        }
        RequestContext.getCurrentInstance()
                .execute("PF('dlgNuevaRegla').hide()");

    }

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

    public void eliminarReglaAsistencia(
            ReglaAsistenciaViewModel reglaEliminar) {

        try {
            jornadaClientRest.eliminarReglaAsistencia(
                    reglaEliminar.getIdReglaAsistencia());
            llenarReglasAsistencia(jornadaFormModel.getIdJornada());
        } catch (RESTClientException e) {
            JSFUtils.errorMessage("Eliminar", e.getMessage());

        }
        // listadoReglasAsistencia.remove(reglaEliminar);

    }

    public void eliminarHorarioJornada(HorarioJornadaViewModel horarioJornada) {

        try {
            jornadaClientRest.eliminarHorarioJornada(
                    horarioJornada.getIdHorarioJornada());
            llenarHorario(jornadaFormModel.getIdJornada());
        } catch (RESTClientException e) {
            JSFUtils.errorMessage("Eliminar", e.getMessage());

        }
        // listadoReglasAsistencia.remove(reglaEliminar);

    }

    private void llenarHorario(Integer idJornada) throws RESTClientException {
        listadoHorarioJornada = jornadaClientRest
                .listadoHorarioPorJornada(idJornada);
    }

    private void llenarReglasAsistencia(Integer idJornada)
            throws RESTClientException {
        listadoReglaAsistencia = jornadaClientRest
                .listadoReglasAsistenciaPorJornada(idJornada);
    }

    public JornadaFormModel getJornadaFormModel() {
        return jornadaFormModel;
    }

    public void setJornadaFormModel(JornadaFormModel jornadaFormModel) {
        this.jornadaFormModel = jornadaFormModel;
    }

    public List<HorarioJornadaViewModel> getListadoHorarioJornada() {
        return listadoHorarioJornada;
    }

    public void setListadoHorarioJornada(
            List<HorarioJornadaViewModel> listadoHorarioJornada) {
        this.listadoHorarioJornada = listadoHorarioJornada;
    }

    public List<ReglaAsistenciaViewModel> getListadoReglaAsistencia() {
        return listadoReglaAsistencia;
    }

    public void setListadoReglaAsistencia(
            List<ReglaAsistenciaViewModel> listadoReglaAsistencia) {
        this.listadoReglaAsistencia = listadoReglaAsistencia;
    }

    public ReglaAsistenciaJornadaFormModel getReglaAsistencia() {
        return reglaAsistencia;
    }

    public void setReglaAsistencia(
            ReglaAsistenciaJornadaFormModel reglaAsistencia) {
        this.reglaAsistencia = reglaAsistencia;
    }

    public IncidenciaModelView getIncidenciaModelViewSelecionada() {
        return incidenciaModelViewSelecionada;
    }

    public void setIncidenciaModelViewSelecionada(
            IncidenciaModelView incidenciaModelViewSelecionada) {
        this.incidenciaModelViewSelecionada = incidenciaModelViewSelecionada;
    }

    public HorarioJornadaFormModel getHorarioJornada() {
        return horarioJornada;
    }

    public void setHorarioJornada(HorarioJornadaFormModel horarioJornada) {
        this.horarioJornada = horarioJornada;
    }

    public int[] getDiasSelecionados() {
        return diasSelecionados;
    }

    public void setDiasSelecionados(int[] diasSelecionados) {
        this.diasSelecionados = diasSelecionados;
    }

}
