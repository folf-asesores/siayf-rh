/*
 *
 */

package mx.gob.saludtlax.rh.empleados.interinatos;

import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.InfoAspiranteDTO;
import mx.gob.saludtlax.rh.empleados.administracion.EmpleadoDetalladoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.empleados.datolaboral.InfoDatosLaboralesDTO;
import mx.gob.saludtlax.rh.empleados.movimientos.DetalleMovimientoDTO;

/**
 * @author Leila Schiaffini Ehuan
 * @since 16/09/2016 14:25:52
 *
 */
public class AltaInterinatoView {
    private List<DisponiblesInterinatoDTO> puestosDisponibles;
    private List<InfoAspiranteDTO> aspirantes;
    private List<InfoEmpleadoDTO> empleados;
    private DisponiblesInterinatoDTO puestoSeleccionado = new DisponiblesInterinatoDTO();
    private DetalleMovimientoDTO detalleMovimiento = new DetalleMovimientoDTO();
    private InfoDatosLaboralesDTO detalle = new InfoDatosLaboralesDTO();
    private InterinatoDTO registro = new InterinatoDTO();
    private EmpleadoDetalladoDTO empleado = new EmpleadoDetalladoDTO();
    private InfoAspiranteDTO aspirante = new InfoAspiranteDTO();

    private List<SelectItem> tiposBusqueda;
    private List<SelectItem> listaTipoCandidato;
    private boolean mostrarRegistro;
    private boolean mostrarBusqueda;
    private boolean mostrarConsultaEmpleado;
    private boolean mostrarConsultaAspirante;
    private boolean mostrarConfirmacionEmpleado;
    private boolean mostrarConfirmacionAspirante;
    private boolean mostrarDetallePuestoActivo;
    private Integer tipoBusqueda;
    private String criterioBusqueda;
    private String consulta;
    private Integer idUsuario;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public boolean isMostrarDetallePuestoActivo() {
        return mostrarDetallePuestoActivo;
    }

    public void setMostrarDetallePuestoActivo(boolean mostrarDetallePuestoActivo) {
        this.mostrarDetallePuestoActivo = mostrarDetallePuestoActivo;
    }

    public boolean isMostrarConfirmacionEmpleado() {
        return mostrarConfirmacionEmpleado;
    }

    public void setMostrarConfirmacionEmpleado(boolean mostrarConfirmacionEmpleado) {
        this.mostrarConfirmacionEmpleado = mostrarConfirmacionEmpleado;
    }

    public boolean isMostrarConfirmacionAspirante() {
        return mostrarConfirmacionAspirante;
    }

    public void setMostrarConfirmacionAspirante(boolean mostrarConfirmacionAspirante) {
        this.mostrarConfirmacionAspirante = mostrarConfirmacionAspirante;
    }

    public EmpleadoDetalladoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDetalladoDTO empleado) {
        this.empleado = empleado;
    }

    public InfoAspiranteDTO getAspirante() {
        return aspirante;
    }

    public void setAspirante(InfoAspiranteDTO aspirante) {
        this.aspirante = aspirante;
    }

    public boolean isMostrarConsultaEmpleado() {
        return mostrarConsultaEmpleado;
    }

    public void setMostrarConsultaEmpleado(boolean mostrarConsultaEmpleado) {
        this.mostrarConsultaEmpleado = mostrarConsultaEmpleado;
    }

    public boolean isMostrarConsultaAspirante() {
        return mostrarConsultaAspirante;
    }

    public void setMostrarConsultaAspirante(boolean mostrarConsultaAspirante) {
        this.mostrarConsultaAspirante = mostrarConsultaAspirante;
    }

    public List<SelectItem> getListaTipoCandidato() {
        return listaTipoCandidato;
    }

    public void setListaTipoCandidato(List<SelectItem> listaTipoCandidato) {
        this.listaTipoCandidato = listaTipoCandidato;
    }

    public List<InfoEmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<InfoEmpleadoDTO> empleados) {
        this.empleados = empleados;
    }

    public List<InfoAspiranteDTO> getAspirantes() {
        return aspirantes;
    }

    public void setAspirantes(List<InfoAspiranteDTO> aspirantes) {
        this.aspirantes = aspirantes;
    }

    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    public InterinatoDTO getRegistro() {
        return registro;
    }

    public void setRegistro(InterinatoDTO registro) {
        this.registro = registro;
    }

    public DisponiblesInterinatoDTO getPuestoSeleccionado() {
        return puestoSeleccionado;
    }

    public void setPuestoSeleccionado(DisponiblesInterinatoDTO puestoSeleccionado) {
        this.puestoSeleccionado = puestoSeleccionado;
    }

    public DetalleMovimientoDTO getDetalleMovimiento() {
        return detalleMovimiento;
    }

    public void setDetalleMovimiento(DetalleMovimientoDTO detalleMovimiento) {
        this.detalleMovimiento = detalleMovimiento;
    }

    public Integer getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setTipoBusqueda(Integer tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public List<SelectItem> getTiposBusqueda() {
        return tiposBusqueda;
    }

    public void setTiposBusqueda(List<SelectItem> tiposBusqueda) {
        this.tiposBusqueda = tiposBusqueda;
    }

    public boolean isMostrarRegistro() {
        return mostrarRegistro;
    }

    public void setMostrarRegistro(boolean mostrarRegistro) {
        this.mostrarRegistro = mostrarRegistro;
    }

    public boolean isMostrarBusqueda() {
        return mostrarBusqueda;
    }

    public void setMostrarBusqueda(boolean mostrarBusqueda) {
        this.mostrarBusqueda = mostrarBusqueda;
    }

    public InfoDatosLaboralesDTO getDetalle() {
        return detalle;
    }

    public void setDetalle(InfoDatosLaboralesDTO detalle) {
        this.detalle = detalle;
    }

    public List<DisponiblesInterinatoDTO> getPuestosDisponibles() {
        return puestosDisponibles;
    }

    public void setPuestosDisponibles(List<DisponiblesInterinatoDTO> puestosDisponibles) {
        this.puestosDisponibles = puestosDisponibles;
    }

}
