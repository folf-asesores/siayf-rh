/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.banco;

import java.io.Serializable;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 03/06/2016 14:21:14
 */
public class BancoDTO implements Serializable {
	private static final long serialVersionUID = -1223155784810216758L;

    private Integer idBanco;
    private String clave;
    private String nombreCorto;
    private String razonSocial;

    public Integer getIdBanco() {
        return idBanco;
    }
    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getNombreCorto() {
        return nombreCorto;
    }
    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
}