
package mx.gob.saludtlax.rh.wsdl.sifoficial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for insercion_tramite_arr_detalle_tramite complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="insercion_tramite_arr_detalle_tramite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="element" maxOccurs="unbounded" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id_proyecto" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
 *                   &lt;element name="partida" type="{http://tempuri.org/}insercion_tramite_arr_detalle_tramite_element_partida" form="unqualified"/>
 *                   &lt;element name="importe" type="{http://tempuri.org/}insercion_tramite_arr_detalle_tramite_element_importe" form="unqualified"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insercion_tramite_arr_detalle_tramite", propOrder = {
    "element"
})
public class InsercionTramiteArrDetalleTramite {

    @XmlElement(required = true, nillable = true)
    protected List<InsercionTramiteArrDetalleTramite.Element> element;

    /**
     * Gets the value of the element property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the element property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InsercionTramiteArrDetalleTramite.Element }
     * 
     * 
     */
    public List<InsercionTramiteArrDetalleTramite.Element> getElement() {
        if (element == null) {
            element = new ArrayList<InsercionTramiteArrDetalleTramite.Element>();
        }
        return this.element;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="id_proyecto" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
     *         &lt;element name="partida" type="{http://tempuri.org/}insercion_tramite_arr_detalle_tramite_element_partida" form="unqualified"/>
     *         &lt;element name="importe" type="{http://tempuri.org/}insercion_tramite_arr_detalle_tramite_element_importe" form="unqualified"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "idProyecto",
        "partida",
        "importe"
    })
    public static class Element {

        @XmlElement(name = "id_proyecto", required = true, type = Integer.class, nillable = true)
        protected Integer idProyecto;
        @XmlElement(required = true, nillable = true)
        protected String partida;
        @XmlElement(required = true, nillable = true)
        protected BigDecimal importe;

        /**
         * Gets the value of the idProyecto property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getIdProyecto() {
            return idProyecto;
        }

        /**
         * Sets the value of the idProyecto property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setIdProyecto(Integer value) {
            this.idProyecto = value;
        }

        /**
         * Gets the value of the partida property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPartida() {
            return partida;
        }

        /**
         * Sets the value of the partida property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPartida(String value) {
            this.partida = value;
        }

        /**
         * Gets the value of the importe property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getImporte() {
            return importe;
        }

        /**
         * Sets the value of the importe property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setImporte(BigDecimal value) {
            this.importe = value;
        }

    }

}
