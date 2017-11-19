/*
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.AspiranteDTO;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.InfoAspiranteDTO;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-13:05:27
 */

@Stateless
public class EmpleadoEJB implements Empleado {

    @Inject
    private AltaEmpleadoService altaEmpleadoService;
    @Inject
    private BitacoraModificacionService bitacoraModificacionService;
    @Inject
    private BusquedaEmpleadoService busquedaEmpleadoService;
    @Inject
    private EmpleadoService empleadoService;
    @Inject
    private DepenedienteEconomicoService depenedienteEconomicoService;
    @Inject
    private RegistroEmpleadoService registroEmpleadoService;

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.empleados.Empleado#
     * aspirantesDisponiblesPorCriterio (java.lang.String)
     */
    @Override
    public List<InfoAspiranteDTO> consultarAspirantesDisponiblesPorCriterio(
            String criterio) {

        return busquedaEmpleadoService
                .aspirantesDisponiblesPorCriterio(criterio);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.empleados.Empleado#aspirantePorId(java.lang.
     * Integer )
     */
    @Override
    public EmpleadoDTO obtenerAspirantePorId(Integer idAspirante) {
        return busquedaEmpleadoService.aspirantePorId(idAspirante);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.empleados.Empleado#
     * consultaAspirantePorIdentificador (java.lang.Integer)
     */
    @Override
    public AspiranteDTO consultaAspirantePorIdentificador(Integer IdAspirante) {
        return busquedaEmpleadoService
                .consultaAspirantePorIdentificador(IdAspirante);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * mx.gob.saludtlax.rh.api.empleados.Empleado#aprobarDatosGeneralesAspirante
     * (mx.gob.saludtlax.rh.api.empleados.DatosGeneralesDTO, java.lang.Integer)
     */
    @Override
    public RegistroGeneralesDTO aprobarDatosGeneralesAspirante(
            DatosGeneralesDTO datosGeneralesDTO, Integer idAspirante) {
        return registroEmpleadoService.aprobarDatosGenerales(datosGeneralesDTO,
                idAspirante);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * mx.gob.saludtlax.rh.api.empleados.Empleado#aprobarDomicilioAspirante(
     * mx.gob.saludtlax.rh.api.empleados.DomicilioDTO, java.lang.Integer,
     * java.lang.Integer)
     */
    @Override
    public int aprobarDomicilioAspirante(DomicilioDTO domicilioDTO,
            Integer idAspirante, Integer idEmpleado) {
        return registroEmpleadoService.aprobarDireccion(domicilioDTO,
                idAspirante, idEmpleado);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.empleados.Empleado#aprobarFaseAspirante(int,
     * java.lang.Integer)
     */
    @Override
    public int aprobarFaseAspirante(int fase, Integer idAspirante) {
        return registroEmpleadoService.aprobarInformacion(fase, idAspirante);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * mx.gob.saludtlax.rh.api.empleados.Empleado#empleadosPorCriterio(java.
     * lang.String)
     */
    @Override
    public List<InfoEmpleadoDTO> consultaPorCriterio(String criterio) {
        return busquedaEmpleadoService.consultaEmpleadoPorCriterio(criterio);
    }

    @Override
    public DatosGeneralesDTO obtenerDatosGenerales(Integer idEmpleado) {
        return busquedaEmpleadoService
                .obtenerDatosGeneralesEmpleado(idEmpleado);
    }

    @Override
    public DomicilioDTO obtenerDomicilio(Integer idEmpleado) {
        return busquedaEmpleadoService.obtenerDomicilioEmpleado(idEmpleado);
    }

    @Override
    public void actualizarDomicilio(Integer idEmpleado, Integer idUsuario,
            DomicilioDTO domicilioDTO) {
        empleadoService.actualizarDomicilio(idEmpleado, idUsuario,
                domicilioDTO);
    }

    @Interceptors({ ActualizarDatosValidator.class })
    @Override
    public void actualizarDatosGenerales(DatosGeneralesDTO datosGeneralesDTO) {
        empleadoService.actualizarDatosGenerales(null, datosGeneralesDTO);
    }

    @Override
    @Deprecated
    public void crearDependienteEconomicoEmpleado(
            DependienteEconomicoDTO dependienteEconomicoDTO) {
        depenedienteEconomicoService
                .crearDependienteEconomicoEmpleado(dependienteEconomicoDTO);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * mx.gob.saludtlax.rh.api.empleados.Empleado#crearDependienteEconomico(mx.
     * gob .saludtlax.rh.api.empleados.DependienteEconomicoDTO)
     */
    @Override
    public void crearDependienteEconomico(
            DependienteEconomicoDTO dependienteEconomico) {
        if (dependienteEconomico == null) {
            throw new ValidacionException(
                    "La información del dependiente económico no puede estar vacia",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esCadenaVacia(dependienteEconomico.getCurp())) {
            throw new ValidacionException(
                    "La CURP del dependiente económico no puede estar vacia",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esCadenaVacia(dependienteEconomico.getNombre())) {
            throw new ValidacionException(
                    "El nombre del dependiente económico no puede estar vacio",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil
                .esCadenaVacia(dependienteEconomico.getParentesco())) {
            throw new ValidacionException(
                    "Debe indicar el tipo de parentesco. Ej. CONYUGE, PADRES, HIJOS, OTROS",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil
                .esCadenaVacia(dependienteEconomico.getApellidoPaterno())
                && ValidacionUtil.esCadenaVacia(
                        dependienteEconomico.getApellidoMaterno())) {
            throw new ValidacionException(
                    "Se requiere al menos uno de los apellidos del dependienete económico.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        depenedienteEconomicoService
                .crearDependienteEconomico(dependienteEconomico);
    }

    @Override
    public DependienteEconomicoDTO obtenerDependienteEconimicoPorId(
            Integer idDependienteEconomico) {
        if (ValidacionUtil.esMenorQueUno(idDependienteEconomico)) {
            throw new ValidacionException(
                    "Ha enviado un identificador dependiente no valido.",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        return depenedienteEconomicoService
                .obtenerDependienteEconimicoPorId(idDependienteEconomico);
    }

    @Override
    public DependienteEconomicoDTO obtenerDependienteEconimicoPorCurp(
            String curp) {
        if (ValidacionUtil.esCadenaVacia(curp)) {
            throw new ValidacionException("La CURP no puede estar vacia.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        return depenedienteEconomicoService
                .obtenerDependienteEconimicoPorCurp(curp);
    }

    @Override
    public boolean existeDependientePorCurp(String curp) {
        if (ValidacionUtil.esCadenaVacia(curp)) {
            throw new ValidacionException("La CURP no puede estar vacia.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        return depenedienteEconomicoService.existeDependientePorCurp(curp);
    }

    @Override
    public void actualizarDependienteEconomico(
            DependienteEconomicoDTO dependienteEconomico) {
        if (dependienteEconomico == null) {
            throw new ValidacionException(
                    "La información del dependiente económico no puede estar vacia",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esCadenaVacia(dependienteEconomico.getCurp())) {
            throw new ValidacionException(
                    "La CURP del dependiente económico no puede estar vacia",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esCadenaVacia(dependienteEconomico.getNombre())) {
            throw new ValidacionException(
                    "El nombre del dependiente económico no puede estar vacio",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil
                .esCadenaVacia(dependienteEconomico.getParentesco())) {
            throw new ValidacionException(
                    "Debe indicar el tipo de parentesco. Ej. CONYUGE, PADRES, HIJOS, OTROS",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil
                .esCadenaVacia(dependienteEconomico.getApellidoPaterno())
                && ValidacionUtil.esCadenaVacia(
                        dependienteEconomico.getApellidoMaterno())) {
            throw new ValidacionException(
                    "Se requiere al menos uno de los apellidos del dependienete económico.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }
        depenedienteEconomicoService
                .actualizarDependienteEconomico(dependienteEconomico);
    }

    @Override
    public void eliminarDependienteEconomico(Integer idDependienteEconomico) {
        if (ValidacionUtil.esMenorQueUno(idDependienteEconomico)) {
            throw new ValidacionException(
                    "Ha enviado un identificador dependiente no valido.",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        depenedienteEconomicoService
                .eliminarDependienteEconomico(idDependienteEconomico);
    }

    @Override
    public List<InfoDependienteEconomicoDTO> consultarDependientesEmpleado(
            Integer idEmpleado) {
        return depenedienteEconomicoService
                .consultarDependientesEmpleado(idEmpleado);
    }

    @Override
    public List<InfoDependienteEconomicoDTO> consultarDependientesEconomicoPadres(
            Integer idEmpleado) {

        return depenedienteEconomicoService
                .consultarDependientesEconomicoPadres(idEmpleado);
    }

    @Override
    public void validarCurpEmpleado(String curp) {
        empleadoService.validarCurpEmpleado(curp);

    }

    @Override
    public String obtenerCurpEmpleado(Integer idEmpleado) {

        return empleadoService.obtenerCurpEmpleado(idEmpleado);
    }

    @Override
    public List<InfoEmpleadoDTO> consultaEmpleado(FiltroDTO filtroDTO) {

        return busquedaEmpleadoService.consultarEmpleado(filtroDTO);
    }

    @Override
    public List<InfoVacantePostularDTO> obtenerListaEmpleadoCandidato() {

        return busquedaEmpleadoService.obtenerListaEmpleadoCandidato();
    }

    @Override
    public List<InfoEmpleadoDTO> consultarEmpleadosConPuestosActivos(
            FiltroDTO filtroDTO) {

        return busquedaEmpleadoService
                .consultarEmpleadosConPuestosActivos(filtroDTO);
    }

    @Override
    public void crearEmpleado(AltaEmpleadoDTO altaEmpleadoDTO) {
        altaEmpleadoService.altaEmpleado(altaEmpleadoDTO);

    }

    @Override
    public ValidacionEmpleadoDTO validarDatosObligatoriosEmpleado(
            Integer idEmpleado) {

        return empleadoService.validarDatosObligatorios(idEmpleado);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * mx.gob.saludtlax.rh.empleados.administracion.Empleado#validarRfcEmpleado(
     * java.lang.String)
     */
    @Override
    public void validarRfcEmpleado(String rfc) {
        empleadoService.validarRfcEmpleado(rfc);

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * mx.gob.saludtlax.rh.empleados.administracion.Empleado#obtenerRfcEmpleado(
     * java.lang.Integer)
     */
    @Override
    public String obtenerRfcEmpleado(Integer idEmpleado) {

        return empleadoService.obtenerRfcEmpleado(idEmpleado);
    }

    @Override
    public EmpleadoDetalladoDTO obtenerInformacionEmpleado(Integer idEmpleado) {

        return busquedaEmpleadoService.obtenerInformacionEmpleado(idEmpleado);
    }

    @Override
    public List<InfoEmpleadoDTO> consultaEmpleadosFederales(String criterio) {

        return busquedaEmpleadoService
                .empleadosPorCriterioTipoContratacion(criterio);
    }

    @Override
    public List<InfoEmpleadoDTO> empleadosPorCriterioConNombramiento(
            String criterio) {

        return busquedaEmpleadoService
                .empleadosPorCriterioConNombramiento(criterio);
    }

    @Override
    public List<BitacoraEmpleadoDTO> consultarBitacorasMovimientos(
            Integer idEmpleado) {
        return bitacoraModificacionService
                .consultarBitacorasMovimientos(idEmpleado);
    }

}
