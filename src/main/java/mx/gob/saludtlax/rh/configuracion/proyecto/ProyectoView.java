/*
 * ProyectoView.java
 * Creado el 25/07/2016 01:46:15 PM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.proyecto;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class ProyectoView {
    private int ejercicioFiscal;
    private ProyectoDTO proyectoDTO;
    private boolean visibleTablaProyecto = true; 
    private boolean visiblePanelNuevo = false;
    private boolean editar = false;

    public int getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(int ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

    public ProyectoDTO getProyectoDTO() {
        return proyectoDTO;
    }

    public void setProyectoDTO(ProyectoDTO proyectoDTO) {
        this.proyectoDTO = proyectoDTO;
    }

    public boolean isVisibleTablaProyecto() {
        return visibleTablaProyecto;
    }

    public void setVisibleTablaProyecto(boolean visibleTablaProyecto) {
        this.visibleTablaProyecto = visibleTablaProyecto;
    }

    public boolean isVisiblePanelNuevo() {
        return visiblePanelNuevo;
    }

    public void setVisiblePanelNuevo(boolean visiblePanelNuevo) {
        this.visiblePanelNuevo = visiblePanelNuevo;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }
}
