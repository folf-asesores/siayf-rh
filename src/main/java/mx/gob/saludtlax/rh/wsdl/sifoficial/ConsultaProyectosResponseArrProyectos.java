
package mx.gob.saludtlax.rh.wsdl.sifoficial;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for consulta_proyectosResponse_arr_proyectos complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="consulta_proyectosResponse_arr_proyectos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="element" maxOccurs="unbounded" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id_dependencia" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
 *                   &lt;element name="id_unidad_responsable" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
 *                   &lt;element name="id_base_36" type="{http://tempuri.org/}consulta_proyectosResponse_arr_proyectos_element_id_base_36" form="unqualified"/>
 *                   &lt;element name="id_proyecto" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
 *                   &lt;element name="descripcion" type="{http://tempuri.org/}consulta_proyectosResponse_arr_proyectos_element_descripcion" form="unqualified"/>
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
@XmlType(name = "consulta_proyectosResponse_arr_proyectos", propOrder = { "element" })
public class ConsultaProyectosResponseArrProyectos {

    @XmlElement(required = true, nillable = true)
    protected List<ConsultaProyectosResponseArrProyectos.Element> element;

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
     * {@link ConsultaProyectosResponseArrProyectos.Element }
     *
     *
     */
    public List<ConsultaProyectosResponseArrProyectos.Element> getElement() {
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
     *         &lt;element name="id_dependencia" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
     *         &lt;element name="id_unidad_responsable" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
     *         &lt;element name="id_base_36" type="{http://tempuri.org/}consulta_proyectosResponse_arr_proyectos_element_id_base_36" form="unqualified"/>
     *         &lt;element name="id_proyecto" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
     *         &lt;element name="descripcion" type="{http://tempuri.org/}consulta_proyectosResponse_arr_proyectos_element_descripcion" form="unqualified"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "idDependencia", "idUnidadResponsable", "idBase36", "idProyecto", "descripcion" })
    public static class Element {

        @XmlElement(name = "id_dependencia", required = true, type = Integer.class, nillable = true)
        protected Integer idDependencia;
        @XmlElement(name = "id_unidad_responsable", required = true, type = Integer.class, nillable = true)
        protected Integer idUnidadResponsable;
        @XmlElement(name = "id_base_36", required = true, nillable = true)
        protected String idBase36;
        @XmlElement(name = "id_proyecto", required = true, type = Integer.class, nillable = true)
        protected Integer idProyecto;
        @XmlElement(required = true, nillable = true)
        protected String descripcion;

        /**
         * Gets the value of the idDependencia property.
         *
         * @return
         *         possible object is
         *         {@link Integer }
         *
         */
        public Integer getIdDependencia() {
            return idDependencia;
        }

        /**
         * Sets the value of the idDependencia property.
         *
         * @param value
         *            allowed object is
         *            {@link Integer }
         *
         */
        public void setIdDependencia(Integer value) {
            idDependencia = value;
        }

        /**
         * Gets the value of the idUnidadResponsable property.
         *
         * @return
         *         possible object is
         *         {@link Integer }
         *
         */
        public Integer getIdUnidadResponsable() {
            return idUnidadResponsable;
        }

        /**
         * Sets the value of the idUnidadResponsable property.
         *
         * @param value
         *            allowed object is
         *            {@link Integer }
         *
         */
        public void setIdUnidadResponsable(Integer value) {
            idUnidadResponsable = value;
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
         * Gets the value of the idProyecto property.
         *
         * @return
         *         possible object is
         *         {@link Integer }
         *
         */
        public Integer getIdProyecto() {
            return idProyecto;
        }

        /**
         * Sets the value of the idProyecto property.
         *
         * @param value
         *            allowed object is
         *            {@link Integer }
         *
         */
        public void setIdProyecto(Integer value) {
            idProyecto = value;
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
