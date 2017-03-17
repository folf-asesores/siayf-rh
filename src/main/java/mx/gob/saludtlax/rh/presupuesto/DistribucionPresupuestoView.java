package mx.gob.saludtlax.rh.presupuesto;

import java.util.List;

import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.SubfuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;
import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsableDTO;

public class DistribucionPresupuestoView {
	private List<DistribucionPresupuestoDTO> listaDistribucion;
	private List<TipoNombramientoDTO> listaTipoNombramiento;
	private List<DependenciaDTO> listaDependencia;
	private List<SubfuenteFinanciamientoDTO> listaSubfuente;
	private List<UnidadResponsableDTO> listaUnidadResponsable;
	private List<DistribucionPresupuestoDTO> listaDistribucionPresupuesto;
	private ProyeccionesPresupuestalesDTO proyeccionPresupuestal;
	private DistribucionPresupuestoDTO distribucionPresupuesto;
	private Boolean mostrarDistribucion;
	private Boolean mostrarPrincipal;
    private Integer anioPresupuesto;
    private Integer idTipoNombramiento;
    private Integer idSubfuente;
    private Integer idDependencia;
    private Integer anio;
    
    
    private boolean mostrarOpcionDescarga;
    
    
    /***********Getters and Setters************/
    public List<DistribucionPresupuestoDTO> getListaDistribucion() {
        return listaDistribucion;
    }
    public void setListaDistribucion(List<DistribucionPresupuestoDTO> listaDistribucion) {
        this.listaDistribucion = listaDistribucion;
    }
    public List<TipoNombramientoDTO> getListaTipoNombramiento() {
        return listaTipoNombramiento;
    }
    public void setListaTipoNombramiento(List<TipoNombramientoDTO> listaTipoNombramiento) {
        this.listaTipoNombramiento = listaTipoNombramiento;
    }
    public DistribucionPresupuestoDTO getDistribucionPresupuesto() {
        return distribucionPresupuesto;
    }
    public void setDistribucionPresupuesto(DistribucionPresupuestoDTO distribucionPresupuesto) {
        this.distribucionPresupuesto = distribucionPresupuesto;
    }
    public Boolean getMostrarDistribucion() {
		return mostrarDistribucion;
	}
	public void setMostrarDistribucion(Boolean mostrarDistribucion) {
		this.mostrarDistribucion = mostrarDistribucion;
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
	public ProyeccionesPresupuestalesDTO getProyeccionPresupuestal() {
		return proyeccionPresupuestal;
	}
	public void setProyeccionPresupuestal(ProyeccionesPresupuestalesDTO proyeccionPresupuestal) {
		this.proyeccionPresupuestal = proyeccionPresupuestal;
	}
	public Integer getIdDependencia() {
		return idDependencia;
	}
	public void setIdDependencia(Integer idDependencia) {
		this.idDependencia = idDependencia;
	}
	public List<DependenciaDTO> getListaDependencia() {
		return listaDependencia;
	}
	public void setListaDependencia(List<DependenciaDTO> listaDependencia) {
		this.listaDependencia = listaDependencia;
	}
	public List<SubfuenteFinanciamientoDTO> getListaSubfuente() {
		return listaSubfuente;
	}
	public void setListaSubfuente(List<SubfuenteFinanciamientoDTO> listaSubfuente) {
		this.listaSubfuente = listaSubfuente;
	}
	public List<UnidadResponsableDTO> getListaUnidadResponsable() {
		return listaUnidadResponsable;
	}
	public void setListaUnidadResponsable(List<UnidadResponsableDTO> listaUnidadResponsable) {
		this.listaUnidadResponsable = listaUnidadResponsable;
	}
	public List<DistribucionPresupuestoDTO> getListaDistribucionPresupuesto() {
		return listaDistribucionPresupuesto;
	}
	public void setListaDistribucionPresupuesto(List<DistribucionPresupuestoDTO> listaDistribucionPresupuesto) {
		this.listaDistribucionPresupuesto = listaDistribucionPresupuesto;
	}
	public Integer getIdSubfuente() {
		return idSubfuente;
	}
	public void setIdSubfuente(Integer idSubfuente) {
		this.idSubfuente = idSubfuente;
	}
	/**
	 * @return the mostrarOpcionDescarga
	 */
	public boolean isMostrarOpcionDescarga() {
		return mostrarOpcionDescarga;
	}
	/**
	 * @param mostrarOpcionDescarga the mostrarOpcionDescarga to set
	 */
	public void setMostrarOpcionDescarga(boolean mostrarOpcionDescarga) {
		this.mostrarOpcionDescarga = mostrarOpcionDescarga;
	}	
}