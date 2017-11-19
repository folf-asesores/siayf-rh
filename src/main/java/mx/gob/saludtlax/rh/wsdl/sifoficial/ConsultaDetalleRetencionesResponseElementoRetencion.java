
package mx.gob.saludtlax.rh.wsdl.sifoficial;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for consulta_detalle_retencionesResponse_elemento_retencion complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="consulta_detalle_retencionesResponse_elemento_retencion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="element" maxOccurs="unbounded" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id_detalle_retencion_cuenta_bancaria" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
 *                   &lt;element name="id_concepto_nomina" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
 *                   &lt;element name="descripcion_concepto_nomina" type="{http://tempuri.org/}consulta_detalle_retencionesResponse_elemento_retencion_element_descripcion_concepto_nomina" form="unqualified"/>
 *                   &lt;element name="cuenta_contable_retencion" type="{http://tempuri.org/}consulta_detalle_retencionesResponse_elemento_retencion_element_cuenta_contable_retencion" form="unqualified"/>
 *                   &lt;element name="descripcion_cuenta_contable" type="{http://tempuri.org/}consulta_detalle_retencionesResponse_elemento_retencion_element_descripcion_cuenta_contable" form="unqualified"/>
 *                   &lt;element name="cuenta_retencion" type="{http://tempuri.org/}consulta_detalle_retencionesResponse_elemento_retencion_element_cuenta_retencion" form="unqualified"/>
 *                   &lt;element name="cuenta_contable_banco" type="{http://tempuri.org/}consulta_detalle_retencionesResponse_elemento_retencion_element_cuenta_contable_banco" form="unqualified"/>
 *                   &lt;element name="descripcion_cuenta_banco" type="{http://tempuri.org/}consulta_detalle_retencionesResponse_elemento_retencion_element_descripcion_cuenta_banco" form="unqualified"/>
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
@XmlType(name = "consulta_detalle_retencionesResponse_elemento_retencion", propOrder = {
        "element" })
public class ConsultaDetalleRetencionesResponseElementoRetencion {

