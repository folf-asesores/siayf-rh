
package mx.gob.saludtlax.rh.ca.incidencia;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.ca.ClienteRest;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.ListadoMensajesSistema;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servicio que consume los servicios RESTFul de incidencias.
 *
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
@Stateless
public class IncidenciaClienteRest extends ClienteRest implements Serializable {

    public IncidenciaClienteRest() {
        super(ServicioWebEnum.CONTROL_ASISTENCIA_RS);

    }

    /**
     *
     */
    private static final long serialVersionUID = 7764758990374165127L;

    static Logger log = Logger.getLogger(Configuracion.LOGGER_CONTROL_ASISTENCIA);

    private final String RESOURCE_BUSCAR_ID = "/incidencia/buscar/id/";

    private final String RESOURCE_NUEVO = "/incidencia/nuevo";

    private final String RESOURCE_EDITAR = "/incidencia/actualiza";

    private final String RESOURCE_LISTADO = "/incidencia/listado";

    private final String RESOURCE_IMAGEN = "/incidencia/imagen/";

    private final String RESOURCE_BUSCAR_DESCRIPCION = "/incidencia/buscar/descripcion/";

    /**
     * Busqueda de incidencias por medio de la descripcion.
     *
     * @param descripcion
     *            de la incidencias.
     * @return
     * @throws RESTClientException
     *             en caso que ocurra un error.
     */
    public List<IncidenciaModelView> buscarIncidenciaPorDescripcion(String descripcion) throws RESTClientException {
        List<IncidenciaModelView> listadoIncidenciaModelView = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        log.debug(url_serivicio + RESOURCE_BUSCAR_DESCRIPCION + descripcion);
        HttpGet httpGet = new HttpGet(url_serivicio + RESOURCE_BUSCAR_DESCRIPCION + descripcion.replace(" ", "%20"));

        try {
            CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    String resultIncidencia = EntityUtils.toString(servicioResponse.getEntity());
                    log.debug(resultIncidencia);
                    Gson incidenciaGson = new Gson();
                    TypeToken<ArrayList<IncidenciaModelView>> tokenListadoIncidencias = new TypeToken<ArrayList<IncidenciaModelView>>() {
                    };
                    listadoIncidenciaModelView = incidenciaGson.fromJson(resultIncidencia, tokenListadoIncidencias.getType());
                    log.debug(listadoIncidenciaModelView);

                    break;
                case 400:
                    log.error(servicioResponse.getStatusLine().getReasonPhrase());
                    throw new RESTClientException(servicioResponse.getStatusLine().getReasonPhrase());
                default:
                    log.error(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode() + " "
                            + servicioResponse.getStatusLine().toString());
                    throw new RESTClientException(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode() + " "
                            + servicioResponse.getStatusLine().toString());

            }

        } catch (IOException e) {
            log.error(e.toString());
            log.error(e.getMessage());
            log.error(e.getCause());
            throw new RESTClientException(e.getMessage());
        }

