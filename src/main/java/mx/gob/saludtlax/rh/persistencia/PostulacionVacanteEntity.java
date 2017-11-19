/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 17/08/2016 16:48:20
 */
@Entity
@Table(name = "postulados_vacantes")
public class PostulacionVacanteEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -356418113492435448L;

    @Id
    @Column(name = "id_postulado_vacante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPostuladoVacante;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_postulacion")
    private Date fechaPostulacion;

    @Column(name = "hora_postulacion")
    private Time horaPostulacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inventario_vacante")
    private InventarioVacanteEntity inventarioVacante;

    @Column(name = "disponible")
    private String disponible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_postulacion")
    private TipoPostulacionEntity tipoPostulacion;

    /**
     * @return the idPostuladoVacante
     */
    public Integer getIdPostuladoVacante() {
        return idPostuladoVacante;
    }

    public TipoPostulacionEntity getTipoPostulacion() {
        return tipoPostulacion;
    }

    public void setTipoPostulacion(TipoPostulacionEntity tipoPostulacion) {
        this.tipoPostulacion = tipoPostulacion;
    }

    /**
     * @param idPostuladoVacante
     *            the idPostuladoVacante to set
     */
    public void setIdPostuladoVacante(Integer idPostuladoVacante) {
        this.idPostuladoVacante = idPostuladoVacante;
    }

    /**
     * @return the fechaPostulacion
     */
    public Date getFechaPostulacion() {
        return fechaPostulacion;
    }

    /**
     * @param fechaPostulacion
     *            the fechaPostulacion to set
     */
    public void setFechaPostulacion(Date fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }

    /**
     * @return the horaPostulacion
     */
    public Time getHoraPostulacion() {
        return horaPostulacion;
    }

    /**
     * @param horaPostulacion
     *            the horaPostulacion to set
     */
    public void setHoraPostulacion(Time horaPostulacion) {
        this.horaPostulacion = horaPostulacion;
    }

    /**
     * @return the usuario
     */
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    /**
     * @param usuario
     *            the usuario to set
     */
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the inventarioVacante
     */
    public InventarioVacanteEntity getInventarioVacante() {
        return inventarioVacante;
    }

    /**
     * @param inventarioVacante
     *            the inventarioVacante to set
     */
    public void setInventarioVacante(
            InventarioVacanteEntity inventarioVacante) {
        this.inventarioVacante = inventarioVacante;
    }

    /**
     * @return the disponible
     */
    public String getDisponible() {
        return disponible;
    }

    /**
     * @param disponible
     *            the disponible to set
     */
    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

}
