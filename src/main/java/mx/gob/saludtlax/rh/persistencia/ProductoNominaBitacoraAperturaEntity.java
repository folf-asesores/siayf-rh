/*
 * ProductoNominaBitacoraAperturaEntity.java
 * Creado el 04/Jan/2017 8:46:33 AM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "productos_nomina_bitacoras_aperturas")
public class ProductoNominaBitacoraAperturaEntity implements Serializable {

    private static final long serialVersionUID = 4000661675694671232L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bitacora")
    private Integer idBitacora;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Basic(optional = false)
    @NotNull
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioEntity usuario;

    public ProductoNominaBitacoraAperturaEntity() {
        this(0);
    }

    public ProductoNominaBitacoraAperturaEntity(Integer idBitacora) {
        this(0, Calendar.getInstance().getTime(),
                Calendar.getInstance().getTime());
    }

    public ProductoNominaBitacoraAperturaEntity(Integer idBitacora, Date fecha,
            Date hora) {
        this.idBitacora = idBitacora;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "ProductosNominaBitacorasAperturasEntity{" + "idBitacora="
                + idBitacora + ", fecha=" + fecha + ", hora=" + hora
                + ", usuario=" + usuario + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(idBitacora);
        hash = 29 * hash + Objects.hashCode(fecha);
        hash = 29 * hash + Objects.hashCode(hora);
        hash = 29 * hash + Objects.hashCode(usuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductoNominaBitacoraAperturaEntity other = (ProductoNominaBitacoraAperturaEntity) obj;
        if (!Objects.equals(idBitacora, other.idBitacora)) {
            return false;
        }
        if (!Objects.equals(fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(hora, other.hora)) {
            return false;
        }
        return Objects.equals(usuario, other.usuario);
    }

}
