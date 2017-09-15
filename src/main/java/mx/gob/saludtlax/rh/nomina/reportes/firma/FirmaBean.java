/*
 * FirmaBean.java
 * Creado el 11/sep/2017 11:05:31 AM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.firma;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class FirmaBean implements Firma {

    private static final long serialVersionUID = -7969962371656776453L;
    
    @Inject
    private FirmaService firmaService; 

    @Override
    public byte[] generarReporte(final Integer idProductoNomina) {
        if (ValidacionUtil.esMenorQueUno(idProductoNomina)) {
            throw new ValidacionException("El ID del producto no debe ser nulo o menor que uno", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        
        FirmaDTO firmasEmpleados = firmaService.obtenerFirmaEmpleado(idProductoNomina);
        FirmaMotor firmaMotor = new FirmaMotor();
        byte[] reporte = firmaMotor.obtenerArchivo(firmasEmpleados);

        return reporte;
    }
}
