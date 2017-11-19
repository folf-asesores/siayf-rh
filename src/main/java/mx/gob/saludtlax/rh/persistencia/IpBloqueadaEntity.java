
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ips_bloqueadas")
public class IpBloqueadaEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3238987064327584509L;

    @Id
    @Column(name = "id_ip")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIp;

    @Column(name = "ip")
    private String direccionIp;

    @Column(name = "fecha_expira")
    @Temporal(TemporalType.DATE)
    private Date fechaExpira;

    @Column(name = "hora_expira")
    @Temporal(TemporalType.TIME)
    private Date horaExpira;

    public Integer getIdIp() {
        return idIp;
    }

    public void setIdIp(Integer idIp) {
        this.idIp = idIp;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
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
}
