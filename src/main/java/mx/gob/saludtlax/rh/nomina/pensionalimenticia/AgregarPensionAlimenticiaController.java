
package mx.gob.saludtlax.rh.nomina.pensionalimenticia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "agregarPensionAlimenticiaController")
@ViewScoped
public class AgregarPensionAlimenticiaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3739783212304380778L;

    private String rfc;

    @Inject
    PensionAlimenticiaEJB pensionAlimenticiaEJB;

    private InformacionEmpleadoDTO informacionEmpleadoDTO = new InformacionEmpleadoDTO();

    private List<SelectItem> listadoTipoCoutas;

    private List<SelectItem> listadoBancos;

    private BeneficiarioPensionAlimenticiaForm beneficiarioPensionAlimenticiaForm = new BeneficiarioPensionAlimenticiaForm();

    @PostConstruct
    public void init() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String id = params.get("i");

        if (id == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String outcome = "index.html?faces-redirect=true";
            facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, outcome);
        }

        informacionEmpleadoDTO = pensionAlimenticiaEJB.buscarEmpleado(new Integer(id));
        listadoTipoCoutas = pensionAlimenticiaEJB.listadoTipoCoutas();
        listadoBancos = pensionAlimenticiaEJB.listadoBanco();
    }

    public String guardarInformacionPension() {
        beneficiarioPensionAlimenticiaForm.idEmpleado = informacionEmpleadoDTO.getIdEmpleado();
        pensionAlimenticiaEJB.guardar(beneficiarioPensionAlimenticiaForm);
        return "index.html?faces-redirect=true&i=" + beneficiarioPensionAlimenticiaForm.idEmpleado;
    }

    // ----Validaciones--//
    public void validatorDatosGenerales(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String nombreComponete = component.getId();
        switch (nombreComponete) {

            case "expediente":
                String expediente = (String) value;

                if (ValidacionUtil.esCadenaVacia(expediente)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingres el numero de expediente.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "juzgado":
                String juzgado = (String) value;

                if (ValidacionUtil.esCadenaVacia(juzgado)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese el numero de juzgado.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "oficio":
                String oficio = (String) value;

                if (ValidacionUtil.esCadenaVacia(oficio)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese el numero de oficio.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

            case "rfc":
                String rfc = (String) value;

                if (ValidacionUtil.esCadenaVacia(rfc)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un rfc.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                } else if (!ValidacionUtil.validarRfc(rfc)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un rfc valido.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

            case "nombre":
                String nombre = (String) value;

                if (ValidacionUtil.esCadenaVacia(nombre)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese el nombre del beneficiario.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

            case "tipo":
                int tipo = (int) value;

                if (tipo == 0) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione el tipo de descuento que se va aplicar.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "valor":
                BigDecimal valor = (BigDecimal) value;

                if (valor == BigDecimal.ZERO || valor == null) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una catidad mayor a 0.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

        }

    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public InformacionEmpleadoDTO getInformacionEmpleadoDTO() {
        return informacionEmpleadoDTO;
    }

    public List<SelectItem> getListadoTipoCoutas() {
        return listadoTipoCoutas;
    }

    public void setListadoTipoCoutas(List<SelectItem> listadoTipoCoutas) {
        this.listadoTipoCoutas = listadoTipoCoutas;
    }

    public void setInformacionEmpleadoDTO(InformacionEmpleadoDTO informacionEmpleadoDTO) {
        this.informacionEmpleadoDTO = informacionEmpleadoDTO;
    }

    public BeneficiarioPensionAlimenticiaForm getBeneficiarioPensionAlimenticiaForm() {
        return beneficiarioPensionAlimenticiaForm;
    }

    public void setBeneficiarioPensionAlimenticiaForm(BeneficiarioPensionAlimenticiaForm beneficiarioPensionAlimenticiaForm) {
        this.beneficiarioPensionAlimenticiaForm = beneficiarioPensionAlimenticiaForm;
    }

    /**
     * @return the listadoBancos
     */
    public List<SelectItem> getListadoBancos() {
        return listadoBancos;
    }

    /**
     * @param listadoBancos
     *            the listadoBancos to set
     */
    public void setListadoBancos(List<SelectItem> listadoBancos) {
        this.listadoBancos = listadoBancos;
    }

}
