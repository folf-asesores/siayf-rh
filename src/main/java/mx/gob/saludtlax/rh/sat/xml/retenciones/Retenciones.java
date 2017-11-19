//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen.
// Generado el: 2016.04.21 a las 10:07:08 AM CDT
//

package mx.gob.saludtlax.rh.sat.xml.retenciones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Clase Java para anonymous complex type.
 *
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Emisor">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="RFCEmisor" use="required" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_RFC" />
 *                 &lt;attribute name="NomDenRazSocE">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="300"/>
 *                       &lt;whiteSpace value="collapse"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="CURPE" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_CURP" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Receptor">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="Nacional">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="RFCRecep" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_RFC">
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="NomDenRazSocR">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="300"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="CURPR" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_CURP" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Extranjero">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="NumRegIdTrib">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="20"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="NomDenRazSocR" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="300"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/choice>
 *                 &lt;attribute name="Nacionalidad" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;whiteSpace value="collapse"/>
 *                       &lt;enumeration value="Nacional"/>
 *                       &lt;enumeration value="Extranjero"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Periodo">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="MesIni" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                       &lt;minInclusive value="1"/>
 *                       &lt;maxInclusive value="12"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="MesFin" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                       &lt;minInclusive value="1"/>
 *                       &lt;maxInclusive value="12"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="Ejerc" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                       &lt;minInclusive value="2004"/>
 *                       &lt;maxInclusive value="2024"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Totales">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ImpRetenidos" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="BaseRet" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_Importe" />
 *                           &lt;attribute name="Impuesto">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.sat.gob.mx/esquemas/retencionpago/1/catalogos}c_TipoImpuesto">
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="montoRet" use="required" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_Importe" />
 *                           &lt;attribute name="TipoPagoRet" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;whiteSpace value="collapse"/>
 *                                 &lt;enumeration value="Pago definitivo"/>
 *                                 &lt;enumeration value="Pago provisional"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="montoTotOperacion" use="required" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_Importe" />
 *                 &lt;attribute name="montoTotGrav" use="required" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_Importe" />
 *                 &lt;attribute name="montoTotExent" use="required" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_Importe" />
 *                 &lt;attribute name="montoTotRet" use="required" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_Importe" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Complemento" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;any maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Addenda" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;any maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Version" use="required" fixed="1.0">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;whiteSpace value="collapse"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="FolioInt">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="1"/>
 *             &lt;maxLength value="20"/>
 *             &lt;whiteSpace value="collapse"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Sello" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;whiteSpace value="collapse"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="NumCert" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;length value="20"/>
 *             &lt;whiteSpace value="collapse"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Cert" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;whiteSpace value="collapse"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="FechaExp" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime">
 *             &lt;whiteSpace value="collapse"/>
 *             &lt;minInclusive value="2014-01-01T00:00:00-06:00"/>
 *             &lt;pattern value="-?([1-9][0-9]{3,}|0[0-9]{3})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])T(([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="CveRetenc" use="required" type="{http://www.sat.gob.mx/esquemas/retencionpago/1/catalogos}c_Retenciones" />
 *       &lt;attribute name="DescRetenc">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="1"/>
 *             &lt;maxLength value="100"/>
 *             &lt;whiteSpace value="collapse"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "emisor", "receptor", "periodo", "totales", "complemento", "addenda" })
@XmlRootElement(name = "Retenciones")
public class Retenciones {

    @XmlElement(name = "Emisor", required = true)
    protected Retenciones.Emisor emisor;
    @XmlElement(name = "Receptor", required = true)
    protected Retenciones.Receptor receptor;
    @XmlElement(name = "Periodo", required = true)
    protected Retenciones.Periodo periodo;
    @XmlElement(name = "Totales", required = true)
    protected Retenciones.Totales totales;
    @XmlElement(name = "Complemento")
    protected Retenciones.Complemento complemento;
    @XmlElement(name = "Addenda")
    protected Retenciones.Addenda addenda;
    @XmlAttribute(name = "Version", required = true)
    protected String version;
    @XmlAttribute(name = "FolioInt")
    protected String folioInt;
    @XmlAttribute(name = "Sello", required = true)
    protected String sello;
    @XmlAttribute(name = "NumCert", required = true)
    protected String numCert;
    @XmlAttribute(name = "Cert", required = true)
    protected String cert;
    @XmlAttribute(name = "FechaExp", required = true)
    protected XMLGregorianCalendar fechaExp;
    @XmlAttribute(name = "CveRetenc", required = true)
    protected String cveRetenc;
    @XmlAttribute(name = "DescRetenc")
    protected String descRetenc;

