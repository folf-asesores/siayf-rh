package mx.gob.saludtlax.rh.acciones;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.persistencia.AccionesRepository;
import mx.gob.saludtlax.rh.areas.AreaDTO;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccion;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;
import mx.gob.saludtlax.rh.persistencia.AccionesEntity;
import mx.gob.saludtlax.rh.persistencia.AreasRepository;
import mx.gob.saludtlax.rh.persistencia.ModuloRepository;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

public class AccionService {

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    @Inject
    private AccionesRepository accionesRepository;

    @Inject
    private ModuloRepository moduloRepository;

    @Inject
    private AreasRepository areasRepository;

    @Inject
    private ConfiguracionModuloAccion configuracionModuloAccion;

    public void crearAccion(AccionDTO accionDTO) {
        String contexto = "Registrar Acción: ";

        AccionesEntity accionesEntity = new AccionesEntity();

        accionesEntity.setClave(accionDTO.getClave());
        accionesEntity.setDescripcion(accionDTO.getDescripcion());

        if (!ValidacionUtil.esNumeroPositivoInt(accionDTO.getIdArea())) {
            throw new ValidacionException(contexto + "Selecione el area.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esNumeroPositivoInt(accionDTO.getIdModulo())) {

            accionesEntity.setModulo(moduloRepository.obtenerPorId(accionDTO.getIdModulo()));

        }

        accionesEntity.setArea(areasRepository.obtenerPorId(accionDTO.getIdArea()));

        accionesRepository.crear(accionesEntity);
    }

    public void editarAccion(AccionDTO accionDTO) {
        AccionesEntity accionesEntity = accionesRepository.obtenerPorId(accionDTO.getIdAccion());

        accionesEntity.setArea(areasRepository.obtenerPorId(accionDTO.getIdArea()));
        accionesEntity.setClave(accionDTO.getClave());
        accionesEntity.setDescripcion(accionDTO.getDescripcion());

        accionesRepository.actualizar(accionesEntity);
    }

    protected List<AccionDTO> obtenerAcciones() {
        List<AccionDTO> listDto = new ArrayList<>();

        List<AccionesEntity> listEntity = accionesRepository.consultarTodos();

        for (AccionesEntity a : listEntity) {
            AccionDTO aDto = new AccionDTO();
            aDto.setClave(a.getClave());
            aDto.setDescripcion(a.getDescripcion());
            aDto.setIdAccion(a.getIdAccion());
            aDto.setIdArea(a.getArea().getIdArea());
            aDto.setNombreArea(a.getArea().getNombreArea());

            listDto.add(aDto);
        }

        return listDto;

    }

    public List<AccionDTO> obtenerAccionesPorArea(Integer idArea) {
        List<AccionDTO> listDto = new ArrayList<>();
        try {
            List<AccionesEntity> listEntity = accionesRepository.obtenerListaAccionesPorIdArea(idArea);

            for (AccionesEntity a : listEntity) {
                AccionDTO aDto = new AccionDTO();
                aDto.setClave(a.getClave());
                aDto.setDescripcion(a.getDescripcion());
                aDto.setIdAccion(a.getIdAccion());
                aDto.setIdArea(a.getArea().getIdArea());
                aDto.setNombreArea(a.getArea().getNombreArea());

                listDto.add(aDto);
            }

            return listDto;
        } catch (NoResultException e) {
            return null;
        }

    }

    public List<AccionDTO> obtenerAccionesPorModulo(Integer idModulo) {
        List<AccionDTO> listDto = new ArrayList<>();
        try {
            List<AccionesEntity> listEntity = accionesRepository.obtenerListaAccionesPorIdModulo(idModulo);

            for (AccionesEntity a : listEntity) {
                AccionDTO aDto = new AccionDTO();
                aDto.setClave(a.getClave());
                aDto.setDescripcion(a.getDescripcion());
                aDto.setIdAccion(a.getIdAccion());
                aDto.setIdArea(a.getArea().getIdArea());
                aDto.setNombreArea(a.getArea().getNombreArea());

                if (a.getModulo() != null) {
                    aDto.setIdModulo(a.getModulo().getIdModulo());
                }

                listDto.add(aDto);
            }

            return listDto;
        } catch (NoResultException e) {
            return null;
        }

    }

    public boolean eliminarAccion(Integer idAccion) {
        List<ConfiguracionModuloAccionDTO> listConf = configuracionModuloAccion.obtenerListaConfiguracionModuloAccionDTOPorAccion(idAccion);
        if (listConf == null || listConf.isEmpty()) {
            accionesRepository.eliminarPorId(idAccion);
            return true;
        } else {
            return false;
        }
    }

    public List<AreaDTO> listaArea() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session
                .createSQLQuery("SELECT " + "id_area AS idArea, " + "nombre_area AS nombreArea " + "FROM areas");
        query.setResultTransformer(Transformers.aliasToBean(AreaDTO.class));
        @SuppressWarnings("unchecked")
        List<AreaDTO> result = (List<AreaDTO>) query.list();
        return result;
    }

    public List<AccionDTO> obtenerAccionesFiltradas(Integer idModulo, List<Integer> idAccionFiltro) {

        List<AccionDTO> listDto = new ArrayList<>();
        try {
            List<AccionesEntity> listEntity = accionesRepository.obtenerListaAccionesPorIdModulo(idModulo);

            for (Integer idAcc : idAccionFiltro) {
                for (int i = 0; i < listEntity.size(); i++) {
                    if (idAcc.compareTo(listEntity.get(i).getIdAccion()) == 0) {
                        listEntity.remove(i);
                    }

                }
            }

            for (AccionesEntity a : listEntity) {

                AccionDTO aDto = new AccionDTO();
                aDto.setClave(a.getClave());
                aDto.setDescripcion(a.getDescripcion());
                aDto.setIdAccion(a.getIdAccion());
                aDto.setIdArea(a.getArea().getIdArea());
                aDto.setNombreArea(a.getArea().getNombreArea());

                listDto.add(aDto);
            }

            return listDto;
        } catch (NoResultException e) {
            return null;
        }

    }
}
