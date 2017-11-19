
package mx.gob.saludtlax.rh.nomina.productosnomina;

/**
 * @author José Pablo
 */
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.persistencia.BancoSatEntity;
import mx.gob.saludtlax.rh.persistencia.BancoSatRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoRepository;
import mx.gob.saludtlax.rh.persistencia.CuentasBancariasEntity;
import mx.gob.saludtlax.rh.persistencia.CuentasBancariasRepository;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoEntity;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoRepository;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.PagoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.PagoNominaRepository;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaRepository;
import mx.gob.saludtlax.rh.persistencia.SubFuenteFinanciamientoTempEntity;
import mx.gob.saludtlax.rh.persistencia.SubFuenteFinanciamientoTempRepository;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@Stateless
public class PagoNominaService {
    @Inject
    private PagoNominaRepository pagoNominaRepository;
    @Inject
    private SubFuenteFinanciamientoTempRepository subfuenteFinanciamientoRepository;
    @Inject
    private FuenteFinanciamientoRepository fuenteFinanciamientoRepository;
    @Inject
    private ProductoNominaRepository productoNominaRepository;
    @Inject
    private NominaEmpleadoRepository nominaEmpleadoRepository;
    @Inject
    private BancoSatRepository bancoSatRepository;
    @Inject
    private CuentasBancariasRepository cuentasBancariasRepository;
    @Inject
    private ConfiguracionPresupuestoRepository configuracionPresupuestoRepository;

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;
    private static final Logger LOGGER = Logger.getLogger(PagoNominaService.class);

