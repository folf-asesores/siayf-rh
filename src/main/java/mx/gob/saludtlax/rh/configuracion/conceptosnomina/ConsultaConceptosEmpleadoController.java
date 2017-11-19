
package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.puestogeneral.PuestoGeneral;
import mx.gob.saludtlax.rh.configuracion.puestogeneral.PuestoGeneralDTO;
import mx.gob.saludtlax.rh.configuracion.tabulador.Tabulador;
import mx.gob.saludtlax.rh.configuracion.tabulador.TabuladorDTO;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.EmpleadoDetalladoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.empleados.datolaboral.ConfiguracionPresupuestal;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.puestosautorizados.InfoConfiguracionDTO;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean
@ViewScoped
public class ConsultaConceptosEmpleadoController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -847148044095954232L;

    @Inject
    ConceptoNominaFederalesEJB conceptoNominaFederalesEJB;
    @Inject
    ConfiguracionPresupuestal configuracionPresupuestal;
    @Inject
    private Empleado empleadoEJB;

    @Inject
    private Tabulador tabuladorEJB;

    @Inject
    PuestoGeneral puestoGeneral;

    private List<ConceptoNominaFederalesDTO> conceptos = new ArrayList<>();
    private String criterioEmpleado = "";
    private List<InfoEmpleadoDTO> empleadoLista = new ArrayList<>();
    private InfoEmpleadoDTO empleadoSeleccionado;
    private EmpleadoDetalladoDTO empleadoDatos = new EmpleadoDetalladoDTO();
    private BigDecimal total;
    private Boolean mostrarTablaEmpleados = false;
    private Boolean panelBusqueda = true;

    public String buscarEmpleados() {
        if (criterioEmpleado == null) {
            JSFUtils.errorMessage("Conceptos empleado: ",
                    "El criterio es necesario");
        } else {

            List<InfoEmpleadoDTO> listaEmpleados = empleadoEJB
                    .consultaPorCriterio(criterioEmpleado);
            mostrarTablaEmpleados = (true);
            if (!listaEmpleados.isEmpty()) {
                empleadoLista.clear();
                empleadoLista = listaEmpleados;
            }
        }
        return null;
    }

    public void buscarConceptos() {
        InfoConfiguracionDTO infoConfiguracionDTO = configuracionPresupuestal
                .obtenerPorIdEmpleado(empleadoSeleccionado.getIdEmpleado());
        try {
            conceptos = conceptoNominaFederalesEJB
                    .obtenerConceptosPorConfiguracionPresupuestal(
                            infoConfiguracionDTO
                                    .getIdConfiguracionPresupuesto());
        } catch (NullPointerException e) {
            JSFUtils.errorMessage("Validacion:",
                    "El empleado no tiene una configuracion presupuestal.");
        }

        PuestoGeneralDTO puesto = puestoGeneral
                .puestoPorClave(empleadoDatos.getCodigoPuesto());
        TabuladorDTO tabulador = tabuladorEJB.obtenerTabuladorPorPuesto(
                puesto.getIdPuestoGeneral(), FechaUtil.ejercicioActual());
        if (tabulador.getIdTabulador() == null) {
            JSFUtils.errorMessage("Tabulador:",
                    "No se encontro configuración de tabulador para el empleado seleccionado");
        }
        BigDecimal subtotal = BigDecimal.ZERO;
        for (ConceptoNominaFederalesDTO conceptoFederal : conceptos) {
            if (conceptoFederal.getFormula() != null) {

                String formulanueva = conceptoFederal.getFormula().replace(
                        "EX0700", tabulador.getSueldoBrutoMensual() + "");
                formulanueva = formulanueva.replace("EX4200",
                        tabulador.getAsignacionBrutaMensual() + "");
                formulanueva = formulanueva.replace("EX55AG",
                        tabulador.getAgaBrutaMensual() + "");
                String resFormula = conceptoNominaFederalesEJB
                        .evaluarFormula(formulanueva);

                BigDecimal valorFormula = new BigDecimal(resFormula).setScale(2,
                        RoundingMode.HALF_UP);
                conceptoFederal.setImporte(valorFormula);

                if (conceptoFederal.getTipo().compareTo(1) == 0) {
                    subtotal = subtotal.add(conceptoFederal.getImporte());
                }
                if (conceptoFederal.getTipo().compareTo(2) == 0) {
                    subtotal = subtotal.subtract(conceptoFederal.getImporte());
                }
            }
        }
        total = subtotal;
    }

    public String irMovimientos(InfoEmpleadoDTO empleado) {
        panelBusqueda = (Boolean.TRUE);
        mostrarTablaEmpleados = (Boolean.FALSE);

        empleadoSeleccionado = empleado;
        try {
            empleadoDatos = empleadoEJB.obtenerInformacionEmpleado(
                    empleadoSeleccionado.getIdEmpleado());
            if (empleadoDatos.getPuesto() != null) {

                buscarConceptos();
            }
        } catch (ReglaNegocioException e) {
            JSFUtils.errorMessage("", e.getMessage());
        }
        return "";
    }

    public String getCriterioEmpleado() {
        return criterioEmpleado;
    }

    public void setCriterioEmpleado(String criterioEmpleado) {
        this.criterioEmpleado = criterioEmpleado;
    }

    public List<ConceptoNominaFederalesDTO> getConceptos() {
        return conceptos;
    }

    public void setConceptos(List<ConceptoNominaFederalesDTO> conceptos) {
        this.conceptos = conceptos;
    }

    public List<InfoEmpleadoDTO> getEmpleadoLista() {
        return empleadoLista;
    }

    public void setEmpleadoLista(List<InfoEmpleadoDTO> empleadoLista) {
        this.empleadoLista = empleadoLista;
    }

    public InfoEmpleadoDTO getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(InfoEmpleadoDTO empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public Boolean getMostrarTablaEmpleados() {
        return mostrarTablaEmpleados;
    }

    public void setMostrarTablaEmpleados(Boolean mostrarTablaEmpleados) {
        this.mostrarTablaEmpleados = mostrarTablaEmpleados;
    }

    public EmpleadoDetalladoDTO getEmpleadoDatos() {
        return empleadoDatos;
    }

    public void setEmpleadoDatos(EmpleadoDetalladoDTO empleadoDatos) {
        this.empleadoDatos = empleadoDatos;
    }

    public Boolean getPanelBusqueda() {
        return panelBusqueda;
    }

    public void setPanelBusqueda(Boolean panelBusqueda) {
        this.panelBusqueda = panelBusqueda;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
