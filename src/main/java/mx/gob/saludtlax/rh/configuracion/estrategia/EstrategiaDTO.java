/*
 * 
 * EstrategiaDTO.java
 * Creado el Jul 12, 2016 10:25:02 AM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.estrategia;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class EstrategiaDTO {
       
    private Integer idEstrategia;    
    private int codigoEstrategia;    
    private String estrategia;

    public EstrategiaDTO() {
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
