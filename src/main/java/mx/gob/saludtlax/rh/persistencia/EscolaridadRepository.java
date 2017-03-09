/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author Eduardo Mex
 * @version 1.0
 */
public class EscolaridadRepository extends GenericRepository<EscolaridadEntity, Integer> {

	private static final long serialVersionUID = -9154454898061895719L;
    
	public List<EscolaridadEntity> listaEscolaridades() {
		return em.createQuery("SELECT e FROM EscolaridadEntity AS e", EscolaridadEntity.class)
				.getResultList();
	}

	public EscolaridadEntity escolaridadPorId(Integer idEscolaridad) {
		return em.find(EscolaridadEntity.class, idEscolaridad);
	}
        
        public EscolaridadEntity escolaridadPorIdHistorialAcademico(Integer idHistorialAcademico) {
            return em
                    .createQuery(
                            "SELECT h.escolaridad FROM HistorialAcademicoEntity AS h WHERE h.idHistorialAcademico = :idHistorialAcademico",
                            EscolaridadEntity.class)
                    .setParameter("idHistorialAcademico", idHistorialAcademico).getSingleResult();
        }

}
