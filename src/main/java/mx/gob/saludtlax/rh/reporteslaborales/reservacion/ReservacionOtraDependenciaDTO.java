package mx.gob.saludtlax.rh.reporteslaborales.reservacion;

import java.io.Serializable;

public class ReservacionOtraDependenciaDTO extends ReservacionDTO implements Serializable {

    private static final long serialVersionUID = 8176691552423716540L;

    private String comunicado;
    private String adscripcion;

    public String getComunicado() {
        return comunicado;
    }

    public void setComunicado(String comunicado) {
        this.comunicado = comunicado;
    }

    public String getAdscripcion() {
        return adscripcion;
    }

    public void setAdscripcion(String adscripcion) {
        this.adscripcion = adscripcion;
    }

}
