/*
 * FirmaEJB.java
 * Creado el 15/Nov/2016 4:56:40 PM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.firma;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class FirmaEJB implements Firma {

    @Inject private FirmaService firmaService;
    
    @Override
    public Integer crear(FirmaDTO firma) {
        if(firma == null) {
            throw new ValidacionException("No se posible guardar información nula.", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        
        if(ValidacionUtil.esMenorQueUno(firma.getIdAdscripcion())) {
            throw new ValidacionException("El ID de la adscripción no debe ser nulo o negativo.", ValidacionCodigoError.NUMERO_NEGATIVO);
        }
        
        if(ValidacionUtil.esCadenaVacia(firma.getNombre())) {
            throw new ValidacionException("El nombre del director no puede estar vacio..", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        
        return firmaService.crear(firma);
    }

    @Override
    public FirmaDTO obtenerPorId(Integer idFirma) {
        if(ValidacionUtil.esMenorQueUno(idFirma)) {
            throw new ValidacionException("El ID de la firma no debe ser nulo o negativo.", ValidacionCodigoError.NUMERO_NEGATIVO);
        }
        
        return firmaService.obtenerPorId(idFirma);
    }

    @Override
    public List<FirmaDTO> consultarTodas() {
        return firmaService.consultarTodas();
    }

    @Override
    public void actualizar(FirmaDTO firma) {
        if(firma == null) {
            throw new ValidacionException("No se posible actulizar información nula.", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        
        if(ValidacionUtil.esMenorQueUno(firma.getIdAdscripcion())) {
            throw new ValidacionException("El ID de la adscripción no debe ser nulo o negativo.", ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        if(ValidacionUtil.esCadenaVacia(firma.getNombre())) {
            throw new ValidacionException("El nombre del director no puede estar vacio..", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        firmaService.actualizar(firma);
    }

    @Override
    public void eliminar(Integer idFirma) {
        if(ValidacionUtil.esMenorQueUno(idFirma)) {
            throw new ValidacionException("No se posible eliminar una firma si el id nulo o es menor que uno.", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        
        firmaService.eliminar(idFirma);
    }

}
