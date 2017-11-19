/*
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import javax.inject.Inject;
import javax.interceptor.Interceptors;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.AsentamientoEntity;
import mx.gob.saludtlax.rh.persistencia.AsentamientoRepository;
import mx.gob.saludtlax.rh.persistencia.DireccionEntity;
import mx.gob.saludtlax.rh.persistencia.DireccionRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.EstadoEntity;
import mx.gob.saludtlax.rh.persistencia.EstadoRepository;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoRepository;
import mx.gob.saludtlax.rh.persistencia.MunicipioRepository;
import mx.gob.saludtlax.rh.persistencia.MunicipiosEntity;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 * @since 15/06/2016 13:36:26
 *
 */
public class EmpleadoService {

    @Inject
    private AsentamientoRepository asentamientoRepository;
    @Inject
    private BitacoraModificacionService bitacoraModificacionService;
    @Inject
    private DireccionRepository direccionRepository;
    @Inject
    private EmpleadoRepository empleadoRepository;
    @Inject
    private EstadoRepository estadoRepository;
    @Inject
    private HistorialAcademicoRepository historialAcademicoRepository;
    @Inject
    private MunicipioRepository municipioRepository;

    @Interceptors({ ActualizarDomicilioValidator.class })
    public void actualizarDomicilio(Integer idEmpleado, Integer idUsuario, DomicilioDTO domicilioDTO) {

        // Valida datos los para la bitacora
        if (ValidacionUtil.esCadenaVacia(domicilioDTO.getComentarioMovimiento()) | ValidacionUtil.esNumeroPositivo(domicilioDTO.getIdUsuarioEnSesion())) {

            bitacoraModificacionService.registrarBitacoraModificacionDomicilio(domicilioDTO, idEmpleado);
        }

        // String contexto = "Actualización domicilio: ";
        EmpleadoEntity empleado = validarEmpleado(idEmpleado);
        DireccionEntity direccion = direccionRepository.consultarDireccionEmpleadoPorId(idEmpleado);
        String direccionCompleta = "";
        EstadoEntity estado = estadoRepository.estadoPorId(domicilioDTO.getIdEstado());
        MunicipiosEntity municipio = municipioRepository.obtenerPorId(domicilioDTO.getIdMunicipio());

        AsentamientoEntity asentamiento = asentamientoRepository.obtenerPorId(domicilioDTO.getIdAsentamiento());
        if (direccion == null) {

            DireccionEntity direccionEntity = new DireccionEntity();
            direccionEntity.setCalle(domicilioDTO.getCalle());
            direccionEntity.setCodigoPostal(asentamiento.getCodigoPostal());
            direccionEntity.setEstado(estado);
            direccionEntity.setIdEmpleado(idEmpleado);
            direccionEntity.setMunicipio(municipio);
            direccionEntity.setNumeroExterior(domicilioDTO.getNumeroExterior());
            direccionEntity.setNumeroInterior(domicilioDTO.getNumeroInterior());
            direccionEntity.setAsentamiento(asentamiento);
            direccionRepository.crear(direccionEntity);

            direccionCompleta = direccionEntity.direccionCompleta();
        } else {
            direccion.setCalle(domicilioDTO.getCalle());
            direccion.setCodigoPostal(asentamiento.getCodigoPostal());
            direccion.setEstado(estado);
            direccion.setIdEmpleado(idEmpleado);
            direccion.setMunicipio(municipio);
            direccion.setNumeroExterior(domicilioDTO.getNumeroExterior());
            direccion.setNumeroInterior(domicilioDTO.getNumeroInterior());
            direccion.setAsentamiento(asentamiento);
            direccionCompleta = direccion.direccionCompleta();
            direccionRepository.actualizar(direccion);
        }
        empleado.setDireccionCompleta(direccionCompleta);
        empleadoRepository.actualizar(empleado);

        /*
         * RegistrarMovimientoDTO dto = new RegistrarMovimientoDTO();
         * dto.setComentarios("ACTUALIZACION PORTAL");
         * dto.setEmpleado(empleado);
         * dto.setIdMovimiento(EnumTipoActualizacion.DATOS_GENERALES);
         * dto.setIdUsuario(idUsuario);
         * dto.setLccActual(domicilioDTO.lccDomicilio());
         * dto.setLccAnterior(lccAnterior);
         * bitacoraMovimientoService.registrarBitacoraMovimiento(dto);
         */
    }

