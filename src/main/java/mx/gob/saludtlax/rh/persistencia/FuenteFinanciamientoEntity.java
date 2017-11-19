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

@Entity
@Table(name = "fuentes_financiamientos")
public class FuenteFinanciamientoEntity implements Serializable {

    private static final long serialVersionUID = -3491218769714297031L;

    @Id
    @Column(name = "id_fuente_financiamiento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFuenteFinanciamiento;

    @Column(name = "id_base_36")
    private String idBase36;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "id_sistema_salud")
    private int idSistemaSalud;

    @Column(name = "id_origen_recurso")
    private int idOrigenRecurso;

    public Integer getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    public int getIdSistemaSalud() {
        return idSistemaSalud;
    }

    public void setIdSistemaSalud(int idSistemaSalud) {
        this.idSistemaSalud = idSistemaSalud;
    }

    public int getIdOrigenRecurso() {
        return idOrigenRecurso;
    }

    public void setIdOrigenRecurso(int idOrigenRecurso) {
        this.idOrigenRecurso = idOrigenRecurso;
    }

    public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    public String getIdBase36() {
        return idBase36;
    }

    public void setIdBase36(String idBase36) {
        this.idBase36 = idBase36;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