        return listadoIncidenciaModelView;

    }

    /**
     *
     * @param id
     *            Identificador de la incidencia a buscar.
     * @return Informacion de una incidencia.
     * @throws RESTClientException
     */
    public IncidenciaModelView buscarIncidenciaPorId(Integer id) throws RESTClientException {

        IncidenciaModelView incidenciaModelView = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        log.debug(url_serivicio + RESOURCE_BUSCAR_ID + id);

        HttpGet httpGet = new HttpGet(url_serivicio + RESOURCE_BUSCAR_ID + id);

        try {
            CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);

            log.debug("Estatus:" + servicioResponse.getStatusLine().getStatusCode() + servicioResponse.getStatusLine().getReasonPhrase());

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:

                    String resultIncidencia = EntityUtils.toString(servicioResponse.getEntity());
                    log.debug(resultIncidencia);

                    Gson incidenciaGson = new Gson();
                    incidenciaModelView = incidenciaGson.fromJson(resultIncidencia, IncidenciaModelView.class);

                    log.debug(incidenciaModelView);

                    break;
                case 400:
                    log.error(servicioResponse.getStatusLine().getReasonPhrase());
                    throw new RESTClientException(servicioResponse.getStatusLine().getReasonPhrase());
                default:

                    log.error(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode() + " "
                            + servicioResponse.getStatusLine().toString());

                    throw new RESTClientException(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode() + " "
                            + servicioResponse.getStatusLine().toString());

            }

        } catch (IOException e) {

            log.error(e.toString());
            log.error(e.getMessage());
            log.error(e.getCause());

            throw new RESTClientException(e.getMessage());
        }

        log.debug(incidenciaModelView);
        return incidenciaModelView;

    }

    /**
     * Guarda la informacion de una incidencia.
     *
     * @param incidenciaFormModel
     *            Formulario de captura de incidencias.
     * @return Integer retorna el identificador unico de la incidencia generada.
     * @throws RESTClientException
     *             en caso que haya un error de validacion o comunicacion.
     */
    public Integer guardarIncidencia(IncidenciaFormModel incidenciaFormModel) throws RESTClientException {
        Integer idIncidencia = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        log.debug(url_serivicio + RESOURCE_NUEVO);

        HttpPost httpPost = new HttpPost(url_serivicio + RESOURCE_NUEVO);

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

        multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));

        log.debug(incidenciaFormModel);

        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        if (incidenciaFormModel.getImagenMarca() != null) {
            multipartEntityBuilder.addBinaryBody("imagen_marca", incidenciaFormModel.getImagenMarca(), ContentType.APPLICATION_OCTET_STREAM,
                    incidenciaFormModel.getMarcaReporte().trim());
        }
        // multipartEntityBuilder.addTextBody("id_incidencia", "");
        if (incidenciaFormModel.getDescripcion() != null) {
            multipartEntityBuilder.addTextBody("descripcion", incidenciaFormModel.getDescripcion(), ContentType.create("application/json", "UTF-8"));
        }
        if (incidenciaFormModel.getMarcaReporte() != null) {
            multipartEntityBuilder.addTextBody("marca_reporte", incidenciaFormModel.getMarcaReporte(), ContentType.create("application/json", "UTF-8"));
        }
        if (incidenciaFormModel.getEsImagen() != null) {
            multipartEntityBuilder.addTextBody("es_imagen", incidenciaFormModel.getEsImagen().toString(), ContentType.create("application/json", "UTF-8"));
        }
        if (incidenciaFormModel.getColorTexto() != null) {
            multipartEntityBuilder.addTextBody("color_texto", incidenciaFormModel.getColorTexto(), ContentType.create("application/json", "UTF-8"));
        }

        if (incidenciaFormModel.getTipoRegistro() != null) {
            multipartEntityBuilder.addTextBody("tipo_registro", incidenciaFormModel.getTipoRegistro().toString(),
                    ContentType.create("application/json", "UTF-8"));
        }

        log.debug(multipartEntityBuilder.toString());

        HttpEntity entity = multipartEntityBuilder.build();

        log.debug(entity.toString());

        httpPost.setEntity(entity);

        try {
            CloseableHttpResponse servicioResponse = httpClient.execute(httpPost);
            log.debug(servicioResponse.getStatusLine().getStatusCode() + " " + servicioResponse.getStatusLine().getReasonPhrase());
            String resultNuevaIncidencia;

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    resultNuevaIncidencia = EntityUtils.toString(servicioResponse.getEntity());
                    log.debug(resultNuevaIncidencia);
                    idIncidencia = new Integer(resultNuevaIncidencia);
                    log.debug(idIncidencia);
                    break;
                case 400:
                    resultNuevaIncidencia = EntityUtils.toString(servicioResponse.getEntity());
                    log.error(servicioResponse.getStatusLine() + resultNuevaIncidencia);
                    throw new RESTClientException(resultNuevaIncidencia);
                default:
                    log.error(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode() + " "
                            + servicioResponse.getStatusLine().toString());
                    throw new RESTClientException(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode() + " "
                            + servicioResponse.getStatusLine().toString());

            }

        } catch (IOException e) {

            log.error(e.toString());
            log.error(e.getMessage());
            log.error(e.getCause());
            throw new RESTClientException(e.getMessage());
        }

        return idIncidencia;
    }

    /**
     * Actualiza la informacion de una incidencia.
     *
     * @param incidenciaFormModel
     *            Formulario de datos de entrada.
     * @return Integer regresa el id de la incidencia actualiza.
     * @throws RESTClientException
     *             En caso de algun error de conexion o validacion.
     */
    public Integer actualizarIncidencia(IncidenciaFormModel incidenciaFormModel) throws RESTClientException {
        Integer idIncidencia = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        log.debug(url_serivicio + RESOURCE_EDITAR);
        HttpPut httpPut = new HttpPut(url_serivicio + RESOURCE_EDITAR);

        log.debug(incidenciaFormModel);

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        // multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));

        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        if (incidenciaFormModel.getImagenMarca() != null) {
            multipartEntityBuilder.addBinaryBody("imagen_marca", incidenciaFormModel.getImagenMarca(), ContentType.APPLICATION_OCTET_STREAM,
                    incidenciaFormModel.getMarcaReporte().trim());
        }
        // multipartEntityBuilder.addTextBody("id_incidencia", "");
        if (incidenciaFormModel.getDescripcion() != null) {
            multipartEntityBuilder.addTextBody("descripcion", incidenciaFormModel.getDescripcion(), ContentType.create("application/json", "UTF-8"));
        }
        if (incidenciaFormModel.getMarcaReporte() != null) {
            multipartEntityBuilder.addTextBody("marca_reporte", incidenciaFormModel.getMarcaReporte(), ContentType.create("application/json", "UTF-8"));
        }
        if (incidenciaFormModel.getEsImagen() != null) {
            multipartEntityBuilder.addTextBody("es_imagen", incidenciaFormModel.getEsImagen().toString(), ContentType.create("application/json", "UTF-8"));
        }

        if (incidenciaFormModel.getColorTexto() != null) {
            multipartEntityBuilder.addTextBody("color_texto", incidenciaFormModel.getColorTexto(), ContentType.create("application/json", "UTF-8"));
        }
        if (incidenciaFormModel.getTipoRegistro() != null) {
            multipartEntityBuilder.addTextBody("tipo_registro", incidenciaFormModel.getTipoRegistro().toString(),
                    ContentType.create("application/json", "UTF-8"));
        }

        multipartEntityBuilder.addTextBody("id_incidencia", incidenciaFormModel.getIdIncidencia().toString(), ContentType.create("application/json", "UTF-8"));

        log.debug(multipartEntityBuilder.toString());

        HttpEntity entity = multipartEntityBuilder.build();

        log.debug(entity);

        httpPut.setEntity(entity);

        try {
            CloseableHttpResponse servicioResponse = httpClient.execute(httpPut);

            String resultNuevaIncidencia;

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    resultNuevaIncidencia = EntityUtils.toString(servicioResponse.getEntity());
                    log.debug(resultNuevaIncidencia);
                    idIncidencia = new Integer(resultNuevaIncidencia);
                    log.debug(idIncidencia);
                    break;
                case 400:
                    resultNuevaIncidencia = EntityUtils.toString(servicioResponse.getEntity());
                    log.error(servicioResponse.getStatusLine() + resultNuevaIncidencia);
                    throw new RESTClientException(resultNuevaIncidencia);
                default:
                    log.error(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode() + " "
                            + servicioResponse.getStatusLine().toString());
                    throw new RESTClientException(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode() + " "
                            + servicioResponse.getStatusLine().toString());

            }

        } catch (IOException e) {
            log.error(e.toString());
            log.error(e.getMessage());
            log.error(e.getCause());
            throw new RESTClientException(e.getMessage());
        }

        return idIncidencia;
    }

    public List<IncidenciaModelView> listadoIncidencias() throws RESTClientException {

        List<IncidenciaModelView> listadoIncidenciaModelView = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        log.debug(url_serivicio + RESOURCE_LISTADO);
        HttpGet httpGet = new HttpGet(url_serivicio + RESOURCE_LISTADO);

        try {
            CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    String resultIncidencia = EntityUtils.toString(servicioResponse.getEntity());
                    log.debug(resultIncidencia);
                    Gson incidenciaGson = new Gson();
                    TypeToken<ArrayList<IncidenciaModelView>> tokenListadoIncidencias = new TypeToken<ArrayList<IncidenciaModelView>>() {
                    };
                    listadoIncidenciaModelView = incidenciaGson.fromJson(resultIncidencia, tokenListadoIncidencias.getType());
                    log.debug(listadoIncidenciaModelView);

                    break;
                case 400:
                    log.error(servicioResponse.getStatusLine().getReasonPhrase());
                    throw new RESTClientException(servicioResponse.getStatusLine().getReasonPhrase());
                default:
                    log.error(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode() + " "
                            + servicioResponse.getStatusLine().toString());
                    throw new RESTClientException(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode() + " "
                            + servicioResponse.getStatusLine().toString());

            }

        } catch (IOException e) {
            log.error(e.toString());
            log.error(e.getMessage());
            log.error(e.getCause());
            throw new RESTClientException(e.getMessage());
        }

        return listadoIncidenciaModelView;
    }

    /**
     * Regresa la ruta url de la imagen que se debe mostrar.
     *
     * @param id
     * @return
     */
    public String rutaImangen() {

        return url_serivicio + RESOURCE_IMAGEN;

    }
}