    @XmlElement(required = true, nillable = true)
    protected List<ConsultaDetalleRetencionesResponseElementoRetencion.Element> element;

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
     * {@link ConsultaDetalleRetencionesResponseElementoRetencion.Element }
     *
     *
     */
    public List<ConsultaDetalleRetencionesResponseElementoRetencion.Element> getElement() {
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
     *         &lt;element name="id_detalle_retencion_cuenta_bancaria" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
     *         &lt;element name="id_concepto_nomina" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/>
     *         &lt;element name="descripcion_concepto_nomina" type="{http://tempuri.org/}consulta_detalle_retencionesResponse_elemento_retencion_element_descripcion_concepto_nomina" form="unqualified"/>
     *         &lt;element name="cuenta_contable_retencion" type="{http://tempuri.org/}consulta_detalle_retencionesResponse_elemento_retencion_element_cuenta_contable_retencion" form="unqualified"/>
     *         &lt;element name="descripcion_cuenta_contable" type="{http://tempuri.org/}consulta_detalle_retencionesResponse_elemento_retencion_element_descripcion_cuenta_contable" form="unqualified"/>
     *         &lt;element name="cuenta_retencion" type="{http://tempuri.org/}consulta_detalle_retencionesResponse_elemento_retencion_element_cuenta_retencion" form="unqualified"/>
     *         &lt;element name="cuenta_contable_banco" type="{http://tempuri.org/}consulta_detalle_retencionesResponse_elemento_retencion_element_cuenta_contable_banco" form="unqualified"/>
     *         &lt;element name="descripcion_cuenta_banco" type="{http://tempuri.org/}consulta_detalle_retencionesResponse_elemento_retencion_element_descripcion_cuenta_banco" form="unqualified"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "idDetalleRetencionCuentaBancaria",
            "idConceptoNomina", "descripcionConceptoNomina",
            "cuentaContableRetencion", "descripcionCuentaContable",
            "cuentaRetencion", "cuentaContableBanco",
            "descripcionCuentaBanco" })
    public static class Element {

        @XmlElement(name = "id_detalle_retencion_cuenta_bancaria", required = true, type = Integer.class, nillable = true)
        protected Integer idDetalleRetencionCuentaBancaria;
        @XmlElement(name = "id_concepto_nomina", required = true, type = Integer.class, nillable = true)
        protected Integer idConceptoNomina;
        @XmlElement(name = "descripcion_concepto_nomina", required = true, nillable = true)
        protected String descripcionConceptoNomina;
        @XmlElement(name = "cuenta_contable_retencion", required = true, nillable = true)
        protected String cuentaContableRetencion;
        @XmlElement(name = "descripcion_cuenta_contable", required = true, nillable = true)
        protected String descripcionCuentaContable;
        @XmlElement(name = "cuenta_retencion", required = true, nillable = true)
        protected String cuentaRetencion;
        @XmlElement(name = "cuenta_contable_banco", required = true, nillable = true)
        protected String cuentaContableBanco;
        @XmlElement(name = "descripcion_cuenta_banco", required = true, nillable = true)
        protected String descripcionCuentaBanco;

        /**
         * Gets the value of the idDetalleRetencionCuentaBancaria property.
         *
         * @return
         *         possible object is
         *         {@link Integer }
         *
         */
        public Integer getIdDetalleRetencionCuentaBancaria() {
            return idDetalleRetencionCuentaBancaria;
        }

        /**
         * Sets the value of the idDetalleRetencionCuentaBancaria property.
         *
         * @param value
         *            allowed object is
         *            {@link Integer }
         *
         */
        public void setIdDetalleRetencionCuentaBancaria(Integer value) {
            idDetalleRetencionCuentaBancaria = value;
        }

        /**
         * Gets the value of the idConceptoNomina property.
         *
         * @return
         *         possible object is
         *         {@link Integer }
         *
         */
        public Integer getIdConceptoNomina() {
            return idConceptoNomina;
        }

        /**
         * Sets the value of the idConceptoNomina property.
         *
         * @param value
         *            allowed object is
         *            {@link Integer }
         *
         */
        public void setIdConceptoNomina(Integer value) {
            idConceptoNomina = value;
        }

        /**
         * Gets the value of the descripcionConceptoNomina property.
         *
         * @return
         *         possible object is
         *         {@link String }
         *
         */
        public String getDescripcionConceptoNomina() {
            return descripcionConceptoNomina;
        }

        /**
         * Sets the value of the descripcionConceptoNomina property.
         *
         * @param value
         *            allowed object is
         *            {@link String }
         *
         */
        public void setDescripcionConceptoNomina(String value) {
            descripcionConceptoNomina = value;
        }

        /**
         * Gets the value of the cuentaContableRetencion property.
         *
         * @return
         *         possible object is
         *         {@link String }
         *
         */
        public String getCuentaContableRetencion() {
            return cuentaContableRetencion;
        }

        /**
         * Sets the value of the cuentaContableRetencion property.
         *
         * @param value
         *            allowed object is
         *            {@link String }
         *
         */
        public void setCuentaContableRetencion(String value) {
            cuentaContableRetencion = value;
        }

        /**
         * Gets the value of the descripcionCuentaContable property.
         *
         * @return
         *         possible object is
         *         {@link String }
         *
         */
        public String getDescripcionCuentaContable() {
            return descripcionCuentaContable;
        }

        /**
         * Sets the value of the descripcionCuentaContable property.
         *
         * @param value
         *            allowed object is
         *            {@link String }
         *
         */
        public void setDescripcionCuentaContable(String value) {
            descripcionCuentaContable = value;
        }

        /**
         * Gets the value of the cuentaRetencion property.
         *
         * @return
         *         possible object is
         *         {@link String }
         *
         */
        public String getCuentaRetencion() {
            return cuentaRetencion;
        }

        /**
         * Sets the value of the cuentaRetencion property.
         *
         * @param value
         *            allowed object is
         *            {@link String }
         *
         */
        public void setCuentaRetencion(String value) {
            cuentaRetencion = value;
        }

        /**
         * Gets the value of the cuentaContableBanco property.
         *
         * @return
         *         possible object is
         *         {@link String }
         *
         */
        public String getCuentaContableBanco() {
            return cuentaContableBanco;
        }

        /**
         * Sets the value of the cuentaContableBanco property.
         *
         * @param value
         *            allowed object is
         *            {@link String }
         *
         */
        public void setCuentaContableBanco(String value) {
            cuentaContableBanco = value;
        }

        /**
         * Gets the value of the descripcionCuentaBanco property.
         *
         * @return
         *         possible object is
         *         {@link String }
         *
         */
        public String getDescripcionCuentaBanco() {
            return descripcionCuentaBanco;
        }

        /**
         * Sets the value of the descripcionCuentaBanco property.
         *
         * @param value
         *            allowed object is
         *            {@link String }
         *
         */
        public void setDescripcionCuentaBanco(String value) {
            descripcionCuentaBanco = value;
        }

    }

}
