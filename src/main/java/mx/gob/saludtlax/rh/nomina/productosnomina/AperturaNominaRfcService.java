/*
 * AperturaNominaRfcService.java
 * Creado el 01/Jan/2017 2:43:40 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaBitacoraAperturaEntity;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaBitacoraEventoEntity;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaBitacoraRepository;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.util.Configuracion;

/**
 * Esta clase se encarga de aperturar un producto de nómina apartir de un
 * archivo con una lista de RFC.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class AperturaNominaRfcService implements Serializable {

    private static final long serialVersionUID = 2094074994987836067L;
    private static final Logger LOGGER = Logger.getLogger(AperturaNominaRfcService.class.getName());
    private static final String OBTENER_RFC_EMPLEADOS_ACTIVOS = 
            "SELECT conf.empleado.rfc "
            + " FROM ConfiguracionPresupuestoEntity AS conf "
            + " WHERE conf.estatus.id = 1 "
            + " AND conf.empleado.rfc IN (:rfcEmpleados)";
    private static final String OBTENER_RFC_EMPLEADOS_TIPO_CONTRATACION = 
            "SELECT conf.empleado.rfc "
            + " FROM ConfiguracionPresupuestoEntity AS conf "
            + " WHERE conf.tipoContratacion.id = :idTipoContratacion"
            + " AND conf.empleado.rfc IN (:rfcEmpleados)";
    private static final String USP_ACTUALIZAR_NOMINA_EMPLEADO = 
            "CALL usp_actualizar_estructura_nomina_por_rfc(:idProductoNomina)";
    private static final String APERTURAR_PRODUCTO_DE_NOMINA = 
            "UPDATE ProductoNominaEntity AS pne"
            + " SET pne.estatusProductoNomina.idEstatusConceptoNomina = 2"
            + " WHERE pne.idProductoNomina = :idProductoNomina";

    private static final short RFC_NO_ACTIVAS = 0;
    private static final short RFC_NO_COINCIDE_TIPO_CONTRATACION = 1;

    	@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager em;
    @Inject
    private EmpleadoRepository empleadoRepository;
    @Inject
    private ProductoNominaBitacoraRepository productosNominaBitacorasAperturasRepository;
    
    protected void abrirProductoNomina(final ProductoNominaDTO productoNomina, final List<String> listaRfc, final Integer idBitacora) {
        List<String> listaRfcActivos = obtenerRfcActivos(listaRfc);
        List<String> listaTipoContratacion = obtenerTipoContratacion(
                productoNomina.getIdTipoContratacion(), listaRfcActivos
        );
        insertarNominaEmpleado(productoNomina.getIdProductoNomina(), listaTipoContratacion);
        int totalActualizado = actualizarNominaEmpleado(productoNomina.getIdProductoNomina());
        int aperturarProductoNomina = aperturarProductoNomina(productoNomina.getIdProductoNomina());
        registrarEnBitacoraListaRfcRechazadas(idBitacora, listaRfc, 
                listaRfcActivos, RFC_NO_ACTIVAS
        );
        registrarEnBitacoraListaRfcRechazadas(idBitacora, listaRfcActivos, 
                listaTipoContratacion, RFC_NO_COINCIDE_TIPO_CONTRATACION);
        registrarEnBitacoraEvento(idBitacora,
                AperturaNominaRfcBitacoraCategoria.INFORMACION,
                "Número de RFC encontrados: {0}", listaRfc.size()
        );
        registrarEnBitacoraEvento(idBitacora, 
                AperturaNominaRfcBitacoraCategoria.INFORMACION, 
                "Número de RFC activos: {0}", listaRfcActivos.size()
        );
        registrarEnBitacoraEvento(idBitacora,
                AperturaNominaRfcBitacoraCategoria.INFORMACION,
                "Número de RFC con tipo de contratación que coninciden: {0}",
                listaTipoContratacion.size()
        );
        registrarEnBitacoraEvento(idBitacora,
                AperturaNominaRfcBitacoraCategoria.INFORMACION,
                "Se agregaron al producto de nómina {0} RFC.", totalActualizado
        );
        registrarEnBitacoraEvento(idBitacora,
                AperturaNominaRfcBitacoraCategoria.INFORMACION,
                "Aperturar producto de nomina {0}: {1}.",
                productoNomina.getIdProductoNomina(), 
                aperturarProductoNomina == 1 ? "HECHO" : "FALLO"
        );
        LOGGER.debug("Apertura del producto de nomina");
    }

    protected Integer obtenerIdBitacora(Integer idUsuario) {
        UsuarioEntity usuario = em.find(UsuarioEntity.class, idUsuario);
        ProductoNominaBitacoraAperturaEntity bitacora = new ProductoNominaBitacoraAperturaEntity();
        bitacora.setIdBitacora(null);
        bitacora.setUsuario(usuario);
        productosNominaBitacorasAperturasRepository.crear(bitacora);
        return bitacora.getIdBitacora();
    }
    
    protected AperturaNominaRfcBitacoraDTO obtenerBitacora(Integer idBitacora) {
        ProductoNominaBitacoraAperturaEntity bitacoraEntity = productosNominaBitacorasAperturasRepository.obtenerPorId(idBitacora);
        AperturaNominaRfcBitacoraDTO bitacoraDto = convertirBitacoraEntidadADto(bitacoraEntity);
        List<AperturaNominaRfcBitacoraEventoDTO> eventos = bitacoraDto.getEventos();
        
        for (ProductoNominaBitacoraEventoEntity eventoEntity : productosNominaBitacorasAperturasRepository.obtenerEventosPorIdBitacora(idBitacora)) {
            AperturaNominaRfcBitacoraEventoDTO eventoDto = convertirEventoEntidadADto(eventoEntity);

            if(eventoDto != null) {
                eventos.add(eventoDto);
            }
        }
        
        return bitacoraDto;
    }

    protected void registrarEnBitacoraEvento(Integer idBitacora, AperturaNominaRfcBitacoraCategoria categoria, String patron, Object... argumentos) {
        String mensaje = MessageFormat.format(patron, argumentos);
        registrarEnBitacoraEvento(idBitacora, categoria, mensaje);
    }
    
    protected void registrarEnBitacoraEvento(Integer idBitacora, AperturaNominaRfcBitacoraCategoria categoria, String mensaje) {
        ProductoNominaBitacoraAperturaEntity bitacoraEntity = productosNominaBitacorasAperturasRepository.obtenerPorId(idBitacora);
        ProductoNominaBitacoraEventoEntity eventoEntity = new ProductoNominaBitacoraEventoEntity();
        eventoEntity.setIdEvento(null);
        eventoEntity.setBitacora(bitacoraEntity);
        eventoEntity.setCategoria(categoria);
        eventoEntity.setMensaje(mensaje);

        em.persist(eventoEntity);
    }
    
    private List<String> obtenerRfcActivos(List<String> listaRfc) {
        TypedQuery<String> query = em.createQuery(OBTENER_RFC_EMPLEADOS_ACTIVOS,
                String.class);
        query.setParameter("rfcEmpleados", listaRfc);
        return query.getResultList();
    }

    private List<String> obtenerTipoContratacion(Integer idTipoContratacion,
            List<String> listaRfc) {
        TypedQuery<String> query = em.createQuery(
                OBTENER_RFC_EMPLEADOS_TIPO_CONTRATACION, String.class);
        query.setParameter("idTipoContratacion", idTipoContratacion);
        query.setParameter("rfcEmpleados", listaRfc);
        return query.getResultList();
    }

    private void insertarNominaEmpleado(Integer idProductoNomina, List<String> listaTipoContratacion) {
        ProductoNominaEntity productoNominaEntity = em.find(ProductoNominaEntity.class, idProductoNomina);
        
        for (String rfc : listaTipoContratacion) {
            EmpleadoEntity empleadoEntity = empleadoRepository.obtenerEmpleadoRfc(rfc);
            NominaEmpleadoEntity nominaEmpleadoEntity = new NominaEmpleadoEntity();
            nominaEmpleadoEntity.setIdNominaEmpleado(null);
            nominaEmpleadoEntity.setIdProductoNomina(productoNominaEntity);
            nominaEmpleadoEntity.setIdEmpleado(empleadoEntity);
            em.persist(nominaEmpleadoEntity);
        }
    }
    
    private int actualizarNominaEmpleado(Integer idProductoNomina) {
        Query query = em.createNativeQuery(USP_ACTUALIZAR_NOMINA_EMPLEADO);
        query.setParameter("idProductoNomina", idProductoNomina);
        return query.executeUpdate();
    }

    private int aperturarProductoNomina(Integer idProductoNomina) {
        Query query = em.createQuery(APERTURAR_PRODUCTO_DE_NOMINA);
        query.setParameter("idProductoNomina", idProductoNomina);
        return query.executeUpdate();
    }

    private void registrarEnBitacoraListaRfcRechazadas(final Integer idBitacora, 
            final List<String> origen, final List<String> coincidencias,
            final short tipoEvento) {
       String patron = "El RFC \"{0}\" no se ha registrado porque {1}.";
       
        for(String rfc : origen) {
            if(!coincidencias.contains(rfc)) {
                String evento;
                switch(tipoEvento) {
                    case RFC_NO_ACTIVAS:
                        evento = "el empleado no está activo";
                        break;
                    case RFC_NO_COINCIDE_TIPO_CONTRATACION:
                        evento = "no coincide con el tipo de la contratación del producto de nómina";
                        break;
                    default:
                        evento = "ocurrio un error inesperado";
                        break;
                }
                String mensaje = MessageFormat.format(patron, rfc, evento);
                registrarEnBitacoraEvento(idBitacora, AperturaNominaRfcBitacoraCategoria.ERROR, mensaje);
            }
        }
    }

    private AperturaNominaRfcBitacoraDTO convertirBitacoraEntidadADto(ProductoNominaBitacoraAperturaEntity entidad) {
        if (entidad == null) {
            return null;
        }

        Calendar fechaHora = Calendar.getInstance();
        fechaHora.setTime(entidad.getFecha());
        fechaHora.set(Calendar.HOUR_OF_DAY, 0);
        fechaHora.set(Calendar.MINUTE, 0);
        fechaHora.set(Calendar.SECOND, 0);

        Calendar hora = Calendar.getInstance();
        hora.setTime(entidad.getHora());
        fechaHora.add(Calendar.HOUR_OF_DAY, hora.get(Calendar.HOUR_OF_DAY));
        fechaHora.add(Calendar.MINUTE, hora.get(Calendar.MINUTE));
        fechaHora.add(Calendar.SECOND, hora.get(Calendar.SECOND));
        
        AperturaNominaRfcBitacoraDTO dto = new AperturaNominaRfcBitacoraDTO();
        dto.setIdBitacora(entidad.getIdBitacora());
        dto.setIdUsuario(entidad.getUsuario() != null ? entidad.getUsuario().getIdUsuario() : null);
        dto.setFechaHora(fechaHora.getTime());

        return dto;
    }

    private AperturaNominaRfcBitacoraEventoDTO convertirEventoEntidadADto(ProductoNominaBitacoraEventoEntity entidad) {
        if (entidad == null) {
            return null;
        }
        
        AperturaNominaRfcBitacoraEventoDTO dto = new AperturaNominaRfcBitacoraEventoDTO();
        
        Calendar fechaHora = Calendar.getInstance();
        fechaHora.setTime(entidad.getFecha());
        fechaHora.set(Calendar.HOUR_OF_DAY, 0);
        fechaHora.set(Calendar.MINUTE, 0);
        fechaHora.set(Calendar.SECOND, 0);

        Calendar hora = Calendar.getInstance();
        hora.setTime(entidad.getHora());
        fechaHora.add(Calendar.HOUR_OF_DAY, hora.get(Calendar.HOUR_OF_DAY));
        fechaHora.add(Calendar.MINUTE, hora.get(Calendar.MINUTE));
        fechaHora.add(Calendar.SECOND, hora.get(Calendar.SECOND));
        
        dto.setIdEvento(entidad.getIdEvento());
        dto.setCategoria(entidad.getCategoria());
        dto.setFechaHora(fechaHora.getTime());
        dto.setMensaje(entidad.getMensaje());
        
        return dto;
    }

}
