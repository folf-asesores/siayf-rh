
package mx.gob.saludtlax.rh.acumulados;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoEJB;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.siif.ConsultaNominaService;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.TipoArchivo;

@ManagedBean
@ViewScoped
public class AcumuladosController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1153038242482285626L;

    @Inject
    private TipoNombramientoEJB nombramientoEJB;

    @Inject
    private ConsultaNominaService consultaNominaService;

    private Integer quincenaInicial;

    private Integer quincenaFinal;

    private Integer anio;

    private byte[] bytes = null;

    private List<SelectItem> tiposNombramientosItems = new ArrayList<>();

    private Integer nombramientoSeleccionado;

    private String claveNombramientoSeleccionado;

    private List<AcumuladosDTO> listaNominas = new ArrayList<>();

    private List<SelectItem> quincenas = new ArrayList<>();

    @PostConstruct
    public void inicio() {
        List<TipoNombramientoDTO> tiposNom = new ArrayList<>();
        tiposNom = nombramientoEJB.obtenerNombramientoLista();
        tiposNombramientosItems.clear();
        tiposNombramientosItems.add(new SelectItem(-1, "Todos"));
        for (TipoNombramientoDTO tipoNombramiento : tiposNom) {
            tiposNombramientosItems.add(
                    new SelectItem(tipoNombramiento.getIdTipoNombramiento(),
                            (tipoNombramiento.getClave().isEmpty()
                                    ? tipoNombramiento.getDescripcion()
                                    : tipoNombramiento.getClave())));
        }

        for (int i = 1; i <= 24; i++) {
            quincenas.add(new SelectItem(i, "Quincena-" + i));
        }

    }

    public void realizarBusqueda() {
        listaNominas.clear();
        List<AcumuladosDTO> dts = new ArrayList<>();
        try {
            if (nombramientoSeleccionado > 0) {

                claveNombramientoSeleccionado = nombramientoEJB
                        .obtenerNombramiento(nombramientoSeleccionado)
                        .getClave();
            }
            dts = consultaNominaService.listaConsultaNominaPorNombramiento(
                    nombramientoSeleccionado, quincenaInicial, quincenaFinal,
                    anio);
            System.out.println("acumulados:" + dts.size());
            listaNominas.addAll(dts);
        } catch (NoResultException e) {
            throw new BusinessException();
        }
    }

    public void descargarExcel() {
        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(
                    ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

            String[] parametros = { "ID_USUARIO",
                    String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
                    "acumulados", "TIPO_REPORTE", "xlsx", "TIPO_NOMBRAMIENTO",
                    String.valueOf(nombramientoSeleccionado),
                    "QUINCENA_INICIAL", String.valueOf(quincenaInicial),
                    "QUINCENA_FINAL", String.valueOf(quincenaFinal),
                    "ANIO_REAL", String.valueOf(anio), };

            AdministradorReportes admintradorReportes = new AdministradorReportes();
            String referencia = admintradorReportes
                    .obtenerReferencia(parametros);

            setBytes(admintradorReportes.obtenerReporte(referencia));

            if (getBytes() != null) {
                JSFUtils.descargarArchivo(getBytes(), "acumulados",
                        TipoArchivo.getMIMEType("xlsx"));

            }

        } catch (NullPointerException | IllegalArgumentException
                | IOException exception) {
            System.err.println(exception.getMessage());
        } catch (ReglaNegocioException reglaNegocioException) {
            throw new ReglaNegocioException(reglaNegocioException.getMessage(),
                    reglaNegocioException.getCodigoError());
        } catch (ValidacionException validacionException) {
            System.err.println(validacionException.getMessage());
            throw new ValidacionException(validacionException.getMessage(),
                    null);
        }
    }

    public TipoNombramientoEJB getNombramientoEJB() {
        return nombramientoEJB;
    }

    public void setNombramientoEJB(TipoNombramientoEJB nombramientoEJB) {
        this.nombramientoEJB = nombramientoEJB;
    }

    public List<SelectItem> getTiposNombramientosItems() {
        return tiposNombramientosItems;
    }

    public void setTiposNombramientosItems(
            List<SelectItem> tiposNombramientosItems) {
        this.tiposNombramientosItems = tiposNombramientosItems;
    }

    public Integer getNombramientoSeleccionado() {
        return nombramientoSeleccionado;
    }

    public void setNombramientoSeleccionado(Integer nombramientoSeleccionado) {
        this.nombramientoSeleccionado = nombramientoSeleccionado;
    }

    public Integer getQuincenaInicial() {
        return quincenaInicial;
    }

    public void setQuincenaInicial(Integer quincenaInicial) {
        this.quincenaInicial = quincenaInicial;
    }

    public Integer getQuincenaFinal() {
        return quincenaFinal;
    }

    public void setQuincenaFinal(Integer quincenaFinal) {
        this.quincenaFinal = quincenaFinal;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public List<SelectItem> getQuincenas() {
        return quincenas;
    }

    public void setQuincenas(List<SelectItem> quincenas) {
        this.quincenas = quincenas;
    }

    public List<AcumuladosDTO> getListaNominas() {
        return listaNominas;
    }

    public void setListaNominas(List<AcumuladosDTO> listaNominas) {
        this.listaNominas = listaNominas;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getClaveNombramientoSeleccionado() {
        return claveNombramientoSeleccionado;
    }

    public void setClaveNombramientoSeleccionado(
            String claveNombramientoSeleccionado) {
        this.claveNombramientoSeleccionado = claveNombramientoSeleccionado;
    }

}
