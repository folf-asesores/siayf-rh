
package mx.gob.saludtlax.rh.wsdl.sifoficial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for consulta_presupuestoResponse_arr_presupuesto complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="consulta_presupuestoResponse_arr_presupuesto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="element" maxOccurs="unbounded" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id_mes" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
 *                   &lt;element name="por_ejercer" type="{http://tempuri.org/}consulta_presupuestoResponse_arr_presupuesto_element_por_ejercer" form="unqualified"/>
 *                   &lt;element name="comprometido" type="{http://tempuri.org/}consulta_presupuestoResponse_arr_presupuesto_element_comprometido" form="unqualified"/>
 *                   &lt;element name="devengado" type="{http://tempuri.org/}consulta_presupuestoResponse_arr_presupuesto_element_devengado" form="unqualified"/>
 *                   &lt;element name="ejercido" type="{http://tempuri.org/}consulta_presupuestoResponse_arr_presupuesto_element_ejercido" form="unqualified"/>
 *                   &lt;element name="pagado" type="{http://tempuri.org/}consulta_presupuestoResponse_arr_presupuesto_element_pagado" form="unqualified"/>
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
@XmlType(name = "consulta_presupuestoResponse_arr_presupuesto", propOrder = { "element" })
public class ConsultaPresupuestoResponseArrPresupuesto {

    @XmlElement(required = true, nillable = true)
    protected List<ConsultaPresupuestoResponseArrPresupuesto.Element> element;

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
     * {@link ConsultaPresupuestoResponseArrPresupuesto.Element }
     *
     *
     */
    public List<ConsultaPresupuestoResponseArrPresupuesto.Element> getElement() {
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
     *         &lt;element name="id_mes" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
     *         &lt;element name="por_ejercer" type="{http://tempuri.org/}consulta_presupuestoResponse_arr_presupuesto_element_por_ejercer" form="unqualified"/>
     *         &lt;element name="comprometido" type="{http://tempuri.org/}consulta_presupuestoResponse_arr_presupuesto_element_comprometido" form="unqualified"/>
     *         &lt;element name="devengado" type="{http://tempuri.org/}consulta_presupuestoResponse_arr_presupuesto_element_devengado" form="unqualified"/>
     *         &lt;element name="ejercido" type="{http://tempuri.org/}consulta_presupuestoResponse_arr_presupuesto_element_ejercido" form="unqualified"/>
     *         &lt;element name="pagado" type="{http://tempuri.org/}consulta_presupuestoResponse_arr_presupuesto_element_pagado" form="unqualified"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "idMes", "porEjercer", "comprometido", "devengado", "ejercido", "pagado" })
    public static class Element {

        @XmlElement(name = "id_mes", required = true, type = Integer.class, nillable = true)
        protected Integer idMes;
        @XmlElement(name = "por_ejercer", required = true, nillable = true)
        protected BigDecimal porEjercer;
        @XmlElement(required = true, nillable = true)
        protected BigDecimal comprometido;
        @XmlElement(required = true, nillable = true)
        protected BigDecimal devengado;
        @XmlElement(required = true, nillable = true)
        protected BigDecimal ejercido;
        @XmlElement(required = true, nillable = true)
        protected BigDecimal pagado;

        /**
         * Gets the value of the idMes property.
         *
         * @return
         *         possible object is
         *         {@link Integer }
         *
         */
        public Integer getIdMes() {
            return idMes;
        }

        /**
         * Sets the value of the idMes property.
         *
         * @param value
         *            allowed object is
         *            {@link Integer }
         *
         */
        public void setIdMes(Integer value) {
            idMes = value;
        }

        /**
         * Gets the value of the porEjercer property.
         *
         * @return
         *         possible object is
         *         {@link BigDecimal }
         *
         */
        public BigDecimal getPorEjercer() {
            return porEjercer;
        }

        /**
         * Sets the value of the porEjercer property.
         *
         * @param value
         *            allowed object is
         *            {@link BigDecimal }
         *
         */
        public void setPorEjercer(BigDecimal value) {
            porEjercer = value;
        }

        /**
         * Gets the value of the comprometido property.
         *
         * @return
         *         possible object is
         *         {@link BigDecimal }
         *
         */
        public BigDecimal getComprometido() {
            return comprometido;
        }

        /**
         * Sets the value of the comprometido property.
         *
         * @param value
         *            allowed object is
         *            {@link BigDecimal }
         *
         */
        public void setComprometido(BigDecimal value) {
            comprometido = value;
        }

        /**
         * Gets the value of the devengado property.
         *
         * @return
         *         possible object is
         *         {@link BigDecimal }
         *
         */
        public BigDecimal getDevengado() {
            return devengado;
        }

        /**
         * Sets the value of the devengado property.
         *
         * @param value
         *            allowed object is
         *            {@link BigDecimal }
         *
         */
        public void setDevengado(BigDecimal value) {
            devengado = value;
        }

        /**
         * Gets the value of the ejercido property.
         *
         * @return
         *         possible object is
         *         {@link BigDecimal }
         *
         */
        public BigDecimal getEjercido() {
            return ejercido;
        }

        /**
         * Sets the value of the ejercido property.
         *
         * @param value
         *            allowed object is
         *            {@link BigDecimal }
         *
         */
        public void setEjercido(BigDecimal value) {
            ejercido = value;
        }

        /**
         * Gets the value of the pagado property.
         *
         * @return
         *         possible object is
         *         {@link BigDecimal }
         *
         */
        public BigDecimal getPagado() {
            return pagado;
        }

        /**
         * Sets the value of the pagado property.
         *
         * @param value
         *            allowed object is
         *            {@link BigDecimal }
         *
         */
        public void setPagado(BigDecimal value) {
            pagado = value;
        }

    }

}
