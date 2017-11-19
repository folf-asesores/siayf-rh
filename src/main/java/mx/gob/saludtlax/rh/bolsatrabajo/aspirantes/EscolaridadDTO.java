/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.io.Serializable;

/**
 * @author eduardo
 *
 */
public class EscolaridadDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8118425682283274874L;

    private Integer idEscolaridad;

    private String escolaridad;

    private String grupoAcademico;

    /**
     * @return the grupoAcademico
     */
    public String getGrupoAcademico() {
        return grupoAcademico;
    }

    /**
     * @param grupoAcademico
     *            the grupoAcademico to set
     */
    public void setGrupoAcademico(String grupoAcademico) {
        this.grupoAcademico = grupoAcademico;
    }

    /**
     * @return the escolaridad
     */
    public String getEscolaridad() {
        return escolaridad;
    }

    /**
     * @param escolaridad
     *            the escolaridad to set
     */
    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    /**
     * @return the idEscolaridad
     */
    public Integer getIdEscolaridad() {
        return idEscolaridad;
    }

    /**
     * @param idEscolaridad
     *            the idEscolaridad to set
     */
    public void setIdEscolaridad(Integer idEscolaridad) {
        this.idEscolaridad = idEscolaridad;
    }

}
