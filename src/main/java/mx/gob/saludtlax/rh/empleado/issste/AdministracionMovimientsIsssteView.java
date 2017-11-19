
package mx.gob.saludtlax.rh.empleado.issste;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

public class AdministracionMovimientsIsssteView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6862305759177180301L;

    private List<InfoEmpleadoDTO> listaEmpleados = new ArrayList<>();
    private List<InfoMovimientoIsssteDTO> listaMovimiento = new ArrayList<>();
    private InfoEmpleadoDTO empleadoSeleccionado = new InfoEmpleadoDTO();
    private MovimientoIsssteEmpleadoDTO altaTrabajadorDTO = new MovimientoIsssteEmpleadoDTO();
    private MovimientoIsssteEmpleadoDTO modificacionTrabajadorDTO = new MovimientoIsssteEmpleadoDTO();
    private MovimientoIsssteEmpleadoDTO bajaTrabajadorDTO = new MovimientoIsssteEmpleadoDTO();
    private InfoMovimientoIsssteDTO movimientoSeleccinado = new InfoMovimientoIsssteDTO();

    private Integer idEmpleado;

    private String criterio;
    private String urlReporte;

    private boolean administracionMovimientos = true;
    private boolean altaTrabajador = false;
    private boolean formularioAltaTrabajador = false;
    private boolean bajaIssste = false;
    private boolean sueldoTrabajador = false;
    private boolean opcionDisponibles = true;
    private boolean ventanaNuevoReporte = false;

    private List<SelectItem> listaCausaBaja = new ArrayList<>();
    private List<SelectItem> listaNombramiento = new ArrayList<>();
    private List<SelectItem> listaNivelSalario = new ArrayList<>();

    public String getUrlReporte() {
        return urlReporte;
    }

    public void setUrlReporte(String urlReporte) {
        this.urlReporte = urlReporte;
    }

    public List<InfoEmpleadoDTO> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<InfoEmpleadoDTO> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public InfoEmpleadoDTO getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(InfoEmpleadoDTO empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public boolean isAdministracionMovimientos() {
        return administracionMovimientos;
    }

    public void setAdministracionMovimientos(boolean administracionMovimientos) {
        this.administracionMovimientos = administracionMovimientos;
    }

    public boolean isAltaTrabajador() {
        return altaTrabajador;
    }

    public void setAltaTrabajador(boolean altaTrabajador) {
        this.altaTrabajador = altaTrabajador;
    }

    public boolean isBajaIssste() {
        return bajaIssste;
    }

    public void setBajaIssste(boolean bajaIssste) {
        this.bajaIssste = bajaIssste;
    }

    public boolean isSueldoTrabajador() {
        return sueldoTrabajador;
    }

    public void setSueldoTrabajador(boolean sueldoTrabajador) {
        this.sueldoTrabajador = sueldoTrabajador;
    }

    public List<SelectItem> getListaCausaBaja() {
        return listaCausaBaja;
    }

    public void setListaCausaBaja(List<SelectItem> listaCausaBaja) {
        this.listaCausaBaja = listaCausaBaja;
    }

    public List<SelectItem> getListaNombramiento() {
        return listaNombramiento;
    }

    public void setListaNombramiento(List<SelectItem> listaNombramiento) {
        this.listaNombramiento = listaNombramiento;
    }

    public List<SelectItem> getListaNivelSalario() {
        return listaNivelSalario;
    }

    public void setListaNivelSalario(List<SelectItem> listaNivelSalario) {
        this.listaNivelSalario = listaNivelSalario;
    }

    public boolean isOpcionDisponibles() {
        return opcionDisponibles;
    }

    public void setOpcionDisponibles(boolean opcionDisponibles) {
        this.opcionDisponibles = opcionDisponibles;
    }

    public boolean isFormularioAltaTrabajador() {
        return formularioAltaTrabajador;
    }

    public void setFormularioAltaTrabajador(boolean formularioAltaTrabajador) {
        this.formularioAltaTrabajador = formularioAltaTrabajador;
    }

    public MovimientoIsssteEmpleadoDTO getAltaTrabajadorDTO() {
        return altaTrabajadorDTO;
    }

    public void setAltaTrabajadorDTO(MovimientoIsssteEmpleadoDTO altaTrabajadorDTO) {
        this.altaTrabajadorDTO = altaTrabajadorDTO;
    }

    public List<InfoMovimientoIsssteDTO> getListaMovimiento() {
        return listaMovimiento;
    }

    public void setListaMovimiento(List<InfoMovimientoIsssteDTO> listaMovimiento) {
        this.listaMovimiento = listaMovimiento;
    }

    public InfoMovimientoIsssteDTO getMovimientoSeleccinado() {
        return movimientoSeleccinado;
    }

    public void setMovimientoSeleccinado(InfoMovimientoIsssteDTO movimientoSeleccinado) {
        this.movimientoSeleccinado = movimientoSeleccinado;
    }

    public MovimientoIsssteEmpleadoDTO getModificacionTrabajadorDTO() {
        return modificacionTrabajadorDTO;
    }

    public void setModificacionTrabajadorDTO(MovimientoIsssteEmpleadoDTO modificacionTrabajadorDTO) {
        this.modificacionTrabajadorDTO = modificacionTrabajadorDTO;
    }

    public MovimientoIsssteEmpleadoDTO getBajaTrabajadorDTO() {
        return bajaTrabajadorDTO;
    }

    public void setBajaTrabajadorDTO(MovimientoIsssteEmpleadoDTO bajaTrabajadorDTO) {
        this.bajaTrabajadorDTO = bajaTrabajadorDTO;
    }

    public boolean isVentanaNuevoReporte() {
        return ventanaNuevoReporte;
    }

    public void setVentanaNuevoReporte(boolean ventanaNuevoReporte) {
        this.ventanaNuevoReporte = ventanaNuevoReporte;
    }

}
