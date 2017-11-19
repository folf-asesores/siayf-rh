
package mx.gob.saludtlax.rh.nomina.exportarxml;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean(name = "exportarXml")
@ViewScoped
public class ExportarXmlController implements Serializable {
    private static final long serialVersionUID = -8317643392267752779L;

    @Inject
    private ExportarXmlService service;

    public void exportarXml() {
        service.exportarXml();
    }

}
