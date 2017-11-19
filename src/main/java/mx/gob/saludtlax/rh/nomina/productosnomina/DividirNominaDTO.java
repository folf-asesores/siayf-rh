/*
 * DividirNominaDTO.java
 * Creado el 25/Dec/2016 8:23:42 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class DividirNominaDTO
        implements Serializable, Comparable<DividirNominaDTO> {

    private static final long serialVersionUID = -8248467557820073623L;

    private Integer idNominaEmpleado;
    private Integer idProductoNomina;
    private String nombreProductoNomina;
    private Integer consecutivo;
    private Integer idEmpleado;
    private String rfc;
    private String nombreEmpleado;
    private Integer idUnidadResponsable;
    private String unidadResponsable;
    private Integer idRama;
    private String rama;
    private BigDecimal percepciones;
    private BigDecimal deducciones;
    private BigDecimal neto;

    public DividirNominaDTO() {
        this(0, 0, "", 0, 0, "", "", 0, "", 0, "", BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public DividirNominaDTO(Integer idNominaEmpleado, Integer idProductoNomina,
            String nombreProductoNomina, Integer consecutivo,
            Integer idEmpleado, String rfc, String nombreEmpleado,
            Integer idUnidadResponsable, String unidadResponsable,
            Integer idRama, String rama, BigDecimal percepciones,
            BigDecimal deducciones, BigDecimal neto) {
        this.idNominaEmpleado = idNominaEmpleado;
        this.idProductoNomina = idProductoNomina;
        this.nombreProductoNomina = nombreProductoNomina;
        this.consecutivo = consecutivo;
        this.idEmpleado = idEmpleado;
        this.rfc = rfc;
        this.nombreEmpleado = nombreEmpleado;
        this.idUnidadResponsable = idUnidadResponsable;
        this.unidadResponsable = unidadResponsable;
        this.idRama = idRama;
        this.rama = rama;
        this.percepciones = percepciones;
        this.deducciones = deducciones;
        this.neto = neto;
    }

    /**
     * Get the value of idNominaEmpleado
     *
     * @return the value of idNominaEmpleado
     */
    public Integer getIdNominaEmpleado() {
        return idNominaEmpleado;
    }

    /**
     * Set the value of idNominaEmpleado
     *
     * @param idNominaEmpleado
     *            new value of idNominaEmpleado
     */
    public void setIdNominaEmpleado(Integer idNominaEmpleado) {
        this.idNominaEmpleado = idNominaEmpleado;
    }

    /**
     * Get the value of idProductoNomina
     *
     * @return the value of idProductoNomina
     */
    public Integer getIdProductoNomina() {
        return idProductoNomina;
    }

    /**
     * Set the value of idProductoNomina
     *
     * @param idProductoNomina
     *            new value of idProductoNomina
     */
    public void setIdProductoNomina(Integer idProductoNomina) {
        this.idProductoNomina = idProductoNomina;
    }

    /**
     * Get the value of nombreProductoNomina
     *
     * @return the value of nombreProductoNomina
     */
    public String getNombreProductoNomina() {
        return nombreProductoNomina;
    }

    /**
     * Set the value of nombreProductoNomina
     *
     * @param nombreProductoNomina
     *            new value of nombreProductoNomina
     */
    public void setNombreProductoNomina(String nombreProductoNomina) {
        this.nombreProductoNomina = nombreProductoNomina;
    }

    /**
     * Get the value of consecutivo
     *
     * @return the value of consecutivo
     */
    public Integer getConsecutivo() {
        return consecutivo;
    }

    /**
     * Set the value of consecutivo
     *
     * @param consecutivo
     *            new value of consecutivo
     */
    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    /**
     * Get the value of idEmpleado
     *
     * @return the value of idEmpleado
     */
    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * Set the value of idEmpleado
     *
     * @param idEmpleado
     *            new value of idEmpleado
     */
    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * Get the value of rfc
     *
     * @return the value of rfc
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Set the value of rfc
     *
     * @param rfc
     *            new value of rfc
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * Get the value of nombreEmpleado
     *
     * @return the value of nombreEmpleado
     */
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    /**
     * Set the value of nombreEmpleado
     *
     * @param nombreEmpleado
     *            new value of nombreEmpleado
     */
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    /**
     * Get the value of idUnidadResponsable
     *
     * @return the value of idUnidadResponsable
     */
    public Integer getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    /**
     * Set the value of idUnidadResponsable
     *
     * @param idUnidadResponsable
     *            new value of idUnidadResponsable
     */
    public void setIdUnidadResponsable(Integer idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    /**
     * Get the value of unidadResponsable
     *
     * @return the value of unidadResponsable
     */
    public String getUnidadResponsable() {
        return unidadResponsable;
    }

    /**
     * Set the value of unidadResponsable
     *
     * @param unidadResponsable
     *            new value of unidadResponsable
     */
    public void setUnidadResponsable(String unidadResponsable) {
        this.unidadResponsable = unidadResponsable;
    }

    /**
     * Get the value of idRama
     *
     * @return the value of idRama
     */
    public Integer getIdRama() {
        return idRama;
    }

    /**
     * Set the value of idRama
     *
     * @param idRama
     *            new value of idRama
     */
    public void setIdRama(Integer idRama) {
        this.idRama = idRama;
    }

    /**
     * Get the value of rama
     *
     * @return the value of rama
     */
    public String getRama() {
        return rama;
    }

    /**
     * Set the value of rama
     *
     * @param rama
     *            new value of rama
     */
    public void setRama(String rama) {
        this.rama = rama;
    }

    /**
     * Get the value of percepciones
     *
     * @return the value of percepciones
     */
    public BigDecimal getPercepciones() {
        return percepciones;
    }

    /**
     * Set the value of percepciones
     *
     * @param percepciones
     *            new value of percepciones
     */
    public void setPercepciones(BigDecimal percepciones) {
        this.percepciones = percepciones;
    }

    /**
     * Get the value of deducciones
     *
     * @return the value of deducciones
     */
    public BigDecimal getDeducciones() {
        return deducciones;
    }

    /**
     * Set the value of deducciones
     *
     * @param deducciones
     *            new value of deducciones
     */
    public void setDeducciones(BigDecimal deducciones) {
        this.deducciones = deducciones;
    }

    /**
     * Get the value of neto
     *
     * @return the value of neto
     */
    public BigDecimal getNeto() {
        return neto;
    }

    /**
     * Set the value of neto
     *
     * @param neto
     *            new value of neto
     */
    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

    @Override
    public int compareTo(DividirNominaDTO o) {
        if (consecutivo == null) {
            return -1;
        }

        if (o == null || o.consecutivo == null) {
            return 1;
        }

        return Integer.compare(consecutivo, o.consecutivo);
    }

    @Override
    public String toString() {
        return "DividirNominaDTO{" + "idProductoNomina=" + idProductoNomina
                + ", nombreProductoNomina=" + nombreProductoNomina
                + ", idEmpleado=" + idEmpleado + ", rfc=" + rfc + ", nombre="
                + nombreEmpleado + ", idUnidadResponsable="
                + idUnidadResponsable + ", unidadResponsable="
                + unidadResponsable + ", idRama=" + idRama + ", rama=" + rama
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(idProductoNomina);
        hash = 89 * hash + Objects.hashCode(nombreProductoNomina);
        hash = 89 * hash + Objects.hashCode(idEmpleado);
        hash = 89 * hash + Objects.hashCode(rfc);
        hash = 89 * hash + Objects.hashCode(nombreEmpleado);
        hash = 89 * hash + Objects.hashCode(idUnidadResponsable);
        hash = 89 * hash + Objects.hashCode(unidadResponsable);
        hash = 89 * hash + Objects.hashCode(idRama);
        hash = 89 * hash + Objects.hashCode(rama);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DividirNominaDTO other = (DividirNominaDTO) obj;
        if (!Objects.equals(nombreProductoNomina, other.nombreProductoNomina)) {
            return false;
        }
        if (!Objects.equals(rfc, other.rfc)) {
            return false;
        }
        if (!Objects.equals(nombreEmpleado, other.nombreEmpleado)) {
            return false;
        }
        if (!Objects.equals(unidadResponsable, other.unidadResponsable)) {
            return false;
        }
        if (!Objects.equals(rama, other.rama)) {
            return false;
        }
        if (!Objects.equals(idProductoNomina, other.idProductoNomina)) {
            return false;
        }
        if (!Objects.equals(idEmpleado, other.idEmpleado)) {
            return false;
        }
        if (!Objects.equals(idUnidadResponsable, other.idUnidadResponsable)) {
            return false;
        }
        return Objects.equals(idRama, other.idRama);
    }

}
