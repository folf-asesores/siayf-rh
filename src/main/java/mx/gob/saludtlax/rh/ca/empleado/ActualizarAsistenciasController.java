
package mx.gob.saludtlax.rh.ca.empleado;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

@ManagedBean
@SessionScoped

public class ActualizarAsistenciasController {

    private Date fechaInicial;
    private Date fechaFin;
    private Date fechaIni;
    private Integer resultado;

    private Integer tipoContratacion;
    private Integer adscripcion;

    private List<SelectItem> catalogoAdscripciones = new ArrayList<>();

    @Inject
    private ActualizarasistenciaEJB ejb;

    @Inject
    private Catalogo catalogo;

    @PostConstruct
    public void inicio() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, -30);  // numero de días a añadir, o restar en caso de días<0
        fechaIni = calendar.getTime();

        setCatalogoAdscripciones(SelectItemsUtil
                .listaCatalogos(catalogo.consultarAdscripciones()));

    }

    public List<SelectItem> getTipoContrataciones() {
        List<SelectItem> items = new ArrayList<>();

        for (Map.Entry<Integer, String> tipoContrataciones : EnumTipoContratacion
                .obtenerTipoContrataciones().entrySet()) {
            SelectItem item = new SelectItem(tipoContrataciones.getKey(),
                    tipoContrataciones.getValue());
            items.add(item);
        }

        return items;
    }

    public void actualizarAsistencias() {
        resultado = ejb.actualizarAsistencias(fechaInicial, fechaFin,
                tipoContratacion, adscripcion);
        if (resultado == null) {
            resultado = 0;
        }

        JSFUtils.infoMessage("Actualizacion concluida",
                "registros afectados " + resultado);

    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public List<SelectItem> getCatalogoAdscripciones() {
        return catalogoAdscripciones;
    }

    public void setCatalogoAdscripciones(
            List<SelectItem> catalogoAdscripciones) {
        this.catalogoAdscripciones = catalogoAdscripciones;
    }

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }

    public Integer getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(Integer tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public Integer getAdscripcion() {
        return adscripcion;
    }

    public void setAdscripcion(Integer adscripcion) {
        this.adscripcion = adscripcion;
    }

}
