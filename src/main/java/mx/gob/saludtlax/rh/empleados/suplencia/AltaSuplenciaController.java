/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.configuracion.tabulador.Tabulador;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.expediente.DocumentoAdjuntableDTO;
import mx.gob.saludtlax.rh.expediente.EntidadContexto;
import mx.gob.saludtlax.rh.expediente.EnumClasificacionExpediente;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.expediente.empleado.AdjuntoEmpleado;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 30/10/2016 12:22:02
 */
@ManagedBean(name = "altaSuplencia")
@ViewScoped
public class AltaSuplenciaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3818708010250317870L;
    @Inject
    private AdjuntoEmpleado adjuntoEmpleado;
    @Inject
    private Catalogo catalogo;
    @Inject
    private Empleado empleado;
    @Inject
    private Suplencia suplencia;
    @Inject
    private Tabulador tabulador;

    private int AREA_DESCUBIERTA = 12;

    private AltaSuplenciaView view = new AltaSuplenciaView();

    @PostConstruct
    public void inicio() {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuarioLogeado = (UsuarioDTO) httpSession
                .getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        view.setIdUsuario(usuarioLogeado.getIdUsuario());
        view.getAltaSuplencia()
                .setIdUsuarioLogeado(usuarioLogeado.getIdUsuario());
        view.setListaDocumentos(SelectItemsUtil.listaCatalogos(
                catalogo.consultarDocumentosExpedientesClasificacion(
                        EnumClasificacionExpediente.DOCUMENTOS_SUPLENCIAS)));

        QuincenaActivaDTO quincenaActivaDTO = suplencia.obtenerQuincenaActiva();
        view.setNumeroQuincenaActual(
                quincenaActivaDTO.getNumeroQuincenaActiva());
        view.setEjercicioActivo(quincenaActivaDTO.getEjercicioFiscal());
        view.setMostrarBusqueda(true);
        view.setTiposSuplencias(SelectItemsUtil
                .listaCatalogos(catalogo.consultarTiposSuplencias()));
        view.setListaTabuladores(SelectItemsUtil
                .listaCatalogos(catalogo.consultarTabuladorSuplencias()));
        view.setListaJornadas(SelectItemsUtil
                .listaCatalogos(catalogo.consultarTiposJornadasSuplencias()));

        DateTime fechaActual = FechaUtil.fechaActualSinTiempo();
        int ejercicioFiscal = fechaActual.getYear() - 2000;
        view.setFechaMaxima(fechaActual.getDayOfMonth() + "/"
                + fechaActual.getMonthOfYear() + "/" + ejercicioFiscal);
        view.setBloquearAdjunto(true);

    }

    public void buscarEmpleadoAutoComplete() {
        view.getListadoEmpleadoDTO().clear();
        if (view.getCriterioEmpleado().trim().length() > 4) {
            List<InfoEmpleadoDTO> listadoEmpleadoDTO = empleado
                    .consultaPorCriterio(view.getCriterioEmpleado());
            for (InfoEmpleadoDTO dto : listadoEmpleadoDTO) {
                SelectItem item = new SelectItem(dto.getIdEmpleado(),
                        dto.getNombre());
                view.getListadoEmpleadoDTO().add(item);
            }

            if (view.getListadoEmpleadoDTO().isEmpty()) {
                JSFUtils.warningMessageEspecifico("error", "",
                        "No se encontraron empleados con el criterio especificado.");
            }

        }
    }

    public void buscarSuplente() {
        view.getFiltro().setTipoConsulta(EnumTipoConsultaSuplencia.NOMBRE);
        view.setSuplentesAutorizados(
                suplencia.consultarSuplentesPorCriterio(view.getFiltro()));

        if (view.getSuplentesAutorizados().isEmpty()) {
            JSFUtils.errorMessage("",
                    "No se encontraron resultado con el criterio ingresado.");
        }

    }

    public void seleccionarSuplente(SuplenteDTO suplente) {
        Integer idQuincena = suplencia.obtenerIdQuincenaSuplente(
                view.getNumeroQuincenaActual(), view.getEjercicioActivo(),
                suplente.getIdSuplente());
        view.setIdQuincena(idQuincena);
        view.setBloquearAdjunto(true);
        view.setSuplenteSeleccionado(suplente);
        view.setIdSuplente(suplente.getIdSuplente());

        if (view.getIdQuincena() != null) {
            ConsultaSuplenciaDTO consulta = new ConsultaSuplenciaDTO();
            consulta.setConDetalleMovimieto(false);
            consulta.setIdQuincena(view.getIdQuincena());
            view.setDetallesSuplencias(
                    suplencia.consultarDetallesSuplenteQuincena(consulta));
        }
        view.setMostrarDetalleSuplente(true);
        view.setMostrarBusqueda(false);

        if (!view.getDetallesSuplencias().isEmpty()) {
            view.setIdQuincena(
                    view.getDetallesSuplencias().get(0).getIdQuincena());
            view.setBloquearAdjunto(false);
        }

        calcularTotales();

    }

    public void mostrarBusqueda() {
        view.setMostrarDetalleSuplente(false);
        view.setMostrarBusqueda(true);
    }

    public void calcular() {

        if (!ValidacionUtil
                .esNumeroPositivo(view.getAltaSuplencia().getIdTabulador())) {

            JSFUtils.errorMessageEspecifico("error", "",
                    "Ingrese la cantidad diaria.");
        } else if (view.getAltaSuplencia().getFechaInicio() == null) {

            JSFUtils.errorMessageEspecifico("error", "",
                    "Seleccione fecha inicio.");
        } else if (!ValidacionUtil
                .esNumeroPositivo(view.getAltaSuplencia().getIdJornada())) {

            JSFUtils.errorMessageEspecifico("error", "",
                    "Seleccione una jornada.");
        } else {

            if (FechaUtil.esFechaInicialMayorQueFinal(
                    view.getAltaSuplencia().getFechaInicio(),
                    view.getAltaSuplencia().getFechaFin())) {
                JSFUtils.errorMessageEspecifico("error", "",
                        "La fecha de inicio no puede ser mayor a la fecha fin");
            } else {

                BigDecimal sueldoDiario = tabulador
                        .obtenerSueldoDiarioPorIdTabulador(
                                view.getAltaSuplencia().getIdTabulador());
                view.getAltaSuplencia().setCantidadDiaria(sueldoDiario);
                int dias = FechaUtil.calcularDias(
                        view.getAltaSuplencia().getFechaInicio(),
                        view.getAltaSuplencia().getFechaFin());
                boolean sePagaDoble = suplencia
                        .sePagaDoble(view.getAltaSuplencia().getIdJornada());

                BigDecimal numeroDias = new BigDecimal(dias);

                BigDecimal total = numeroDias
                        .multiply(view.getAltaSuplencia().getCantidadDiaria());

                if (sePagaDoble) {
                    total = total.multiply(new BigDecimal(2));
                    sueldoDiario = sueldoDiario.multiply(new BigDecimal(2));
                    view.getAltaSuplencia().setCantidadDiaria(sueldoDiario);
                }

                view.getAltaSuplencia().setTotal(total);
                view.getAltaSuplencia().setDias(dias);
            }

        }
    }

    public void calcularTotalesEditados() {

        if (!ValidacionUtil
                .esNumeroPositivo(view.getEdicion().getIdTabulador())) {

            JSFUtils.errorMessageEspecifico("error", "",
                    "Ingrese la cantidad diaria.");
        } else if (view.getEdicion().getFechaInicio() == null) {

            JSFUtils.errorMessageEspecifico("error", "",
                    "Seleccione fecha inicio.");
        } else if (!ValidacionUtil
                .esNumeroPositivo(view.getEdicion().getIdJornada())) {

            JSFUtils.errorMessageEspecifico("error", "",
                    "Seleccione una jornada.");
        } else {

            DateTime fechaInicio = FechaUtil
                    .fechaSinTiempo(view.getEdicion().getFechaInicio());
            DateTime fechaFin = FechaUtil
                    .fechaSinTiempo(view.getEdicion().getFechaFin());

            if (fechaInicio.compareTo(fechaFin) == 1) {
                JSFUtils.errorMessageEspecifico("error", "",
                        "La fecha de inicio no puede ser mayor a la fecha fin");
            } else {

                BigDecimal sueldoDiario = tabulador
                        .obtenerSueldoDiarioPorIdTabulador(
                                view.getEdicion().getIdTabulador());
                view.getEdicion().setCantidadDiaria(sueldoDiario);
                int dias = FechaUtil.calcularDias(
                        view.getEdicion().getFechaInicio(),
                        view.getEdicion().getFechaFin());
                boolean sePagaDoble = suplencia
                        .sePagaDoble(view.getEdicion().getIdJornada());

                BigDecimal numeroDias = new BigDecimal(dias);

                BigDecimal total = numeroDias
                        .multiply(view.getEdicion().getCantidadDiaria());

                if (sePagaDoble) {
                    total = total.multiply(new BigDecimal(2));
                    sueldoDiario = sueldoDiario.multiply(new BigDecimal(2));
                    view.getEdicion().setCantidadDiaria(sueldoDiario);
                }

                view.getEdicion().setTotal(total);
                view.getEdicion().setDias(dias);
            }

        }
    }

    public void mostrarAltaSuplenciaRango() {
        String estatusQuincena = suplencia.obtenerEstatusQuincenaSuplencia(
                view.getNumeroQuincenaActual(), FechaUtil.ejercicioActual(),
                view.getSuplenteSeleccionado().getIdSuplente());
        if (estatusQuincena == null) {
            view.setMostrarAltaSuplenciaRango(true);
        } else if (!estatusQuincena.equals(EnumEstatusSuplencia.CAPTURA)) {
            JSFUtils.errorMessage("", "La quincena "
                    + view.getNumeroQuincenaActual() + " del suplente "
                    + view.getSuplenteSeleccionado().getNombre()
                    + " no puede ser modificada por que cuenta con un estatus de "
                    + estatusQuincena.toLowerCase());
        } else {
            view.setMostrarAltaSuplenciaRango(true);
        }

    }

    public void ocultarAltaSuplenciaRango() {
        view.setMostrarAltaSuplenciaRango(false);
        AltaSuplenciaDTO dto = new AltaSuplenciaDTO();
        view.setAltaSuplencia(dto);
    }

    public void mostrarAltaSuplenciaDias() {
        String estatusQuincena = suplencia.obtenerEstatusQuincenaSuplencia(
                view.getNumeroQuincenaActual(), FechaUtil.ejercicioActual(),
                view.getSuplenteSeleccionado().getIdSuplente());
        if (estatusQuincena == null) {
            view.setMostrarAltaSuplenciaDias(true);
        } else if (!estatusQuincena.equals(EnumEstatusSuplencia.CAPTURA)) {
            JSFUtils.errorMessage("", "La quincena "
                    + view.getNumeroQuincenaActual() + " del suplente "
                    + view.getSuplenteSeleccionado().getNombre()
                    + " no puede ser modificada por que cuenta con un estatus de "
                    + estatusQuincena.toLowerCase());
        } else {
            view.setMostrarAltaSuplenciaDias(true);
        }
    }

    public void ocultarAltaSuplenciaDias() {
        view.setMostrarAltaSuplenciaDias(false);
        AltaSuplenciaDTO dto = new AltaSuplenciaDTO();
        view.setAltaSuplencia(dto);
        view.getAltasSuplencias().clear();
        view.setCriterioEmpleado("");
        if (view.getIdQuincena() != null) {
            ConsultaSuplenciaDTO consulta = new ConsultaSuplenciaDTO();
            consulta.setConDetalleMovimieto(false);
            consulta.setIdQuincena(view.getIdQuincena());
            view.setDetallesSuplencias(
                    suplencia.consultarDetallesSuplenteQuincena(consulta));
        }
    }

    public void agregarDia() {
        // La fecha se agrega como inicial y final
        view.getAltaSuplencia().setFechaInicio(view.getFechaSuplencia());
        view.getAltaSuplencia().setFechaFin(view.getFechaSuplencia());

        if (!ValidacionUtil
                .esNumeroPositivo(view.getAltaSuplencia().getIdTabulador())) {

            JSFUtils.errorMessageEspecifico("errorDias", "",
                    "Ingrese la cantidad diaria.");
        } else if (view.getAltaSuplencia().getFechaInicio() == null) {

            JSFUtils.errorMessageEspecifico("errorDias", "",
                    "Seleccione fecha inicio.");

        } else if (view.getAltaSuplencia().getFechaFin() == null) {
            JSFUtils.errorMessageEspecifico("errorDias", "",
                    "Seleccione fecha fin.");
        }

        else if (!ValidacionUtil
                .esNumeroPositivo(view.getAltaSuplencia().getIdJornada())) {
            JSFUtils.errorMessageEspecifico("errorDias", "",
                    "Seleccione una jornada.");
        } else {

            BigDecimal sueldoDiario = tabulador
                    .obtenerSueldoDiarioPorIdTabulador(
                            view.getAltaSuplencia().getIdTabulador());
            view.getAltaSuplencia().setCantidadDiaria(sueldoDiario);
            int dias = FechaUtil.calcularDias(
                    view.getAltaSuplencia().getFechaInicio(),
                    view.getAltaSuplencia().getFechaFin());
            boolean sePagaDoble = suplencia
                    .sePagaDoble(view.getAltaSuplencia().getIdJornada());

            BigDecimal numeroDias = new BigDecimal(dias);

            BigDecimal total = numeroDias
                    .multiply(view.getAltaSuplencia().getCantidadDiaria());

            if (sePagaDoble) {
                total = total.multiply(new BigDecimal(2));
                sueldoDiario = sueldoDiario.multiply(new BigDecimal(2));
                view.getAltaSuplencia().setCantidadDiaria(sueldoDiario);
            }

            view.getAltaSuplencia().setTotal(total);
            view.getAltaSuplencia().setDias(dias);

            try {

                view.getAltaSuplencia()
                        .setNumeroQuincena(view.getNumeroQuincenaActual());
                view.getAltaSuplencia()
                        .setEjercicioFiscal(view.getEjercicioActivo());
                view.getAltaSuplencia()
                        .setIdSuplenteAutorizado(view.getIdSuplente());
                view.getAltaSuplencia()
                        .setIdUsuarioLogeado(view.getIdUsuario());
                Integer idQuincena = suplencia
                        .crearDetalleSuplencia(view.getAltaSuplencia());

                view.setIdQuincena(idQuincena);
                view.setBloquearAdjunto(false);

                view.getAltasSuplencias().add(view.getAltaSuplencia());

                AltaSuplenciaDTO nueva = new AltaSuplenciaDTO();
                nueva.setCantidadDiaria(
                        view.getAltaSuplencia().getCantidadDiaria());
                nueva.setDias(view.getAltaSuplencia().getDias());
                nueva.setFechaFin(null);
                nueva.setFechaInicio(null);
                nueva.setIdCausaSuplencia(
                        view.getAltaSuplencia().getIdCausaSuplencia());
                nueva.setIdEmpleado(view.getAltaSuplencia().getIdEmpleado());
                nueva.setIdJornada(view.getAltaSuplencia().getIdJornada());
                nueva.setIdMovimiento(
                        view.getAltaSuplencia().getIdMovimiento());
                nueva.setIdSuplenteAutorizado(
                        view.getAltaSuplencia().getIdSuplenteAutorizado());
                nueva.setIdTabulador(view.getAltaSuplencia().getIdTabulador());
                nueva.setIdUsuarioLogeado(
                        view.getAltaSuplencia().getIdUsuarioLogeado());
                nueva.setNumeroQuincena(view.getNumeroQuincenaActual());
                nueva.setObservaciones(
                        view.getAltaSuplencia().getObservaciones());
                nueva.setTotal(view.getAltaSuplencia().getTotal());

                view.setAltaSuplencia(nueva);
                view.setFechaSuplencia(null);

                JSFUtils.infoMessageEspecifico("errorDias", "",
                        "El detalle ha sido registrado con éxito.");
            } catch (ValidacionException | ReglaNegocioException exception) {
                JSFUtils.errorMessageEspecifico("errorDias", "",
                        exception.getMessage());
            }

        }

    }

    public void registrarDetalleRango() {

        try {
            view.getAltaSuplencia()
                    .setNumeroQuincena(view.getNumeroQuincenaActual());
            view.getAltaSuplencia()
                    .setEjercicioFiscal(view.getEjercicioActivo());
            view.getAltaSuplencia()
                    .setIdSuplenteAutorizado(view.getIdSuplente());
            Integer idQuincena = suplencia
                    .crearDetalleSuplencia(view.getAltaSuplencia());

            view.setIdQuincena(idQuincena);
            view.setBloquearAdjunto(false);
            view.setMostrarAltaSuplenciaRango(false);

            ConsultaSuplenciaDTO consulta = new ConsultaSuplenciaDTO();
            consulta.setConDetalleMovimieto(false);
            consulta.setIdQuincena(idQuincena);
            consulta.setTipoConsulta(3);
            view.setDetallesSuplencias(
                    suplencia.consultarDetallesSuplenteQuincena(consulta));

            Integer idSuplente = view.getAltaSuplencia()
                    .getIdSuplenteAutorizado();
            AltaSuplenciaDTO dto = new AltaSuplenciaDTO();
            view.setAltaSuplencia(dto);
            view.getAltaSuplencia().setIdSuplenteAutorizado(idSuplente);
            view.getAltaSuplencia().setIdUsuarioLogeado(view.getIdUsuario());
            JSFUtils.infoMessage("",
                    "¡El detalle ha sido registrado con éxito!");

        } catch (ReglaNegocioException | ValidacionException exception) {
            JSFUtils.errorMessageEspecifico("error", "",
                    exception.getMessage());
        }
    }

    public void enviaraRevision() {
        try {
            suplencia.actualizarEstatusQuincena(view.getIdQuincena(),
                    EnumEstatusSuplencia.REVISION);
            JSFUtils.infoMessage("", "La quincena se ha mandado a revisión");
            view.setMostrarDetalleSuplente(false);
            view.setMostrarBusqueda(true);

        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }
    }

    public void subirDocumentoAdjunto(FileUploadEvent evento) {
        UploadedFile archivo = evento.getFile();
        String nombreAdjunto = archivo.getFileName();
        byte[] adjunto = archivo.getContents();

        TipoArchivo extension = TipoArchivo
                .getTipoArchivoPorMIMEType(archivo.getContentType());

        // Integer idEmpleado = (Integer)
        // evento.getComponent().getAttributes().get("idEmpleado");
        Integer idDocAdj = (Integer) evento.getComponent().getAttributes()
                .get("idDocAdj");

        InformacionAdjuntoDTO info = new InformacionAdjuntoDTO();

        DocumentoAdjuntableDTO dto = new DocumentoAdjuntableDTO();
        dto.setIdDocumentoAdjuntable(idDocAdj);
        info.setEntidadContexto(EntidadContexto.SUPLENCIA);
        info.setIdEntidadContexto(view.getIdQuincena());
        info.setIdAdjunto(null);
        info.setNombreAdjunto(nombreAdjunto);
        info.setExtension(extension);
        info.setDocumentoAdjuntable(dto);
        info.setIdEmpleado(view.getSuplenteSeleccionado().getIdEmpleado());
        info.setIdExpediente(12);

        adjuntoEmpleado.crear(info, adjunto);
        JSFUtils.infoMessage("",
                "¡El documento soporte se ha adjuntado con éxito!");
        view.setIdDocumentoAdjuntable(0);
        // view.getDocumentosExpedientes().clear();
        // List<InformacionAdjuntoDTO> documentosExpedientes = adjuntoEmpleado
        // .consultarInformacionAdjuntosPorIdEmpleado(view
        // .getIdEmpleadoSeleccionado());
        // view.setDocumentosExpedientes(documentosExpedientes);
    }

    public void mostrarEdicionSuplencia(DetalleSuplenciaDTO detalle) {
        view.getEdicion().setFechaInicio(detalle.getFechaInicio());
        view.getEdicion().setFechaFin(detalle.getFechaFin());
        view.getEdicion().setCantidadDiaria(detalle.getCantidadDiaria());
        view.getEdicion().setTotal(detalle.getTotal());
        view.getEdicion().setIdJornada(detalle.getIdJornada());
        view.getEdicion()
                .setIdDetalleSuplencia(detalle.getIdDetalleSuplencia());
        view.getEdicion().setIdTabulador(detalle.getIdTabulador());
        view.getEdicion().setDias(detalle.getDias());
        view.setMostrarEdicion(true);
        view.setDetalleSeleccionado(detalle);

    }

    public void ocultarEdicion() {

        view.setMostrarEdicion(false);
    }

    public void editarSuplencia() {
        try {
            suplencia.editarSuplencia(view.getEdicion());
            view.setMostrarEdicion(false);
            ConsultaSuplenciaDTO dto = new ConsultaSuplenciaDTO();
            dto.setIdSuplenteAutorizado(
                    view.getSuplenteSeleccionado().getIdSuplente());
            dto.setConDetalleMovimieto(false);
            dto.setQuincena(view.getNumeroQuincenaActual());
            dto.setTipoConsulta(1);
            view.setDetallesSuplencias(
                    suplencia.consultarDetallesSuplenteQuincena(dto));
            calcularTotales();
            JSFUtils.infoMessage("", "El detalle ha sido editado con éxito");
        } catch (ReglaNegocioException | ValidacionException exception) {
            JSFUtils.errorMessageEspecifico("errorEdicion", "",
                    exception.getMessage());
        }
    }

    public void eliminarSuplencia(Integer idDetalleSuplencia) {
        try {
            suplencia.eliminarSuplencia(idDetalleSuplencia);
            ConsultaSuplenciaDTO dto = new ConsultaSuplenciaDTO();
            dto.setConDetalleMovimieto(false);
            dto.setIdQuincena(view.getIdQuincena());
            view.setDetallesSuplencias(
                    suplencia.consultarDetallesSuplenteQuincena(dto));
            calcularTotales();
            JSFUtils.infoMessage("", "El detalle ha sido eliminado con éxito");

        } catch (ReglaNegocioException | ValidacionException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }
    }

    public void seleccionarTipoSuplencia() {
        view.setMostrarEmpleado(false);
        if (view.getAltaSuplencia().getIdCausaSuplencia() != AREA_DESCUBIERTA) {
            view.setMostrarEmpleado(true);
        }
    }

    public void seleccionarJornada() {
        view.getAltaSuplencia().setIdTabulador(0);
        view.getAltaSuplencia().setFechaInicio(null);
        view.getAltaSuplencia().setFechaFin(null);
    }

    public void seleccionarJornadaEdicion() {
        view.getEdicion().setIdTabulador(0);
        view.getEdicion().setFechaInicio(null);
        view.getEdicion().setFechaFin(null);
    }

    public void seleccionarTabulador() {
        view.getAltaSuplencia().setFechaInicio(null);
        view.getAltaSuplencia().setFechaFin(null);
    }

    public void seleccionarTabuladorEdicion() {
        view.getEdicion().setFechaInicio(null);
        view.getEdicion().setFechaFin(null);
    }

    public void mostrarAdjuntos() {

        if (view.getIdQuincena() != null) {
            List<InformacionAdjuntoDTO> documentosAdjuntosGradoAcademico = adjuntoEmpleado
                    .consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(
                            EntidadContexto.SUPLENCIA, view.getIdQuincena());
            view.setDocumentosAdjuntos(documentosAdjuntosGradoAcademico);
            if (!view.getDocumentosAdjuntos().isEmpty()) {
                view.setMostrarDocumentacion(true);
            } else {
                JSFUtils.warningMessage("", "Quincena sin documentación.");
            }

        } else {
            JSFUtils.errorMessage("",
                    "La quincena no tiene asignado ningun detalle");
        }

    }

    public void ocultarAdjuntos() {
        view.setMostrarDocumentacion(false);
        view.getDocumentosAdjuntos().clear();
    }

    public void onDateSelectEdicion(SelectEvent event) {
        view.getEdicion().setFechaFin(null);

    }

    public void descargarAdjunto(InformacionAdjuntoDTO adjunto)
            throws IOException {

        byte[] bytes = adjuntoEmpleado
                .obtenerAdjuntoPorIdAdjunto(adjunto.getIdAdjunto());

        JSFUtils.descargarArchivo(bytes, adjunto.getNombreAdjunto(),
                adjunto.getExtension().getMIMEType());
        JSFUtils.infoMessage("Descarga iniciada",
                "La descarga del archivo ha iniciado.");

    }

    public void calcularTotales() {
        int totalDias = 0;
        BigDecimal totalSuplencias = BigDecimal.ZERO;

        if (!view.getDetallesSuplencias().isEmpty()) {
            for (DetalleSuplenciaDTO dto : view.getDetallesSuplencias()) {
                totalDias = totalDias + dto.getDias();
                totalSuplencias = totalSuplencias.add(dto.getTotal());
            }
        }

        view.setTotalDias(totalDias);
        view.setTotalSuplencias(totalSuplencias);
    }

    public void onDateSelect(SelectEvent event) {
        view.getAltaSuplencia().setFechaFin(null);

    }

    public AltaSuplenciaView getView() {
        return view;
    }

    public void setView(AltaSuplenciaView view) {
        this.view = view;
    }

}
