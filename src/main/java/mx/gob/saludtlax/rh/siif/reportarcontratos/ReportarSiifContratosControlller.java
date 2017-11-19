
package mx.gob.saludtlax.rh.siif.reportarcontratos;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.siif.ReporteSiifEJB;
import mx.gob.saludtlax.rh.siif.ReporteSiifView;
import mx.gob.saludtlax.rh.siif.layout.SIIFLayout;

@ManagedBean(name = "reportarSiifContratos")
@SessionScoped
public class ReportarSiifContratosControlller {

    private static final Logger LOGGER = Logger
            .getLogger(ReportarSiifContratosControlller.class.getName());

    @Inject
    private SIIFLayout generarLayout;
    @Inject
    private ReporteSiifEJB ejbc;
    private ReporteSiifView viewc;

    //private static final Logger LOGGER = Logger.getLogger(ReporteSiifController.class.getName());

    @Inject
    private ReportarSiifContratosEJB ejb;

    private ReportarSiifContratosView view;

    @PostConstruct
    public void init() {
        view = new ReportarSiifContratosView();
        irPrincipal();
        view.setCuentaBancariaList(ejb.obtenerCuentaBancariaList());
        view.setTipoNominaList(ejb.obtenerTipoNominaList());
    }

    public String irPrincipal() {
        view.panelPrincipal();
        return null;
    }

    public String cancelar() {
        return "/contenido/configuracion/reportarSiif.xhtml?faces-redirect=true";
    }

    public ReportarSiifContratosView getView() {
        return view;
    }

    /***
     * Import Excel
     *
     * @param event
     */
    //	public void uploadFile() {
    //		uploadedFile = new UploadExcelFileAnexo();
    //		uploadedFile.validate(view.getDat());
    //		settingDataDAT(uploadedFile.getAnexoDTOs());
    //		uploadedFile.validate(view.getTra());
    //		settingDataTRA(uploadedFile.getAnexoDTOs());
    //	}
    //
    //	public void uploadFileSP() {
    //		uploadedFile = new UploadExcelFileAnexo();
    //		uploadedFile.validate(view.getDat());
    //		settingDataDAT(uploadedFile.getAnexoDTOs());
    //	}
    //
    //	public void uploadFileCONT() {
    //
    //		uploadedFile = new UploadExcelFileAnexo();
    //		uploadedFile.validate(view.getCont());
    //		settingDataCONT(uploadedFile.getAnexoDTOs());
    //	}
    /***
     * Colaborador del import excel
     *
     * @param data
     */
    //	private void settingDataTRA(List<EstructuraDTO> data) {
    //
    //		Iterator<EstructuraDTO> arrayIterator = data.iterator();
    //		List<EstructuraContratosTrailersDTO> listaEstructura = new ArrayList<EstructuraContratosTrailersDTO>();
    //		try {
    //			JSFUtils.infoMessage("En este momento han terminado de subir los archivos y se empieza a procesar","");
    //			view.setSiifBitacoraProcesada(ejb.procesarContratosTheosToSIIF(view.getPaqueteEntrada()));
    //			view.setSiifBitacoraProcesada(ejb.obtenerSiifBitacora(view.getSiifBitacoraProcesada()));// revisar proceso, no se ha movido nada
    //
    //			JSFUtils.infoMessage("En este momento se han terminado de procesar los archivos","");
    //		} catch (Exception e) {
    //			e.printStackTrace();
    //		}
    //	}

