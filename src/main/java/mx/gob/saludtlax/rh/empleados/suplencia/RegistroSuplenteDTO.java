/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-19
 *
 */
public class RegistroSuplenteDTO {
    private Integer idTipoCandidato;
    private Integer idEmpleado;
    private String numeroCuenta;

    private Integer idUsuario;
    private Integer idBanco;
    private Integer idProyecto;
    private Integer idDependencia;
    private Integer idUnidadResponsable;
    private Integer idCentroResponsabilidad;
    private Integer idPuesto;
    private Integer idMetodoPago;
    private Integer numeroLaboral;
    private AltaSuplenteDTO suplente = new AltaSuplenteDTO();

    public Integer getNumeroLaboral() {
        return numeroLaboral;
    }

    public void setNumeroLaboral(Integer numeroLaboral) {
        this.numeroLaboral = numeroLaboral;
    }

    public Integer getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(Integer idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public Integer getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    public Integer getIdCentroResponsabilidad() {
        return idCentroResponsabilidad;
    }

    public void setIdCentroResponsabilidad(Integer idCentroResponsabilidad) {
        this.idCentroResponsabilidad = idCentroResponsabilidad;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Integer getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    public void setIdUnidadResponsable(Integer idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Integer getIdTipoCandidato() {
        return idTipoCandidato;
    }

    public void setIdTipoCandidato(Integer idTipoCandidato) {
        this.idTipoCandidato = idTipoCandidato;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public AltaSuplenteDTO getSuplente() {
        return suplente;
    }

    public void setSuplente(AltaSuplenteDTO suplente) {
        this.suplente = suplente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

}
