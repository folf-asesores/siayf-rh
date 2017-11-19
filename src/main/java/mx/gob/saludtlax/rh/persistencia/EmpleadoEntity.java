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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Leila Schiaffini Ehuan
 *
 */
@Entity
@Table(name = "empleados")
public class EmpleadoEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2144997736010384226L;

    @Id
    @Column(name = "id_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpleado;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "numero_empleado")
    private Integer numeroEmpleado;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "curp")
    private String curp;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;

    @Column(name = "direccion_completa")
    private String direccionCompleta;

    @Column(name = "tipo_sangre")
    private String tipoSangre;

    @Column(name = "id_nacionalidad")
    private String nacionalidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pais_nacionalidad")
    private PaisEntity paisNacionalidad;

    @Column(name = "id_estado_civil")
    private String estadoCivil;

    @Column(name = "id_sexo")
    private String idSexo;

    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;

    @Column(name = "peso")
    private float peso;

    @Column(name = "estatura")
    private float estatura;

    @Column(name = "vive_con")
    private String viveCon;

    @Column(name = "persona_dependiente")
    private Integer personasDependientes;

    @Column(name = "id_estatus")
    private String idEstatus;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;

    @Column(name = "tiene_persona_dependiente")
    private Boolean tienePersonasDependientes;

    @Column(name = "tiene_licencia")
    private Boolean tieneLicencia;

    @Column(name = "numero_hijo")
    private int numeroHijos;

    @Column(name = "numero_conyuge")
    private Integer numeroConyuges;

    @Column(name = "numero_padre")
    private Integer numeroPadres;

    @Column(name = "numero_otro")
    private Integer numeroOtros;

    @Column(name = "otro_parentesco")
    private String otroParentesco;

    @Column(name = "id_foto")
    private Integer idFoto;

    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    @Column(name = "id_banco")
    private Integer idBanco;

    @Column(name = "id_tipo_baja")
    private Integer tipoBaja;

    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @Column(name = "id_tipo_contratacion_actual")
    private Integer idTipoContratacionActual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_empleado")
    private TipoEmpleadoEntity tipoEmpleado;

    @Column(name = "perfil_academico")
    private String perfilAcademico;

    @Column(name = "clave_cobro")
    private String claveCobro;

    @Column(name = "numero_seguro_social")
    private String numeroSeguroSocial;

    @Column(name = "id_metodo_pago")
    private Integer idMetodoPago;

    public void toUpperCase() {
        nombre = nombre.toUpperCase();
        apellidoMaterno = apellidoMaterno.toUpperCase();
        if (apellidoPaterno != null) {
            apellidoPaterno = apellidoPaterno.toUpperCase();
        }
        curp = curp.toUpperCase();
        lugarNacimiento = lugarNacimiento.toUpperCase();
    }

    public String nombreCompleto() {
        String nombreCompleto = nombre + " ";
        if (apellidoPaterno != null || !apellidoPaterno.trim().isEmpty()) {
            nombreCompleto = nombreCompleto + apellidoPaterno + " ";
        }
        nombreCompleto = nombreCompleto + apellidoMaterno;
        return nombreCompleto;
    }

    public String lccDatosGenerales() {
        String lcc = "DatosGenerales[nombre=" + nombre + ", apellidoPaterno="
                + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno
                + ", rfc=" + rfc + ", curp=" + curp + ", idSexo=" + idSexo
                + ", idEstadoCivil=" + estadoCivil + ", fechaNacimiento="
                + fechaNacimiento + ", lugarNacimiento=" + lugarNacimiento
                + ", telefonos=" + telefono + ", correo=" + correoElectronico
                + ", idTipoSangre=" + tipoSangre + ", peso=" + peso
                + ", estatura=" + estatura + ", tienePersonasDependientes="
                + tienePersonasDependientes + ", numeroHijos=" + numeroHijos
                + ", numeroPadres=" + numeroPadres + ", numeroConyuges="
                + numeroConyuges + ", numeroOtros=" + numeroOtros
                + ", otroParentesco=" + otroParentesco + "]";
        return lcc.toUpperCase();
    }

    public String lccDatoGeneral() {
        String lcc = "DatosGenerales[idSexo=" + idSexo + ", idEstadoCivil="
                + estadoCivil + ", fechaNacimiento=" + fechaNacimiento
                + ", lugarNacimiento=" + lugarNacimiento + ", telefonos="
                + telefono + ", correo=" + correoElectronico + ", idTipoSangre="
                + tipoSangre + ", peso=" + peso + ", estatura=" + estatura
                + ", tienePersonasDependientes=" + tienePersonasDependientes
                + ", numeroHijos=" + numeroHijos + ", numeroPadres="
                + numeroPadres + ", numeroConyuges=" + numeroConyuges
                + ", numeroOtros=" + numeroOtros + ", otroParentesco="
                + otroParentesco + "]";
        return lcc.toUpperCase();
    }

    public Integer getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(Integer idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public String getPerfilAcademico() {
        return perfilAcademico;
    }

    public void setPerfilAcademico(String perfilAcademico) {
        this.perfilAcademico = perfilAcademico;
    }

    public TipoEmpleadoEntity getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleadoEntity tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public Integer getIdTipoContratacionActual() {
        return idTipoContratacionActual;
    }

    public void setIdTipoContratacionActual(Integer idTipoContratacionActual) {
        this.idTipoContratacionActual = idTipoContratacionActual;
    }

    public Integer getTipoBaja() {
        return tipoBaja;
    }

    public void setTipoBaja(Integer tipoBaja) {
        this.tipoBaja = tipoBaja;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Integer getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Integer idFoto) {
        this.idFoto = idFoto;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Integer getNumeroConyuges() {
        return numeroConyuges;
    }

    public void setNumeroConyuges(Integer numeroConyuges) {
        this.numeroConyuges = numeroConyuges;
    }

    public Integer getNumeroPadres() {
        return numeroPadres;
    }

    public void setNumeroPadres(Integer numeroPadres) {
        this.numeroPadres = numeroPadres;
    }

    public Integer getNumeroOtros() {
        return numeroOtros;
    }

    public void setNumeroOtros(Integer numeroOtros) {
        this.numeroOtros = numeroOtros;
    }

    public String getOtroParentesco() {
        return otroParentesco;
    }

    public void setOtroParentesco(String otroParentesco) {
        this.otroParentesco = otroParentesco;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(Integer numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getClaveCobro() {
        return claveCobro;
    }

    public void setClaveCobro(String claveCobro) {
        this.claveCobro = claveCobro;
    }

    public String getNumeroSeguroSocial() {
        return numeroSeguroSocial;
    }

    public void setNumeroSeguroSocial(String numeroSeguroSocial) {
        this.numeroSeguroSocial = numeroSeguroSocial;
    }

    public Boolean getTienePersonasDependientes() {
        return tienePersonasDependientes;
    }

    public void setTienePersonasDependientes(
            Boolean tienePersonasDependientes) {
        this.tienePersonasDependientes = tienePersonasDependientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getNumeroHijos() {
        return numeroHijos;
    }

    public void setNumeroHijos(int numeroHijos) {
        this.numeroHijos = numeroHijos;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public PaisEntity getPaisNacionalidad() {
        return paisNacionalidad;
    }

    public void setPaisNacionalidad(PaisEntity paisNacionalidad) {
        this.paisNacionalidad = paisNacionalidad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(String idSexo) {
        this.idSexo = idSexo;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public String getViveCon() {
        return viveCon;
    }

    public void setViveCon(String viveCon) {
        this.viveCon = viveCon;
    }

    public String getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(String idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getPersonasDependientes() {
        return personasDependientes;
    }

    public void setPersonasDependientes(Integer personasDependientes) {
        this.personasDependientes = personasDependientes;
    }

    public Boolean getTieneLicencia() {
        return tieneLicencia;
    }

    public void setTieneLicencia(Boolean tieneLicencia) {
        this.tieneLicencia = tieneLicencia;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

}
