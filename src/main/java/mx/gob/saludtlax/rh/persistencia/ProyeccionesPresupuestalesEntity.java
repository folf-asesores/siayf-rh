/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "proyeccion_partida_mensual")
@Entity
public class ProyeccionesPresupuestalesEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7025549545991141860L;
	
	@Id
	@Column(name = "id_proyeccion_partida_mensual")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProyeccionPartidaMensual;
	
	@Column (name = "id_consulta_partida")
	private Integer idConsultaPartida;
	
	@Column (name = "id_unidad_responsable")
	private Integer idUnidadResponsable;
	
	@Column (name = "id_nombramiento")
	private Integer idNombramiento;
	
	@Column (name = "id_partida")
	private Integer idPartida;
	
	@Column (name = "partida")
	private String partida;
		
	@Column (name = "enero")
	private BigDecimal enero;
	
	@Column (name = "febrero")
	private BigDecimal febrero;
	
	@Column (name = "marzo")
	private BigDecimal marzo;
	
	@Column (name = "abril")
	private BigDecimal abril;
	
	@Column (name = "mayo")
	private BigDecimal mayo;
	
	@Column (name = "junio")
	private BigDecimal junio;
	
	@Column (name = "julio")
	private BigDecimal julio;
	
	@Column (name = "agosto")
	private BigDecimal agosto;
	
	@Column (name = "septiembre")
	private BigDecimal septiembre;
	
	@Column (name = "octubre")
	private BigDecimal octubre;
	
	@Column (name = "noviembre")
	private BigDecimal noviembre;
	
	@Column (name = "diciembre")
	private BigDecimal diciembre;
	
	@Column (name = "porcentaje_enero")
	private BigDecimal porcentajeEnero;
	
	@Column (name = "porcentaje_febrero")
	private BigDecimal porcentajeFebrero;
	
	@Column (name = "porcentaje_marzo")
	private BigDecimal porcentajeMarzo;
	
	@Column (name = "porcentaje_abril")
	private BigDecimal porcentajeAbril;
	
	@Column (name = "porcentaje_mayo")
	private BigDecimal porcentajeMayo;
	
	@Column (name = "porcentaje_junio")
	private BigDecimal porcentajeJunio;
	
	@Column (name = "porcentaje_julio")
	private BigDecimal porcentajeJulio;
	
	@Column (name = "porcentaje_agosto")
	private BigDecimal porcentajeAgosto;
	
	@Column (name = "porcentaje_septiembre")
	private BigDecimal porcentajeSeptiembre;
	
	@Column (name = "porcentaje_octubre")
	private BigDecimal porcentajeOctubre;
	
	@Column (name = "porcentaje_noviembre")
	private BigDecimal porcentajeNoviembre;
	
	@Column (name = "porcentaje_diciembre")
	private BigDecimal porcentajeDiciembre;
	
	@Column (name = "proyeccion_enero")
	private BigDecimal proyeccionEnero;
	
	@Column (name = "proyeccion_febrero")
	private BigDecimal proyeccionFebrero;
	
	@Column (name = "proyeccion_marzo")
	private BigDecimal proyeccionMarzo;
	
	@Column (name = "proyeccion_abril")
	private BigDecimal proyeccionAbril;
	
	@Column (name = "proyeccion_mayo")
	private BigDecimal proyeccionMayo;
	
	@Column (name = "proyeccion_junio")
	private BigDecimal proyeccionJunio;
	
	@Column (name = "proyeccion_julio")
	private BigDecimal proyeccionJulio;
	
	@Column (name = "proyeccion_agosto")
	private BigDecimal proyeccionAgosto;
	
	@Column (name = "proyeccion_septiembre")
	private BigDecimal proyeccionSeptiembre;
	
	@Column (name = "proyeccion_octubre")
	private BigDecimal proyeccionOctubre;
	
	@Column (name = "proyeccion_noviembre")
	private BigDecimal proyeccionNoviembre;
	
	@Column (name = "proyeccion_diciembre")
	private BigDecimal proyeccionDiciembre;
	
	@Column (name = "anio")
	private Integer anio;
	
	@Column (name = "total_actual")
	private BigDecimal totalActual;
	
	@Column (name = "total_proyeccion")
	private BigDecimal totalProyeccion;

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

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public BigDecimal getTotalActual() {
		return totalActual;
	}

	public void setTotalActual(BigDecimal totalActual) {
		this.totalActual = totalActual;
	}

	public BigDecimal getTotalProyeccion() {
		return totalProyeccion;
	}

	public void setTotalProyeccion(BigDecimal totalProyeccion) {
		this.totalProyeccion = totalProyeccion;
	}

	
}
