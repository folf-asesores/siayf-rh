/*
 * DividirNominaFiltro.java
 * Creado el 26/Dec/2016 8:50:27 AM
 *
 */

package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class DividirNominaFiltro implements Serializable {

    private static final long serialVersionUID = 148685796812491366L;

    private Integer idProductoNomina;
    private String nombreProductoNomina;
    private List<String> rfc;
    private String unidadResponsable;
    private String ramaPuesto;

    public DividirNominaFiltro() {
        this(0, "", Collections.EMPTY_LIST, "", "");
    }

    public DividirNominaFiltro(Integer idProductoNomina,
            String nombreProductoNomina, List<String> rfc,
            String unidadResponsable, String ramaPuesto) {
        this.idProductoNomina = idProductoNomina;
        this.nombreProductoNomina = nombreProductoNomina;
        this.rfc = rfc;
        this.unidadResponsable = unidadResponsable;
        this.ramaPuesto = ramaPuesto;
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
     * Get the value of rfc
     *
     * @return the value of rfc
     */
    public List<String> getRfc() {
        return rfc;
    }

    /**
     * Set the value of rfc
     *
     * @param rfc
     *            new value of rfc
     */
    public void setRfc(List<String> rfc) {
        this.rfc = rfc;
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
     * Get the value of ramaPuesto
     *
     * @return the value of ramaPuesto
     */
    public String getRamaPuesto() {
        return ramaPuesto;
    }

    /**
     * Set the value of ramaPuesto
     *
     * @param ramaPuesto
     *            new value of ramaPuesto
     */
    public void setRamaPuesto(String ramaPuesto) {
        this.ramaPuesto = ramaPuesto;
    }

    @Override
    public String toString() {
        return "DividirNominaFiltro{" + "idProductoNomina=" + idProductoNomina
                + ", nombreProductoNomina=" + nombreProductoNomina + ", rfc="
                + rfc + ", unidadResponsable=" + unidadResponsable
                + ", ramaPuesto=" + ramaPuesto + '}';
    }

    @Override
    public int hashCode() {
        int hash = 14;
        hash = 89 * hash + Objects.hashCode(idProductoNomina);
        hash = 89 * hash + Objects.hashCode(nombreProductoNomina);
        hash = 89 * hash + Objects.hashCode(rfc);
        hash = 89 * hash + Objects.hashCode(unidadResponsable);
        hash = 89 * hash + Objects.hashCode(ramaPuesto);
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
        final DividirNominaFiltro other = (DividirNominaFiltro) obj;
        if (!Objects.equals(idProductoNomina, other.idProductoNomina)) {
            return false;
        }
        if (!Objects.equals(nombreProductoNomina, other.nombreProductoNomina)) {
            return false;
        }
        if (!Objects.equals(rfc, other.rfc)) {
            return false;
        }
        if (!Objects.equals(unidadResponsable, other.unidadResponsable)) {
            return false;
        }
        return Objects.equals(ramaPuesto, other.ramaPuesto);
    }

}
