package mx.gob.saludtlax.rh.empleados.suplencia;


public class InfoSuplenciaDTO {
	private Integer mes;
	private int ejercicio;
	private long total;

	public InfoSuplenciaDTO(Integer mes, int ejercicio, long total) {
	
		this.mes = mes;
		this.ejercicio = ejercicio;
		this.total = total;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public int getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(int ejercicio) {
		this.ejercicio = ejercicio;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
