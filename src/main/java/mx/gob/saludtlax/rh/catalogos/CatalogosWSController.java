
package mx.gob.saludtlax.rh.catalogos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.wsdl.sifoficial.ConsultaConceptosPartidaNominaResponseElementoConversion;
import mx.gob.saludtlax.rh.wsdl.sifoficial.ConsultaDetalleRetencionesResponseElementoRetencion;
import mx.gob.saludtlax.rh.wsdl.sifoficial.ConsultaTcode;

/**
 *
 * @author kisin-hp1 Eduardo N Castillo Caballero <eduardo.castillo.caballero@hotmail.com>
 * @version 1.0 10/01/2017
 */
@ManagedBean(name = "catalogosWs")
@SessionScoped
public class CatalogosWSController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8480746485295811124L;
    @Inject
    CatalogosWsEJB catalogoswsEJB;
    @Inject
    ConsultaTcode metodosServices;  // ws

    private List<ConceptoPartidaNominaDTO> listaConceptos = new ArrayList<>();
    private List<RetencionDTO> listaElementosRetenciones = new ArrayList<>();
    private Boolean panelConceptoPartidaNomina = Boolean.FALSE;
    private Boolean panelDetalleRetenciones = Boolean.FALSE;

    public void consultarConceptosPartidasNominas() {

        try {
            panelDetalleRetenciones = Boolean.FALSE;
            panelConceptoPartidaNomina = Boolean.TRUE;
            String cuentaRetencion = "";

            listaConceptos = new ArrayList<>();
            String token = "krt/jkwufs6uijcn/taa1lmovbqcjdzv4wkvsfgfnk=";
            String llaveTokent = metodosServices.getConsultaTcodePortType().firmarTokenBy5(token);
            System.out.println("llave :" + llaveTokent);

            ConsultaConceptosPartidaNominaResponseElementoConversion dto = metodosServices.getConsultaTcodePortType()
                    .consultaConceptosPartidaNomina(llaveTokent, cuentaRetencion);

            for (ConsultaConceptosPartidaNominaResponseElementoConversion.Element elemento : dto.getElement()) {

                ConceptoPartidaNominaDTO concepto = new ConceptoPartidaNominaDTO();
                concepto.setId_concepto_partida_nomina(elemento.getIdConceptoPartidaNomina());
                concepto.setId_concepto_nomina(elemento.getIdConceptoNomina());
                concepto.setDescripcion_concepto_nomina(elemento.getDescripcionConceptoNomina());
                concepto.setId_nombramiento(elemento.getIdNombramiento());
                concepto.setDescripcion_nombramiento(elemento.getDescripcionNombramiento());
                concepto.setId_partida(elemento.getIdPartida());
                concepto.setDescripcion_partida(elemento.getDescripcionPartida());

                listaConceptos.add(concepto);
            }

        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * consulta AL ws de detalles_retenciones
     */
    public void consultaRetenciones() {
        try {
            panelDetalleRetenciones = Boolean.TRUE;
            panelConceptoPartidaNomina = Boolean.FALSE;
            String cuentaRetencion = "";

            listaElementosRetenciones = new ArrayList<>();
            String token = "krt/jkwufs6uijcn/taa1lmovbqcjdzv4wkvsfgfnk=";
            String llaveTokent = metodosServices.getConsultaTcodePortType().firmarTokenBy5(token);
            System.out.println("llave :" + llaveTokent);

            ConsultaDetalleRetencionesResponseElementoRetencion dto = metodosServices.getConsultaTcodePortType().consultaDetalleRetenciones(llaveTokent,
                    cuentaRetencion);

            for (ConsultaDetalleRetencionesResponseElementoRetencion.Element elemento : dto.getElement()) {

                RetencionDTO concepto = new RetencionDTO();

                concepto.setId_detalle_retencion_cuenta_bancaria(elemento.getIdDetalleRetencionCuentaBancaria());
                concepto.setId_concepto_nomina(elemento.getIdConceptoNomina());
                concepto.setDescripcion_concepto_nomina(elemento.getDescripcionConceptoNomina());
                concepto.setCuenta_contable_retencion(elemento.getCuentaContableRetencion());
                concepto.setDescripcion_cuenta_contable(elemento.getDescripcionCuentaContable());
                concepto.setCuenta_retencion(elemento.getCuentaRetencion());
                concepto.setCuenta_contable_banco(elemento.getCuentaContableBanco());
                concepto.setDescripcion_cuenta_banco(elemento.getDescripcionCuentaBanco());

                listaElementosRetenciones.add(concepto);
            }

        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void actualizar() {
        System.out.println("debe mandar a actualizar a la bd");
        if (panelConceptoPartidaNomina) {
            System.out.println("va actualizar concepto_partida_nomina");
            catalogoswsEJB.guardarConceptosNominaPartida(listaConceptos);
            panelConceptoPartidaNomina = Boolean.FALSE;

        } else if (panelDetalleRetenciones) {
            catalogoswsEJB.guardarDetalleRetenciones(getListaElementosRetenciones());
            System.out.println("va actualizar rerenciones");
            panelDetalleRetenciones = Boolean.FALSE;
        }

    }

    public List<ConceptoPartidaNominaDTO> getListaConceptos() {
        return listaConceptos;
    }

    public void setListaConceptos(List<ConceptoPartidaNominaDTO> listaConceptos) {
        this.listaConceptos = listaConceptos;
    }

    public Boolean getPanelConceptoPartidaNomina() {
        return panelConceptoPartidaNomina;
    }

    public void setPanelConceptoPartidaNomina(Boolean panelConceptoPartidaNomina) {
        this.panelConceptoPartidaNomina = panelConceptoPartidaNomina;
    }

    public Boolean getPanelDetalleRetenciones() {
        return panelDetalleRetenciones;
    }

    public void setPanelDetalleRetenciones(Boolean panelDetalleRetenciones) {
        this.panelDetalleRetenciones = panelDetalleRetenciones;
    }

    public List<RetencionDTO> getListaElementosRetenciones() {
        return listaElementosRetenciones;
    }

    public void setListaElementosRetenciones(List<RetencionDTO> listaElementosRetenciones) {
        this.listaElementosRetenciones = listaElementosRetenciones;
    }

}
