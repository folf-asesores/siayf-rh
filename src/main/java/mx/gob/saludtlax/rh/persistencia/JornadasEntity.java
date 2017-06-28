package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jornadas")

public class JornadasEntity implements Serializable {

    private static final long serialVersionUID = 3111008823681449423L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdJornada")
    private Integer idJornada;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "TrabajaDiasNoLaborales")
    private Boolean trabajaDiasNoLaborales;

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getTrabajaDiasNoLaborales() {
        return trabajaDiasNoLaborales;
    }

    public void setTrabajaDiasNoLaborales(Boolean trabajaDiasNoLaborales) {
        this.trabajaDiasNoLaborales = trabajaDiasNoLaborales;
    }

}
