/*
 *
 * SectorDTO.java
 * Creado el Jul 27, 2016 12:24:06 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.sector;

import java.io.Serializable;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class SectorDTO implements Serializable {

    private static final long serialVersionUID = 7662687092010171940L;

    private Integer idSectores;
    private String descripcion;

    public Integer getIdSectores() {
        return idSectores;
    }

    public void setIdSectores(Integer idSectores) {
        this.idSectores = idSectores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
