
package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

import mx.gob.saludtlax.rh.configuracion.puestogeneral.PuestoGeneral;
import mx.gob.saludtlax.rh.configuracion.puestogeneral.PuestoGeneralDTO;
import mx.gob.saludtlax.rh.configuracion.tabulador.Tabulador;
import mx.gob.saludtlax.rh.configuracion.tabulador.TabuladorDTO;
import mx.gob.saludtlax.rh.nomina.EvaluadorService;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean(name = "conceptosNominaFederales")
@ViewScoped
public class ConceptosNominaFederalesController implements Serializable {

    private static final long serialVersionUID = 1439823081136660382L;

    @Inject
    private ConceptoNominaFederalesEJB ejb;
    @Inject
    private ConceptosNominaFederalesView view;

    @Inject
    PuestoGeneral puestoGeneral;

    @Inject
    private Tabulador tabuladorEJB;

    @Inject
    private ConfiguracionConceptoPuestoEJB configuracionConceptoEjb;

    private List<PuestoGeneralDTO> puestosTarget = new ArrayList<>();

    private BigDecimal sueldoBase = BigDecimal.ZERO;
    private BigDecimal asignacionbruta = BigDecimal.ZERO;
    private BigDecimal ayudagastosactualiazacion = BigDecimal.ZERO;

    @Inject
    private EvaluadorService evaluadorService;

    @PostConstruct
    public void conceptosInit() {
        view.setEstatusConceptoNominaLista(ejb.listaEstatusConceptoNomina());
        view.setNivelEmpleadoLista(ejb.listaNivelEmpleado());
        view.setNombramientoLista(ejb.listaNombramiento());
        view.setTipoSelect(2);
        irPrincipal();

        cargarListaPuestos();

    }

    public void cargarListaPuestos() {
        view.setPuestos(puestoGeneral.consultarListaPuestoGeneral());
        view.setPuestosSeleccion(new DualListModel<>(view.getPuestos(), puestosTarget));
    }

    public Boolean esMandoMedio(ConfiguracionConceptoPuestoDTO configPuesto) {
        PuestoGeneralDTO puesto = puestoGeneral.puestoPorId(configPuesto.getIdPuestoGeneral());
        Boolean res = false;
        if (puesto.getIdTipoPuesto().compareTo(1) == 0) {
            res = true;
        }
        return res;
    }

    public void obtenerConfiguraciones() {
        List<ConfiguracionConceptoPuestoDTO> listaConf = new ArrayList<>();
        listaConf = configuracionConceptoEjb.obtenerListaPorConcepto(view.getConceptoNominaSelect().getIdConceptoNomina());
        List<PuestoGeneralDTO> listaNewPuestos = new ArrayList<>();
        listaNewPuestos = puestoGeneral.consultarListaPuestoGeneral();

        for (ConfiguracionConceptoPuestoDTO confDto : listaConf) {
            for (int i = 0; i < listaNewPuestos.size(); i++) {
                if (listaNewPuestos.get(i).getIdPuestoGeneral().compareTo(confDto.getIdPuestoGeneral()) == 0) {
                    listaNewPuestos.remove(listaNewPuestos.get(i));
                }
            }

        }
        view.setPuestosSeleccion(new DualListModel<>(listaNewPuestos, puestosTarget));
        view.setConfigPuesto(listaConf);
        view.setPuestos(listaNewPuestos);
    }

