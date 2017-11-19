/*
 * DependienteEconomicoController.java
 * Creado el Sep 26, 2016 10:44:55 AM
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Named
@ViewScoped
public class DependienteEconomicoController implements Serializable {

    private static final long serialVersionUID = -4392634290378579914L;

    private DependienteEconomicoView view;

    @Inject
    private Empleado empleadoEJB;

    @PostConstruct
    public void init() {
        view = new DependienteEconomicoView();
    }

    public void consultarEmpleados(ActionEvent actionEvent) {
        List<InfoEmpleadoDTO> empleadosEncontrados = empleadoEJB.consultaPorCriterio(view.getBusqueda());
        view.setEmpleadosEncontrados(empleadosEncontrados);
    }

    public void mostrarDetalleEmpleado(InfoEmpleadoDTO empleado) {
        view.setEmpleadoSelecionado(empleado);
        List<InfoDependienteEconomicoDTO> dependientesEconomicos = empleadoEJB.consultarDependientesEmpleado(empleado.getIdEmpleado());
        view.setDependientesEconomicos(dependientesEconomicos);

        view.setPanelBusqueda(false);
        view.setPanelDependienteEconomico(true);
    }

    public void mostrarBusquedaEmpleados(ActionEvent actionEvent) {
        view.setPanelBusqueda(true);
        view.setPanelDependienteEconomico(false);
    }

    public void mostrarNuevoDependieteEconomico(ActionEvent actionEvent) {
        view.setPanelNuevo(true);
    }

    public void ocultarNuevoDependieteEconomico(ActionEvent actionEvent) {
        view.setDependienteEconomico(new DependienteEconomicoDTO());
        view.setPanelNuevo(false);
    }

    public void ocultarActualizarDependieteEconomico(ActionEvent actionEvent) {
        view.setDependienteEconomico(new DependienteEconomicoDTO());
        view.setPanelActualizar(false);
    }

    public void agregarDependieteEconomico(ActionEvent actionEvent) {
        DependienteEconomicoDTO dependienteEconomico = view.getDependienteEconomico();
        dependienteEconomico.setIdEmpleado(view.getEmpleadoSelecionado().getIdEmpleado());
        empleadoEJB.crearDependienteEconomico(dependienteEconomico);
        ocultarNuevoDependieteEconomico(actionEvent);
        mostrarDetalleEmpleado(view.getEmpleadoSelecionado());
    }

    public void actualizarDependieteEconomico(ActionEvent actionEvent) {
        DependienteEconomicoDTO dependienteEconomico = view.getDependienteEconomico();
        dependienteEconomico.setIdEmpleado(view.getEmpleadoSelecionado().getIdEmpleado());
        empleadoEJB.actualizarDependienteEconomico(dependienteEconomico);
        ocultarActualizarDependieteEconomico(actionEvent);
        mostrarDetalleEmpleado(view.getEmpleadoSelecionado());
    }

    public void mostrarDependienteEconomico(Integer idDependienteEconomico) {
        DependienteEconomicoDTO dependienteEconomico = empleadoEJB.obtenerDependienteEconimicoPorId(idDependienteEconomico);
        view.setDependienteEconomico(dependienteEconomico);
        view.setPanelActualizar(true);
    }

    public void eliminarDependienteEconomico(Integer idDependienteEconomico) {
        empleadoEJB.eliminarDependienteEconomico(idDependienteEconomico);
        mostrarDetalleEmpleado(view.getEmpleadoSelecionado());
    }

    public void validarDependienteEconomico(FacesContext context, UIComponent component, Object value) {
        String nombreComponente = component.getId();

        switch (nombreComponente) {
            case "txtCurp":
            case "txtActualizarCurp":
                String curp = (String) value;

                if (ValidacionUtil.esCadenaVacia(curp)) {
                    FacesMessage curpFmVacio = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La CURP es un campo requerido.");
                    context.addMessage(nombreComponente, curpFmVacio);
                    throw new ValidatorException(curpFmVacio);
                }

                if (!ValidacionUtil.validarCurp(curp)) {
                    FacesMessage curpFmVacio = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            "Verifique el formato de la CURP. La CURP debe contener 4 letras al principio segudido de 6 números a continuación debe tener 3 letras y finalmente 5 caracteres alfanúmericos.");
                    context.addMessage(nombreComponente, curpFmVacio);
                    throw new ValidatorException(curpFmVacio);
                }

                if (!"txtActualizarCurp".equals(nombreComponente) && empleadoEJB.existeDependientePorCurp(curp)) {
                    FacesMessage curpFmDuplicado = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            "Ya existe un dependiente económico registrado con la misma CURP.");
                    context.addMessage(nombreComponente, curpFmDuplicado);
                    throw new ValidatorException(curpFmDuplicado);
                }

                break;
            case "txtNombre":
            case "txtActualizarNombre":
                String nombre = (String) value;

                if (ValidacionUtil.esCadenaVacia(nombre)) {
                    FacesMessage nombreFmVacio = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El nombre es un campo requerido.");
                    context.addMessage(nombreComponente, nombreFmVacio);
                    throw new ValidatorException(nombreFmVacio);
                }
                break;
            case "txtApellidoPaterno":
            case "txtActualizarApellidoPaterno":
                String apellidoPaterno = (String) value;

                if (ValidacionUtil.esCadenaVacia(apellidoPaterno) && ValidacionUtil.esCadenaVacia(view.getDependienteEconomico().getApellidoMaterno())) {
                    FacesMessage fechaNacimientoFmVacio = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe indicar por lo menos un apellido.");
                    context.addMessage(nombreComponente, fechaNacimientoFmVacio);
                    throw new ValidatorException(fechaNacimientoFmVacio);
                }
                break;
            case "txtApellidoMaterno":
            case "txtActualizarApellidoMaterno":
                String apellidoMaterno = (String) value;

                if (ValidacionUtil.esCadenaVacia(apellidoMaterno) && ValidacionUtil.esCadenaVacia(view.getDependienteEconomico().getApellidoPaterno())) {
                    FacesMessage fechaNacimientoFmVacio = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe indicar por lo menos un apellido.");
                    context.addMessage(nombreComponente, fechaNacimientoFmVacio);
                    throw new ValidatorException(fechaNacimientoFmVacio);
                }
                break;
            case "txtFechaNacimiento":
            case "txtActualizarFechaNacimiento":
                Date fechaNacimiento = (Date) value;

                if (fechaNacimiento == null) {
                    FacesMessage fechaNacimientoFmVacio = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La fecha de nacimiento es un campo requerido.");
                    context.addMessage(nombreComponente, fechaNacimientoFmVacio);
                    throw new ValidatorException(fechaNacimientoFmVacio);
                }
                break;
            case "sorSexo":
            case "soActualizarrSexo":
                String sexo = (String) value;

                if (ValidacionUtil.esCadenaVacia(sexo)) {
                    FacesMessage nombreFmVacio = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El sexo es un campo requerido.");
                    context.addMessage(nombreComponente, nombreFmVacio);
                    throw new ValidatorException(nombreFmVacio);
                }
                break;
            case "somParentesco":
            case "somActualizarParentesco":
                String parentesco = (String) value;

                if (ValidacionUtil.esCadenaVacia(parentesco)) {
                    FacesMessage parentescoFmVacio = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El parentesco es un campo requerido.");
                    context.addMessage(nombreComponente, parentescoFmVacio);
                    throw new ValidatorException(parentescoFmVacio);
                }
                break;
            case "txtOtroParentesco":
            case "txtActualizarOtroParentesco":
                if ("OTROS".equals(view.getDependienteEconomico().getParentesco())) {
                    String otroParentesco = (String) value;

                    if (ValidacionUtil.esCadenaVacia(otroParentesco)) {
                        FacesMessage parentescoFmVacio = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El parentesco es un campo requerido.");
                        context.addMessage(nombreComponente, parentescoFmVacio);
                        throw new ValidatorException(parentescoFmVacio);
                    }
                }
                break;
        }
    }

    public DependienteEconomicoView getView() {
        return view;
    }

    public void setView(DependienteEconomicoView view) {
        this.view = view;
    }
}
