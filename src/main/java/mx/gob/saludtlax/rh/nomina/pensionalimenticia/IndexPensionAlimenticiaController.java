
package mx.gob.saludtlax.rh.nomina.pensionalimenticia;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean(name = "indexPensionAlimenticiaController")
@ViewScoped
public class IndexPensionAlimenticiaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -518433374279409272L;

    @Inject
    PensionAlimenticiaEJB pensionAlimenticiaEJB;

    private String rfc;

    private InformacionEmpleadoDTO informacionEmpleado = new InformacionEmpleadoDTO();

    private boolean mostrarInformacion = Boolean.FALSE;

    @PostConstruct
    public void init() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String id = params.get("i");

        if (id != null) {
            informacionEmpleado = pensionAlimenticiaEJB.buscarEmpleado(new Integer(id));
            mostrarInformacion = Boolean.TRUE;

        }

    }

    public void buscarPorRFC() {

        if (rfc != null && rfc.length() > 0) {

            informacionEmpleado = pensionAlimenticiaEJB.buscarEmpleado(rfc);

            if (informacionEmpleado == null) {
                mostrarInformacion = Boolean.FALSE;
                informacionEmpleado = new InformacionEmpleadoDTO();

                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Buscar",
                        "No se encontro información del empleado con el RFC ingresado");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                return;
            }

            mostrarInformacion = Boolean.TRUE;

        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Buscar", "Ingrese un RFC");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }

    }

    public void eliminarPensionAlimenticia(BeneficiarioPensionAlimienticiaDTO beneficiario) {

        pensionAlimenticiaEJB.eliminarBeneficiarioPension(beneficiario.getIdPensionAlimenticia());
        informacionEmpleado = pensionAlimenticiaEJB.buscarEmpleado(informacionEmpleado.getIdEmpleado());

    }

    public String guardar() {

        return "";
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public InformacionEmpleadoDTO getInformacionEmpleado() {
        return informacionEmpleado;
    }

    public void setInformacionEmpleado(InformacionEmpleadoDTO informacionEmpleado) {
        this.informacionEmpleado = informacionEmpleado;
    }

    public boolean isMostrarInformacion() {
        return mostrarInformacion;
    }

    public void setMostrarInformacion(boolean mostrarInformacion) {
        this.mostrarInformacion = mostrarInformacion;
    }

}
