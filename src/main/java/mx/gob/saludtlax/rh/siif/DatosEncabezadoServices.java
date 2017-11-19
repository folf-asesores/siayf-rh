
package mx.gob.saludtlax.rh.siif;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;

import mx.gob.saludtlax.rh.persistencia.DatosEncabezadoRepository;
import mx.gob.saludtlax.rh.persistencia.SIIFEncabezadoRepository;
import mx.gob.saludtlax.rh.siif.layout.SIIFEncabezadoDTO;
import mx.gob.saludtlax.rh.util.Configuracion;

public class DatosEncabezadoServices implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -591511725948944727L;
    /**
     *
     */
    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;
    @Inject
    private DatosEncabezadoRepository datosEncabezadoRepository;
    @Inject
    private SIIFEncabezadoRepository siifEncabezadoRepository;

    protected List<ConsultaDatosEncabezadoDTO> obtenerListaDatosEncabezado(Integer idEncabezado) {
        List<ConsultaDatosEncabezadoDTO> listaDatosEncabezadoDTOs = datosEncabezadoRepository.obtenerListaDatosEncabezado(idEncabezado);
        return listaDatosEncabezadoDTOs;
    }

    protected ConsultaDatosEncabezadoDTO obtenerDatosEncabezadoPorId(Integer idDatoPersonal) {

        return datosEncabezadoRepository.obtenerDatosEncabezadoPorId(idDatoPersonal);
    }
    //
    //	protected List<RevisarChequesDTO> obtenerListaRevisarChequesAvanzada() {
    //		List<RevisarChequesDTO> listaRevisarChequesDTOs = new ArrayList<>();
    //		listaRevisarChequesDTOs = revisarChequesRepository.obtenerListaRevisarChequesAvanzada();
    //		return listaRevisarChequesDTOs;
    //	}

    protected List<ConsultaDatosEncabezadoDTO> obtenerListaDatosEncabezadoProspera(Integer idSiifEncabezado) {
        List<ConsultaDatosEncabezadoDTO> listaDatosEncabezadoDTOs = datosEncabezadoRepository.obtenerListaDatosEncabezadoProspera(idSiifEncabezado);
        return listaDatosEncabezadoDTOs;
    }

    protected List<ConsultaDatosEncabezadoDTO> obtenerListaDatosEncabezadoContrato(Integer idSiifEncabezado) {
        List<ConsultaDatosEncabezadoDTO> listaDatosEncabezadoDTOs = datosEncabezadoRepository.obtenerListaDatosEncabezadoContrato(idSiifEncabezado);
        return listaDatosEncabezadoDTOs;
    }

    public Integer obtenerCuentaBancaria(Integer idSiifEncabezado) {
        return siifEncabezadoRepository.obtenerCuentaBancaria(idSiifEncabezado);
    }

    public SIIFEncabezadoDTO actualizarCheques(SIIFEncabezadoDTO encabezadoDTO, Integer qna) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_actualizar_cheques_siif(:idSiifEncabezdo,:qna)")
                .setParameter("idSiifEncabezdo", encabezadoDTO.getIdSIIFEncabezado()).setParameter("qna", qna);
        query.executeUpdate();
        return null;
    }

}
