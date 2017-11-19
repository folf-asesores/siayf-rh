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
 * @since 04/03/2016-17:56:40
 */
@Entity
@Table(name = "escolaridades")
public class EscolaridadEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7480644052515441771L;
    @Id
    @Column(name = "id_escolaridad")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEscolaridad;

    @Column(name = "escolaridad")
    private String escolaridad;

    @Column(name = "grupo_academico")
    private String grupoAcademico;

    @Column(name = "perfil")
    private boolean perfil;

    public boolean isPerfil() {
        return perfil;
    }

    public void setPerfil(boolean perfil) {
        this.perfil = perfil;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getGrupoAcademico() {
        return grupoAcademico;
    }

    public void setGrupoAcademico(String grupoAcademico) {
        this.grupoAcademico = grupoAcademico;
    }

    public Integer getIdEscolaridad() {
        return idEscolaridad;
    }

}
