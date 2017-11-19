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
 *
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 10/03/2016 11:14:30
 */
@Table(name = "profesiones")
@Entity
public class ProfesionEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7025549545991141860L;

    @Id
    @Column(name = "id_profesion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfesion;

    @Column(name = "profesion")
    private String profesion;

    /**
     * @return the idProfesion
     */
    public Integer getIdProfesion() {
        return idProfesion;
    }

    /**
     * @param idProfesion
     *            the idProfesion to set
     */
    public void setIdProfesion(Integer idProfesion) {
        this.idProfesion = idProfesion;
    }

    /**
     * @return the profesion
     */
    public String getProfesion() {
        return profesion;
    }

    /**
     * @param profesion
     *            the profesion to set
     */
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

}
