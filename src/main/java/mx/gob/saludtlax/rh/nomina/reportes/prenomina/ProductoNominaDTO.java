/*
 * ProductoNominaDTO.java
 * Creado el 27/Jun/2017 3:14:50 PM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class ProductoNominaDTO implements Iterable<ProgramaDTO> {

    private final Integer idProductoNomina;
    private String quincena;
    private final Date fechaPago;
    private final Map<Integer, ProgramaDTO> programas;
    private final String nombreElaboro;
    private final String cargoElaboro;
    private final String nombreReviso;
    private final String cargoReviso;
    private final String nombreAutorizo;
    private final String cargoAutorizo;

    public ProductoNominaDTO(Integer idProductoNomina, Date fechaPago, Map<Integer, ProgramaDTO> programas, String nombreElaboro, String cargoElaboro, String nombreReviso, String cargoReviso, String nombreAutorizo, String cargoAutorizo) {
        this.idProductoNomina = idProductoNomina;
        this.fechaPago = fechaPago;
        this.programas = programas;
        this.nombreElaboro = nombreElaboro;
        this.cargoElaboro = cargoElaboro;
        this.nombreReviso = nombreReviso;
        this.cargoReviso = cargoReviso;
        this.nombreAutorizo = nombreAutorizo;
        this.cargoAutorizo = cargoAutorizo;
    }

    public Integer getIdProductoNomina() {
        return idProductoNomina;
    }

    public String getQuincena() {
        if (quincena == null && fechaPago != null) {
            String dia = new SimpleDateFormat("dd").format(fechaPago);
            quincena = Integer.valueOf(dia) < 15 ? "1A" : "2A";
        }

        return quincena;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public Map<Integer, ProgramaDTO> getProgramas() {
        return programas;
    }

    public String getNombreElaboro() {
        return nombreElaboro;
    }

    public String getCargoElaboro() {
        return cargoElaboro;
    }

    public String getNombreReviso() {
        return nombreReviso;
    }

    public String getCargoReviso() {
        return cargoReviso;
    }

    public String getNombreAutorizo() {
        return nombreAutorizo;
    }

    public String getCargoAutorizo() {
        return cargoAutorizo;
    }

    @Override
    public Iterator<ProgramaDTO> iterator() {
        return programas.values().iterator();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.idProductoNomina);
        hash = 83 * hash + Objects.hashCode(this.quincena);
        hash = 83 * hash + Objects.hashCode(this.fechaPago);
        hash = 83 * hash + Objects.hashCode(this.programas);
        hash = 83 * hash + Objects.hashCode(this.nombreElaboro);
        hash = 83 * hash + Objects.hashCode(this.cargoElaboro);
        hash = 83 * hash + Objects.hashCode(this.nombreReviso);
        hash = 83 * hash + Objects.hashCode(this.cargoReviso);
        hash = 83 * hash + Objects.hashCode(this.nombreAutorizo);
        hash = 83 * hash + Objects.hashCode(this.cargoAutorizo);
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
        final ProductoNominaDTO other = (ProductoNominaDTO) obj;
        if (!Objects.equals(this.quincena, other.quincena)) {
            return false;
        }
        if (!Objects.equals(this.nombreElaboro, other.nombreElaboro)) {
            return false;
        }
        if (!Objects.equals(this.cargoElaboro, other.cargoElaboro)) {
            return false;
        }
        if (!Objects.equals(this.nombreReviso, other.nombreReviso)) {
            return false;
        }
        if (!Objects.equals(this.cargoReviso, other.cargoReviso)) {
            return false;
        }
        if (!Objects.equals(this.nombreAutorizo, other.nombreAutorizo)) {
            return false;
        }
        if (!Objects.equals(this.cargoAutorizo, other.cargoAutorizo)) {
            return false;
        }
        if (!Objects.equals(this.idProductoNomina, other.idProductoNomina)) {
            return false;
        }
        if (!Objects.equals(this.fechaPago, other.fechaPago)) {
            return false;
        }
        return Objects.equals(this.programas, other.programas);
    }

    @Override
    public String toString() {
        return "ProductoNomina{" + "idProductoNomina=" + idProductoNomina + ", quincena=" + getQuincena() + ", fechaPago=" + fechaPago + ", programas=" + programas + ", nombreElaboro=" + nombreElaboro + ", cargoElaboro=" + cargoElaboro + ", nombreReviso=" + nombreReviso + ", cargoReviso=" + cargoReviso + ", nombreAutorizo=" + nombreAutorizo + ", cargoAutorizo=" + cargoAutorizo + '}';
    }

}
