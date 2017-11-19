/*
 * Copyright ® 2016
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
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 24/05/2016 11:30:11
 */
@Entity
@Table(name = "terceros_institucionales")
public class TerceroInstitucionalEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5737058451521420922L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tercero_institucional")
    private Integer idTerceroInstitucional;

    @Column(name = "numero")
    private String numero;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "concepto_deduccion")
    private String conceptoDeduccion;

    @Column(name = "contra_partida_identificadora")
    private String contrapartidaIdentificadora;

    @Column(name = "giro")
    private String giro;

    /**
     * @return the idTerceroInstitucional
     */
    public Integer getIdTerceroInstitucional() {
        return idTerceroInstitucional;
    }

    /**
     * @param idTerceroInstitucional
     *            the idTerceroInstitucional to set
     */
    public void setIdTerceroInstitucional(Integer idTerceroInstitucional) {
        this.idTerceroInstitucional = idTerceroInstitucional;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero
     *            the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * @param nombreEmpresa
     *            the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * @return the giro
     */
    public String getGiro() {
        return giro;
    }

    /**
     * @param giro
     *            the giro to set
     */
    public void setGiro(String giro) {
        this.giro = giro;
    }

    /**
     * @return the conceptoDeduccion
     */
    public String getConceptoDeduccion() {
        return conceptoDeduccion;
    }

    /**
     * @param conceptoDeduccion
     *            the conceptoDeduccion to set
     */
    public void setConceptoDeduccion(String conceptoDeduccion) {
        this.conceptoDeduccion = conceptoDeduccion;
    }

    /**
     * @return the contrapartidaIdentificadora
     */
    public String getContrapartidaIdentificadora() {
        return contrapartidaIdentificadora;
    }

    /**
     * @param contrapartidaIdentificadora
     *            the contrapartidaIdentificadora to set
     */
    public void setContrapartidaIdentificadora(
            String contrapartidaIdentificadora) {
        this.contrapartidaIdentificadora = contrapartidaIdentificadora;
    }

}
