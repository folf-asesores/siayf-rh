/*
 * ProductoNomina.java
 * Creado el 27/Jun/2017 3:14:50 PM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class ProductoNomina implements Iterable<Programa> {

    private final Integer idProductoNomina;
    private String quincena;
    private final Date fechaPago;
    private final List<Programa> programas;
    private final String nombreElaboro;
    private final String cargoElaboro;
    private final String nombreReviso;
    private final String cargoReviso;
    private final String nombreAutorizo;
    private final String cargoAutorizo;

    public ProductoNomina(Integer idProductoNomina, Date fechaPago, List<Programa> programas, String nombreElaboro, String cargoElaboro, String nombreReviso, String cargoReviso, String nombreAutorizo, String cargoAutorizo) {
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

    public List<Programa> getProgramas() {
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
    public Iterator<Programa> iterator() {
        return programas.iterator();
    }
}
