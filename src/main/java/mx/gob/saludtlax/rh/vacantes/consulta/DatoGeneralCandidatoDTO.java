/*
 *
 */

package mx.gob.saludtlax.rh.vacantes.consulta;

import java.util.Date;
import java.util.List;

/**
 * @author eduardo
 *
 */
public class DatoGeneralCandidatoDTO {

    private String rfc;
    private String curp;
    private String idSexo;
    private String idEstadoCivil;
    private Date fechaNacimiento;
    private String lugarNacimiento;
    private String telefonos;
    private String correo;
    private String idTipoSangre;
    private Boolean tienePersonasDependientes;
    private int numeroHijos;
    private Integer numeroPadres;
    private Integer numeroConyuges;
    private Integer numeroOtros;
    private String nombreCompleto;
    private String direccionCompleta;
    private List<String> parentescos;

    /**
     *
     */
    public DatoGeneralCandidatoDTO() {
        super();
    }

    public DatoGeneralCandidatoDTO(String nombreCompleto, String rfc, String curp, String direccionCompleta, String idSexo, String idEstadoCivil,
            Date fechaNacimiento, String lugarNacimiento, String telefonos, String correo, String idTipoSangre, Boolean tienePersonasDependientes,
            int numeroHijos, Integer numeroPadres, Integer numeroConyuges, Integer numeroOtros) {

        this.nombreCompleto = nombreCompleto;
        this.rfc = rfc;
        this.curp = curp;
        this.direccionCompleta = direccionCompleta;
        this.idSexo = idSexo;
        this.idEstadoCivil = idEstadoCivil;
        this.fechaNacimiento = fechaNacimiento;
        this.lugarNacimiento = lugarNacimiento;
        this.telefonos = telefonos;
        this.correo = correo;
        this.idTipoSangre = idTipoSangre;
        this.tienePersonasDependientes = tienePersonasDependientes;
        this.numeroHijos = numeroHijos;
        this.numeroPadres = numeroPadres;
        this.numeroConyuges = numeroConyuges;
        this.numeroOtros = numeroOtros;

    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto
     *            the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return the rfc
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * @param rfc
     *            the rfc to set
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * @return the curp
     */
    public String getCurp() {
        return curp;
    }

    /**
     * @param curp
     *            the curp to set
     */
    public void setCurp(String curp) {
        this.curp = curp;
    }

    /**
     * @return the direccionCompleta
     */
    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    /**
     * @param direccionCompleta
     *            the direccionCompleta to set
     */
    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }

    /**
     * @return the idSexo
     */
    public String getIdSexo() {
        return idSexo;
    }

    /**
     * @param idSexo
     *            the idSexo to set
     */
    public void setIdSexo(String idSexo) {
        this.idSexo = idSexo;
    }

    /**
     * @return the idEstadoCivil
     */
    public String getIdEstadoCivil() {
        return idEstadoCivil;
    }

    /**
     * @param idEstadoCivil
     *            the idEstadoCivil to set
     */
    public void setIdEstadoCivil(String idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento
     *            the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the lugarNacimiento
     */
    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    /**
     * @param lugarNacimiento
     *            the lugarNacimiento to set
     */
    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    /**
     * @return the telefonos
     */
    public String getTelefonos() {
        return telefonos;
    }

    /**
     * @param telefonos
     *            the telefonos to set
     */
    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo
     *            the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the idTipoSangre
     */
    public String getIdTipoSangre() {
        return idTipoSangre;
    }

    /**
     * @param idTipoSangre
     *            the idTipoSangre to set
     */
    public void setIdTipoSangre(String idTipoSangre) {
        this.idTipoSangre = idTipoSangre;
    }

    /**
     * @return the tienePersonasDependientes
     */
    public Boolean getTienePersonasDependientes() {
        return tienePersonasDependientes;
    }

    /**
     * @param tienePersonasDependientes
     *            the tienePersonasDependientes to set
     */
    public void setTienePersonasDependientes(Boolean tienePersonasDependientes) {
        this.tienePersonasDependientes = tienePersonasDependientes;
    }

    /**
     * @return the numeroHijos
     */
    public int getNumeroHijos() {
        return numeroHijos;
    }

    /**
     * @param numeroHijos
     *            the numeroHijos to set
     */
    public void setNumeroHijos(int numeroHijos) {
        this.numeroHijos = numeroHijos;
    }

    /**
     * @return the numeroPadres
     */
    public Integer getNumeroPadres() {
        return numeroPadres;
    }

    /**
     * @param numeroPadres
     *            the numeroPadres to set
     */
    public void setNumeroPadres(Integer numeroPadres) {
        this.numeroPadres = numeroPadres;
    }

    /**
     * @return the numeroConyuges
     */
    public Integer getNumeroConyuges() {
        return numeroConyuges;
    }

    /**
     * @param numeroConyuges
     *            the numeroConyuges to set
     */
    public void setNumeroConyuges(Integer numeroConyuges) {
        this.numeroConyuges = numeroConyuges;
    }

    /**
     * @return the numeroOtros
     */
    public Integer getNumeroOtros() {
        return numeroOtros;
    }

    /**
     * @param numeroOtros
     *            the numeroOtros to set
     */
    public void setNumeroOtros(Integer numeroOtros) {
        this.numeroOtros = numeroOtros;
    }

    /**
     * @return the parentescos
     */
    public List<String> getParentescos() {
        return parentescos;
    }

    /**
     * @param parentescos
     *            the parentescos to set
     */
    public void setParentescos(List<String> parentescos) {
        this.parentescos = parentescos;
    }

}
