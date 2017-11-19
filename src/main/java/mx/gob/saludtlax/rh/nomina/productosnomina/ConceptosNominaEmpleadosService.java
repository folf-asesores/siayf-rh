
package mx.gob.saludtlax.rh.nomina.productosnomina;

import javax.ejb.Stateless;
/**
 *
 * @author José Pablo
 *
 */
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import mx.gob.saludtlax.rh.persistencia.ConceptosNominaEmpleadosEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptosNominaEmpleadosRepository;

@Stateless
public class ConceptosNominaEmpleadosService {
    @Inject
    ConceptosNominaEmpleadosRepository conceptosNominaEmpleadosRepository;

    public void crea(ConceptosNominaEmpleadosDTO datos) {
        try {
            System.out.println("valor..." + datos);
            ConceptosNominaEmpleadosEntity entity = new ConceptosNominaEmpleadosEntity();
            //entity.setIdNominaEmpleado(datos.getIdNominaEmpleado());
            //			entity.setTipo(datos.getTipo());
            entity.setClave(datos.getClave());
            entity.setTipoSat(datos.getTipoSat());
            //			entity.setConcepto(datos.getIdConcepto());
            entity.setImporteGravado(datos.getImporteGravado());
            entity.setImporteExcento(datos.getImporteExcento());
            conceptosNominaEmpleadosRepository.crear(entity);
        } catch (PersistenceException px) {
            px.printStackTrace();
        }
    }

    public void actualiza(ConceptosNominaEmpleadosDTO datos) {
        try {
            System.out.println("valor..." + datos);
            ConceptosNominaEmpleadosEntity entity = new ConceptosNominaEmpleadosEntity();
            entity.setIdConceptosNominaEmpleados(datos.getIdNominaEmpleado());
            //entity.setIdNominaEmpleado(datos.getIdNominaEmpleado());
            //			entity.setTipo(datos.getTipo());
            entity.setClave(datos.getClave());
            entity.setTipoSat(datos.getTipoSat());
            //			entity.setConcepto(datos.getIdConcepto());
            entity.setImporteGravado(datos.getImporteGravado());
            entity.setImporteExcento(datos.getImporteExcento());
            conceptosNominaEmpleadosRepository.actualizar(entity);
        } catch (PersistenceException px) {
            px.printStackTrace();
        }
    }

    public void eliminar(ConceptosNominaEmpleadosDTO datos) {
        conceptosNominaEmpleadosRepository.eliminarPorId(datos.getIdConceptosNominaEmpleado());
    }
}
