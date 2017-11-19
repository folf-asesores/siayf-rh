/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 21/03/2016-10:30:35
 */
public class InfoAspiranteDTO {
    private Integer idAspirante;
    private String nombre;
    private String curp;
    private String rfc;
    private String direccion;
    private String estatus;
    private int edad;
    private String nacionalidad;
    private String sexo;
    private String estadoCivil;
    private String estudios;
    private String profesionEspecialidad;

    public InfoAspiranteDTO() {
        super();
    }

    public InfoAspiranteDTO(Integer idAspirante, String nombre, String curp,
            String rfc, String direccion, String estatus) {

        this.idAspirante = idAspirante;
        this.nombre = nombre;
        this.curp = curp;
        this.rfc = rfc;
        this.direccion = direccion;
        this.estatus = estatus;
    }

    public InfoAspiranteDTO(Integer idAspirante, String nombre, String curp,
            String rfc, String direccion, String estatus,
            String profesionEspecialidad) {

        this.idAspirante = idAspirante;
        this.nombre = nombre;
        this.curp = curp;
        this.rfc = rfc;
        this.direccion = direccion;
        this.estatus = estatus;
        setProfesionEspecialidad(profesionEspecialidad);
    }

    public String getEstatus() {
        return estatus;
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

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Integer idAspirante) {
        this.idAspirante = idAspirante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the profesionEspecialidad
     */
    public String getProfesionEspecialidad() {
        return profesionEspecialidad;
    }

    /**
     * @param profesionEspecialidad
     *            the profesionEspecialidad to set
     */
    public void setProfesionEspecialidad(String profesionEspecialidad) {
        this.profesionEspecialidad = profesionEspecialidad;
    }

}
