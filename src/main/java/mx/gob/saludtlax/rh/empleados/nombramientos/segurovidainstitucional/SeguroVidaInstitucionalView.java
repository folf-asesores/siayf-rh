/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.nombramientos.segurovidainstitucional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.empleados.administracion.InfoDependienteEconomicoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

/**
 * @author Eduardo Mex
 *
 */
public class SeguroVidaInstitucionalView implements Serializable {

	/**
	 * 
	 */
	

	private List<InfoEmpleadoDTO> listaEmpleados = new ArrayList<InfoEmpleadoDTO>();

	private List<InfoDependienteEconomicoDTO> listaDependientesEconomicos = new ArrayList<InfoDependienteEconomicoDTO>();

	private List<BeneficiariosDTO> beneficiariosDTOsNuevos = new ArrayList<BeneficiariosDTO>();

	private InfoEmpleadoDTO infoEmpleadoDTO = new InfoEmpleadoDTO();

	private SeguroVidaInstitucionalDTO seguroVidaInstitucionalDTONuevo = new SeguroVidaInstitucionalDTO();

	private InfoDependienteEconomicoDTO infoDependienteEconomicoDTOSeleccionado = new InfoDependienteEconomicoDTO();

	private String criterio;

	private Integer totalPorcentaje = 0;

	private Integer porcentajeBeneficiario;

	private boolean formularioAltaSeguro = false;

	private boolean dialogPorcentaje = false;
	private boolean principal = true;
	private boolean ventanaNuevoReporte = false;
	private boolean reporteExitoso = false;
	private boolean generarReporte = false;
	private boolean datosEmpleadoSeleccionado = false;
	private String urlReporte;
	private Integer idSeguroVida = 0;

	

	public List<InfoEmpleadoDTO> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<InfoEmpleadoDTO> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public InfoEmpleadoDTO getInfoEmpleadoDTO() {
		return infoEmpleadoDTO;
	}

	public void setInfoEmpleadoDTO(InfoEmpleadoDTO infoEmpleadoDTO) {
		this.infoEmpleadoDTO = infoEmpleadoDTO;
	}

	public boolean isFormularioAltaSeguro() {
		return formularioAltaSeguro;
	}

	public void setFormularioAltaSeguro(boolean formularioAltaSeguro) {
		this.formularioAltaSeguro = formularioAltaSeguro;
	}

	public boolean isDialogPorcentaje() {
		return dialogPorcentaje;
	}

	public void setDialogPorcentaje(boolean dialogPorcentaje) {
		this.dialogPorcentaje = dialogPorcentaje;
	}

	public SeguroVidaInstitucionalDTO getSeguroVidaInstitucionalDTONuevo() {
		return seguroVidaInstitucionalDTONuevo;
	}

	public void setSeguroVidaInstitucionalDTONuevo(SeguroVidaInstitucionalDTO seguroVidaInstitucionalDTONuevo) {
		this.seguroVidaInstitucionalDTONuevo = seguroVidaInstitucionalDTONuevo;
	}

	public List<BeneficiariosDTO> getBeneficiariosDTOsNuevos() {
		return beneficiariosDTOsNuevos;
	}

	public void setBeneficiariosDTOsNuevos(List<BeneficiariosDTO> beneficiariosDTOsNuevos) {
		this.beneficiariosDTOsNuevos = beneficiariosDTOsNuevos;
	}

	public InfoDependienteEconomicoDTO getInfoDependienteEconomicoDTOSeleccionado() {
		return infoDependienteEconomicoDTOSeleccionado;
	}

	public void setInfoDependienteEconomicoDTOSeleccionado(
			InfoDependienteEconomicoDTO infoDependienteEconomicoDTOSeleccionado) {
		this.infoDependienteEconomicoDTOSeleccionado = infoDependienteEconomicoDTOSeleccionado;
	}

	public List<InfoDependienteEconomicoDTO> getListaDependientesEconomicos() {
		return listaDependientesEconomicos;
	}

	public void setListaDependientesEconomicos(List<InfoDependienteEconomicoDTO> listaDependientesEconomicos) {
		this.listaDependientesEconomicos = listaDependientesEconomicos;
	}

	public Integer getPorcentajeBeneficiario() {
		return porcentajeBeneficiario;
	}

	public void setPorcentajeBeneficiario(Integer porcentajeBeneficiario) {
		this.porcentajeBeneficiario = porcentajeBeneficiario;
	}

	public Integer getTotalPorcentaje() {
		return totalPorcentaje;
	}

	public void setTotalPorcentaje(Integer totalPorcentaje) {
		this.totalPorcentaje = totalPorcentaje;
	}

	public boolean isVentanaNuevoReporte() {
		return ventanaNuevoReporte;
	}

	public void setVentanaNuevoReporte(boolean ventanaNuevoReporte) {
		this.ventanaNuevoReporte = ventanaNuevoReporte;
	}

	public boolean isReporteExitoso() {
		return reporteExitoso;
	}

	public void setReporteExitoso(boolean reporteExitoso) {
		this.reporteExitoso = reporteExitoso;
	}

	public String getUrlReporte() {
		return urlReporte;
	}

	public void setUrlReporte(String urlReporte) {
		this.urlReporte = urlReporte;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public boolean isGenerarReporte() {
		return generarReporte;
	}

	public void setGenerarReporte(boolean generarReporte) {
		this.generarReporte = generarReporte;
	}

	public Integer getIdSeguroVida() {
		return idSeguroVida;
	}

	public void setIdSeguroVida(Integer idSeguroVida) {
		this.idSeguroVida = idSeguroVida;
	}

	public boolean isDatosEmpleadoSeleccionado() {
		return datosEmpleadoSeleccionado;
	}

	public void setDatosEmpleadoSeleccionado(boolean datosEmpleadoSeleccionado) {
		this.datosEmpleadoSeleccionado = datosEmpleadoSeleccionado;
	}

}
