/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 		Eduardo Mex
 * @email		Lic.Eduardo_Mex@hotmail.com
 * @version     1.0
 * @since       25/07/2016 14:14:59
 */
@Entity
@Table(name = "partidas_temp")
public class PartidaTempEntity {
	
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_partida")
	private String idPartida;

	@Column(name = "id_capitulo")
	private Integer idCapitulo;

	@Column(name = "descripcion")
	private String descripcion;

	@Override
	public String toString() {
		return "PartidaEntity [id partida=" + idPartida + ", id capitulo=" + idCapitulo + ", descripcion=" + descripcion
				+ "]";
	}

}
