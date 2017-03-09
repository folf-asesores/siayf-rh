/*
 * 
 * EstrategiaController.java
 * Creado el Jul 12, 2016 11:08:14 AM
 * 
 */

package mx.gob.saludtlax.rh.configuracion.estrategia;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidationException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

import org.primefaces.context.RequestContext;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@ManagedBean
@ViewScoped
public class EstrategiaController implements Serializable {

    private static final long serialVersionUID = 5108272348608513389L;

    @Inject private Estrategia estrategiaEJB;

    private EstrategiaView view;
    
    /**
     * Permite inicializar las propiedades que el contenedor no puede 
     * inicializar.
     */
    @PostConstruct
    public void init() {
        view = new EstrategiaView();
    }
    
    /**
     * Trae todas las estrategias del almacen de datos.
     * 
     * @return una lista con todas las estrategias disponibles.
     */
    public List<EstrategiaDTO> getEstrategias(){
        return estrategiaEJB.consultarEstrategias();
    }    
    
    public void guardar() {
        try {
            estrategiaEJB.crear(view.getEstrategia());
            view.setEstrategia(new EstrategiaDTO());
            RequestContext.getCurrentInstance().update("dlgNuevaEstrategiaWV");
            RequestContext.getCurrentInstance().execute("PF('dlgNuevaEstrategiaWV').hide()");
        } catch(ReglaNegocioException ex) {
            if(ex.getCodigoError().equals(ReglaNegocioCodigoError.CODIGO_ESTRATEGIA_DUPLICADO)) {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ex.getMessage());
                context.addMessage("txtCodigo", facesMessage);
                    throw new ValidatorException(facesMessage);
            }
        } catch(ValidationException ex){
            if(ex.getCodigoError().equals(ValidacionCodigoError.NUMERO_NEGATIVO)) {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ex.getMessage());
                context.addMessage("txtCodigo", facesMessage);
                    throw new ValidatorException(facesMessage);
            }
        }
    }
    
    public void actualizar() {
        estrategiaEJB.actualizar(view.getEstrategia());
        view.setEstrategia(new EstrategiaDTO());
        RequestContext.getCurrentInstance().execute("PF('dlgEditarEstrategiaWV').hide()");
    }
    
    public void eliminar(int idCodigoEstrategia) {
        estrategiaEJB.eliminar(idCodigoEstrategia);
    }
    
    public void cancelar() {
        view.setEstrategia(new EstrategiaDTO());
        RequestContext.getCurrentInstance().execute("PF('dlgNuevaEstrategiaWV').hide()");
        RequestContext.getCurrentInstance().execute("PF('dlgEditarEstrategiaWV').hide()");
    }
    
    public void validatorGuardar(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
        String nombreComponete = component.getId();
        
        switch(nombreComponete){
            case "txtCodigo" :
                
                if(value == null) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Valor invalido.");
                    context.addMessage(nombreComponete, facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {                
                    Integer codigo = (Integer) value;

                    if(codigo < 1) {
                        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El código de la estrategia no puede ser negativo.");
                        context.addMessage(component.getId(), facesMessage);
                        throw new ValidatorException(facesMessage);
                    }
                    
                    if(estrategiaEJB.existeCodigoEstrategia(codigo)){
                        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ya existe una estrategia con ese código.");
                        context.addMessage(nombreComponete, facesMessage);
                        throw new ValidatorException(facesMessage);
                    }
                }
                
                break;
            case "txtDescripcion":
            case "txtDescripcionEditar":
                if(value == null) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Valor invalido.");
                    context.addMessage(nombreComponete, facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    String descripcion = (String) value;
                    
                    if(ValidacionUtil.esCadenaVacia(descripcion)) {
                        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El campo no puede estar vacio.");
                        context.addMessage(nombreComponete, facesMessage);
                        throw new ValidatorException(facesMessage);
                    }
                }
                
                break;
        }
    }

    public EstrategiaView getView() {
        return view;
    }

    public void setView(EstrategiaView view) {
        this.view = view;
    }
}
