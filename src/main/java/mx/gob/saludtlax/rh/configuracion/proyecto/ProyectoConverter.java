/*
 *
 * ProyectoConverter.java
 * Creado el Jul 29, 2016 4:28:36 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.proyecto;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.catalogos.areasadscripcion.AreaAdscripcion;
import mx.gob.saludtlax.rh.catalogos.areasadscripcion.AreaAdscripcionDTO;
import mx.gob.saludtlax.rh.configuracion.dependencia.Dependencia;
import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaDTO;
import mx.gob.saludtlax.rh.configuracion.estrategia.Estrategia;
import mx.gob.saludtlax.rh.configuracion.estrategia.EstrategiaDTO;
import mx.gob.saludtlax.rh.configuracion.lineaaccion.LineaAccion;
import mx.gob.saludtlax.rh.configuracion.lineaaccion.LineaAccionDTO;
import mx.gob.saludtlax.rh.configuracion.sector.Sector;
import mx.gob.saludtlax.rh.configuracion.sector.SectorDTO;
import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsable;
import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsableDTO;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@FacesConverter(value = "proyectoConverter")
public class ProyectoConverter implements Converter {
    private static final Logger LOGGER = Logger
            .getLogger(ProyectoConverter.class.getName());
    private static final String AREA_ADSCRIPCION_BEAN = "java:module/AreaAdscripcionEJB";
    private static final String DEPENDENCIA_BEAN = "java:module/DependenciaEJB";
    private static final String UNIDAD_RESPONSABLE_BEAN = "java:module/UnidadResponsableEJB";
    private static final String SECTOR_BEAN = "java:module/SectorEJB";
    private static final String ESTRATEGIA_BEAN = "java:module/EstrategiaEJB";
    private static final String LINEA_ACCION_BEAN = "java:module/LineaAccionEJB";

    @Override
    public Object getAsObject(FacesContext fc, UIComponent component,
            String value) {

        if (value != null && !ValidacionUtil.esCadenaVacia(value)) {

            switch (component.getId()) {
                case "txtAreaAdscripcionNuevo":
                    return getIdAreaAdscripcion(value);
                case "txtDependenciaNuevo":
                    return getIdDependencia(value);
                case "txtEstrategiaNuevo":
                    return getIdEstrategia(value);
                case "txtLineaAccionNuevo":
                    return getIdLineaAccion(value);
                case "txtSectorNuevo":
                    return getIdSector(value);
                case "txtUnidadResponsableNuevo":
                    return getIdUnidadResponsable(value);
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent component,
            Object object) {
        if (object != null && object instanceof Integer) {
            Integer id = (Integer) object;
            switch (component.getId()) {
                case "txtAreaAdscripcionNuevo":
                    return getDescripcionAreaAdscripcion(id);
                case "txtDependenciaNuevo":
                    return getDescripcionDependencia(id);
                case "txtEstrategiaNuevo":
                    return getDescripcionEstrategia(id);
                case "txtLineaAccionNuevo":
                    return getDescripcionLineaAccion(id);
                case "txtSectorNuevo":
                    return getDescripcionSector(id);
                case "txtUnidadResponsableNuevo":
                    return getDescripcionUnidadResponsable(id);
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    private Integer getIdAreaAdscripcion(String value) {
        AreaAdscripcion areaAdscripcionEJB = getAreaAdscripcionEJB();

        if (areaAdscripcionEJB != null) {
            return areaAdscripcionEJB
                    .consultarIdAreaAdscripcionPorDescripcion(value);
        } else {
            LOGGER.warn("El EJB no está instanciado.");
            return null;
        }
    }

    private String getDescripcionAreaAdscripcion(Integer id) {
        AreaAdscripcion areaAdscripcionEJB = getAreaAdscripcionEJB();
        AreaAdscripcionDTO areaAdscripcionDTO = areaAdscripcionEJB
                .obtenerPorId(id);

        return areaAdscripcionDTO == null ? ""
                : areaAdscripcionDTO.getAreaAdscripcion();
    }

    private Integer getIdDependencia(String value) {
        Dependencia dependenciaEJB = getDependenciaEJB();

        if (dependenciaEJB != null) {
            return dependenciaEJB.consultarIdDependenciaPorDescripcion(value);
        } else {
            LOGGER.warn("El EJB no está instanciado.");
            return null;
        }
    }

    private String getDescripcionDependencia(int idDependecia) {
        DependenciaDTO dependencia = new DependenciaDTO();
        dependencia.setIdDependencia(idDependecia);
        Dependencia dependenciaEJB = getDependenciaEJB();
        dependencia = dependenciaEJB.obtenerDependencia(dependencia);

        return dependencia == null ? "" : dependencia.getDescripcion();
    }

    private Integer getIdEstrategia(String value) {
        Estrategia estrategiaEJB = getEstrategiaEJB();

        if (estrategiaEJB != null) {
            return estrategiaEJB.consultarIdEstrategiaPorDescripcion(value);
        } else {
            LOGGER.warn("El EJB no está instanciado.");
            return null;
        }
    }

    private String getDescripcionEstrategia(Integer id) {
        Estrategia estrategiaEJB = getEstrategiaEJB();
        EstrategiaDTO estrategiaDTO = estrategiaEJB.obtenerPorId(id);

        return estrategiaDTO == null ? "" : estrategiaDTO.getEstrategia();
    }

    private Integer getIdLineaAccion(String value) {
        LineaAccion lineaAccionEJB = getLineaAccionEJB();

        if (lineaAccionEJB != null) {
            return lineaAccionEJB.consultarIdLineaAccionPorDescripcion(value);
        } else {
            LOGGER.warn("El EJB no está instanciado.");
            return null;
        }
    }

    private String getDescripcionLineaAccion(Integer id) {
        LineaAccion lineaAccionEJB = getLineaAccionEJB();
        LineaAccionDTO lineaAccionDTO = lineaAccionEJB.obtenerPorId(id);

        return lineaAccionDTO == null ? "" : lineaAccionDTO.getDescripcion();
    }

    private Integer getIdSector(String value) {
        Sector sectorEJB = getSectorEJB();

        if (sectorEJB != null) {
            return sectorEJB.consultarIdSectorPorDescripcion(value);
        } else {
            LOGGER.warn("El EJB no está instanciado.");
            return null;
        }
    }

    private String getDescripcionSector(Integer id) {
        if (id != null && id > 0) {
            Sector sectorEJB = getSectorEJB();
            SectorDTO sectorDTO = sectorEJB.obtenerPorId(id);

            return sectorDTO == null ? "" : sectorDTO.getDescripcion();
        } else {
            return null;
        }
    }

    private Integer getIdUnidadResponsable(String value) {
        UnidadResponsable unidadResponsableEJB = getUnidadResponsableEJB();

        if (unidadResponsableEJB != null) {
            return unidadResponsableEJB
                    .consultarIdUnidadResponsablePorDescripcion(value);
        } else {
            LOGGER.warn("El EJB no está instanciado.");
            return null;
        }
    }

    private String getDescripcionUnidadResponsable(Integer id) {
        UnidadResponsable unidadResponsableEJB = getUnidadResponsableEJB();
        UnidadResponsableDTO unidadResponsableDTO = new UnidadResponsableDTO();
        unidadResponsableDTO.setIdUnidadResponsable(id);
        unidadResponsableDTO = unidadResponsableEJB
                .obtenerUnidadResponsable(unidadResponsableDTO);

        return unidadResponsableDTO == null ? ""
                : unidadResponsableDTO.getDescripcion();
    }

    private AreaAdscripcion getAreaAdscripcionEJB() {
        try {
            Context initContext = new InitialContext();
            AreaAdscripcion areaAdscripcionEJB = (AreaAdscripcion) initContext
                    .lookup(AREA_ADSCRIPCION_BEAN);

            return areaAdscripcionEJB;
        } catch (NamingException ex) {
            LOGGER.log(Logger.Level.ERROR, null, ex);
            return null;
        }
    }

    private Dependencia getDependenciaEJB() {
        try {
            Context initContext = new InitialContext();
            Dependencia dependenciaEJB = (Dependencia) initContext
                    .lookup(DEPENDENCIA_BEAN);

            return dependenciaEJB;
        } catch (NamingException ex) {
            LOGGER.log(Logger.Level.ERROR, null, ex);
            return null;
        }
    }

    private Estrategia getEstrategiaEJB() {
        try {
            Context initContext = new InitialContext();
            Estrategia estrategiaEJB = (Estrategia) initContext
                    .lookup(ESTRATEGIA_BEAN);

            return estrategiaEJB;
        } catch (NamingException ex) {
            LOGGER.log(Logger.Level.ERROR, null, ex);
            return null;
        }
    }

    private LineaAccion getLineaAccionEJB() {
        try {
            Context initContext = new InitialContext();
            LineaAccion lineaAccionEJB = (LineaAccion) initContext
                    .lookup(LINEA_ACCION_BEAN);

            return lineaAccionEJB;
        } catch (NamingException ex) {
            LOGGER.log(Logger.Level.ERROR, null, ex);
            return null;
        }
    }

    private Sector getSectorEJB() {
        try {
            Context initContext = new InitialContext();
            Sector sectorEJB = (Sector) initContext.lookup(SECTOR_BEAN);

            return sectorEJB;
        } catch (NamingException ex) {
            LOGGER.log(Logger.Level.ERROR, null, ex);
            return null;
        }
    }

    private UnidadResponsable getUnidadResponsableEJB() {
        try {
            Context initContext = new InitialContext();
            UnidadResponsable unidadResponsableEJB = (UnidadResponsable) initContext
                    .lookup(UNIDAD_RESPONSABLE_BEAN);

            return unidadResponsableEJB;
        } catch (NamingException ex) {
            LOGGER.log(Logger.Level.ERROR, null, ex);
            return null;
        }
    }

}
