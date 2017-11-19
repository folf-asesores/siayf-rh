
package mx.gob.saludtlax.rh.nomina.movimientosnomina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaFederalesDTO;
import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaFederalesService;
import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean
@ViewScoped
public class ConfiguracionTiposMovimientoNominaController
        implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1677426588024670661L;

    @Inject
    ConfiguracionTipoMovimientoEJB configuracionTipoMovimientoEJB;

    @Inject
    TipoMovimientosNominaEJB tipoMovimientosNominaEJB;

    @Inject
    ConceptoNominaFederalesService conceptoNominaService;
    private List<ConfiguracionTipoMovimientoDTO> listaDeConfiguraciones = new ArrayList<>();
    private List<TipoMovimientoNominaDTO> listaMovimientos = new ArrayList<>();
    private List<TipoMovimientoNominaDTO> listaMovimientosfilter = new ArrayList<>();
    private List<SelectItem> itemsTiposMov = new ArrayList<>();

    private List<ConceptoNominaFederalesDTO> listaConceptos = new ArrayList<>();
    private List<ConceptoNominaFederalesDTO> listaConceptosSeleccionados = new ArrayList<>();
    private ConfiguracionTipoMovimientoDTO configuracionNew = new ConfiguracionTipoMovimientoDTO();
    private ConfiguracionTipoMovimientoDTO configuracinSeleccionada;
    private TipoMovimientoNominaDTO tipoMovimientoSeleccionado = new TipoMovimientoNominaDTO();
    private Integer timovSeleccionado;

    private Boolean mostrarPanelbusqueda = true;
    private Boolean mostrarPanelConf = false;

    @PostConstruct
    public void inicio() {
        listaMovimientos = new ArrayList<>();
        listaMovimientos = tipoMovimientosNominaEJB.obtenerListaMovimientos();
        itemsTiposMov.clear();
        for (TipoMovimientoNominaDTO dto : listaMovimientos) {
            itemsTiposMov.add(new SelectItem(dto.getIdTimpoMovimiento(),
                    dto.getClave() + " - " + dto.getDescripcion()));
        }

        listaConceptos = new ArrayList<>();
        listaConceptos = conceptoNominaService.listaConceptosNomina();
    }

    public void buscarConfiguracion(TipoMovimientoNominaDTO dto) {
        configuracionNew = new ConfiguracionTipoMovimientoDTO();
        configuracionNew = configuracionTipoMovimientoEJB
                .configuracionPorTipoMovimiento(
                        tipoMovimientoSeleccionado.getIdTimpoMovimiento());
        mostrarPanelbusqueda = false;
        mostrarPanelConf = true;
        if (configuracionNew != null) {
            listaConceptosSeleccionados.clear();
            listaConceptosSeleccionados = configuracionNew
                    .getListConceptoNomina();
        }
    }

    public void guardarConfiguracion() {
        try {
            configuracionNew = new ConfiguracionTipoMovimientoDTO();
            configuracionNew.setTipoMovimiento(tipoMovimientoSeleccionado);
            configuracionNew.setListConceptoNomina(listaConceptosSeleccionados);
            configuracionTipoMovimientoEJB.crear(configuracionNew);
            JSFUtils.infoMessage("", "Se creo la configuracion correctamente.");
        } catch (PersistenceException e) {
            JSFUtils.errorMessage("",
                    "Ocurrio un error al intentar guardar la configuracion.");
        }
    }

    public void regresar() {
        mostrarPanelbusqueda = true;
        mostrarPanelConf = false;
    }

    public List<ConfiguracionTipoMovimientoDTO> getListaDeConfiguraciones() {
        return listaDeConfiguraciones;
    }

    public void setListaDeConfiguraciones(
            List<ConfiguracionTipoMovimientoDTO> listaDeConfiguraciones) {
        this.listaDeConfiguraciones = listaDeConfiguraciones;
    }

    public List<TipoMovimientoNominaDTO> getListaMovimientos() {
        return listaMovimientos;
    }

    public void setListaMovimientos(
            List<TipoMovimientoNominaDTO> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    public List<ConceptoNominaFederalesDTO> getListaConceptos() {
        return listaConceptos;
    }

    public void setListaConceptos(
            List<ConceptoNominaFederalesDTO> listaConceptos) {
        this.listaConceptos = listaConceptos;
    }

    public ConfiguracionTipoMovimientoDTO getConfiguracionNew() {
        return configuracionNew;
    }

    public void setConfiguracionNew(
            ConfiguracionTipoMovimientoDTO configuracionNew) {
        this.configuracionNew = configuracionNew;
    }

    public ConfiguracionTipoMovimientoDTO getConfiguracinSeleccionada() {
        return configuracinSeleccionada;
    }

    public void setConfiguracinSeleccionada(
            ConfiguracionTipoMovimientoDTO configuracinSeleccionada) {
        this.configuracinSeleccionada = configuracinSeleccionada;
    }

    public TipoMovimientoNominaDTO getTipoMovimientoSeleccionado() {
        return tipoMovimientoSeleccionado;
    }

    public void setTipoMovimientoSeleccionado(
            TipoMovimientoNominaDTO tipoMovimientoSeleccionado) {
        this.tipoMovimientoSeleccionado = tipoMovimientoSeleccionado;
    }

    public List<SelectItem> getItemsTiposMov() {
        return itemsTiposMov;
    }

    public void setItemsTiposMov(List<SelectItem> itemsTiposMov) {
        this.itemsTiposMov = itemsTiposMov;
    }

    public Integer getTimovSeleccionado() {
        return timovSeleccionado;
    }

    public void setTimovSeleccionado(Integer timovSeleccionado) {
        this.timovSeleccionado = timovSeleccionado;
    }

    public Boolean getMostrarPanelbusqueda() {
        return mostrarPanelbusqueda;
    }

    public void setMostrarPanelbusqueda(Boolean mostrarPanelbusqueda) {
        this.mostrarPanelbusqueda = mostrarPanelbusqueda;
    }

    public Boolean getMostrarPanelConf() {
        return mostrarPanelConf;
    }

    public void setMostrarPanelConf(Boolean mostrarPanelConf) {
        this.mostrarPanelConf = mostrarPanelConf;
    }

    public List<ConceptoNominaFederalesDTO> getListaConceptosSeleccionados() {
        return listaConceptosSeleccionados;
    }

    public void setListaConceptosSeleccionados(
            List<ConceptoNominaFederalesDTO> listaConceptosSeleccionados) {
        this.listaConceptosSeleccionados = listaConceptosSeleccionados;
    }

    public List<TipoMovimientoNominaDTO> getListaMovimientosfilter() {
        return listaMovimientosfilter;
    }

    public void setListaMovimientosfilter(
            List<TipoMovimientoNominaDTO> listaMovimientosfilter) {
        this.listaMovimientosfilter = listaMovimientosfilter;
    }

}
