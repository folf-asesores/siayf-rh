/*
 * AdjuntoEmpleadoEJB.java
 * Creado el May 12, 2016 11:07:05 AM
 * 
 */
package mx.gob.saludtlax.rh.expediente.empleado;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.expediente.EntidadContexto;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;

/**
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 *
 */
@Stateless
public class AdjuntoEmpleadoEJB implements AdjuntoEmpleado {

    private static final long serialVersionUID = 1687957622457403252L;

    @Inject private AdjuntoEmpleadoService adjuntoService;
    

    @Override
    public int crear(InformacionAdjuntoDTO informacionDelAdjunto, byte[] adjunto) {
        if (informacionDelAdjunto == null) {
            throw new NullPointerException("No se puede guardar un adjunto cuando la información es nula");
        }
        
        if (adjunto == null) {
            throw new NullPointerException("No se puede guardar un adjunto que es nulo");
        }

        if (informacionDelAdjunto.getIdEmpleado() == null || informacionDelAdjunto.getIdEmpleado() < 1) {
            throw new ValidacionException("Debe incluir el ID del empleado", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (informacionDelAdjunto.getEntidadContexto() == null) {
            throw new ValidacionException("Debe incluir la entidad contexto.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (informacionDelAdjunto.getDocumentoAdjuntable() == null || informacionDelAdjunto.getDocumentoAdjuntable().getIdDocumentoAdjuntable() == null) {
            throw new ValidacionException("Debe incluir el documento adjuntable. Verifique que el ID no sea nulo.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        return adjuntoService.crear(informacionDelAdjunto, adjunto);
    }

    @Override
    public InformacionAdjuntoDTO obtenerInformacionAdjuntoPorIdAdjunto(int idAdjunto) {
        if (idAdjunto < 1) {
            throw new ValidacionException("El ID del adjunto no puede ser cero o negativo", ValidacionCodigoError.NUMERO_NEGATIVO);
        }
        
        return adjuntoService.obtenerInformacionAdjuntoPorIdAdjunto(idAdjunto);
    }

    @Override
    public byte[] obtenerAdjuntoPorIdAdjunto(int idAdjunto) {
        if (idAdjunto < 1) {
            throw new ValidacionException(
                    "El ID del adjunto no puede ser cero o negativo", ValidacionCodigoError.NUMERO_NEGATIVO);
        }
        
        return adjuntoService.obtenerAdjuntoPorIdAdjunto(idAdjunto);
    }

    @Override
    public byte[] obtenerVistaPreviaPorIdAdjunto(int idAdjunto) {
        if (idAdjunto < 1) {
            throw new ValidacionException(
                    "El ID del adjunto no puede ser cero o negativo", 
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        return adjuntoService.obtenerVistaPreviaPorIdAdjunto(idAdjunto);
    }    
    
    @Override
    public List<InformacionAdjuntoDTO> consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(
            EntidadContexto entidadContexto, int idEntidadContexto) {
        if (entidadContexto == null) {
            throw new ValidacionException("El tipo de documento adjunto es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (idEntidadContexto < 1) {
            throw new ValidacionException("El ID del tipo de documento no puede ser cero o negativo", ValidacionCodigoError.NUMERO_NEGATIVO);
        }        
        
        return adjuntoService.consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(entidadContexto, idEntidadContexto);
    }

    @Override
    public List<InformacionAdjuntoDTO> consultarInformacionAdjuntosPorEntidadContextoIdEntidadContextoIdDocumentoAdjuntable(
            EntidadContexto entidadContexto, int idEntidadContexto, int idDocumentoAdjuntable) {

        if (entidadContexto == null) {
            throw new ValidacionException("El tipoDocumento no puede ser null", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (idEntidadContexto < 1) {
            throw new ValidacionException(
                    "El ID del tipo de documento no puede ser cero o negativo", ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        if (idDocumentoAdjuntable < 1) {
            throw new NullPointerException(
                    "El ID del documento adjuntable no puede ser cero o negativo");
        }

        return adjuntoService.obtenerInformacionAduntosPorContextoEntidadIdContextoEntidadIdDocumentoAdjuntable(
                entidadContexto, idEntidadContexto, idDocumentoAdjuntable);
    }

    @Override
    public List<InformacionAdjuntoDTO> consultarInformacionAdjuntosPorIdEmpleado(int idEmpleado) {
        if (idEmpleado < 1) {
            throw new ValidacionException(
                    "El ID del empleado no puede ser cero o negativo", ValidacionCodigoError.NUMERO_NEGATIVO);
        }
        
        return adjuntoService
                .consultarInformacionAdjuntosPorIdEmpleado(idEmpleado);
    }

    @Override
    public List<String> consultarDocumentosAdjuntosPorEntidadContextoIdEntidadContexto(EntidadContexto entidadContexto,
            Integer idEntidadContexto) {

        if(entidadContexto == null) {
            throw new ValidacionException(
                    "La entidad contexto no puede ser nulo.",
                    ValidacionCodigoError.VALOR_REQUERIDO
            );
        }
        
        if(idEntidadContexto == null || idEntidadContexto < 1) {
            throw new ValidacionException(
                "El ID de la entidad contexto no puede ser nulo o menor que 1.",
                ValidacionCodigoError.VALOR_REQUERIDO
            );
        }
        
        return adjuntoService.documentosAdjuntosPorEntidadContexto(entidadContexto, idEntidadContexto);
    }
    
    @Override
    public void actualizar(Map<String, Object> parametros) {
        if (parametros == null) {
            throw new NullPointerException(
                    "No se puede actualizar el adjunto porque los parametros están vacios.");
        }

        adjuntoService.actualizar(parametros);
    }

    @Override
    public void importarExpedienteAspirante(Integer idAspirantente, 
            Integer idEmpleado) {
        if (idAspirantente < 1) {
            throw new ValidacionException(
                    "El ID del aspirante no debe ser negativo.", 
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        if (idEmpleado < 1) {
            throw new ValidacionException(
                    "El ID del empleado no debe ser negativo.", 
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }
        
        adjuntoService.importarExpedienteAspirante(idAspirantente, idEmpleado);
    }
    
    @Override
    public void elimnar(int idAdjunto) {
        if (idAdjunto < 1) {
            throw new ValidacionException(
                    "El ID del adjunto no puede ser cero o negativo",
                    ValidacionCodigoError.VALOR_REQUERIDO
            );
        }

        adjuntoService.eliminar(idAdjunto);
    }
}
