package mx.gob.saludtlax.rh.nomina.calculonomina.isr;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.TablaSubsidioRepositroy;
import mx.gob.saludtlax.rh.persistencia.TablaSubsidioEntity;
import mx.gob.saludtlax.rh.persistencia.TarifaRetencionRepository;
import mx.gob.saludtlax.rh.persistencia.TarifaRetencionEntity;

@Stateless
public class IsrService {
	@Inject
	private TarifaRetencionRepository tarifaRetencionDAO;
	@Inject
	private TablaSubsidioRepositroy tablaSubsidioDAO;
	

	public ResultadoIsrDTO calculoIsr(BigDecimal ingresoGravable, Integer peridiocidad, Integer anio) {
		ResultadoIsrDTO resultadoIsrDTO = new ResultadoIsrDTO();
		TarifaRetencionEntity tarifaRetencion = tarifaRetencionDAO.getTarifaRetencionByDatos(ingresoGravable,peridiocidad, anio);
		TablaSubsidioEntity tablaSubsidio = tablaSubsidioDAO.getTablaSubsidioByDatos(ingresoGravable, peridiocidad, anio);
		
		BigDecimal op1 = ingresoGravable.subtract(tarifaRetencion.getLimiteInferior()); 
		// Se saca el exedente del Ingreso Gravable - Limite inferior Correspondiente
		BigDecimal op2 = op1.multiply(tarifaRetencion.getPorcentajeAplicable());
		// Se obtiene el impuesto marginal al multiplicar por el Porcentaje especificada en la Tabla de Tarifa de Retencion
		BigDecimal op3 = op2.add(tarifaRetencion.getCuotaFija());
		// Se obtiene el impuesto previo al sumar la Cuota Fija especificada en la Tabla de Tarifa de Retencion
		BigDecimal op4 = op3.subtract(tablaSubsidio.getSubsidio());
		// Este es el previo de la Retencion, si es menor a 0 entonces es subsidio aplicable, si no es la retencion aplicable
		BigDecimal op5 = ingresoGravable.subtract(op4);
		
		resultadoIsrDTO.setIngresoGravable(ingresoGravable);
		if(op4.compareTo(BigDecimal.ZERO)<0){
			resultadoIsrDTO.setSubsidiosEntregar(op4);
			resultadoIsrDTO.setImpuestoRetener(BigDecimal.ZERO);
		}else{
			resultadoIsrDTO.setSubsidiosEntregar(BigDecimal.ZERO);
			resultadoIsrDTO.setImpuestoRetener(op4);
		}
		resultadoIsrDTO.setImpuestoRetener(op5);
		return resultadoIsrDTO;
	}
	
}
