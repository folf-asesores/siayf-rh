
package mx.gob.saludtlax.rh.seguridad.usuario;

import java.io.Serializable;
import java.util.Date;

public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 9031160518080210230L;

    private Integer idUsuario;
    private String userName;
    private String password;
    private Boolean activo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String cargo;
    private Integer idPerfil;
    private Date fechaAlta;
    private Integer idAdscripcion;
    private Integer idLugarAdscripcion;
    private Integer idAreaAdscripcion;
    private String telefono;
    private String celular;

    private String adscripcionDescripcion;
    private String lugarAdscripcionDescripcion;
    private String areaAdscripcionDescripcion;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getIdAdscripcion() {
        return idAdscripcion;
    }

    public void setIdAdscripcion(Integer idAdscripcion) {
        this.idAdscripcion = idAdscripcion;
    }

    public Integer getIdLugarAdscripcion() {
        return idLugarAdscripcion;
    }

    public void setIdLugarAdscripcion(Integer idLugarAdscripcion) {
        this.idLugarAdscripcion = idLugarAdscripcion;
    }

    public Integer getIdAreaAdscripcion() {
        return idAreaAdscripcion;
    }

    public void setIdAreaAdscripcion(Integer idAreaAdscripcion) {
        this.idAreaAdscripcion = idAreaAdscripcion;
    }

    public String getAdscripcionDescripcion() {
        return adscripcionDescripcion;
    }

    public void setAdscripcionDescripcion(String adscripcionDescripcion) {
        this.adscripcionDescripcion = adscripcionDescripcion;
    }

    public String getLugarAdscripcionDescripcion() {
        return lugarAdscripcionDescripcion;
    }

    public void setLugarAdscripcionDescripcion(
            String lugarAdscripcionDescripcion) {
        this.lugarAdscripcionDescripcion = lugarAdscripcionDescripcion;
    }

    public String getAreaAdscripcionDescripcion() {
        return areaAdscripcionDescripcion;
    }

    public void setAreaAdscripcionDescripcion(
            String areaAdscripcionDescripcion) {
        this.areaAdscripcionDescripcion = areaAdscripcionDescripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

}
