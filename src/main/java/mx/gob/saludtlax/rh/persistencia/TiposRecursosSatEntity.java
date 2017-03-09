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
@Table(name = "tipos_recursos_sat")
public class TiposRecursosSatEntity implements Serializable {
    private static final long serialVersionUID = 5555008853681449423L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tipo_recurso_sat")
    private Integer idTipoRecurso;
    @Column(name = "clave_sat")
    private String claveSat;
    @Column(name = "tipo_recurso_sat")
    private String tipoRecurso;

    public Integer getIdTipoRecurso() {
        return idTipoRecurso;
    }
    public void setIdTipoRecurso(Integer idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }
    public String getClaveSat() {
        return claveSat;
    }
    public void setClaveSat(String claveSat) {
        this.claveSat = claveSat;
    }
    public String getTipoRecurso() {
        return tipoRecurso;
    }
    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }
}