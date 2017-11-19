package mx.gob.saludtlax.rh.siif.revisarcheques;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean(name="revisarCheque")
@ViewScoped
public class RevisarChequesController implements Serializable {
	
	private static final long serialVersionUID=1545175823479673131L;
	
	@Inject
	private RevisarCheques revisarCheque;

	private RevisarChequesView view;

	@PostConstruct
	private void init() {
		view = new RevisarChequesView();
		obtenerListaRevisarCheques();
	}
	
	public void obtenerListaRevisarCheques() {

		List<RevisarChequesDTO> obtenerLista = revisarCheque.obtenerListaRevisarCheques();

		if (!obtenerLista.isEmpty()) {
			this.view.setObtenerListaRevisarChequesDTOs(obtenerLista);
		} else {
			this.view.setObtenerListaRevisarChequesDTOs(new ArrayList<RevisarChequesDTO>());
		}
	}
	
	public void accionRevisarCheque() {
		try {
			this.view.setMostrarResultadoConsulta(true);
			if (this.view.getAccionRevisarCheques().equals("Revisar")) {
				this.view.setObtenerListaRevisarChequesDTOs(revisarCheque.obtenerListaRevisarCheques(
						this.view.getRevisarChequesDTO().getPeriodo(), this.view.getRevisarChequesDTO().getAnio()));
				JSFUtils.infoMessage("Lista de Cheques: ", "Se realizo correctamente");
				// limpiarVista();
			}else if (this.view.getAccionRevisarCheques().equals("Avanzada")) {
				JSFUtils.infoMessage("Busqueda de Cheques: ", "Se realizo correctamente");
				// limpiarVista();
			} else if (this.view.getObtenerListaRevisarChequesDTOs().isEmpty()) {
				JSFUtils.infoMessageEspecifico("info", "",
						"No se encontraron registros " );
			}
		} catch (BusinessException ex) {
			JSFUtils.errorMessage("Error: ", ex.getMessage());
		}

	}
	
	
	

//	public RevisarCheques getRevisarCheque() {
//		return revisarCheque;
//	}
//
//	public void setRevisarCheque(RevisarCheques revisarCheque) {
//		this.revisarCheque = revisarCheque;
//	}

	public RevisarChequesView getView() {
		return view;
	}

	public void setView(RevisarChequesView view) {
		this.view = view;
	}
	

	
}
