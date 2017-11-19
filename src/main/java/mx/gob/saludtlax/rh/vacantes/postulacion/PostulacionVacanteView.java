/*
 *
 */

package mx.gob.saludtlax.rh.vacantes.postulacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.InfoAspiranteDTO;
import mx.gob.saludtlax.rh.empleados.administracion.EmpleadoDetalladoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.puestosautorizados.PuestoEmpleadoDTO;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-06
 *
 */
public class PostulacionVacanteView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8176948186193407368L;

    private List<SelectItem> listaPuestos = new ArrayList<>();
    private List<PuestoDisponibleDTO> puestosDisponibles = new ArrayList<>();
    private List<InfoAspiranteDTO> aspirantes = new ArrayList<>();
    private List<InfoEmpleadoDTO> empleados = new ArrayList<>();
    private List<InfoCandidatoDTO> candidatosPostulados = new ArrayList<>();

    private PuestoEmpleadoDTO puesto = new PuestoEmpleadoDTO();
    private EmpleadoDetalladoDTO empleado = new EmpleadoDetalladoDTO();
    private InfoAspiranteDTO aspirante = new InfoAspiranteDTO();
    private PostulacionDTO postulacion = new PostulacionDTO();

    private boolean mostrarBusqueda;
    private boolean mostrarPostulacion;
    private boolean mostrarConsultaAspirante;
    private boolean mostrarConsultaEmpleado;
    private boolean mostrarDetalleEmpleado;
    private boolean mostrarDetallePuestoActivo;
    private boolean mostrarDetalleAspirante;
    private boolean mostrarDetalle;
    private boolean mostrarCapturaPuesto;
    private boolean mostrarDetallePuesto;
    private String criterioBusqueda;
    private Integer tipoCandidato;
    private Integer idPostulacion;

    public List<SelectItem> getListaPuestos() {
        return listaPuestos;
    }

    public void setListaPuestos(List<SelectItem> listaPuestos) {
        this.listaPuestos = listaPuestos;
    }

    public boolean isMostrarDetallePuesto() {
        return mostrarDetallePuesto;
    }

    public void setMostrarDetallePuesto(boolean mostrarDetallePuesto) {
        this.mostrarDetallePuesto = mostrarDetallePuesto;
    }

    public boolean isMostrarCapturaPuesto() {
        return mostrarCapturaPuesto;
    }

    public void setMostrarCapturaPuesto(boolean mostrarCapturaPuesto) {
        this.mostrarCapturaPuesto = mostrarCapturaPuesto;
    }

    public boolean isMostrarDetalle() {
        return mostrarDetalle;
    }

    public void setMostrarDetalle(boolean mostrarDetalle) {
        this.mostrarDetalle = mostrarDetalle;
    }

    public PostulacionDTO getPostulacion() {
        return postulacion;
    }

    public void setPostulacion(PostulacionDTO postulacion) {
        this.postulacion = postulacion;
    }

    public Integer getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Integer idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public List<InfoCandidatoDTO> getCandidatosPostulados() {
        return candidatosPostulados;
    }

    public void setCandidatosPostulados(
            List<InfoCandidatoDTO> candidatosPostulados) {
        this.candidatosPostulados = candidatosPostulados;
    }

    public boolean isMostrarDetallePuestoActivo() {
        return mostrarDetallePuestoActivo;
    }

    public void setMostrarDetallePuestoActivo(
            boolean mostrarDetallePuestoActivo) {
        this.mostrarDetallePuestoActivo = mostrarDetallePuestoActivo;
    }

    public InfoAspiranteDTO getAspirante() {
        return aspirante;
    }

    public void setAspirante(InfoAspiranteDTO aspirante) {
        this.aspirante = aspirante;
    }

    public boolean isMostrarDetalleEmpleado() {
        return mostrarDetalleEmpleado;
    }

    public void setMostrarDetalleEmpleado(boolean mostrarDetalleEmpleado) {
        this.mostrarDetalleEmpleado = mostrarDetalleEmpleado;
    }

    public boolean isMostrarDetalleAspirante() {
        return mostrarDetalleAspirante;
    }

    public void setMostrarDetalleAspirante(boolean mostrarDetalleAspirante) {
        this.mostrarDetalleAspirante = mostrarDetalleAspirante;
    }

    public EmpleadoDetalladoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDetalladoDTO empleado) {
        this.empleado = empleado;
    }

    public Integer getTipoCandidato() {
        return tipoCandidato;
    }

    public void setTipoCandidato(Integer tipoCandidato) {
        this.tipoCandidato = tipoCandidato;
    }

    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    public List<InfoAspiranteDTO> getAspirantes() {
        return aspirantes;
    }

    public void setAspirantes(List<InfoAspiranteDTO> aspirantes) {
        this.aspirantes = aspirantes;
    }

    public List<InfoEmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<InfoEmpleadoDTO> empleados) {
        this.empleados = empleados;
    }

    public boolean isMostrarConsultaAspirante() {
        return mostrarConsultaAspirante;
    }

    public void setMostrarConsultaAspirante(boolean mostrarConsultaAspirante) {
        this.mostrarConsultaAspirante = mostrarConsultaAspirante;
    }

    public boolean isMostrarConsultaEmpleado() {
        return mostrarConsultaEmpleado;
    }

    public void setMostrarConsultaEmpleado(boolean mostrarConsultaEmpleado) {
        this.mostrarConsultaEmpleado = mostrarConsultaEmpleado;
    }

    private List<SelectItem> listaTipoCandidato;

    public List<SelectItem> getListaTipoCandidato() {
        return listaTipoCandidato;
    }

    public void setListaTipoCandidato(List<SelectItem> listaTipoCandidato) {
        this.listaTipoCandidato = listaTipoCandidato;
    }

    public boolean isMostrarBusqueda() {
        return mostrarBusqueda;
    }

    public void setMostrarBusqueda(boolean mostrarBusqueda) {
        this.mostrarBusqueda = mostrarBusqueda;
    }

    public boolean isMostrarPostulacion() {
        return mostrarPostulacion;
    }

    public void setMostrarPostulacion(boolean mostrarPostulacion) {
        this.mostrarPostulacion = mostrarPostulacion;
    }

    public PuestoEmpleadoDTO getPuesto() {
        return puesto;
    }

    public void setPuesto(PuestoEmpleadoDTO puesto) {
        this.puesto = puesto;
    }

    public List<PuestoDisponibleDTO> getPuestosDisponibles() {
        return puestosDisponibles;
    }

    public void setPuestosDisponibles(
            List<PuestoDisponibleDTO> puestosDisponibles) {
        this.puestosDisponibles = puestosDisponibles;
    }

}
