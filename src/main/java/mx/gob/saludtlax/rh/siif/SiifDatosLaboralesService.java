package mx.gob.saludtlax.rh.siif;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.FuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.SubfuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;
import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsableDTO;
import mx.gob.saludtlax.rh.persistencia.SIIFDatosLaboralesEntity;
import mx.gob.saludtlax.rh.persistencia.SiifDatosLaboralesRepository;
import mx.gob.saludtlax.rh.persistencia.SiifLaboralesSubfuentesRepository;
import mx.gob.saludtlax.rh.siif.layout.DatosLaboralesDTO;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.persistencia.SiifLaboralesSubfuentesEntity;


@Stateless
public class SiifDatosLaboralesService {
		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;
	
	@Inject
	private SiifDatosLaboralesRepository siifDatosLaboralesDAO;
	@Inject
	private SiifLaboralesSubfuentesRepository siifLaboralesSubfuentesDAO;
	
//	<<<<Listas>>>>
	
	public List<SiifDatosLaboralesDTO> listaSiifDatosLaborales() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				  "SELECT id_dato_laboral AS idDatoLaboral, "
				+ "id_empleado_datos_laborales AS idEmpleadoDatosLaborales, "
				+ "id_empleado_datos_personales AS idEmpleadoDatosPersonales, "
				+ "rfc AS rfc, "
				+ "id_rfc AS idRfc, "
				+ "id_plaza AS idPlaza, "
				+ "id_proyecto AS idProyecto, "
				+ "id_dependencia AS idDependencia, "
				+ "id_unidad_responsable AS idUnidadResponsable, "
				+ "nombramiento AS nombramiento, "
				+ "id_puesto AS idPuesto, "
				+ "id_sindicato AS idSindicato, "
				+ "id_habilitado AS idHabilitado, "
				+ "fecha_ingreso AS fechaIngreso, "
				+ "no_quinquenios AS noQuinquenios, "
				+ "sueldo_mensual AS sueldoMensual, "
				+ "percepcion_complementaria AS percepcionComplementaria, "
				+ "despensa AS despensa, "
				+ "incentivo_ahorro AS incentivoAhorro, "
				+ "compensacion AS compensacion, "
				+ "quinquenio AS quinquenio, "
				+ "no_cuenta AS noCuenta, "
				+ "policia AS policia, "
				+ "id_fuente_financiamiento AS idFuenteFinanciamiento, "
				+ "id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
				+ "id_tipo_recurso As idTipoRecurso, "
				+ "id_estado_empleado AS idEstadoEmpleado, "
				+ "id_nomina AS idNomina "				
				+ "FROM siif_datos_laborales");
		query.setResultTransformer(Transformers.aliasToBean(SiifDatosLaboralesDTO.class));
		@SuppressWarnings("unchecked")
		List<SiifDatosLaboralesDTO> result = (List<SiifDatosLaboralesDTO>) query.list();
		return result;
	}
	
	public List<SiifDatosLaboralesListaDTO> listaSiifDatosLaboralesLista() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT * FROM siif_datos_laborales_lista");
		query.setResultTransformer(Transformers.aliasToBean(SiifDatosLaboralesListaDTO.class));
		@SuppressWarnings("unchecked")
		List<SiifDatosLaboralesListaDTO> result = (List<SiifDatosLaboralesListaDTO>) query.list();
		return result;
	}

	public List<SiifDatosLaboralesDTO> listaSiifDatosLaboralesPorCriterio(String rfc) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
						  "SELECT id_dato_laboral AS idDatoLaboral, "
						+ "id_empleado_datos_laborales AS idEmpleadoDatosLaborales, "
						+ "id_empleado_datos_personales AS idEmpleadoDatosPersonales, "
						+ "rfc AS rfc, "
						+ "id_rfc AS idRfc, "
						+ "id_plaza AS idPlaza, "
						+ "id_proyecto AS idProyecto, "
						+ "id_dependencia AS idDependencia, "
						+ "id_unidad_responsable AS idUnidadResponsable, "
						+ "nombramiento AS nombramiento, "
						+ "id_puesto AS idPuesto, "
						+ "id_sindicato AS idSindicato, "
						+ "id_habilitado AS idHabilitado, "
						+ "fecha_ingreso AS fechaIngreso, "
						+ "no_quinquenios AS noQuinquenios, "
						+ "sueldo_mensual AS sueldoMensual, "
						+ "percepcion_complementaria AS percepcionComplementaria, "
						+ "despensa AS despensa, "
						+ "incentivo_ahorro AS incentivoAhorro, "
						+ "compensacion AS compensacion, "
						+ "quinquenio AS quinquenio, "
						+ "no_cuenta AS noCuenta, "
						+ "policia AS policia, "
						+ "id_fuente_financiamiento AS idFuenteFinanciamiento, "
						+ "id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
						+ "id_tipo_recurso As idTipoRecurso, "
						+ "id_estado_empleado AS idEstadoEmpleado, "
						+ "id_nomina AS idNomina "				
						+ "FROM siif_datos_laborales WHERE rfc LIKE :rfc")
				.setParameter("rfc", "%" + rfc + "%");
		query.setResultTransformer(Transformers.aliasToBean(SiifDatosLaboralesDTO.class));
		@SuppressWarnings("unchecked")
		List<SiifDatosLaboralesDTO> result = (List<SiifDatosLaboralesDTO>) query.list();
		return result;
	}
	
	public List<SiifDatosLaboralesListaDTO> listaSiifDatosLaboralesListaPorCriterio(String rfc) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT * FROM siif_datos_laborales_lista WHERE rfc = :rfc").setParameter("rfc", rfc);
		query.setResultTransformer(Transformers.aliasToBean(SiifDatosLaboralesListaDTO.class));
		@SuppressWarnings("unchecked")
		List<SiifDatosLaboralesListaDTO> result = (List<SiifDatosLaboralesListaDTO>) query.list();
		return result;
	}
	
	public SiifDatosLaboralesDTO obtenerSiifDatosLaboralesPorId(Integer idDatoLaboral) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_dato_laboral AS idDatoLaboral, "
						+ "id_empleado_datos_laborales AS idEmpleadoDatosLaborales, "
						+ "id_empleado_datos_personales AS idEmpleadoDatosPersonales, "
						+ "rfc AS rfc, "
						+ "id_rfc AS idRfc, "
						+ "id_plaza AS idPlaza, "
						+ "id_proyecto AS idProyecto, "
						+ "id_dependencia AS idDependencia, "
						+ "id_unidad_responsable AS idUnidadResponsable, "
						+ "nombramiento AS nombramiento, "
						+ "id_puesto AS idPuesto, "
						+ "id_sindicato AS idSindicato, "
						+ "id_habilitado AS idHabilitado, "
						+ "fecha_ingreso AS fechaIngreso, "
						+ "no_quinquenios AS noQuinquenios, "
						+ "sueldo_mensual AS sueldoMensual, "
						+ "percepcion_complementaria AS percepcionComplementaria, "
						+ "despensa AS despensa, "
						+ "incentivo_ahorro AS incentivoAhorro, "
						+ "compensacion AS compensacion, "
						+ "quinquenio AS quinquenio, "
						+ "no_cuenta AS noCuenta, "
						+ "policia AS policia, "
						+ "id_fuente_financiamiento AS idFuenteFinanciamiento, "
						+ "id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
						+ "id_tipo_recurso As idTipoRecurso, "
						+ "id_estado_empleado AS idEstadoEmpleado, "
						+ "id_nomina AS idNomina "				
						+ "FROM siif_datos_laborales WHERE id_dato_laboral = :idDatoLaboral")
				.setParameter("idDatoLaboral", idDatoLaboral);
		query.setResultTransformer(Transformers.aliasToBean(SiifDatosLaboralesDTO.class));
		SiifDatosLaboralesDTO result = (SiifDatosLaboralesDTO) query.list().get(0);
		return result;
	}
	
	public List<SiifDatosLaboralesDTO> listaConsultaDatosLaboralesPorIdPersonales(Integer idEmpleadoDatosPersonales) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_dato_laboral AS idDatoLaboral, "
						+ "id_empleado_datos_laborales AS idEmpleadoDatosLaborales, "
						+ "id_empleado_datos_personales AS idEmpleadoDatosPersonales, "
						+ "rfc AS rfc, "
						+ "id_rfc AS idRfc, "
						+ "id_plaza AS idPlaza, "
						+ "id_proyecto AS idProyecto, "
						+ "id_dependencia AS idDependencia, "
						+ "id_unidad_responsable AS idUnidadResponsable, "
						+ "nombramiento AS nombramiento, "
						+ "id_puesto AS idPuesto, "
						+ "id_sindicato AS idSindicato, "
						+ "id_habilitado AS idHabilitado, "
						+ "fecha_ingreso AS fechaIngreso, "
						+ "no_quinquenios AS noQuinquenios, "
						+ "sueldo_mensual AS sueldoMensual, "
						+ "percepcion_complementaria AS percepcionComplementaria, "
						+ "despensa AS despensa, "
						+ "incentivo_ahorro AS incentivoAhorro, "
						+ "compensacion AS compensacion, "
						+ "quinquenio AS quinquenio, "
						+ "no_cuenta AS noCuenta, "
						+ "policia AS policia, "
						+ "id_fuente_financiamiento AS idFuenteFinanciamiento, "
						+ "id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
						+ "id_tipo_recurso As idTipoRecurso, "
						+ "id_estado_empleado AS idEstadoEmpleado, "
						+ "id_nomina AS idNomina "				
						+ "FROM siif_datos_laborales WHERE id_empleado_datos_personales = :idEmpleadoDatosPersonales")
				.setParameter("idEmpleadoDatosPersonales",idEmpleadoDatosPersonales);
		query.setResultTransformer(Transformers.aliasToBean(SiifDatosLaboralesDTO.class));
		@SuppressWarnings("unchecked")
		List<SiifDatosLaboralesDTO> result = (List<SiifDatosLaboralesDTO>) query.list();
		return result;
	}
	
	public int verificaIdDatoLaboralPorId(Integer idDatoLaboral) {
        Session session = entityManager.unwrap(Session.class);
        SQLQuery query = (SQLQuery) session.createSQLQuery(
        		"SELECT id_empleado_datos_laborales AS idEmpleadoDatosLaborales FROM siif_datos_laborales "
                + " where id_empleado_datos_personales=:idDatoLaboral")
                .setParameter("idDatoLaboral", idDatoLaboral);
        query.setResultTransformer(Transformers
                .aliasToBean(DatosLaboralesDTO.class));
        DatosLaboralesDTO result;
        if (query.list().size() > 0) {
            result = (DatosLaboralesDTO) query.list().get(0);
            //System.out.println("ID PERSONAL ENCONTRADO:::" + result.getIdEmpleadoDatosPersonales());
            return 1;
        } else {
            //System.out.println("ID PERSONAL NO FUE ENCONTRADO:::");
            return 0;
        }
    }

	
	public List<SiifLaboralesSubfuentesDTO> listaSiifLaboralesSubfuentes() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				  "SELECT id_siif_laborales_subfuentes AS idSiifLaboralesSubfuentes, "
				+ "id_fuente_financiamiento AS idFuenteFinanciamiento, "
				+ "id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "				
				+ "id_siif_datos_laborales AS idSiifDatosLaborales "				
				+ "FROM siif_laborales_subfuentes");
		query.setResultTransformer(Transformers.aliasToBean(SiifLaboralesSubfuentesDTO.class));
		@SuppressWarnings("unchecked")
		List<SiifLaboralesSubfuentesDTO> result = (List<SiifLaboralesSubfuentesDTO>) query.list();
		return result;
	}
	
	public SiifLaboralesSubfuentesDTO obtenerSiifLaboralesSubfuentesPorId(Integer idSiifLaboralesSubfuentes) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_siif_laborales_subfuentes AS idSiifLaboralesSubfuentes, "
						+ "id_fuente_financiamiento AS idFuenteFinanciamiento, "
						+ "id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "				
						+ "id_siif_datos_laborales AS idSiifDatosLaborales "
						+ "FROM siif_laborales_subfuentes WHERE id_siif_laborales_subfuentes = :idSiifLaboralesSubfuentes")
				.setParameter("idSiifLaboralesSubfuentes", idSiifLaboralesSubfuentes);
		query.setResultTransformer(Transformers.aliasToBean(SiifLaboralesSubfuentesDTO.class));
		SiifLaboralesSubfuentesDTO result = (SiifLaboralesSubfuentesDTO) query.list().get(0);
		return result;
	}
	
	public List<SiifLaboralesSubfuentesDTO> obtenerSiifLaboralesSubfuentesPorIdDatos(Integer idSiifDatosLaborales) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_siif_laborales_subfuentes AS idSiifLaboralesSubfuentes, "
						+ "id_fuente_financiamiento AS idFuenteFinanciamiento, "
						+ "id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "				
						+ "id_siif_datos_laborales AS idSiifDatosLaborales "
						+ "FROM siif_laborales_subfuentes WHERE id_siif_datos_laborales = :idSiifDatosLaborales")
				.setParameter("idSiifDatosLaborales", idSiifDatosLaborales);
		query.setResultTransformer(Transformers.aliasToBean(SiifLaboralesSubfuentesDTO.class));
		@SuppressWarnings("unchecked")
		List<SiifLaboralesSubfuentesDTO> result = (List<SiifLaboralesSubfuentesDTO>) query.list();
		return result;
	}
	