    /**
     * Metodo usado para el componente pick List el cual fue remplazado por una
     * tabla seleccionable
     */
    //	public void guardarConfiguracionConceptos() {
    //		List<ConfiguracionConceptoPuestoDTO> listNewConfigDto = new ArrayList<>();
    //		if (!view.getPuestosSeleccion().getTarget().isEmpty()) {
    //			for (PuestoGeneralDTO puesto : view.getPuestosSeleccion().getTarget()) {
    //				ConfiguracionConceptoPuestoDTO newConfigConcepto = new ConfiguracionConceptoPuestoDTO();
    //				newConfigConcepto.setClaveConcepto(view.getConceptoNominaSelect().getClave());
    //				newConfigConcepto.setCodigoPuesto(puesto.getCodigo());
    //				newConfigConcepto.setDecripcionConcepto(view.getConceptoNominaSelect().getDescripcion());
    //				newConfigConcepto.setDescripcionPuesto(puesto.getPuesto());
    //
    //				if (view.getResult() != null) {
    //
    //					BigDecimal newImport = new BigDecimal(view.getResult().trim());
    //					newConfigConcepto.setImporte_concepto(newImport);
    //				}
    //
    //				TabuladorDTO tabulador = new TabuladorDTO();
    //				System.out.println("puesto " + puesto.getIdPuestoGeneral());
    //				// validar el año del tabulador
    //				
    //				try {
    //					tabulador = tabuladorEJB.obtenerTabuladorPorPuesto(puesto.getIdPuestoGeneral(),
    //							FechaUtil.ejercicioActual());
    //					System.out.println("tabulador " + tabulador.getIdTabulador() + "--- "
    //							+ view.getConceptoNominaSelect().getClave());
    //					if (view.getConceptoNominaSelect().getClave().contentEquals("0700")) {
    //						newConfigConcepto.setId_tabulador(tabulador.getIdTabulador());
    //						newConfigConcepto.setEjercicioFiscalTabulador(tabulador.getEjercicioFiscal());
    //						newConfigConcepto.setImporte_concepto(
    //								tabulador.getSueldoBrutoMensual().setScale(2, RoundingMode.HALF_EVEN));
    //						newConfigConcepto.setFormula("EX0700");
    //					}
    //
    //					if (view.getConceptoNominaSelect().getClave().contentEquals("4200")) {
    //						newConfigConcepto.setId_tabulador(tabulador.getIdTabulador());
    //						newConfigConcepto.setEjercicioFiscalTabulador(tabulador.getEjercicioFiscal());
    //						newConfigConcepto.setImporte_concepto(tabulador.getAsignacionBrutaMensual().setScale(2, RoundingMode.HALF_EVEN));
    //						newConfigConcepto.setFormula("(EX4200)");
    //					}
    //
    //					if (view.getConceptoNominaSelect().getClave().contentEquals("55AG")) {
    //						newConfigConcepto.setId_tabulador(tabulador.getIdTabulador());
    //						newConfigConcepto.setEjercicioFiscalTabulador(tabulador.getEjercicioFiscal());
    //						newConfigConcepto.setImporte_concepto(
    //								tabulador.getAgaBrutaMensual().setScale(2, RoundingMode.HALF_EVEN));
    //						newConfigConcepto.setFormula("(EX55AG)");
    //					}
    //				} catch (NullPointerException ex) {
    //					JSFUtils.errorMessage("Error", "No se encontro el tabulador para el puesto " + puesto.getCodigo());
    //				}
    //				newConfigConcepto.setId_concepto_nomina(view.getConceptoNominaSelect().getIdConceptoNomina());
    //				newConfigConcepto.setId_puesto_general(puesto.getIdPuestoGeneral());
    //				newConfigConcepto.setTipoPuesto(puesto.getIdTipoPuesto());
    //
    //				if(view.getConceptoNominaSelect().getFormula()!=null){
    //					newConfigConcepto.setFormula(view.getConceptoNominaSelect().getFormula());
    //					String formulanueva = newConfigConcepto.getFormula().replace("EX0700", tabulador.getSueldoBrutoMensual()+"");
    //				formulanueva = formulanueva.replace("EX4200", tabulador.getAsignacionBrutaMensual()+"");
    //				formulanueva = formulanueva.replace("EX55AG", tabulador.getAgaBrutaMensual()+"");
    //				String resFormula = ejb.evaluarFormula(formulanueva);
    //				System.out.println("cambiar valor constante :. " +formulanueva +" "+ resFormula );
    //				BigDecimal valorFormula = new BigDecimal(resFormula).setScale(2, RoundingMode.HALF_UP);
    //				newConfigConcepto.setImporte_concepto(valorFormula);
    //				}
    //				listNewConfigDto.add(newConfigConcepto);
    //			}
    //
    //			for (ConfiguracionConceptoPuestoDTO dtoDel : configuracionConceptoEjb
    //					.obtenerListaPorConcepto(view.getConceptoNominaSelect().getIdConceptoNomina())) {
    //				configuracionConceptoEjb.borrar(dtoDel);
    //			}
    //
    //
    //			for (ConfiguracionConceptoPuestoDTO dto : listNewConfigDto) {
    //				configuracionConceptoEjb.crear(dto);
    //			}
    //			List<ConfiguracionConceptoPuestoDTO> listaConfignew = new ArrayList<>();
    //			listaConfignew=configuracionConceptoEjb.obtenerListaPorConcepto(view.getConceptoNominaSelect().getIdConceptoNomina());
    //			System.out.println("lista"+listaConfignew.size());
    //			view.getConfigPuesto().addAll(listaConfignew);
    //
    //			view.getPuestosSeleccion().getTarget().clear();
    //		}
    //
    //	}
    public void guardarConfiguracionConceptosNew() {
        List<ConfiguracionConceptoPuestoDTO> listNewConfigDto = new ArrayList<>();
        if (!view.getPuestosSeleccionados().isEmpty()) {
            for (PuestoGeneralDTO puesto : view.getPuestosSeleccionados()) {
                ConfiguracionConceptoPuestoDTO newConfigConcepto = new ConfiguracionConceptoPuestoDTO();
                newConfigConcepto.setClaveConcepto(view.getConceptoNominaSelect().getClave());
                newConfigConcepto.setCodigoPuesto(puesto.getCodigo());
                newConfigConcepto.setDecripcionConcepto(view.getConceptoNominaSelect().getDescripcion());
                newConfigConcepto.setDescripcionPuesto(puesto.getPuesto());
                if (view.getResult() != null) {

                    BigDecimal newImport = new BigDecimal(view.getResult().trim());
                    newConfigConcepto.setImporteConcepto(newImport);
                }

                TabuladorDTO tabulador = new TabuladorDTO();
                System.out.println("puesto " + puesto.getIdPuestoGeneral());
                // validar el año del tabulador
                
                try {
                    tabulador = tabuladorEJB.obtenerTabuladorPorPuesto(puesto.getIdPuestoGeneral(), FechaUtil.ejercicioActual());
                    System.out.println("tabulador " + tabulador.getIdTabulador() + "--- " + view.getConceptoNominaSelect().getClave());

                } catch (NullPointerException ex) {
                    JSFUtils.errorMessage("Error", "No se encontro el tabulador para el puesto " + puesto.getCodigo());
                }
                newConfigConcepto.setIdConceptoNomina(view.getConceptoNominaSelect().getIdConceptoNomina());
                newConfigConcepto.setIdPuestoGeneral(puesto.getIdPuestoGeneral());
                newConfigConcepto.setTipoPuesto(puesto.getIdTipoPuesto());

                if (view.getConceptoNominaSelect().getFormula() != null) {

                    newConfigConcepto.setFormula(view.getConceptoNominaSelect().getFormula());
                    String formulanueva = newConfigConcepto.getFormula().replace("EX0700", tabulador.getSueldoBrutoMensual() + "");
                    formulanueva = formulanueva.replace("C30", "0");
                    formulanueva = formulanueva.replace("CA1", "0");
                    formulanueva = formulanueva.replace("EX4200", tabulador.getAsignacionBrutaMensual() + "");
                    formulanueva = formulanueva.replace("EX55AG", tabulador.getAgaBrutaMensual() + "");
                    String resFormula = ejb.evaluarFormula(formulanueva);

                    BigDecimal valorFormula = new BigDecimal(resFormula).setScale(2, RoundingMode.HALF_UP);

                    newConfigConcepto.setImporteConcepto(valorFormula);
                }
                listNewConfigDto.add(newConfigConcepto);
                view.getPuestos().remove(puesto);
            }

            //			List<ConfiguracionConceptoPuestoDTO> configuracionesOld = new ArrayList<>();
            //			configuracionesOld= configuracionConceptoEjb.obtenerListaPorConcepto(view.getConceptoNominaSelect().getIdConceptoNomina());
            //			System.out.println("ConfiguracionesOld "+ configuracionesOld.size() +" conceptoNomina");
            //			for (ConfiguracionConceptoPuestoDTO dtoDel : configuracionesOld) {
            //				configuracionConceptoEjb.borrar(dtoDel);
            //			}
            for (ConfiguracionConceptoPuestoDTO dto : listNewConfigDto) {
                configuracionConceptoEjb.crear(dto);
            }
            List<ConfiguracionConceptoPuestoDTO> listaConfignew = new ArrayList<>();
            listaConfignew = configuracionConceptoEjb.obtenerListaPorConcepto(view.getConceptoNominaSelect().getIdConceptoNomina());
            System.out.println("lista" + listaConfignew.size());
            view.getConfigPuesto().addAll(listaConfignew);

            view.getPuestosSeleccionados().clear();
        }

    }

