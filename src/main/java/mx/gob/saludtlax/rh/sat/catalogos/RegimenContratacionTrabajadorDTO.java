/*
 *  RegimenContratacionTrabajadorDTO.java
 *  Creado el May 25, 2016 2:12:29 PM
 * 
 */
package mx.gob.saludtlax.rh.sat.catalogos;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class RegimenContratacionTrabajadorDTO {
    
    private int clave;
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
