
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "certificados_sellos_digitales")
public class CertificadoSelloDigitalEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -794771593796734356L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_certificado_sello_digital")
    private Integer idCertificadoSelloDigital;

    @Column(name = "archivo_key")
    private byte[] archivoKey;

    @Column(name = "certificado")
    private byte[] certificado;

    @Column(name = "clave")
    private String clave;

    @Column(name = "activo")
    private Integer activo;

    @Column(name = "numero_certificado")
    private String numeroCertificado;

    public String getNumeroCertificado() {
        return numeroCertificado;
    }

    public void setNumeroCertificado(String numeroCertificado) {
        this.numeroCertificado = numeroCertificado;
    }

    public Integer getIdCertificadoSelloDigital() {
        return idCertificadoSelloDigital;
    }

    public void setIdCertificadoSelloDigital(
            Integer idCertificadoSelloDigital) {
        this.idCertificadoSelloDigital = idCertificadoSelloDigital;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public byte[] getArchivoKey() {
        return archivoKey;
    }

    public void setArchivoKey(byte[] archivoKey) {
        this.archivoKey = archivoKey;
    }

    public byte[] getCertificado() {
        return certificado;
    }

    public void setCertificado(byte[] certificado) {
        this.certificado = certificado;
    }

}
