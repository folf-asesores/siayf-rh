

package mx.gob.saludtlax.rh.sat.xml.nomina;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Percepciones" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Percepcion" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="TipoPercepcion" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                                 &lt;minInclusive value="1"/>
 *                                 &lt;pattern value="[0-9]{3}"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Clave" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="3"/>
 *                                 &lt;maxLength value="15"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Concepto" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="100"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="ImporteGravado" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
 *                           &lt;attribute name="ImporteExento" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="TotalGravado" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
 *                 &lt;attribute name="TotalExento" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Deducciones" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Deduccion" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="TipoDeduccion" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                                 &lt;minInclusive value="1"/>
 *                                 &lt;pattern value="[0-9]{3}"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Clave" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="3"/>
 *                                 &lt;maxLength value="15"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Concepto" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="100"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="ImporteGravado" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
 *                           &lt;attribute name="ImporteExento" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="TotalGravado" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
 *                 &lt;attribute name="TotalExento" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Incapacidades" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Incapacidad" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="DiasIncapacidad" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                 &lt;fractionDigits value="6"/>
 *                                 &lt;minInclusive value="1"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="TipoIncapacidad" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                           &lt;attribute name="Descuento" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="HorasExtras" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="HorasExtra" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="Dias" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                           &lt;attribute name="TipoHoras" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;whiteSpace value="collapse"/>
 *                                 &lt;enumeration value="Dobles"/>
 *                                 &lt;enumeration value="Triples"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="HorasExtra" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                           &lt;attribute name="ImportePagado" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="1.1" />
 *       &lt;attribute name="RegistroPatronal">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="1"/>
 *             &lt;maxLength value="20"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="NumEmpleado" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="1"/>
 *             &lt;maxLength value="15"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="CURP" use="required" type="{http://www.sat.gob.mx/nomina}t_CURP" />
 *       &lt;attribute name="TipoRegimen" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *             &lt;minInclusive value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="NumSeguridadSocial">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="1"/>
 *             &lt;maxLength value="15"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="FechaPago" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="FechaInicialPago" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="FechaFinalPago" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="NumDiasPagados" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;fractionDigits value="6"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Departamento">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="1"/>
 *             &lt;maxLength value="100"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="CLABE" type="{http://www.sat.gob.mx/nomina}t_Clabe" />
 *       &lt;attribute name="Banco">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *             &lt;minInclusive value="1"/>
 *             &lt;pattern value="[0-9]{3}"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="FechaInicioRelLaboral" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="Antiguedad" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Puesto" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TipoContrato" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TipoJornada" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PeriodicidadPago" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="1"/>
 *             &lt;maxLength value="100"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="SalarioBaseCotApor" type="{http://www.sat.gob.mx/nomina}t_Importe" />
 *       &lt;attribute name="RiesgoPuesto">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *             &lt;minInclusive value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="SalarioDiarioIntegrado" type="{http://www.sat.gob.mx/nomina}t_Importe" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "percepciones",
    "deducciones",
    "incapacidades",
    "horasExtras"
})
@XmlRootElement(name = "Nomina")
public class Nomina {

