/*
 * FirmaDTO.java
 * Creado el 15/Nov/2016 4:57:32 PM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.firma;

import java.util.List;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class FirmaDTO {
    
    private Integer idFirma;
    private Integer idAdscripcion;
    private String adscripcion;
    private String nombre;
    private String cargo;
    private Integer nivel;

    /**
     * Get the value of idFirma
     *
     * @return the value of idFirma
     */
    public Integer getIdFirma() {
        return idFirma;
    }

    /**
     * Set the value of idFirma
     *
     * @param idFirma new value of idFirma
     */
    public void setIdFirma(Integer idFirma) {
        this.idFirma = idFirma;
    }

    
    /**
     * Get the value of idAdscripcion
     *
     * @return the value of idAdscripcion
     */
    public Integer getIdAdscripcion() {
        return idAdscripcion;
    }

    /**
     * Set the value of idAdscripcion
     *
     * @param idAdscripcion new value of idAdscripcion
     */
    public void setIdAdscripcion(Integer idAdscripcion) {
        this.idAdscripcion = idAdscripcion;
    }
    

    /**
     * Get the value of adscripcion
     *
     * @return the value of adscripcion
     */
    public String getAdscripcion() {
        return adscripcion;
    }

    /**
     * Set the value of adscripcion
     *
     * @param adscripcion new value of adscripcion
     */
    public void setAdscripcion(String adscripcion) {
        this.adscripcion = adscripcion;
    }

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the value of cargo
     *
     * @return the value of cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Set the value of cargo
     *
     * @param cargo new value of cargo
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Get the value of nivel
     *
     * @return the value of nivel
     */
    public Integer getNivel() {
        return nivel;
    }

    /**
     * Set the value of nivel
     *
     * @param nivel new value of nivel
     */
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

}
