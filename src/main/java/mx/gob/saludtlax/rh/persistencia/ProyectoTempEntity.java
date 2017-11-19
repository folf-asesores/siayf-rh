/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 25/07/2016 14:17:23
 */
@Entity
@Table(name = "proyectos_temp")
public class ProyectoTempEntity implements Serializable {

    private static final long serialVersionUID = 3648966099601855479L;

    @Id
    //	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Integer idProyecto;

    @Column(name = "id_dependencia")
    private Integer idDependencia;

    @Column(name = "id_unidad_responsable")
    private Integer idUnidadResponsable;

    @Column(name = "id_base_36")
    private String idBase36;

    @Column(name = "descripcion")
    private String descripcion;

    @Override
    public String toString() {
        return "ProyectoEntity [id proyecto=" + idProyecto + ", id dependencia="
                + idDependencia + ", id unidad responsable="
                + idUnidadResponsable + ", id base 36=" + idBase36
                + ", descripcion=" + descripcion + "]";
    }

    /**
     * @return the idProyecto
     */
    public Integer getIdProyecto() {
        return idProyecto;
    }

    /**
     * @param idProyecto
     *            the idProyecto to set
     */
    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    /**
     * @return the idDependencia
     */
    public Integer getIdDependencia() {
        return idDependencia;
    }

    /**
     * @param idDependencia
     *            the idDependencia to set
     */
    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    /**
     * @return the idUnidadResponsable
     */
    public Integer getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    /**
     * @param idUnidadResponsable
     *            the idUnidadResponsable to set
     */
    public void setIdUnidadResponsable(Integer idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    /**
     * @return the idBase36
     */
    public String getIdBase36() {
        return idBase36;
    }

    /**
     * @param idBase36
     *            the idBase36 to set
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
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
