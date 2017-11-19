/*
 *
 */

package mx.gob.saludtlax.rh.expediente.aspirante;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * @since 30/06/2016 15:38:24
 * @version 1.0
 * 
 */
@Stateless
public class ExpedienteAspiranteEJB implements ExpedienteAspirante {

    @Inject
    private ExpedienteAspiranteService expedienteAspiranteService;

    @Override
    public void crearExpediente(ExpedienteAspiranteDTO expedienteDTO) {
        expedienteAspiranteService.crearExpediente(expedienteDTO);
    }

    @Override
    public boolean tieneExpedienteAperturado(Integer idAspirante) {
        return expedienteAspiranteService
                .tieneExpedienteAperturado(idAspirante);
    }

    @Override
    public String numeroExpedienteAspirante(Integer idAspirante) {
        return expedienteAspiranteService
                .numeroExpedienteAspirante(idAspirante);
    }

    @Override
    public Integer obtenerIdExpedienteAspirante(Integer idAspirante) {
        return expedienteAspiranteService
                .obtenerIdExpedienteAspirante(idAspirante);
    }

    @Override
    public boolean validarNumeroExpedienteAspirante(String numeroExpediente) {
        return expedienteAspiranteService
                .validarNumeroExpedienteAspirante(numeroExpediente);
    }

}
