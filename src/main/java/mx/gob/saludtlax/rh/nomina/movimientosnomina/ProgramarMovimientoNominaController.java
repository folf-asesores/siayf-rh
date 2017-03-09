package mx.gob.saludtlax.rh.nomina.movimientosnomina;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaService;
import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean
@ViewScoped
public class ProgramarMovimientoNominaController  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1677426588024670661L;

	
	@Inject
	ProgramacionMovimientosEJB programacionMovimientosEJB;
	
	@Inject
	TipoMovimientosNominaEJB tipoMovimientosNominaEJB;
	
	@Inject
	Catalogo catalogos;
	
	@Inject
	ConceptoNominaService conceptoNominaService;
	
	//******************************************
	private List<TipoMovimientoNominaDTO> listaMovimientos = new ArrayList<>();
	private List<SelectItem> itemsTiposMov = new ArrayList<>();
	
	private List<CatalogoDTO> tiposContratacion = new ArrayList<>();
	private Integer tipoContratacionSeleccionada;
	
	private Integer modoAplicacion;
	private Integer periodoAplicacion=0;
	private String descripcion;
	
	
	private List<CatalogoDTO> opciones = new ArrayList<>();
	private List<CatalogoDTO> opcionesSeleccionadas = new ArrayList<>();
	
	private List<CatalogoDTO> opcionesSeleccionadasEdicion = new ArrayList<>();
	
	private Integer puestoseleccionado;
	
	private TipoMovimientoNominaDTO tipoMovimientoSeleccionado = new TipoMovimientoNominaDTO();
	private Integer timovSeleccionado;
	
	private Boolean mostrarPanelbusqueda=true;
	private Boolean mostrarPanelConf = false;
	
	private Boolean mostrarTipoContratacion=false;
	private Boolean mostrarPuesto = false;
	
	private Boolean mostrarPanelEdicion = false;
	
	private List<ProgramarMovimientoDTO> listaMovimientoProgramados = new ArrayList<>();
	private ProgramarMovimientoDTO movimientoProgramadoSeleccionado = new ProgramarMovimientoDTO();
	private ProgramarMovimientoDTO nuevoMovimientoProgramado = new ProgramarMovimientoDTO();
		
	@PostConstruct
	public void inicio(){
		listaMovimientos = new ArrayList<>();
		listaMovimientos = tipoMovimientosNominaEJB.obtenerListaMovimientos();
		itemsTiposMov.clear();
		for(TipoMovimientoNominaDTO dto : listaMovimientos){
			itemsTiposMov.add(new SelectItem(dto.getIdTimpoMovimiento(),dto.getClave()+" - "+ dto.getDescripcion()));
		}
		
		tiposContratacion= catalogos.consultarTiposContratacion();
	}
	
	public void renderizarCampos(){
		if(modoAplicacion.compareTo(1)==0){
			opciones.clear();
			opciones= catalogos.consultarTiposContratacion();
		}
		if(modoAplicacion.compareTo(2)==0){
			opciones.clear();
			opciones= catalogos.listaPuestos();		
		}
	}
	
	public void actualizarpaneledicion(){
		mostrarPanelEdicion = true;
	}
	
	public void renderizarCamposEdicion(){
		opcionesSeleccionadasEdicion.clear();
		if(movimientoProgramadoSeleccionado.getTipoAplicacion().compareTo(1)==0){
			opciones.clear();
			opciones= catalogos.consultarTiposContratacion();
			
		}
		if(movimientoProgramadoSeleccionado.getTipoAplicacion().compareTo(2)==0){
			opciones.clear();
			opciones= catalogos.listaPuestos();		
		}
	}
	
	
	public void cargarCatalogosEdicion(){
		
		modoAplicacion = movimientoProgramadoSeleccionado.getTipoAplicacion();
		renderizarCampos();
		List<CatalogoDTO> listaSeleccionados = new ArrayList<>();
		for(DetalleProgramacionMovimientoDTO detalle: movimientoProgramadoSeleccionado.getListaDetalles()){
			CatalogoDTO catalogo = new CatalogoDTO();
			if(movimientoProgramadoSeleccionado.getTipoAplicacion().compareTo(1)==0){
			catalogo.setId(detalle.getIdTipoContratacion());
			catalogo.setNombre(detalle.getDescripcionTipoContratacion());
			}
			if(movimientoProgramadoSeleccionado.getTipoAplicacion().compareTo(2)==0){
				catalogo.setId(detalle.getIdPuesto());
				catalogo.setNombre(detalle.getDescripcionPuesto());
			}
			
			listaSeleccionados.add(catalogo);
			
		}
		opcionesSeleccionadasEdicion=listaSeleccionados;
	}
	
	
	public void buscarConfiguracion(){
		listaMovimientoProgramados = programacionMovimientosEJB.obtenerMovimientosProgramados(tipoMovimientoSeleccionado.getIdTimpoMovimiento());
		mostrarPanelbusqueda=false;
		mostrarPanelConf=true;
		
	}
	
	public void eliminar(){
		programacionMovimientosEJB.eliminar(movimientoProgramadoSeleccionado);
		listaMovimientoProgramados.clear();
		limpiarCampos();
		listaMovimientoProgramados = programacionMovimientosEJB.obtenerMovimientosProgramados(tipoMovimientoSeleccionado.getIdTimpoMovimiento());
		
		JSFUtils.infoMessage("Atencion:", "El registro de movimiento se elimino correctamente");
	}
	
	public void guardarNuevaConfiguracion(){
		try{
			ProgramarMovimientoDTO newProgramacionMovimiento = new ProgramarMovimientoDTO();
			newProgramacionMovimiento.setDescripcion(descripcion);
			newProgramacionMovimiento.setIdTipoMovimiento(tipoMovimientoSeleccionado.getIdTimpoMovimiento());
			newProgramacionMovimiento.setMovimiento(tipoMovimientoSeleccionado.getClave()+" "+tipoMovimientoSeleccionado.getDescripcion());
			newProgramacionMovimiento.setPeriodoAplicacion(periodoAplicacion);
			newProgramacionMovimiento.setTipoAplicacion(modoAplicacion);
			
			List<DetalleProgramacionMovimientoDTO> detallesProgramacion = new ArrayList<>();
			for(CatalogoDTO catDto : opcionesSeleccionadas){
				DetalleProgramacionMovimientoDTO detallenew = new DetalleProgramacionMovimientoDTO();
				if(modoAplicacion.compareTo(2)==0){
				detallenew.setIdPuesto(catDto.getId());
				}
				if(modoAplicacion.compareTo(1)==0){
				detallenew.setIdTipoContratacion(catDto.getId());
				}
				detallenew.setImporte(new BigDecimal(0));
				
				detallesProgramacion.add(detallenew);
			}
			
			newProgramacionMovimiento.setListaDetalles(detallesProgramacion);
			
			programacionMovimientosEJB.crear(newProgramacionMovimiento);
			listaMovimientoProgramados.clear();
			limpiarCampos();
			listaMovimientoProgramados = programacionMovimientosEJB.obtenerMovimientosProgramados(tipoMovimientoSeleccionado.getIdTimpoMovimiento());
			JSFUtils.infoMessage("", "Se creo la configuracion correctamente.");
		}catch(PersistenceException e){
			JSFUtils.errorMessage("", "Ocurrio un error al intentar guardar la configuracion.");
		}
	}
	
	public void actualizarNuevaConfiguracion(){
		try{
						modoAplicacion = movimientoProgramadoSeleccionado.getTipoAplicacion();
			List<DetalleProgramacionMovimientoDTO> detallesProgramacionEdicion = new ArrayList<>();
			for(CatalogoDTO catDto : opcionesSeleccionadasEdicion){
				DetalleProgramacionMovimientoDTO detallenew = new DetalleProgramacionMovimientoDTO();
				if(modoAplicacion.compareTo(2)==0){
				detallenew.setIdPuesto(catDto.getId());
				}
				if(modoAplicacion.compareTo(1)==0){
				detallenew.setIdTipoContratacion(catDto.getId());
				}
				detallenew.setImporte(new BigDecimal(0));
				
				detallesProgramacionEdicion.add(detallenew);
			}
			movimientoProgramadoSeleccionado.getListaDetalles().clear();
			movimientoProgramadoSeleccionado.setListaDetalles(detallesProgramacionEdicion);
			
			programacionMovimientosEJB.modificar(movimientoProgramadoSeleccionado);
			listaMovimientoProgramados.clear();
			limpiarCampos();
			listaMovimientoProgramados = programacionMovimientosEJB.obtenerMovimientosProgramados(tipoMovimientoSeleccionado.getIdTimpoMovimiento());
			JSFUtils.infoMessage("", "Se edito la configuracion correctamente.");
		}catch(PersistenceException e){
			JSFUtils.errorMessage("", "Ocurrio un error al intentar guardar la configuracion.");
		}
	}
	
	public void limpiarCampos(){
		 modoAplicacion=0;
		 periodoAplicacion=0;
		 descripcion="";
		 opciones.clear();
	}
	
	public void regresar(){
	mostrarPanelbusqueda = true;
	mostrarPanelConf = false;
	}

	public List<TipoMovimientoNominaDTO> getListaMovimientos() {
		return listaMovimientos;
	}

	public void setListaMovimientos(List<TipoMovimientoNominaDTO> listaMovimientos) {
		this.listaMovimientos = listaMovimientos;
	}

	
	public TipoMovimientoNominaDTO getTipoMovimientoSeleccionado() {
		return tipoMovimientoSeleccionado;
	}

	public void setTipoMovimientoSeleccionado(TipoMovimientoNominaDTO tipoMovimientoSeleccionado) {
		this.tipoMovimientoSeleccionado = tipoMovimientoSeleccionado;
	}

	public List<SelectItem> getItemsTiposMov() {
		return itemsTiposMov;
	}

	public void setItemsTiposMov(List<SelectItem> itemsTiposMov) {
		this.itemsTiposMov = itemsTiposMov;
	}


	public Integer getTimovSeleccionado() {
		return timovSeleccionado;
	}


	public void setTimovSeleccionado(Integer timovSeleccionado) {
		this.timovSeleccionado = timovSeleccionado;
	}


	public Boolean getMostrarPanelbusqueda() {
		return mostrarPanelbusqueda;
	}


	public void setMostrarPanelbusqueda(Boolean mostrarPanelbusqueda) {
		this.mostrarPanelbusqueda = mostrarPanelbusqueda;
	}


	public Boolean getMostrarPanelConf() {
		return mostrarPanelConf;
	}


	public void setMostrarPanelConf(Boolean mostrarPanelConf) {
		this.mostrarPanelConf = mostrarPanelConf;
	}

	public List<CatalogoDTO> getTiposContratacion() {
		return tiposContratacion;
	}


	public void setTiposContratacion(List<CatalogoDTO> tiposContratacion) {
		this.tiposContratacion = tiposContratacion;
	}


	public Integer getTipoContratacionSeleccionada() {
		return tipoContratacionSeleccionada;
	}


	public void setTipoContratacionSeleccionada(Integer tipoContratacionSeleccionada) {
		this.tipoContratacionSeleccionada = tipoContratacionSeleccionada;
	}

	public Integer getPuestoseleccionado() {
		return puestoseleccionado;
	}


	public void setPuestoseleccionado(Integer puestoseleccionado) {
		this.puestoseleccionado = puestoseleccionado;
	}

	public Boolean getMostrarTipoContratacion() {
		return mostrarTipoContratacion;
	}

	public void setMostrarTipoContratacion(Boolean mostrarTipoContratacion) {
		this.mostrarTipoContratacion = mostrarTipoContratacion;
	}

	public Boolean getMostrarPuesto() {
		return mostrarPuesto;
	}

	public void setMostrarPuesto(Boolean mostrarPuesto) {
		this.mostrarPuesto = mostrarPuesto;
	}

	public Integer getModoAplicacion() {
		return modoAplicacion;
	}

	public void setModoAplicacion(Integer modoAplicacion) {
		this.modoAplicacion = modoAplicacion;
	}

	public Integer getPeriodoAplicacion() {
		return periodoAplicacion;
	}

	public void setPeriodoAplicacion(Integer periodoAplicacion) {
		this.periodoAplicacion = periodoAplicacion;
	}

	public List<CatalogoDTO> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<CatalogoDTO> opciones) {
		this.opciones = opciones;
	}

	public List<CatalogoDTO> getOpcionesSeleccionadas() {
		return opcionesSeleccionadas;
	}

	public void setOpcionesSeleccionadas(List<CatalogoDTO> opcionesSeleccionadas) {
		this.opcionesSeleccionadas = opcionesSeleccionadas;
	}

	public List<ProgramarMovimientoDTO> getListaMovimientoProgramados() {
		return listaMovimientoProgramados;
	}

	public void setListaMovimientoProgramados(List<ProgramarMovimientoDTO> listaMovimientoProgramados) {
		this.listaMovimientoProgramados = listaMovimientoProgramados;
	}

	public ProgramarMovimientoDTO getNuevoMovimientoProgramado() {
		return nuevoMovimientoProgramado;
	}

	public void setNuevoMovimientoProgramado(ProgramarMovimientoDTO nuevoMovimientoProgramado) {
		this.nuevoMovimientoProgramado = nuevoMovimientoProgramado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ProgramarMovimientoDTO getMovimientoProgramadoSeleccionado() {
		return movimientoProgramadoSeleccionado;
	}

	public void setMovimientoProgramadoSeleccionado(ProgramarMovimientoDTO movimientoProgramadoSeleccionado) {
		this.movimientoProgramadoSeleccionado = movimientoProgramadoSeleccionado;
	}

	public List<CatalogoDTO> getOpcionesSeleccionadasEdicion() {
		return opcionesSeleccionadasEdicion;
	}

	public void setOpcionesSeleccionadasEdicion(List<CatalogoDTO> opcionesSeleccionadasEdicion) {
		this.opcionesSeleccionadasEdicion = opcionesSeleccionadasEdicion;
	}

	public Boolean getMostrarPanelEdicion() {
		return mostrarPanelEdicion;
	}

	public void setMostrarPanelEdicion(Boolean mostrarPanelEdicion) {
		this.mostrarPanelEdicion = mostrarPanelEdicion;
	}


	
	
}
