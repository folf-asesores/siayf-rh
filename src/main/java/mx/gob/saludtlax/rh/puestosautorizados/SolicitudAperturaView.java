/**
 * 
 */
package mx.gob.saludtlax.rh.puestosautorizados;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.configuracion.tabulador.InfoSueldoDTO;
import mx.gob.saludtlax.rh.empleados.datolaboral.SolicitudNuevoPuestoDTO;
import mx.gob.saludtlax.rh.empleados.interinatos.DisponiblesInterinatoDTO;

/**
 * @author Leila Schiaffini Ehuan
 * @since 08/08/2016 17:02:00
 * 
 */
public class SolicitudAperturaView {

	private InfoSueldoDTO salario = new InfoSueldoDTO();
	private SolicitudNuevoPuestoDTO solicitud = new SolicitudNuevoPuestoDTO();

	private FiltroVacanteDTO filtro = new FiltroVacanteDTO();
	private List<DisponiblesInterinatoDTO> puestosDisponibles;
	private Integer idUsuarioLogeado;

	private boolean mostrarSalarioFederal;
	private boolean mostrarSalarioEstatal;
	private boolean mostrarNombramientos;
	private boolean mostrarContratoFederal;
	private boolean mostrarInterinatos;
	private boolean mostrarSalarioInterino;
	private boolean bloquearFinanciamientos;
	private boolean bloquearFinanciamientoInterinato;
	private boolean bloquearCuenta;

	private List<SelectItem> listaProyectos = new ArrayList<SelectItem>();
	private List<SelectItem> listaDependencias = new ArrayList<SelectItem>();
	private List<SelectItem> listaUnidadesResponsables = new ArrayList<SelectItem>();
	private List<SelectItem> listaPuestos = new ArrayList<SelectItem>();
	private List<SelectItem> listaFuentesFinanciamiento = new ArrayList<SelectItem>();
	private List<SelectItem> listaSubfuentesFinanciamiento = new ArrayList<SelectItem>();
	private List<SelectItem> listaTiposRecursos = new ArrayList<SelectItem>();
	private List<SelectItem> listaContratacionEventual = new ArrayList<SelectItem>();
	private List<SelectItem> listaContratacionLaboral = new ArrayList<SelectItem>();
	private List<SelectItem> listaTiposNombramiento = new ArrayList<SelectItem>();
	private List<SelectItem> listaProgramas = new ArrayList<SelectItem>();
	private List<SelectItem> listaDetallesPrograma = new ArrayList<SelectItem>();

	public boolean isBloquearCuenta() {
		return bloquearCuenta;
	}

	public void setBloquearCuenta(boolean bloquearCuenta) {
		this.bloquearCuenta = bloquearCuenta;
	}

	public boolean isBloquearFinanciamientoInterinato() {
		return bloquearFinanciamientoInterinato;
	}

	public void setBloquearFinanciamientoInterinato(
			boolean bloquearFinanciamientoInterinato) {
		this.bloquearFinanciamientoInterinato = bloquearFinanciamientoInterinato;
	}

	public boolean isBloquearFinanciamientos() {
		return bloquearFinanciamientos;
	}

	public void setBloquearFinanciamientos(boolean bloquearFinanciamientos) {
		this.bloquearFinanciamientos = bloquearFinanciamientos;
	}

	public List<SelectItem> getListaContratacionEventual() {
		return listaContratacionEventual;
	}

	public void setListaContratacionEventual(
			List<SelectItem> listaContratacionEventual) {
		this.listaContratacionEventual = listaContratacionEventual;
	}

	public List<SelectItem> getListaContratacionLaboral() {
		return listaContratacionLaboral;
	}

	public void setListaContratacionLaboral(
			List<SelectItem> listaContratacionLaboral) {
		this.listaContratacionLaboral = listaContratacionLaboral;
	}

	public boolean isMostrarSalarioInterino() {
		return mostrarSalarioInterino;
	}

	public void setMostrarSalarioInterino(boolean mostrarSalarioInterino) {
		this.mostrarSalarioInterino = mostrarSalarioInterino;
	}

	public boolean isMostrarInterinatos() {
		return mostrarInterinatos;
	}

	public void setMostrarInterinatos(boolean mostrarInterinatos) {
		this.mostrarInterinatos = mostrarInterinatos;
	}

	public List<DisponiblesInterinatoDTO> getPuestosDisponibles() {
		return puestosDisponibles;
	}