//	>>>Operaciones Automaticas<<<
	
	public Integer asignarID (String rfc) {
//		try{
			Integer r = entityManager.createQuery("SELECT MAX (idEmpleadoDatosLaborales) "
					+ "FROM SIIFDatosLaboralesEntity WHERE rfc=:rfc",Integer.class)
					.setParameter("rfc", rfc).getSingleResult();		
			if (r==null){
				return siguienteIDE(rfc);
			}
			return r;
//		}catch(NoResultException ex){
//			throw new BusinessException();
//		}
	}
	
	public Integer siguienteIDE (String rfc){
		Integer t = new Integer(0);
		String s = entityManager.createQuery("SELECT nombramiento "
				+ "FROM SIIFDatosLaboralesEntity WHERE rfc=:rfc",String.class)
				.setParameter("rfc", rfc).getSingleResult();
		if(s=="E"){
			Integer p = entityManager.createQuery("SELECT MAX(idEmpleadoDatosLaborales) "
					+ "FROM siif_datos_laborales "
					+ "WHERE idEmpleadoDatosLaborales > 10000",Integer.class).getSingleResult();	
				t=p+1;
			return t;
		}else{
			Integer p = entityManager.createQuery("SELECT MAX(idEmpleadoDatosLaborales) "
					+ "FROM SIIFDatosLaboralesEntity "
					+ "WHERE idEmpleadoDatosLaborales < 10000",Integer.class).getSingleResult();
				t=p+1;
			return t;
		}
	}
	
	public Integer siguienteID (String rfc) {
		Integer p = entityManager.createQuery("SELECT MAX(idRfc) "
				+ "FROM SIIFDatosLaboralesEntity WHERE rfc = :rfc",Integer.class)
				.setParameter("rfc", rfc).getSingleResult();		
		return (p==null)?0:p+1;
	}
	
