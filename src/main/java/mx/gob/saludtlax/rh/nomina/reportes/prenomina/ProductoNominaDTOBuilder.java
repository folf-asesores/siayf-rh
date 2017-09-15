/*
 * ProductoNominaDTOBuilder.java
 * Creado el 28/Jun/2017 11:36:55 AM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.util.Date;
import java.util.Map;

public class ProductoNominaDTOBuilder {

    private Integer idProductoNomina;
    private Date fechaPago;
    private Map<Integer, ProgramaDTO> programas;
    private String nombreElaboro;
    private String cargoElaboro;
    private String nombreReviso;
    private String cargoReviso;
    private String nombreAutorizo;
    private String cargoAutorizo;

    public ProductoNominaDTOBuilder(Integer idProductoNomina, Date fechaPago, Map<Integer, ProgramaDTO> programas) {
        this.idProductoNomina = idProductoNomina;
        this.fechaPago = fechaPago;
        this.programas = programas;
        nombreElaboro = null;
        cargoElaboro = null;
        nombreReviso = null;
        cargoReviso = null;
        nombreAutorizo = null;
        cargoAutorizo = null;
    }

    public ProductoNominaDTOBuilder setIdProductoNomina(Integer idProductoNomina) {
        this.idProductoNomina = idProductoNomina;
        return this;
    }

    public ProductoNominaDTOBuilder setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
        return this;
    }

    public ProductoNominaDTOBuilder setProgramas(Map<Integer, ProgramaDTO> programas) {
        this.programas = programas;
        return this;
    }

    public ProductoNominaDTOBuilder setNombreElaboro(String nombreElaboro) {
        this.nombreElaboro = nombreElaboro;
        return this;
    }

    public ProductoNominaDTOBuilder setCargoElaboro(String cargoElaboro) {
        this.cargoElaboro = cargoElaboro;
        return this;
    }

    public ProductoNominaDTOBuilder setNombreReviso(String nombreReviso) {
        this.nombreReviso = nombreReviso;
        return this;
    }

    public ProductoNominaDTOBuilder setCargoReviso(String cargoReviso) {
        this.cargoReviso = cargoReviso;
        return this;
    }

    public ProductoNominaDTOBuilder setNombreAutorizo(String nombreAutorizo) {
        this.nombreAutorizo = nombreAutorizo;
        return this;
    }

    public ProductoNominaDTOBuilder setCargoAutorizo(String cargoAutorizo) {
        this.cargoAutorizo = cargoAutorizo;
        return this;
    }

    public ProductoNominaDTO createProductoNominaDTO() {
        return new ProductoNominaDTO(idProductoNomina, fechaPago, programas, nombreElaboro, cargoElaboro, nombreReviso, cargoReviso, nombreAutorizo, cargoAutorizo);
    }

}