	public void setPuestosDisponibles(
			List<DisponiblesInterinatoDTO> puestosDisponibles) {
		this.puestosDisponibles = puestosDisponibles;
	}

	public boolean isMostrarSalarioEstatal() {
		return mostrarSalarioEstatal;
	}

	public void setMostrarSalarioEstatal(boolean mostrarSalarioEstatal) {
		this.mostrarSalarioEstatal = mostrarSalarioEstatal;
	}

	public List<SelectItem> getListaDetallesPrograma() {
		return listaDetallesPrograma;
	}

	public void setListaDetallesPrograma(List<SelectItem> listaDetallesPrograma) {
		this.listaDetallesPrograma = listaDetallesPrograma;
	}

	public List<SelectItem> getListaProgramas() {
		return listaProgramas;
	}

	public void setListaProgramas(List<SelectItem> listaProgramas) {
		this.listaProgramas = listaProgramas;
	}

	public boolean isMostrarContratoFederal() {
		return mostrarContratoFederal;
	}

	public void setMostrarContratoFederal(boolean mostrarContratoFederal) {
		this.mostrarContratoFederal = mostrarContratoFederal;
	}

	public FiltroVacanteDTO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroVacanteDTO filtro) {
		this.filtro = filtro;
	}

	public Integer getIdUsuarioLogeado() {
		return idUsuarioLogeado;
	}

	public void setIdUsuarioLogeado(Integer idUsuarioLogeado) {
		this.idUsuarioLogeado = idUsuarioLogeado;
	}

	public boolean isMostrarNombramientos() {
		return mostrarNombramientos;
	}

	public void setMostrarNombramientos(boolean mostrarNombramientos) {
		this.mostrarNombramientos = mostrarNombramientos;
	}

	public boolean isMostrarSalarioFederal() {
		return mostrarSalarioFederal;
	}

	public void setMostrarSalarioFederal(boolean mostrarSalarioFederal) {
		this.mostrarSalarioFederal = mostrarSalarioFederal;
	}

	public List<SelectItem> getListaTiposNombramiento() {
		return listaTiposNombramiento;
	}

	public void setListaTiposNombramiento(
			List<SelectItem> listaTiposNombramiento) {
		this.listaTiposNombramiento = listaTiposNombramiento;
	}

	public SolicitudNuevoPuestoDTO getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudNuevoPuestoDTO solicitud) {
		this.solicitud = solicitud;
	}

	public InfoSueldoDTO getSalario() {
		return salario;
	}

	public void setSalario(InfoSueldoDTO salario) {
		this.salario = salario;
	}

	public List<SelectItem> getListaProyectos() {
		return listaProyectos;
	}

	public void setListaProyectos(List<SelectItem> listaProyectos) {
		this.listaProyectos = listaProyectos;
	}

	public List<SelectItem> getListaDependencias() {
		return listaDependencias;
	}

	public void setListaDependencias(List<SelectItem> listaDependencias) {
		this.listaDependencias = listaDependencias;
	}

	public List<SelectItem> getListaUnidadesResponsables() {
		return listaUnidadesResponsables;
	}

	public void setListaUnidadesResponsables(
			List<SelectItem> listaUnidadesResponsables) {
		this.listaUnidadesResponsables = listaUnidadesResponsables;
	}

	public List<SelectItem> getListaPuestos() {
		return listaPuestos;
	}

	public void setListaPuestos(List<SelectItem> listaPuestos) {
		this.listaPuestos = listaPuestos;
	}

	public List<SelectItem> getListaFuentesFinanciamiento() {
		return listaFuentesFinanciamiento;
	}

	public void setListaFuentesFinanciamiento(
			List<SelectItem> listaFuentesFinanciamiento) {
		this.listaFuentesFinanciamiento = listaFuentesFinanciamiento;
	}

	public List<SelectItem> getListaSubfuentesFinanciamiento() {
		return listaSubfuentesFinanciamiento;
	}

	public void setListaSubfuentesFinanciamiento(
			List<SelectItem> listaSubfuentesFinanciamiento) {
		this.listaSubfuentesFinanciamiento = listaSubfuentesFinanciamiento;
	}

	public List<SelectItem> getListaTiposRecursos() {
		return listaTiposRecursos;
	}

	public void setListaTiposRecursos(List<SelectItem> listaTiposRecursos) {
		this.listaTiposRecursos = listaTiposRecursos;
	}

}
