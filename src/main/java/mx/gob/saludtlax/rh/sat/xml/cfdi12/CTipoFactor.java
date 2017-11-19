//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen.
// Generado el: 2017.01.16 a las 04:13:02 PM CST
//

package mx.gob.saludtlax.rh.sat.xml.cfdi12;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para c_TipoFactor.
 *
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 *
 * <pre>
 * &lt;simpleType name="c_TipoFactor">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;whiteSpace value="collapse"/>
 *     &lt;enumeration value="Tasa"/>
 *     &lt;enumeration value="Cuota"/>
 *     &lt;enumeration value="Exento"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "c_TipoFactor", namespace = "http://www.sat.gob.mx/sitio_internet/cfd/catalogos")
@XmlEnum
public enum CTipoFactor {

    @XmlEnumValue("Tasa")
    TASA("Tasa"), @XmlEnumValue("Cuota")
    CUOTA("Cuota"), @XmlEnumValue("Exento")
    EXENTO("Exento");
    private final String value;

    CTipoFactor(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CTipoFactor fromValue(String v) {
        for (CTipoFactor c : CTipoFactor.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
