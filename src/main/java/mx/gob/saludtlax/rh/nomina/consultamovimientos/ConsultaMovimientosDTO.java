package mx.gob.saludtlax.rh.nomina.consultamovimientos;

import java.util.Date;

/**
 * 
 * @author José Pablo
 *
 */
public class ConsultaMovimientosDTO {
	private Integer idEmpleado;
	private String rfc;
	private String curp;
	private String idSexo;
	private String idEstadoCivil;
	private Date fechaNacimiento;
	private String lugarNacimiento;
	private String telefonos;
	private String correo;
	private Boolean tienePersonasDependientes;
	private String nombreCompleto;
	private String direccionCompleta;
	private Integer idFoto;

	public Integer getIdEmpleado() {
        return idEmpleado;
    }
    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
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
    public Boolean getTienePersonasDependientes() {
        return tienePersonasDependientes;
    }
    public void setTienePersonasDependientes(Boolean tienePersonasDependientes) {
        this.tienePersonasDependientes = tienePersonasDependientes;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public String getDireccionCompleta() {
        return direccionCompleta;
    }
    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }
    public Integer getIdFoto() {
        return idFoto;
    }
    public void setIdFoto(Integer idFoto) {
        this.idFoto = idFoto;
    }
}
