/*
 * BitacoraExcepcionEntity.java
 * Creado el Jun 24, 2016 2:10:02 PM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "bitacoras_excepciones")
public class BitacoraExcepcionEntity implements Serializable {

    private static final long serialVersionUID = 3903033883294920730L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bitacora_excepcion")
    private Integer idBitacoraExcepcion;

    @JoinColumn(name = "id_usuario", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuario;

    @Basic(optional = false)
    @Column(name = "tipo_excepcion", nullable = false)
    private String tipoExcepcion;

    @Basic(optional = false)
    @Column(name = "mensaje", nullable = true)
    private String mensaje;

    @Basic(optional = false)
    @Lob
    @Column(name = "pila_seguimiento", nullable = false)
    private String pilaSeguimiento;

    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Basic(optional = false)
    @Column(name = "hora", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date hora;

    public BitacoraExcepcionEntity() {
    }

    public BitacoraExcepcionEntity(Integer idBitacoraExcepcion) {
        this.idBitacoraExcepcion = idBitacoraExcepcion;
    }

    public BitacoraExcepcionEntity(Integer idBitacoraExcepcion,
            UsuarioEntity usuario, String tipoExcepcion, String mensaje,
            String pilaSeguimiento, Date fecha, Date hora) {
        this.idBitacoraExcepcion = idBitacoraExcepcion;
        this.usuario = usuario;
        this.tipoExcepcion = tipoExcepcion;
        this.mensaje = mensaje;
        this.pilaSeguimiento = pilaSeguimiento;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getIdBitacoraExcepcion() {
        return idBitacoraExcepcion;
    }

    public void setIdBitacoraExcepcion(Integer idBitacoraExcepcion) {
        this.idBitacoraExcepcion = idBitacoraExcepcion;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public String getTipoExcepcion() {
        return tipoExcepcion;
    }

    public void setTipoExcepcion(String tipoExcepcion) {
        this.tipoExcepcion = tipoExcepcion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getPilaSeguimiento() {
        return pilaSeguimiento;
    }

    public void setPilaSeguimiento(String pilaSeguimiento) {
        this.pilaSeguimiento = pilaSeguimiento;
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
}
