/*
 *
 */

package mx.gob.saludtlax.rh.empleados.movimientos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

/**
 * @author Leila Schiaffini Ehuan
 * @since 14/09/2016 16:22:46
 *
 */
@ManagedBean(name = "consultaMovimiento")
@ViewScoped
public class ConsultaMovimientoController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6926294565121698558L;
    @Inject
    private Catalogo catalogo;
    @Inject
    private MovimientosEmpleados movimientosEmpleados;

    private ConsultaMovimientoView view;

    SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");

    @PostConstruct
    public void inicio() {
        view = new ConsultaMovimientoView();
        view.setListaFiltros(SelectItemsUtil.listaFiltrosConsultaMovimientos());
        view.setListaMovimientos(SelectItemsUtil.listaCatalogos(catalogo.consultaMovimientosAutorizadosPorPadre(0)));
        view.setMostrarBusqueda(true);

    }

    public void consultarMovimientosHijos() {
        List<CatalogoDTO> movimientosHijos = catalogo.consultaMovimientosAutorizadosPorPadre(view.getIdPadre());
        view.setListaMovimientosHijos(SelectItemsUtil.listaCatalogos(movimientosHijos));
    }

    public void seleccionarPadre() {
        if (view.getIdFiltro() == EnumTipoConsultaMovimiento.MOVIMIENTOS) {
            consultarMovimientos();
        } else if (view.getIdFiltro() == EnumTipoConsultaMovimiento.MOVIMIENTO_ESPECIFICO) {

            consultarMovimientosHijos();
        }
    }

    public void asignarFecha() {
        System.out.println("datos " + formateador.format(view.getFechaInicial()));
    }

    public void consultarMovimientos() {

        view.getMovimientos().clear();

        FiltroConsultaDTO filtroConsultaDTO = new FiltroConsultaDTO();
        if (view.getIdFiltro() == EnumTipoConsultaMovimiento.MOVIMIENTOS) {
            filtroConsultaDTO.setIdentificador(view.getIdPadre());
        } else if (view.getIdFiltro() == EnumTipoConsultaMovimiento.MOVIMIENTO_ESPECIFICO) {
            filtroConsultaDTO.setIdentificador(view.getIdHijo());
        } else if (view.getIdFiltro() == EnumTipoConsultaMovimiento.MOVIMIENTO_POR_FECHAS) {
            filtroConsultaDTO.setFechaInicial(view.getFechaInicial());
            filtroConsultaDTO.setFechaFinal(view.getFechaFinal());
        } else if (view.getIdFiltro() == EnumTipoConsultaMovimiento.MOVIMIENTO_POR_RFC_NOMBRE) {
            filtroConsultaDTO.setCriterio(view.getParametro());
        }

        filtroConsultaDTO.setTipoBusqueda(view.getIdFiltro());

        view.setMovimientos(movimientosEmpleados.consultarMovimientos(filtroConsultaDTO));
        if (view.getMovimientos().isEmpty()) {
            JSFUtils.warningMessage("", "No se encontraron movimientos.");
        }

    }

    public void seleccionarFiltro() {
        view.setMostrarHijos(false);
        view.setMostrarCriterio(false);
        view.setMostrarPadres(true);
        view.setFechas(Boolean.FALSE);
        view.setRfc(Boolean.FALSE);
        view.setIdPadre(0);

        if (view.getIdFiltro() == EnumTipoConsultaMovimiento.MOVIMIENTO_ESPECIFICO) {
            view.setMostrarHijos(true);
            view.setMostrarPadres(true);
        } else if (view.getIdFiltro() == EnumTipoConsultaMovimiento.MOVIMIENTOS) {
            view.setMostrarPadres(true);
        } else if (view.getIdFiltro() == EnumTipoConsultaMovimiento.MOVIMIENTO_POR_FECHAS) {
            view.setMostrarPadres(false);
            view.setFechas(Boolean.TRUE);
            view.setRfc(Boolean.FALSE);
        } else if (view.getIdFiltro() == EnumTipoConsultaMovimiento.MOVIMIENTO_POR_RFC_NOMBRE) {
            view.setMostrarPadres(false);
            view.setFechas(Boolean.FALSE);
            view.setRfc(Boolean.TRUE);
        }

    }

    public void obtenerDetalleMovimiento(Integer idMovimiento) {
        DetalleMovimientoDTO detalle = movimientosEmpleados.obtenerDetalleMovimiento(idMovimiento);
        if (detalle.getIdMovimiento() != null) {
            view.setMostrarBusqueda(false);
            view.setMostrarDetalle(true);
            view.setDetalleMovimiento(detalle);
        }
    }

    public ConsultaMovimientoView getView() {
        return view;
    }

    public void setView(ConsultaMovimientoView view) {
        this.view = view;
    }

}
