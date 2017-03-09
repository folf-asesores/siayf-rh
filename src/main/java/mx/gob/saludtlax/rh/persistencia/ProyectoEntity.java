/*
 * ProyectoEntity.java
 * Creado el 23/07/2016 09:01:58 PM
 * 
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Entity
@Table(name = "proyectos")
public class ProyectoEntity implements Serializable {

    private static final long serialVersionUID = 128948382866249698L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    
    @Column(name = "id_dependencia")
    private Integer idDependencia;
    
    @Column(name = "id_unidad_responsable")
    private Integer idUnidadResponsable;
    
    @Column(name = "base_36")
    private String base36;
    
    @Column(name = "clave")
    private Integer clave;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "id_sector")
    private Integer idSector;
    
    @Column(name = "clave_area_adscripcion")
    private String claveAreaAdscripcion;
    
    @Column(name = "id_estrategia")
    private Integer idEstrategia;
    
    @Column(name = "id_linea_accion")
    private Integer idLineaAccion;
    
    @Column(name = "id_area_adscripcion")
    private Integer idAreaAdscripcion;
    
    @Column(name = "ejercicio_fiscal")
    private Integer ejercicioFiscal;

    public ProyectoEntity() {
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(int idDependencia) {
        this.idDependencia = idDependencia;
    }

    public int getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    public void setIdUnidadResponsable(int idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    public String getBase36() {
        return base36;
    }

    public void setBase36(String base36) {
        this.base36 = base36;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdSector() {
        return idSector;
    }

    public void setIdSector(int idSector) {
        this.idSector = idSector;
    }

    public String getClaveAreaAdscripcion() {
        return claveAreaAdscripcion;
    }

    public void setClaveAreaAdscripcion(String claveAreaAdscripcion) {
        this.claveAreaAdscripcion = claveAreaAdscripcion;
    }

    public int getIdEstrategia() {
        return idEstrategia;
    }

    public void setIdEstrategia(int idEstrategia) {
        this.idEstrategia = idEstrategia;
    }

    public int getIdLineaAccion() {
        return idLineaAccion;
    }

    public void setIdLineaAccion(int idLineaAccion) {
        this.idLineaAccion = idLineaAccion;
    }

    public Integer getIdAreaAdscripcion() {
        return idAreaAdscripcion;
    }

    public void setIdAreaAdscripcion(Integer idAreaAdscripcion) {
        this.idAreaAdscripcion = idAreaAdscripcion;
    }

    public int getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(int ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }
}
