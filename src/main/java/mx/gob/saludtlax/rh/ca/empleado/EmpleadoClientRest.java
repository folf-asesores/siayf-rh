
package mx.gob.saludtlax.rh.ca.empleado;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.ca.ClienteRest;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.excepciones.ValidacionIncidenciaException;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.ListadoMensajesSistema;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class EmpleadoClientRest extends ClienteRest implements Serializable {

    public EmpleadoClientRest() {
        super(ServicioWebEnum.CONTROL_ASISTENCIA_RS);
    }

    /**
     *
     */
    private static final long serialVersionUID = 63237774225756500L;

    static Logger log = Logger.getLogger(Configuracion.LOGGER_CONTROL_ASISTENCIA);

    private final String RESOURCE_EMPLEADO_INCIDENCIA = "/incidencia/empleado/agregar";

    private final String RESOURCE_EMPLEADO_ELIMINAR_INCIDENCIA = "/incidencia/empleado/eliminar/";

    private final String RESOURCE_EMPLEADO_HORARIO = "/horario/empleado/agregar";

    private final String RESOURCE_EMPLEADO_LISTADO_HORARIO = "/horario/empleado/listado/";

    private final String RESOURCE_EMPLEADO_ELIMINAR_HORARIO = "/horario/empleado/eliminar/jornada/";

    private final String RESOURCE_INCIDENCIA_EMPLEADO = "/empleado/buscar/incidencias/fecha";

    private final String RESOURCE_TOTAL_HORAS_EMPLEADO = "/empleado/buscar/incidencias/horas";

    public void elminarIncidenciaEmpleado(Integer idIncidenciaEmpleado) throws RESTClientException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpDelete httpDelete = new HttpDelete(url_serivicio + RESOURCE_EMPLEADO_ELIMINAR_INCIDENCIA + idIncidenciaEmpleado);

        try {
            CloseableHttpResponse servicioResponse = httpClient.execute(httpDelete);

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    String resultPeriodoEspera = EntityUtils.toString(servicioResponse.getEntity());
                    break;
                case 400:

                    throw new RESTClientException(servicioResponse.getStatusLine().getReasonPhrase());
                default:

                    throw new RESTClientException(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode() + " "
                            + servicioResponse.getStatusLine().toString());

            }

        } catch (IOException e) {

            throw new RESTClientException(e.getMessage());
        }

    }

    public Integer agregarNuevaIncidenciaEmpleado(IncidenciaEmpleadoFormModel incidenciaEmpleadoForm)
            throws RESTClientException, ValidacionIncidenciaException {
        Integer idNuevaIncidenciaEmpleado = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        log.debug(url_serivicio + RESOURCE_EMPLEADO_INCIDENCIA);

        HttpPost httpPost = new HttpPost(url_serivicio + RESOURCE_EMPLEADO_INCIDENCIA);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        StringEntity nuevaIncidenciaEmpleadoJSON;
        try {
            nuevaIncidenciaEmpleadoJSON = new StringEntity(gson.toJson(incidenciaEmpleadoForm));

            nuevaIncidenciaEmpleadoJSON.setContentType("application/json");
            log.debug(nuevaIncidenciaEmpleadoJSON);

            httpPost.setEntity(nuevaIncidenciaEmpleadoJSON);

            CloseableHttpResponse servicioResponse = httpClient.execute(httpPost);

            String resultNuevaIncidenciaEmpleado;

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    resultNuevaIncidenciaEmpleado = EntityUtils.toString(servicioResponse.getEntity());
                    log.debug(resultNuevaIncidenciaEmpleado);
                    idNuevaIncidenciaEmpleado = new Integer(resultNuevaIncidenciaEmpleado);
                    break;
                case 400:
                    resultNuevaIncidenciaEmpleado = EntityUtils.toString(servicioResponse.getEntity());
                    log.error(resultNuevaIncidenciaEmpleado);
                    throw new RESTClientException(resultNuevaIncidenciaEmpleado);
                case 412:
                    resultNuevaIncidenciaEmpleado = EntityUtils.toString(servicioResponse.getEntity());
                    throw new ValidacionIncidenciaException(resultNuevaIncidenciaEmpleado);

                default:
                    log.error(servicioResponse.getStatusLine().getReasonPhrase());
                    log.error(servicioResponse.getStatusLine().getStatusCode());
                    throw new RESTClientException(servicioResponse.getStatusLine().getStatusCode() + " " + servicioResponse.getStatusLine().getReasonPhrase());

            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RESTClientException(e.getMessage());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new RESTClientException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RESTClientException(e.getMessage());
        }

        return idNuevaIncidenciaEmpleado;
    }

    public Integer agregarHorarioEmpleado(HorarioEmpleadoFormModel horarioEmpleadoFormModel) throws RESTClientException {
        Integer idNuevoHorarioEmpleado = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        log.debug(url_serivicio + RESOURCE_EMPLEADO_HORARIO);

        HttpPost httpPost = new HttpPost(url_serivicio + RESOURCE_EMPLEADO_HORARIO);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        StringEntity nuevoHorarioEmpleadoJSON;
        try {
            nuevoHorarioEmpleadoJSON = new StringEntity(gson.toJson(horarioEmpleadoFormModel));

            nuevoHorarioEmpleadoJSON.setContentType("application/json");

            httpPost.setEntity(nuevoHorarioEmpleadoJSON);

            CloseableHttpResponse servicioResponse = httpClient.execute(httpPost);

            String resultNuevoHorarioEmpleado;

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    resultNuevoHorarioEmpleado = EntityUtils.toString(servicioResponse.getEntity());

                    idNuevoHorarioEmpleado = new Integer(resultNuevoHorarioEmpleado);
                    break;
                case 400:
                    resultNuevoHorarioEmpleado = EntityUtils.toString(servicioResponse.getEntity());
                    log.error(resultNuevoHorarioEmpleado);
                    throw new RESTClientException(resultNuevoHorarioEmpleado);

                default:
                    log.error(servicioResponse.getStatusLine().getReasonPhrase());
                    log.error(servicioResponse.getStatusLine().getStatusCode());
                    throw new RESTClientException(servicioResponse.getStatusLine().getStatusCode() + " " + servicioResponse.getStatusLine().getReasonPhrase());

            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RESTClientException(e.getMessage());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new RESTClientException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RESTClientException(e.getMessage());
        }

        return idNuevoHorarioEmpleado;
    }

    public List<HorarioEmpleadoViewModel> buscarHorarioPorEmpleado(Integer idEmpleado) throws RESTClientException {
        List<HorarioEmpleadoViewModel> listadoHorarioEmpleadoModelView = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url_serivicio + RESOURCE_EMPLEADO_LISTADO_HORARIO + idEmpleado);

        try {
            CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    String resultHorario = EntityUtils.toString(servicioResponse.getEntity());
                    log.debug(resultHorario);
                    Gson horarioGson = new Gson();
                    TypeToken<ArrayList<HorarioEmpleadoViewModel>> tokenListadoHorario = new TypeToken<ArrayList<HorarioEmpleadoViewModel>>() {
                    };
                    listadoHorarioEmpleadoModelView = horarioGson.fromJson(resultHorario, tokenListadoHorario.getType());

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

        return listadoHorarioEmpleadoModelView;

    }

    public List<IncidenciaEmpleadoView> buscarIncidenciaEmpleado(ConsultarIncidenciasEmpleadoModel model) throws RESTClientException {
        List<IncidenciaEmpleadoView> listadoEmpleadoModelView = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpGet = new HttpPost(url_serivicio + RESOURCE_INCIDENCIA_EMPLEADO);

        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
            StringEntity consultaJSON;
            consultaJSON = new StringEntity(gson.toJson(model));
            consultaJSON.setContentType("application/json");
            httpGet.setEntity(consultaJSON);
            CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    String resultIncidenciaEmpleado = EntityUtils.toString(servicioResponse.getEntity());

                    Gson horarioGson = new Gson();
                    TypeToken<ArrayList<IncidenciaEmpleadoView>> tokenListadoIncidencia = new TypeToken<ArrayList<IncidenciaEmpleadoView>>() {
                    };
                    listadoEmpleadoModelView = horarioGson.fromJson(resultIncidenciaEmpleado, tokenListadoIncidencia.getType());

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

        return listadoEmpleadoModelView;

    }

    public BigDecimal buscarHorasPaseSalidaEmpleado(ConsultarIncidenciasEmpleadoModel model) throws RESTClientException {
        BigDecimal totalHoras = BigDecimal.ZERO;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpGet = new HttpPost(url_serivicio + RESOURCE_TOTAL_HORAS_EMPLEADO);

        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
            StringEntity consultaJSON;
            consultaJSON = new StringEntity(gson.toJson(model));
            consultaJSON.setContentType("application/json");
            httpGet.setEntity(consultaJSON);
            CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    String resultIncidenciaEmpleado = EntityUtils.toString(servicioResponse.getEntity());

                    Gson horarioGson = new Gson();

                    totalHoras = horarioGson.fromJson(resultIncidenciaEmpleado, BigDecimal.class);

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

        return totalHoras;

    }

    public boolean eliminarJornadaEmpleado(Integer idHorario) throws RESTClientException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url_serivicio + RESOURCE_EMPLEADO_ELIMINAR_HORARIO + idHorario);

        try {
            CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    String resultHorario = EntityUtils.toString(servicioResponse.getEntity());
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

        return true;

    }

}
