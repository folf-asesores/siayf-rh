/*
 * 
 * LineaAccionDTO.java
 * Creado el Jul 27, 2016 1:49:05 PM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.lineaaccion;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class LineaAccionDTO {

    private Integer idLineaAccion;
    private int codigoLinea;
    private String descripcion;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
