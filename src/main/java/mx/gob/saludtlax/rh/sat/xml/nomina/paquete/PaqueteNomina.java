
package mx.gob.saludtlax.rh.sat.xml.nomina.paquete;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "PaqueteNomina")
@XmlType(name = "", propOrder = { "controlComprobanteNomina" })
@XmlAccessorType(XmlAccessType.FIELD)
public class PaqueteNomina implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -342644658644408621L;

    @XmlAttribute(name = "id")
    protected String id;

    @XmlAttribute(name = "totalregs")
    protected Integer totalRegistros;

    @XmlElement(name = "ControlComprobante")
    protected List<PaqueteNomina.ControlComprobante> controlComprobanteNomina;

    public List<PaqueteNomina.ControlComprobante> getControlComprobanteNomina() {
        return controlComprobanteNomina;
    }

    public void setControlComprobanteNomina(
            List<PaqueteNomina.ControlComprobante> controlComprobanteNomina) {
        this.controlComprobanteNomina = controlComprobanteNomina;
    }

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

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ControlComprobante {

        @XmlAttribute(name = "id")
        protected Integer id;

        @XmlAttribute(name = "Num")
        protected Integer num;

        @XmlElement(name = "xmlComp")
        protected String xmlComp;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public String getXmlComp() {
            return xmlComp;
        }

        public void setXmlComp(String xmlComp) {
            this.xmlComp = xmlComp;
        }

    }

}
