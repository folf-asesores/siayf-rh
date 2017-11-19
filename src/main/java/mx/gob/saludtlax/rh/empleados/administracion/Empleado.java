
package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.List;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.AspiranteDTO;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.InfoAspiranteDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

public interface Empleado {

    public List<InfoAspiranteDTO> consultarAspirantesDisponiblesPorCriterio(
            String criterio);

    public EmpleadoDTO obtenerAspirantePorId(Integer idAspirante);

    public AspiranteDTO consultaAspirantePorIdentificador(Integer IdAspirante);

    public RegistroGeneralesDTO aprobarDatosGeneralesAspirante(
            DatosGeneralesDTO datosGeneralesDTO, Integer idAspirante);

    public int aprobarDomicilioAspirante(DomicilioDTO domicilioDTO,
            Integer idAspirante, Integer idEmpleado);

    public int aprobarFaseAspirante(int fase, Integer idAspirante);

    /**
     * Consulta la información básica del empleado.
     *
     * @param criterio
     *            puede ser el rfc o el nombre completo del empleado
     */
    public List<InfoEmpleadoDTO> consultaPorCriterio(String criterio);

    /**
     * Consulta a la tabla de empleados, obteniendo información de empleados
     * activos e inactivos.
     *
     * @param filtroDTO
     * @return
     */
    public List<InfoEmpleadoDTO> consultaEmpleado(FiltroDTO filtroDTO);

    /**
     * Obtiene los datos generales del empleado
     *
     * @param idEmpleado
     */
    public DatosGeneralesDTO obtenerDatosGenerales(Integer idEmpleado);

    /**
     * Obtiene los datos del domicilio del empleado.
     *
     * @param idEmpleado
     */
    public DomicilioDTO obtenerDomicilio(Integer idEmpleado);

    public void actualizarDomicilio(Integer idEmpleado, Integer idUsuario,
            DomicilioDTO domicilioDTO);

    public void actualizarDatosGenerales(DatosGeneralesDTO datosGeneralesDTO);

    /**
     *
     * @param dependienteEconomicoDTO
     * @deprecated remplazado por
     *             {@link #crearDependienteEconomico(mx.gob.saludtlax.rh.empleados.administracion.NuevoDependienteEconomicoDTO) }
     */
    @Deprecated
    public void crearDependienteEconomicoEmpleado(
            DependienteEconomicoDTO dependienteEconomicoDTO);

    /**
     * Permite agregar un nuevo dependiente económico de un empleado.
     *
     * @param dependienteEconomico
     *            La información sobre el dependiente económico.
     */
    void crearDependienteEconomico(
            DependienteEconomicoDTO dependienteEconomico);

    /**
     * Permite obtener un dependiente economico ṕor medio de su CURP.
     *
     * @param idDependienteEconomico
     *            el ID del dependiente econónmico.
     * @return la información que describe al dependiente económico.
     */
    DependienteEconomicoDTO obtenerDependienteEconimicoPorId(
            Integer idDependienteEconomico);

    /**
     * Permite obtener un dependiente economico ṕor medio de su CURP.
     *
     * @param curp
     *            la CURP del dependiente econónmico.
     * @return la información que describe al dependiente económico.
     */
    DependienteEconomicoDTO obtenerDependienteEconimicoPorCurp(String curp);

    /**
     * Permite actualizar la infomación de un dependiente economico.
     *
     * @param dependienteEconomico
     *            la infomación del dependiente económico que será actualizada.
     */
    void actualizarDependienteEconomico(
            DependienteEconomicoDTO dependienteEconomico);

    /**
     * Permite saber si exite un dependiente economico registrado con una CURP
     * dada.
     *
     * @param curp
     *            la CURP a consultar.
     * @return <code>true</code> si la CURP ya exite en el almacen de datos en
     *         caso contrario se devolvera <code>false</code>.
     */
    boolean existeDependientePorCurp(String curp);

    /**
     * Permite eliminar un dependiente económico de un empleado.
     *
     * @param idDependienteEconomico
     *            el ID del dependiente económico a eliminar.
     */
    void eliminarDependienteEconomico(Integer idDependienteEconomico);

    /**
     * Permite conocer los dependientes económicos de un empleado.
     *
     * @param idEmpleado
     *            el identificador del empleado.
     * @return una lista con los dependientes económicos del empleado indicado.
     */
    public List<InfoDependienteEconomicoDTO> consultarDependientesEmpleado(
            Integer idEmpleado);

    public List<InfoDependienteEconomicoDTO> consultarDependientesEconomicoPadres(
            Integer idEmpleado);

    public void validarCurpEmpleado(String curp);

    public String obtenerCurpEmpleado(Integer idEmpleado);

    public void validarRfcEmpleado(String rfc);

    public String obtenerRfcEmpleado(Integer idEmpleado);

    /**
     * Obtiene la lista de empleados a postularse
     *
     * @return
     */
    public List<InfoVacantePostularDTO> obtenerListaEmpleadoCandidato();

    public List<InfoEmpleadoDTO> consultarEmpleadosConPuestosActivos(
            FiltroDTO filtroDTO);

    public void crearEmpleado(AltaEmpleadoDTO altaEmpleadoDTO);

    public ValidacionEmpleadoDTO validarDatosObligatoriosEmpleado(
            Integer idEmpleado);

    /**
     * Consulta al empleado con información detallada, si el empleado es activo
     * obtiene la información del puesto que actualmente ocupa, si es inactivo
     * su información general
     */
    public EmpleadoDetalladoDTO obtenerInformacionEmpleado(Integer idEmpleado);

    /**
     * Consulta los empleados de inventario vacante con de tipo contratacion con
     * area responsable 2
     *
     * @param criterio
     * @return
     */
    public List<InfoEmpleadoDTO> consultaEmpleadosFederales(String criterio);

    /**
     * Consukta los empleados de inbventario vacante con nombramiento
     *
     * @param criterio
     * @return
     */
    public List<InfoEmpleadoDTO> empleadosPorCriterioConNombramiento(
            String criterio);

    /**
     * Consulta la bitacora de movimientos que se le han realizado al empleado
     */
    public List<BitacoraEmpleadoDTO> consultarBitacorasMovimientos(
            Integer idEmpleado);

}