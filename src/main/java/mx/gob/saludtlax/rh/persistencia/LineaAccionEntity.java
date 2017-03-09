/*
 * 
 * LineaAccionEntity.java
 * Creado el Jul 27, 2016 1:35:08 PM
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
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Entity
@Table(name = "lineas_accion")
public class LineaAccionEntity implements Serializable {

    private static final long serialVersionUID = -8550648382122533094L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_linea_accion")
    private Integer idLineaAccion;
    
    @Column(name = "codigo_linea")
    private int codigoLinea;
    
    @Column(name = "linea_accion")
    private String lineaAccion;

    public LineaAccionEntity() {
    }

    public Integer getIdLineaAccion() {
        return idLineaAccion;
    }

    public void setIdLineaAccion(Integer idLineaAccion) {
        this.idLineaAccion = idLineaAccion;
    }

    public int getCodigoLinea() {
        return codigoLinea;
    }

    public void setCodigoLinea(int codigoLinea) {
        this.codigoLinea = codigoLinea;
    }

    public String getLineaAccion() {
        return lineaAccion;
    }

    public void setLineaAccion(String lineaAccion) {
        this.lineaAccion = lineaAccion;
    }
}
