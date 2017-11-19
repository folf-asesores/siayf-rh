
package mx.gob.saludtlax.rh.ca.incidencia;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import mx.gob.saludtlax.rh.ca.ClienteRest;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.util.ListadoMensajesSistema;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Stateless
public class ReglaIncidenciaClienteRest extends ClienteRest
        implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -137318489550762817L;

    public ReglaIncidenciaClienteRest() {
        super(ServicioWebEnum.CONTROL_ASISTENCIA_RS);
    }

    private final String RESOURCE_LISTADO_REGLAS_INCIDENCIAS = "/incidencia/regla/buscar/listado/";
    private final String RESOURCE_NUEVA_REGLA_ASISTENCIA = "/incidencia/regla/agregar";
    private final String RESOURCE_ELIMINA_REGLA_INCIDENCIA = "/incidencia/regla/eliminar/";

    public List<ReglaIncidenciaViewModel> listadoReglasIncidencia(
            Integer idIncidencia, Integer idTipoContraacion)
            throws RESTClientException {

        List<ReglaIncidenciaViewModel> listadoReglasIncidenciaViewModel = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(
                url_serivicio + RESOURCE_LISTADO_REGLAS_INCIDENCIAS
                        + idIncidencia + "/" + idTipoContraacion);

        try {
            CloseableHttpResponse servicioResponse = httpClient
                    .execute(httpGet);

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    String result = EntityUtils
                            .toString(servicioResponse.getEntity());

                    Gson incidenciaGson = new Gson();
                    TypeToken<ArrayList<ReglaIncidenciaViewModel>> tokenListado = new TypeToken<ArrayList<ReglaIncidenciaViewModel>>() {
                    };
                    listadoReglasIncidenciaViewModel = incidenciaGson
                            .fromJson(result, tokenListado.getType());

                    break;
                case 400:
                    throw new RESTClientException(
                            servicioResponse.getStatusLine().getReasonPhrase());
                default:
                    throw new RESTClientException(ListadoMensajesSistema.E000
                            .getMensaje()
                            + servicioResponse.getStatusLine().getStatusCode()
                            + " "
                            + servicioResponse.getStatusLine().toString());

            }

        } catch (IOException e) {

            throw new RESTClientException(e.getMessage());
        }

        return listadoReglasIncidenciaViewModel;

    }

    public void crearNuevaReglaIncidencia(
            ReglaIncidenciaFormModel reglaIncidenciaFormModel)
            throws RESTClientException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(
                url_serivicio + RESOURCE_NUEVA_REGLA_ASISTENCIA);

        Gson gson = new Gson();
        StringEntity nuevaReglaIncidenciaFormModelJSON;
        try {
            nuevaReglaIncidenciaFormModelJSON = new StringEntity(
                    gson.toJson(reglaIncidenciaFormModel));

            nuevaReglaIncidenciaFormModelJSON
                    .setContentType("application/json");

            httpPost.setEntity(nuevaReglaIncidenciaFormModelJSON);

            CloseableHttpResponse servicioResponse = httpClient
                    .execute(httpPost);

            String resultNuevaIncidenciaEmpleado;

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    resultNuevaIncidenciaEmpleado = EntityUtils
                            .toString(servicioResponse.getEntity());
                    break;
                case 400:
                    resultNuevaIncidenciaEmpleado = EntityUtils
                            .toString(servicioResponse.getEntity());
                    throw new RESTClientException(
                            resultNuevaIncidenciaEmpleado);

                default:

                    throw new RESTClientException(
                            servicioResponse.getStatusLine().getStatusCode()
                                    + " " + servicioResponse.getStatusLine()
                                            .getReasonPhrase());

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

    }

    public void elminarReglaIncidencia(Integer idReglaIncidenciaElminar)
            throws RESTClientException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpDelete httpDelete = new HttpDelete(url_serivicio
                + RESOURCE_ELIMINA_REGLA_INCIDENCIA + idReglaIncidenciaElminar);

        try {
            CloseableHttpResponse servicioResponse = httpClient
                    .execute(httpDelete);

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    String resultHorario = EntityUtils
                            .toString(servicioResponse.getEntity());
                    break;
                case 400:

                    throw new RESTClientException(
                            servicioResponse.getStatusLine().getReasonPhrase());
                default:

                    throw new RESTClientException(ListadoMensajesSistema.E000
                            .getMensaje()
                            + servicioResponse.getStatusLine().getStatusCode()
                            + " "
                            + servicioResponse.getStatusLine().toString());

            }

        } catch (IOException e) {

            throw new RESTClientException(e.getMessage());
        }

    }

}
