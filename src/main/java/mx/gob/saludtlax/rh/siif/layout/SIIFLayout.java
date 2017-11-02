/*
 * SIIFLayout.java
 * Creado el 26/06/2016 01:09:23 AM
 * 
 */
package mx.gob.saludtlax.rh.siif.layout;

import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface SIIFLayout extends Serializable {

    /**
     * Este método permite obtener el Layout.
     * 
     * @param quincena el número de quincena real.
     * @param anyo el año de la quincena.
     * @return un arreglo que representa el archivo zip con la información del 
     * layout.
     */
    byte [] getLayoutComoZip(String quincena, int anyo);
    
    byte [] getLayoutFinalComoZip(String quincena, int anyo);
    
    byte[] getLayoutComoDatTra(String quincena);
    
    byte[] getLayoutComoZipRH(Integer idProductoNomina);
    
    void crearLayoutsRH(Integer idProductoNomina);
    
    /**
     * Genera los archivos DAT y TRA de una nomina en un archivo zip.
     * 
     * @param idBitacora el id de la bitacora de SIIF de la cual se obtendrá.
     * @return un arreglo de bytes que representa un archivo zip que contiene 
     * el archivo DAT y TRA.
     */
    byte[] getDatTra(Integer idBitacora);	
	
    byte[] getLayoutSeguroPopular(String quincena);

    byte[] getDatTraContrato(String quincena);

    byte[] getDatTraRH(Integer idBitacora);
    
    byte[] getDatTraProdNom(Integer idBitacora);
    
    byte[] getDatTraProdNomRH(Integer idBitacora);
    
    void crearDatTraProdNom(Integer idProductoNomina);
    
    int verificaProductoNomina(Integer idProductoNomina);
    
    byte[] getLayoutSeguroPopularRH(Integer idProductoNomina);

	byte[] getDatTraProdNomRH_Cont(Integer idProductoNomina);
    

    
}
