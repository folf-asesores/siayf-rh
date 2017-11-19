
package mx.gob.saludtlax.rh.wsdl.sifoficial;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for consulta_partidasResponse_arr_partidas complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="consulta_partidasResponse_arr_partidas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="element" maxOccurs="unbounded" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id_capitulo" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
 *                   &lt;element name="id_partida" type="{http://tempuri.org/}consulta_partidasResponse_arr_partidas_element_id_partida" form="unqualified"/>
 *                   &lt;element name="descripcion" type="{http://tempuri.org/}consulta_partidasResponse_arr_partidas_element_descripcion" form="unqualified"/>
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
@XmlType(name = "consulta_partidasResponse_arr_partidas", propOrder = {
        "element" })
public class ConsultaPartidasResponseArrPartidas {

    @XmlElement(required = true, nillable = true)
    protected List<ConsultaPartidasResponseArrPartidas.Element> element;

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
     * {@link ConsultaPartidasResponseArrPartidas.Element }
     *
     *
     */
    public List<ConsultaPartidasResponseArrPartidas.Element> getElement() {
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
     *         &lt;element name="id_capitulo" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
     *         &lt;element name="id_partida" type="{http://tempuri.org/}consulta_partidasResponse_arr_partidas_element_id_partida" form="unqualified"/>
     *         &lt;element name="descripcion" type="{http://tempuri.org/}consulta_partidasResponse_arr_partidas_element_descripcion" form="unqualified"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "idCapitulo", "idPartida",
            "descripcion" })
    public static class Element {

        @XmlElement(name = "id_capitulo", required = true, type = Integer.class, nillable = true)
        protected Integer idCapitulo;
        @XmlElement(name = "id_partida", required = true, nillable = true)
        protected String idPartida;
        @XmlElement(required = true, nillable = true)
        protected String descripcion;

        /**
         * Gets the value of the idCapitulo property.
         *
         * @return
         *         possible object is
         *         {@link Integer }
         *
         */
        public Integer getIdCapitulo() {
            return idCapitulo;
        }

        /**
         * Sets the value of the idCapitulo property.
         *
         * @param value
         *            allowed object is
         *            {@link Integer }
         *
         */
        public void setIdCapitulo(Integer value) {
            idCapitulo = value;
        }

        /**
         * Gets the value of the idPartida property.
         *
         * @return
         *         possible object is
         *         {@link String }
         *
         */
        public String getIdPartida() {
            return idPartida;
        }

        /**
         * Sets the value of the idPartida property.
         *
         * @param value
         *            allowed object is
         *            {@link String }
         *
         */
        public void setIdPartida(String value) {
            idPartida = value;
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