    public List<SubFuenteFinanciamientoTempEntity> obtenerSubfuentesPorProductoNomina(ProductoNominaDTO productoNomina) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session
                .createSQLQuery("" + " SELECT " + "	id_subfuente_financiamiento " + " FROM nomina_empleado " + " WHERE "
                        + "	id_producto_nomina = :idProductoNomina " + " GROUP BY " + "	id_subfuente_financiamiento ")
                .setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
        @SuppressWarnings("unchecked")
        List<Integer> result = query.list();
        List<SubFuenteFinanciamientoTempEntity> subfuenteFinanciamientoList = new ArrayList<>();
        for (Integer idSubfuenteFinanciamiento : result) {
            if (idSubfuenteFinanciamiento != null) {
                subfuenteFinanciamientoList.add(subfuenteFinanciamientoRepository.obtenerPorId(idSubfuenteFinanciamiento));
            }
        }
        return subfuenteFinanciamientoList;
    }

    public List<PagoNominaEntity> crearPagosNomina(ProductoNominaDTO productoNomina) {
        List<SubFuenteFinanciamientoTempEntity> subfuenteFinanciamientoList = obtenerSubfuentesPorProductoNomina(productoNomina);
        ProductoNominaEntity productoNominaEntity = productoNominaRepository.obtenerPorId(productoNomina.getIdProductoNomina());
        List<PagoNominaEntity> pagosNominaList = new ArrayList<>();
        PagoNominaEntity pagoNominaEntity = null;
        BancoSatEntity banco = bancoSatRepository.obtenerPorId(7);
        LOGGER.info("fuenteFinanciamientoRepository:: " + fuenteFinanciamientoRepository);
        for (SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento : subfuenteFinanciamientoList) {
            pagoNominaEntity = new PagoNominaEntity();
            LOGGER.info("subfuenteFinanciamiento.getIdFuenteFinanciamiento():: " + subfuenteFinanciamiento);
            LOGGER.info("subfuenteFinanciamiento.getIdFuenteFinanciamiento():: " + subfuenteFinanciamiento.getIdFuenteFinanciamiento());
            FuenteFinanciamientoEntity fuenteFinanciamientoEntity = null;
            try {
                fuenteFinanciamientoEntity = fuenteFinanciamientoRepository.obtenerPorId(subfuenteFinanciamiento.getIdFuenteFinanciamiento());
            } catch (Exception e) {
                LOGGER.error("LOGGER:: " + e.getMessage());
            }
            LOGGER.info("fuenteFinanciamientoEntity:: " + fuenteFinanciamientoEntity);

            pagoNominaEntity.setFuenteFinanciamiento(fuenteFinanciamientoEntity);
            pagoNominaEntity.setProductoNomina(productoNominaEntity);
            pagoNominaEntity.setSubfuenteFinanciamiento(subfuenteFinanciamiento);
            pagoNominaEntity.setFechaPago(productoNominaEntity.getFinPeriodo());
            pagoNominaEntity.setBanco(banco);
            pagoNominaRepository.crear(pagoNominaEntity);
            List<NominaEmpleadoEntity> nominaEmpleadoList = nominaEmpleadoRepository.obtenerNominaEmpleadoPorSubfuente(productoNominaEntity,
                    subfuenteFinanciamiento);
            for (NominaEmpleadoEntity nominaEmpleado : nominaEmpleadoList) {
                nominaEmpleado.setPagoNomina(pagoNominaEntity);
                nominaEmpleadoRepository.actualizar(nominaEmpleado);
            }
            pagosNominaList.add(pagoNominaEntity);
        }
        return pagosNominaList;
    }

    public List<PagoNominaEntity> obtenerPagosNomina(ProductoNominaDTO productoNomina) {
        ProductoNominaEntity productoNominaEntity = productoNominaRepository.obtenerPorId(productoNomina.getIdProductoNomina());
        return pagoNominaRepository.obtenerPagosNomina(productoNominaEntity);
    }

    public PagoNominaDTO obtenerPagoNomina(PagoNominaDTO pagoNomina) {
        PagoNominaEntity pagoNominaEntity = pagoNominaRepository.obtenerPorId(pagoNomina.getIdPagoNomina());
        pagoNomina = toPagoNominaDTO(pagoNominaEntity);
        pagoNomina.setListaRfc(obtenerListaRfc(pagoNominaEntity));
        return pagoNomina;
    }

    public PagoNominaDTO crearPagoNomina(PagoNominaDTO pagoNomina) {
        PagoNominaEntity pagoNominaEntity = new PagoNominaEntity();
        if (pagoNomina.getIdProductoNomina() != null) {
            if (pagoNomina.getIdFuenteFinanciamiento() != null) {
                FuenteFinanciamientoEntity fuenteFinanciamientoEntity = fuenteFinanciamientoRepository.obtenerPorId(pagoNomina.getIdFuenteFinanciamiento());
                pagoNominaEntity.setFuenteFinanciamiento(fuenteFinanciamientoEntity);
            }
            ProductoNominaEntity productoNominaEntity = productoNominaRepository.obtenerPorId(pagoNomina.getIdProductoNomina());
            pagoNominaEntity.setProductoNomina(productoNominaEntity);

            if (pagoNomina.getIdSubfuenteFinanciamiento() != null) {
                SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento = subfuenteFinanciamientoRepository
                        .obtenerPorId(pagoNomina.getIdSubfuenteFinanciamiento());
                pagoNominaEntity.setSubfuenteFinanciamiento(subfuenteFinanciamiento);
            }

            if (pagoNomina.getIdBanco() != null) {
                BancoSatEntity bancoSatEntity = bancoSatRepository.obtenerPorId(pagoNomina.getIdBanco());
                pagoNominaEntity.setBanco(bancoSatEntity);
            }

            if (pagoNomina.getIdCuentaBancaria() != null) {
                CuentasBancariasEntity cuentasBancariasEntity = cuentasBancariasRepository.obtenerPorId(pagoNomina.getIdCuentaBancaria());
                pagoNominaEntity.setCuentaBancaria(cuentasBancariasEntity);
            }

            pagoNominaEntity.setFechaPago(pagoNomina.getFechaPago());
            pagoNominaRepository.crear(pagoNominaEntity);

            List<String> listaRfc = rfcValidos(pagoNomina.getListaRfc().split("\\r?\\n"));

            List<NominaEmpleadoEntity> nominaEmpleadoList = obtenerNominaEmpleadoPorRfc(listaRfc, productoNominaEntity);
            for (NominaEmpleadoEntity nominaEmpleado : nominaEmpleadoList) {
                nominaEmpleado.setPagoNomina(pagoNominaEntity);
                nominaEmpleadoRepository.actualizar(nominaEmpleado);
            }
            if (pagoNomina.getAplicarPadron()) {
                aplicarPadron(pagoNominaEntity);
            }
            return toPagoNominaDTO(pagoNominaEntity);
        } else {
            throw new SistemaException("Ocurrio al crear Pago", SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }

    }

    private void aplicarPadron(PagoNominaEntity pagoNomina) {
        List<NominaEmpleadoEntity> nominaEmpleadoList = nominaEmpleadoRepository.obtenerNominaEmpleadoPorPagoNominaEntity(pagoNomina);
        SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento = pagoNomina.getSubfuenteFinanciamiento();
        for (NominaEmpleadoEntity nominaEmpleado : nominaEmpleadoList) {
            ConfiguracionPresupuestoEntity configuracionPresupuestoEntity = nominaEmpleado.getIdConfiguracionPresupuestal();
            configuracionPresupuestoEntity.setSubfuenteFinanciamiento(subfuenteFinanciamiento);
            configuracionPresupuestoRepository.actualizar(configuracionPresupuestoEntity);
        }
    }

    private List<String> rfcValidos(String rfcArray[]) {
        List<String> rfcValidos = new ArrayList<>();
        for (String rfc : rfcArray) {
            if (ValidacionUtil.validarRfc(rfc)) {

                rfcValidos.add(rfc);
            }
        }
        return rfcValidos;
    }

    public PagoNominaDTO actualizarPagoNomina(PagoNominaDTO pagoNomina) {
        PagoNominaEntity pagoNominaEntity = pagoNominaRepository.obtenerPorId(pagoNomina.getIdPagoNomina());
        ProductoNominaEntity productoNominaEntity = productoNominaRepository.obtenerPorId(pagoNomina.getIdProductoNomina());
        if (pagoNomina.getIdFuenteFinanciamiento() != null) {
            FuenteFinanciamientoEntity fuenteFinanciamientoEntity = fuenteFinanciamientoRepository.obtenerPorId(pagoNomina.getIdFuenteFinanciamiento());
            pagoNominaEntity.setFuenteFinanciamiento(fuenteFinanciamientoEntity);
        }
        if (pagoNomina.getIdSubfuenteFinanciamiento() != null) {
            SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento = subfuenteFinanciamientoRepository
                    .obtenerPorId(pagoNomina.getIdSubfuenteFinanciamiento());
            pagoNominaEntity.setSubfuenteFinanciamiento(subfuenteFinanciamiento);
        }
        pagoNominaEntity.setFechaPago(pagoNomina.getFechaPago());
        if (pagoNomina.getIdBanco() != null) {
            BancoSatEntity banco = bancoSatRepository.obtenerPorId(pagoNomina.getIdBanco());
            pagoNominaEntity.setBanco(banco);
        }
        if (pagoNomina.getIdCuentaBancaria() != null) {
            CuentasBancariasEntity cuentasBancariasEntity = cuentasBancariasRepository.obtenerPorId(pagoNomina.getIdCuentaBancaria());
            pagoNominaEntity.setCuentaBancaria(cuentasBancariasEntity);
        }
        pagoNominaRepository.actualizar(pagoNominaEntity);

        String rfcArrayDto[] = pagoNomina.getListaRfc().split("\\r?\\n");
        String rfcArrayEntity[] = obtenerListaRfc(pagoNominaEntity).split("\\r?\\n");
        List<String> rfcListNuevos = new ArrayList<>();
        List<String> rfcListEliminar = new ArrayList<>();
        List<String> rfcListIgual = new ArrayList<>();

        Boolean nuevo = null;
        for (String rfcDto : rfcArrayDto) {
            nuevo = Boolean.TRUE;
            for (String rfcEntity : rfcArrayEntity) {
                if (rfcDto.equals(rfcEntity)) {
                    nuevo = Boolean.FALSE;
                    rfcListIgual.add(rfcEntity);
                    break;
                }
            }
            if (nuevo) {
                rfcListNuevos.add(rfcDto);
            }
        }

        rfcListIgual.addAll(rfcListNuevos);
        for (String rfcEntity : rfcArrayEntity) {
            if (!rfcListIgual.contains(rfcEntity)) {
                rfcListEliminar.add(rfcEntity);
            }
        }
        String[] listaRfc = new String[rfcListNuevos.size()];
        listaRfc = rfcListNuevos.toArray(listaRfc);
        List<String> rfcListNuevosValidos = rfcValidos(listaRfc);

        System.out.println("rfcListIgual:: " + rfcListIgual);
        System.out.println("rfcListNuevos:: " + rfcListNuevosValidos);
        System.out.println("rfcListEliminar:: " + rfcListEliminar);
        List<NominaEmpleadoEntity> nominaEmpleadoList = obtenerNominaEmpleadoPorRfc(rfcListNuevosValidos, productoNominaEntity);
        for (NominaEmpleadoEntity nominaEmpleado : nominaEmpleadoList) {
            nominaEmpleado.setPagoNomina(pagoNominaEntity);
            nominaEmpleadoRepository.actualizar(nominaEmpleado);
        }
        nominaEmpleadoList = obtenerNominaEmpleadoPorRfc(rfcListEliminar, productoNominaEntity);
        for (NominaEmpleadoEntity nominaEmpleado : nominaEmpleadoList) {
            nominaEmpleado.setPagoNomina(null);
            nominaEmpleadoRepository.actualizar(nominaEmpleado);
        }
        System.out.println("pagoNomina.getAplicarPadron():: " + pagoNomina.getAplicarPadron());
        if (pagoNomina.getAplicarPadron()) {
            aplicarPadron(pagoNominaEntity);
        }
        return toPagoNominaDTO(pagoNominaEntity);
    }

    public PagoNominaDTO toPagoNominaDTO(PagoNominaEntity pagoNominaEntity) {
        PagoNominaDTO pagoNomina = new PagoNominaDTO();
        if (pagoNominaEntity.getBanco() != null) {
            pagoNomina.setIdBanco(pagoNominaEntity.getBanco().getIdBanco());
            pagoNomina.setBanco(pagoNominaEntity.getBanco().getNombreCorto());
        }
        if (pagoNominaEntity.getCuentaBancaria() != null) {
            pagoNomina.setIdCuentaBancaria(pagoNominaEntity.getCuentaBancaria().getIdCuentaBancaria());
            pagoNomina.setCuentaBancaria(pagoNominaEntity.getCuentaBancaria().getDescripcion());
        }
        if (pagoNominaEntity.getFuenteFinanciamiento() != null) {
            pagoNomina.setIdFuenteFinanciamiento(pagoNominaEntity.getFuenteFinanciamiento().getIdFuenteFinanciamiento());
            pagoNomina.setFuenteFinanciamiento(pagoNominaEntity.getFuenteFinanciamiento().getDescripcion());
        }
        if (pagoNominaEntity.getSubfuenteFinanciamiento() != null) {
            pagoNomina.setIdSubfuenteFinanciamiento(pagoNominaEntity.getSubfuenteFinanciamiento().getIdSubfuenteFinanciamiento());
            pagoNomina.setSubfuenteFinanciamiento(pagoNominaEntity.getSubfuenteFinanciamiento().getDescripcion());
        }
        pagoNomina.setFechaPago(pagoNominaEntity.getFechaPago());
        pagoNomina.setIdPagoNomina(pagoNominaEntity.getIdPagoNomina());
        pagoNomina.setIdProductoNomina(pagoNominaEntity.getProductoNomina().getIdProductoNomina());
        return pagoNomina;
    }

    public List<NominaEmpleadoEntity> obtenerNominaEmpleadoPorRfc(List<String> listaRfc, ProductoNominaEntity productoNominaEntity) {
        List<NominaEmpleadoEntity> nominaEmpleadoList = new ArrayList<>();
        for (String rfc : listaRfc) {
            Integer idNominaEmpleado = obtenerIdNominaEmpleado(rfc, productoNominaEntity.getIdProductoNomina());
            if (idNominaEmpleado != null) {
                NominaEmpleadoEntity nominaEmpleado = nominaEmpleadoRepository.obtenerPorId(idNominaEmpleado);
                nominaEmpleadoList.add(nominaEmpleado);
            }
        }
        return nominaEmpleadoList;
    }

    private Integer obtenerIdNominaEmpleado(String rfc, Integer idProductoNomina) {
        rfc = rfc.replaceAll("[\n\r]", "");
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("" + " SELECT                                                            "
                + " n.id_nomina_empleado                                              " + " FROM nomina_empleado AS n                                         "
                + " INNER JOIN empleados AS e                                         " + " ON e.id_empleado = n.id_empleado                                  "
                + " WHERE                                                             " + " n.id_producto_nomina = :idProductoNomina                          "
                + " AND                                                               " + " e.rfc = :rfc                                                      ")
                .setParameter("idProductoNomina", idProductoNomina).setParameter("rfc", rfc);
        Integer idNominaEmpleado = (Integer) query.uniqueResult();
        return idNominaEmpleado;
    }

    private String obtenerListaRfc(PagoNominaEntity pagoNominaEntity) {
        List<NominaEmpleadoEntity> nominaEmpleadoList = nominaEmpleadoRepository.obtenerNominaEmpleadoPorPagoNominaEntity(pagoNominaEntity);
        return nominaEmpleadoEntityToRfcStr(nominaEmpleadoList);
    }

    private String nominaEmpleadoEntityToRfcStr(List<NominaEmpleadoEntity> nominaEmpleadoList) {
        StringBuilder listaRfc = new StringBuilder();
        for (NominaEmpleadoEntity nominaEmpleado : nominaEmpleadoList) {
            listaRfc.append(nominaEmpleado.getIdEmpleado().getRfc());
            listaRfc.append("\n");
        }
        return listaRfc.toString();
    }

    public void eliminarPagoNomina(PagoNominaDTO pagoNomina) {
        PagoNominaEntity pagoNominaEntity = pagoNominaRepository.obtenerPorId(pagoNomina.getIdPagoNomina());
        List<NominaEmpleadoEntity> nominaEmpleadoList = nominaEmpleadoRepository.obtenerNominaEmpleadoPorPagoNominaEntity(pagoNominaEntity);
        for (NominaEmpleadoEntity nominaEmpleado : nominaEmpleadoList) {
            nominaEmpleado.setPagoNomina(null);
            nominaEmpleadoRepository.actualizar(nominaEmpleado);
        }
        pagoNominaRepository.eliminar(pagoNominaEntity);
    }

    public PagoNominaDTO obtenerListaRfcSinPago(ProductoNominaDTO productoNomina) {
        ProductoNominaEntity productoNominaEntity = productoNominaRepository.obtenerPorId(productoNomina.getIdProductoNomina());
        List<NominaEmpleadoEntity> nominaEmpleadoList = nominaEmpleadoRepository.obtenerNominaEmpleadoSinPago(productoNominaEntity);
        if (nominaEmpleadoList.isEmpty()) {
            return null;
        }
        PagoNominaDTO pagoNomina = new PagoNominaDTO();
        pagoNomina.setListaRfc(nominaEmpleadoEntityToRfcStr(nominaEmpleadoList));
        return pagoNomina;
    }
}