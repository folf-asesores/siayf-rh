/*
 * ProductoNominaBuilder.java
 * Creado el 28/Jun/2017 11:36:55 AM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.util.Date;
import java.util.List;

public class ProductoNominaBuilder {

    private Integer idProductoNomina = null;
    private Date fechaPago = null;
    private List<Programa> programas = null;
    private String nombreElaboro = null;
    private String cargoElaboro = null;
    private String nombreReviso = null;
    private String cargoReviso = null;
    private String nombreAutorizo = null;
    private String cargoAutorizo = null;

    public ProductoNominaBuilder(Integer idProductoNomina, Date fechaPago, List<Programa> programas) {
        this.idProductoNomina = idProductoNomina;
        this.fechaPago = fechaPago;
        this.programas = programas;
    }

    public ProductoNominaBuilder setIdProductoNomina(Integer idProductoNomina) {
        this.idProductoNomina = idProductoNomina;
        return this;
    }

    public ProductoNominaBuilder setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
        return this;
    }

    public ProductoNominaBuilder setProgramas(List<Programa> programas) {
        this.programas = programas;
        return this;
    }

    public ProductoNominaBuilder setNombreElaboro(String nombreElaboro) {
        this.nombreElaboro = nombreElaboro;
        return this;
    }

    public ProductoNominaBuilder setCargoElaboro(String cargoElaboro) {
        this.cargoElaboro = cargoElaboro;
        return this;
    }

    public ProductoNominaBuilder setNombreReviso(String nombreReviso) {
        this.nombreReviso = nombreReviso;
        return this;
    }

    public ProductoNominaBuilder setCargoReviso(String cargoReviso) {
        this.cargoReviso = cargoReviso;
        return this;
    }

    public ProductoNominaBuilder setNombreAutorizo(String nombreAutorizo) {
        this.nombreAutorizo = nombreAutorizo;
        return this;
    }

    public ProductoNominaBuilder setCargoAutorizo(String cargoAutorizo) {
        this.cargoAutorizo = cargoAutorizo;
        return this;
    }

    public ProductoNomina createProductoNomina() {
        return new ProductoNomina(idProductoNomina, fechaPago, programas, nombreElaboro, cargoElaboro, nombreReviso, cargoReviso, nombreAutorizo, cargoAutorizo);
    }

}
