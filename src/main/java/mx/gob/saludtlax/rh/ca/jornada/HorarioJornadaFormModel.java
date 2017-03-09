package mx.gob.saludtlax.rh.ca.jornada;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import mx.gob.saludtlax.rh.util.JsonDateSerializer;

public class HorarioJornadaFormModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4845700843168666311L;

	private Integer idHorarioJornada;

	private Integer idJornada;

	private Integer idDia;

	private Date horaEntrada;

	private Date horaSalida;

	public Integer getIdHorarioJornada() {
		return idHorarioJornada;
	}

	public void setIdHorarioJornada(Integer idHorarioJornada) {
		this.idHorarioJornada = idHorarioJornada;
	}

	public Integer getIdJornada() {
		return idJornada;
	}

	public void setIdJornada(Integer idJornada) {
		this.idJornada = idJornada;
	}

	public Integer getIdDia() {
		return idDia;
	}

	public void setIdDia(Integer idDia) {
		this.idDia = idDia;
	}

	
	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}

}
