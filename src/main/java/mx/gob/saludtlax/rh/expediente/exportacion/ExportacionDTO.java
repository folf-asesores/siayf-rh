/*
 * ExportacionDTO.java
 * Creado el Sep 9, 2016 4:21:52 PM
 * 
 */
package mx.gob.saludtlax.rh.expediente.exportacion;

import java.io.Serializable;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ExportacionDTO extends InformacionAdjuntoDTO implements Serializable {

    private static final long serialVersionUID = 2106794813469670965L;

    private byte [] adjunto;

    public byte[] getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(byte[] adjunto) {
        this.adjunto = adjunto;
    }
}
