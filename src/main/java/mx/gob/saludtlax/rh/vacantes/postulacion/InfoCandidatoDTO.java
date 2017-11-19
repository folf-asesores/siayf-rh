/*
 *
 */

package mx.gob.saludtlax.rh.vacantes.postulacion;

/**
 * @author Leila Schiaffini Ehuan
 * @since 01/09/2016 11:33:46
 *
 */
public class InfoCandidatoDTO {
    private Integer idCandidatoPostulado;
    private String nombre;
    private String rfc;
    private String curp;
    private int edad;
    private String nacionalidad;
    private String sexo;
    private String estadoCivil;
    private String domicilio;
    private String estudios;
    private String tipoCandidato;
    private Integer idTipoCandidato;
    private Integer idContexto;
    private String estatus;

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdTipoCandidato() {
        return idTipoCandidato;
    }

    public void setIdTipoCandidato(Integer idTipoCandidato) {
        this.idTipoCandidato = idTipoCandidato;
    }

    public Integer getIdCandidatoPostulado() {
        return idCandidatoPostulado;
    }

    public void setIdCandidatoPostulado(Integer idCandidatoPostulado) {
        this.idCandidatoPostulado = idCandidatoPostulado;
    }

    public Integer getIdContexto() {
        return idContexto;
    }

    public void setIdContexto(Integer idContexto) {
        this.idContexto = idContexto;
    }

    public String getTipoCandidato() {
        return tipoCandidato;
    }

    public void setTipoCandidato(String tipoCandidato) {
        this.tipoCandidato = tipoCandidato;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

}
