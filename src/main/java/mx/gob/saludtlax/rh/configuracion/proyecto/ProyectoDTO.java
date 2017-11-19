/*
 * ProyectoDTO.java
 * Creado el 23/07/2016 09:41:58 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.proyecto;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ProyectoDTO {

    private Integer idProyecto;
    private Integer idDependencia;
    private Integer idUnidadResponsable;
    private String base36;
    private Integer clave;
    private String nombre;
    private Integer idSector;
    private String claveAreaAdscripcion;
    private Integer idEstrategia;
    private Integer idLineaAccion;
    private Integer idAreaAdscripcion;
    private Integer ejercicioFiscal;

    public ProyectoDTO() {
    }

    public ProyectoDTO(Integer idProyecto, Integer idDependencia, Integer idUnidadResponsable, String base36, Integer clave, String nombre, Integer idSector,
            String claveAreaAdscripcion, Integer idEstrategia, Integer idLineaAccion, Integer idAreaAdscripcion, Integer ejercicioFiscal) {
        this.idProyecto = idProyecto;
        this.idDependencia = idDependencia;
        this.idUnidadResponsable = idUnidadResponsable;
        this.base36 = base36;
        this.clave = clave;
        this.nombre = nombre;
        this.idSector = idSector;
        this.claveAreaAdscripcion = claveAreaAdscripcion;
        this.idEstrategia = idEstrategia;
        this.idLineaAccion = idLineaAccion;
        this.idAreaAdscripcion = idAreaAdscripcion;
        this.ejercicioFiscal = ejercicioFiscal;
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

    public String getBase36() {
        return base36;
    }

    public void setBase36(String base36) {
        this.base36 = base36;
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdSector() {
        return idSector;
    }

    public void setIdSector(Integer idSector) {
        this.idSector = idSector;
    }

    public String getClaveAreaAdscripcion() {
        return claveAreaAdscripcion;
    }

    public void setClaveAreaAdscripcion(String claveAreaAdscripcion) {
        this.claveAreaAdscripcion = claveAreaAdscripcion;
    }

    public Integer getIdEstrategia() {
        return idEstrategia;
    }

    public void setIdEstrategia(Integer idEstrategia) {
        this.idEstrategia = idEstrategia;
    }

    public Integer getIdLineaAccion() {
        return idLineaAccion;
    }

    public void setIdLineaAccion(Integer idLineaAccion) {
        this.idLineaAccion = idLineaAccion;
    }

    public Integer getIdAreaAdscripcion() {
        return idAreaAdscripcion;
    }

    public void setIdAreaAdscripcion(Integer idAreaAdscripcion) {
        this.idAreaAdscripcion = idAreaAdscripcion;
    }

    public Integer getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(Integer ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }
}
