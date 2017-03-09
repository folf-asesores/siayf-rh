//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.01.16 a las 04:13:02 PM CST 
//


package mx.gob.saludtlax.rh.sat.xml.cfdi12;

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
import javax.xml.datatype.DatatypeConstants;
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
 *         &lt;element name="Emisor" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="EntidadSNCF" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="OrigenRecurso" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_OrigenRecurso" />
 *                           &lt;attribute name="MontoRecursoPropio" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Curp" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_CURP" />
 *                 &lt;attribute name="RegistroPatronal">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="20"/>
 *                       &lt;pattern value="([A-Z]|[a-z]|[0-9]|�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,20}"/>
 *                       &lt;whiteSpace value="collapse"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="RfcPatronOrigen" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_RFC" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Receptor">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SubContratacion" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="RfcLabora" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_RFC" />
 *                           &lt;attribute name="PorcentajeTiempo" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                 &lt;minInclusive value="0.001"/>
 *                                 &lt;maxInclusive value="100.000"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                                 &lt;pattern value="[0-9]{1,3}(.([0-9]{1,3}))?"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Curp" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_CURP" />
 *                 &lt;attribute name="NumSeguridadSocial">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="15"/>
 *                       &lt;whiteSpace value="collapse"/>
 *                       &lt;pattern value="[0-9]{1,15}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="FechaInicioRelLaboral" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_Fecha" />
 *                 &lt;attribute name="Antig�edad">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;whiteSpace value="collapse"/>
 *                       &lt;pattern value="P(([1-9][0-9]{0,3})|[0])W|P(([1-9][0-9]?Y)?([1-9]|1[012])M)?([0]|[1-9]|[12][0-9]|3[01])D"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="TipoContrato" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoContrato" />
 *                 &lt;attribute name="Sindicalizado">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;whiteSpace value="collapse"/>
 *                       &lt;enumeration value="S�"/>
 *                       &lt;enumeration value="No"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="TipoJornada" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoJornada" />
 *                 &lt;attribute name="TipoRegimen" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoRegimen" />
 *                 &lt;attribute name="NumEmpleado" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="15"/>
 *                       &lt;whiteSpace value="collapse"/>
 *                       &lt;pattern value="([A-Z]|[a-z]|[0-9]|�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,15}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="Departamento">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="100"/>
 *                       &lt;whiteSpace value="collapse"/>
 *                       &lt;pattern value="([A-Z]|[a-z]|[0-9]| |�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,100}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="Puesto">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;minLength value="1"/>
 *                       &lt;maxLength value="100"/>
 *                       &lt;whiteSpace value="collapse"/>
 *                       &lt;pattern value="([A-Z]|[a-z]|[0-9]| |�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,100}"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="RiesgoPuesto" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_RiesgoPuesto" />
 *                 &lt;attribute name="PeriodicidadPago" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_PeriodicidadPago" />
 *                 &lt;attribute name="Banco" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_Banco" />
 *                 &lt;attribute name="CuentaBancaria" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_CuentaBancaria" />
 *                 &lt;attribute name="SalarioBaseCotApor" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                 &lt;attribute name="SalarioDiarioIntegrado" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                 &lt;attribute name="ClaveEntFed" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos}c_Estado" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Percepciones" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Percepcion" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="AccionesOTitulos" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="ValorMercado" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                           &lt;fractionDigits value="6"/>
 *                                           &lt;minInclusive value="0.000001"/>
 *                                           &lt;whiteSpace value="collapse"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="PrecioAlOtorgarse" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                           &lt;fractionDigits value="6"/>
 *                                           &lt;minInclusive value="0.000001"/>
 *                                           &lt;whiteSpace value="collapse"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="HorasExtra" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="Dias" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                                           &lt;minInclusive value="1"/>
 *                                           &lt;whiteSpace value="collapse"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="TipoHoras" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoHoras" />
 *                                     &lt;attribute name="HorasExtra" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                                           &lt;minInclusive value="1"/>
 *                                           &lt;whiteSpace value="collapse"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="ImportePagado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="TipoPercepcion" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoPercepcion" />
 *                           &lt;attribute name="Clave" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="3"/>
 *                                 &lt;maxLength value="15"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                                 &lt;pattern value="([A-Z]|[a-z]|[0-9]|�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){3,15}"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Concepto" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="100"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                                 &lt;pattern value="([A-Z]|[a-z]|[0-9]| |�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,100}"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="ImporteGravado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                           &lt;attribute name="ImporteExento" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="JubilacionPensionRetiro" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="TotalUnaExhibicion" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                           &lt;attribute name="TotalParcialidad" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                           &lt;attribute name="MontoDiario" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                           &lt;attribute name="IngresoAcumulable" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                           &lt;attribute name="IngresoNoAcumulable" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="SeparacionIndemnizacion" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="TotalPagado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                           &lt;attribute name="NumA�osServicio" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                                 &lt;minInclusive value="1"/>
 *                                 &lt;maxInclusive value="75"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="UltimoSueldoMensOrd" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                           &lt;attribute name="IngresoAcumulable" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                           &lt;attribute name="IngresoNoAcumulable" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="TotalSueldos" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                 &lt;attribute name="TotalSeparacionIndemnizacion" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                 &lt;attribute name="TotalJubilacionPensionRetiro" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                 &lt;attribute name="TotalGravado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                 &lt;attribute name="TotalExento" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
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
 *                           &lt;attribute name="TipoDeduccion" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoDeduccion" />
 *                           &lt;attribute name="Clave" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="3"/>
 *                                 &lt;maxLength value="15"/>
 *                                 &lt;pattern value="([A-Z]|[a-z]|[0-9]|�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){3,15}"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Concepto" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="100"/>
 *                                 &lt;pattern value="([A-Z]|[a-z]|[0-9]| |�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,100}"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Importe" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="TotalOtrasDeducciones" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                 &lt;attribute name="TotalImpuestosRetenidos" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="OtrosPagos" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="OtroPago" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="SubsidioAlEmpleo" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="SubsidioCausado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="CompensacionSaldosAFavor" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="SaldoAFavor" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                                     &lt;attribute name="A�o" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *                                           &lt;minInclusive value="2016"/>
 *                                           &lt;whiteSpace value="collapse"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                     &lt;attribute name="RemanenteSalFav" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="TipoOtroPago" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoOtroPago" />
 *                           &lt;attribute name="Clave" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="3"/>
 *                                 &lt;maxLength value="15"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                                 &lt;pattern value="([A-Z]|[a-z]|[0-9]|�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){3,15}"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Concepto" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;minLength value="1"/>
 *                                 &lt;maxLength value="100"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                                 &lt;pattern value="([A-Z]|[a-z]|[0-9]| |�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,100}"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="Importe" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
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
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                                 &lt;minInclusive value="1"/>
 *                                 &lt;whiteSpace value="collapse"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="TipoIncapacidad" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoIncapacidad" />
 *                           &lt;attribute name="ImporteMonetario" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
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
 *       &lt;attribute name="Version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="1.2" />
 *       &lt;attribute name="TipoNomina" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoNomina" />
 *       &lt;attribute name="FechaPago" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_Fecha" />
 *       &lt;attribute name="FechaInicialPago" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_Fecha" />
 *       &lt;attribute name="FechaFinalPago" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_Fecha" />
 *       &lt;attribute name="NumDiasPagados" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;minInclusive value="0.001"/>
 *             &lt;maxInclusive value="5490.000"/>
 *             &lt;fractionDigits value="3"/>
 *             &lt;pattern value="(([1-9][0-9]{0,3})|[0])(.[0-9]{3})?"/>
 *             &lt;whiteSpace value="collapse"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="TotalPercepciones" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *       &lt;attribute name="TotalDeducciones" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *       &lt;attribute name="TotalOtrosPagos" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "emisor",
    "receptor",
    "percepciones",
    "deducciones",
    "otrosPagos",
    "incapacidades"
})
@XmlRootElement(name = "Nomina")
public class Nomina12 {

