/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 25/07/2016 14:14:59
 */
@Entity
@Table(name = "partidas_temp")
public class PartidaTempEntity implements Serializable {

    private static final long serialVersionUID = -5701220922165388884L;

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
        return "PartidaEntity [id partida=" + idPartida + ", id capitulo="
                + idCapitulo + ", descripcion=" + descripcion + "]";
    }

}