//	<<<Variables>>>
	
	BigDecimal nul0 = new BigDecimal("0.0");
	private Integer ic;
	private Integer y;
	private Integer z;
	
	public Integer getY() {return y;}
	public void setY(Integer y) {this.y = y;}

	public Integer getZ() {return z;}
	public void setZ(Integer z) {this.z = z;}
	
	public Integer getIc() {return ic;}
	public void setIc(Integer ic) {this.ic = ic;}
	
//	<<<CLAE (Creacion-Lectura-Actualizacion-Eliminacion)>>>

	public SiifDatosLaboralesDTO nuevosDatosLaborales(String rfc) {
//		try{
			SiifDatosLaboralesDTO  dto = new SiifDatosLaboralesDTO();
			dto.setRfc(rfc);
			dto.setIdPlaza(null);
			dto.setIdProyecto(null);
			dto.setIdDependencia(null);
			dto.setIdUnidadResponsable(null);
			dto.setNombramiento(null);
			dto.setIdPuesto(null);
			dto.setIdSindicato(null);
			dto.setIdHabilitado(null);
			dto.setFechaIngreso(null);
			dto.setNoQuinquenios(0);
			dto.setSueldoMensual(nul0);
			dto.setPercepcionComplementaria(nul0);
			dto.setDespensa(nul0);
			dto.setIncentivoAhorro(nul0);
			dto.setCompensacion(nul0);
			dto.setQuinquenio(nul0);
			dto.setNoCuenta(null);
			dto.setPolicia(null);
			dto.setIdFuenteFinanciamiento(null);
			dto.setIdSubfuenteFinanciamiento(null);
			dto.setIdTipoRecurso(3);
			dto.setIdEstadoEmpleado("A");
			dto.setIdNomina(0);
			return dto;
//		}catch(BusinessException ex){
//			throw new BusinessException();
//		}
	}

	public SiifDatosLaboralesDTO crearDatosLaborales(SiifDatosLaboralesDTO dto, String rfc) {
//	try{
		SIIFDatosLaboralesEntity entity = new SIIFDatosLaboralesEntity();
//		y=siguienteID(rfc);
//		z=asignarID(rfc);
		entity.setIdEmpleadoDatosLaborales(dto.getIdEmpleadoDatosLaborales());
		entity.setIdEmpleadoDatosPersonales(dto.getIdEmpleadoDatosPersonales());
		entity.setRfc(dto.getRfc());
		entity.setIdRfc(dto.getIdRfc());	
		entity.setIdPlaza(dto.getIdPlaza());
		entity.setIdProyecto(dto.getIdProyecto());
		entity.setIdDependencia(dto.getIdDependencia());
		entity.setIdUnidadResponsable(dto.getIdUnidadResponsable());
		entity.setNombramiento(dto.getNombramiento());
		entity.setIdPuesto(dto.getIdPuesto());
		entity.setIdSindicato(dto.getIdSindicato());
		entity.setIdHabilitado(dto.getIdHabilitado());
		entity.setFechaIngreso(dto.getFechaIngreso());
		entity.setNoQuinquenios(dto.getNoQuinquenios());
		entity.setSueldoMensual(dto.getSueldoMensual());
		entity.setPercepcionComplementaria(dto.getPercepcionComplementaria());
		entity.setDespensa(dto.getDespensa());
		entity.setIncentivoAhorro(dto.getIncentivoAhorro());
		entity.setCompensacion(dto.getCompensacion());
		entity.setQuinquenio(dto.getQuinquenio());
		entity.setNoCuenta(dto.getNoCuenta());
		entity.setPolicia(0);
		entity.setIdFuenteFinanciamiento(dto.getIdFuenteFinanciamiento());
		entity.setIdSubfuenteFinanciamiento(dto.getIdSubfuenteFinanciamiento());
		entity.setIdTipoRecurso(dto.getIdTipoRecurso());
		entity.setIdEstadoEmpleado(dto.getIdEstadoEmpleado());
		entity.setIdNomina(0);
		siifDatosLaboralesDAO.crear(entity);
		return obtenerSiifDatosLaboralesPorId(entity.getIdDatoLaboral());
//	}catch(BusinessException ex){
//		throw new BusinessException();
//		}
	}

	public SiifDatosLaboralesDTO actualizarDatosLaborales(SiifDatosLaboralesDTO dto) {
		SIIFDatosLaboralesEntity entity = siifDatosLaboralesDAO.obtenerPorId(dto.getIdDatoLaboral());
		//consulta Maximo si son contratos aplicarlos despues de 10,000 +1  si  son base antes de 10,000 + 1
		entity.setIdEmpleadoDatosLaborales(dto.getIdEmpleadoDatosLaborales());
		//igual al resultante de la consulta de arriba
		entity.setIdEmpleadoDatosPersonales(dto.getIdEmpleadoDatosPersonales());
		entity.setRfc(dto.getRfc());
		//si es el primer registro entonces es 0, si no busca el maximo +1
		entity.setIdRfc(dto.getIdRfc());	
		entity.setIdPlaza(dto.getIdPlaza());
		entity.setIdProyecto(dto.getIdProyecto());
		entity.setIdDependencia(dto.getIdDependencia());
		entity.setIdUnidadResponsable(dto.getIdUnidadResponsable());
		entity.setNombramiento(dto.getNombramiento());
		entity.setIdPuesto(dto.getIdPuesto());
		entity.setIdSindicato(dto.getIdSindicato());
		entity.setIdHabilitado(dto.getIdHabilitado());
		entity.setFechaIngreso(dto.getFechaIngreso());
		entity.setNoQuinquenios(dto.getNoQuinquenios());
		entity.setSueldoMensual(dto.getSueldoMensual());
		entity.setPercepcionComplementaria(dto.getPercepcionComplementaria());
		entity.setDespensa(dto.getDespensa());
		entity.setIncentivoAhorro(dto.getIncentivoAhorro());
		entity.setCompensacion(dto.getCompensacion());
		entity.setQuinquenio(dto.getQuinquenio());
		entity.setNoCuenta(dto.getNoCuenta());
		entity.setPolicia(dto.getPolicia());
		entity.setIdFuenteFinanciamiento(dto.getIdFuenteFinanciamiento());
		entity.setIdSubfuenteFinanciamiento(dto.getIdSubfuenteFinanciamiento());
		entity.setIdTipoRecurso(dto.getIdTipoRecurso());
		entity.setIdEstadoEmpleado(dto.getIdEstadoEmpleado());
		entity.setIdNomina(0); 
		siifDatosLaboralesDAO.actualizar(entity);
		return obtenerSiifDatosLaboralesPorId(entity.getIdDatoLaboral());
	}

	public void eliminarDatosLaborales(SiifDatosLaboralesDTO dto) {
		SIIFDatosLaboralesEntity entity = entityManager.find
				(SIIFDatosLaboralesEntity.class, dto.getIdDatoLaboral());
		entityManager.remove(entity);
	}
	
