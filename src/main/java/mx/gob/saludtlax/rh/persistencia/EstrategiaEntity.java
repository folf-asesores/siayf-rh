/*
 * 
 * EstrategiaEntity.java
 * Creado el Jul 12, 2016 10:08:41 AM
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "estrategias")
public class EstrategiaEntity implements Serializable {

    private static final long serialVersionUID = -811915842884886894L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estrategia")
    private Integer idEstrategia;
    
    @Basic(optional = false)
    @Column(name = "codigo_estrategia", nullable = false)
    private int codigoEstrategia;
    
    @Basic(optional = false)
    @Column(name = "estrategia", nullable = false)
    private String estrategia;

    public EstrategiaEntity() {
    }

    public Integer getIdEstrategia() {
        return idEstrategia;
    }

    public void setIdEstrategia(Integer idEstrategia) {
        this.idEstrategia = idEstrategia;
    }

    public int getCodigoEstrategia() {
        return codigoEstrategia;
    }

    public void setCodigoEstrategia(int codigoEstrategia) {
        this.codigoEstrategia = codigoEstrategia;
    }

    public String getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(String estrategia) {
        this.estrategia = estrategia;
    }
}