    public void eliminarConfiguracionPuesto() {
        configuracionConceptoEjb.borrar(view.getConfiguracionConceptoPuestoDTOSeleccionado());
        obtenerConfiguraciones();
    }

    public void borrarTodasLasConfiguraciones() {
        if (!view.getConfigPuesto().isEmpty()) {
            for (ConfiguracionConceptoPuestoDTO conf : view.getConfigPuesto()) {
                configuracionConceptoEjb.borrar(conf);
            }
            obtenerConfiguraciones();
        }
    }

    public void onRowEdit(RowEditEvent event) {

        JSFUtils.infoMessage("Edicion:", "El registro se edito correctamente.");

    }

    public void onRowCancel(RowEditEvent event) {
        JSFUtils.infoMessage("Edicion:", "Edicion cancelada.");
    }

    public String updateCategoriaSAT() {
        view.setCategoriaSatLista(ejb.listaCategoriaSatPorTipo(view.getConceptoNominaSelect().getTipo()));
        return null;
    }

    public String habilitarIrGestionar() {
        view.setDisabledIrGestionar(Boolean.FALSE);
        return null;
    }

    public String deshabilitarIrGestionar() {
        view.setDisabledIrGestionar(Boolean.TRUE);
        return null;
    }

    public String irPrincipal() {
        if (view.getConceptoNominaLista() != null) {
            view.getConceptoNominaLista().clear();
        }
        view.setConceptoNominaLista(ejb.obtenerConceptoNominasLista(TipoConceptoNominaEnum.get(view.getTipoSelect())));
        view.panelPrincipal();
        return null;
    }

