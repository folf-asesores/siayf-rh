/*
 *
 */

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

/**
 * @author Eduardo Mex
 *
 */
@ManagedBean(name = "editarPensionAlimenticia")
@ViewScoped
public class EditarPensionAlimenticiaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8155106657288513705L;

    @Inject
    PensionAlimenticiaEJB pensionAlimenticiaEJB;

    private InformacionEmpleadoDTO informacionEmpleadoDTO = new InformacionEmpleadoDTO();

    private BeneficiarioPensionAlimenticiaForm beneficiarioPensionAlimenticiaForm = new BeneficiarioPensionAlimenticiaForm();

    private List<SelectItem> listadoTipoCoutas;

    private List<SelectItem> listadoBancos;

    private String rfc;

    @PostConstruct
    public void init() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String idPensionAlimenticia = params.get("i");

        if (idPensionAlimenticia == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String outcome = "index.html?faces-redirect=true";
            facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, outcome);
        }

        beneficiarioPensionAlimenticiaForm = pensionAlimenticiaEJB.obtenerPensionAlimenticiaPorId(new Integer(idPensionAlimenticia));
        informacionEmpleadoDTO = pensionAlimenticiaEJB.buscarEmpleado(new Integer(beneficiarioPensionAlimenticiaForm.getIdEmpleado()));
        listadoTipoCoutas = pensionAlimenticiaEJB.listadoTipoCoutas();
        listadoBancos = pensionAlimenticiaEJB.listadoBanco();

    }

    public String editarInformacionPension() {

        pensionAlimenticiaEJB.editar(beneficiarioPensionAlimenticiaForm);
        return "index.html?faces-redirect=true&i=" + beneficiarioPensionAlimenticiaForm.idEmpleado;
    }

    public String eliminarPensionAlimenticia() {

        pensionAlimenticiaEJB.eliminarBeneficiarioPension(beneficiarioPensionAlimenticiaForm.getIdPensionAlimenticia());
        //		informacionEmpleadoDTO = pensionAlimenticiaEJB.buscarEmpleado(informacionEmpleadoDTO.getIdEmpleado());
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

    

    /**
     * @return the beneficiarioPensionAlimenticiaForm
     */
    public BeneficiarioPensionAlimenticiaForm getBeneficiarioPensionAlimenticiaForm() {
        return beneficiarioPensionAlimenticiaForm;
    }

    /**
     * @param beneficiarioPensionAlimenticiaForm
     *            the beneficiarioPensionAlimenticiaForm to set
     */
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

    /**
     * @return the listadoTipoCoutas
     */
    public List<SelectItem> getListadoTipoCoutas() {
        return listadoTipoCoutas;
    }

    /**
     * @param listadoTipoCoutas
     *            the listadoTipoCoutas to set
     */
    public void setListadoTipoCoutas(List<SelectItem> listadoTipoCoutas) {
        this.listadoTipoCoutas = listadoTipoCoutas;
    }

    /**
     * @return the informacionEmpleadoDTO
     */
    public InformacionEmpleadoDTO getInformacionEmpleadoDTO() {
        return informacionEmpleadoDTO;
    }

    /**
     * @param informacionEmpleadoDTO
     *            the informacionEmpleadoDTO to set
     */
    public void setInformacionEmpleadoDTO(InformacionEmpleadoDTO informacionEmpleadoDTO) {
        this.informacionEmpleadoDTO = informacionEmpleadoDTO;
    }

    /**
     * @return the rfc
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * @param rfc
     *            the rfc to set
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

}