    //	public void validatorReporteSiif(FacesContext context, UIComponent component,
    //			Object value) {
    //
    //
    //		Iterator<EstructuraDTO> arrayIterator = data.iterator();
    //		List<EstructuraContratosExcelDTO> listaEstructura = new ArrayList<EstructuraContratosExcelDTO>();
    //		try {
    //			while (arrayIterator.hasNext()) {
    //
    //				EstructuraDTO genericoDTO = arrayIterator.next();
    //				EstructuraContratosExcelDTO estructuraDTO = new EstructuraContratosExcelDTO();
    //
    //				estructuraDTO.setNum(genericoDTO.getDato(0, Integer.class));
    //				estructuraDTO.setPrograma(genericoDTO.getDato(1, String.class));
    //				estructuraDTO.setRfc(genericoDTO.getDato(2, String.class));
    //				estructuraDTO.setRfcVal(genericoDTO.getDato(3, String.class));
    //				estructuraDTO.setNombre(genericoDTO.getDato(4, String.class));
    //				estructuraDTO.setfI(genericoDTO.getDato(5, String.class));
    //				estructuraDTO.setcResponsable(genericoDTO.getDato(6, String.class));
    //				estructuraDTO.setFuncion(genericoDTO.getDato(7, String.class));
    //				estructuraDTO.setRama(genericoDTO.getDato(8, String.class));
    //				estructuraDTO.setfFinan(genericoDTO.getDato(9, String.class));
    //				estructuraDTO.setProg(genericoDTO.getDato(10, String.class));
    //
    //				estructuraDTO.setTotalBruto(genericoDTO.getDato(11, BigDecimal.class));
    //				estructuraDTO.setPercepciones(genericoDTO.getDato(12, BigDecimal.class));
    //				estructuraDTO.setDeducciones(genericoDTO.getDato(13, BigDecimal.class));
    //				estructuraDTO.setNeto(genericoDTO.getDato(14, BigDecimal.class));
    //
    //				estructuraDTO.setSueldo(genericoDTO.getDato(15, BigDecimal.class));
    //				estructuraDTO.setSup(genericoDTO.getDato(16, BigDecimal.class));
    //				estructuraDTO.setComp(genericoDTO.getDato(17, BigDecimal.class));
    //				estructuraDTO.setAg(genericoDTO.getDato(18, BigDecimal.class));
    //				estructuraDTO.setSubsidio(genericoDTO.getDato(19, BigDecimal.class));
    //				estructuraDTO.setVac(genericoDTO.getDato(20, Integer.class));
    //				estructuraDTO.setrFaltas(genericoDTO.getDato(21, Integer.class));
    //
    //				estructuraDTO.setRetroa(genericoDTO.getDato(22, BigDecimal.class));//este no estaba
    //				estructuraDTO.setOtros(genericoDTO.getDato(23, BigDecimal.class));//este no estaba
    //				estructuraDTO.setFaltas(genericoDTO.getDato(24, Integer.class));//esta no estaba
    //
    //				estructuraDTO.setIstp(genericoDTO.getDato(25, BigDecimal.class));
    //				estructuraDTO.setRespons(genericoDTO.getDato(26, Integer.class));
    //				estructuraDTO.setPrestamo(genericoDTO.getDato(27, Integer.class));
    //				estructuraDTO.setPa(genericoDTO.getDato(28, BigDecimal.class));
    //				estructuraDTO.setTotal(genericoDTO.getDato(29, BigDecimal.class));
    //
    //				estructuraDTO.setVerifica(genericoDTO.getDato(30, String.class));
    //				estructuraDTO.settCentro(genericoDTO.getDato(31, String.class));
    //				estructuraDTO.setcClues(genericoDTO.getDato(32, String.class));
    //				estructuraDTO.setNomUnidad(genericoDTO.getDato(33, String.class));
    //				estructuraDTO.setaAdscripcion(genericoDTO.getDato(34, String.class));
    //				estructuraDTO.setPuesto(genericoDTO.getDato(35, String.class));
    //				estructuraDTO.setcPuesto(genericoDTO.getDato(36, String.class));
    //				estructuraDTO.setServicio(genericoDTO.getDato(37, String.class));
    //				estructuraDTO.setRamas(genericoDTO.getDato(38, String.class));
    //				estructuraDTO.setTurno(genericoDTO.getDato(39, String.class));
    //
    //				listaEstructura.add(estructuraDTO);
    //			}
    //			estructuraContratoTra.registrarListaEstructuraExcel(listaEstructura);
    //			JSFUtils.infoMessage("Registro Contratos", "Proceso realizado correctamente");
    //		} catch (EstructuraException e) {
    //			e.printStackTrace();
    //			JSFUtils.errorMessage("Error", e.getMessage());
    //
    //		String nombreComponete = component.getId();
    //		switch (nombreComponete) {
    //		case "anioCriterio":
    //			Integer anioCriterio = (Integer) value;
    //				if (!ValidacionUtil.esNumeroPositivo(anioCriterio)) {
    //					FacesMessage facesMessage = new FacesMessage(
    //							FacesMessage.SEVERITY_ERROR, "",
    //							"Por favor ingrese un criterio de búsqueda.");
    //					context.addMessage(component.getClientId(), facesMessage);
    //					throw new ValidatorException(facesMessage);
    //			}else{
    //				if (anioCriterio<999) {
    //					FacesMessage facesMessage = new FacesMessage(
    //							FacesMessage.SEVERITY_ERROR, "",
    //							"Por favor ingrese 4 digitos");
    //					context.addMessage(component.getClientId(), facesMessage);
    //					throw new ValidatorException(facesMessage);
    //			}else{
    //				if(anioCriterio>9999){
    //					FacesMessage facesMessage = new FacesMessage(
    //							FacesMessage.SEVERITY_ERROR, "",
    //							"Por favor ingrese 4 digitos");
    //					context.addMessage(component.getClientId(), facesMessage);
    //					throw new ValidatorException(facesMessage);
    //					}
    //				}
    //			}
    //			break;
    //		case "periodoCriterio":
    //			String periodoCriterio = (String) value;
    //				if (ValidacionUtil.esCadenaVacia(periodoCriterio)) {
    //					FacesMessage facesMessage = new FacesMessage(
    //							FacesMessage.SEVERITY_ERROR, "",
    //							"Por favor ingrese un criterio de búsqueda.");
    //					context.addMessage(component.getClientId(), facesMessage);
    //					throw new ValidatorException(facesMessage);
    //			}
    //			break;
    //		case "idCuentaBancaria":
    //			Integer idCuentaBancaria = (Integer) value;
    //			if (!ValidacionUtil.esNumeroPositivo(idCuentaBancaria)) {
    //				FacesMessage facesMessage = new FacesMessage(
    //						FacesMessage.SEVERITY_ERROR, "",
    //						"Por favor seleccione una Cuenta Bancaria.");
    //				context.addMessage(component.getClientId(), facesMessage);
    //				throw new ValidatorException(facesMessage);
    //			}
    //			break;
    //		case "idTipoNomina":
    //			Integer idTipoNomina = (Integer) value;
    //			if (!ValidacionUtil.esNumeroPositivo(idTipoNomina)) {
    //				FacesMessage facesMessage = new FacesMessage(
    //						FacesMessage.SEVERITY_ERROR, "",
    //						"Por favor Seleccione un Tipo de Nomina.");
    //				context.addMessage(component.getClientId(), facesMessage);
    //				throw new ValidatorException(facesMessage);
    //			}
    //			break;
    //		default:
    //			break;
    //
    //		}
    //		}

}