    @XmlElement(name = "Emisor")
    protected Nomina12.Emisor emisor;
    @XmlElement(name = "Receptor", required = true)
    protected Nomina12.Receptor receptor;
    @XmlElement(name = "Percepciones")
    protected Nomina12.Percepciones percepciones;
    @XmlElement(name = "Deducciones")
    protected Nomina12.Deducciones deducciones;
    @XmlElement(name = "OtrosPagos")
    protected Nomina12.OtrosPagos otrosPagos;
    @XmlElement(name = "Incapacidades")
    protected Nomina12.Incapacidades incapacidades;
    @XmlAttribute(name = "Version", required = true)
    protected String version;
    @XmlAttribute(name = "TipoNomina", required = true)
    protected CTipoNomina tipoNomina;    
    @XmlAttribute(name = "FechaPago", required = true)
    protected XMLGregorianCalendar fechaPago;
    @XmlAttribute(name = "FechaInicialPago", required = true)
    protected XMLGregorianCalendar fechaInicialPago;
    @XmlAttribute(name = "FechaFinalPago", required = true)
    protected XMLGregorianCalendar fechaFinalPago;
    @XmlAttribute(name = "NumDiasPagados", required = true)
    protected BigDecimal numDiasPagados;
    @XmlAttribute(name = "TotalPercepciones")
    protected BigDecimal totalPercepciones;
    @XmlAttribute(name = "TotalDeducciones")
    protected BigDecimal totalDeducciones;
    @XmlAttribute(name = "TotalOtrosPagos")
    protected BigDecimal totalOtrosPagos;

    /**
     * Obtiene el valor de la propiedad emisor.
     * 
     * @return
     *     possible object is
     *     {@link Nomina12.Emisor }
     *     
     */
    public Nomina12.Emisor getEmisor() {
        return emisor;
    }

    /**
     * Define el valor de la propiedad emisor.
     * 
     * @param value
     *     allowed object is
     *     {@link Nomina12.Emisor }
     *     
     */
    public void setEmisor(Nomina12.Emisor value) {
        this.emisor = value;
    }

    /**
     * Obtiene el valor de la propiedad receptor.
     * 
     * @return
     *     possible object is
     *     {@link Nomina12.Receptor }
     *     
     */
    public Nomina12.Receptor getReceptor() {
        return receptor;
    }

    /**
     * Define el valor de la propiedad receptor.
     * 
     * @param value
     *     allowed object is
     *     {@link Nomina12.Receptor }
     *     
     */
    public void setReceptor(Nomina12.Receptor value) {
        this.receptor = value;
    }

    /**
     * Obtiene el valor de la propiedad percepciones.
     * 
     * @return
     *     possible object is
     *     {@link Nomina12.Percepciones }
     *     
     */
    public Nomina12.Percepciones getPercepciones() {
        return percepciones;
    }

    /**
     * Define el valor de la propiedad percepciones.
     * 
     * @param value
     *     allowed object is
     *     {@link Nomina12.Percepciones }
     *     
     */
    public void setPercepciones(Nomina12.Percepciones value) {
        this.percepciones = value;
    }

    /**
     * Obtiene el valor de la propiedad deducciones.
     * 
     * @return
     *     possible object is
     *     {@link Nomina12.Deducciones }
     *     
     */
    public Nomina12.Deducciones getDeducciones() {
        return deducciones;
    }

    /**
     * Define el valor de la propiedad deducciones.
     * 
     * @param value
     *     allowed object is
     *     {@link Nomina12.Deducciones }
     *     
     */
    public void setDeducciones(Nomina12.Deducciones value) {
        this.deducciones = value;
    }

    /**
     * Obtiene el valor de la propiedad otrosPagos.
     * 
     * @return
     *     possible object is
     *     {@link Nomina12.OtrosPagos }
     *     
     */
    public Nomina12.OtrosPagos getOtrosPagos() {
        return otrosPagos;
    }

    /**
     * Define el valor de la propiedad otrosPagos.
     * 
     * @param value
     *     allowed object is
     *     {@link Nomina12.OtrosPagos }
     *     
     */
    public void setOtrosPagos(Nomina12.OtrosPagos value) {
        this.otrosPagos = value;
    }

    /**
     * Obtiene el valor de la propiedad incapacidades.
     * 
     * @return
     *     possible object is
     *     {@link Nomina12.Incapacidades }
     *     
     */
    public Nomina12.Incapacidades getIncapacidades() {
        return incapacidades;
    }

