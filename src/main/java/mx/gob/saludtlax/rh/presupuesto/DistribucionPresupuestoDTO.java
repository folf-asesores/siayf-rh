package mx.gob.saludtlax.rh.presupuesto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DistribucionPresupuestoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2346097516770608127L;

	private Integer idProyeccionPartidaMensual;
	private Integer idConsultaPartida;
	private Integer idUnidadResponsable;
	private Integer idNombramiento;
	private Integer idPartida;
	private Integer anio;
	private BigDecimal montoTotal;
	private BigDecimal aumentoTotal;

	private String proyecto;
	private String unidadResponsable;
	private String nombramiento;
	private String fuenteFinanciamiento;
	private String subfuenteFinanciamiento;
	private String partida;

	private BigDecimal enero;
	private BigDecimal febrero;
	private BigDecimal marzo;
	private BigDecimal abril;
	private BigDecimal mayo;
	private BigDecimal junio;
	private BigDecimal julio;
	private BigDecimal agosto;
	private BigDecimal septiembre;
	private BigDecimal octubre;
	private BigDecimal noviembre;
	private BigDecimal diciembre;

	private BigDecimal porcentajeEnero;
	private BigDecimal porcentajeFebrero;
	private BigDecimal porcentajeMarzo;
	private BigDecimal porcentajeAbril;
	private BigDecimal porcentajeMayo;
	private BigDecimal porcentajeJunio;
	private BigDecimal porcentajeJulio;
	private BigDecimal porcentajeAgosto;
	private BigDecimal porcentajeSeptiembre;
	private BigDecimal porcentajeOctubre;
	private BigDecimal porcentajeNoviembre;
	private BigDecimal porcentajeDiciembre;

	private BigDecimal proyeccionEnero;
	private BigDecimal proyeccionFebrero;
	private BigDecimal proyeccionMarzo;
	private BigDecimal proyeccionAbril;
	private BigDecimal proyeccionMayo;
	private BigDecimal proyeccionJunio;
	private BigDecimal proyeccionJulio;
	private BigDecimal proyeccionAgosto;
	private BigDecimal proyeccionSeptiembre;
	private BigDecimal proyeccionOctubre;
	private BigDecimal proyeccionNoviembre;
	private BigDecimal proyeccionDiciembre;

	private BigDecimal total;

	public Integer getIdProyeccionPartidaMensual() {
		return idProyeccionPartidaMensual;
	}

	public void setIdProyeccionPartidaMensual(Integer idProyeccionPartidaMensual) {
		this.idProyeccionPartidaMensual = idProyeccionPartidaMensual;
	}

	public Integer getIdConsultaPartida() {
		return idConsultaPartida;
	}

	public void setIdConsultaPartida(Integer idConsultaPartida) {
		this.idConsultaPartida = idConsultaPartida;
	}

	public Integer getIdUnidadResponsable() {
		return idUnidadResponsable;
	}

	public void setIdUnidadResponsable(Integer idUnidadResponsable) {
		this.idUnidadResponsable = idUnidadResponsable;
	}

	public Integer getIdNombramiento() {
		return idNombramiento;
	}

	public void setIdNombramiento(Integer idNombramiento) {
		this.idNombramiento = idNombramiento;
	}

	public Integer getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(Integer idPartida) {
		this.idPartida = idPartida;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public BigDecimal getAumentoTotal() {
		return aumentoTotal;
	}

	public void setAumentoTotal(BigDecimal aumentoTotal) {
		this.aumentoTotal = aumentoTotal;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public String getUnidadResponsable() {
		return unidadResponsable;
	}

	public void setUnidadResponsable(String unidadResponsable) {
		this.unidadResponsable = unidadResponsable;
	}

	public String getNombramiento() {
		return nombramiento;
	}

	public void setNombramiento(String nombramiento) {
		this.nombramiento = nombramiento;
	}

	public String getFuenteFinanciamiento() {
		return fuenteFinanciamiento;
	}

	public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
		this.fuenteFinanciamiento = fuenteFinanciamiento;
	}

	public String getSubfuenteFinanciamiento() {
		return subfuenteFinanciamiento;
	}

	public void setSubfuenteFinanciamiento(String subfuenteFinanciamiento) {
		this.subfuenteFinanciamiento = subfuenteFinanciamiento;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public BigDecimal getEnero() {
		return enero;
	}

	public void setEnero(BigDecimal enero) {
		this.enero = enero;
	}

	public BigDecimal getFebrero() {
		return febrero;
	}

	public void setFebrero(BigDecimal febrero) {
		this.febrero = febrero;
	}

	public BigDecimal getMarzo() {
		return marzo;
	}

	public void setMarzo(BigDecimal marzo) {
		this.marzo = marzo;
	}

	public BigDecimal getAbril() {
		return abril;
	}

	public void setAbril(BigDecimal abril) {
		this.abril = abril;
	}

	public BigDecimal getMayo() {
		return mayo;
	}

	public void setMayo(BigDecimal mayo) {
		this.mayo = mayo;
	}

	public BigDecimal getJunio() {
		return junio;
	}

	public void setJunio(BigDecimal junio) {
		this.junio = junio;
	}

	public BigDecimal getJulio() {
		return julio;
	}

	public void setJulio(BigDecimal julio) {
		this.julio = julio;
	}

	public BigDecimal getAgosto() {
		return agosto;
	}

	public void setAgosto(BigDecimal agosto) {
		this.agosto = agosto;
	}

	public BigDecimal getSeptiembre() {
		return septiembre;
	}

	public void setSeptiembre(BigDecimal septiembre) {
		this.septiembre = septiembre;
	}

	public BigDecimal getOctubre() {
		return octubre;
	}

	public void setOctubre(BigDecimal octubre) {
		this.octubre = octubre;
	}

	public BigDecimal getNoviembre() {
		return noviembre;
	}

	public void setNoviembre(BigDecimal noviembre) {
		this.noviembre = noviembre;
	}

	public BigDecimal getDiciembre() {
		return diciembre;
	}

	public void setDiciembre(BigDecimal diciembre) {
		this.diciembre = diciembre;
	}

	public BigDecimal getPorcentajeEnero() {
		return porcentajeEnero;
	}

	public void setPorcentajeEnero(BigDecimal porcentajeEnero) {
		this.porcentajeEnero = porcentajeEnero;
	}

	public BigDecimal getPorcentajeFebrero() {
		return porcentajeFebrero;
	}

	public void setPorcentajeFebrero(BigDecimal porcentajeFebrero) {
		this.porcentajeFebrero = porcentajeFebrero;
	}

	public BigDecimal getPorcentajeMarzo() {
		return porcentajeMarzo;
	}

	public void setPorcentajeMarzo(BigDecimal porcentajeMarzo) {
		this.porcentajeMarzo = porcentajeMarzo;
	}

	public BigDecimal getPorcentajeAbril() {
		return porcentajeAbril;
	}

	public void setPorcentajeAbril(BigDecimal porcentajeAbril) {
		this.porcentajeAbril = porcentajeAbril;
	}

	public BigDecimal getPorcentajeMayo() {
		return porcentajeMayo;
	}

	public void setPorcentajeMayo(BigDecimal porcentajeMayo) {
		this.porcentajeMayo = porcentajeMayo;
	}

	public BigDecimal getPorcentajeJunio() {
		return porcentajeJunio;
	}

	public void setPorcentajeJunio(BigDecimal porcentajeJunio) {
		this.porcentajeJunio = porcentajeJunio;
	}

	public BigDecimal getPorcentajeJulio() {
		return porcentajeJulio;
	}

	public void setPorcentajeJulio(BigDecimal porcentajeJulio) {
		this.porcentajeJulio = porcentajeJulio;
	}

	public BigDecimal getPorcentajeAgosto() {
		return porcentajeAgosto;
	}

	public void setPorcentajeAgosto(BigDecimal porcentajeAgosto) {
		this.porcentajeAgosto = porcentajeAgosto;
	}

	public BigDecimal getPorcentajeSeptiembre() {
		return porcentajeSeptiembre;
	}

	public void setPorcentajeSeptiembre(BigDecimal porcentajeSeptiembre) {
		this.porcentajeSeptiembre = porcentajeSeptiembre;
	}

	public BigDecimal getPorcentajeOctubre() {
		return porcentajeOctubre;
	}

	public void setPorcentajeOctubre(BigDecimal porcentajeOctubre) {
		this.porcentajeOctubre = porcentajeOctubre;
	}

	public BigDecimal getPorcentajeNoviembre() {
		return porcentajeNoviembre;
	}

	public void setPorcentajeNoviembre(BigDecimal porcentajeNoviembre) {
		this.porcentajeNoviembre = porcentajeNoviembre;
	}

	public BigDecimal getPorcentajeDiciembre() {
		return porcentajeDiciembre;
	}

	public void setPorcentajeDiciembre(BigDecimal porcentajeDiciembre) {
		this.porcentajeDiciembre = porcentajeDiciembre;
	}

	public BigDecimal getProyeccionEnero() {
		return proyeccionEnero;
	}

	public void setProyeccionEnero(BigDecimal proyeccionEnero) {
		this.proyeccionEnero = proyeccionEnero;
	}

	public BigDecimal getProyeccionFebrero() {
		return proyeccionFebrero;
	}

	public void setProyeccionFebrero(BigDecimal proyeccionFebrero) {
		this.proyeccionFebrero = proyeccionFebrero;
	}

	public BigDecimal getProyeccionMarzo() {
		return proyeccionMarzo;
	}

	public void setProyeccionMarzo(BigDecimal proyeccionMarzo) {
		this.proyeccionMarzo = proyeccionMarzo;
	}

	public BigDecimal getProyeccionAbril() {
		return proyeccionAbril;
	}

	public void setProyeccionAbril(BigDecimal proyeccionAbril) {
		this.proyeccionAbril = proyeccionAbril;
	}

	public BigDecimal getProyeccionMayo() {
		return proyeccionMayo;
	}

	public void setProyeccionMayo(BigDecimal proyeccionMayo) {
		this.proyeccionMayo = proyeccionMayo;
	}

	public BigDecimal getProyeccionJunio() {
		return proyeccionJunio;
	}

	public void setProyeccionJunio(BigDecimal proyeccionJunio) {
		this.proyeccionJunio = proyeccionJunio;
	}

	public BigDecimal getProyeccionJulio() {
		return proyeccionJulio;
	}

	public void setProyeccionJulio(BigDecimal proyeccionJulio) {
		this.proyeccionJulio = proyeccionJulio;
	}

	public BigDecimal getProyeccionAgosto() {
		return proyeccionAgosto;
	}

	public void setProyeccionAgosto(BigDecimal proyeccionAgosto) {
		this.proyeccionAgosto = proyeccionAgosto;
	}

	public BigDecimal getProyeccionSeptiembre() {
		return proyeccionSeptiembre;
	}

	public void setProyeccionSeptiembre(BigDecimal proyeccionSeptiembre) {
		this.proyeccionSeptiembre = proyeccionSeptiembre;
	}

	public BigDecimal getProyeccionOctubre() {
		return proyeccionOctubre;
	}

	public void setProyeccionOctubre(BigDecimal proyeccionOctubre) {
		this.proyeccionOctubre = proyeccionOctubre;
	}

	public BigDecimal getProyeccionNoviembre() {
		return proyeccionNoviembre;
	}

	public void setProyeccionNoviembre(BigDecimal proyeccionNoviembre) {
		this.proyeccionNoviembre = proyeccionNoviembre;
	}

	public BigDecimal getProyeccionDiciembre() {
		return proyeccionDiciembre;
	}

	public void setProyeccionDiciembre(BigDecimal proyeccionDiciembre) {
		this.proyeccionDiciembre = proyeccionDiciembre;
	}

	/**
	 * @return the total
	 */
	public BigDecimal getTotal() {

		this.total = BigDecimal.ZERO;
		
		this.total = total.add(enero);
		this.total = total.add(febrero);
		this.total = total.add(marzo);
		this.total = total.add(abril);
		this.total = total.add(mayo);
		this.total = total.add(junio);
		this.total = total.add(julio);
		this.total = total.add(agosto);
		this.total = total.add(septiembre);
		this.total = total.add(octubre);
		this.total = total.add(noviembre);
		this.total = total.add(diciembre);

		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}