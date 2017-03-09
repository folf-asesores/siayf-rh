package mx.gob.saludtlax.rh.wsdl.sifoficial;



import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for consulta_conceptos_partida_nominaResponse_elemento_conversion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consulta_conceptos_partida_nominaResponse_elemento_conversion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="element" maxOccurs="unbounded" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id_concepto_partida_nomina" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
 *                   &lt;element name="id_concepto_nomina" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
 *                   &lt;element name="descripcion_concepto_nomina" type="{http://tempuri.org/}consulta_conceptos_partida_nominaResponse_elemento_conversion_element_descripcion_concepto_nomina" form="unqualified"/>
 *                   &lt;element name="id_nombramiento" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
 *                   &lt;element name="descripcion_nombramiento" type="{http://tempuri.org/}consulta_conceptos_partida_nominaResponse_elemento_conversion_element_descripcion_nombramiento" form="unqualified"/>
 *                   &lt;element name="id_partida" type="{http://tempuri.org/}consulta_conceptos_partida_nominaResponse_elemento_conversion_element_id_partida" form="unqualified"/>
 *                   &lt;element name="descripcion_partida" type="{http://tempuri.org/}consulta_conceptos_partida_nominaResponse_elemento_conversion_element_descripcion_partida" form="unqualified"/>
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
@XmlType(name = "consulta_conceptos_partida_nominaResponse_elemento_conversion", propOrder = {
    "element"
})
public class ConsultaConceptosPartidaNominaResponseElementoConversion {

    @XmlElement(required = true, nillable = true)
    protected List<ConsultaConceptosPartidaNominaResponseElementoConversion.Element> element;

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
     * {@link ConsultaConceptosPartidaNominaResponseElementoConversion.Element }
     * 
     * 
     */
    public List<ConsultaConceptosPartidaNominaResponseElementoConversion.Element> getElement() {
        if (element == null) {
            element = new ArrayList<ConsultaConceptosPartidaNominaResponseElementoConversion.Element>();
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
     *         &lt;element name="id_concepto_partida_nomina" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
     *         &lt;element name="id_concepto_nomina" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
     *         &lt;element name="descripcion_concepto_nomina" type="{http://tempuri.org/}consulta_conceptos_partida_nominaResponse_elemento_conversion_element_descripcion_concepto_nomina" form="unqualified"/>
     *         &lt;element name="id_nombramiento" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
     *         &lt;element name="descripcion_nombramiento" type="{http://tempuri.org/}consulta_conceptos_partida_nominaResponse_elemento_conversion_element_descripcion_nombramiento" form="unqualified"/>
     *         &lt;element name="id_partida" type="{http://tempuri.org/}consulta_conceptos_partida_nominaResponse_elemento_conversion_element_id_partida" form="unqualified"/>
     *         &lt;element name="descripcion_partida" type="{http://tempuri.org/}consulta_conceptos_partida_nominaResponse_elemento_conversion_element_descripcion_partida" form="unqualified"/>
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
        "idConceptoPartidaNomina",
        "idConceptoNomina",
        "descripcionConceptoNomina",
        "idNombramiento",
        "descripcionNombramiento",
        "idPartida",
        "descripcionPartida"
    })
    public static class Element {

        @XmlElement(name = "id_concepto_partida_nomina", required = true, type = Integer.class, nillable = true)
        protected Integer idConceptoPartidaNomina;
        @XmlElement(name = "id_concepto_nomina", required = true, type = Integer.class, nillable = true)
        protected Integer idConceptoNomina;
        @XmlElement(name = "descripcion_concepto_nomina", required = true, nillable = true)
        protected String descripcionConceptoNomina;
        @XmlElement(name = "id_nombramiento", required = true, type = Integer.class, nillable = true)
        protected Integer idNombramiento;
        @XmlElement(name = "descripcion_nombramiento", required = true, nillable = true)
        protected String descripcionNombramiento;
        @XmlElement(name = "id_partida", required = true, nillable = true)
        protected String idPartida;
        @XmlElement(name = "descripcion_partida", required = true, nillable = true)
        protected String descripcionPartida;

        /**
         * Gets the value of the idConceptoPartidaNomina property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getIdConceptoPartidaNomina() {
            return idConceptoPartidaNomina;
        }

        /**
         * Sets the value of the idConceptoPartidaNomina property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setIdConceptoPartidaNomina(Integer value) {
            this.idConceptoPartidaNomina = value;
        }

        /**
         * Gets the value of the idConceptoNomina property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getIdConceptoNomina() {
            return idConceptoNomina;
        }

        /**
         * Sets the value of the idConceptoNomina property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setIdConceptoNomina(Integer value) {
            this.idConceptoNomina = value;
        }

        /**
         * Gets the value of the descripcionConceptoNomina property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionConceptoNomina() {
            return descripcionConceptoNomina;
        }

        /**
         * Sets the value of the descripcionConceptoNomina property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionConceptoNomina(String value) {
            this.descripcionConceptoNomina = value;
        }

        /**
         * Gets the value of the idNombramiento property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getIdNombramiento() {
            return idNombramiento;
        }

        /**
         * Sets the value of the idNombramiento property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setIdNombramiento(Integer value) {
            this.idNombramiento = value;
        }

        /**
         * Gets the value of the descripcionNombramiento property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionNombramiento() {
            return descripcionNombramiento;
        }

        /**
         * Sets the value of the descripcionNombramiento property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionNombramiento(String value) {
            this.descripcionNombramiento = value;
        }

        /**
         * Gets the value of the idPartida property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdPartida() {
            return idPartida;
        }

        /**
         * Sets the value of the idPartida property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdPartida(String value) {
            this.idPartida = value;
        }

        /**
         * Gets the value of the descripcionPartida property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionPartida() {
            return descripcionPartida;
        }

        /**
         * Sets the value of the descripcionPartida property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionPartida(String value) {
            this.descripcionPartida = value;
        }

    }

}
