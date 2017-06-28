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

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

import mx.gob.saludtlax.rh.configuracion.puestogeneral.PuestoGeneral;
import mx.gob.saludtlax.rh.configuracion.puestogeneral.PuestoGeneralDTO;
import mx.gob.saludtlax.rh.configuracion.tabulador.Tabulador;
import mx.gob.saludtlax.rh.configuracion.tabulador.TabuladorDTO;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean(name = "conceptosNomina")
@ViewScoped
public class ConceptosNominaController implements Serializable {
	private static final long serialVersionUID = 1439823081136660382L;

	@Inject
	private ConceptoNominaEJB ejb;
	@Inject
	private ConceptosNominaView view;

	@Inject
	PuestoGeneral puestoGeneral;

	@Inject
	private Tabulador tabuladorEJB;

	@Inject
	private ConfiguracionConceptoPuestoEJB configuracionConceptoEjb;

	private List<PuestoGeneralDTO> puestosTarget = new ArrayList<PuestoGeneralDTO>();

	@PostConstruct
	public void conceptosInit() {
		view.setEstatusConceptoNominaLista(ejb.listaEstatusConceptoNomina());
		view.setNivelEmpleadoLista(ejb.listaNivelEmpleado());
		view.setNombramientoLista(ejb.listaNombramiento());
		view.setTipoSelect(2);
		irPrincipal();

		view.setPuestos(puestoGeneral.consultarListaPuestoGeneral());
		view.setPuestosSeleccion(new DualListModel<PuestoGeneralDTO>(view.getPuestos(), puestosTarget));

	}

	public void obtenerConfiguraciones() {
		List<ConfiguracionConceptoPuestoDTO> listaConf = new ArrayList<>();
		listaConf = configuracionConceptoEjb
				.obtenerListaPorConcepto(view.getConceptoNominaSelect().getIdConceptoNomina());
		view.setConfigPuesto(listaConf);
	}

	public void guardarConfiguracionConceptos() {
		List<ConfiguracionConceptoPuestoDTO> listNewConfigDto = new ArrayList<>();
		if (!view.getPuestosSeleccion().getTarget().isEmpty()) {
			for (PuestoGeneralDTO puesto : view.getPuestosSeleccion().getTarget()) {
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
				// **************************************************************************
				try {
					tabulador = tabuladorEJB.obtenerTabuladorPorPuesto(puesto.getIdPuestoGeneral(),
							FechaUtil.ejercicioActual());
					if (view.getConceptoNominaSelect().getClave().contentEquals("0700")) {
						newConfigConcepto.setIdTabulador(tabulador.getIdTabulador());
						newConfigConcepto.setEjercicioFiscalTabulador(tabulador.getEjercicioFiscal());
						newConfigConcepto
								.setImporteConcepto(tabulador.getSueldoBrutoMensual().divide(new BigDecimal(30),2, RoundingMode.HALF_EVEN).setScale(2, RoundingMode.HALF_EVEN));
					}

					if (view.getConceptoNominaSelect().getClave().contentEquals("4200")) {
						newConfigConcepto.setIdTabulador(tabulador.getIdTabulador());
						newConfigConcepto.setEjercicioFiscalTabulador(tabulador.getEjercicioFiscal());
						newConfigConcepto
								.setImporteConcepto(tabulador.getAsignacionBrutaMensual().divide(new BigDecimal(30),2, RoundingMode.HALF_EVEN).setScale(2, RoundingMode.HALF_EVEN));
					}

					if (view.getConceptoNominaSelect().getClave().contentEquals("55AG")) {
						newConfigConcepto.setIdTabulador(tabulador.getIdTabulador());
						newConfigConcepto.setEjercicioFiscalTabulador(tabulador.getEjercicioFiscal());
						newConfigConcepto
								.setImporteConcepto(tabulador.getAgaBrutaMensual().divide(new BigDecimal(30),2, RoundingMode.HALF_EVEN).setScale(2, RoundingMode.HALF_EVEN));
					}
				} catch (NullPointerException ex) {
                      JSFUtils.errorMessage("Error", "No se encontro el tabulador para el puesto "+puesto.getCodigo());
				}

				newConfigConcepto.setIdConceptoNomina(view.getConceptoNominaSelect().getIdConceptoNomina());
				newConfigConcepto.setIdPuestoGeneral(puesto.getIdPuestoGeneral());

				listNewConfigDto.add(newConfigConcepto);
			}

			for (ConfiguracionConceptoPuestoDTO dtoDel : configuracionConceptoEjb
					.obtenerListaPorConcepto(view.getConceptoNominaSelect().getIdConceptoNomina())) {
				configuracionConceptoEjb.borrar(dtoDel);
			}

			view.getConfigPuesto().addAll(listNewConfigDto);

			for (ConfiguracionConceptoPuestoDTO dto : view.getConfigPuesto()) {
				configuracionConceptoEjb.crear(dto);
			}

			view.getPuestosSeleccion().getTarget().clear();
		}

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
		view.setConceptoNominaLista(ejb.obtenerConceptoNominasLista(TipoConceptoNominaEnum.get(view.getTipoSelect())));
		view.panelPrincipal();
		return null;
	}

	public String irGestionarConceptoNomina() {
		updateCategoriaSAT();
		view.panelGestion();
		obtenerConfiguraciones();
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

	public ConceptosNominaView getView() {
		return view;
	}

	public List<PuestoGeneralDTO> getPuestosTarget() {
		return puestosTarget;
	}

	public void setPuestosTarget(List<PuestoGeneralDTO> puestosTarget) {
		this.puestosTarget = puestosTarget;
	}
}