//	<<<CLAE para Estructura Nomina Datos(Creacion-Lectura-Actualizacion-Eliminacion)>>>
	
	public SiifLaboralesSubfuentesDTO nuevasSubfuentes() {
		SiifLaboralesSubfuentesDTO dto = new SiifLaboralesSubfuentesDTO();
		dto.setIdFuenteFinanciamiento(null);
		dto.setIdSubfuenteFinanciamiento(null);
			return dto;
	}

	public SiifLaboralesSubfuentesDTO crearSubfuentes(Integer idDatosLaborales) {
		SiifLaboralesSubfuentesEntity entity = new SiifLaboralesSubfuentesEntity();
		entity.setIdSubfuenteFinanciamiento(null);
		entity.setIdSiifDatosLaborales(idDatosLaborales);
		siifLaboralesSubfuentesDAO.crear(entity);
		return obtenerSiifLaboralesSubfuentesPorId(entity.getIdSiifLaboralesSubfuentes());
	}

	public void actualizarDatos(List<SiifLaboralesSubfuentesDTO> list, Integer idDL) {
		for(SiifLaboralesSubfuentesDTO subfuente: list){
			System.out.println("id::"+subfuente.getIdSiifLaboralesSubfuentes());
			SiifLaboralesSubfuentesEntity entity = new  SiifLaboralesSubfuentesEntity();
			entity = siifLaboralesSubfuentesDAO.obtenerPorId(subfuente.getIdSiifLaboralesSubfuentes());
			entity.setIdSubfuenteFinanciamiento(subfuente.getIdSubfuenteFinanciamiento());
			siifLaboralesSubfuentesDAO.actualizar(entity);
		}
	}
	
	public void eliminarSiifLAborlaesSubfuente(SiifLaboralesSubfuentesDTO dto) {
		SiifLaboralesSubfuentesEntity entity = entityManager.find
				(SiifLaboralesSubfuentesEntity.class, dto.getIdSiifLaboralesSubfuentes());
		entityManager.remove(entity);
		}
		
