
package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Leila
 * @modify Eduardo Mex
 * 
 * @version 1.0
 * @since 13/05/2016 10:59:26
 */
public class AspiranteDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -298910740178671798L;

    private Integer IdAspirante = 0;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rfc;
    private String curp;
    private Date fechaNacimiento;
    private String telefono;
    private String correoElectronico;
    private Integer numeroHijos = 0;
    private Integer numeroConyuges = 0;
    private Integer numeroPadres = 0;
    private Integer numeroOtros = 0;
    private boolean tienePersonasDependientes;
    private String tipoSangre;
    private String nacionalidad = "MEXICANA";
    private Integer idPaisNacionalidad;
    private String estadoCivil;
    private Integer idPuesto = 0;
    private String sexo;
    private String lugarNacimiento;
    private Integer idDepartamento = 0;
    private float peso;
    private float estatura;
    private String viveCon;
    private int[] personasDependientes;
    private String afore;
    private String numeroSeguroSocial;
    private String numeroCartillaMilitar;
    private String numeroPasaporte;
    private boolean tieneLicencia;
    private String tipoLicencia;
    private String numeroLicencia;
    private DireccionDTO direccionDTO = new DireccionDTO();
    private Integer idFotografia = 0;

    public String getViveCon() {
        return viveCon;
    }

    public void setViveCon(String viveCon) {
        this.viveCon = viveCon;
    }

    public String getAfore() {
        return afore;
    }

    public void setAfore(String afore) {
        this.afore = afore;
    }

    public String getNumeroSeguroSocial() {
        return numeroSeguroSocial;
    }

    public void setNumeroSeguroSocial(String numeroSeguroSocial) {
        this.numeroSeguroSocial = numeroSeguroSocial;
    }

    public String getNumeroCartillaMilitar() {
        return numeroCartillaMilitar;
    }

    public void setNumeroCartillaMilitar(String numeroCartillaMilitar) {
        this.numeroCartillaMilitar = numeroCartillaMilitar;
    }

    public String getNumeroPasaporte() {
        return numeroPasaporte;
    }

    public void setNumeroPasaporte(String numeroPasaporte) {
        this.numeroPasaporte = numeroPasaporte;
    }

    public boolean isTieneLicencia() {
        return tieneLicencia;
    }

    public void setTieneLicencia(boolean tieneLicencia) {
        this.tieneLicencia = tieneLicencia;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
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

    public Integer getNumeroHijos() {
        return numeroHijos;
    }

    public void setNumeroHijos(Integer numeroHijos) {
        this.numeroHijos = numeroHijos;
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

    public Integer getIdPaisNacionalidad() {
        return idPaisNacionalidad;
    }

    public void setIdPaisNacionalidad(Integer idPaisNacionalidad) {
        this.idPaisNacionalidad = idPaisNacionalidad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Integer getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public DireccionDTO getDireccionDTO() {
        return direccionDTO;
    }

    public void setDireccionDTO(DireccionDTO direccionDTO) {
        this.direccionDTO = direccionDTO;
    }

    public int[] getPersonasDependientes() {
        return personasDependientes;
    }

    public void setPersonasDependientes(int[] personasDependientes) {
        this.personasDependientes = personasDependientes;
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

    /**
     * @return the idAspirante
     */
    public Integer getIdAspirante() {
        return IdAspirante;
    }

    /**
     * @param idAspirante
     *            the idAspirante to set
     */
    public void setIdAspirante(Integer idAspirante) {
        IdAspirante = idAspirante;
    }

    /**
     * @return the tienePersonasDependientes
     */
    public boolean isTienePersonasDependientes() {
        return tienePersonasDependientes;
    }

    /**
     * @param tienePersonasDependientes
     *            the tienePersonasDependientes to set
     */
    public void setTienePersonasDependientes(
            boolean tienePersonasDependientes) {
        this.tienePersonasDependientes = tienePersonasDependientes;
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico
     *            the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Integer getIdFotografia() {
        return idFotografia;
    }

    public void setIdFotografia(Integer idFotografia) {
        this.idFotografia = idFotografia;
    }

}
