package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_configuracion_modulo_accion")
public class DetalleConfiguracionModuloAccionEntity implements Serializable {

    private static final long serialVersionUID = -7997732885184384801L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_configuracion_modulo_accion")
    private Integer idDetalleConfiguracionModuloAccion;

    @Column(name = "id_configuracion_modulo_accion")
    private Integer idConfiguracionModuloAccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_accion")
    private AccionesEntity accion;

    public Integer getIdDetalleConfiguracionModuloAccion() {
        return idDetalleConfiguracionModuloAccion;
    }

    public void setId_detalle_configuracion_modulo_accion(Integer idDetalleConfiguracionModuloAccion) {
        this.idDetalleConfiguracionModuloAccion = idDetalleConfiguracionModuloAccion;
    }

    public Integer getIdConfiguracionModuloAccion() {
        return idConfiguracionModuloAccion;
    }

    public void setIdConfiguracionModuloAccion(Integer idConfiguracionModuloAccion) {
        this.idConfiguracionModuloAccion = idConfiguracionModuloAccion;
    }

    public AccionesEntity getAccion() {
        return accion;
    }

    public void setId_accion(AccionesEntity accion) {
        this.accion = accion;
    }

}
