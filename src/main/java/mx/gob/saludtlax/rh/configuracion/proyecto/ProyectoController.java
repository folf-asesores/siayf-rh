/*
 * ProyectoController.java
 * Creado el 25/07/2016 01:43:03 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.proyecto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.catalogos.areasadscripcion.AreaAdscripcion;
import mx.gob.saludtlax.rh.catalogos.areasadscripcion.AreaAdscripcionDTO;
import mx.gob.saludtlax.rh.configuracion.dependencia.Dependencia;
import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaDTO;
import mx.gob.saludtlax.rh.configuracion.ejerciciofiscal.EjercicioFiscalEJB;
import mx.gob.saludtlax.rh.configuracion.ejerciciofiscal.EjercicioFiscalListaDTO;
import mx.gob.saludtlax.rh.configuracion.estrategia.Estrategia;
import mx.gob.saludtlax.rh.configuracion.estrategia.EstrategiaDTO;
import mx.gob.saludtlax.rh.configuracion.lineaaccion.LineaAccion;
import mx.gob.saludtlax.rh.configuracion.lineaaccion.LineaAccionDTO;
import mx.gob.saludtlax.rh.configuracion.sector.Sector;
import mx.gob.saludtlax.rh.configuracion.sector.SectorDTO;
import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsable;
import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsableDTO;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@ManagedBean
@ViewScoped
public class ProyectoController implements Serializable {

    private static final long serialVersionUID = -6137517116110299248L;
    private static final Logger LOGGER = Logger
            .getLogger(ProyectoController.class.getName());

    @Inject
    private AreaAdscripcion areaAdscripcionEJB;
    @Inject
    private Dependencia dependenciaEJB;
    @Inject
    private EjercicioFiscalEJB ejercicioFiscalEJB;
    @Inject
    private Estrategia estrategiaEJB;
    @Inject
    private LineaAccion lineaAccionEJB;
    @Inject
    private Proyecto proyectoEJB;
    @Inject
    private Sector sectorEJB;
    @Inject
    private UnidadResponsable unidadResponsableEJB;

    private ProyectoView view;

    /**
     * Permite inicializar las propiedades que el contenedor no puede
     * inicializar.
     */
    @PostConstruct
    public void init() {
        view = new ProyectoView();
    }

    public void guardar() {
        if (view.isEditar()) {
            proyectoEJB.actualizar(view.getProyectoDTO());
        } else {
            proyectoEJB.crear(view.getProyectoDTO());
        }

        mostrarTablaProyecto();
    }

    public void eliminar(Integer idProyecto) {
        proyectoEJB.eliminar(idProyecto);
    }

    public void mostrarPanelNuevo() {
        view.setProyectoDTO(new ProyectoDTO());
        view.setVisiblePanelNuevo(true);
        view.setVisibleTablaProyecto(false);
    }

    public void mostrarTablaProyecto() {
        view.setProyectoDTO(new ProyectoDTO());
        view.setVisiblePanelNuevo(false);
        view.setVisibleTablaProyecto(true);
    }

    public List<String> obtenerDependenciasPorConsulta(String consulta) {
        return dependenciaEJB
                .consultarDescripcionDependenciasPorCriterio(consulta);
    }

    public List<String> obtenerSectorPorConsulta(String consulta) {
        return sectorEJB.consultarDescripcionSectorPorCriterio(consulta);
    }

    public List<String> obtenerUnidadResponsablePorConsulta(String consulta) {
        return unidadResponsableEJB
                .consultarDescripcionUnidadesResponsablesPorCriterio(consulta);
    }

    public List<String> obtenerLineaAccionPorConsulta(String consulta) {
        return lineaAccionEJB
                .consultarDescripcionLineaAccionPorCriterio(consulta);
    }

    public List<String> obtenerEstrategiaPorConsulta(String consulta) {
        return estrategiaEJB
                .consultarDescripcionEstrategiaPorCriterio(consulta);
    }

    public List<String> obtenerAreaAdscripcionPorConsulta(String consulta) {
        return areaAdscripcionEJB.consultarAreaAdscripcionPorCriterio(consulta);
    }

    public List<SelectItem> getEjerciciosFiscales() {
        List<SelectItem> items = new ArrayList<>();

        for (EjercicioFiscalListaDTO ejercicioFiscal : ejercicioFiscalEJB
                .obtenerEjercicioFiscalLista()) {
            SelectItem item = new SelectItem(
                    ejercicioFiscal.getEjercicioFiscal(),
                    ejercicioFiscal.getEjercicioFiscal().toString());

            items.add(item);
        }

        return items;
    }

    public String getDescripcionDependencia(int idDependencia) {
        if (idDependencia > 0) {
            DependenciaDTO dependencia = new DependenciaDTO();
            dependencia.setIdDependencia(idDependencia);
            dependencia = dependenciaEJB.obtenerDependencia(dependencia);

            return dependencia == null ? ""
                    : (dependencia.getDescripcion() == null ? ""
                            : dependencia.getDescripcion());
        } else {
            return "";
        }
    }

    public String getDescripcionUnidadResponsable(int idUnidadResponsable) {
        if (idUnidadResponsable > 0) {
            UnidadResponsableDTO unidadResponsable = new UnidadResponsableDTO();
            unidadResponsable.setIdUnidadResponsable(idUnidadResponsable);
            unidadResponsable = unidadResponsableEJB
                    .obtenerUnidadResponsable(unidadResponsable);

            return unidadResponsable == null ? ""
                    : (unidadResponsable.getDescripcion() == null ? ""
                            : unidadResponsable.getDescripcion());
        } else {
            return "";
        }
    }

    public String getDescripcionSector(int idSector) {
        SectorDTO sector = sectorEJB.obtenerPorId(idSector);

        return sector == null ? "" : (sector.getDescripcion() == null ? ""
                : sector.getDescripcion());
    }

    public String getDescripcionLineaAccion(int idLineaAccion) {
        LineaAccionDTO lineaAccion = lineaAccionEJB.obtenerPorId(idLineaAccion);

        return lineaAccion == null ? "" : (lineaAccion.getDescripcion() == null
                ? "" : lineaAccion.getDescripcion());
    }

    public String getDescripcionEstrategia(int idEstrategia) {
        if (idEstrategia > 0) {
            EstrategiaDTO estrategia = estrategiaEJB.obtenerPorId(idEstrategia);

            return estrategia == null ? "" : (estrategia.getEstrategia() == null
                    ? "" : estrategia.getEstrategia());
        } else {
            return "";
        }
    }

    public String getDescripcionAreaAdscripcion(int idAreaAdscripcion) {
        if (idAreaAdscripcion > 0) {
            AreaAdscripcionDTO areaAdscripcion = areaAdscripcionEJB
                    .obtenerPorId(idAreaAdscripcion);

            return areaAdscripcion == null ? ""
                    : (areaAdscripcion.getAreaAdscripcion() == null ? ""
                            : areaAdscripcion.getAreaAdscripcion());
        } else {
            return "";
        }
    }

    public List<ProyectoDTO> getProyectos() {
        return proyectoEJB.consultarProyectosPorEjercicioFiscal(
                view.getEjercicioFiscal());
    }

    public ProyectoView getView() {
        return view;
    }

    public void setView(ProyectoView view) {
        this.view = view;
    }
}
