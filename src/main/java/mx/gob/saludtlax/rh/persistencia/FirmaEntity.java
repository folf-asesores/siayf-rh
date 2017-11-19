/*
 * FirmaEntity.java
 * Creado el 15/Nov/2016 4:38:08 PM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "firmas")
public class FirmaEntity implements Serializable {

    private static final long serialVersionUID = -5602463577141765035L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_firma")
    private Integer idFirma;

    @Size(max = 255)
    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @Size(max = 255)
    @NotNull
    @Column(name = "cargo")
    private String cargo;

    @NotNull
    @Column(name = "nivel")
    private Integer nivel;

    @JoinColumn(name = "id_adscripcion", referencedColumnName = "id_adscripcion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AdscripcionEntity adscripcion;

    /**
     *
     */
    public FirmaEntity() {
    }

    /**
     *
     * @return
     */
    public Integer getIdFirma() {
        return idFirma;
    }

    /**
     *
     * @param idFirma
     */
    public void setIdFirma(Integer idFirma) {
        this.idFirma = idFirma;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre == null ? "" : nombre.toUpperCase().trim();
    }

    /**
     *
     * @return
     */
    public String getCargo() {
        return cargo;
    }

    /**
     *
     * @param cargo
     */
    public void setCargo(String cargo) {
        this.cargo = cargo == null ? "" : cargo.toUpperCase().trim();
    }

    /**
     *
     * @return
     */
    public AdscripcionEntity getAdscripcion() {
        return adscripcion;
    }

    /**
     *
     * @param adscripcion
     */
    public void setAdscripcion(AdscripcionEntity adscripcion) {
        this.adscripcion = adscripcion;
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
     * @param nivel
     *            new value of nivel
     */
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFirma != null ? idFirma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof FirmaEntity)) {
            return false;
        }
        FirmaEntity other = (FirmaEntity) object;
        if ((idFirma == null && other.idFirma != null)
                || (idFirma != null && !idFirma.equals(other.idFirma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.gob.saludtlax.rh.persistencia.Firmas[ idFirma=" + idFirma
                + " ]";
    }

}
