
package mx.gob.saludtlax.rh.empleados.suplencia.reportes;

/**
 *
 * @author José Pablo
 *
 */
public class ReporteCausalGlobalDTO {
    private Integer numeroQuincena;
    private Integer eFiscal;
    private Integer idCentroResponsabilidad;
    private String lugar;

    public Integer getNumeroQuincena() {
        return numeroQuincena;
    }

    public void setNumeroQuincena(Integer numeroQuincena) {
        this.numeroQuincena = numeroQuincena;
    }

    public Integer geteFiscal() {
        return eFiscal;
    }

    public void seteFiscal(Integer eFiscal) {
        this.eFiscal = eFiscal;
    }

    public Integer getCentroResponsabilidad() {
        return idCentroResponsabilidad;
    }

    public void setCentroResponsabilidad(Integer centroResponsabilidad) {
        idCentroResponsabilidad = centroResponsabilidad;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

}
