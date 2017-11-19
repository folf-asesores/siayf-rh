
package mx.gob.saludtlax.rh.nomina.movimientofijo;

import java.util.Date;

import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.SubFuenteFinanciamientoTempEntity;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionEntity;
import mx.gob.saludtlax.rh.persistencia.TipoRecursoTempEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity;

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