//	> > > > Otras Listas < < < <
	
	public List<DependenciaDTO> obtenerDependencia() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_dependencia AS idDependencia, "
				+ "descripcion AS descripcion "
				+ "FROM dependencias_temp");
		query.setResultTransformer(Transformers.aliasToBean(DependenciaDTO.class));
		@SuppressWarnings("unchecked")
		List<DependenciaDTO> result = (List<DependenciaDTO>) query.list();
		return result;
	}
	
	public List<UnidadResponsableDTO> obtenerUnidad() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_unidad_responsable AS idUnidadResponsable, "
				+ "descripcion AS descripcion "
				+ "FROM unidades_responsables");
		query.setResultTransformer(Transformers.aliasToBean(UnidadResponsableDTO.class));
		@SuppressWarnings("unchecked")
		List<UnidadResponsableDTO> result = (List<UnidadResponsableDTO>) query.list();
		return result;
	}
	
	public List<TipoNombramientoDTO> obtenerNombramiento() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT nombramiento AS nombramiento, "
				+ "descripcion AS descripcion "
				+ "FROM tipos_nombramientos");
		query.setResultTransformer(Transformers.aliasToBean(TipoNombramientoDTO.class));
		@SuppressWarnings("unchecked")
		List<TipoNombramientoDTO> result = (List<TipoNombramientoDTO>) query.list();
		return result;
	}
	
	public List<TipoNombramientoDTO> obtenerNombramientoID() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT nombramiento AS nombramiento, "
				+ "descripcion AS descripcion "
				+ "FROM tipos_nombramientos");
		query.setResultTransformer(Transformers.aliasToBean(TipoNombramientoDTO.class));
		@SuppressWarnings("unchecked")
		List<TipoNombramientoDTO> result = (List<TipoNombramientoDTO>) query.list();
		return result;
	}
	
