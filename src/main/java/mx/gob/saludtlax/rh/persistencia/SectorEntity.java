package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sectores")
public class SectorEntity implements Serializable {

    private static final long serialVersionUID = 3049706593709307279L;

    @Id
    @Column(name = "id_sector")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSector;

    @Column(name = "sector")
    private String sector;

    public Integer getIdSector() {
        return idSector;
    }

    public void setIdSector(Integer idSectores) {
        this.idSector = idSectores;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
