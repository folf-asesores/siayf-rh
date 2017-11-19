
package mx.gob.saludtlax.rh.siif.layout;

import java.util.Date;

public class DatosPersonalesDTO {
    private Integer idEmpleadoDatosPersonales;
    private String rfc;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombre;
    private Date fechaNacimineto;
    /**
     * <p>
     * Define el sexo del empleado.
     * </p>
     * <p>
     * Donde 'F' es femenino y 'M' es masculino.
     * </p>
     */
    private Character sexo;
    private String idLocalidad;
    private String idColonia;
    private String calle;
    private String numeroExterior;
    private String numeroInterior;
    private String codigoPostal;
    private String telefono;
    private Character idEstadoEmpleado;
    private Integer idNomina;

    public DatosPersonalesDTO() {
    }

    public DatosPersonalesDTO(Integer idEmpleadoDatosPersonales, String rfc, String apellidoPaterno, String apellidoMaterno, String nombre,
            Date fechaNacimineto, Character sexo, String idLocalidad, String idColonia, String calle, String numeroExterior, String numeroInterior,
            String codigoPostal, String telefono, Character idEstadoEmpleado, Integer idNomina) {
        this.idEmpleadoDatosPersonales = idEmpleadoDatosPersonales;
        this.rfc = rfc;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombre = nombre;
        this.fechaNacimineto = fechaNacimineto;
        this.sexo = sexo;
        this.idLocalidad = idLocalidad;
        this.idColonia = idColonia;
        this.calle = calle;
        this.numeroExterior = numeroExterior;
        this.numeroInterior = numeroInterior;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.idEstadoEmpleado = idEstadoEmpleado;
        this.idNomina = idNomina;
    }

    @MethodOrder(value = 1)
    public Integer getIdEmpleadoDatosPersonales() {
        return idEmpleadoDatosPersonales;
    }

    public void setIdEmpleadoDatosPersonales(Integer idEmpleadoDatosPersonales) {
        this.idEmpleadoDatosPersonales = idEmpleadoDatosPersonales;
    }

    @MethodOrder(value = 2)
    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @MethodOrder(value = 3)
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @MethodOrder(value = 4)
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    @MethodOrder(value = 5)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @MethodOrder(value = 6)
    public Date getFechaNacimineto() {
        return fechaNacimineto;
    }

    public void setFechaNacimineto(Date fechaNacimineto) {
        this.fechaNacimineto = fechaNacimineto;
    }

    @MethodOrder(value = 7)
    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    @MethodOrder(value = 8)
    public String getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(String idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    @MethodOrder(value = 9)
    public String getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(String idColonia) {
        this.idColonia = idColonia;
    }

    @MethodOrder(value = 10)
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @MethodOrder(value = 11)
    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    @MethodOrder(value = 12)
    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    @MethodOrder(value = 13)
    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @MethodOrder(value = 14)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @MethodOrder(value = 15)
    public Character getIdEstadoEmpleado() {
        return idEstadoEmpleado;
    }

    public void setIdEstadoEmpleado(Character idEstadoEmpleado) {
        this.idEstadoEmpleado = idEstadoEmpleado;
    }

    public Integer getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(Integer idNomina) {
        this.idNomina = idNomina;
    }

}