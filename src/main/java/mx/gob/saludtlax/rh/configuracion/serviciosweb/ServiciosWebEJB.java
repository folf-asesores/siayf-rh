package mx.gob.saludtlax.rh.configuracion.serviciosweb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSRepository;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@Stateless
public class ServiciosWebEJB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8448867127855413320L;

	@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	protected EntityManager entityManager;

	@Inject
	ServiciosRSRepository serviciosRSRepository;

	public List<ServiciosRSEntity> obtenerListadoServicios() {

		return serviciosRSRepository.obtenerListadoServicios();
	}

	public void guardarInformacionServicio(ServiciosRSEntity servicioRSEntity) throws ServicioWebException {

		if (servicioRSEntity.isActivo()) {
			if (serviciosRSRepository.existeServicioActivo(servicioRSEntity.getServicio())) {
				throw new ServicioWebException("Ya existe un servicio activo");
			}

		}
		entityManager.persist(servicioRSEntity);

	}

	public void actualizarInformacionServicio(ServiciosRSEntity servicioRSEntity) throws ServicioWebException {

		ServiciosRSEntity servicioRSEntityDB = getServicioRSEntity(servicioRSEntity.getIdServicioRS());

		if (servicioRSEntity.isActivo()) {
			if (serviciosRSRepository.existeServicioActivo(servicioRSEntity.getServicio(),
					servicioRSEntity.getIdServicioRS())) {
				throw new ServicioWebException("Ya existe un servicio activo");
			}

		}

		servicioRSEntityDB.setActivo(servicioRSEntity.isActivo());
		if (servicioRSEntity.getClave() != null) {
			servicioRSEntityDB.setClave(servicioRSEntity.getClave());
		}
		servicioRSEntityDB.setContexto(servicioRSEntity.getContexto());
		servicioRSEntityDB.setProduccion(servicioRSEntity.isProduccion());
		servicioRSEntityDB.setPuerto(servicioRSEntity.getPuerto());
		servicioRSEntityDB.setServicio(servicioRSEntity.getServicio());
		servicioRSEntityDB.setUrl(servicioRSEntity.getUrl());
		servicioRSEntityDB.setUsuario(servicioRSEntity.getUsuario());
		entityManager.persist(servicioRSEntityDB);

	}

	public ServiciosRSEntity getServicioRSEntity(Integer id) {
		return entityManager.find(ServiciosRSEntity.class, id);
	}

	/**
	 * Regresa los datos del servicio activo
	 * 
	 * @param servicio
	 * @return
	 * @throws ServicioWebException
	 */
	public ServiciosRSEntity getServicioActivo(ServicioWebEnum servicio) throws ServicioWebException {

		ServiciosRSEntity servicioRSEntity = serviciosRSRepository.getDatosServicioActivo(servicio);

		if (servicioRSEntity == null) {
			throw new ServicioWebException(
					"No hay servicio activo registrado en la base de datos,configure un servicio como activo para que el modulo funcione adecuadamente");
		}

		return servicioRSEntity;

	}

}
