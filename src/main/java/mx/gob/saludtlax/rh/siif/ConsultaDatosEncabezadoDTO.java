
package mx.gob.saludtlax.rh.siif;

import java.math.BigDecimal;

public class ConsultaDatosEncabezadoDTO {

    private String rfc;
    private String nombre;
    private Integer idNomina;
    private Integer id_empleado_datos_laborales;
    private String noChequeCuenta;
    private String ur;
    private String fn;
    private String cr;
    private String id_puesto;
    private BigDecimal percepciones;
    private BigDecimal deducciones;
    private BigDecimal neto;
    private Integer id_empleado_datos_personales;

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(Integer idNomina) {
        this.idNomina = idNomina;
    }

    public Integer getId_empleado_datos_laborales() {
        return id_empleado_datos_laborales;
    }

    public void setId_empleado_datos_laborales(
            Integer id_empleado_datos_laborales) {
        this.id_empleado_datos_laborales = id_empleado_datos_laborales;
    }

    public String getNoChequeCuenta() {
        return noChequeCuenta;
    }

    public void setNoChequeCuenta(String noChequeCuenta) {
        this.noChequeCuenta = noChequeCuenta;
    }

    public String getUr() {
        return ur;
    }

    public void setUr(String ur) {
        this.ur = ur;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getCr() {
        return cr;
    }

    public void setCr(String cr) {
        this.cr = cr;
    }

    public String getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(String id_puesto) {
        this.id_puesto = id_puesto;
    }

    public BigDecimal getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(BigDecimal percepciones) {
        this.percepciones = percepciones;
    }

    public BigDecimal getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(BigDecimal deducciones) {
        this.deducciones = deducciones;
    }

    public BigDecimal getNeto() {
        return neto;
    }

    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

    public Integer getId_empleado_datos_personales() {
        return id_empleado_datos_personales;
    }

    public void setId_empleado_datos_personales(
            Integer id_empleado_datos_personales) {
        this.id_empleado_datos_personales = id_empleado_datos_personales;
    }

}
