/**
 * 
 */
package mx.gob.saludtlax.rh.siif.serica;

/**
 * @author Eduardo Mex
 *
 */
public interface Serica {
	
public	byte[] getDetalleSerica();	

public byte[] getDetallerSericaPeriodo(Integer periodo, Integer ejercicioFiscal);

}
