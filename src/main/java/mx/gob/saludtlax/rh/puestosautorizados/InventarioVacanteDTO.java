/*
 *
 */

package mx.gob.saludtlax.rh.puestosautorizados;

/**
 * @author Leila Schiaffini Ehuan
 * @since 08/08/2016 17:13:27
 *
 */
public class InventarioVacanteDTO {

    private long total;
    private long totalDisponibles;
    private long totalInterinatos;
    private long totalActivos;
    private long totalPermiso;
    private String tipoContratacion;
    private Integer idTipoContratacion;

    public InventarioVacanteDTO(long total, String tipoContratacion, Integer idTipoContratacion) {

        this.total = total;
        this.tipoContratacion = tipoContratacion;
        this.idTipoContratacion = idTipoContratacion;
    }

    public InventarioVacanteDTO(long total, Integer idTipoContratacion) {

        this.total = total;
        this.idTipoContratacion = idTipoContratacion;
    }

    public long getTotalActivos() {
        return totalActivos;
    }

    public void setTotalActivos(long totalActivos) {
        this.totalActivos = totalActivos;
    }

    public long getTotalPermiso() {
        return totalPermiso;
    }

    public void setTotalPermiso(long totalPermiso) {
        this.totalPermiso = totalPermiso;
    }

    public long getTotalInterinatos() {
        return totalInterinatos;
    }

    public void setTotalInterinatos(long totalInterinatos) {
        this.totalInterinatos = totalInterinatos;
    }

    public long getTotalDisponibles() {
        return totalDisponibles;
    }

    public void setTotalDisponibles(long totalDisponibles) {
        this.totalDisponibles = totalDisponibles;
    }

    public Integer getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Integer idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

}
