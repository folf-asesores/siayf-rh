/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.empleados.datolaboral.DatoLaboralDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 08/11/2016 18:07:41
 */
@ManagedBean(name = "cierreSuplencia")
@ViewScoped
public class CierreSuplenciaController implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 127592054124549369L;
    @Inject
    private Catalogo catalogo;
    @Inject
    private Suplencia suplencia;

    private CierreSuplenciaView view;

    @PostConstruct
    public void inicio() {
        view = new CierreSuplenciaView();
        view.setMostrarBusqueda(true);

        for (int i = 1; i <= 24; i++) {
            view.getListaQuincenas().add(new SelectItem(i, "Quincena-" + i));
        }
        view.setListaEstatus(SelectItemsUtil.listaEstatusSuplenciasCierre());
        view.setListaDependencias(SelectItemsUtil.listaCatalogos(catalogo.listaDependencias()));
        view.setListaFuentesFinanciamiento(SelectItemsUtil.listaCatalogos(catalogo.listaFuentesFinanciamientos()));
        view.setListaTiposRecursos(SelectItemsUtil.listaCatalogos(catalogo.listaTiposRecursos()));
        view.setListaCuentaFinanciamiento(SelectItemsUtil.listaCatalogos(catalogo.consultarCuentasBancariasActuales()));
        view.setListaProyectos(SelectItemsUtil.listaCatalogos(catalogo.consultarProyectos()));
        view.setListaSubfuentesFinanciamiento(SelectItemsUtil.listaCatalogos(catalogo.listaSubfuentesFinanciamientos()));
    }

    public void consultarDetallesQuincena() {
        view.setMostrarColumnasCierre(false);
        view.getListaDetalles().clear();
        view.setListaDetalles(suplencia.consultarQuincenasSuplente(view.getNumeroQuincena(), FechaUtil.ejercicioActual(), view.getEstatus()));
        if (view.getEstatus().equals(EnumEstatusSuplencia.CERRADA)) {
            view.setMostrarColumnasCierre(true);
        }
    }

    public void mostrarDesglose(Integer idSuplenteAutorizado, String suplente, Integer idQuincena, String estatus) {
        view.getDesglose().clear();
        view.getCierre().setIdQuincena(idQuincena);
        ConsultaSuplenciaDTO dto = new ConsultaSuplenciaDTO();
        dto.setConDetalleMovimieto(true);
        dto.setIdSuplenteAutorizado(idSuplenteAutorizado);
        dto.setQuincena(view.getNumeroQuincena());
        dto.setEstatus(EnumEstatusSuplencia.APROBADO);
        dto.setTipoConsulta(2);
        view.setDesglose(suplencia.consultarDetallesSuplenteQuincena(dto));
        view.setMostrarDesglose(true);
        view.setMostrarBusqueda(false);
        view.setSuplente(suplente);
        view.setBloquearFinanciamientos(false);
        calcularTotal();

        if (estatus.equals(EnumEstatusSuplencia.CERRADA)) {
            view.setBloquearFinanciamientos(true);
            DatoLaboralDTO c = suplencia.obtenerFinanciamientos(idQuincena);
            view.getCierre().setIdProyecto(c.getIdProyecto());
            view.getCierre().setIdDependencia(c.getIdDependencia());
            obtenerUnidadesResponsables();
            view.getCierre().setIdUnidadResponsable(c.getIdUnidadResponsable());
            view.getCierre().setIdFuenteFinanciamiento(c.getIdFuenteFinanciamiento());
            view.getCierre().setIdSubfuenteFinanciamiento(c.getIdSubfuenteFinanciamiento());
            view.getCierre().setIdTipoRecurso(c.getIdTipoRecurso());

        }

    }

    public void regresarARevision() {
        try {
            suplencia.regresarARevision(view.getCierre().getIdQuincena());
            view.setMostrarDesglose(false);
            view.setMostrarBusqueda(true);
            JSFUtils.infoMessage("", "La suplencia ha sido enviada a revisión con éxito.");
        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessageEspecifico("errorCierre", "", exception.getMessage());
        }
    }

    public void calcularTotal() {
        BigDecimal total = BigDecimal.ZERO;
        int totalDias = 0;
        if (!view.getDesglose().isEmpty()) {
            for (DetalleSuplenciaDTO dto : view.getDesglose()) {
                System.out.println("dias" + dto.getDias());
                total = total.add(dto.getTotal());
                totalDias = totalDias + dto.getDias();
            }
            view.setTotal(total);
            view.setTotalDias(totalDias);
            view.getCierre().setSueldo(total);
            view.getCierre().setTotalDias(totalDias);
        }
    }

    public void obtenerUnidadesResponsables() {
        view.getListaUnidadesResponsables().clear();
        if (view.getCierre().getIdDependencia() != 0) {
            view.setListaUnidadesResponsables(
                    SelectItemsUtil.listaCatalogos(catalogo.listaUnidadesResponsablesPorDependencia(view.getCierre().getIdDependencia())));
        }

    }

    public void cierreQuincena() {
        try {
            view.getCierre().setSueldo(view.getTotal());
            suplencia.cerrarQuincenaSuplencia(view.getCierre());

            view.setMostrarDesglose(false);
            view.setMostrarBusqueda(true);
            consultarDetallesQuincena();
        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessageEspecifico("error", "", exception.getMessage());
        }

    }

    public void ocultarDesglose() {
        view.setMostrarDesglose(false);
        view.setMostrarBusqueda(true);
    }

    public CierreSuplenciaView getView() {
        return view;
    }

    public void setView(CierreSuplenciaView view) {
        this.view = view;
    }

}
