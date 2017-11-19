/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 13/12/2016 03:04:35
 */
@Entity
@Table(name = "jornadas_suplencias")
public class TipoJornadaSuplenciaEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4356771416070062809L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_jornada")
    private Integer id;

    @Column(name = "jornada")
    private String jornada;

    @Column(name = "doble")
    private boolean doble;

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public boolean isDoble() {
        return doble;
    }

    public void setDoble(boolean doble) {
        this.doble = doble;
    }

    public Integer getId() {
        return id;
    }

}
