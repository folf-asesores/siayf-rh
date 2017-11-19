
package mx.gob.saludtlax.rh.presupuesto;

import java.util.List;

import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;

public class ProyeccionesPresupuestalesView {
    private List<ProyeccionesPresupuestalesDTO> listaProyecciones;
    private List<TipoNombramientoDTO> listaTipoNombramiento;
    private ProyeccionesPresupuestalesDTO proyeccionPresupuestal;
    private Boolean mostrarProyeccion;
    private Boolean mostrarPrincipal;
    private Integer anioPresupuesto;
    private Integer idTipoNombramiento;
    private Integer anio;

    private boolean mostrarOpcionDescarga = false;

    public List<ProyeccionesPresupuestalesDTO> getListaProyecciones() {
        return listaProyecciones;
    }

    public void setListaProyecciones(List<ProyeccionesPresupuestalesDTO> listaProyecciones) {
        this.listaProyecciones = listaProyecciones;
    }

    public List<TipoNombramientoDTO> getListaTipoNombramiento() {
        return listaTipoNombramiento;
    }

    public void setListaTipoNombramiento(List<TipoNombramientoDTO> listaTipoNombramiento) {
        this.listaTipoNombramiento = listaTipoNombramiento;
    }

    public ProyeccionesPresupuestalesDTO getProyeccionPresupuestal() {
        return proyeccionPresupuestal;
    }

    public void setProyeccionPresupuestal(ProyeccionesPresupuestalesDTO proyeccionPresupuestal) {
        this.proyeccionPresupuestal = proyeccionPresupuestal;
    }

    public Boolean getMostrarProyeccion() {
        return mostrarProyeccion;
    }

    public void setMostrarProyeccion(Boolean mostrarProyeccion) {
        this.mostrarProyeccion = mostrarProyeccion;
    }

    public Boolean getMostrarPrincipal() {
        return mostrarPrincipal;
    }

    public void setMostrarPrincipal(Boolean mostrarPrincipal) {
        this.mostrarPrincipal = mostrarPrincipal;
    }

    public Integer getAnioPresupuesto() {
        return anioPresupuesto;
    }

    public void setAnioPresupuesto(Integer anioPresupuesto) {
        this.anioPresupuesto = anioPresupuesto;
    }

    public Integer getIdTipoNombramiento() {
        return idTipoNombramiento;
    }

    public void setIdTipoNombramiento(Integer idTipoNombramiento) {
        this.idTipoNombramiento = idTipoNombramiento;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    /**
     * @return the mostrarOpcionDescarga
     */
    public boolean isMostrarOpcionDescarga() {
        return mostrarOpcionDescarga;
    }

    /**
     * @param mostrarOpcionDescarga
     *            the mostrarOpcionDescarga to set
     */
    public void setMostrarOpcionDescarga(boolean mostrarOpcionDescarga) {
        this.mostrarOpcionDescarga = mostrarOpcionDescarga;
    }
}