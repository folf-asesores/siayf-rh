
package mx.gob.saludtlax.rh.configuracion.nombramiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosRepository;
import mx.gob.saludtlax.rh.util.Configuracion;

@Stateless
public class TipoNombramientoService {
    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    @Inject
    private TiposNombramientosRepository tipoNombramientoRepository;

    public List<TipoNombramientoDTO> listaNombramiento() {
        List<TiposNombramientosEntity> nombramientos = tipoNombramientoRepository
                .consultarTodos();
        List<TipoNombramientoDTO> lista = new ArrayList<>();
        if (!nombramientos.isEmpty()) {
            for (TiposNombramientosEntity entity : nombramientos) {
                TipoNombramientoDTO dto = new TipoNombramientoDTO();
                dto.setClave(entity.getClave());
                dto.setDescripcion(entity.getDescripcion());
                dto.setIdPoder(entity.getIdPoder());
                dto.setIdTipoNombramiento(entity.getIdTipoNombramiento());
                dto.setNombramiento(entity.getNombramiento());
                lista.add(dto);
            }
        }

        return lista;
    }

    public TipoNombramientoDTO obtenerNombramientoPorId(
            Integer idNombramiento) {
        TiposNombramientosEntity tipoNombramiento = tipoNombramientoRepository
                .nombramientoPorId(idNombramiento);
        if (tipoNombramiento == null) {
            throw new ValidacionException("El nombramiento con identificador "
                    + idNombramiento
                    + " no está registrado en el catalogo de nombramientos.",
                    ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
        }
        TipoNombramientoDTO dto = new TipoNombramientoDTO();
        dto.setClave(tipoNombramiento.getClave());
        dto.setDescripcion(tipoNombramiento.getDescripcion());
        dto.setIdPoder(tipoNombramiento.getIdPoder());
        dto.setIdTipoNombramiento(tipoNombramiento.getIdTipoNombramiento());
        dto.setNombramiento(tipoNombramiento.getNombramiento());
        return dto;
    }

}