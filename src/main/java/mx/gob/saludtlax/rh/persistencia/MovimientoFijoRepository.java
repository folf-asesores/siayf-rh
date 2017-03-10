/**
 * Copyright ® 2016
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.batik.bridge.NoLoadExternalResourceSecurity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import mx.gob.saludtlax.rh.nomina.movimientofijo.MovimientoNominaDTO;
import mx.gob.saludtlax.rh.nomina.movimientosnomina.TipoMovimientoNominaDTO;


/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 25/05/2016 14:15:54
 */
public class MovimientoFijoRepository extends GenericRepository<MovimientoFijoEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3009737178790534145L;
	
	public List<MovimientoNominaDTO> obtenerMovimientosFijosPorEmpleado(Integer idEmpleado){
		Session session = em.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT "
				+ "id_movimiento_fijo AS idMovimientoFijo, "
				+ "id_tercero_institucional AS idTerceroInstitucional, "
				+ "id_empleado AS idEmpleado, "
				+ "rfc AS rfc, "
				+ "quincena_operacion as quincenaOperacion, "
				+ "anyo_operacion AS anyoOperacion, "
				+ "importe_descontado AS importeDescontado, "
				+ "quincena_inicial AS quincenaInicial, "
				+ "quincena_final AS quincenaFinal, "
				+ "anio_final AS anioFinal, "
				+ "anio_inicial as anioInicial, "
				+ "fecha_documento as fechaDocumento, "
				+ "folio_documento as folio,"
				+ "id_tipo_movimiento as idTipoMovimiento,"
				+ "dias as dias "
				+ "FROM movimientos_empleado_nomina where id_empleado=:idEmpleado")
				.setParameter("idEmpleado", idEmpleado);		
		query.setResultTransformer(Transformers.aliasToBean(MovimientoNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<MovimientoNominaDTO> datosDto = (List<MovimientoNominaDTO>) query.list();
		List<MovimientoNominaDTO> datosDto2= new ArrayList<>();
		
		for(MovimientoNominaDTO dto: datosDto){
			if(dto.getIdTerceroInstitucional()!=null){
			TerceroInstitucionalEntity tI = em.createQuery("Select t From TerceroInstitucionalEntity as t where t.idTerceroInstitucional=:idTercero", TerceroInstitucionalEntity.class)
					.setParameter("idTercero", dto.getIdTerceroInstitucional()).getSingleResult();
			
			dto.setTerceroInstitucional(tI.getNumero()+"-"+tI.getConceptoDeduccion()+"-"+tI.getContrapartidaIdentificadora());
			}
			datosDto2.add(dto);
		
		}
		
		return datosDto2;
	}
	
	
	public List<MovimientoNominaDTO> obtenerMovimientosFijosPorEmpleadoytipoMov(Integer idEmpleado,TipoMovimientoNominaDTO movDto){
		Session session = em.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT "
				+ "id_movimiento_fijo AS idMovimientoFijo, "
				+ "id_tercero_institucional AS idTerceroInstitucional, "
				+ "id_empleado AS idEmpleado, "
				+ "rfc AS rfc, "
				+ "quincena_operacion as quincenaOperacion, "
				+ "anyo_operacion AS anyoOperacion, "
				+ "importe_descontado AS importeDescontado, "
				+ "quincena_inicial AS quincenaInicial, "
				+ "quincena_final AS quincenaFinal, "
				+ "anio_final AS anioFinal, "
				+ "anio_inicial as anioInicial, "
				+ "fecha_documento as fechaDocumento, "
				+ "folio_documento as folio,"
				+ "id_tipo_movimiento as idTipoMovimiento,"
				+ "dias as dias,"
				+ "clave,"
				+ "estatus "
				+ "FROM movimientos_empleado_nomina where id_empleado=:idEmpleado and id_tipo_movimiento=:idTipoMovimiento")
				.setParameter("idEmpleado", idEmpleado)
				.setParameter("idTipoMovimiento", movDto.getIdTimpoMovimiento());		
		query.setResultTransformer(Transformers.aliasToBean(MovimientoNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<MovimientoNominaDTO> datosDto = (List<MovimientoNominaDTO>) query.list();
		List<MovimientoNominaDTO> datosDto2= new ArrayList<>();
		
		for(MovimientoNominaDTO dto: datosDto){
			if(dto.getIdTerceroInstitucional()!=null){
			TerceroInstitucionalEntity tI = em.createQuery("Select t From TerceroInstitucionalEntity as t where t.idTerceroInstitucional=:idTercero", TerceroInstitucionalEntity.class)
					.setParameter("idTercero", dto.getIdTerceroInstitucional()).getSingleResult();
			
			dto.setTerceroInstitucional(tI.getNumero()+"-"+tI.getConceptoDeduccion()+"-"+tI.getContrapartidaIdentificadora());
			}
			datosDto2.add(dto);
		
		}
		
		return datosDto2;
	}
	
	
	public List<MovimientoNominaDTO> obtenerMovimiento(Integer idEmpleado,Integer tipoMovimiento){
		
		try{
		Session session = em.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT "
				+ "id_movimiento_fijo AS idMovimientoFijo, "
				+ "id_tercero_institucional AS idTerceroInstitucional, "
				+ "id_empleado AS idEmpleado, "
				+ "rfc AS rfc, "
				+ "quincena_operacion as quincenaOperacion, "
				+ "anyo_operacion AS anyoOperacion, "
				+ "importe_descontado AS importeDescontado, "
				+ "quincena_inicial AS quincenaInicial, "
				+ "quincena_final AS quincenaFinal, "
				+ "anio_final AS anioFinal, "
				+ "anio_inicial as anioInicial, "
				+ "fecha_documento as fechaDocumento, "
				+ "folio_documento as folio,"
				+ "id_tipo_movimiento as idTipoMovimiento,"
				+ "dias as dias,"
				+ "clave,"
				+ "estatus "
				+ "FROM movimientos_empleado_nomina where id_empleado=:idEmpleado and id_tipo_movimiento=:idTipoMovimiento and estatus=1")
				.setParameter("idEmpleado", idEmpleado)
				.setParameter("idTipoMovimiento", tipoMovimiento);		
		query.setResultTransformer(Transformers.aliasToBean(MovimientoNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<MovimientoNominaDTO> datosDto = (List<MovimientoNominaDTO>) query.list();
		List<MovimientoNominaDTO> datosDto2= new ArrayList<>();
		
		for(MovimientoNominaDTO dto: datosDto){
			if(dto.getIdTerceroInstitucional()!=null){
			TerceroInstitucionalEntity tI = em.createQuery("Select t From TerceroInstitucionalEntity as t where t.idTerceroInstitucional=:idTercero", TerceroInstitucionalEntity.class)
					.setParameter("idTercero", dto.getIdTerceroInstitucional()).getSingleResult();
			
			dto.setTerceroInstitucional(tI.getNumero()+"-"+tI.getConceptoDeduccion()+"-"+tI.getContrapartidaIdentificadora());
			}
			datosDto2.add(dto);
		
		}
		
		return datosDto2;
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<MovimientoNominaDTO> obtenerMovimientosFijos(){
		Session session = em.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT "
				+ "id_movimiento_fijo AS idMovimientoFijo, "
				+ "id_tercero_institucional AS idTerceroInstitucional, "
				+ "id_empleado AS idEmpleado, "
				+ "rfc AS rfc, "
				+ "quincena_operacion as quincenaOperacion, "
				+ "anyo_operacion AS anyoOperacion, "
				+ "importe_descontado AS importeDescontado, "
				+ "quincena_inicial AS quincenaInicial, "
				+ "quincena_final AS quincenaFinal, "
				+ "anio_final AS anioFinal, "
				+ "folio_documento as folio,"
				+ "fecha_documento, "
				+ "anio_inicial as anioInicial, "
				+ "fecha_documento as fechaDocumento, "
				+ "folio_documento as folio,"
				+ "id_tipo_movimiento as idTipoMovimiento,"
				+ "clave,"
				+ "estatus, "
				+ "dias as dias "
				+ "FROM movimientos_empleado_nomina");
		
		query.setResultTransformer(Transformers.aliasToBean(MovimientoNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<MovimientoNominaDTO> datosDto = (List<MovimientoNominaDTO>) query.list();
		return datosDto;
	}
	
public Integer periodoActual(Integer tipoPeriodo, Integer ejercicioFiscal,Date fechaActual){
	Integer res=0;
	
	try{
		
		EjercicioFiscalEntity ejercicioF = em.createQuery("Select e from EjercicioFiscalEntity as e where e.ejercicioFiscal=:ejercicioFiscal", EjercicioFiscalEntity.class)
				.setParameter("ejercicioFiscal", ejercicioFiscal).getSingleResult();
		PeriodoCalendariosEntity query = em.createQuery("SELECT pc FROM PeriodoCalendariosEntity as pc "
				+ "WHERE pc.tipoPeriodo.idTipoPeriodo=:tipoPeriodo and pc.idEjercicioFiscal=:ejercicioFiscal and :fecha "
				+ "BETWEEN pc.inicioPeriodo AND pc.finPeriodo", PeriodoCalendariosEntity.class)
				.setParameter("tipoPeriodo", tipoPeriodo)
				.setParameter("ejercicioFiscal",ejercicioF.getIdEjercicioFiscal())
				.setParameter("fecha", fechaActual)
				.getSingleResult();
		
		return query.getNumeroPeriodo();
	}catch(NoResultException e){
		return res;
	}
}
	

public MovimientoNominaDTO obtenerMovimientoPorDatosArchivo(MovimientoNominaDTO dto){
	try{
	Session session = em.unwrap(Session.class);
	Query query = session.createSQLQuery("SELECT "
			+ "id_movimiento_fijo AS idMovimientoFijo, "
			+ "id_tercero_institucional AS idTerceroInstitucional, "
			+ "quincena_operacion AS quincenaOperacion, "
			+ "rfc as rfc, "
			+ "anyo_operacion AS anyoOperacion, "
			+ "importe_descontado AS importeDescontado, "
			+ "quincena_inicial AS quincenaInicial, "
			+ "quincena_final AS quincenaFinal, "
			+ "anio_final AS anioFinal, "
			+ "anio_inicial as anioInicial, "
			+ "fecha_documento as fechaDocumento, "
			+ "folio_documento as folio,"
			+ "id_tipo_movimiento as idTipoMovimiento,"
			+ "dias as dias, "
			+ "clave,"
			+ "estatus "
			+ "FROM movimientos_empleado_nomina"
			+ " WHERE movimientos_empleado_nomina.rfc =:rfc and movimientos_empleado_nomina.id_tercero_institucional =:idTercero")
			.setParameter("rfc", dto.getRfc())
			.setParameter("idTercero",dto.getIdTerceroInstitucional());
	
	
	query.setResultTransformer(Transformers.aliasToBean(MovimientoNominaDTO.class));
	@SuppressWarnings("unchecked")
	List<MovimientoNominaDTO> datosDto = (List<MovimientoNominaDTO>) query.list();
	if(datosDto.size()>0){
	return datosDto.get(0);
	}
	return null;
	}catch(NoResultException e){
		return null;
	}
}


public List<MovimientoNominaDTO> obtenerMovimientosTercerosPorEmpleado(Integer idEmpleado) {
	Session session = em.unwrap(Session.class);
	Query query = session.createSQLQuery("SELECT "
			+ "id_movimiento_fijo AS idMovimientoFijo, "
			+ "id_tercero_institucional AS idTerceroInstitucional, "
			+ "id_empleado AS idEmpleado, "
			+ "rfc AS rfc, "
			+ "quincena_operacion as quincenaOperacion, "
			+ "anyo_operacion AS anyoOperacion, "
			+ "importe_descontado AS importeDescontado, "
			+ "quincena_inicial AS quincenaInicial, "
			+ "quincena_final AS quincenaFinal, "
			+ "anio_final AS anioFinal, "
			+ "anio_inicial as anioInicial, "
			+ "fecha_documento as fechaDocumento, "
			+ "folio_documento as folio,"
			+ "id_tipo_movimiento as idTipoMovimiento,"
			+ "dias as dias,"
			+ "clave,"
			+ "estatus "
			+ "FROM movimientos_empleado_nomina where id_empleado=:idEmpleado and id_tipo_movimiento IS NULL")
			.setParameter("idEmpleado", idEmpleado);		
	query.setResultTransformer(Transformers.aliasToBean(MovimientoNominaDTO.class));
	@SuppressWarnings("unchecked")
	List<MovimientoNominaDTO> datosDto = (List<MovimientoNominaDTO>) query.list();
	List<MovimientoNominaDTO> datosDto2= new ArrayList<>();
	
	for(MovimientoNominaDTO dto: datosDto){
		if(dto.getIdTerceroInstitucional()!=null){
		TerceroInstitucionalEntity tI = em.createQuery("Select t From TerceroInstitucionalEntity as t where t.idTerceroInstitucional=:idTercero", TerceroInstitucionalEntity.class)
				.setParameter("idTercero", dto.getIdTerceroInstitucional()).getSingleResult();
		
		dto.setTerceroInstitucional(tI.getNumero()+"-"+tI.getConceptoDeduccion()+"-"+tI.getContrapartidaIdentificadora());
		}
		datosDto2.add(dto);
	}
	
	return datosDto2;
}

}
