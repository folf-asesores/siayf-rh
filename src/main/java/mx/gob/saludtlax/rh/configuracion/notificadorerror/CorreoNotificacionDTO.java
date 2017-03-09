/*
 *  CorreoNotificacionDTO.java
 *  Creado el Jun 16, 2016 4:41:03 PM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.notificadorerror;

import java.io.Serializable;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class CorreoNotificacionDTO implements Serializable {

    private static final long serialVersionUID = 3580203487814567897L;

    private Integer idCorreoNotificacion;
    private String alias;
    private String correoElectronico;

    public CorreoNotificacionDTO() {
        this(0, "", "");
    }

    public CorreoNotificacionDTO(Integer idCorreoNotificacion, String alias, String correoElectronico) {
        this.idCorreoNotificacion = idCorreoNotificacion;
        this.alias = alias;
        this.correoElectronico = correoElectronico;
    }
    
    public Integer getIdCorreoNotificacion() {
        return idCorreoNotificacion;
    }

    public void setIdCorreoNotificacion(Integer idCorreoNotificacion) {
        this.idCorreoNotificacion = idCorreoNotificacion;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

}
