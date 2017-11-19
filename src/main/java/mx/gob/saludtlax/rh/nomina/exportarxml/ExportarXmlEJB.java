
package mx.gob.saludtlax.rh.nomina.exportarxml;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ExportarXmlEJB {
    @Inject
    private ExportarXmlService service;

    public void exportarXml() {
        service.exportarXml();
    }

}
