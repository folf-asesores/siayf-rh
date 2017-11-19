/*
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
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-14:23:54
 */
@Entity
@Table(name = "asentamientos")
public class AsentamientoEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7645592671077621759L;

    @Id
    @Column(name = "id_asentamiento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsentamiento;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "id_municipio")
    private Integer idMunicipio;

    @Column(name = "codigo_postal")
    private Integer codigoPostal;

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getIdAsentamiento() {
        return idAsentamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

}
