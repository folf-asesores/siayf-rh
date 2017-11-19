/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.profesion;

import java.io.Serializable;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 * @version 10/03/2016 11:03:32
 */
public class ProfesionDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -271448965755413750L;

    private Integer idProfesionAspiranteEmpleado;

    private Integer idProfesion;

    private String profesion;

    public ProfesionDTO() {
        super();
    }

    public ProfesionDTO(Integer idProfesionAspiranteEmpleado,
            Integer idProfesion, String profesion) {

        this.idProfesionAspiranteEmpleado = idProfesionAspiranteEmpleado;
        this.idProfesion = idProfesion;
        this.profesion = profesion;

    }

    public ProfesionDTO(Integer idProfesion, String profesion) {

        this.idProfesion = idProfesion;
        this.profesion = profesion;

    }

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

    /**
     * @return the idProfesionAspiranteEmpleado
     */
    public Integer getIdProfesionAspiranteEmpleado() {
        return idProfesionAspiranteEmpleado;
    }

    /**
     * @param idProfesionAspiranteEmpleado
     *            the idProfesionAspiranteEmpleado to set
     */
    public void setIdProfesionAspiranteEmpleado(
            Integer idProfesionAspiranteEmpleado) {
        this.idProfesionAspiranteEmpleado = idProfesionAspiranteEmpleado;
    }

}
