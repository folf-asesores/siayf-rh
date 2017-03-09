package mx.gob.saludtlax.rh.nomina.movimientofijo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import mx.gob.saludtlax.rh.persistencia.DependenciaTempEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoEntity;
import mx.gob.saludtlax.rh.persistencia.ProyectoTempEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.SubFuenteFinanciamientoTempEntity;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionEntity;
import mx.gob.saludtlax.rh.persistencia.TipoRecursoTempEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity;
import mx.gob.saludtlax.rh.persistencia.UnidadResponsableEntity;

public class ResumenConfiguracionPresupuestalDTO {
	private Integer id;

	private Integer numeroEmpleado;


	private TipoContratacionEntity tipoContratacion;

	private TiposNombramientosEntity nombramiento;

	private PuestoGeneralEntity puesto;

	
	private FuenteFinanciamientoEntity fuenteFinanciamiento;

	private SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento;

	
	private TipoRecursoTempEntity tipoRecurso;

	private EmpleadoEntity empleado;

	private Date fechaAltaConfiguracion;

	private Boolean estatus;
}
