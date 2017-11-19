/*
 * PagoGeneralEJB.java
 * Creado el 25/Dec/2016 8:21:08 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.productosnomina;

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
public class PagoGeneralEJB implements PagoGeneral {

    private static final long serialVersionUID = -8434975257791206013L;

    @Inject
    private PagoGeneralService pagoGeneralService;

    @Override
    public Boolean rfcPerteneceAProductoNomina(String rfc, Integer idProductoNomina) {
        if (ValidacionUtil.esMenorQueUno(idProductoNomina)) {
            throw new ValidacionException("El ID del producto de nomina no puede ser cero o negativo", ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        return pagoGeneralService.rfcPerteneceAProductoNomina(rfc, idProductoNomina);
    }

    //    @Override
    //    public List<DividirNominaDTO> obtenerProductoNomina(Integer idProductoNomina) {
    //        if(ValidacionUtil.esMenorQueUno(idProductoNomina)) {
    //            throw new ValidacionException("El ID del producto de nomina no puede ser cero o negativo", ValidacionCodigoError.NUMERO_NEGATIVO);
    //        }

    //        List<DividirNominaDTO> productoNomina = dividirNominaService.obtenerProductoNomina(true, null, null, idProductoNomina);
    //        Collections.sort(productoNomina);

    //        return productoNomina;
    //    }

    //    @Override
    //    public List<DividirNominaDTO> obtenerProductoNomina(Integer primerResultado, Integer cantidadResultados, Integer idProductoNomina) {
    //        if(ValidacionUtil.esMenorQueUno(idProductoNomina)) {
    //            throw new ValidacionException("El ID del producto de nomina no puede ser cero o negativo", ValidacionCodigoError.NUMERO_NEGATIVO);
    //        }

    //        if(ValidacionUtil.esMenorQueUno(primerResultado)) {
    //            throw new ValidacionException("El primer resultado no puede ser cero o negativo.", ValidacionCodigoError.NUMERO_NEGATIVO);
    //        }

    //        if(ValidacionUtil.esMenorQueUno(cantidadResultados)) {
    //            throw new ValidacionException("La cantidad de resultados no puede ser cero o negativo.", ValidacionCodigoError.NUMERO_NEGATIVO);
    //        }

    //        List<DividirNominaDTO> productoNomina = dividirNominaService.obtenerProductoNomina(false, primerResultado, cantidadResultados, idProductoNomina);
    //        Collections.sort(productoNomina);

    //        return productoNomina;
    //    }

    //    @Override
    //    public Integer obtenerTotalProductosNomina(Integer idProductoNomina) {
    //        if(ValidacionUtil.esMenorQueUno(idProductoNomina)) {
    //            throw new ValidacionException("El ID del producto de nomina no puede ser cero o negativo", ValidacionCodigoError.NUMERO_NEGATIVO);
    //        }

    //        return dividirNominaService.obtenerTotalProductosNomina(idProductoNomina).intValue();
    //    }

    //    @Override
    //    public List<DividirNominaDTO> dividirProductoNomina(DividirNominaFiltro filtro, Integer idUsuario) {

    //        if(filtro == null) {
    //            throw new ReglaNegocioException("Prueba de error", ReglaNegocioCodigoError.SIN_REGISTRO);
    //            //throw new ValidacionException("El filtro no puede estar vacio.", ValidacionCodigoError.VALOR_REQUERIDO);
    //        }

    //        if(ValidacionUtil.esMenorQueUno(filtro.getIdProductoNomina())) {
    //            throw new ValidacionException("El ID del producto de nomina no puede ser cero o negativo", ValidacionCodigoError.NUMERO_NEGATIVO);
    //        }

    //        if (ValidacionUtil.esCadenaVacia(filtro.getNombreProductoNomina())) {
    //            throw new ValidacionException("Debe indicar el nombre del nuevo producto de nómina.", ValidacionCodigoError.VALOR_REQUERIDO);
    //        }

    //        if ((filtro.getRfc() == null || filtro.getRfc().isEmpty()) && ValidacionUtil.esCadenaVacia(filtro.getUnidadResponsable()) && ValidacionUtil.esCadenaVacia(filtro.getRamaPuesto())) {
    //            throw new ValidacionException("Por lo menos debe filtrar por un campo.", ValidacionCodigoError.VALOR_REQUERIDO);
    //        }

    //        return dividirNominaService.dividirProductoNomina(filtro, idUsuario);
    //    }
}
