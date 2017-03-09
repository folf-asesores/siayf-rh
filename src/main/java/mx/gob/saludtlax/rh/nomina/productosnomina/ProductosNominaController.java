package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.ByteArrayInputStream;
/**
 * @author José Pablo
 */
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.seguridad.autenticacion.AutenticacionUtil;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.siif.layout.SIIFLayout;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.TipoArchivo;

@Named(value = "productosNomina")
@SessionScoped
public class ProductosNominaController implements Serializable {

    private static final long serialVersionUID = 350883556927088002L;
    private static final Logger LOGGER = Logger.getLogger(ProductosNominaController.class.getName());

    @Inject private ProcesoEJB procesoEjb;
    @Inject private ProductosNominaEJB ejb;
    @Inject private SIIFLayout generarLayout;
    private ProductosNominaView view;
    private ProductoNominaValidator validator;
    private StreamedContent reporte;
	
    @PostConstruct
    public void init() {
        validator = new ProductoNominaValidator();
        view = new ProductosNominaView();
        view.setSubfuenteFinanciamientoList(ejb.obtenerSubfuenteFinanciamientoNominaList());
        view.setFuenteFinanciamientoList(ejb.obtenerFuentesFinanciamientoNominaList());
        view.setTipoNominaList(ejb.obtenerTipoNominaList());
        view.setEstatusProductoNominaLista(ejb.obtenerEstatusProductoNominaList());
        view.setTipoContratacionList(ejb.obtenerTipoContratacionList());
        reporte = new DefaultStreamedContent();
        irPrincipal();
    }

    public String irPrincipal() {
        view.showPanelPrincipal();
        view.setFiltro(new ProductoNominaFiltroDTO());
        return "/contenido/nomina/productos/productoNomina.xhtml?faces-redirect=true";
    }

    public String abrirNomina() {
        if (view.getProductoNomina() == null) {
            JSFUtils.errorMessage("", "No se detecta proceso de nómina para abrir.");
            return null;
        } else {
            ejb.abrirProductoNomina(view.getProductoNomina());
            view.setNominaEmpleadoList(ejb.obtenerNominaEmpleadoList(view.getProductoNomina()));
            view.showPanelPrincipalDetalle();
            view.setMostrarNominaErronea(Boolean.FALSE);
//            if (view.getProductoNomina().getIdTipoContratacion() == EnumTipoContratacion.SUPLENCIA) {
//                procesoEjb.detenerProceso(2);
//            }
            return "/contenido/nomina/productos/productoNominaDetalle.xhtml?faces-redirect=true";
        }
    }

    public String abrirNominaPorRfc() {
        if (view.getProductoNomina() == null) {
            JSFUtils.errorMessage("", "No se detecta proceso de nómina para abrir.");
            return null;
        } else {
            view.showPanelPrincipal();
            return "/contenido/nomina/productos/abrirProductoNominaRfc.xhtml?faces-redirect=true&idProductoNomina=" + view.getProductoNomina().getIdProductoNomina();
        }
    }

    public String irNuevo() {
        view.setOperacion(Boolean.TRUE);
        ProductoNominaDTO productoNomina = new ProductoNominaDTO();
        productoNomina.setIdEstatusProductoNomina(1);
        view.setProductoNomina(productoNomina);
        view.showPanelForm();
        view.setHabilitarEstatus(Boolean.FALSE);
        return null;
    }

    public String irGestionar() {
        view.setOperacion(Boolean.FALSE);
        view.setProductoNomina(ejb.obtenerProductoNomina(view.getProductoNominaSelect().getIdProductoNomina()));
        view.setTipoPeriodoLista(ejb.obtenerTipoPeriodoLista(view.getProductoNomina().getEjercicioFiscal()));
        String url = null;
        if (view.getProductoNomina().getIdEstatusProductoNomina() == 1) {
            view.showPanelForm();
            url = "/contenido/nomina/productos/productoNomina.xhtml?faces-redirect=true";
        }
        if (view.getProductoNomina().getIdEstatusProductoNomina() > 1
                && view.getProductoNomina().getIdEstatusProductoNomina() < 5) {
            view.setNominaEmpleadoList(ejb.obtenerNominaEmpleadoList(view.getProductoNomina()));
            view.showPanelPrincipalDetalle();
            if (view.getProductoNomina().getIdEstatusProductoNomina() > 2) {
                view.setNominasErroneas(ejb.consultarNominasErroneas(view.getProductoNomina().getIdProductoNomina()));
                view.setMostrarNominaErronea(!view.getNominasErroneas().isEmpty());
            }
            view.showPanelPrincipal();
            url = "/contenido/nomina/productos/productoNominaDetalle.xhtml?faces-redirect=true";
        }
        if (view.getProductoNomina().getIdEstatusProductoNomina() >= 5) {
            view.showPanelForm();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            httpSession.setAttribute("idProductoNomina", view.getProductoNomina().getIdProductoNomina());
            view.showPanelPrincipal();
            url = "/contenido/nomina/productos/ejecutarProductoNomina.xhtml?faces-redirect=true";
        }
        return url;
    }

