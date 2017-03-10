package mx.gob.saludtlax.rh.ca.biometrico;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import mx.gob.saludtlax.rh.ca.ClienteRest;
import mx.gob.saludtlax.rh.ca.cliente.biometrico.ClienteBiometricoFormModel;
import mx.gob.saludtlax.rh.ca.cliente.biometrico.ClienteBiometricoREST;
import mx.gob.saludtlax.rh.ca.incidencia.IncidenciaFormModel;
import mx.gob.saludtlax.rh.ca.incidencia.IncidenciaModelView;
import mx.gob.saludtlax.rh.ca.jornada.JornadaFormModel;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.util.ListadoMensajesSistema;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

public class BiometricoClientRest extends ClienteRest implements Serializable {

	public BiometricoClientRest() {
		super(ServicioWebEnum.RELOJ_CHECADOR);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1958131322500804170L;

	private final String RESOURCE_LISTADO = "/Checador/listado";

	private final String RESOURCE_GUARDAR = "/Checador/registrar";

	private final String RESOURCE_ACTUALIZAR = "/Checador/actualizar";

	private final String RESOURCE_BUSCAR_ID = "/Checador/obtener/?id=";

	private final String RESOURCE_DESCARGAR_INFORMACION = "/ca/api/Checador/asignarRegistroEmpleadoHuella";

	@Inject
	ClienteBiometricoREST clienteBiometricoREST;

	/*
	 * Consume el servicio web del api de comunicacion con los equipos
	 * biometricos
	 */
	public List<BiometricoFormModel> listadoEquiposBiometricos() throws RESTClientException {

		List<BiometricoFormModel> listadoEquiposBiometricos = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(url_serivicio + RESOURCE_LISTADO);

		try {
			CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);

			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				String resultBiometricos = EntityUtils.toString(servicioResponse.getEntity());

				Gson biometricoGson = new Gson();
				TypeToken<ArrayList<BiometricoFormModel>> tokenListadoBiometrico = new TypeToken<ArrayList<BiometricoFormModel>>() {
				};
				listadoEquiposBiometricos = biometricoGson.fromJson(resultBiometricos,
						tokenListadoBiometrico.getType());
				break;
			case 400:
				throw new RESTClientException(servicioResponse.getStatusLine().getReasonPhrase());
			default:
				throw new RESTClientException(
						ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode()
								+ " " + servicioResponse.getStatusLine().toString());
			}

		} catch (IOException e) {
			throw new RESTClientException(e.getMessage());
		}

