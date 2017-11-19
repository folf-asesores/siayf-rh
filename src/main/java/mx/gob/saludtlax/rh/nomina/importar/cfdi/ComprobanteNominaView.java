
package mx.gob.saludtlax.rh.nomina.importar.cfdi;

import java.io.Serializable;

import mx.gob.saludtlax.rh.sat.xml.cfdi.Comprobante;
import mx.gob.saludtlax.rh.sat.xml.nomina.Nomina;
import mx.gob.saludtlax.rh.sat.xml.nomina12.Nomina12;
import mx.gob.saludtlax.rh.sat.xml.timbre.TimbreFiscalDigital;

/**
 *
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
public class ComprobanteNominaView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8110591705973112010L;

    private Integer num;

    private Integer id;

    private Comprobante comprobanteCFDI;

    private mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante comprobanteCFDI12;

    private Nomina complementoNomina;

    private Nomina12 complementoNomina12;

    private TimbreFiscalDigital complementoTimbre;

    private String comprobanteXML;

    private String cadenaOriginal;

    public mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante getComprobanteCFDI12() {
        return comprobanteCFDI12;
    }

    public void setComprobanteCFDI12(
            mx.gob.saludtlax.rh.sat.xml.cfdi12.Comprobante comprobanteCFDI12) {
        this.comprobanteCFDI12 = comprobanteCFDI12;
    }

    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    public void setCadenaOriginal(String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comprobante getComprobanteCFDI() {
        return comprobanteCFDI;
    }

    public void setComprobanteCFDI(Comprobante comprobanteCFDI) {
        this.comprobanteCFDI = comprobanteCFDI;
    }

    public Nomina getComplementoNomina() {
        return complementoNomina;
    }

    public void setComplementoNomina(Nomina complementoNomina) {
        this.complementoNomina = complementoNomina;
    }

    public TimbreFiscalDigital getComplementoTimbre() {
        return complementoTimbre;
    }

    public void setComplementoTimbre(TimbreFiscalDigital complementoTimbre) {
        this.complementoTimbre = complementoTimbre;
    }

    public String getComprobanteXML() {
        return comprobanteXML;
    }

    public void setComprobanteXML(String comprobanteXML) {
        this.comprobanteXML = comprobanteXML;
    }

    public Nomina12 getComplementoNomina12() {
        return complementoNomina12;
    }

    public void setComplementoNomina12(Nomina12 complementoNomina12) {
        this.complementoNomina12 = complementoNomina12;
    }

}
