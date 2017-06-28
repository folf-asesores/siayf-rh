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
 * @since 25/07/2016 14:11:10
 */
@Entity
@Table(name = "dependencias_temp")
public class DependenciaTempEntity implements Serializable {

    private static final long serialVersionUID = 3901902915856729473L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dependencia")
    private Integer idDependencia;

    @Column(name = "id_ramo")
    private int idRamo;

    @Column(name = "id_sector")
    private int idSector;

    @Column(name = "id_ente_publico")
    private int idEntePublico;

    @Column(name = "id_clasificacion_organismo")
    private int idClasificacionOrganismo;

    @Column(name = "id_base_36")
    private String idBase36;

    @Column(name = "Descripcion")
    private String descripcion;

    @Override
    public String toString() {
        return "DependenciaEntity [Id Dependencia =" + idDependencia + ", id base 36=" + idBase36 + ", descripcion="
                + descripcion + "]";
    }

    public int getIdEntePublico() {
        return idEntePublico;
    }

    public void setIdEntePublico(int idEntePublico) {
        this.idEntePublico = idEntePublico;
    }

    public int getIdRamo() {
        return idRamo;
    }

    public void setIdRamo(int idRamo) {
        this.idRamo = idRamo;
    }

    public int getIdSector() {
        return idSector;
    }

    public void setIdSector(int idSector) {
        this.idSector = idSector;
    }

    public int getIdClasificacionOrganismo() {
        return idClasificacionOrganismo;
    }

    public void setIdClasificacionOrganismo(int idClasificacionOrganismo) {
        this.idClasificacionOrganismo = idClasificacionOrganismo;
    }

    /**
     * @return the idDependencia
     */
    public Integer getIdDependencia() {
        return idDependencia;
    }

    /**
     * @param idDependencia the idDependencia to set
     */
    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
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

}
