
package mx.gob.saludtlax.rh.nomina.exportarxml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.util.Configuracion;

public class ExportarXmlService {
    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void exportarXml() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL sp_exportar_comprobante()");
        query.setResultTransformer(Transformers.aliasToBean(TimbreExportarXmlDTO.class));

        @SuppressWarnings("unchecked")
        List<TimbreExportarXmlDTO> result = query.list();

        for (TimbreExportarXmlDTO xml : result) {
            exportarArchivo(xml);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void exportarArchivo(TimbreExportarXmlDTO xml) {
        try {
            OutputStream out = new FileOutputStream("C:\\xmlTimbres\\" + xml.getRfc() + "-" + xml.getUuid() + ".xml");
            out.write(xml.getComprobanteXml());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}