    public String irPrincipalDetalleNomina() {
        view.showPanelPrincipalDetalle();
        view.setNominaEmpleadoList(ejb.obtenerNominaEmpleadoList(view.getProductoNomina()));
        return "/contenido/nomina/productos/productoNominaDetalle.xhtml?faces-redirect=true";
    }

    public String irDetalleNomina(NominaEmpleadoDTO dto) {
        view.setNominaEmpleadoSelect(ejb.obtenerNominaEmpleadoDetalle(dto));
        view.setOperacion(Boolean.TRUE);
        view.showPanelDetalle();
        return null;
    }

    public String regresarDetalleNomina() {
        view.setNominaEmpleadoList(ejb.obtenerNominaEmpleadoList(view.getProductoNomina()));
        view.showPanelDetalle();
        return null;
    }

    public String cambiarTipoPeriodoLista() {
        view.setTipoPeriodoLista(ejb.obtenerTipoPeriodoLista(view.getProductoNomina().getEjercicioFiscal()));
        if (view.getTipoPeriodoLista().size() == 1) {
            Integer idTipoPeriodo = view.getTipoPeriodoLista().get(0).getIdTipoPeriodo();
            view.getProductoNomina().setIdTipoPeriodo(idTipoPeriodo);
        }
        return null;
    }

    public String cambiarFechasPeriodos() {
        view.getProductoNomina().setIdEjercicioFiscal(ejb.obtenerIdEjercicioFiscal(view.getProductoNomina()));
        view.setProductoNomina(ejb.cambiarFechasPerido(view.getProductoNomina()));
        return null;
    }

    public String cambiarFuenteFinanciamiento() {
        view.setCambiarFuenteFinanciamiento(view.getProductoNomina().getCambiarFuenteFinanciamiento());
        return null;
    }

    public String actualizarFuenteFinanciamiento() {
        view.setProductoNomina(ejb.obtenerFuentePorSubfuente(view.getProductoNomina()));
        return null;
    }

    public String actualizarFuenteFinanciamientoFiltro() {
        view.setProductoNomina(ejb.obtenerFuentePorSubfuente(view.getProductoNomina()));
        return null;
    }

    public String irCalcularNomina() {
        view.panelCalculoNomina();
        return null;
    }

    public String calcularNomina() {
        ejb.actualizarProductoNomina(view.getProductoNomina());
        view.setProductoNomina(ejb.calcularProductoNomina(view.getProductoNomina()));
        view.setNominaEmpleadoList(ejb.obtenerNominaEmpleadoList(view.getProductoNomina()));
        view.setNominasErroneas(ejb.consultarNominasErroneas(view.getProductoNomina().getIdProductoNomina()));
        view.setMostrarNominaErronea(!view.getNominasErroneas().isEmpty());
//        procesoEjb.detenerProceso(1);
        JSFUtils.infoMessage("", "El cálculo ha sido procesado con éxito.");
        view.showPanelPrincipalDetalle();
        return null;
    }

    public String validarNomina() {
        ejb.validarProductoNomina(view.getProductoNomina());
        view.setProductoNomina(ejb.obtenerProductoNomina(view.getProductoNomina().getIdProductoNomina()));
        return null;
    }

    public void guardarProductoNomina() {
        if (view.getOperacion()) {
            view.setProductoNomina(ejb.crearProductoNomina(view.getProductoNomina()));
            view.setOperacion(Boolean.FALSE);
            JSFUtils.infoMessage("El registro producto nómina se ha guardado exitosamente", "");
        } else {
            ejb.actualizarProductoNomina(view.getProductoNomina());
        }
    }

