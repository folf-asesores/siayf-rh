/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia.relacionpersonal;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.centroresponsabilidad.CentroResponsabilidadService;
import mx.gob.saludtlax.rh.configuracion.ejerciciofiscal.EjercicioFiscalService;

/**
 * @author Eduardo Mex
 *
 */
@Stateless
public class RelacionPersonalSuplenteEJB implements RelacionPersonalSuplente, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6107641243367646840L;

    @Inject
    private EjercicioFiscalService ejercicioFiscalService;

    @Inject
    private CentroResponsabilidadService centroResponsabilidadService;

    @Inject
    private RelacionPersonalSuplenteService relacionPersonalSuplenteService;

    @Override
    public List<RelacionPersonalSuplenteDTO> obtenerListaRelacionPersonalSuplente(Integer numeroQuincena, Integer ejercicioFiscal,
            Integer idCentroResponsabilidad) {

        return relacionPersonalSuplenteService.obtenerListaRelacionPersonalSuplente(numeroQuincena, ejercicioFiscal, idCentroResponsabilidad);
    }

    @Override
    public List<SelectItem> listaEjercicioFiscal() {

        return ejercicioFiscalService.listaEjercicioFiscalItems();
    }

    @Override
    public List<SelectItem> listaCentroResponsabilidad() {

        return centroResponsabilidadService.obtenerCentroResponsabilidadItems();
    }

}
