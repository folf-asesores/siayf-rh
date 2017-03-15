package mx.gob.saludtlax.rh.presupuesto.presupuestoautorizado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.persistencia.PresupuestoAutorizadoEntity;
import mx.gob.saludtlax.rh.persistencia.PresupuestoAutorizadoRepository;
/**
 * 
 * @author José Pablo
 *
 */
public class PresupuestoAutorizadoService implements Serializable  {
	
	private static final long serialVersionUID = -3726285933360387718L;
	
	@Inject
	private PresupuestoAutorizadoRepository presupuestoAutorizadoRepository;
	
	public void crearPresupuestoAutorizado(PresupuestoAutorizadoDTO dto) {

		PresupuestoAutorizadoEntity entity = new PresupuestoAutorizadoEntity();

		entity.setIdUnidadResponsable(dto.getIdUnidadResponsable());
		entity.setFin(dto.getFin());
		entity.setFn(dto.getFn());
		entity.setIdSunfuenteFinanciamiento(dto.getIdSubfuenteFinanciamiento());
		entity.setRg(dto.getRg());
		entity.setAi(dto.getAi());
		entity.setMpp(dto.getMpp());
		entity.setPp(dto.getPp());
		entity.setPartida(dto.getPartida());
		entity.setTg(dto.getTg());
		entity.setFf(dto.getFf());
		entity.setEf(dto.getEf());
		entity.setPpii(dto.getPpii());
		entity.setConcepto(dto.getConcepto());
		entity.setImporteAnual(dto.getImporteAnual());
		entity.setAnio(dto.getAnio());
		
		presupuestoAutorizadoRepository.crear(entity);
	}
		
	public void actualizarPresupuestoAutorizado(PresupuestoAutorizadoDTO dto) {
	PresupuestoAutorizadoEntity entity = presupuestoAutorizadoRepository
			.obtenerPorId(dto.getIdPresupuestoAutorizado());
		
		entity.setIdUnidadResponsable(dto.getIdUnidadResponsable());
		entity.setFin(dto.getFin());
		entity.setFn(dto.getFn());
		entity.setIdSunfuenteFinanciamiento(dto.getIdSubfuenteFinanciamiento());
		entity.setRg(dto.getRg());
		entity.setAi(dto.getAi());
		entity.setMpp(dto.getMpp());
		entity.setPp(dto.getPp());
		entity.setPartida(dto.getPartida());
		entity.setTg(dto.getTg());
		entity.setFf(dto.getFf());
		entity.setEf(dto.getEf());
		entity.setPpii(dto.getPpii());
		entity.setConcepto(dto.getConcepto());
		entity.setImporteAnual(dto.getImporteAnual());
		entity.setAnio(dto.getAnio());
		
		presupuestoAutorizadoRepository.actualizar(entity);
	}
	
	public void eliminarPresupuestoAutorizado(Integer idPresupuestoAutorizado) {

		presupuestoAutorizadoRepository.eliminarPorId(idPresupuestoAutorizado);

	}
	
	public List<PresupuestoAutorizadoDTO> obtenerListaPresupuestoAutorizado() {

		List<PresupuestoAutorizadoDTO> dtos = new ArrayList<PresupuestoAutorizadoDTO>();

		List<PresupuestoAutorizadoEntity> entities = presupuestoAutorizadoRepository
				.obtenerListaPresupuestoAutorizado();

		// || entities != null
		if (!entities.isEmpty()) {
			for (PresupuestoAutorizadoEntity presupuestoAutorizadoEntity : entities) {
				PresupuestoAutorizadoDTO dto = new PresupuestoAutorizadoDTO();
				dto.setIdPresupuestoAutorizado(presupuestoAutorizadoEntity.getIdPresupuestoAutorizado());
				dto.setIdUnidadResponsable(presupuestoAutorizadoEntity.getIdUnidadResponsable());
				dto.setFin(presupuestoAutorizadoEntity.getFin());
				dto.setFn(presupuestoAutorizadoEntity.getFn());
				dto.setIdSubfuenteFinanciamiento(presupuestoAutorizadoEntity.getIdSubfuenteFinanciamiento());
				dto.setRg(presupuestoAutorizadoEntity.getRg());
				dto.setAi(presupuestoAutorizadoEntity.getAi());
				dto.setMpp(presupuestoAutorizadoEntity.getMpp());
				dto.setPp(presupuestoAutorizadoEntity.getPp());
				dto.setPartida(presupuestoAutorizadoEntity.getPartida());
				dto.setTg(presupuestoAutorizadoEntity.getTg());
				dto.setFf(presupuestoAutorizadoEntity.getFf());
				dto.setEf(presupuestoAutorizadoEntity.getEf());
				dto.setPpii(presupuestoAutorizadoEntity.getPpii());
				dto.setConcepto(presupuestoAutorizadoEntity.getConcepto());
				dto.setImporteAnual(presupuestoAutorizadoEntity.getImporteAnual());
				dto.setAnio(presupuestoAutorizadoEntity.getAnio());

				dtos.add(dto);
			}
		}

		return dtos;
	}
}
