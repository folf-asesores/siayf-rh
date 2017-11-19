/*
 *
 */

package mx.gob.saludtlax.rh.empleado.formatofiliacion;

import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.FormatoFiliacionEntity;
import mx.gob.saludtlax.rh.persistencia.FormatoFiliacionRepository;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class FormatoFiliacionService {

    @Inject
    private FormatoFiliacionRepository formatoFiliacionRepository;

    protected Integer crearFormatoFiliacion(FormatoFiliacionDTO dto) {

        String contexto = "crearFormatoFiliacion: ";

        if (dto == null) {
            throw new ValidacionException(contexto + "Ingrese los datos",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        List<FormatoFiliacionEntity> listaFormatoFiliacion = formatoFiliacionRepository
                .listaFormatoFiliacionPorIdEmpleado(dto.getIdEmpleado());

        if (!listaFormatoFiliacion.isEmpty()) {
            for (FormatoFiliacionEntity formatoFiliacionEntity : listaFormatoFiliacion) {
                formatoFiliacionEntity.setEstatus("INACTIVO");
                formatoFiliacionRepository.actualizar(formatoFiliacionEntity);
            }
        }

        FormatoFiliacionEntity entity = new FormatoFiliacionEntity();

        entity.setIdEmpleado(dto.getIdEmpleado());
        entity.setFiliacion(dto.getFiliacion());
        entity.setClave(dto.getClave());
        entity.setNombrePadre(dto.getNombrePadre());
        entity.setNombreMadre(dto.getNombreMadre());
        entity.setActaNumero(dto.getActaNumero());
        entity.setAnio(dto.getAnio());
        entity.setFoja(dto.getFoja());
        entity.setLibro(dto.getLibro());
        entity.setCartillaSmn(dto.getCartillaSmn());
        entity.setClabeCartilla(dto.getClabeCartilla());
        entity.setNombreConyuge(dto.getNombreConyuge());
        entity.setDomicilio(dto.getDomicilio());
        entity.setExtranjeros(dto.getExtranjeros());
        entity.setFecha(dto.getFecha());
        entity.setNombrePersonaConocidoUno(dto.getNombrePersonaConocidoUno());
        entity.setDomicilioPersonaConocidoUno(
                dto.getDomicilioPersonaConocidoUno());
        entity.setLugarPersonaConocidoUno(dto.getLugarPersonaConocidoUno());
        entity.setNombrePersonaConocidoDos(dto.getNombrePersonaConocidoDos());
        entity.setDomicilioPersonaConocidoDos(
                dto.getDomicilioPersonaConocidoDos());
        entity.setLugarPersonaConocidoDos(dto.getLugarPersonaConocidoDos());
        entity.setNombreParienteUno(dto.getNombreParienteUno());
        entity.setDomicilioParienteUno(dto.getDomicilioParienteUno());
        entity.setLugarParienteUno(dto.getLugarParienteUno());
        entity.setNombreParienteDos(dto.getNombreParienteDos());
        entity.setDomicilioParienteDos(dto.getDomicilioParienteDos());
        entity.setLugarParienteDos(dto.getLugarParienteDos());
        entity.setColorBlanco(dto.isColorBlanco());
        entity.setColorNegro(dto.isColorNegro());
        entity.setColorMorenoClaro(dto.isColorMorenoOscuro());
        entity.setColorMorenoOscuro(dto.isColorMorenoOscuro());
        entity.setColorAmarillo(dto.isColorAmarillo());
        entity.setCabelloCastClaro(dto.isCabelloCastClaro());
        entity.setCabelloCastOscuro(dto.isCabelloCastOscuro());
        entity.setCabelloNegro(dto.isCabelloNegro());
        entity.setCabelloRubio(dto.isCabelloRubio());
        entity.setCabelloRojo(dto.isCabelloRojo());
        entity.setCabelloAlbino(dto.isCabelloAlbino());
        entity.setCabelloEntrecano(dto.isCabelloEntrecano());
        entity.setCabelloCano(dto.isCabelloCano());
        entity.setFrentePequenia(dto.isFrentePequenia());
        entity.setFrenteMediana(dto.isFrenteMediana());
        entity.setFrenteGrande(dto.isFrenteGrande());
        entity.setCejasAbundantes(dto.isCejasAbundantes());
        entity.setCejasEscasas(dto.isCejasEscasas());
        entity.setCejasRegulares(dto.isCejasRegulares());
        entity.setOjosAzules(dto.isOjosAzules());
        entity.setOjosVerdes(dto.isOjosVerdes());
        entity.setOjosCastClaro(dto.isOjosCastClaro());
        entity.setOjosCastOscuro(dto.isOjosCastOscuro());
        entity.setOjosPardos(dto.isOjosPardos());
        entity.setOjosVerdosos(dto.isOjosVerdosos());
        entity.setOjosNegros(dto.isOjosNegros());
        entity.setNarizConvexa(dto.isNarizConvexa());
        entity.setNarizConcava(dto.isNarizConcava());
        entity.setNarizRectilinea(dto.isNarizRectilinea());
        entity.setBocaPequenia(dto.isBocaPequenia());
        entity.setBocaRegular(dto.isBocaRegular());
        entity.setBocaGrande(dto.isBocaGrande());
        entity.setSeniasVisibles(dto.getSeniasVisibles());
        entity.setEstatus("ACTIVO");

        formatoFiliacionRepository.crear(entity);

        return entity.getIdFormatoFiliacion();
    }

}
