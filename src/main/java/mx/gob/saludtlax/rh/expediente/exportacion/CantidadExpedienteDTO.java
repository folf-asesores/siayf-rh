/*
 * CantidadExpedienteDTO.java
 * Creado el Sep 5, 2016 1:34:07 PM
 * 
 */
package mx.gob.saludtlax.rh.expediente.exportacion;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class CantidadExpedienteDTO {
    
    private Integer idExpediente;
    private Long cantidadAdjuntos;

    public CantidadExpedienteDTO() {
    }

    public CantidadExpedienteDTO(Integer idExpediente, Long cantidadAdjuntos) {
        this.idExpediente = idExpediente;
        this.cantidadAdjuntos = cantidadAdjuntos;
    }

    public Integer getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    public Long getCantidadAdjuntos() {
        return cantidadAdjuntos;
    }

    public void setCantidadAdjuntos(Long cantidadAdjuntos) {
        this.cantidadAdjuntos = cantidadAdjuntos;
    }
}