    /**
     * Obtiene el valor de la propiedad emisor.
     *
     * @return
     *         possible object is
     *         {@link Retenciones.Emisor }
     *
     */
    public Retenciones.Emisor getEmisor() {
        return emisor;
    }

    /**
     * Define el valor de la propiedad emisor.
     *
     * @param value
     *            allowed object is
     *            {@link Retenciones.Emisor }
     *
     */
    public void setEmisor(Retenciones.Emisor value) {
        emisor = value;
    }

    /**
     * Obtiene el valor de la propiedad receptor.
     *
     * @return
     *         possible object is
     *         {@link Retenciones.Receptor }
     *
     */
    public Retenciones.Receptor getReceptor() {
        return receptor;
    }

    /**
     * Define el valor de la propiedad receptor.
     *
     * @param value
     *            allowed object is
     *            {@link Retenciones.Receptor }
     *
     */
    public void setReceptor(Retenciones.Receptor value) {
        receptor = value;
    }

    /**
     * Obtiene el valor de la propiedad periodo.
     *
     * @return
     *         possible object is
     *         {@link Retenciones.Periodo }
     *
     */
    public Retenciones.Periodo getPeriodo() {
        return periodo;
    }

    /**
     * Define el valor de la propiedad periodo.
     *
     * @param value
     *            allowed object is
     *            {@link Retenciones.Periodo }
     *
     */
    public void setPeriodo(Retenciones.Periodo value) {
        periodo = value;
    }

    /**
     * Obtiene el valor de la propiedad totales.
     *
     * @return
     *         possible object is
     *         {@link Retenciones.Totales }
     *
     */
    public Retenciones.Totales getTotales() {
        return totales;
    }

    /**
     * Define el valor de la propiedad totales.
     *
     * @param value
     *            allowed object is
     *            {@link Retenciones.Totales }
     *
     */
    public void setTotales(Retenciones.Totales value) {
        totales = value;
    }

    /**
     * Obtiene el valor de la propiedad complemento.
     *
     * @return
     *         possible object is
     *         {@link Retenciones.Complemento }
     *
     */
    public Retenciones.Complemento getComplemento() {
        return complemento;
    }

    /**
     * Define el valor de la propiedad complemento.
     *
     * @param value
     *            allowed object is
     *            {@link Retenciones.Complemento }
     *
     */
    public void setComplemento(Retenciones.Complemento value) {
        complemento = value;
    }

    /**
     * Obtiene el valor de la propiedad addenda.
     *
     * @return
     *         possible object is
     *         {@link Retenciones.Addenda }
     *
     */
    public Retenciones.Addenda getAddenda() {
        return addenda;
    }

    /**
     * Define el valor de la propiedad addenda.
     *
     * @param value
     *            allowed object is
     *            {@link Retenciones.Addenda }
     *
     */
    public void setAddenda(Retenciones.Addenda value) {
        addenda = value;
    }

    /**
     * Obtiene el valor de la propiedad version.
     *
     * @return
     *         possible object is
     *         {@link String }
     *
     */
    public String getVersion() {
        if (version == null) {
            return "1.0";
        } else {
            return version;
        }
    }

    /**
     * Define el valor de la propiedad version.
     *
     * @param value
     *            allowed object is
     *            {@link String }
     *
     */
    public void setVersion(String value) {
        version = value;
    }

