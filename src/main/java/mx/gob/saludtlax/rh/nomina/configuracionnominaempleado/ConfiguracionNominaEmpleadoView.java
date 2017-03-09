package mx.gob.saludtlax.rh.nomina.configuracionnominaempleado;

import java.util.List;

import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaDTO;
import mx.gob.saludtlax.rh.empleados.administracion.EmpleadoDetalladoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

public class ConfiguracionNominaEmpleadoView {
    private List<InfoEmpleadoDTO> empleados;
    private EmpleadoDetalladoDTO empleadoDatos;
    private List<ConceptoNominaDTO> conceptos;
    private String criterio;
    private boolean mostrarBusqueda;
    private boolean mostrarResultados;
    private boolean mostrarFormulario;

    public void panelBusqueda() {
        mostrarBusqueda = true;
        mostrarFormulario = false;
    }

    public void panelFormulario() {
        mostrarBusqueda = false;
        mostrarFormulario = true;
    }

    public List<InfoEmpleadoDTO> getEmpleados() {
        return empleados;
    }
    public void setEmpleados(List<InfoEmpleadoDTO> empleados) {
        this.empleados = empleados;
    }
    public EmpleadoDetalladoDTO getEmpleadoDatos() {
        return empleadoDatos;
    }
    public void setEmpleadoDatos(EmpleadoDetalladoDTO empleadoDatos) {
        this.empleadoDatos = empleadoDatos;
    }
    public String getCriterio() {
        return criterio;
    }
    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }
    public boolean isMostrarBusqueda() {
        return mostrarBusqueda;
    }
    public void setMostrarBusqueda(boolean mostrarBusqueda) {
        this.mostrarBusqueda = mostrarBusqueda;
    }
    public boolean isMostrarResultados() {
        return mostrarResultados;
    }
    public void setMostrarResultados(boolean mostrarResultados) {
        this.mostrarResultados = mostrarResultados;
    }
    public boolean isMostrarFormulario() {
        return mostrarFormulario;
    }
    public void setMostrarFormulario(boolean mostrarFormulario) {
        this.mostrarFormulario = mostrarFormulario;
    }
}