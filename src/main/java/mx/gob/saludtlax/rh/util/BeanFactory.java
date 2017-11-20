/*
 * BeanFactory.java
 * Creado el 19/nov/2017 05:44:35 p. m.
 *
 */

package mx.gob.saludtlax.rh.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

/**
 * 
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class BeanFactory {

    private static final Logger LOGGER = Logger
            .getLogger(BeanFactory.class.getName());

    private static final String CONSULTA_NOMINA_SERVICE_BEAN = "java:module/ConsultaNominaService";
    private static final String CONSULTA_COMISIONADO_LICENCIA_SERVICE_BEAN = "java:module/MovimientoEmpleadoReporteService";
    private static final String SEGURO_POPULAR_REPORTE_BEAN = "java:module/SeguroPopularReporteEJB";
    private static final String PROYECCIONES_PRESUPUESTALES_BEAN = "java:module/ProyeccionesPresupuestalesEJB";
    private static final String DETALLE_EMPLEADO_BEAN = "java:module/DetalleEmpleadoEJB";
    private static final String PRODUCTO_NOMINA_BEAN = "java:module/ProductoNominaEJB";
    private static final String HISTORIAL_PAGO_BEAN = "java:module/HistorialPagoEJB";
    private static final String RELACION_PERSONAL_SUPLENTE_BEAN = "java:module/RelacionPersonalSuplenteEJB";
    private static final String DISPERSION_BEAN = "java:module/DispersionEJB";
    private static final String PAGO_GENERAL_BEAN = "java:module/PagoGeneralReporteEJB";
    private static final String DISTRIBUCION_PRESUPUESTO_BEAN = "java:module/DistribucionPresupuestoEJB";

    /**
     * No se permite crear intancias de esta clase porque su función es
     * construir u obtener instancias de EJB.
     */
    private BeanFactory() {
    }

    /**
     * Este método permite obtener un EJB empleado JNDI.
     * Esto es valido cuando sea creado una instancia unsando la palabra clave
     * <code>new</code> en vez de realizar una inyección por lo tanto el
     * servidor de aplicaciones no es administrada dicha instancia y no incluye
     * dependencia al EJB.
     * 
     * @param clase
     *            si existe una interfaz se emplea la interfaz sino la clase EJB.
     * @return una intancia del EJB, en caso contrario un null.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clase) {
        String bean = "";
        try {
            switch (clase.getName()) {
                case "mx.gob.saludtlax.rh.nomina.reportes.pagogeneral.PagoGeneralReporte":
                    bean = PAGO_GENERAL_BEAN;
                    break;
                case "mx.gob.saludtlax.rh.siif.ConsultaNominaService":
                    bean = CONSULTA_NOMINA_SERVICE_BEAN;
                    break;
                case "mx.gob.saludtlax.rh.empleados.movimientos.reportes.MovimientoEmpleadoReporteService":
                    bean = CONSULTA_COMISIONADO_LICENCIA_SERVICE_BEAN;
                    break;
                case "mx.gob.saludtlax.rh.siif.seguropopular.SeguroPopularReporte":
                    bean = SEGURO_POPULAR_REPORTE_BEAN;
                    break;
                case "mx.gob.saludtlax.rh.presupuesto.ProyeccionesPresupuestalesEJB":
                    bean = PROYECCIONES_PRESUPUESTALES_BEAN;
                    break;
                case "mx.gob.saludtlax.rh.empleados.detallesempleado.DetalleEmpleado":
                    bean = DETALLE_EMPLEADO_BEAN;
                    break;
                case "mx.gob.saludtlax.rh.nomina.productosnomina.ProductoNomina":
                    bean = PRODUCTO_NOMINA_BEAN;
                    break;
                case "mx.gob.saludtlax.rh.nomina.historialpago.HistorialPago":
                    bean = HISTORIAL_PAGO_BEAN;
                    break;
                case "mx.gob.saludtlax.rh.empleados.suplencia.relacionpersonal.RelacionPersonalSuplente":
                    bean = RELACION_PERSONAL_SUPLENTE_BEAN;
                    break;
                case "mx.gob.saludtlax.rh.nomina.reportes.dispersion.Dispersion":
                    bean = DISPERSION_BEAN;
                    break;
                case "mx.gob.saludtlax.rh.presupuesto.DistribucionPresupuestoEJB":
                    bean = DISTRIBUCION_PRESUPUESTO_BEAN;
                    break;
                default:
                    return null;
            }

            Context initContext = new InitialContext();
            return (T) initContext.lookup(bean);
        } catch (NamingException ex) {
            LOGGER.errorv(PlantillaMensaje.REPORTE_ERROR_BEAN_NO_ENCONTRADO,
                    bean, ex.getCause());
            return null;
        }
    }
}
