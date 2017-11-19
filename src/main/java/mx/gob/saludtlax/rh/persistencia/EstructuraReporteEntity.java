/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
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

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@Entity
@Table(name = "estructuras_reportes")
public class EstructuraReporteEntity implements Serializable {

    private static final long serialVersionUID = -8829278004818724632L;

    @Id
    @Column(name = "id_estructura_reporte")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstructuraReporte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clasificacion_reporte")
    private ClasificacionReporteEntity clasificacionReporte;

    @Column(name = "texto_uno")
    private String textoUno;

    @Column(name = "fecha_posicion_uno")
    private Date fechaPosicionUno;

    @Column(name = "texto_dos")
    private String textoDos;

    @Column(name = "sustituye")
    private String sustituye;

    @Column(name = "nombre_secretario")
    private String nombreSecretario;

    @Column(name = "horario_trabajo")
    private String horarioTrabajo;

    public Integer getIdEstructuraReporte() {
        return idEstructuraReporte;
    }

    public void setIdEstructuraReporte(Integer idEstructuraReporte) {
        this.idEstructuraReporte = idEstructuraReporte;
    }

    public String getTextoUno() {
        return textoUno;
    }

    public void setTextoUno(String textoUno) {
        this.textoUno = textoUno;
    }

    public String getTextoDos() {
        return textoDos;
    }

    public void setTextoDos(String textoDos) {
        this.textoDos = textoDos;
    }

    public String getSustituye() {
        return sustituye;
    }

    public void setSustituye(String sustituye) {
        this.sustituye = sustituye;
    }

    public String getNombreSecretario() {
        return nombreSecretario;
    }

    public void setNombreSecretario(String nombreSecretario) {
        this.nombreSecretario = nombreSecretario;
    }

    public String getHorarioTrabajo() {
        return horarioTrabajo;
    }

    public void setHorarioTrabajo(String horarioTrabajo) {
        this.horarioTrabajo = horarioTrabajo;
    }

    public ClasificacionReporteEntity getClasificacionReporte() {
        return clasificacionReporte;
    }

    public void setClasificacionReporte(
            ClasificacionReporteEntity clasificacionReporte) {
        this.clasificacionReporte = clasificacionReporte;
    }

    public Date getFechaPosicionUno() {
        return fechaPosicionUno;
    }

    public void setFechaPosicionUno(Date fechaPosicionUno) {
        this.fechaPosicionUno = fechaPosicionUno;
    }

}
