/*
 * AdjuntoAspiranteEJB.java
 * Creado el May 12, 2016 11:07:05 AM
 *
 */

package mx.gob.saludtlax.rh.expediente.aspirante;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.expediente.EntidadContexto;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;

/**
 * Este EJB se encarga de la administración de los adjuntos del aspirante.
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class AdjuntoAspiranteEJB implements AdjuntoAspirante {

    private static final long serialVersionUID = 6366642726916563077L;

    @Inject
    private AdjuntoAspiranteService adjuntoService;

    @Override
    public int crear(InformacionAdjuntoDTO informacionDelAdjunto,
            byte[] adjunto) {

        if (informacionDelAdjunto == null) {
            throw new ValidacionException(
                    "No se puede guadar un adjunto que es nulo",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (informacionDelAdjunto.getIdAspirante() == null
                || informacionDelAdjunto.getIdAspirante() < 1) {

            throw new ValidacionException("Debe incluir el ID del aspirante.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (informacionDelAdjunto.getEntidadContexto() == null) {
            throw new ValidacionException("Debe incluir el tipo documento",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (informacionDelAdjunto.getDocumentoAdjuntable() == null
                || informacionDelAdjunto.getDocumentoAdjuntable()
                        .getIdDocumentoAdjuntable() == null) {

            throw new ValidacionException(
                    "Debe incluir el documento adjuntable. Verifique que el ID no sea nulo.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        return adjuntoService.crear(informacionDelAdjunto, adjunto);
    }

    @Override
    public InformacionAdjuntoDTO obtenerInformacionAdjuntoPorIdAdjunto(
            int idAdjunto) {

        if (idAdjunto < 1) {
            throw new ValidacionException(
                    "El ID del adjunto no puede ser cero o negativo",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        return adjuntoService.obtenerInformacionAdjuntoPorIdAdjunto(idAdjunto);
    }

    @Override
    public byte[] obtenerAdjuntoPorIdAdjunto(int idAdjunto) {
        if (idAdjunto < 1) {
            throw new ValidacionException(
                    "El ID del adjunto no puede ser cero o negativo",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
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

        return adjuntoService.obtenerVistaPreviaPorIDAdjunto(idAdjunto);
    }

    @Override
    public List<InformacionAdjuntoDTO> consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(
            EntidadContexto entidadContexto, int idEntidadContexto) {

        if (entidadContexto == null) {
            throw new ValidacionException("El tipoDocumento no puede ser null",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (idEntidadContexto < 1) {
            throw new ValidacionException(
                    "El ID del tipo de documento no puede ser cero o negativo",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        return adjuntoService
                .consultarInformacionAdjuntosPorTipoDocumentoIdTipoDocumento(
                        entidadContexto, idEntidadContexto);
    }

    @Override
    public List<InformacionAdjuntoDTO> consultarInformacionAdjuntosPorEntidadContextoIdEntidadContextoIdDocumentoAdjuntable(
            EntidadContexto entidadContexto, int idEntidadContexto,
            int idDocumentoAdjuntable) {

        if (entidadContexto == null) {
            throw new ValidacionException("El tipoDocumento no puede ser null",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (idEntidadContexto < 1) {
            throw new ValidacionException(
                    "El ID del tipo de documento no puede ser cero o negativo",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        if (idDocumentoAdjuntable < 1) {
            throw new ValidacionException(
                    "El idDocumentoAdjuntable no puede ser cero o negativo.",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        return adjuntoService
                .obtenerInformacionAduntosPorTipoDocumentoIdTipoDocumentoIdDocumentoAdjuntable(
                        entidadContexto, idEntidadContexto,
                        idDocumentoAdjuntable);
    }

    @Override
    public List<InformacionAdjuntoDTO> consultarInformacionAdjuntosPorIdAspirante(
            int idAspirante) {
        if (idAspirante < 1) {
            throw new ValidacionException(
                    "El ID del aspirante no puede ser cero o negativo",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        return adjuntoService
                .consultarInformacionAdjuntosPorIdAspirante(idAspirante);
    }

    @Override
    public void actualizar(Map<String, Object> parametros) {
        if (parametros == null) {
            throw new ValidacionException(
                    "No se puede actualizar el adjunto porque los parametros están vacios.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        adjuntoService.actualizar(parametros);
    }

    @Override
    public void elimnar(int idAdjunto) {
        if (idAdjunto < 1) {
            throw new ValidacionException(
                    "El ID del adjunto no puede ser cero o negativo",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        adjuntoService.elimnar(idAdjunto);
    }
}