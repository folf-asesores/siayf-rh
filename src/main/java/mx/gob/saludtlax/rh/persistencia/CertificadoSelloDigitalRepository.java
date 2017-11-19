
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.util.Configuracion;

/**
 *
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
public class CertificadoSelloDigitalRepository implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5002950575573911645L;

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    public Integer guardarNuevoCertificadoSelloDigital(CertificadoSelloDigitalEntity certificadoSelloDigitalEntity) {

        Integer idCertificadoSelloDigital = null;

        entityManager.persist(certificadoSelloDigitalEntity);

        return idCertificadoSelloDigital;
    }

    public CertificadoSelloDigitalEntity obtenerCertificadoSelloDigitalActivo() {
        CertificadoSelloDigitalEntity certificadoSelloDigital = null;

        certificadoSelloDigital = entityManager
                .createQuery("SELECT c FROM CertificadoSelloDigitalEntity c WHERE c.activo = 1", CertificadoSelloDigitalEntity.class).getSingleResult();
        return certificadoSelloDigital;
    }

}
