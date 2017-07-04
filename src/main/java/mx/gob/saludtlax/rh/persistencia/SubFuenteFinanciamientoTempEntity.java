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
 * @since 25/07/2016 14:19:03
 */
@Entity
@Table(name = "subfuentes_financiamientos_temp")
public class SubFuenteFinanciamientoTempEntity implements Serializable {

    private static final long serialVersionUID = 6938825378940666299L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subfuente_financiamiento")
    private Integer idSubfuenteFinanciamiento;

    @Column(name = "id_fuente_financiamiento")
    private Integer idFuenteFinanciamiento;

    @Column(name = "id_fuente_financiamiento_opd")
    private Integer idFuenteFinanciamientoOpd;

    @Column(name = "id_base_36")
    private String idBase36;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "id_subfuente_financiamiento_salud")
    private Integer idSubfuenteFinanciamientoSalud;

    @Column(name="nombramiento")
    private Boolean nombramiento;
    
    @Column(name="estatales")
    private Boolean estatales;
    
    @Column(name="federales")
    private Boolean federales;
    
    @Override
    public String toString() {
        return "SubFuenteFinanciamientoEntity [id subfuente financiamiento=" + idSubfuenteFinanciamiento
                + ", id fuente financiamiento=" + idFuenteFinanciamiento + ", id fuente financiamiento opd="
                + idFuenteFinanciamientoOpd + ", id base 36=" + idBase36 + ", descripcion=" + descripcion + "]";
    }

    public Integer getIdSubfuenteFinanciamientoSalud() {
        return idSubfuenteFinanciamientoSalud;
    }

    public void setIdSubfuenteFinanciamientoSalud(Integer idSubfuenteFinanciamientoSalud) {
        this.idSubfuenteFinanciamientoSalud = idSubfuenteFinanciamientoSalud;
    }

    /**
     * @return the idSubfuenteFinanciamiento
     */
    public Integer getIdSubfuenteFinanciamiento() {
        return idSubfuenteFinanciamiento;
    }

    /**
     * @param idSubfuenteFinanciamiento the idSubfuenteFinanciamiento to set
     */
    public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
    }

    /**
     * @return the idFuenteFinanciamiento
     */
    public Integer getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    /**
     * @param idFuenteFinanciamiento the idFuenteFinanciamiento to set
     */
    public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    /**
     * @return the idFuenteFinanciamientoOpd
     */
    public Integer getIdFuenteFinanciamientoOpd() {
        return idFuenteFinanciamientoOpd;
    }

    /**
     * @param idFuenteFinanciamientoOpd the idFuenteFinanciamientoOpd to set
     */
    public void setIdFuenteFinanciamientoOpd(Integer idFuenteFinanciamientoOpd) {
        this.idFuenteFinanciamientoOpd = idFuenteFinanciamientoOpd;
    }

    /**
     * @return the idBase36
     */
    public String getIdBase36() {
        return idBase36;
    }

    /**
     * @param idBase36 the idBase36 to set
     */
    public void setIdBase36(String idBase36) {
        this.idBase36 = idBase36;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

	

	public Boolean getNombramiento() {
		return nombramiento;
	}

	public void setNombramiento(Boolean nombramiento) {
		this.nombramiento = nombramiento;
	}



	public Boolean getFederales() {
		return federales;
	}

	public void setFederales(Boolean federales) {
		this.federales = federales;
	}

	public Boolean getEstatales() {
		return estatales;
	}

	public void setEstatales(Boolean estatales) {
		this.estatales = estatales;
	}

	
    
    

}
