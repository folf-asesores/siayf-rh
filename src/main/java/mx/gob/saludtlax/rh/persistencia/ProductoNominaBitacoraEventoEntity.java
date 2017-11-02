/*
 * ProductoNominaBitacoraEventoEntity.java
 * Creado el 04/Jan/2017 8:46:34 AM
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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import mx.gob.saludtlax.rh.nomina.productosnomina.AperturaNominaRfcBitacoraCategoria;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "productos_nomina_bitacoras_eventos")
public class ProductoNominaBitacoraEventoEntity implements Serializable {

    private static final long serialVersionUID = 7306403356758120294L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_evento")
    private Integer idEvento;

    @Basic(optional = false)
    @NotNull
    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private AperturaNominaRfcBitacoraCategoria categoria;

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

    @Lob
    @Size(max = 65535)
    @Column(name = "mensaje")
    private String mensaje;

    @JoinColumn(name = "id_bitacora", referencedColumnName = "id_bitacora")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProductoNominaBitacoraAperturaEntity bitacora;

    public ProductoNominaBitacoraEventoEntity() {
        this(0);
    }

    public ProductoNominaBitacoraEventoEntity(Integer idEvento) {
        this(
                0,
                AperturaNominaRfcBitacoraCategoria.INFORMACION,
                Calendar.getInstance().getTime(),
                Calendar.getInstance().getTime()
        );
    }

    public ProductoNominaBitacoraEventoEntity(Integer idEvento, AperturaNominaRfcBitacoraCategoria categoria, Date fecha, Date hora) {
        this.idEvento = idEvento;
        this.categoria = categoria;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public AperturaNominaRfcBitacoraCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(AperturaNominaRfcBitacoraCategoria categoria) {
        this.categoria = categoria;
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ProductoNominaBitacoraAperturaEntity getBitacora() {
        return bitacora;
    }

    public void setBitacora(ProductoNominaBitacoraAperturaEntity bitacora) {
        this.bitacora = bitacora;
    }

    @Override
    public String toString() {
        return "ProductosNominaBitacorasEventosEntity{" 
                + "idEvento=" + idEvento 
                + ", categoria=" + categoria 
                + ", fecha=" + fecha
                + ", hora=" + hora
                + ", mensaje=" + mensaje
                + ", idBitacora=" + bitacora
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(idEvento);
        hash = 79 * hash + Objects.hashCode(categoria);
        hash = 79 * hash + Objects.hashCode(fecha);
        hash = 79 * hash + Objects.hashCode(hora);
        hash = 79 * hash + Objects.hashCode(mensaje);
        hash = 79 * hash + Objects.hashCode(bitacora);
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
        final ProductoNominaBitacoraEventoEntity other = (ProductoNominaBitacoraEventoEntity) obj;
        if (!Objects.equals(categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(mensaje, other.mensaje)) {
            return false;
        }
        if (!Objects.equals(idEvento, other.idEvento)) {
            return false;
        }
        if (!Objects.equals(fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(hora, other.hora)) {
            return false;
        }
        return Objects.equals(bitacora, other.bitacora);
    }
    
}
