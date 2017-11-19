
package mx.gob.saludtlax.rh.wsdl.sifoficial;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for consulta_beneficiariosResponse_arr_beneficiarios complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="consulta_beneficiariosResponse_arr_beneficiarios">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="element" maxOccurs="unbounded" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id_beneficiario" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
 *                   &lt;element name="rfc" type="{http://www.w3.org/2001/XMLSchema}string" form="unqualified"/>
 *                   &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" form="unqualified"/>
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
@XmlType(name = "consulta_beneficiariosResponse_arr_beneficiarios", propOrder = { "element" })
public class ConsultaBeneficiariosResponseArrBeneficiarios {

    @XmlElement(required = true, nillable = true)
    protected List<ConsultaBeneficiariosResponseArrBeneficiarios.Element> element;

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
     * {@link ConsultaBeneficiariosResponseArrBeneficiarios.Element }
     *
     *
     */
    public List<ConsultaBeneficiariosResponseArrBeneficiarios.Element> getElement() {
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
     *         &lt;element name="id_beneficiario" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
     *         &lt;element name="rfc" type="{http://www.w3.org/2001/XMLSchema}string" form="unqualified"/>
     *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" form="unqualified"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "idBeneficiario", "rfc", "nombre" })
    public static class Element {

        @XmlElement(name = "id_beneficiario", required = true, type = Integer.class, nillable = true)
        protected Integer idBeneficiario;
        @XmlElement(required = true, nillable = true)
        protected String rfc;
        @XmlElement(required = true, nillable = true)
        protected String nombre;

        /**
         * Gets the value of the idBeneficiario property.
         *
         * @return
         *         possible object is
         *         {@link Integer }
         *
         */
        public Integer getIdBeneficiario() {
            return idBeneficiario;
        }

        /**
         * Sets the value of the idBeneficiario property.
         *
         * @param value
         *            allowed object is
         *            {@link Integer }
         *
         */
        public void setIdBeneficiario(Integer value) {
            idBeneficiario = value;
        }

        /**
         * Gets the value of the rfc property.
         *
         * @return
         *         possible object is
         *         {@link String }
         *
         */
        public String getRfc() {
            return rfc;
        }

        /**
         * Sets the value of the rfc property.
         *
         * @param value
         *            allowed object is
         *            {@link String }
         *
         */
        public void setRfc(String value) {
            rfc = value;
        }

        /**
         * Gets the value of the nombre property.
         *
         * @return
         *         possible object is
         *         {@link String }
         *
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * Sets the value of the nombre property.
         *
         * @param value
         *            allowed object is
         *            {@link String }
         *
         */
        public void setNombre(String value) {
            nombre = value;
        }

    }

}
