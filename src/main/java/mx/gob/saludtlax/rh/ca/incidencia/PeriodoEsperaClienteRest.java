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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import mx.gob.saludtlax.rh.ca.ClienteRest;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.util.ListadoMensajesSistema;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@Stateless
public class PeriodoEsperaClienteRest extends ClienteRest implements Serializable {

	public PeriodoEsperaClienteRest() {
		super(ServicioWebEnum.CONTROL_ASISTENCIA_RS);

	}

	private static final long serialVersionUID = -6700504858124596163L;

	private final String RESOURCE_LISTADO_PERIODO_ESPERA_INCIDENCIAS = "/incidencia/periodo/espera/buscar/listado/";
	private final String RESOURCE_NUEVO_PERIODO_ESPERA_ASISTENCIA = "/incidencia/periodo/espera/nuevo";
	private final String RESOURCE_ELIMINA_PERIODO_ESPERA_INCIDENCIA = "/incidencia/periodo/espera/eliminar/";

	public List<PeriodoEsperaViewModel> listadoPeriodoEsperaIncidencia(Integer idIncidencia, Integer idTipoContraacion)
			throws RESTClientException {

		List<PeriodoEsperaViewModel> listadoPeriodoEsperaViewModel = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(
				url_serivicio + RESOURCE_LISTADO_PERIODO_ESPERA_INCIDENCIAS + idIncidencia + "/" + idTipoContraacion);

		try {
			CloseableHttpResponse servicioResponse = httpClient.execute(httpGet);

			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				String result = EntityUtils.toString(servicioResponse.getEntity());

				Gson incidenciaGson = new Gson();
				TypeToken<ArrayList<PeriodoEsperaViewModel>> tokenListado = new TypeToken<ArrayList<PeriodoEsperaViewModel>>() {
				};
				listadoPeriodoEsperaViewModel = incidenciaGson.fromJson(result, tokenListado.getType());

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

		return listadoPeriodoEsperaViewModel;

	}

	public void crearNuevoPeriodoEsperaIncidencia(PeriodoEsperaFormModel periodoEsperaFormModel)
			throws RESTClientException {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(url_serivicio + RESOURCE_NUEVO_PERIODO_ESPERA_ASISTENCIA);

		Gson gson = new Gson();
		StringEntity nuevoPeriodoEsperaIncidenciaFormModelJSON;
		try {
			nuevoPeriodoEsperaIncidenciaFormModelJSON = new StringEntity(gson.toJson(periodoEsperaFormModel));

			nuevoPeriodoEsperaIncidenciaFormModelJSON.setContentType("application/json");

			httpPost.setEntity(nuevoPeriodoEsperaIncidenciaFormModelJSON);

			CloseableHttpResponse servicioResponse = httpClient.execute(httpPost);

			String resultNuevoPeriodEsperaIncidencia;

			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				resultNuevoPeriodEsperaIncidencia = EntityUtils.toString(servicioResponse.getEntity());
				break;
			case 400:
				resultNuevoPeriodEsperaIncidencia = EntityUtils.toString(servicioResponse.getEntity());
				throw new RESTClientException(resultNuevoPeriodEsperaIncidencia);

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

	}

	public void elminarPeriodoEsperaIncidencia(Integer idPeriodoEsperaIncidenciaElminar) throws RESTClientException {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpDelete httpDelete = new HttpDelete(
				url_serivicio + RESOURCE_ELIMINA_PERIODO_ESPERA_INCIDENCIA + idPeriodoEsperaIncidenciaElminar);

		try {
			CloseableHttpResponse servicioResponse = httpClient.execute(httpDelete);

			switch (servicioResponse.getStatusLine().getStatusCode()) {
			case 200:
				String resultPeriodoEspera = EntityUtils.toString(servicioResponse.getEntity());
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

	}

}
