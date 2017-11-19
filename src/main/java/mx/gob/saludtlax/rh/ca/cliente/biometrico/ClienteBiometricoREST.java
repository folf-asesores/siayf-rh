
package mx.gob.saludtlax.rh.ca.cliente.biometrico;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import mx.gob.saludtlax.rh.ca.ClienteRest;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.util.ListadoMensajesSistema;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class ClienteBiometricoREST extends ClienteRest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2837542732766289813L;

    public ClienteBiometricoREST() {
        super(ServicioWebEnum.CONTROL_ASISTENCIA_RS);
    }

    private final String RESOURCE_LISTADO = "/cliente/biometrico/listado";

    private final String RESOURCE_BUSCAR = "/cliente/biometrico/buscar/id/";
    private final String RESOURCE_GUARDAR = "/cliente/biometrico/nuevo/";
    private final String RESOURCE_ACTUALIZAR = "/cliente/biometrico/actualizar/";
    private final String RESOURCE_ELIMINAR = "/cliente/biometrico/eliminar/id/";

    /**
     * Metodo que regresa el listado de Clientes Biometricos registrados.
     *
     * @return Listado de ClienteBiometricoFormModel
     * @throws RESTClientException
     */
    public List<ClienteBiometricoFormModel> listadoClientesBiometricos() throws RESTClientException {

        List<ClienteBiometricoFormModel> listadoClienteBiometrico = new ArrayList<>();

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url_serivicio + RESOURCE_LISTADO);

        try {
            CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    String resultBiometricos = EntityUtils.toString(servicioResponse.getEntity());

                    Gson biometricoGson = new Gson();
                    TypeToken<ArrayList<ClienteBiometricoFormModel>> tokenListadoBiometrico = new TypeToken<ArrayList<ClienteBiometricoFormModel>>() {

                        /**
                         *
                         */
                        private static final long serialVersionUID = -1365297973402098439L;
                    };
                    listadoClienteBiometrico = biometricoGson.fromJson(resultBiometricos, tokenListadoBiometrico.getType());
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

        return listadoClienteBiometrico;

    }

    public ClienteBiometricoFormModel buscarClienteBiometrico(Integer idClienteBiometrico) throws RESTClientException {

        ClienteBiometricoFormModel clienteBiometricoForm = new ClienteBiometricoFormModel();

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url_serivicio + RESOURCE_BUSCAR + idClienteBiometrico);

        try {
            CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    String resultBiometricos = EntityUtils.toString(servicioResponse.getEntity());
                    Gson biometricoGson = new Gson();
                    clienteBiometricoForm = biometricoGson.fromJson(resultBiometricos, ClienteBiometricoFormModel.class);
                    break;
                case 400:
                    throw new RESTClientException(servicioResponse.getStatusLine().getReasonPhrase());
                default:
                    throw new RESTClientException(ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode() + " "
                            + servicioResponse.getStatusLine().toString());
            }

        } catch (IOException e) {
            //System.out.println("error:: "+ e.getMessage());
            throw new RESTClientException(e.getMessage());
        }

        return clienteBiometricoForm;
    }

    public void guardarBiometrico(ClienteBiometricoFormModel biometricoFormModel) throws RESTClientException {

        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url_serivicio + RESOURCE_GUARDAR);
        StringEntity nuevoBiometricoJSON;

        System.out.println("datos " + biometricoFormModel.getDireccionIP() + "," + biometricoFormModel.getPuerto() + " , " + biometricoFormModel.getUnidad());

        try {
            nuevoBiometricoJSON = new StringEntity(gson.toJson(biometricoFormModel));
            nuevoBiometricoJSON.setContentType("application/json");
            httpPost.setEntity(nuevoBiometricoJSON);
            HttpResponse servicioResponse = httpClient.execute(httpPost);

            String resultNuevoBiometrico;
            System.out.println("estaus despues de llamada codigo " + servicioResponse.getStatusLine().getStatusCode());
            System.out.println("tracer: " + servicioResponse.getStatusLine().getReasonPhrase());

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    resultNuevoBiometrico = EntityUtils.toString(servicioResponse.getEntity());
                    String response = gson.fromJson(resultNuevoBiometrico, String.class);
                    //biometricoFormModel = gson.fromJson(resultNuevoBiometrico,ClienteBiometricoFormModel.class);
                    System.out.println("en el servicio con codigo 200 :" + response);

                    break;
                case 400:
                    resultNuevoBiometrico = EntityUtils.toString(servicioResponse.getEntity());
                    throw new RESTClientException(resultNuevoBiometrico);
                case 412:
                    resultNuevoBiometrico = EntityUtils.toString(servicioResponse.getEntity());
                    throw new RESTClientException(resultNuevoBiometrico);
                default:
                    throw new RESTClientException(servicioResponse.getStatusLine().getStatusCode() + " " + servicioResponse.getStatusLine().getReasonPhrase());
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RESTClientException(e.getMessage());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RESTClientException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RESTClientException(e.getMessage());
        }

    }

    /*
     * actualizar
     */
    public String actualizarBiometrico(ClienteBiometricoFormModel biometricoFormModel) throws RESTClientException {
        String estatus;
        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut httpPost = new HttpPut(url_serivicio + RESOURCE_ACTUALIZAR);
        StringEntity nuevoBiometricoJSON;

        System.out.println("actualizar  " + biometricoFormModel.getIdClienteBiometricoFormModel() + " , " + biometricoFormModel.getDireccionIP() + "  , "
                + biometricoFormModel.getPuerto() + " , " + biometricoFormModel.getUnidad());

        try {
            nuevoBiometricoJSON = new StringEntity(gson.toJson(biometricoFormModel));
            nuevoBiometricoJSON.setContentType("application/json");
            httpPost.setEntity(nuevoBiometricoJSON);
            HttpResponse servicioResponse = httpClient.execute(httpPost);

            String resultNuevoBiometrico;
            System.out.println("estaus despues de llamada codigo " + servicioResponse.getStatusLine().getStatusCode());
            System.out.println("tracer: " + servicioResponse.getStatusLine().getReasonPhrase());

            switch (servicioResponse.getStatusLine().getStatusCode()) {
                case 200:
                    resultNuevoBiometrico = EntityUtils.toString(servicioResponse.getEntity());
                    String response = gson.fromJson(resultNuevoBiometrico, String.class);

                    break;
                case 400:
                    resultNuevoBiometrico = EntityUtils.toString(servicioResponse.getEntity());
                    throw new RESTClientException(resultNuevoBiometrico);
                case 412:
                    resultNuevoBiometrico = EntityUtils.toString(servicioResponse.getEntity());
                    //	 response = gson.fromJson(resultNuevoBiometrico, String.class);
                    System.out.println("erro:" + resultNuevoBiometrico);
                    throw new RESTClientException(resultNuevoBiometrico);
                case 405:
                    resultNuevoBiometrico = EntityUtils.toString(servicioResponse.getEntity());
                    response = gson.fromJson(resultNuevoBiometrico, String.class);
                    System.out.println("error;: " + response);
                    throw new RESTClientException(resultNuevoBiometrico);
                default:
                    throw new RESTClientException(servicioResponse.getStatusLine().getStatusCode() + " " + servicioResponse.getStatusLine().getReasonPhrase());
            }

            estatus = resultNuevoBiometrico;
            return estatus;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RESTClientException(e.getMessage());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RESTClientException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RESTClientException(e.getMessage());
        }

    }

}
