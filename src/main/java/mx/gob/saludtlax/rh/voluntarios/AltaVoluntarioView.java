
package mx.gob.saludtlax.rh.voluntarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.InfoAspiranteDTO;

public class AltaVoluntarioView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1259806070280582328L;
    private List<SelectItem> programas = new ArrayList<>();
    private List<SelectItem> listaAdscripciones;
    private List<SelectItem> listaSubadscripcion;
    private List<SelectItem> listaServicio;
    private List<SelectItem> listaFuncion;
    private List<InfoAspiranteDTO> aspirantes = new ArrayList<>();
    private InfoAspiranteDTO aspirante = new InfoAspiranteDTO();
    private AltaVoluntarioDTO alta = new AltaVoluntarioDTO();
    private String criterio;

    private boolean mostrarBusqueda;
    private boolean mostrarAlta;

    public List<SelectItem> getListaAdscripciones() {
        return listaAdscripciones;
    }

    public void setListaAdscripciones(List<SelectItem> listaAdscripciones) {
        this.listaAdscripciones = listaAdscripciones;
    }

    public List<SelectItem> getListaSubadscripcion() {
        return listaSubadscripcion;
    }

    public void setListaSubadscripcion(List<SelectItem> listaSubadscripcion) {
        this.listaSubadscripcion = listaSubadscripcion;
    }

    public List<SelectItem> getListaServicio() {
        return listaServicio;
    }

    public void setListaServicio(List<SelectItem> listaServicio) {
        this.listaServicio = listaServicio;
    }

    public List<SelectItem> getListaFuncion() {
        return listaFuncion;
    }

    public void setListaFuncion(List<SelectItem> listaFuncion) {
        this.listaFuncion = listaFuncion;
    }

    public List<SelectItem> getProgramas() {
        return programas;
    }

    public void setProgramas(List<SelectItem> programas) {
        this.programas = programas;
    }

    public AltaVoluntarioDTO getAlta() {
        return alta;
    }

    public void setAlta(AltaVoluntarioDTO alta) {
        this.alta = alta;
    }

    public InfoAspiranteDTO getAspirante() {
        return aspirante;
    }

    public void setAspirante(InfoAspiranteDTO aspirante) {
        this.aspirante = aspirante;
    }

    public boolean isMostrarBusqueda() {
        return mostrarBusqueda;
    }

    public void setMostrarBusqueda(boolean mostrarBusqueda) {
        this.mostrarBusqueda = mostrarBusqueda;
    }

    public boolean isMostrarAlta() {
        return mostrarAlta;
    }

    public void setMostrarAlta(boolean mostrarAlta) {
        this.mostrarAlta = mostrarAlta;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public List<InfoAspiranteDTO> getAspirantes() {
        return aspirantes;
    }

    public void setAspirantes(List<InfoAspiranteDTO> aspirantes) {
        this.aspirantes = aspirantes;
    }

}