    public void descargarNominaFederales() {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        try {
            AdministradorReportes adm = new AdministradorReportes();
            String[] parametros = new String[] {
                "ID_USUARIO", usuario.getIdUsuario().toString(),
                "REPORTE_NOMBRE", "nomina_federales",
                "TIPO_REPORTE", "txt",
                "ID_PRODUCTO_NOMINA", view.getProductoNomina().getIdProductoNomina().toString()
            };
            String referencia = adm.obtenerReferencia(parametros);
            LOGGER.debugv("Referencia: {0}", referencia);
            byte[] bytesReporte = adm.obtenerReporte(referencia);
            JSFUtils.descargarArchivo(bytesReporte, "nomina-eventuales", "text/plain");
        } catch (IOException ex) {
            LOGGER.errorv("Error de lectuta/escritura: {0}", ex);
        }
    }

    public void descargarNominaEventuales() {
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        AdministradorReportes adm = new AdministradorReportes();
	    String[] parametros = new String[] {
	        "ID_USUARIO", usuario.getIdUsuario().toString(),
	        "REPORTE_NOMBRE", "nomina_eventuales",
	        "TIPO_REPORTE", "txt",
	        "ID_PRODUCTO_NOMINA", view.getProductoNomina().getIdProductoNomina().toString()
	    };
	    String referencia = adm.obtenerReferencia(parametros);
	    LOGGER.debugv("Referencia: {0}", referencia);
	    byte[] bytesReporte = adm.obtenerReporte(referencia);
	    
	    ByteArrayInputStream bis = new ByteArrayInputStream(bytesReporte);
	    setReporte(new DefaultStreamedContent(bis, "text/plain", "dispersion-nomina_" + view.getProductoNomina().getNombreProducto() + ".txt"));
	    
    }

    public void descargarNominaSuplencia(Integer idProductoNomina) {
        System.out.println("idProductoNomina:: " + idProductoNomina);

        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        System.out.println("usuario:: " + usuario);
        try {
            AdministradorReportes adm = new AdministradorReportes();
            String[] parametros = new String[] {
                "ID_USUARIO", usuario.getIdUsuario().toString(),
                "REPORTE_NOMBRE", "nomina_suplencias",
                "TIPO_REPORTE", "txt",
                "ID_PRODUCTO_NOMINA", idProductoNomina.toString()
            };
            String referencia = adm.obtenerReferencia(parametros);
            LOGGER.debugv("Referencia: {0}", referencia);
            byte[] bytesReporte = adm.obtenerReporte(referencia);
            JSFUtils.descargarArchivo(bytesReporte, "nomina-suplencias", "text/plain");
        } catch (IOException ex) {
            LOGGER.errorv("Error de lectuta/escritura: {0}", ex);
        }
    }

    public void descargarDispersion(Integer idProductoNomina) {
        LOGGER.debugv("idProductoNomina :: {0}", idProductoNomina);
        UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
        LOGGER.debugv("usuario :: {0}", usuario);
        AdministradorReportes adm = new AdministradorReportes();
        String[] parametros = new String[] {
            "ID_USUARIO", usuario.getIdUsuario().toString(),
            "REPORTE_NOMBRE", "dispersion_nomina",
            "TIPO_REPORTE", "xlsx",
            "ID_PRODUCTO_NOMINA", idProductoNomina.toString()
        };
        String referencia = adm.obtenerReferencia(parametros);
        LOGGER.debugv("Referencia: {0}", referencia);
        byte[] reporteBytes = adm.obtenerReporte(referencia);
        ByteArrayInputStream bis = new ByteArrayInputStream(reporteBytes);
        setReporte(new DefaultStreamedContent(bis, TipoArchivo.XLSX.getMIMEType(), "dispersion-nomina.xlsx"));
    }