    /**
     * Define el valor de la propiedad incapacidades.
     * 
     * @param value
     *     allowed object is
     *     {@link Nomina12.Incapacidades }
     *     
     */
    public void setIncapacidades(Nomina12.Incapacidades value) {
        this.incapacidades = value;
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
            return "1.2";
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
     * Obtiene el valor de la propiedad tipoNomina.
     * 
     * @return
     *     possible object is
     *     {@link CTipoNomina }
     *     
     */
    public CTipoNomina getTipoNomina() {
        return tipoNomina;
    }

    /**
     * Define el valor de la propiedad tipoNomina.
     * 
     * @param value
     *     allowed object is
     *     {@link CTipoNomina }
     *     
     */
    public void setTipoNomina(CTipoNomina value) {
        this.tipoNomina = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaPago.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @XmlSchemaType(name="date")
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
    @XmlSchemaType(name="date")
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
    @XmlSchemaType(name="date")
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
     * Obtiene el valor de la propiedad totalPercepciones.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalPercepciones() {
        return totalPercepciones;
    }

    /**
     * Define el valor de la propiedad totalPercepciones.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalPercepciones(BigDecimal value) {
        this.totalPercepciones = value;
    }

    /**
     * Obtiene el valor de la propiedad totalDeducciones.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalDeducciones() {
        return totalDeducciones;
    }

    /**
     * Define el valor de la propiedad totalDeducciones.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalDeducciones(BigDecimal value) {
        this.totalDeducciones = value;
    }

    /**
     * Obtiene el valor de la propiedad totalOtrosPagos.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalOtrosPagos() {
        return totalOtrosPagos;
    }

    /**
     * Define el valor de la propiedad totalOtrosPagos.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalOtrosPagos(BigDecimal value) {
        this.totalOtrosPagos = value;
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
     *                 &lt;attribute name="TipoDeduccion" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoDeduccion" />
     *                 &lt;attribute name="Clave" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;minLength value="3"/>
     *                       &lt;maxLength value="15"/>
     *                       &lt;pattern value="([A-Z]|[a-z]|[0-9]|�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){3,15}"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="Concepto" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;minLength value="1"/>
     *                       &lt;maxLength value="100"/>
     *                       &lt;pattern value="([A-Z]|[a-z]|[0-9]| |�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,100}"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="Importe" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="TotalOtrasDeducciones" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *       &lt;attribute name="TotalImpuestosRetenidos" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
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
        protected List<Nomina12.Deducciones.Deduccion> deduccion;
        @XmlAttribute(name = "TotalOtrasDeducciones")
        protected BigDecimal totalOtrasDeducciones;
        @XmlAttribute(name = "TotalImpuestosRetenidos")
        protected BigDecimal totalImpuestosRetenidos;

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
         * {@link Nomina12.Deducciones.Deduccion }
         * 
         * 
         */
        public List<Nomina12.Deducciones.Deduccion> getDeduccion() {
            if (deduccion == null) {
                deduccion = new ArrayList<Nomina12.Deducciones.Deduccion>();
            }
            return this.deduccion;
        }

        /**
         * Obtiene el valor de la propiedad totalOtrasDeducciones.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalOtrasDeducciones() {
            return totalOtrasDeducciones;
        }

        /**
         * Define el valor de la propiedad totalOtrasDeducciones.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalOtrasDeducciones(BigDecimal value) {
            this.totalOtrasDeducciones = value;
        }

        /**
         * Obtiene el valor de la propiedad totalImpuestosRetenidos.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalImpuestosRetenidos() {
            return totalImpuestosRetenidos;
        }

        /**
         * Define el valor de la propiedad totalImpuestosRetenidos.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalImpuestosRetenidos(BigDecimal value) {
            this.totalImpuestosRetenidos = value;
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
         *       &lt;attribute name="TipoDeduccion" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoDeduccion" />
         *       &lt;attribute name="Clave" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;minLength value="3"/>
         *             &lt;maxLength value="15"/>
         *             &lt;pattern value="([A-Z]|[a-z]|[0-9]|�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){3,15}"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="Concepto" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;minLength value="1"/>
         *             &lt;maxLength value="100"/>
         *             &lt;pattern value="([A-Z]|[a-z]|[0-9]| |�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,100}"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="Importe" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
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
            @XmlAttribute(name = "Importe", required = true)
            protected BigDecimal importe;

            /**
             * Obtiene el valor de la propiedad tipoDeduccion.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTipoDeduccion() {
                return tipoDeduccion;
            }

            /**
             * Define el valor de la propiedad tipoDeduccion.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
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
             * Obtiene el valor de la propiedad importe.
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
             * Define el valor de la propiedad importe.
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
     *         &lt;element name="EntidadSNCF" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="OrigenRecurso" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_OrigenRecurso" />
     *                 &lt;attribute name="MontoRecursoPropio" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="Curp" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_CURP" />
     *       &lt;attribute name="RegistroPatronal">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;maxLength value="20"/>
     *             &lt;pattern value="([A-Z]|[a-z]|[0-9]|�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,20}"/>
     *             &lt;whiteSpace value="collapse"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="RfcPatronOrigen" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_RFC" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entidadSNCF"
    })
    public static class Emisor {

        @XmlElement(name = "EntidadSNCF")
        protected Nomina12.Emisor.EntidadSNCF entidadSNCF;
        @XmlAttribute(name = "Curp")
        protected String curp;
        @XmlAttribute(name = "RegistroPatronal")
        protected String registroPatronal;
        @XmlAttribute(name = "RfcPatronOrigen")
        protected String rfcPatronOrigen;

        /**
         * Obtiene el valor de la propiedad entidadSNCF.
         * 
         * @return
         *     possible object is
         *     {@link Nomina12.Emisor.EntidadSNCF }
         *     
         */
        public Nomina12.Emisor.EntidadSNCF getEntidadSNCF() {
            return entidadSNCF;
        }

        /**
         * Define el valor de la propiedad entidadSNCF.
         * 
         * @param value
         *     allowed object is
         *     {@link Nomina12.Emisor.EntidadSNCF }
         *     
         */
        public void setEntidadSNCF(Nomina12.Emisor.EntidadSNCF value) {
            this.entidadSNCF = value;
        }

        /**
         * Obtiene el valor de la propiedad curp.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCurp() {
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
        public void setCurp(String value) {
            this.curp = value;
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
         * Obtiene el valor de la propiedad rfcPatronOrigen.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRfcPatronOrigen() {
            return rfcPatronOrigen;
        }

        /**
         * Define el valor de la propiedad rfcPatronOrigen.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRfcPatronOrigen(String value) {
            this.rfcPatronOrigen = value;
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
         *       &lt;attribute name="OrigenRecurso" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_OrigenRecurso" />
         *       &lt;attribute name="MontoRecursoPropio" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class EntidadSNCF {

            @XmlAttribute(name = "OrigenRecurso", required = true)
            protected COrigenRecurso origenRecurso;
            @XmlAttribute(name = "MontoRecursoPropio")
            protected BigDecimal montoRecursoPropio;

            /**
             * Obtiene el valor de la propiedad origenRecurso.
             * 
             * @return
             *     possible object is
             *     {@link COrigenRecurso }
             *     
             */
            public COrigenRecurso getOrigenRecurso() {
                return origenRecurso;
            }

            /**
             * Define el valor de la propiedad origenRecurso.
             * 
             * @param value
             *     allowed object is
             *     {@link COrigenRecurso }
             *     
             */
            public void setOrigenRecurso(COrigenRecurso value) {
                this.origenRecurso = value;
            }

            /**
             * Obtiene el valor de la propiedad montoRecursoPropio.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getMontoRecursoPropio() {
                return montoRecursoPropio;
            }

            /**
             * Define el valor de la propiedad montoRecursoPropio.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setMontoRecursoPropio(BigDecimal value) {
                this.montoRecursoPropio = value;
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
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *                       &lt;minInclusive value="1"/>
     *                       &lt;whiteSpace value="collapse"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="TipoIncapacidad" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoIncapacidad" />
     *                 &lt;attribute name="ImporteMonetario" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
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
        protected List<Nomina12.Incapacidades.Incapacidad> incapacidad;

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
         * {@link Nomina12.Incapacidades.Incapacidad }
         * 
         * 
         */
        public List<Nomina12.Incapacidades.Incapacidad> getIncapacidad() {
            if (incapacidad == null) {
                incapacidad = new ArrayList<Nomina12.Incapacidades.Incapacidad>();
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
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
         *             &lt;minInclusive value="1"/>
         *             &lt;whiteSpace value="collapse"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="TipoIncapacidad" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoIncapacidad" />
         *       &lt;attribute name="ImporteMonetario" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
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
            protected int diasIncapacidad;
            @XmlAttribute(name = "TipoIncapacidad", required = true)
            protected String tipoIncapacidad;
            @XmlAttribute(name = "ImporteMonetario")
            protected BigDecimal importeMonetario;

            /**
             * Obtiene el valor de la propiedad diasIncapacidad.
             * 
             */
            public int getDiasIncapacidad() {
                return diasIncapacidad;
            }

            /**
             * Define el valor de la propiedad diasIncapacidad.
             * 
             */
            public void setDiasIncapacidad(int value) {
                this.diasIncapacidad = value;
            }

            /**
             * Obtiene el valor de la propiedad tipoIncapacidad.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTipoIncapacidad() {
                return tipoIncapacidad;
            }

            /**
             * Define el valor de la propiedad tipoIncapacidad.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTipoIncapacidad(String value) {
                this.tipoIncapacidad = value;
            }

            /**
             * Obtiene el valor de la propiedad importeMonetario.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getImporteMonetario() {
                return importeMonetario;
            }

            /**
             * Define el valor de la propiedad importeMonetario.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setImporteMonetario(BigDecimal value) {
                this.importeMonetario = value;
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
     *         &lt;element name="OtroPago" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="SubsidioAlEmpleo" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="SubsidioCausado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="CompensacionSaldosAFavor" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="SaldoAFavor" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *                           &lt;attribute name="A�o" use="required">
     *                             &lt;simpleType>
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
     *                                 &lt;minInclusive value="2016"/>
     *                                 &lt;whiteSpace value="collapse"/>
     *                               &lt;/restriction>
     *                             &lt;/simpleType>
     *                           &lt;/attribute>
     *                           &lt;attribute name="RemanenteSalFav" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="TipoOtroPago" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoOtroPago" />
     *                 &lt;attribute name="Clave" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;minLength value="3"/>
     *                       &lt;maxLength value="15"/>
     *                       &lt;whiteSpace value="collapse"/>
     *                       &lt;pattern value="([A-Z]|[a-z]|[0-9]|�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){3,15}"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="Concepto" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;minLength value="1"/>
     *                       &lt;maxLength value="100"/>
     *                       &lt;whiteSpace value="collapse"/>
     *                       &lt;pattern value="([A-Z]|[a-z]|[0-9]| |�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,100}"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="Importe" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
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
        "otroPago"
    })
    public static class OtrosPagos {

        @XmlElement(name = "OtroPago", required = true)
        protected List<Nomina12.OtrosPagos.OtroPago> otroPago;

        /**
         * Gets the value of the otroPago property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the otroPago property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOtroPago().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Nomina12.OtrosPagos.OtroPago }
         * 
         * 
         */
        public List<Nomina12.OtrosPagos.OtroPago> getOtroPago() {
            if (otroPago == null) {
                otroPago = new ArrayList<Nomina12.OtrosPagos.OtroPago>();
            }
            return this.otroPago;
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
         *         &lt;element name="SubsidioAlEmpleo" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="SubsidioCausado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="CompensacionSaldosAFavor" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="SaldoAFavor" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *                 &lt;attribute name="A�o" use="required">
         *                   &lt;simpleType>
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
         *                       &lt;minInclusive value="2016"/>
         *                       &lt;whiteSpace value="collapse"/>
         *                     &lt;/restriction>
         *                   &lt;/simpleType>
         *                 &lt;/attribute>
         *                 &lt;attribute name="RemanenteSalFav" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="TipoOtroPago" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoOtroPago" />
         *       &lt;attribute name="Clave" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;minLength value="3"/>
         *             &lt;maxLength value="15"/>
         *             &lt;whiteSpace value="collapse"/>
         *             &lt;pattern value="([A-Z]|[a-z]|[0-9]|�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){3,15}"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="Concepto" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;minLength value="1"/>
         *             &lt;maxLength value="100"/>
         *             &lt;whiteSpace value="collapse"/>
         *             &lt;pattern value="([A-Z]|[a-z]|[0-9]| |�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,100}"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="Importe" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "subsidioAlEmpleo",
            "compensacionSaldosAFavor"
        })
        public static class OtroPago {

            @XmlElement(name = "SubsidioAlEmpleo")
            protected Nomina12.OtrosPagos.OtroPago.SubsidioAlEmpleo subsidioAlEmpleo;
            @XmlElement(name = "CompensacionSaldosAFavor")
            protected Nomina12.OtrosPagos.OtroPago.CompensacionSaldosAFavor compensacionSaldosAFavor;
            @XmlAttribute(name = "TipoOtroPago", required = true)
            protected String tipoOtroPago;
            @XmlAttribute(name = "Clave", required = true)
            protected String clave;
            @XmlAttribute(name = "Concepto", required = true)
            protected String concepto;
            @XmlAttribute(name = "Importe", required = true)
            protected BigDecimal importe;

            /**
             * Obtiene el valor de la propiedad subsidioAlEmpleo.
             * 
             * @return
             *     possible object is
             *     {@link Nomina12.OtrosPagos.OtroPago.SubsidioAlEmpleo }
             *     
             */
            public Nomina12.OtrosPagos.OtroPago.SubsidioAlEmpleo getSubsidioAlEmpleo() {
                return subsidioAlEmpleo;
            }

            /**
             * Define el valor de la propiedad subsidioAlEmpleo.
             * 
             * @param value
             *     allowed object is
             *     {@link Nomina12.OtrosPagos.OtroPago.SubsidioAlEmpleo }
             *     
             */
            public void setSubsidioAlEmpleo(Nomina12.OtrosPagos.OtroPago.SubsidioAlEmpleo value) {
                this.subsidioAlEmpleo = value;
            }

            /**
             * Obtiene el valor de la propiedad compensacionSaldosAFavor.
             * 
             * @return
             *     possible object is
             *     {@link Nomina12.OtrosPagos.OtroPago.CompensacionSaldosAFavor }
             *     
             */
            public Nomina12.OtrosPagos.OtroPago.CompensacionSaldosAFavor getCompensacionSaldosAFavor() {
                return compensacionSaldosAFavor;
            }

            /**
             * Define el valor de la propiedad compensacionSaldosAFavor.
             * 
             * @param value
             *     allowed object is
             *     {@link Nomina12.OtrosPagos.OtroPago.CompensacionSaldosAFavor }
             *     
             */
            public void setCompensacionSaldosAFavor(Nomina12.OtrosPagos.OtroPago.CompensacionSaldosAFavor value) {
                this.compensacionSaldosAFavor = value;
            }

            /**
             * Obtiene el valor de la propiedad tipoOtroPago.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTipoOtroPago() {
                return tipoOtroPago;
            }

            /**
             * Define el valor de la propiedad tipoOtroPago.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTipoOtroPago(String value) {
                this.tipoOtroPago = value;
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
             * Obtiene el valor de la propiedad importe.
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
             * Define el valor de la propiedad importe.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setImporte(BigDecimal value) {
                this.importe = value;
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
             *       &lt;attribute name="SaldoAFavor" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
             *       &lt;attribute name="A�o" use="required">
             *         &lt;simpleType>
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
             *             &lt;minInclusive value="2016"/>
             *             &lt;whiteSpace value="collapse"/>
             *           &lt;/restriction>
             *         &lt;/simpleType>
             *       &lt;/attribute>
             *       &lt;attribute name="RemanenteSalFav" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class CompensacionSaldosAFavor {

                @XmlAttribute(name = "SaldoAFavor", required = true)
                protected BigDecimal saldoAFavor;
                @XmlAttribute(name = "A\u00f1o", required = true)
                protected short anyo;
                @XmlAttribute(name = "RemanenteSalFav", required = true)
                protected BigDecimal remanenteSalFav;

                /**
                 * Obtiene el valor de la propiedad saldoAFavor.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getSaldoAFavor() {
                    return saldoAFavor;
                }

                /**
                 * Define el valor de la propiedad saldoAFavor.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setSaldoAFavor(BigDecimal value) {
                    this.saldoAFavor = value;
                }

                /**
                 * Obtiene el valor de la propiedad a�o.
                 * 
                 */
                public short getAnyo() {
                    return anyo;
                }

                /**
                 * Define el valor de la propiedad a�o.
                 * 
                 */
                public void setAnyo(short value) {
                    this.anyo = value;
                }

                /**
                 * Obtiene el valor de la propiedad remanenteSalFav.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getRemanenteSalFav() {
                    return remanenteSalFav;
                }

                /**
                 * Define el valor de la propiedad remanenteSalFav.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setRemanenteSalFav(BigDecimal value) {
                    this.remanenteSalFav = value;
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
             *       &lt;attribute name="SubsidioCausado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class SubsidioAlEmpleo {

                @XmlAttribute(name = "SubsidioCausado", required = true)
                protected BigDecimal subsidioCausado;

                /**
                 * Obtiene el valor de la propiedad subsidioCausado.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getSubsidioCausado() {
                    return subsidioCausado;
                }

                /**
                 * Define el valor de la propiedad subsidioCausado.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setSubsidioCausado(BigDecimal value) {
                    this.subsidioCausado = value;
                }

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
     *                 &lt;sequence>
     *                   &lt;element name="AccionesOTitulos" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="ValorMercado" use="required">
     *                             &lt;simpleType>
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                 &lt;fractionDigits value="6"/>
     *                                 &lt;minInclusive value="0.000001"/>
     *                                 &lt;whiteSpace value="collapse"/>
     *                               &lt;/restriction>
     *                             &lt;/simpleType>
     *                           &lt;/attribute>
     *                           &lt;attribute name="PrecioAlOtorgarse" use="required">
     *                             &lt;simpleType>
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                 &lt;fractionDigits value="6"/>
     *                                 &lt;minInclusive value="0.000001"/>
     *                                 &lt;whiteSpace value="collapse"/>
     *                               &lt;/restriction>
     *                             &lt;/simpleType>
     *                           &lt;/attribute>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="HorasExtra" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="Dias" use="required">
     *                             &lt;simpleType>
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *                                 &lt;minInclusive value="1"/>
     *                                 &lt;whiteSpace value="collapse"/>
     *                               &lt;/restriction>
     *                             &lt;/simpleType>
     *                           &lt;/attribute>
     *                           &lt;attribute name="TipoHoras" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoHoras" />
     *                           &lt;attribute name="HorasExtra" use="required">
     *                             &lt;simpleType>
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *                                 &lt;minInclusive value="1"/>
     *                                 &lt;whiteSpace value="collapse"/>
     *                               &lt;/restriction>
     *                             &lt;/simpleType>
     *                           &lt;/attribute>
     *                           &lt;attribute name="ImportePagado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="TipoPercepcion" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoPercepcion" />
     *                 &lt;attribute name="Clave" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;minLength value="3"/>
     *                       &lt;maxLength value="15"/>
     *                       &lt;whiteSpace value="collapse"/>
     *                       &lt;pattern value="([A-Z]|[a-z]|[0-9]|�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){3,15}"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="Concepto" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;minLength value="1"/>
     *                       &lt;maxLength value="100"/>
     *                       &lt;whiteSpace value="collapse"/>
     *                       &lt;pattern value="([A-Z]|[a-z]|[0-9]| |�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,100}"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="ImporteGravado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *                 &lt;attribute name="ImporteExento" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="JubilacionPensionRetiro" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="TotalUnaExhibicion" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *                 &lt;attribute name="TotalParcialidad" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *                 &lt;attribute name="MontoDiario" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *                 &lt;attribute name="IngresoAcumulable" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *                 &lt;attribute name="IngresoNoAcumulable" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="SeparacionIndemnizacion" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="TotalPagado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *                 &lt;attribute name="NumA�osServicio" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *                       &lt;minInclusive value="1"/>
     *                       &lt;maxInclusive value="75"/>
     *                       &lt;whiteSpace value="collapse"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="UltimoSueldoMensOrd" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *                 &lt;attribute name="IngresoAcumulable" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *                 &lt;attribute name="IngresoNoAcumulable" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="TotalSueldos" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *       &lt;attribute name="TotalSeparacionIndemnizacion" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *       &lt;attribute name="TotalJubilacionPensionRetiro" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *       &lt;attribute name="TotalGravado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *       &lt;attribute name="TotalExento" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "percepcion",
        "jubilacionPensionRetiro",
        "separacionIndemnizacion"
    })
    public static class Percepciones {

        @XmlElement(name = "Percepcion", required = true)
        protected List<Nomina12.Percepciones.Percepcion> percepcion;
        @XmlElement(name = "JubilacionPensionRetiro")
        protected Nomina12.Percepciones.JubilacionPensionRetiro jubilacionPensionRetiro;
        @XmlElement(name = "SeparacionIndemnizacion")
        protected Nomina12.Percepciones.SeparacionIndemnizacion separacionIndemnizacion;
        @XmlAttribute(name = "TotalSueldos")
        protected BigDecimal totalSueldos;
        @XmlAttribute(name = "TotalSeparacionIndemnizacion")
        protected BigDecimal totalSeparacionIndemnizacion;
        @XmlAttribute(name = "TotalJubilacionPensionRetiro")
        protected BigDecimal totalJubilacionPensionRetiro;
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
         * {@link Nomina12.Percepciones.Percepcion }
         * 
         * 
         */
        public List<Nomina12.Percepciones.Percepcion> getPercepcion() {
            if (percepcion == null) {
                percepcion = new ArrayList<Nomina12.Percepciones.Percepcion>();
            }
            return this.percepcion;
        }

        /**
         * Obtiene el valor de la propiedad jubilacionPensionRetiro.
         * 
         * @return
         *     possible object is
         *     {@link Nomina12.Percepciones.JubilacionPensionRetiro }
         *     
         */
        public Nomina12.Percepciones.JubilacionPensionRetiro getJubilacionPensionRetiro() {
            return jubilacionPensionRetiro;
        }

        /**
         * Define el valor de la propiedad jubilacionPensionRetiro.
         * 
         * @param value
         *     allowed object is
         *     {@link Nomina12.Percepciones.JubilacionPensionRetiro }
         *     
         */
        public void setJubilacionPensionRetiro(Nomina12.Percepciones.JubilacionPensionRetiro value) {
            this.jubilacionPensionRetiro = value;
        }

        /**
         * Obtiene el valor de la propiedad separacionIndemnizacion.
         * 
         * @return
         *     possible object is
         *     {@link Nomina12.Percepciones.SeparacionIndemnizacion }
         *     
         */
        public Nomina12.Percepciones.SeparacionIndemnizacion getSeparacionIndemnizacion() {
            return separacionIndemnizacion;
        }

        /**
         * Define el valor de la propiedad separacionIndemnizacion.
         * 
         * @param value
         *     allowed object is
         *     {@link Nomina12.Percepciones.SeparacionIndemnizacion }
         *     
         */
        public void setSeparacionIndemnizacion(Nomina12.Percepciones.SeparacionIndemnizacion value) {
            this.separacionIndemnizacion = value;
        }

        /**
         * Obtiene el valor de la propiedad totalSueldos.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalSueldos() {
            return totalSueldos;
        }

        /**
         * Define el valor de la propiedad totalSueldos.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalSueldos(BigDecimal value) {
            this.totalSueldos = value;
        }

        /**
         * Obtiene el valor de la propiedad totalSeparacionIndemnizacion.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalSeparacionIndemnizacion() {
            return totalSeparacionIndemnizacion;
        }

        /**
         * Define el valor de la propiedad totalSeparacionIndemnizacion.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalSeparacionIndemnizacion(BigDecimal value) {
            this.totalSeparacionIndemnizacion = value;
        }

        /**
         * Obtiene el valor de la propiedad totalJubilacionPensionRetiro.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalJubilacionPensionRetiro() {
            return totalJubilacionPensionRetiro;
        }

        /**
         * Define el valor de la propiedad totalJubilacionPensionRetiro.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalJubilacionPensionRetiro(BigDecimal value) {
            this.totalJubilacionPensionRetiro = value;
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
         *       &lt;attribute name="TotalUnaExhibicion" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *       &lt;attribute name="TotalParcialidad" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *       &lt;attribute name="MontoDiario" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *       &lt;attribute name="IngresoAcumulable" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *       &lt;attribute name="IngresoNoAcumulable" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class JubilacionPensionRetiro {

            @XmlAttribute(name = "TotalUnaExhibicion")
            protected BigDecimal totalUnaExhibicion;
            @XmlAttribute(name = "TotalParcialidad")
            protected BigDecimal totalParcialidad;
            @XmlAttribute(name = "MontoDiario")
            protected BigDecimal montoDiario;
            @XmlAttribute(name = "IngresoAcumulable", required = true)
            protected BigDecimal ingresoAcumulable;
            @XmlAttribute(name = "IngresoNoAcumulable", required = true)
            protected BigDecimal ingresoNoAcumulable;

            /**
             * Obtiene el valor de la propiedad totalUnaExhibicion.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getTotalUnaExhibicion() {
                return totalUnaExhibicion;
            }

            /**
             * Define el valor de la propiedad totalUnaExhibicion.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setTotalUnaExhibicion(BigDecimal value) {
                this.totalUnaExhibicion = value;
            }

            /**
             * Obtiene el valor de la propiedad totalParcialidad.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getTotalParcialidad() {
                return totalParcialidad;
            }

            /**
             * Define el valor de la propiedad totalParcialidad.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setTotalParcialidad(BigDecimal value) {
                this.totalParcialidad = value;
            }

            /**
             * Obtiene el valor de la propiedad montoDiario.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getMontoDiario() {
                return montoDiario;
            }

            /**
             * Define el valor de la propiedad montoDiario.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setMontoDiario(BigDecimal value) {
                this.montoDiario = value;
            }

            /**
             * Obtiene el valor de la propiedad ingresoAcumulable.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getIngresoAcumulable() {
                return ingresoAcumulable;
            }

            /**
             * Define el valor de la propiedad ingresoAcumulable.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setIngresoAcumulable(BigDecimal value) {
                this.ingresoAcumulable = value;
            }

            /**
             * Obtiene el valor de la propiedad ingresoNoAcumulable.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getIngresoNoAcumulable() {
                return ingresoNoAcumulable;
            }

            /**
             * Define el valor de la propiedad ingresoNoAcumulable.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setIngresoNoAcumulable(BigDecimal value) {
                this.ingresoNoAcumulable = value;
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
         *         &lt;element name="AccionesOTitulos" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="ValorMercado" use="required">
         *                   &lt;simpleType>
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                       &lt;fractionDigits value="6"/>
         *                       &lt;minInclusive value="0.000001"/>
         *                       &lt;whiteSpace value="collapse"/>
         *                     &lt;/restriction>
         *                   &lt;/simpleType>
         *                 &lt;/attribute>
         *                 &lt;attribute name="PrecioAlOtorgarse" use="required">
         *                   &lt;simpleType>
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                       &lt;fractionDigits value="6"/>
         *                       &lt;minInclusive value="0.000001"/>
         *                       &lt;whiteSpace value="collapse"/>
         *                     &lt;/restriction>
         *                   &lt;/simpleType>
         *                 &lt;/attribute>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="HorasExtra" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="Dias" use="required">
         *                   &lt;simpleType>
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
         *                       &lt;minInclusive value="1"/>
         *                       &lt;whiteSpace value="collapse"/>
         *                     &lt;/restriction>
         *                   &lt;/simpleType>
         *                 &lt;/attribute>
         *                 &lt;attribute name="TipoHoras" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoHoras" />
         *                 &lt;attribute name="HorasExtra" use="required">
         *                   &lt;simpleType>
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
         *                       &lt;minInclusive value="1"/>
         *                       &lt;whiteSpace value="collapse"/>
         *                     &lt;/restriction>
         *                   &lt;/simpleType>
         *                 &lt;/attribute>
         *                 &lt;attribute name="ImportePagado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="TipoPercepcion" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoPercepcion" />
         *       &lt;attribute name="Clave" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;minLength value="3"/>
         *             &lt;maxLength value="15"/>
         *             &lt;whiteSpace value="collapse"/>
         *             &lt;pattern value="([A-Z]|[a-z]|[0-9]|�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){3,15}"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="Concepto" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;minLength value="1"/>
         *             &lt;maxLength value="100"/>
         *             &lt;whiteSpace value="collapse"/>
         *             &lt;pattern value="([A-Z]|[a-z]|[0-9]| |�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,100}"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="ImporteGravado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *       &lt;attribute name="ImporteExento" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "accionesOTitulos",
            "horasExtra"
        })
        public static class Percepcion {

            @XmlElement(name = "AccionesOTitulos")
            protected Nomina12.Percepciones.Percepcion.AccionesOTitulos accionesOTitulos;
            @XmlElement(name = "HorasExtra")
            protected List<Nomina12.Percepciones.Percepcion.HorasExtra> horasExtra;
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
             * Obtiene el valor de la propiedad accionesOTitulos.
             * 
             * @return
             *     possible object is
             *     {@link Nomina12.Percepciones.Percepcion.AccionesOTitulos }
             *     
             */
            public Nomina12.Percepciones.Percepcion.AccionesOTitulos getAccionesOTitulos() {
                return accionesOTitulos;
            }

            /**
             * Define el valor de la propiedad accionesOTitulos.
             * 
             * @param value
             *     allowed object is
             *     {@link Nomina12.Percepciones.Percepcion.AccionesOTitulos }
             *     
             */
            public void setAccionesOTitulos(Nomina12.Percepciones.Percepcion.AccionesOTitulos value) {
                this.accionesOTitulos = value;
            }

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
             * {@link Nomina12.Percepciones.Percepcion.HorasExtra }
             * 
             * 
             */
            public List<Nomina12.Percepciones.Percepcion.HorasExtra> getHorasExtra() {
                if (horasExtra == null) {
                    horasExtra = new ArrayList<Nomina12.Percepciones.Percepcion.HorasExtra>();
                }
                return this.horasExtra;
            }

            /**
             * Obtiene el valor de la propiedad tipoPercepcion.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTipoPercepcion() {
                return tipoPercepcion;
            }

            /**
             * Define el valor de la propiedad tipoPercepcion.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
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


            /**
             * <p>Clase Java para anonymous complex type.
             * 
             * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="ValorMercado" use="required">
             *         &lt;simpleType>
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *             &lt;fractionDigits value="6"/>
             *             &lt;minInclusive value="0.000001"/>
             *             &lt;whiteSpace value="collapse"/>
             *           &lt;/restriction>
             *         &lt;/simpleType>
             *       &lt;/attribute>
             *       &lt;attribute name="PrecioAlOtorgarse" use="required">
             *         &lt;simpleType>
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *             &lt;fractionDigits value="6"/>
             *             &lt;minInclusive value="0.000001"/>
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
            public static class AccionesOTitulos {

                @XmlAttribute(name = "ValorMercado", required = true)
                protected BigDecimal valorMercado;
                @XmlAttribute(name = "PrecioAlOtorgarse", required = true)
                protected BigDecimal precioAlOtorgarse;

                /**
                 * Obtiene el valor de la propiedad valorMercado.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getValorMercado() {
                    return valorMercado;
                }

                /**
                 * Define el valor de la propiedad valorMercado.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setValorMercado(BigDecimal value) {
                    this.valorMercado = value;
                }

                /**
                 * Obtiene el valor de la propiedad precioAlOtorgarse.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getPrecioAlOtorgarse() {
                    return precioAlOtorgarse;
                }

                /**
                 * Define el valor de la propiedad precioAlOtorgarse.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setPrecioAlOtorgarse(BigDecimal value) {
                    this.precioAlOtorgarse = value;
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
             *       &lt;attribute name="Dias" use="required">
             *         &lt;simpleType>
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
             *             &lt;minInclusive value="1"/>
             *             &lt;whiteSpace value="collapse"/>
             *           &lt;/restriction>
             *         &lt;/simpleType>
             *       &lt;/attribute>
             *       &lt;attribute name="TipoHoras" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoHoras" />
             *       &lt;attribute name="HorasExtra" use="required">
             *         &lt;simpleType>
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
             *             &lt;minInclusive value="1"/>
             *             &lt;whiteSpace value="collapse"/>
             *           &lt;/restriction>
             *         &lt;/simpleType>
             *       &lt;/attribute>
             *       &lt;attribute name="ImportePagado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
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
         *       &lt;attribute name="TotalPagado" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *       &lt;attribute name="NumA�osServicio" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
         *             &lt;minInclusive value="1"/>
         *             &lt;maxInclusive value="75"/>
         *             &lt;whiteSpace value="collapse"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="UltimoSueldoMensOrd" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *       &lt;attribute name="IngresoAcumulable" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *       &lt;attribute name="IngresoNoAcumulable" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class SeparacionIndemnizacion {

            @XmlAttribute(name = "TotalPagado", required = true)
            protected BigDecimal totalPagado;
            @XmlAttribute(name = "NumA\u00f1osServicio", required = true)
            protected int numAnyosServicio;
            @XmlAttribute(name = "UltimoSueldoMensOrd", required = true)
            protected BigDecimal ultimoSueldoMensOrd;
            @XmlAttribute(name = "IngresoAcumulable", required = true)
            protected BigDecimal ingresoAcumulable;
            @XmlAttribute(name = "IngresoNoAcumulable", required = true)
            protected BigDecimal ingresoNoAcumulable;

            /**
             * Obtiene el valor de la propiedad totalPagado.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getTotalPagado() {
                return totalPagado;
            }

            /**
             * Define el valor de la propiedad totalPagado.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setTotalPagado(BigDecimal value) {
                this.totalPagado = value;
            }

            /**
             * Obtiene el valor de la propiedad numA�osServicio.
             * 
             */
            public int getNumAnyosServicio() {
                return numAnyosServicio;
            }

            /**
             * Define el valor de la propiedad numA�osServicio.
             * 
             */
            public void setNumAnyosServicio(int value) {
                this.numAnyosServicio = value;
            }

            /**
             * Obtiene el valor de la propiedad ultimoSueldoMensOrd.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getUltimoSueldoMensOrd() {
                return ultimoSueldoMensOrd;
            }

            /**
             * Define el valor de la propiedad ultimoSueldoMensOrd.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setUltimoSueldoMensOrd(BigDecimal value) {
                this.ultimoSueldoMensOrd = value;
            }

            /**
             * Obtiene el valor de la propiedad ingresoAcumulable.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getIngresoAcumulable() {
                return ingresoAcumulable;
            }

            /**
             * Define el valor de la propiedad ingresoAcumulable.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setIngresoAcumulable(BigDecimal value) {
                this.ingresoAcumulable = value;
            }

            /**
             * Obtiene el valor de la propiedad ingresoNoAcumulable.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getIngresoNoAcumulable() {
                return ingresoNoAcumulable;
            }

            /**
             * Define el valor de la propiedad ingresoNoAcumulable.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setIngresoNoAcumulable(BigDecimal value) {
                this.ingresoNoAcumulable = value;
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
     *         &lt;element name="SubContratacion" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="RfcLabora" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_RFC" />
     *                 &lt;attribute name="PorcentajeTiempo" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                       &lt;minInclusive value="0.001"/>
     *                       &lt;maxInclusive value="100.000"/>
     *                       &lt;whiteSpace value="collapse"/>
     *                       &lt;pattern value="[0-9]{1,3}(.([0-9]{1,3}))?"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="Curp" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_CURP" />
     *       &lt;attribute name="NumSeguridadSocial">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;maxLength value="15"/>
     *             &lt;whiteSpace value="collapse"/>
     *             &lt;pattern value="[0-9]{1,15}"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="FechaInicioRelLaboral" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_Fecha" />
     *       &lt;attribute name="Antig�edad">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;whiteSpace value="collapse"/>
     *             &lt;pattern value="P(([1-9][0-9]{0,3})|[0])W|P(([1-9][0-9]?Y)?([1-9]|1[012])M)?([0]|[1-9]|[12][0-9]|3[01])D"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="TipoContrato" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoContrato" />
     *       &lt;attribute name="Sindicalizado">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;whiteSpace value="collapse"/>
     *             &lt;enumeration value="S�"/>
     *             &lt;enumeration value="No"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="TipoJornada" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoJornada" />
     *       &lt;attribute name="TipoRegimen" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_TipoRegimen" />
     *       &lt;attribute name="NumEmpleado" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;maxLength value="15"/>
     *             &lt;whiteSpace value="collapse"/>
     *             &lt;pattern value="([A-Z]|[a-z]|[0-9]|�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,15}"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="Departamento">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;maxLength value="100"/>
     *             &lt;whiteSpace value="collapse"/>
     *             &lt;pattern value="([A-Z]|[a-z]|[0-9]| |�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,100}"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="Puesto">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;minLength value="1"/>
     *             &lt;maxLength value="100"/>
     *             &lt;whiteSpace value="collapse"/>
     *             &lt;pattern value="([A-Z]|[a-z]|[0-9]| |�|�|!|"|%|&amp;|'|�|-|:|;|>|=|&lt;|@|_|,|\{|\}|`|~|�|�|�|�|�|�|�|�|�|�|�|�){1,100}"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="RiesgoPuesto" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_RiesgoPuesto" />
     *       &lt;attribute name="PeriodicidadPago" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_PeriodicidadPago" />
     *       &lt;attribute name="Banco" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos/Nomina}c_Banco" />
     *       &lt;attribute name="CuentaBancaria" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_CuentaBancaria" />
     *       &lt;attribute name="SalarioBaseCotApor" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *       &lt;attribute name="SalarioDiarioIntegrado" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_ImporteMXN" />
     *       &lt;attribute name="ClaveEntFed" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/catalogos}c_Estado" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "subContratacion"
    })
    public static class Receptor {

        @XmlElement(name = "SubContratacion")
        protected List<Nomina12.Receptor.SubContratacion> subContratacion;
        @XmlAttribute(name = "Curp", required = true)
        protected String curp;
        @XmlAttribute(name = "NumSeguridadSocial")
        protected String numSeguridadSocial;
        @XmlAttribute(name = "FechaInicioRelLaboral")
        protected XMLGregorianCalendar fechaInicioRelLaboral;
        @XmlAttribute(name = "Antig\u00fcedad")
        protected String antiguedad;
        @XmlAttribute(name = "TipoContrato", required = true)
        protected String tipoContrato;
        @XmlAttribute(name = "Sindicalizado")
        protected String sindicalizado;
        @XmlAttribute(name = "TipoJornada")
        protected String tipoJornada;
        @XmlAttribute(name = "TipoRegimen", required = true)
        protected String tipoRegimen;
        @XmlAttribute(name = "NumEmpleado", required = true)
        protected String numEmpleado;
        @XmlAttribute(name = "Departamento")
        protected String departamento;
        @XmlAttribute(name = "Puesto")
        protected String puesto;
        @XmlAttribute(name = "RiesgoPuesto")
        protected String riesgoPuesto;
        @XmlAttribute(name = "PeriodicidadPago", required = true)
        protected String periodicidadPago;
        @XmlAttribute(name = "Banco")
        protected String banco;
        @XmlAttribute(name = "CuentaBancaria")
        protected BigInteger cuentaBancaria;
        @XmlAttribute(name = "SalarioBaseCotApor")
        protected BigDecimal salarioBaseCotApor;
        @XmlAttribute(name = "SalarioDiarioIntegrado")
        protected BigDecimal salarioDiarioIntegrado;
        @XmlAttribute(name = "ClaveEntFed", required = true)
        protected CEstado claveEntFed;

        /**
         * Gets the value of the subContratacion property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the subContratacion property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSubContratacion().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Nomina12.Receptor.SubContratacion }
         * 
         * 
         */
        public List<Nomina12.Receptor.SubContratacion> getSubContratacion() {
            if (subContratacion == null) {
                subContratacion = new ArrayList<Nomina12.Receptor.SubContratacion>();
            }
            return this.subContratacion;
        }

        /**
         * Obtiene el valor de la propiedad curp.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCurp() {
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
        public void setCurp(String value) {
            this.curp = value;
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
         * Obtiene el valor de la propiedad fechaInicioRelLaboral.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        @XmlSchemaType(name="date")
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
         * Obtiene el valor de la propiedad antig�edad.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAntiguedad() {
            return antiguedad;
        }

        /**
         * Define el valor de la propiedad antig�edad.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAntiguedad(String value) {
            this.antiguedad = value;
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
         * Obtiene el valor de la propiedad sindicalizado.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSindicalizado() {
            return sindicalizado;
        }

        /**
         * Define el valor de la propiedad sindicalizado.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSindicalizado(String value) {
            this.sindicalizado = value;
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
         * Obtiene el valor de la propiedad tipoRegimen.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipoRegimen() {
            return tipoRegimen;
        }

        /**
         * Define el valor de la propiedad tipoRegimen.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipoRegimen(String value) {
            this.tipoRegimen = value;
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
         * Obtiene el valor de la propiedad riesgoPuesto.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRiesgoPuesto() {
            return riesgoPuesto;
        }

        /**
         * Define el valor de la propiedad riesgoPuesto.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRiesgoPuesto(String value) {
            this.riesgoPuesto = value;
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
         * Obtiene el valor de la propiedad banco.
         * 
         * @return
         *     possible object is
         *     {@link String }
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
         *     {@link String }
         *     
         */
        public void setBanco(String value) {
            this.banco = value;
        }

        /**
         * Obtiene el valor de la propiedad cuentaBancaria.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getCuentaBancaria() {
            return cuentaBancaria;
        }

        /**
         * Define el valor de la propiedad cuentaBancaria.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setCuentaBancaria(BigInteger value) {
            this.cuentaBancaria = value;
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
         * Obtiene el valor de la propiedad claveEntFed.
         * 
         * @return
         *     possible object is
         *     {@link CEstado }
         *     
         */
        public CEstado getClaveEntFed() {
            return claveEntFed;
        }

        /**
         * Define el valor de la propiedad claveEntFed.
         * 
         * @param value
         *     allowed object is
         *     {@link CEstado }
         *     
         */
        public void setClaveEntFed(CEstado value) {
            this.claveEntFed = value;
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
         *       &lt;attribute name="RfcLabora" use="required" type="{http://www.sat.gob.mx/sitio_internet/cfd/tipoDatos/tdCFDI}t_RFC" />
         *       &lt;attribute name="PorcentajeTiempo" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *             &lt;minInclusive value="0.001"/>
         *             &lt;maxInclusive value="100.000"/>
         *             &lt;whiteSpace value="collapse"/>
         *             &lt;pattern value="[0-9]{1,3}(.([0-9]{1,3}))?"/>
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
        public static class SubContratacion {

            @XmlAttribute(name = "RfcLabora", required = true)
            protected String rfcLabora;
            @XmlAttribute(name = "PorcentajeTiempo", required = true)
            protected BigDecimal porcentajeTiempo;

            /**
             * Obtiene el valor de la propiedad rfcLabora.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRfcLabora() {
                return rfcLabora;
            }

            /**
             * Define el valor de la propiedad rfcLabora.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRfcLabora(String value) {
                this.rfcLabora = value;
            }

            /**
             * Obtiene el valor de la propiedad porcentajeTiempo.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPorcentajeTiempo() {
                return porcentajeTiempo;
            }

            /**
             * Define el valor de la propiedad porcentajeTiempo.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPorcentajeTiempo(BigDecimal value) {
                this.porcentajeTiempo = value;
            }

        }

    }

}
