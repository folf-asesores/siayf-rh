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
 * @since 04/08/2016 14:19:27
 *
 */
@Entity
@Table(name = "estatus_configuraciones_presupuestales")
public class EstatusConfiguracionesEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6732570304070740030L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estatus")
    private Integer id;

    @Column(name = "estatus")
    private String estatus;

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getId() {
        return id;
    }

}