    public void actualizarDatosGenerales(Integer idUsuario, DatosGeneralesDTO datosGeneralesDTO) {

        // Valida datos los para la bitacora
        if (ValidacionUtil.esCadenaVacia(datosGeneralesDTO.getComentarioMovimiento())
                | ValidacionUtil.esNumeroPositivo(datosGeneralesDTO.getIdUsuarioEnSesion())) {

            bitacoraModificacionService.registrarBitacoraModificacionDatoGeneral(datosGeneralesDTO);

        }

        EmpleadoEntity empleado = validarEmpleado(datosGeneralesDTO.getIdEmpleado());
        /*
         * RegistrarMovimientoDTO dto = new RegistrarMovimientoDTO();
         * dto.setComentarios("ACTUALIZACION PORTAL");
         * dto.setEmpleado(empleado);
         * dto.setIdMovimiento(EnumTipoActualizacion.DATOS_GENERALES);
         * dto.setIdUsuario(idUsuario);
         * dto.setLccActual(datosGeneralesDTO.lccDatosGenerales());
         * dto.setLccAnterior(empleado.lccDatosGenerales());
         * bitacoraMovimientoService.registrarBitacoraMovimiento(dto);
         */

        if (empleado.getRfc().compareTo(datosGeneralesDTO.getRfc().trim()) != 0) {
            validarRfcEmpleado(datosGeneralesDTO.getRfc());
            empleado.setRfc(datosGeneralesDTO.getRfc());
        }

        if (empleado.getCurp().compareToIgnoreCase(datosGeneralesDTO.getCurp().trim()) != 0) {
            validarCurpEmpleado(datosGeneralesDTO.getCurp());
            empleado.setCurp(datosGeneralesDTO.getCurp());
        }

        empleado.setNombre(datosGeneralesDTO.getNombre());
        empleado.setApellidoPaterno(datosGeneralesDTO.getApellidoPaterno());
        empleado.setApellidoMaterno(datosGeneralesDTO.getApellidoMaterno());
        empleado.setIdSexo(datosGeneralesDTO.getIdSexo());
        empleado.setEstadoCivil(datosGeneralesDTO.getIdEstadoCivil());
        empleado.setFechaNacimiento(datosGeneralesDTO.getFechaNacimiento());
        empleado.setLugarNacimiento(datosGeneralesDTO.getLugarNacimiento());
        empleado.setTelefono(datosGeneralesDTO.getTelefonos());
        empleado.setCorreoElectronico(datosGeneralesDTO.getCorreo());
        empleado.setTipoSangre(datosGeneralesDTO.getIdTipoSangre());
        empleado.setPeso(datosGeneralesDTO.getPeso());
        empleado.setEstatura(datosGeneralesDTO.getEstatura());
        empleado.setTienePersonasDependientes(datosGeneralesDTO.getTienePersonasDependientes());
        empleado.setNumeroHijos(datosGeneralesDTO.getNumeroHijos());
        empleado.setNumeroPadres(datosGeneralesDTO.getNumeroPadres());
        empleado.setNumeroConyuges(datosGeneralesDTO.getNumeroConyuges());
        empleado.setNumeroOtros(datosGeneralesDTO.getNumeroOtros());
        empleado.setOtroParentesco(datosGeneralesDTO.getOtroParentesco());
        empleado.setNacionalidad(datosGeneralesDTO.getNacionalidad());
        empleado.toUpperCase();
        String nombreCompleto = empleado.nombreCompleto();
        empleado.setNombreCompleto(nombreCompleto);
        empleadoRepository.actualizar(empleado);

    }

    protected void validarCurpEmpleado(String curp) {
        if (ValidacionUtil.esCadenaVacia(curp)) {
            throw new BusinessException("Por favor envie una curp.");
        }
        if (!curp.matches("[A-Z]{4}[0-9]{6}[H,M][A-Z]{5}[0-9]{2}")) {
            throw new BusinessException("El formato de la curp  es invalido, por favor ingrese una curp valida.");
        }

        if (empleadoRepository.existeEmpleadoConCurp(curp.trim().toUpperCase())) {
            throw new BusinessException("La curp que ha ingresado ya ha sido asignada, ingrese una nueva.");
        }

    }

