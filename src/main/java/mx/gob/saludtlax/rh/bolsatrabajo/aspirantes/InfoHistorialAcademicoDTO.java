/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 29/03/2016-12:10:17
 */
public class InfoHistorialAcademicoDTO {

    private String escolaridad;
    private Integer idEscolaridad;
    private String institucion;
    private String estadoEscolaridad;
    private Integer idEstadoEscolaridad;
    private int inicio;
    private int fin;
    private String nombre;
    private int promedio;
    private String duracion;
    private boolean cursando;
    private String instructor;
    private String direccion;

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public Integer getIdEscolaridad() {
        return idEscolaridad;
    }

    public void setIdEscolaridad(Integer idEscolaridad) {
        this.idEscolaridad = idEscolaridad;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getEstadoEscolaridad() {
        return estadoEscolaridad;
    }

    public void setEstadoEscolaridad(String estadoEscolaridad) {
        this.estadoEscolaridad = estadoEscolaridad;
    }

    public Integer getIdEstadoEscolaridad() {
        return idEstadoEscolaridad;
    }

    public void setIdEstadoEscolaridad(Integer idEstadoEscolaridad) {
        this.idEstadoEscolaridad = idEstadoEscolaridad;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPromedio() {
        return promedio;
    }

    public void setPromedio(int promedio) {
        this.promedio = promedio;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public boolean isCursando() {
        return cursando;
    }

    public void setCursando(boolean cursando) {
        this.cursando = cursando;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
