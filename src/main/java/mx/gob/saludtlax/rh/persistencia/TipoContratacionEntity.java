/*
 *
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
 * @author Leila Schiaffini Ehuan
 * @since 04/08/2016 13:16:24
 *
 */
@Entity
@Table(name = "tipos_contratacion")
public class TipoContratacionEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4744503704330516270L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_contratacion")
    private Integer id;

    @Column(name = "tipo_contratacion")
    private String tipoContratacion;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "area_responsable")
    private Integer areaResponsable;

    @Column(name = "inventario")
    private boolean inventario;

    public boolean isInventario() {
        return inventario;
    }

    public void setInventario(boolean inventario) {
        this.inventario = inventario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAreaResponsable() {
        return areaResponsable;
    }

    public void setAreaResponsable(Integer areaResponsable) {
        this.areaResponsable = areaResponsable;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
