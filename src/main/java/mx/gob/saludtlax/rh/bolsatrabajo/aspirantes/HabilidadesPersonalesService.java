/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.AspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.HabilidadPersonalEntity;
import mx.gob.saludtlax.rh.persistencia.HabilidadPersonalRepository;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 07/04/2016 9:29:09
 */
public class HabilidadesPersonalesService {

    @Inject
    private AspiranteRepository aspiranteRepository;
    @Inject
    private HabilidadPersonalRepository habilidadesPersonalesAspiranteRepository;

    /***
     * Registro de encuesta personal
     *
     * @param encuestaPersonalAspirante
     */
    protected void crearHabilidadesPersonalesAspirante(
            HabilidadesPersonalesAspiranteDTO habilidadesPersonalesDTO) {
        String contexto = "Registro Habilidades Personales: ";

        AspiranteEntity aspirante = aspiranteRepository
                .obtenerPorId(habilidadesPersonalesDTO.getIdAspirante());

        if (aspirante == null) {
            throw new ValidacionException(
                    "Es requerido que registre los datos personales del aspirante.",
                    ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
        }

        HabilidadPersonalEntity registroHabilidadPersonal = new HabilidadPersonalEntity();
        // Consultando si ya registro la habilidad personal del aspirante
        Boolean existeExperienciaLaboral = habilidadesPersonalesAspiranteRepository
                .existeIdAspirante(aspirante.getIdAspirante());
        if (existeExperienciaLaboral) {

            throw new ValidacionException(contexto
                    + "La habilidad personal del aspirante ya se encuentra registrado",
                    ValidacionCodigoError.VALOR_DUPLICADO);
        }

        // Conocimientos Generales
        registroHabilidadPersonal.setAspirante(aspirante);
        registroHabilidadPersonal
                .setIdioma(habilidadesPersonalesDTO.getIdioma());
        registroHabilidadPersonal
                .setNivelIdioma(habilidadesPersonalesDTO.getNivelIdioma());
        registroHabilidadPersonal.setMaquinaTallerDomina(
                habilidadesPersonalesDTO.getMaquinaTallerDomina());
        registroHabilidadPersonal.setFuncionesOficinaDomina(
                habilidadesPersonalesDTO.getFuncionesOficinaDomina());
        registroHabilidadPersonal.setSoftwareDomina(
                habilidadesPersonalesDTO.getSoftwareDomina());
        registroHabilidadPersonal.setOtrosTrabajosFunciones(
                habilidadesPersonalesDTO.getOtrosTrabajosFunciones());
        // Datos Generales
        registroHabilidadPersonal
                .setAnuncio(habilidadesPersonalesDTO.getAnuncio());
        registroHabilidadPersonal
                .setOtroMedio(habilidadesPersonalesDTO.getOtroMedio());
        registroHabilidadPersonal
                .setParientes(habilidadesPersonalesDTO.isParientes());
        registroHabilidadPersonal.setNombreParientes(
                habilidadesPersonalesDTO.getNombreParientes());
        registroHabilidadPersonal
                .setAfianzado(habilidadesPersonalesDTO.isAfianzado());
        registroHabilidadPersonal
                .setNombreAfianza(habilidadesPersonalesDTO.getNombreAfianza());
        registroHabilidadPersonal
                .setSindicato(habilidadesPersonalesDTO.isSindicato());
        registroHabilidadPersonal.setNombreSindicato(
                habilidadesPersonalesDTO.getNombreSindicato());
        registroHabilidadPersonal
                .setSeguroVida(habilidadesPersonalesDTO.isSeguroVida());
        registroHabilidadPersonal.setNombreSeguroVida(
                habilidadesPersonalesDTO.getNombreSeguroVida());
        registroHabilidadPersonal.setDisposicionViajar(
                habilidadesPersonalesDTO.isDisposicionViajar());
        registroHabilidadPersonal
                .setRazonNoViajar(habilidadesPersonalesDTO.getRazonNoViajar());
        registroHabilidadPersonal.setCambioResidencia(
                habilidadesPersonalesDTO.isCambioResidencia());
        registroHabilidadPersonal.setRazonNoCambioResidencia(
                habilidadesPersonalesDTO.getRazonNoCambioResidencia());
        registroHabilidadPersonal.setFechaEmpezarTrabajar(
                habilidadesPersonalesDTO.getFechaEmpezarTrabajar());
        // Datos Económicos
        registroHabilidadPersonal
                .setOtroIngreso(habilidadesPersonalesDTO.isOtroIngreso());
        registroHabilidadPersonal.setNombreOtroIngreso(
                habilidadesPersonalesDTO.getNombreOtroIngreso());
        registroHabilidadPersonal.setImporteOtroIngreso(
                habilidadesPersonalesDTO.getImporteOtroIngreso());
        registroHabilidadPersonal.setConyugeTrabajando(
                habilidadesPersonalesDTO.isConyugeTrabajando());
        registroHabilidadPersonal.setNombreTrabajoConyuge(
                habilidadesPersonalesDTO.getNombreTrabajoConyuge());
        registroHabilidadPersonal.setPercepcionMensualConyuge(
                habilidadesPersonalesDTO.getPercepcionMensualConyuge());
        registroHabilidadPersonal
                .setCasaPropia(habilidadesPersonalesDTO.isCasaPropia());
        registroHabilidadPersonal.setValorAproximadoCasa(
                habilidadesPersonalesDTO.getValorAproximadoCasa());
        registroHabilidadPersonal
                .setRentaCasa(habilidadesPersonalesDTO.isRentaCasa());
        registroHabilidadPersonal
                .setRentaMensual(habilidadesPersonalesDTO.getRentaMensual());
        registroHabilidadPersonal.setAutomovilPropio(
                habilidadesPersonalesDTO.isAutomovilPropio());
        registroHabilidadPersonal.setMarcaAutomovil(
                habilidadesPersonalesDTO.getMarcaAutomovil());
        registroHabilidadPersonal.setModeloAutomovil(
                habilidadesPersonalesDTO.getModeloAutomovil());
        registroHabilidadPersonal
                .setDeudas(habilidadesPersonalesDTO.isDeudas());
        registroHabilidadPersonal
                .setNombreDeuda(habilidadesPersonalesDTO.getNombreDeuda());
        registroHabilidadPersonal
                .setImporteDeuda(habilidadesPersonalesDTO.getImporteDeuda());
        registroHabilidadPersonal.setAbonoMensualDeuda(
                habilidadesPersonalesDTO.getAbonoMensualDeuda());
        registroHabilidadPersonal
                .setGastoMensual(habilidadesPersonalesDTO.getGastoMensual());
        // registrando encuesta personal
        habilidadesPersonalesAspiranteRepository
                .crear(registroHabilidadPersonal);

    }

    /**
     * Actualización de habilidades personales
     *
     * @param encuestaPersonalAspirante
     */
    protected void actualizarHabilidadesPersonalesAspirante(
            HabilidadesPersonalesAspiranteDTO habilidadesPersonalesDTO) {

        // Verificando que exista el aspírante por identificador unico
        AspiranteEntity aspirante = aspiranteRepository
                .obtenerPorId(habilidadesPersonalesDTO.getIdAspirante());

        if (aspirante == null) {

            throw new ValidacionException(
                    "Es requerido que registre los datos personales del aspirante.",
                    ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);

        }
        // Obteniendo la habilidad personal por identificador unico
        HabilidadPersonalEntity actualizacionHabilidadPersonal = habilidadesPersonalesAspiranteRepository
                .obtenerPorId(habilidadesPersonalesDTO
                        .getIdEncuestaPersonalAspirante());

        // Conocimientos Generales
        actualizacionHabilidadPersonal.setAspirante(aspirante);
        actualizacionHabilidadPersonal
                .setIdioma(habilidadesPersonalesDTO.getIdioma());
        actualizacionHabilidadPersonal
                .setNivelIdioma(habilidadesPersonalesDTO.getNivelIdioma());
        actualizacionHabilidadPersonal.setMaquinaTallerDomina(
                habilidadesPersonalesDTO.getMaquinaTallerDomina());
        actualizacionHabilidadPersonal.setFuncionesOficinaDomina(
                habilidadesPersonalesDTO.getFuncionesOficinaDomina());
        actualizacionHabilidadPersonal.setSoftwareDomina(
                habilidadesPersonalesDTO.getSoftwareDomina());
        actualizacionHabilidadPersonal.setOtrosTrabajosFunciones(
                habilidadesPersonalesDTO.getOtrosTrabajosFunciones());
        // Datos Generales
        actualizacionHabilidadPersonal
                .setAnuncio(habilidadesPersonalesDTO.getAnuncio());
        actualizacionHabilidadPersonal
                .setOtroMedio(habilidadesPersonalesDTO.getOtroMedio());
        actualizacionHabilidadPersonal
                .setParientes(habilidadesPersonalesDTO.isParientes());
        actualizacionHabilidadPersonal.setNombreParientes(
                habilidadesPersonalesDTO.getNombreParientes());
        actualizacionHabilidadPersonal
                .setAfianzado(habilidadesPersonalesDTO.isAfianzado());
        actualizacionHabilidadPersonal
                .setNombreAfianza(habilidadesPersonalesDTO.getNombreAfianza());
        actualizacionHabilidadPersonal
                .setSindicato(habilidadesPersonalesDTO.isSindicato());
        actualizacionHabilidadPersonal.setNombreSindicato(
                habilidadesPersonalesDTO.getNombreSindicato());
        actualizacionHabilidadPersonal
                .setSeguroVida(habilidadesPersonalesDTO.isSeguroVida());
        actualizacionHabilidadPersonal.setNombreSeguroVida(
                habilidadesPersonalesDTO.getNombreSeguroVida());
        actualizacionHabilidadPersonal.setDisposicionViajar(
                habilidadesPersonalesDTO.isDisposicionViajar());
        actualizacionHabilidadPersonal
                .setRazonNoViajar(habilidadesPersonalesDTO.getRazonNoViajar());
        actualizacionHabilidadPersonal.setCambioResidencia(
                habilidadesPersonalesDTO.isCambioResidencia());
        actualizacionHabilidadPersonal.setRazonNoCambioResidencia(
                habilidadesPersonalesDTO.getRazonNoCambioResidencia());
        actualizacionHabilidadPersonal.setFechaEmpezarTrabajar(
                habilidadesPersonalesDTO.getFechaEmpezarTrabajar());
        // Datos Económicos
        actualizacionHabilidadPersonal
                .setOtroIngreso(habilidadesPersonalesDTO.isOtroIngreso());
        actualizacionHabilidadPersonal.setNombreOtroIngreso(
                habilidadesPersonalesDTO.getNombreOtroIngreso());
        actualizacionHabilidadPersonal.setImporteOtroIngreso(
                habilidadesPersonalesDTO.getImporteOtroIngreso());
        actualizacionHabilidadPersonal.setConyugeTrabajando(
                habilidadesPersonalesDTO.isConyugeTrabajando());
        actualizacionHabilidadPersonal.setNombreTrabajoConyuge(
                habilidadesPersonalesDTO.getNombreTrabajoConyuge());
        actualizacionHabilidadPersonal.setPercepcionMensualConyuge(
                habilidadesPersonalesDTO.getPercepcionMensualConyuge());
        actualizacionHabilidadPersonal
                .setCasaPropia(habilidadesPersonalesDTO.isCasaPropia());
        actualizacionHabilidadPersonal.setValorAproximadoCasa(
                habilidadesPersonalesDTO.getValorAproximadoCasa());
        actualizacionHabilidadPersonal
                .setRentaCasa(habilidadesPersonalesDTO.isRentaCasa());
        actualizacionHabilidadPersonal
                .setRentaMensual(habilidadesPersonalesDTO.getRentaMensual());
        actualizacionHabilidadPersonal.setAutomovilPropio(
                habilidadesPersonalesDTO.isAutomovilPropio());
        actualizacionHabilidadPersonal.setMarcaAutomovil(
                habilidadesPersonalesDTO.getMarcaAutomovil());
        actualizacionHabilidadPersonal.setModeloAutomovil(
                habilidadesPersonalesDTO.getModeloAutomovil());
        actualizacionHabilidadPersonal
                .setDeudas(habilidadesPersonalesDTO.isDeudas());
        actualizacionHabilidadPersonal
                .setNombreDeuda(habilidadesPersonalesDTO.getNombreDeuda());
        actualizacionHabilidadPersonal
                .setImporteDeuda(habilidadesPersonalesDTO.getImporteDeuda());
        actualizacionHabilidadPersonal.setAbonoMensualDeuda(
                habilidadesPersonalesDTO.getAbonoMensualDeuda());
        actualizacionHabilidadPersonal
                .setGastoMensual(habilidadesPersonalesDTO.getGastoMensual());
        // Actualización encuesta personal
        habilidadesPersonalesAspiranteRepository
                .actualizar(actualizacionHabilidadPersonal);

    }

    /**
     * Consultar Habilidades personales por identificador unico del aspirante
     *
     * @param idAspirante
     * @return
     */
    protected HabilidadesPersonalesAspiranteDTO obtenerHabilidadesPersonalesPorIdAspirante(
            Integer idAspirante) {

        String contexto = "Habilidad Personal: ";

        if (idAspirante == null) {
            throw new ValidacionException(
                    contexto + "El identificador del aspirante es requerido",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        HabilidadPersonalEntity habilidadPersonal = habilidadesPersonalesAspiranteRepository
                .encuentaPersonalPorIdAspirante(idAspirante);

        // if (habilidadPersonal == null) {
        // throw new BusinessException(contexto + "La habilidad personal del
        // aspirante no se encuentra registrado");
        // }

        HabilidadesPersonalesAspiranteDTO habilidadPersonalDTO = new HabilidadesPersonalesAspiranteDTO();

        if (habilidadPersonal != null) {

            habilidadPersonalDTO.setIdEncuestaPersonalAspirante(
                    habilidadPersonal.getIdEncuestaPersonalAspirante());
            habilidadPersonalDTO.setIdAspirante(
                    habilidadPersonal.getAspirante().getIdAspirante());
            habilidadPersonalDTO.setIdioma(habilidadPersonal.getIdioma());
            habilidadPersonalDTO
                    .setNivelIdioma(habilidadPersonal.getNivelIdioma());
            habilidadPersonalDTO.setMaquinaTallerDomina(
                    habilidadPersonal.getMaquinaTallerDomina());
            habilidadPersonalDTO.setFuncionesOficinaDomina(
                    habilidadPersonal.getFuncionesOficinaDomina());
            habilidadPersonalDTO
                    .setSoftwareDomina(habilidadPersonal.getSoftwareDomina());
            habilidadPersonalDTO.setOtrosTrabajosFunciones(
                    habilidadPersonal.getOtrosTrabajosFunciones());
            habilidadPersonalDTO.setAnuncio(habilidadPersonal.getAnuncio());
            habilidadPersonalDTO.setOtroMedio(habilidadPersonal.getOtroMedio());
            habilidadPersonalDTO.setParientes(habilidadPersonal.isParientes());
            habilidadPersonalDTO
                    .setNombreParientes(habilidadPersonal.getNombreParientes());
            habilidadPersonalDTO.setAfianzado(habilidadPersonal.isAfianzado());
            habilidadPersonalDTO
                    .setNombreAfianza(habilidadPersonal.getNombreAfianza());
            habilidadPersonalDTO.setSindicato(habilidadPersonal.isSindicato());
            habilidadPersonalDTO
                    .setNombreSindicato(habilidadPersonal.getNombreSindicato());
            habilidadPersonalDTO
                    .setSeguroVida(habilidadPersonal.isSeguroVida());
            habilidadPersonalDTO.setNombreSeguroVida(
                    habilidadPersonal.getNombreSeguroVida());
            habilidadPersonalDTO.setDisposicionViajar(
                    habilidadPersonal.isDisposicionViajar());
            habilidadPersonalDTO
                    .setRazonNoViajar(habilidadPersonal.getRazonNoViajar());
            habilidadPersonalDTO.setCambioResidencia(
                    habilidadPersonal.isCambioResidencia());
            habilidadPersonalDTO.setRazonNoCambioResidencia(
                    habilidadPersonal.getRazonNoCambioResidencia());
            habilidadPersonalDTO.setFechaEmpezarTrabajar(
                    habilidadPersonal.getFechaEmpezarTrabajar());
            habilidadPersonalDTO
                    .setOtroIngreso(habilidadPersonal.isOtroIngreso());
            habilidadPersonalDTO.setNombreOtroIngreso(
                    habilidadPersonal.getNombreOtroIngreso());
            habilidadPersonalDTO.setImporteOtroIngreso(
                    habilidadPersonal.getImporteOtroIngreso());
            habilidadPersonalDTO.setConyugeTrabajando(
                    habilidadPersonal.isConyugeTrabajando());
            habilidadPersonalDTO.setNombreTrabajoConyuge(
                    habilidadPersonal.getNombreTrabajoConyuge());
            habilidadPersonalDTO.setPercepcionMensualConyuge(
                    habilidadPersonal.getPercepcionMensualConyuge());
            habilidadPersonalDTO
                    .setCasaPropia(habilidadPersonal.isCasaPropia());
            habilidadPersonalDTO.setValorAproximadoCasa(
                    habilidadPersonal.getValorAproximadoCasa());
            habilidadPersonalDTO.setRentaCasa(habilidadPersonal.isRentaCasa());
            habilidadPersonalDTO
                    .setRentaMensual(habilidadPersonal.getRentaMensual());
            habilidadPersonalDTO
                    .setAutomovilPropio(habilidadPersonal.isAutomovilPropio());
            habilidadPersonalDTO
                    .setMarcaAutomovil(habilidadPersonal.getMarcaAutomovil());
            habilidadPersonalDTO
                    .setModeloAutomovil(habilidadPersonal.getModeloAutomovil());
            habilidadPersonalDTO.setDeudas(habilidadPersonal.isDeudas());
            habilidadPersonalDTO
                    .setNombreDeuda(habilidadPersonal.getNombreDeuda());
            habilidadPersonalDTO
                    .setImporteDeuda(habilidadPersonal.getImporteDeuda());
            habilidadPersonalDTO.setAbonoMensualDeuda(
                    habilidadPersonal.getAbonoMensualDeuda());
            habilidadPersonalDTO
                    .setGastoMensual(habilidadPersonal.getGastoMensual());

        }

        return habilidadPersonalDTO;
    }

}
