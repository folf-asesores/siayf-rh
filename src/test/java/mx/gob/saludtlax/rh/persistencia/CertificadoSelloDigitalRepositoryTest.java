
package mx.gob.saludtlax.rh.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import mx.gob.saludtlax.rh.util.InitTest;

import junit.framework.Assert;

@RunWith(Arquillian.class)
@Transactional
public class CertificadoSelloDigitalRepositoryTest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7282377562358184657L;

    @Inject
    CertificadoSelloDigitalRepository certificadoSelloDigitalRepository;

    @Deployment
    public static WebArchive createDeployment() {

        return InitTest.crearWar();
    }

    @Ignore
    @Test
    public void crearNuevoCertificadoSelloDigital() {

        FileInputStream fisKey = null;
        FileInputStream fisCer = null;
        File fKey = new File("C:\\Users\\JuanCarlosIvan\\Documents\\FOLF\\SIAYF–RH\\Certificado humanos 2014\\stl961105ht8_1403241914s.key");
        File fCer = new File("C:\\Users\\JuanCarlosIvan\\Documents\\FOLF\\SIAYF–RH\\Certificado humanos 2014\\00001000000303454456.cer");

        byte[] bKey = new byte[(int) fKey.length()];
        byte[] bCer = new byte[(int) fCer.length()];
        try {
            fisKey = new FileInputStream(fKey);
            fisCer = new FileInputStream(fCer);
            fisKey.read(bKey);
            fisCer.read(bCer);
            fisKey.close();
            fisCer.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        CertificadoSelloDigitalEntity certificadoSelloDigitalEntity = new CertificadoSelloDigitalEntity();
        certificadoSelloDigitalEntity.setActivo(1);
        certificadoSelloDigitalEntity.setArchivoKey(bKey);
        certificadoSelloDigitalEntity.setCertificado(bCer);
        certificadoSelloDigitalEntity.setClave("TalonRH14");
        certificadoSelloDigitalRepository.guardarNuevoCertificadoSelloDigital(certificadoSelloDigitalEntity);

        Assert.assertNotNull(certificadoSelloDigitalEntity.getIdCertificadoSelloDigital());

    }

}