    private EmpleadoEntity validarEmpleado(Integer idEmpleado) {
        String contexto = "Actualizacion datos: ";
        EmpleadoEntity empleado = empleadoRepository.obtenerPorId(idEmpleado);
        if (empleado == null) {
            throw new BusinessException(contexto + "El empleado con identificador " + idEmpleado + " no esta registrado en el sistema");
        }

        if (empleado.getIdEstatus().equals("INACTIVO")) {
            throw new BusinessException(contexto + " El empleado no esta activo.");
        }

        return empleado;
    }

    protected String obtenerCurpEmpleado(Integer idEmpleado) {
        if (!ValidacionUtil.esNumeroPositivo(idEmpleado)) {
            throw new ValidacionException("obtenerCurpEmpleado: El identificador del empleado es requerido.", ValidacionCodigoError.NUMERO_NEGATIVO);
        }
        return empleadoRepository.obtenerCurpEmpleado(idEmpleado);
    }

    protected void validarRfcEmpleado(String rfc) {
        String contexto = "validarRfcEmpleado: ";
        if (ValidacionUtil.esCadenaVacia(rfc)) {
            throw new ValidacionException(contexto + "Por favor envie un rfc.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.validarRfc(rfc)) {
            throw new ValidacionException(contexto + "El formato del rfc  es invalido, por favor ingrese el rfc valida.",
                    ValidacionCodigoError.FORMATO_INVALIDO);
        }

        if (empleadoRepository.existeEmpleadoConRfc(rfc.trim().toUpperCase())) {
            throw new ReglaNegocioException(contexto + "El rfc que ha ingresado ya ha sido asignada, ingrese una nueva.",
                    ReglaNegocioCodigoError.RFC_REGISTRADO);
        }
    }

    protected String obtenerRfcEmpleado(Integer idEmpleado) {

        if (!ValidacionUtil.esNumeroPositivo(idEmpleado)) {
            throw new ValidacionException("obtenerRfcEmpleado: El identificador del empleado es requerido.", ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        return empleadoRepository.obtenerRfcEmpleado(idEmpleado);
    }

    protected ValidacionEmpleadoDTO validarDatosObligatorios(Integer idEmpleado) {
        ValidacionEmpleadoDTO validacion = new ValidacionEmpleadoDTO();
        EmpleadoEntity empleado = empleadoRepository.obtenerPorId(idEmpleado);
        if (empleado == null) {
            throw new ReglaNegocioException("El empleado con identificador " + idEmpleado + "no está registrado", ReglaNegocioCodigoError.SIN_REGISTRO);
        }
        validacion.setEsValido(true);
        validacion.setMensaje("");
        String mensaje = "Se detectó que el empleado no cuenta con los siguientes datos obligatorios: ";
        String campos = "";

        if (ValidacionUtil.esCadenaVacia(empleado.getNombreCompleto())) {
            validacion.setEsValido(false);
            campos = "nombre, ";
        }
        if (ValidacionUtil.esCadenaVacia(empleado.getRfc())) {
            validacion.setEsValido(false);
            campos = campos + "rfc, ";
        }
        if (ValidacionUtil.esCadenaVacia(empleado.getCurp())) {
            validacion.setEsValido(false);
            campos = campos + "curp, ";
        }
        if (empleado.getFechaNacimiento() == null) {
            validacion.setEsValido(false);
            campos = campos + "fecha nacimiento, ";
        }
        if (ValidacionUtil.esCadenaVacia(empleado.getIdSexo())) {
            validacion.setEsValido(false);
            campos = campos + "sexo, ";
        }
        if (ValidacionUtil.esCadenaVacia(empleado.getEstadoCivil())) {

            validacion.setEsValido(false);
            campos = campos + "estado civil, ";
        }
        if (ValidacionUtil.esCadenaVacia(empleado.getDireccionCompleta())) {

            validacion.setEsValido(false);
            campos = campos + "domicilio, ";
        }
        if (!historialAcademicoRepository.tieneHistorialAcademicoEmpleado(empleado.getIdEmpleado())) {
            validacion.setEsValido(false);
            campos = campos + "historial academico, ";
        }

        if (!validacion.isEsValido()) {
            campos = campos.substring(0, campos.length() - 2);
            validacion.setMensaje(mensaje + campos + ".");
        }

        return validacion;
    }
}