//	public List<PlazaDTO> obtenerPlaza() {
//		Session session = entityManager.unwrap(Session.class);
//		Query query = session.createSQLQuery(
//				"SELECT nombramiento AS nombramiento, "
//				+ "descripcion AS descripcion "
//				+ "FROM tipos_nombramientos");
//		query.setResultTransformer(Transformers.aliasToBean(PlazaDTO.class));
//		@SuppressWarnings("unchecked")
//		List<PlazaDTO> result = (List<PlazaDTO>) query.list();
//		return result;
//	}

	public List<PuestosGeneralesDTO> obtenerPuesto() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT codigo AS codigo, "
				+ "puesto AS puesto "
				+ "FROM puestos_generales");
		query.setResultTransformer(Transformers.aliasToBean(PuestosGeneralesDTO.class));
		@SuppressWarnings("unchecked")
		List<PuestosGeneralesDTO> result = (List<PuestosGeneralesDTO>) query.list();
		return result;
	}
	
	public List<FuenteFinanciamientoDTO> obtenerFuenteFinanciamiento() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_fuente_financiamiento AS idFuenteFinanciamiento, "
				+ "descripcion AS descripcion "
				+ "FROM fuentes_financiamientos");
		query.setResultTransformer(Transformers.aliasToBean(FuenteFinanciamientoDTO.class));
		@SuppressWarnings("unchecked")
		List<FuenteFinanciamientoDTO> result = (List<FuenteFinanciamientoDTO>) query.list();
		return result;
	}
	
	public List<SubfuenteFinanciamientoDTO> obtenerSubfuenteFinanciamiento() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
				+ "descripcion AS descripcion "
				+ "FROM subfuentes_financiamientos_temp");
		query.setResultTransformer(Transformers.aliasToBean(SubfuenteFinanciamientoDTO.class));
		@SuppressWarnings("unchecked")
		List<SubfuenteFinanciamientoDTO> result = (List<SubfuenteFinanciamientoDTO>) query.list();
		return result;
	}
	
	public List<ProyectosTempDTO> obtenerProyecto(){
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_proyecto AS idProyecto, "
				+ "CONCAT(id_proyecto,' - ',descripcion) AS descripcion "
				+ "FROM proyectos_temp ");
		query.setResultTransformer(Transformers.aliasToBean(ProyectosTempDTO.class));
		@SuppressWarnings("unchecked")
		List<ProyectosTempDTO> result = (List<ProyectosTempDTO>) query.list();
		return result;
	}

	public void modificarLaborales(String rfc, Integer id) {
		for(SiifDatosLaboralesDTO tra: listaConsultaDatosLaboralesPorIdPersonales(id)){
//			for(SiifDatosLaboralesDTO tra: listaSiifDatosLaboralesPorCriterio(rfcOriginal)){
				System.out.println("lista encontrada..."+tra);
				SIIFDatosLaboralesEntity entity = siifDatosLaboralesDAO.obtenerPorId(tra.getIdDatoLaboral());
				entity.setRfc(rfc);
			}
	}
	
	public List<FuenteFinanciamientoDTO> listaFuenteFinanciamiento(){
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				  "SELECT id_fuente_financiamiento AS idFuenteFinanciamiento, "				
				+ "descripcion AS descripcion "				
				+ "FROM fuentes_financiamientos");
		query.setResultTransformer(Transformers.aliasToBean(FuenteFinanciamientoDTO.class));
		@SuppressWarnings("unchecked")
		List<FuenteFinanciamientoDTO> result = (List<FuenteFinanciamientoDTO>) query.list();
		return result;
	}
	
	public List<SubfuenteFinanciamientoDTO> listaSubfuenteFinanciamiento(){
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				  "SELECT id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "				
				+ "descripcion AS descripcion "				
				+ "FROM subfuentes_financiamientos_temp");
		query.setResultTransformer(Transformers.aliasToBean(SubfuenteFinanciamientoDTO.class));
		@SuppressWarnings("unchecked")
		List<SubfuenteFinanciamientoDTO> result = (List<SubfuenteFinanciamientoDTO>) query.list();
		return result;
	}
	
	public List<SubfuenteFinanciamientoDTO> listaSubfuenteFinanciamientoPorIdFF(Integer idFuenteFinanciamiento){
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				  "SELECT id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
				+ "id_fuente_financiamiento AS idFuenteFinanciamiento, "				
				+ "descripcion AS descripcion "				
				+ "FROM subfuentes_financiamientos_temp WHERE id_fuente_financiamiento =:idFuenteFinanciamiento")
				.setParameter("idFuenteFiananciamiento", idFuenteFinanciamiento);
		query.setResultTransformer(Transformers.aliasToBean(SubfuenteFinanciamientoDTO.class));
		@SuppressWarnings("unchecked")
		List<SubfuenteFinanciamientoDTO> result = (List<SubfuenteFinanciamientoDTO>) query.list();
		return result;
	}

	public void change() {
		for(SiifDatosLaboralesDTO laborales :listaSiifDatosLaborales()){
			SiifLaboralesSubfuentesEntity entity = new SiifLaboralesSubfuentesEntity();
			entity.setIdSubfuenteFinanciamiento(laborales.getIdSubfuenteFinanciamiento());
			entity.setIdSiifDatosLaborales(laborales.getIdDatoLaboral());
			siifLaboralesSubfuentesDAO.crear(entity);
		}
	}

}