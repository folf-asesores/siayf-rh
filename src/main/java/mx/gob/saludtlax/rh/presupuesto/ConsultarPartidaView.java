package mx.gob.saludtlax.rh.presupuesto;

import java.util.List;

import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;
import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsableDTO;

public class ConsultarPartidaView {
	private String rfc;
	private Integer idUnidadResponsable;
	private Integer idTipoNombramiento;
	private List<UnidadResponsableDTO> listaUnidadResponsable;
	private List<TipoNombramientoDTO> listaTipoNombramiento;
	private List<ConsultarPartidaDTO> listaConsultaPartida;
	
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public Integer getIdUnidadResponsable() {
		return idUnidadResponsable;
	}
	public void setIdUnidadResponsable(Integer idUnidadResponsable) {
		this.idUnidadResponsable = idUnidadResponsable;
	}
	public Integer getIdTipoNombramiento() {
		return idTipoNombramiento;
	}
	public void setIdTipoNombramiento(Integer idTipoNombramiento) {
		this.idTipoNombramiento = idTipoNombramiento;
	}
	public List<ConsultarPartidaDTO> getListaConsultaPartida() {
		return listaConsultaPartida;
	}
	public void setListaConsultaPartida(List<ConsultarPartidaDTO> listaConsultaPartida) {
		this.listaConsultaPartida = listaConsultaPartida;
	}
	public List<UnidadResponsableDTO> getListaUnidadResponsable() {
		return listaUnidadResponsable;
	}
	public void setListaUnidadResponsable(List<UnidadResponsableDTO> listaUnidadResponsable) {
		this.listaUnidadResponsable = listaUnidadResponsable;
	}
	public List<TipoNombramientoDTO> getListaTipoNombramiento() {
		return listaTipoNombramiento;
	}
	public void setListaTipoNombramiento(List<TipoNombramientoDTO> listaTipoNombramiento) {
		this.listaTipoNombramiento = listaTipoNombramiento;
	}
}
