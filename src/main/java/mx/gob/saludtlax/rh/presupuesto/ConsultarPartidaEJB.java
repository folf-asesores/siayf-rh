package mx.gob.saludtlax.rh.presupuesto;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaDTO;
import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaService;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoService;
import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsableDTO;
import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsableService;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;

@Stateless
public class ConsultarPartidaEJB {
    @Inject
    private TipoNombramientoService tipoNombramientoService;
    @Inject
    private UnidadResponsableService unidadResponsableService;
    @Inject
    private ConsultarPartidaService consultarPartidaService;
    @Inject
	private DependenciaService dependenciaService;

    public List<TipoNombramientoDTO> getListaTipoNombramiento() {
        return tipoNombramientoService.listaNombramiento();
    }

    public List<UnidadResponsableDTO> getListaUnidadResponsable() {
        return unidadResponsableService.listaUnidadResponsable();
    }
    
    public List<DependenciaDTO> getListaDependencia(){
    	return dependenciaService.listaDependencia();
    }
    
    public List<ConsultarPartidaDTO> consultarPartidasPorRfc(String rfc) {
        // TODO Auto-generated method stub
        return consultarPartidaService.consultarPartidasPorRfc(rfc);
    }

    public List<ConsultarPartidaDTO> consultarPartidasPorUnidadResponsable(Integer unidadResponsable)
            throws ReglaNegocioException {
        // TODO Auto-generated method stub
        return consultarPartidaService.consultarPartidasPorUnidadResponsable(unidadResponsable);
    }

    public List<ConsultarPartidaDTO> consultarPartidasPorTipoNombramiento(Integer tipoNombramiento)
            throws ReglaNegocioException {
        // TODO Auto-generated method stub
        return consultarPartidaService.consultarPartidasPorTipoNombramiento(tipoNombramiento);
    }
    public List<ConsultarPartidaDTO> consultarPartidasPorDependencia(Integer dependencia)
            throws ReglaNegocioException {
        // TODO Auto-generated method stub
        return consultarPartidaService.consultarPartidasPorDependencia(dependencia);
    }
    
    public static List<SelectItem> listaQuincena() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem("15/01/2016", "15/01/2016");
		SelectItem item2 = new SelectItem("31/01/2016", "31/01/2016");
		SelectItem item3 = new SelectItem("15/02/2016", "15/02/2016");
		SelectItem item4 = new SelectItem("29/02/2016", "29/02/2016");
		SelectItem item5 = new SelectItem("15/03/2016", "15/03/2016");
		SelectItem item6 = new SelectItem("31/03/2016", "31/03/2016");
		SelectItem item7 = new SelectItem("15/04/2016", "15/04/2016");
		SelectItem item8 = new SelectItem("30/04/2016", "30/04/2016");
		SelectItem item9 = new SelectItem("15/05/2016", "15/05/2016");
		SelectItem item10 = new SelectItem("31/05/2016", "31/05/2016");
		SelectItem item11 = new SelectItem("15/06/2016", "15/06/2016");
		SelectItem item12 = new SelectItem("30/06/2016", "30/06/2016");
		SelectItem item13 = new SelectItem("15/07/2016", "15/07/2016");
		SelectItem item14 = new SelectItem("31/07/2016", "31/07/2016");
		SelectItem item15 = new SelectItem("15/08/2016", "15/08/2016");
		SelectItem item16 = new SelectItem("31/08/2016", "31/08/2016");
		SelectItem item17 = new SelectItem("15/09/2016", "15/09/2016");
		SelectItem item18 = new SelectItem("30/09/2016", "30/09/2016");
		SelectItem item19 = new SelectItem("15/10/2016", "15/10/2016");
		

		lista.add(item1);
		lista.add(item2);
		lista.add(item3);
		lista.add(item4);
		lista.add(item5);
		lista.add(item6);
		lista.add(item7);
		lista.add(item8);
		lista.add(item9);
		lista.add(item10);
		lista.add(item11);
		lista.add(item12);
		lista.add(item13);
		lista.add(item14);
		lista.add(item15);
		lista.add(item16);
		lista.add(item17);
		lista.add(item18);
		lista.add(item19);
		return lista;
	}

}
