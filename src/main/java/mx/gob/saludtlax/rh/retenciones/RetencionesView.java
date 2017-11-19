
package mx.gob.saludtlax.rh.retenciones;

import java.io.Serializable;

/**
 *
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
public class RetencionesView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6750676040026839901L;

    private String xml;

    private String cadenaOriginal;

    private String sello;

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    public void setCadenaOriginal(String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    public String getSello() {
        return sello;
    }

    public void setSello(String sello) {
        this.sello = sello;
    }

}