    public String irGestionarConceptoNomina() {
        updateCategoriaSAT();
        obtenerConfiguraciones();
        view.panelGestion();

        return null;
    }

    public String irNuevoConceptoNomina() {
        view.setConceptoNominaSelect(ejb.nuevoConceptoNomina());
        view.panelGestion();
        return null;
    }

    public String editarFormula() {
        view.setEditarFormula(view.getConceptoNominaSelect().getFormula());
        return null;
    }

    public String agregarFormula() {
        view.getConceptoNominaSelect().setFormula(view.getEditarFormula());
        return null;
    }

    public String guardarConceptoNomina() {
        ejb.crearConceptoNomina(view.getConceptoNominaSelect());
        view.panelGestion();
        return null;
    }

    public String actualizarConceptoNomina() {
        ejb.actualizarConceptoNomina(view.getConceptoNominaSelect());
        view.panelGestion();
        return null;
    }

    public String probarFormula() {
        view.setResult(ejb.evaluarFormula(view.getEditarFormula()));
        return null;
    }

    public void onRowSelectConceptoNomina(SelectEvent event) {
        view.setDisabledIrGestionar(Boolean.FALSE);
    }

    public void onRowUnselectConceptoNomina(UnselectEvent event) {
        view.setDisabledIrGestionar(Boolean.TRUE);
    }

    public ConceptosNominaFederalesView getView() {
        return view;
    }

    public List<PuestoGeneralDTO> getPuestosTarget() {
        return puestosTarget;
    }

    public void setPuestosTarget(List<PuestoGeneralDTO> puestosTarget) {
        this.puestosTarget = puestosTarget;
    }

    public BigDecimal getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(BigDecimal sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public BigDecimal getAsignacionbruta() {
        return asignacionbruta;
    }

    public void setAsignacionbruta(BigDecimal asignacionbruta) {
        this.asignacionbruta = asignacionbruta;
    }

    public BigDecimal getAyudagastosactualiazacion() {
        return ayudagastosactualiazacion;
    }

    public void setAyudagastosactualiazacion(BigDecimal ayudagastosactualiazacion) {
        this.ayudagastosactualiazacion = ayudagastosactualiazacion;
    }

}
