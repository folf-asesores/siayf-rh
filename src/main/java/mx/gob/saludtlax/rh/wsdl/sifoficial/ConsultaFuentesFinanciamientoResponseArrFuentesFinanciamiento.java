
package mx.gob.saludtlax.rh.wsdl.sifoficial;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for consulta_fuentes_financiamientoResponse_arr_fuentes_financiamiento complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="consulta_fuentes_financiamientoResponse_arr_fuentes_financiamiento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="element" maxOccurs="unbounded" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id_fuente_financiamiento" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
 *                   &lt;element name="id_base_36" type="{http://tempuri.org/}consulta_fuentes_financiamientoResponse_arr_fuentes_financiamiento_element_id_base_36" form="unqualified"/>
 *                   &lt;element name="descripcion" type="{http://tempuri.org/}consulta_fuentes_financiamientoResponse_arr_fuentes_financiamiento_element_descripcion" form="unqualified"/>
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
@XmlType(name = "consulta_fuentes_financiamientoResponse_arr_fuentes_financiamiento", propOrder = {
        "element" })
public class ConsultaFuentesFinanciamientoResponseArrFuentesFinanciamiento {

    @XmlElement(required = true, nillable = true)
    protected List<ConsultaFuentesFinanciamientoResponseArrFuentesFinanciamiento.Element> element;

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
     *
     * <pre>
     * getElement().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConsultaFuentesFinanciamientoResponseArrFuentesFinanciamiento.Element }
     *
     *
     */
    public List<ConsultaFuentesFinanciamientoResponseArrFuentesFinanciamiento.Element> getElement() {
        if (element == null) {
            element = new ArrayList<>();
        }
        return element;
    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="id_fuente_financiamiento" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
     *         &lt;element name="id_base_36" type="{http://tempuri.org/}consulta_fuentes_financiamientoResponse_arr_fuentes_financiamiento_element_id_base_36" form="unqualified"/>
     *         &lt;element name="descripcion" type="{http://tempuri.org/}consulta_fuentes_financiamientoResponse_arr_fuentes_financiamiento_element_descripcion" form="unqualified"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "idFuenteFinanciamiento", "idBase36",
            "descripcion" })
    public static class Element {

        @XmlElement(name = "id_fuente_financiamiento", required = true, type = Integer.class, nillable = true)
        protected Integer idFuenteFinanciamiento;
        @XmlElement(name = "id_base_36", required = true, nillable = true)
        protected String idBase36;
        @XmlElement(required = true, nillable = true)
        protected String descripcion;

        /**
         * Gets the value of the idFuenteFinanciamiento property.
         *
         * @return
         *         possible object is
         *         {@link Integer }
         *
         */
        public Integer getIdFuenteFinanciamiento() {
            return idFuenteFinanciamiento;
        }

        /**
         * Sets the value of the idFuenteFinanciamiento property.
         *
         * @param value
         *            allowed object is
         *            {@link Integer }
         *
         */
        public void setIdFuenteFinanciamiento(Integer value) {
            idFuenteFinanciamiento = value;
        }

        /**
         * Gets the value of the idBase36 property.
         *
         * @return
         *         possible object is
         *         {@link String }
         *
         */
        public String getIdBase36() {
            return idBase36;
        }

        /**
         * Sets the value of the idBase36 property.
         *
         * @param value
         *            allowed object is
         *            {@link String }
         *
         */
        public void setIdBase36(String value) {
            idBase36 = value;
        }

        /**
         * Gets the value of the descripcion property.
         *
         * @return
         *         possible object is
         *         {@link String }
         *
         */
        public String getDescripcion() {
            return descripcion;
        }

        /**
         * Sets the value of the descripcion property.
         *
         * @param value
         *            allowed object is
         *            {@link String }
         *
         */
        public void setDescripcion(String value) {
            descripcion = value;
        }

    }

}
