/*
 *  RegimenContratacionTrabajadorEntity.java
 *  Creado el May 25, 2016 2:01:30 PM
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "SATRegimenContratacionTrabajadores")
public class RegimenContratacionTrabajadorEntity implements Serializable {
    
    private static final long serialVersionUID = -2874378758815369416L;
    
    @Id
    @Column(name = "clave")
    private int clave;
    
    @Column(name = "descripcion")
    private String descripcion;

    /**
     * 
     * @return 
     */
    public int getClave() {
        return clave;
    }

    /**
     * 
     * @param clave 
     */
    public void setClave(int clave) {
        this.clave = clave;
    }

    /**
     * 
     * @return 
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * 
     * @param descripcion 
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}