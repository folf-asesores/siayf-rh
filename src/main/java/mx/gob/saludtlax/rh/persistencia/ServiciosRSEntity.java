
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@NamedQueries({
        @NamedQuery(name = ServiciosRSEntity.OBTENER_LISTADO, query = "SELECT s FROM ServiciosRSEntity s"),
        @NamedQuery(name = ServiciosRSEntity.BUSCAR_SERVICIO_ACTIVO, query = "SELECT s FROM ServiciosRSEntity s WHERE s.activo = true AND s.servicio =:servicioEnum"),
        @NamedQuery(name = ServiciosRSEntity.BUSCAR_SERVICIO_ACTIVO_ID, query = "SELECT s FROM ServiciosRSEntity s WHERE s.activo = true AND s.servicio =:servicioEnum AND s.idServicioRS <> :idServicio"), })
@Entity
@Table(name = "servicios_rs")
public class ServiciosRSEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -703217250657754406L;

    /**
     * Tododo el listado de Servicios registrados
     */
    public static final String OBTENER_LISTADO = "ServiciosRSEntity.ObtenerListado";
    /**
     * <b>Query </b> Busqueda de un servicio que este activo
     *
     * @param servicioEnum
     *            pasar el enumerador del servicio que se va a comprobar
     */
    public static final String BUSCAR_SERVICIO_ACTIVO = "ServiciosRSEntity.BuscarServicioActivo";

    /**
     * <b>Query </b> Busqueda de un servicio que este activo, por id
     *
     * @param servicioEnum
     *            pasar el enumerador del servicio que se va a comprobar
     * @param idServicio
     *            Se comprueba si el servicio esta activo
     */
    public static final String BUSCAR_SERVICIO_ACTIVO_ID = "ServiciosRSEntity.BuscarServicioActivoPorId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio_rs")
    private Integer idServicioRS;
    @Column(name = "url")
    private String url;
    @Column(name = "puerto")
    private String puerto;
    @Column(name = "contexto")
    private String contexto;
    @Column(name = "clave")
    private String clave;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "produccion")
    private boolean produccion;
    @Column(name = "activo")
    private boolean activo;
    @Column(name = "servicio")
    private ServicioWebEnum servicio;

    public Integer getIdServicioRS() {
        return idServicioRS;
    }

    public void setIdServicioRS(Integer idServicioRS) {
        this.idServicioRS = idServicioRS;
    }

    public Integer getidServicioRS() {
        return idServicioRS;
    }

    public void setidServicioRS(Integer idServicioRS) {
        this.idServicioRS = idServicioRS;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isProduccion() {
        return produccion;
    }

    public void setProduccion(boolean produccion) {
        this.produccion = produccion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Enumerated(EnumType.ORDINAL)
    public ServicioWebEnum getServicio() {
        return servicio;
    }

    public void setServicio(ServicioWebEnum servicio) {
        this.servicio = servicio;
    }

}
