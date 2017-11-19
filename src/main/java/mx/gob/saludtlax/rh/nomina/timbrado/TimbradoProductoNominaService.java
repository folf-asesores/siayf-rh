
package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.ConceptosNominaEmpleadosEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptosNominaEmpleadosRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoRepository;
import mx.gob.saludtlax.rh.persistencia.DireccionEntity;
import mx.gob.saludtlax.rh.persistencia.DireccionRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaRepository;
import mx.gob.saludtlax.rh.util.JSFUtils;

@Stateless
public class TimbradoProductoNominaService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5130514258852796991L;

    @Inject
    ProductoNominaRepository productoNominaRepository;
    @Inject
    NominaEmpleadoRepository nominaEmpleadoRepository;
    @Inject
    DireccionRepository direccionRepository;
    @Inject
    InventarioVacanteRepository inventarioVacanteRepository;
    @Inject
    ConfiguracionPresupuestoRepository configuracionPresupuestaRepository;
    @Inject
    ConceptosNominaEmpleadosRepository conceptosNominaEmpleadoRepository;
    @Inject
    TimbradoService timbradoService;

    public List<ProductoNominaDTO> listadoNominasPendientesPorTimbrar() {

        List<ProductoNominaEntity> listadoProductosNominaEntity = productoNominaRepository.listadoProductosNominasPorTimbrar();

        List<ProductoNominaDTO> listadoProductoNominaDTO = new ArrayList<>();

        for (ProductoNominaEntity productoNominaEntity : listadoProductosNominaEntity) {
            ProductoNominaDTO productoNominaDTO = new ProductoNominaDTO();

            productoNominaDTO.setEjercicioFiscal(productoNominaEntity.getAnyoEjercicioFiscal().toString());
            productoNominaDTO.setFinPeriodo(productoNominaEntity.getFinPeriodo());
            productoNominaDTO.setIdProductoNomina(productoNominaEntity.getIdProductoNomina());
            productoNominaDTO.setInicioPeriodo(productoNominaEntity.getInicioPeriodo());
            productoNominaDTO.setNumeroPeriodo(productoNominaEntity.getNumeroPeriodo());
            productoNominaDTO.setTipoContrato(productoNominaEntity.getTipoContratacion().getTipoContratacion());
            productoNominaDTO.setTipoNomina(productoNominaEntity.getTipoNomina().getDescripcion());
            productoNominaDTO.setTipoPeriodo(productoNominaEntity.getTipoPeriodo().getTipoPeriodo());
            productoNominaDTO.setNombreProducto(productoNominaEntity.getNombreProducto());

            listadoProductoNominaDTO.add(productoNominaDTO);

        }

        return listadoProductoNominaDTO;

    }

    public TimbradoResult timbrarProductoNominina(int idProductoNomina) {

        TimbradoResult timbradoResult = new TimbradoResult();

        ProductoNominaEntity productoNominaEntity = productoNominaRepository.obtenerPorId(idProductoNomina);

        List<NominaEmpleadoEntity> listadoNominaEmpleadoEntity = nominaEmpleadoRepository.obtenerNominaEmpleadoPorIdProductoNomina(idProductoNomina);

        timbradoResult.setTotalNominasEncontradas(listadoNominaEmpleadoEntity.size());
        timbradoResult.setTotalFallos(0);
        timbradoResult.setTotalTimbradas(0);

        List<DatosCFDINomina> listadoDatosCFDINomina = new ArrayList<>();

        for (NominaEmpleadoEntity nominaEmpleadoEntity : listadoNominaEmpleadoEntity) {
            DatosCFDINomina datosCFDINomina = new DatosCFDINomina();

            datosCFDINomina.setAntiguedad(calcularAnyosAntiguedad(nominaEmpleadoEntity.getIdEmpleado().getFechaIngreso()));
            datosCFDINomina.setBanco(productoNominaEntity.getBanco().getClave());
            datosCFDINomina.setCurp(nominaEmpleadoEntity.getIdEmpleado().getCurp());
            datosCFDINomina.setFechaFinalPago(productoNominaEntity.getFinPeriodo());
            datosCFDINomina.setFechaInicalPago(productoNominaEntity.getInicioPeriodo());
            datosCFDINomina.setFechaInicioRelLaboral(nominaEmpleadoEntity.getIdEmpleado().getFechaIngreso());
            datosCFDINomina.setFechaPago(productoNominaEntity.getFechaPago());
            datosCFDINomina.setFolio(nominaEmpleadoEntity.getIdNominaEmpleado().toString());
            datosCFDINomina.setNombre(nominaEmpleadoEntity.getIdEmpleado().getNombreCompleto());
            datosCFDINomina.setNumeroDiasPagados(new BigDecimal(nominaEmpleadoEntity.getNumeroDiasPagados()));
            if (nominaEmpleadoEntity.getNumeroEmpleado() != null) {
                datosCFDINomina.setNumeroEmpleado(nominaEmpleadoEntity.getNumeroEmpleado().toString());
            } else {
                datosCFDINomina.setNumeroEmpleado("");
            }
            datosCFDINomina.setNumeroSeguroSocial(nominaEmpleadoEntity.getIdEmpleado().getNumeroSeguroSocial());
            datosCFDINomina.setPais("MEXICO");
            datosCFDINomina.setPeriocidadPago(productoNominaEntity.getTipoPeriodo().getTipoPeriodo());
            if (productoNominaEntity.getTipoRecursoSat() != null) {
                datosCFDINomina.setTipoFuente(productoNominaEntity.getTipoRecursoSat().getIdTipoRecurso());
            }
            // Falta de donde saco el riesgo de trabajo
            // Falta salario base
            // Falta salario base integrado
            if (nominaEmpleadoEntity.getIdConfiguracionPresupuestal().getPuesto().getPuestoTimbre() != null) {
                datosCFDINomina.setPuesto(nominaEmpleadoEntity.getIdConfiguracionPresupuestal().getPuesto().getPuestoTimbre());

            } else {
                datosCFDINomina.setPuesto(nominaEmpleadoEntity.getIdConfiguracionPresupuestal().getPuesto().getPuesto());
            }

            datosCFDINomina.setTipoContrato(nominaEmpleadoEntity.getIdConfiguracionPresupuestal().getTipoContratacion().getTipoContratacion());
            // Falta Jornada
            datosCFDINomina.setTipoJornada("MIXTA");
            datosCFDINomina.setTipoRegimen(1);
            datosCFDINomina.setRfc(nominaEmpleadoEntity.getIdEmpleado().getRfc());

            DireccionEntity direccionEmpleadoEntity = direccionRepository.consultarDireccionEmpleadoPorId(nominaEmpleadoEntity.getIdEmpleado().getIdEmpleado());

            if (direccionEmpleadoEntity != null) {
                datosCFDINomina.setCalle(direccionEmpleadoEntity.getCalle());
                datosCFDINomina.setCodigoPostal(String.valueOf(direccionEmpleadoEntity.getCodigoPostal()));
                // datosCFDINomina.setColonia(direccionEmpleadoEntity.getAsentamiento().getNombre());
                datosCFDINomina.setEstado(direccionEmpleadoEntity.getEstado().getNombre());
                datosCFDINomina.setMunicipio(direccionEmpleadoEntity.getMunicipio().getNombre());
                datosCFDINomina.setNoExterio(direccionEmpleadoEntity.getNumeroExterior());
                datosCFDINomina.setNoInterior(direccionEmpleadoEntity.getNumeroInterior());
            }

            List<ConceptosNominaEmpleadosEntity> listadoDeduccionesEntity = conceptosNominaEmpleadoRepository
                    .listaConceptosNominaPorIdNominaEmpleado(nominaEmpleadoEntity.getIdNominaEmpleado(), 2);

            List<DeduccionCFDI> listadoDeducionesCFDI = new ArrayList<>();

            for (ConceptosNominaEmpleadosEntity conceptosNominaEmpleadosEntity : listadoDeduccionesEntity) {

                DeduccionCFDI deduccionCFDI = new DeduccionCFDI();
                deduccionCFDI.setClave(validarMinimoClave(conceptosNominaEmpleadosEntity.getClave()));
                deduccionCFDI.setConcepto(conceptosNominaEmpleadosEntity.getConcepto());
                deduccionCFDI.setImporteGravado(conceptosNominaEmpleadosEntity.getImporteGravado());
                deduccionCFDI.setImporteExcento(conceptosNominaEmpleadosEntity.getImporteExcento());
                deduccionCFDI.setTipoPercepcion(conceptosNominaEmpleadosEntity.getTipoSat());
                listadoDeducionesCFDI.add(deduccionCFDI);

            }

            List<ConceptosNominaEmpleadosEntity> listadoPercepcionesEntity = conceptosNominaEmpleadoRepository
                    .listaConceptosNominaPorIdNominaEmpleado(nominaEmpleadoEntity.getIdNominaEmpleado(), 1);

            List<PercepcionCFDI> listadoPercepcionesCFDI = new ArrayList<>();

            for (ConceptosNominaEmpleadosEntity conceptosNominaEmpleadosEntity : listadoPercepcionesEntity) {
                PercepcionCFDI percepcionCFDI = new PercepcionCFDI();
                percepcionCFDI.setClave(validarMinimoClave(conceptosNominaEmpleadosEntity.getClave()));
                percepcionCFDI.setConcepto(conceptosNominaEmpleadosEntity.getConcepto());
                percepcionCFDI.setImporteExcento(conceptosNominaEmpleadosEntity.getImporteExcento());
                percepcionCFDI.setImporteGravado(conceptosNominaEmpleadosEntity.getImporteGravado());
                percepcionCFDI.setTipoPercepcion(conceptosNominaEmpleadosEntity.getTipoSat());
                listadoPercepcionesCFDI.add(percepcionCFDI);

            }

            if (listadoDeducionesCFDI.size() == 0) {
                DeduccionCFDI deduccionCFDI = new DeduccionCFDI();
                deduccionCFDI.setClave("052");
                deduccionCFDI.setConcepto("I.S.R.");
                deduccionCFDI.setImporteGravado(BigDecimal.ZERO);
                deduccionCFDI.setImporteExcento(BigDecimal.ZERO);
                deduccionCFDI.setTipoPercepcion("002");
                listadoDeducionesCFDI.add(deduccionCFDI);
            }

            datosCFDINomina.setDeducciones(listadoDeducionesCFDI);
            datosCFDINomina.setPercepciones(listadoPercepcionesCFDI);

            listadoDatosCFDINomina.add(datosCFDINomina);

        }

        for (DatosCFDINomina datosCFDINominaTimbrar : listadoDatosCFDINomina) {

            if (datosCFDINominaTimbrar.getCurp().length() > 0) {
                DatosCFDITimbrado datosCFDITimbrado = timbradoService.generarProductoNominaCFDI(datosCFDINominaTimbrar);
                if (datosCFDITimbrado.getIdComprobante() != null) {
                    timbradoResult.setTotalFallos(timbradoResult.getTotalFallos() + 1);
                } else {
                    timbradoResult.setTotalTimbradas(timbradoResult.getTotalTimbradas() + 1);

                    NominaEmpleadoEntity nominaEmpleado = nominaEmpleadoRepository.obtenerPorId(new Integer(datosCFDINominaTimbrar.getFolio()));
                    nominaEmpleado.setIdComprobante(datosCFDITimbrado.getIdComprobante());
                    nominaEmpleadoRepository.actualizar(nominaEmpleado);

                }
            } else {
                timbradoResult.setTotalFallos(timbradoResult.getTotalFallos() + 1);
            }

        }

        if (timbradoResult.getTotalTimbradas().equals(timbradoResult.getTotalNominasEncontradas())) {

            JSFUtils.infoMessage("Timbrado", "Se realizo el timbrado de  la nomina completa");

        } else {
            JSFUtils.errorMessage("Timbrado", "Se realizo el timbrado de  la nomina de manera parcial se encontraron registros con errores");
        }

        return timbradoResult;

    }

    private String validarMinimoClave(String clave) {

        while (clave.length() < 3) {
            clave = "0" + clave;

        }

        return clave;

    }

    private Integer calcularAnyosAntiguedad(Date ingreso) {

        if (ingreso != null) {
            Date d = new Date();
            int anyoActual = d.getYear();
            int anyoIngreso = ingreso.getYear();

            return anyoActual - anyoIngreso;
        } else {
            return 1;
        }

    }

}
