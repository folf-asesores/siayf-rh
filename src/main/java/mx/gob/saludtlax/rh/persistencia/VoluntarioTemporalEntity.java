/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

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
 * @author Leila Schiaffini Ehuan
 *
 * @since 24/11/2016 21:56:53
 */
@Entity
@Table(name = "voluntarios_padron")
public class VoluntarioTemporalEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7358643708898980051L;

    @Id
    @Column(name = "id_voluntario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVoluntario;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "curp")
    private String curp;

    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "sueldo")
    private BigDecimal sueldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adscripcion")
    private AdscripcionEntity adscripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subadscripcion")
    private SubadscripcionEntity subadscripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcion")
    private FuncionEntity funcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programa")
    private ProgramaEntity programa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicio")
    private ServicioEntity servicio;

    public ServicioEntity getServicio() {
        return servicio;
    }

    public void setServicio(ServicioEntity servicio) {
        this.servicio = servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdVoluntario(Integer idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public AdscripcionEntity getAdscripcion() {
        return adscripcion;
    }

    public void setAdscripcion(AdscripcionEntity adscripcion) {
        this.adscripcion = adscripcion;
    }

    public SubadscripcionEntity getSubadscripcion() {
        return subadscripcion;
    }

    public void setSubadscripcion(SubadscripcionEntity subadscripcion) {
        this.subadscripcion = subadscripcion;
    }

    public FuncionEntity getFuncion() {
        return funcion;
    }

    public void setFuncion(FuncionEntity funcion) {
        this.funcion = funcion;
    }

    public ProgramaEntity getPrograma() {
        return programa;
    }

    public void setPrograma(ProgramaEntity programa) {
        this.programa = programa;
    }

}
