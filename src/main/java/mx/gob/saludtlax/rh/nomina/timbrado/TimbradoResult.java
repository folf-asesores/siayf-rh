package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.Serializable;

public class TimbradoResult implements Serializable {
	
	 private Integer totalNominasEncontradas;
	 private Integer totalTimbradas;
	 private Integer totalFallos;
	public Integer getTotalNominasEncontradas() {
		return totalNominasEncontradas;
	}
	public void setTotalNominasEncontradas(Integer totalNominasEncontradas) {
		this.totalNominasEncontradas = totalNominasEncontradas;
	}
	public Integer getTotalTimbradas() {
		return totalTimbradas;
	}
	public void setTotalTimbradas(Integer totalTimbradas) {
		this.totalTimbradas = totalTimbradas;
	}
	public Integer getTotalFallos() {
		return totalFallos;
	}
	public void setTotalFallos(Integer totalFallos) {
		this.totalFallos = totalFallos;
	}
	 
	 
	 

}
