/*
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.Date;
import java.util.List;

import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 13/04/2016-14:52:19
 */
public class DatosGeneralesDTO {
    private Integer idEmpleado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rfc;
    private String curp;
    private String idSexo;
    private String idEstadoCivil;
    private Date fechaNacimiento;
    private String lugarNacimiento;
    private String telefonos;
    private String correo;
    private String idTipoSangre;
    private float peso;
    private float estatura;
    private Boolean tienePersonasDependientes;
    private List<String> parentescos;
    private int numeroHijos;
    private int numeroPadres;
    private int numeroConyuges;
    private int numeroOtros;
    private String otroParentesco;
    private String nacionalidad;
    private String nombreCompleto;
    private String direccionCompleta;
    private Integer idFoto;
    private String tipoEmpleado;
    private String estatus;

    private String comentarioMovimiento;
    private Integer idUsuarioEnSesion;

    public String lccDatosGenerales() {
        String lcc = "DatosGenerales[nombre=" + nombre + ", apellidoPaterno="
                + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno
                + ", rfc=" + rfc + ", curp=" + curp + ", idSexo=" + idSexo
                + ", idEstadoCivil=" + idEstadoCivil + ", fechaNacimiento="
                + fechaNacimiento + ", lugarNacimiento=" + lugarNacimiento
                + ", telefonos=" + telefonos + ", correo=" + correo
                + ", idTipoSangre=" + idTipoSangre + ", peso=" + peso
                + ", estatura=" + estatura + ", tienePersonasDependientes="
                + tienePersonasDependientes + ", numeroHijos=" + numeroHijos
                + ", numeroPadres=" + numeroPadres + ", numeroConyuges="
                + numeroConyuges + ", numeroOtros=" + numeroOtros
                + ", otroParentesco=" + otroParentesco + "]";
        return lcc.toUpperCase();
    }

    public String lccDatoGeneral() {
        String lcc = "DatosGenerales[idSexo=" + idSexo + ", idEstadoCivil="
                + idEstadoCivil + ", fechaNacimiento=" + fechaNacimiento
                + ", lugarNacimiento=" + lugarNacimiento + ", telefonos="
                + telefonos + ", correo=" + correo + ", idTipoSangre="
                + idTipoSangre + ", peso=" + peso + ", estatura=" + estatura
                + ", tienePersonasDependientes=" + tienePersonasDependientes
                + ", numeroHijos=" + numeroHijos + ", numeroPadres="
                + numeroPadres + ", numeroConyuges=" + numeroConyuges
                + ", numeroOtros=" + numeroOtros + ", otroParentesco="
                + otroParentesco + "]";
        return lcc.toUpperCase();
    }

    public boolean tieneInformacionObligatoriaCompleta() {
        boolean tieneInformacionCompleta = true;
        if (ValidacionUtil.esCadenaVacia(nombre)
                || ValidacionUtil.esCadenaVacia(apellidoMaterno)
                || ValidacionUtil.esCadenaVacia(idSexo)
                || ValidacionUtil.esCadenaVacia(idEstadoCivil)
                || ValidacionUtil.esCadenaVacia(correo)) {
            tieneInformacionCompleta = false;
        }

        if (fechaNacimiento == null) {
            tieneInformacionCompleta = false;
        }

        return tieneInformacionCompleta;
    }

    @Override
    public String toString() {
        return "DatosGeneralesDTO [idEmpleado=" + idEmpleado + ", nombre="
                + nombre + ", apellidoPaterno=" + apellidoPaterno
                + ", apellidoMaterno=" + apellidoMaterno + ", rfc=" + rfc
                + ", curp=" + curp + ", idSexo=" + idSexo + ", idEstadoCivil="
                + idEstadoCivil + ", fechaNacimiento=" + fechaNacimiento
                + ", lugarNacimiento=" + lugarNacimiento + ", telefonos="
                + telefonos + ", correo=" + correo + ", idTipoSangre="
                + idTipoSangre + ", peso=" + peso + ", estatura=" + estatura
                + ", tienePersonasDependientes=" + tienePersonasDependientes
                + ", parentescos=" + parentescos + ", numeroHijos="
                + numeroHijos + ", numeroPadres=" + numeroPadres
                + ", numeroConyuges=" + numeroConyuges + ", numeroOtros="
                + numeroOtros + ", otroParentesco=" + otroParentesco
                + ", nacionalidad=" + nacionalidad + ", nombreCompleto="
                + nombreCompleto + ", direccionCompleta=" + direccionCompleta
                + ", idFoto=" + idFoto + "]";
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Integer idFoto) {
        this.idFoto = idFoto;
    }

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public List<String> getParentescos() {
        return parentescos;
    }

    public void setParentescos(List<String> parentescos) {
        this.parentescos = parentescos;
    }

    public int getNumeroHijos() {
        return numeroHijos;
    }

    public void setNumeroHijos(int numeroHijos) {
        this.numeroHijos = numeroHijos;
    }

    public int getNumeroPadres() {
        return numeroPadres;
    }

    public void setNumeroPadres(int numeroPadres) {
        this.numeroPadres = numeroPadres;
    }

    public int getNumeroConyuges() {
        return numeroConyuges;
    }

    public void setNumeroConyuges(int numeroConyuges) {
        this.numeroConyuges = numeroConyuges;
    }

    public int getNumeroOtros() {
        return numeroOtros;
    }

    public void setNumeroOtros(int numeroOtros) {
        this.numeroOtros = numeroOtros;
    }

    public String getOtroParentesco() {
        return otroParentesco;
    }

    public void setOtroParentesco(String otroParentesco) {
        this.otroParentesco = otroParentesco;
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

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
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

    public String getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(String idSexo) {
        this.idSexo = idSexo;
    }

    public String getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(String idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIdTipoSangre() {
        return idTipoSangre;
    }

    public void setIdTipoSangre(String idTipoSangre) {
        this.idTipoSangre = idTipoSangre;
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

    /**
     * @return the comentarioMovimiento
     */
    public String getComentarioMovimiento() {
        return comentarioMovimiento;
    }

    /**
     * @param comentarioMovimiento
     *            the comentarioMovimiento to set
     */
    public void setComentarioMovimiento(String comentarioMovimiento) {
        this.comentarioMovimiento = comentarioMovimiento;
    }

    /**
     * @return the idUsuarioEnSesion
     */
    public Integer getIdUsuarioEnSesion() {
        return idUsuarioEnSesion;
    }

    /**
     * @param idUsuarioEnSesion
     *            the idUsuarioEnSesion to set
     */
    public void setIdUsuarioEnSesion(Integer idUsuarioEnSesion) {
        this.idUsuarioEnSesion = idUsuarioEnSesion;
    }

}
