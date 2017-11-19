/*
 *
 */

package mx.gob.saludtlax.rh.siif.serica;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public interface Serica {

    public byte[] getDetalleSerica();

    public byte[] getDetallerSericaPeriodo(Integer periodo,
            Integer ejercicioFiscal);

}
