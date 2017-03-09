package mx.gob.saludtlax.rh.nomina.consultamovimientos;
import java.util.List;
import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaDTO;
import mx.gob.saludtlax.rh.empleados.administracion.EmpleadoDetalladoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.nomina.productosnomina.ProductoNominaListaDTO;

/**
 * 
 * @author José Pablo
 *
 */
public class ConsultaMovimientosView {
	private List<InfoEmpleadoDTO> empleados;
    private EmpleadoDetalladoDTO empleadoDatos;
    private List<ConceptoNominaDTO> conceptos;
    private String criterio;
    private boolean mostrarBusqueda;
    private boolean mostrarResultados;
    private boolean mostrarFormulario;
    private MovimientosNominaEmpleadoDTO empleadoSelect;
    private Boolean consultaMovimientos;
    private List<MovimientosNominaEmpleadoDTO> consulta;

   

	public void panelBusqueda() {
        mostrarBusqueda = true;
        mostrarFormulario = false;
    }

    public void panelFormulario() {
        mostrarBusqueda = false;
        mostrarFormulario = true;
    }
    
    public void panelConsulta(){
    
        
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

	public MovimientosNominaEmpleadoDTO getEmpleadoSelect() {
		return empleadoSelect;
	}

	public void setEmpleadoSelect(MovimientosNominaEmpleadoDTO empleadoSelect) {
		this.empleadoSelect = empleadoSelect;
	}

	public Boolean getConsultaMovimientos() {
		return consultaMovimientos;
	}

	public void setConsultaMovimientos(Boolean consultaMovimientos) {
		this.consultaMovimientos = consultaMovimientos;
	}
	 public List<MovimientosNominaEmpleadoDTO> getConsulta() {
			return consulta;
		}

		public void setConsulta(List<MovimientosNominaEmpleadoDTO> consulta) {
			this.consulta = consulta;
		}
	
}
