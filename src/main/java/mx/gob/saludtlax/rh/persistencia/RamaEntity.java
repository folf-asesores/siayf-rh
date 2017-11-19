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
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * @since 27/07/2016 10:57:02
 * @version 1.0
 * 
 */
@Entity
@Table(name = "ramas")
public class RamaEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3167242269188642706L;

    @Id
    @Column(name = "id_rama_puesto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRamaPuesto;

    @Column(name = "nombre_rama_puesto")
    private String nombreRamaPuesto;

    /**
     * @return the idRamaPuesto
     */
    public Integer getIdRamaPuesto() {
        return idRamaPuesto;
    }

    /**
     * @param idRamaPuesto
     *            the idRamaPuesto to set
     */
    public void setIdRamaPuesto(Integer idRamaPuesto) {
        this.idRamaPuesto = idRamaPuesto;
    }

    /**
     * @return the nombreRamaPuesto
     */
    public String getNombreRamaPuesto() {
        return nombreRamaPuesto;
    }

    /**
     * @param nombreRamaPuesto
     *            the nombreRamaPuesto to set
     */
    public void setNombreRamaPuesto(String nombreRamaPuesto) {
        this.nombreRamaPuesto = nombreRamaPuesto;
    }

}
