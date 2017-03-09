package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="detalle_programacion_movimiento")
public class DetalleProgramacionMovimientoEntity implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 634087231087585085L;

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_detalle_programacion_movimiento")
	    private Integer idDetalleProgramacionMovimiento;
	 
		@ManyToOne(fetch = FetchType.LAZY)
	   	@JoinColumn(name = "id_programacion_movimiento")
	    private ProgramarMovimientoEntity idProgramacionMovimiento;
		
		@ManyToOne(fetch = FetchType.LAZY)
	   	@JoinColumn(name = "id_puesto")
	    private PuestoGeneralEntity idPuesto;
		
	    @ManyToOne(fetch = FetchType.LAZY)
	   	@JoinColumn(name = "id_tipo_contratacion")
		private TipoContratacionEntity idTipoContratacion;
	    
	    @Column(name="importe")
	    private BigDecimal importe;

		public Integer getIdDetalleProgramacionMovimiento() {
			return idDetalleProgramacionMovimiento;
		}

		public void setIdDetalleProgramacionMovimiento(Integer idDetalleProgramacionMovimiento) {
			this.idDetalleProgramacionMovimiento = idDetalleProgramacionMovimiento;
		}

		
		
		
		public ProgramarMovimientoEntity getIdProgramacionMovimiento() {
			return idProgramacionMovimiento;
		}

		public void setIdProgramacionMovimiento(ProgramarMovimientoEntity idProgramacionMovimiento) {
			this.idProgramacionMovimiento = idProgramacionMovimiento;
		}

		public PuestoGeneralEntity getIdPuesto() {
			return idPuesto;
		}

		public void setIdPuesto(PuestoGeneralEntity idPuesto) {
			this.idPuesto = idPuesto;
		}

		public TipoContratacionEntity getIdTipoContratacion() {
			return idTipoContratacion;
		}

		public void setIdTipoContratacion(TipoContratacionEntity idTipoContratacion) {
			this.idTipoContratacion = idTipoContratacion;
		}

		public BigDecimal getImporte() {
			return importe;
		}

		public void setImporte(BigDecimal importe) {
			this.importe = importe;
		}

	 
	 
}
