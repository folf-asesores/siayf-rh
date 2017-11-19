
package mx.gob.saludtlax.rh.nomina.movimientoscontrato;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

//import org.apache.xerces.impl.dv.xs.QNameDV;

import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.nomina.productosnomina.FaltaContadaDTO;
import mx.gob.saludtlax.rh.seguridad.autenticacion.AutenticacionUtil;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author José Pablo
 *
 */
@ManagedBean(name = "movimientosContratos")
@ViewScoped
public class MovimientosContratosController implements Serializable {
    private static final long serialVersionUID = -1531828229286984832L;

    @Inject
    private Empleado empleado;
    @Inject
    private MovimientosContratosEJB ejb;
    @Inject
    private MovimientosContratosView view;

    @PostConstruct
    public void initMovimientosContratos() {
        view.panelBusqueda();
        view.setListaConceptos(ejb.obtenerConceptosLista(true));
    }

    public void validatorConsulta(FacesContext context, UIComponent component,
            Object value) {
        String nombreComponete = component.getId();

        switch (nombreComponete) {
            case "criterio":
                String criterio = (String) value;
                if (ValidacionUtil.esCadenaVacia(criterio)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un criterio de búsqueda.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (criterio.trim().length() < 5) {
                        FacesMessage facesMessage = new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "",
                                "Por favor ingrese un criterio de búsqueda mayor a 4 letras.");
                        context.addMessage(component.getClientId(),
                                facesMessage);
                        throw new ValidatorException(facesMessage);
                    }
                }
                break;
            default:
                break;
        }
    }

    public void buscarEmpleado() {
        System.out.println(
                "view.isMostrarBusqueda():: " + view.isMostrarBusqueda());
        try {
            view.panelBusqueda();
            System.out
                    .println("this.view.getCriterio():: " + view.getCriterio());
            view.setEmpleados(empleado.consultaPorCriterio(view.getCriterio()));
            if (view.getEmpleados().isEmpty()) {
                JSFUtils.infoMessageEspecifico("info", "",
                        "No se encontrarón registros con el criterio "
                                + view.getCriterio());
            } else {
                view.setMostrarResultados(true);
            }
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException | ValidacionException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
            System.out.println(
                    "view.isMostrarBusqueda():: " + view.isMostrarBusqueda());
        }
        System.out.println(
                "view.isMostrarBusqueda():: " + view.isMostrarBusqueda());
    }

    public void seleccionarEmpleado(InfoEmpleadoDTO empleadoSeleccionado) {
        try {
            view.panelMovimientos();
            UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
            if (usuario.getUserName().equals("veronica")
                    || usuario.getUserName().equals("rosaA")) {
                view.setEmpleadoDatos(empleado.obtenerInformacionEmpleado(
                        empleadoSeleccionado.getIdEmpleado()));
                view.setEmpleadoSelect(empleadoSeleccionado);
                view.setMovimientoContratosLista(
                        ejb.obtenerMovimientosNominaEmpleadoLista(
                                empleadoSeleccionado.getIdEmpleado()));
            }
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("Error: ", validatorException.getMessage());
        }
    }

    public void validator(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "numDias":
                Integer dias = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(dias)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese el numero de dias, el campo no puede quedar vacio.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

            case "folio":
                String folio = (String) value;
                if (ValidacionUtil.esCadenaVacia(folio)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese el folio del documento, el campo no puede quedar vacio.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;

            case "anioFinal":
                Integer anioFinal = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(anioFinal)
                        || anioFinal.compareTo(2015) < 0) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese el año final, debe ser mayor a"
                                    + (FechaUtil.ejercicioActual() - 1) + ".");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "importe":
                BigDecimal importeQuincenal = (BigDecimal) value;
                if (!ValidacionUtil.esMayorCero(importeQuincenal)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "El importe debe ser mayor a 0.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "concepto":
                Integer concepto = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(concepto)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Eliga un concepto para el movimiento.");
                    System.out.println("concepto::" + concepto);
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
        }
    }

    public String irNuevoMovimiento() {
        view.setMovimientoContratos(new MovimientoContratosDTO());
        view.setProductoNominaLista(ejb.obtenerProductoNominaLista(
                view.getEmpleadoSelect().getIdEmpleado()));
        if (view.getProductoNominaLista().isEmpty()) {
            JSFUtils.errorMessage(
                    "Debe existir un producto de nomina disponible para registrar el movimiento",
                    "");
        }
        view.setNuevo(Boolean.TRUE);
        view.panelFormulario();
        return null;
    }

    public String agregarMovimiento() {
        if (view.getNuevo()) {
            view.getMovimientoContratos().setAbonado(new BigDecimal(0));
            view.getMovimientoContratos()
                    .setSaldo(view.getMovimientoContratos().getMonto());
            view.getMovimientoContratos()
                    .setAnioInicial(FechaUtil.ejercicioActual());
            view.getMovimientoContratos().setIdNominaEmpleado(
                    view.getMovimientoContratos().getIdNominaEmpleado());
            view.getMovimientoContratos().setIdConceptoContratos(
                    view.getMovimientoContratos().getIdConceptoContratos());
            // 1:Registrado
            view.getMovimientoContratos().setIdEstatus(1);
            view.getMovimientoContratos().setNumeroAbonos(
                    view.getMovimientoContratos().getNumeroAbonos() != null
                            ? view.getMovimientoContratos().getNumeroAbonos()
                            : 0);
            view.getMovimientoContratos()
                    .setRfc(view.getEmpleadoDatos().getRfc());
            view.getMovimientoContratos()
                    .setIdEmpleado(view.getEmpleadoSelect().getIdEmpleado());
            Integer idMovimiento = ejb.crear(view.getMovimientoContratos());

            BigDecimal descuento = new BigDecimal(0);
            if (view.getMovimientoContratos().getNumeroAbonos() != null
                    && view.getMovimientoContratos().getNumeroAbonos() > 0) {
                descuento = view
                        .getMovimientoContratos().getMonto().divide(
                                new BigDecimal(view.getMovimientoContratos()
                                        .getNumeroAbonos()),
                                2, RoundingMode.HALF_UP);
            }

            for (int i = 1; i <= view.getMovimientoContratos()
                    .getNumeroAbonos(); i++) {
                DetalleMovimientoContratoDTO detalleDto = new DetalleMovimientoContratoDTO();
                detalleDto.setAbono(descuento.multiply(new BigDecimal(i)));
                detalleDto.setDescuento(descuento);
                detalleDto.setFechaRegistro(new Date());
                detalleDto.setIdMovimientoContrato(idMovimiento);
                detalleDto.setMonto(view.getMovimientoContratos().getMonto());
                detalleDto.setNumeroPago(i);

                BigDecimal saldo = view.getMovimientoContratos().getMonto()
                        .subtract(detalleDto.getAbono());
                System.out
                        .println("saldo."
                                + view.getMovimientoContratos().getMonto()
                                        .subtract(detalleDto.getAbono())
                                + " " + saldo);
                detalleDto.setSaldo(saldo);
                ejb.guardarDetalle(detalleDto);
            }
            JSFUtils.infoMessage("Correcto:",
                    "El movimiento se registro exitosamente.");
        } else {
            ejb.actualizarMovimientoContratos(view.getMovimientoContratos());
            JSFUtils.infoMessage("Correcto:",
                    "El movimiento se actualizó exitosamente.");
        }
        view.panelFormulario();
        return null;
    }

    //    public void calcularPeriodos(){
    //    	Calendar fecha = Calendar.getInstance();
    //    	int dia = fecha.get(Calendar.DAY_OF_MONTH);
    //    	Integer qnaFinal =0;
    //    	Integer mesFinal = 0;
    //    	Integer anioActual = FechaUtil.ejercicioActual();
    //    	Double opQna;
    //    	long restoQna=0;
    //
    //    	Integer qnaActual = nominaEmpleadoService.obtenerNumeroQuincena(FechaUtil.mesActual(), dia);
    //    	quincenaActual = qnaActual;
    //    	Integer mesAnioActual = nominaEmpleadoService.obtenerMes(qnaActual);
    //    	// si el tipo de periodo es quincenal sumara a la quincena actual el numero de qnas ingresadas
    //
    //    	if(view.getMovimientoContratos().getIdTipoPeriodo().compareTo(4)==0 && view.isDesabilitarFijos()){
    //    	qnaFinal = qnaActual+view.getMovimientoContratos().getNumeroAbonos();
    //    	      //si el numero de quincenas es mayor a 24 se debra incrementar el anio
    //    		  if(qnaFinal > 24){
    //    	    	  opQna = (double) (qnaFinal / 24);
    //    	    	  String cad=String.format("%.3f",opQna);
    //    	    	  restoQna = (long)(Double.parseDouble(cad));
    //    	    	  anioActual = (int) (anioActual + restoQna);
    //
    //    	    	  System.out.println("Periodo actual:"+quincenaActual);
    //        		  System.out.println("Anio actual: "+anioActual);
    //
    //    	    	  Integer restoQna2 = qnaFinal;
    //    	    	  for(int i = 1; i<=restoQna; i++){
    //    	    		  // sacara el resto de quincenas del siguiente anio
    //    	    		  restoQna2 = restoQna2-24;
    //
    //    	    	  }
    //    	    	  periodoFinal = restoQna2;
    //    	    	  anioFinal = anioActual;
    //    	      }else{
    //    	    	  periodoFinal=qnaFinal;
    //    	    	  anioFinal=anioActual;
    //    	      }
    //    		  System.out.println("Periodo final:"+periodoFinal);
    //    		  System.out.println("Anio final: "+anioFinal);
    //    	}
    //    	//si el tipo de periodo es mensual sumara al mes actual el numero ingresado
    //    	if(view.getMovimientoContratos().getIdTipoPeriodo().compareTo(5)==0 && view.isDesabilitarFijos()){
    ////    		System.out.println("mes actual:"+mesAnioActual);
    //        mesFinal = mesAnioActual+view.getMovimientoContratos().getNumeroAbonos();
    ////        System.out.println("mes actual:"+mesFinal);
    //        if(mesFinal > 12){
    //	    	  opQna = (double) (mesFinal / 12);
    //	    	  String cad=String.format("%.3f",opQna);
    //	    	  restoQna = (long)(Double.parseDouble(cad));
    //	    	  anioActual = (int) (anioActual + restoQna);
    ////	    	  System.out.println("restoQna:"+restoQna);
    //	    	  Integer restoQna2 = mesFinal;
    ////	    	  System.out.println("restoQna2:"+restoQna2);
    //	    	  for(int i = 1; i<=restoQna; i++){
    //	    		  // sacara el resto de quincenas del siguiente anio
    ////	    		  System.out.println("restoQna4:"+restoQna2);
    //	    		  restoQna2 = restoQna2-12;
    ////	    		   System.out.println("aui.." +restoQna2);
    //	    	  }
    //	    	  periodoFinal = restoQna2;
    //	    	  anioFinal = anioActual;
    //	      }else{
    //	    	  periodoFinal=qnaFinal;
    //	    	  anioFinal=anioActual;
    //	      }
    ////		  System.out.println("Periodo final2:"+periodoFinal);
    ////		  System.out.println("Anio final2: "+anioFinal);
    //        }
    //    }

    public String irMovimientos() {
        view.setMovimientoContratosLista(
                ejb.obtenerMovimientosNominaEmpleadoLista(
                        view.getEmpleadoSelect().getIdEmpleado()));
        view.panelMovimientos();
        return null;
    }

    public String irPanelBusqeuda() {
        view.panelBusqueda();
        return null;
    }

    public String irGestionMovimiento() {
        view.setNuevo(Boolean.FALSE);
        view.setProductoNominaLista(ejb.obtenerProductoNominaLista(
                view.getEmpleadoSelect().getIdEmpleado()));
        view.setMovimientoContratos(
                ejb.obtenerMovimientoContrato(view.getMovimientoContratos()));
        if (view.getProductoNominaLista().isEmpty()) {
            JSFUtils.errorMessage(
                    "Debe existir un producto de nomina disponible para registrar el movimiento",
                    "");
        }
        actualizarFormulario();
        view.panelFormulario();
        return null;
    }

    public String actualizarFormulario() {
        view.setHabilitarFaltas(view.getMovimientoContratos()
                .getIdConceptoContratos().equals(13));
        if (view.isHabilitarFaltas() && view.getMovimientoContratos()
                .getFaltaContadaList() == null) {
            List<FaltaContadaDTO> faltaContadaList = new ArrayList<>();
            view.getMovimientoContratos().setFaltaContadaList(faltaContadaList);
        }
        view.setDesabilitarFijos(ejb.esMovimientoFijo(
                view.getMovimientoContratos().getIdConceptoContratos()));
        return null;
    }

    public String agregarFalta() {
        FaltaContadaDTO faltaContada = new FaltaContadaDTO();
        faltaContada.setFechaFalta(new Date(view.getFechaFalta().getTime()));
        Boolean noExiste = Boolean.TRUE;
        for (FaltaContadaDTO faltaContadaTem : view.getMovimientoContratos()
                .getFaltaContadaList()) {
            if (faltaContadaTem.getFechaFalta().equals(view.getFechaFalta())) {
                noExiste = Boolean.FALSE;
                break;
            }
        }
        if (noExiste) {
            view.getMovimientoContratos().getFaltaContadaList()
                    .add(faltaContada);
            view.getMovimientoContratos().setMonto(
                    ejb.calcularDescuentoFaltas(view.getMovimientoContratos()));
        }
        return null;
    }

    public String eliminarFalta() {
        for (FaltaContadaDTO faltaContada : view.getMovimientoContratos()
                .getFaltaContadaList()) {
            if (faltaContada.getFechaFalta().equals(view.getFechaFalta())) {
                view.getMovimientoContratos().getFaltaContadaList()
                        .remove(faltaContada);
                view.getMovimientoContratos()
                        .setMonto(ejb.calcularDescuentoFaltas(
                                view.getMovimientoContratos()));
                break;
            }
        }
        return null;
    }

    public String eliminarMovimientoContrato() {
        if (view.getMovimientoContratos().getIdEstatus() == 1) {
            ejb.eliminarMovimientoContrato(view.getMovimientoContratos());
            view.setMovimientoContratosLista(
                    ejb.obtenerMovimientosNominaEmpleadoLista(
                            view.getEmpleadoSelect().getIdEmpleado()));
            view.panelMovimientos();
            JSFUtils.infoMessage("El movimiento se ha eliminado exitosamente",
                    "");
        } else {
            JSFUtils.errorMessage(
                    "El movimiento ya esta considerado en la nómina", "");
        }
        return null;
    }

    public MovimientosContratosView getView() {
        return view;
    }
}