		return listadoEquiposBiometricos;

	}

	public BiometricoClientRestResponse guardarBiometrico(BiometricoFormModel biometricoFormModel)
			throws RESTClientException {
		BiometricoClientRestResponse biometricoClientRestResponse = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(url_serivicio + RESOURCE_GUARDAR);
		Gson gson = new Gson();
		StringEntity nuevoBiometricoJSON;

		try {
			nuevoBiometricoJSON = new StringEntity(gson.toJson(biometricoFormModel));
			nuevoBiometricoJSON.setContentType("application/json");

			httpPost.setEntity(nuevoBiometricoJSON);
			CloseableHttpResponse servicioResponse = httpClient.execute(httpPost);

			String resultNuevoBiometrico;
			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				resultNuevoBiometrico = EntityUtils.toString(servicioResponse.getEntity());

				biometricoClientRestResponse = gson.fromJson(resultNuevoBiometrico, BiometricoClientRestResponse.class);
				break;
			case 400:
				resultNuevoBiometrico = EntityUtils.toString(servicioResponse.getEntity());
				throw new RESTClientException(resultNuevoBiometrico);
			default:
				throw new RESTClientException(servicioResponse.getStatusLine().getStatusCode() + " "
						+ servicioResponse.getStatusLine().getReasonPhrase());
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

		return biometricoClientRestResponse;
	}

	public BiometricoClientRestResponse guardarBiometrico(BiometricoFormModel biometricoFormModel, String urlServicio)
			throws RESTClientException {
		BiometricoClientRestResponse biometricoClientRestResponse = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(urlServicio + RESOURCE_GUARDAR);
		Gson gson = new Gson();
		StringEntity nuevoBiometricoJSON;

		try {
			nuevoBiometricoJSON = new StringEntity(gson.toJson(biometricoFormModel));
			nuevoBiometricoJSON.setContentType("application/json");

			httpPost.setEntity(nuevoBiometricoJSON);
			CloseableHttpResponse servicioResponse = httpClient.execute(httpPost);

			String resultNuevoBiometrico;
			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				resultNuevoBiometrico = EntityUtils.toString(servicioResponse.getEntity());

				biometricoClientRestResponse = gson.fromJson(resultNuevoBiometrico, BiometricoClientRestResponse.class);
				break;
			case 400:
				resultNuevoBiometrico = EntityUtils.toString(servicioResponse.getEntity());
				throw new RESTClientException(resultNuevoBiometrico);
			default:
				throw new RESTClientException(servicioResponse.getStatusLine().getStatusCode() + " "
						+ servicioResponse.getStatusLine().getReasonPhrase());
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

		return biometricoClientRestResponse;
	}

	public BiometricoClientRestResponse asignarEmpleadoIdBiometrico(AsignarEmpleadoRegistroBiometricoForm model)
			throws RESTClientException {
		BiometricoClientRestResponse biometricoClientRestResponse = null;
		
		

		ClienteBiometricoFormModel clienteBiometricoFormModel = clienteBiometricoREST
				.buscarClienteBiometrico(buscarBiometrico(model.getIdBiometrico()).getIdClienteBiometrico());
		
		String urlCliente ="http://" + clienteBiometricoFormModel.getDireccionIP();
		if(clienteBiometricoFormModel.getPuerto() != 0){
			urlCliente = urlCliente + ":" + clienteBiometricoFormModel.getPuerto();
		}

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(url_serivicio + RESOURCE_DESCARGAR_INFORMACION);
		Gson gson = new Gson();
		StringEntity asignarBiometricoJSON;

		try {
			asignarBiometricoJSON = new StringEntity(gson.toJson(model));
			asignarBiometricoJSON.setContentType("application/json");

			httpPost.setEntity(asignarBiometricoJSON);
			CloseableHttpResponse servicioResponse = httpClient.execute(httpPost);

			String resultAsignarBiometrico;
			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				resultAsignarBiometrico = EntityUtils.toString(servicioResponse.getEntity());

				biometricoClientRestResponse = gson.fromJson(resultAsignarBiometrico,
						BiometricoClientRestResponse.class);
				break;
			case 400:
				resultAsignarBiometrico = EntityUtils.toString(servicioResponse.getEntity());
				throw new RESTClientException(resultAsignarBiometrico);
			default:
				throw new RESTClientException(servicioResponse.getStatusLine().getStatusCode() + " "
						+ servicioResponse.getStatusLine().getReasonPhrase());
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

		return biometricoClientRestResponse;
	}

	public BiometricoClientRestResponse actualizarBiometrico(BiometricoFormModel biometricoFormModel)
			throws RESTClientException {
		BiometricoClientRestResponse biometricoClientRestResponse = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPut httpPut = new HttpPut(url_serivicio + RESOURCE_ACTUALIZAR);
		Gson gson = new Gson();
		StringEntity nuevoBiometricoJSON;

		try {
			nuevoBiometricoJSON = new StringEntity(gson.toJson(biometricoFormModel));
			nuevoBiometricoJSON.setContentType("application/json");

			httpPut.setEntity(nuevoBiometricoJSON);
			CloseableHttpResponse servicioResponse = httpClient.execute(httpPut);

			String resultNuevoBiometrico;
			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				resultNuevoBiometrico = EntityUtils.toString(servicioResponse.getEntity());

				biometricoClientRestResponse = gson.fromJson(resultNuevoBiometrico, BiometricoClientRestResponse.class);
				break;
			case 400:
				resultNuevoBiometrico = EntityUtils.toString(servicioResponse.getEntity());
				throw new RESTClientException(resultNuevoBiometrico);
			default:
				throw new RESTClientException(servicioResponse.getStatusLine().getStatusCode() + " "
						+ servicioResponse.getStatusLine().getReasonPhrase());
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

		return biometricoClientRestResponse;
	}

	public BiometricoClientRestResponse actualizarBiometrico(BiometricoFormModel biometricoFormModel,
			String urlServicio) throws RESTClientException {
		BiometricoClientRestResponse biometricoClientRestResponse = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPut httpPut = new HttpPut(urlServicio + RESOURCE_ACTUALIZAR);
		Gson gson = new Gson();
		StringEntity nuevoBiometricoJSON;

		try {
			nuevoBiometricoJSON = new StringEntity(gson.toJson(biometricoFormModel));
			nuevoBiometricoJSON.setContentType("application/json");

			httpPut.setEntity(nuevoBiometricoJSON);
			CloseableHttpResponse servicioResponse = httpClient.execute(httpPut);

			String resultNuevoBiometrico;
			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				resultNuevoBiometrico = EntityUtils.toString(servicioResponse.getEntity());

				biometricoClientRestResponse = gson.fromJson(resultNuevoBiometrico, BiometricoClientRestResponse.class);
				break;
			case 400:
				resultNuevoBiometrico = EntityUtils.toString(servicioResponse.getEntity());
				throw new RESTClientException(resultNuevoBiometrico);
			default:
				throw new RESTClientException(servicioResponse.getStatusLine().getStatusCode() + " "
						+ servicioResponse.getStatusLine().getReasonPhrase());
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

		return biometricoClientRestResponse;
	}

	public BiometricoFormModel buscarBiometrico(Integer id) throws RESTClientException {

		BiometricoFormModel biometricoFormModel;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(url_serivicio + RESOURCE_BUSCAR_ID + id);

		try {
			CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);
			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				String resultBiometrico = EntityUtils.toString(servicioResponse.getEntity());
				Gson biometricoGson = new Gson();
				biometricoFormModel = biometricoGson.fromJson(resultBiometrico, BiometricoFormModel.class);
				break;
			case 400:
				throw new RESTClientException(servicioResponse.getStatusLine().getReasonPhrase());
			default:
				throw new RESTClientException(
						ListadoMensajesSistema.E000.getMensaje() + servicioResponse.getStatusLine().getStatusCode()
								+ " " + servicioResponse.getStatusLine().toString());
			}

		} catch (IOException e) {

			throw new RESTClientException(e.getMessage());
		}

		return biometricoFormModel;

	}

}