    /**
     * Obtiene el valor de la propiedad folioInt.
     *
     * @return
     *         possible object is
     *         {@link String }
     *
     */
    public String getFolioInt() {
        return folioInt;
    }

    /**
     * Define el valor de la propiedad folioInt.
     *
     * @param value
     *            allowed object is
     *            {@link String }
     *
     */
    public void setFolioInt(String value) {
        folioInt = value;
    }

    /**
     * Obtiene el valor de la propiedad sello.
     *
     * @return
     *         possible object is
     *         {@link String }
     *
     */
    public String getSello() {
        return sello;
    }

    /**
     * Define el valor de la propiedad sello.
     *
     * @param value
     *            allowed object is
     *            {@link String }
     *
     */
    public void setSello(String value) {
        sello = value;
    }

    /**
     * Obtiene el valor de la propiedad numCert.
     *
     * @return
     *         possible object is
     *         {@link String }
     *
     */
    public String getNumCert() {
        return numCert;
    }

    /**
     * Define el valor de la propiedad numCert.
     *
     * @param value
     *            allowed object is
     *            {@link String }
     *
     */
    public void setNumCert(String value) {
        numCert = value;
    }

    /**
     * Obtiene el valor de la propiedad cert.
     *
     * @return
     *         possible object is
     *         {@link String }
     *
     */
    public String getCert() {
        return cert;
    }

    /**
     * Define el valor de la propiedad cert.
     *
     * @param value
     *            allowed object is
     *            {@link String }
     *
     */
    public void setCert(String value) {
        cert = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaExp.
     *
     * @return
     *         possible object is
     *         {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaExp() {
        return fechaExp;
    }

    /**
     * Define el valor de la propiedad fechaExp.
     *
     * @param value
     *            allowed object is
     *            {@link XMLGregorianCalendar }
     *
     */
    public void setFechaExp(XMLGregorianCalendar value) {
        fechaExp = value;
    }

    /**
     * Obtiene el valor de la propiedad cveRetenc.
     *
     * @return
     *         possible object is
     *         {@link String }
     *
     */
    public String getCveRetenc() {
        return cveRetenc;
    }

    /**
     * Define el valor de la propiedad cveRetenc.
     *
     * @param value
     *            allowed object is
     *            {@link String }
     *
     */
    public void setCveRetenc(String value) {
        cveRetenc = value;
    }

    /**
     * Obtiene el valor de la propiedad descRetenc.
     *
     * @return
     *         possible object is
     *         {@link String }
     *
     */
    public String getDescRetenc() {
        return descRetenc;
    }

    /**
     * Define el valor de la propiedad descRetenc.
     *
     * @param value
     *            allowed object is
     *            {@link String }
     *
     */
    public void setDescRetenc(String value) {
        descRetenc = value;
    }

    /**
     * <p>
     * Clase Java para anonymous complex type.
     *
     * <p>
     * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;any maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "any" })
    public static class Addenda {

        @XmlAnyElement(lax = true)
        protected List<Object> any;

        /**
         * Gets the value of the any property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the any property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getAny().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Object }
         *
         *
         */
        public List<Object> getAny() {
            if (any == null) {
                any = new ArrayList<>();
            }
            return any;
        }

    }

    /**
     * <p>
     * Clase Java para anonymous complex type.
     *
     * <p>
     * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;any maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "any" })
    public static class Complemento {

        @XmlAnyElement(lax = true)
        protected List<Object> any;

        /**
         * Gets the value of the any property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the any property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getAny().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Object }
         *
         *
         */
        public List<Object> getAny() {
            if (any == null) {
                any = new ArrayList<>();
            }
            return any;
        }

    }

    /**
     * <p>
     * Clase Java para anonymous complex type.
     *
     * <p>
     * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="RFCEmisor" use="required" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_RFC" />
     *       &lt;attribute name="NomDenRazSocE">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;maxLength value="300"/>
     *             &lt;whiteSpace value="collapse"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="CURPE" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_CURP" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Emisor {

        @XmlAttribute(name = "RFCEmisor", required = true)
        protected String rfcEmisor;
        @XmlAttribute(name = "NomDenRazSocE")
        protected String nomDenRazSocE;
        @XmlAttribute(name = "CURPE")
        protected String curpe;

        /**
         * Obtiene el valor de la propiedad rfcEmisor.
         *
         * @return
         *         possible object is
         *         {@link String }
         *
         */
        public String getRFCEmisor() {
            return rfcEmisor;
        }

