
package mx.gob.saludtlax.rh.siif;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.FuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.SubfuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.persistencia.SiifLaboralesSubfuentesEntity;
import mx.gob.saludtlax.rh.persistencia.SiifLaboralesSubfuentesRepository;
import mx.gob.saludtlax.rh.util.Configuracion;

@Stateless
public class SiifLaboralesSubfuentesService {
    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    @Inject
    private SiifLaboralesSubfuentesRepository DAO;

    public List<SiifLaboralesSubfuentesDTO> listaSiifLaboralesSubfuentes() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(
                "SELECT id_siif_laborales_subfuentes AS idSiifLaboralesSubfuentes, "
                        + "id_fuente_financiamiento AS idFuenteFinanciamiento, "
                        + "id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
                        + "id_siif_datos_laborales AS idSiifDatosLaborales "
                        + "FROM siif_laborales_subfuentes");
        query.setResultTransformer(
                Transformers.aliasToBean(SiifLaboralesSubfuentesDTO.class));
        @SuppressWarnings("unchecked")
        List<SiifLaboralesSubfuentesDTO> result = query.list();
        return result;
    }

    public SiifLaboralesSubfuentesDTO obtenerSiifLaboralesSubfuentesPorId(
            Integer idSiifLaboralesSubfuentes) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(
                "SELECT id_siif_laborales_subfuentes AS idSiifLaboralesSubfuentes, "
                        + "id_fuente_financiamiento AS idFuenteFinanciamiento, "
                        + "id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
                        + "id_siif_datos_laborales AS idSiifDatosLaborales "
                        + "FROM siif_laborales_subfuentes WHERE id_siif_laborales_subfuentes = :idSiifLaboralesSubfuentes")
                .setParameter("idSiifLaboralesSubfuentes",
                        idSiifLaboralesSubfuentes);
        query.setResultTransformer(
                Transformers.aliasToBean(SiifLaboralesSubfuentesDTO.class));
        SiifLaboralesSubfuentesDTO result = (SiifLaboralesSubfuentesDTO) query
                .list().get(0);
        return result;
    }

    public List<SiifLaboralesSubfuentesDTO> obtenerSiifLaboralesSubfuentesPorIdDatos(
            Integer idSiifDatosLaborales) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(
                "SELECT id_siif_laborales_subfuentes AS idSiifLaboralesSubfuentes, "
                        + "id_fuente_financiamiento AS idFuenteFinanciamiento, "
                        + "id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
                        + "id_siif_datos_laborales AS idSiifDatosLaborales "
                        + "FROM siif_laborales_subfuentes WHERE id_siif_datos_laborales = :idSiifDatosLaborales")
                .setParameter("idSiifDatosLaborales", idSiifDatosLaborales);
        query.setResultTransformer(
                Transformers.aliasToBean(SiifLaboralesSubfuentesDTO.class));
        @SuppressWarnings("unchecked")
        List<SiifLaboralesSubfuentesDTO> result = query.list();
        return result;
    }

    //	<<<CLAE para Estructura Nomina Datos(Creacion-Lectura-Actualizacion-Eliminacion)>>>

    public SiifLaboralesSubfuentesDTO nuevasSubfuentes() {
        SiifLaboralesSubfuentesDTO dto = new SiifLaboralesSubfuentesDTO();
        dto.setIdFuenteFinanciamiento(null);
        dto.setIdSubfuenteFinanciamiento(null);
        return dto;
    }

    public SiifLaboralesSubfuentesDTO crearSubfuentes(
            SiifLaboralesSubfuentesDTO dto) {
        SiifLaboralesSubfuentesEntity entity = new SiifLaboralesSubfuentesEntity();
        entity.setIdFuenteFinanciamiento(1);
        entity.setIdSubfuenteFinanciamiento(1);
        //		entity.setIdSiifDatosLaborales(Se necesita traer el dato del id de la consulta padre);
        DAO.crear(entity);
        return obtenerSiifLaboralesSubfuentesPorId(
                entity.getIdSiifLaboralesSubfuentes());
    }

    public void actualizarDatos(SiifLaboralesSubfuentesDTO dto, Integer idDL) {
        for (SiifLaboralesSubfuentesDTO subfuente : obtenerSiifLaboralesSubfuentesPorIdDatos(
                idDL)) {
            SiifLaboralesSubfuentesEntity entity = DAO
                    .obtenerPorId(dto.getIdSiifLaboralesSubfuentes());
            entity.setIdFuenteFinanciamiento(
                    subfuente.getIdFuenteFinanciamiento());
            entity.setIdSubfuenteFinanciamiento(
                    subfuente.getIdSubfuenteFinanciamiento());
            //		entity.setIdSiifDatosLaborales(Se necesita traer el dato del id de la consulta);
            DAO.actualizar(entity);
        }
    }

    public void eliminarSiifLAborlaesSubfuente(SiifLaboralesSubfuentesDTO dto) {
        SiifLaboralesSubfuentesEntity entity = entityManager.find(
                SiifLaboralesSubfuentesEntity.class,
                dto.getIdSiifLaboralesSubfuentes());
        entityManager.remove(entity);
    }

    //	>>>>Otras Tablas<<<<

    public List<FuenteFinanciamientoDTO> listaFuenteFinanciamiento() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(
                "SELECT id_fuente_financiamiento AS idFuenteFinanciamiento, "
                        + "descripcion AS descripcion "
                        + "FROM fuentes_financiamientos");
        query.setResultTransformer(
                Transformers.aliasToBean(FuenteFinanciamientoDTO.class));
        @SuppressWarnings("unchecked")
        List<FuenteFinanciamientoDTO> result = query.list();
        return result;
    }

    public List<SubfuenteFinanciamientoDTO> listaSubfuenteFinanciamiento() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(
                "SELECT id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
                        + "descripcion AS descripcion "
                        + "FROM subfuentes_financiamientos");
        query.setResultTransformer(
                Transformers.aliasToBean(SubfuenteFinanciamientoDTO.class));
        @SuppressWarnings("unchecked")
        List<SubfuenteFinanciamientoDTO> result = query.list();
        return result;
    }

    public List<SubfuenteFinanciamientoDTO> listaSubfuenteFinanciamientoPorIdFF(
            Integer idFuenteFinanciamiento) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(
                "SELECT id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
                        + "id_fuente_financiamiento AS idFuenteFinanciamiento, "
                        + "descripcion AS descripcion "
                        + "FROM subfuentes_financiamientos WHERE id_fuente_financiamiento =:idFuenteFinanciamiento")
                .setParameter("idFuenteFiananciamiento",
                        idFuenteFinanciamiento);
        query.setResultTransformer(
                Transformers.aliasToBean(SubfuenteFinanciamientoDTO.class));
        @SuppressWarnings("unchecked")
        List<SubfuenteFinanciamientoDTO> result = query.list();
        return result;
    }
}