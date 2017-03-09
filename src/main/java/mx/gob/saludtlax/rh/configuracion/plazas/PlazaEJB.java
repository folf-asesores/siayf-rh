package mx.gob.saludtlax.rh.configuracion.plazas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.transaction.UserTransaction;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.persistencia.PlazaEntity;
import mx.gob.saludtlax.rh.persistencia.PlazaRepository;

@Stateless
public class PlazaEJB {

	@Inject
	private PlazaRepository plazaQuery;

	@Resource
	EJBContext context;

	public void registrarPlaza(PlazaDTO plaza) {
		UserTransaction utx = context.getUserTransaction();
		try {
			utx.begin();
			PlazaEntity plazaEntity = new PlazaEntity();
			plazaEntity.setClave(plaza.getClave());
			plazaEntity.setNombrePlaza(plaza.getNombrePlaza());
			plazaEntity.setAdscripcion(plaza.getAdscripcion());
			plazaEntity.setTipo(plaza.getTipo());
			plazaEntity.setCondicionLaboral(plaza.getCondicionLaboral());
			plazaQuery.registrarPlaza(plazaEntity);
			utx.commit();
		} catch (PersistenceException ex) {
			throw new BusinessException("Error al registrar una plaza");
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			ex.printStackTrace();
		}
	}

	public void actualizarPlaza(PlazaDTO plaza) {
		UserTransaction utx = context.getUserTransaction();
		try {
			utx.begin();
			PlazaEntity plazaEntity = new PlazaEntity();
			plazaEntity.setIdPlaza(plaza.getIdPlaza());
			plazaEntity.setClave(plaza.getClave());
			plazaEntity.setNombrePlaza(plaza.getNombrePlaza());
			plazaEntity.setAdscripcion(plaza.getAdscripcion());
			plazaEntity.setTipo(plaza.getTipo());
			plazaEntity.setCondicionLaboral(plaza.getCondicionLaboral());
			plazaQuery.actualizarPlaza(plazaEntity);
			utx.commit();
		} catch (PersistenceException ex) {
			throw new BusinessException("Erro al actualizar una plaza");
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
	}

	public void eliminarPlaza(Integer idPlaza) {
		UserTransaction utx = context.getUserTransaction();
		try {
			utx.begin();
			plazaQuery.eliminarPlazaPorId(idPlaza);
			utx.commit();
		} catch (NoResultException ex) {
			throw new BusinessException("Erro al eliminar la plaza");
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}

	}

	public List<PlazaDTO> obtenerListaPlazas(String clave) {
		UserTransaction utx = context.getUserTransaction();
		List<PlazaDTO> listaPlazas = new ArrayList<>();
		try {
			utx.begin();
			List<PlazaEntity> plazas = plazaQuery.obtenerListaPlaza(clave);
			for (PlazaEntity lista : plazas) {
				PlazaDTO plazaDTO = new PlazaDTO();
				plazaDTO.setIdPlaza(lista.getIdPlaza());
				plazaDTO.setClave(lista.getClave());
				plazaDTO.setNombrePlaza(lista.getNombrePlaza());
				plazaDTO.setAdscripcion(lista.getAdscripcion());
				plazaDTO.setTipo(lista.getTipo());
				plazaDTO.setCondicionLaboral(lista.getCondicionLaboral());
				listaPlazas.add(plazaDTO);
			}
			utx.commit();
			return listaPlazas;
		} catch (NoResultException ex) {
			throw new BusinessException("Error al obtener la lista de plazas");
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
		return listaPlazas;
	}

	public PlazaDTO obtenerPorId(Integer idPlaza) {
		UserTransaction utx = context.getUserTransaction();
		PlazaDTO plaza = new PlazaDTO();
		try {
			utx.begin();
			PlazaEntity plazaEntity = plazaQuery.obtenerPlazaPorId(idPlaza);
			plaza.setIdPlaza(plazaEntity.getIdPlaza());
			plaza.setClave(plazaEntity.getClave());
			plaza.setNombrePlaza(plazaEntity.getNombrePlaza());
			plaza.setAdscripcion(plazaEntity.getAdscripcion());
			plaza.setTipo(plazaEntity.getTipo());
			plaza.setCondicionLaboral(plazaEntity.getCondicionLaboral());
			utx.commit();
			return plaza;
		} catch (NoResultException ex) {
			throw new BusinessException("Error al obtener la plaza");
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
		return plaza;
	}

	public String buscarClave(String clave) {
		String claveEncontrada = "";
		UserTransaction utx = context.getUserTransaction();
		try {
			utx.begin();
			if (plazaQuery.buscarClave(clave) != "" || plazaQuery.buscarClave(clave) != null) {
				claveEncontrada = plazaQuery.buscarClave(clave);
			}
			utx.commit();
			return claveEncontrada;
		} catch (NoResultException ex) {
			throw new BusinessException("Error");
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
		return claveEncontrada;
	}

}