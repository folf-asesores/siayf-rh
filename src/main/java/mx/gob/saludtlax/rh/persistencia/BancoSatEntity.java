/**
 * Copyright © 2016
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
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 03/06/2016 14:16:56
 */
@Entity
@Table(name = "bancos_sat")
public class BancoSatEntity implements Serializable {

	private static final long serialVersionUID = -6859458033874030213L;

	@Id
	@Column(name = "id_banco_sat")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBanco;

	@Column(name = "clave")
	private String clave;

	@Column(name = "nombre_corto")
	private String nombreCorto;

	@Column(name = "razon_social")
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