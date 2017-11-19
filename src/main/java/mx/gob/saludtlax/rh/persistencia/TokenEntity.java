
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tokens")
public class TokenEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6339105883470486587L;

    @Id
    @Column(name = "id_token")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger idToken;

    @Column(name = "token")
    private String token;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "hora_creacion")
    @Temporal(TemporalType.TIME)
    private Date horaCreacion;

    @Column(name = "fecha_expira")
    @Temporal(TemporalType.DATE)
    private Date fechaExpira;

    @Column(name = "hora_expira")
    @Temporal(TemporalType.TIME)
    private Date horaExpira;

    @Column(name = "activo")
    private Boolean activo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    public BigInteger getIdToken() {
        return idToken;
    }

    public void setIdToken(BigInteger idToken) {
        this.idToken = idToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(Date horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public Date getFechaExpira() {
        return fechaExpira;
    }

    public void setFechaExpira(Date fechaExpira) {
        this.fechaExpira = fechaExpira;
    }

    public Date getHoraExpira() {
        return horaExpira;
    }

    public void setHoraExpira(Date horaExpira) {
        this.horaExpira = horaExpira;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity idUsuario) {
        usuario = idUsuario;
    }

}
