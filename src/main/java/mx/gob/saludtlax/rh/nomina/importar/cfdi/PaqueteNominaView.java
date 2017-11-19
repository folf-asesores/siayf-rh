
package mx.gob.saludtlax.rh.nomina.importar.cfdi;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
public class PaqueteNominaView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8193019890755934437L;

    private String id;

    private Integer totalRegistros;

    private List<ComprobanteNominaView> comprobanteNominaView;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(Integer totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public List<ComprobanteNominaView> getComprobanteNominaView() {
        return comprobanteNominaView;
    }

    public void setComprobanteNominaView(List<ComprobanteNominaView> comprobanteNominaView) {
        this.comprobanteNominaView = comprobanteNominaView;
    }

}
