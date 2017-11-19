/*
 *
 */

package mx.gob.saludtlax.rh.empleados.nombramientos.segurovidainstitucional;

import java.sql.Time;
import java.util.Date;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.persistencia.BeneficiarioEntity;
import mx.gob.saludtlax.rh.persistencia.BeneficiarioRepository;
import mx.gob.saludtlax.rh.persistencia.SeguroVidaEntity;
import mx.gob.saludtlax.rh.persistencia.SeguroVidaRepository;

/**
 * @author Eduardo Mex
 *
 */
public class SeguroVidaInstitucionalService {

    @Inject
    private SeguroVidaRepository seguroVidaRepository;
    @Inject
    private BeneficiarioRepository beneficiarioRepository;

    protected Integer crearSeguroVida(SeguroVidaInstitucionalDTO seguroVidaInstitucionalDTO) {

        if (seguroVidaRepository.existeNumeroExpediente(seguroVidaInstitucionalDTO.getNumeroExpediente())) {
            throw new ReglaNegocioException("El numero de expediente ya existe", ReglaNegocioCodigoError.YA_REGISTRADO);
        }

        SeguroVidaEntity seguroVidaEntity = new SeguroVidaEntity();

        seguroVidaEntity.setEstatus(true);
        seguroVidaEntity.setFechaAlta(new Date());
        seguroVidaEntity.setHoraAlta(new Time(new Date().getTime()));
        seguroVidaEntity.setIdEmpleado(seguroVidaInstitucionalDTO.getIdEmpleado());
        seguroVidaEntity.setNumeroExpediente(seguroVidaInstitucionalDTO.getNumeroExpediente());

        seguroVidaRepository.crear(seguroVidaEntity);

        for (BeneficiariosDTO beneficiariosDTO : seguroVidaInstitucionalDTO.getBeneficiariosDTOs()) {
            BeneficiarioEntity beneficiarioEntity = new BeneficiarioEntity();

            beneficiarioEntity.setIdDependienteEconomico(beneficiariosDTO.getIdDependienteEconomico());
            beneficiarioEntity.setIdSeguroVida(seguroVidaEntity.getIdSeguroVida());
            beneficiarioEntity.setPorcetaje(beneficiariosDTO.getPorcetaje());

            beneficiarioRepository.crear(beneficiarioEntity);
        }

        return seguroVidaEntity.getIdSeguroVida();
    }

    protected boolean existeNumeroExpediente(String numeroExpediente) {

        return seguroVidaRepository.existeNumeroExpediente(numeroExpediente);
    }

    protected Integer existeEmpleado(Integer idEmpleado) {

        return seguroVidaRepository.existeEmpleado(idEmpleado);
    }

}