	public void descargarProductoNomina() {
		try {
			UsuarioDTO usuario = AutenticacionUtil.recuperarUsuarioSesion();
			String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
					"producto_nomina", "TIPO_REPORTE", "xlsx", "ID_PRODUCTO_NOMINA",
					String.valueOf(view.getProductoNomina().getIdProductoNomina()) };

			AdministradorReportes admintradorReportes = new AdministradorReportes();
			String referencia = admintradorReportes.obtenerReferencia(parametros);
			byte[] bytes = admintradorReportes.obtenerReporte(referencia);
			if (bytes != null) {
		        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
				setReporte(new DefaultStreamedContent(bis, TipoArchivo.getMIMEType("xlsx"),
						"Productos_Nomina_" + view.getProductoNomina().getNombreProducto() + ".xlsx"));
				JSFUtils.infoMessage("Descargar Productos Nomina: " + view.getProductoNomina().getNombreProducto(),
						"Se descargo correctamente.");
			}
		} catch (ReglaNegocioException e) {
			e.printStackTrace();
			JSFUtils.errorMessage("Error: ", e.getMessage());
		}
	}

    public void estaEnProcesoCalculo(int proceso) {
        view.setProceso(procesoEjb.obtenerProceso(proceso));

    }

    public void filtrarProductoNomina() {
        view.setFiltroProductoNominaList(ejb.filtrarProductoNomina(view.getFiltro()));
    }

    public String irDividirNomina() {
        view.setOperacion(Boolean.TRUE);
        view.showPanelForm();
        view.setHabilitarEstatus(Boolean.FALSE);

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("idProductoNomina", view.getProductoNomina().getIdProductoNomina());

        return "/contenido/nomina/productos/dividirProductoNomina.xhtml?faces-redirect=true";
    }

public String irGestionarConcepto(ConceptosNominaEmpleadosDTO conceptoNominaEmpleado) {
        view.setConceptosNominaSelect(conceptoNominaEmpleado);
        List<FaltaContadaDTO> faltasContadas = (ejb.obtenerFaltasContadas(conceptoNominaEmpleado));
        List<FaltaContadaDTO> faltasNoContadas = (ejb.obtenerFaltasNoContadas(faltasContadas, conceptoNominaEmpleado));
        view.setFaltasGestionar(new DualListModel<FaltaContadaDTO>(faltasContadas, faltasNoContadas));
        view.showGestionFaltas();
        return null;
    }

    public String recalcularNominaEmpleado() {
        ejb.recalcularNominaEmpleado(view.getProductoNomina(), view.getNominaEmpleadoSelect());
        view.setNominaEmpleadoSelect(ejb.obtenerNominaEmpleadoDetalle(view.getNominaEmpleadoSelect()));
        view.showPanelDetalle();
        return null;
    }

    public String recalcularConcepto() {
        ejb.actualizarConcepto(view.getConceptosNominaSelect(), view.getFaltasGestionar().getSource());
        ejb.recalcularNominaEmpleado(view.getProductoNomina(), view.getNominaEmpleadoSelect());
        view.setNominaEmpleadoSelect(ejb.obtenerNominaEmpleadoDetalle(view.getNominaEmpleadoSelect()));
        view.showPanelDetalle();
        return null;
    }

    public String irPensiones() {
        view.setPensionesNominaList(ejb.obtenerPensionesNominaList(view.getProductoNomina()));
        view.showPanelPension();
        return null;
    }

    public String eliminarProductoNomina() {
        ejb.eliminarProductoNomina(view.getProductoNomina());
        view.setFiltroProductoNominaList(new ArrayList<ProductoNominaListaDTO>());
        return irPrincipal();
    }

    public ProductosNominaView getView() {
        return view;
    }

    public ProductoNominaValidator getValidator() {
        return validator;
    }
	
	public void generarDatTra() {
		if(generarLayout.verificaProductoNomina(view.getProductoNomina().getIdProductoNomina())<=0){
			generarLayout.crearDatTraProdNom(view.getProductoNomina().getIdProductoNomina());			
		}
		try {
			byte[] layout = generarLayout.getDatTraProdNomRH(view.getProductoNomina().getIdProductoNomina());
			JSFUtils.descargarArchivo(layout, "datytra",TipoArchivo.ZIP.getMIMEType());
			} catch (IOException ex) {
				LOGGER.error(ex);
			}
		
	}
	    
    public void setReporte(StreamedContent reporte) {
        this.reporte = reporte;
    }

    public StreamedContent getReporte() {
        return reporte;
    }
    
    public void generarLayout() {				
		try {
			byte[] layout = generarLayout.getLayoutComoZipRH(view.getProductoNomina().getIdProductoNomina());
			JSFUtils.descargarArchivo(layout, "layout",TipoArchivo.ZIP.getMIMEType());
			} catch (IOException ex) {
				LOGGER.error(ex);
			}
	}

}