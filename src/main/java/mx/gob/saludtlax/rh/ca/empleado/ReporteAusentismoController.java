package mx.gob.saludtlax.rh.ca.empleado;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

@ManagedBean(name = "reporteAusentismoController")
@ViewScoped
public class ReporteAusentismoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4120415982583328913L;
	
	
	@Inject
	private Catalogo catalogo;
	
	
	private Date fechaInicio;
	private Date fechaFin;
	List<CatalogoDTO> catalogoTipoContratacionDTO;
	public Integer idTipoContratacion = -1;
	public Integer idAdscripcion = -1;
	
	@PostConstruct
	public void init() {

		
	}
	
	
	public String imprimirReporte() {
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyyMMdd");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("url",
				"reporte-ausentismo_empleado?fechaInicio="
						+ dt1.format(fechaInicio) + "&fechaFin=" + dt1.format(fechaFin) 
						+ "&ida=" + idAdscripcion );
		return "imprimirreporte.xhtml?faces-redirect=true";
	}
	
	public List<SelectItem> getAdscripciones() {

		return SelectItemsUtil.listaCatalogos(catalogo.consultarAdscripciones());
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<CatalogoDTO> getCatalogoTipoContratacionDTO() {
		return catalogoTipoContratacionDTO;
	}

	public void setCatalogoTipoContratacionDTO(List<CatalogoDTO> catalogoTipoContratacionDTO) {
		this.catalogoTipoContratacionDTO = catalogoTipoContratacionDTO;
	}
	public Integer getIdTipoContratacion() {
		return idTipoContratacion;
	}
	public void setIdTipoContratacion(Integer idTipoContratacion) {
		this.idTipoContratacion = idTipoContratacion;
	}


	public Integer getIdAdscripcion() {
		return idAdscripcion;
	}


	public void setIdAdscripcion(Integer idAdscripcion) {
		this.idAdscripcion = idAdscripcion;
	}
	
	
	

}
