/*
 *
 */

package mx.gob.saludtlax.rh.puestosautorizados;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 26/01/2017 21:35:19
 */
public class ResumenPuestoDTO {

    private long total;
    private String puesto;
    private String codigo;

    public ResumenPuestoDTO(long total, String puesto, String codigo) {

        this.total = total;
        this.puesto = puesto;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}