        /**
         * Define el valor de la propiedad rfcEmisor.
         *
         * @param value
         *            allowed object is
         *            {@link String }
         *
         */
        public void setRFCEmisor(String value) {
            rfcEmisor = value;
        }

        /**
         * Obtiene el valor de la propiedad nomDenRazSocE.
         *
         * @return
         *         possible object is
         *         {@link String }
         *
         */
        public String getNomDenRazSocE() {
            return nomDenRazSocE;
        }

        /**
         * Define el valor de la propiedad nomDenRazSocE.
         *
         * @param value
         *            allowed object is
         *            {@link String }
         *
         */
        public void setNomDenRazSocE(String value) {
            nomDenRazSocE = value;
        }

        /**
         * Obtiene el valor de la propiedad curpe.
         *
         * @return
         *         possible object is
         *         {@link String }
         *
         */
        public String getCURPE() {
            return curpe;
        }

        /**
         * Define el valor de la propiedad curpe.
         *
         * @param value
         *            allowed object is
         *            {@link String }
         *
         */
        public void setCURPE(String value) {
            curpe = value;
        }

    }

    /**
     * <p>
     * Clase Java para anonymous complex type.
     *
     * <p>
     * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="MesIni" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *             &lt;minInclusive value="1"/>
     *             &lt;maxInclusive value="12"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="MesFin" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *             &lt;minInclusive value="1"/>
     *             &lt;maxInclusive value="12"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="Ejerc" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *             &lt;minInclusive value="2004"/>
     *             &lt;maxInclusive value="2024"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Periodo {

        @XmlAttribute(name = "MesIni", required = true)
        protected int mesIni;
        @XmlAttribute(name = "MesFin", required = true)
        protected int mesFin;
        @XmlAttribute(name = "Ejerc", required = true)
        protected int ejerc;

        /**
         * Obtiene el valor de la propiedad mesIni.
         *
         */
        public int getMesIni() {
            return mesIni;
        }

        /**
         * Define el valor de la propiedad mesIni.
         *
         */
        public void setMesIni(int value) {
            mesIni = value;
        }

        /**
         * Obtiene el valor de la propiedad mesFin.
         *
         */
        public int getMesFin() {
            return mesFin;
        }

        /**
         * Define el valor de la propiedad mesFin.
         *
         */
        public void setMesFin(int value) {
            mesFin = value;
        }

        /**
         * Obtiene el valor de la propiedad ejerc.
         *
         */
        public int getEjerc() {
            return ejerc;
        }

        /**
         * Define el valor de la propiedad ejerc.
         *
         */
        public void setEjerc(int value) {
            ejerc = value;
        }

    }

    /**
     * <p>
     * Clase Java para anonymous complex type.
     *
     * <p>
     * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;element name="Nacional">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="RFCRecep" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_RFC">
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="NomDenRazSocR">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;minLength value="1"/>
     *                       &lt;maxLength value="300"/>
     *                       &lt;whiteSpace value="collapse"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="CURPR" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_CURP" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Extranjero">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="NumRegIdTrib">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;minLength value="1"/>
     *                       &lt;maxLength value="20"/>
     *                       &lt;whiteSpace value="collapse"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="NomDenRazSocR" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;minLength value="1"/>
     *                       &lt;maxLength value="300"/>
     *                       &lt;whiteSpace value="collapse"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/choice>
     *       &lt;attribute name="Nacionalidad" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;whiteSpace value="collapse"/>
     *             &lt;enumeration value="Nacional"/>
     *             &lt;enumeration value="Extranjero"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "nacional", "extranjero" })
    public static class Receptor {

        @XmlElement(name = "Nacional")
        protected Retenciones.Receptor.Nacional nacional;
        @XmlElement(name = "Extranjero")
        protected Retenciones.Receptor.Extranjero extranjero;
        @XmlAttribute(name = "Nacionalidad", required = true)
        protected String nacionalidad;

        /**
         * Obtiene el valor de la propiedad nacional.
         *
         * @return
         *         possible object is
         *         {@link Retenciones.Receptor.Nacional }
         *
         */
        public Retenciones.Receptor.Nacional getNacional() {
            return nacional;
        }

        /**
         * Define el valor de la propiedad nacional.
         *
         * @param value
         *            allowed object is
         *            {@link Retenciones.Receptor.Nacional }
         *
         */
        public void setNacional(Retenciones.Receptor.Nacional value) {
            nacional = value;
        }

        /**
         * Obtiene el valor de la propiedad extranjero.
         *
         * @return
         *         possible object is
         *         {@link Retenciones.Receptor.Extranjero }
         *
         */
        public Retenciones.Receptor.Extranjero getExtranjero() {
            return extranjero;
        }

        /**
         * Define el valor de la propiedad extranjero.
         *
         * @param value
         *            allowed object is
         *            {@link Retenciones.Receptor.Extranjero }
         *
         */
        public void setExtranjero(Retenciones.Receptor.Extranjero value) {
            extranjero = value;
        }

        /**
         * Obtiene el valor de la propiedad nacionalidad.
         *
         * @return
         *         possible object is
         *         {@link String }
         *
         */
        public String getNacionalidad() {
            return nacionalidad;
        }

        /**
         * Define el valor de la propiedad nacionalidad.
         *
         * @param value
         *            allowed object is
         *            {@link String }
         *
         */
        public void setNacionalidad(String value) {
            nacionalidad = value;
        }

        /**
         * <p>
         * Clase Java para anonymous complex type.
         *
         * <p>
         * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="NumRegIdTrib">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;minLength value="1"/>
         *             &lt;maxLength value="20"/>
         *             &lt;whiteSpace value="collapse"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="NomDenRazSocR" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;minLength value="1"/>
         *             &lt;maxLength value="300"/>
         *             &lt;whiteSpace value="collapse"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Extranjero {

            @XmlAttribute(name = "NumRegIdTrib")
            protected String numRegIdTrib;
            @XmlAttribute(name = "NomDenRazSocR", required = true)
            protected String nomDenRazSocR;

            /**
             * Obtiene el valor de la propiedad numRegIdTrib.
             *
             * @return
             *         possible object is
             *         {@link String }
             *
             */
            public String getNumRegIdTrib() {
                return numRegIdTrib;
            }

            /**
             * Define el valor de la propiedad numRegIdTrib.
             *
             * @param value
             *            allowed object is
             *            {@link String }
             *
             */
            public void setNumRegIdTrib(String value) {
                numRegIdTrib = value;
            }

            /**
             * Obtiene el valor de la propiedad nomDenRazSocR.
             *
             * @return
             *         possible object is
             *         {@link String }
             *
             */
            public String getNomDenRazSocR() {
                return nomDenRazSocR;
            }

            /**
             * Define el valor de la propiedad nomDenRazSocR.
             *
             * @param value
             *            allowed object is
             *            {@link String }
             *
             */
            public void setNomDenRazSocR(String value) {
                nomDenRazSocR = value;
            }

        }

        /**
         * <p>
         * Clase Java para anonymous complex type.
         *
         * <p>
         * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="RFCRecep" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_RFC">
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="NomDenRazSocR">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;minLength value="1"/>
         *             &lt;maxLength value="300"/>
         *             &lt;whiteSpace value="collapse"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="CURPR" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_CURP" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Nacional {

            @XmlAttribute(name = "RFCRecep", required = true)
            protected String rfcRecep;
            @XmlAttribute(name = "NomDenRazSocR")
            protected String nomDenRazSocR;
            @XmlAttribute(name = "CURPR")
            protected String curpr;

            /**
             * Obtiene el valor de la propiedad rfcRecep.
             *
             * @return
             *         possible object is
             *         {@link String }
             *
             */
            public String getRFCRecep() {
                return rfcRecep;
            }

            /**
             * Define el valor de la propiedad rfcRecep.
             *
             * @param value
             *            allowed object is
             *            {@link String }
             *
             */
            public void setRFCRecep(String value) {
                rfcRecep = value;
            }

            /**
             * Obtiene el valor de la propiedad nomDenRazSocR.
             *
             * @return
             *         possible object is
             *         {@link String }
             *
             */
            public String getNomDenRazSocR() {
                return nomDenRazSocR;
            }

            /**
             * Define el valor de la propiedad nomDenRazSocR.
             *
             * @param value
             *            allowed object is
             *            {@link String }
             *
             */
            public void setNomDenRazSocR(String value) {
                nomDenRazSocR = value;
            }

            /**
             * Obtiene el valor de la propiedad curpr.
             *
             * @return
             *         possible object is
             *         {@link String }
             *
             */
            public String getCURPR() {
                return curpr;
            }

            /**
             * Define el valor de la propiedad curpr.
             *
             * @param value
             *            allowed object is
             *            {@link String }
             *
             */
            public void setCURPR(String value) {
                curpr = value;
            }

        }

    }

    /**
     * <p>
     * Clase Java para anonymous complex type.
     *
     * <p>
     * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ImpRetenidos" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="BaseRet" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_Importe" />
     *                 &lt;attribute name="Impuesto">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.sat.gob.mx/esquemas/retencionpago/1/catalogos}c_TipoImpuesto">
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="montoRet" use="required" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_Importe" />
     *                 &lt;attribute name="TipoPagoRet" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;whiteSpace value="collapse"/>
     *                       &lt;enumeration value="Pago definitivo"/>
     *                       &lt;enumeration value="Pago provisional"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="montoTotOperacion" use="required" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_Importe" />
     *       &lt;attribute name="montoTotGrav" use="required" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_Importe" />
     *       &lt;attribute name="montoTotExent" use="required" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_Importe" />
     *       &lt;attribute name="montoTotRet" use="required" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_Importe" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "impRetenidos" })
    public static class Totales {

        @XmlElement(name = "ImpRetenidos")
        protected List<Retenciones.Totales.ImpRetenidos> impRetenidos;
        @XmlAttribute(name = "montoTotOperacion", required = true)
        protected BigDecimal montoTotOperacion;
        @XmlAttribute(name = "montoTotGrav", required = true)
        protected BigDecimal montoTotGrav;
        @XmlAttribute(name = "montoTotExent", required = true)
        protected BigDecimal montoTotExent;
        @XmlAttribute(name = "montoTotRet", required = true)
        protected BigDecimal montoTotRet;

        /**
         * Gets the value of the impRetenidos property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the impRetenidos property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getImpRetenidos().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Retenciones.Totales.ImpRetenidos }
         *
         *
         */
        public List<Retenciones.Totales.ImpRetenidos> getImpRetenidos() {
            if (impRetenidos == null) {
                impRetenidos = new ArrayList<>();
            }
            return impRetenidos;
        }

        /**
         * Obtiene el valor de la propiedad montoTotOperacion.
         *
         * @return
         *         possible object is
         *         {@link BigDecimal }
         *
         */
        public BigDecimal getMontoTotOperacion() {
            return montoTotOperacion;
        }

        /**
         * Define el valor de la propiedad montoTotOperacion.
         *
         * @param value
         *            allowed object is
         *            {@link BigDecimal }
         *
         */
        public void setMontoTotOperacion(BigDecimal value) {
            montoTotOperacion = value;
        }

        /**
         * Obtiene el valor de la propiedad montoTotGrav.
         *
         * @return
         *         possible object is
         *         {@link BigDecimal }
         *
         */
        public BigDecimal getMontoTotGrav() {
            return montoTotGrav;
        }

        /**
         * Define el valor de la propiedad montoTotGrav.
         *
         * @param value
         *            allowed object is
         *            {@link BigDecimal }
         *
         */
        public void setMontoTotGrav(BigDecimal value) {
            montoTotGrav = value;
        }

        /**
         * Obtiene el valor de la propiedad montoTotExent.
         *
         * @return
         *         possible object is
         *         {@link BigDecimal }
         *
         */
        public BigDecimal getMontoTotExent() {
            return montoTotExent;
        }

        /**
         * Define el valor de la propiedad montoTotExent.
         *
         * @param value
         *            allowed object is
         *            {@link BigDecimal }
         *
         */
        public void setMontoTotExent(BigDecimal value) {
            montoTotExent = value;
        }

        /**
         * Obtiene el valor de la propiedad montoTotRet.
         *
         * @return
         *         possible object is
         *         {@link BigDecimal }
         *
         */
        public BigDecimal getMontoTotRet() {
            return montoTotRet;
        }

        /**
         * Define el valor de la propiedad montoTotRet.
         *
         * @param value
         *            allowed object is
         *            {@link BigDecimal }
         *
         */
        public void setMontoTotRet(BigDecimal value) {
            montoTotRet = value;
        }

        /**
         * <p>
         * Clase Java para anonymous complex type.
         *
         * <p>
         * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="BaseRet" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_Importe" />
         *       &lt;attribute name="Impuesto">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.sat.gob.mx/esquemas/retencionpago/1/catalogos}c_TipoImpuesto">
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="montoRet" use="required" type="{http://www.sat.gob.mx/esquemas/retencionpago/1}t_Importe" />
         *       &lt;attribute name="TipoPagoRet" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;whiteSpace value="collapse"/>
         *             &lt;enumeration value="Pago definitivo"/>
         *             &lt;enumeration value="Pago provisional"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class ImpRetenidos {

            @XmlAttribute(name = "BaseRet")
            protected BigDecimal baseRet;
            @XmlAttribute(name = "Impuesto")
            protected String impuesto;
            @XmlAttribute(name = "montoRet", required = true)
            protected BigDecimal montoRet;
            @XmlAttribute(name = "TipoPagoRet", required = true)
            protected String tipoPagoRet;

            /**
             * Obtiene el valor de la propiedad baseRet.
             *
             * @return
             *         possible object is
             *         {@link BigDecimal }
             *
             */
            public BigDecimal getBaseRet() {
                return baseRet;
            }

            /**
             * Define el valor de la propiedad baseRet.
             *
             * @param value
             *            allowed object is
             *            {@link BigDecimal }
             *
             */
            public void setBaseRet(BigDecimal value) {
                baseRet = value;
            }

            /**
             * Obtiene el valor de la propiedad impuesto.
             *
             * @return
             *         possible object is
             *         {@link String }
             *
             */
            public String getImpuesto() {
                return impuesto;
            }

            /**
             * Define el valor de la propiedad impuesto.
             *
             * @param value
             *            allowed object is
             *            {@link String }
             *
             */
            public void setImpuesto(String value) {
                impuesto = value;
            }

            /**
             * Obtiene el valor de la propiedad montoRet.
             *
             * @return
             *         possible object is
             *         {@link BigDecimal }
             *
             */
            public BigDecimal getMontoRet() {
                return montoRet;
            }

            /**
             * Define el valor de la propiedad montoRet.
             *
             * @param value
             *            allowed object is
             *            {@link BigDecimal }
             *
             */
            public void setMontoRet(BigDecimal value) {
                montoRet = value;
            }

            /**
             * Obtiene el valor de la propiedad tipoPagoRet.
             *
             * @return
             *         possible object is
             *         {@link String }
             *
             */
            public String getTipoPagoRet() {
                return tipoPagoRet;
            }

            /**
             * Define el valor de la propiedad tipoPagoRet.
             *
             * @param value
             *            allowed object is
             *            {@link String }
             *
             */
            public void setTipoPagoRet(String value) {
                tipoPagoRet = value;
            }

        }

    }

}