    @XmlElement(name = "Percepciones")
    protected Nomina.Percepciones percepciones;
    @XmlElement(name = "Deducciones")
    protected Nomina.Deducciones deducciones;
    @XmlElement(name = "Incapacidades")
    protected Nomina.Incapacidades incapacidades;
    @XmlElement(name = "HorasExtras")
    protected Nomina.HorasExtras horasExtras;
    @XmlAttribute(name = "Version", required = true)
    protected String version;
    @XmlAttribute(name = "RegistroPatronal")
    protected String registroPatronal;
    @XmlAttribute(name = "NumEmpleado", required = true)
    protected String numEmpleado;
    @XmlAttribute(name = "CURP", required = true)
    protected String curp;
    @XmlAttribute(name = "TipoRegimen", required = true)
    protected int tipoRegimen;
    @XmlAttribute(name = "NumSeguridadSocial")
    protected String numSeguridadSocial;
    @XmlAttribute(name = "FechaPago", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaPago;
    @XmlAttribute(name = "FechaInicialPago", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicialPago;
    @XmlAttribute(name = "FechaFinalPago", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaFinalPago;
    @XmlAttribute(name = "NumDiasPagados", required = true)
    protected BigDecimal numDiasPagados;
    @XmlAttribute(name = "Departamento")
    protected String departamento;
    @XmlAttribute(name = "CLABE")
    protected BigInteger clabe;
    @XmlAttribute(name = "Banco")
    protected String banco;
    @XmlAttribute(name = "FechaInicioRelLaboral")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicioRelLaboral;
    @XmlAttribute(name = "Antiguedad")
    protected Integer antiguedad;
    @XmlAttribute(name = "Puesto")
    protected String puesto;
    @XmlAttribute(name = "TipoContrato")
    protected String tipoContrato;
    @XmlAttribute(name = "TipoJornada")
    protected String tipoJornada;
    @XmlAttribute(name = "PeriodicidadPago", required = true)
    protected String periodicidadPago;
    @XmlAttribute(name = "SalarioBaseCotApor") // Sueldo integrado del imss
    protected BigDecimal salarioBaseCotApor;
    @XmlAttribute(name = "RiesgoPuesto")
    protected Integer riesgoPuesto;
    @XmlAttribute(name = "SalarioDiarioIntegrado")
    protected BigDecimal salarioDiarioIntegrado;

    /**
     * Obtiene el valor de la propiedad percepciones.
     * 
     * @return
     *     possible object is
     *     {@link Nomina.Percepciones }
     *     
     */
    public Nomina.Percepciones getPercepciones() {
        return percepciones;
    }

    /**
     * Define el valor de la propiedad percepciones.
     * 
     * @param value
     *     allowed object is
     *     {@link Nomina.Percepciones }
     *     
     */
    public void setPercepciones(Nomina.Percepciones value) {
        this.percepciones = value;
    }

    /**
     * Obtiene el valor de la propiedad deducciones.
     * 
     * @return
     *     possible object is
     *     {@link Nomina.Deducciones }
     *     
     */
    public Nomina.Deducciones getDeducciones() {
        return deducciones;
    }

    /**
     * Define el valor de la propiedad deducciones.
     * 
     * @param value
     *     allowed object is
     *     {@link Nomina.Deducciones }
     *     
     */
    public void setDeducciones(Nomina.Deducciones value) {
        this.deducciones = value;
    }

    /**
     * Obtiene el valor de la propiedad incapacidades.
     * 
     * @return
     *     possible object is
     *     {@link Nomina.Incapacidades }
     *     
     */
    public Nomina.Incapacidades getIncapacidades() {
        return incapacidades;
    }

    /**
     * Define el valor de la propiedad incapacidades.
     * 
     * @param value
     *     allowed object is
     *     {@link Nomina.Incapacidades }
     *     
     */
    public void setIncapacidades(Nomina.Incapacidades value) {
        this.incapacidades = value;
    }

    /**
     * Obtiene el valor de la propiedad horasExtras.
     * 
     * @return
     *     possible object is
     *     {@link Nomina.HorasExtras }
     *     
     */
    public Nomina.HorasExtras getHorasExtras() {
        return horasExtras;
    }

    /**
     * Define el valor de la propiedad horasExtras.
     * 
     * @param value
     *     allowed object is
     *     {@link Nomina.HorasExtras }
     *     
     */
    public void setHorasExtras(Nomina.HorasExtras value) {
        this.horasExtras = value;
    }

    /**
     * Obtiene el valor de la propiedad version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        if (version == null) {
            return "1.1";
        } else {
            return version;
        }
    }

    /**
     * Define el valor de la propiedad version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Obtiene el valor de la propiedad registroPatronal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistroPatronal() {
        return registroPatronal;
    }

    /**
     * Define el valor de la propiedad registroPatronal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistroPatronal(String value) {
        this.registroPatronal = value;
    }

    /**
     * Obtiene el valor de la propiedad numEmpleado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumEmpleado() {
        return numEmpleado;
    }

    /**
     * Define el valor de la propiedad numEmpleado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumEmpleado(String value) {
        this.numEmpleado = value;
    }

    /**
     * Obtiene el valor de la propiedad curp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCURP() {
        return curp;
    }

    /**
     * Define el valor de la propiedad curp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCURP(String value) {
        this.curp = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoRegimen.
     * 
     */
    public int getTipoRegimen() {
        return tipoRegimen;
    }

    /**
     * Define el valor de la propiedad tipoRegimen.
     * 
     */
    public void setTipoRegimen(int value) {
        this.tipoRegimen = value;
    }

    /**
     * Obtiene el valor de la propiedad numSeguridadSocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumSeguridadSocial() {
        return numSeguridadSocial;
    }

    /**
     * Define el valor de la propiedad numSeguridadSocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumSeguridadSocial(String value) {
        this.numSeguridadSocial = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaPago.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaPago() {
        return fechaPago;
    }

    /**
     * Define el valor de la propiedad fechaPago.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaPago(XMLGregorianCalendar value) {
        this.fechaPago = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicialPago.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicialPago() {
        return fechaInicialPago;
    }

    /**
     * Define el valor de la propiedad fechaInicialPago.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicialPago(XMLGregorianCalendar value) {
        this.fechaInicialPago = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFinalPago.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFinalPago() {
        return fechaFinalPago;
    }

    /**
     * Define el valor de la propiedad fechaFinalPago.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFinalPago(XMLGregorianCalendar value) {
        this.fechaFinalPago = value;
    }

    /**
     * Obtiene el valor de la propiedad numDiasPagados.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumDiasPagados() {
        return numDiasPagados;
    }

    /**
     * Define el valor de la propiedad numDiasPagados.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumDiasPagados(BigDecimal value) {
        this.numDiasPagados = value;
    }

    /**
     * Obtiene el valor de la propiedad departamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Define el valor de la propiedad departamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamento(String value) {
        this.departamento = value;
    }

    /**
     * Obtiene el valor de la propiedad clabe.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCLABE() {
        return clabe;
    }

    /**
     * Define el valor de la propiedad clabe.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCLABE(BigInteger value) {
        this.clabe = value;
    }

    /**
     * Obtiene el valor de la propiedad banco.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public String getBanco() {
        return banco;
    }

    /**
     * Define el valor de la propiedad banco.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBanco(String value) {
        this.banco = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicioRelLaboral.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicioRelLaboral() {
        return fechaInicioRelLaboral;
    }

    /**
     * Define el valor de la propiedad fechaInicioRelLaboral.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicioRelLaboral(XMLGregorianCalendar value) {
        this.fechaInicioRelLaboral = value;
    }

    /**
     * Obtiene el valor de la propiedad antiguedad.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAntiguedad() {
        return antiguedad;
    }

    /**
     * Define el valor de la propiedad antiguedad.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAntiguedad(Integer value) {
        this.antiguedad = value;
    }

    /**
     * Obtiene el valor de la propiedad puesto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * Define el valor de la propiedad puesto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuesto(String value) {
        this.puesto = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoContrato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoContrato() {
        return tipoContrato;
    }

    /**
     * Define el valor de la propiedad tipoContrato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoContrato(String value) {
        this.tipoContrato = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoJornada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoJornada() {
        return tipoJornada;
    }

    /**
     * Define el valor de la propiedad tipoJornada.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoJornada(String value) {
        this.tipoJornada = value;
    }

    /**
     * Obtiene el valor de la propiedad periodicidadPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicidadPago() {
        return periodicidadPago;
    }

    /**
     * Define el valor de la propiedad periodicidadPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicidadPago(String value) {
        this.periodicidadPago = value;
    }

    /**
     * Obtiene el valor de la propiedad salarioBaseCotApor.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSalarioBaseCotApor() {
        return salarioBaseCotApor;
    }

    /**
     * Define el valor de la propiedad salarioBaseCotApor.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSalarioBaseCotApor(BigDecimal value) {
        this.salarioBaseCotApor = value;
    }

    /**
     * Obtiene el valor de la propiedad riesgoPuesto.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRiesgoPuesto() {
        return riesgoPuesto;
    }

    /**
     * Define el valor de la propiedad riesgoPuesto.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRiesgoPuesto(Integer value) {
        this.riesgoPuesto = value;
    }

    /**
     * Obtiene el valor de la propiedad salarioDiarioIntegrado.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSalarioDiarioIntegrado() {
        return salarioDiarioIntegrado;
    }

    /**
     * Define el valor de la propiedad salarioDiarioIntegrado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSalarioDiarioIntegrado(BigDecimal value) {
        this.salarioDiarioIntegrado = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Deduccion" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="TipoDeduccion" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *                       &lt;minInclusive value="1"/>
     *                       &lt;pattern value="[0-9]{3}"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="Clave" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;minLength value="3"/>
     *                       &lt;maxLength value="15"/>
     *                       &lt;whiteSpace value="collapse"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="Concepto" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;minLength value="1"/>
     *                       &lt;maxLength value="100"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="ImporteGravado" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
     *                 &lt;attribute name="ImporteExento" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="TotalGravado" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
     *       &lt;attribute name="TotalExento" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "deduccion"
    })
    public static class Deducciones {

        @XmlElement(name = "Deduccion", required = true)
        protected List<Nomina.Deducciones.Deduccion> deduccion;
        @XmlAttribute(name = "TotalGravado", required = true)
        protected BigDecimal totalGravado;
        @XmlAttribute(name = "TotalExento", required = true)
        protected BigDecimal totalExento;

        /**
         * Gets the value of the deduccion property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the deduccion property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDeduccion().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Nomina.Deducciones.Deduccion }
         * 
         * 
         */
        public List<Nomina.Deducciones.Deduccion> getDeduccion() {
            if (deduccion == null) {
                deduccion = new ArrayList<Nomina.Deducciones.Deduccion>();
            }
            return this.deduccion;
        }

        /**
         * Obtiene el valor de la propiedad totalGravado.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalGravado() {
            return totalGravado;
        }

        /**
         * Define el valor de la propiedad totalGravado.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalGravado(BigDecimal value) {
            this.totalGravado = value;
        }

        /**
         * Obtiene el valor de la propiedad totalExento.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalExento() {
            return totalExento;
        }

        /**
         * Define el valor de la propiedad totalExento.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalExento(BigDecimal value) {
            this.totalExento = value;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="TipoDeduccion" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
         *             &lt;minInclusive value="1"/>
         *             &lt;pattern value="[0-9]{3}"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="Clave" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;minLength value="3"/>
         *             &lt;maxLength value="15"/>
         *             &lt;whiteSpace value="collapse"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="Concepto" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;minLength value="1"/>
         *             &lt;maxLength value="100"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="ImporteGravado" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
         *       &lt;attribute name="ImporteExento" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Deduccion {

            @XmlAttribute(name = "TipoDeduccion", required = true)
            protected String tipoDeduccion;
            @XmlAttribute(name = "Clave", required = true)
            protected String clave;
            @XmlAttribute(name = "Concepto", required = true)
            protected String concepto;
            @XmlAttribute(name = "ImporteGravado", required = true)
            protected BigDecimal importeGravado;
            @XmlAttribute(name = "ImporteExento", required = true)
            protected BigDecimal importeExento;

            /**
             * Obtiene el valor de la propiedad tipoDeduccion.
             * 
             */
            public String getTipoDeduccion() {
                return tipoDeduccion;
            }

            /**
             * Define el valor de la propiedad tipoDeduccion.
             * 
             */
            public void setTipoDeduccion(String value) {
                this.tipoDeduccion = value;
            }

            /**
             * Obtiene el valor de la propiedad clave.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getClave() {
                return clave;
            }

            /**
             * Define el valor de la propiedad clave.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setClave(String value) {
                this.clave = value;
            }

            /**
             * Obtiene el valor de la propiedad concepto.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getConcepto() {
                return concepto;
            }

            /**
             * Define el valor de la propiedad concepto.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setConcepto(String value) {
                this.concepto = value;
            }

            /**
             * Obtiene el valor de la propiedad importeGravado.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getImporteGravado() {
                return importeGravado;
            }

            /**
             * Define el valor de la propiedad importeGravado.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setImporteGravado(BigDecimal value) {
                this.importeGravado = value;
            }

            /**
             * Obtiene el valor de la propiedad importeExento.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getImporteExento() {
                return importeExento;
            }

            /**
             * Define el valor de la propiedad importeExento.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setImporteExento(BigDecimal value) {
                this.importeExento = value;
            }

        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="HorasExtra" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="Dias" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
     *                 &lt;attribute name="TipoHoras" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;whiteSpace value="collapse"/>
     *                       &lt;enumeration value="Dobles"/>
     *                       &lt;enumeration value="Triples"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="HorasExtra" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
     *                 &lt;attribute name="ImportePagado" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
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
    @XmlType(name = "", propOrder = {
        "horasExtra"
    })
    public static class HorasExtras {

        @XmlElement(name = "HorasExtra", required = true)
        protected List<Nomina.HorasExtras.HorasExtra> horasExtra;

        /**
         * Gets the value of the horasExtra property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the horasExtra property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getHorasExtra().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Nomina.HorasExtras.HorasExtra }
         * 
         * 
         */
        public List<Nomina.HorasExtras.HorasExtra> getHorasExtra() {
            if (horasExtra == null) {
                horasExtra = new ArrayList<Nomina.HorasExtras.HorasExtra>();
            }
            return this.horasExtra;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="Dias" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
         *       &lt;attribute name="TipoHoras" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;whiteSpace value="collapse"/>
         *             &lt;enumeration value="Dobles"/>
         *             &lt;enumeration value="Triples"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="HorasExtra" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
         *       &lt;attribute name="ImportePagado" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class HorasExtra {

            @XmlAttribute(name = "Dias", required = true)
            protected int dias;
            @XmlAttribute(name = "TipoHoras", required = true)
            protected String tipoHoras;
            @XmlAttribute(name = "HorasExtra", required = true)
            protected int horasExtra;
            @XmlAttribute(name = "ImportePagado", required = true)
            protected BigDecimal importePagado;

            /**
             * Obtiene el valor de la propiedad dias.
             * 
             */
            public int getDias() {
                return dias;
            }

            /**
             * Define el valor de la propiedad dias.
             * 
             */
            public void setDias(int value) {
                this.dias = value;
            }

            /**
             * Obtiene el valor de la propiedad tipoHoras.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTipoHoras() {
                return tipoHoras;
            }

            /**
             * Define el valor de la propiedad tipoHoras.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTipoHoras(String value) {
                this.tipoHoras = value;
            }

            /**
             * Obtiene el valor de la propiedad horasExtra.
             * 
             */
            public int getHorasExtra() {
                return horasExtra;
            }

            /**
             * Define el valor de la propiedad horasExtra.
             * 
             */
            public void setHorasExtra(int value) {
                this.horasExtra = value;
            }

            /**
             * Obtiene el valor de la propiedad importePagado.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getImportePagado() {
                return importePagado;
            }

            /**
             * Define el valor de la propiedad importePagado.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setImportePagado(BigDecimal value) {
                this.importePagado = value;
            }

        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Incapacidad" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="DiasIncapacidad" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                       &lt;fractionDigits value="6"/>
     *                       &lt;minInclusive value="1"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="TipoIncapacidad" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
     *                 &lt;attribute name="Descuento" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
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
    @XmlType(name = "", propOrder = {
        "incapacidad"
    })
    public static class Incapacidades {

        @XmlElement(name = "Incapacidad", required = true)
        protected List<Nomina.Incapacidades.Incapacidad> incapacidad;

        /**
         * Gets the value of the incapacidad property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the incapacidad property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIncapacidad().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Nomina.Incapacidades.Incapacidad }
         * 
         * 
         */
        public List<Nomina.Incapacidades.Incapacidad> getIncapacidad() {
            if (incapacidad == null) {
                incapacidad = new ArrayList<Nomina.Incapacidades.Incapacidad>();
            }
            return this.incapacidad;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="DiasIncapacidad" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *             &lt;fractionDigits value="6"/>
         *             &lt;minInclusive value="1"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="TipoIncapacidad" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
         *       &lt;attribute name="Descuento" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Incapacidad {

            @XmlAttribute(name = "DiasIncapacidad", required = true)
            protected BigDecimal diasIncapacidad;
            @XmlAttribute(name = "TipoIncapacidad", required = true)
            protected int tipoIncapacidad;
            @XmlAttribute(name = "Descuento", required = true)
            protected BigDecimal descuento;

            /**
             * Obtiene el valor de la propiedad diasIncapacidad.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getDiasIncapacidad() {
                return diasIncapacidad;
            }

            /**
             * Define el valor de la propiedad diasIncapacidad.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setDiasIncapacidad(BigDecimal value) {
                this.diasIncapacidad = value;
            }

            /**
             * Obtiene el valor de la propiedad tipoIncapacidad.
             * 
             */
            public int getTipoIncapacidad() {
                return tipoIncapacidad;
            }

            /**
             * Define el valor de la propiedad tipoIncapacidad.
             * 
             */
            public void setTipoIncapacidad(int value) {
                this.tipoIncapacidad = value;
            }

            /**
             * Obtiene el valor de la propiedad descuento.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getDescuento() {
                return descuento;
            }

            /**
             * Define el valor de la propiedad descuento.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setDescuento(BigDecimal value) {
                this.descuento = value;
            }

        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Percepcion" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="TipoPercepcion" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *                       &lt;minInclusive value="1"/>
     *                       &lt;pattern value="[0-9]{3}"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="Clave" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;minLength value="3"/>
     *                       &lt;maxLength value="15"/>
     *                       &lt;whiteSpace value="collapse"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="Concepto" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;minLength value="1"/>
     *                       &lt;maxLength value="100"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="ImporteGravado" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
     *                 &lt;attribute name="ImporteExento" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="TotalGravado" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
     *       &lt;attribute name="TotalExento" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "percepcion"
    })
    public static class Percepciones {

        @XmlElement(name = "Percepcion", required = true)
        protected List<Nomina.Percepciones.Percepcion> percepcion;
        @XmlAttribute(name = "TotalGravado", required = true)
        protected BigDecimal totalGravado;
        @XmlAttribute(name = "TotalExento", required = true)
        protected BigDecimal totalExento;

        /**
         * Gets the value of the percepcion property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the percepcion property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPercepcion().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Nomina.Percepciones.Percepcion }
         * 
         * 
         */
        public List<Nomina.Percepciones.Percepcion> getPercepcion() {
            if (percepcion == null) {
                percepcion = new ArrayList<Nomina.Percepciones.Percepcion>();
            }
            return this.percepcion;
        }

        /**
         * Obtiene el valor de la propiedad totalGravado.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalGravado() {
            return totalGravado;
        }

        /**
         * Define el valor de la propiedad totalGravado.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalGravado(BigDecimal value) {
            this.totalGravado = value;
        }

        /**
         * Obtiene el valor de la propiedad totalExento.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalExento() {
            return totalExento;
        }

        /**
         * Define el valor de la propiedad totalExento.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalExento(BigDecimal value) {
            this.totalExento = value;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="TipoPercepcion" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
         *             &lt;minInclusive value="1"/>
         *             &lt;pattern value="[0-9]{3}"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="Clave" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;minLength value="3"/>
         *             &lt;maxLength value="15"/>
         *             &lt;whiteSpace value="collapse"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="Concepto" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;minLength value="1"/>
         *             &lt;maxLength value="100"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="ImporteGravado" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
         *       &lt;attribute name="ImporteExento" use="required" type="{http://www.sat.gob.mx/nomina}t_Importe" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Percepcion {

            @XmlAttribute(name = "TipoPercepcion", required = true)
            protected String tipoPercepcion;
            @XmlAttribute(name = "Clave", required = true)
            protected String clave;
            @XmlAttribute(name = "Concepto", required = true)
            protected String concepto;
            @XmlAttribute(name = "ImporteGravado", required = true)
            protected BigDecimal importeGravado;
            @XmlAttribute(name = "ImporteExento", required = true)
            protected BigDecimal importeExento;

            /**
             * Obtiene el valor de la propiedad tipoPercepcion.
             * 
             */
            public String getTipoPercepcion() {
                return tipoPercepcion;
            }

            /**
             * Define el valor de la propiedad tipoPercepcion.
             * 
             */
            public void setTipoPercepcion(String value) {
                this.tipoPercepcion = value;
            }

            /**
             * Obtiene el valor de la propiedad clave.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getClave() {
                return clave;
            }

            /**
             * Define el valor de la propiedad clave.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setClave(String value) {
                this.clave = value;
            }

            /**
             * Obtiene el valor de la propiedad concepto.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getConcepto() {
                return concepto;
            }

            /**
             * Define el valor de la propiedad concepto.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setConcepto(String value) {
                this.concepto = value;
            }

            /**
             * Obtiene el valor de la propiedad importeGravado.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getImporteGravado() {
                return importeGravado;
            }

            /**
             * Define el valor de la propiedad importeGravado.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setImporteGravado(BigDecimal value) {
                this.importeGravado = value;
            }

            /**
             * Obtiene el valor de la propiedad importeExento.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getImporteExento() {
                return importeExento;
            }

            /**
             * Define el valor de la propiedad importeExento.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setImporteExento(BigDecimal value) {
                this.importeExento = value;
            }

        }

    }

}
