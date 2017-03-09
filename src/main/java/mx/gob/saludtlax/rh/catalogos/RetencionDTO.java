package mx.gob.saludtlax.rh.catalogos;

import mx.gob.saludtlax.rh.persistencia.DetalleRetencionEntity;

public class RetencionDTO {

	private Integer id_detalle_retencion_cuenta_bancaria;
    private Integer id_concepto_nomina;
    private String  descripcion_concepto_nomina;
    private String cuenta_contable_retencion;
    private String descripcion_cuenta_contable;
    private String cuenta_retencion;
    private String cuenta_contable_banco;
    private String descripcion_cuenta_banco;
    
    
    
    public DetalleRetencionEntity toEntity(DetalleRetencionEntity entity,RetencionDTO dto ){
    	
    	entity.setIdDetalleRetencionCuentaBancaria(dto.getId_detalle_retencion_cuenta_bancaria());
    	entity.setIdConceptoNomina(dto.getId_concepto_nomina());
    	entity.setDescripcionConceptoNomina(dto.getDescripcion_concepto_nomina());
    	entity.setCuentaContableRetencion(dto.getCuenta_contable_retencion());
    	entity.setDescripcionCuentaContable(dto.getDescripcion_cuenta_contable());
    	entity.setCuentaRetencion(dto.getCuenta_retencion());
    	entity.setCuentaContableBanco(dto.getCuenta_contable_banco());
    	entity.setDescripcionCuentaBanco(dto.getDescripcion_cuenta_banco());
    	
    	return entity;
    	
    }
    
	public Integer getId_detalle_retencion_cuenta_bancaria() {
		return id_detalle_retencion_cuenta_bancaria;
	}
	public void setId_detalle_retencion_cuenta_bancaria(
			Integer id_detalle_retencion_cuenta_bancaria) {
		this.id_detalle_retencion_cuenta_bancaria = id_detalle_retencion_cuenta_bancaria;
	}
	public Integer getId_concepto_nomina() {
		return id_concepto_nomina;
	}
	public void setId_concepto_nomina(Integer id_concepto_nomina) {
		this.id_concepto_nomina = id_concepto_nomina;
	}
	public String getDescripcion_concepto_nomina() {
		return descripcion_concepto_nomina;
	}
	public void setDescripcion_concepto_nomina(String descripcion_concepto_nomina) {
		this.descripcion_concepto_nomina = descripcion_concepto_nomina;
	}
	public String getCuenta_contable_retencion() {
		return cuenta_contable_retencion;
	}
	public void setCuenta_contable_retencion(String cuenta_contable_retencion) {
		this.cuenta_contable_retencion = cuenta_contable_retencion;
	}
	public String getDescripcion_cuenta_contable() {
		return descripcion_cuenta_contable;
	}
	public void setDescripcion_cuenta_contable(String descripcion_cuenta_contable) {
		this.descripcion_cuenta_contable = descripcion_cuenta_contable;
	}
	public String getCuenta_retencion() {
		return cuenta_retencion;
	}
	public void setCuenta_retencion(String cuenta_retencion) {
		this.cuenta_retencion = cuenta_retencion;
	}
	public String getCuenta_contable_banco() {
		return cuenta_contable_banco;
	}
	public void setCuenta_contable_banco(String cuenta_contable_banco) {
		this.cuenta_contable_banco = cuenta_contable_banco;
	}
	public String getDescripcion_cuenta_banco() {
		return descripcion_cuenta_banco;
	}
	public void setDescripcion_cuenta_banco(String descripcion_cuenta_banco) {
		this.descripcion_cuenta_banco = descripcion_cuenta_banco;
	}
	
    
    
}
