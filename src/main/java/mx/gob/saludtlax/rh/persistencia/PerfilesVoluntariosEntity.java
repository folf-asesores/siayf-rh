/**
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

@Entity
@Table(name = "perfiles_voluntarios")
public class PerfilesVoluntariosEntity implements Serializable {

    private static final long serialVersionUID = -3491218769714297031L;

    @Id
    @Column(name = "id_perfil_voluntario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVoluntario;

    @Column(name = "perfil")
    private String perfil;

//		<<<<<Getters & Setters>>>>>
    public String getPerfil() {
        return perfil;
    }

    public Integer getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdVoluntario(Integer idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

}
